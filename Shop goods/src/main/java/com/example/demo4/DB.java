package com.example.demo4;
import java.sql.*;
import java.util.ArrayList;

public class DB {
    private final String LOGIN = "user24";
    private final String PASS = "ilya";

    private Connection dbConn = null;

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://localhost:3306/bd";
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return dbConn;
    }
    public ArrayList<String> Name() throws SQLException, ClassNotFoundException {
        String sql = "SELECT NameFood FROM bd.Food order by idFood";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> login = new ArrayList<>();
        while (res.next())
            login.add(res.getString("NameFood"));

        return login;
    }
    public ArrayList<String> Kkal() throws SQLException, ClassNotFoundException {
        String sql = "SELECT KalloriiFood FROM bd.Food order by idFood";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> login = new ArrayList<>();
        while (res.next())
            login.add(res.getString("KalloriiFood"));

        return login;
    }
    public ArrayList<String> Desc() throws SQLException, ClassNotFoundException {
        String sql = "SELECT DescriptionFood FROM bd.Food order by idFood";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> login = new ArrayList<>();
        while (res.next())
            login.add(res.getString("DescriptionFood"));

        return login;
    }
    public void Create_food(String name, Integer kal, String cat, String desc) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO `bd`.`Food` (`NameFood`, `KalloriiFood`, `CategoriesFood`, `DescriptionFood`) VALUES (?, ?, ?, ?);";

        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, name);
        prSt.setInt(2, kal);
        prSt.setString(3, cat);
        prSt.setString(4, desc);

        prSt.executeUpdate();
    }
    public ArrayList<String> ID() throws SQLException, ClassNotFoundException {
        String sql = "SELECT UserID FROM bd.User order by UserID";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> login = new ArrayList<>();
        while (res.next())
            login.add(res.getString("UserID"));

        return login;
    }
    public ArrayList<String> Password() throws SQLException, ClassNotFoundException {
        String sql = "SELECT UserPassword FROM bd.User order by UserID";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> login = new ArrayList<>();
        while (res.next())
            login.add(res.getString("UserPassword"));

        return login;
    }
    public ArrayList<String> Role_id() throws SQLException, ClassNotFoundException {
        String sql = "SELECT UserRole FROM bd.User order by UserID";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> login = new ArrayList<>();
        while (res.next())
            login.add(res.getString("UserRole"));

        return login;
    }
    public ArrayList<String> Role(Integer id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT RoleName FROM bd.Role where RoleID = ?";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, id);
        ResultSet res = prSt.executeQuery();

        ArrayList<String> login = new ArrayList<>();
        while (res.next())
            login.add(res.getString("RoleName"));

        return login;
    }
    public void Delete_food(Integer id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM `bd`.`Food` WHERE (`idFood` = ?);";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, id);
        prSt.executeUpdate();

    }
    public ArrayList<String> Id_food() throws SQLException, ClassNotFoundException {
        String sql = "SELECT idFood FROM bd.Food order by idFood";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> login = new ArrayList<>();
        while (res.next())
            login.add(res.getString("idFood"));

        return login;
    }
}
