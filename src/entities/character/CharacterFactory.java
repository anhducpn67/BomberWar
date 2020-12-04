package entities.character;

import entities.character.enemy.*;
import map.GameMap;
import sprite.Sprite;

public class CharacterFactory {

    private CharacterFactory() {
    }

    public static void getCharacter(char ch, int i, int j) {
        switch (ch) {
            case 'p':
                if (GameMap.player_1 == null) {
                    GameMap.player_1 = new Bomber(j, i, Sprite.player_right);
                } else {
                    GameMap.player_1.tileX = j;
                    GameMap.player_1.tileY = i;
                    GameMap.player_1.pixelX = j * Sprite.SCALED_SIZE;
                    GameMap.player_1.pixelY = i * Sprite.SCALED_SIZE;
                    GameMap.getGameMap().characters.add(GameMap.player_1);
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
