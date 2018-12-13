package advent2018

import scala.io.Source

object Exercise1 {
  def main(args: Array[String]): Unit = {
    val input_data =
      Source.fromURL(getClass.getResource("/exercise1.input"))

    val result = input_data.getLines
      .map((xx) => xx.toInt)
      .reduce((aa,bb) => aa + bb)

    println(result)
  }
}
