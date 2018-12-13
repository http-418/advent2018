package advent2018

import scala.io.Source
import util.control.Breaks._

object Day3Ex2 {
  val input_data =
    Source.fromURL(advent2018.Exercise2.getClass.getResource("/day3ex1.input"))

  val swatches = input_data.getLines.toList.map(Swatch(_))

  val intersections =
    swatches.map( (ii) => (ii, swatches.map(ii.intersectp(_))) ).toMap

  val canvas = Array.ofDim[Int](1000,1000)

  def main(args: Array[String]): Unit = {
    // Plot all the swatches
    for(sw <- swatches)
    {
      sw.plot(canvas)
      println(s"Plotted ${sw.id} (${sw.area})")
    }

    // Read back their actual areas from the plotted canvas
    val areas = swatches.map((sw) => (sw, sw.area, sw.plotted_area(canvas)))

    //println s"Swatch count: ${swatches.length}"
    //println s"Total swatch area: ${swatches.map(_.area).sum}"
  }
}
