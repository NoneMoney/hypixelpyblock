package dev.yavuztas.cap.capsource.registry

import dev.yavuztas.cap.capsource.config.properties.RegistryProperties
import dev.yavuztas.cap.capsource.feed.FeedConsumer
import dev.yavuztas.cap.capsource.feed.FeedSupplier
import io.vertx.core.AbstractVerticle
import io.vertx.core.DeploymentOptions
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.core.net.NetServer
import io.vertx.core.net.NetServerOptions
import io.vertx.core.net.NetSocket
import mu.KotlinLogging
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

/**
 * Multiple Registries must share the same Vertx instance.
 * Thus, we can distribute clients if needed.
 */
class Registry(
  private val props: RegistryProperties,
  private val vertx: Vertx,
  private val suppliers: List<FeedSupplier> = ArrayList()
) : FeedConsumer {

  private val log = KotlinLogging.logger {}
  private val clients: MutableMap<NetSocket, RegistryClient> = ConcurrentHashMap()

  inner class TcpServer(
    private val options: NetServerOptions
  ) : AbstractVerticle() {

    private lateinit var server: NetServer

    override fun start() {
      server = vertx.createNetServer(options)
        .connectHandler(this::onConnect)
      server.listen()
    }

    private fun onConnect(socket: NetSocket) {
     