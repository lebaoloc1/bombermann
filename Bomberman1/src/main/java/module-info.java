module org.secondgroup.bomberman {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    opens org.secondgroup.bomberman to javafx.fxml;
    exports org.secondgroup.bomberman;
    exports org.secondgroup.bomberman.view;
    opens org.secondgroup.bomberman.view to javafx.fxml;
    exports org.secondgroup.bomberman.view.controllers;
    opens org.secondgroup.bomberman.view.controllers to javafx.fxml;
    exports org.secondgroup.bomberman.common;
    opens org.secondgroup.bomberman.common to javafx.fxml;
}