package top.criwits.print.frame

import top.criwits.print.tui.InfoBox

object About {
  def apply(): Unit = {
    new InfoBox("关于 Hans Print Station",
      s"""
        |  一个简单的 PDF 打印服务程序。
        |
        |  版本：0.0.1
        |  Scala 版本：2.13.10
        |  Java VM 版本：${System.getProperty("java.vm.version")}
        |""".stripMargin)
  }
}
