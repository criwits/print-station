package top.criwits.print.tui

import top.criwits.print.tui.InfoBox.showInfoOnly

import java.util.Timer
import scala.Console._
import sys.process._

class InfoBox(title: String, content: String, sec: Int = 0, requiresCheck: Boolean = true) {
  var seconds = sec
  while (seconds > 0) {
    showInfoOnly(title, content, seconds)
    "sleep 1".!
    seconds -= 1
  }

  if (requiresCheck) {
    showInfoOnly(title, content, 0)

    var key = -1
    do {
      key = ConsoleUtils.getKeyEvent()
    } while (key != 2)
  }

}

object InfoBox {
  def showInfoOnly(title: String, content: String, sec: Int) = {
    ConsoleUtils.clear()
    println()
    println()
    println(s"  ${title}")
    println()
    println(s"  ${content}")
    println()
    println()
    if (sec == 0) {
      println(s"  按 ${GREEN}[ENTER]${RESET} 或 ${GREEN}[SPACE]${RESET} 确认此消息")
    } else {
      println(s"  请确保您已完整阅读并理解上面的消息（${sec} 秒）")
    }
    println()
    println(s" ------------------------------------------------")
    println(s"  HPS (C) Hans WAN / 版本 0.0.1")
    println()
  }
}
