package com.canopylabs.wordsearch

import org.scalatest.{Matchers, FlatSpec}

/**
 * functional spec
 */
class DocumentSearchServiceSpec extends FlatSpec with Matchers {

  val document1 = Document("a", Map("cat" -> 4, "bed" -> 7, "duck" ->99))
  val document2 = Document("b", Map("dog" -> 1, "joker" -> 7, "bacon" ->99))

  val searchService = new DocumentSearchService(List(document1, document2), Map("cat" -> 4, "dog" -> 1,  "bed" -> 7, "duck" ->99, "joker" -> 7, "bacon" ->99), 100)

  "DocumentSearchService" should "produce recommendation" in {
    val res = searchService.findTopNRecords(Map("dog" -> 1, "joker" -> 1, "bacon" -> 1), 1)
    res.head.name should equal("b")
  }
}
