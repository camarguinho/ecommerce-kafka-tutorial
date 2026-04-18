# Docker Compose (Kafka KRaft + Kafdrop)

## Subida

- `docker compose up -d`

## Validacao

- Kafka em `localhost:9092`
- Kafdrop em `http://localhost:9000`

## Parada

- `docker compose down`

## Limpeza total

- `docker compose down -v`

## Observacao

Esta stack usa Kafka em modo KRaft (sem ZooKeeper), simplificando ambiente local e reduzindo componentes para estudo.

[Voltar para o Sumário](SUMMARY.md)

[Anterior: Testes](05-testes.md)
[Próximo: Solução de Problemas (Troubleshooting)](07-troubleshooting.md)
