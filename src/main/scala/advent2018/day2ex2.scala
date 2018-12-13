package advent2018

import scala.io.Source
import util.control.Breaks._

object Day2Ex2 {

  // // Levenshtein is the wrong approach -- too darn slow
  // def levenshtein_distance(s1: String, s2: String): Map[(Int, Int), Int] = {
  //   val memoizedCosts = collection.mutable.Map[(Int, Int), Int]()
    
  //   def lev: ((Int, Int)) => Int = {
  //     case (k1, k2) =>
  //       memoizedCosts.getOrElseUpdate((k1, k2), (k1, k2) match {
  //                                       case (i, 0) => i
  //                                       case (0, j) => j
  //                                       case (i, j) =>
  //                                         collection.parallel.ParSeq(1 + lev((i - 1, j)),
  //                                                                    1 + lev((i, j - 1)),
  //                                                                    lev((i - 1, j - 1))
  //                                                                      + (if (s1(i - 1) != s2(j - 1)) 1 else 0)).min
  //                                     })
  //   }
    
  //   lev((s1.length, s2.length))
  //   memoizedCosts.toMap
  // }

  // Hamming distance uses fixed length strings and is much simpler.
  def hamming_distance(s1: String, s2: String): Int = {
    if(s1.length != s2.length) {
      throw new IllegalArgumentException("Strings must be equal length")
    }
    s1.zip(s2).filter((tt) => tt._1 != tt._2).length
  }

  def close_strings(values_map: Map[String,Int]): Seq[String] = {
    values_map.filter(_._2 == 1).keys.toSeq
  }

  def main(args: Array[String]): Unit = {
    val input_data =
      Source.fromURL(advent2018.Exercise2.getClass.getResource("/day2ex1.input"))

    val strings = input_data.getLines.toList
    val sums = strings.map(_.getBytes.map(_.toInt).sum)
    val sums_hash = strings.zip(sums).toMap

    // Rule out anything with excessive distance using simple sum of ascii values
    val candidates = strings.map((ii) => (ii, strings.filter((jj) => (sums_hash(ii) - sums_hash(jj)).abs <= 26))).toMap

    // Build a map of hamming distances on all pairs
    val distances = strings.map((ii) => (ii, candidates(ii).map((cc) => (cc, hamming_distance(ii,cc))).toMap)).toMap

    // Filter out only very close pairs
    val close_pairs = distances.map((kv) => (kv._1, close_strings(kv._2))).filter(_._2.size > 0)

    println(close_pairs)

    val final_targets = close_pairs.map((kv) => (kv._1, kv._2(0)))

    println(final_targets)

    for(kv <- final_targets){
      println(kv._1.zip(kv._2).filter((kv) => kv._1 == kv._2).map(_._1).mkString)
    }
  }
}
