# CAP - Crypto API
A PoC for an example financial data stream service. It's based on public cryptocurrency data.  

## cap-source
This project is the upstream part of the tick data stream.

### Overview
* Using [Binance Public API](https://binance-docs.github.io/apidocs) (websocket) to listen to ticker stream of configurable symbols.
* [FeedSupplier](https://github.com/yavuztas/cap-source/blob/master/src/main/kotlin/dev/yavuztas/cap/capsource/feed/FeedSupplier.kt) stores the raw data into a [RingBuff