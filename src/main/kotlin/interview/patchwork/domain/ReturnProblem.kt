package interview.patchwork.domain

sealed interface ReturnProblem {
  data object BookNotFound : ReturnProblem

  data object BookAlreadyReturned : ReturnProblem

  data object BookReservedForReference : ReturnProblem
}
