package com.byma.crudgerente.application.validation;

public class Validador {

    public static boolean nullParams(Object ...objects){
        for (Object object : objects) {
            if (object == null) {
                return true;
            }
        }
        return false;
    }

    public static void validadorParametrosNull(Object... objects) {
        if (Validador.nullParams(objects)) {
            throw new IllegalArgumentException();
        }
    }
}
