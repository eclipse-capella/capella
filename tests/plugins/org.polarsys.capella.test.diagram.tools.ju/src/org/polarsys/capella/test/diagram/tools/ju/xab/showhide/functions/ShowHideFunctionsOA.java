/*******************************************************************************
 * Copyright (c) 2019, THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xab.showhide.functions;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.OABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public final class ShowHideFunctionsOA extends AbstractShowHideFunctions {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    String rootSF = EmptyProject.OA__OPERATIONAL_ACTIVITIES__ROOT_OA;
    String rootCPS = EmptyProject.OA__OPERATIONAL_CONTEXT;

    initialize(context, rootSF, rootCPS);
    testCommonScenarios(context, rootSF, rootCPS);

    testLeafFunctionInRole(context, rootSF, rootCPS);
    testLeafFunctionInParentRole(context, rootSF, rootCPS);
    testParentFunctionInRole(context, rootSF, rootCPS);
    testParentFunctionInParentOfRole(context, rootSF, rootCPS);
    testFunctionsMustHideIfShowSubRole(context, rootSF, rootCPS);
  }

  @Override
  protected XDFBDiagram initializeXDFB(SessionContext context, String rootSF, String rootCPS) {

    XDFBDiagram xdfb = super.initializeXDFB(context, rootSF, rootCPS);
    xdfb.createFunction(GenericModel.FUNCTION_1_4);
    xdfb.createFunction(GenericModel.FUNCTION_2);
    xdfb.createFunction(GenericModel.FUNCTION_1_1_3, GenericModel.FUNCTION_2);
    xdfb.createFunction(GenericModel.FUNCTION_1_1_4, GenericModel.FUNCTION_2);

    return xdfb;
  }

  @Override
  protected XABDiagram initializeXAB(SessionContext context, String rootSF, String rootCPS) {

    OABDiagram xab = (OABDiagram) super.initializeXAB(context, rootSF, rootCPS);
    createComponent(xab, GenericModel.COMPONENT_1_3, GenericModel.COMPONENT_1);

    xab.createRole(GenericModel.ROLE_1_1_3, GenericModel.COMPONENT_1_3);
    xab.createRole(GenericModel.ROLE_1_4, GenericModel.COMPONENT_1);

    xab.manageAllocatedFunction(GenericModel.FUNCTION_1_1_3, GenericModel.ROLE_1_1_3);
    xab.manageAllocatedFunction(GenericModel.FUNCTION_1_1_4, GenericModel.ROLE_1_1_3);

    xab.manageAllocatedFunction(GenericModel.FUNCTION_1_4, GenericModel.ROLE_1_4);

    return xab;
  }

  protected OABDiagram setUpDiagram(SessionContext context, String rootSF, String rootCPS) {

    OABDiagram xab = (OABDiagram) super.setUpDiagram(context, rootSF, rootCPS);

    insertComponent(xab, GenericModel.COMPONENT_1_3, GenericModel.COMPONENT_1);
    xab.insertRole(GenericModel.ROLE_1_1_3, GenericModel.COMPONENT_1_3);
    xab.insertRole(GenericModel.ROLE_1_4, GenericModel.COMPONENT_1);

    return xab;
  }

  protected void testLeafFunctionInRole(SessionContext context, String rootSF, String rootCPS) {
    OABDiagram oab = setUpDiagram(context, rootSF, rootCPS);

    oab.hasntView(GenericModel.FUNCTION_1_1_3);
    oab.hasntView(GenericModel.FUNCTION_1_1_4);
    oab.hasntView(GenericModel.FUNCTION_1_4);

    oab.insertAllocatedFunction(GenericModel.FUNCTION_1_1_3, GenericModel.ROLE_1_1_3);
    oab.insertAllocatedFunction(GenericModel.FUNCTION_1_1_4, GenericModel.ROLE_1_1_3);
    oab.insertAllocatedFunction(GenericModel.FUNCTION_1_4, GenericModel.ROLE_1_4);

    checkFunction(oab, GenericModel.FUNCTION_1_1_3, false, false);
    checkFunction(oab, GenericModel.FUNCTION_1_1_4, false, false);
    checkFunction(oab, GenericModel.FUNCTION_1_4, false, false);
  }

  protected void testLeafFunctionInParentRole(SessionContext context, String rootSF, String rootCPS) {
    OABDiagram oab = setUpDiagram(context, rootSF, rootCPS);

    oab.removeRole(GenericModel.ROLE_1_1_3, GenericModel.COMPONENT_1_3);
    oab.hasntView(GenericModel.FUNCTION_1_1_3);
    oab.hasntView(GenericModel.FUNCTION_1_1_4);

    oab.insertAllocatedFunction(GenericModel.FUNCTION_1_1_3, GenericModel.COMPONENT_1_3);
    oab.insertAllocatedFunction(GenericModel.FUNCTION_1_1_4, GenericModel.COMPONENT_1_3);
    checkFunction(oab, GenericModel.FUNCTION_1_1_3, false, false);
    checkFunction(oab, GenericModel.FUNCTION_1_1_4, false, false);

    removeComponent(oab, GenericModel.COMPONENT_1_3, GenericModel.COMPONENT_1);
    oab.hasntView(GenericModel.FUNCTION_1_1_3);
    oab.hasntView(GenericModel.FUNCTION_1_1_4);

    oab.insertAllocatedFunction(GenericModel.FUNCTION_1_1_3, GenericModel.COMPONENT_1);
    oab.insertAllocatedFunction(GenericModel.FUNCTION_1_1_4, GenericModel.COMPONENT_1);
    checkFunction(oab, GenericModel.FUNCTION_1_1_3, false, false);
    checkFunction(oab, GenericModel.FUNCTION_1_1_4, false, false);
  }

  protected void testParentFunctionInRole(SessionContext context, String rootSF, String rootCPS) {
    OABDiagram oab = setUpDiagram(context, rootSF, rootCPS);

    oab.hasntView(GenericModel.FUNCTION_2);
    oab.insertAllocatedFunction(GenericModel.FUNCTION_2, GenericModel.ROLE_1_1_3);
    checkFunction(oab, GenericModel.FUNCTION_2, true, false);
  }

  protected void testParentFunctionInParentOfRole(SessionContext context, String rootSF, String rootCPS) {
    OABDiagram oab = setUpDiagram(context, rootSF, rootCPS);

    oab.removeRole(GenericModel.ROLE_1_1_3, GenericModel.COMPONENT_1_3);
    oab.hasntView(GenericModel.FUNCTION_2);

    oab.insertAllocatedFunction(GenericModel.FUNCTION_2, GenericModel.COMPONENT_1_3);
    checkFunction(oab, GenericModel.FUNCTION_2, true, false);
  }

  protected void testFunctionsMustHideIfShowSubRole(SessionContext context, String rootSF, String rootCPS) {
    OABDiagram oab = setUpDiagram(context, rootSF, rootCPS);
    oab.removeRole(GenericModel.ROLE_1_4, GenericModel.COMPONENT_1);

    oab.insertAllocatedFunction(GenericModel.FUNCTION_1_4, GenericModel.COMPONENT_1);
    checkFunction(oab, GenericModel.FUNCTION_1_4, false, false);

    oab.insertRole(GenericModel.ROLE_1_4, GenericModel.COMPONENT_1);
    oab.hasntView(GenericModel.FUNCTION_1_4);
  }

}
