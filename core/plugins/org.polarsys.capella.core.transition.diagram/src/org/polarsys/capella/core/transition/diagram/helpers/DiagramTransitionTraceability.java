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
package org.polarsys.capella.core.transition.diagram.helpers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.diagram.helpers.traceability.IDiagramTraceability;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.context.TransitionContext;
import org.polarsys.capella.core.transition.diagram.handlers.DiagramDescriptionHelper;
import org.polarsys.capella.core.transition.diagram.handlers.IDiagramHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DiagramTransitionTraceability implements IDiagramTraceability {

  public static final String ALLOCATING_DIAGRAMS = "INITIALIZATION_REALIZING"; //$NON-NLS-1$

  public static final String ALLOCATED_DIAGRAMS = "INITIALIZATION_REALIZED"; //$NON-NLS-1$

  private IContext context;

  /**
   * @return the handler
   */
  protected IDiagramHandler getHandler() {
    return DiagramDescriptionHelper.getService(getContext());
  }

  /**
   * @return the context_p
   */
  protected IContext getContext() {
    if (context == null) {
      context = new TransitionContext();
    }
    return context;
  }

  public boolean isRealizingable(DRepresentation realizing_p) {

    IDiagramHandler handler = getHandler();
    RepresentationDescription sourceDescription = DiagramHelper.getService().getDescription(realizing_p);

    if ((handler == null) || (sourceDescription == null)) {
      return false;
    }

    if (!handler.backCovers(getContext(), sourceDescription)) {
      return false;
    }

    if (!handler.backCovers(getContext(), realizing_p)) {
      return false;
    }

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isRealizable(DRepresentation realized_p, DRepresentation realizing_p) {
    Session session = DiagramHelper.getService().getSession(realized_p);

    IDiagramHandler handler = getHandler();
    if (handler == null) {
      return false;
    }

    //description should be compatible
    RepresentationDescription sourceDescription = DiagramHelper.getService().getDescription(realized_p);
    RepresentationDescription targetDescription = DiagramHelper.getService().getDescription(realizing_p);
    if ((sourceDescription == null) || (targetDescription == null)) {
      return false;
    }

    if (!handler.covers(context, sourceDescription)) {
      return false;
    }

    if (!handler.covers(context, realized_p)) {
      return false;
    }

    if (!handler.backCovers(context, targetDescription)) {
      return false;
    }

    if (!handler.backCovers(context, realizing_p)) {
      return false;
    }

    RepresentationDescription target = getHandler().getTargetDescription(context, session, sourceDescription);
    if (target == null) {
      return false;
    }
    if (!target.equals(targetDescription)) {
      return false;
    }

    //architecture should be compatible too
    EObject sourceTarget = ((DSemanticDecorator) realized_p).getTarget();
    EObject targetTarget = ((DSemanticDecorator) realizing_p).getTarget();
    if ((sourceTarget == null) || (targetTarget == null)) {
      return false;
    }

    BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(sourceTarget);
    BlockArchitecture targetArchitecture = BlockArchitectureExt.getRootBlockArchitecture(targetTarget);
    if ((sourceArchitecture == null) || (targetArchitecture == null)) {
      return false;
    }

    if (!sourceArchitecture.getAllocatingArchitectures().contains(targetArchitecture)) {
      return false;
    }

    return true;
  }

  @Override
  public Collection<DRepresentation> getRealizingRepresentations(DRepresentation representation_p) {
    return getDiagrams(representation_p, ALLOCATING_DIAGRAMS);
  }

  @Override
  public Collection<DRepresentation> getRealizedRepresentations(DRepresentation representation_p) {
    return getDiagrams(representation_p, ALLOCATED_DIAGRAMS);
  }

  protected Collection<DRepresentation> getDiagrams(DRepresentation representation_p, String annotationId_p) {
    Collection<DRepresentation> diagrams = new ArrayList<DRepresentation>();

    DAnnotation annotation = RepresentationHelper.getAnnotation(annotationId_p, representation_p);
    if (annotation == null) {
      //Avoid any checkout
      return diagrams;
    }

    Session session = DiagramHelper.getService().getSession(representation_p);
    if (session != null) {
      for (Resource resource : session.getAllSessionResources()) {
        if (annotation.getDetails() != null) {
          for (String value : annotation.getDetails().values()) {
            try {
              if ((value == null) || value.isEmpty()) {
                continue;
              }
              EObject element = resource.getEObject(value);
              if ((element != null) && !(element.eIsProxy()) && (element instanceof DRepresentation)) {
                if (!diagrams.contains(element)) {
                  diagrams.add((DRepresentation) element);
                }
              }
            } catch (Exception e) {
              //Nothing to worry here
            }
          }
        }
      }
    }
    return diagrams;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus addRealizingRepresentation(DRepresentation realized_p, DRepresentation realizing_p) {
    addAnnotation(realized_p, realizing_p, ALLOCATING_DIAGRAMS);
    addAnnotation(realizing_p, realized_p, ALLOCATED_DIAGRAMS);
    return Status.OK_STATUS;
  }

  protected IStatus addAnnotation(DRepresentation realized_p, DRepresentation realizing_p, String annotationId_p) {
    DAnnotation annotation = RepresentationHelper.getAnnotation(annotationId_p, realized_p);
    if (annotation == null) {
      annotation = RepresentationHelper.createAnnotation(annotationId_p, realized_p);
    }

    String id = realizing_p.eResource().getURIFragment(realizing_p);
    for (String value : annotation.getDetails().values()) {
      if ((value != null) && value.equals(id)) {
        return Status.OK_STATUS;
      }
    }
    annotation.getDetails().put("id_" + annotation.getDetails().size(), id);
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    if (context != null) {
      context.reset();
      context = null;
    }
  }

}
