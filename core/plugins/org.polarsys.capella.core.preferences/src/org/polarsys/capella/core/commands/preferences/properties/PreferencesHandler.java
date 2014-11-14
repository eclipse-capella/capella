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
package org.polarsys.capella.core.commands.preferences.properties;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionInfo;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.menus.UIElement;

import org.polarsys.capella.core.commands.preferences.service.IItemDescriptor;
import org.polarsys.capella.core.commands.preferences.service.PreferencesItemsRegistry;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public class PreferencesHandler extends AbstractHandler implements IElementUpdater {

  private static HashMap<Command, PreferencesHandler> instances = new HashMap<Command, PreferencesHandler>();

  public static PreferencesHandler getInstance(Command command) {
    if (!instances.containsKey(command)) {
      instances.put(command, new PreferencesHandler(command));
    } else {

      handlerService.deactivateHandler(preferenceHandlerActivations.get(command.getId()));
      instances.remove(command);
      instances.put(command, new PreferencesHandler(command));
    }
    return instances.get(command);

  }


  /*
	 * 
	 */
  private Command currentCommand;

  /*
	 * 
	 */
  private static Map<String, IHandler> pluginHandlers = new HashMap<String, IHandler>(0);

  /*
	 * 
	 */
  private static IEclipsePreferences commandsPreferences;

  private static IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);

  /*
	 * 
	 */
  private boolean isEnabledCommand;

  private static Map<String, IHandlerActivation> preferenceHandlerActivations = new HashMap<String, IHandlerActivation>();


  /**
   * @param command
   */
  private PreferencesHandler(Command command) {
    this.currentCommand = command;
    pluginHandlers.put(command.getId(), command.getHandler());
    commandsPreferences = new InstanceScope().getNode(Activator.PLUGIN_ID);
    activateHandler(command);

  }

  /**
   * @param command_p
   */
  private void activateHandler(Command command_p) {


    IItemDescriptor commandDescriptor = isPreferenceCommandEnabled();
    if ((commandDescriptor != null) && !isEnabled()) {
      activatePreferenceHandler();
    } else {

      IHandlerActivation handlerActivation = handlerService.activateHandler(currentCommand.getId(), pluginHandlers.get(currentCommand.getId()));
      preferenceHandlerActivations.put(currentCommand.getId(), handlerActivation);
    }

  }

  private IItemDescriptor isPreferenceCommandEnabled() {
    IItemDescriptor commandDescriptor = PreferencesItemsRegistry.getInstance().getDescriptor(this.currentCommand.getId());
    isEnabledCommand = commandDescriptor == null ? true : commandsPreferences.getBoolean(this.currentCommand.getId(), commandDescriptor.isEnabledByDefault());
    return commandDescriptor;
  }

  @Override
  public boolean isEnabled() {
    IItemDescriptor commandDescriptor = PreferencesItemsRegistry.getInstance().getDescriptor(currentCommand.getId());
    boolean result = commandDescriptor == null ? true : commandsPreferences.getBoolean(currentCommand.getId(), commandDescriptor.isEnabledByDefault());
    return result;
  };

  /**
	 * 
	 */
  private void activatePreferenceHandler() {
    IHandlerActivation handlerActivation = handlerService.activateHandler(currentCommand.getId(), this, new Expression() {

      @Override
      public EvaluationResult evaluate(IEvaluationContext context_p) throws CoreException {
        Object selection = context_p.getVariable("selection");
        boolean result = true;
        if ((selection != null) && (selection instanceof ISelection)) {
          IItemDescriptor commandDescriptor = PreferencesItemsRegistry.getInstance().getDescriptor(currentCommand.getId());
          result = commandDescriptor == null ? true : commandsPreferences.getBoolean(currentCommand.getId(), commandDescriptor.isEnabledByDefault());
        }

        IItemDescriptor commandDescriptor = PreferencesItemsRegistry.getInstance().getDescriptor(currentCommand.getId());
        result = commandDescriptor == null ? true : commandsPreferences.getBoolean(currentCommand.getId(), commandDescriptor.isEnabledByDefault());

        return EvaluationResult.valueOf(result);
      }

      @Override
      public void collectExpressionInfo(final ExpressionInfo info) {
        info.markDefaultVariableAccessed();
      }

    });
    currentCommand.setHandler(null);
    currentCommand.setHandler(this);

    preferenceHandlerActivations.put(currentCommand.getId(), handlerActivation);

  }

  // }

  /**
   * @param command_p
   */
  private PreferencesHandler(ParameterizedCommand parameterizedCommand) {
    this.currentCommand = parameterizedCommand.getCommand();
    pluginHandlers = new HashMap<String, IHandler>(0);
    pluginHandlers.put(this.currentCommand.getId(), this.currentCommand.getHandler());
    PreferencesHandler.commandsPreferences = new InstanceScope().getNode(Activator.PLUGIN_ID);
  }

  /**
   * {@inheritDoc}
   */

  public Object execute(ExecutionEvent event_p) throws ExecutionException {

    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateElement(UIElement element_p, Map parameters_p) {
    if (!isEnabledCommand) {
      element_p.setIcon(Activator.getImageDescriptor("preference.gif"));
      element_p.setHoverIcon(Activator.getImageDescriptor("preference.gif"));
      element_p.setTooltip("this element is disabled from preferences");
    }

  }

  /**
   * @throws NotDefinedException
   */
  public static void initializePreferenceCommands() {
    ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
    Command[] commands = commandService.getDefinedCommands();
    for (Object object : commands) {
      Command command = (Command) object;
      IItemDescriptor commandDescriptor = PreferencesItemsRegistry.getInstance().getDescriptor(command.getId());

      if ((commandDescriptor != null) && command.isDefined()) {
        Activator.preferencedCommands.add(command.getId());
        getInstance(command);
        commandService.refreshElements(command.getId(), null);

      }

    }

  }
}
