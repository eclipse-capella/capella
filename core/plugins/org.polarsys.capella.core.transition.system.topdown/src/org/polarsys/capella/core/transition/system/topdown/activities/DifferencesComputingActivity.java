/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.activities;

import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.policies.diff.TopDownDiffPolicy;
import org.polarsys.capella.core.transition.system.topdown.policies.match.TopDownMatchPolicy;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class DifferencesComputingActivity
    extends org.polarsys.capella.core.transition.system.activities.DifferencesComputingActivity {

  public static final String ID = "org.polarsys.capella.core.transition.system.topdown.activities.DifferencesComputingActivity"; //$NON-NLS-1$

  /**
   * @return
   */
  @Override
  protected IMatchPolicy createMatchPolicy(IContext context) {
    IMatchPolicy policy = new TopDownMatchPolicy(context);
    return policy;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IDiffPolicy createDiffPolicy(IContext context) {
    IDiffPolicy policy = new TopDownDiffPolicy(context);
    return policy;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean displayLog(IContext context) {
    return OptionsHandlerHelper.getInstance(context).getBooleanValue(context, ITopDownConstants.OPTIONS_SCOPE,
        ITopDownConstants.OPTIONS_LOG, ITopDownConstants.OPTIONS_LOG__DEFAULT.booleanValue());
  }

}
