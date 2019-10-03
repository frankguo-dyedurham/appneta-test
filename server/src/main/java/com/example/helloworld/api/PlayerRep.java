package com.example.helloworld.api;

import com.example.helloworld.core.Player;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.Length;

public class PlayerRep {
    private long id;
    private String name;
    private String nationality;
    private String pos;
    private float height;
    private int weight;
    private String dateOfBirth;
    private String cityOfBirth; 

    @Length(max = 3)
    private String content;

    public PlayerRep() {
        // Jackson deserialization
    }

    public PlayerRep(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.nationality = player.getNationality();
        this.pos = player.getPos();
        this.height = player.getHeight();
        this.weight = player.getWeight();
        this.dateOfBirth = player.getDateOfBirth();
        this.cityOfBirth = player.getCityOfBirth();
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getNationality() {
        return nationality;
    }

    @JsonProperty
    public String getPos() {
        return pos;
    }

    @JsonProperty
    public float getHeight() {
        return height;
    }

    @JsonProperty
    public int getWeight() {
        return weight;
    }

    @JsonProperty
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonProperty
    public String getCityOfBirth() {
        return cityOfBirth;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("nationality", nationality)
                .add("pos", pos)
                .add("height", height)
                .add("weight", weight)
                .add("dateOfBirth", dateOfBirth)
                .add("cityOfBirth", cityOfBirth)                
                .toString();
    }
}
