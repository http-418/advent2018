package advent2018

import scala.io.Source
import util.control.Breaks._

object Shift {
  def apply(lines: Seq[String]): Shift = {
    this(0, Array(true))
  }
}
case class Shift(guard_id: Int, minutes: Array[Boolean])

object Day4Ex1 {
  val input_data =
    Source.fromURL(advent2018.Exercise2.getClass.getResource("/day4.input")).getLines

  val shifts = collection.mutable.LinkedList[String]()

  val shift_start_regex =
    """.(\d+-\d+-\d+\s+\d+:\d+).\s+(Guard)""".r
  val wake_regex =
    ".(\\d+-\\d+-\\d+\\s+\\d+:\\d+). wakes up".r
  val sleep_regex =
    ".(\\d+-\\d+-\\d+\\s+\\d+:\\d+). falls asleep".r

  def split_shifts: Unit = {
    var current_shift = collection.mutable.LinkedList[String]()
    shifts.drop(_)

    for(ll <- input_data)
    {
      ll match {
        case shift_start_regex(dt, guard_id) =>
          if(current_shift.length > 0) { shifts :+ current_shift }
          current_shift = collection.mutable.LinkedList[String]()
          current_shift :+ ll
        case wake_regex(dt) =>
          current_shift :+ ll
        case sleep_regex(dt) =>
          current_shift :+ ll
        case _ =>
          println(s"${ll} failed to match regex!")
          None
      }
    }
  }

  def main(args: Array[String]): Unit = {
    split_shifts
  }
}
