package interview.patchwork

import interview.patchwork.domain.Book
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import java.util.UUID

fun Int.toUUID(): UUID =
    UUID.fromString("00000000-0000-0000-a000-${this.toString().padStart(11, '0')}")

class LibraryTest :
    FeatureSpec({
      val book1a =
          Book(
              title = "Intro to Coding",
              author = "John Doe",
              isbn = "1234",
              status = "Available",
              id = 0.toUUID())
      val book1b =
          Book(
              title = "Intro to Coding",
              author = "John Doe",
              isbn = "1234",
              status = "Available",
              id = 1.toUUID())
      val book2 =
          Book(
              title = "Intro to Coding",
              author = "John Smith",
              isbn = "2345",
              status = "Available",
              id = 2.toUUID())
      val book3 =
          Book(
              title = "Anyone Can Cook",
              author = "Average Joe",
              isbn = "3456",
              status = "Available",
              id = 3.toUUID())
      val book4 =
          Book(
              title = "Adventure is Out There!",
              author = "Average Joe",
              isbn = "4567",
              status = "Available",
              id = 4.toUUID())

      val library = Library(mutableListOf(book1a, book1b, book2, book3, book4))

      feature("Lookup by author") {
        scenario("One book by that author") {
          val result = library.findByAuthor("John Smith")
          result.shouldContainExactly(book2)
        }

        scenario("Multiple books by that author") {
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

        scenario("Multiple books by that title") {
          val result = library.findByTitle("Intro to Coding")
          result.shouldContainExactly(book1a, book1b, book2)
        }

        scenario("No books by that title") {
          val result = library.findByTitle("The Princess Bride")
          result.shouldBeEmpty()
        }
      }

      feature("Lookup by ISBN") {
        scenario("One book by that ISBN") {
          val result = library.findByIsbn("2345")
          result.shouldContainExactly(book2)
        }

        scenario("Multiple books by that ISBN") {
          val result = library.findByIsbn("1234")
          result.shouldContainExactly(book1a, book1b)
        }

        scenario("No books by that ISBN") {
          val result = library.findByIsbn("abcd")
          result.shouldBeEmpty()
        }
      }
    })
