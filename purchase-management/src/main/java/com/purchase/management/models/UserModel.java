package com.purchase.management.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private Long id;
    private String username;
    private String name;
    private String lastName;
    private String password;
    private Boolean isActive;


}
