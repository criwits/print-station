package top.criwits.print.pdf

import org.apache.pdfbox.pdmodel.PDDocument
import top.criwits.print.tui.{ConsoleUtils, InfoBox}

import java.io.File

class PDFDoc(val file: File) {
  ConsoleUtils.clear()
  println(s"  正在读入 PDF 文件，请稍等...")
  println()
  println(s" ------------------------------------------------")
  println(s"  HPS (C) Hans WAN / 版本 0.0.1")
  println()
  var document: PDDocument = try {
    PDDocument.load(file)
  } catch {
    case e: Throwable => null
  }

  var duplex = false
  var oddPages: PDDocument = null
  var evenPages: PDDocument = null
  var copies = 1

  if (document == null) {
    new InfoBox("PDF 文件是损坏的",
      """
        |  您选择的 PDF 文件是损坏的。
        |  请选择其他文件。
        |""".stripMargin)
  }

  def getNumberOfPages() = document.getNumberOfPages
  if (document != null && getNumberOfPages() == 1) {
    duplex = false
  }

  def close(): Unit = {
    if (oddPages != null) {
      oddPages.close()
    }
    if (evenPages != null) {
      evenPages.close()
    }
    document.close()
  }

}
