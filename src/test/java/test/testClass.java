package test;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.bus.Domino;
import org.example.qaTransactionTeam.backEnd.bus.Hulk;
import org.example.qaTransactionTeam.backEnd.kofola.Kofola;
import org.example.qaTransactionTeam.backEnd.uzvar.Uzvar;
import org.example.qaTransactionTeam.backEnd.wine.Wine;
import org.testng.annotations.Test;

@Test
public class testClass extends BaseTest {

    public void adsd(){
       new Kofola().getAccountByIban("UA903348510000026204404198193");
    }

    public void asdfz(){
        new Uzvar().itm_tokens("5355280039708811");
    }

    public void saas(){
        new Wine().changeClient("{\n" +
                "   \"wb_client_id\":544881,\n" +
                "   \"final_client_type\":\"новий недовведений клієнт\",\n" +
                "   \"identification\":false,\n" +
                "   \"wb_client_info\":{\n" +
                " \"addresses\":[\n" +
                        "      {\n" +
                        "         \"address_type\":1,\n" +
                        "         \"postal_code\":\"71504\",\n" +
                        "         \"country_code\":\"804\",\n" +
                        "         \"province\":\"Запорізька обл.\",\n" +
                        "         \"district\":\"\",\n" +
                        "         \"locality_type\":null,\n" +
                        "         \"address_locality_id\":null,\n" +
                        "         \"locality\":\"м.Енергодар\",\n" +
                        "         \"address_street_id\":null,\n" +
                        "         \"street_type\":null,\n" +
                        "         \"street\":\"В-Інтернаціоналістів, б.26, кв.9\",\n" +
                        "         \"dwelling_type\":\"буд.\",\n" +
                        "         \"address_house_nums_id\":null,\n" +
                        "         \"house\":\"\",\n" +
                        "         \"apartment_type\":null,\n" +
                        "         \"apartment\":\"\",\n" +
                        "         \"date\":null,\n" +
                        "         \"main\":false,\n" +
                        "         \"status\":1,\n" +
                        "         \"address_source\":null\n" +
                        "      },\n" +
                        "      {\n" +
                        "         \"address_type\":2,\n" +
                        "         \"postal_code\":\"71504\",\n" +
                        "         \"country_code\":\"804\",\n" +
                        "         \"province\":\"Житомирська обл.\",\n" +
                        "         \"district\":\"\",\n" +
                        "         \"locality_type\":null,\n" +
                        "         \"address_locality_id\":null,\n" +
                        "         \"locality\":\"м.Енергодар\",\n" +
                        "         \"address_street_id\":null,\n" +
                        "         \"street_type\":null,\n" +
                        "         \"street\":\"В-Інтернаціоналістів, б.26, кв.9\",\n" +
                        "         \"dwelling_type\":\"буд.\",\n" +
                        "         \"address_house_nums_id\":null,\n" +
                        "         \"house\":\"\",\n" +
                        "         \"apartment_type\":null,\n" +
                        "         \"apartment\":\"\",\n" +
                        "         \"date\":null,\n" +
                        "         \"main\":false,\n" +
                        "         \"status\":1,\n" +
                        "         \"address_source\":null\n" +
                        "      },\n" +
                        "      {\n" +
                        "         \"address_type\":3,\n" +
                        "         \"postal_code\":\"71504\",\n" +
                        "         \"country_code\":\"804\",\n" +
                        "         \"province\":\"Херсонська обл.\",\n" +
                        "         \"district\":\"\",\n" +
                        "         \"locality_type\":null,\n" +
                        "         \"address_locality_id\":null,\n" +
                        "         \"locality\":\"м.Енергодар\",\n" +
                        "         \"address_street_id\":null,\n" +
                        "         \"street_type\":null,\n" +
                        "         \"street\":\"В-Інтернаціоналістів б.26 кв.9\",\n" +
                        "         \"dwelling_type\":\"буд.\",\n" +
                        "         \"address_house_nums_id\":null,\n" +
                        "         \"house\":\"\",\n" +
                        "         \"apartment_type\":null,\n" +
                        "         \"apartment\":\"\",\n" +
                        "         \"date\":null,\n" +
                        "         \"main\":false,\n" +
                        "         \"status\":1,\n" +
                        "         \"address_source\":null\n" +
                        "      }\n" +
                        "   ]"+
                "   }\n" +
                "}");

        new Wine().getClient("544881");
    }

    public void asd(){
        System.out.println(    "{\n" +
                "    \"wb_client_id\": 8531524,\n" +
                "    \"wb_client_info\": {\n" +
                "       \"addresses\": [\n" +
                "        {\n" +
                "            \"address_type\": 1,\n" +
                "            \"postal_code\": \"01001\",\n" +
                "            \"country_code\": \"804\",\n" +
                "            \"province\": \"Київ\",\n" +
                "            \"district\": \"\",\n" +
                "            \"locality_type\": \"місто\",\n" +
                "            \"address_locality_id\": null,\n" +
                "            \"locality\": \"Київ\",\n" +
                "            \"address_street_id\": null,\n" +
                "            \"street_type\": \"вул.\",\n" +
                "            \"street\": \"Лаврухіна\",\n" +
                "            \"dwelling_type\": \"буд.\",\n" +
                "            \"address_house_nums_id\": null,\n" +
                "            \"house\": \"11\",\n" +
                "            \"apartment_type\": null,\n" +
                "            \"apartment\": \"203\",\n" +
                "            \"date\": null,\n" +
                "            \"main\": false,\n" +
                "            \"status\": 1,\n" +
                "            \"address_source\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"address_type\": 2,\n" +
                "            \"postal_code\": \"01001\",\n" +
                "            \"country_code\": \"804\",\n" +
                "            \"province\": \"Київ\",\n" +
                "            \"district\": \"\",\n" +
                "            \"locality_type\": \"місто\",\n" +
                "            \"address_locality_id\": null,\n" +
                "            \"locality\": \"Київ\",\n" +
                "            \"address_street_id\": null,\n" +
                "            \"street_type\": \"вул.\",\n" +
                "            \"street\": \"Лаврухіна\",\n" +
                "            \"dwelling_type\": \"буд.\",\n" +
                "            \"address_house_nums_id\": null,\n" +
                "            \"house\": \"11\",\n" +
                "            \"apartment_type\": null,\n" +
                "            \"apartment\": \"203\",\n" +
                "            \"date\": null,\n" +
                "            \"main\": false,\n" +
                "            \"status\": 1,\n" +
                "            \"address_source\": null\n" +
                "        }\n" +
                "    ]\n" +
                "    }\n" +
                "}");
    }


}