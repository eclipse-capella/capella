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

package org.polarsys.capella.core.transition.common.transposer;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.transposer.current.GenericRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.mappings.purposes.NonExistingPurposeException;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.rules.RuleExecutionException;
import org.polarsys.kitalpha.transposer.rules.handler.rules.runtime.RuntimePurpose;

/**
 * 
 * An extended version of RuleHandler to add information on apply rules.
 * A rule is used as completeRule or incompleteRule, we need to know which mode we are when apply is called
 * 
 *
 */
public class ExtendedRulesHandler extends GenericRulesHandler {

  /**
   * @param purpose
   * @param mappingId
   * @throws NonExistingPurposeException
   */
  public ExtendedRulesHandler(String purpose, String mappingId) throws NonExistingPurposeException {
    super(purpose, mappingId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean apply(Object object, boolean complete, IProgressMonitor monitor) throws RuleExecutionException {
    //register an attribute in the current context
    getContext().put(ITransitionConstants.TRANSPOSER_APPLY_IS_COMPLETE, Boolean.valueOf(complete));
    return super.apply(object, complete, monitor);
  }

  @Override
  protected RuntimePurpose createRuntimePurpose(String purpose, String mappingId) {
    return ExtendedPurposeRegistry.getInstance().getRegisteredPurpose(purpose, mappingId);
  }

}
