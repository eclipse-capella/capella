/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.utils.saxparser;

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
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Using SAX parser correct capella element name in description
 */
public class WriteCapellaElementDescriptionSAXParser {

  protected Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.VALIDATION);
  protected StringBuilder description;
  protected StringBuilder currentElementDescription;

  public WriteCapellaElementDescriptionSAXParser() {
    description = null;
    currentElementDescription = null;
  }

  protected String getName(EObject object) {
    if (null != object) {
      return CapellaElementExt.getName(object);
    }
    return null;
  }

  protected boolean managedObject(EObject object) {
    return (object instanceof CapellaElement);
  }

  public boolean updateDescription(List<EObject> modelElements) {
    Iterator<EObject> iterator = modelElements.iterator();
    while (iterator.hasNext()) {
      EObject object = iterator.next();
      if (object instanceof CapellaElement) {
        final CapellaElement capellaElement = (CapellaElement) object;
        String elementDescription = capellaElement.getDescription();
        if ((null != elementDescription) && !elementDescription.isEmpty()) {
          currentElementDescription = new StringBuilder();
          // for each description, start from scratch
          description = new StringBuilder();
          elementDescription = SaxParserHelper.escapeSpecialCharacter(elementDescription);
          currentElementDescription.append(IConstantValidation.ROOT_NODE);
          currentElementDescription.append(elementDescription);
          currentElementDescription.append(IConstantValidation.ROOT_NODE_END);
          SAXParser saxParser = null;
          StringReader reader = null;
          try {

            SAXParserFactory saxFactory = SAXParserFactory.newInstance();
            saxFactory.setValidating(false);
            saxParser = saxFactory.newSAXParser();
            // sax data handler
            DefaultHandler handler = new DefaultHandler() {
              // check the capella name: if same as value do nothing else update the description
              EObject elementFound = null;
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

                // startElement can be called just after value is calculated for nested element
                // example: <p> value1 <a href="hvalue"> value2 </a></p>
                // so need to add the value to the result before starting new element
                if (valueToAdd) {
                  description.append(elementValue.toString());
                  valueToAdd = false;
                }
                elementValue = new StringBuilder(0);

                // if qName is break
                // an endElement will add the tag value to the result
                if (qName.equals(IConstantValidation.XHTML_BREAK_ELEMENT)) {
                  return;
                }

                // '<'
                description.append(IConstantValidation.LESS_THAN);
                // element name
                description.append(qName);
                if (attributes.getLength() == 0) {
                  // close tab ">" if no attributes
                  // empty line correction in description after publication
                  description.append(IConstantValidation.GREATER_THAN);
                } else {
                  // fore all attributes
                  for (int i = 0; i < attributes.getLength(); i++) {
                    // above filter state the image source (which could be relative or absolute path)
                    String attValue = attributes.getValue(i);
                    String attName = attributes.getQName(i);
                    // assuming that capella/diagram element reference contain "platform" and "resource" string in href
                    if ((null != attValue) && !attValue.isEmpty() && qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)
                        && attName.equalsIgnoreCase(IConstantValidation.XHTML_HREF_ATT)) {

                      EObject eObject = SaxParserHelper.getEObjectFromHrefAttribute(capellaElement, attValue);

                      if (managedObject(eObject)) {
                        // if ok default value will be added else the value will be updated
                        elementFound = eObject;
                      }
                    }
                    // add other attribute
                    description.append(IConstantValidation.SPACE);
                    description.append(attName);
                    description.append(IConstantValidation.EQUAL);
                    description.append(IConstantValidation.DOUBLE_QUOTES);
                    description.append(attValue);
                    description.append(IConstantValidation.DOUBLE_QUOTES);
                  }
                  // close start Element
                  description.append(IConstantValidation.GREATER_THAN);
                }
              }

              /**
               * {@inheritDoc}
               */
              @Override
              public void endElement(String uri, String localName, String qName) throws SAXException {

                // break element
                if (qName.equals(IConstantValidation.XHTML_BREAK_ELEMENT)) {
                  description.append(IConstantValidation.XHTML_BREAK_ELEMENT_END);
                  return;
                }

                // value
                if (valueToAdd) {
                  valueToAdd = false;
                  // flag will make sure that element value is added to result
                  boolean flag = false;
                  String value = elementValue.toString();
                  if ((null != elementFound)) {
                    String name = getName(elementFound);
                    value = value.replaceAll("\\s+", " "); //$NON-NLS-1$//$NON-NLS-2$
                    if (!name.equals(value)) {
                      if (managedObject(elementFound)) {
                        flag = true;
                        // add the name of the capella element or diagram
                        description.append(name);
                      }
                    }
                    // element treated so re-initialize
                    elementFound = null;
                  }
                  if (!flag) {
                    // add the current value
                    description.append(value);
                  }
                }

                // end tab
                //
                description.append(IConstantValidation.CLOSE_TAB_PREFIX);
                description.append(qName);
                description.append(IConstantValidation.GREATER_THAN);

                // empty the element value
                elementValue = new StringBuilder(0);

              }

            };
            // check if capella element id is contained in

            // input Source
            InputSource is = new InputSource();
            reader = new StringReader(currentElementDescription.toString());
            is.setCharacterStream(reader);
            saxParser.parse(is, handler);

          } catch (Exception exception) {
            logger.error("Exception while quick fix : " + exception.toString()); //$NON-NLS-1$
            return false;
          } finally {
            // reset parser and reader
            if(reader != null){
              reader.close();
            }
            if(saxParser != null){
              saxParser.reset();
            }
          }
          // remove root
          String result = description.toString().replaceAll(IConstantValidation.ROOT_NODE, ICommonConstants.EMPTY_STRING);
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
