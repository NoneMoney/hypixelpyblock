package dev.yavuztas.cap.capsource.feed

import io.vertx.core.Handler
import io.vertx.core.buffer.Buffer
import io.vertx.core.streams.ReadStream
import java.util.concurrent.atomic.AtomicLong

class FeedDataStream (private val supplier: FeedSupplier) : ReadStream<Buffer> {

  private var readIndex = AtomicLong(this.supplier.writeIndex() -