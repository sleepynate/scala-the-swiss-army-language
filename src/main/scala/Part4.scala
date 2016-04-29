package scala.swiss_army_language

import marconilanna.REPLesent.REPLesent

class Part4(intp: scala.tools.nsc.interpreter.IMain)
  extends REPLesent(input = "Part4.txt", slideCounter = true, slideTotal = true, intp=intp)

object Part4 {
  def apply(intp: scala.tools.nsc.interpreter.IMain):Part4 = {
    return new Part4(intp)
  }
}
