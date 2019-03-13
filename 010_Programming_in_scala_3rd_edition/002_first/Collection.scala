class Collection(length : Int) {
  val inner = new Array[Int](length)

  def apply(i : Int) = inner(i)

  def update(i : Int, v : Int) = inner(i) = v
}
