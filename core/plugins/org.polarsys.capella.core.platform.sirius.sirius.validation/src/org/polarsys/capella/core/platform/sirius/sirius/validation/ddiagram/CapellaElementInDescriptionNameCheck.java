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

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.utils.saxparser.IConstantValidation;
import org.polarsys.capella.core.model.utils.saxparser.SaxParserHelper;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Check if HyperLinks to capella element or diagram in the description are up to date
 */
public class CapellaElementInDescriptionNameCheck extends AbstractValidationRule {

  protected Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.VALIDATION);
  protected StringBuilder _description = null;

  public static String getName(EObject object) {
    String result = null;
    if (null != object) {
      result = CapellaElementExt.getName(object);
      if (object instanceof DRepresentation) {
        DRepresentation res = (DRepresentation) object;
        object = RepresentationHelper.getRepresentationDescriptor(res);
      }
      if (object instanceof DRepresentationDescriptor) {
        result = ((DRepresentationDescriptor)object).getName();
      }
    }
    return result;
  }
  
  @Override
  public IStatus validate(final IValidationContext ctx) {
    EObject target = ctx.getTarget();
    final IStatus[] result = { null };
    if (target instanceof CapellaElement) {
      final CapellaElement capellaElement = (CapellaElement) target;
      String description = capellaElement.getDescription();
      if ((null != description) && !description.isEmpty()) {
        // few escape char (need to improve this part)
        description = SaxParserHelper.escapeSpecialCharacter(description);
        _description = new StringBuilder();
        // root added, because some time in capella there might not be any root element
        _description.append(IConstantValidation.ROOT_NODE);
        _description.append(description);
        _description.append(IConstantValidation.ROOT_NODE_END);
        // parser
        SAXParser saxParser = null;
        StringReader reader = null;
        try {

          SAXParserFactory saxFactory = SAXParserFactory.newInstance();
          saxFactory.setValidating(false);
          saxParser = saxFactory.newSAXParser();
          // SAXParser data handler
          DefaultHandler handler = new DefaultHandler() {
            // element calculated from a tag attribute in 'startElement' service
            EObject elementFound = null;
            // value of an element
            StringBuilder elementValue = new StringBuilder();

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
              // assuming capella element or diagram hyperlink always resides in a tag attribute
              if (qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)) {
                // empty the elementValue at the start of the a tag
                elementValue = new StringBuilder(0);
                for (int i = 0; i < attributes.getLength(); i++) {
                  // above filter state the image source (which could be relative or absolute path)
                  String attValue = attributes.getValue(i);
                  String attQName = attributes.getQName(i);

                  if ((null != attValue) && !attValue.isEmpty() && qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)
                      && attQName.equalsIgnoreCase(IConstantValidation.XHTML_HREF_ATT)) {

                    EObject eObject = SaxParserHelper.getEObjectFromHrefAttribute(capellaElement, attValue);

                    if ((null != eObject) && ((eObject instanceof CapellaElement) || (eObject instanceof DRepresentationDescriptor) || (eObject instanceof DRepresentation))) {
                      elementFound = eObject;
                      // once found discontinue
                      break;
                    }
                  }
                }
              }

            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void characters(char ch[], int start, int length) throws SAXException {
              String string = new String(ch, start, length);
              elementValue.append(string);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
              // a tag
              if ((qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)) && (null != elementFound)) {
                String name = getName(elementFound);
                String value = elementValue.toString();
                value = value.replaceAll("\\s+", " "); //$NON-NLS-1$//$NON-NLS-2$
                if (!name.equals(value)) {
                  // IStatus createFailureStatus = createFailureStatus(ctx_p, new Object[] { value + " [in description instead of ] " + name });
                  result[0] = ctx.createFailureStatus();
                }
                // re-init for new element to be found
                elementFound = null;
                // empty the value
                elementValue = new StringBuilder();
              }
            }

          };
          // check if capella element id is contained in

          // input Source
          InputSource is = new InputSource();
          reader = new StringReader(_description.toString());
          is.setCharacterStream(reader);
          saxParser.parse(is, handler);

        } catch (SAXParseException ex) {
          return ctx.createFailureStatus("Invalid description format at line " + ex.getLineNumber()); //$NON-NLS-1$
        } catch (Exception exception) {
          StringBuilder loggerMessage = new StringBuilder("Invalid description format"); //$NON-NLS-1$
          _logger.debug(loggerMessage.toString(), exception);

        } finally {
          // unload every thing
          if(reader != null) {
        	reader.close();        		
          }
          if(saxParser != null) {
        	  saxParser.reset();        	  
          }
        }
      }
    }

    if (null != result[0]) {
      return ctx.createFailureStatus("HyperLinks to capella element or diagram in the description are not up to date"); //$NON-NLS-1$
    }
    return ctx.createSuccessStatus();
  }

}
