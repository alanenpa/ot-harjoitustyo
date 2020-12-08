package calculator.history;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.*;

/**
 * Luokka, joka säilöö tehdyt laskutoimitukset kokonaisuudessaan tiedostoon history.db
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

    public ObservableList<String> getAll() throws SQLException {
        Statement s = db.createStatement();
        ResultSet r = s.executeQuery("SELECT * FROM Calculations");
        ObservableList<String> list = FXCollections.observableArrayList();
        while (r.next()) {
            list.add(r.getString("expression") + " = " + r.getString("result"));
        }
        return list;
    }

    public void endSession() throws SQLException {
        db.close();
        new File("history.db").delete();
    }
}
