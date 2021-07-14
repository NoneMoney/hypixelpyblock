package dev.yavuztas.cap.capsource.feed

import io.vertx.core.buffer.Buffer

interface FeedData {

  fun asMutable(): Buffer

  fun asDuplica