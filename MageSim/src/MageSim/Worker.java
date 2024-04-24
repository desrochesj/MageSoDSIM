/*
 * Public Mage Sim SoD P3 (Sunken Temple)
 * 4/23/2024
 */

package MageSim;

import java.io.*;

public class Worker extends Player{
	public static void main(String[] args) throws Exception
	{
		//Load Profile Characteristics to Perform the SIM
		//TODO: manual txt file is JANK build automated solution
		//TODO: build talent support instead of doing it all manually lmao
		File simCharacteristics = new File(
				"C:\\Eclipse Projects\\MageSim\\Sim Characteristics.txt");
		
		int fightDuration = 60; //TODO: get number from characteristics txt
		
		while (fightDuration > 0) {
			CastSpell
		}
	}
}
