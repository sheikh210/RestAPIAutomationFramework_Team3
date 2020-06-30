package utilities;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class TestUtilities {

    static DataReader dataReader = new DataReader();

    // Gets text from String[] and compares against expected String array from Excel workbook
    public static boolean compareTextListToExpectedStringArray(String[] actualArray, String path, String sheetName) throws IOException {
        String[] expectedList = dataReader.fileReaderStringXSSF(path, sheetName);

        int falseCount = 0;
        boolean flag = false;
        for (int i = 0; i < expectedList.length; i++) {
            if (actualArray[i].replaceAll("&amp;", "&").replaceAll("’", "'").trim().equalsIgnoreCase(expectedList[i])) {
                flag = true;
                System.out.println("ACTUAL " + (i + 1) + ": " + actualArray[i]);
                System.out.println("EXPECTED " + (i + 1) + ": " + expectedList[i] + "\n");
            } else {
                System.out.println("FAILED AT INDEX " + (i + 1) + "\nEXPECTED STRING: " + expectedList[i] + "\nACTUAL STRING: "
                        + actualArray[i]);
                falseCount++;
            }
        }
        if (falseCount > 0) {
            flag = false;
        }
        return flag;
    }



    public String getValueByJSONPath(JSONObject responseJSON, String pathJSON){
        Object obj = responseJSON;
        for(String s : pathJSON.split("/"))
            if(!s.isEmpty())
                if(!(s.contains("[") || s.contains("]")))
                    obj = ((JSONObject) obj).get(s);
                else if(s.contains("[") || s.contains("]"))
                    obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0]))
                            .get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
        return obj.toString();
    }
}
