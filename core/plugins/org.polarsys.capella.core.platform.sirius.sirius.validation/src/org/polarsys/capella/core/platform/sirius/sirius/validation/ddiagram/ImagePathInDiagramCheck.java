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
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.tools.internal.validation.constraints.ImagePathConstraint;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.handler.validation.CapellaDiagnostician;

/**
 * Check if the images used on diagram elements, can be found.</br>
 * Warning: This rule will load all representation associated to the validation context(Capella element)
 * 
 * @author lfasani
 */
public class ImagePathInDiagramCheck extends ImagePathConstraint {

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
    if (target instanceof CapellaElement) {
      Session.of(target).ifPresent(session -> {
        // This will load the representation.
        Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(target, session);
        for (DRepresentation dRepresentation : representations) {
          Iterable<EObject> iterable = () -> dRepresentation.eAllContents();
          List<WorkspaceImage> workspaceImages = StreamSupport.stream(iterable.spliterator(), false)
              .filter(WorkspaceImage.class::isInstance).map(WorkspaceImage.class::cast).collect(Collectors.toList());

          for (WorkspaceImage workspaceImage : workspaceImages) {
            validateWorkspaceImagePath(workspaceImage, ctx, failureStatuses);
          }
        }
      });
    }

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
