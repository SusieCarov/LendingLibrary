package interview.patchwork.domain

sealed interface BorrowProblem {
  data object BookNotFound : BorrowProblem

  data object BookAlreadyBorrowed : BorrowProblem

  data object BookReservedForReference : BorrowProblem
}
