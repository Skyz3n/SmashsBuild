package com.skyzen.smashsgames.utils;

public class StringFormateur {

    public static String upperFirstLetter(String texte) {
        return texte.substring(0, 1).toUpperCase() + texte.substring(1);
    }
}