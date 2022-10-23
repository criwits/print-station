package top.criwits.print.frame

import top.criwits.print.pdf.PDFDoc
import top.criwits.print.tui.{ConsoleUtils, InfoBox, NumberChoice}

import java.io.{BufferedReader, InputStreamReader}
import java.util.Scanner
import scala.io.AnsiColor._

object SetCopies {
  def apply(doc: PDFDoc): PDFDoc = {
    var copies = -1
      val c = new NumberChoice(
          """请设置打印份数（最少 1 份，最多 9 份）
          |  若要打印更多的份数，请分多次打印。
          |
          |  稍后，您有机会检查您的打印信息。
          |""".stripMargin)
      copies = c.choice
    doc.copies = copies

    doc
  }
}
