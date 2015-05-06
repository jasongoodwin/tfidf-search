package com.canopylabs.wordsearch

import org.scalatest.{FlatSpec, Matchers}

import scala.collection.immutable.HashMap

class DocumentSpec extends FlatSpec with Matchers {
  //test resource
  val file = System.getProperty("user.dir") + "/src/test/resources/example_review.txt"

  "Document" should "produce dictionary from files" in {
    val dictionary = Document.produceFrequencyMap(file)
    dictionary.wordFrequencyMap.get("further").get should equal(1)
    dictionary.wordFrequencyMap.get("two").get should equal(2)
    dictionary.wordFrequencyMap.get("teen").get should equal(4)
  }

  "Document" should "produce total document frequency map from frequency maps" in {
    val dictionary1 = Document.produceFrequencyMap(file)
    val dictionary2 = Document("test", HashMap("further" -> 4, "two" -> 3))

    val res = Document.produceDocumentsFrequencyMap(List(dictionary1, dictionary2))
    res.get("further").get should equal(2)
    res.get("two").get should equal(2)
    res.get("teen").get should equal(1)
    res.get("slartibartfast").isDefined should equal(false)
  }


  //  it should "produce vector from dictionary for own words" in {
//    val vector = Dictionary.produceVectorForDictionary(exampleDictioary, exampleDictioary.keys.toList)
//    vector should equal(List(1,1,1,1,1))
//  }
}
