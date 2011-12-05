package com.hqsolution.hqserver.util;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

import com.hqsolution.hqserver.app.common.EntityType;
import com.hqsolution.hqserver.app.dto.FlexibleTask;
import com.hqsolution.hqserver.app.dto.HQAccount;
import com.hqsolution.hqserver.app.pack.ObjectUnPackMessage;

public class MessageHelper {

	public static String getProcessingCode(ISOMsg msg) {
		String result = "";
		try {
			byte[] data = (byte[]) msg.getValue(48);
			ObjectUnPackMessage unPackMessage = new ObjectUnPackMessage(data);
			Object obj = unPackMessage.unpackObject();
			System.out.println(unPackMessage.getObjectType());
			if(obj instanceof FlexibleTask){
				FlexibleTask flexTask = (FlexibleTask) unPackMessage.unpackObject();
				if (flexTask != null) {
					System.out.println(flexTask.getTaskCode());
					return flexTask.getTaskCode();
				}
			}
			

		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static HQAccount getHQAccount(ISOMsg msg) {
		try {
			byte[] data = (byte[]) msg.getValue(48);
			ObjectUnPackMessage unPackMessage = new ObjectUnPackMessage(data);
			Object obj = unPackMessage.unpackObject();
			if (obj instanceof FlexibleTask) {
				FlexibleTask flexTask = (FlexibleTask) unPackMessage
						.unpackObject();
				Object newObject = flexTask.getdata();
				System.out.println("============##" + newObject.getClass().getName());
				if (newObject instanceof HQAccount)
					return (HQAccount) newObject;
			}
		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
