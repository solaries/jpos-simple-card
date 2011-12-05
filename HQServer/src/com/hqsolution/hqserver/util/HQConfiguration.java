package com.hqsolution.hqserver.util;

import org.jpos.q2.QBeanSupport;
import org.jpos.util.NameRegistrar;

/**
 * This class aim to load all configuration properties of HQServer on Q2 container.
 * @author HUNGPT
 *
 */
public class HQConfiguration extends QBeanSupport {
	
	public static final String REGISTER_NAME = "HQConfiguration.cfg";
	
	public HQConfiguration(){
		super();
	}
	
	@Override
	protected void stopService() throws Exception {
		NameRegistrar.unregister(REGISTER_NAME);
		log.info("HQServer configuration stopped...");
	}

	/**
	 * Register configuration with NameRegistrar
	 * @see NameRegistrar
	 */
	public void setName(String name){
		super.setName(name);
		NameRegistrar.register(REGISTER_NAME, this);
	}
}
