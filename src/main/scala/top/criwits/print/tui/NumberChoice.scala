package top.criwits.print.tui

import scala.io.AnsiColor._

class NumberChoice(hint: String) {
  var choice = 0

  var key = 0
  do {
    ConsoleUtils.clear()
    println()
    println()
    println(s"  ${hint}")
    println()
    println(s"  按 ${CYAN}[1]${RESET} —— ${CYAN}[9]${RESET} 的数字键给出选择")
    println()
    println(s" ------------------------------------------------")
    println(s"  HPS (C) Hans WAN / 版本 0.0.1")
    println()

    key = ConsoleUtils.getKeyEvent()
    key match {
      case x if 11 to 19 contains x => choice = x - 10
      case _ =>
    }
  } while (!(key >= 11 && key <= 19))

}
