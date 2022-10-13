package dev.yavuztas.cap.capsource.registry

import dev.yavuztas.cap.capsource.config.properties.RegistryProperties
import dev.yavuztas.cap.capsource.feed.FeedConsumer
import dev.yavuztas.cap.capsource.feed.FeedData
import dev.yavuztas.cap.capsource.feed.FeedSupplier
import dev.yavuztas.cap.capsource.feed.RawFeedData
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.core.net.NetSocket
import org.jetbrains.annotations.NotNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Specification
import spock.util.concurrent.AsyncConditions

import java.nio.charset.StandardCharsets
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import java.util.function.BiConsumer
import java.util.function.Consumer

class RegistrySpec extends Specification {

  static Logger log = LoggerFactory.getLogger(RegistrySpec)

  Vertx vertx
  Vertx clientVertx
  Registry registry
  ScheduledExecutorService writeThread

  def setup() {
    writeThread = Executors.newScheduledThreadPool(1, r -> new Thread(r, "write-thread"))
    clientVertx = Vert