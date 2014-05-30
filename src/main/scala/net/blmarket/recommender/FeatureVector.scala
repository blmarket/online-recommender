package net.blmarket.recommender

import scala.util.Random

object FeatureVector {
  /**
   * create new Feature Vector with 50 elements of (-1, 1) real number.
   * @return created FeatureVector object
   */
  def createRandom(): FeatureVector = {
    FeatureVector(Array.fill(50)(Random.nextDouble).map(_ * 2 - 1.0))
  }
}

case class FeatureVector(var vec: Array[Double]) {
  def +(rhs: FeatureVector): FeatureVector = {
    assert(rhs.vec.length == vec.length)
    FeatureVector(vec.zip(rhs.vec).map(x => x._1 + x._2))
  }

  def *(rhs: FeatureVector): Double = {
    assert(rhs.vec.length == vec.length)

    vec.zip(rhs.vec).map(x => x._1 * x._2).sum
  }

  def *(rhs: Double): FeatureVector = {
    FeatureVector(vec.map(_ * rhs))
  }

  override def toString(): String = vec.toList.toString
}
