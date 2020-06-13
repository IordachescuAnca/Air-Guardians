package com.example.air_guardiansproject;

import android.content.Intent;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class FavouritesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        List<Integer> idCities = User.getCities();

        String cities = "(";
        for(Integer i = 0; i < idCities.size(); ++i){
            cities += idCities.get(i);
            if(i < idCities.size() - 1){
                cities += ',';
            }
        }
        cities += ")";
        System.out.println(cities);

        List<String> nameCities = new ArrayList<>();
        List<String> categoryCities = new ArrayList<>();
        List<Double> aqiCities = new ArrayList<>();
        List<Double> COCities = new ArrayList<>();
        List<Double> NO2Cities = new ArrayList<>();
        List<Double> O3Cities = new ArrayList<>();
        List<Double> PM10Cities = new ArrayList<>();
        List<Double> PM25Cities = new ArrayList<>();

        if(idCities.size() > 0) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://mds-project.mysql.database.azure.com:3306/mydb";
                String user = "mdsproject@mds-project";
                String pass = "qwer1234!@#$";
                Connection dbConn = getConnection(url, user, pass);

                Statement statement = (Statement) dbConn.createStatement();
                ResultSet res = statement.executeQuery("Select * from oras_date WHERE oras_id IN " + cities);
                while (res.next()) {
                    String nameCity = res.getString("oras");
                    nameCities.add(nameCity);
                    String categoryCity = res.getString("categorie");
                    categoryCities.add(categoryCity);
                    Double aqi = res.getDouble("aqi");
                    aqiCities.add(aqi);
                    Double CO = res.getDouble("CO");
                    COCities.add(CO);
                    Double NO2 = res.getDouble("NO2");
                    NO2Cities.add(NO2);
                    Double O3 = res.getDouble("O3");
                    O3Cities.add(O3);
                    Double PM10 = res.getDouble("PM10");
                    PM10Cities.add(PM10);
                    Double PM25 = res.getDouble("PM25");
                    PM25Cities.add(PM25);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        ArrayList<BoxFavourite> boxFavourites = new ArrayList<>();
        for(Integer i = 0; i < idCities.size(); ++i){
            boxFavourites.add(new BoxFavourite(nameCities.get(i), categoryCities.get(i), aqiCities.get(i).toString(), COCities.get(i).toString(),
                    NO2Cities.get(i).toString(), O3Cities.get(i).toString(), PM10Cities.get(i).toString(), PM25Cities.get(i).toString()));
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterFavourite(boxFavourites);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //Init and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.favourites);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.info:
                        startActivity(new Intent(getApplicationContext(), InformationActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_home1:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.exit:
                        startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favourites:
                        return true;
                }
                return false;
            }
        });
    }
}
