package advent2018

import scala.io.Source
import util.control.Breaks._

object Day4Ex1 {
  val input_data =
    Source.fromURL(advent2018.Exercise2.getClass.getResource("/day4.input")).getLines

  val shifts = List[String]()

  val shift_start_regex = r"""\[(\d+-\d+\d+\s+\d+:\d+\)] Guard #(\d+) begins"""
  val wake_regex = r"""\[(\d+-\d+\d+\s+\d+:\d+\)] wakes up"""
  val sleep_regex = r"""\[(\d+-\d+\d+\s+\d+:\d+\)] falls asleep"""

  def main(args: Array[Sring]): Unit = {
    for(ll <- input_data)
    {
      var current_shift = List(String)()

      case shift_start_regex(dt, guard_id) =>
        None
      case wake_regex(dt) =>
        None
      case sleep_regex(dt) =>
        None
      case _ =>
        throw new Exception(ll + " did not match regex!")



    }
  }
}
