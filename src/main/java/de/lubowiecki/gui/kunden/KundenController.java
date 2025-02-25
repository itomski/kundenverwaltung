package de.lubowiecki.gui.kunden;

import de.lubowiecki.gui.kunden.model.Kunde;
import de.lubowiecki.gui.kunden.model.KundenCrud;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class KundenController implements Initializable {

    @FXML
    private TextField vorname;

    @FXML
    private TextField nachname;

    @FXML
    private CheckBox aktiv;

    @FXML
    private TableView<Kunde> kundenTbl;

    private KundenCrud crud = new KundenCrud();

    @FXML
    protected void save() {
        // Formular wird abgefragt und das Objekt daraus erzeugt
        Kunde kunde = new Kunde(vorname.getText(), nachname.getText());
        if(aktiv.isSelected()) { // TODO: Checken
            kunde.setAktiv(true);
        }

        try {
            if (crud.save(kunde)) {
                System.out.println("Gespeichert"); // TODO: Ausgabe in der GUI
                clearForm();
                showKunden();
            }
        }
        catch(Exception e) {
            System.out.println("Problem"); // TODO: Ausgabe in der GUI
        }
    }

    // Zustand des Formulars zurücksetzen
    private void clearForm() {
        vorname.clear();
        nachname.clear();
        aktiv.setSelected(false);
    }

    private void showKunden() {
        try {
            List<Kunde> kunden = crud.findAll(); // Kunden aus der DB abfragen
            kundenTbl.setItems(FXCollections.observableList(kunden)); // Kunden in der TableView anzeigen
            kundenTbl.refresh();
        }
        catch(Exception e) {
            System.out.println("Problem"); // TODO: Ausgabe in der GUI
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showKunden();
    }
}