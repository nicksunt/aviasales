package apiTests;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CustomHttpClient {
    String body;
    int statusCode;
    HttpResponse httpResponse;
    HttpUriRequest request;

    public void sendGetRequest(String url) {
        request = new HttpGet(url);

        try{
            httpResponse = HttpClientBuilder.create().build().execute(request);
        }
        catch (IOException e){
            // todo log
        }
    }

    public int getStatusCode(){
        statusCode = httpResponse.getStatusLine().getStatusCode();
        return statusCode;
    }

    public String getBody()  {
        try {
            body = EntityUtils.toString(httpResponse.getEntity());
        }
        catch (IOException e){
            // todo log
        }
        return body;
    }

}
