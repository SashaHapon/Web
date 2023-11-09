package org.food.security.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    public ERole name;

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }


}
