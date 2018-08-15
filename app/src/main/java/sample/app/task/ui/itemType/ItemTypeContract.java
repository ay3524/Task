package sample.app.task.ui.itemType;

import java.util.List;

import sample.app.task.pojo.Option;

public interface ItemTypeContract {

    interface Action {

    }

    interface View {
        void showProgress();
        void hideProgress();
    }
}
