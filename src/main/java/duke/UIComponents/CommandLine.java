package duke.UIComponents;

import javafx.scene.control.TextField;

public class CommandLine extends TextField {

    public CommandLine(){
        super();
    }

    public CommandLine(String text, String prompt){
        super(text);
        setPromptText(prompt);
        getStyleClass().add("style/persistent-prompt.css");
        refreshPromptVisibility();
        textProperty().addListener(observable -> refreshPromptVisibility());
    }

    private void refreshPromptVisibility(){
        final String text = getText();
        if (isEmptyString(text)) {
            getStyleClass().remove("no-prompt");
            getStyleClass().add("persistent-prompt");
        } else {
            if (!getStyleClass().contains("no-prompt")) {
                getStyleClass().add("no-prompt");
                getStyleClass().remove("persistent-prompt");
            }
        }
    }

    private boolean isEmptyString(String text) {
        return text == null || text.isEmpty();
    }
}
