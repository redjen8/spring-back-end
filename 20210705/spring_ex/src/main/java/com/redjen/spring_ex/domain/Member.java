package com.redjen.spring_ex.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;

@Getter
@Setter
@ToString
public class Member {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    @Size(max = 4)
    private String UserName;

    @Email
    private String email;
    private String gender;

    @Valid
    private Address address;

    @Valid
    private List<Card> cardList;

    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
}
