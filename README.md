# sistema-leilao-eletronico

Projeto da matéria de Linguagem de programação 2

## Requisitos
Antes de executar o projeto, certifique se os seguintes requisitos estão instalados em sua máquina:

  - **Java 17** ou uma versão compatível.
  - **Maven 3.8.6** ou uma versão compatível.

## Rodando aplicação no modo dev

```shell script
mvn compile quarkus:dev
```

## Documentação Swagger
O projeto inclui uma documentação Swagger para facilitar o entendimento da API.Acesse a documentação Swagger em seu navegador:

http://localhost:8080/swagger-ui/#/

## Fluxo para teste completo do Leilão

**Para todos os casos que precisa utiliza o id, temos o método get do mesmo para busca-lo**

1º Criar uma instituição Financeira<br>
**POST - /instituicao-financeira**

2º Criar um leilão passando id(s) da Financeira criada anteriormente<br>
**POST - /leilao**

3º Criar um Veiculo ou Dispositivo passando o id do leilão<br>
Veiculo:<br>
**POST - /veiculo/cadastrar-caminhao<br>**
**POST - /veiculo/cadastrar-carro<br>**
**POST - /veiculo/cadastrar-moto<br>**
Dispositivo:<br>
**POST - /dispositivo/cadastrar-celular<br>**
**POST - /dispositivo/cadastrar-monitor<br>**
**POST - /dispositivo/cadastrar-notebook<br>**

4º Criar um Cliente<br>
**POST - /cliente**

5º Criar um Lance passando o id do dispositivo/veiculo e id do cliente<br>
**POST - /lance/dispositivo**
**POST - /lance/veiculo**
