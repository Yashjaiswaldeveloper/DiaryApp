package com.eventstore.bookdatabase.diaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pesonal.adsdk.AppManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CheckingActivity extends AppCompatActivity {
    public final int REQ_CD_FILES = 101;
    private Timer _timer = new Timer();

    private HashMap<String, Object> tmp = new HashMap<>();
    TextView next;
    private ArrayList<String> store = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> colour = new ArrayList<>();
//    private ProgressBar progressbar1;
    private Intent intent = new Intent();
    private Intent files = new Intent(Intent.ACTION_GET_CONTENT);
    private SharedPreferences data;
    private SpeechRecognizer ppp;
    LinearLayout tv;
    private TimerTask timmm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);
        AppManage.getInstance(CheckingActivity.this).loadInterstitialAd(this);
        initialize(savedInstanceState);
        next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(CheckingActivity.this).showInterstitialAd(CheckingActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(CheckingActivity.this,DashboardActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }
        else {
            initializeLogic();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            initializeLogic();
        }
    }

    private void initialize(Bundle _savedInstanceState) {
//        progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
        files.setType("*/*");
        files.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        data = getSharedPreferences("color", Activity.MODE_PRIVATE);
        ppp = SpeechRecognizer.createSpeechRecognizer(this);
    }
    private void initializeLogic() {
        if (FileUtil.isExistFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/.database/nodata.aio"))) {

        }
        else {
            FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/.database/note.aio"), "[{\"title\":\"Hello There\",\"content\":\"Write your first note\",\"ispin\":\"true\",\"date\":\"today\"}]");
            FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/.database/todo.aio"), "[{\"title\":\"Drink a coffe\",\"isdone\":\"true\"}]");
            FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/.database/shop.aio"), "[{\"product\":\"Buy a lipstick\",\"isimportant\":\"true\",\"at\":\"carrefour\",\"isdone\":\"true\"}]");
            FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/.database/diary.aio"), "[{\"title\":\"First time...\",\"date\":\"today\",\"content\":\"hello, it's my first time trying this app\",\"mood\":\"😃\",\"img\":\"ex\"}]");
            FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/.database/nodata.aio"), "nodata");
        }
        if (data.getString("set", "").equals("true")) {

        }
        else {
            data.edit().putString("note", "#1de9b6").commit();
            data.edit().putString("todo1", "#f8bbd0").commit();
            data.edit().putString("todo2", "#b2ebf2").commit();
            data.edit().putString("shop1", "#f8bbd0").commit();
            data.edit().putString("shop2", "#b2ebf2").commit();
            tmp.put("data", "#f8bbd0");
            tmp.put("name", "pink");
            colour.add(tmp);
            tmp = new HashMap<>();
            tmp.put("data", "#e1bee7");
            tmp.put("name", "purple");
            colour.add(tmp);
            tmp = new HashMap<>();
            tmp.put("data", "#ffcdd2");
            tmp.put("name", "red");
            colour.add(tmp);
            tmp = new HashMap<>();
            tmp.put("data", "#bbdefb");
            tmp.put("name", "blue");
            colour.add(tmp);
            tmp = new HashMap<>();
            tmp.put("data", "#b2ebf2");
            tmp.put("name", "cyan");
            colour.add(tmp);
            tmp = new HashMap<>();
            tmp.put("data", "#b2dfdb");
            tmp.put("name", "teal");
            colour.add(tmp);
            tmp = new HashMap<>();
            tmp.put("data", "#ffe0b2");
            tmp.put("name", "orange");
            colour.add(tmp);
            tmp = new HashMap<>();
            tmp.put("data", "#ffffff");
            tmp.put("name", "white");
            colour.add(tmp);
            FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/.database/color.aio"), new Gson().toJson(colour));
        }
//        timmm = new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        intent.setClass(getApplicationContext(), DashboardActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
//            }
//        };
//        _timer.schedule(timmm, (int)(1000));
    }

    @Override
    public void onBackPressed() {
        AppManage.getInstance(CheckingActivity.this).showInterstitialAd(CheckingActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                CheckingActivity.super.onBackPressed();
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }

    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);

        switch (_requestCode) {

            default:
                break;
        }
    }

    @Deprecated
    public void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }

    @Deprecated
    public int getLocationX(View _v) {
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location[0];
    }

    @Deprecated
    public int getLocationY(View _v) {
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location[1];
    }

    @Deprecated
    public int getRandom(int _min, int _max) {
        Random random = new Random();
        return random.nextInt(_max - _min + 1) + _min;
    }

    @Deprecated
    public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
        ArrayList<Double> _result = new ArrayList<Double>();
        SparseBooleanArray _arr = _list.getCheckedItemPositions();
        for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
            if (_arr.valueAt(_iIdx))
                _result.add((double)_arr.keyAt(_iIdx));
        }
        return _result;
    }

    @Deprecated
    public float getDip(int _input){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
    }

    @Deprecated
    public int getDisplayWidthPixels(){
        return getResources().getDisplayMetrics().widthPixels;
    }

    @Deprecated
    public int getDisplayHeightPixels(){
        return getResources().getDisplayMetrics().heightPixels;
    }

}
