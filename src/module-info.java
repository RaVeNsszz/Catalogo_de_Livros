/**
 * 
 */
/**
 * 
 */
module Catalogo_de_Livros {
    requires jakarta.persistence;
    requires jakarta.servlet;
    
    requires java.sql;
    requires org.hibernate.orm.core;

    opens main.java.model to org.hibernate.orm.core;
}