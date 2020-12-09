package input;

public class PlayerTwo implements KeyInput {

    public void initialization() {
        keyInput.put("LEFT", false);
        keyInput.put("RIGHT", false);
        keyInput.put("UP", false);
        keyInput.put("DOWN", false);
        keyInput.put("ENTER", false);
    }

    @Override
    public int handleKeyInput() {
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
        if (keyInput.get("ENTER")) {
            keyInput.put("ENTER", false);
            return 4;
        }
        return -1;
    }
}
