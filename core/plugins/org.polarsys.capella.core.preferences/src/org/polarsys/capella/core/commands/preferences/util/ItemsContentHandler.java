/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.commands.preferences.util;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import org.polarsys.capella.core.commands.preferences.internalization.l10n.CustomPreferencesStatusCodes;

/**
 * A SAX content handler for parsing the <tt>&lt;constraints&gt;</tt> XML.
 * 
 */
public class ItemsContentHandler extends DefaultHandler {
	
	/**
	 * Processing instruction parameter indicating the version of the
	 * constraint markup language.  The default (and currently only) version
	 * is 1.0.
	 */ 
	static final String VERSION_PARAMETER = "version"; //$NON-NLS-1$
	
	/**
	 * Processing instruction parameter indicating the translated
	 * <tt>.properties</tt> file for localization (a.k.a. "national language"
	 * handling) of strings in the XML.
	 */ 
	static final String NL_PARAMETER = "nl"; //$NON-NLS-1$
	
	
	/** Used to cache resource bundles for localized constraint strings. */
	private static final ResourceBundleCache resourceBundleCache = 	new ResourceBundleCache();
	
	/** The extension which defines the constraints. */
	private final IExtension extension;
	
	/** Base URL on which relative URLs are constructed. */
	private final URL baseUrl;
	
	/** A stack of XML elements. */
	private final Stack stack = new Stack();

	/**
	 * The resulting configuration element that I construct.
	 */
	private IConfigurationElement resultElement;
	
	/**
	 * My resource bundle for localization, if any is specified by a
	 * <tt>&lt;?emf-validation?&gt;</tt> processing instruction.
	 */
	private ResourceBundle resourceBundle;

	/**
	 * Maintains a stack of XML elements as they are encountered by the SAX
	 * parser.  Elements are popped onto and off of the stack as the SAX parser
	 * sees them come and go.  Whenever an element is on the top of the stack,
	 * it can have more data added to it.
	 * 
	 */
	private class Stack {
		private final List<XmlConfigurationElement> contents =
			new java.util.ArrayList<XmlConfigurationElement>();
		private final List<StringBuffer> bodies = new java.util.ArrayList<StringBuffer>();
		private int lastIndex = -1;
		
		/**
		 * Queries whether the stack is empty.
		 * 
		 * @return <CODE>true</CODE> if the stack has no elements;
		 *     <CODE>false</CODE>, otherwise
		 */
		public boolean isEmpty() {
			return contents.isEmpty();
		}

		/**
		 * Pushes a new element onto the stack.
		 * 
		 * @param element the new top element
		 */
		public void push(XmlConfigurationElement element) {
			assert element != null;

			contents.add(element);
			bodies.add(new StringBuffer(32));
			
			lastIndex++;
		}

		/**
		 * Pops an element from the top of the stack.  At this point, the
		 * body of the element which has been accumulating is assigned to it.
		 * 
		 * @return the former top element.
		 * @throws SAXException if the stack is empty (in which case it cannot
		 *     be popped)
		 */
		public XmlConfigurationElement pop() throws SAXException {
			if (isEmpty()) {
				SAXException se = new SAXException(
					CustomPreferencesStatusCodes.XML_CANNOT_POP_STACK_MSG);
				
				se.printStackTrace();
				
				throw se;
			}

			XmlConfigurationElement result = contents.get(lastIndex);
			
			result.setValue(localize(getBody().toString().trim()));

			contents.remove(lastIndex);
			bodies.remove(lastIndex);

			lastIndex--;
			
			return result;
		}

		/**
		 * Obtains the top element of the stack.
		 * 
		 * @return the top element
		 * @throws SAXException if the stack is empty (in which case it cannot
		 *     be peeked)
		 */
		public XmlConfigurationElement peek() throws SAXException {
			if (isEmpty()) {
				SAXException se = new SAXException(
					CustomPreferencesStatusCodes.XML_CANNOT_PEEK_STACK_MSG);
				
				se.printStackTrace();
				
				throw se;
			}

			return contents.get(lastIndex);
		}

		/**
		 * Gets the string buffer which is accumulating the body of the current
		 * top element of the stack.
		 * 
		 * @return the top element's body
		 * @throws SAXException if the stack is empty (in which case there is
		 *     no body)
		 */
		public StringBuffer getBody() throws SAXException {
			if (isEmpty()) {
				SAXException se = new SAXException(
					CustomPreferencesStatusCodes.XML_NO_STACK_BODY_MSG);
				
				se.printStackTrace();
				
				throw se;
			}

			return bodies.get(lastIndex);
		}
	}
	
	/**
	 * Helper class that caches resource bundles by host OSGI bundle and
	 * name.
	 *
	 */
	private static final class ResourceBundleCache {
		private final Map<Bundle, Map<String, ResourceBundle>> map =
			new java.util.HashMap<Bundle, Map<String, ResourceBundle>>();
		
		/**
		 * Obtains the resource bundle named <code>baseName</code> in the
		 * specified OSGI bundle's classpath, if it exists in this cache.
		 * 
		 * @param osgiBundle an OSGI bundle that defines a classpath
		 * @param baseName the base of the possibly locale-extended
		 *     resource file name
		 * @return the cached resource bundle, or <code>null</code> if it does
		 *     not exist in the cache
		 */
		ResourceBundle get(Bundle osgiBundle, String baseName) {
			ResourceBundle result = null;
			Map<String, ResourceBundle> secondLevel = map.get(osgiBundle);
			
			if (secondLevel != null) {
				result = secondLevel.get(baseName);
			}
			
			return result;
		}
		
		/**
		 * Puts the resource bundle named <code>baseName</code>, in the
		 * specified OSGI bundle's classpath, into this cache.
		 * 
		 * @param osgiBundle an OSGI bundle that defines a classpath
		 * @param baseName the base of the possibly locale-extended
		 *     resource file name
		 * @param rb the resource bundle to cache
		 */
		void put(Bundle osgiBundle, String baseName, ResourceBundle rb) {
			Map<String, ResourceBundle> secondLevel = map.get(osgiBundle);
			
			if (secondLevel == null) {
				secondLevel = new java.util.HashMap<String, ResourceBundle>();
				map.put(osgiBundle, secondLevel);
			}
			
			secondLevel.put(baseName, rb);
		}
		
		/**
		 * Fluhes the entire contents of the cache.
		 */
		void flush() {
			map.clear();
		}
	}

	/**
	 * Initializes me with the extension which defines the XML that I parse.
	 * The extension is used to gain information about the source plug-in.
	 * 
	 * @param extension the source extension
	 * @param baseUrl base URL on which any relative URLs of referenced XML
	 *     files are constructed
	 */
	public ItemsContentHandler(
			IExtension extension,
			URL baseUrl) {
		
		this.extension = extension;
		this.baseUrl = baseUrl;
	}

	/**
	 * Obtains the configuration element that I have parsed.
	 * Should only be called when parsing is complete.
	 * 
	 * @return my element, which will be an <tt>&lt;includedItems&gt;</tt>
	 */
	public IConfigurationElement getResult() {
		return resultElement;
	}

	/**
	 * Pushes a new element onto the top of the stack.
	 */
	@Override
	public void startElement(
			String namespaceURI,
			String localName,
			String qName,
			Attributes atts) {
		int attCount = atts.getLength();
		Map<String, String> attMap = new java.util.HashMap<String, String>();
		
		for (int i = 0; i < attCount; i++) {
			attMap.put(atts.getQName(i), localize(atts.getValue(i)));
		}
		
		stack.push(
				new XmlConfigurationElement(qName, attMap, extension, baseUrl));
	}

	/**
	 * Pops an element from the top of the stack.  This is retained as my
	 * result if it was the last element, otherwise it is added to the new top
	 * as a child element.
	 * 
	 * @see #getResult
	 */
	@Override
	public void endElement(
			String namespaceURI,
			String localName,
			String qName) throws SAXException {
		
		resultElement = stack.pop();

		if (!stack.isEmpty()) {
			stack.peek().addChild(resultElement);
			resultElement = null;
		}

	}

	/**
	 * Appends text to the body of the top element on the stack.
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
		stack.getBody().append(ch, start, length);
	}
	

	
	
	
	
	
	/**
	 * Flushes the resource bundle cache.  This should be called when no more
	 * constraint XML will be parsed, and the cache therefore is no longer
	 * needed.
	 */
	public static void flushResourceBundleCache() {
		resourceBundleCache.flush();
	}
	
	
	
	/**
	 * Implements the <tt>%<i>key</i></tt> localization mechanism in the
	 * XML file that I am parsing.  Uses my extension to look up the resource,
	 * unless a <tt>&lt;?emf-validation?&gt;</tt> processing instruction
	 * has indicated a different resource bundle.
	 * 
	 * @param s the string to localize, if it starts with a % character
	 * @return the localized string, or just <code>s</code> if no localization
	 *     was needed
	 */
	protected String localize(String s) {
		if ((s == null) || !s.startsWith("%")) { //$NON-NLS-1$
			return s;
		} else if (resourceBundle == null) {
			return Platform.getResourceString(
					Platform.getBundle(extension.getNamespaceIdentifier()),
					s);
		} else {
			return localize(s, resourceBundle);
		}
	}

	/**
	 * Implements the <tt>%<i>key</i></tt> localization mechanism using the
	 * specified resource <code>bundle</code>.
	 * 
	 * @param s the string to localize
	 * @param bundle the resource bundle to look it up in
	 * @return the localized string, or just <code>s</code> if no localization
	 *     was needed or the resource was not found
	 */
	protected String localize(String s, ResourceBundle bundle) {
		try {
			// strip off the initial '%'
			return bundle.getString(s.substring(1));
		} catch (MissingResourceException e) {
			
			e.printStackTrace() ;
			
			// just return the original string (it's the best we can do)
			return s;
		}
	}
	
	//
	// Handle errors my logging the problem and passing to super
	//
	
	// extends the inherited method
	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		
		e.printStackTrace();

		super.fatalError(e);
	}
	
	// extends the inherited method
	@Override
	public void error(SAXParseException e) throws SAXException {
		
		e.printStackTrace();
		
		super.error(e);
	}
	
	// extends the inherited method
	@Override
	public void warning(SAXParseException e) throws SAXException {
		e.printStackTrace();
		
		
		super.warning(e);
	}
	
	/**
	 * Obtains my file name.
	 * 
	 * @return my file name
	 */
	private String getFileName() {
		return baseUrl.getFile();
	}
}
