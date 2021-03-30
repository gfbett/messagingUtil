package io.swagger.api;

@javax.annotation.Generated(value = "io.io.swagger.codegen.languages.SpringCodegen", date = "2021-03-30T12:50:03.016+02:00")

public class ApiException extends Exception{
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
