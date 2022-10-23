package top.criwits.print.frame

import top.criwits.print.tui.{ConsoleUtils, InfoBox}

import scala.sys.process._

object RemoveDisk {
  def apply(): Unit = {
    ConsoleUtils.clear()
    val chooseDisk = ChooseDisk()
    if (chooseDisk != null) {
      val unmount = s"udisksctl unmount -b ${chooseDisk.getDevice}".!
      val poweroff = s"udisksctl power-off -b ${chooseDisk.getDevice}".!

      if (unmount == 0 && poweroff == 0) {
        // success
        new InfoBox(s"已成功弹出设备",
          s"""
            |  已成功弹出设备 ${chooseDisk.getDevice}。
            |  请从电脑上拔下它。
            |""".stripMargin, 5, false)
      } else {
        new InfoBox(s"未能成功弹出设备 ${chooseDisk.getDevice}",
          s"""
            |  下面的信息可能有用：
            |  unmount 返回值：${unmount}
            |  power-off 返回值：${poweroff}
            |  请再试一次。
            |""".stripMargin)
      }
    }
  }
}
