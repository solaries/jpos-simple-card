package com.hqsolution.hqserver.app.log;

import org.jpos.util.Logger;
import org.jpos.util.NameRegistrar;

/**
 * This log is aim to create a wrapper log for HQ system.
 * @author HUNGPT
 *
 */
public class HQLogger extends Logger {

	private LogLevel level;

	public HQLogger() {
		super();
		level = LogLevel.INFO;
	}
	
	/**
     * @return logger instance with given name. Creates one if necessary
     * @see NameRegistrar
     */
    public synchronized static HQLogger getLogger (String name) {
        HQLogger l;
        try {
            l = (HQLogger) NameRegistrar.get ("logger."+name);
        } catch (NameRegistrar.NotFoundException e) {
            l = new HQLogger();
            l.setName (name);
        }
        return l;
    }

    public void setLevel(String level) {
    	setLevel(getLevel(level));
    }
    
	public void setLevel(LogLevel level) {
		this.level = level;
	}

	public LogLevel getLevel() {
		return level;
	}
	
	public LogLevel getLevel(String level) {
		LogLevel lv = LogLevel.INFO;
		
		if (level.equalsIgnoreCase("trace")) {
			return LogLevel.TRACE;
		}

		if (level.equalsIgnoreCase("debug")) {
			return LogLevel.DEBUG;
		}

		if (level.equalsIgnoreCase("info")) {
			return LogLevel.INFO;
		}

		if (level.equalsIgnoreCase("warn")) {
			return LogLevel.WARN;
		}

		if (level.equalsIgnoreCase("error")) {
			return LogLevel.ERROR;
		}
		
		if (level.equalsIgnoreCase("fatal")) {
			return LogLevel.FATAL;
		}
		
		return lv;
	}
	
	public boolean isTraceAble() {
		return this.getLevel().getWeight() <= 0;
	}
	
	public boolean isDebugAble() {
		return this.getLevel().getWeight() <= 1;
	}
	
	public boolean isInfoAble() {
		return this.getLevel().getWeight() <= 2;
	}
	
	public boolean isWarnAble() {
		return this.getLevel().getWeight() <=3;
	}
	
	public boolean isErrorAble() {
		return this.getLevel().getWeight() <=4;
	}
	
	public boolean isFatalAble() {
		return this.getLevel().getWeight() <=5;
	}
	
}
