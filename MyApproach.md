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

- [ ] Find books by ISBN
- [ ] Expose result to user

ISBNs are unique to a publication but not to a specific book. So I need to make sure to test an edge
case where I have two books with the same ISBN.

Also, since ISBNs should be unique to a specific publication, other fields like author and title
should all be the same if the ISBN is the same between two books. I'm not going to worry about
implementing this validation for now, but I will make sure my test data follows this.

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

## Questions

1. Do we want to ignore capitalization for user queries?
   : I'm ignoring capitalization for user searches for now
