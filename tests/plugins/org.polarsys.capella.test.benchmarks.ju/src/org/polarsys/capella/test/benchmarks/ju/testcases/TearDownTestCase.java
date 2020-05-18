/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;

public class TearDownTestCase extends AbstractBenchmarkTestCase {
  BasicTestArtefact benchmarkTestCase;

  public TearDownTestCase(BasicTestArtefact benchmarkTestCase) {
    this.benchmarkTestCase = benchmarkTestCase;
  }

  public void test() {
    Session session = getSession(getRequiredTestModels().get(0));
    if (session != null && session.isOpen()) {
      session.save(new NullProgressMonitor());
      session.close(new NullProgressMonitor());
    }
  }

  @Override
  public void tearDown() throws Exception {
    // release test models
    List<String> projectNamesToLoad = getRequiredTestModels();
    if (projectNamesToLoad != null) {
      for (String modelName : projectNamesToLoad) {
        ModelProviderHelper.getInstance().getModelProvider().releaseTestModel(modelName, benchmarkTestCase);
      }
    }
  }

  @Override
  public String getName() {
    return "-";
  }
}
