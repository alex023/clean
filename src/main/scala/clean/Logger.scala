package clean

object Logger {
  def info(msg: String): Unit = println(msg)

  def debug(msg: String): Unit = println(msg)

  def warn(msg: String): Unit = println(msg)

  def warn(msg:Object):Unit=println(msg.toString)
}
