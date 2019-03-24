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

  property("gen1") = forAll { h: H =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    val newHeap = insert(m, h)
    val newMin = findMin(newHeap)
    
    newMin == m
  }

}
