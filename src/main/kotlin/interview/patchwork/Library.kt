package interview.patchwork

import interview.patchwork.domain.Book

class Library(val books: MutableList<Book>) {
  fun findByAuthor(author: String): List<Book> {
    // TODO: Do we want to ignore capitalization
    // See MyApproach Question 1
    return books.filter { it.author.equals(author, ignoreCase = true) }
  }
}
