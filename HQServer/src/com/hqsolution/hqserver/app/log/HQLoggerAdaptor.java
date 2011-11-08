package com.hqsolution.hqserver.app.log;

import java.util.Iterator;

import org.jdom.Element;
import org.jpos.core.Configurable;
import org.jpos.core.ConfigurationException;
import org.jpos.q2.QBeanSupport;
import org.jpos.q2.QFactory;
import org.jpos.util.LogListener;

public class HQLoggerAdaptor extends QBeanSupport {

	HQLogger logger;

	protected void initService() {
		logger = (HQLogger) HQLogger.getLogger(getName());
		String logLevel = cfg.get("log-level");
		logger.setLevel(logLevel);
	}

	protected void startService() throws ConfigurationException {
		logger.removeAllListeners();
		Iterator<?> iter = getPersist().getChildren("log-listener").iterator();
		while (iter.hasNext())
			addListener((Element) iter.next());
	}

	protected void stopService() {
		logger.removeAllListeners();
	}

	protected void destroyService() {
		// we don't destroy (that would unregister the logger from the
		// NameRegistrar) because other components might have references
		// to this logger.
		//
		// logger.destroy ();
	}

	private void addListener(Element e) throws ConfigurationException {
		QFactory factory = getServer().getFactory();
		String clazz = e.getAttributeValue("class");
		LogListener listener = (LogListener) factory.newInstance(clazz);
		if (listener instanceof Configurable) {
			try {
				((Configurable) listener).setConfiguration(factory
						.getConfiguration(e));
			} catch (ConfigurationException ex) {
				throw new ConfigurationException(ex);
			}
		}

		logger.addListener(listener);
	}

}
