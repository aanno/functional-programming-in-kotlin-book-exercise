package org.eureka.kotlin.fp.ch5

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import org.junit.Test
import org.eureka.kotlin.fp.ch3.List
import org.eureka.kotlin.fp.ch4.Option

class StreamTest {

    @Test
    fun `head of a stream`() {
        assertThat(Stream.of(1, 2, 3).headOption()).isEqualTo(Option.of(1))
        assertThat(Stream.empty<Int>().headOption()).isEqualTo(Option.empty())
    }

    @Test
    fun `stream to list`() {
        assertThat(Stream.of(1, 2, 3, 4).toList()).isEqualTo(List.of(1, 2, 3, 4))
        assertThat(Stream.empty<Int>().toList()).isEqualTo(List.empty())
    }

    @Test
    fun `take n`() {
        assertThat(Stream.of(1, 2, 3, 4, 5, 6).take(3).toList()).isEqualTo(List.of(1, 2, 3))
        assertThat(Stream.empty<Any>().take(1)).isEqualTo(Stream.empty())
    }

    @Test
    fun `drop n`() {
        assertThat(Stream.of(1, 2, 3, 4, 5, 6).drop(3).toList()).isEqualTo(List.of(4, 5, 6))
        assertThat(Stream.empty<Any>().drop(1)).isEqualTo(Stream.empty())
    }

    @Test
    fun `take while`() {
        val p: (Int) -> Boolean = { it % 2 != 0 }
        assertThat(Stream.of(1, 3, 5, 6, 7).takeWhile(p).toList()).isEqualTo(List.of(1, 3, 5))
        assertThat { Stream.empty<Int>().takeWhile(p) }.isFailure()
    }

}