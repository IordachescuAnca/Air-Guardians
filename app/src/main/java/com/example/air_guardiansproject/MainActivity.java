package com.example.air_guardiansproject;

//IMPORTURI API
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.*;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.mysql.jdbc.PreparedStatement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//IMPORTURI API

import android.app.Dialog;
import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.sql.DriverManager.getConnection;

public class MainActivity extends AppCompatActivity {


    private RequestQueue requestQueue;

    TextView textRegister;
    TextView textView;
    TextView textView2;
    Button button;
    Button buttonHome;
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // ca sa nu primim exceptie la requesturi pe retea din threadul principal
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        requestQueue = Volley.newRequestQueue(this);

        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                List<Double> listLatitudes = getAllLatitude();
                List<Double> listLongitude = getAllLongitude();
                List<String> listCities = getAllCities();
                Integer count = listCities.size();
                for(Integer i = 0; i < count; i++){
                    Double latitude = listLatitudes.get(i);
                    Double longitude = listLongitude.get(i);
                    String city = listCities.get(i);
                    //System.out.println(latitude);
                    //System.out.println(longitude);
                    setInfo(latitude, longitude, i + 1);
                }
            };
        };
        t.scheduleAtFixedRate(tt,new Date(),1000 * 60 * 60);

        textRegister = (TextView) findViewById(R.id.register);
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        button = (Button) findViewById(R.id.button_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });

        button = (Button) findViewById(R.id.button_map);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServicesOK()) {
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                }
            }
        });

    }

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public List<String> getAllCities(){
        List<String> cities = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://mds-project.mysql.database.azure.com:3306/mydb";
            String user = "mdsproject@mds-project";
            String pass = "qwer1234!@#$";
            Connection dbConn = getConnection(url, user, pass);

            Statement statement = (Statement)dbConn.createStatement();
            ResultSet res = statement.executeQuery("Select * from oras_date");
            while (res.next()){
                String city = res.getString("Oras");
                cities.add(city);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cities;
    }

    public List<Double> getAllLongitude(){
        List<Double> longitudes = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://mds-project.mysql.database.azure.com:3306/mydb";
            String user = "mdsproject@mds-project";
            String pass = "qwer1234!@#$";
            Connection dbConn = getConnection(url, user, pass);

            Statement statement = (Statement)dbConn.createStatement();
            ResultSet res = statement.executeQuery("Select * from oras_date");
            while (res.next()){
                Double longitude = res.getDouble("Longitudine");
                longitudes.add(longitude);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return longitudes;
    }

    public List<Double> getAllLatitude(){
        List<Double> latitudes = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://mds-project.mysql.database.azure.com:3306/mydb";
            String user = "mdsproject@mds-project";
            String pass = "qwer1234!@#$";
            Connection dbConn = getConnection(url, user, pass);

            Statement statement = (Statement)dbConn.createStatement();
            ResultSet res = statement.executeQuery("Select * from oras_date");
            while (res.next()){
                Double latitude = res.getDouble("Latitudine");
                latitudes.add(latitude);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return latitudes;
    }

    public void setInfo(Double latitudee, Double longitudee, Integer index) {
        //System.out.println("DA");

        String token  = new String("25823674605b4305b50052b7da10cc04");
        requestQueue = Volley.newRequestQueue(this);
        StringBuilder sb = new StringBuilder("https://api.breezometer.com/air-quality/v2/current-conditions?lat=");
        sb.append(latitudee);
        sb.append("&lon=");
        sb.append(longitudee);
        sb.append("&key=");
        sb.append(token);
        sb.append("&features=breezometer_aqi,local_aqi,health_recommendations,sources_and_effects,pollutants_concentrations,pollutants_aqi_information");
        String url = sb.toString();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {

                JSONObject dataObject = response.getJSONObject("data");
                JSONObject indexesObject = dataObject.getJSONObject("indexes");
                JSONObject rou_calitateaerObject = indexesObject.getJSONObject("baqi");
                Double aqiValue = rou_calitateaerObject.getDouble("aqi");
                //System.out.println("aqi e: " + aqiValue.toString());
                String categoryValue = rou_calitateaerObject.getString("category");

                JSONObject pollutantsObject = dataObject.getJSONObject("pollutants");
                JSONObject coObject = pollutantsObject.getJSONObject("co");
                JSONObject coConcentration = coObject.getJSONObject("concentration");
                Double coValue = coConcentration.getDouble("value");

                JSONObject no2Object = pollutantsObject.getJSONObject("no2");
                JSONObject no2Concentration = no2Object.getJSONObject("concentration");
                Double no2Value = no2Concentration.getDouble("value");

                JSONObject o3Object = pollutantsObject.getJSONObject("o3");
                JSONObject o3Concentration = o3Object.getJSONObject("concentration");
                Double o3Value = o3Concentration.getDouble("value");

                JSONObject pm10Object = pollutantsObject.getJSONObject("pm10");
                JSONObject pm10Concentration = pm10Object.getJSONObject("concentration");
                Double pm10Value = pm10Concentration.getDouble("value");

                JSONObject pm25Object = pollutantsObject.getJSONObject("pm25");
                JSONObject pm25Concentration = pm25Object.getJSONObject("concentration");
                Double pm25Value = pm25Concentration.getDouble("value");

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url2 = "jdbc:mysql://mds-project.mysql.database.azure.com:3306/mydb";
                    String user = "mdsproject@mds-project";
                    String pass = "qwer1234!@#$";
                    Connection dbConn = getConnection(url2, user, pass);

                    String query = "update oras_date set Aqi = ?, Categorie = ?, CO = ?, NO2 = ?, O3 = ?, PM10 = ?, PM25 = ? where oras_id = ?";
                    PreparedStatement preparedStmt = (PreparedStatement) dbConn.prepareStatement(query);
                    preparedStmt.setDouble(1, aqiValue);
                    preparedStmt.setString(2, categoryValue);
                    preparedStmt.setDouble(3, coValue);
                    preparedStmt.setDouble(4, no2Value);
                    preparedStmt.setDouble(5, o3Value);
                    preparedStmt.setDouble(6, pm10Value);
                    preparedStmt.setDouble(7, pm25Value);
                    preparedStmt.setInt(8,index);
                    preparedStmt.executeUpdate();

                    dbConn.close();
                }catch (ClassNotFoundException | SQLException e){
                    e.printStackTrace();
                }

            } catch (JSONException j ) {
                j.printStackTrace();
            }
        }, error -> {
        });
        requestQueue.add(request);
    }
}
