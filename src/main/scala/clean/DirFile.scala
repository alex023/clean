package clean

import java.io.File

object DirFile {
  final val SUFFIX =List("xml", "java", "yml", "properties","sql")
  //  获取任意多个目录下所有文件
  def getAllSubFiles(files: Array[File], legalSuffix: List[String] = SUFFIX ): Seq[File] = {
    files.filter(it => it.isFile && legalFile(it, legalSuffix)) ++
      files.filter(_.isDirectory).flatMap(dir => getAllSubFiles(dir.listFiles, legalSuffix))
  }


  //获取指定单个目录及其子目录下所有源代码及配置文件
  def getFiles(dir: File, legalSuffix: List[String] = SUFFIX): Array[File] = {
    dir.listFiles.filter(it => it.isFile && legalFile(it, legalSuffix)) ++
      dir.listFiles.filter(_.isDirectory).flatMap(getFiles(_, legalSuffix))
  }

  //根据文件名称和属性，判定文件是否为需判定的文本文件
  private def legalFile(file: File, legalSuffix: List[String]): Boolean = {
    if (file.isHidden) false
    else {
      val strs = file.getName.split("\\.")
      if ((strs.length > 1) && legalSuffix.exists(it => strs.last.toLowerCase.contains(it))) true
      else false
    }
  }

  def isExist(pathnames: Array[String]): Boolean = {
    var result = true
    pathnames.foreach { path =>
      val file = new File(path)
      if (!file.exists()) {
        logger.info(s"$path is not exist")
        result = false
      }
    }
    result
  }

}
