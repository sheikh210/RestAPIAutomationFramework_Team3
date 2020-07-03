package dataProviders;

import org.testng.annotations.DataProvider;

import java.util.HashMap;

public class JsonPlaceholderDataProviders {

    @DataProvider (name = "comments_data")
    public Object[][] getGetData() {
        return new Object[][] {
                {"?id=3", "[Nikita@garfield.biz]"},
                {"?id=488", "[Albert@johnny.biz]"},
                {"?id=347", "[Anthony.Koepp@savannah.tv]"},
                {"?id=223", "[Zella@jan.net]"},
                {"?id=29", "[Jennings_Pouros@erica.biz]"},
                {"?id=130", "[Trevion_Kuphal@bernice.name]"},
                {"?id=46", "[Jeremy.Harann@waino.me]"},
                {"?id=79", "[Esther_Ratke@shayna.biz]"},
                {"?id=157", "[Makenzie@libbie.io]"},
                {"?id=289", "[Paolo@cristopher.com]"}
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

    @DataProvider (name = "delete_data")
    public Object[][] getDeleteData() {
        return new Object[][] {
                {"/489", "{}"},
                {"/256", "{}"},
                {"/71", "{}"},
                {"/190", "{}"},
                {"/403", "{}"},
                {"/339", "{}"},
                {"/275", "{}"},
                {"/221", "{}"},
                {"/111", "{}"},
                {"/14", "{}"}
        };
    }

    @DataProvider (name = "put_data")
    public Object[][] getPutData() {
        HashMap<String, String> data1 = new HashMap<>();
        HashMap<String, String> data2 = new HashMap<>();
        HashMap<String, String> data3 = new HashMap<>();
        HashMap<String, String> data4 = new HashMap<>();
        HashMap<String, String> data5 = new HashMap<>();


        data1.put("title", "TEST POST");
        data1.put("body", "TEST BODY");
        data2.put("title", "TEST POST 2");
        data2.put("body", "TEST BODY 2");
        data3.put("title", "TEST POST 3");
        data3.put("body", "TEST BODY 3");
        data4.put("title", "TEST POST 4");
        data4.put("body", "TEST BODY 4");
        data5.put("title", "TEST POST 5");
        data5.put("body", "TEST BODY 5");

        return new Object[][]{
                {data1, "/92"},
                {data2, "/14"},
                {data3, "/35"},
                {data4, "/77"},
                {data5, "/69"}
        };

    }

}
