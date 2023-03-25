package com.example.snortdroid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.snortdroid.rules.SnortRule;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class DBAdapter {
    private static  int counter=0;
    private SQLiteDatabase database;
    private DBHelper helper;
    public DBAdapter(Context ctx) {
        helper = new DBHelper(ctx, "snortdroid",null, 1);
    }
    public void openConnection() {
        database = helper.getWritableDatabase();
    }
    public void closeConnection() {
        database.close();
    }
    public void insertSnortRule(SnortRule rule){
        ContentValues cv = new ContentValues();
        cv.put(Names.ID,counter++);
        cv.put(Names.RULE,rule.toString());
        database.insert(Names.SNORT_TABLE,null,cv);
    }
    public List<String> selectRulesByAction(String action){
        Cursor cursor = database.query(
                Names.SNORT_TABLE,
                new String[]{Names.ID, Names.RULE},
                null,
                null,
                null,
                null,
                null);
        List<String> lista = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                lista.add( cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return lista;
    }
    public String selectRulesByProtocol(String protocol){
        Cursor cursor = database.query(
                Names.SNORT_TABLE,
                new String[]{Names.ID, Names.RULE},
                Names.RULE + " = ?",
                new String[]{protocol},
                null,
                null,
                null);
        if (cursor != null && cursor.moveToFirst()) {
            String ruleProto=cursor.getString(1).split(";")[2];
            if(protocol.equals(ruleProto)){
                return cursor.getString(1);
            }
        }
        return null;
    }
    public void updateRuleParams(String srcNet,int srcPort,String destNet,int destPort){

    }
}
