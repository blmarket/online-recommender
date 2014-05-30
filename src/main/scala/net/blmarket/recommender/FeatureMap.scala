package net.blmarket.recommender

import scala.collection.mutable

class FeatureMap {
  var items = mutable.HashMap[String, FeatureVector]()

  def apply(name: String): FeatureVector = {
    items.getOrElseUpdate(name, FeatureVector.createRandom)
  }

  def update(name: String, delta: FeatureVector) {
    items.update(name, apply(name) + delta)
  }
}
