package sample.app.task.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //This url can be changed to some valid base url later
    public static final String BASE_URL = "https://my-json-server.typicode.com/iranjith4/";
    private static Retrofit apiClient = null;

    public static Retrofit getClient() {
        if (apiClient == null) {
            apiClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return apiClient;
    }
}
