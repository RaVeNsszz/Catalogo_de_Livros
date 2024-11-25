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
# Configuração do banco de dados
hibernate.connection.driver_class=org.postgresql.Driver
hibernate.connection.url=jdbc:postgresql://localhost:5432/catalogo_livros
hibernate.connection.username=seu_usuario
hibernate.connection.password=sua_senha

# Propriedades do Hibernate
hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
hibernate.hbm2ddl.auto=update
hibernate.show_sql=true
hibernate.format_sql=true
```
Este mesmo código de exemplo está disponível no arquivo ```/src/application_sample```.
