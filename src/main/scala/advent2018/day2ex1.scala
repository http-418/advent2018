package advent2018

import scala.io.Source
import util.control.Breaks._

object Day2Ex1 {
  // Takes a box ID, returns character counts for items appearing 2 or 3 times
  def count_chars(box_id: String): Map[Char,Int] = {
    box_id.groupBy(identity(_))
      .map((kv) => (kv._1, kv._2.length))
      .filter((kv) => kv._2 > 1)
  }

  def main(args: Array[String]): Unit = {
    val input_data =
      Source.fromURL(advent2018.Exercise2.getClass.getResource("/day2ex1.input"))

    //
    // counts is a seq of bitfields -- is there a char with 3 units, etc
    //
    // this has to be a list and not an iterator because we use it twice
    //
    val counts =
      input_data.getLines.map((ll) => count_chars(ll)).map((mm) => mm.values.toSet).toList

    val threes = counts.filter((ss) => ss(3)).map(_ => 1).sum
    val twos = counts.filter((ss) => ss(2)).map(_ => 1).sum

    val checksum = threes * twos

    println(counts.length)
    println(threes)
    println(twos)
    println(checksum)
  }
}
