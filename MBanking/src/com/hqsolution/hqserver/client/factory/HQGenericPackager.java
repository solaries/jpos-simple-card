package com.hqsolution.hqserver.client.factory;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jpos.iso.ISOBitMap;
import org.jpos.iso.ISOBitMapPackager;
import org.jpos.iso.ISOComponent;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOFieldPackager;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOMsgFieldPackager;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.packager.GenericPackager;
import org.jpos.util.LogEvent;
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
	 * 
	 * @param filename
	 *            The XML field description file
	 */
	public void readFile(String filename) throws ISOException {
		try {
			createXMLReader().parse(filename);
		} catch (Exception e) {
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
	 * 
	 * @param input
	 *            The XML field description InputStream
	 */
	public void readFile(InputStream input) throws ISOException {
		try {
			createXMLReader().parse(new InputSource(input));
		} catch (Exception e) {
			throw new ISOException(e);
		}
	}

	/**
	 * @param m
	 *            the Component to pack
	 * @return Message image
	 * @exception ISOException
	 */
	public byte[] pack(ISOComponent m) throws ISOException {
		LogEvent evt = new LogEvent(this, "pack");
		try {
			if (m.getComposite() != m)
				throw new ISOException("Can't call packager on non Composite");

			ISOComponent c;
			ArrayList v = new ArrayList(128);
			Map fields = m.getChildren();
			int len = 0;
			int first = getFirstField();

			c = (ISOComponent) fields.get(0);
			byte[] b;

			if (m instanceof ISOMsg && headerLength > 0) {
				byte[] h = ((ISOMsg) m).getHeader();
				if (h != null)
					len += h.length;
			}

			if (first > 0 && c != null) {
				b = fld[0].pack(c);
				len += b.length;
				v.add(b);
			}

			if (emitBitMap()) {
				// BITMAP (-1 in HashTable)
				c = (ISOComponent) fields.get(-1);
				b = getBitMapfieldPackager().pack(c);
				len += b.length;
				v.add(b);
			}

			// if Field 1 is a BitMap then we are packing an
			// ISO-8583 message so next field is fld#2.
			// else we are packing an ANSI X9.2 message, first field is 1
			int tmpMaxField = Math.min(m.getMaxField(), 128);

			for (int i = first; i <= tmpMaxField; i++) {
				if ((c = (ISOComponent) fields.get(i)) != null) {
					try {
						ISOFieldPackager fp = fld[i];
						if (fp == null)
							throw new ISOException("null field " + i
									+ " packager");
						b = fp.pack(c);
						len += b.length;
						v.add(b);
					} catch (ISOException e) {
						System.out.println("error packing field " + i);
						System.out.println(c);
						System.out.println(e);
						throw e;
					}
				}
			}

			if (m.getMaxField() > 128 && fld.length > 128) {
				for (int i = 1; i <= 64; i++) {
					if ((c = (ISOComponent) fields.get(i + 128)) != null) {
						try {
							b = fld[i + 128].pack(c);
							len += b.length;
							v.add(b);
						} catch (ISOException e) {
							System.out.println("error packing field "
									+ (i + 128));
							System.out.println(c);
							System.out.println(e);
							throw e;
						}
					}
				}
			}

			int k = 0;
			byte[] d = new byte[len];

			// if ISOMsg insert header
			if (m instanceof ISOMsg && headerLength > 0) {
				byte[] h = ((ISOMsg) m).getHeader();
				if (h != null)
					for (int j = 0; j < h.length; j++)
						d[k++] = h[j];
			}

			for (int i = 0; i < v.size(); i++) {
				b = (byte[]) v.get(i);
				for (int j = 0; j < b.length; j++)
					d[k++] = b[j];
			}

			System.out.println(ISOUtil.hexString(d));
			return d;
		} catch (ISOException e) {
			System.out.println(e);
			throw e;
		}
	}

	/**
	 * @param m
	 *            the Container of this message
	 * @param b
	 *            ISO message image
	 * @return consumed bytes
	 * @exception ISOException
	 */
	public int unpack(ISOComponent m, byte[] b) throws ISOException {
		System.out.println(this.toString() + "unpack");
		int consumed = 0;

		try {
			if (m.getComposite() != m)
				throw new ISOException("Can't call packager on non Composite");

			System.out.println(ISOUtil.hexString(b));

			// if ISOMsg and headerLength defined
			if (m instanceof ISOMsg /* && ((ISOMsg) m).getHeader()==null */
					&& headerLength > 0) {
				byte[] h = new byte[headerLength];
				System.arraycopy(b, 0, h, 0, headerLength);
				((ISOMsg) m).setHeader(h);
				consumed += headerLength;
			}

			if (!(fld[0] == null) && !(fld[0] instanceof ISOBitMapPackager)) {
				ISOComponent mti = fld[0].createComponent(0);
				consumed += fld[0].unpack(mti, b, consumed);
				m.set(mti);
			}
			BitSet bmap = null;
			int maxField = fld.length;
			if (emitBitMap()) {
				ISOBitMap bitmap = new ISOBitMap(-1);
				consumed += getBitMapfieldPackager()
						.unpack(bitmap, b, consumed);
				bmap = (BitSet) bitmap.getValue();
				System.out.println("<bitmap>" + bmap.toString() + "</bitmap>");
				m.set(bitmap);
				maxField = Math.min(maxField, bmap.size());
			}
			for (int i = getFirstField(); i < maxField; i++) {
				try {
					if (bmap == null && fld[i] == null)
						continue;
					if (maxField > 128 && i == 65)
						continue; // ignore extended bitmap

					if (bmap == null || bmap.get(i)) {
						if (fld[i] == null)
							throw new ISOException("field packager '" + i
									+ "' is null");

						ISOComponent c = fld[i].createComponent(i);
						consumed += fld[i].unpack(c, b, consumed);

						System.out.println("<unpack fld=\"" + i
								+ "\" packager=\""
								+ fld[i].getClass().getName() + "\">");
						if (c.getValue() instanceof ISOMsg)
							System.out.println(c.getValue());
						else if (c.getValue() instanceof byte[]) {
							System.out.println("  <value type='binary'>"
									+ ISOUtil.hexString((byte[]) c.getValue())
									+ "</value>");
						} else {
							System.out.println("  <value>" + c.getValue()
									+ "</value>");
						}
						System.out.println("</unpack>");
						m.set(c);
					}
				} catch (ISOException e) {
					System.out.println("error unpacking field " + i
							+ " consumed=" + consumed);
					System.out.println(e);
					// jPOS-3
					e = new ISOException(String.format(
							"%s (%s) unpacking field=%d, consumed=%d",
							e.getMessage(), e.getNested().toString(), i,
							consumed));
					throw e;
				}
			}
			if (b.length != consumed) {
				System.out.println("WARNING: unpack len=" + b.length
						+ " consumed=" + consumed);
			}
			return consumed;
		} catch (ISOException e) {
			System.out.println(e);
			throw e;
		} catch (Exception e) {
			System.out.println(e);
			throw new ISOException(e.getMessage() + " consumed=" + consumed);
		}
	}

	public void unpack(ISOComponent m, InputStream in) throws IOException,
			ISOException {
		LogEvent evt = new LogEvent(this, "unpack");
		try {
			if (m.getComposite() != m)
				throw new ISOException("Can't call packager on non Composite");

			// if ISOMsg and headerLength defined
			if (m instanceof ISOMsg && ((ISOMsg) m).getHeader() == null
					&& headerLength > 0) {
				byte[] h = new byte[headerLength];
				in.read(h, 0, headerLength);
				((ISOMsg) m).setHeader(h);
			}

			if (!(fld[0] instanceof ISOMsgFieldPackager)
					&& !(fld[0] instanceof ISOBitMapPackager)) {
				ISOComponent mti = fld[0].createComponent(0);
				fld[0].unpack(mti, in);
				m.set(mti);
			}
			BitSet bmap = null;
			int maxField = fld.length;
			if (emitBitMap()) {
				ISOBitMap bitmap = new ISOBitMap(-1);
				getBitMapfieldPackager().unpack(bitmap, in);
				bmap = (BitSet) bitmap.getValue();

				System.out.println("<bitmap>" + bmap.toString() + "</bitmap>");
				m.set(bitmap);
				maxField = Math.min(maxField, bmap.size());
			}

			for (int i = getFirstField(); i < maxField; i++) {
				if (bmap == null && fld[i] == null)
					continue;

				if (bmap == null || bmap.get(i)) {
					if (fld[i] == null)
						throw new ISOException("field packager '" + i
								+ "' is null");

					ISOComponent c = fld[i].createComponent(i);
					fld[i].unpack(c, in);

					System.out.println("<unpack fld=\"" + i + "\" packager=\""
							+ fld[i].getClass().getName() + "\">");
					if (c.getValue() instanceof ISOMsg)
						System.out.println(c.getValue());
					else
						System.out.println("  <value>"
								+ c.getValue().toString() + "</value>");
					System.out.println("</unpack>");

					m.set(c);
				}
			}
			if (bmap != null && bmap.get(65) && fld.length > 128
					&& fld[65] instanceof ISOBitMapPackager) {
				bmap = (BitSet) ((ISOComponent) m.getChildren().get(65))
						.getValue();
				for (int i = 1; i < 64; i++) {
					if (bmap == null || bmap.get(i)) {
						ISOComponent c = fld[i + 128].createComponent(i);
						fld[i + 128].unpack(c, in);

						System.out.println("<unpack fld=\"" + i + 128
								+ "\" packager=\""
								+ fld[i + 128].getClass().getName() + "\">");
						System.out.println("  <value>"
								+ c.getValue().toString() + "</value>");
						System.out.println("</unpack>");

						m.set(c);
					}
				}
			}
		} catch (ISOException e) {
			System.out.println(e);
			throw e;
		} catch (EOFException e) {
			throw e;
		} catch (Exception e) {
			System.out.println(e);
			throw new ISOException(e);
		}
	}
}
