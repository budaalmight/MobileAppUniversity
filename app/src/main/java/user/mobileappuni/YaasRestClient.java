package user.mobileappuni;

import android.content.Context;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

import java.text.SimpleDateFormat;
import java.util.Date;

public class YaasRestClient {
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    private static final String basicUrl = "https://api.yaas.io/hybris/";

    private static final AsyncHttpClient client = new AsyncHttpClient();

    public void getToken(Context context, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("grant_type", "client_credentials");
        params.add("client_id", "y2YR5x9rcdNPD7QrAfWqm0DxC4eDilaS");
        params.add("client_secret", "QKghiFBEUioCZg5o");
        params.add("scope", "hybris.document_manage hybris.document_view");
        BasicHeader header = new BasicHeader("Content-Type", "application/x-www-form-urlencoded");
        BasicHeader[] headers = {header};
        client.post(context, buildUrl("oauth2/v1/token"), headers, params, null, handler);
    }

    public void getProducts(Context context, String token, AsyncHttpResponseHandler handler) {
        BasicHeader header = new BasicHeader("Authorization", "Bearer " + token);
        BasicHeader[] headers = {header};
        client.get(context, buildUrl("productdetails/v1/helloyaas1/productdetails"), headers, new RequestParams(),handler);
    }

    public void getVisits(Context context, String token, String sport, AsyncHttpResponseHandler handler) {
        BasicHeader header = new BasicHeader("Authorization", "Bearer " + token);
        RequestParams params = new RequestParams();
        params.put("user", LoginActivity.getUsername());
        if (sport != null) {
            params.put("sport", sport);
        }
        client.get(context, buildUrl("document/v1/helloyaas1/helloyaas1.storefront111/data/visits"), new Header[]{header}, params, handler);
    }

    public void addVisit(Context context, String token, String username, String sport, String place, AsyncHttpResponseHandler handler) {
        BasicHeader header = new BasicHeader("Authorization", "Bearer" + token);
        BasicHeader metadata = new BasicHeader("hybris-metaData", "date:date");
        RequestParams params = new RequestParams();
        params.put("user", username);
        params.put("sport", sport);
        params.put("place", place);
        params.put("date", format.format(new Date()));
        client.post(context, buildUrl("document/v1/helloyaas1/helloyaas1.storefront111/data/visits"), new Header[]{header, metadata}, params, "application/json", handler);
    }

    private static String buildUrl(String url) {
        return basicUrl + url;
    }
}
