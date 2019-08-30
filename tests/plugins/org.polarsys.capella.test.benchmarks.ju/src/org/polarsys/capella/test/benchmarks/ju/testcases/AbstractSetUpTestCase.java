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
package org.polarsys.capella.test.benchmarks.ju.testcases;

import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;

public abstract class AbstractSetUpTestCase extends AbstractBenchmarkTestCase {
  BasicTestArtefact benchmarkTestCase;

  public AbstractSetUpTestCase(BasicTestArtefact benchmarkTestCase) {
    this.benchmarkTestCase = benchmarkTestCase;
  }

  @Override
  public void setUp() throws Exception {
    List<String> projectNamesToLoad = getRequiredTestModels();
    if (projectNamesToLoad != null) {
      ModelProviderHelper.getInstance().getModelProvider().requireTestModel(projectNamesToLoad, benchmarkTestCase);
    }
  }

  @Override
  public void tearDown() throws Exception {
    // Do nothing
  }

  @Override
  public String getName() {
    return "-";
  }
}
