package com.canopylabs.wordsearch

import org.scalatest.{Matchers, FlatSpec}

import scala.collection.immutable.HashMap

class TfLdfVectorSpec extends FlatSpec with Matchers {

  /**
   * Test against known:
   *
   * "Consider a document containing 100 words wherein the word cat appears 3 times.
   * The term frequency (i.e., tf) for cat is then (3 / 100) = 0.03. Now, assume we have
   * 10 million documents and the word cat appears in one thousand of these.
   * Then, the inverse document frequency (i.e., idf) is calculated as log(10,000,000 / 1,000) = 4.
   * Thus, the Tf-idf weight is the product of these quantities: 0.03 * 4 = 0.12."
   *
   * http://www.tfidf.com/
   */

  val wordFreqInDocument = HashMap("dog" -> 97, "cat" -> 3)
  val documentFreq = HashMap("cat" -> 1000)
  val numDocs = 10000000

  "TfLdfVector" should "produce correct value" in {
    val wordList = List("cat")
    val words = TfIdfVector.produceTfIdfVector(wordList, wordFreqInDocument, documentFreq, numDocs)
    words should equal (List(0.12))

  }

}
