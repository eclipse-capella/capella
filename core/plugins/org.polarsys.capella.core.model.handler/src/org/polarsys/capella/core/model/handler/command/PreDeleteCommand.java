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
package org.polarsys.capella.core.model.handler.command;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * The very same command as {@link DeleteCommand} but it does nothing to the model.<br>
 * Instead, it creates EMF notifications of what will be done, letting the user confirm the behavior.
 */
public class PreDeleteCommand extends DeleteCommand {
  /**
   * Pre-delete handler.
   */
  private PreDeleteHandler _handler;

  /**
   * Constructor.
   * @param editingDomain_p
   * @param elements_p
   * @param handler_p The resulting notifications chain, faking the delete behavior, plus shared data.
   */
  public PreDeleteCommand(EditingDomain editingDomain_p, Collection<?> elements_p, PreDeleteHandler handler_p) {
    super(editingDomain_p, elements_p);
    _handler = handler_p;
  }

  /**
   * @see org.polarsys.capella.core.model.handler.command.DeleteCommand#doPrepare()
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void doPrepare() {
    append(new PreRemoveCommand((Collection<EObject>) _elementsToDelete, _handler));
  }

  /**
   * @see org.polarsys.capella.core.model.handler.command.DeleteStructureCommand#deletePointingReference(org.eclipse.emf.ecore.EObject,
   *      org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected void deletePointingReference(EObject referencingEObject_p, EStructuralFeature feature_p, EObject referenceToDelete_p) {
    if (feature_p.isMany()) {
      _handler._notifications.add(PreRemoveCommand.createNotification((InternalEObject) referencingEObject_p, Notification.REMOVE, referenceToDelete_p,
          feature_p));
    } else {
      _handler._notifications
          .add(PreRemoveCommand.createNotification((InternalEObject) referencingEObject_p, Notification.SET, referenceToDelete_p, feature_p));
    }
  }

  /**
   * @see org.eclipse.emf.common.command.CompoundCommand#canUndo()
   */
  @Override
  public boolean canUndo() {
    return false;
  }

  /**
   * @see org.eclipse.emf.common.command.CompoundCommand#redo()
   */
  @Override
  public void redo() {
    // Nothing to do, the command is neither undoable, nor redoable.
  }

  /**
   * @see org.eclipse.emf.common.command.CompoundCommand#undo()
   */
  @Override
  public void undo() {
    // Nothing to do, the command is neither undoable, nor redoable.
  }
}
