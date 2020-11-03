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
package org.polarsys.capella.test.diagram.common.ju.headless.actions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.sirius.analysis.actions.extensions.AffectToMessageDialogBox;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;

/**
 * UI-free release of the {@link AffectToMessageDialogBox} class
 * 
 * @deprecated
 */
@Deprecated
public class HeadlessAffectToMessageDialogBox extends AffectToMessageDialogBox {

  /**
   * Mainly code duplication
   * 
   * @see org.polarsys.capella.core.sirius.analysis.actions.extensions.AffectToMessage#execute(java.util.Collection,
   *      java.util.Map)
   */
  @Override
  public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
    // String resultPortStrategyVariable = (String) parameters.get("resultPortStrategyVariable"); //$NON-NLS-1$
    String resultVariable = (String) parameters.get("resultVariable"); //$NON-NLS-1$

    InstanceRole irToUse = null;

    if (parameters.get(SOURCE_IR) != null) {
      irToUse = (InstanceRole) parameters.get(SOURCE_IR);
    }

    // for found functional exchange case or similar case
    if ((parameters.get(SOURCE_IR) == null) && (parameters.get(TARGET_IR) != null)) {
      irToUse = (InstanceRole) parameters.get(TARGET_IR);
    }
    Scenario scenario = (Scenario) irToUse.eContainer();

    // Assert.isNotNull(resultPortStrategyVariable);
    Assert.isNotNull(resultVariable);

    IHeadlessResult itwr = HeadlessResultOpProvider.INSTANCE.getCurrentOp();

    Object result = itwr.getResult(selections, parameters);
    // InterpreterUtil.getInterpreter(scenario).setVariable(resultPortStrategyVariable,
    // Boolean.valueOf(DialogProvider.getPortStrategie()));

    InterpreterUtil.getInterpreter(scenario).setVariable(resultVariable, result);

    return;
  }
  // TODO: not finished
}
