package interview.patchwork

import dev.forkhandles.result4k.Result
import interview.patchwork.domain.Book
import interview.patchwork.domain.BorrowProblem
import java.util.UUID

class Library(val books: MutableList<Book>) {
  fun findByAuthor(author: String): List<Book> {
    // TODO: Do we want to ignore capitalization
    // See MyApproach Question 1
    return books.filter { it.author.equals(author, ignoreCase = true) }
  }

  fun findByTitle(title: String): List<Book> {
    // TODO: Do we want to ignore capitalization
    // See MyApproach Question 1
    return books.filter { it.title.equals(title, ignoreCase = true) }
  }

  fun findByIsbn(isbn: String): List<Book> {
    // ISBN isn't really a string and will be all numbers or dashes, so not worrying about
    // ignoreCase
    return books.filter { it.isbn == isbn }
  }

  fun borrow(bookId: UUID): Result<Unit, BorrowProblem> {
    TODO()
  }
}
