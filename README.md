# Catalogo_de_Livros
 
## Instalação do Maven

- Verifique se o Maven está instalado:
```
mvn -v
```
- Se não estiver: [Documentação Oficial](https://maven.apache.org/install.html)


## Adicionando Dependências
No pom.xml, adicione as dependências necessárias. Exemplo:
```
<dependencies>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.12.0</version>
    </dependency>
</dependencies>
```

## Compilando e Executando o Projeto
Baixar dependências e compilar:
```
mvn clean install
```
Executar testes:
```
mvn test
```
Gerar o JAR:
```
mvn package
```

## Configurando o Arquivo "application.properties"
Se você estiver usando Spring Boot, a configuração do banco de dados deve estar no arquivo application.properties ou application.yml. Por exemplo:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/catalogo_livros
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```
Este mesmo código de exemplo está disponível no arquivo ```/src/application_sample```.
