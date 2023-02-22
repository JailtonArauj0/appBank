package com.appbank.utils;

import java.util.Random;

public class CreateAccNumber {

    public Integer accNumber() {
        Random rand = new Random();
        return rand.nextInt(89999999) + 10000000;
    }

    public Integer agencyNumber() {
        Random rand = new Random();
        return rand.nextInt(9000) + 1000;
    }

}
