package com.eventstore.bookdatabase.diaryapp.themechanging;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eventstore.bookdatabase.diaryapp.R;

public class BaseActivity extends AppCompatActivity {

    private int currentTheme =0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        currentTheme = Utility.getTheme(this);
        setAppTheme(currentTheme);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        int theme = Utility.getTheme(this);
        if(currentTheme!=theme)
            recreate();
    }

    private void setAppTheme(int currentTheme) {
        switch (currentTheme){
            case 1:
                setTheme(R.style.AppTheme_Orange);
                break;
            case 2:
                setTheme(R.style.AppTheme_Brown);
                break;
            case 3:
                setTheme(R.style.AppTheme_Yellow);
                break;
            case 4:
                setTheme(R.style.AppTheme_Green);
                break;
            case 5:
                setTheme(R.style.AppTheme_Blue);
                break;
            case 6:
                setTheme(R.style.AppTheme_Purple);
                break;
            default:
                setTheme(R.style.Theme_DiaryApp);
        }
    }
}
