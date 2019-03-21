def init[T](xs: List[T]): List[T] = xs match {
  case List() => throw new Error("init of empty list")
  case List(x) => Nil
  case x :: xs => x :: init(xs)
}

def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
  case List() => ys
  case z  :: zs => z :: concat(zs, ys)
}

def reverse[T](xs: List[T]): List[T] = xs match {
  case List() => xs
  case y :: ys => ys.reverse ++ List(y)
}

def fastReverse[T](xs: List[T]): List[T] = {
  def rev(xs: List[T], result: List[T]): List[T] = xs match {
    case List() => result
    case y :: ys => rev(ys, y :: result)
  }

  rev(xs, List())
}

def removeAt[T](n: Int, xs: List[T]): List[T] = xs match {
  case List() => xs
  case y :: ys => if(n < 0) xs else if (n == 0) ys else y :: removeAt(n - 1, ys)
}

def fastRemoveAt[T](n: Int, xs: List[T]): List[T] = if (n <= 0 || n > xs.length) xs else ((xs take n ) ++ (xs drop n + 1))

def flatten[T](xs: List[List[T]]) = {
  def flatten(ys: List[List[T]], result: List[T]): List[T] = ys match {
    case List() => result
    case z :: zs => flatten(zs, concat(result, z))
  }
  flatten(xs, List())
}

def msort(xs: List[Int]): List[Int] = {
  val n = xs.length / 2

  if (n == 0) xs
  else {
    def merge(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
      case (Nil, ys) => ys
      case (xs, Nil) => xs
      case (x :: xs1, y :: ys1) => if (x < y) x :: merge(xs1, ys) else y :: merge(xs, ys1)
    }
    
    val (fst, snd) = xs splitAt n
    merge(msort(fst), msort(snd))
  }
}

def msortT[T](xs: List[T])(implicit ord: math.Ordering[T]): List[T] = {
  val n = xs.length / 2

  if (n == 0) xs
  else {
    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
      case (Nil, ys) => ys
      case (xs, Nil) => xs
      case (x :: xs1, y :: ys1) => if (ord.lt(x, y)) x :: merge(xs1, ys) else y :: merge(xs, ys1)
    }

    val (fst, snd) = xs splitAt n
    merge(msortT(fst)(ord), msortT(snd)(ord))
  }
}

def pack[T](xs: List[T]): List[List[T]] = {
  def pack(xs: List[T], value: T, result: List[T], allResult: List[List[T]]): List[List[T]] = xs match {
    case Nil => result :: allResult
    case y :: ys => 
      if (y == value) pack(ys, value, y::result, allResult)
      else pack(ys, y, List(y), result :: allResult)
  }

  xs match {
    case Nil => Nil
    case x :: xs => pack(xs, x, List(x), List()).reverse
  }
}

def fastPack[T](xs: List[T]): List[List[T]] = {
  def fastPack(xs: List[T], result: List[List[T]]): List[List[T]] = xs match {
    case Nil => result
    case x :: _ => {
      val (fst, snd) = xs span (x == _)

      fastPack(snd, fst :: result)
    }
  }

  fastPack(xs, Nil)
}

def encode[T](xs: List[T]): List[(T, Int)] = pack(xs) map (ys => (ys.head, ys.length))
