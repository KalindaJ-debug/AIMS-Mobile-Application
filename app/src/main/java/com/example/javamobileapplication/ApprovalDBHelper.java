package com.example.javamobileapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class ApprovalDBHelper {
    private String test;
    private String testColumn1;

    public ApprovalDBHelper() {
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTestColumn1() {
        return testColumn1;
    }

    public void setTestColumn1(String testColumn1) {
        this.testColumn1 = testColumn1;
    }
}
