package com.canopylabs.wordsearch

import scala.collection.immutable.HashMap

object WordFrequencyMap {
  
  def produceWordFrequencyMap(input: Iterator[String],
                              frequencyMap: Map[String, Int] = HashMap.empty[String, Int]
                               ): Map[String, Int] = {
    sanitize(input)
      .foldLeft(frequencyMap)((z,x) => {
      z.get(x)
        .map(value => z + (x -> (value + 1)))
        .getOrElse(z + (x -> 1))
    })
  }

  def sanitize(input: Iterator[String]) = {
    input.map(_.toLowerCase)
      .map(word => word.filter(Character.isLetter))
      .filter(_.length() > 0)
  }
}
