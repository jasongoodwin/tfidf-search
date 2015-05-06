package com.canopylabs.wordsearch

import scala.collection.immutable.HashMap
import scala.io.Source

case class Document(name: String, wordFrequencyMap: Map[String, Int])

object Document {
  /**
   * From a list of files
   * @param files
   * @return
   */
  def produceFrequencyMaps(files: List[String]): List[Document] = {
    files.map(x => produceFrequencyMap(x))
  }

  /**
   * puts the file into memory as words
   * Could be Lazy by processing each line as a document. This is probably clearer.
   * @param filePath
   * @return
   */
  def produceFrequencyMap(filePath: String): Document = {
    val lines = getStreamFromFile(filePath) //could optimize - this breaks the stream's laziness
    val words = lines.flatMap(line => line.split(" "))
    Document(filePath, WordFrequencyMap.produceWordFrequencyMap(words).toMap)
  }

  /**
   * Produces a frequency map from all documents describing how many documents a word appears in.
   * This could potentially be done while generating the frequency maps for each document.
   * @param documents
   * @return
   */
  def produceDocumentsFrequencyMap(documents: List[Document]) = {
    documents.foldLeft(HashMap.empty[String, Int])((z,x) => {
      val keys = x.wordFrequencyMap.keys
      keys.foldLeft(z)((z,x) => {
        z.get(x)
          .map(value => z + (x -> (value + 1)))
          .getOrElse(z + (x -> 1))
      })
    })
  }

  private def getStreamFromFile(filePath: String): Iterator[String] = {
    Source.fromFile(filePath).getLines()
  }
}