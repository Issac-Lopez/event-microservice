package edu.msudenver.city;

import javax.persistence.*;

@Entity
@Table(name = "string_array_type")
public class StringArrayType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}