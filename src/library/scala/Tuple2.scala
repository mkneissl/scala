
/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2002-2008, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

// $Id$

// generated by genprod on Wed Apr 23 10:06:16 CEST 2008  (with extra methods)

package scala

import annotation.unchecked.uncheckedVariance

object Tuple2 {

  import collection.generic._
/* !!! todo: enable
  class IterableOps[CC[+B] <: Iterable[B] with IterableTemplate[CC, B @uncheckedVariance], A1, A2](tuple: (CC[A1], Iterable[A2])) {
    def zip: CC[(A1, A2)] = {
      val elems1 = tuple._1.elements
      val elems2 = tuple._2.elements
      val b = (tuple._1: IterableTemplate[CC, A1]).newBuilder[(A1, A2)]
        // : needed because otherwise it picks Iterable's builder.
      while (elems1.hasNext && elems2.hasNext)
        b += ((elems1.next, elems2.next))
      b.result
    }
    def map[B](f: (A1, A2) => B): CC[B] = {
      val elems1 = tuple._1.elements
      val elems2 = tuple._2.elements
      val b = (tuple._1: IterableTemplate[CC, A1]).newBuilder[B]
      while (elems1.hasNext && elems2.hasNext)
        b += f(elems1.next, elems2.next)
      b.result
    }
    def flatMap[B](f: (A1, A2) => CC[B]): CC[B] = {
      val elems1 = tuple._1.elements
      val elems2 = tuple._2.elements
      val b = (tuple._1: IterableTemplate[CC, A1]).newBuilder[B]
      while (elems1.hasNext && elems2.hasNext)
        b ++= f(elems1.next, elems2.next)
      b.result
    }
    def foreach(f: (A1, A2) => Unit) {
      val elems1 = tuple._1.elements
      val elems2 = tuple._2.elements
      while (elems1.hasNext && elems2.hasNext)
        f(elems1.next, elems2.next)
    }
    def forall(p: (A1, A2) => Boolean): Boolean = {
      val elems1 = tuple._1.elements
      val elems2 = tuple._2.elements
      while (elems1.hasNext && elems2.hasNext)
        if (!p(elems1.next, elems2.next)) return false
      true
    }
    def exists(p: (A1, A2) => Boolean): Boolean = {
      val elems1 = tuple._1.elements
      val elems2 = tuple._2.elements
      while (elems1.hasNext && elems2.hasNext)
        if (p(elems1.next, elems2.next)) return true
      false
    }
  }    
  implicit def tupleOfIterableWrapper[CC[+B] <: Iterable[B] with IterableTemplate[CC, B], A1, A2](tuple: (CC[A1], Iterable[A2])) =
    new IterableOps[CC, A1, A2](tuple)


/* A more general version which will probably not work.
  implicit def tupleOfIterableWrapper[CC[+B] <: Iterable[B] with IterableTemplate[CC, B], A1, A2, B1 <: CC[A1]](tuple: B1, Iterable[A2]) =
    new IterableOps[CC, A1, A2](tuple)
*/

  // Adriaan: If you drop the type parameters it will infer the wrong types.
  tupleOfIterableWrapper[collection.immutable.List, Int, Int]((collection.immutable.Nil, collection.immutable.Nil)) forall (_ + _ < 10)
*/
}

/** Tuple2 is the canonical representation of a @see Product2 
 *  
 */
case class Tuple2[+T1, +T2](_1:T1, _2:T2) 
  extends Product2[T1, T2]  {

   override def toString() = {
     val sb = new StringBuilder
     sb.append('(').append(_1).append(',').append(_2).append(')')
     sb.toString
   }
  
  /** Swap the elements of the tuple */
  def swap: Tuple2[T2,T1] = Tuple2(_2, _1)

}
