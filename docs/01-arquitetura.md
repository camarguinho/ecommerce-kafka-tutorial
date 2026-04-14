# Arquitetura

A arquitetura usa coreografia orientada a eventos:

1. Order Service cria pedido e publica `order-created`.
2. Inventory Service reserva estoque e publica `inventory-reserved`.
3. Payment Service processa pagamento com resiliencia.
4. Shipping Service agenda envio e publica `order-confirmed`.
5. Em falhas, publica-se `order-failed` (DLQ funcional).

Esse desenho permite desacoplamento entre dominios e demonstracao clara de consistencia eventual.
