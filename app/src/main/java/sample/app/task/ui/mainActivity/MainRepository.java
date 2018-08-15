package sample.app.task.ui.mainActivity;

import java.util.List;
import sample.app.task.network.DataCallback;
import sample.app.task.network.DataUtils;
import sample.app.task.pojo.Exclusion;

import sample.app.task.pojo.Facility;

public class MainRepository implements DataCallback {
    private MainContract.Action mainActionContract;

    public MainRepository(MainContract.Action mainActionContract) {
        this.mainActionContract = mainActionContract;
    }

    public void makeApiCall() {
        //TODO first check if there is data in Db
        //TODO If no data then make an api call and save the data
        DataUtils dataUtils = new DataUtils(this);
        dataUtils.fetchData();
    }

    @Override
    public void onDataFetchError() {
        mainActionContract.onApiError();
    }

    @Override
    public void onDataFetchSuccess(List<Facility> facilityList, List<List<Exclusion>> exclusionsList) {
        mainActionContract.onDataFetched(facilityList, exclusionsList);
    }




}
