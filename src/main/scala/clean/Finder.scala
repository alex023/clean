package clean

import java.io.File

import scala.io.Source

object Finder {
  //根据文件，按行读取并形成数组
  def initDict(fileName: String, encode: String = "UTF-8"): Array[String] = {
    val reader = Source.fromFile(fileName, encode)
    val result = reader.getLines().toArray
    reader.close()
    result
  }

  //  判断一个文本文件中，是否存在需要匹配的字符串
  private def seek(srcFile: File, find: String, encode: String = "UTF-8"): Boolean = {
    val reader = Source.fromFile(srcFile, encode)
    val lowerCaseFind = find.toLowerCase
    var founded = false
    try {
      founded = reader.
        getLines().
        to(LazyList).
        filter(_.trim().length != 0).
        exists(_.toLowerCase.contains(lowerCaseFind))
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      reader.close()
    }
    founded
  }

  def matchUsedTable(srcDir: String, file: String): Array[(String, Boolean)] = {
    initDict(file).map { it =>
      val srcFileDir = new File(srcDir)
      val result: Boolean = if (srcFileDir.exists()) {
        val files=DirFile.getFiles(srcFileDir)
        files.exists(seek(_, it))
      }
      else false
      it -> result
    }.sortBy(_._2)
  }

}