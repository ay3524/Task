package sample.app.task.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sample.app.task.pojo.Response;

public interface ApiInterface {
    @GET("ad-assignment/db")
    Call<Response> getResponse();
}
