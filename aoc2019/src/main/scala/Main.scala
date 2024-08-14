import reader.Reader
import solution.DayThreeTaskOne

@main def hello(): Unit =
  val reader = Reader
  val crossedWires = solution.DayThreeTaskOne
  val crossedWiresInput = crossedWires.getFilepath()
  val lines = reader.getLines(crossedWiresInput)
  lines.foreach(println)

// def calculateFuelRequired(moduleMass : Int) : Int = {
//   moduleMass / 3 - 2
// }

// @tailrec
// def calculateFuelRequiredRecursive(remainingModuleMass : Int, fuelRequiredSoFar: Int) : Int = {
//   val fuelRequired = calculateFuelRequired(remainingModuleMass)
//   if (fuelRequired <= 0) {
//     fuelRequiredSoFar
//   } else {
//     calculateFuelRequiredRecursive(fuelRequired, fuelRequiredSoFar + fuelRequired)
//   }
// }

// def applyRecursiveFunctionToLines(lines : List[String], lineRecursiveFunction : (Int, Int) => Int) = {
//   lines.foldLeft(0) { (acc, line) =>
//     acc + lineRecursiveFunction(line.toInt, 0)
//   }
// }

// @tailrec
// def inner(inputOne : Int, current : Int, end: Int, intcode : Array[Int], func : (Array[Int], Int) => Array[Int]) : Array[Int] = {
//   print(s"-Inner $inputOne, $current ")
//   if (current > end) {
//     return Array(-1)
//   } else {
//     val inputState = Array(intcode(0), inputOne, current) ++ intcode.slice(3, intcode.length)
//     val outputState = func(inputState, 0)
//     if (outputState(0) == 19690720) {
//       return inputState
//     }
//     else (
//       return inner(inputOne, current + 1, end, intcode, func)
//     )
//   }
// }

// @tailrec
// def outer(current : Int, end : Int, intcode : Array[Int]) : Array[Int] = {
//   if (current > end) {
//     return Array(-1)
//   } else {
//     val input = inner(current, 0, 99, intcode, recursiveOpcode)
//     if (input(0) == -1) {
//       return outer(current + 1, end, intcode)
//     } else {
//       return input
//     }
//   }
// }