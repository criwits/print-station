package top.criwits.print.frame

import top.criwits.print.pdf.PDFDoc
import top.criwits.print.tui.MultiChoice

object DuplexChooser {
  def apply(pdf: PDFDoc): PDFDoc = {
    val chooser = new MultiChoice("您要进行双面打印还是单面打印？", Seq(
      "双面打印（推荐）",
      "单面打印",
      "放弃打印任务"
    ))

    chooser.choice match {
      case 0 => pdf.duplex = true
      case 1 => pdf.duplex = false
      case 2 => return null
    }

    pdf
  }
}
