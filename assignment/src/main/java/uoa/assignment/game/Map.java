package uoa.assignment.game;

import uoa.assignment.character.GameCharacter;
import uoa.assignment.character.Monster;
import uoa.assignment.character.Player;

public class Map {

public String [][] layout;
public GameCharacter characters [] ;
 
  Map (int height, int width) {
	layout = new String[height][width];
    characters = new GameCharacter[4];
    initialiseArray(); // 调用初始化数组方法
    createCharacters(); // 调用创建角色方法
  }
   private void initialiseArray() {
        // 将2D数组用'.'初始化
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                layout[i][j] = ".";
            }
        }
    }

    private void createCharacters() {
        // 创建玩家和怪物，将它们放置在地图上
        characters[0] = new Player("Player");
        characters[1] = new Monster("Monster1");
        characters[2] = new Monster("Monster2");
        characters[3] = new Monster("Monster3");

        // 将玩家放在地图的右下角
        characters[0].row = layout.length - 1;
        characters[0].column = layout[0].length - 1;
        layout[characters[0].row][characters[0].column] = "*";

        // 将怪物放在地图的其他角落
        characters[1].row = 0;
        characters[1].column = layout[0].length - 1;
        layout[characters[1].row][characters[1].column] = "%";

        characters[2].row = layout.length - 1;
        characters[2].column = 0;
        layout[characters[2].row][characters[2].column] = "%";

        characters[3].row = 0;
        characters[3].column = 0;
        layout[characters[3].row][characters[3].column] = "%";
    }


    public void printLayout() {
        // 打印地图布局到控制台
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                System.out.print(layout[i][j]);
            }
            System.out.println(); // 换行
        }
    
   }

    public GameCharacter getCharacterAt(int row, int column) {
        for (GameCharacter character : characters) {
            if (character != null && character.row == row && character.column == column) {
                return character;
            }
        }
        return null; // 如果没有找到匹配的角色，返回 null
    }

    public void killCharacter(int row, int column) {
        for (GameCharacter character : characters) {
            if (character != null && character.row == row && character.column == column) {
                character = null;
                break; // 找到匹配的角色后立即退出循环
            }
        }
    }

}



