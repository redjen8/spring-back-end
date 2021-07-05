package com.redjen.spring_ex.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.*;

@Getter
@Setter
@ToString
public class Member {

    @NotBlank
    private String userId;
    private String password;

    @NotBlank
    @Size(max = 3)
    private String UserName;

    private String email;
    private String gender;
}
