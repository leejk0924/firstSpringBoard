package com.example.firstproject.ioc;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class Beef extends Ingredient{

    public Beef(String name) {
        super(name);
    }
}
