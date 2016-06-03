package user.mobileappuni;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.message.BasicHeader;

public class YaasRestClient {

    JSONObject jsonResponse = null;

    private static final String basicUrl = "https://api.yaas.io/hybris/";

    private static final AsyncHttpClient client = new AsyncHttpClient();

    public void getToken(Context context, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("grant_type", "client_credentials");
        params.add("client_id", "y2YR5x9rcdNPD7QrAfWqm0DxC4eDilaS");
        params.add("client_secret", "QKghiFBEUioCZg5o");
        BasicHeader header = new BasicHeader("Content-Type", "application/x-www-form-urlencoded");
        BasicHeader[] headers = {header};
        client.post(context, buildUrl("oauth2/v1/token"), headers, params, null,handler);
    }

    public void getProduct(Context context, String token, AsyncHttpResponseHandler handler) {
        BasicHeader header = new BasicHeader("Authorization", token);
        BasicHeader[] headers = {header};
        client.get(context, buildUrl("category/v1/helloyaas1/categories?expand=assignments"), headers, new RequestParams(),handler);
    }

    private static String buildUrl(String url) {
        return basicUrl + url;
    }
}
