package com.aquirozc.literalura.persistence;

import java.util.List;
import com.aquirozc.literalura.transitional.PersonDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name="AUTORES", schema = "LITERALURA")
public class Person {
    
    @Id
    @Column(name="NOMBRE")
    private String name;

    @Column(name="AÑO_DE_NACIMIENTO", nullable=true)
    private int birth;

    @Column(name="AÑO_DE_DEFUNCION", nullable=true)
    private int death;

    @ManyToMany(mappedBy="authors")
    private List<Book> books;

    public Person(PersonDTO person){
        this.name = person.getName();
        this.birth = person.getBirth();
        this.death = person.getDeath();
    }

    @Override
    public String toString(){
        return "\n---- Autor ----\n" +
                "Nombre: " + name + "\n" +
                "Nacimiento: " + birth + "\n" +
                "Defunción: " + death + "\n" +
                "----------------\n";
    }

}
