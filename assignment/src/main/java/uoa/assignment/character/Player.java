package uoa.assignment.character;

import java.util.Random;

public class Player extends GameCharacter{

	public Player(String name) {
		super(name);
	}

	@Override
	public
	void hurtCharacter(GameCharacter character) {
		if (!character.successfulDefense()) {
            int currentHealth = character.getHealth();
            int newHealth = Math.max(currentHealth - 50, 0); 
            character.setHealth(newHealth);
        }
	}

	@Override
	public
	boolean successfulDefense() {
		Random random = new Random();
        return random.nextDouble() <= 0.3;
	}

}
