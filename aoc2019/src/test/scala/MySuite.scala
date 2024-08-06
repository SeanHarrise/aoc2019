// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
// import main
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.shouldBe
import org.scalatest.prop.TableDrivenPropertyChecks

class MyOtherSuite extends AnyFlatSpec with TableDrivenPropertyChecks {

  private val fuelTable = Table(
    ("ModuleMass", "FuelRequired"),
    (9, 1),
    (10, 1)
  )

  it should "calculate correct fuel required" in {
    forEvery(fuelTable) { (moduleMass, expected) =>
      calculateFuelRequired(moduleMass) shouldBe expected
    }
  }

  private val intcodeTable = Table(
    ("input", "func", "output"),
    (Array(1,0,0,0,99), opcodeOne, Array(2,0,0,0,99)),
    (Array(2,3,0,3,99), opcodeTwo, Array(2,0,0,0,99)),
    (Array(2,4,4,5,99,0), opcodeTwo, Array(2,4,4,5,99,9801)),
    (Array(1,1,1,4,99), opcodeOne, Array(1,1,1,4,2)),
  )

  it should "return correct intcode" in {
    forEvery(intcodeTable) { (input, func, output) =>
      applyOpcode(input, 0, func) shouldBe output  
    }
  }
}

class MySuite extends munit.FunSuite {
  test("example test that succeeds") {
    val obtained = 43
    val expected = 43
    assertEquals(obtained, expected)
  }

  test("test calculate fuel required") {
    val obtained = calculateFuelRequired(9)
    val expected = 1
    assertEquals(obtained, expected)
  }
}
