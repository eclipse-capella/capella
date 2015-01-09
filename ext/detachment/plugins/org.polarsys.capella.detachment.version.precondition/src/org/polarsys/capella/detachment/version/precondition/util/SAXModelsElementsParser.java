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
package org.polarsys.capella.detachment.version.precondition.util;

import java.util.Collection;
import java.util.HashSet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 */
public class SAXModelsElementsParser extends DefaultHandler {
	
	Collection<String> capellaModellers;
	
	public void startDocument() throws SAXException{
		capellaModellers = new HashSet<String>();
	}
	
	public void startElement(String namespaceURI, String name, String qname, Attributes attr) throws SAXException {
		if (qname != null && qname.equals("models")){ //$NON-NLS-1
			String fileModelName = attr.getValue("href"); //$NON-NLS-1
			
			if (fileModelName != null && !fileModelName.isEmpty()){
				if (fileModelName.contains("#")){ //NON-NLS-1
					fileModelName = fileModelName.substring(0, fileModelName.indexOf("#")); //NON-NLS-1
				}
				capellaModellers.add(fileModelName);
			}
		}
	}

	public void endDocument() throws SAXException {
	}

	public Collection<String> getCapellaModellers() {
		return capellaModellers;
	}
}
