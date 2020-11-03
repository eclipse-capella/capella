/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.helpers.validation.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import org.polarsys.capella.common.helpers.HelperPlugin;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * A helper class to 'validate' xml documents.
 * 
 */
public class XMLValidationHelper {

  protected SAXParser parser;
  protected DefaultHandler defaultHandler;
  
  /**
   * Create a new XMLValidationHelper.
   * 
   * This helper is using SAX to parse xml. If no SAXParser instance
   * can be obtained via the SAXParserFactory, we throw an 
   * unchecked exception. Unchecked because this will happen rarely/never?
   * on a standard sun jre.
   */
  public XMLValidationHelper() {
    try {
      parser = SAXParserFactory.newInstance().newSAXParser();
    } catch (ParserConfigurationException exception) {
      HelperPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, 
          HelperPlugin.getDefault().getPluginId(), exception.getMessage(), exception));
    } catch (SAXException exception) {
      HelperPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, 
          HelperPlugin.getDefault().getPluginId(), exception.getMessage(), exception));
    } finally {
      if (parser == null){
        throw new RuntimeException("Cannot get a SAXParser instance"); //$NON-NLS-1$
      }
    }
  }
  
  /**
   * Tests if the argument is well formed xml by pumping it thru a sax parser.
   * 
   * @param text the text you want to check for well formedness
   * @return List of SAXParseExceptions that the SAXParser dumped while parsing. never null.
   */
  public List<SAXParseException> checkWellFormed(String text) {
    SAXExceptions handler = new SAXExceptions();
    try {
      parser.parse(new InputSource(new StringReader(text)), handler);
    } catch (IOException exception) {
      HelperPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, 
          HelperPlugin.getDefault().getPluginId(), exception.getMessage(), exception));
    } catch (SAXException exception) {
      if (!(exception instanceof SAXParseException)){
        // SAXParseExceptions are stored already in the handler, so skip them here.
        HelperPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, 
          HelperPlugin.getDefault().getPluginId(), exception.getMessage(), exception));
      }
    }
    return handler.getExceptions();
  }


  class SAXExceptions extends DefaultHandler {
    protected List<SAXParseException> exceptions = new ArrayList<SAXParseException>();
    @Override
    public void error(SAXParseException e){
      exceptions.add(e);
    }
    @Override
    public void fatalError(SAXParseException e){
      exceptions.add(e);
    }
    public List<SAXParseException> getExceptions(){
      return exceptions;
    }
  }
}
