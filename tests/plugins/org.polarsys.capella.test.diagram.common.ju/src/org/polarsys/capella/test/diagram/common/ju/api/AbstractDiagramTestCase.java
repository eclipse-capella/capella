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
package org.polarsys.capella.test.diagram.common.ju.api;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;

import junit.framework.TestResult;

/**
 *
 */
public abstract class AbstractDiagramTestCase extends BasicTestCase {

  /*
   * (non-Javadoc)
   *
   * @see junit.framework.TestCase#run(junit.framework.TestResult)
   */
  @Override
  public void run(TestResult result) {
    DiagramHelper.setPrefereneRefreshOnOpening(false);
    DiagramHelper.setPreferenceAutoRefresh(false);
    super.run(result);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getRequiredTestModel());
  }

  protected abstract String getRequiredTestModel();

}
