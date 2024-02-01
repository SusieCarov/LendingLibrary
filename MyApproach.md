# LendingLibrary

## Tech Choices

- Kotlin
  : My most familiar language, and also the language that will be most used in the role

- http4k
  : I'm actually more familiar with Spring, but on the last stage of the interview y'all mentioned
  that you use http4k. I read up on it a bit, and it had a nice toolbox for getting a project set
  up, so I'm giving it a go!

- Kotest
  : I was between this and Junit and the different Testing Styles and other Kotlin features tipped
  me towards Kotest for this

- ktfmt
  : Opinionated style plugin to minimize fighting with formatting

- gitmoji
  : I'm trying to follow the [gitmoji](https://gitmoji.dev/) convention. This is a new habit I'm
  trying to build and I think
  it is a neat way to see at a glance what sort of changes a commit contains.

## Initial Thoughts

We've got 6 user stories to implement (love that they are user stories!) with two different user
profiles: user and owner. I'm not going to worry about user permissions unless I have time left.

There are some obvious domain objects that we'll need that will be shared across the stories. I'll
start with implementing those, and then I plan to move through the stories in order.

<details>
<summary>Common Domain objects</summary>

- [x] Book
    - author
    - title
    - isbn
    - status (lent/available)
    - (putting off reference for now, because I don't love the idea of a simple isReference boolean,
      and I'm hoping another solution will be more obvious later)
- [x] Library
    - collection of books

</details>


With user stories, this lends itself very well to BDD and TDD. Kotest has nice support for BDD style
spec that I'll try and use. If it ends up taking too much time, I'll pivot to more generic TDD.

## User Story 1

<details>

As a library user, I would like to be able to find books by my favourite author, so that I know if
they are available in the library.

- [x] Find books by author method
- [ ] Expose result to user

Okay! Got test framework running and these feature tests fail as expected. Time to start
implementing

I would ideally want to load book list into memory (no database) at start up from a csv or
something.

For now, I'll start with assuming the books are loaded.

Actually, exposing the result to user is going to be quite tricky for me since I'm not familiar with
http4k. So I'm going to leave that part out for now.
</details>

## User Story 2

<details>
As a library user, I would like to be able to find books by title, so that I know if they are
available in the library.

- [x] Find books by title method
- [ ] Expose result to user

Pretty straightforward, and again let's start with the tests.

</details>

## User Story 3

<details>
As a library user, I would like to be able to find books by ISBN, so that I know if they are available in the library.

- [x] Find books by ISBN
- [ ] Expose result to user

ISBNs are unique to a publication but not to a specific book. So I need to make sure to test an edge
case where I have two books with the same ISBN.

Also, since ISBNs should be unique to a specific publication, other fields like author and title
should all be the same if the ISBN is the same between two books. I'm not going to worry about
implementing this validation for now, but I will make sure my test data follows this.

</details>

## User Story 4

<details>
As a library user, I would like to be able to borrow a book, so I can read it at home.

- [x] Validate the book isn't already borrowed
- [x] Change the status of the book on a successful borrow function call
- [ ] Expose functionality to user

This is our first feature that modifies the state of the library!

We need a unique identifier for the books at this point. The ISBN is not enough because the library
can have multiple copies of the same book and thus they would have the same ISBN. And while we might
be able to get away with treated such multiple copies as interchangeable, we will likely want to
eventually track condition of the books, so better to have a way to uniquely identify the books.

For now, I'm going to use a UUID.

Also, I think this might be a good spot to use Result4k instead of a boolean, unit return with
exceptions, or similar. Having Library.kt return a Result with related Failure types will make a
nice abstraction layer(s) for the client interaction layer to map that to something meaningful to
the client (like an appropriate HTTP status code)
</details>

## User Story 5

<details>
As the library owner, I would like to know how many books are being borrowed, so I can see how many are outstanding.

- [x] Find books by Borrowed status
- [ ] Expose to the user

Relatively straightforward again. (Ah, wait. It says "how many books" not which ones... Could just
do a sum and return the number of books, but that is less extensible for future use cases. So I'm
going to assume they want a list of the books and make a note of this question below as normally I'd
clarify this with the product owner.)

First story we've had for the library owner, but I'm not going to worry about user permissions right
now as this story doesn't specify that normal users shouldn't be able to know this.
</details>

## User Story 6

<details>
As a library user, I should be to prevented from borrowing reference books, so that they are always available.

- [x] Figure out how to represent reference attribute on Book
- [x] Prevent reference books from being borrowed

I can think of a few ways to implement this (an isReference boolean, a genre Enum with a Reference
value, compositions/inheritance and move borrow to Book object, etc.)

For now, I'm going to go with making Reference a type of Status of a book. This solution came to me
when I was trying to figure out how to word the FeatureSpec tests.

Also, it has the benefit of putting Available and Reference as separate values for the same
attribute, lowering the risk of a bug being introduced that a user can borrow a reference book (
assuming the book must be Available to be borrowed)

I'm also going to introduce a new BorrowProblem to make it clear that the reason a given book can't
be borrowed is because it's a Reference book, not just that it isn't available.

</details>

## Not Doing For Now

<details>
<summary>Initialize Library Books</summary>

Since the assignment says
> Just prove it works by calling the relevant functions from other code.

I'm choosing not to worry about the initial loading/initialization of the library books for the app.

The test classes manually load in book objects and will prove the functions work, so that's good
enough for now. If I have time, I'll go back and try and add a csv load of some initial data.

</details>

<details>
<summary>Expose Features and Results to User</summary>

I'm not familiar enough with http4k to quickly get the Library functionality connected to the
router. So again, since the assignment says
> Just prove it works by calling the relevant functions from other code.

I'm going to leave this be for now and let calling the Library functions from the tests be enough
for now.

</details>

<details>
<summary> ISBN Validation </summary>

If the ISBN is the same between two books, then other core fields like author and title should be
equal too. This should be validated on the book being added to the library.
</details>

<details>
<summary> ISBN Domain object </summary>

ISBNs have a specif format that I'm ignoring for now. Implementing an ISBN domain object would allow
us to validate that format and make other ISBN related functionality easier to extend.
</details>

<details>
<summary> Return book functionality </summary>

We're obviously going to need it eventually, but it isn't in scope for the current user stories.
</details>

<details>
<summary> Use a Test Data Generator Tool</summary>

Something like https://serpro69.github.io/kotlin-faker/ would be very good to make sure our tests
aren't too brittle
</details>

<details>
<summary> User profiles</summary>

To start with, I would like to get a Library Owner and a User profile set-up with appropriate
feature permissions. Then this has a lot of room to grow to support other features like returning
books, adding new books, removing books from circulation, etc.
</details>

## Questions

1. Do we want to ignore capitalization for user queries?
   : I'm ignoring capitalization for user searches for now

2. What do we want the response to the user to be if the book is already borrowed?
   : I'm using a sealed interface internally, but ideally we'd have a nice human-readable
   message to show the user once it got back to them.

3. For User story 5, do we really want to know just how many books? Or also which books?
   : I'm going with which books for now as that is more extensible to future use cases.