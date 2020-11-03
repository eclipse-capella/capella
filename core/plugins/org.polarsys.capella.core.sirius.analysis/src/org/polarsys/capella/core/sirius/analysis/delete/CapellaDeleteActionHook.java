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
package org.polarsys.capella.core.sirius.analysis.delete;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.delete.IDeleteHook;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.model.preferences.IDeletePreferences;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;

public class CapellaDeleteActionHook implements IDeleteHook {

  // Sirius triggers the hook for all selected elements but triggers the externalDeleteAction for each item of
  // selection,
  // we need to store this selection to trigger only one time the externalDeleteAction with full initial selection.

  private static Collection<EObject> selection;

  public static Collection<EObject> getSelection() {
    return selection;
  }

  protected static void setSelection(Collection<EObject> semanticElements) {
    selection = semanticElements;
  }

  public static void removeSelection() {
    setSelection(null);
  }

  /**
   * @see org.eclipse.sirius.business.api.delete.IDeleteHook#beforeDeleteCommandExecution(java.util.Collection,
   *      java.util.Map)
   */
  @Override
  public IStatus beforeDeleteCommandExecution(Collection<DSemanticDecorator> selection,
      Map<String, Object> parameters) {

    if (null == selection || selection.isEmpty()) {
      return Status.CANCEL_STATUS;
    }

    Collection<EObject> deletionCandidates = extractDeletionCandidates(selection);

    setSelection(deletionCandidates);

    if (confirmDeletion(deletionCandidates)) {
      return Status.OK_STATUS;
    }

    removeSelection();
    return Status.CANCEL_STATUS;
  }

  /**
   * Extracts the deletion candidate elements from the user selection of DSemanticDecorators. All the
   * {@link DSemanticDecorator#getTarget()} elements are extracted, except those that are referenced in TitleBlock elements.
   * When deleting a TitleBlock, referenced Model Elements should not be deleted.
   * 
   * @param selection
   *          the user selection
   * @return the valid semantic elements that will be deleted.
   */
  private Collection<EObject> extractDeletionCandidates(Collection<DSemanticDecorator> selection) {
    Collection<EObject> semanticElements = new HashSet<>(selection.size());

    for (DSemanticDecorator semanticDecorator : selection) {
      EObject target = semanticDecorator.getTarget();

      if (target != null) {
        
        if (!(target instanceof DAnnotation) && semanticDecorator instanceof DDiagramElement) {
          DDiagramElement diagramElement = (DDiagramElement) semanticDecorator;
          RepresentationElementMapping mapping = diagramElement.getMapping();

          if (!TitleBlockHelper.isTitleBlockMapping(mapping)) {
            semanticElements.add(target);
          }
        }

        else {
          semanticElements.add(target);
        }
      }
    }

    return semanticElements;
  }

  /**
   * Confirms the deletion with the user (by displaying the appropriate dialog), if the required preference is activated. 
   * @param deletionCandidates the deletion candidates
   * @return false if the deletion is not confirmed, true otherwise.
   */
  private boolean confirmDeletion(Collection<EObject> deletionCandidates) {

    if (IDeletePreferences.INSTANCE.isConfirmationRequired()) {
      ExecutionManager executionManager = TransactionHelper.getExecutionManager(deletionCandidates);
      return executionManager != null && CapellaDeleteCommand.confirmDeletion(executionManager, deletionCandidates);
    }

    return true;
  }
}
