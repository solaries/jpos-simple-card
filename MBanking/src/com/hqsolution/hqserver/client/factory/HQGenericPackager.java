package com.hqsolution.hqserver.client.factory;

import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jpos.iso.ISOException;
import org.jpos.iso.packager.GenericPackager;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class HQGenericPackager extends GenericPackager {

	public HQGenericPackager() throws ISOException {
		super();
	}

	/**
	 * Create a HQGenericPackager with the field descriptions from an XML File
	 * 
	 * @param filename
	 *            The XML field description file
	 */
	public HQGenericPackager(String filename) throws ISOException {
		super(filename);
	}

	/**
	 * Create a HQGenericPackager with the field descriptions from an XML
	 * InputStream
	 * 
	 * @param input
	 *            The XML field description InputStream
	 */
	public HQGenericPackager(InputStream input) throws ISOException {
		super(input);
	}

	protected XMLReader createXMLReader() throws SAXException {
		XMLReader reader = null;
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp;
		try {
			sp = spf.newSAXParser();
			reader = sp.getXMLReader();
			reader.setFeature("http://xml.org/sax/features/validation", true);
			GenericContentHandler handler = new GenericContentHandler();
			reader.setContentHandler(handler);
			reader.setErrorHandler(handler);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reader;
	}
}
