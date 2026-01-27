package com.mipres.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenResponse implements Serializable{

    String token;
}