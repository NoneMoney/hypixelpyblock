package dev.yavuztas.cap.capsource.feed.supplier

import com.binance.connector.client.WebsocketClient
import com.binance.connector.client.impl.WebsocketClientImpl
import com.binance.connector.client.utils.WebSocketCallback
import dev.yavuztas.cap.capsource.feed.FeedConsumer
import dev.yavuztas.cap.capsource.feed.FeedData
import dev.yavuztas.cap.capsource.feed.RawFeedData
import dev.yavuztas.cap.capsource.feed.FeedSupplier
import dev.yavuztas.cap.capsource.util.RingBuffer
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

/**
 * @pa