package top.criwits.print.frame

import top.criwits.print.pdf.PDFDoc
import top.criwits.print.tui.{InfoBox, MultiChoice}

object InfoCheck {
  def apply(doc: PDFDoc): PDFDoc = {
    val c = new MultiChoice(
      s"""请确认文件打印信息
        |
        |   打印文件：${doc.file.getName}
        |   文件页数：${doc.getNumberOfPages()}
        |   双面打印：${if (doc.duplex) { "是" } else { "否" }}
        |   打印份数：${doc.copies}
        |   计费参考：0.2 * ${if (doc.duplex) {doc.getNumberOfPages() / 2 + {if (doc.getNumberOfPages() % 2 == 0) { 0 } else { 1 }}} else {doc.getNumberOfPages()}} * ${doc.copies} = ${"%.1f".format(0.2*doc.copies*{if (doc.duplex) {doc.getNumberOfPages() / 2 + {if (doc.getNumberOfPages() % 2 == 0) { 0 } else { 1 }}} else {doc.getNumberOfPages()}})}
        |
        |""".stripMargin, Seq(
        "开始打印",
        "放弃打印"
      ))
    if (c.choice == 0) {
      doc
    } else {
      null
    }
  }

}
