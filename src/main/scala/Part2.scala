package scala.swiss_army_language

import marconilanna.REPLesent.REPLesent

class Part2(intp: scala.tools.nsc.interpreter.IMain)
  extends REPLesent(input = "Part2.txt", slideCounter = true, slideTotal = true, intp=intp)

object Part2 {
  def apply(intp: scala.tools.nsc.interpreter.IMain):Part2 = {
    return new Part2(intp)
  }
}
