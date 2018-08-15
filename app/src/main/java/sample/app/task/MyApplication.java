package sample.app.task;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

//        createWorkManager();
        createRealm();
    }

    private void createRealm() {
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("tasky.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

//    private void createWorkManager() {
//        PeriodicWorkRequest periodicWork = new PeriodicWorkRequest.Builder(MyWorker.class, 5, TimeUnit.SECONDS)
//                .build();
//        WorkManager.getInstance().enqueue(periodicWork);
//    }
}
