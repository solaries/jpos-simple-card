package com.hqsolution.hqserver.client.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.packager.GenericPackager;

public class PackagerFactory {
    public static ISOPackager getPackager() {
        ISOPackager packager = null;
        try {
            String filename = "./cfg/iso87binary.xml";
            InputStream is = null;
            is = PackagerFactory.class.getResourceAsStream(filename);
            packager = new GenericPackager(is);
        } 
        catch (ISOException e) {
            e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}
        return packager;
    }
}