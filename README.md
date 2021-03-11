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
- Consumer:
    - bootstrap: Server url to connect to (ex: localhost:9092)
    - topic: Name of the topic to consume
    - groupId: Group id for the consumer