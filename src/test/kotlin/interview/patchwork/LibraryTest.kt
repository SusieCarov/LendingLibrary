package interview.patchwork

import interview.patchwork.domain.Book
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly

class LibraryTest :
    FeatureSpec({
      val book1a =
          Book(title = "Intro to Coding", author = "John Doe", isbn = "1234", status = "Available")
      val book1b =
          Book(title = "Intro to Coding", author = "John Doe", isbn = "1234", status = "Available")
      val book2 =
          Book(
              title = "Intro to Coding", author = "John Smith", isbn = "2345", status = "Available")
      val book3 =
          Book(
              title = "Anyone Can Cook",
              author = "Average Joe",
              isbn = "3456",
              status = "Available")
      val book4 =
          Book(
              title = "Adventure is Out There!",
              author = "Average Joe",
              isbn = "4567",
              status = "Available")

      val library = Library(mutableListOf(book1a, book1b, book2, book3, book4))

      feature("Lookup by author") {
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

      feature("Lookup by title") {
        scenario("One book by that title") {
          val result = library.findByTitle("Anyone Can Cook")
          result.shouldContainExactly(book3)
        }

        scenario("Two books by that title") {
          val result = library.findByTitle("Intro to Coding")
          result.shouldContainExactly(book1a, book1b, book2)
        }

        scenario("No books by that title") {
          val result = library.findByTitle("The Princess Bride")
          result.shouldBeEmpty()
        }
      }
    })
