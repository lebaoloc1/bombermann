package org.secondgroup.bomberman.view.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.secondgroup.bomberman.view.TabName;

import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {
    private static volatile RootController instance;

    @FXML
    private AnchorPane root;

    public static RootController getInstance() {
        return instance;
    }

    public void switchTab(String tabName) {
        for (var child : root.getChildren()) {
            if (child == null) continue;
            var id = child.getId();

            child.setVisible(id != null && id.equals(tabName));
        }
    }

    public void initialize(URL url, ResourceBundle bundle) {
        instance = this;

        switchTab(TabName.DEFAULT);
    }
}