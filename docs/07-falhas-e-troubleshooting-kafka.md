# Falhas e Troubleshooting no Kafka

## Introdução

O Apache Kafka é uma plataforma poderosa para processamento de mensagens, mas sua configuração e operação podem levar a erros comuns que afetam o funcionamento do sistema. Este documento lista os principais problemas que podem ocorrer, como solucioná-los e inclui uma seção de troubleshooting para facilitar a identificação e resolução de problemas.

---

## Erros Comuns

### 1. Consumidores Ociosos
- **Causa**: Número de consumidores maior que o número de partições no tópico.
- **Sintomas**: Alguns consumidores não recebem mensagens.
- **Exceções Lançadas**: Nenhuma exceção diretamente, mas os consumidores ociosos não processam mensagens.
- **Como provocar**: Configure um tópico com menos partições do que consumidores no grupo.
- **Solução**: Aumente o número de partições ou reduza o número de consumidores no grupo.

### 2. Problemas de Serialização/Deserialização
- **Causa**: Configuração incorreta de serializadores ou deserializadores.
- **Sintomas**: Exceções como `SerializationException` ou `DeserializationException` nos logs.
- **Exceções Lançadas**: `org.apache.kafka.common.errors.SerializationException`, `org.apache.kafka.common.errors.DeserializationException`.
- **Como provocar**: Envie mensagens com um formato inesperado ou sem configurar corretamente os serializadores.
- **Solução**: Verifique se os serializadores/deserializadores estão configurados corretamente no `application.yml`.

### 3. Tópicos Inexistentes
- **Causa**: Tentativa de produzir ou consumir mensagens de um tópico que não existe.
- **Sintomas**: Erros nos logs indicando que o tópico não foi encontrado.
- **Exceções Lançadas**: `org.apache.kafka.common.errors.UnknownTopicOrPartitionException`.
- **Como provocar**: Tente consumir ou produzir mensagens para um tópico não configurado.
- **Solução**: Certifique-se de que os tópicos são criados antes de usá-los.

### 4. Falhas de Conexão com o Broker
- **Causa**: Configuração incorreta do endereço do broker ou problemas de rede.
- **Sintomas**: Erros de conexão nos logs.
- **Exceções Lançadas**: `org.apache.kafka.common.errors.TimeoutException`.
- **Como provocar**: Configure um endereço de broker inválido no `application.yml`.
- **Solução**: Verifique se o endereço do broker está correto e se a rede está funcionando.

### 5. Configurações Incorretas de `group.id` ou `auto.offset.reset`
- **Causa**: Configurações inadequadas para o grupo de consumidores.
- **Sintomas**: Mensagens não consumidas ou consumo começando de um ponto inesperado.
- **Exceções Lançadas**: Nenhuma exceção diretamente, mas o comportamento do consumidor será inesperado.
- **Como provocar**: Configure valores inválidos para `group.id` ou `auto.offset.reset`.
- **Solução**: Ajuste as configurações no `application.yml` para refletir o comportamento desejado.

---

## Troubleshooting

### 1. Não conecta no Kafka
- **Sintoma**: Erro `Connection to node could not be established`.
- **Exceções Lançadas**: `org.apache.kafka.common.errors.TimeoutException`.
- **Verifique**:
  - `docker compose ps`
  - Porta `9092` livre.
  - Broker saudável no healthcheck.
- **Ação**:
  - `docker compose restart kafka`

### 2. Kafdrop sem tópicos
- **Sintoma**: UI abre, mas tópicos não aparecem.
- **Exceções Lançadas**: Nenhuma diretamente, mas os tópicos não são exibidos.
- **Verifique**:
  - Se os serviços Spring subiram.
  - Se houve publicação de evento (logs).

---

## Conclusão

Entender e resolver falhas no Kafka é essencial para manter a estabilidade do sistema. Use este documento como referência para identificar e corrigir problemas comuns. Lembre-se de monitorar os logs e testar suas configurações regularmente.

[Voltar para o Sumário](SUMMARY.md)