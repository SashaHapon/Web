package org.food.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private int id;

    private String name;

    private Double money;

    private String phoneNumber;
}
