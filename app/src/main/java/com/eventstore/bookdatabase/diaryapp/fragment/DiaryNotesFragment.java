package com.eventstore.bookdatabase.diaryapp.fragment;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.eventstore.bookdatabase.diaryapp.DashboardActivity;
import com.eventstore.bookdatabase.diaryapp.DiaryNotesActivity;
import com.eventstore.bookdatabase.diaryapp.HomeActivity;
import com.eventstore.bookdatabase.diaryapp.NotesActivity;
import com.eventstore.bookdatabase.diaryapp.R;
import com.eventstore.bookdatabase.diaryapp.RequestNetwork;
import com.eventstore.bookdatabase.diaryapp.SettingsActivity;
import com.eventstore.bookdatabase.diaryapp.ShopActivity;
import com.eventstore.bookdatabase.diaryapp.TodoActivity;
import com.pesonal.adsdk.AppManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class DiaryNotesFragment extends Fragment {
    private HashMap<String, Object> pp = new HashMap<>();
    private double num = 0;

    private ArrayList<String> db = new ArrayList<>();
    private ArrayList<String> img = new ArrayList<>();

    private ScrollView vscroll1;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_diary_notes, container, false);
        AppManage.getInstance(getActivity()).showNative((ViewGroup) view.findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);
        AppManage.getInstance(getActivity()).loadInterstitialAd(getActivity());

//        AppManage.getInstance(getActivity()).showNative((ViewGroup) view.findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }
        else {
//            initializeLogic();
        }
        vscroll1 = (ScrollView) view.findViewById(R.id.vscroll1);
        bbo = (LinearLayout) view.findViewById(R.id.bbo);
        note = (LinearLayout) view.findViewById(R.id.note);
        todo = (LinearLayout) view.findViewById(R.id.todo);
        shop = (LinearLayout) view.findViewById(R.id.shop);
        diary = (LinearLayout) view.findViewById(R.id.diary);
        imageview2 = (ImageView) view.findViewById(R.id.imageview2);
        tnote = (TextView) view.findViewById(R.id.tnote);
        imageview3 = (ImageView) view.findViewById(R.id.imageview3);
        ttodo = (TextView) view.findViewById(R.id.ttodo);
        imageview4 = (ImageView) view.findViewById(R.id.imageview4);
        tshop = (TextView) view.findViewById(R.id.tshop);
        imageview5 = (ImageView) view.findViewById(R.id.imageview5);
        tdiary = (TextView) view.findViewById(R.id.tdiary);
        dbl = new AlertDialog.Builder(getActivity());
        req = new RequestNetwork(getActivity());
        sgl = new AlertDialog.Builder(getActivity());
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                AppManage.getInstance(getActivity()).showInterstitialAd(getActivity(), new AppManage.MyCallback() {
                    public void callbackCall() {
                        intent.putExtra("bookmark", "false");
                        intent.putExtra("s", "false");
                        intent.setClass(getActivity(), NotesActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });

        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                AppManage.getInstance(getActivity()).showInterstitialAd(getActivity(), new AppManage.MyCallback() {
                    public void callbackCall() {
                        intent.putExtra("t", "p");
                        intent.setClass(getActivity(), TodoActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                AppManage.getInstance(getActivity()).showInterstitialAd(getActivity(), new AppManage.MyCallback() {
                    public void callbackCall() {
                        intent.setClass(getActivity(), ShopActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });

        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                AppManage.getInstance(getActivity()).showInterstitialAd(getActivity(), new AppManage.MyCallback() {
                    public void callbackCall() {
                        intent.setClass(getActivity(), DiaryNotesActivity.class);
                        startActivity(intent);
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
        return view;
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 1000) {
//            initializeLogic();
//        }
//    }
//    private void initializeLogic() {
//        android.graphics.drawable.GradientDrawable s = new android.graphics.drawable.GradientDrawable(); s.setColor(Color.parseColor("#ffffff")); s.setCornerRadius(10); note.setBackground(s);
//        todo.setBackground(s);
//        shop.setBackground(s);
//        diary.setBackground(s);
//        android.graphics.drawable.GradientDrawable t = new android.graphics.drawable.GradientDrawable(); t.setColor(Color.parseColor("#367CFF")); t.setCornerRadius(70); tnote.setBackground(t);
//        ttodo.setBackground(t);
//        tshop.setBackground(t);
//        tdiary.setBackground(t);
//    }

    @Override
    public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);

        switch (_requestCode) {

            default:
                break;
        }
    }
    @Deprecated
    public void showMessage(String _s) {
        Toast.makeText(getActivity(), _s, Toast.LENGTH_SHORT).show();
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