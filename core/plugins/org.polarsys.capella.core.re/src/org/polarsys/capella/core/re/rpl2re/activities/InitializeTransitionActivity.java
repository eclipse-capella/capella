/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.rpl2re.activities;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.re.handlers.attachment.ReAttachmentHandler;
import org.polarsys.capella.core.re.handlers.attributes.CapellaAttributeHandler;
import org.polarsys.capella.core.re.handlers.location.CapellaLocationHandler;
import org.polarsys.capella.core.re.handlers.replicable.ReplicableElementHandler;
import org.polarsys.capella.core.re.handlers.transformation.CapellaTransformationHandler;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.scope.CompoundScopeRetriever;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class InitializeTransitionActivity
    extends org.polarsys.capella.common.re.rpl2re.activities.InitializeTransitionActivity {

  public static final String ID = InitializeTransitionActivity.class.getCanonicalName();

  @Override
  protected IHandler createDefaultTransformationHandler() {
    return new CapellaTransformationHandler();
  }

  @Override
  protected IHandler createDefaultAttachmentHandler() {
    return new ReAttachmentHandler();
  }

  @Override
  protected IHandler createDefaultAttributeHandler() {
    return new CapellaAttributeHandler();
  }

  @Override
  protected IHandler createDefaultLocationHandler() {
    return new CapellaLocationHandler();
  }

  @Override
  protected IHandler createDefaultReplicableElementHandler() {
    return new ReplicableElementHandler();
  }

  @Override
  protected IStatus initializeScopeRetrieverHandlers(IContext context, CompoundScopeRetriever handler,
      ActivityParameters activityParams) {
    IStatus status = super.initializeScopeRetrieverHandlers(context, handler, activityParams);
    return status;
  }

  @Override
  protected IStatus initializeTransitionSources(IContext context, ActivityParameters activityParams) {
    Collection<Object> selection = (Collection) context.get(ITransitionConstants.TRANSITION_SELECTION);
    Collection<EObject> result = CapellaAdapterHelper.resolveEObjects(selection, true, false);
    context.put(ITransitionConstants.TRANSITION_SOURCES, result);
    return Status.OK_STATUS;
  }

}
