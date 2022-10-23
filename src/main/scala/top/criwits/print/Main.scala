package top.criwits.print

import com.typesafe.scalalogging.Logger
import net.samuelcampos.usbdrivedetector.USBDeviceDetectorManager
import top.criwits.print.frame.{About, ChooseDisk, DuplexChooser, FilePick, Finish, InfoCheck, PrintDoc, ProcessPDF, RemoveDisk, SetCopies, Welcome}
import top.criwits.print.pdf.PDFDoc
import top.criwits.print.tui.{ConsoleUtils, InfoBox, MultiChoice}

import scala.io.AnsiColor._
import java.io.{BufferedInputStream, BufferedReader, FileOutputStream, InputStreamReader}
import java.text.SimpleDateFormat
import java.util.Date

object Main {
  def main(args: Array[String]): Unit = {
    val logger = Logger("MAIN")

    logger.info("Welcome to Hans Print Station!")
    logger.info("Initialising...")

    while (true) {
      val welcomeChoice = Welcome()
      welcomeChoice match {
        case 0 =>
          val disk = ChooseDisk()
          if (disk != null) {
            val file = FilePick(disk.getRootDirectory.getAbsolutePath)
            if (file != null) {
              val pdf = new PDFDoc(file)
              if (pdf.document != null) {
                if (pdf.getNumberOfPages() == 1 || DuplexChooser(pdf) != null) {
                  SetCopies(pdf)
                  if (InfoCheck(pdf) != null) {
                    val startTime = System.currentTimeMillis()
                    if (pdf.duplex) {
                      ProcessPDF(pdf)
                      PrintDoc(pdf, pdf.oddPages, "奇数页")
                      new InfoBox("双面打印恢复提示",
                        s"""
                          |  您所要打印文档的奇数页（即所有纸张的“正面”）将很快打印完成。
                          |
                          |  如果打印机因缺纸而停止（机器亮红灯），请取纸放入进纸槽后，按打印机上的 ${BLUE_B}${WHITE}[取消/继续]${RESET} 键。
                          |  如果打印机卡纸，请联系 Hans 处理。
                          |
                          |  完成打印后，请将打印出的纸张 ${MAGENTA_B}${WHITE}保持原样${RESET} 放入进纸槽，
                          |  然后按电脑上的 ${GREEN}[ENTER]${RESET} 或 ${GREEN}[SPACE]${RESET}。
                          |  这样将继续打印偶数页（即所有纸张的“背面”）。
                          |
                          |  ${RED_B}${WHITE}注意：不用按机器上的“取消 / 继续”键。这和以前不一样。${RESET}
                          |""".stripMargin, 8)

                      PrintDoc(pdf, pdf.evenPages, "偶数页")
                    } else {
                      PrintDoc(pdf, pdf.document, "文档")
                    }
                    // Finished!
                    val endTime = System.currentTimeMillis()
                    val fos = new FileOutputStream("printstation.log", true)
                    fos.write(s"[${new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date)}] FILENAME=${pdf.file.getName} USED_PAGE_CNT=${pdf.getNumberOfPages()} DUPLEX=${pdf.duplex} COPIES=${pdf.copies} USED_TIME=${endTime - startTime}\n".getBytes())
                    fos.close()
                    pdf.close()
                    Finish()
                  } else {
                    pdf.close()
                  }
                } else {
                  pdf.close()
                }
              }
            }
          }
        case 1 => RemoveDisk()
        case 2 => About()
      }
    }

  }
}
