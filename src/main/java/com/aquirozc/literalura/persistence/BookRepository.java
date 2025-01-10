package com.aquirozc.literalura.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    @Query("SELECT b FROM Book b WHERE :locale IN (SELECT UNNEST(l.locales) FROM Book l WHERE l.id = b.id)")
    List<Book> findBooksByLocale(String locale);

}
