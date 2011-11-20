package com.hqsolution.hqserver.client.factory;

import org.jpos.iso.ISOBasePackager;
import org.jpos.iso.ISOException;
import org.jpos.iso.packager.GenericPackager.GenericContentHandler;

public class HQGenericPackager extends ISOBasePackager {

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
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();
		reader.setFeature("http://xml.org/sax/features/validation", true);
		GenericContentHandler handler = new GenericContentHandler();
		reader.setContentHandler(handler);
		reader.setErrorHandler(handler);
		return reader;
	}
}
