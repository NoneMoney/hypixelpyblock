package dev.yavuztas.cap.capsource.feed

import io.vertx.core.Handler
import io.vertx.core.buffer.Buffer
import io.vertx.core.streams.ReadStream
import java.util.concurrent.atomic.AtomicLong

class FeedDataStream (private val supplier: FeedSupplier) : ReadStream<Buffer> {

  private var readIndex = AtomicLong(this.supplier.writeIndex() - 1)
  private var demand = AtomicLong(Long.MAX_VALUE)

  private var exceptionHandler: Handler<Throwable>? = null
  private var eventHandler: Handler<