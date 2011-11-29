package com.hqsolution.hqserver.client.sess;

public interface ISession {
	void set(String key, Object value);
	Object get(String key);
	void addSessionLifeCycleListener(SessionListener listener);
	void removeAllListener();
}
