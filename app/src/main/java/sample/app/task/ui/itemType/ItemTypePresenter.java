package sample.app.task.ui.itemType;

import java.util.List;

import sample.app.task.pojo.Option;

public class ItemTypePresenter implements ItemTypeContract.Action {

    private ItemTypeContract.View propertyTypeViewContract;
    private ItemTypeRepository itemTypeRepository;

    public ItemTypePresenter(ItemTypeContract.View propertyTypeViewContract) {
        this.propertyTypeViewContract = propertyTypeViewContract;
    }

    public void init() {
        itemTypeRepository = new ItemTypeRepository(this);
    }

}
