package com.redjen.spring_ex.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Member {

    @NotBlank
    @GeneratedValue(strategy=GenerationType.AUTO)
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
