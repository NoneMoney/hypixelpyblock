package dev.yavuztas.cap.capsource.feed

import io.vertx.core.Handler
import io.vertx.core.buffer.Buffer
import io.vertx.core.streams.ReadStream
import java.util.concurrent.atomic.AtomicLong

class FeedDataStream (private val supplier: FeedSupplier) : ReadStream<Buffer> {

  private var readIndex = AtomicLong(this.supplier.writeIndex() - 1)
  private var demand = AtomicLong(Long.MAX_VALUE)

  private var exceptionHandler: Handler<Throwable>? = null
  private var eventHandler: Handler<Buffer>? = null

  override fun exceptionHandler(handler: Handler<Throwable>?): ReadStream<Buffer> {
    this.exceptionHandler = handler
    return this
  }

  override fun pause(): ReadStream<Buffer> {
    demand.set(0L)
    return this
  }

  override fun resume(): ReadStream<Buffer> {
    return fetch(Long.MAX_VALUE)
  }

  override fun fetch(amount: Long): ReadStream<Buffer> {
    if (demand.addAndGet(amount) < 0L) {
      demand.set(Long.MAX_VALUE)
    }
    doFetch()
    retur