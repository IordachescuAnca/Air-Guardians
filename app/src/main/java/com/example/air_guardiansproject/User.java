package com.example.air_guardiansproject;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

public class User {
 public static int user_id = 0;
 public static ArrayList<String> favorites = new ArrayList<>();

 public static void getFavorites() {

  try {
   Class.forName("com.mysql.jdbc.Driver");
   String url = "jdbc:mysql://mds-project.mysql.database.azure.com:3306/mydb";
   String user = "mdsproject@mds-project";
   String pass = "qwer1234!@#$";
   Connection dbConn = getConnection(url, user, pass);

   try {
    favorites.clear();
    Statement stmt = (Statement) dbConn.createStatement();
    ResultSet res = stmt.executeQuery("Select * from favorites,oras_date where id_client="+user_id+"  and favorites.oras_id = oras_date.oras_id");
    while (res.next()) {
        favorites.add(res.getString("Oras"));
    }
   } catch (SQLException e) {
    e.printStackTrace();
   }

   dbConn.close();

  } catch (ClassNotFoundException | SQLException e) {
   e.printStackTrace();
  }

 }

 public static void addToFavorites(String oras){
  try {
   Class.forName("com.mysql.jdbc.Driver");
   String url = "jdbc:mysql://mds-project.mysql.database.azure.com:3306/mydb";
   String user = "mdsproject@mds-project";
   String pass = "qwer1234!@#$";
   Connection dbConn = getConnection(url, user, pass);

   try {
    favorites.add(oras);
    Statement stmt = (Statement) dbConn.createStatement();
    ResultSet res = stmt.executeQuery("Select * from oras_date where Oras='"+oras+"'");
    res.next();
    int oras_id = res.getInt("oras_id");
    stmt.execute("insert into favorites values("+user_id+","+oras_id+")");

   } catch (SQLException e) {
    e.printStackTrace();
   }

   dbConn.close();

  } catch (ClassNotFoundException | SQLException e) {
   e.printStackTrace();
  }

 }

 public static void removeFromFavorites(String oras){
  try {
   Class.forName("com.mysql.jdbc.Driver");
   String url = "jdbc:mysql://mds-project.mysql.database.azure.com:3306/mydb";
   String user = "mdsproject@mds-project";
   String pass = "qwer1234!@#$";
   Connection dbConn = getConnection(url, user, pass);

   try {
    favorites.remove(oras);
    Statement stmt = (Statement) dbConn.createStatement();
    ResultSet res = stmt.executeQuery("Select * from oras_date where Oras='"+oras+"'");
    res.next();
    int oras_id = res.getInt("oras_id");

    stmt.execute("delete from favorites where id_client="+user_id+" and oras_id="+oras_id);

   } catch (SQLException e) {
    e.printStackTrace();
   }

   dbConn.close();

  } catch (ClassNotFoundException | SQLException e) {
   e.printStackTrace();
  }

 }

}




