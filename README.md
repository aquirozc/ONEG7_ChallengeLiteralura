# Alura Challenge | Literalura

Literalura es un aplicación de consola que permite buscar libros en el repositorio online Gutendex y guardalos en un catalogo local de libros.

## Funciones

- Buscar libros en Gutendex API y guardalos en una base de datos relacional
- Consultar libros guardados en el catalogo
- Filtrar libros guardados por idioma
- Consultar autores guardados en el catalogo
- Filtrar autores guardados vivos en determinado año

## Requisitos 

- Java SE Development Kit (Versión 21 o superior)
- Apache Maven (Versión 3.8.8 o superior)
- Postgres SQl (Versión 15.3 o superior)

## Preparación de la base de datos

Conexión al cliente de Postgres

`sudo -iu postgres; psql`

Creación de la base de datos

`create database oneg7; \c oneg7`

Creación del esquema de la aplicación

`create schema literalura`
