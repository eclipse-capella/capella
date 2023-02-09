/*******************************************************************************
 * Copyright (c) 2022, 2023 THALES GLOBAL SERVICES.
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

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.diagram.tools.internal.validation.constraints.ImagePathWrappingStatus;
import org.eclipse.sirius.diagram.ui.internal.quickfix.ImageMarkerMassResolution;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * This class represents a marker resolution to replace all the image paths.
 * 
 * @author lfasani
 */
@SuppressWarnings("restriction")
public class ImagePathMassResolver extends AbstractCapellaMarkerResolution {

  protected Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.VALIDATION);

  @Override
  public void run(IMarker marker_p) {
    final List<EObject> modelElements = getModelElements(marker_p);
    if (modelElements.size() < 1)
      return;
    EObject target = modelElements.get(0);
    Optional<Session> sessionOpt = Session.of(target);

    Diagnostic diagnostic = MarkerViewHelper.getDiagnostic(marker_p);
    ImagePathWrappingStatus imagePathStatus = null;
    if (diagnostic instanceof ConstraintStatusDiagnostic
        && ((ConstraintStatusDiagnostic) diagnostic).getConstraintStatus() instanceof ImagePathWrappingStatus) {
      imagePathStatus = (ImagePathWrappingStatus) ((ConstraintStatusDiagnostic) diagnostic).getConstraintStatus();
    }

    if (imagePathStatus != null && sessionOpt.isPresent()) {
      String notReachableImagePath = imagePathStatus.getNotReachableImagePath();
      boolean fixSucceeded = FileProvider.getDefault().exists(new Path(notReachableImagePath), sessionOpt.get());

      if (!fixSucceeded) {
        fixSucceeded = ImageMarkerMassResolution.fixImagePathMassively(sessionOpt.get(), notReachableImagePath);
      }

      if (fixSucceeded) {
        try {
          marker_p.delete();
        } catch (CoreException exception_p) {
          _logger.error("Exception while deleting marker : " + exception_p.toString()); //$NON-NLS-1$
        }
      }
    }
  }

  /**
   * Browse the model upward (from the leaf to the root) and return the first representation found.
   * 
   * @return the representation if found, null otherwise.
   */
  private DRepresentation getRepresentation(DSemanticDecorator dSemanticDecorator) {
    EObject current = dSemanticDecorator;
    while (current != null) {
      if (current instanceof DRepresentation) {
        return (DRepresentation) current;
      }
      current = current.eContainer();
    }
    return null;
  }
}
