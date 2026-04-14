# Resiliencia com Resilience4j

O Payment Service aplica:

- Retry: 3 tentativas
- Circuit Breaker: abre com 50% de falha
- TimeLimiter: timeout de 5s

## Como testar

1. Envie pedido com `simulatePaymentFailure=true`.
2. Observe logs do payment-service com retries.
3. Verifique evento `order-failed` no Kafdrop.
4. Consulte status final do pedido no banco do order-service.

## Resultado esperado

Mesmo com falha externa, o sistema responde com degradacao controlada e registra o motivo da falha no fluxo de eventos.
