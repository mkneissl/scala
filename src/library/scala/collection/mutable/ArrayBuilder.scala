/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2009, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

// $Id: ArrayBuffer.scala 18387 2009-07-24 15:28:37Z odersky $


package scala.collection.mutable

import scala.collection.generic._
import scala.reflect.ClassManifest
import scala.runtime.BoxedArray

/** A builder class for arrays */
class ArrayBuilder[A](manifest: ClassManifest[A]) extends Builder[A, BoxedArray[A]] {

  private var elems: BoxedArray[A] = _
  private var capacity: Int = 0
  private var size: Int = 0

  private def mkArray(size: Int): BoxedArray[A] = {
    if (false && manifest != null) { // !!!
      val newelems = manifest.newArray(size)
      if (this.size > 0) Array.copy(elems.value, 0, newelems.value, 0, this.size)
      newelems
    } else { // !!!
      val newelems = new scala.runtime.BoxedAnyArray[A](size)
      if (this.size > 0) Array.copy(elems, 0, newelems, 0, this.size)
      newelems
    }
  }

  private def resize(size: Int) {
    elems = mkArray(size)
    capacity = size
  }
  
  override def sizeHint(size: Int) {
    if (capacity < size) resize(size)
  }

  private def ensureSize(size: Int) {
    if (capacity < size) {
      var newsize = if (capacity == 0) 16 else capacity * 2
      while (newsize < size) newsize *= 2
      resize(newsize)
    }
  } 
  
  def +=(elem: A): this.type = {
    ensureSize(size + 1)
    elems(size) = elem
    size += 1
    this
  }

  def clear() {
    size = 0
  }

  def result() = {
    if (capacity != 0 && capacity == size) elems 
    else mkArray(size)
  }

  // todo: add ++=
}

object ArrayBuilder {

  class ofRef[T <: AnyRef] extends Builder[T, Array[AnyRef]] {

    private var elems: Array[AnyRef] = _
    private var capacity: Int = 0
    private var size: Int = 0

    private def mkArray(size: Int): Array[AnyRef] = {
      val newelems = new Array[AnyRef](size)
      if (this.size > 0) Array.copy(elems, 0, newelems, 0, this.size)
      newelems
    }

    private def resize(size: Int) {
      elems = mkArray(size)
      capacity = size
    }

    override def sizeHint(size: Int) {
      if (capacity < size) resize(size)
    }

    private def ensureSize(size: Int) {
      if (capacity < size) {
        var newsize = if (capacity == 0) 16 else capacity * 2
        while (newsize < size) newsize *= 2
        resize(newsize)
      }
    } 

    def +=(elem: T): this.type = {
      ensureSize(size + 1)
      elems(size) = elem
      size += 1
      this
    }

    override def ++=(xs: scala.collection.Traversable[T]): this.type = (xs: AnyRef) match {
      case xs: WrappedArray.ofRef[_] =>
        ensureSize(this.size + xs.length)
        Array.copy(elems, this.size, xs.array, 0, xs.length)
        size += xs.length
        this
      case _ =>
        super.++=(xs)
    }

    def clear() {
      size = 0
    }

    def result() = {
      if (capacity != 0 && capacity == size) elems 
      else mkArray(size)
    }
  }

  class ofByte extends Builder[Byte, Array[Byte]] {

    private var elems: Array[Byte] = _
    private var capacity: Int = 0
    private var size: Int = 0

    private def mkArray(size: Int): Array[Byte] = {
      val newelems = new Array[Byte](size)
      if (this.size > 0) Array.copy(elems, 0, newelems, 0, this.size)
      newelems
    }

    private def resize(size: Int) {
      elems = mkArray(size)
      capacity = size
    }

    override def sizeHint(size: Int) {
      if (capacity < size) resize(size)
    }

    private def ensureSize(size: Int) {
      if (capacity < size) {
        var newsize = if (capacity == 0) 16 else capacity * 2
        while (newsize < size) newsize *= 2
        resize(newsize)
      }
    } 

    def +=(elem: Byte): this.type = {
      ensureSize(size + 1)
      elems(size) = elem
      size += 1
      this
    }

    override def ++=(xs: scala.collection.Traversable[Byte]): this.type = xs match {
      case xs: WrappedArray.ofByte =>
        ensureSize(this.size + xs.length)
        Array.copy(elems, this.size, xs.array, 0, xs.length)
        size += xs.length
        this
      case _ =>
        super.++=(xs)
    }

    def clear() {
      size = 0
    }

    def result() = {
      if (capacity != 0 && capacity == size) elems 
      else mkArray(size)
    }
  }

  class ofShort extends Builder[Short, Array[Short]] {

    private var elems: Array[Short] = _
    private var capacity: Int = 0
    private var size: Int = 0

    private def mkArray(size: Int): Array[Short] = {
      val newelems = new Array[Short](size)
      if (this.size > 0) Array.copy(elems, 0, newelems, 0, this.size)
      newelems
    }

    private def resize(size: Int) {
      elems = mkArray(size)
      capacity = size
    }

    override def sizeHint(size: Int) {
      if (capacity < size) resize(size)
    }

    private def ensureSize(size: Int) {
      if (capacity < size) {
        var newsize = if (capacity == 0) 16 else capacity * 2
        while (newsize < size) newsize *= 2
        resize(newsize)
      }
    } 

    def +=(elem: Short): this.type = {
      ensureSize(size + 1)
      elems(size) = elem
      size += 1
      this
    }

    override def ++=(xs: scala.collection.Traversable[Short]): this.type = xs match {
      case xs: WrappedArray.ofShort =>
        ensureSize(this.size + xs.length)
        Array.copy(elems, this.size, xs.array, 0, xs.length)
        size += xs.length
        this
      case _ =>
        super.++=(xs)
    }

    def clear() {
      size = 0
    }

    def result() = {
      if (capacity != 0 && capacity == size) elems 
      else mkArray(size)
    }
  }

  class ofChar extends Builder[Char, Array[Char]] {

    private var elems: Array[Char] = _
    private var capacity: Int = 0
    private var size: Int = 0

    private def mkArray(size: Int): Array[Char] = {
      val newelems = new Array[Char](size)
      if (this.size > 0) Array.copy(elems, 0, newelems, 0, this.size)
      newelems
    }

    private def resize(size: Int) {
      elems = mkArray(size)
      capacity = size
    }

    override def sizeHint(size: Int) {
      if (capacity < size) resize(size)
    }

    private def ensureSize(size: Int) {
      if (capacity < size) {
        var newsize = if (capacity == 0) 16 else capacity * 2
        while (newsize < size) newsize *= 2
        resize(newsize)
      }
    } 

    def +=(elem: Char): this.type = {
      ensureSize(size + 1)
      elems(size) = elem
      size += 1
      this
    }

    override def ++=(xs: scala.collection.Traversable[Char]): this.type = xs match {
      case xs: WrappedArray.ofChar =>
        ensureSize(this.size + xs.length)
        Array.copy(elems, this.size, xs.array, 0, xs.length)
        size += xs.length
        this
      case _ =>
        super.++=(xs)
    }

    def clear() {
      size = 0
    }

    def result() = {
      if (capacity != 0 && capacity == size) elems 
      else mkArray(size)
    }
  }

  class ofInt extends Builder[Int, Array[Int]] {

    private var elems: Array[Int] = _
    private var capacity: Int = 0
    private var size: Int = 0

    private def mkArray(size: Int): Array[Int] = {
      val newelems = new Array[Int](size)
      if (this.size > 0) Array.copy(elems, 0, newelems, 0, this.size)
      newelems
    }

    private def resize(size: Int) {
      elems = mkArray(size)
      capacity = size
    }

    override def sizeHint(size: Int) {
      if (capacity < size) resize(size)
    }

    private def ensureSize(size: Int) {
      if (capacity < size) {
        var newsize = if (capacity == 0) 16 else capacity * 2
        while (newsize < size) newsize *= 2
        resize(newsize)
      }
    } 

    def +=(elem: Int): this.type = {
      ensureSize(size + 1)
      elems(size) = elem
      size += 1
      this
    }

    override def ++=(xs: scala.collection.Traversable[Int]): this.type = xs match {
      case xs: WrappedArray.ofInt =>
        ensureSize(this.size + xs.length)
        Array.copy(elems, this.size, xs.array, 0, xs.length)
        size += xs.length
        this
      case _ =>
        super.++=(xs)
    }

    def clear() {
      size = 0
    }

    def result() = {
      if (capacity != 0 && capacity == size) elems 
      else mkArray(size)
    }
  }

  class ofLong extends Builder[Long, Array[Long]] {

    private var elems: Array[Long] = _
    private var capacity: Int = 0
    private var size: Int = 0

    private def mkArray(size: Int): Array[Long] = {
      val newelems = new Array[Long](size)
      if (this.size > 0) Array.copy(elems, 0, newelems, 0, this.size)
      newelems
    }

    private def resize(size: Int) {
      elems = mkArray(size)
      capacity = size
    }

    override def sizeHint(size: Int) {
      if (capacity < size) resize(size)
    }

    private def ensureSize(size: Int) {
      if (capacity < size) {
        var newsize = if (capacity == 0) 16 else capacity * 2
        while (newsize < size) newsize *= 2
        resize(newsize)
      }
    } 

    def +=(elem: Long): this.type = {
      ensureSize(size + 1)
      elems(size) = elem
      size += 1
      this
    }

    override def ++=(xs: scala.collection.Traversable[Long]): this.type = xs match {
      case xs: WrappedArray.ofLong =>
        ensureSize(this.size + xs.length)
        Array.copy(elems, this.size, xs.array, 0, xs.length)
        size += xs.length
        this
      case _ =>
        super.++=(xs)
    }

    def clear() {
      size = 0
    }

    def result() = {
      if (capacity != 0 && capacity == size) elems 
      else mkArray(size)
    }
  }

  class ofFloat extends Builder[Float, Array[Float]] {

    private var elems: Array[Float] = _
    private var capacity: Int = 0
    private var size: Int = 0

    private def mkArray(size: Int): Array[Float] = {
      val newelems = new Array[Float](size)
      if (this.size > 0) Array.copy(elems, 0, newelems, 0, this.size)
      newelems
    }

    private def resize(size: Int) {
      elems = mkArray(size)
      capacity = size
    }

    override def sizeHint(size: Int) {
      if (capacity < size) resize(size)
    }

    private def ensureSize(size: Int) {
      if (capacity < size) {
        var newsize = if (capacity == 0) 16 else capacity * 2
        while (newsize < size) newsize *= 2
        resize(newsize)
      }
    } 

    def +=(elem: Float): this.type = {
      ensureSize(size + 1)
      elems(size) = elem
      size += 1
      this
    }

    override def ++=(xs: scala.collection.Traversable[Float]): this.type = xs match {
      case xs: WrappedArray.ofFloat =>
        ensureSize(this.size + xs.length)
        Array.copy(elems, this.size, xs.array, 0, xs.length)
        size += xs.length
        this
      case _ =>
        super.++=(xs)
    }

    def clear() {
      size = 0
    }

    def result() = {
      if (capacity != 0 && capacity == size) elems 
      else mkArray(size)
    }
  }

  class ofDouble extends Builder[Double, Array[Double]] {

    private var elems: Array[Double] = _
    private var capacity: Int = 0
    private var size: Int = 0

    private def mkArray(size: Int): Array[Double] = {
      val newelems = new Array[Double](size)
      if (this.size > 0) Array.copy(elems, 0, newelems, 0, this.size)
      newelems
    }

    private def resize(size: Int) {
      elems = mkArray(size)
      capacity = size
    }

    override def sizeHint(size: Int) {
      if (capacity < size) resize(size)
    }

    private def ensureSize(size: Int) {
      if (capacity < size) {
        var newsize = if (capacity == 0) 16 else capacity * 2
        while (newsize < size) newsize *= 2
        resize(newsize)
      }
    } 

    def +=(elem: Double): this.type = {
      ensureSize(size + 1)
      elems(size) = elem
      size += 1
      this
    }

    override def ++=(xs: scala.collection.Traversable[Double]): this.type = xs match {
      case xs: WrappedArray.ofDouble =>
        ensureSize(this.size + xs.length)
        Array.copy(elems, this.size, xs.array, 0, xs.length)
        size += xs.length
        this
      case _ =>
        super.++=(xs)
    }

    def clear() {
      size = 0
    }

    def result() = {
      if (capacity != 0 && capacity == size) elems 
      else mkArray(size)
    }
  }

  class ofBoolean extends Builder[Boolean, Array[Boolean]] {

    private var elems: Array[Boolean] = _
    private var capacity: Int = 0
    private var size: Int = 0

    private def mkArray(size: Int): Array[Boolean] = {
      val newelems = new Array[Boolean](size)
      if (this.size > 0) Array.copy(elems, 0, newelems, 0, this.size)
      newelems
    }

    private def resize(size: Int) {
      elems = mkArray(size)
      capacity = size
    }

    override def sizeHint(size: Int) {
      if (capacity < size) resize(size)
    }

    private def ensureSize(size: Int) {
      if (capacity < size) {
        var newsize = if (capacity == 0) 16 else capacity * 2
        while (newsize < size) newsize *= 2
        resize(newsize)
      }
    } 

    def +=(elem: Boolean): this.type = {
      ensureSize(size + 1)
      elems(size) = elem
      size += 1
      this
    }

    override def ++=(xs: scala.collection.Traversable[Boolean]): this.type = xs match {
      case xs: WrappedArray.ofBoolean =>
        ensureSize(this.size + xs.length)
        Array.copy(elems, this.size, xs.array, 0, xs.length)
        size += xs.length
        this
      case _ =>
        super.++=(xs)
    }

    def clear() {
      size = 0
    }

    def result() = {
      if (capacity != 0 && capacity == size) elems 
      else mkArray(size)
    }
  }

  class ofUnit extends Builder[Unit, Array[Unit]] {

    private var elems: Array[Unit] = _
    private var capacity: Int = 0
    private var size: Int = 0

    private def mkArray(size: Int): Array[Unit] = {
      val newelems = new Array[Unit](size)
      if (this.size > 0) Array.copy(elems, 0, newelems, 0, this.size)
      newelems
    }

    private def resize(size: Int) {
      elems = mkArray(size)
      capacity = size
    }

    override def sizeHint(size: Int) {
      if (capacity < size) resize(size)
    }

    private def ensureSize(size: Int) {
      if (capacity < size) {
        var newsize = if (capacity == 0) 16 else capacity * 2
        while (newsize < size) newsize *= 2
        resize(newsize)
      }
    } 

    def +=(elem: Unit): this.type = {
      ensureSize(size + 1)
      elems(size) = elem
      size += 1
      this
    }

    override def ++=(xs: scala.collection.Traversable[Unit]): this.type = xs match {
      case xs: WrappedArray.ofUnit =>
        ensureSize(this.size + xs.length)
        Array.copy(elems, this.size, xs.array, 0, xs.length)
        size += xs.length
        this
      case _ =>
        super.++=(xs)
    }

    def clear() {
      size = 0
    }

    def result() = {
      if (capacity != 0 && capacity == size) elems 
      else mkArray(size)
    }
  }
}