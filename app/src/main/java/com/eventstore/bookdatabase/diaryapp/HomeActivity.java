package com.eventstore.bookdatabase.diaryapp;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.pesonal.adsdk.AppManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class HomeActivity extends AppCompatActivity {

    private HashMap<String, Object> pp = new HashMap<>();
    private double num = 0;
    ImageView back;

    private ArrayList<String> db = new ArrayList<>();
    private ArrayList<String> img = new ArrayList<>();

    private LinearLayout linear1;
    private ScrollView vscroll1;
    private TextView textview1;
    private ImageView settings;
    private LinearLayout bbo;
    private LinearLayout note;
    private LinearLayout todo;
    private LinearLayout shop;
    private LinearLayout diary;
    private ImageView imageview2;
    private TextView tnote;
    private ImageView imageview3;
    private TextView ttodo;
    private ImageView imageview4;
    private TextView tshop;
    private ImageView imageview5;
    private TextView tdiary;

    private Intent intent = new Intent();
    private AlertDialog.Builder dbl;
    private RequestNetwork req;
    private RequestNetwork.RequestListener _req_request_listener;
    private AlertDialog.Builder sgl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AppManage.getInstance(HomeActivity.this).loadInterstitialAd(this);
        AppManage.getInstance(HomeActivity.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);
        initialize(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }
        else {
            initializeLogic();
        }
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(HomeActivity.this).showInterstitialAd(HomeActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(HomeActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            initializeLogic();
        }
    }

    private void initialize(Bundle _savedInstanceState) {

        linear1 = (LinearLayout) findViewById(R.id.linear1);
        vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
        textview1 = (TextView) findViewById(R.id.textview1);
        settings = (ImageView) findViewById(R.id.settings);
        bbo = (LinearLayout) findViewById(R.id.bbo);
        note = (LinearLayout) findViewById(R.id.note);
        todo = (LinearLayout) findViewById(R.id.todo);
        shop = (LinearLayout) findViewById(R.id.shop);
        diary = (LinearLayout) findViewById(R.id.diary);
        imageview2 = (ImageView) findViewById(R.id.imageview2);
        tnote = (TextView) findViewById(R.id.tnote);
        imageview3 = (ImageView) findViewById(R.id.imageview3);
        ttodo = (TextView) findViewById(R.id.ttodo);
        imageview4 = (ImageView) findViewById(R.id.imageview4);
        tshop = (TextView) findViewById(R.id.tshop);
        imageview5 = (ImageView) findViewById(R.id.imageview5);
        tdiary = (TextView) findViewById(R.id.tdiary);
        dbl = new AlertDialog.Builder(this);
        req = new RequestNetwork(this);
        sgl = new AlertDialog.Builder(this);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                AppManage.getInstance(HomeActivity.this).showInterstitialAd(HomeActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        intent.setClass(getApplicationContext(), SettingsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });

        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                AppManage.getInstance(HomeActivity.this).showInterstitialAd(HomeActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        intent.putExtra("bookmark", "false");
                        intent.putExtra("s", "false");
                        intent.setClass(getApplicationContext(), NotesActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });

        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                AppManage.getInstance(HomeActivity.this).showInterstitialAd(HomeActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        intent.putExtra("t", "p");
                        intent.setClass(getApplicationContext(), TodoActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                AppManage.getInstance(HomeActivity.this).showInterstitialAd(HomeActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        intent.setClass(getApplicationContext(), ShopActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });

        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                AppManage.getInstance(HomeActivity.this).showInterstitialAd(HomeActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        intent.setClass(getApplicationContext(), DiaryNotesActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });

        _req_request_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String _param1, String _param2) {
                final String _tag = _param1;
                final String _response = _param2;
                sgl.setTitle("Error");
                sgl.setMessage("message : \nthis feature still under development.");
                sgl.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {

                    }
                });
                sgl.create().show();
            }

            @Override
            public void onErrorResponse(String _param1, String _param2) {
                final String _tag = _param1;
                final String _message = _param2;
                sgl.setTitle("Error");
                sgl.setMessage("message : \nthis feature still under development.");
                sgl.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {

                    }
                });
                sgl.create().show();
            }
        };
    }
    private void initializeLogic() {
        android.graphics.drawable.GradientDrawable s = new android.graphics.drawable.GradientDrawable(); s.setColor(Color.parseColor("#ffffff")); s.setCornerRadius(10); note.setBackground(s);
        todo.setBackground(s);
        shop.setBackground(s);
        diary.setBackground(s);
        android.graphics.drawable.GradientDrawable t = new android.graphics.drawable.GradientDrawable(); t.setColor(Color.parseColor("#367CFF")); t.setCornerRadius(70); tnote.setBackground(t);
        ttodo.setBackground(t);
        tshop.setBackground(t);
        tdiary.setBackground(t);
    }

    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);

        switch (_requestCode) {

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        AppManage.getInstance(HomeActivity.this).showInterstitialAd(HomeActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                Intent intent=new Intent(HomeActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        }, "", AppManage.app_mainClickCntSwAd);
//        if (false) {
//
//        }
//        else {
//            dbl.setTitle("Exit");
//            dbl.setMessage("Are you sure want to exit?");
//            dbl.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface _dialog, int _which) {
//                    finish();
//                }
//            });
//            dbl.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface _dialog, int _which) {
//
//                }
//            });
//            dbl.create().show();
//        }
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