package com.hqsolution.hqserver.app.core;

import java.io.Serializable;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.GroupSelector;

import com.hqsolution.hqserver.util.MessageHelper;
import com.hqsolution.hqserver.util.SystemConstant;

/**
 * 
 * @author HUNGPT Switch message to accurate business by field no3 of message.
 */
public class Switch implements GroupSelector, Configurable {

	Configuration cfg = null;

	@Override
	public String select(long arg0, Serializable context) {

		try {
			/** Get context from space **/
			Context ctx = (Context) context;

			/** Get message from context **/
			ISOMsg msg = (ISOMsg) ctx.get(SystemConstant.REQUEST);

			String groups = "";

			String processingCode = MessageHelper.getProcessingCode(msg);
			
			/** Get group String from task code of message. **/ 
			if (processingCode == null || processingCode == "") {
				groups = cfg.get(SystemConstant.ERROR_FLOW);
			}else{
				groups = cfg.get(processingCode);
			}
			
			return groups;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void abort(long arg0, Serializable arg1) { }

	@Override
	public void commit(long arg0, Serializable arg1) { }

	@Override
	public int prepare(long arg0, Serializable arg1) {
		return PREPARED | READONLY | NO_JOIN;
	}

	@Override
	public void setConfiguration(Configuration cfg)
			throws ConfigurationException {
		this.cfg = cfg;
	}

}
