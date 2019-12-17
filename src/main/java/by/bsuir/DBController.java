package by.bsuir;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.SQLException;
        import org.apache.log4j.Logger;

public class DBController {
    private static final String INSERT_CAR = "INSERT INTO cars (model, make, price, fuelConsumption, power, year) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String DELETE_CAR = "DELETE FROM cars WHERE model = ? AND make = ? AND price = ? AND fuelConsumption = ? AND power = ? AND year = ?;";
    private static final Logger log = Logger.getLogger(DBController.class);

    public DBController() {
    }

    public boolean DBUpdate(String model, String make, double price, double fuelConsumption, int power, int year, boolean is_insert) {
        boolean result = false;
        String password = "";
        String username = "root";
        String url = "jdbc:mysql://localhost:3308/autobase?serverTimezone=Europe/Moscow&useSSL=false";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            try {
                PreparedStatement stmt = null;

                try {
                    if (is_insert) {
                        stmt = conn.prepareStatement("INSERT INTO cars (model, make, price, fuelConsumption, power, year) VALUES (?, ?, ?, ?, ?, ?);");
                    } else {
                        stmt = conn.prepareStatement("DELETE FROM cars WHERE model = ? AND make = ? AND price = ? AND fuelConsumption = ? AND power = ? AND year = ?;");
                    }
                } catch (SQLException var25) {
                    log.error(var25.getMessage());
                }

                try {
                    assert stmt != null;

                    stmt.setString(1, model);
                    stmt.setString(2, make);
                    stmt.setDouble(3, price);
                    stmt.setDouble(4, fuelConsumption);
                    stmt.setInt(5, power);
                    stmt.setInt(6, year);
                    if (stmt.executeUpdate() == 1) {
                        log.info("DB statement completed");
                        result = true;
                    }
                } catch (SQLException var26) {
                    log.error(var26.getMessage());
                    throw new RuntimeException(var26);
                } finally {
                    conn.close();
                }
            } catch (Throwable var28) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var24) {
                        var28.addSuppressed(var24);
                    }
                }

                throw var28;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var29) {
            log.error(var29.getMessage());
        }

        return result;
    }
}
