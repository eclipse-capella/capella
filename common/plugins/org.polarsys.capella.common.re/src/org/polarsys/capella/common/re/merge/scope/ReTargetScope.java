/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.merge.scope;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.core.transition.common.merge.scope.ITargetModelScope;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReTargetScope extends ReScope implements ITargetModelScope {

  @Override
  protected boolean isSource() {
    return false;
  }

  /**
   * @param element
   * @param handler
   * @param elements
   * @param context
   */
  public ReTargetScope(CatalogElement element, Collection<? extends EObject> elements,
      IContext context) {
    super(element, elements, context);
    
    // if the command is:
    String commandValue = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);
    // - Update selected RPL from its REC
    if (IReConstants.COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE.equals(commandValue)) {
      setOriginator(Messages.ReTargetScope_selectedRPL);
    }
    // - Update REC from selection
    else if (IReConstants.COMMAND__UPDATE_CURRENT_REPLICA_FROM_REPLICA.equals(commandValue)) {
      setOriginator(Messages.ReTargetScope_REC);
    }
    // - Update REC from selected RPL
    else if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(commandValue)) {
      setOriginator(Messages.ReTargetScope_REC);
    }
    // else
    else {
      setOriginator(Messages.ReTargetScope_resultingModel);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<EObject> retrieveTransformedElementsFromTarget(EObject targetElement) {
    return null;
  }

  @Override
  public boolean isDirty() {
    return true;
  }
}
