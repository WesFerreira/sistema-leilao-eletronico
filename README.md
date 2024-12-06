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

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/sistema-leilao-eletronico-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
