package com.example.mcpjcr

import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.jcr.Node
import javax.jcr.Property
import javax.jcr.query.Query

interface McpTool {
    val name: String
    val description: String

    suspend fun execute(params: JsonObject): JsonElement
}

private const val DEFAULT_LIMIT = 100

class QueryTool(private val jcrService: JcrService) : McpTool {
    override val name = "query"
    override val description = "Runs a JCR-SQL2 query and returns node paths"

    override suspend fun execute(params: JsonObject): JsonElement =
        coroutineScope {
            val query =
                params["query"]?.toString()?.removeSurrounding("\"")
                    ?: throw IllegalArgumentException("Missing required parameter: query")

            val limit = params["limit"]?.toString()?.toIntOrNull() ?: DEFAULT_LIMIT

            jcrService.executeInSession { session ->
                val queryManager = session.workspace.queryManager
                val jcrQuery = queryManager.createQuery(query, Query.JCR_SQL2)
                jcrQuery.setLimit(limit.toLong())

                val result = jcrQuery.execute()

                buildJsonObject {
                    put("query", query)
                    put(
                        "results",
                        buildJsonArray {
                            result.nodes.forEach { node ->
                                val jcrNode = node as Node
                                buildJsonObject {
                                    put("path", jcrNode.path)
                                    put("primaryType", jcrNode.primaryNodeType.name)
                                    put("created", jcrNode.getPropertyOrNull("jcr:created")?.string)
                                }
                            }
                        },
                    )
                    put("count", result.nodes.size.toString())
                }
            }
        }

    private fun Node.getPropertyOrNull(name: String): Property? = if (hasProperty(name)) getProperty(name) else null
}
