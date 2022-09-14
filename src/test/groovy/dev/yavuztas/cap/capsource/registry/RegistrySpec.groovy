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

import java.ni