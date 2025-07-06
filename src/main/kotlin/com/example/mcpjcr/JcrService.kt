package com.example.mcpjcr

import org.apache.jackrabbit.commons.JcrUtils
import javax.jcr.Repository
import javax.jcr.Session

class JcrService(private val repositoryUrl: String) {
    private val repository: Repository by lazy {
        JcrUtils.getRepository(repositoryUrl)
    }

    fun <T> executeInSession(block: (Session) -> T): T {
        var session: Session? = null
        try {
            session = repository.login()
            return block(session)
        } finally {
            session?.logout()
        }
    }
}
