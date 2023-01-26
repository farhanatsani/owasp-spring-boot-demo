package com.example.base.rest;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseResponseDTO<T> {
    @JsonAlias("status_code")
    private Integer statusCode;
    @JsonAlias("date")
    private LocalDateTime date;
    private String message;
    private Integer dataAmount;
    private T data;
}
