package scala.swiss_army_language

import marconilanna.REPLesent.REPLesent

class Part3(intp: scala.tools.nsc.interpreter.IMain)
  extends REPLesent(input = "Part3.txt", slideCounter = true, slideTotal = true, intp=intp)

object Part3 {
  def apply(intp: scala.tools.nsc.interpreter.IMain):Part3 = {
    return new Part3(intp)
  }
}
