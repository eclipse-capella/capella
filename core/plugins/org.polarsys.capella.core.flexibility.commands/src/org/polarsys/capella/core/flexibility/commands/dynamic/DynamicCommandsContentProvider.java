/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.flexibility.commands.dynamic;

import java.util.Collection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionInfo;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandImageService;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.commands.CommandImageService;

import org.polarsys.capella.core.flexibility.commands.actions.DefaultAction;
import org.polarsys.capella.core.flexibility.commands.dynamic.tools.AllPackagedProviders;
import org.polarsys.capella.core.flexibility.commands.menus.provider.CommandsContentProvider;

/**
 */
public class DynamicCommandsContentProvider extends CommandsContentProvider implements IActionsProvider {

  Collection<DefaultAction> actions = null;

  public Collection<DefaultAction> getActions(Shell shell, ISelectionProvider selectionProvider) {
    return new AllPackagedProviders().getActions(shell, selectionProvider);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    super.inputChanged(viewer, oldInput, newInput);
    ICommandService cmdService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);

    if (null != currentInput) {
      actions = getActions(currentInput.shell, currentInput.selectionProvider);

      for (DefaultAction action : actions) {
        String idCategory = action.getCategory();
        registerCategory(idCategory, null);
        Category category = cmdService.getCategory(idCategory);
        registerCommand(action, category);
      }
    }

  }

  protected void registerCommand(Object object, Category parentCategory) {
    ICommandService cmdService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
    IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
    ICommandImageService imgService = (ICommandImageService) PlatformUI.getWorkbench().getService(ICommandImageService.class);

    if (object instanceof DefaultAction) {
      final DefaultAction action = (DefaultAction) object;
      Command eatTaco = cmdService.getCommand(action.getId());
      if (!eatTaco.isDefined()) {
        eatTaco.define(action.getText(), action.getDescription(), parentCategory);
        //Register defined image
        ((CommandImageService) imgService).bind(action.getId(), ICommandImageService.TYPE_DEFAULT, ICommandImageService.IMAGE_STYLE_DEFAULT,
            action.getImageDescriptor());

        final IHandler handler = new AbstractHandler() {
          public Object execute(ExecutionEvent event) throws ExecutionException {
            action.run();
            return event;
          }

          /**
           * {@inheritDoc}
           */
          @Override
          public boolean isEnabled() {
            return action.isEnabled();
          }

        };

        handlerService.activateHandler(eatTaco.getId(), handler, new Expression() {

          @Override
          public EvaluationResult evaluate(IEvaluationContext context) throws CoreException {
            Object selection = context.getVariable("selection");
            boolean result = true;
            if ((selection != null) && (selection instanceof ISelection)) {
              currentInput.selectionProvider.setSelection((ISelection) selection);
              result = action.isEnabled();
            }
            return EvaluationResult.valueOf(result);
          }

          @Override
          public void collectExpressionInfo(final ExpressionInfo info) {
            info.markDefaultVariableAccessed();
          }

        });
      }

    }

  }

  /**
   * @param subCategory
   * @param category
   */
  protected void registerCategory(String categoryId, Category parent) {
    ICommandService cmdService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
    Category lunch = cmdService.getCategory(categoryId);
    if (!lunch.isDefined()) {
      lunch.define(getName(categoryId), getName(categoryId) + " commands");
    }
  }

  public String getName(String categoryId) {
    String[] ides = categoryId.split("\\.");
    String value = categoryId;
    if (ides.length != 0) {
      value = ides[ides.length - 1];
    }
    return Character.toString((char) (value.charAt(0) - 'a' + 'A')) + value.substring(1);
  }

}
