/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.diagram.handlers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 * A handler to all common diagrams between phases (same description between phases)
 */
public class CommonHandler extends AbstractDiagramHandler {

  @Override
  public boolean handles(IContext context_p, RepresentationDescription description_p) {
    DiagramHelper service = DiagramHelper.getService();

    if (service.isA(description_p, DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (service.isA(description_p, IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME)) {
      return true;

    } else if (service.isA(description_p, IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME)) {
      return true;

    } else if (service.isA(description_p, IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME)) {
      return true;

    } else if (service.isA(description_p, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (service.isA(description_p, IDiagramNameConstants.MODES_AND_STATES_DIAGRAM_NAME)) {
      return true;

    }

    return false;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean covers(IContext context_p, RepresentationDescription description_p) {
    return handles(context_p, description_p);
  }

  @Override
  public boolean backCovers(IContext context_p, RepresentationDescription description_p) {
    return handles(context_p, description_p);
  }

  @Override
  public boolean covers(IContext context_p, DRepresentation representation_p) {
    if (representation_p instanceof DSemanticDecorator) {
      EObject target = ((DSemanticDecorator) representation_p).getTarget();
      if ((target != null) && (target instanceof CapellaElement)) {
        return !CapellaLayerCheckingExt.isAOrInPhysicalLayer((CapellaElement) target) && !CapellaLayerCheckingExt.isAOrInEPBSLayer((CapellaElement) target);
      }
    }
    return false;
  }

  @Override
  public boolean backCovers(IContext context_p, DRepresentation representation_p) {
    if (representation_p instanceof DSemanticDecorator) {
      EObject target = ((DSemanticDecorator) representation_p).getTarget();
      if ((target != null) && (target instanceof CapellaElement)) {
        return !CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) target)
               && !CapellaLayerCheckingExt.isAOrInEPBSLayer((CapellaElement) target);
      }
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DiagramElementMapping getTargetMapping(IContext context_p, RepresentationDescription sourceDescription_p,
      RepresentationDescription targetDescription_p, DiagramElementMapping sourceMapping_p, EObject source_p, EObject target_p) {

    if (sourceDescription_p == targetDescription_p) {
      return sourceMapping_p;
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public RepresentationDescription getTargetDescription(IContext context_p, Session session_p, RepresentationDescription description_p) {
    DiagramHelper service = DiagramHelper.getService();

    if (service.isA(description_p, DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME)) {
      return description_p;

    } else if (service.isA(description_p, IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME)) {
      return description_p;

    } else if (service.isA(description_p, IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME)) {
      return description_p;

    } else if (service.isA(description_p, IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME)) {
      return description_p;

    } else if (service.isA(description_p, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME)) {
      return description_p;

    } else if (service.isA(description_p, IDiagramNameConstants.MODES_AND_STATES_DIAGRAM_NAME)) {
      return description_p;

    }

    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTargetName(IContext context_p, DRepresentation diagram_p, RepresentationDescription targetDescription_p) {
    String name = diagram_p.getName();
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getTargetDefaultLocation(IContext context_p, BlockArchitecture root_p, RepresentationDescription description_p) {

    //common.odesign
    if (DiagramHelper.getService().isA(description_p, DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getDataPkg(root_p);

    } else if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getFirstComponent(root_p);

    } else if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getFirstComponent(root_p);

    } else if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getFirstComponent(root_p);

    } else if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getFirstComponent(root_p);

    }

    return null;
  }

}
