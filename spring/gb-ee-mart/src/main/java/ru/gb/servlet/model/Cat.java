package ru.gb.servlet.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cat {
    private String name;
    private int age;
    private List<Cat> children;
}
