package net.blmarket.recommender

import org.scalatest._

class OnlineRecommenderTest extends FlatSpec with Matchers {
  "OnlineRecommender" should "learn from data" in {
    val OR = new OnlineRecommender()
    for(i <- 1 to 10000) {
      OR.feed("Erika", Seq(("News", 5), ("Blog", 3), ("Naver", 1)))
      OR.feed("Jayce", Seq(("Blog", 5), ("Naver", 1), ("GOGO", 2)))
    }

    OR.calcScore("Erika", "News") should (be > 4.5 and be < 5.5)
    OR.calcScore("Erika", "Blog") should (be > 2.5 and be < 3.5)
    OR.calcScore("Erika", "Naver") should (be > 0.5 and be < 1.5)

    OR.calcScore("Jayce", "Blog") should (be > 4.5 and be < 5.5)
    OR.calcScore("Jayce", "Naver") should (be > 0.5 and be < 1.5)
    OR.calcScore("Jayce", "GOGO") should (be > 1.5 and be < 2.5)
  }
}
