package trace;

import entities.bean.Enemy;
import entities.character.Bomber;

public interface TraceStrategy {
    int trace(Enemy enemy, Bomber player);
}
