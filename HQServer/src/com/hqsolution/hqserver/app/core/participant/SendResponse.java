package com.hqsolution.hqserver.app.core.participant;

import java.io.IOException;
import java.io.Serializable;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.ISOFilter.VetoException;
import org.jpos.transaction.AbortParticipant;
import org.jpos.transaction.Context;

import com.hqsolution.hqserver.util.SystemConstant;

/**
 * Send transaction information to client.
 * 
 * @author HUNGPT
 * 
 */
public class SendResponse implements AbortParticipant {

	@Override
	public void abort(long id, Serializable context) {
	}

	@Override
	public int prepareForAbort(long id, Serializable context) {
		System.out.println(this.getClass().getName() + " prepareForAbort");
		sendResponse(id, (Context) context);
		return ABORTED | READONLY | NO_JOIN;
	}

	@Override
	public void commit(long id, Serializable context) {
		System.out.println(this.getClass().getName() + " commit");
		sendResponse(id, (Context) context);
	}

	@Override
	public int prepare(long id, Serializable context) {

		System.out.println(this.getClass().getName() + " prepare");
		// get context from space
		Context ctx = (Context) context;

		// get source or sender from context
		ISOSource source = (ISOSource) ctx.get(SystemConstant.SOURCE);
		if (source == null || !source.isConnected()) {
			return ABORTED | READONLY | NO_JOIN;
		}

		return PREPARED | READONLY;
	}

	/**
	 * Send response to client.
	 * @param id Identify of transaction
	 * @param ctx Context of transaction
	 */
	private void sendResponse(long id,Context ctx) {
		
		ISOSource source = (ISOSource) ctx.get(SystemConstant.SOURCE);
		if (source == null || !source.isConnected()) {
			return; // too late to send
		}

		try {
			ISOMsg msg = (ISOMsg) ctx.get(SystemConstant.REQUEST);
			
			//Get response code 
			String rc = (String)ctx.get(SystemConstant.RC);
			
			if (source != null && source.isConnected() && msg != null) {
				
				//Create new message response
				ISOMsg msgResponse = new ISOMsg();
				msgResponse.set(0,"0210");
				msgResponse.set(3,(String)msg.getValue(3));
				msgResponse.set(11,(String)msg.getValue(11));
				msgResponse.set(48,msg.getComponent(48).getBytes());
				if(rc == null || "00".equals(rc)) {
					msgResponse.set(39, "00");
				}
				else if(rc != null) {
					int error = Integer.parseInt(rc);
					
				}
				
				source.send(msgResponse);
			}
		} catch (VetoException e) {
			e.printStackTrace();
		} catch (ISOException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
