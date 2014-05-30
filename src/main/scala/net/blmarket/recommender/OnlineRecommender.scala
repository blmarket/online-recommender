package net.blmarket.recommender

class OnlineRecommender {
  val userFeatures = new FeatureMap()
  val itemFeatures = new FeatureMap()
  val lambda = 0.001 // regularization
  var alpha = -0.03 // learning rate

  /**
   * calculate cost for given feature vectors, score and its importance
   * @param ufv user's feature vector. size should be same with ifv
   * @param ifv item's feature vector. size should be same with ufv
   * @param prob score's importance. usually (0~1] is preferred range.
   * @param score user-item preference score.
   * @return calculated score and delta of two vectors
   */
  def costFunc(ufv: FeatureVector, ifv: FeatureVector, prob: Double, score: Double): (Double, FeatureVector, FeatureVector) = {
    val h = ufv * ifv - score
    val J = prob * h * h + lambda * (ufv * ufv + ifv * ifv)

    val userDelta = (ifv * h + ufv * (2 * lambda)) * alpha * prob
    val itemDelta = (ufv * h + ifv * (2 * lambda)) * alpha * prob

    (J, userDelta, itemDelta)
  }

  /**
   * train for given user-item score.
   * @param userName name of user
   * @param itemName name of item
   * @param prob score's importance. usually (0~1] is preferred range.
   * @param score user-item preference score.
   * @return calculated cost J
   */
  def feed(userName: String, itemName: String, prob: Double, score: Double): Double = {
    val cost = costFunc(userFeatures(userName), itemFeatures(itemName), prob, score)
    userFeatures.update(userName, cost._2)
    itemFeatures.update(itemName, cost._2)

    cost._1
  }

  /**
   * train for given user-(item*) score.
   * @param userName name of user.
   * @param scores sequence of (item, importance, score) tuple.
   * @return sum of calculated cost J
   */
  def feed(userName: String, scores: Seq[(String, Double, Double)]): Double = {
    scores.map(x => feed(userName, x._1, x._2, x._3)).sum
  }

  /**
   * predict score for given user-item pair
   * @param userName name of user
   * @param itemName name of item
   * @return predicted score.
   */
  def predict(userName: String, itemName: String): Double = {
    userFeatures(userName) * itemFeatures(itemName)
  }
}
