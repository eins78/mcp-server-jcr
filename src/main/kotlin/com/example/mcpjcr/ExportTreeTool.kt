package com.example.mcpjcr

import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.jcr.Node

class ExportTreeTool(private val jcrService: JcrService) : McpTool {
    override val name = "exportTree"
    override val description = "Export an entire subtree as JSON for analysis"

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
                            "children",
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
