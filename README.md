Small utility used to produce and consume messages over a messaging provider.

#### Endpoints available:
path: `/v1/messaging/produce`

body: 
```
{
  "properties": {
    "bootstrap": "localhost:9092",
    "topic":"test"
  },
  "provider": "KAFKA",
  "quantity": 10,
  "sizeFrom": 2000,
  "sizeTo": 3000
}
```

For production the following options are available: 
- provider: One of the supported providers. Details below
- properties: Provider specific properties
- quantity: number of messages to send
- sizeFrom: minimum size for the randomly generated message
- sizeTo: maximum size for the randomly generated message.

Path: `/v1/messaging/consume`

body: 
```
{
   "properties": {
     "bootstrap": "localhost:9092",
     "topic": "test",
     "groupId": "group1"
 
   },
   "provider": "KAFKA"
 }
 ```

For consumption the consumer type and properties are the options allowed to be set.

### Supported consumers/producers

##### ZEROMQ
Properties:
- Producer:
    - host: Hostname/ip where the consumer is listening
    - port: Port where the consumer is listeing
- Consumer:
    - port: Port where the consumer should listen

##### KAFKA
Properties:
- Producer:
    - bootstrap: Server url to connect to (ex: localhost:9092)
    - topic: Name of the topic where the messages will be published
    - kafka.*: Properties starting with kafka will be passed without the kafka. prefix, for example using kafka.ssl.key.password will set the property ssl.key.password to the kafka client.
    - truststoreEntry: a base64 encoded certificate to be added to the trust store of the kafka client
    - keystoreKey: a base64 encoded key to be added to the key store of the kafka client
    - keystoreChain: A base64 encoded certificate chain to be added to the key store of the kafka client
- Consumer:
    - bootstrap: Server url to connect to (ex: localhost:9092)
    - topic: Name of the topic to consume
    - groupId: Group id for the consumer
    - kafka.*: Properties starting with kafka will be passed without the kafka. prefix, for example using kafka.ssl.key.password will set the property ssl.key.password to the kafka client.
    - truststoreEntry: a base64 encoded certificate to be added to the trust store of the kafka client
    - keystoreKey: a base64 encoded key to be added to the key store of the kafka client
    - keystoreChain: A base64 encoded certificate chain to be added to the key store of the kafka client