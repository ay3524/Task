package sample.app.task.scheduler;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import sample.app.task.network.ApiClient;
import sample.app.task.network.ApiInterface;
import sample.app.task.network.DataCallback;
import sample.app.task.network.DataUtils;
import sample.app.task.pojo.Exclusion;
import sample.app.task.pojo.Facility;
import sample.app.task.pojo.Response;

public class MyJobTask implements DataCallback {

    public void fetchDataFromServer() {
        DataUtils dataUtils = new DataUtils(this);
        dataUtils.fetchData();
    }

    @Override
    public void onDataFetchError() {
        Log.e(MyJobTask.class.getSimpleName(), "API Call Error");
    }

    @Override
    public void onDataFetchSuccess(List<Facility> facilityList, List<List<Exclusion>> exclusionsList) {

    }
}
