package sample.app.task.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sample.app.task.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinalFragment extends Fragment {


    @BindView(R.id.final_text)
    TextView finalText;

    public FinalFragment() {
        // Required empty public constructor
    }

    public static FinalFragment newInstance(String selection) {
        Bundle args = new Bundle();
        args.putString("key",selection);
        FinalFragment fragment = new FinalFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_final, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(FinalFragment.this, view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String text = bundle.getString("key");
            finalText.setText("You have made these selections : \n"+text);
        }
    }
}
