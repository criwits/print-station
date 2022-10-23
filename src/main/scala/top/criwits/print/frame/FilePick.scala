package top.criwits.print.frame

import top.criwits.print.tui.{ConsoleUtils, InfoBox}
import top.criwits.print.file.DirectoryRestrictedFileSystemView

import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileFilter

import scala.io.AnsiColor._

object FilePick {
 def apply(path: String): File = {
   ConsoleUtils.clear()
   ConsoleUtils.clear()
   println()
   println()
   println(s"  请在右侧选择需要打印的文件")
   println()
   println()
   println(s"  注意：仅支持 PDF 文件。")
   println()
   println(s" ------------------------------------------------")
   println(s"  HPS (C) Hans WAN / 版本 0.0.1")
   println()

   val fsv = new DirectoryRestrictedFileSystemView(new File(path))
   val fc = new JFileChooser(fsv)
   fc.setFileSelectionMode(JFileChooser.FILES_ONLY)
   fc.setFileFilter(new FileFilter {
     override def accept(file: File): Boolean = file.isDirectory || file.getName.toLowerCase().endsWith(".pdf")
     override def getDescription: String = "PDF Document (*.pdf)"
   })
   fc.showOpenDialog(null) match {
     case JFileChooser.CANCEL_OPTION =>
       new InfoBox("操作被取消",
         """
           |  您取消了文件选择。
           |""".stripMargin)
       return null
     case JFileChooser.ERROR_OPTION =>
       new InfoBox("发生了错误",
         """
           |  在读入文件时发生了错误。
           |""".stripMargin)
       return null
     case JFileChooser.APPROVE_OPTION =>
       val file = fc.getSelectedFile
       if (!file.getName.endsWith(".pdf")) {
         new InfoBox("您选择的不是 PDF 文件",
           """
             |  Print Station 只支持 PDF 文件的打印。
             |  感谢您的理解。
             |""".stripMargin)
         return null
       }
       return file
   }
 }
}
