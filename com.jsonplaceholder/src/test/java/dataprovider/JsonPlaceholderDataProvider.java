package dataprovider;

import org.testng.annotations.DataProvider;

import java.util.HashMap;

public class JsonPlaceholderDataProvider {

    @DataProvider (name = "comments_data")
    public Object[][] getData() {
        return new Object[][] {
                {3, "[Nikita@garfield.biz]"},
                {488, "[Albert@johnny.biz]"},
                {347, "[Anthony.Koepp@savannah.tv]"},
                {223, "[Zella@jan.net]"},
                {29, "[Jennings_Pouros@erica.biz]"},
                {130, "[Trevion_Kuphal@bernice.name]"},
                {46, "[Jeremy.Harann@waino.me]"},
                {79, "[Esther_Ratke@shayna.biz]"},
                {157, "[Makenzie@libbie.io]"},
                {289, "[Paolo@cristopher.com]"}
        };
    }

    @DataProvider (name = "patch_data")
    public Object[][] getPatchData () {
        HashMap<String, String> data1 = new HashMap<>();
        HashMap<String, String> data2 = new HashMap<>();
        HashMap<String, String> data3 = new HashMap<>();
        HashMap<String, String> data4 = new HashMap<>();
        HashMap<String, String> data5 = new HashMap<>();

        data1.put("name", "Sami Sheikh");
        data2.put("username", "TestUser123");
        data3.put("email", "testemail123@gmail.com");
        data4.put("phone", "555-555-5555");
        data5.put("website", "testwebsite.org");

        return new Object[][]{
                {data1, "/2"},
                {data2, "/4"},
                {data3, "/5"},
                {data4, "/7"},
                {data5, "/9"}
        };
    }
}
