package org.secondgroup.bomberman.view.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.secondgroup.bomberman.view.TabName;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private AnchorPane root;

    public void initialize(URL url, ResourceBundle bundle) {
        AnchorPane.setTopAnchor(root, 0d);
        AnchorPane.setRightAnchor(root, 0d);
        AnchorPane.setBottomAnchor(root, 0d);
        AnchorPane.setLeftAnchor(root, 0d);

        root.idProperty().set(TabName.MENU_TAB);
    }
}
