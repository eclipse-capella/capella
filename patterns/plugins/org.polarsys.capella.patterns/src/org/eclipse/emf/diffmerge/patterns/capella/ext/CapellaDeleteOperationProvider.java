/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.emf.diffmerge.patterns.capella.ext;

import java.util.Collection;

import org.eclipse.emf.diffmerge.patterns.capella.Messages;
import org.eclipse.emf.diffmerge.patterns.core.api.ext.IDeleteOperationProvider;
import org.eclipse.emf.diffmerge.patterns.core.api.status.IModelTransformationStatus;
import org.eclipse.emf.diffmerge.patterns.core.api.status.ModelTransformationStatus;
import org.eclipse.emf.diffmerge.patterns.core.operations.DeleteOperation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.helpers.HoldingResource;
import org.polarsys.capella.core.model.preferences.IDeletePreferences;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaDeleteAction;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;


/**
 * A class which provides delete operations for Capella
 */
public class CapellaDeleteOperationProvider implements IDeleteOperationProvider {
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IDeleteOperationProvider#getDeleteOperation(java.util.Collection, boolean, boolean, java.lang.Object)
   */
  public CapellaDeleteOperation getDeleteOperation(Collection<? extends EObject> toDelete_p,
      boolean skipConfirmation_p, boolean isExpensive_p,
      Object modelSideContext_p) {
    return new CapellaDeleteOperation(toDelete_p, skipConfirmation_p, isExpensive_p, modelSideContext_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IDeleteOperationProvider#isInModel(org.eclipse.emf.ecore.EObject)
   */
  public boolean isInModel(EObject element_p) {
    Resource resource = element_p.eResource();
    return resource != null && !(resource instanceof HoldingResource);
  }
  
  
  /**
   * A delete operation for Capella models
   */
  private static class CapellaDeleteOperation extends DeleteOperation {
    
    /** Whether the user should never be prompted for confirmation */
    private final boolean _skipConfirmation;
    
    /**
     * Constructor
     * @param toDelete_p a non-null, potentially empty collection of the elements to delete
     * @param skipConfirmation_p whether the user should never be prompted for confirmation
     * @param isExpensive_p whether the operation should be considered as expensive
     */
    public CapellaDeleteOperation(Collection<? extends EObject> toDelete_p,
        boolean skipConfirmation_p, boolean isExpensive_p,
        Object targetContext_p) {
      super(toDelete_p, isExpensive_p, targetContext_p);
      _skipConfirmation = skipConfirmation_p;
    }
    
    /**
     * @see org.eclipse.emf.diffmerge.patterns.core.operations.AbstractModelOperation#run()
     */
    @Override
    protected IModelTransformationStatus run() {
      IModelTransformationStatus result;
      Collection<EObject> toDelete = getElementsToDelete();
      ExecutionManager executionManager = TransactionHelper.getExecutionManager(toDelete);
      if (CapellaDeleteAction.canDelete(toDelete) && executionManager != null) {
        boolean confirmed = true;
        if (!_skipConfirmation && IDeletePreferences.INSTANCE.isConfirmationRequired())
          // We ask for confirmation first in order to know whether the user has confirmed
          // or not, which is not possible if confirmation is asked during command execution
          confirmed = CapellaDeleteCommand.confirmDeletion(executionManager, toDelete);
        if (confirmed) {
          CapellaDeleteCommand deleteCmd = new CapellaDeleteCommand(
              executionManager, toDelete, false, false, isExpensive());
          deleteCmd.execute();
          result = new ModelTransformationStatus(true, false, null, true);
          for (Object affected : deleteCmd.getAffectedObjects()) {
            if (affected instanceof EObject)
              result.getDeletedElements().add((EObject)affected);
          }
          ((ModelTransformationStatus)result).freeze();
        } else {
          // Canceled
          result = new ModelTransformationStatus(false, false, null); // No element deleted
          abort();
        }
      } else {
        result = new ModelTransformationStatus(
            false, Messages.CapellaDeleteOperationProvider_DeleteFailed);
      }
      return result;
    }
    
  }
  
}
