/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.benchmarks.ju.modelvalidation;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.core.validation.ui.actions.CapellaValidateAction;
import org.polarsys.capella.test.benchmarks.ju.HeadlessBenchmarkCapellaValidateAction;
import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractBenchmarkTestCase;

/**
 * 
 * Launch a validation on test model
 */
public class ModelValidationTestCase extends AbstractBenchmarkTestCase {

  public ModelValidationTestCase() {
    // Do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    Session session = getSession(getRequiredTestModels().get(0));

    CapellaValidateAction action = new HeadlessBenchmarkCapellaValidateAction();
    action.updateSelection(new StructuredSelection(SessionHelper.getCapellaProject(session)));

    action.run();
  }
}
