/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.utils.saxparser.IConstantValidation;
import org.polarsys.capella.core.model.utils.saxparser.SaxParserHelper;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Using SAX parser delete capella element or diagram hyperLink from description
 */
public class DeleteInValidHyperLinkInDescription {

  protected Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.VALIDATION);
  protected StringBuilder __description;
  protected StringBuilder _currentElementDescription;

  public DeleteInValidHyperLinkInDescription() {
    __description = null;
    _currentElementDescription = null;
  }

  public boolean updateDescription(List<EObject> modelElements) {
    Iterator<EObject> iterator = modelElements.iterator();
    while (iterator.hasNext()) {
      EObject object = iterator.next();
      if (object instanceof CapellaElement) {
        final CapellaElement capellaElement = (CapellaElement) object;
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
              // check the capella name: if same as value do nothing else update the description
              boolean elementIsNull = false;
              // value calculated
              boolean valueToAdd = false;
              // merge the value
              StringBuilder elementValue = new StringBuilder();

              /**
               * {@inheritDoc}
               */
              @Override
              public void characters(char ch[], int start, int length) throws SAXException {
                valueToAdd = true;
                // value of an element
                elementValue.append(new String(ch, start, length));
              }

              @Override
              public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (elementIsNull) {
                  elementValue = new StringBuilder(0);
                  valueToAdd = false;
                  return;
                }
                // startElement can be called just after value is calculated for nested element
                // example: <p> value1 <a href="hvalue"> value2 </a></p>
                // so need to add the value to the result before starting new element
                if (valueToAdd) {
                  __description.append(elementValue.toString());
                  valueToAdd = false;
                }
                elementValue = new StringBuilder(0);

                // if qName is break
                // an endElement will add the tag value to the result
                if (qName.equals(IConstantValidation.XHTML_BREAK_ELEMENT)) {
                  return;
                }

                // if not a tag, add avery thing to result
                if (!qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)) {
                  addElementToResult(qName, attributes);
                } else {
                  for (int i = 0; i < attributes.getLength(); i++) {
                    // above filter state the image source (which could be relative or absolute path)
                    String attValue = attributes.getValue(i);
                    String attName = attributes.getQName(i);
                    if ((null != attValue) && !attValue.isEmpty() && qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)
                        && attName.equalsIgnoreCase(IConstantValidation.XHTML_HREF_ATT)) {

                      EObject eObject = SaxParserHelper.getEObjectFromHrefAttribute(capellaElement, attValue);
                      if (null == eObject) {
                        // if ok default value will be added else the value will be updated
                        elementIsNull = true;
                        break;
                      }
                    }
                  }
                }
                if (!elementIsNull) {
                  addElementToResult(qName, attributes);
                }

              }

              public void addElementToResult(String qName, Attributes attributes) {
                // '<'
                __description.append(IConstantValidation.LESS_THAN);
                // element name
                __description.append(qName);
                if (attributes.getLength() == 0) {
                  // close tab ">" if no attributes
                  // empty line correction in description after publication
                  __description.append(IConstantValidation.GREATER_THAN);
                } else {
                  // fore all attributes
                  for (int i = 0; i < attributes.getLength(); i++) {
                    // above filter state the image source (which could be relative or absolute path)
                    String attValue = attributes.getValue(i);
                    String attName = attributes.getQName(i);
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

                if (!elementIsNull) {
                  // add break element
                  if (qName.equals(IConstantValidation.XHTML_BREAK_ELEMENT)) {
                    __description.append(IConstantValidation.XHTML_BREAK_ELEMENT_END);
                    return;
                  }

                  // add value
                  if (valueToAdd) {
                    valueToAdd = false;
                    __description.append(elementValue.toString());
                  }

                  // add end tab
                  __description.append(IConstantValidation.CLOSE_TAB_PREFIX);
                  __description.append(qName);
                  __description.append(IConstantValidation.GREATER_THAN);
                }

                if (elementIsNull && qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)) {
                  // re-initialize
                  elementIsNull = false;
                }
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

          } catch (Exception exception) {
            _logger.error("Exception while quick fix : " + exception.toString()); //$NON-NLS-1$
            return false;
          } finally {
        	  // reset parser and reader
        	  if(reader != null) {
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
    }
    return true;
  }
}
