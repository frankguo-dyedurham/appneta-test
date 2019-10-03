package com.example.helloworld.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "players")
@NamedQueries(
    {
        @NamedQuery(
            name = "com.example.helloworld.core.Player.findAll",
            query = "SELECT p FROM Player p"
        )
    })
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number")
    private long number;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nat", nullable = false)
    private String nationality;

    @Column(name = "pos", nullable = false)
    private String pos;

    @Column(name = "height", nullable = false)
    private float height;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "dob", nullable = false)
    private String dateOfBirth;

    @Column(name = "birthplace", nullable = false)
    private String cityOfBirth;

    public Player() {
    }

    public Player(long number, String name, String nationality, String pos, float height,
        int weight, String dateOfBirth, String cityOfBirth) {
        this.number = number;
        this.name = name;
        this.nationality = nationality;
        this.pos = pos;
        this.height = height;
        this.weight = weight;
        this.dateOfBirth = dateOfBirth;
        this.cityOfBirth = cityOfBirth;
    }

    public long getId() {
        return number;
    }

    public void setId(long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setlName(String name) {
        this.name = name;
    }
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nat) {
        this.nationality = nat;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCityOfBirth() {
        return cityOfBirth;
    }

    public void setCityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }

        final Player that = (Player) o;

        return Objects.equals(this.number, that.number) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.nationality, that.nationality) &&
                Objects.equals(this.pos, that.pos) &&
                Objects.equals(this.height, that.height) &&
                Objects.equals(this.weight, that.weight) &&
                Objects.equals(this.dateOfBirth, that.dateOfBirth) &&
                Objects.equals(this.cityOfBirth, that.cityOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, nationality, pos, height,
            weight, dateOfBirth, cityOfBirth);
    }
}
