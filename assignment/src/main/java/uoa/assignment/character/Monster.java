package uoa.assignment.character;

import java.util.Random;

public class Monster extends GameCharacter {

	public  Monster(String name) {
		super(name);
	}


	@Override
	public void hurtCharacter(GameCharacter character) {
		if (!character.successfulDefense()) {
            int currentHealth = character.getHealth();
            int newHealth = currentHealth - 20; 
            character.setHealth(newHealth);
	}
	}

	@Override
	public boolean successfulDefense() {
		Random random = new Random();
        return random.nextBoolean();
	}


	
	public String decideMove () {
		// 随机返回 "up"、"down"、"left" 或 "right"
        Random random = new Random();
        int moveIndex = random.nextInt(4);

        switch (moveIndex) {
            case 0:
                return "up";
            case 1:
                return "down";
            case 2:
                return "left";
            case 3:
                return "right";
            default:
                // 不太可能到达的代码，但为了满足方法的返回值要求
                return "up";
		}
	}

}
