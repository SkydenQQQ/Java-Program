package uoa.assignment.game;

import java.util.Scanner;

import uoa.assignment.character.GameCharacter;
import uoa.assignment.character.Player;
import uoa.assignment.character.Monster;

public class GameLogic {

    private static boolean gameOver = false;
    private static Map gameMap;

    public static void setGameMap(Map map) {
        gameMap = map;
    }

	public static void moveCharacter(String input, Map gameMap, GameCharacter character) {
		// 根据用户输入调用对应的移动方法
        switch (input.toLowerCase()) {
            case "up":
                moveUp(character, gameMap);
                break;
            case "down":
                moveDown(character, gameMap);
                break;
            case "left":
                moveLeft(character, gameMap);
                break;
            case "right":
                moveRight(character, gameMap);
                break;
            default:
                System.out.println("Use only keywords up, down, left, right");
        }
    }

    private static void moveRight(GameCharacter character, Map gameMap) {
        int newRow = character.row;
        int newColumn = character.column + 1;

        if (isValidMove(newRow, newColumn, gameMap)) {
            if (isTargetPositionEmpty(newRow, newColumn, gameMap)) {
                updateCharacterPosition(character, gameMap, newRow, newColumn);
            } else {
                GameCharacter targetCharacter = gameMap.getCharacterAt(newRow, newColumn);
                if (character instanceof Player && targetCharacter instanceof Monster) {
                    attackPlayer((Player) character, (Monster) targetCharacter);
                    if(targetCharacter.getHealth()<=0)
                    gameMap.layout[newRow][newColumn]="x";
                    System.out.println("Character already dead");
                } else if (character instanceof Monster && targetCharacter instanceof Player) {
                    attackMonster((Monster) character, (Player) targetCharacter);
                } else {
                    System.out.println("Monster already there so it cannot move");
                }
            }
        } else {
            System.out.println("You can't go right. You lose a move.");
        }
    }

    
    

    private static void moveLeft(GameCharacter character, Map gameMap) {
        int newRow = character.row;
        int newColumn = character.column - 1;

        if (isValidMove(newRow, newColumn, gameMap)) {
            if (isTargetPositionEmpty(newRow, newColumn, gameMap)) {
                updateCharacterPosition(character, gameMap, newRow, newColumn);
            } else {
                GameCharacter targetCharacter = gameMap.getCharacterAt(newRow, newColumn);
                if (character instanceof Player && targetCharacter instanceof Monster) {
                    attackPlayer((Player) character, (Monster) targetCharacter);
                    if(targetCharacter.getHealth()<=0)
                    gameMap.layout[newRow][newColumn]="x";
                    System.out.println("Character already dead");
                } else if (character instanceof Monster && targetCharacter instanceof Player) {
                    attackMonster((Monster) character, (Player) targetCharacter);
                } else {
                    System.out.println("Monster already there so it cannot move");
                }
            }
        } else {
            System.out.println("You can't go left. You lose a move.");
        }
    }
    
    private static void moveUp(GameCharacter character, Map gameMap) {
        int newRow = character.row - 1;
        int newColumn = character.column;

        if (isValidMove(newRow, newColumn, gameMap)) {
            if (isTargetPositionEmpty(newRow, newColumn, gameMap)) {
                updateCharacterPosition(character, gameMap, newRow, newColumn);
            } else {
                GameCharacter targetCharacter = gameMap.getCharacterAt(newRow, newColumn);
                if (character instanceof Player && targetCharacter instanceof Monster) {
                    attackPlayer((Player) character, (Monster) targetCharacter);
                    if(targetCharacter.getHealth()<=0)
                    gameMap.layout[newRow][newColumn]="x";
                    System.out.println("Character already dead");
                } else if (character instanceof Monster && targetCharacter instanceof Player) {
                    attackMonster((Monster) character, (Player) targetCharacter);
                } else {
                    System.out.println("Monster already there so it cannot move");
                }
            }
        } else {
            System.out.println("You can't go up. You lose a move.");
        }
    }
    

    private static void moveDown(GameCharacter character, Map gameMap) {
        int newRow = character.row + 1;
        int newColumn = character.column;

        if (isValidMove(newRow, newColumn, gameMap)) {
            if (isTargetPositionEmpty(newRow, newColumn, gameMap)) {
                updateCharacterPosition(character, gameMap, newRow, newColumn);
            } else {
                GameCharacter targetCharacter = gameMap.getCharacterAt(newRow, newColumn);
                if (character instanceof Player && targetCharacter instanceof Monster) {
                    attackPlayer((Player) character, (Monster) targetCharacter);
                    if(targetCharacter.getHealth()<=0)
                    gameMap.layout[newRow][newColumn]="x";
                    System.out.println("Character already dead");
                } else if (character instanceof Monster && targetCharacter instanceof Player) {
                    attackMonster((Monster) character, (Player) targetCharacter);
                } else {
                    System.out.println("Monster already there so it cannot move");
                }
            }
        } else {
            System.out.println("You can't go down. You lose a move.");
        }
    }

  private static void attackPlayer(Player player, Monster monster) {
        if (!player.successfulDefense()) {
            player.hurtCharacter(monster);
            System.out.println(monster.sayName() + " attacked " + player.sayName() + " for " + 20 + " damage.");
            System.out.println("Health " + player.sayName() + ": " + player.getHealth());
            if (player.getHealth() <= 0) {
                System.out.println("YOU HAVE DIED!");
                gameOver = true;
            }
        } else {
            System.out.println(player.sayName() + " successfully defended against the attack.");
        }
    }

    private static void attackMonster(Monster monster, Player player) {
        if (!monster.successfulDefense()) {
            monster.hurtCharacter(player);
            System.out.println(player.sayName() + " attacked " + monster.sayName() + " for " + 50 + " damage.");
            System.out.println("Health " + monster.sayName() + ": " + monster.getHealth());
            if (monster.getHealth() <= 0) {
                System.out.println("Monster " + monster.sayName() + " has been defeated!");
                gameMap.killCharacter(monster.row, monster.column);
            }
        } else {
            System.out.println(monster.sayName() + " successfully defended against the attack.");
        }
    }

    private static boolean isValidMove(int newRow, int newColumn, Map gameMap) {
        // 检查是否越界
        if(newRow >= 0 && newRow < gameMap.layout.length &&
               newColumn >= 0 && newColumn < gameMap.layout[0].length)
        return true;
        else return false;
    }
    private static boolean isTargetPositionEmpty(int newRow, int newColumn, Map gameMap) {
        // 检查目标位置是否为空
        return gameMap.layout[newRow][newColumn].equals(".");
    }


    private static void updateCharacterPosition(GameCharacter character, Map gameMap, int newRow, int newColumn) {
        // 更新角色在地图上的位置
        gameMap.layout[character.row][character.column] = ".";  // 旧位置设置为'.'
        character.row = newRow;
        character.column = newColumn;
        gameMap.layout[character.row][character.column] = character instanceof Player ? "*" : "%";  // 新位置设置为角色标识
    }
}
	
	

