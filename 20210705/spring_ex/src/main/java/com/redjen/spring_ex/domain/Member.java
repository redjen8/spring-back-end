package com.redjen.spring_ex.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Member {
    private String userId = "hongkd";
    private String password = "1234";
    private LocalDate dateOfBirth;
}
