package apiTests;

import org.json.JSONArray;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class TestApiAviasalesPriceOnWeek {

    private final static String url = "http://api.travelpayouts.com/v2/prices/week-matrix?currency=rub&origin=LED&destination=HKT&show_to_affiliates=true&depart_date=2022-07-04&return_date=2022-07-18&token=3ede34968c6e4d84fd585357e8208547";

    @Test(groups = "regression")
    public void checkStatusCode()  {

        CustomHttpClient customHttpClient = new CustomHttpClient();
        customHttpClient.sendGetRequest(url);

        assertEquals(customHttpClient.getStatusCode(), 200);
    }

    @Test(groups = "regression")
    public void checkAllObjectsAreActual() throws IOException {

        CustomHttpClient customHttpClient = new CustomHttpClient();
        customHttpClient.sendGetRequest(url);
        String jsonString = customHttpClient.getBody();
        ArrayList<Boolean> list = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(jsonString);

        JSONArray jsonArray = jsonObject.getJSONArray("data");

        for (Object jsonObj : jsonArray)
            list.add(((JSONObject) jsonObj).getBoolean("actual"));

       assertTrue(list.stream().allMatch(x->x));
    }

    @Test(groups = "regression")
    public void checkAllObjectsAreEconomClass() throws IOException {

        CustomHttpClient customHttpClient = new CustomHttpClient();
        customHttpClient.sendGetRequest(url);
        String jsonString = customHttpClient.getBody();
        ArrayList<Integer> list = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(jsonString);

        JSONArray jsonArray = jsonObject.getJSONArray("data");

        for (Object jsonObj : jsonArray)
            list.add(((JSONObject) jsonObj).getInt("trip_class"));

        assertTrue(list.stream().allMatch(x->x==0));
    }
}


