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
