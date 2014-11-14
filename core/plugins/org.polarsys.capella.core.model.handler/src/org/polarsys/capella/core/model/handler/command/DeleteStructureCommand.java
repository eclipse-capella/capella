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
   * Should undefined parts be deleted as well ?
   */
  protected boolean _deleteParts;

  /**
   * Constructor.
   * @param editingDomain_p
   * @param elements_p
   * @param deleteParts_p Remove parts that are no longer typed because of the deletion ? <code>true</code> if so, <code>false</code> otherwise.
   */
  public DeleteStructureCommand(EditingDomain editingDomain_p, Collection<?> elements_p, boolean deleteParts_p) {
    super(editingDomain_p, elements_p);
    _deleteParts = deleteParts_p;
  }

  /**
   * @see org.polarsys.capella.core.model.handler.command.DeleteCommand#deletePointingReference(org.eclipse.emf.ecore.EObject,
   *      org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected void deletePointingReference(EObject referencingEObject_p, EStructuralFeature feature_p, EObject referenceToDelete_p) {
    super.deletePointingReference(referencingEObject_p, feature_p, referenceToDelete_p);

    // Delete specific semantic structure, if any.
    deleteSemanticStructure(referenceToDelete_p, referencingEObject_p, feature_p);
  }

  /**
   * Handles the deletion of pending semantic elements for specified link (if it makes sense to treat it as a link).
   * @param linkObject_p
   * @param sourceObject_p
   * @param feature_p
   */
  protected void deleteSemanticStructure(EObject linkObject_p, EObject sourceObject_p, EStructuralFeature feature_p) {
    IDeleteHelper helper = IDeleteHelper.DEFAULT;

    if (helper.isDeleteSemanticStructure(sourceObject_p, linkObject_p, feature_p)) {
      appendAndExecute(doDeleteStructure(sourceObject_p));

    } else if (helper.isDeleteElement(sourceObject_p, linkObject_p, feature_p)) {
      appendAndExecute(doDeleteElement(sourceObject_p));
    }

    Collection<EObject> additionnalElements = helper.getAdditionalElements(sourceObject_p, linkObject_p, feature_p);
    if (additionnalElements != null) {
      for (EObject object : additionnalElements) {
        appendAndExecute(doDeleteStructure(object));
      }
    }

    if (runAdditionalCommands()) {
      Collection<Command> additionnalCommands = helper.getAdditionalCommands(sourceObject_p, linkObject_p, feature_p);
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
   * @param sourceObject_p
   * @return
   */
  protected Command doDeleteElement(EObject sourceObject_p) {
    return new DeleteCommand(_editingDomain, Collections.singletonList(sourceObject_p));
  }

  /**
   * Do return a new delete structure command targeting specified element.
   * @param sourceObject_p
   * @return
   */
  protected Command doDeleteStructure(EObject sourceObject_p) {
    return new DeleteStructureCommand(_editingDomain, Collections.singletonList(sourceObject_p), _deleteParts);
  }

}
