package com.vn.sbit.idenfity_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor //constructor null
@AllArgsConstructor // constructor full property
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
     String id;

     String userName;

     String passWord;

     String firstName;

     String lastName;

     LocalDate dob;

     Set<String> roles;





}
