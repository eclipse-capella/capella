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
import org.polarsys.capella.core.sirius.analysis.actions.extensions.SelectElementsFromCheckBoxWizard;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;

/**
 * SelectElementsFromCheckBoxWizard specialization in order to avoid any UI call.
 */
public class HeadlessSelectElementsFromCheckBoxWizard extends SelectElementsFromCheckBoxWizard {

  @Override
  public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {

    EObject context = (EObject) parameters.get("context"); //$NON-NLS-1$
    String resultVariable = (String) parameters.get("resultVariable"); //$NON-NLS-1$

    Assert.isNotNull(context);
    Assert.isNotNull(resultVariable);

    IHeadlessResult itwr = HeadlessResultOpProvider.INSTANCE.getCurrentOp();

    Object result = itwr.getResult(selections, parameters);

    InterpreterUtil.getInterpreter(context).setVariable(resultVariable, result);
  }
}
