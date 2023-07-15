package com.helf.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Errors {

    private String errorCode;

    private String errorMessage;
}
