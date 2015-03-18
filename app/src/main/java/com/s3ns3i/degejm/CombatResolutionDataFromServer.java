package com.s3ns3i.degejm;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

public class CombatResolutionDataFromServer extends AsyncTask<String, String, String> {

    private JSONParser jParser = new JSONParser();
    private String []dataTables ={"attacker_id","defender_id","meele_attack","magic_attack","online_state"};
    private static String url = "http://wfisfantasy.16mb.com/";

    private Context context;
    private ListView LVenemies;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }


    @Override
    protected String doInBackground(String... params) {
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }


}
