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
   * @param delegated
   * @param serviceLocator
   */
  public SlavePopulatorHandlerService(IHandlerService delegated, IServiceLocator serviceLocator) {
    super();
    _delegated = delegated;
    _serviceLocator = serviceLocator;
  }

  /**
   * @param provider
   * @see org.eclipse.ui.services.IServiceWithSources#addSourceProvider(org.eclipse.ui.ISourceProvider)
   */
  public void addSourceProvider(ISourceProvider provider) {
    _delegated.addSourceProvider(provider);
  }

  /**
   * @param provider
   * @see org.eclipse.ui.services.IServiceWithSources#removeSourceProvider(org.eclipse.ui.ISourceProvider)
   */
  public void removeSourceProvider(ISourceProvider provider) {
    _delegated.removeSourceProvider(provider);
  }

  /**
   * 
   * @see org.eclipse.ui.services.IDisposable#dispose()
   */
  public void dispose() {
    _delegated.dispose();
  }

  /**
   * @param activation
   * @return
   * @see org.eclipse.ui.handlers.IHandlerService#activateHandler(org.eclipse.ui.handlers.IHandlerActivation)
   */
  public IHandlerActivation activateHandler(IHandlerActivation activation) {
    return _delegated.activateHandler(activation);
  }

  /**
   * @param commandId
   * @param handler
   * @return
   * @see org.eclipse.ui.handlers.IHandlerService#activateHandler(java.lang.String, org.eclipse.core.commands.IHandler)
   */
  public IHandlerActivation activateHandler(String commandId, IHandler handler) {
    return _delegated.activateHandler(commandId, handler);
  }

  /**
   * @param commandId
   * @param handler
   * @param expression
   * @return
   * @see org.eclipse.ui.handlers.IHandlerService#activateHandler(java.lang.String, org.eclipse.core.commands.IHandler, org.eclipse.core.expressions.Expression)
   */
  public IHandlerActivation activateHandler(String commandId, IHandler handler, Expression expression) {
    return _delegated.activateHandler(commandId, handler, expression);
  }

  /**
   * @param commandId
   * @param handler
   * @param expression
   * @param global
   * @return
   * @see org.eclipse.ui.handlers.IHandlerService#activateHandler(java.lang.String, org.eclipse.core.commands.IHandler, org.eclipse.core.expressions.Expression, boolean)
   */
  public IHandlerActivation activateHandler(String commandId, IHandler handler, Expression expression, boolean global) {
    return _delegated.activateHandler(commandId, handler, expression, global);
  }

  /**
   * @param commandId
   * @param handler
   * @param expression
   * @param sourcePriorities
   * @return
   * @deprecated
   * @see org.eclipse.ui.handlers.IHandlerService#activateHandler(java.lang.String, org.eclipse.core.commands.IHandler, org.eclipse.core.expressions.Expression, int)
   */
  @Deprecated
  public IHandlerActivation activateHandler(String commandId, IHandler handler, Expression expression, int sourcePriorities) {
    return _delegated.activateHandler(commandId, handler, expression, sourcePriorities);
  }

  /**
   * @param command
   * @param event
   * @return
   * @see org.eclipse.ui.handlers.IHandlerService#createExecutionEvent(org.eclipse.core.commands.Command, org.eclipse.swt.widgets.Event)
   */
  public ExecutionEvent createExecutionEvent(Command command, Event event) {
    return _delegated.createExecutionEvent(command, event);
  }

  /**
   * @param command
   * @param event
   * @return
   * @see org.eclipse.ui.handlers.IHandlerService#createExecutionEvent(org.eclipse.core.commands.ParameterizedCommand, org.eclipse.swt.widgets.Event)
   */
  public ExecutionEvent createExecutionEvent(ParameterizedCommand command, Event event) {
    return _delegated.createExecutionEvent(command, event);
  }

  /**
   * @param activation
   * @see org.eclipse.ui.handlers.IHandlerService#deactivateHandler(org.eclipse.ui.handlers.IHandlerActivation)
   */
  public void deactivateHandler(IHandlerActivation activation) {
    _delegated.deactivateHandler(activation);
  }

  /**
   * @param activations
   * @see org.eclipse.ui.handlers.IHandlerService#deactivateHandlers(java.util.Collection)
   */
  public void deactivateHandlers(Collection activations) {
    _delegated.deactivateHandlers(activations);
  }

  /**
   * @param commandId
   * @param event
   * @return
   * @throws ExecutionException
   * @throws NotDefinedException
   * @throws NotEnabledException
   * @throws NotHandledException
   * @see org.eclipse.ui.handlers.IHandlerService#executeCommand(java.lang.String, org.eclipse.swt.widgets.Event)
   */
  public Object executeCommand(String commandId, Event event) throws ExecutionException, NotDefinedException, NotEnabledException, NotHandledException {
    return _delegated.executeCommand(commandId, event);
  }

  /**
   * @param command
   * @param event
   * @return
   * @throws ExecutionException
   * @throws NotDefinedException
   * @throws NotEnabledException
   * @throws NotHandledException
   * @see org.eclipse.ui.handlers.IHandlerService#executeCommand(org.eclipse.core.commands.ParameterizedCommand, org.eclipse.swt.widgets.Event)
   */
  public Object executeCommand(ParameterizedCommand command, Event event) throws ExecutionException, NotDefinedException, NotEnabledException,
      NotHandledException {
    return command.executeWithChecks(event, getCurrentState());
  }

  /**
   * @param command
   * @param event
   * @param context
   * @return
   * @throws ExecutionException
   * @throws NotDefinedException
   * @throws NotEnabledException
   * @throws NotHandledException
   * @see org.eclipse.ui.handlers.IHandlerService#executeCommandInContext(org.eclipse.core.commands.ParameterizedCommand, org.eclipse.swt.widgets.Event, org.eclipse.core.expressions.IEvaluationContext)
   */
  public Object executeCommandInContext(ParameterizedCommand command, Event event, IEvaluationContext context) throws ExecutionException,
      NotDefinedException, NotEnabledException, NotHandledException {
    return _delegated.executeCommandInContext(command, event, context);
  }

  /**
   * @param includeSelection
   * @return
   * @see org.eclipse.ui.handlers.IHandlerService#createContextSnapshot(boolean)
   */
  public IEvaluationContext createContextSnapshot(boolean includeSelection) {
    return _delegated.createContextSnapshot(includeSelection);
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
   * @param handler
   * @param helpContextId
   * @see org.eclipse.ui.handlers.IHandlerService#setHelpContextId(org.eclipse.core.commands.IHandler, java.lang.String)
   */
  public void setHelpContextId(IHandler handler, String helpContextId) {
    _delegated.setHelpContextId(handler, helpContextId);
  }

}
