package test.backTests.domino;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.bus.Domino;
import org.testng.annotations.Test;

@Test
public class DominoTest extends BaseTest {

    String client_id = "544881";

    public void changeAddress() {
        new Domino().update_customers(client_id,"{\n" +
                " \"client\" : {\n"+
                "  \"addresses\" : [ {\n" +
                "    \"address_type\" : 1,\n" +
                "    \"postal_code\" : \"71504\",\n" +
                "    \"country_code\" : \"804\",\n" +
                "    \"province\" : \"Донецька обл.\",\n" +
                "    \"district\" : \"\",\n" +
                "    \"locality\" : \"м.Енергодар\",\n" +
                "    \"street\" : \"В-Інтернаціоналістів, б.26, кв.9\",\n" +
                "    \"house\" : \"\",\n" +
                "    \"apartment\" : \"\",\n" +
                "    \"main\" : false,\n" +
                "    \"status\" : 1\n" +
                "  }, {\n" +
                "    \"address_type\" : 2,\n" +
                "    \"postal_code\" : \"71504\",\n" +
                "    \"country_code\" : \"804\",\n" +
                "    \"province\" : \"Луганська обл.\",\n" +
                "    \"district\" : \"\",\n" +
                "    \"locality\" : \"м.Енергодар\",\n" +
                "    \"street\" : \"В-Інтернаціоналістів, б.26, кв.9\",\n" +
                "    \"house\" : \"\",\n" +
                "    \"apartment\" : \"\",\n" +
                "    \"main\" : false,\n" +
                "    \"status\" : 1\n" +
                "  }, {\n" +
                "    \"address_type\" : 3,\n" +
                "    \"postal_code\" : \"71504\",\n" +
                "    \"country_code\" : \"804\",\n" +
                "    \"province\" : \"Чернігівська обл.\",\n" +
                "    \"district\" : \"\",\n" +
                "    \"locality\" : \"м.Енергодар\",\n" +
                "    \"street\" : \"В-Інтернаціоналістів б.26 кв.9\",\n" +
                "    \"house\" : \"\",\n" +
                "    \"apartment\" : \"\",\n" +
                "    \"main\" : false,\n" +
                "    \"status\" : 1\n" +
                "  } ]\n" +
                " } \n"+
                "}");

        new Domino().get_customers(client_id);
    }
}
