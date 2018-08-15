package sample.app.task.network;

import java.util.List;

import sample.app.task.pojo.Exclusion;
import sample.app.task.pojo.Facility;
import sample.app.task.pojo.Response;

public interface DataCallback {
    void onDataFetchError();

    void onDataFetchSuccess(List<Facility> facilityList, List<List<Exclusion>> exclusionsList);
}
