/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
  public IStatus init(IContext context) {
    return super.init(context);
  }

  @Override
  public IStatus computeScope(Collection<EObject> bootstrap, IContext context) {
    String scope = (String) context.get(ITransitionConstants.OPTIONS_SCOPE);
    IPropertyContext ctx = ((IPropertyHandler) OptionsHandlerHelper.getInstance(context)).getPropertyContext(context, scope);

    //don't forget to write all ! some properties needs to be written
    ctx.writeAll();

    ContextScopeHandlerHelper.getInstance(context).clear(ITransitionConstants.TRANSITION_SCOPE, context);
    ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.TRANSITION_SCOPE,
        (Collection) ctx.getCurrentValue(ctx.getProperties().getProperty(IReConstants.PROPERTY__SCOPE)), context);

    return Status.OK_STATUS;

  }

  @Override
  public IStatus dispose(IContext iContext) {
    return Status.OK_STATUS;
  }

}
