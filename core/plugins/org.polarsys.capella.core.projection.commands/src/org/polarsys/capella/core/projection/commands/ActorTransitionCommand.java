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
package org.polarsys.capella.core.projection.commands;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.projection.actors.ctx2la.TransformActorsCtx2La;
import org.polarsys.capella.core.projection.actors.la2pa.TransformActorsLa2Pa;
import org.polarsys.capella.core.projection.actors.oa2ctx.TransformActorsOa2Ctx;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.projection.common.TransitionHelper;

/**
 */
public class ActorTransitionCommand extends AbstractTransitionCommand {

  public ActorTransitionCommand(Collection<EObject> rootElements_p) {
    super(rootElements_p);
  }

  public ActorTransitionCommand(Collection<EObject> rootElements_p, IProgressMonitor progressMonitor_p) {
    super(rootElements_p, progressMonitor_p);
  }

  @Override
  public String getName() {
    if ((_rootElements != null) && (_rootElements.size() > 0)) {
      EObject element = _rootElements.iterator().next();
      if (TransitionHelper.getService().isOE2ActorTransitionAvailable(element)) {
        return Messages.transitionOE2Actor_label;
      }
    }

    return Messages.transitionActor_label;
  }

  @Override
  protected Collection<EObject> retrieveModelElements(EObject modelElement_p) {

    EObject element = modelElement_p;
    if (element instanceof Part) {
      if (TriStateBoolean.False.equals(CapellaProjectHelper.isReusableComponentsDriven((Part) element))) {
        element = ((Part) element).getAbstractType();
      }
    }

    return Collections.singleton(element);
  }

  /**
   * @see org.polarsys.capella.core.projection.commands.AbstractTransitionCommand#getTransformation(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  @Override
  protected AbstractTransform getTransformation(EObject element_p) {

    if ((element_p != null) && (element_p instanceof CapellaElement)) {
      CapellaElement element = (CapellaElement) element_p;

      if (CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer(element)) {
        return new TransformActorsOa2Ctx();

      } else if (CapellaLayerCheckingExt.isAOrInContextLayer(element)) {
        return new TransformActorsCtx2La();

      } else if (CapellaLayerCheckingExt.isAOrInLogicalLayer(element)) {
        return new TransformActorsLa2Pa();
      }
    }

    return null;
  }

}
