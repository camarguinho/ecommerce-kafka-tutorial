# H2 + Liquibase

Cada microservico possui banco H2 em memoria proprio, com schema versionado por Liquibase.

## Migrations

- Arquivo mestre: `db.changelog-master.yaml`
- Changesets de schema: `001-*.yaml`
- Seed SQL: `002-*.sql` chamados por `002-*.yaml`

## H2 Console

Acesse cada servico em `/h2-console`, use o JDBC indicado no README e o usuario `sa`.

Se o console nao abrir:

- confirme que o servico subiu sem erro de Liquibase;
- confirme que a URL JDBC corresponde ao servico correto.
