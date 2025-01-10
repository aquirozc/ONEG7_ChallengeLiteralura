package com.aquirozc.literalura.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

   List<Person> findByName(String name);

   @Query("SELECT p FROM Person p WHERE p.birth <= :year AND (p.death IS NULL OR p.death >= :year)")
    List<Person> findLivingAuthors(int year);
    
}
