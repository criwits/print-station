package top.criwits.print.frame

import org.apache.pdfbox.pdmodel.{PDDocument, PDPage}
import top.criwits.print.pdf.PDFDoc
import top.criwits.print.tui.ConsoleUtils

object ProcessPDF {
  def apply(pdf: PDFDoc): PDFDoc = {
    ConsoleUtils.clear()
    println(s"  正在处理文档分页，请稍等...")
    println()
    println(s" ------------------------------------------------")
    println(s"  HPS (C) Hans WAN / 版本 0.0.1")
    println()

    pdf.oddPages = new PDDocument()
    pdf.evenPages = new PDDocument()

    val allPages = pdf.document.getDocumentCatalog().getPages()
    var pages = pdf.getNumberOfPages()
    if (pages % 2 == 1) {
      val blankPage = new PDPage(allPages.get(0).getMediaBox)
      allPages.add(blankPage)
      pages = pages + 1
    }
    for (i <- 0 until pages) {
      val page = allPages.get(i)
      if ((i + 1) % 2 == 1) {
        pdf.oddPages.addPage(page)
      }
    }

    for (i <- 0 until pages) {
      val j = pages - i - 1
      val page = allPages.get(j)
      if ((j + 1) % 2 == 0) {
        page.setRotation(180)
        pdf.evenPages.addPage(page)
      }
    }
    pdf
  }
}
