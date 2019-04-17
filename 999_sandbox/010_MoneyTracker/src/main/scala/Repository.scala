import java.io.{File, FileWriter}

import scala.io.Source

case class Repository(fileName: String) {

  private var loadedContents: String = null: String

  private def file: File = new File(fileName)

  def fileExists: Boolean = file.exists

  def contents: String = {
    if (loadedContents == null) {
      loadedContents = if (fileExists) {
        val source = Source.fromFile(fileName)
        val result = source.getLines().mkString(s"\n")
        source.close()

        println(s"""Loaded from $file: "$result"""")

        result
      } else "hello"
    }

    loadedContents
  }

  def save(): Unit = {
    file.getParentFile.mkdirs()

    val writer = new FileWriter(file)
    writer.write(contents)
    writer.close()
  }

  override def toString: String
    = if (fileExists) super.toString
      else s"Repository($fileName) - not existing file"
}
