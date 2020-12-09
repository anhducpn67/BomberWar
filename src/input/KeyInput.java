package input;

import java.util.HashMap;

public interface KeyInput {
    HashMap<String, Boolean> keyInput = new HashMap<>();
    void initialization();
    int handleKeyInput();
}
