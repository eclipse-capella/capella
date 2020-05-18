/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.diagram.helpers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.diagram.helpers.traceability.IDiagramTraceability;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.context.TransitionContext;
import org.polarsys.capella.core.transition.diagram.handlers.DiagramDescriptionHelper;
import org.polarsys.capella.core.transition.diagram.handlers.IDiagramHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DiagramTransitionTraceability implements IDiagramTraceability {

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

  public boolean isRealizingable(DRepresentationDescriptor realizing) {

    IDiagramHandler handler = getHandler();
    RepresentationDescription sourceDescription = realizing.getDescription();

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
  public boolean isRealizable(DRepresentationDescriptor realized, DRepresentationDescriptor realizing) {
    Session session = SessionManager.INSTANCE.getSession(realized.getTarget());

    IDiagramHandler handler = getHandler();

    // description should be compatible
    RepresentationDescription sourceDescription = realized.getDescription();
    RepresentationDescription targetDescription = realizing.getDescription();
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

    // architecture should be compatible too
    EObject sourceTarget = realized.getTarget();
    EObject targetTarget = realizing.getTarget();
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
