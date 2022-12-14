package com.upthemuscle.converter;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component("photoConverter")
public class PhotoConverter {

    public String byteArrayToBase64(byte[] byteArrayPhoto) {
        return Base64.getEncoder().encodeToString(byteArrayPhoto);
    }

    public byte[] base64ToByArray(String base64Photo) {
        return Base64.getDecoder().decode(base64Photo);
    }

}
