/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2009, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

// $Id$


package scala.collection.mutable

import generic._

trait Map[A, B] 
  extends Iterable[(A, B)]
     with collection.Map[A, B] 
     with MutableMapTemplate[A, B, Map[A, B]] 
     with Unhashable {

  override def empty: Map[A, B] = Map.empty
  override def mapBuilder[A, B]: Builder[(A, B), Map[A, B], Any] = Map.newBuilder[A, B]

  /** Return a read-only projection of this map.  !!! or just use an (immutable) MapProxy? 
  def readOnly : collection.Map[A, B] = new collection.Map[A, B] {
    override def size = self.size
    override def update(key: A, value: B) = self.update(key, value)
    override def - (elem: A) = self - elem
    override def elements = self.elements
    override def foreach(f: ((A, B)) => Unit) = self.foreach(f)
    override def empty[C] = self.empty[C]
    def get(key: A) = self.get(key)
  }
  */
}
/* Factory object for `Map` class
 * Currently this returns a HashMap.
 */
object Map extends MutableMapFactory[Map] {
  type Coll = Map[_, _]
  implicit def builderFactory[A, B]: BuilderFactory[(A, B), Map[A, B], Coll] = new BuilderFactory[(A, B), Map[A, B], Coll] { def apply(from: Coll) = from.mapBuilder[A, B] }
  def empty[A, B]: Map[A, B] = new HashMap[A, B]
}

