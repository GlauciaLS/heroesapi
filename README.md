
# API reativa de gerenciador de heróis utilizando Spring Webflux

## Stack utilizada

  * Java 8
  * Spring Webflux
  * Spring Data
  * DynamoDB
  * jUnit
  * Sl4j
  * Reactor  


### Executar DynamoDB: 

Execute na pasta em que o jar está baixado: 

```shell script
java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb
```

Para listar as tabelas criadas:

```shell script
aws dynamodb list-tables --endpoint-url http://localhost:8000
```

Documentação gerada pelo Swagger: http://localhost:8080/swagger-ui-heroes-reactive-api.html
