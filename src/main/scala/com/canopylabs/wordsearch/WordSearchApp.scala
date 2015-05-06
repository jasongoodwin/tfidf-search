package com.canopylabs.wordsearch

import java.io.File

object WordSearchApp {
  val nRecordsToFind = 10

  def main(args: Array[String]): Unit = {
    val argList = args.toList

    val folder = argList.headOption.getOrElse(
      throw new scala.Exception("Please supply folder as argument. " +
      "Eg for demo execute the following: " +
      "\n\tactivator 'run ./src/main/resources/source'"))

    val files: List[String] = getFilesInFolder(folder)
    println(s"found ${files.size} files - indexing files from $folder now... Please wait.")

    val documentSearchService = prepareSearchService(files)

    while(0==0){
      queryLoop(documentSearchService)
    }
  }

  def prepareSearchService(files: List[String]): DocumentSearchService = {
    val startTime = System.currentTimeMillis
    val indexes = Document.produceFrequencyMaps(files)
    println(s"individual document frequency maps took ${System.currentTimeMillis - startTime}...")
    val documentsStartTime = System.currentTimeMillis
    val documentFrequencyMap = Document.produceDocumentsFrequencyMap(indexes)
    println(s"documents frequency maps took ${System.currentTimeMillis - documentsStartTime}...")
    val documentSearchService = new DocumentSearchService(indexes, documentFrequencyMap, files.size)
    println(s"took ${System.currentTimeMillis - startTime} millis to start...")
    documentSearchService
  }

  def queryLoop(documentSearchService: DocumentSearchService): Unit = {
    val query = scala.io.StdIn.readLine("What would you like to search for? \n:")
    println(s"Searching: $query...\n")

    val freqMap = WordFrequencyMap.produceWordFrequencyMap(query.split(" ").toIterator)
    val res = documentSearchService.findTopNRecords(freqMap, nRecordsToFind)

    println(s"Top ${res.size} results: ")
    res.foreach(x => println(x.name + "\n"))
  }

  def getFilesInFolder(folder: String): List[String] = {
    val d = new File(folder)
    val files: List[String] = if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).map(x => x.getAbsolutePath).toList
    } else {
      throw new scala.Exception("Not a folder! Please supply a folder with files")
    }
    files
  }
}
