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
public class DifferencesComputingActivity extends org.polarsys.capella.core.transition.common.activities.DifferencesComputingActivity {

  public static final String ID = "org.polarsys.capella.core.transition.system.topdown.activities.DifferencesComputingActivity"; //$NON-NLS-1$

  /**
   * @return
   */
  @Override
  protected IMatchPolicy createMatchPolicy(IContext context_p) {
    IMatchPolicy policy = new TopDownMatchPolicy(context_p);
    return policy;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IDiffPolicy createDiffPolicy(IContext context_p) {
    IDiffPolicy policy = new TopDownDiffPolicy(context_p);
    return policy;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean displayLog(IContext context_p) {
    return OptionsHandlerHelper.getInstance(context_p).getBooleanValue(context_p, ITopDownConstants.OPTIONS_SCOPE, ITopDownConstants.OPTIONS_LOG,
        ITopDownConstants.OPTIONS_LOG__DEFAULT.booleanValue());
  }

}
