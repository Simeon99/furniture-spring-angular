package com.metropolitan.yokaappproject.domain;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "korpa")
public class Korpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_korpe")
    private Long id_korpe;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "korpa_namestaj",
            joinColumns = @JoinColumn(name = "id_korpe"),
            inverseJoinColumns = @JoinColumn(name = "id_namestaj"))
    private Set<Namestaj> namestajs;

    public Korpa(Long id_korpe) {
        this.id_korpe = id_korpe;

    }

    public Korpa() {
    }

    public Long getId_korpe() {
        return id_korpe;
    }

    public void setId_korpe(Long id_korpe) {
        this.id_korpe = id_korpe;
    }

    public Set<Namestaj> getNamestajs() {
        return namestajs;
    }

    public void setNamestajs(Set<Namestaj> namestajs) {
        this.namestajs = namestajs;
    }

    public void addTem(Namestaj n){
        this.namestajs.add(n);
    }
}
