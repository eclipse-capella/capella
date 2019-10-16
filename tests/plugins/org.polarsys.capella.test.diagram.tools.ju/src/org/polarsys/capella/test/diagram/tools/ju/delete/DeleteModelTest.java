package org.polarsys.capella.test.diagram.tools.ju.delete;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;

public abstract class DeleteModelTest extends BasicTestCase {

  protected static final String PROJECT_NAME = "DeleteModel";

  protected Session session;
  protected SessionContext context;

  protected static final String LAB = "[LAB] B"; // $NON-NLS-1
  protected static final String LAB_B1 = "f401873c-2d5f-43d3-b80c-370440c68f29"; //$NON-NLS-1$
  protected static final String LAB_C = "960f9dca-8e8b-4428-9e83-d9869c1de251"; //$NON-NLS-1$
  protected static final String LAB_A = "d429f36b-c3be-4c10-a0b3-9f79b8cd979b"; //$NON-NLS-1$
  protected static final String LAB_B = "e511f7d8-bcb7-49ff-a14d-59fd1e908411"; //$NON-NLS-1$

  protected static final String CDB = "[CDB] B"; // $NON-NLS-1
  protected static final String CDB_A = "c78e3755-a5e5-4ba5-b858-f1974cebeea9"; //$NON-NLS-1$
  protected static final String CDB_B1 = "03c33810-3348-4c93-8e5e-339116c3f000"; //$NON-NLS-1$
  protected static final String CDB_B = "fa958f9b-1d86-43a4-97cb-15328cb85764"; //$NON-NLS-1$
  protected static final String CDB_C = "e18012ce-c8d9-4524-93ea-7a96041c225b"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(PROJECT_NAME);
  }

}
