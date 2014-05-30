import java.io.{BufferedReader, FileReader}
import net.blmarket.recommender.OnlineRecommender
import scala.util.Random

object Main {
  def main(args: Array[String]) {
    val bfr = new BufferedReader(new FileReader("ml-1m/ratings.dat"))
    val arr = Random.shuffle(Iterator.continually(bfr.readLine).takeWhile(_ != null).map(_.split("::").take(3)).take(500000)).toIndexedSeq
    val train_pos = arr.length * 9 / 10

    val model = new OnlineRecommender()

    // Change this iteration count to change Total Diff
    for(i <- 1 to 1000000) {
      val obj = arr(Random.nextInt(train_pos))
      model.feed(obj(0), obj(1), 1.0, obj(2).toDouble)
      if((i % 100000) == 0) { // to display intermediate status
        println(i)
      }
    }

    var sum: Double = 0.0
    for(i <- train_pos until train_pos + 10) {
      val obj = arr(i)
      val score = model.predict(obj(0), obj(1))
      val diff = Math.abs(score - obj(2).toDouble)

      sum += diff
    }
    println("Total Diff : " + sum)
  }
}
