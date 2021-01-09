# Pesquisa por Produtos

Este projeto contempla a pesquisa por produto consumindo a api do [Mercado Livre](https://developers.mercadolibre.com.ar/es_ar/items-y-busquedas).

Tendo como principais features:

- Pesquisa por nome de produto;
- Lista com os resultados da pesquisa;
- Tela da detalhe do produto;

## Estrutura do Projeto

O código fonte foi escrito em kotlin, seguindo o *pattern* MVVM e prática do *Clean Architecture*.

### Teste unitário 

Os testes foram escritos para as `Extensions`, classes do *Clean Architecture* e `ViewModel`, seguindo o padrão *AAA*.

### Testes instrumentados

Testes para validar o fluxo de pesquisa de produtos. 

### JaCoCo

Para executar o relatório de cobertura de testes basta executar a `task` do *gradle*:

```groovy
./gradlew jacocoReport
```

**NOTA**: Foram excluídos do relatório de cobertura os arquivos de Activity, Fragment e Adapter.
