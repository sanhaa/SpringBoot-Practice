package sh.hellospringboot.domain;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;

@Entity
public class Member {
    // PK 맵핑
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
