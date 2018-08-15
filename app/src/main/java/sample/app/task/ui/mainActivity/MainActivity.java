package sample.app.task.ui.mainActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sample.app.task.R;
import sample.app.task.pojo.Option;
import sample.app.task.scheduler.MyJobService;
import sample.app.task.ui.itemType.ItemTypeFragment;
import sample.app.task.ui.itemType.ItemTypeListener;

public class MainActivity extends AppCompatActivity implements MainContract.View, ItemTypeListener {

    public static String PROPERTY_TYPE_FRAGMENT_TAG = "property_type_fragment";
    @BindView(R.id.progress)
    ProgressBar progressBar;
    MainPresenter mainPresenter;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this);
        mainPresenter.init();
        scheduleJob();
    }

    public void addFragment(@IdRes int containerViewId,
                            @NonNull Fragment fragment,
                            @NonNull String fragmentTag) {


        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment) {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(containerViewId, fragment);
        ft.commit();
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
    public void onError() {
        Toast.makeText(this, "API Error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshData(List<Option> optionList) {
        ItemTypeFragment itemTypeFragment = ItemTypeFragment.newInstance(optionList);
        itemTypeFragment.setItemTypeListener(this);
        replaceFragment(R.id.activity_main_content, itemTypeFragment);
    }

    @Override
    public void onEndReached() {
        Toast.makeText(this, "End Reached!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemListenFromFragment(Option option) {
        Toast.makeText(getApplicationContext(), option.getName(), Toast.LENGTH_SHORT).show();
        //TODO Apply exclusion logic
        mainPresenter.exclusionLogic(option);
    }

    @Override
    public void onBackPressed() {
        int backStackCount = mFragmentManager.getBackStackEntryCount();
        if (backStackCount > 1) {
            mFragmentManager.popBackStackImmediate();
            mainPresenter.updatePositionValue(backStackCount);
        } else {
            finish();
        }
    }

    private void scheduleJob() {
        JobScheduler jobScheduler = (JobScheduler) getApplicationContext()
                .getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName componentName = new ComponentName(this,
                MyJobService.class);

        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setPeriodic(1000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .build();
        if (jobScheduler != null) {
            jobScheduler.schedule(jobInfo);
        }
    }
}
