package interview.patchwork.domain

import java.time.LocalDate
import java.util.UUID

data class Book(
    val title: String,
    val author: String,
    val isbn: String,
    var status: BookStatus,
    val id: UUID = UUID.randomUUID(),
    var lastBorrowTime: LocalDate? = null,
    var lastReturnTime: LocalDate? = null
)

enum class BookStatus {
  Available,
  Borrowed,
  Reference
}
