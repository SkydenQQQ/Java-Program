package uoa.assignment.game;
import java.util.*;
public class RunGame {

	private static boolean gameOver = false;

	public static void main(String[] args) {
		if (args.length != 2) {
            return;
        }
        
        int height = Integer.parseInt(args[0]);
        int width = Integer.parseInt(args[1]);
        Game game = new Game(height, width);
        GameLogic.setGameMap(game.getMap());
        
        int round = 1;
        while (!gameOver) {
		// 创建Game对象，实例化和初始化Map对象，并打印地图布局
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            gameOver = game.nextRound(input);
            System.out.println("Round "+round); 
            round++;

             // 在每次循环中执行游戏的一个回合
		}
	}

}
