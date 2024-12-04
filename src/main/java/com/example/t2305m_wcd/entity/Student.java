package com.example.t2305m_wcd.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Student {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String telephone;
    private ClassEntity classEntity; // Reference to ClassEntity
}
