package top.criwits.print.frame

import net.samuelcampos.usbdrivedetector.{USBDeviceDetectorManager, USBStorageDevice}
import top.criwits.print.tui.{InfoBox, MultiChoice}

object ChooseDisk {
  def apply(): USBStorageDevice = {
    // Get all devices
    val driver = new USBDeviceDetectorManager()
    val devices = driver.getRemovableDevices()

    if (devices.size() == 0) {
      // No device
      new InfoBox("目前没有已挂载的 U 盘",
        """
          |  目前似乎没有 USB 设备挂载。
          |  请检查设备是否已正常连接，然后再试一次。
          |""".stripMargin)

      null
    } else {
      var list = Seq("返回上一级")
      for (i <- 0 until devices.size()) {
        list ++= Seq(s"U 盘 #${i + 1} (${devices.get(i).getDevice}, ${devices.get(i).getDeviceName})")
      }
      val multiChoice = new MultiChoice("选择一个 USB 设备", list)
      if (multiChoice.choice > driver.getRemovableDevices.size()) {
        new InfoBox("出现了错误",
          """
            |  所请求的设备现在不存在。
            |  您是否在选择设备期间移除了设备？
            |""".stripMargin)
        null
      } else {
        if (multiChoice.choice == 0) {
          null
        } else {
          driver.getRemovableDevices.get(multiChoice.choice - 1)
        }
      }
    }
  }
}
