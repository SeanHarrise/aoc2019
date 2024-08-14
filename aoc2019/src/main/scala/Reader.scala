package reader

import scala.io.Source

object Reader {

  def getLines(filePath : String) : List[String] = {
    val bufferedSource = Source.fromFile(filePath)
    try {
      bufferedSource.getLines().toList
    } finally {
      bufferedSource.close()
    }
  }

  def applyFunctionToLines(lines : List[String], lineFunction : Int => Int) = {
    lines.foldLeft(0) { (acc, line) =>
      acc + lineFunction(line.toInt)
    }
  }
}