package com.example.policklinika;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class db {
    Map<String ,String > env = System.getenv();
    private final String HOST = env.getOrDefault("DB_HOST","127.0.0.1") ;

    private final String PORT = env.getOrDefault("DB_PORT","3306") ;
    private final String DB_NAME = env.getOrDefault("DB_NAME","policklinika") ;
    private final String LOGIN = env.getOrDefault("DB_USER","root") ; // Если OpenServer, то здесь mysql напишите
    private final String PASS = env.getOrDefault("DB_PASS",""); // Если OpenServer, то здесь mysql напишите

    private Connection dbConn = null;

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return dbConn;
    }

    public ArrayList<String> Login () throws SQLException, ClassNotFoundException {
        String sql = "SELECT login FROM policklinika.worker ORDER BY id_worker";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> login = new ArrayList<>();
        while(res.next())
            login.add(res.getString("login"));

        return login;
    }

    public void CreateClients() throws SQLException, ClassNotFoundException {
        String sql1 = "CREATE TABLE `clients` (\n" +
                "  `id_client` int(11) NOT NULL,\n" +
                "  `fio` varchar(45) DEFAULT NULL,\n" +
                "  `address` varchar(45) DEFAULT NULL,\n" +
                "  `birthday` date DEFAULT NULL,\n" +
                "  `client_login` varchar(45) DEFAULT NULL,\n" +
                "  `client_password` varchar(45) DEFAULT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public void CreateEmployee() throws SQLException, ClassNotFoundException {
        String sql1 = "CREATE TABLE `orders` (\n" +
                "  `id_order` int(11) NOT NULL,\n" +
                "  `date_reception` date DEFAULT NULL,\n" +
                "  `date_time` time DEFAULT NULL,\n" +
                "  `id_client` int(11) DEFAULT NULL,\n" +
                "  `id_service` int(11) DEFAULT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public void CreateLoans() throws SQLException, ClassNotFoundException {
        String sql1 = "CREATE TABLE `service` (\n" +
                "  `id_service` int(11) NOT NULL,\n" +
                "  `specialization` varchar(45) DEFAULT NULL,\n" +
                "  `price` int(11) DEFAULT NULL,\n" +
                "  `income` int(11) NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public void CreateServices() throws SQLException, ClassNotFoundException {
        String sql1 = "CREATE TABLE `worker` (\n" +
                "  `id_worker` int(11) NOT NULL,\n" +
                "  `FIO` varchar(45) DEFAULT NULL,\n" +
                "  `post` varchar(45) DEFAULT NULL,\n" +
                "  `address` varchar(45) DEFAULT NULL,\n" +
                "  `birthday` date DEFAULT NULL,\n" +
                "  `login` varchar(45) DEFAULT NULL,\n" +
                "  `password` varchar(45) DEFAULT NULL,\n" +
                "  `last_enter` datetime DEFAULT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public void InsertClients() throws SQLException, ClassNotFoundException {
        String sql1 = "INSERT INTO `clients` (`id_client`, `fio`, `address`, `birthday`, `client_login`, `client_password`) VALUES\n" +
                "(1, 'Прусаков М.А', 'Пензенская область, город Ступино, бульвар Ко', '2001-11-08', 'mihailPR', 'mihail222'),\n" +
                "(2, 'Конев А.А', 'Пензенская область, город Ступино, бульвар Ко', '2000-11-01', 'Artem12', 'koni2000'),\n" +
                "(3, 'Ануфриев Д.О', 'Пензенская область, город Ступино, бульвар Ко', '1998-11-11', 'skezy20', 'diman822'),\n" +
                "(4, 'Сироткин Д.А', 'Пензенская область, город Ступино, бульвар Ко', '1999-06-21', 'sirons0', 'danilka1003'),\n" +
                "(5, 'Волков М.А', 'Пензенская область, город Ступино, бульвар Ко', '2001-09-14', 'volk999', 'dota993');\n";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public void InsertEmployee() throws SQLException, ClassNotFoundException {
        String sql1 = "INSERT INTO `orders` (`id_order`, `date_reception`, `date_time`, `id_client`, `id_service`) VALUES\n" +
                "(1, '2022-11-16', '14:20:09', 3, 2),\n" +
                "(2, '2022-11-18', '11:15:08', 2, 1),\n" +
                "(3, '2022-11-18', '16:20:00', 1, 4),\n" +
                "(4, '2022-11-20', '11:15:08', 5, 3),\n" +
                "(5, '2022-11-22', '11:15:00', 4, 0),\n" +
                "(7, '2022-11-21', '00:00:11', 3, 3),\n" +
                "(10, '2022-11-21', '00:00:11', 5, 4),\n" +
                "(11, '2022-11-21', '00:00:11', 5, 4),\n" +
                "(12, '2022-11-22', '00:00:23', 2, 2),\n" +
                "(13, '2022-11-22', '00:00:23', 2, 2);";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public void InsertLoans() throws SQLException, ClassNotFoundException {
        String sql1 = "INSERT INTO `service` (`id_service`, `specialization`, `price`, `income`) VALUES\n" +
                "(0, 'Окулист', 1000, 8000),\n" +
                "(1, 'Терапевт', 500, 1000),\n" +
                "(2, 'Невролог', 1000, 6000),\n" +
                "(3, 'Педиатр', 1500, 3000),\n" +
                "(4, 'Психолог', 2500, 22500);";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public void InsertServices() throws SQLException, ClassNotFoundException {
        String sql1 = "INSERT INTO `worker` (`id_worker`, `FIO`, `post`, `address`, `birthday`, `login`, `password`, `last_enter`) VALUES\n" +
                "(1, 'Князева А.А.', 'Администратор', 'нижний новгород улица большая покровская', '2000-11-01', 'kniaz', 'kniaz1', '2022-11-19 14:17:01'),\n" +
                "(2, 'Воронова П.А.', 'Оператор', 'нижний новгород улица большая покровская', '2000-11-02', 'vorona', 'vorona2', '2022-11-17 14:17:01'),\n" +
                "(3, 'Иванов А.М', 'Врач', 'нижний новгород большая покровская', '1999-11-13', 'ivanovvv1', 'zxcqwe123', '2022-11-19 20:14:04');\n";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public ArrayList<String> Password() throws SQLException, ClassNotFoundException {
        String sql = "SELECT password FROM policklinika.worker ORDER BY id_worker";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("password"));

        return password;
    }
    public ArrayList<String> FIO() throws SQLException, ClassNotFoundException {
        String sql = "SELECT FIO FROM policklinika.worker order by id_worker";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("FIO"));

        return password;
    }
    public ArrayList<String> Post() throws SQLException, ClassNotFoundException {
        String sql = "SELECT post FROM policklinika.worker order by id_worker";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("post"));

        return password;
    }
    public ArrayList<String> FIO_Clients() throws SQLException, ClassNotFoundException {
        String sql = "SELECT fio FROM policklinika.clients order by id_client";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("fio"));

        return password;
    }
    public ArrayList<String> Name_Service() throws SQLException, ClassNotFoundException {
        String sql = "SELECT specialization FROM policklinika.service order by id_service";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("specialization"));

        return password;
    }
    public void CreateOrder(String date, String time, Integer id_client, Integer id_service) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO policklinika.orders (date_reception, date_time, id_client, id_service) VALUES (?, ?, ?, ?);";

        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, date);
        prSt.setString(2, time);
        prSt.setInt(3, id_client);
        prSt.setInt(4, id_service);

        prSt.executeUpdate();

    }
    public ArrayList<String> Check() throws SQLException, ClassNotFoundException {
        String sql = "show tables;";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> login = new ArrayList<>();
        while(res.next())
            login.add(String.valueOf(res));

        return login;
    }
    public ArrayList<String> Income_s() throws SQLException, ClassNotFoundException {
        String sql = "SELECT income FROM policklinika.service order by id_service";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("income"));

        return password;
    }
    public ArrayList<String> Price() throws SQLException, ClassNotFoundException {
        String sql = "SELECT price FROM policklinika.service order by id_service";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("price"));

        return password;
    }
    public ArrayList<String> Specialization() throws SQLException, ClassNotFoundException {
        String sql = "SELECT specialization FROM policklinika.service order by id_service";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("specialization"));

        return password;
    }
    public void Income(Integer in, Integer id_s ) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE policklinika.service SET income = ? WHERE (id_service = ?);";

        PreparedStatement prSt = getDbConnection().prepareStatement(sql);

        prSt.setInt(1, in);
        prSt.setInt(2, id_s);

        prSt.executeUpdate();


    }
}