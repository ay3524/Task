
package sample.app.task.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Exclusion {

    @SerializedName("facility_id")
    @Expose
    private String facilityId;
    @SerializedName("options_id")
    @Expose
    private String optionsId;

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getOptionsId() {
        return optionsId;
    }

    public void setOptionsId(String optionsId) {
        this.optionsId = optionsId;
    }

    public boolean isEqual(Exclusion exclusion) {
        return facilityId.equals(exclusion.getFacilityId()) && optionsId.equals(exclusion.getOptionsId());
    }

}
