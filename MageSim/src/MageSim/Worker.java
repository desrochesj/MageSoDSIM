/*
 * Public Mage Sim SoD P3 (Sunken Temple)
 * 5/3/2024
 */

package MageSim;

import java.io.*;
import java.util.Scanner;

public class Worker extends Player{
	public static void main(String[] args) throws Exception
	{
		//Variables
		int fightDuration = -1;
		int remainingDuration = -1;
		int damageDealt = 0;
		
		//Build Player
		Player player = new Player();
		
		//Load Sim Characteristics
		//TODO: manual txt file is JANK build automated solution
		//TODO: build talent support instead of assuming builds
		try {
			File simCharacteristics = new File(
					"Sim Characteristics.txt");
			Scanner fileReader = new Scanner(simCharacteristics);
			while (fileReader.hasNextLine()) {
				String locate = fileReader.nextLine(); //Fight Length
				fightDuration = Integer.parseInt(locate.substring(locate.lastIndexOf(":") + 1));
				
				locate = fileReader.nextLine(); //Mana
				player.setRemainingMana(Integer.parseInt(locate.substring(locate.lastIndexOf(":") + 1)));
				
				locate = fileReader.nextLine(); //Intellect
				player.setIntellect(Integer.parseInt(locate.substring(locate.lastIndexOf(":") + 1)));
				
				locate = fileReader.nextLine(); //Spellpower
				player.setSpellPower(Integer.parseInt(locate.substring(locate.lastIndexOf(":") + 1)));
				
				locate = fileReader.nextLine(); //Crit Chance
				player.setCritChance(Double.parseDouble(locate.substring(locate.lastIndexOf(":") + 1)));
				
				locate = fileReader.nextLine(); //Crit Multiplier
				player.setCritMultiplier(Double.parseDouble(locate.substring(locate.lastIndexOf(":") + 1)));
				
				locate = fileReader.nextLine(); //Hit Chance
				player.setHitChance(Integer.parseInt(locate.substring(locate.lastIndexOf(":") + 1)));
			}
			fileReader.close();
		} catch (FileNotFoundException e){
			System.out.println("Unable to located Sim Characteristics");
			e.printStackTrace();
		}
		
		//Run the Sim
		//TODO iterate 10k times and take average
		remainingDuration = fightDuration;
		while (remainingDuration > 3) {
			damageDealt += player.castFrostFireBolt();
			remainingDuration -= 3;
		}
		
		System.out.println("Total Damage Dealt = " + damageDealt);
		System.out.println("DPS = " + damageDealt / fightDuration);
	}
}
