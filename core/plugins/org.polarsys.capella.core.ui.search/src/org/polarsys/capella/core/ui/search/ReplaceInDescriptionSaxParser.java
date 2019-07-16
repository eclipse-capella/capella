/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.utils.saxparser.IConstantValidation;
import org.polarsys.capella.core.model.utils.saxparser.SaxParserHelper;

/**
 * copied from org.polarsys.capella.core.platform.sirius.viewpoint.validation.ddiagram.CapellaElementInDescriptionNameCheck
 */
public class ReplaceInDescriptionSaxParser {

  protected Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.VALIDATION);
  protected StringBuilder __description;
  protected StringBuilder _currentElementDescription;

  public ReplaceInDescriptionSaxParser() {
    __description = null;
    _currentElementDescription = null;
  }

  protected String getName(EObject object) {
    String result = null;
    if (null != object) {
      result = CapellaElementExt.getName(object);
      if ((null == result || result.isEmpty()) && object instanceof DRepresentationDescriptor) {
        DRepresentationDescriptor res = (DRepresentationDescriptor) object;
        String repName = res.getName();
        if (null != repName) {
          result = repName;
        }
      }
    }
    return result;
  }

  public boolean replace(final String wildCardFindString_p, final String replaceString_p, EObject modelElt) {

    if (modelElt instanceof CapellaElement) {
      CapellaElement capellaElement = (CapellaElement) modelElt;
      String description = capellaElement.getDescription();

      if ((null != description) && !description.isEmpty()) {
        _currentElementDescription = new StringBuilder();
        // for each description, start from scratch
        __description = new StringBuilder();
        description = SaxParserHelper.escapeSpecialCharacter(description);
        _currentElementDescription.append(IConstantValidation.ROOT_NODE);
        _currentElementDescription.append(description);
        _currentElementDescription.append(IConstantValidation.ROOT_NODE_END);
        SAXParser saxParser = null;
        StringReader reader = null;
        try {

          SAXParserFactory saxFactory = SAXParserFactory.newInstance();
          saxFactory.setValidating(false);
          saxParser = saxFactory.newSAXParser();

          // sax data handler
          DefaultHandler handler = new DefaultHandler() {

            // value calculated
            boolean valueToAdd = false;
            // merge the value
            StringBuilder elementValue = new StringBuilder();
            private boolean protectedData;

            /**
             * {@inheritDoc}
             */
            @Override
            public void characters(char ch[], int start, int length) throws SAXException {
              valueToAdd = true;
              // value of an element

              String data = new String(ch, start, length);

              elementValue.append(data);
            }

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

              // startElement can be called just after value is calculated for nested element
              // example: <p> value1 <a href="hvalue"> value2 </a></p>
              // so need to add the value to the result before starting new element
              if (valueToAdd) {
                elementValue = new StringBuilder(elementValue.toString().replaceAll(wildCardFindString_p, replaceString_p));
                __description.append(elementValue.toString());
                valueToAdd = false;
              }
              elementValue = new StringBuilder(0);

              // if qName is break
              // an endElement will add the tag value to the result
              if (qName.equals(IConstantValidation.XHTML_BREAK_ELEMENT)) {
                return;
              }

              // '<'
              __description.append(IConstantValidation.LESS_THAN);
              // element name
              __description.append(qName);
              if (attributes.getLength() == 0) {
                // close tab ">" if no attributes
                // empty line correction in description after publication
                __description.append(IConstantValidation.GREATER_THAN);
              } else {
                // for all attributes
                for (int i = 0; i < attributes.getLength(); i++) {
                  // above filter state the image source (which could be relative or absolute path)
                  String attValue = attributes.getValue(i);
                  String attName = attributes.getQName(i);

                  // assuming that capella/diagram element reference contain "platform" and "resource" string in href
                  if ((null != attValue) && !attValue.isEmpty() && qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)
                      && attName.equalsIgnoreCase(IConstantValidation.XHTML_HREF_ATT)) {

                    protectedData = true;
                  }

                  // add other attribute
                  __description.append(IConstantValidation.SPACE);
                  __description.append(attName);
                  __description.append(IConstantValidation.EQUAL);
                  __description.append(IConstantValidation.DOUBLE_QUOTES);
                  __description.append(attValue);
                  __description.append(IConstantValidation.DOUBLE_QUOTES);
                }
                // close start Element
                __description.append(IConstantValidation.GREATER_THAN);
              }
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {

              // break element
              if (qName.equals(IConstantValidation.XHTML_BREAK_ELEMENT)) {
                __description.append(IConstantValidation.XHTML_BREAK_ELEMENT_END);
                return;
              }

              // value
              if (valueToAdd) {
                valueToAdd = false;
                // flag will make sure that element value is added to result
                String value = elementValue.toString();

                value = value.replaceAll("\\s+", " "); //$NON-NLS-1$//$NON-NLS-2$
                // perform replace
                if (!protectedData) {
                  value = value.replaceAll(wildCardFindString_p, replaceString_p);
                }
                protectedData = false;
                __description.append(value);

              }

              // end tab
              //
              __description.append(IConstantValidation.CLOSE_TAB_PREFIX);
              __description.append(qName);
              __description.append(IConstantValidation.GREATER_THAN);

              // empty the element value
              elementValue = new StringBuilder(0);
            }
          };
          // check if capella element id is contained in

          // input Source
          InputSource is = new InputSource();
          reader = new StringReader(_currentElementDescription.toString());
          is.setCharacterStream(reader);
          saxParser.parse(is, handler);
          reader.close();
          saxParser.reset();

        } catch (Exception exception) {
          _logger.error("Exception while quick fix : " + exception.toString()); //$NON-NLS-1$
          return false;
        } finally {
        	if(reader != null) {
        	  // reset parser and reader
        	  reader.close();
        	}
        	if(saxParser != null) {
        		saxParser.reset();        		
        	}
        }
        // remove root
        String result = __description.toString().replaceAll(IConstantValidation.ROOT_NODE, ICommonConstants.EMPTY_STRING);
        // remove root_end
        result = result.replaceAll(IConstantValidation.ROOT_NODE_END, ICommonConstants.EMPTY_STRING);
        // set description
        capellaElement.setDescription(result);
      }
    }

    return true;
  }
}
