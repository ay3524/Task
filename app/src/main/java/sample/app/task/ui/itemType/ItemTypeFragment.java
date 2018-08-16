package sample.app.task.ui.itemType;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sample.app.task.R;
import sample.app.task.pojo.Option;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemTypeFragment extends Fragment implements ItemTypeContract.View, ItemTypeClickListener {

    @BindView(R.id.fragment_property_type_list)
    RecyclerView recyclerView;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.text_header)
    TextView headerText;
    ItemTypePresenter itemTypePresenter;
    ItemTypeAdapter itemTypeAdapter;
    ItemTypeListener itemTypeListener;
    ArrayList<Option> optionArrayList = new ArrayList<>(0);

    public ItemTypeFragment() {
        // Required empty public constructor

    }

    public void setItemTypeListener(ItemTypeListener itemTypeListener) {
        this.itemTypeListener = itemTypeListener;
    }

    public static ItemTypeFragment newInstance(List<Option> optionList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("item", (ArrayList<? extends Parcelable>) optionList);
        ItemTypeFragment fragment = new ItemTypeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intializeViews(view);
        itemTypePresenter = new ItemTypePresenter(this);
        itemTypePresenter.init();
    }

    private void intializeViews(View view) {
        ButterKnife.bind(ItemTypeFragment.this, view);
        itemTypeAdapter = new ItemTypeAdapter(this, getActivity());

        // Set Layout Manager.
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(itemTypeAdapter);

        Bundle bundle = getArguments();
        if (bundle != null) {
            optionArrayList = bundle.getParcelableArrayList("item");
            itemTypeAdapter.updateList(optionArrayList);
        }

    }

    @Override
    public void showProgress() {
        if (progressBar.getVisibility() == View.GONE) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClicked(Option option) {
        itemTypeListener.onItemListenFromFragment(option);
    }
}
