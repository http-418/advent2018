package advent2018

import scala.io.Source
import util.control.Breaks._

object Exercise2 {
  def main(args: Array[String]): Unit = {
    val input_data =
      Source.fromURL(advent2018.Exercise2.getClass.getResource("/exercise1.input"))

    val numbers = input_data.getLines.map((xx) => xx.toInt).toList
    
    val all_values =
      numbers.foldLeft(Vector.empty[(Int,Int)]) { (result, ee) =>
        result :+ (result.lastOption.getOrElse((0,0))._1 + ee,ee)
      }

    println("Last tuple: " + all_values.last)

    val totals = all_values.map(_._1)
    val iter = Iterator.continually(totals).flatten
    val seen = collection.mutable.Set[Int]()

    breakable {
    for ((value, idx) <- iter.zipWithIndex) {
      if(idx > 0 && idx % 1000 == 0) {
        println(idx + ", " + seen.size)
      }

      if(seen(value)){
        println(idx + ", " + seen.size)
        println(value)
        break
      }
      else
      {
        seen += value
      }
    }
    }

  }
}
