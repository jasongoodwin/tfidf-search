package com.canopylabs.wordsearch

/**
 * Holds frequency maps and can query against them
 * @param indexes
 * @param documentsFrequencyMap
 * @param numDocs
 */

class DocumentSearchService(indexes: List[Document], documentsFrequencyMap: Map[String, Int], numDocs: Int) {

  def findTopNRecords(searchInput: Map[String, Int], n: Int): List[Document] = {
    val keys = searchInput.keys.toList

    val searchTfIdfVector =
      TfIdfVector.produceTfIdfVector(keys, searchInput, documentsFrequencyMap, numDocs)

    val indexesTfLdfVectors = indexes.map {
      case doc @ Document(x, y) =>
        val vector = TfIdfVector.produceTfIdfVector(keys, y, documentsFrequencyMap, numDocs)
        (x, vector, doc)
      case _ =>
        throw new IllegalStateException("Indexes did not contain expected types...")
    }

    val cosineSimilarity = indexesTfLdfVectors.map{
      case (name, vector, doc) =>
        (name, CosineSimilarity.cosineSimilarity(searchTfIdfVector, vector), doc)
    }
    
    takeTopN(n, cosineSimilarity)
  }

  private def takeTopN(n: Int, cosineSimilarity: List[(String, Double, Document)]): List[Document] = {
    cosineSimilarity
      .sortWith(_._2 > _._2)
      .take(n)
      .filter(_._2>0)
      .map(x => x._3)
  }
}
