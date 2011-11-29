package com.hqsolution.hqserver.client.sess;

import java.util.Map;

public interface SessionListener {
	void postCreated(Map<String, Object> eventdata);
	void preDestroy(Map<String, Object> eventdata);
}
