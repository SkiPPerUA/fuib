package org.example.qaTransactionTeam.backEnd.transaction;

import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import java.util.Map;

public interface Payer_constructor {

    static String PAN_payer(String value, String expire, String cvv){
        return "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ value+"\",\n" +
                "        \"expire\": \""+expire+"\",\n" +
                "        \"cvv\": \""+cvv+"\"}\n";
    }

    static String PAN_payer(Map card){
        return "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ card.get(Card_param.pan)+"\",\n" +
                "        \"expire\": \""+card.get(Card_param.expire)+"\",\n" +
                "        \"cvv\": \""+card.get(Card_param.cvv)+"\"}\n";
    }

    static String ITM_payer(Map card){
        return "    \"payer\": {\n" +
                "        \"source\": \"ITM\",\n" +
                "        \"value\": \""+ card.get(Card_param.token)+"\"}\n";
    }

    static String CardId_payer(String cardId){
        return "    \"payer\": {\n" +
                "        \"source\": \"CARD_ID\",\n" +
                "        \"value\": \""+ cardId +"\"}\n";
    }

    static String PAN_payer(String value, String expire, String cvv, String client_source, String client_id){
        return "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ value+"\",\n" +
                "        \"expire\": \""+expire+"\",\n" +
                "        \"cvv\": \""+cvv+"\",\n"+
                "        \"client\": {\n" +
                "            \"source\": \""+client_source+"\",\n" +
                "            \"id\": \""+client_id+"\"\n" +
                "        }}\n";
    }

    static String PAN_payer(Map card, String client_source, String client_id){
        return "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ card.get(Card_param.pan)+"\",\n" +
                "        \"expire\": \""+card.get(Card_param.expire)+"\",\n" +
                "        \"cvv\": \""+card.get(Card_param.cvv)+"\",\n"+
                "        \"client\": {\n" +
                "            \"source\": \""+client_source+"\",\n" +
                "            \"id\": \""+client_id+"\"\n" +
                "        }}\n";
    }
}
