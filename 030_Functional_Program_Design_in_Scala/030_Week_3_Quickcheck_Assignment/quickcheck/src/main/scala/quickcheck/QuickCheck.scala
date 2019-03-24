package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = oneOf(const(empty),
    for {
      i <- arbitrary[Int]
      prev <- oneOf(const(empty), genHeap)
    } yield insert(i, prev)
  )

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("min2") = forAll { (a: Int, b: Int) =>
    val h = insert(b, insert(a, empty))
    findMin(h) == (a min b)
  }

  property("del1") = forAll { a: Int =>
    val h = insert(a, empty)
    val h2 = deleteMin(h)

    isEmpty(h2)
  }

  property("min is min") = forAll { h: H =>
    def contents(h: H, result: List[A]): List[A] = if (isEmpty(h)) result else contents(deleteMin(h), findMin(h) :: result)
    val c = contents(h, List())

    c.reverse == c.sorted
  }

  property("min of meld") = forAll { (h1: H, h2: H) =>
    if(!isEmpty(h1) && !isEmpty(h2)){
      val min1 = findMin(h1)
      val min2 = findMin(h2)
      val bothMin = findMin(meld(h1, h2))

      bothMin == min1 || bothMin == min2
    } else true
  }

  property("gen1") = forAll { h: H =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    val newHeap = insert(m, h)
    val newMin = findMin(newHeap)

    newMin == m
  }

}
