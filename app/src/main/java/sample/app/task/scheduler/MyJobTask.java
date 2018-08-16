package sample.app.task.scheduler;

import android.util.Log;

import java.util.List;

import sample.app.task.network.DataCallback;
import sample.app.task.pojo.Exclusion;
import sample.app.task.pojo.Facility;

public class MyJobTask implements DataCallback {

    public void fetchDataFromServer() {
//        DataUtils dataUtils = new DataUtils(this);
//        dataUtils.fetchData();
    }

    @Override
    public void onDataFetchError() {
        Log.e(MyJobTask.class.getSimpleName(), "API Call Error");
    }

    @Override
    public void onDataFetchSuccess(List<Facility> facilityList, List<List<Exclusion>> exclusionsList) {

    }
}
