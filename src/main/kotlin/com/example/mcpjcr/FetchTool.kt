package com.example.mcpjcr

import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.jcr.Property

class FetchTool(private val jcrService: JcrService) : McpTool {
    override val name = "fetch"
    override val description = "Retrieve node data and properties by path"

    override suspend fun execute(params: JsonObject): JsonElement =
        coroutineScope {
            val path =
                params["path"]?.toString()?.removeSurrounding("\"")
                    ?: throw IllegalArgumentException("Missing required parameter: path")

            jcrService.executeInSession { session ->
                if (session.nodeExists(path)) {
                    val node = session.getNode(path)
                    buildJsonObject {
                        put("path", node.path)
                        put("primaryType", node.primaryNodeType.name)
                        put(
                            "properties",
                            buildJsonObject {
                                node.properties.forEach { prop ->
                                    val property = prop as Property
                                    put(property.name, property.value.toString())
                                }
                            },
                        )
                    }
                } else {
                    buildJsonObject {
                        put("error", "Node not found at path: $path")
                    }
                }
            }
        }
}
