/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.helpers;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.helpers.information.services.DataTypeExt;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.TestHelper;

public class DataTypeRealizedInformationTest extends BasicTestCase {

  public static final String BOOLEAN_TYPE_1 = "09dc7956-5dee-44b8-b8b4-7ee623a3a343";
  public static final String BOOLEAN_TYPE_2 = "a9491dd0-cc6e-407f-987e-c82b132ce601";
  public static final String DIAGRAM = "[CDB] Data";
  public static String MODEL_NAME = "miscmodel";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

  @Override
  public void test() throws Exception {
    ICapellaModel model = getTestModel(MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));

    BooleanType bt1 = (BooleanType) IdManager.getInstance().getEObject(BOOLEAN_TYPE_1, scope);
    BooleanType bt2 = (BooleanType) IdManager.getInstance().getEObject(BOOLEAN_TYPE_2, scope);
    DDiagram diagram = (DSemanticDiagram) DiagramHelper.getDRepresentation(session, DIAGRAM);

    AbstractCommand cmd = new AbstractReadWriteCommand() {
      public void run() {
        DataTypeExt.addRealizedInformation(bt1, bt2);
      }
    };
    TestHelper.getExecutionManager(diagram).execute(cmd);

    assertFalse(bt1.getOwnedInformationRealizations().isEmpty());
    assertTrue(bt1.getOwnedInformationRealizations().get(0).getSourceElement() != null);
    assertTrue(bt1.getOwnedInformationRealizations().get(0).getSourceElement().equals(bt1));

  }
}
