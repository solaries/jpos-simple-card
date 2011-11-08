package com.hqsolution.hqserver.app.log;

import org.jpos.util.NameRegistrar;
import org.jpos.util.NameRegistrar.NotFoundException;

import com.hqsolution.hqserver.util.HQConfiguration;
import com.hqsolution.hqserver.util.SystemConstant;

public class LogFactory {

	public static HQLog getTimmingLog() {
		HQLog log = null;
		HQConfiguration cfg;
		String logName = "";
		String realm = "";
		try {
			cfg = (HQConfiguration) NameRegistrar.get(SystemConstant.SYSTEM_CONF);
			logName = realm = cfg.getConfiguration().get("timming-log");
		} catch (NotFoundException e2) {
			e2.printStackTrace();
		}
		
		try {
		log = (HQLog)NameRegistrar.get(logName);
		}catch (NotFoundException e) {
			log = HQLog.getLog(logName, realm);
			NameRegistrar.register("log." + logName, log);
		}
		return log;
	}
}
