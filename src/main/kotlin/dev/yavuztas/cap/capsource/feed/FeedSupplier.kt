package dev.yavuztas.cap.capsource.feed

import java.util.function.Consumer

interface FeedSupplier {

  fun addConsumer(consumer: FeedConsumer)

  fun wr