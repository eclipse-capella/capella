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
package org.polarsys.capella.common.flexibility.wizards.ui.util;

import java.util.Collection;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.ISourceProvider;
import org.eclipse.ui.ISources;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.services.IServiceLocator;

/**
 * A handler service which delegates almost all responsibility to the parent
 * service. It is only responsible for disposing of locally activated handlers
 * when it is disposed.
 * <p>
 * This class is not intended for use outside of the
 * <code>org.eclipse.ui.workbench</code> plug-in.
 * </p>
 * 
 * @since 3.2
 */
public class SlavePopulatorHandlerService implements IHandlerService {

  IHandlerService _delegated;

  IServiceLocator _serviceLocator;

  /**
   * @param delegated_p
   * @param serviceLocator_p
   */
  public SlavePopulatorHandlerService(IHandlerService delegated_p, IServiceLocator serviceLocator_p) {
    super();
    _delegated = delegated_p;
    _serviceLocator = serviceLocator_p;
  }

  /**
   * @param provider_p
   * @see org.eclipse.ui.services.IServiceWithSources#addSourceProvider(org.eclipse.ui.ISourceProvider)
   */
  public void addSourceProvider(ISourceProvider provider_p) {
    _delegated.addSourceProvider(provider_p);
  }

  /**
   * @param provider_p
   * @see org.eclipse.ui.services.IServiceWithSources#removeSourceProvider(org.eclipse.ui.ISourceProvider)
   */
  public void removeSourceProvider(ISourceProvider provider_p) {
    _delegated.removeSourceProvider(provider_p);
  }

  /**
   * 
   * @see org.eclipse.ui.services.IDisposable#dispose()
   */
  public void dispose() {
    _delegated.dispose();
  }

  /**
   * @param activation_p
   * @return
   * @see org.eclipse.ui.handlers.IHandlerService#activateHandler(org.eclipse.ui.handlers.IHandlerActivation)
   */
  public IHandlerActivation activateHandler(IHandlerActivation activation_p) {
    return _delegated.activateHandler(activation_p);
  }

  /**
   * @param commandId_p
   * @param handler_p
   * @return
   * @see org.eclipse.ui.handlers.IHandlerService#activateHandler(java.lang.String, org.eclipse.core.commands.IHandler)
   */
  public IHandlerActivation activateHandler(String commandId_p, IHandler handler_p) {
    return _delegated.activateHandler(commandId_p, handler_p);
  }

  /**
   * @param commandId_p
   * @param handler_p
   * @param expression_p
   * @return
   * @see org.eclipse.ui.handlers.IHandlerService#activateHandler(java.lang.String, org.eclipse.core.commands.IHandler, org.eclipse.core.expressions.Expression)
   */
  public IHandlerActivation activateHandler(String commandId_p, IHandler handler_p, Expression expression_p) {
    return _delegated.activateHandler(commandId_p, handler_p, expression_p);
  }

  /**
   * @param commandId_p
   * @param handler_p
   * @param expression_p
   * @param global_p
   * @return
   * @see org.eclipse.ui.handlers.IHandlerService#activateHandler(java.lang.String, org.eclipse.core.commands.IHandler, org.eclipse.core.expressions.Expression, boolean)
   */
  public IHandlerActivation activateHandler(String commandId_p, IHandler handler_p, Expression expression_p, boolean global_p) {
    return _delegated.activateHandler(commandId_p, handler_p, expression_p, global_p);
  }

  /**
   * @param commandId_p
   * @param handler_p
   * @param expression_p
   * @param sourcePriorities_p
   * @return
   * @deprecated
   * @see org.eclipse.ui.handlers.IHandlerService#activateHandler(java.lang.String, org.eclipse.core.commands.IHandler, org.eclipse.core.expressions.Expression, int)
   */
  @Deprecated
  public IHandlerActivation activateHandler(String commandId_p, IHandler handler_p, Expression expression_p, int sourcePriorities_p) {
    return _delegated.activateHandler(commandId_p, handler_p, expression_p, sourcePriorities_p);
  }

  /**
   * @param command_p
   * @param event_p
   * @return
   * @see org.eclipse.ui.handlers.IHandlerService#createExecutionEvent(org.eclipse.core.commands.Command, org.eclipse.swt.widgets.Event)
   */
  public ExecutionEvent createExecutionEvent(Command command_p, Event event_p) {
    return _delegated.createExecutionEvent(command_p, event_p);
  }

  /**
   * @param command_p
   * @param event_p
   * @return
   * @see org.eclipse.ui.handlers.IHandlerService#createExecutionEvent(org.eclipse.core.commands.ParameterizedCommand, org.eclipse.swt.widgets.Event)
   */
  public ExecutionEvent createExecutionEvent(ParameterizedCommand command_p, Event event_p) {
    return _delegated.createExecutionEvent(command_p, event_p);
  }

  /**
   * @param activation_p
   * @see org.eclipse.ui.handlers.IHandlerService#deactivateHandler(org.eclipse.ui.handlers.IHandlerActivation)
   */
  public void deactivateHandler(IHandlerActivation activation_p) {
    _delegated.deactivateHandler(activation_p);
  }

  /**
   * @param activations_p
   * @see org.eclipse.ui.handlers.IHandlerService#deactivateHandlers(java.util.Collection)
   */
  public void deactivateHandlers(Collection activations_p) {
    _delegated.deactivateHandlers(activations_p);
  }

  /**
   * @param commandId_p
   * @param event_p
   * @return
   * @throws ExecutionException
   * @throws NotDefinedException
   * @throws NotEnabledException
   * @throws NotHandledException
   * @see org.eclipse.ui.handlers.IHandlerService#executeCommand(java.lang.String, org.eclipse.swt.widgets.Event)
   */
  public Object executeCommand(String commandId_p, Event event_p) throws ExecutionException, NotDefinedException, NotEnabledException, NotHandledException {
    return _delegated.executeCommand(commandId_p, event_p);
  }

  /**
   * @param command_p
   * @param event_p
   * @return
   * @throws ExecutionException
   * @throws NotDefinedException
   * @throws NotEnabledException
   * @throws NotHandledException
   * @see org.eclipse.ui.handlers.IHandlerService#executeCommand(org.eclipse.core.commands.ParameterizedCommand, org.eclipse.swt.widgets.Event)
   */
  public Object executeCommand(ParameterizedCommand command_p, Event event_p) throws ExecutionException, NotDefinedException, NotEnabledException,
      NotHandledException {
    return command_p.executeWithChecks(event_p, getCurrentState());
  }

  /**
   * @param command_p
   * @param event_p
   * @param context_p
   * @return
   * @throws ExecutionException
   * @throws NotDefinedException
   * @throws NotEnabledException
   * @throws NotHandledException
   * @see org.eclipse.ui.handlers.IHandlerService#executeCommandInContext(org.eclipse.core.commands.ParameterizedCommand, org.eclipse.swt.widgets.Event, org.eclipse.core.expressions.IEvaluationContext)
   */
  public Object executeCommandInContext(ParameterizedCommand command_p, Event event_p, IEvaluationContext context_p) throws ExecutionException,
      NotDefinedException, NotEnabledException, NotHandledException {
    return _delegated.executeCommandInContext(command_p, event_p, context_p);
  }

  /**
   * @param includeSelection_p
   * @return
   * @see org.eclipse.ui.handlers.IHandlerService#createContextSnapshot(boolean)
   */
  public IEvaluationContext createContextSnapshot(boolean includeSelection_p) {
    return _delegated.createContextSnapshot(includeSelection_p);
  }

  /**
   * @return
   * @see org.eclipse.ui.handlers.IHandlerService#getCurrentState()
   */
  public IEvaluationContext getCurrentState() {
    IEvaluationContext parentA = _delegated.getCurrentState();
    Object value = ((ISelectionService) _serviceLocator.getService(ISelectionService.class)).getSelection();
    if (value == null) {
      value = new StructuredSelection();
    }
    IEvaluationContext context = new EvaluationContext(parentA, ((IStructuredSelection) value).toList());
    context.addVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME, value);
    return context;
  }

  /**
   * 
   * @see org.eclipse.ui.handlers.IHandlerService#readRegistry()
   */
  public void readRegistry() {
    _delegated.readRegistry();
  }

  /**
   * @param handler_p
   * @param helpContextId_p
   * @see org.eclipse.ui.handlers.IHandlerService#setHelpContextId(org.eclipse.core.commands.IHandler, java.lang.String)
   */
  public void setHelpContextId(IHandler handler_p, String helpContextId_p) {
    _delegated.setHelpContextId(handler_p, helpContextId_p);
  }

}
