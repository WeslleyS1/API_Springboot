package com.unipe.Api_Spring.dto.form;

import lombok.Data;

@Data
public class LoginAuth {

    private RegistroRequest registroRequest;
    private String token;

}
