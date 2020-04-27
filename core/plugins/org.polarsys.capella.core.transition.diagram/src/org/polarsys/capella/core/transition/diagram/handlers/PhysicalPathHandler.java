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
package org.polarsys.capella.core.transition.diagram.handlers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramNamingConstants;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class PhysicalPathHandler extends AbstractDiagramHandler {

  @Override
  public boolean handles(IContext context_p, RepresentationDescription description_p) {
    if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.PHYSICAL_PATH_DIAGRAM_NAME)) {
      return true;
    }
    return false;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean covers(IContext context_p, RepresentationDescription description_p) {
    if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.PHYSICAL_PATH_DIAGRAM_NAME)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean backCovers(IContext context_p, RepresentationDescription description_p) {
    if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.PHYSICAL_PATH_DIAGRAM_NAME)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean covers(IContext context_p, DRepresentationDescriptor representation_p) {
      EObject target = representation_p.getTarget();
      if ((target != null) && (target instanceof CapellaElement)) {
        return !CapellaLayerCheckingExt.isAOrInPhysicalLayer((CapellaElement) target) && !CapellaLayerCheckingExt.isAOrInEPBSLayer((CapellaElement) target);
      }
    return false;
  }

  @Override
  public boolean backCovers(IContext context_p, DRepresentationDescriptor representation_p) {
      EObject target = representation_p.getTarget();
      if ((target != null) && (target instanceof CapellaElement)) {
        return !CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) target)
               && !CapellaLayerCheckingExt.isAOrInEPBSLayer((CapellaElement) target);
      }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DiagramElementMapping getTargetMapping(IContext context_p, RepresentationDescription sourceDescription_p,
      RepresentationDescription targetDescription_p, DiagramElementMapping sourceMapping_p, EObject source_p, EObject target_p) {
    return sourceMapping_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public RepresentationDescription getTargetDescription(IContext context_p, Session session_p, RepresentationDescription description_p) {
    DiagramHelper service = DiagramHelper.getService();

    if (service.isA(description_p, IDiagramNameConstants.PHYSICAL_PATH_DIAGRAM_NAME)) {
      return description_p;
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTargetName(IContext context_p, DRepresentation diagram_p, RepresentationDescription targetDescription_p) {
    String name = RepresentationHelper.getRepresentationDescriptor(diagram_p).getName();
    name = name.replace(DiagramNamingConstants.PHYSICAL_PATH_DIAGRAM_LOGICAL_NAME, DiagramNamingConstants.PHYSICAL_PATH_DIAGRAM_PHYSICAL_NAME);
    name = name.replace(DiagramNamingConstants.PHYSICAL_PATH_DIAGRAM_SYSTEM_NAME, DiagramNamingConstants.PHYSICAL_PATH_DIAGRAM_LOGICAL_NAME);

    name = name.replace(DiagramNamingConstants.PHYSICAL_PATH_DIAGRAM_LOGICAL_PREFIX, DiagramNamingConstants.PHYSICAL_PATH_DIAGRAM_PHYSICAL_PREFIX);
    name = name.replace(DiagramNamingConstants.PHYSICAL_PATH_DIAGRAM_SYSTEM_PREFIX, DiagramNamingConstants.PHYSICAL_PATH_DIAGRAM_LOGICAL_PREFIX);
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getTargetDefaultLocation(IContext context_p, BlockArchitecture root_p, RepresentationDescription description_p) {
    return null;
  }

}
