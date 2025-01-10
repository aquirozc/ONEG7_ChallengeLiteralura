package com.aquirozc.literalura.persistence;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.aquirozc.literalura.transitional.BookDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="LIBROS", schema = "LITERALURA")
public class Book {

    @Id
    private long id;

    @Column(name="TITULO", nullable=false)
    private String title;

    @Column(name="IDIOMAS", nullable=false)
    private String[] locales;

    @Column(name="NUMERO_DE_DESCARGAS", nullable=false)
    private int downloads;

    @ManyToMany(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinTable(
        name = "AUTORIA",
        schema = "LITERALURA",
        joinColumns = @JoinColumn(name = "LIBRO_FK", referencedColumnName = "ID"),
        inverseJoinColumns = @JoinColumn(name = "AUTOR_FK", referencedColumnName = "NOMBRE")
    )
    private List<Person> authors;

    public Book(BookDTO book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.locales = book.getLocales();
        this.downloads = book.getDownloads();
        this.authors = book.getAuthors().stream().map(Person::new).toList();
    }

    @Override
    public String toString() {
        return "\n---- Libro ----\n" +
                "ID: " + id + "\n" +
                "Título: " + title + "\n" +
                "Idiomas: " + Arrays.toString(locales) + "\n" +
                "Descargas: " + downloads + "\n" +
                "Autores: " + authors.stream().map(a -> a.getName()).collect(Collectors.joining(", ")) + "\n"+
                "----------------\n";
    }
    
}
