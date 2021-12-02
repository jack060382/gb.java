package server;

import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AuthService {

    private static Connection connection;
    private static Statement stmt;

    public AuthService() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:chat.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }/* finally {
            disconnect();
        }*/
    }

    public static void disconnect() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(Entry user) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE users " +
                    "SET " +
                    "`nick` = ?, " +
                    "`login` = ?, " +
                    "`password` = ? " +
                    "WHERE `id` = ?;");
            ps.setString(1, user.getNick());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getId());
            int updateResult = ps.executeUpdate();
            //System.out.println("updateResult="+updateResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<Entry> findUserByNick(String nick) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users " +
                    "WHERE `nick` = ?;");
            ps.setString(1, nick);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return Optional.of(new Entry(
                        rs.getInt("id"),
                        rs.getString("nick"),
                        rs.getString("login"),
                        rs.getString("password")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Entry> findUserByLoginAndPassword(String login, String password) {

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users " +
                    "WHERE `login` = ? " +
                    "and `password` = ?;");
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return Optional.of(new Entry(
                        rs.getInt("id"),
                        rs.getString("nick"),
                        rs.getString("login"),
                        rs.getString("password")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    static class Entry {
        int id;
        String nick;
        String login;
        String password;

        Entry(int id, String nick, String login, String password) {
            this.id = id;
            this.nick = nick;
            this.login = login;
            this.password = password;
        }

        int getId() {return id;}

        String getNick() {
            return nick;
        }

        String getLogin() {
            return login;
        }

        void setNick(String nick) {
            this.nick = nick;
        }

        String getPassword() {
            return password;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return Objects.equals(nick, entry.nick) && Objects.equals(login, entry.login) && Objects.equals(password, entry.password);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, nick, login, password);
        }
    }

}
