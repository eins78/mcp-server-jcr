package com.example.mcpjcr

import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.jcr.Node
import javax.jcr.query.Query

private const val DEFAULT_LIMIT = 100

class SearchByFullTextTool(private val jcrService: JcrService) : McpTool {
    override val name = "searchByFullText"
    override val description = "Perform full-text searches across content"

    override suspend fun execute(params: JsonObject): JsonElement =
        coroutineScope {
            val query =
                params["query"]?.toString()?.removeSurrounding("\"")
                    ?: throw IllegalArgumentException("Missing required parameter: query")

            val limit = params["limit"]?.toString()?.toIntOrNull() ?: DEFAULT_LIMIT

            jcrService.executeInSession { session ->
                val queryManager = session.workspace.queryManager
                val jcrQuery =
                    queryManager.createQuery(
                        "SELECT * FROM [nt:base] WHERE CONTAINS(*, '$query')",
                        Query.JCR_SQL2,
                    )
                jcrQuery.setLimit(limit.toLong())

                val result = jcrQuery.execute()

                buildJsonArray {
                    result.nodes.forEach { node ->
                        val jcrNode = node as Node
                        add(
                            buildJsonObject {
                                put("path", jcrNode.path)
                                put("primaryType", jcrNode.primaryNodeType.name)
                            },
                        )
                    }
                }
            }
        }
}
