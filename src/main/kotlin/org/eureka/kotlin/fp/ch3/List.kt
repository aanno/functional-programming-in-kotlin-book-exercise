package org.eureka.kotlin.fp.ch3

import java.lang.UnsupportedOperationException

sealed class List<out A> {
    companion object {
        fun <A> of(vararg aa: A): List<A> =
            if (aa.isEmpty())
                Nil
            else {
                val tail = aa.sliceArray(1 until aa.size)
                Cons(aa[0], of(*tail))
            }

        fun <A> tail(xs: List<A>): List<A> = when (xs) {
            is Nil -> throw UnsupportedOperationException("Tail of an empty list")
            is Cons -> xs.tail
        }

        fun <A> setHead(xs: List<A>, x: A): List<A> = when (xs) {
            is Nil -> throw UnsupportedOperationException("Cannot replace `head` of a Nil list")
            is Cons -> Cons(x, xs.tail)
        }

        tailrec fun <A> drop(l: List<A>, n: Int): List<A> =
            if (n == 0) l
            else when (l) {
                is Cons -> drop(l.tail, n - 1)
                is Nil -> throw IllegalStateException(
                    "Cannot drop more elements than in list"
                )
            }

        tailrec fun <A> dropWhile(l: List<A>, f: (A) -> Boolean): List<A> = when (l) {
            is Cons -> if (f(l.head)) dropWhile(l.tail, f) else l
            is Nil -> l
        }

        fun <A> init(l: List<A>): List<A> = when (l) {
            is Cons ->
                if (l.tail == Nil) Nil
                else Cons(l.head, init(l.tail))
            is Nil ->
                throw IllegalStateException("Cannot init Nil list")
        }
    }
}

object Nil : List<Nothing>()

data class Cons<out A>(
    val head: A,
    val tail: List<A>
) : List<A>()