package clean

import org.slf4j.{Logger, LoggerFactory}

//通过给定的库名、表名文件以及源代码（包含配置文件）目录，通过模式查找寻找没有使用的库、表
//以简化部署
object Main {
  final val logger=LoggerFactory.getLogger(Main.getClass)
  def log: Logger = LoggerFactory.getLogger(Main.getClass)

  def main(args: Array[String]): Unit = {
    if ((args.length == 3) && DirFile.isExist(args)) {
      try {
        val databaseFile = args(0)
        val tableFile = args(1)
        val srcDir = args(2)
        logger.info("程序开始执行！")
        for ((value, idx) <- Finder.matchUsedTable(srcDir, databaseFile).zipWithIndex) prt(idx, value)
        for ((value, idx) <- Finder.matchUsedTable(srcDir, tableFile).zipWithIndex) prt(idx, value)
      } catch {
        case e: Exception =>
          e.getStackTrace.foreach(it => logger.warn(it.toString))
      }
    } else {
      logger.info("需要参数 库名文件 表名文件 源代码（含配置文件）目录")
    }

    def prt(num: Int, t: Tuple2[String, Boolean]): Unit = {
      def numToStr(num: Int): String = {
        val str = num.toString
        val buffer = new StringBuffer()
        for (_ <- 0 until (7 - str.length)) buffer.append(' ')
        buffer.append(str).toString
      }

      t._2 match {
        case true => logger.info(s"${numToStr(num)}  ${t._2}  ->  ${t._1} ")
        case false => logger.info(s"${numToStr(num)}  ${t._2} ->  ${t._1} ")
      }
    }
  }

}
