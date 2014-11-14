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
   * @param purpose_p
   * @param mappingId_p
   * @throws NonExistingPurposeException
   */
  public ExtendedRulesHandler(String purpose_p, String mappingId_p) throws NonExistingPurposeException {
    super(purpose_p, mappingId_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean apply(Object object_p, boolean complete_p, IProgressMonitor monitor_p) throws RuleExecutionException {
    //register an attribute in the current context
    getContext().put(ITransitionConstants.TRANSPOSER_APPLY_IS_COMPLETE, Boolean.valueOf(complete_p));
    return super.apply(object_p, complete_p, monitor_p);
  }

  @Override
  protected RuntimePurpose createRuntimePurpose(String purpose_p, String mappingId_p) {
    return ExtendedPurposeRegistry.getInstance().getRegisteredPurpose(purpose_p, mappingId_p);
  }

}
