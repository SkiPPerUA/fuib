package org.example.qaTransactionTeam.backEnd.helper;

import java.util.UUID;

public interface Uuid_helper {

    static String generate_uuid(){
        return UUID.randomUUID().toString();
    }

    static String generate_uuid4(){
        StringBuilder stringBuilder = new StringBuilder(generate_uuid());
        stringBuilder.replace(8,13,"");
        return stringBuilder.toString();
    }
}
