package sample.app.task.utils;

import java.util.ArrayList;
import java.util.List;

import sample.app.task.pojo.Exclusion;
import sample.app.task.pojo.Option;

public class ListUtils {
    public static List<Option> getUpdatedOption(List<Option> optionArray, String id) {

        for (int i = 0; i < optionArray.size(); i++) {
            if (optionArray.get(i).getId().equals(id)) {
                optionArray.remove(i);
            }
        }
        return optionArray;
    }

    public static List<String> getAllExclusionId(String facilityId, List<List<Exclusion>> finalExclusionArray) {

        List<String> ids = new ArrayList<>();

        for (int i = 0; i < finalExclusionArray.size(); i++) {
            if (finalExclusionArray.get(i).get(1).getFacilityId().equals(facilityId)) {
                ids.add(finalExclusionArray.get(i).get(1).getOptionsId());
            }
        }
        return ids;
    }
}
