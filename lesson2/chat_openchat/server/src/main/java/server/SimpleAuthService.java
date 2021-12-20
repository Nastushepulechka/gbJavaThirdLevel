package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService{
    private static Connection connection;
    private static PreparedStatement psInsert;
    private static PreparedStatement psSelect;
    private static PreparedStatement psDelete;
    private static PreparedStatement psUpdate;


//    private class UserData {
//        String login;
//        String password;
//        String nickname;
//
//        public UserData(String login, String password, String nickname) {
//            this.login = login;
//            this.password = password;
//            this.nickname = nickname;
//        }
//    }

//    private List<UserData> users;

//    public SimpleAuthService() {
//        users = new ArrayList<>();
//
//        users.add(new UserData("qwe", "qwe", "qwe"));
//        users.add(new UserData("asd", "asd", "asd"));
//        users.add(new UserData("zxc", "zxc", "zxc"));
//
//        for (int i = 0; i < 10; i++) {
//            users.add(new UserData("login" + i, "pass" + i, "nick" + i));
//        }
//    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        String nick = null;
        try {
            psSelect.setString(1, login);
            psSelect.setString(2, password);
            ResultSet rs = psSelect.executeQuery();

            if (rs.next()){
                nick = rs.getString(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nick;
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        try {
            psInsert.setString(1, login);
            psInsert.setString(2, password);
            psInsert.setString(3, nickname);
            psInsert.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
//        for (UserData u : users) {
//            if(u.login.equals(login) || u.nickname.equals(nickname)){
//                return false;
//            }
//        }
//        users.add(new UserData(login, password, nickname));
    }

    @Override
    public boolean changeNickname(String oldNickname, String newNickname) {
        try {
            psUpdate.setString(1, newNickname);
            psUpdate.setString(2, oldNickname);
            psInsert.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //CRUD
    private static void prepareAllStatements() throws SQLException {
        psInsert = connection.prepareStatement("INSERT INTO auth (login, password, nickname) VALUES ( ? , ? , ? ); ");
        psSelect = connection.prepareStatement("SELECT nickname FROM auth WHERE login = ? AND password = ?;");
        psDelete = connection.prepareStatement("DELETE FROM auth WHERE login = ?;");
        psUpdate = connection.prepareStatement("UPDATE auth SET nickname = ? WHERE nickname = ?;");

    }



    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        prepareAllStatements();
//        stmt = connection.createStatement();
    }

    public static void disconnect(){
        try {
            psSelect.close();
            psUpdate.close();
            psInsert.close();
            psDelete.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
