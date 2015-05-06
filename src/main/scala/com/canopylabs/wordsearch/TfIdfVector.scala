package com.canopylabs.wordsearch

/**
 * "Consider a document containing 100 words wherein the word cat appears 3 times.
 * The term frequency (i.e., tf) for cat is then (3 / 100) = 0.03. Now, assume we have
 * 10 million documents and the word cat appears in one thousand of these.
 * Then, the inverse document frequency (i.e., idf) is calculated as log(10,000,000 / 1,000) = 4.
 * Thus, the Tf-idf weight is the product of these quantities: 0.03 * 4 = 0.12."
 *
 * http://www.tfidf.com/
 */

object TfIdfVector {
  def produceTfIdfVector(keys: List[String], searchFrequencyMap: Map[String, Int], documentsFrequency: Map[String, Int], numDocs: Int): List[Double] = {
    val totalDocumentWordCount = countWords(searchFrequencyMap)

    keys.map(k => {
      searchFrequencyMap.get(k).map(searchWordCount => {
        val tf: Double = calculateTf(searchWordCount, totalDocumentWordCount)
        val idf: Double = calculateIdf(documentsFrequency, numDocs, k)
        tf*idf
      }).getOrElse(0D)
    })
  }

  def calculateIdf(documentsFrequency: Map[String, Int], numDocs: Int, k: String): Double = {
    log(
      numDocs.toDouble
        /
        documentsFrequency.getOrElse(k, 0).toDouble
    )
  }

  def calculateTf(totalDocumentWordCount: Int, entryWordCount: Int): Double = {
    totalDocumentWordCount.toDouble / entryWordCount.toDouble
  }

  private def log(x : Double) = scala.math.log(x)/scala.math.log(10)
  private def countWords(input: Map[String, Int]) = input.foldLeft(0)((z, x) => z + x._2)
}
