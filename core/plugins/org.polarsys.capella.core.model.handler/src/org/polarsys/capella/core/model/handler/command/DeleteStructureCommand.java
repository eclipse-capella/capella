/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.handler.command;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * Extension of the delete command that also deletes attached semantic structure (no longer in use).
 */
public class DeleteStructureCommand extends DeleteCommand {

  /**
   * Constructor.
   * @param editingDomain
   * @param elements
   */
  public DeleteStructureCommand(EditingDomain editingDomain, Collection<?> elements) {
    super(editingDomain, elements);
  }

  /**
   * @see org.polarsys.capella.core.model.handler.command.DeleteCommand#deletePointingReference(org.eclipse.emf.ecore.EObject,
   *      org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected void deletePointingReference(EObject referencingEObject, EStructuralFeature feature, EObject referenceToDelete) {
    super.deletePointingReference(referencingEObject, feature, referenceToDelete);

    // Delete specific semantic structure, if any.
    deleteSemanticStructure(referenceToDelete, referencingEObject, feature);
  }

  /**
   * Handles the deletion of pending semantic elements for specified link (if it makes sense to treat it as a link).
   * @param linkObject
   * @param sourceObject
   * @param feature
   */
  protected void deleteSemanticStructure(EObject linkObject, EObject sourceObject, EStructuralFeature feature) {
    IDeleteHelper helper = IDeleteHelper.DEFAULT;

    if (helper.isDeleteSemanticStructure(sourceObject, linkObject, feature)) {
      appendAndExecute(doDeleteStructure(sourceObject));

    } else if (helper.isDeleteElement(sourceObject, linkObject, feature)) {
      appendAndExecute(doDeleteElement(sourceObject));
    }

    Collection<EObject> additionnalElements = helper.getAdditionalElements(sourceObject, linkObject, feature);
    if (additionnalElements != null) {
      for (EObject object : additionnalElements) {
        appendAndExecute(doDeleteStructure(object));
      }
    }

    if (runAdditionalCommands()) {
      Collection<Command> additionnalCommands = helper.getAdditionalCommands(sourceObject, linkObject, feature);
      if (additionnalCommands != null) {
        for (Command object : additionnalCommands) {
          appendAndExecute(object);
        }
      }
    }
  }

  /**
   * Returns whether additional commands provided by Delete Helper should be triggered
   */
  protected boolean runAdditionalCommands() {
    return true;
  }

  /**
   * Do return a new delete command targeting specified element.
   * @param sourceObject
   * @return
   */
  protected Command doDeleteElement(EObject sourceObject) {
    return new DeleteCommand(getEditingDomain(), Collections.singletonList(sourceObject));
  }

  /**
   * Do return a new delete structure command targeting specified element.
   * @param sourceObject
   * @return
   */
  protected Command doDeleteStructure(EObject sourceObject) {
    return new DeleteStructureCommand(getEditingDomain(), Collections.singletonList(sourceObject));
  }
}
