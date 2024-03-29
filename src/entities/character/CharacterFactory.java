package entities.character;

import entities.character.enemy.*;
import input.PlayerOne;
import input.PlayerTwo;
import map.Map;
import sprite.Sprite;

public class CharacterFactory {

    private CharacterFactory() {
    }

    public static void getCharacter(char ch, int i, int j) {
        switch (ch) {
            case 'p':
                if (Map.player_1 == null) {
                    Map.player_1 = new Bomber(j, i, Sprite.player_right);
                    Map.player_1.keyInput = new PlayerOne();
                    Map.player_1.keyInput.initialization();
                } else {
                    Map.player_1.tileX = j;
                    Map.player_1.tileY = i;
                    Map.player_1.pixelX = j * Sprite.SCALED_SIZE;
                    Map.player_1.pixelY = i * Sprite.SCALED_SIZE;
                    Map.getGameMap().characters.add(Map.player_1);
                }
                break;
            case 'o':
                if (Map.player_2 == null) {
                    Map.player_2 = new Bomber(j, i, Sprite.player_right);
                    Map.player_2.keyInput = new PlayerTwo();
                    Map.player_2.keyInput.initialization();
                } else {
                    Map.player_2.tileX = j;
                    Map.player_2.tileY = i;
                    Map.player_2.pixelX = j * Sprite.SCALED_SIZE;
                    Map.player_2.pixelY = i * Sprite.SCALED_SIZE;
                    Map.getGameMap().characters.add(Map.player_2);
                }
                break;
            case '1':
                new Balloon(j, i, Sprite.balloon_right1);
                break;
            case '2':
                new Oneal(j, i, Sprite.oneal_right1);
                break;
            case '3':
                new Minvo(j, i, Sprite.minvo_right1);
                break;
            case '4':
                new Kondoria(j, i, Sprite.kondoria_right1);
                break;
            case '5':
                new Ghost(j, i, Sprite.ghost_right1);
                break;
            case '6':
                new Boss(j, i, Sprite.boss_left1);
                break;
        }
    }
}
