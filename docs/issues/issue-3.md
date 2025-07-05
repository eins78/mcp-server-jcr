[Core] JCR Service Implementation
**Labels:** `core`, `phase-1`, `high-priority`
**Depends on:** #1

#### 🎯 Goal
Implement JcrService for managing JCR repository connections with session pooling.

#### 📋 Tasks
- [ ] Add Jackrabbit dependencies
- [ ] Create JcrService interface
- [ ] Implement session management with coroutines
- [ ] Add connection configuration
- [ ] Create unit tests with MockK

#### 📁 Files to Create
- `src/main/kotlin/com/example/mcpjcr/service/JcrService.kt`
- `src/main/kotlin/com/example/mcpjcr/service/impl/JcrServiceImpl.kt`
- `src/main/kotlin/com/example/mcpjcr/config/JcrConfig.kt`
- `src/main/kotlin/com/example/mcpjcr/model/JcrProperties.kt`
- `src/test/kotlin/com/example/mcpjcr/service/JcrServiceTest.kt`

#### 📚 References
- [Apache Jackrabbit Documentation](https://jackrabbit.apache.org/jcr/first-hops.html)
- [MVP Spec - Architecture](/docs/mvp-spec-2025-07-05.md#️-architecture-overview)
- [Kotlin Coroutines Guide](https://kotlinlang.org/docs/coroutines-guide.html)

#### 🔧 Implementation Details
```kotlin
interface JcrService {
    suspend fun <T> executeInSession(block: suspend (Session) -> T): T
    suspend fun isConnected(): Boolean
    fun getRepository(name: String = "default"): Repository
}
```

#### ✅ Acceptance Criteria
- Can connect to local JCR repository
- Session management works with coroutines
- Proper error handling for connection failures
- Unit tests pass with 80% coverage