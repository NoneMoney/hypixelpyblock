# CAP - Crypto API
A PoC for an example financial data stream service. It's based on public cryptocurrency data.  

## cap-source
This project is the upstream part of the tick data stream.

### Overview
* Using [Binance Public API](https://binance-docs.github.io/apidocs) (websocket) to listen to ticker stream of configurable symbols.
* [FeedSupplier](https://github.com/yavuztas/cap-source/blob/master/src/main/kotlin/dev/yavuztas/cap/capsource/feed/FeedSupplier.kt) stores the raw data into a [RingBuffer](https://github.com/yavuztas/cap-source/blob/master/src/main/kotlin/dev/yavuztas/cap/capsource/util/RingBuffer.kt) (a naive implementation by utilizing a fixed size ArrayList)
* Incoming data is consumed and encoded by [FeedConsumer](https://github.com/yavuztas/cap-source/blob/master/src/main/kotlin/dev/yavuztas/cap/capsource/feed/FeedConsumer.kt)s (only basic encoding (US_ASCII), doesn't compress) and is redirected to [Registry](https://github.com/yavuztas/cap-source/blob/master/src/main/kotlin/dev/yavuztas/cap/capsource/registry/Registry.kt).
* [Registry](https://github.com/yavuztas/cap-source/blob/master/src/main/kotlin/dev/yavuztas/cap/capsource/registry/Registry.kt) makes a TCP fan-out to the connected [RegistryClient](https://github.com/yavuztas/cap-source/blob/master/src/main/kotlin/d