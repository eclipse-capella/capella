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
package org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
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
 * Check if HyperLinks to capella element or diagram does not exist in model any more
 */
public class CapellaElementInDescriptionExistanceCheck extends AbstractValidationRule {

  public final class LocalDefaultHandler extends DefaultHandler {
    private final EObject element;
    private List<IStatus> result;
    private final IValidationContext ctx;
    private boolean isElementExist = true;
    private String elementId;
    private StringBuilder elementValue = new StringBuilder();
    private List<String> alreadyParsedLinks = new ArrayList<>();

    public LocalDefaultHandler(EObject element, List<IStatus> result, IValidationContext ctx) {
      this.element = element;
      this.result = result;
      this.ctx = ctx;
    }

    public String extractName(String statusMessage) {
      String pattern = "named “(.*?)”"; // regular expression to match the name
      Pattern r = Pattern.compile(pattern);
      Matcher m = r.matcher(statusMessage);
      if (m.find()) {
        return m.group(1); // return the matched name
      }
      return ""; // return an empty string if no match was found

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
      // if a tag : look for hyperLink to capella element or diagram
      if (qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)) {
        elementValue = new StringBuilder(0);
        elementId = "";
        isElementExist = true;
        for (int i = 0; i < attributes.getLength(); i++) {
          // above filter state the image source (which could be relative or absolute path)
          String attValue = attributes.getValue(i);
          String attName = attributes.getQName(i);

          if ((null != attValue) && !attValue.isEmpty() && qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)
              && attName.equalsIgnoreCase(IConstantValidation.XHTML_HREF_ATT)) {
            // get id
            // get element
            EObject eObject = SaxParserHelper.getEObjectFromHrefAttribute(element, attValue);

            if (null == eObject) {
              // element does not exist in the resource
              elementId = attValue.replace("hlink://", "");
              isElementExist = false;
              break;
            }
          }
        }
      }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
      String string = new String(ch, start, length);
      elementValue.append(string);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      // a tag
      if ((qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)) && !isElementExist) {
        String value = elementValue.toString();
        value = value.replaceAll("\\s+", " "); //$NON-NLS-1$//$NON-NLS-2$
        String elementName = getName(element);
        if (!alreadyParsedLinks.contains(elementId)) {
          alreadyParsedLinks.add(elementId);
          String failureMessage = "(Hyperlink) The model/diagram element named “" + value + "” (id: " + elementId
              + ") can not be found for the rich text description of the element " + elementName;
          result.add(ConstraintStatus.createStatus(ctx, element, ctx.getResultLocus(), "{0}", failureMessage));
        } else {
          List<IStatus> updatedResult = result.stream().map(sts -> {
            if (sts.getMessage().contains(elementId)) {
              String name = extractName(sts.getMessage());
              String failureMessage = "(Hyperlink) The model/diagram elements named “" + name + ", ...” (id: "
                  + elementId + ") can not be found for the rich text description of the element " + elementName;
              return ConstraintStatus.createStatus(ctx, element, ctx.getResultLocus(), "{0}", failureMessage);
            }
            return sts;
          }).collect(Collectors.toList());
          result.clear();
          result.addAll(updatedResult);
        }
        // re-init for new element to be found or not
        isElementExist = true;
        // empty the value and the id
        elementValue = new StringBuilder();
        elementId = "";
      }
    }

  }

  public static String getName(EObject object) {
    String result = null;
    if (null != object) {
      result = CapellaElementExt.getName(object);
      if (object instanceof DRepresentation) {
        DRepresentation res = (DRepresentation) object;
        object = RepresentationHelper.getRepresentationDescriptor(res);
      }
      if (object instanceof DRepresentationDescriptor) {
        result = ((DRepresentationDescriptor) object).getName();
      }
    }
    return result;
  }

  public List<IStatus> validateElement(EObject element, String description, IValidationContext ctx) {
    List<IStatus> result = new ArrayList<>();
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
      saxParser.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
      saxParser.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
      // sax data handler
      DefaultHandler handler = new LocalDefaultHandler(element, result, ctx);
      // check if capella element id is contained in

      // input Source
      InputSource is = new InputSource();
      reader = new StringReader(desc.toString());
      is.setCharacterStream(reader);
      saxParser.parse(is, handler);

    } catch (SAXParseException ex) {
      result.add(ctx.createFailureStatus("Invalid description format at line " + ex.getLineNumber())); //$NON-NLS-1$
    } catch (Exception exception_p) {
      StringBuilder loggerMessage = new StringBuilder("Invalid description format"); //$NON-NLS-1$
      logger.debug(loggerMessage.toString(), exception_p);
    } finally {
      if (reader != null && saxParser != null) {
        reader.close();
        saxParser.reset();
      }
    }

    return result;
  }

  protected Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.VALIDATION);
  protected StringBuilder desc = null;

  @Override
  public IStatus validate(final IValidationContext ctx) {
    EObject target = ctx.getTarget();
    List<IStatus> result = new ArrayList<>();
    if ((null != target) && (target instanceof CapellaElement)) {
      final CapellaElement capellaElement = (CapellaElement) target;
      String description = capellaElement.getDescription();
      if ((null != description) && !description.isEmpty()) {
        result.addAll(validateElement(capellaElement, description, ctx));
      }
      Session.of(target).ifPresent(session -> {
        Collection<DRepresentationDescriptor> representationDescriptors = DialectManager.INSTANCE
            .getRepresentationDescriptors(target, session);
        for (DRepresentationDescriptor dRepresentationDescriptor : representationDescriptors) {
          String documentation = dRepresentationDescriptor.getDocumentation();
          List<IStatus> currentRepresentationStatus = validateElement(dRepresentationDescriptor, documentation, ctx);
          result.addAll(currentRepresentationStatus);
        }
      });
    }

    IStatus returnedStatus = null;
    if (result.isEmpty()) {
      returnedStatus = ctx.createSuccessStatus();
    } else {
      if (result.size() == 1) {
        returnedStatus = result.get(0);
      } else {
        returnedStatus = ConstraintStatus.createMultiStatus(ctx, result);
      }
    }
    return returnedStatus;
  }

}
