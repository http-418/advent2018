package advent2018

import scala.io.Source
import util.control.Breaks._

object Swatch {
  val swatchRegex = "#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)".r

  def apply(ll: String): Swatch = {
    ll match {
          case swatchRegex(id, xx, yy, width, height) =>
            this(id.toString,xx.toInt,yy.toInt,width.toInt,height.toInt)
          case _ =>
            throw new Exception(ll + " did not match regex!")
    }
  }
}

case class Swatch(id: String, xs: Int, ys: Int, width: Int, length: Int) {
  //
  // The functional intersection approach was bad because I had no way
  // to handle intersections among > 2 swatches.
  //
  def intersection(target: Swatch): Swatch = {
    val new_id = s"${this.id} + ${target.id}"

    if(this == target)
    {
      // This is obviously not a correct result, but it makes the code
      // easier whem we are mapping across stuff.
      Swatch("identity",0,0,0,0)
    }
    else if(x_intersectp(target) && y_intersectp(target))
    {
      val new_xs = List(this.xs,target.xs).max
      val new_xe = List(this.xe,target.xe).min
      val new_width = new_xe - new_xs + 1

      val new_ys = List(this.xs,target.xs).max
      val new_ye = List(this.ye,target.ye).min
      val new_height = new_ye - new_ys + 1

      Swatch(new_id, new_xs, new_ys, new_width, new_height)
    }
    else
    {
      Swatch(new_id,0,0,0,0)
    }
  }

  def intersectp(target: Swatch): Boolean = {
    this.x_intersectp(target) && this.y_intersectp(target)
  }

  def x_intersectp(target: Swatch): Boolean = {
    !(target.xs > this.xe) &&
    !(target.xe < this.xs)
  }

  def y_intersectp(target: Swatch): Boolean = {
    !(target.ys > this.xe) &&
    !(target.ye < this.ys)
  }

  def area: Int = {
    width * length
  }

  def xe: Int = {
    xs + width - 1
  }

  def ye: Int = {
    ys + length - 1
  }

  def plot(canvas: Array[Array[Int]]): Unit = {
    for(xx <- (this.xs to this.xe))
    {
      for(yy <- (this.ys to this.ye))
      {
        canvas(xx)(yy) += 1
      }
    }
  }

  def plotted_area(canvas: Array[Array[Int]]): Int = {
    var acc = 0

    for(xx <- (this.xs to this.xe))
    {
      for(yy <- (this.ys to this.ye))
      {
        acc += canvas(xx)(yy)
      }
    }

    acc
  }
}
object Day3Ex1 {
  val input_data =
    Source.fromURL(advent2018.Exercise2.getClass.getResource("/day3ex1.input"))

  val swatches = input_data.getLines.toList.map(Swatch(_))

  val canvas = Array.ofDim[Int](1000,1000)

  def main(args: Array[String]): Unit = {

    // Then, plot them all in a 2d array to find out the total intersected area.

    for(sw <- swatches)
    {
      sw.plot(canvas)
      println(s"Plotted ${sw.id} (${sw.area})")
    }

    println("Total canvas area: " + canvas.flatten.length)
    println("Total plotted area: " + canvas.flatten.filter(_ > 0).length)
    println("Total intersections: " + canvas.flatten.filter(_ > 1).length)
  }
}
