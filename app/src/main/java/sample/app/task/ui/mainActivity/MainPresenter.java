package sample.app.task.ui.mainActivity;

import java.util.ArrayList;
import java.util.List;

import sample.app.task.pojo.Exclusion;
import sample.app.task.pojo.Facility;
import sample.app.task.pojo.Option;
import sample.app.task.utils.ListUtils;

public class MainPresenter implements MainContract.Action {
    private MainContract.View mainContractView;
    private List<List<Exclusion>> exclusionsList = new ArrayList<>(0);
    private List<Facility> facilityList = new ArrayList<>(0);
    private static int listSize = 0;
    private int i = 0;
    private List<Exclusion> currentExclusionList = new ArrayList<>();

    public MainPresenter(MainContract.View mainContractView) {
        this.mainContractView = mainContractView;
    }

    public void init() {
        MainRepository mainRepository = new MainRepository(this);
        mainRepository.makeApiCall();
        mainContractView.showProgress();
    }

    @Override
    public void onApiError() {

    }

    @Override
    public void onDataFetched(List<Facility> facilityList, List<List<Exclusion>> exclusionsList) {
        mainContractView.hideProgress();
        this.exclusionsList = exclusionsList;
        this.facilityList = facilityList;
        List<Option> facilityOptionList = facilityList.get(i).getOptions();
        mainContractView.refreshData(facilityOptionList);
        listSize = facilityList.size();
    }

    public void exclusionLogic(Option option) {

        if ((i + 1) < listSize && i >= 0) {
            //TODO Pass the filtered Data not "facilityList.get(i).getOptions()"
            navigateLogic(facilityList.get(i).getOptions());
            String facilityId = facilityList.get(i).getFacilityId();
            String optionId = option.getId();

            Exclusion exclusion = new Exclusion();
            exclusion.setFacilityId(facilityId);
            exclusion.setOptionsId(optionId);
            currentExclusionList.add(exclusion);

            Facility nextFacility = facilityList.get(i + 1);
            List<Option> nextOptionList = getNextOptionForFacilityId(nextFacility.getFacilityId(), nextFacility.getOptions());

            navigateLogic(nextOptionList);
            i++;
        } else {
            mainContractView.onEndReached();
        }

    }

    private List<Option> getNextOptionForFacilityId(String facilityId, List<Option> optionList) {

        List<Option> result;

        List<List<Exclusion>> finalExclusionArray = new ArrayList<>();

        for (int i = 0; i < currentExclusionList.size(); i++) {
            List<List<Exclusion>> exclusionArrayOfCurrentFacility = getExclusionListOfFacility(currentExclusionList.get(i));
            finalExclusionArray.addAll(exclusionArrayOfCurrentFacility);
        }

        result = getFinalOptionsForFacilityId(facilityId, optionList, finalExclusionArray);

        return result;
    }

    private void navigateLogic(List<Option> optionsList) {
        if (listSize > 0 && listSize >= i + 1 && i >= 0) {
            mainContractView.refreshData(optionsList);
        } else {
            mainContractView.onEndReached();
        }
    }

    private List<Option> getFinalOptionsForFacilityId(String facilityId, List<Option> optionList, List<List<Exclusion>> finalExclusionArray) {

        List<String> ids = ListUtils.getAllExclusionId(facilityId, finalExclusionArray);
        List<Option> result = new ArrayList<>();

        result.addAll(optionList);
        for (String id : ids) {
            result = ListUtils.getUpdatedOption(result, id);
        }
        return result;
    }

    private List<List<Exclusion>> getExclusionListOfFacility(Exclusion exclusion) {

        List<List<Exclusion>> result = new ArrayList<>();

        for (int i = 0; i < exclusionsList.size(); i++) {
            List<Exclusion> currentExclusionArray = exclusionsList.get(i);
            Exclusion ifObj = currentExclusionArray.get(0);

            if (ifObj.isEqual(exclusion)) {
                result.add(currentExclusionArray);
            }
        }
        return result;
    }
}
