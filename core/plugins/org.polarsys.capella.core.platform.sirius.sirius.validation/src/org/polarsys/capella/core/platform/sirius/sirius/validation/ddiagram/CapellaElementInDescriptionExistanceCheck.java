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

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check if HyperLinks to capella element or diagram does not exist in model any more
 */
public class CapellaElementInDescriptionExistanceCheck extends AbstractValidationRule {

  public List<IStatus> validateElement(EObject element, String description, IValidationContext ctx) {
    List<IStatus> result = new ArrayList<>();
    ILinkParserHandler linkParserHandler = new ILinkParserHandler() {
      private List<LinkDescription> parsedLinks = new ArrayList<>();

      @Override
      public void handleParsedLink(LinkDescription parsedLink) {
        if (parsedLink.getTargetElement() == null) {
          String elementName = NamingHelper.getElementName(element);
          String elementId = parsedLink.getHref().replace("hlink://", "");
          if (!parsedLinks.contains(parsedLink)) {
            parsedLinks.add(parsedLink);
            String failureMessage = "(Hyperlink) The model/diagram element named “" + parsedLink.getName() + "” (id: "
                + elementId + ") can not be found for the rich text description of the element " + elementName;
            result.add(ConstraintStatus.createStatus(ctx, element, ctx.getResultLocus(), "{0}", failureMessage));
          } else {
            List<IStatus> updatedResult = result.stream().map(sts -> {
              if (sts.getMessage().contains(elementId)) {
                String name = DescriptionLinkParserHandler.extractName(sts.getMessage());
                String failureMessage = "(Hyperlink) The model/diagram elements named “" + name + ", ...” (id: "
                    + elementId + ") can not be found for the rich text description of the element " + elementName;
                return ConstraintStatus.createStatus(ctx, element, ctx.getResultLocus(), "{0}", failureMessage);
              }
              return sts;
            }).collect(Collectors.toList());
            result.clear();
            result.addAll(updatedResult);
          }
        }

      }
    };

    DescriptionLinkParserHandler descriptionParser = new DescriptionLinkParserHandler(element, ctx, linkParserHandler);

    List<IStatus> exceptions = descriptionParser.process(description);
    result.addAll(exceptions);

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
