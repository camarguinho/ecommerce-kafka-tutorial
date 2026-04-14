# Roadmap para Kubernetes (documentacao)

Objetivo: preparar o backend para ambiente conteinerizado em Kubernetes sem alterar o desenho de negocio.

## Diretrizes

1. Externalizar configs com ConfigMap/Secret.
2. Usar probes (readiness/liveness) nos servicos Spring Boot.
3. Escalar consumidores por replicas e observar particionamento de topicos.
4. Isolar recursos por namespace e limites de CPU/memoria.
5. Centralizar logs e metricas (ELK/OpenSearch + Prometheus/Grafana).

## Evolucoes recomendadas

- adicionar Schema Registry;
- adotar Outbox Pattern completo;
- usar OpenTelemetry para tracing distribuido.
