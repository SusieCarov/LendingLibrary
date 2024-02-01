package interview.patchwork.domain

import java.util.UUID

data class Book(
    val title: String,
    val author: String,
    val isbn: String,
    val status: BookStatus,
    val id: UUID = UUID.randomUUID()
)

enum class BookStatus {
  Available,
  Borrowed
}
