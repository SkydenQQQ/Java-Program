package uoa.assignment.game;

import uoa.assignment.character.GameCharacter;
import uoa.assignment.character.Monster;
import uoa.assignment.character.Player;

public class Game {
    
    private Map map;
    private Player player;
    private Monster[] monsters;
    
    Game (int height, int width) {
        map = new Map(height,width);
        player = (Player)map.characters[0];
        monsters = new Monster[]{(Monster)map.characters[1], (Monster)map.characters[2], (Monster)map.characters[3]};
        map.printLayout();
    }
    			
    public Map getMap() {
        return map;
    }
    
    public boolean nextRound (String input) {
        // 移动玩家
        
        moveCharacter(input, player);
        System.out.println(player.sayName() + " is moving " + input);

        // 移动怪物
        for (Monster monster : monsters) {
            String monsterMove = monster.decideMove();
            System.out.println(monster.sayName() + " is moving " + monsterMove);
            moveCharacter(monsterMove, monster);
        }
    
        map.printLayout(); // 打印更新后的地图布局


        System.out.println("Health " + player.sayName() + ": " + player.getHealth());
        for (Monster monster : monsters) {
            System.out.println("Health " + monster.sayName() + ": " + monster.getHealth());
        }

         if (noLivingMonsters()) {
            System.out.println("YOU HAVE WON!");
            return true; // 游戏结束
        } else if (player.getHealth() <= 0) {
            System.out.println("YOU HAVE DIED!");
            return true; // 游戏结束
        }


        return false; // 继续下一回合
    }
   
    private void moveCharacter(String input, GameCharacter character) {
        GameLogic.moveCharacter(input, this.getMap(), character);
    }

    private boolean noLivingMonsters() {
        for (Monster monster : monsters) {
            if (monster.getHealth() > 0) {
                return false;
            }
        }
        return true;
    }
   
}