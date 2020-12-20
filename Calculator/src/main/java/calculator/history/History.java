package calculator.history;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.*;

/**
 * Luokka, joka säilöö tehdyt laskutoimitukset ja tulokset tiedostoon history.db
 */
public class History {
    Connection db;

    public History() throws SQLException {
        String path = "jdbc:sqlite:history.db";
        db = DriverManager.getConnection(path);
        Statement s = db.createStatement();
        s.execute("CREATE TABLE Calculations (expression TEXT, result TEXT)");
    }

    public void addEntry(String expression, String result) throws SQLException {
        PreparedStatement p = db.prepareStatement("INSERT INTO Calculations(expression, result) VALUES (?,?)");
        p.setString(1, expression);
        p.setString(2, result);
        p.executeUpdate();
    }

    public String getExpression(String item) throws SQLException {
        Statement s = db.createStatement();
        int index = item.indexOf("=");
        String expression = item.substring(0,index+1);
        String result = item.substring(index+2);
        ResultSet r = s.executeQuery("SELECT expression FROM Calculations WHERE expression = '" + expression + "' AND result = '" + result + "'");
        return r.getString(1);
    }

    public String getResult(String item) throws SQLException {
        Statement s = db.createStatement();
        int index = item.indexOf("=");
        String expression = item.substring(0,index+1);
        String result = item.substring(index+2);
        ResultSet r = s.executeQuery("SELECT result FROM Calculations WHERE expression = '" + expression + "' AND result = '" + result + "'");
        return r.getString(1);
    }

    public ObservableList<String> getAll() throws SQLException {
        Statement s = db.createStatement();
        ResultSet r = s.executeQuery("SELECT * FROM Calculations");
        ObservableList<String> list = FXCollections.observableArrayList();
        while (r.next()) {
            list.add(r.getString("expression") + " " + r.getString("result"));
        }
        return list;
    }

    public void endSession() throws SQLException {
        db.close();
        new File("history.db").delete();
    }
}
