package oansweety.cpn.co.th.oanqrcode.utility;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by kachutima on 13/3/2561.
 */

public class GetFoodWhereQRcode extends AsyncTask<String, Void, String>{

    private Context context;

    public GetFoodWhereQRcode(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("QRcode", strings[0])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[1]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
