demo's AWS Kinesis Client Demo
===================================

This is a sample Kinesis producer/consumer.

## Clean the application compiles sources
The following command will clean the compiles sources

``./gradlew clean``

## Build the app
The following command will compile and create the jar file and then execute the unit and integration tests

``./gradlew build``

## Run the application without building a jar first
The following command will run the application without building the jar first. This way also makes the static resources
(in src/main/resources) reloadable in the live application.

``./gradlew bootRun``

# Swagger docs
Swagger API documentation is included with this application and can be used to interact with the various endpoints. To
access the swagger documentation, make sure the application is running and navigate to the following URL (making sure
that the host and port is correct for your targeted environment):

``http://localhost:8080/swagger-ui.html``


## Endpoints
This SpringBoot template application includes actuator library which will expose some default endpoints which can be used to monitor/measure the running application.

- Metrics:      http://localhost:8081/metrics
- Health:       http://localhost:8081/health
- Info:         http://localhost:8081/info
- Env Config:   http://localhost:8081/env
- Auto Config:  http://localhost:8081/autoconfig
- Swagger UI:   http://localhost:8080/swagger-ui.html
- Swagger Spec: http://localhost:8080/v2/api-docs

## Manual AWS api steps to put and get records
```json
aws kinesis put-record --stream-name test --partition-key myKey --data hello

{
    "ShardId": "shardId-000000000000",
    "SequenceNumber": "49573439609077956250842575773354631510868257831976435714"
}
```

```json
aws kinesis get-shard-iterator --shard-id shardId-000000000000 --shard-iterator-type TRIM_HORIZON  --stream-name test

{
    "ShardIterator": "AAAAAAAAAAED85UtppgzB/emnz2cEx8f2JdHcDeFzVi9o3f0hkRZXGYBK5/bb/ENyoX9/tZ4spcgtJoFsci1jyNoP6S6IYk60FBqznRMNNhL/UZNOCxbxt/zRsRKMqbdBuDHJqFHkTzg2GfENqGztSRZzdChsUOT9rZLSvrkgPUkau7EJWZEEVsgUaIuKqEUqL14A1n3EVsgBYJLU/7/lG4VQmFITeUZ"
}
```


```json
aws kinesis get-records --shard-iterator AAAAAAAAAAED85UtppgzB/emnz2cEx8f2JdHcDeFzVi9o3f0hkRZXGYBK5/bb/ENyoX9/tZ4spcgtJoFsci1jyNoP6S6IYk60FBqznRMNNhL/UZNOCxbxt/zRsRKMqbdBuDHJqFHkTzg2GfENqGztSRZzdChsUOT9rZLSvrkgPUkau7EJWZEEVsgUaIuKqEUqL14A1n3EVsgBYJLU/7/lG4VQmFITeUZ

{
    "Records": [
        {
            "Data": "aGVsbG8=",
            "PartitionKey": "myKey",
            "ApproximateArrivalTimestamp": 1495424780.971,
            "SequenceNumber": "49573439609077956250842575773354631510868257831976435714"
        }
    ],
    "NextShardIterator": "AAAAAAAAAAHCR5e5z6LEcvMCexnp73GRuFxvVe12zccJ2NQLUiNa9mgclCe1+DI1NFW2Ehh1fvepeVqMhCyXSE67jS8gEj42DY55xCeYLsoujyS9SGvvwlLhIsBsy6f5ptiCcEc//uoi3iegkgQlesZ7TQ2qFmpmxEUD5R3/Cypeuypsbq1JKNjYnji7on4qsawm+JEabf14PAJO/IJgJZ+42aKWNyCS",
    "MillisBehindLatest": 0
}
```