module com.minotor.desktop_minotor {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.mongodb.driver.sync.client;   // driver sync
    requires org.mongodb.driver.core;          // core driver
    requires org.mongodb.bson;                  // bson
    requires org.json;



    opens com.minotor.desktop_minotor to javafx.fxml;
    opens com.minotor.desktop_minotor.controller to javafx.fxml;
    exports com.minotor.desktop_minotor;
    exports App;
}