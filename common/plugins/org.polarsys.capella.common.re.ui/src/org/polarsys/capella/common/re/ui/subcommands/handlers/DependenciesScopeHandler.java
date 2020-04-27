/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.re.ui.subcommands.handlers;

import java.util.Collection;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.ui.util.ExecutionEventUtil;
import org.polarsys.capella.common.re.constants.IReConstants;

/**
 */
public class DependenciesScopeHandler extends SubCommandHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ISelection selection = getSelection(event);
    if (selection != null && selection instanceof IStructuredSelection) {
      IRendererContext context = ExecutionEventUtil.getRendererContext(event);
      IProperty property = context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__SCOPE);
      Collection<EObject> currentValue = (Collection<EObject>) context.getPropertyContext().getCurrentValue(property);

      try {
        IProperty dependenciesProperty = context.getPropertyContext().getProperties().getProperty("dependencies");
        Collection<EObject> dependencies = (Collection) context.getPropertyContext()
            .getCurrentValue(dependenciesProperty);
        currentValue.addAll(dependencies);
        context.getPropertyContext().setCurrentValue(property, currentValue);
      } catch (Exception e) {
        // Nothing here
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(Object evaluationContext) {
    if (evaluationContext != null) {
      IEvaluationContext context = (IEvaluationContext) evaluationContext;
      IRendererContext rendererContext = ExecutionEventUtil.getRendererContext(context);
      if ((rendererContext != null) && (rendererContext.getPropertyContext() != null)
          && (rendererContext.getPropertyContext().getProperties() != null)) {
        IProperty dependenciesProperty = rendererContext.getPropertyContext().getProperties()
            .getProperty("dependencies");
        if (dependenciesProperty != null) {
          Collection<EObject> values = (Collection<EObject>) rendererContext.getPropertyContext()
              .getCurrentValue(dependenciesProperty);
          setBaseEnabled(!values.isEmpty());
        }
      }
    }
  }
}
