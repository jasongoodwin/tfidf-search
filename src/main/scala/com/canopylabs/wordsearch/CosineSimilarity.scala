package com.canopylabs.wordsearch

/**
 * Modified from:
 * https://gist.github.com/reuben-sutton/2932974
 */

object CosineSimilarity {

  /**
   * Will return 0 for NaN
   * @param x
   * @param y
   * @return
   */
  def cosineSimilarity(x: List[Double], y: List[Double]): Double = {
    require(x.size == y.size)
    val res = dotProduct(x, y)/(magnitude(x) * magnitude(y))
    if(java.lang.Double.isNaN(res)) 0 else res
  }

  private def dotProduct(x: List[Double], y: List[Double]): Double = {
    (for((a, b) <- x zip y) yield a * b) sum
  }

  private def magnitude(x: List[Double]): Double = {
    math.sqrt(x map(i => i*i) sum)
  }
}
