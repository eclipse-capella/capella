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

package org.polarsys.capella.common.re.launcher;

import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.DefaultMergeHandler;
import org.polarsys.capella.core.transition.common.handlers.merge.IMergeHandler;
import org.polarsys.capella.core.transition.common.launcher.DefaultLauncher;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;

/**
 *
 */
public abstract class ReLauncher extends DefaultLauncher {
 
  protected void initializeParameters() {
    super.initializeParameters();

    addSharedParameter(new GenericParameter<IRulesHandler>(org.polarsys.capella.core.transition.common.activities.InitializeTransitionActivity.PARAMETER_RULE_HANDLER,
        getTransposer().getRulesHandler(), "Rule handler"));

    addSharedParameter(new GenericParameter<String>(ITransitionConstants.OPTIONS_SCOPE, getScope(), "Transposer Rule handler")); //$NON-NLS-1$;

    addSharedParameter(new GenericParameter<String>(IReConstants.COMMAND__CURRENT_VALUE, getKind(), "Transposer Rule handler")); //$NON-NLS-1$

    addSharedParameter(new GenericParameter<IMergeHandler>(ITransitionConstants.MERGE_DIFFERENCES_HANDLER, createMergeHandler() , "Re Merge handler")); //$NON-NLS-1$

  }
  
  @Override
  protected void dispose() {
    super.dispose();
    removeSharedParameter(org.polarsys.capella.core.transition.common.activities.InitializeTransitionActivity.PARAMETER_RULE_HANDLER);
  }
  
  @Override
  protected String getPurpose() {
    return "org.polarsys.capella.common.re";
  }

  @Override
  protected abstract String getMapping();

  protected abstract String getScope();

  protected abstract String getKind();

  /**
   * This default implementation will use a {@code org.polarsys.capella.core.transition.common.handlers.merge.DefaultMergeHandler} which
   * will process source and target differences. Subclasses may override this for customized behavior.
   * @return the merge handler to use in this launcher
   */
  protected IMergeHandler createMergeHandler(){
    return new DefaultMergeHandler(true);
  }

  @Override
  protected String getReportComponent() {
    return "REC-RPL";
  }
}
