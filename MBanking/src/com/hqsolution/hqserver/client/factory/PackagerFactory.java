package com.hqsolution.hqserver.client.factory;

import java.io.InputStream;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOPackager;

public class PackagerFactory {
    public static ISOPackager getPackager() {
        ISOPackager packager = null;
        try {
            String filename = "/iso87binary.xml";
            InputStream is = null;
            is = PackagerFactory.class.getResourceAsStream(filename);
            packager = new HQGenericPackager(is);
        } 
        catch (ISOException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return packager;
    }
}