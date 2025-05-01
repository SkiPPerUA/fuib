package org.example.qaTransactionTeam.backEnd.helper;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.*;

public interface BodyHelper {

    Logger logger = Logger.getLogger(BodyHelper.class);

    static ArrayList without_fields_cases(Map<String, String> fields){
        List<String> result = new ArrayList<>();
        Set<String> setKeys = fields.keySet();
        for(String k: setKeys){
            Map<String,String> test = new HashMap<>(fields);
            test.remove(k);
            logger.info("Без "+k);
            JSONObject jsonObject = new JSONObject(test);
            result.add(jsonObject.toString());
        }
        return (ArrayList) result;
    }
}
