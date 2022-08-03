package dev.yavuztas.cap.capsource.registry

import dev.yavuztas.cap.capsource.feed.FeedSupplier
import dev.yavuztas.cap.capsource.feed.FeedDataStream
import io.vertx.core.net.NetSocket
import mu.KotlinLogging

class RegistryClient (private val socket: NetSocket) {

  private val log =