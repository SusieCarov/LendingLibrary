package interview.patchwork.domain

sealed interface BorrowProblem {
  object BookNotFound

  object BookNotAvailable
}
