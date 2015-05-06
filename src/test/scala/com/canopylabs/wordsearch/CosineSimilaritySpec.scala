package com.canopylabs.wordsearch

import org.scalatest.{Matchers, FlatSpec}

class CosineSimilaritySpec extends FlatSpec with Matchers {
  "CosineSimilarity" should "rank one vector higher than another" in {

    val low = CosineSimilarity.cosineSimilarity(List(0, 0, 1), List(3, 3, 3))
    val high = CosineSimilarity.cosineSimilarity(List(2, 2, 2), List(3, 3, 3))
    assert(high>low)
  }
}
