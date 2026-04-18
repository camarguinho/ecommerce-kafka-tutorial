# Ecommerce Kafka Tutorial (Java 17 + Spring Boot)

Projeto didatico para aprender Apache Kafka na pratica com uma regra de negocio de e-commerce simples usando microservicos Spring Boot 3, Java 17, H2 in-memory, Liquibase e Resilience4j.

## Arquitetura

- service-order (porta 8080): recebe pedidos e publica `order-created`.
- service-inventory (porta 8081): reserva estoque e publica `inventory-reserved`.
- service-payment (porta 8082): processa pagamento com resiliencia e publica `payment-processed` ou `order-failed`.
- service-shipping (porta 8083): agenda envio e publica `order-confirmed`.
- shared-events: contratos de eventos com Java 17 records.

## Subindo Kafka e Kafdrop

1. `docker compose up -d`
2. Kafka: `localhost:9092`
3. Kafdrop: `http://localhost:9000`

Para desligar:

- `docker compose down`

Para resetar volumes/estado de broker:

- `docker compose down -v`

## Executando os servicos
Buildar o projeto e instalar no repositório local .m2:

1. `mvn clean package install`

Em terminais separados:

1. `cd service-order && mvn spring-boot:run`
2. `cd service-inventory && mvn spring-boot:run`
3. `cd service-payment && mvn spring-boot:run`
4. `cd service-shipping && mvn spring-boot:run`

## Criando um pedido

Use o endpoint do Order Service:

```bash
curl -X POST 'http://localhost:8080/api/orders' \
  -H 'Content-Type: application/json' \
  -d '{
    "customerId": "de305d54-75b4-431b-adb2-eb6b9e546014",
    "simulatePaymentFailure": false,
    "items": [
      {
        "productId": "8f95de2b-5c39-4b72-9c6a-6f793f4dc001",
        "quantity": 1,
        "unitPrice": 350.00
      }
    ]
  }'
```

Para simular falha de pagamento, altere `simulatePaymentFailure` para `true`.

## H2 Console (runtime)

- Order: `http://localhost:8080/h2-console` | JDBC `jdbc:h2:mem:orderdb`
- Inventory: `http://localhost:8081/h2-console` | JDBC `jdbc:h2:mem:inventorydb`
- Payment: `http://localhost:8082/h2-console` | JDBC `jdbc:h2:mem:paymentdb`
- Shipping: `http://localhost:8083/h2-console` | JDBC `jdbc:h2:mem:shippingdb`
- Usuario: `sa`
- Senha: em branco

## Testes

- `mvn test`

## Documentacao detalhada

- docs/00-setup.md
- docs/01-arquitetura.md
- docs/02-fluxo-kafka-ecommerce.md
- docs/03-banco-liquibase-h2.md
- docs/04-resiliencia-resilience4j.md
- docs/05-testes.md
- docs/06-docker-compose-kafdrop.md
- docs/07-troubleshooting.md
- docs/08-boas-praticas-kafka.md
- docs/09-anti-padroes-kafka.md
- docs/10-kubernetes-roadmap.md
