/*
 * Public Mage Sim SoD
 * 7/1/2024
 */

package MageSim;

import java.io.*;
import java.util.Scanner;

public class Worker extends Rotations{
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception
	{
		//Variables
		final int numIterations = 10000;
		final int damageTypes = 5;
		final int profileToRun = 1; //scuffed rotation selector
		int[][] simValues = new int[damageTypes][numIterations];
		int[] simOutput = new int[damageTypes];
		int fightDuration = 0;
		int totalFFB = 0;
		int totalBombTick = 0;
		int totalBombExplosion = 0;
		int totalIgnite = 0;
		int totalDamage = 0;
		
		//Gather Spell and Rotation Lists
		SpellList spell = new SpellList();
		Rotations[] rotations = new Rotations[numIterations];
		
		//Load Sim Characteristics
		//TODO: manual txt file is JANK build automated solution
		//TODO: build talent support instead of assuming build
		try {
			File simCharacteristics = new File(
					"Sim Characteristics.txt");
			Scanner fileReader = new Scanner(simCharacteristics);
			while (fileReader.hasNextLine()) {
				String locate = fileReader.nextLine(); //Fight Length [UNIT .1s]
				fightDuration = Integer.parseInt(locate.substring(locate.lastIndexOf(":") + 1));
				
				locate = fileReader.nextLine(); //Mana
				spell.setRemainingMana(Integer.parseInt(locate.substring(locate.lastIndexOf(":") + 1)));
				
				locate = fileReader.nextLine(); //Intellect
				spell.setIntellect(Integer.parseInt(locate.substring(locate.lastIndexOf(":") + 1)));
				
				locate = fileReader.nextLine(); //Spellpower
				spell.setSpellPower(Integer.parseInt(locate.substring(locate.lastIndexOf(":") + 1)));
				
				locate = fileReader.nextLine(); //Crit Chance
				spell.setCritChance(Double.parseDouble(locate.substring(locate.lastIndexOf(":") + 1)));
				
				locate = fileReader.nextLine(); //Crit Multiplier
				spell.setCritMultiplier(Double.parseDouble(locate.substring(locate.lastIndexOf(":") + 1)));
				
				locate = fileReader.nextLine(); //Hit Chance
				spell.setHitChance(Integer.parseInt(locate.substring(locate.lastIndexOf(":") + 1)));
			}
			fileReader.close();
		} catch (FileNotFoundException e){
			System.out.println("Unable to locate Sim Characteristics");
			e.printStackTrace();
		}
		
		//Sim the Correct Rotation
		if (profileToRun == 0) { //Simple Frostfire Bolt
			for (int i = 0; i < numIterations; i++) {
				rotations[i] = new Rotations();
				simOutput = rotations[i].SimpleFFB(spell,  fightDuration);
				
				for (int j = 0; j < damageTypes; j++) {
					simValues[j][i] = simOutput[j];
				}

				totalFFB += simValues[0][i];
				totalBombTick += simValues[1][i];
				totalBombExplosion += simValues[2][i];
				totalIgnite += simValues[3][i];
				totalDamage += simValues[4][i];
			}
		}
		if (profileToRun == 1) { //Frostfire Bolt + Living Bomb
			for (int i = 0; i < numIterations; i++) {
				rotations[i] = new Rotations();
				simOutput = rotations[i].BombAndFFB(spell,  fightDuration);
				
				for (int j = 0; j < damageTypes; j++) {
					simValues[j][i] = simOutput[j];
				}

				totalFFB += simValues[0][i];
				totalBombTick += simValues[1][i];
				totalBombExplosion += simValues[2][i];
				totalIgnite += simValues[3][i];
				totalDamage += simValues[4][i];
			}
		}
		
		//Print Results After 10k Iterations
		System.out.println("");
		System.out.println("Damage Breakdown After " + numIterations + " run(s)  DMG  | DPS");
		System.out.println("________________________________________________");
		System.out.println("");
		System.out.println("Frostfire Bolt Damage             = " + totalFFB / numIterations + " | " + (totalFFB / numIterations) * 10 / fightDuration);
		System.out.println("Living Bomb Tick Damage           = " + totalBombTick / numIterations + "  | " + (totalBombTick / numIterations) * 10 / fightDuration);
		System.out.println("Living Bomb Explosion Damage      = " + totalBombExplosion / numIterations + "  | " + (totalBombExplosion / numIterations) * 10 / fightDuration);
		System.out.println("Ignite Damage                     = " + totalIgnite / numIterations + "  | " + (totalIgnite / numIterations) * 10 / fightDuration);
		System.out.println("Total Damage Dealt                = " + totalDamage / numIterations + " | " + (totalDamage / numIterations) * 10 / fightDuration);
	}
}
