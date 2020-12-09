package input;

public class PlayerOne implements KeyInput{

    public void initialization() {
        keyInput.put("A", false);
        keyInput.put("D", false);
        keyInput.put("W", false);
        keyInput.put("S", false);
        keyInput.put("SPACE", false);
    }

    @Override
    public int handleKeyInput() {
        if (keyInput.get("A")) {
            return 1;
        }
        if (keyInput.get("D")) {
            return 2;
        }
        if (keyInput.get("W")) {
            return 3;
        }
        if (keyInput.get("S")) {
            return 0;
        }
        if (keyInput.get("SPACE")) {
            keyInput.put("SPACE", false);
            return 4;
        }
        return -1;
    }
}
