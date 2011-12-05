package com.hqsolution.hqserver.app.core.participant;

import java.io.Serializable;
import java.sql.SQLException;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

import com.hqsolution.hqserver.app.common.DatabaseConnection;
import com.hqsolution.hqserver.app.dao.DbDao;
import com.hqsolution.hqserver.app.dao.factory.DbDaoFactory;
import com.hqsolution.hqserver.app.dao.idao.IAccount;
import com.hqsolution.hqserver.app.dto.HQAccount;
import com.hqsolution.hqserver.util.MessageHelper;
import com.hqsolution.hqserver.util.SQLResult;
import com.hqsolution.hqserver.util.SystemConstant;

/**
 * Check All Information about card include existing card, expire card and activated card
 * @author HUNGPT
 *
 */
public class AddAccount implements TransactionParticipant {

	@Override
	public void abort(long id, Serializable serializeable) {
		System.out.println(this.getClass().getName() + " abort");
	}

	@Override
	public void commit(long id, Serializable serialieable) {
		System.out.println(this.getClass().getName() + " commit");
		/** get context from space **/
		Context ctx = (Context)serialieable;
		
		/** Get connection from context **/
		DatabaseConnection con = (DatabaseConnection)ctx.get(SystemConstant.CONNECTION);
		if(con == null){
			ctx.put(SystemConstant.RC, "15");
		}else{
			try {
				con.commit();
			} catch (SQLException e) {
				ctx.put(SystemConstant.RC, "16");
				e.printStackTrace();
			}
		}
	}

	@Override
	public int prepare(long id, Serializable serializeable) {
		
		System.out.println(this.getClass().getName() + " prepare");
		/** get context from space **/
		Context ctx = (Context)serializeable;
		
		/** get message from context **/
		ISOMsg msg = (ISOMsg)ctx.get(SystemConstant.REQUEST);
		msg.dump(System.out, "");
		try {
			System.out.println(ISOUtil.hexString(msg.pack()));
		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/** Get connection from context **/
		DatabaseConnection con = (DatabaseConnection)ctx.get(SystemConstant.CONNECTION);
		if(con == null){
			ctx.put(SystemConstant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}
		
		if(msg != null) {
			HQAccount account = MessageHelper.getHQAccount(msg);
			if(account == null){
				System.out.println("Account is null");
			}
			DbDao dao = DbDaoFactory.getInstances();
			IAccount accountDao = dao.getAccount();
			String result = accountDao.createAccount(con, account);
			if(result.equals(SQLResult.FAIL)){
				ctx.put(SystemConstant.RC, "12");
				return ABORTED | READONLY | NO_JOIN;
			}
			
		}else {
			ctx.put(SystemConstant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}
		
		return PREPARED | NO_JOIN ;
	}

}
