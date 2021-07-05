package com.redjen.spring_ex.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

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

    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
}
