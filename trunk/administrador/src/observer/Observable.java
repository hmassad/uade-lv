package observer;

import java.util.*;

public abstract class Observable {
	
	private ArrayList<Observer> observers;
	
	public Observable()
	{
		observers = new ArrayList<Observer>();
	}

	public void addObserver(Observer observer)
	{
		observers.add(observer);
	}
	
	public void removeObserver(Observer observer)
	{
		observers.remove(observer);
	}
	
	public void notifyObservers(Object o)
	{
		for(Iterator<Observer> i = observers.iterator();i.hasNext();)
			i.next().update(o, this);
	}
}
