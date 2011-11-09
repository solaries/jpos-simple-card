package com.hqsolution.hqserver.app.log;

import org.jpos.util.Log;
import org.jpos.util.NameRegistrar;
import org.jpos.util.NameRegistrar.NotFoundException;

/**
 * This class is aim to control logger level for log object
 * @author HUNGPT
 *
 */
public class HQLog extends Log {

	public HQLog() {
		super();
	}
	
	public HQLog(HQLogger logger, String realm) {
		this.logger = logger;
		this.realm = realm;
	}
	
	public static HQLog getLog (String logName, String realm) {
		HQLog log;
        try {
			log = (HQLog) NameRegistrar.get("log." + logName);
		} catch (NotFoundException e) {
			log = new HQLog(HQLogger.getLogger(logName), realm);
			NameRegistrar.register("log." + logName, log);
		}
		return log;
    }
	
	@Override
	public void debug(Object detail, Object obj) {
		if (logger instanceof HQLogger && ((HQLogger) logger).isDebugAble()) {
			super.debug(detail, obj);
		}
	}

	@Override
	public void debug(Object detail) {
		if (logger instanceof HQLogger && ((HQLogger) logger).isDebugAble()) {
			super.debug(detail);
		}
	}

	@Override
	public void error(Object detail, Object obj) {
		if (logger instanceof HQLogger && ((HQLogger) logger).isErrorAble()) {
			super.error(detail, obj);
		}
	}

	@Override
	public void error(Object detail) {
		if (logger instanceof HQLogger && ((HQLogger) logger).isErrorAble()) {
			super.error(detail);
		}
	}

	@Override
	public void fatal(Object detail, Object obj) {
		if (logger instanceof HQLogger && ((HQLogger) logger).isFatalAble()) {
			super.fatal(detail, obj);
		}
	}

	@Override
	public void fatal(Object detail) {
		if (logger instanceof HQLogger && ((HQLogger) logger).isFatalAble()) {
			super.fatal(detail);
		}
	}

	@Override
	public void info(Object detail, Object obj) {
		if (logger instanceof HQLogger && ((HQLogger) logger).isInfoAble()) {
			super.info(detail, obj);
		}
	}

	@Override
	public void info(Object detail) {
		if (logger instanceof HQLogger && ((HQLogger) logger).isInfoAble()) {
			super.info(detail);
		}
	}

	@Override
	public void trace(Object detail, Object obj) {
		if (logger instanceof HQLogger && ((HQLogger) logger).isTraceAble()) {
			super.trace(detail, obj);
		}
	}

	@Override
	public void trace(Object detail) {
		if (logger instanceof HQLogger && ((HQLogger) logger).isTraceAble()) {
			super.trace(detail);
		}
	}

	@Override
	public void warn(Object detail, Object obj) {
		if (logger instanceof HQLogger && ((HQLogger) logger).isWarnAble()) {
			super.warn(detail, obj);
		}
	}

	@Override
	public void warn(Object detail) {
		if (logger instanceof HQLogger && ((HQLogger) logger).isWarnAble()) {
			super.warn(detail);
		}
	}

}
