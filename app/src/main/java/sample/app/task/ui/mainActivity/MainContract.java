package sample.app.task.ui.mainActivity;

import java.util.List;

import sample.app.task.pojo.Exclusion;
import sample.app.task.pojo.Facility;
import sample.app.task.pojo.Option;

public interface MainContract {
    interface Action {
        void onApiError();
        void onDataFetched(List<Facility> facilityList, List<List<Exclusion>> exclusionsList);
    }

    interface View {
        void showProgress();
        void hideProgress();
        void onError();
        void refreshData(List<Option> optionList);
        void onEndReached();
    }
}
