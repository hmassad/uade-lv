package remoteObserver;

import java.util.ArrayList;

public class Observable {

	private ArrayList<Observer> observers = new ArrayList<Observer>();

	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	public void notifyObservers(EventoObservable roe) {
		for (Observer ro : observers) {
			try {
				ro.update(this, roe);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}