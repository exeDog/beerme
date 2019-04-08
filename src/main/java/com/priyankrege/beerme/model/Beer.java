package com.priyankrege.beerme.model;

import com.priyankrege.beerme.values.AlchoholContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Float price;

    private AlchoholContent alchoholContent;

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", alchoholContent=" + alchoholContent +
                '}';
    }
}
