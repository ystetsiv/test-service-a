package core;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HttpClient {

    @Value("${weather.host.url}")
    private String weatherUrl;

    private final CloseableHttpClient httpClient = HttpClients.createDefault();


    public Integer sendGet(String city) throws Exception {

        HttpGet request = new HttpGet(weatherUrl + "weather?city=" + city);

        // add request headers
        //request.addHeader("custom-key", "mkyong");
        //request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            String result = null;

            if (entity != null) {
                // return it as a String
                 result= EntityUtils.toString(entity);
                System.out.println(result);
            }


            JSONObject json = new JSONObject(result);

           return json.getInt("temperature");

        }

    }
}
