/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
