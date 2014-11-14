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
package org.polarsys.capella.common.re.ui.subcommands.handlers;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.emf.common.util.TreeIterator;
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
 *
 */
public class ChildrenScopeHandler extends SubCommandHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(Object evaluationContext_p) {
    Object variable = ((EvaluationContext) evaluationContext_p).getDefaultVariable();
    setBaseEnabled(((variable instanceof Collection) && (!((Collection) variable).isEmpty())));
    super.setEnabled(evaluationContext_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    ISelection selection = HandlerUtil.getCurrentSelection(event_p);
    IRenderer renderer = ExecutionEventUtil.getRenderer(event_p);
    IRendererContext context = ExecutionEventUtil.getRendererContext(event_p);

    Collection<Object> selectiona =
        ((selection == null) || (selection.isEmpty())) ? context.getPropertyContext().getSourceAsList() : ((IStructuredSelection) selection).toList();

    IProperty property = context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__SCOPE);

    Collection<EObject> currentValue = (Collection<EObject>) context.getPropertyContext().getCurrentValue(property);

    try {
      Collection<EObject> result = new HashSet<EObject>(currentValue);
      for (Object selected : selectiona) {
        if (selected instanceof EObject) {
          TreeIterator<EObject> childs = ((EObject) selected).eAllContents();
          while (childs.hasNext()) {
            result.add(childs.next());
          }
        }
      }
      currentValue.clear();
      currentValue.addAll(result);
      context.getPropertyContext().setCurrentValue(property, currentValue);
    } catch (Exception e) {
      //Nothing here
    }

    return null;
  }

}
