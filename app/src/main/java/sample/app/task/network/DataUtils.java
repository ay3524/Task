package sample.app.task.network;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import sample.app.task.pojo.Exclusion;
import sample.app.task.pojo.Facility;
import sample.app.task.pojo.Response;

public class DataUtils {

    private DataCallback dataCallback;

    public DataUtils(DataCallback dataCallback) {
        this.dataCallback = dataCallback;
    }

    public void fetchData() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Response> call = apiService.getResponse();

        call.enqueue(responseCall);
    }

    private Callback<Response> responseCall =
            new Callback<Response>() {
                @Override
                public void onResponse(
                        @NonNull Call<Response> call,
                        @NonNull retrofit2.Response<Response> response) {
                    switch (response.code()) {
                        case 200: {
                            onResponseSuccess(response);
                        }
                        break;

                        default:
                            dataCallback.onDataFetchError();
                    }
                }

                @Override
                public void onFailure(
                        @NonNull Call<Response> call,
                        @NonNull Throwable t) {
                    dataCallback.onDataFetchError();
                }
            };

    private void onResponseSuccess(retrofit2.Response<Response> response) {
        Response responseFetched = response.body();
        if (responseFetched != null) {
            List<Facility> facilityList = responseFetched.getFacilities();
            List<List<Exclusion>> exclusionsList = responseFetched.getExclusions();

            dataCallback.onDataFetchSuccess(facilityList, exclusionsList);
        }
    }
}
