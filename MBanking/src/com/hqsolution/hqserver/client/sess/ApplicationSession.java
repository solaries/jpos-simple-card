package com.hqsolution.hqserver.client.sess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationSession implements ISession{
	private static ApplicationSession instance;
	
	public static ApplicationSession getInstance(){
		if(instance == null){
			instance = new ApplicationSession();
		}
		return instance;
	}
	
	private Map<String,Object> session;
	private List<SessionListener> listeners;
	
	private ApplicationSession(){
		if(listeners == null){
			listeners = new ArrayList<SessionListener>();
			listeners.add(new DefaultSessionListener());
		}
	}

	private void init() {
		if(session == null){
			session = new HashMap<String, Object>();
			postCreated();
		}
	}
	
	private void postCreated(){
		if(listeners != null){
			for(SessionListener list : listeners){
				list.postCreated(session);
			}
		}
	}
	
	private void preDestroy(){
		if(listeners != null){
			for(SessionListener list : listeners){
				list.preDestroy(session);
			}
		}
	}
	
	public void addSessionLifeCycleListener(SessionListener listener) {
		
		listeners.add(listener);
	} 
	
	public void removeAllListener() {
		listeners.clear();
	}

	public void set(String key, Object value) {
		if(session == null){
			init();
		}
		session.put(key, value);
		
	}

	public Object get(String key) {
		return session.get(key);
	}

	public void destroy() {
		preDestroy();
		session = null;
		listeners = null;
	}
	
}
