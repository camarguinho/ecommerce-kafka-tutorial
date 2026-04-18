# O que evitar (anti-padroes)

1. Publicar evento sem rastreabilidade (`orderId`, timestamp, origem).
2. Consumidor com retry infinito sem estrategia de DLQ.
3. Mudar schema de evento sem compatibilidade.
4. Tratar Kafka como fila simples ignorando ordenacao e particionamento.
5. Falta de monitoracao da lag de consumidores.
6. Acoplamento sincrono entre servicos em fluxo que deveria ser assincorono.

[Voltar para o Sumário](SUMMARY.md)
[Anterior: Boas Práticas com Kafka](08-boas-praticas-kafka.md)
[Próximo: Roadmap para Kubernetes](10-kubernetes-roadmap.md)
