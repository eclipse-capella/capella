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
package org.polarsys.capella.core.transition.system.topdown.activities;

import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.ecore.EObject;
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
  protected IMatchPolicy<EObject> createMatchPolicy(IContext context) {
    return new TopDownMatchPolicy(context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IDiffPolicy<EObject> createDiffPolicy(IContext context) {
    return new TopDownDiffPolicy(context);
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
