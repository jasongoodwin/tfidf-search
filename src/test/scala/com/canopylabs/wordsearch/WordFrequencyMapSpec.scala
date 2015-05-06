package com.canopylabs.wordsearch

import org.scalatest.{FlatSpec, Matchers}

import scala.collection.immutable.HashMap

class WordFrequencyMapSpec extends FlatSpec with Matchers {

  val example = "hello world! I am Steven! steven"
  val exampleDictioary = HashMap("hello" -> 1,
    "world" -> 1,
    "i" -> 1,
    "am" -> 1,
    "steven" -> 2)

  List("hello", "world", "i", "am", "jason")

  "Dictionary" should "produce dictionary from words" in {
    val wordList = example.split(" ").toIterator

    WordFrequencyMap.produceWordFrequencyMap(wordList) should equal(
      HashMap("hello" -> 1,
        "world" -> 1,
        "i" -> 1,
        "am" -> 1,
        "steven" -> 2)
    )
  }
}
