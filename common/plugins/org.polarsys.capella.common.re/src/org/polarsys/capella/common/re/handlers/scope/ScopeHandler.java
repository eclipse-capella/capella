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
package org.polarsys.capella.common.re.handlers.scope;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.options.IPropertyHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.scope.DefaultScopeHandler;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ScopeHandler extends DefaultScopeHandler {

  protected String getTitle() {
    return "Replicable Elements";//$NON-NLS-1$
  }

  protected String getDescription() {
    return "Select options";//$NON-NLS-1$
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context_p) {
    return super.init(context_p);
  }

  @Override
  public IStatus computeScope(Collection<EObject> bootstrap_p, IContext context_p) {
    String scope = (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE);
    IPropertyContext context = ((IPropertyHandler) OptionsHandlerHelper.getInstance(context_p)).getPropertyContext(context_p, scope);

    //don't forget to write all ! some properties needs to be written
    context.writeAll();

    ContextScopeHandlerHelper.getInstance(context_p).clear(ITransitionConstants.TRANSITION_SCOPE, context_p);
    ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.TRANSITION_SCOPE,
        (Collection) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__SCOPE)), context_p);

    return Status.OK_STATUS;

  }

  @Override
  public IStatus dispose(IContext iContext_p) {
    return Status.OK_STATUS;
  }

}
