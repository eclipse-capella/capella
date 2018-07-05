/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DiagramTransitionTraceability implements IDiagramTraceability {

  public static final String allocating_diagrams = "INITIALIZATION_REALIZING"; //$NON-NLS-1$

  public static final String allocated_diagrams = "INITIALIZATION_REALIZED"; //$NON-NLS-1$

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

  public boolean isRealizingable(DRepresentation realizing) {

    IDiagramHandler handler = getHandler();
    RepresentationDescription sourceDescription = DiagramHelper.getService().getDescription(realizing);

    if ((handler == null) || (sourceDescription == null)) {
      return false;
    }

    if (!handler.backCovers(getContext(), sourceDescription)) {
      return false;
    }

    if (!handler.backCovers(getContext(), realizing)) {
      return false;
    }

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isRealizable(DRepresentation realized, DRepresentation realizing) {
    Session session = DiagramHelper.getService().getSession(realized);

    IDiagramHandler handler = getHandler();

    //description should be compatible
    RepresentationDescription sourceDescription = DiagramHelper.getService().getDescription(realized);
    RepresentationDescription targetDescription = DiagramHelper.getService().getDescription(realizing);
    if ((sourceDescription == null) || (targetDescription == null)) {
      return false;
    }

    if (!handler.covers(context, sourceDescription)) {
      return false;
    }

    if (!handler.covers(context, realized)) {
      return false;
    }

    if (!handler.backCovers(context, targetDescription)) {
      return false;
    }

    if (!handler.backCovers(context, realizing)) {
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
    EObject sourceTarget = ((DSemanticDecorator) realized).getTarget();
    EObject targetTarget = ((DSemanticDecorator) realizing).getTarget();
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
  public Collection<DRepresentation> getRealizingRepresentations(DRepresentation representation) {
    return getDiagrams(representation, allocating_diagrams);
  }

  @Override
  public Collection<DRepresentation> getRealizedRepresentations(DRepresentation representation) {
    return getDiagrams(representation, allocated_diagrams);
  }

  protected Collection<DRepresentation> getDiagrams(DRepresentation representation, String annotationId) {
    Collection<DRepresentation> diagrams = new ArrayList<DRepresentation>();

    DAnnotation annotation = RepresentationHelper.getAnnotation(annotationId, representation);
    if (annotation == null) {
      //Avoid any checkout
      return diagrams;
    }

    Session session = DiagramHelper.getService().getSession(representation);
    if (session != null) {
      for (final Resource resource : session.getAllSessionResources()) {
        if (annotation.getDetails() != null) {
          for (String value : annotation.getDetails().values()) {
            try {
              if ((value == null) || value.isEmpty()) {
                continue;
              }
              // Bug 2014 - Impacts of new UID implementation while transitioning diagrams
              // We initialize the scope for IdManager with the current resource in the loop as same as the previous implementation.
              EObject element = IdManager.getInstance().getEObject(value, new IScope() {
                
                @Override
                public List<Resource> getResources() {
                  return Arrays.asList(resource);
                }
              });
              if (element != null && !element.eIsProxy() && (element instanceof DRepresentation) 
            		  && !diagrams.contains(element)) {
                  diagrams.add((DRepresentation) element);
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
  public IStatus addRealizingRepresentation(DRepresentation realized, DRepresentation realizing) {
    addAnnotation(realized, realizing, allocating_diagrams);
    addAnnotation(realizing, realized, allocated_diagrams);
    return Status.OK_STATUS;
  }

  protected IStatus addAnnotation(DRepresentation realized, DRepresentation realizing, String annotationId) {
    DAnnotation annotation = RepresentationHelper.getAnnotation(annotationId, realized);
    if (annotation == null) {
      annotation = RepresentationHelper.createAnnotation(annotationId, realized);
    }
    // Bug 2014 - Impacts of new UID implementation while transitioning diagrams
    // Using IdManager in order to take into account the new UID implementation.
    String id = IdManager.getInstance().getId(realizing);
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
