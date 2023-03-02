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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper.DescriptionLinkParserHandler;
import org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper.InvalidNameHandler;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check if HyperLinks to capella element or diagram in the description are up to date
 */
public class CapellaElementInDescriptionNameCheck extends AbstractValidationRule {

  public IStatus validateElement(EObject element, String description, final IValidationContext ctx) {
    List<IStatus> failureMessages = new ArrayList<>();
    InvalidNameHandler linkParser = new InvalidNameHandler(element, ctx);

    DescriptionLinkParserHandler descriptionParser = new DescriptionLinkParserHandler(element, ctx, linkParser);

    List<IStatus> exceptions = descriptionParser.process(description);
    List<IStatus> parserResult = linkParser.getResult();
    failureMessages.addAll(parserResult);
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
