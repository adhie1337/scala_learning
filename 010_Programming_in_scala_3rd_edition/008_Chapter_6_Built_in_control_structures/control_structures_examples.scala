def conditionExample(i: Int) = if (i == 0) "Zero" else i.toString

val allFiles = new java.io.File("..").listFiles

for (file <- allFiles if file.isDirectory) println(file.getName)

def getScalaClassNames() = for {
  file <- allFiles
  if file.isDirectory
  innerFile <- file.listFiles
  if innerFile.isFile
  fileName = innerFile.getName
  if fileName(0).isUpper
  if fileName.endsWith(".scala")
} yield fileName.substring(0, fileName.length() - 6)

for (className <- getScalaClassNames()) println(className)


