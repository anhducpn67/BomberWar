package input;

import entities.character.Bomber;

import java.util.HashMap;

public interface KeyInput {
    HashMap<String, Boolean> keyInput = new HashMap<>();
    void initialization();
    int handleKeyInput(Bomber bomber);
}
