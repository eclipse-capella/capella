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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check if HyperLinks to capella element or diagram in the description are up to date
 */
public class CapellaElementInDescriptionNameCheck extends AbstractValidationRule {

  public IStatus validateElement(EObject element, String description, final IValidationContext ctx) {
    List<IStatus> failureMessages = new ArrayList<>();
    ILinkParserHandler linkNameCheckHandler = new ILinkParserHandler() {
      private List<LinkDescription> parsedLinks = new ArrayList<>();

      @Override
      public void handleParsedLink(LinkDescription parsedLink) {
        if (parsedLink.getTargetElement() != null) {
          EObject elementFound = parsedLink.getTargetElement();
          boolean isDiagram = elementFound instanceof DDiagram;
          String name = NamingHelper.getElementName(elementFound);
          String value = parsedLink.getName();
          String elementId = parsedLink.getHref().replace("hlink://", "");
          if (!name.equals(value)) {
            if (!parsedLinks.contains(parsedLink)) {
              parsedLinks.add(parsedLink);
              String message = "(Hyperlink) The " + (isDiagram ? "diagram" : "model") + " element named \"" + value
                  + "\" (id: " + elementId + ") found in the rich text description of "
                  + NamingHelper.getElementName(element) + " is not up to date.";
              failureMessages.add(ConstraintStatus.createStatus(ctx, element, ctx.getResultLocus(), "{0}", message));
            } else {
              String elementName = value;
              List<IStatus> updatedResult = failureMessages.stream().map(sts -> {
                if (sts.getMessage().contains(elementId)) {
                  String message = "(Hyperlink) The " + (isDiagram ? "diagrams" : "models") + " elements named \""
                      + elementName + ", ...\" (id: " + elementId + ") found in the rich text description of "
                      + NamingHelper.getElementName(element) + " are not up to date.";
                  return ConstraintStatus.createStatus(ctx, element, ctx.getResultLocus(), "{0}", message);
                }
                return sts;
              }).collect(Collectors.toList());
              failureMessages.clear();
              failureMessages.addAll(updatedResult);
            }
          }
        }
      }
    };

    DescriptionLinkParserHandler descriptionParser = new DescriptionLinkParserHandler(element, ctx,
        linkNameCheckHandler);

    List<IStatus> exceptions = descriptionParser.process(description);
    failureMessages.addAll(exceptions);

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
