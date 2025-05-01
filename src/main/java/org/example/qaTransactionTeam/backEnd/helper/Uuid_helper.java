package org.example.qaTransactionTeam.backEnd.helper;

import java.util.UUID;

public interface Uuid_helper {

    static String generate_uuid(){
        return UUID.randomUUID().toString();
    }
}
