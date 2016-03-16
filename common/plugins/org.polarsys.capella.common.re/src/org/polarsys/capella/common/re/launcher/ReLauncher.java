/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.re.launcher;

import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.launcher.DefaultLauncher;
import org.polarsys.capella.core.transition.common.transposer.SharedWorkflowActivityParameter;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.constants.Messages;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;

/**
 *
 */
public abstract class ReLauncher extends DefaultLauncher {

  @Override
  protected String getPurpose() {
    return "org.polarsys.capella.common.re";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void dispose() {
    super.dispose();
  }

  @Override
  protected abstract String getMapping();

  protected abstract String getScope();

  protected abstract String getKind();

  @Override
  protected SharedWorkflowActivityParameter getSharedParameter(String workflowId) {
    SharedWorkflowActivityParameter parameter = super.getSharedParameter(workflowId);

    GenericParameter<IRulesHandler> param =
        new GenericParameter<IRulesHandler>(org.polarsys.capella.core.transition.common.activities.InitializeTransitionActivity.PARAMETER_RULE_HANDLER,
            getTransposer().getRulesHandler(), "Rule handler"); //$NON-NLS-1$
    parameter.addSharedParameter(param);

    GenericParameter<String> param2 = new GenericParameter<String>(ITransitionConstants.OPTIONS_SCOPE, getScope(), "Transposer Rule handler"); //$NON-NLS-1$
    parameter.addSharedParameter(param2);

    GenericParameter<String> param3 = new GenericParameter<String>(IReConstants.COMMAND__CURRENT_VALUE, getKind(), "Transposer Rule handler"); //$NON-NLS-1$
    parameter.addSharedParameter(param3);

    return parameter;
  }

  @Override
  protected String getName() {
    return Messages.ReLauncher_Title;
  }

}
