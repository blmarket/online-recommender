package net.blmarket.recommender

import scala.collection.mutable

class OnlineRecommender {
  val userFeatures = new FeatureMap()
  val itemFeatures = new FeatureMap()
  val lambda = 0.001 // regularization
  var alpha = -0.03 // learning rate

  def costFunc(ufv: FeatureVector)(itemName: String, prob: Double, score: Double): (Double, FeatureVector, FeatureVector) = {
    val ifv = itemFeatures(itemName)
    val h = ufv * ifv - score
    val J = h*h + lambda * (ufv * ufv) + (ifv * ifv) * lambda

    val userDelta = (ifv * h + ufv * (2 * lambda)) * alpha * prob
    val itemDelta = (ufv * h + ifv * (2 * lambda)) * alpha * prob * 0.001

    (J, userDelta, itemDelta)
  }

  def feed(userName: String, scores: Seq[(String, Double, Double)]): Double = {
    val cf = costFunc(userFeatures(userName))_
    val tmp = scores.map(x => cf(x._1, x._2, x._3))
    val userDelta = tmp.map(x => (x._1, x._2)).reduce((x,y) => (x._1 + y._1, x._2 + y._2))

    userFeatures.update(userName, userDelta._2)
    scores.zip(tmp).map(x => (x._1._1, x._2._3)).foreach(x =>
      itemFeatures.update(x._1, x._2)
    )

    userDelta._1
  }

  def calcScore(userName: String, itemName: String): Double = {
    userFeatures(userName) * itemFeatures(itemName)
  }
}
