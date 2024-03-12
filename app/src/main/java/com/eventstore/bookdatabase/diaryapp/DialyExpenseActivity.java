package com.eventstore.bookdatabase.diaryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.eventstore.bookdatabase.diaryapp.adapter.ExpenseAdapter;
import com.eventstore.bookdatabase.diaryapp.database.DatabaseHelper;
import com.eventstore.bookdatabase.diaryapp.expense.MainExpenseActivity;
import com.eventstore.bookdatabase.diaryapp.model_class.Expense;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pesonal.adsdk.AppManage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DialyExpenseActivity extends AppCompatActivity {
    List<Expense> expenseList;
    private Spinner expensetype;
    private String[] expenseTypeList={"Select expense type","Breakfast","Lunch","Dinner","Transport Cost","Medicine","Phone Bill","Others"};
    private ArrayAdapter<String> arrayAdapter;
    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    public static DatabaseHelper helper;
    private LottieAnimationView lottieAnimationView;

    private TextView fromDateTV,toDateTV;
    private String fromdate;
    private FloatingActionButton favicon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialy_expense);
        AppManage.getInstance(DialyExpenseActivity.this).loadInterstitialAd(this);
        favicon = findViewById(R.id.favicon);
        expenseList = new ArrayList<>();
        recyclerView = findViewById(R.id.expenseRecycleView);
        lottieAnimationView = findViewById(R.id.animationView);
        helper = new DatabaseHelper(getApplicationContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.isSmoothScrollbarEnabled();
        recyclerView.setLayoutManager(linearLayoutManager);



        expensetype = findViewById(R.id.expense_typeSP);
        arrayAdapter = new ArrayAdapter<>(DialyExpenseActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,expenseTypeList);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        expensetype.setAdapter(arrayAdapter);

        fromDateTV = findViewById(R.id.fromdateTV);
        toDateTV = findViewById(R.id.todateTV);

        getexpense();

        fabscroll();

        getFromDate();

        getToDate();

        //getdata();

        Filtering();
    }
    private void notifyRecyclerView() {

        adapter = new ExpenseAdapter(expenseList,DialyExpenseActivity.this);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    private void Filtering() {

        expensetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){

                    Cursor cursor = helper.showData();
                    if(cursor.getCount() >0){
                        setData(cursor);
                    }
                    else {
                        loadAnimation(true);
                        Toast.makeText(DialyExpenseActivity.this, "No Data available.", Toast.LENGTH_SHORT).show();
                        expenseList.clear();
                    }
                }
                else if(position == 1){

                    Cursor cursor = helper.getItemTypeData(parent.getSelectedItem().toString());

                    if(cursor.getCount() > 0){

                        setData(cursor);
                    }
                    else {
                        loadAnimation(true);
                        Toast.makeText(DialyExpenseActivity.this, "No Data available.", Toast.LENGTH_SHORT).show();
                        expenseList.clear();
                    }
                }
                else if(position == 2){

                    Cursor cursor = helper.getItemTypeData(parent.getSelectedItem().toString());

                    if(cursor.getCount() > 0){

                        setData(cursor);
                    }
                    else {
                        loadAnimation(true);
                        Toast.makeText(DialyExpenseActivity.this, "No Data available.", Toast.LENGTH_SHORT).show();
                        expenseList.clear();
                    }
                }
                else if(position == 3){

                    Cursor cursor = helper.getItemTypeData(parent.getSelectedItem().toString());

                    if(cursor.getCount() > 0){

                        setData(cursor);
                    }
                    else {
                        loadAnimation(true);
                        Toast.makeText(DialyExpenseActivity.this, "No Data available.", Toast.LENGTH_SHORT).show();
                        expenseList.clear();
                    }
                }
                else if(position == 4){

                    Cursor cursor = helper.getItemTypeData(parent.getSelectedItem().toString());

                    if(cursor.getCount() > 0){

                        setData(cursor);
                    }
                    else {
                        loadAnimation(true);
                        Toast.makeText(DialyExpenseActivity.this, "No Data available.", Toast.LENGTH_SHORT).show();
                        expenseList.clear();
                    }
                }
                else if(position == 5){

                    Cursor cursor = helper.getItemTypeData(parent.getSelectedItem().toString());

                    if(cursor.getCount() > 0){

                        setData(cursor);
                    }
                    else {
                        loadAnimation(true);
                        Toast.makeText(DialyExpenseActivity.this, "No Data available.", Toast.LENGTH_SHORT).show();
                        expenseList.clear();
                    }
                }
                else if(position == 6){

                    Cursor cursor = helper.getItemTypeData(parent.getSelectedItem().toString());

                    if(cursor.getCount() > 0){

                        setData(cursor);
                    }
                    else {
                        loadAnimation(true);
                        Toast.makeText(DialyExpenseActivity.this, "No Data available.", Toast.LENGTH_SHORT).show();
                        expenseList.clear();
                    }
                }
                else if(position == 7){

                    Cursor cursor = helper.getItemTypeData(parent.getSelectedItem().toString());

                    if(cursor.getCount() > 0){

                        setData(cursor);
                    }
                    else {
                        loadAnimation(true);
                        Toast.makeText(DialyExpenseActivity.this, "No Data available.", Toast.LENGTH_SHORT).show();
                        expenseList.clear();
                    }
                }
                else {
                    loadAnimation(true);
                    Toast.makeText(DialyExpenseActivity.this, "No Data available.", Toast.LENGTH_SHORT).show();
                    expenseList.clear();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setData(Cursor cursor) {

        expenseList.clear();

        while (cursor.moveToNext()){

            String expense_id = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ID));
            String expense_type = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_TYPE));
            String expense_amount = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_AMOUNT));
            String expense_date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_DATE));
            String expense_time = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_TIME));
            String expese_doc = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_DOC));

            expenseList.add(new Expense(expense_id,expense_type,expense_amount,expense_date,expense_time,expese_doc));
        }

        loadAnimation(false);

        notifyRecyclerView();
    }

    private void loadAnimation(boolean b) {
        if(b){
            lottieAnimationView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }
        else{
            lottieAnimationView.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }


    private void fabscroll() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0){
                    favicon.hide();
                }
                else if(dy<0){
                    favicon.show();
                }
            }
        });
    }

    private void getexpense() {
        favicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManage.getInstance(DialyExpenseActivity.this).showInterstitialAd(DialyExpenseActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(DialyExpenseActivity.this,NextExpenseActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
    }

    private void getToDate() {

        toDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fromdate != null){
                    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            month = month+1;

                            String currentdate = day+"/"+month+"/"+year;

                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                            Date date = null;

                            try {
                                date = dateFormat.parse(currentdate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            toDateTV.setText(dateFormat.format(date));
                        }
                    };

                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(DialyExpenseActivity.this,dateSetListener,year,month,day);
                    datePickerDialog.show();
                }
                else {
                    Toast.makeText(DialyExpenseActivity.this, "Select from date first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getFromDate() {
        fromDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;

                        String currentdate = day+"/"+month+"/"+year;

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                        Date date = null;

                        try {
                            date = dateFormat.parse(currentdate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        fromdate = dateFormat.format(date);
                        fromDateTV.setText(fromdate);
                    }
                };

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(DialyExpenseActivity.this,dateSetListener,year,month,day);
                datePickerDialog.show();
            }
        });


    }

    /*private void getdata() {

        expenseList.clear();



        while (cursor.moveToNext()){

            String expense_id = cursor.getString(cursor.getColumnIndex(helper.COL_ID));
            String expense_type = cursor.getString(cursor.getColumnIndex(helper.COL_TYPE));
            String expense_amount = cursor.getString(cursor.getColumnIndex(helper.COL_AMOUNT));
            String expense_date = cursor.getString(cursor.getColumnIndex(helper.COL_DATE));
            String expense_time = cursor.getString(cursor.getColumnIndex(helper.COL_TIME));
            String expese_doc = cursor.getString(cursor.getColumnIndex(helper.COL_DOC));

            expenseList.add(new Expense(expense_id,expense_type,expense_amount,expense_date,expense_time,expese_doc));

        }
        notifyRecyclerView();
    }*/



}