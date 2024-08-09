import scala.annotation.tailrec
import scala.io.Source
import java.util.ArrayList

@main def hello(): Unit =
  val filename = "../resources/day1a.txt"
  val bufferedSource = Source.fromFile(filename)
  val lines = getLines(filename)

  val intcode = getLines("../resources/day2.txt").head.split(",").map(a => a.toInt)
  val intcodeRestored = Array(intcode(0), 12, 2) ++ intcode.slice(3, intcode.length)
  // Array(1,0,0,0,99)
  // println(applyOpcode(Array(1,0,0,0,99), 0, opcodeOne).mkString(","))
  // println(intcodeRestored.mkString(","))
  println(recursiveOpcode(intcodeRestored, 0).mkString(","))
  // val answer2 = applyRecursiveFunctionToLines(lines, calculateFuelRequiredRecursive)
  // println(answer2)
  // val answer = applyFunctionToLines(lines, calculateFuelRequired)
  // println(answer)
  println(outer(0, 99, intcode).mkString(","))

def getLines(filePath : String) : List[String] = {
    val bufferedSource = Source.fromFile(filePath)
    try {
        bufferedSource.getLines().toList
    } finally {
        bufferedSource.close()
    }
}

def calculateFuelRequired(moduleMass : Int) : Int = {
  moduleMass / 3 - 2
}

def applyFunctionToLines(lines : List[String], lineFunction : Int => Int) = {
  lines.foldLeft(0) { (acc, line) =>
    acc + lineFunction(line.toInt)
  }
}

@tailrec
def calculateFuelRequiredRecursive(remainingModuleMass : Int, fuelRequiredSoFar: Int) : Int = {
  val fuelRequired = calculateFuelRequired(remainingModuleMass)
  if (fuelRequired <= 0) {
    fuelRequiredSoFar
  } else {
    calculateFuelRequiredRecursive(fuelRequired, fuelRequiredSoFar + fuelRequired)
  }
}

def applyRecursiveFunctionToLines(lines : List[String], lineRecursiveFunction : (Int, Int) => Int) = {
  lines.foldLeft(0) { (acc, line) =>
    acc + lineRecursiveFunction(line.toInt, 0)
  }
}

def applyOpcode(intcode : Array[Int], i : Int, opcodeFunction : (Int, Int) => Int) : Array[Int] = {
  val arrayLeft = intcode.slice(0, intcode(i+3)) // intcode(i+3)
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

@tailrec
def inner(inputOne : Int, current : Int, end: Int, intcode : Array[Int], func : (Array[Int], Int) => Array[Int]) : Array[Int] = {
  if (current > end) {
    return Array(-1)
  } else {
    val inputState = Array(intcode(0), inputOne, current) ++ intcode.slice(3, intcode.length)
    val outputState = func(inputState, 0)
    if (outputState(0) == 19690720) {
      return inputState
    }
    else (
      return inner(inputOne, current + 1, end, intcode, func)
    )
  }
}

@tailrec
def outer(current : Int, end : Int, intcode : Array[Int]) : Array[Int] = {
  if (current > end) {
    return Array(-1)
  } else {
    val input = inner(current, 0, 99, intcode, recursiveOpcode)
    if (input(0) == -1) {
      return outer(current + 1, end, intcode)
    } else {
      return input
    }
  }
}