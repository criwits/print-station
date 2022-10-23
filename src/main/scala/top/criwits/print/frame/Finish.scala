package top.criwits.print.frame

import top.criwits.print.tui.InfoBox
import scala.io.AnsiColor._

object Finish {
  def apply(): Unit = {
    new InfoBox("打印将很快完成",
      s"""
        |  您的打印任务已完全提交到打印机。
        |  打印将很快完成。
        |
        |  如果打印机因缺纸而停止（机器亮红灯），请取纸放入进纸槽后，按打印机上的 ${BLUE_B}${WHITE}[取消/继续]${RESET} 键。
        |  如果打印机卡纸，请联系 Hans 处理。
        |
        |  请在打印完成后，取走打印好的文件，带走垃圾。
        |  您可在本系统主菜单选择 ${CYAN}[弹出 U 盘]${RESET} 功能安全地断开您的 U 盘。
        |""".stripMargin, 5)
  }
}
