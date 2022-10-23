package top.criwits.print.tui

import org.jline.keymap.KeyMap
import org.jline.terminal.TerminalBuilder

object ConsoleUtils{
  def clear(): Unit = {
    print("\u001b[2J")
  }

  private def getSingleKey(): Int = {
    val terminal = TerminalBuilder.builder()
      .jna(true)
      .system(true)
      .build()

    terminal.enterRawMode()
    val reader = terminal.reader()
    val key = reader.read()
    reader.close()
    terminal.close()

    key
  }

  private def getSingleKeyEvent(): Int = {
    val key1 = getSingleKey()
    if (key1 == 27) {
      if (getSingleKey() == 91) {
        getSingleKey() match {
          case 66 => 0
          case 65 => 1
          case _ => -1
        }
      } else {
        -1
      }
    } else {
      key1 match {
        case 106 | 74 => 0
        case 107 | 75 => 1
        case 13 | 32 => 2
        case x if 49 to 57 contains x => x - 38
        // case x if 97 to 105 contains x => x - 86
        case _ => -1
      }
    }
  }

  def getKeyEvent(): Int = {
    while (true) {
      val key = getSingleKeyEvent()
      if (key != -1) { return key }
    }
    -1
  }

}
