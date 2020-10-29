package input;

import java.util.HashMap;

public class KeyInput {
    public static HashMap<String, Boolean> keyInput = new HashMap<>();
    static {
        keyInput.put("LEFT", false);
        keyInput.put("RIGHT", false);
        keyInput.put("UP", false);
        keyInput.put("DOWN", false);
        keyInput.put("SPACE", false);
    }
}
