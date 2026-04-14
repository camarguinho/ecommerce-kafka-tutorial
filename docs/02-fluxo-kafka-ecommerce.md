# Kafka suportando a regra de negocio

Kafka permite separar etapas do checkout sem acoplamento temporal.

## Topicos

- `order-created`
- `inventory-reserved`
- `payment-processed`
- `order-confirmed`
- `order-failed`

## Beneficios didaticos

- Reprocessamento por offset
- Escalabilidade por particoes/consumidores
- Rastreabilidade do fluxo completo pelo `orderId`

## Exemplo de fluxo com sucesso

`order-created -> inventory-reserved -> payment-processed -> order-confirmed`

## Exemplo com falha de pagamento

`order-created -> inventory-reserved -> order-failed`
