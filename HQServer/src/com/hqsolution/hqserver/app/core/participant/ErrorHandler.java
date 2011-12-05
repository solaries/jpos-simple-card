package com.hqsolution.hqserver.app.core.participant;

import java.io.Serializable;

import org.jpos.transaction.TransactionParticipant;

public class ErrorHandler implements TransactionParticipant {

	@Override
	public void abort(long arg0, Serializable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commit(long arg0, Serializable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int prepare(long arg0, Serializable arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
