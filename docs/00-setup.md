# Setup do ambiente

## Pre-requisitos

- Java 17
- Maven 3.9+
- Docker e Docker Compose

## Ordem recomendada

1. Subir Kafka/Kafdrop: `docker compose up -d`
2. Subir servicos Spring Boot
3. Executar fluxo de pedido
4. Inspecionar eventos no Kafdrop
5. Validar tabelas no H2 Console

[Voltar para o Sumário](SUMMARY.md)

[Próximo: Arquitetura](01-arquitetura.md)
