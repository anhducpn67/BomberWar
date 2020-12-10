package input;

import entities.character.Bomber;

public class PlayerTwo implements KeyInput {

    public void initialization() {
        keyInput.put("LEFT", false);
        keyInput.put("RIGHT", false);
        keyInput.put("UP", false);
        keyInput.put("DOWN", false);
        keyInput.put("ENTER", false);
    }

    @Override
    public int handleKeyInput(Bomber bomber) {
        if (keyInput.get("ENTER")) {
            keyInput.put("ENTER", false);
            bomber.placeBomb();
        }
        if (keyInput.get("LEFT")) {
            return 1;
        }
        if (keyInput.get("RIGHT")) {
            return 2;
        }
        if (keyInput.get("UP")) {
            return 3;
        }
        if (keyInput.get("DOWN")) {
            return 0;
        }
        return -1;
    }
}
