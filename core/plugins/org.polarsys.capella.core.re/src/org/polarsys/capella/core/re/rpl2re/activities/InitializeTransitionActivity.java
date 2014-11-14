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
package org.polarsys.capella.core.re.rpl2re.activities;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.core.re.handlers.attachment.ReAttachmentHandler;
import org.polarsys.capella.core.re.handlers.attributes.CapellaAttributeHandler;
import org.polarsys.capella.core.re.handlers.replicable.ReplicableElementHandler;
import org.polarsys.capella.core.re.handlers.scope.CapellaDependenciesHandler;
import org.polarsys.capella.core.re.handlers.transformation.CapellaTransformationHandler;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.scope.CompoundScopeRetriever;
import org.polarsys.capella.core.transition.system.helpers.SemanticHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class InitializeTransitionActivity extends org.polarsys.capella.common.re.rpl2re.activities.InitializeTransitionActivity {

  public static final String ID = InitializeTransitionActivity.class.getCanonicalName();

  @Override
  protected IHandler createDefaultTransformationHandler() {
    return new CapellaTransformationHandler();
  }

  /**
   * @return
   */
  @Override
  protected IHandler createDefaultDependenciesHandler() {
    return new CapellaDependenciesHandler();
  }

  @Override
  protected IHandler createDefaultAttributeHandler() {
    return new CapellaAttributeHandler();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IHandler createDefaultAttachmentHandler() {
    return new ReAttachmentHandler();
  }

  @Override
  protected IHandler createDefaultReplicableElementHandler() {
    return new ReplicableElementHandler();
  }

  @Override
  protected IStatus initializeScopeRetrieverHandlers(IContext context_p, CompoundScopeRetriever handler_p, ActivityParameters activityParams_p) {
    IStatus status = super.initializeScopeRetrieverHandlers(context_p, handler_p, activityParams_p);
    return status;
  }

  @Override
  protected IStatus initializeTransitionSources(IContext context_p, ActivityParameters activityParams_p) {
    Collection<Object> selection = (Collection) context_p.get(ITransitionConstants.TRANSITION_SELECTION);
    Collection<Object> result = SemanticHelper.getSemanticObjects(selection);
    context_p.put(ITransitionConstants.TRANSITION_SOURCES, result);
    return Status.OK_STATUS;
  }

}
