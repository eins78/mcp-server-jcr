package com.example.mcpjcr

import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.jcr.Node

class ListChildrenTool(private val jcrService: JcrService) : McpTool {
    override val name = "listChildren"
    override val description = "List child nodes of a given path"

    override suspend fun execute(params: JsonObject): JsonElement =
        coroutineScope {
            val path =
                params["path"]?.toString()?.removeSurrounding("\"")
                    ?: throw IllegalArgumentException("Missing required parameter: path")

            jcrService.executeInSession { session ->
                if (session.nodeExists(path)) {
                    val node = session.getNode(path)
                    buildJsonArray {
                        node.nodes.forEach { childNode ->
                            val jcrChildNode = childNode as Node
                            add(
                                buildJsonObject {
                                    put("path", jcrChildNode.path)
                                    put("primaryType", jcrChildNode.primaryNodeType.name)
                                },
                            )
                        }
                    }
                } else {
                    buildJsonObject {
                        put("error", "Node not found at path: $path")
                    }
                }
            }
        }
}
