package interview.patchwork

import interview.patchwork.domain.Book
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly

class LibraryTest :
    FeatureSpec({
      feature("Lookup by author") {
        val book1 = Book(title = "Book 1", author = "John Doe", isbn = "1234", status = "Available")
        val book2 =
            Book(title = "Book 2", author = "John Smith", isbn = "1234", status = "Available")
        val book3 =
            Book(title = "Book 3", author = "Average Joe", isbn = "1234", status = "Available")
        val book4 =
            Book(title = "Book 4", author = "Average Joe", isbn = "1234", status = "Available")

        val library = Library(mutableListOf(book1, book2, book3, book4))

        scenario("One book by that author") {
          val result = library.findByAuthor("John Smith")
          result.shouldContainExactly(book2)
        }

        scenario("Two books by that author") {
          val result = library.findByAuthor("Average Joe")
          result.shouldContainExactly(book3, book4)
        }

        scenario("No books by that author") {
          val result = library.findByAuthor("New Kid on the Block")
          result.shouldBeEmpty()
        }
      }
    })
