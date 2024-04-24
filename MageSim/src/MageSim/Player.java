
package MageSim;

public class Player {
	//Variables
	int remainingMana;
	int intellect;
	int spellPower;
	double critChance;
	double critMultiplier;
	
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
    public double getCritMutliplier(){
        return critMultiplier;
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
    }
    
    /**
     * Overloaded Constructor
     * @param remainingMana
     * @param intellect
     * @param spellPower
     * @param critChance
     * @param critMultiplier
     */
    public Player(int remainingMana, int intellect, int spellPower, double critChance, double critMultiplier) {
    	this.remainingMana = remainingMana;
    	this.intellect = intellect;
    	this.spellPower = spellPower;
    	this.critChance = critChance;
    	this.critMultiplier = critMultiplier;
    }
    
    public void castFrostFireBolt
}
