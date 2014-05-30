import java.io.{BufferedReader, FileReader}

object Main {
  def main(args: Array[String]) {
    val bfr = new BufferedReader(new FileReader("ml-1m/ratings.dat"))
    val arr = Iterator.continually(bfr.readLine).takeWhile(_ != null).map(_.split("::")).toArray
    println("Hello World")
  }
}
