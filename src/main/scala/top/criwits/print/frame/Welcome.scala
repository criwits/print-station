package top.criwits.print.frame

import top.criwits.print.tui.MultiChoice
import scala.io.AnsiColor._

object Welcome {
  def apply(): Int = {
    val a = new MultiChoice(
      s"""欢迎使用 Hans Print Station！
        |
        |  本机 ${YELLOW}仅支持打印 PDF 文件${RESET}。对于其他的文件格式，
        |  请自行转换后再来打印。
        |
        |  若要打印，请插入 U 盘，然后选择下面的 ${CYAN}[开始打印]${RESET}。
        |  若已完成打印，请选择下面的 ${CYAN}[弹出 U 盘]${RESET} 来弹出您的 U 盘。
        |  若后续无人使用，请按一下电脑上的 ${YELLOW}[⏻]${RESET} 键来关机。""".stripMargin, Seq(
      "开始打印",
      "弹出 U 盘",
      "关于本系统"))
    a.choice
  }
}
