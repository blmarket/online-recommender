package net.blmarket.recommender

import org.scalatest._

class FeatureVectorTest extends FlatSpec with Matchers {
  "FeatureVector" should "be created with random generator" in {
    val vec = FeatureVector.createRandom()
    vec.vec should have length 50
    assert(vec.vec(0) != vec.vec(1))
  }

  "FeatureVector" should "support add functionality" in {
    val vec = FeatureVector.createRandom()
    val vec2 = FeatureVector.createRandom()
    val v3 = vec + vec2

    assert(v3.vec(0) > vec.vec(0))
    assert(v3.vec(0) > vec2.vec(0))

    assert(v3.vec(49) > vec.vec(49))
    assert(v3.vec(49) > vec2.vec(49))
  }
}
