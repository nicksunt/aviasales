package apiTests;

import com.google.gson.Gson;
import org.testng.annotations.Test;
import java.io.IOException;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestApiAviasalesLowPrice {

    private final static String url = "https://api.travelpayouts.com/aviasales/v3/prices_for_dates?origin=MOW&destination=DXB&currency=usd&departure_at=2022-07-01&return_at=2022-07-21&sorting=price&direct=true&limit=10&token=3ede34968c6e4d84fd585357e8208547";

    @Test(groups = {"smoke"})
    public void checkStatusCode()  {

        CustomHttpClient customHttpClient = new CustomHttpClient();
        customHttpClient.sendGetRequest(url);

        assertEquals(customHttpClient.getStatusCode(), 200);
    }

    @Test(groups = {"smoke"})
    public void checkAllObjectsHavePrice() throws IOException {

        CustomHttpClient customHttpClient = new CustomHttpClient();
        customHttpClient.sendGetRequest(url);
        String jsonString = customHttpClient.getBody();
        Root fromJson = new Gson().fromJson(jsonString, Root.class);

        assertTrue(fromJson.getData().stream().allMatch(x->x.getPrice()>0));
    }

    @Test(groups = {"smoke"})
    public void checkPlaceDepartAndDestination() throws IOException {

        CustomHttpClient customHttpClient = new CustomHttpClient();
        customHttpClient.sendGetRequest(url);
        String jsonString = customHttpClient.getBody();
        Root fromJson = new Gson().fromJson(jsonString, Root.class);

        assertTrue(fromJson.getData().stream()
                .allMatch(x -> (x.getOrigin().equals("MOW")) && x.getDestination().equals("DXB")));
    }
}
