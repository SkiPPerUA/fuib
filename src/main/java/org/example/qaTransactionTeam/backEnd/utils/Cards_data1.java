package org.example.qaTransactionTeam.backEnd.utils;

import org.testng.Assert;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cards_data1 {

    private static final Map<Card, Map> CARD_DATA = new HashMap<>();

    static void setData(){

        Map<Card_param, String> FUIB_VISA = new HashMap<>();
        FUIB_VISA.put(Card_param.pan,"4206520000078197");
        FUIB_VISA.put(Card_param.expire,"2602");
        FUIB_VISA.put(Card_param.cvv,"843");
        FUIB_VISA.put(Card_param.token,"?C8RL8XT8A5FV6J7");
        FUIB_VISA.put(Card_param.iban,"UA953348510000026201112609803");
        CARD_DATA.put(Card.FUIB_VISA,FUIB_VISA);

        Map<Card_param, String> FUIB_MC = new HashMap<>();
        FUIB_MC.put(Card_param.pan,"5218320000163351");
        FUIB_MC.put(Card_param.expire,"2602");
        FUIB_MC.put(Card_param.cvv,"598");
        FUIB_MC.put(Card_param.token,"?C8RL3MP049UGTEC");
        FUIB_MC.put(Card_param.iban,"UA953348510000026201112609803");
        CARD_DATA.put(Card.FUIB_MC,FUIB_MC);

        Map<Card_param, String> OSCHAD_MC = new HashMap<>();
        OSCHAD_MC.put(Card_param.pan,"5274109990480631");
        CARD_DATA.put(Card.OSCHAD_MC,OSCHAD_MC);

        Map<Card_param, String> RAIF_VISA = new HashMap<>();
        RAIF_VISA.put(Card_param.pan,"4149500165464242");
        RAIF_VISA.put(Card_param.expire,"2601");
        RAIF_VISA.put(Card_param.cvv,"868");
        CARD_DATA.put(Card.RAIF_VISA,RAIF_VISA);

        Map<Card_param, String> PRIVAT_VISA = new HashMap<>();
        PRIVAT_VISA.put(Card_param.pan,"4149499005619828");
        PRIVAT_VISA.put(Card_param.expire,"2503");
        PRIVAT_VISA.put(Card_param.cvv,"039");
        CARD_DATA.put(Card.PRIVAT_VISA,PRIVAT_VISA);

        Map<Card_param, String> MONO_VISA = new HashMap<>();
        MONO_VISA.put(Card_param.pan,"4441111067616619");
        MONO_VISA.put(Card_param.expire,"2811");
        MONO_VISA.put(Card_param.cvv,"551");
        CARD_DATA.put(Card.MONO_VISA,MONO_VISA);

        Map<Card_param, String> TEST_CARD = new HashMap<>();
        TEST_CARD.put(Card_param.pan,"5476500000036528");
        TEST_CARD.put(Card_param.expire,"2703");
        TEST_CARD.put(Card_param.cvv,"111");
        CARD_DATA.put(Card.TEST_CARD,TEST_CARD);

        Map<Card_param, String> PROSTIR = new HashMap<>();
        PROSTIR.put(Card_param.pan,"9804440209995692");
        PROSTIR.put(Card_param.expire,"2506");
        PROSTIR.put(Card_param.cvv,"354");
        CARD_DATA.put(Card.PROSTIR,PROSTIR);
    }

    public static Map getData(Card card){
        setData();
        return CARD_DATA.get(card);
    }

    public static String getData(Card card, Card_param param){
        setData();
        switch (param) {
            case expire_month: return CARD_DATA.get(card).get(Card_param.expire).toString().substring(2,4);
            case expire_year: return CARD_DATA.get(card).get(Card_param.expire).toString().substring(0,2);
        }
        return CARD_DATA.get(card).get(param).toString();
    }

    public static Map<Card_param, String> get_card_by_param_value(String value){
        setData();
        Map <Card_param, String> resultMap = null;
        boolean found = false;
        Iterator<Map.Entry<Card, Map>> iteratorCard = CARD_DATA.entrySet().iterator();
        while (iteratorCard.hasNext()){
            Map.Entry<Card, Map> actualValue = iteratorCard.next();
            resultMap = actualValue.getValue();
            if (resultMap.get(Card_param.pan).equals(value)) {
                found = true;
                break;
            }
        }
        if (!found){
            Assert.fail("Карта не найдена");
        }
        return resultMap;
    }
}
