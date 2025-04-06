package com.a5ur4.goldengains.dtos;

import java.sql.Date;

public record PersonalDataDTO(
    Long id,
    String name,
    Date birthday,
    String phone,
    Double weight,
    Double height,
    String country,
    Long user
) {
}
