package com.hqsolution.hqserver.client.factory;


import java.util.Date;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;

import com.hqsolution.hqserver.app.util.MessageUtil;

public class IsoMessageBuilder {

	public static IsoMessageBuilder createBuilder() {
		return new IsoMessageBuilder();
	}

	private ISOMsg isoMsg = new ISOMsg();

	public IsoMessageBuilder() {

	}
	
	public IsoMessageBuilder setMTI(String mti) {
		try {
			isoMsg.setMTI(mti);
		} catch (ISOException e) {

		}
		return this;
	}

	public IsoMessageBuilder setField11(Date date) {
		try {
			isoMsg.set(11, MessageUtil.dateToSixBytes(date));
		} catch (ISOException e) {

		}
		return this;
	}

	public IsoMessageBuilder setField3(String processionCode) {
		try {
			isoMsg.set(3, processionCode);
		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

	public IsoMessageBuilder setField48(byte[] processionCode) {
		try {
			isoMsg.set(48, processionCode);
		} catch (ISOException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	

	public IsoMessageBuilder rebuild(ISOMsg isoMsg) {
		this.isoMsg = isoMsg;
		return this;
	}
	
	public IsoMessageBuilder setPackage(ISOPackager isoPackager) {
		this.isoMsg.setPackager(isoPackager);
		return this;
	}

	public ISOMsg build() {
		return isoMsg;
	}

}
