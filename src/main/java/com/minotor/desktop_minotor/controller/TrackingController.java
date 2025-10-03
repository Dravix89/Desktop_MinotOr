package com.minotor.desktop_minotor.controller;

import com.minotor.desktop_minotor.model.PageVisit;
import com.minotor.desktop_minotor.service.ApiService;
import com.minotor.desktop_minotor.service.TrackingService;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TrackingController {
    public Label titleText;
    @FXML
    private TableView<PageVisit> visitTable;
    @FXML
    private TableColumn<PageVisit, String> pageColumn;
    @FXML
    private TableColumn<PageVisit, String> dateColumn;

    private final TrackingService trackingService = new TrackingService();

    public TrackingController() {
    }

    @FXML
    public void initialize() {
        try {
            ApiService.postVisit("tracking", Instant.now().toString());

            trackingService.logPageVisit("tracking");
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.pageColumn.setCellValueFactory(new PropertyValueFactory<>("pageName"));
        this.dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getVisitDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

        this.visitTable.getItems().setAll(this.readVisits());
    }

    private List<PageVisit> readVisits() {
        return trackingService.getAllVisits();
    }
}

