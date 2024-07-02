
package MageSim;

public class FFBDeepFire {
	//Variables
	int remainingMana;
	int intellect;
	int spellPower;
	double critChance;
	double critMultiplier;
	int hitChance;
	
	//Getters
    public int getRemainingMana(){
        return remainingMana;
    }
    public int getIntellect(){
        return intellect;
    }
    public int getSpellPower(){
        return spellPower;
    }
    public double getCritChance(){
        return critChance;
    }
    public double getCritMultiplier(){
        return critMultiplier;
    }
    public int getHitChance(){
        return hitChance;
    }
	
	//Setters
    public void setRemainingMana(int remainingMana){
        this.remainingMana = remainingMana;
    }
    public void setIntellect(int intellect){
        this.intellect = intellect;
    }
    public void setSpellPower(int spellPower){
        this.spellPower = spellPower;
    }
    public void setCritChance(double critChance){
        this.critChance = critChance;
    }
    public void setCritMultiplier(double critMultiplier){
        this.critMultiplier = critMultiplier;
    }
    public void setHitChance(int hitChance){
        this.hitChance = hitChance;
    }
	
	//Constructors
    public FFBDeepFire(){
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
    public FFBDeepFire(int remainingMana, int intellect, int spellPower, double critChance, double critMultiplier, int hitChance) {
    	this.remainingMana = remainingMana;
    	this.intellect = intellect;
    	this.spellPower = spellPower;
    	this.critChance = critChance;
    	this.critMultiplier = critMultiplier;
    	this.hitChance = hitChance;
    }
    
    //Generate Random Integer Between 2 Values
    /*
     * @param min number
     * @param max number
     * @return random value between min and max
     */
    public int generateRandom(int min, int max) {
    	return (int) (Math.floor(Math.random() * (max - min + 1) ) + min);
    }
    
    //Spells
    /*
     * Frostfire Bolt
     * @param Spell Crit Status
     * @return Damage Dealt by Spell
     */
    public int castFrostFireBolt(boolean hasCrit){
    	//Spell Misses
    	if (generateRandom(0,100) > 94 + getHitChance()) {
    		//System.out.println("Frostfire Bolt Missed");
    		return 0;
    	}
    	
    	if (hasCrit) {
    		int damage = (int) ((generateRandom(713,831) + (getSpellPower() * .814)) * getCritMultiplier());
    		//System.out.println("Frostfire Bolt did: *" + damage + "*");
    		return damage;
    	} else {
    		int damage = (int) (generateRandom(713,831) + (getSpellPower() * .814));
    		//System.out.println("Frostfire Bolt did: " + damage);
    		return damage;
    	}
    }
    
    /*
     * Living Bomb Final Explosion
     * @param Spell Crit Status
     * @return Damage Dealt by Spell
     */
    public int castLivingBomb(boolean hasCrit) {
    	if (hasCrit) {
    		int damage = (int) (297.226 + (getSpellPower() * .4286) * getCritMultiplier());
    		//System.out.println("Living Bomb Explosion did: *" + damage + "*");
    		return damage;
    	} else {
    		int damage = (int) (297.226 + (getSpellPower() * .4286));
    		//System.out.println("Living Bomb Explosion did: " + damage);
    		return damage;
    	}
    }
    
    /*
     * Living Bomb Tick
     * @return Living Bomb Tick Damage
     */
    public int castLivingBombTick() {
    	int damage = (int) (590.976 + (getSpellPower() * .8));
		//System.out.println("Living Bomb Tick did: " + damage / 4);
		return damage / 4;
    }
}
