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
import org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper.DescriptionLinkParserHandler;
import org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper.MissingElementHandler;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check if HyperLinks to capella element or diagram does not exist in model any more
 */
public class CapellaElementInDescriptionExistanceCheck extends AbstractValidationRule {

  public List<IStatus> validateElement(EObject element, String description, IValidationContext ctx) {
    List<IStatus> result = new ArrayList<>();
    MissingElementHandler linkParser = new MissingElementHandler(element, ctx);
    DescriptionLinkParserHandler descriptionParser = new DescriptionLinkParserHandler(element, ctx, linkParser);
    List<IStatus> exceptions = descriptionParser.process(description);
    List<IStatus> parserResult = linkParser.getResult();
    result.addAll(parserResult);
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
