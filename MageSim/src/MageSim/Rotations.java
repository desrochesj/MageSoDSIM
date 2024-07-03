package MageSim;

public class Rotations extends SpellList{
	//Timing Variables
	int remainingDuration;
	int busyDuration;
	int ffbConnect;
	int igniteStart;
	int igniteEnd;
	int igniteTick;
	int bombExplode;
	int bombTick;
	boolean hasCrit;
	boolean igniteActive;
	boolean livingBomb;
			
	//Damage Tracking Variables
	int currentDamage;
	int currentFFB;
	int currentBombTick;
	int currentBombExplosion;
	int currentIgnite;
	int totalFFB;
	int totalBombTick;
	int totalBombExplosion;
	int totalIgnite;
	int totalDamage;
	
	//Getters
	public int getRemainingDuration(){
        return remainingDuration;
    }
	public int getBusyDuration(){
        return busyDuration;
    }
	public int getFFBConnect(){
        return ffbConnect;
    }
	public int getIgniteStart(){
        return igniteStart;
    }
	public int getIgniteEnd(){
        return igniteEnd;
    }
	public int getIgniteTick(){
        return igniteTick;
    }
	public int getBombExplode(){
        return bombExplode;
    }
	public int getBombTick(){
        return bombTick;
    }
	public boolean getHasCrit(){
        return hasCrit;
    }
	public boolean getIgniteActive(){
        return igniteActive;
    }
	public boolean getLivingBomb(){
        return livingBomb;
    }
	public int getCurrentDamage(){
        return currentDamage;
    }
	public int getCurrentFFB(){
        return currentFFB;
    }
	public int getCurrentBombTick(){
        return currentBombTick;
    }
	public int getCurrentBombExplosion(){
        return currentBombExplosion;
    }
	public int getCurrentIgnite(){
        return currentIgnite;
    }
	public int getTotalFFB(){
        return totalFFB;
    }
	public int getTotalBombTick(){
        return totalBombTick;
    }
	public int getTotalBombExplosion(){
        return totalBombExplosion;
    }
	public int getTotalIgnite(){
        return totalIgnite;
    }
	public int getTotalDamage(){
        return totalDamage;
    }
	
	//Setters
	public void setRemainingDuration(int remainingDuration){
        this.remainingDuration = remainingDuration;
    }
	public void setBusyDuration(int busyDuration){
        this.busyDuration = busyDuration;
    }
	public void setFFBConnect(int ffbConnect){
        this.ffbConnect = ffbConnect;
    }
	public void setIgniteStart(int igniteStart){
        this.igniteStart = igniteStart;
    }
	public void setIgniteEnd(int igniteEnd){
        this.igniteEnd = igniteEnd;
    }
	public void setIgniteTick(int igniteTick){
        this.igniteTick = igniteTick;
    }
	public void setBombExplode(int bombExplode){
        this.bombExplode = bombExplode;
    }
	public void setBombTick(int bombTick){
        this.bombTick = bombTick;
    }
	public void setHasCrit(boolean hasCrit){
        this.hasCrit = hasCrit;
    }
	public void setIgniteActive(boolean igniteActive){
        this.igniteActive = igniteActive;
    }
	public void setLivingBomb(boolean livingBomb){
        this.livingBomb = livingBomb;
    }
	public void setCurrentDamage(int currentDamage){
        this.currentDamage = currentDamage;
    }
	public void setCurrentFFB(int currentFFB){
        this.currentFFB = currentFFB;
    }
	public void setCurrentBombTick(int currentBombTick){
        this.currentBombTick = currentBombTick;
    }
	public void setCurrentBombExplosion(int currentBombExplosion){
        this.currentBombExplosion = currentBombExplosion;
    }
	public void setCurrentIgnite(int currentIgnite){
        this.currentIgnite = currentIgnite;
    }
	public void setTotalFFB(int totalFFB){
        this.totalFFB = totalFFB;
    }
	public void setTotalBombTick(int totalBombTick){
        this.totalBombTick = totalBombTick;
    }
	public void setTotalBombExplosion(int totalBombExplosion){
        this.totalBombExplosion = totalBombExplosion;
    }
	public void setTotalIgnite(int totalIgnite){
        this.totalIgnite = totalIgnite;
    }
	public void setTotalDamage(int totalDamage){
        this.totalDamage = totalDamage;
    }
	
	//Constructors
	public Rotations(){
        this.remainingDuration = 0;
        this.busyDuration = 0;
        this.ffbConnect = 0;
        this.igniteStart = 0;
        this.igniteEnd = 0;
        this.igniteTick = 0;
        this.bombExplode = 0;
        this.bombTick = 0;
        this.hasCrit = false;
        this.igniteActive = false;
        this.livingBomb = false;
        this.currentDamage = 0;
        this.currentFFB = 0;
        this.currentBombTick = 0;
        this.currentBombExplosion = 0;
        this.currentIgnite = 0;
        this.totalFFB = 0;
        this.totalBombTick = 0;
        this.totalBombExplosion = 0;
        this.totalIgnite = 0;
        this.totalDamage = 0;
    }
	
	//Rotations
	/*
	 * Frostfire Bolt
	 * Just Cast FFB 4Head
	 */
	public int[] SimpleFFB(SpellList spell, int fightDuration) {		
		setRemainingDuration(fightDuration);
		
		while (getRemainingDuration() > 0) {
			//Direct Damage Effects
			//FrostFire Bolt
			if (getRemainingDuration() == getFFBConnect()) {
				if (spell.generateRandom(0,100) <= spell.getCritChance() * 100) {
					setHasCrit(true);
				}
				
				setCurrentFFB(spell.castFrostFireBolt(getHasCrit()));
				setTotalFFB(getTotalFFB() + getCurrentFFB());
				setCurrentDamage(getCurrentDamage() + getCurrentFFB());
			}
			
			//Periodic Damage Effects
			//Ignite
			if (getHasCrit()) {
				setIgniteStart(getRemainingDuration() - 20);
				setIgniteEnd(getRemainingDuration() - 40);
				
				if (getIgniteActive()) {
					setIgniteTick((int) Math.round(getCurrentDamage() * .2));
				} else {
					setIgniteTick((int) Math.round(getCurrentDamage() * .2));
					setIgniteActive(true);
				}
			}			
			
			if (getRemainingDuration() == getIgniteStart()) {
				setCurrentIgnite(getIgniteTick());
				setTotalIgnite(getTotalIgnite() + getCurrentIgnite());
				setCurrentDamage(getCurrentDamage() + getCurrentIgnite());			
				//System.out.println("Ignite did: " + currentDamage);
			}
			
			if (getRemainingDuration() == getIgniteEnd()) {
				setCurrentIgnite(getIgniteTick());
				setTotalIgnite(getTotalIgnite() + getCurrentIgnite());
				setCurrentDamage(getCurrentDamage() + getCurrentIgnite());		
				//System.out.println("Ignite did: " + currentDamage);		
				setIgniteActive(false);
			}
			
			//Spell Choice
			//Frostfire Bolt
			if (getBusyDuration() <= 0) { 
				setFFBConnect(getRemainingDuration() - 30);
				//System.out.println("Time Left + FFB Cast Start: " + remainingDuration);
				setBusyDuration(30);
			}
			
			//Tally Damage Dealt
			setTotalDamage(getTotalDamage() + getCurrentDamage());
			
			//Reset States
			setCurrentDamage(0);
			setHasCrit(false);
			
			//Decrement Timers
			setBusyDuration(getBusyDuration() - 1);
			setRemainingDuration(getRemainingDuration() - 1);
		}
		
		//Build and Load Array for Output
		int[] simValues = new int[5];
		simValues[0] = getTotalFFB();
		simValues[1] = getTotalBombTick();
		simValues[2] = getTotalBombExplosion();
		simValues[3] = getTotalIgnite();
		simValues[4] = getTotalDamage();
		
		return simValues;
	}

	/*
	 * Frostfire Bolt and Living Bomb
	 * Maintain LB and Cast FFB
	 */
	public int[] BombAndFFB(SpellList spell, int fightDuration) {	
		setRemainingDuration(fightDuration);
		
		while (getRemainingDuration() > 0) {
			//Direct Damage Effects
			//FrostFire Bolt
			if (getRemainingDuration() == getFFBConnect()) {
				if (spell.generateRandom(0,100) <= spell.getCritChance() * 100) {
					setHasCrit(true);
				}
				
				setCurrentFFB(spell.castFrostFireBolt(getHasCrit()));
				setTotalFFB(getTotalFFB() + getCurrentFFB());
				setCurrentDamage(getCurrentDamage() + getCurrentFFB());
			}
			
			//Living Bomb Explosion
			if (getRemainingDuration() == getBombExplode()) {
				if (spell.generateRandom(0, 100) <= spell.getCritChance() * 100) {
					setHasCrit(true);
				}
				
				setCurrentBombExplosion(spell.castLivingBomb(getHasCrit()));
				setTotalBombExplosion(getTotalBombExplosion() + getCurrentBombExplosion());
				setCurrentDamage(getCurrentDamage() + getCurrentBombExplosion());
				
				setLivingBomb(false);
			}
			
			//Periodic Damage Effects
			//Living Bomb Tick
			if (getRemainingDuration() == getBombTick()
					|| getRemainingDuration() == getBombTick() - 30
					|| getRemainingDuration() == getBombTick() - 60
					|| getRemainingDuration() == getBombTick() - 90) {
				setCurrentBombTick(spell.castLivingBombTick());
				setTotalBombTick(getTotalBombTick() + getCurrentBombTick());
				setCurrentDamage(getCurrentDamage() + getCurrentBombTick());
			}
			
			//Ignite
			if (getHasCrit()) {
				setIgniteStart(getRemainingDuration() - 20);
				setIgniteEnd(getRemainingDuration() - 40);
				
				if (getIgniteActive()) {
					setIgniteTick((int) Math.round(getCurrentDamage() * .2));
				} else {
					setIgniteTick((int) Math.round(getCurrentDamage() * .2));
					setIgniteActive(true);
				}
			}				
			if (getRemainingDuration() == getIgniteStart()) {
				setCurrentIgnite(getIgniteTick());
				setTotalIgnite(getTotalIgnite() + getCurrentIgnite());
				setCurrentDamage(getCurrentDamage() + getCurrentIgnite());			
				//System.out.println("Ignite did: " + currentDamage);
			}
			if (getRemainingDuration() == getIgniteEnd()) {
				setCurrentIgnite(getIgniteTick());
				setTotalIgnite(getTotalIgnite() + getCurrentIgnite());
				setCurrentDamage(getCurrentDamage() + getCurrentIgnite());		
				//System.out.println("Ignite did: " + currentDamage);		
				setIgniteActive(false);
			}
			
			//Spell Choice
			//Living Bomb
			if (getBusyDuration() <= 0 && !getLivingBomb()) {
				//Spell Misses
		    	if (spell.generateRandom(0,100) > 94 + spell.getHitChance()) {
		    		//System.out.println("Living Bomb Missed");
		    		setBusyDuration(15);
		    	} else {
		    		setBombExplode(getRemainingDuration() - 120);
		    		setBombTick(getRemainingDuration() - 30);					
					//System.out.println("Living Bomb Applied");
					//System.out.println("Time Left + Bomb Applied: " + remainingDuration);		    		
					setLivingBomb(true);
					setBusyDuration(15);
		    	}
			}
			
			//Frostfire Bolt
			if (getBusyDuration() <= 0) { 
				setFFBConnect(getRemainingDuration() - 30);
				//System.out.println("Time Left + FFB Cast Start: " + remainingDuration);
				setBusyDuration(30);
			}
			
			//Tally Damage Dealt
			setTotalDamage(getTotalDamage() + getCurrentDamage());
			
			//Reset States
			setCurrentDamage(0);
			setHasCrit(false);
			
			//Decrement Timers
			setBusyDuration(getBusyDuration() - 1);
			setRemainingDuration(getRemainingDuration() - 1);
		}
		
		//Build and Load Array for Output
		int[] simValues = new int[5];
		simValues[0] = getTotalFFB();
		simValues[1] = getTotalBombTick();
		simValues[2] = getTotalBombExplosion();
		simValues[3] = getTotalIgnite();
		simValues[4] = getTotalDamage();
		
		return simValues;
	}
}
