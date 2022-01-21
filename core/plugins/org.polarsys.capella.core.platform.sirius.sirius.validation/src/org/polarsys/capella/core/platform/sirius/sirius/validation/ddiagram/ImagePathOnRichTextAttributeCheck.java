/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
import java.util.stream.Stream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.tools.internal.validation.constraints.ImagePathConstraint;
import org.eclipse.sirius.diagram.tools.internal.validation.constraints.ImagePathWrappingStatus;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.handler.validation.CapellaDiagnostician;

/**
 * Check if images can be found for images used in the rich text description of the capella element, diagram description
 * or other rich text attributes.
 * 
 * @author lfasani
 */
@SuppressWarnings("restriction")
public class ImagePathOnRichTextAttributeCheck extends ImagePathConstraint {

  @Override
  public IStatus validate(final IValidationContext ctx) {
    // Ugly code to check only if called by the Capella Validation
    // Indeed, otherwise, this code is called many times by Sirius and it is too impacting to change Sirius.
    boolean calledByCapella = Stream.of(Thread.currentThread().getStackTrace())
        .filter(ste -> ste.getClassName().equals(CapellaDiagnostician.class.getName())).findFirst().isPresent();
    if (!calledByCapella) {
      return ctx.createSuccessStatus();
    }

    EObject target = ctx.getTarget();
    List<IStatus> failureStatuses = new ArrayList<>();

    // Check the DRepresentationDescriptor.description
    if (target instanceof CapellaElement) {
      Session.of(target).ifPresent(session -> {
        Collection<DRepresentationDescriptor> representationDescriptors = DialectManager.INSTANCE
            .getRepresentationDescriptors(target, session);
        for (DRepresentationDescriptor dRepresentationDescriptor : representationDescriptors) {
          failureStatuses.addAll(validateImagePathInRichText(dRepresentationDescriptor, ctx,
              ImagePathWrappingStatus.ImagePathTarget.DREPRESENTATION_DESCRIPTOR));
        }
      });
    }

    // check the element itself (may not be a Capella element)
    failureStatuses
        .addAll(validateImagePathInRichText(target, ctx, ImagePathWrappingStatus.ImagePathTarget.SEMANTIC_TARGET));

    IStatus returnedStatus = null;
    if (failureStatuses.isEmpty()) {
      returnedStatus = ctx.createSuccessStatus();
    } else {
      if (failureStatuses.size() == 1) {
        returnedStatus = failureStatuses.get(0);
      } else {
        returnedStatus = ConstraintStatus.createMultiStatus(ctx, failureStatuses);
      }
    }
    return returnedStatus;
  }
}
