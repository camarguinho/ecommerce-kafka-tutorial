# Boas praticas com Kafka

1. Defina contratos de evento estaveis e versionaveis.
2. Use chave de particao coerente (`orderId`) para manter ordenacao por entidade.
3. Implemente consumidores idempotentes para lidar com reentrega.
4. Tenha topico de falhas (DLQ funcional/tecnico) monitorado.
5. Use correlation-id e metadados de observabilidade em logs.
6. Evite acoplamento entre schema de evento e entidade JPA interna.
7. Teste falhas reais de rede/timeout para validar resiliencia.

[Voltar para o Sumário](SUMMARY.md)
[Anterior: Solução de Problemas (Troubleshooting)](07-troubleshooting.md)
[Próximo: Anti-Padrões com Kafka](09-anti-padroes-kafka.md)
