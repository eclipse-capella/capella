package org.polarsys.capella.test.migration.ju.testcases.basic;

import java.io.File;

import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;

public abstract class MigrateAirdAbstractTestCase extends BasicTestCase {

  @Override
  protected void setUp() throws Exception {
    String relativePath = getRequiredTestModels().get(0);
    File sourceFolder = getFolderInTestModelRepository(relativePath);
    ModelProviderHelper.getInstance().importCapellaProject(relativePath, sourceFolder);
  }

  @Override
  protected void tearDown() throws Exception {
    ModelProviderHelper.getInstance().removeCapellaProject(getRequiredTestModels().get(0));
  }
}
