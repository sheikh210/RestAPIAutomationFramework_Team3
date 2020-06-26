package utilities;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtilities {

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
