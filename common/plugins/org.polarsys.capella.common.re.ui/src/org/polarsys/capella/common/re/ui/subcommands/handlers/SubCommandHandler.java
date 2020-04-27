/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISources;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.IHandlerService;

/**
 *
 */
public abstract class SubCommandHandler extends AbstractHandler {
  
  /**
   * Looks for the selection in the current evaluation context and when not found asks the HandlerUtil.
   * @param event
   * @return
   */
  protected ISelection getSelection(ExecutionEvent event){
    IEclipseContext eContext = PlatformUI.getWorkbench().getService(IEclipseContext.class);
    IEvaluationContext currentState = ((IHandlerService)eContext.get(IHandlerService.class)).getCurrentState();
    ISelection selection = (ISelection)currentState.getVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME);
    return selection != null ? selection : HandlerUtil.getCurrentSelection(event);
  }
  
  /**
   * Interrogate first the current evaluation context retrieved from the workbench context, when nothing found interrogate the passed evaluation context.
   * @param evaluationContext
   * @return
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected Collection<Object> getSelectedObjects(IEvaluationContext evaluationContext){
    Collection<Object> result = new ArrayList<Object>();
    IEclipseContext eContext = PlatformUI.getWorkbench().getService(IEclipseContext.class);
    IEvaluationContext currentState = ((IHandlerService)eContext.get(IHandlerService.class)).getCurrentState();
    
    // Interrogate the current state first
    ISelection selection = (ISelection)currentState.getVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME);
    if(selection != null && selection instanceof IStructuredSelection){
      IStructuredSelection structuredSelection = (IStructuredSelection)selection;
      if(!structuredSelection.toList().isEmpty()){
        result.addAll(structuredSelection.toList());
      }
    }
    // If result still empty interrogate the given evaluation context
    if(result.isEmpty() && evaluationContext != null){
      Object variable = ((IEvaluationContext) evaluationContext).getDefaultVariable();
      if(variable instanceof Collection){
        result.addAll(((Collection)variable));
      }
    }
    return result;
  }
}
