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
package org.polarsys.capella.core.ui.toolkit;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.helpers.TransactionHelper;

public class AbstractCommandActionHandler extends BaseSelectionListenerAction {

  public AbstractCommandActionHandler(String text_p) {
    super(text_p);
  }

  protected WeakReference<Command> command;

  protected void setCommand(Command command_p) {
    command = new WeakReference<Command>(command_p);
  }

  /**
   * This simply execute the command.
   */
  @Override
  public void run() {
    EditingDomain editingDomain = getEditingDomain(getStructuredSelection());
    if (null != editingDomain) {
      editingDomain.getCommandStack().execute(getCommand());
    }
  }

  protected EditingDomain getEditingDomain(IStructuredSelection selection_p) {
    if (null != selection_p) {
      Collection<EObject> collection = filterSelection(selection_p.toList());
      return TransactionHelper.getEditingDomain(collection);
    }
    return null;
  }

  @Override
  public boolean updateSelection(IStructuredSelection selection) {
    if (command != null) {
      command = null;
    }
    Command c = getCommand();
    return (c != null) && c.canExecute();
  }

  /**
   * Return a new list from the selection with some filtered elements if required
   */
  protected Collection<EObject> filterSelection(Collection<Object> list_p) {
    ArrayList<EObject> temporarySelection = new ArrayList<EObject>((List) list_p);
    Iterator<?> iterator = temporarySelection.iterator();
    while (iterator.hasNext()) {
      Object object = iterator.next();
      if (!(object instanceof EObject)) {
        iterator.remove();
      }
    }
    return temporarySelection;
  }

  /**
   * @return
   */
  protected Command getCommand() {
    Command result = null;
    if (command != null) {
      result = command.get();
    }
    if (result == null) {
      Collection<EObject> collection = filterSelection(getStructuredSelection().toList());
      result = createCommand((Collection) collection);
      setCommand(result);
    }
    return result;
  }

  protected Command createUnexecutableCommand() {
    return UnexecutableCommand.INSTANCE;
  }

  public Command createCommand(Collection<Object> selection) {
    return createUnexecutableCommand();
  }
}
