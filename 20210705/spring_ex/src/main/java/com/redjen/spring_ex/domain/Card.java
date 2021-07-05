package com.redjen.spring_ex.domain;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.YearMonth;
import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
@ToString
public class Card {
    
    @NotBlank
    private String no;

    @JsonFormat(pattern="yyyy-MM")
    @Future
    private YearMonth validMonth;
}
