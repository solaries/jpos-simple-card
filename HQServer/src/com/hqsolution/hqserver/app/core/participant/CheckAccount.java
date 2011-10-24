package com.hqsolution.hqserver.app.core.participant;

import java.io.Serializable;

import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

import com.hqsolution.hqserver.util.SystemConstant;

/**
 * Check All Information about card include existing card, expire card and activated card
 * @author HUNGPT
 *
 */
public class CheckAccount implements TransactionParticipant {

	@Override
	public void abort(long id, Serializable serializeable) {
		System.out.println(this.getClass().getName() + " abort");
	}

	@Override
	public void commit(long id, Serializable serialieable) {
		System.out.println(this.getClass().getName() + " commit");
	}

	@Override
	public int prepare(long id, Serializable serializeable) {
		
		System.out.println(this.getClass().getName() + " prepare");
		/** get context from space **/
		Context ctx = (Context)serializeable;
		
		/** get message from context **/
		ISOMsg msg = (ISOMsg)ctx.get(SystemConstant.REQUEST);
		msg.dump(System.out, "");
		
		/*
		/** Get connection from context 
		Connection con = (Connection)ctx.get(SystemConstant.CONN);
		if(con == null){
			ctx.put(Constant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}
		
		if(msg != null) {
			String cardNumber = MessageHelper.getCardId(msg);
			int result = JPOS_CardBUS.checkCard(cardNumber,con);
			if(result == 0) {
				ctx.put(Constant.RC, "14");
				return ABORTED | READONLY | NO_JOIN;
			}else
			{
				result = JPOS_CardBUS.checkExpire(cardNumber,con);
				if(result == 1) {
					ctx.put(Constant.RC, "54");
					return ABORTED | READONLY | NO_JOIN;
				}
				result = JPOS_CardBUS.checkActivatedCard(cardNumber, con);
				if(result == 0){
					ctx.put(Constant.RC, "93");
					return ABORTED | READONLY | NO_JOIN;
				}
			}
		}else {
			ctx.put(Constant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}*/
		return PREPARED | NO_JOIN ;
	}

}
