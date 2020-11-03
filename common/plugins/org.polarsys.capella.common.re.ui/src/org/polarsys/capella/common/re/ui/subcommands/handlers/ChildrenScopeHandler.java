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

package org.polarsys.capella.common.re.ui.subcommands.handlers;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
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
  public void setEnabled(Object evaluationContext) {
    Collection<Object> selectedObjects = getSelectedObjects((IEvaluationContext) evaluationContext);
    setBaseEnabled(!selectedObjects.isEmpty());
    super.setEnabled(evaluationContext);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ISelection selection = getSelection(event);
    if(selection != null && selection instanceof IStructuredSelection){
      IRendererContext context = ExecutionEventUtil.getRendererContext(event);
      
      Collection<Object> selectiona =
          ((selection.isEmpty())) ? context.getPropertyContext().getSourceAsList() : ((IStructuredSelection) selection).toList();
          
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
    }
    return null;
  }
}
