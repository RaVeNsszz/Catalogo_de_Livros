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
A configuração do banco de dados deve estar no arquivo "\src\main\resources\application.properties":
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
Este mesmo código de exemplo está disponível no arquivo ```application_sample```.

## Configurando o Arquivo "persistence.xml"
Outra configuração do banco de dados deve estar no arquivo "\src\main\resources\META-INF\persistence.xml":
```
<persistence xmlns="https://jakarta.ee/xml/ns/persistence" version="3.0">
    <persistence-unit name="catalogo_livros">
        <class>Livro</class>
        <properties>
            <property name="jakarta.persistence.jdbc.driver" value="org.postgresql.Driver"/>
            <property name="jakarta.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/catalogo_livros"/>
            <property name="jakarta.persistence.jdbc.user" value="seu_usuario"/>
            <property name="jakarta.persistence.jdbc.password" value="sua_senha"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
        </properties>
    </persistence-unit>
</persistence>
```
Este mesmo código de exemplo está disponível no arquivo ```persistence_sample```.
