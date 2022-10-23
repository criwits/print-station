package top.criwits.print.tui

import scala.io.AnsiColor._

class MultiChoice(hint: String, choices: Seq[String]) {
  var choice = 0
  def printAllItems(currentIndex: Int): Unit = {
    ConsoleUtils.clear()
    println()
    println()
    println(s"  ${hint}")
    println()
    println(s"  请选择一个选项：")
    println()

    for (i <- choices.indices) {
      if (i == currentIndex) {
        println(s"    ${WHITE}${CYAN_B}${choices(i)}${RESET}")
      } else {
        println(s"    ${choices(i)}")
      }
    }

    println()
    println()
    println(s"  按 ${YELLOW}[↓]${RESET} 或 ${YELLOW}[J]${RESET} 选择下一项，${YELLOW}[↑]${RESET} 或 ${YELLOW}[K]${RESET} 选择上一项")
    println(s"  按 ${GREEN}[ENTER]${RESET} 或 ${GREEN}[SPACE]${RESET} 确认选择")
    println()
    println(s" ------------------------------------------------")
    println(s"  HPS (C) Hans WAN / 版本 0.0.1")
    println()
  }

  var key: Int = -1

  do {
    ConsoleUtils.clear()
    printAllItems(choice)
    key = ConsoleUtils.getKeyEvent()
    key match {
      case 0 => choice = (choice + 1) % choices.length
      case 1 => choice = (choice + choices.length - 1) % choices.length
      case _ =>
    }
  } while (key != 2)

  ConsoleUtils.clear()
}
