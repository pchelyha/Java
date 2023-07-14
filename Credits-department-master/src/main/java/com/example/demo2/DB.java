package com.example.demo2;


import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class DB {

    // Данные для подключения к локальной базе данных
    Map<String ,String > env = System.getenv();
    private final String HOST = env.getOrDefault("DB_HOST","127.0.0.1") ;
    private final String PORT = env.getOrDefault("DB_PORT","3306") ;
    private final String DB_NAME = env.getOrDefault("DB_NAME","kurs") ;
    private final String LOGIN = env.getOrDefault("DB_USER","root") ; // Если OpenServer, то здесь mysql напишите
    private final String PASS = env.getOrDefault("DB_PASS",""); // Если OpenServer, то здесь mysql напишите

    private Connection dbConn = null;

    // Метод для подключения к БД с использованием значений выше
    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return dbConn;
    }
    public void CreateClients() throws SQLException, ClassNotFoundException {
        String sql1 = "CREATE TABLE IF NOT EXISTS `clients` (\n" +
                "  `idClients` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `FIO` varchar(100) DEFAULT NULL,\n" +
                "  `Passport_data` varchar(45) DEFAULT NULL,\n" +
                "  `Phone` varchar(45) DEFAULT NULL,\n" +
                "  `Address` varchar(45) DEFAULT NULL,\n" +
                "  `E-mail` varchar(45) DEFAULT NULL,\n" +
                "  `Password` varchar(45) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`idClients`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public void CreateEmployee() throws SQLException, ClassNotFoundException {
        String sql1 = "CREATE TABLE IF NOT EXISTS `employee` (\n" +
                "  `idEmployee` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `Post` varchar(45) DEFAULT NULL,\n" +
                "  `FIO` varchar(45) DEFAULT NULL,\n" +
                "  `Login` varchar(45) DEFAULT NULL,\n" +
                "  `Password` varchar(45) DEFAULT NULL,\n" +
                "  `Last_enter` datetime DEFAULT NULL,\n" +
                "  PRIMARY KEY (`idEmployee`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public void CreateLoans() throws SQLException, ClassNotFoundException {
        String sql1 = "CREATE TABLE IF NOT EXISTS `loans` (\n" +
                "  `idLoans` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `idClients` int(11) NOT NULL,\n" +
                "  `idEmployee` int(11) NOT NULL,\n" +
                "  `idServices` int(11) NOT NULL,\n" +
                "  `Date_begin` datetime DEFAULT NULL,\n" +
                "  `Date_end` datetime DEFAULT NULL,\n" +
                "  `Returned` int(11) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`idLoans`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public void CreateServices() throws SQLException, ClassNotFoundException {
        String sql1 = "CREATE TABLE IF NOT EXISTS `services` (\n" +
                "  `idServices` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `Name` varchar(100) DEFAULT NULL,\n" +
                "  `Money` decimal(10,2) DEFAULT NULL,\n" +
                "  `Percent_per_year` int(11) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`idServices`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public void InsertClients() throws SQLException, ClassNotFoundException {
        String sql1 = "INSERT INTO `clients` VALUES (\"1\",\"Ануфриев Дмитрий Олегович\",\"2134 214525\",\"85341235364\",\"Студенческая, 35а\",\"skezy124@mail.ru\",\"402526\"),\n" +
                "(\"2\",\"Сироткин Данил Александрович\",\"4124 214242\",\"8532532964\",\"Ближнее Борисово, 31\",\"sirons@mail.ru\",\"412512\"),(\"3\",\"Цветков Илья Викторович\",\n" +
                "\"4125 241653\",\"8325893538\",\"Киселиха, 124\",\"fancy@mail.ru\",\"401290\"),(\"4\",\"Прусаков Алексей Алексеевич\",\"1242 421442\",\"89345383521\",\"Киселиха, 142а\",\n" +
                "\"prus@mail.ru\",\"asdzxc\"),(\"5\",\"Лавров Максим Петрович\",\"2132 134224\",\"84328934354\",\"Нартова, 5в\",\"max123@mail.ru\",\"123fgf\");";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public void InsertEmployee() throws SQLException, ClassNotFoundException {
        String sql1 = "INSERT INTO `employee` VALUES (\"1\",\"Администратор\",\"Прусаков Михаил Алексеевич\",\"mikhas\",\"142643\",\"2022-11-23 07:46:37\"),(\"2\",\n" +
                "\"Старший кредитного отдела\",\"Быков Илья Александрович\",\"zxcqwe\",\"426236\",\"2022-11-23 07:36:19\"),(\"3\",\"Кредитный специалист\",\"Надёжин Иван Александрович\",\n" +
                "\"kzzbn\",\"512637\",\"2022-11-23 07:24:02\"),(\"4\",\"Администратор\",\"Колесник Максим Петрович\",\"ooooo\",\"123asd\",\"2022-11-22 23:29:20\"),(\"5\",\"Старший кредитного отдела\",\n" +
                "\"Ануфриева Ирина Константиновна\",\"ira312\",\"32149ia\",\"2022-11-23 07:56:07\");";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public void InsertLoans() throws SQLException, ClassNotFoundException {
        String sql1 = "INSERT INTO `loans` VALUES (\"1\",\"1\",\"1\",\"2\",\"2021-11-20 18:27:17\",\"2022-11-20 18:27:17\",\"1\"),(\"2\",\"2\",\"2\",\"4\",\"2022-11-20 18:27:17\",\n" +
                "\"2023-11-20 18:27:17\",\"0\"),(\"3\",\"3\",\"2\",\"3\",\"2020-11-20 18:27:17\",\"2021-11-20 18:27:17\",\"1\"),(\"4\",\"2\",\"1\",\"1\",\"2022-11-20 18:27:17\",\n" +
                "\"2024-11-20 18:27:17\",\"0\"),(\"5\",\"3\",\"2\",\"4\",\"2022-11-21 12:12:12\",\"2023-11-21 12:12:12\",\"0\"),(\"6\",\"4\",\"2\",\"1\",\"2022-11-21 12:12:12\",\n" +
                "\"2023-11-21 12:12:12\",\"0\"),(\"7\",\"1\",\"2\",\"2\",\"2022-11-20 10:10:10\",\"2025-11-20 10:10:10\",\"0\");";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public void InsertServices() throws SQLException, ClassNotFoundException {
        String sql1 = "INSERT INTO `services` VALUES (\"1\",\"Персональный\",\"124634.0\",\"12\"),(\"2\",\"Для бизнеса\",\"999999.0\",\"10\"),(\"3\",\"Ипотека на жилье\",\"924144.0\",\"11\"),\n" +
                "(\"4\",\"Льготный\",\"100000.0\",\"0\");";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public ArrayList<String> Login() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Login FROM employee order by idEmployee";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> login = new ArrayList<>();
        while(res.next())
            login.add(res.getString("Login"));

        return login;
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
    public ArrayList<String> Password() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Password FROM employee order by idEmployee";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("Password"));

        return password;
    }
    public ArrayList<String> FIO() throws SQLException, ClassNotFoundException {
        String sql = "SELECT FIO FROM employee order by idEmployee";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("FIO"));

        return password;
    }
    public String client(String cl) throws SQLException, ClassNotFoundException {
        String sql = "SELECT FIO,idClients FROM clients where idClients='"+cl+"'";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        String password = null;
        while(res.next()) {
            password = res.getString("idClients")+" - "+res.getString("FIO");
        }

        return password;
    }
    public String empl(String cl) throws SQLException, ClassNotFoundException {
        String sql = "SELECT FIO,idEmployee FROM employee where idEmployee='"+cl+"'";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        String password = null;
        while(res.next()) {
            password = res.getString("idEmployee")+" - "+res.getString("FIO");
        }

        return password;
    }
    public String  serv(String cl) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Name,idServices FROM services where idServices='"+cl+"'";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        String password = null;
        while(res.next()) {
            password = res.getString("idServices")+" - "+res.getString("Name");
        }

        return password;
    }

    public ArrayList<String> FIO_client() throws SQLException, ClassNotFoundException {
        String sql = "SELECT idClients,FIO FROM clients order by idClients";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        ArrayList<String> password = new ArrayList<>();
        while(res.next()) {
            password.add(res.getString(1));
            password.add(res.getString(2));

        }
        return password;
    }

    public ArrayList NameLoans() throws SQLException, ClassNotFoundException {
        String sql = "SELECT distinct(idServices),Name FROM services order by idServices";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next()) {
            password.add(res.getString(1));
            password.add(res.getString(2));
        }
        return password;
    }

    public ArrayList Post() throws SQLException, ClassNotFoundException {
        String sql = "SELECT distinct(Post) FROM employee order by idEmployee";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> password = new ArrayList<>();
        while(res.next())
            password.add(res.getString("Post"));

        return password;
    }
    public void Update(String loginDB) throws SQLException, ClassNotFoundException {
        String sql1 = "update employee set Last_enter =current_timestamp() where Login = '"+loginDB+"'";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);
    }
    public void Insert_Loan(String number,String client,String employee,String typeloan,String begin,String end,String returned)
            throws SQLException, ClassNotFoundException {
        String sql1 = "insert into loans values("+number+","+client+","+employee+","+typeloan+",'"+begin+"','"+end+"',"+returned+")";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);


    }
    public void Insert_Client(String id,String fio,String p_data,String tel,String addr,String mail,String pass)
            throws SQLException, ClassNotFoundException {
        String sql1 = "insert into clients values("+id+",'"+fio+"','"+p_data+"','"+tel+"','"+addr+"','"+mail+"','"+pass+"')";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);


    }
    public void Insert_Employee(String id,String post,String fio,String login,String pass)
            throws SQLException, ClassNotFoundException {
        String sql1 = "insert into employee values("+id+",'"+post+"','"+fio+"','"+login+"','"+pass+"',current_timestamp())";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql1);


    }
    public ArrayList getOtchet() throws SQLException, ClassNotFoundException {
        String a="0";
        String sql1 = "SELECT l.idLoans,concat(c.idClients,\" - \",c.FIO) as Clients,concat(e.idEmployee,\" - \",e.FIO)as Clients,concat(s.idServices,\" - \",s.Name) \n" +
                " as Employee,l.Date_begin,l.Date_end FROM loans l inner join clients c inner join employee e inner join services s where Returned='"+a+"' and c.idClients=l.idClients and\n" +
                " l.idEmployee=e.idEmployee and l.idServices=s.idServices";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql1);
        ArrayList<String> tasks = new ArrayList<>();
        while(res.next()) {
            tasks.add(res.getString(1));
            tasks.add(res.getString(2));
            tasks.add(res.getString(3));
            tasks.add(res.getString(4));
            tasks.add(res.getString(5));
            tasks.add(res.getString(6));


        }
        return tasks;
    }
    public Integer getNumberMaxZacaz() throws SQLException, ClassNotFoundException {
        String sql1 = "SELECT max(idLoans) FROM loans";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql1);
        Integer tasks = 0;
        while(res.next()) {
            tasks = res.getInt(1)+1;

        }
        return tasks;
    }
    public Integer getNumberMaxClient() throws SQLException, ClassNotFoundException {
        String sql1 = "SELECT max(idClients) FROM clients";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql1);
        Integer tasks = 0;
        while(res.next()) {
            tasks = res.getInt(1)+1;

        }
        return tasks;
    }
    public Integer getNumberMaxEmployee() throws SQLException, ClassNotFoundException {
        String sql1 = "SELECT max(idEmployee) FROM employee";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql1);
        Integer tasks = 0;
        while(res.next()) {
            tasks = res.getInt(1)+1;

        }
        return tasks;
    }

    public ArrayList History() throws SQLException, ClassNotFoundException {
        String sql1 = "SELECT FIO, Last_enter FROM employee";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql1);
        ArrayList<String> tasks = new ArrayList<>();
        while(res.next()) {
            tasks.add(res.getString(1));
            tasks.add(res.getString(2));

        }
        return tasks;
    }

}
