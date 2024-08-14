package intcode

import scala.annotation.tailrec
// import java.util.ArrayList

object IntcodeRunner {

  @tailrec
  def recursiveOpcode(intcode : Array[Int], i : Int) : Array[Int] = {
    if (intcode(i) == 99) {
      return intcode
    } else if (intcode(i) == 1) {
      val newIntcode = applyOpcode(intcode, i, opcodeOne)
      return recursiveOpcode(newIntcode, i + 4)
    } else {
      val newIntcode = applyOpcode(intcode, i, opcodeTwo)
      return recursiveOpcode(newIntcode, i + 4)
    }
  }

  def applyOpcode(intcode : Array[Int], i : Int, opcodeFunction : (Int, Int) => Int) : Array[Int] = {
    val arrayLeft = intcode.slice(0, intcode(i+3))
    val updatedIndex = Array(opcodeFunction(intcode(intcode(i+1)), intcode(intcode(i+2))))
    val arrayRight = intcode.slice(intcode(i+3)+1, intcode.length)
    arrayLeft ++ updatedIndex ++ arrayRight
  }

  def opcodeOne(a : Int, b : Int) : Int = {
    a + b
  }

  def opcodeTwo(a : Int, b : Int) : Int = {
    a * b
  }
}