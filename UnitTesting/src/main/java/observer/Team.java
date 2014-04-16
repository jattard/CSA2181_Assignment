package main.java.observer;

import java.util.ArrayList;
import java.util.List;

public class Team implements Subject {

	private List<Observer> observers;
	private int[][] playerTrail;
    private boolean changed;
    
    public Team() {
        this.observers = new ArrayList<Observer>();
    }
    
    public List<Observer> getPlayers() {
    	return observers;
    }
    
    @Override
    public void register(Observer obj) {
        
    	if(obj == null) 
    		throw new NullPointerException("Can't register null player!");

        if(!observers.contains(obj)) observers.add(obj);
    }
    
    @Override
    public void notifyObservers() {
        List<Observer> observersLocal = null;

        if (!changed)
            return;
        
        observersLocal = new ArrayList<Observer>(this.observers);
        this.changed = false;
        
        for (Observer obj : observersLocal) {
        	obj.update();
        }
    }
 
    @Override
    public Object getUpdate(Observer obj) {
        return this.playerTrail;
    }
     
    //method to post message to the topic
    public void setTeamTrail(int[][] playerTrail){

        this.playerTrail = playerTrail;
        this.changed = true;
        notifyObservers();
    }
}
