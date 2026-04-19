# Arquitetura

A arquitetura usa coreografia orientada a eventos:

1. Order Service cria pedido e publica `order-created`.
2. Inventory Service reserva estoque e publica `inventory-reserved`.
3. Payment Service processa pagamento com resiliencia.
4. Shipping Service agenda envio e publica `order-confirmed`.
5. Em falhas, publica-se `order-failed` (DLQ funcional).

Esse desenho permite desacoplamento entre dominios e demonstracao clara de consistencia eventual.

```
+---------------+          +-------+
| Order Service +--------->| Kafka |
+---------------+          +-------+
       |                      |
       |                      v
       |          +---------------------+
       +--------->| Inventory Service   |
       |          +---------------------+
       |                      |
       |                      v
       |          +---------------------+
       +--------->| Payment Service     |
       |          +---------------------+
       |                      |
       |                      v
       |          +---------------------+
       +--------->| Shipping Service    |
                  +---------------------+

H2 Database:
+---------------+   +---------------------+
| Order Service |   | Inventory Service   |
+---------------+   +---------------------+
+---------------+   +---------------------+
| Payment Service|   | Shipping Service   |
+---------------+   +---------------------+
```

[Voltar para o Sumário](SUMMARY.md)

[Anterior: Setup do Projeto](00-setup.md)
[Próximo: Fluxo Kafka e E-commerce](02-fluxo-kafka-ecommerce.md)
