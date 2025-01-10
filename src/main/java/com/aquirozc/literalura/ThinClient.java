package com.aquirozc.literalura;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aquirozc.literalura.net.GutendexAPIClient;
import com.aquirozc.literalura.persistence.Book;
import com.aquirozc.literalura.persistence.BookRepository;
import com.aquirozc.literalura.persistence.PersonRepository;
import com.aquirozc.literalura.transitional.BookDTO;

@Component
public class ThinClient {

    private static final int FETCH_BOOK_FROM_API = 1;
    private static final int FETCH_BOOKS_FROM_DB = 2;
    private static final int FETCH_BOOKS_BY_LANGUAGE = 3;
    private static final int FETCH_AUTHORS_FROM_DB = 4;
    private static final int FETCH_LIVING_AUTHORS = 5;
    private static final int EXIT_APPLICATION = 6;

    private GutendexAPIClient client = new GutendexAPIClient();
    private Scanner scanner = new Scanner(System.in);

    @Autowired private BookRepository bookRepository;
    @Autowired private PersonRepository personRepository;

    public void onCreate() throws Exception{

        System.out.println("Bienvenido a Literalura");

        int input = 0;

        while(input != EXIT_APPLICATION){

            System.out.println("""
                    
                    1. Buscar libro en Gutendex
                    2. Consultar libros guardados
                    3. Consultar libros guardados por idioma
                    4. Consultar autores guardados
                    5. Consultar autores vivos en determinado año
                    6. Salir

            """);

            System.out.print("Ingrese la opción deseada: ");
            input = scanner.nextInt();
            scanner.nextLine();
        
            switch (input) {
                
                case FETCH_BOOK_FROM_API -> {

                    System.out.print("Ingrese el título del libro a buscar: ");

                    try{

                        BookDTO response = client.queryBook(scanner.nextLine()).get(0);
                        System.out.println(response);

                        Book book = new Book(response);
                        personRepository.saveAll(book.getAuthors());
                        bookRepository.save(book);

                    }catch(IndexOutOfBoundsException e){
                        System.out.println("No se encontraron resultados");
                    }catch(IOException | InterruptedException e){
                        System.out.println("Error al consultar la API");
                    }

                    System.out.println("Libro guardado");

                }

                case FETCH_BOOKS_FROM_DB -> {

                    bookRepository.findAll().forEach(System.out::println);

                }

                case FETCH_BOOKS_BY_LANGUAGE -> {

                    System.out.print("Ingrese el idioma a consultar: ");
                    bookRepository.findBooksByLocale(scanner.nextLine()).forEach(System.out::println);

                }

                case FETCH_AUTHORS_FROM_DB -> {

                    personRepository.findAll().forEach(System.out::println);

                }
                case FETCH_LIVING_AUTHORS -> {

                    System.out.print("Ingrese el año a consultar: ");
                    personRepository.findLivingAuthors(scanner.nextInt()).forEach(System.out::println);

                }

                default -> System.out.println("Opción no válida");

            }

        }

        System.out.println("Gracias por usar la aplicación");

    }

   
    
}
