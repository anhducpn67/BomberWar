package entities.character;

import entities.bean.Character;
import entities.character.enemy.Balloon;
import entities.character.enemy.Oneal;
import sprite.Sprite;

public class CharacterFactory {

    private CharacterFactory() {
    }

    public static void getCharacter(char ch, int i, int j) {
        switch (ch) {
            case 'p':
                new Bomber(j, i, Sprite.player_right);
                break;
            case '1':
                new Balloon(j, i, Sprite.balloon_right1);
                break;
            case '2':
                new Oneal(j, i, Sprite.oneal_right1);
                break;
        }
    }
}
