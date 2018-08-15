package sample.app.task.ui.itemType;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import sample.app.task.network.ApiClient;
import sample.app.task.network.ApiInterface;
import sample.app.task.pojo.Option;
import sample.app.task.pojo.Response;

public class ItemTypeRepository {

    private ItemTypeContract.Action propertyTypeActionContract;

    public ItemTypeRepository(ItemTypeContract.Action propertyTypeActionContract) {
        this.propertyTypeActionContract = propertyTypeActionContract;
    }
}
