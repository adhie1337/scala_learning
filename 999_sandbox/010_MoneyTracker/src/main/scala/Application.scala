import scala.io.StdIn

object Application extends App {
  private def getCurrentDirectory = new java.io.File(".").getCanonicalPath

  println(s"Current directory: $getCurrentDirectory")
  print("Load file: ")
  val file = StdIn.readLine

  val repository: Repository = Repository(s"data/$file.txt")

  repository.save()

  println(s"""contents: "${ repository.contents }"""")
}
