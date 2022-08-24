
package dev.yavuztas.cap.capsource.util

import java.util.concurrent.atomic.AtomicLong
import java.util.function.Consumer

private fun isPowerOfTwo(n: Int): Boolean {
  return n != 0 && n and n - 1 == 0
}

/**
 * @param bufferSize: Must be a power of 2, the modulo calculation depends on that.
 */
class RingBuffer<T>(bufferSize: Int) {
  init {
    if (!isPowerOfTwo(bufferSize))
      throw IllegalArgumentException("bufferSize must be a power of 2")
  }

  private val buffer = mutableListOf<T?>().apply {
    for (index in 0 until bufferSize) {
      add(null)
    }
  }
  private val moduloBitMask = (bufferSize - 1).toLong()
  private val writeIndex: AtomicLong = AtomicLong(0)

  fun size(): Int {
    return this.buffer.size
  }

  fun add(e: T) {
    val index = relativeIndex(this.writeIndex.getAndIncrement())
    this.buffer[index] = e
  }
