# Troubleshooting

## 1. Nao conecta no Kafka

Sintoma: erro `Connection to node could not be established`.

Verifique:

- `docker compose ps`
- porta `9092` livre
- broker saudavel no healthcheck

Acao:

- `docker compose restart kafka`

## 2. Kafdrop sem topicos

Sintoma: UI abre, mas topicos nao aparecem.

Verifique:

- se os servicos Spring subiram
- se houve publicacao de evento (logs)

## 3. Liquibase lock

Sintoma: migration travada.

Acao no H2 console do servico:

```sql
DELETE FROM DATABASECHANGELOGLOCK;
```

## 4. H2 Console nao autentica

Use JDBC exato do servico e usuario `sa` com senha vazia.

## 5. Circuit breaker aberto no payment

Sintoma: falhas imediatas em novos pagamentos.

Acao:

- aguardar janela de recuperacao configurada;
- reduzir taxa de erro simulada;
- revisar logs de causa raiz.

[Voltar para o Sumário](SUMMARY.md)

[Anterior: Docker Compose e Kafdrop](06-docker-compose-kafdrop.md)
[Próximo: Boas Práticas com Kafka](08-boas-praticas-kafka.md)
