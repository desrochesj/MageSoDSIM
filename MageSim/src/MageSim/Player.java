
package MageSim;

public class Player {
	//Variables
	int remainingMana;
	int intellect;
	int spellPower;
	double critChance;
	double critMultiplier;
	int hitChance;
	
	//Getters
	/**
     * @return remainingMana
     */
    public int getRemainingMana(){
        return remainingMana;
    }
    
    /**
     * @return intellect
     */
    public int getIntellect(){
        return intellect;
    }
    
    /**
     * @return spellPower
     */
    public int getSpellPower(){
        return spellPower;
    }
    
    /**
     * @return critChance
     */
    public double getCritChance(){
        return critChance;
    }
    
    /**
     * @return critMultiplier
     */
    public double getCritMultiplier(){
        return critMultiplier;
    }
    
    /**
     * @return hitChance
     */
    public int getHitChance(){
        return hitChance;
    }
	
	//Setters
    /**
     * @param remainingMana
     */
    public void setRemainingMana(int remainingMana){
        this.remainingMana = remainingMana;
    }
    
    /**
     * @param intellect
     */
    public void setIntellect(int intellect){
        this.intellect = intellect;
    }
    
    /**
     * @param spellPower
     */
    public void setSpellPower(int spellPower){
        this.spellPower = spellPower;
    }
    
    /**
     * @param critChance
     */
    public void setCritChance(double critChance){
        this.critChance = critChance;
    }
    
    /**
     * @param critMultiplier
     */
    public void setCritMultiplier(double critMultiplier){
        this.critMultiplier = critMultiplier;
    }
    
    /**
     * @param hitChance
     */
    public void setHitChance(int hitChance){
        this.hitChance = hitChance;
    }
	
	//Constructors
	/**
     * Generic Unloaded Constructor
     */
    public Player(){
        this.remainingMana = 0;
        this.intellect = 0;
        this.spellPower = 0;
        this.critChance = 0.0;
        this.critMultiplier = 0.0;
        this.hitChance = 0;
    }
    
    /**
     * Overloaded Constructor
     * @param remainingMana
     * @param intellect
     * @param spellPower
     * @param critChance
     * @param critMultiplier
     */
    public Player(int remainingMana, int intellect, int spellPower, double critChance, double critMultiplier, int hitChance) {
    	this.remainingMana = remainingMana;
    	this.intellect = intellect;
    	this.spellPower = spellPower;
    	this.critChance = critChance;
    	this.critMultiplier = critMultiplier;
    	this.hitChance = hitChance;
    }
    
    //Generate Random Integer Between 2 Values
    /*
     * @param min
     * @param max
     * @return random value between min and max
     */
    public int generateRandom(int min, int max) {
    	return (int) (Math.floor(Math.random() * (max - min + 1) ) + min);
    }
    
    //Possible Spells to Cast
    /*
     * Frostfire Bolt
     * @return Damage Dealt
     */
    public int castFrostFireBolt(){
    	if (generateRandom(0,100) > 94 + getHitChance()) { //Spell Misses
    		return 0;
    	}
    	if (generateRandom(0,100) <= getCritChance() * 100) { //Spell Crits
    		//TODO Ignite lmao
    		int damage = (int) ((generateRandom(513,598) + (getSpellPower() * .814)) * getCritMultiplier());
    		System.out.println("Frostfire Bolt CRIT did: " + damage);
    		return damage;
    		//return (int) ((generateRandom(513,598) + (getSpellPower() * .814)) * getCritMultiplier());
    	} else { //No Crit
    		int damage = (int) (generateRandom(513,598) + (getSpellPower() * .814));
    		System.out.println("Frostfire Bolt did: " + damage);
    		return damage;
    		//return (int) (generateRandom(513,598) + getSpellPower() * .814);
    	}
    }
}
