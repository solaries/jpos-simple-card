package com.hqsolution.hqserver.client.factory;

import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jpos.iso.ISOException;
import org.jpos.iso.packager.GenericPackager;
import org.xml.sax.InputSource;
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
		this();
		readFile(filename);
	}

	/**
	 * Create a HQGenericPackager with the field descriptions from an XML
	 * InputStream
	 * 
	 * @param input
	 *            The XML field description InputStream
	 */
	public HQGenericPackager(InputStream input) throws ISOException {
		this();
		readFile(input);
	}

	protected XMLReader createXMLReader() throws SAXException {
		XMLReader reader = null;
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp;
		try {
			sp = spf.newSAXParser();
			reader = sp.getXMLReader();
			GenericContentHandler handler = new GenericContentHandler();
			reader.setContentHandler(handler);
			reader.setErrorHandler(handler);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reader;
	}
	
	/**
     * Parse the field descriptions from an XML file.
     *
     * <pre>
     * Uses the sax parser specified by the system property 'sax.parser'
     * The default parser is org.apache.crimson.parser.XMLReaderImpl
     * </pre>
     * @param filename The XML field description file
     */
    public void readFile(String filename) throws ISOException
    {
        try {
            createXMLReader().parse(filename);  
        } 
        catch (Exception e) {
            throw new ISOException(e);
        }
    }
    
    /**
     * Parse the field descriptions from an XML InputStream.
     *
     * <pre>
     * Uses the sax parser specified by the system property 'sax.parser'
     * The default parser is org.apache.crimson.parser.XMLReaderImpl
     * </pre>
     * @param input The XML field description InputStream
     */
    public void readFile(InputStream input) throws ISOException
    {
        try {
            createXMLReader().parse(new InputSource(input));
        } 
        catch (Exception e) {
            throw new ISOException(e);
        }
    }
}
