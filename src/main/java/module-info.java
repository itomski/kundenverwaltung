module de.lubowiecki.gui.kunden {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens de.lubowiecki.gui.kunden to javafx.fxml;
    opens de.lubowiecki.gui.kunden.model to javafx.base;
    exports de.lubowiecki.gui.kunden;
}