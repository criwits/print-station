package top.criwits.print.frame

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.printing.{PDFPrintable, Scaling}
import top.criwits.print.pdf.PDFDoc
import top.criwits.print.printer.PrintHelper
import top.criwits.print.tui.ConsoleUtils

import java.awt.print.{Book, PageFormat, Paper, PrinterJob}
import javax.print.attribute.HashPrintRequestAttributeSet

import scala.io.AnsiColor._

object PrintDoc {
  def apply(pdf: PDFDoc, doc: PDDocument, hint: String): Unit = {
    ConsoleUtils.clear()
    val printJob = PrinterJob.getPrinterJob()
    printJob.setJobName(pdf.file.getName)
    val printServices = PrinterJob.lookupPrintServices()
    val printService = printServices(0)
    printJob.setPrintService(printService)
    println(s"  已连接到打印机 ${printService.getName}")
    println(s"  正在向打印机发送${hint}，请稍等...")
    println()
    println(s" ------------------------------------------------")
    println(s"  HPS (C) Hans WAN / 版本 0.0.1")
    println()

    val PDFPrintable = new PDFPrintable(doc, Scaling.SCALE_TO_FIT)
    val book = new Book()
    val pageFormat = new PageFormat()
    pageFormat.setOrientation(PageFormat.PORTRAIT)
    def getPaper: Paper = {
      val paper = new Paper()
      paper.setSize(595, 842)
      paper.setImageableArea(0, 0, 595, 842)
      paper
    }
    pageFormat.setPaper(getPaper)
    book.append(PDFPrintable, pageFormat, doc.getNumberOfPages)

    printJob.setPageable(book)
    printJob.setCopies(pdf.copies)

    val pars = new HashPrintRequestAttributeSet()
    printJob.print(pars)

    ConsoleUtils.clear()
    println(s"  正在请求打印${hint}")
    println(s"  打印机将很快启动，请稍等...")
    println()
    println(s"  如果打印机因缺纸而停止（机器亮红灯），请取纸放入进纸槽后，按打印机上的 ${BLUE_B}${WHITE}[取消/继续]${RESET} 键。")
    println(s"  如果打印机卡纸，请联系 Hans 处理。")
    println()
    println(s" ------------------------------------------------")
    println(s"  HPS (C) Hans WAN / 版本 0.0.1")
    println()
    PrintHelper.waitEndJobs(printService)
  }
}
