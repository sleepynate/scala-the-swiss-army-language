package scala.swiss_army_language

import marconilanna.REPLesent.REPLesent

class Part1(intp: scala.tools.nsc.interpreter.IMain)
  extends REPLesent(input = "Part1.txt", intp=intp)

object Part1 {
  def apply(intp: scala.tools.nsc.interpreter.IMain):Part1 = {
    return new Part1(intp)
  }
}
