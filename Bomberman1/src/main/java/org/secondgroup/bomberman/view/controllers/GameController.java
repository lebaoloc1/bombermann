package org.secondgroup.bomberman.view.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.secondgroup.bomberman.view.TabName;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane container;

    private void resizeInner() {
        var rootWidth = root.widthProperty().get();
        var rootHeight = root.heightProperty().get();

        AnchorPane.clearConstraints(container);

        if (rootWidth < rootHeight) {
            AnchorPane.setLeftAnchor(container, 0d);
            AnchorPane.setRightAnchor(container, 0d);

            var padding = (rootHeight - rootWidth) / 2;
            AnchorPane.setTopAnchor(container, padding);
            AnchorPane.setBottomAnchor(container, padding);
        } else {
            AnchorPane.setTopAnchor(container, 0d);
            AnchorPane.setBottomAnchor(container, 0d);

            var padding = (rootWidth - rootHeight) / 2;
            AnchorPane.setLeftAnchor(container, padding);
            AnchorPane.setRightAnchor(container, padding);
        }
    }

    public void initialize(URL url, ResourceBundle bundle) {
        AnchorPane.setTopAnchor(root, 0d);
        AnchorPane.setRightAnchor(root, 0d);
        AnchorPane.setBottomAnchor(root, 0d);
        AnchorPane.setLeftAnchor(root, 0d);

        root.widthProperty().addListener((obs, ov, nv) -> resizeInner());
        root.heightProperty().addListener((obs, ov, nv) -> resizeInner());

        root.idProperty().set(TabName.GAME_TAB);
    }
}
