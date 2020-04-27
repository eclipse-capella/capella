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
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.utils.saxparser.IConstantValidation;
import org.polarsys.capella.core.model.utils.saxparser.SaxParserHelper;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Check if HyperLinks to capella element or diagram does not exist in model any more
 */
public class CapellaElementInDescriptionExistanceCheck extends AbstractValidationRule {

  public final class LocalDefaultHandler extends DefaultHandler {
    private final CapellaElement capellaElement;
    private final IStatus[] result;
    private final IValidationContext ctx;

    public LocalDefaultHandler(CapellaElement capellaElement, IStatus[] result, IValidationContext ctx) {
      this.capellaElement = capellaElement;
      this.result = result;
      this.ctx = ctx;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
      // if a tag : look for hyperLink to capella element or diagram
      if (qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)) {
        for (int i = 0; i < attributes.getLength(); i++) {
          // above filter state the image source (which could be relative or absolute path)
          String attValue = attributes.getValue(i);
          String attName = attributes.getQName(i);

          if ((null != attValue) && !attValue.isEmpty() && qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)
              && attName.equalsIgnoreCase(IConstantValidation.XHTML_HREF_ATT)) {
            // get id
            // get element
            EObject eObject = SaxParserHelper.getEObjectFromHrefAttribute(capellaElement, attValue);

            if (null == eObject) {
              // element does not exist in the resource
              result[0] = ctx.createFailureStatus();
              break;
            }
          }
        }
      }
    }
  }

  protected Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.VALIDATION);
  protected StringBuilder desc = null;

  @Override
  public IStatus validate(final IValidationContext ctx) {
    EObject target = ctx.getTarget();
    final IStatus[] result = { null };
    if ((null != target) && (target instanceof CapellaElement)) {
      final CapellaElement capellaElement = (CapellaElement) target;
      String description = capellaElement.getDescription();
      if ((null != description) && !description.isEmpty()) {
        description = SaxParserHelper.escapeSpecialCharacter(description);
        desc = new StringBuilder();
        desc.append(IConstantValidation.ROOT_NODE);
        desc.append(description);
        desc.append(IConstantValidation.ROOT_NODE_END);
        // parser
        SAXParser saxParser = null;
        StringReader reader = null;
        try {

          SAXParserFactory saxFactory = SAXParserFactory.newInstance();
          saxFactory.setValidating(false);
          saxParser = saxFactory.newSAXParser();
          // sax data handler
          DefaultHandler handler = new LocalDefaultHandler(capellaElement, result, ctx);
          // check if capella element id is contained in

          // input Source
          InputSource is = new InputSource();
          reader = new StringReader(desc.toString());
          is.setCharacterStream(reader);
          saxParser.parse(is, handler);

        } catch (SAXParseException ex) {
          return ctx.createFailureStatus("Invalid description format at line " + ex.getLineNumber()); //$NON-NLS-1$
        } catch (Exception exception_p) {
          StringBuilder loggerMessage = new StringBuilder("Invalid description format"); //$NON-NLS-1$
          logger.debug(loggerMessage.toString(), exception_p);
        } finally {
          if (reader != null && saxParser != null) {
            reader.close();
            saxParser.reset();
          }
        }
      }
    }

    if (null != result[0]) {
      return ctx.createFailureStatus("HyperLinks to capella element or diagram does not exist in model any more"); //$NON-NLS-1$
    }
    return ctx.createSuccessStatus();
  }


}
