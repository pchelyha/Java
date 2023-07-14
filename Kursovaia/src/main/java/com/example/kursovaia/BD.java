package com.example.kursovaia;

import java.sql.*;
import java.util.ArrayList;

public class BD {

    private final String LOGIN = "user19";
    private final String PASS = "ilya";

    private Connection dbConn = null;

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://localhost:3306/kursovaia";
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return dbConn;
    }
    public ArrayList<String> Login () throws SQLException, ClassNotFoundException {
        String sql = "SELECT login FROM kursovaia.worker ORDER BY id_worker";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> login = new ArrayList<>();
        while(res.next())
            login.add(res.getString("login"));

        return login;
    }
    public ArrayList<String> Password() throws SQLException, ClassNotFoundException {
        String sql = "SELECT password FROM kursovaia.worker ORDER BY id_worker";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("password"));

        return password;
    }
    public ArrayList<String> FIO() throws SQLException, ClassNotFoundException {
        String sql = "SELECT FIO FROM kursovaia.worker order by id_worker";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("FIO"));

        return password;
    }
    public ArrayList<String> Post() throws SQLException, ClassNotFoundException {
        String sql = "SELECT post FROM kursovaia.worker order by id_worker";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("post"));

        return password;
    }
    public ArrayList<String> FIO_Clients() throws SQLException, ClassNotFoundException {
        String sql = "SELECT fio FROM kursovaia.clients order by id_client";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("fio"));

        return password;
    }
    public ArrayList<String> Name_Service() throws SQLException, ClassNotFoundException {
        String sql = "SELECT service FROM kursovaia.service order by id_service";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("service"));

        return password;
    }
    public ArrayList<String> Last_enter() throws SQLException, ClassNotFoundException {
        String sql = "SELECT last_enter FROM kursovaia.worker order by id_worker";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("last_enter"));

        return password;
    }
    public ArrayList<String> Disease() throws SQLException, ClassNotFoundException {
        String sql = "SELECT disease FROM kursovaia.clients order by id_client ";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("disease"));

        return password;
    }
    public ArrayList<String> Income_s() throws SQLException, ClassNotFoundException {
        String sql = "SELECT sum FROM kursovaia.service order by id_service";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("sum"));

        return password;
    }
    public ArrayList<String> Price() throws SQLException, ClassNotFoundException {
        String sql = "SELECT cost FROM kursovaia.service order by id_service";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("cost"));

        return password;
    }
    public void CreateOrder(String date, String time, Integer id_client, Integer id_service) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO kursovaia.orders (date_reception, date_time, id_client, id_service) VALUES (?, ?, ?, ?);";

        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, date);
        prSt.setString(2, time);
        prSt.setInt(3, id_client);
        prSt.setInt(4, id_service);

        prSt.executeUpdate();

    }
    public ArrayList<String> Specialization() throws SQLException, ClassNotFoundException {
        String sql = "SELECT service FROM kursovaia.service order by id_service";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("service"));

        return password;
    }
    public void Income(Integer in, Integer id_s ) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE kursovaia.service SET sum = ? WHERE (id_service = ?);";

        PreparedStatement prSt = getDbConnection().prepareStatement(sql);

        prSt.setInt(1, in);
        prSt.setInt(2, id_s);

        prSt.executeUpdate();


    }
}