/*
 * Public Mage Sim SoD
 * 7/1/2024
 */

package MageSim;

import java.io.*;
import java.util.Scanner;

public class Worker extends FFBDeepFire{
	public static void main(String[] args) throws Exception
	{
		//Timing Variables
		int fightDuration = -1;
		int remainingDuration = -1;
		int busyDuration = -1;
		int ffbConnect = -1;
		int igniteStart = -1;
		int igniteEnd = -1;
		int igniteTick = -1;
		int bombExplode = -1;
		int bombTick = -1;
		boolean hasCrit = false;
		boolean igniteActive = false;
		boolean livingBomb = false;
		
		//Damage Tracking Variables
		int currentDamage = 0;
		int currentFFB = 0;
		int currentBombTick = 0;
		int currentBombExplosion = 0;
		int currentIgnite = 0;
		int totalFFB = 0;
		int totalBombTick = 0;
		int totalBombExplosion = 0;
		int totalIgnite = 0;
		int totalDamage = 0;
		
		//Arrays
		final int numIterations = 10000;
		int[][] simValues = new int[5][numIterations];
		
		//Build Profile
		FFBDeepFire profile = new FFBDeepFire();
		
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
				profile.setRemainingMana(Integer.parseInt(locate.substring(locate.lastIndexOf(":") + 1)));
				
				locate = fileReader.nextLine(); //Intellect
				profile.setIntellect(Integer.parseInt(locate.substring(locate.lastIndexOf(":") + 1)));
				
				locate = fileReader.nextLine(); //Spellpower
				profile.setSpellPower(Integer.parseInt(locate.substring(locate.lastIndexOf(":") + 1)));
				
				locate = fileReader.nextLine(); //Crit Chance
				profile.setCritChance(Double.parseDouble(locate.substring(locate.lastIndexOf(":") + 1)));
				
				locate = fileReader.nextLine(); //Crit Multiplier
				profile.setCritMultiplier(Double.parseDouble(locate.substring(locate.lastIndexOf(":") + 1)));
				
				locate = fileReader.nextLine(); //Hit Chance
				profile.setHitChance(Integer.parseInt(locate.substring(locate.lastIndexOf(":") + 1)));
			}
			fileReader.close();
		} catch (FileNotFoundException e){
			System.out.println("Unable to locate Sim Characteristics");
			e.printStackTrace();
		}
		
		//Run the Sim
		for (int i = 0; i < numIterations; i++) {
			//Reset Timing Variables
			remainingDuration = fightDuration;
			busyDuration = -1;
			ffbConnect = -1;
			igniteStart = -1;
			igniteEnd = -1;
			igniteTick = -1;
			bombExplode = -1;
			bombTick = -1;
			hasCrit = false;
			igniteActive = false;
			livingBomb = false;
			
			//Reset Damage Variables
			currentDamage = 0;
			currentFFB = 0;
			currentBombTick = 0;
			currentBombExplosion = 0;
			currentIgnite = 0;
			totalFFB = 0;
			totalBombTick = 0;
			totalBombExplosion = 0;
			totalIgnite = 0;
			totalDamage = 0;
			
			while (remainingDuration > 0) {
				//Spell Choice
				//Living Bomb
				if (busyDuration <= 0 && !livingBomb) {
					//Spell Misses
			    	if (profile.generateRandom(0,100) > 94 + profile.getHitChance()) {
			    		//System.out.println("Living Bomb Missed");
			    		busyDuration = 15;
			    	} else {
			    		bombExplode = remainingDuration - 120;
						bombTick = remainingDuration - 30;
						
						//System.out.println("Living Bomb Applied");
						//System.out.println("Time Left + Bomb Applied: " + remainingDuration);
						livingBomb = true;
						busyDuration = 15;
			    	}
				}
				
				//Frostfire Bolt
				if (busyDuration <= 0) { 
					ffbConnect = remainingDuration - 29;
					//System.out.println("Time Left + FFB Cast Start: " + remainingDuration);
					busyDuration = 30;
				}
				
				//Direct Damage Effects
				//FrostFire Bolt
				if (remainingDuration == ffbConnect) {
					if (profile.generateRandom(0,100) <= profile.getCritChance() * 100) {
						hasCrit = true;
					}
					
					currentFFB = profile.castFrostFireBolt(hasCrit);		
					totalFFB += currentFFB;
					currentDamage += currentFFB;
				}
				
				//Living Bomb Explosion
				if (remainingDuration == bombExplode) {
					if (profile.generateRandom(0, 100) <= profile.getCritChance() * 100) {
						hasCrit = true;
					}
					
					currentBombExplosion = profile.castLivingBomb(hasCrit);
					totalBombExplosion += currentBombExplosion;
					currentDamage += currentBombExplosion;
					
					livingBomb = false;
				}
				
				//Periodic Damage Effects
				//Living Bomb Tick
				if (remainingDuration == bombTick
						|| remainingDuration == bombTick - 30
						|| remainingDuration == bombTick - 60
						|| remainingDuration == bombTick - 90) {
					currentBombTick = profile.castLivingBombTick();
					totalBombTick += currentBombTick;
					currentDamage += currentBombTick;
				}
				
				//Ignite
				if (hasCrit) {
					igniteStart = remainingDuration - 20;
					igniteEnd = remainingDuration - 40;
					
					if (igniteActive) {
						igniteTick += (int) Math.round(currentDamage * .2);
					} else {
						igniteTick = (int) Math.round(currentDamage * .2);
						igniteActive = true;
					}
				}			
				
				//Apply Ignite Damage
				if (remainingDuration == igniteStart) {
					currentIgnite = igniteTick;
					totalIgnite += currentIgnite;
					currentDamage += currentIgnite;
					
					//System.out.println("Ignite did: " + currentDamage);
				}
				if (remainingDuration == igniteEnd) {
					currentIgnite = igniteTick;
					totalIgnite += currentIgnite;
					currentDamage += currentIgnite;
					
					//System.out.println("Ignite did: " + currentDamage);
					
					igniteActive = false;
				}
				
				//Increment Values and Reset States
				totalDamage += currentDamage;
				
				currentDamage = 0;
				hasCrit = false;
				
				busyDuration -= 1;
				remainingDuration -= 1;
			}
			
			//Add Sim Data to Array
			simValues[0][i] = totalFFB;
			simValues[1][i] = totalBombTick;
			simValues[2][i] = totalBombExplosion;
			simValues[3][i] = totalIgnite;
			simValues[4][i] = totalDamage;
			
			//Print Out Single Sim Iteration
			//System.out.println("");
			//System.out.println("Damage Breakdown");
			//System.out.println("Frostfire Bolt Damage        = " + simValues[0][i] + " | " + totalFFB * 10 / fightDuration);
			//System.out.println("Living Bomb Tick Damage      = " + totalBombTick + "  | " + totalBombTick * 10 / fightDuration);
			//System.out.println("Living Bomb Explosion Damage = " + totalBombExplosion + "  | " + totalBombExplosion * 10 / fightDuration);
			//System.out.println("Ignite Damage                = " + totalIgnite + "  | " + totalIgnite * 10 / fightDuration);
			//System.out.println("Total Damage Dealt           = " + totalDamage + " | " + totalDamage * 10 / fightDuration);
		}
		
		//Reset All Totals
		totalFFB = 0;
		totalBombTick = 0;
		totalBombExplosion = 0;
		totalIgnite = 0;
		totalDamage = 0;
		
		//Pull Overall Results from Array
		for (int j = 0; j < numIterations; j++) {
			totalFFB += simValues[0][j];
			totalBombTick += simValues[1][j];
			totalBombExplosion += simValues[2][j];
			totalIgnite += simValues[3][j];
			totalDamage += simValues[4][j];
		}
		
		//Print Results After 10k Iterations
		System.out.println("");
		System.out.println("Damage Breakdown                DMG  | DPS");
		System.out.println("__________________________________________");
		System.out.println("");
		System.out.println("Frostfire Bolt Damage        = " + totalFFB / numIterations + " | " + (totalFFB / numIterations) * 10 / fightDuration);
		System.out.println("Living Bomb Tick Damage      = " + totalBombTick / numIterations + "  | " + (totalBombTick / numIterations) * 10 / fightDuration);
		System.out.println("Living Bomb Explosion Damage = " + totalBombExplosion / numIterations + "  | " + (totalBombExplosion / numIterations) * 10 / fightDuration);
		System.out.println("Ignite Damage                = " + totalIgnite / numIterations + "  | " + (totalIgnite / numIterations) * 10 / fightDuration);
		System.out.println("Total Damage Dealt           = " + totalDamage / numIterations + " | " + (totalDamage / numIterations) * 10 / fightDuration);
	}
}
