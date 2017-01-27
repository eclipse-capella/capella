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
package org.polarsys.capella.core.flexibility.commands.dynamic.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;

import org.polarsys.capella.core.flexibility.commands.actions.DefaultAction;
import org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider;
import org.polarsys.capella.core.flexibility.commands.dynamic.tools.testgen.TestGenerationProvider;

/**
 */
public class AllPackagedProviders implements IActionsProvider {

  /**
   * @see org.polarsys.capella.core.flexibility.commands.menus.DynamicCreationAction#getActions()
   */
  public Collection<DefaultAction> getActions(Shell shell, ISelectionProvider selectionProvider) {
    List<DefaultAction> actions = new ArrayList<DefaultAction>();

    actions.addAll(new AccessActionsProvider().getActions(shell, selectionProvider));
    actions.addAll(new RenamingActionsProvider().getActions(shell, selectionProvider));
    actions.addAll(new MoveActionsProvider().getActions(shell, selectionProvider));
    actions.addAll(new DiagramActionsProvider().getActions(shell, selectionProvider));
    actions.addAll(new InformationActionsProvider().getActions(shell, selectionProvider));
    actions.addAll(new CleanActionsProvider().getActions(shell, selectionProvider));
    actions.addAll(new CreationActionsProvider().getActions(shell, selectionProvider));

    actions.addAll(new HelperActionsProvider().getActions(shell, selectionProvider));
    actions.addAll(new PropertiesProvider().getActions(shell, selectionProvider));

    actions.addAll(new TestGenerationProvider().getActions(shell, selectionProvider));
    return actions;
  }
}
