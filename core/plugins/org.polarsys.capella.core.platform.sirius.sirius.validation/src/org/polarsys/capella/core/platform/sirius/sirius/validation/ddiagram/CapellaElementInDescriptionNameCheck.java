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
import org.eclipse.sirius.diagram.DDiagram;
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
        result = ((DRepresentationDescriptor) object).getName();
      }
    }
    return result;
  }  
  
  public IStatus validateElement(EObject element, String description, final IValidationContext ctx) { 
    List<IStatus> failureMessages = new ArrayList<IStatus>();
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
        saxParser.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        saxParser.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        // SAXParser data handler
        DefaultHandler handler = new DefaultHandler() {
          // element calculated from a tag attribute in 'startElement' service
          EObject elementFound = null;
          // value of an element
          StringBuilder elementValue = new StringBuilder();
          private String elementId;
          private List<String> alreadyParsedLinks = new ArrayList<>();

          @Override
          public void startElement(String uri, String localName, String qName, Attributes attributes)
              throws SAXException {
            // assuming capella element or diagram hyperlink always resides in a tag attribute
            if (qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)) {
              // empty the elementValue at the start of the a tag
              elementValue = new StringBuilder(0);
              elementId = "";
              for (int i = 0; i < attributes.getLength(); i++) {
                // above filter state the image source (which could be relative or absolute path)
                String attValue = attributes.getValue(i);
                String attQName = attributes.getQName(i);

                if ((null != attValue) && !attValue.isEmpty() && qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)
                    && attQName.equalsIgnoreCase(IConstantValidation.XHTML_HREF_ATT)) {

                  EObject eObject = SaxParserHelper.getEObjectFromHrefAttribute(element, attValue);

                  if ((null != eObject) && ((eObject instanceof CapellaElement)
                      || (eObject instanceof DRepresentationDescriptor) || (eObject instanceof DRepresentation))) {
                    elementFound = eObject;
                    elementId = attValue.replace("hlink://", "");
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
              boolean isDiagram = elementFound instanceof DDiagram;
              String name = getName(elementFound);
              String value = elementValue.toString();
              value = value.replaceAll("\\s+", " "); //$NON-NLS-1$//$NON-NLS-2$
              if (!name.equals(value)) {
            	  if(!alreadyParsedLinks.contains(elementId)) {
            		  alreadyParsedLinks.add(elementId);
            		  String message = "(Hyperlink) The " + (isDiagram ? "diagram" : "model") 
            				  + " element named \"" + value
                              + "\" (id: "+ elementId 
                              + ") found in the rich text description of " + getName(element)
                              + " is not up to date.";
            		  failureMessages.add(ConstraintStatus.createStatus(ctx, element, ctx.getResultLocus(), "{0}", message));			  
            	  }else { 
            		  String elementName = value;
            		  List<IStatus> updatedResult = failureMessages.stream()
      							.map(sts -> {
      								if(sts.getMessage().contains(elementId)) {
      									String message = "(Hyperlink) The model/diagram elements named \"" + elementName
      			                              + ", ...\" (id: "+ elementId 
      			                              + ") found in the rich text description of " + getName(element)
      			                              + " are not up to date.";
      									return ConstraintStatus.createStatus(ctx, element, ctx.getResultLocus(), "{0}", message);
      								}
      								return sts;
      							})
      							.collect(Collectors.toList());
            		  failureMessages.clear();
            		  failureMessages.addAll(updatedResult);
            	  }
              }
              // re-init for new element to be found
              elementFound = null;
              // empty the value
              elementValue = new StringBuilder();
              elementId = "";
            }
          }

        };
        // check if capella element id is contained in

        // input Source
        InputSource is = new InputSource();
        reader = new StringReader(_description.toString());
        is.setCharacterStream(reader);
        saxParser.parse(is, handler);

      } 
      catch (SAXParseException ex) {
         failureMessages.add(ctx.createFailureStatus("Invalid description format at line " + ex.getLineNumber())); //$NON-NLS-1$
      } catch (Exception exception) {
        StringBuilder loggerMessage = new StringBuilder("Invalid description format"); //$NON-NLS-1$
        _logger.debug(loggerMessage.toString(), exception);
      }
      finally {
        // unload every thing
        if (reader != null) {
          reader.close();
        }
        if (saxParser != null) {
          saxParser.reset();
        }
      }
    }
    if (failureMessages.isEmpty()) {
      return ctx.createSuccessStatus();
    }
    
    return ConstraintStatus.createMultiStatus(ctx, failureMessages);   
  }


  @Override
  public IStatus validate(final IValidationContext ctx) {
    List<IStatus> failureMessages = new ArrayList<IStatus>();
    EObject target = ctx.getTarget();

    if (target instanceof CapellaElement) {
      final CapellaElement capellaElement = (CapellaElement) target;

      IStatus currentElementStatus = validateElement(capellaElement, capellaElement.getDescription(), ctx);
      if (!currentElementStatus.isOK()) {
        failureMessages.add(currentElementStatus);
      }

      Session.of(target).ifPresent(session -> {
        Collection<DRepresentationDescriptor> representationDescriptors = DialectManager.INSTANCE
            .getRepresentationDescriptors(target, session);
        for (DRepresentationDescriptor dRepresentationDescriptor : representationDescriptors) {
          IStatus currentRepresentationStatus = validateElement(dRepresentationDescriptor,
              dRepresentationDescriptor.getDocumentation(), ctx);
          if (!currentRepresentationStatus.isOK()) {
            failureMessages.add(currentRepresentationStatus);
          }
        }
      });
    }

    if (failureMessages.isEmpty()) {
      return ctx.createSuccessStatus();
    }
    return ConstraintStatus.createMultiStatus(ctx, failureMessages);
  }

}
