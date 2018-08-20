/*******************************************************************************
 * Copyright (c) 2016, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class ShowHideFunctions extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOn(context, EmptyProject.OA__OPERATIONAL_ACTIVITIES__ROOT_OA, EmptyProject.OA__OPERATIONAL_CONTEXT);
    testOn(context, EmptyProject.LA__ROOT_LF, EmptyProject.LA__LOGICAL_SYSTEM);
    testOn(context, EmptyProject.PA__ROOT_PF, EmptyProject.PA__PHYSICAL_SYSTEM);

  }

  /**
   * Perform the test on the given architecture
   */
  protected void testOn(SessionContext context, String rootSF, String rootCPS) {
    initialize(context, rootSF, rootCPS);
    testLeafFunctionInComponent(context, rootSF, rootCPS);
    testLeafFunctionInParentComponent(context, rootSF, rootCPS);
    testParentFunctionInComponent(context, rootSF, rootCPS);
    testParentFunctionInParentComponent(context, rootSF, rootCPS);
    testFunctionsMustHideIfShowSubComponent(context, rootSF, rootCPS);

  }

  /**
   * Create a model
   */
  protected void initialize(SessionContext context, String rootSF, String rootCPS) {

    XDFBDiagram xdfb = XDFBDiagram.createDiagram(context, rootSF);
    xdfb.createFunction(GenericModel.FUNCTION_1);
    xdfb.createFunction(GenericModel.FUNCTION_1_1, GenericModel.FUNCTION_1);
    xdfb.createFunction(GenericModel.FUNCTION_1_1_1, GenericModel.FUNCTION_1_1);
    xdfb.createFunction(GenericModel.FUNCTION_1_1_2, GenericModel.FUNCTION_1_1);
    xdfb.createFunction(GenericModel.FUNCTION_1_2, GenericModel.FUNCTION_1);

    XABDiagram xab = XABDiagram.createDiagram(context, rootCPS);
    xab.clearDiagram();
    createComponent(xab, GenericModel.COMPONENT_1, xab.getDiagramId());
    createComponent(xab, GenericModel.COMPONENT_1_1, GenericModel.COMPONENT_1);
    createComponent(xab, GenericModel.COMPONENT_1_1_1, GenericModel.COMPONENT_1_1);
    createComponent(xab, GenericModel.COMPONENT_1_2, GenericModel.COMPONENT_1);

    xab.manageAllocatedFunction(GenericModel.FUNCTION_1_1_1, GenericModel.COMPONENT_1_1_1);
    xab.manageAllocatedFunction(GenericModel.FUNCTION_1_1_2, GenericModel.COMPONENT_1_1_1);
    xab.manageAllocatedFunction(GenericModel.FUNCTION_1_2, GenericModel.COMPONENT_1_2);

  }

  private void createComponent(XABDiagram xab, String id, String containerId) {
    if (xab instanceof PABDiagram) {
      ((PABDiagram) xab).createBehaviorComponent(id, containerId);
    } else {
      xab.createComponent(id, containerId);
    }
  }

  private void insertComponent(XABDiagram xab, String id, String containerId) {
    if (xab instanceof PABDiagram) {
      ((PABDiagram) xab).insertBehaviorComponent(id, containerId);
    } else {
      xab.insertComponent(id, containerId);
    }
  }

  private void removeComponent(XABDiagram xab, String id, String containerId) {
    if (xab instanceof PABDiagram) {
      ((PABDiagram) xab).removeBehaviorComponent(id, containerId);
    } else {
      xab.removeComponent(id, containerId);
    }
  }

  /**
   * Create a diagram with components inside
   */
  private XABDiagram setUpDiagram(SessionContext context, String rootSF, String rootCPS) {

    XABDiagram xab = XABDiagram.createDiagram(context, rootCPS);
    xab.clearDiagram();

    insertComponent(xab, GenericModel.COMPONENT_1, xab.getDiagramId());
    insertComponent(xab, GenericModel.COMPONENT_1_1, GenericModel.COMPONENT_1);
    insertComponent(xab, GenericModel.COMPONENT_1_1_1, GenericModel.COMPONENT_1_1);
    insertComponent(xab, GenericModel.COMPONENT_1_2, GenericModel.COMPONENT_1);

    return xab;
  }

  /**
   * test show leaf function in component (not italic, not dash)
   * 
   * @param context
   */
  private void testLeafFunctionInComponent(SessionContext context, String rootSF, String rootCPS) {
    XABDiagram xab = setUpDiagram(context, rootSF, rootCPS);

    xab.hasntView(GenericModel.FUNCTION_1_1_1);
    xab.hasntView(GenericModel.FUNCTION_1_1_2);
    xab.hasntView(GenericModel.FUNCTION_1_2);
    xab.insertAllocatedFunction(GenericModel.FUNCTION_1_1_1, GenericModel.COMPONENT_1_1_1);
    xab.insertAllocatedFunction(GenericModel.FUNCTION_1_1_2, GenericModel.COMPONENT_1_1_1);
    xab.insertAllocatedFunction(GenericModel.FUNCTION_1_2, GenericModel.COMPONENT_1_2);
    checkFunction(xab, GenericModel.FUNCTION_1_1_1, false, false);
    checkFunction(xab, GenericModel.FUNCTION_1_1_2, false, false);
    checkFunction(xab, GenericModel.FUNCTION_1_2, false, false);
  }

  /**
   * test show leaf function in parent component (not italic, dash)
   * 
   * @param context
   */
  private void testLeafFunctionInParentComponent(SessionContext context, String rootSF, String rootCPS) {
    XABDiagram xab = setUpDiagram(context, rootSF, rootCPS);

    removeComponent(xab, GenericModel.COMPONENT_1_1_1, GenericModel.COMPONENT_1_1);
    xab.hasntView(GenericModel.FUNCTION_1_1_1);
    xab.hasntView(GenericModel.FUNCTION_1_1_2);
    xab.insertAllocatedFunction(GenericModel.FUNCTION_1_1_1, GenericModel.COMPONENT_1_1);
    xab.insertAllocatedFunction(GenericModel.FUNCTION_1_1_2, GenericModel.COMPONENT_1_1);
    checkFunction(xab, GenericModel.FUNCTION_1_1_1, false, true);
    checkFunction(xab, GenericModel.FUNCTION_1_1_2, false, true);

    removeComponent(xab, GenericModel.COMPONENT_1_1, GenericModel.COMPONENT_1);
    removeComponent(xab, GenericModel.COMPONENT_1_2, GenericModel.COMPONENT_1);
    xab.hasntView(GenericModel.FUNCTION_1_1_1);
    xab.hasntView(GenericModel.FUNCTION_1_1_2);
    xab.hasntView(GenericModel.FUNCTION_1_2);
    xab.insertAllocatedFunction(GenericModel.FUNCTION_1_1_1, GenericModel.COMPONENT_1);
    xab.insertAllocatedFunction(GenericModel.FUNCTION_1_1_2, GenericModel.COMPONENT_1);
    xab.insertAllocatedFunction(GenericModel.FUNCTION_1_2, GenericModel.COMPONENT_1);
    checkFunction(xab, GenericModel.FUNCTION_1_1_1, false, true);
    checkFunction(xab, GenericModel.FUNCTION_1_1_2, false, true);
    checkFunction(xab, GenericModel.FUNCTION_1_2, false, true);
  }

  /**
   * test show parent function in component (italic, not dash)
   * 
   * @param context
   */
  private void testParentFunctionInComponent(SessionContext context, String rootSF, String rootCPS) {
    XABDiagram xab = setUpDiagram(context, rootSF, rootCPS);
    xab.hasntView(GenericModel.FUNCTION_1_1);
    xab.insertAllocatedFunction(GenericModel.FUNCTION_1_1, GenericModel.COMPONENT_1_1_1);
    checkFunction(xab, GenericModel.FUNCTION_1_1, true, true);
  }

  /**
   * test show parent function in parent component (italic, dash)
   * 
   * @param context
   */
  private void testParentFunctionInParentComponent(SessionContext context, String rootSF, String rootCPS) {
    XABDiagram xab = setUpDiagram(context, rootSF, rootCPS);
    removeComponent(xab, GenericModel.COMPONENT_1_1_1, GenericModel.COMPONENT_1_1);
    xab.insertAllocatedFunction(GenericModel.FUNCTION_1_1, GenericModel.COMPONENT_1_1);
    checkFunction(xab, GenericModel.FUNCTION_1_1, true, false);

    removeComponent(xab, GenericModel.COMPONENT_1_1, GenericModel.COMPONENT_1);
    xab.hasntView(GenericModel.FUNCTION_1_1);
    xab.insertAllocatedFunction(GenericModel.FUNCTION_1_1, GenericModel.COMPONENT_1);
    checkFunction(xab, GenericModel.FUNCTION_1_1, true, false);
  }

  /**
   * test that if sub component is shown, allocated functions in parent component must hide
   * 
   * @param context
   */
  private void testFunctionsMustHideIfShowSubComponent(SessionContext context, String rootSF, String rootCPS) {
    XABDiagram xab = setUpDiagram(context, rootSF, rootCPS);
    removeComponent(xab, GenericModel.COMPONENT_1_2, GenericModel.COMPONENT_1);
    xab.insertAllocatedFunction(GenericModel.FUNCTION_1_2, GenericModel.COMPONENT_1);
    insertComponent(xab, GenericModel.COMPONENT_1_2, GenericModel.COMPONENT_1);
    xab.hasntView(GenericModel.FUNCTION_1_2);
  }

  private void checkFunction(XABDiagram xab, String id, boolean mustBeItalic, boolean mustBeDash) {

    DSemanticDecorator view = xab.getView(id);
    if (view instanceof DNode) {
      DNode node = (DNode) view;
      NodeStyle style = node.getOwnedStyle();

      LineStyle lineStyle = style.getBorderLineStyle();
      boolean isDash = "dash".equals(lineStyle.getLiteral());
      assertEquals(mustBeDash, isDash);

      boolean isItalic = false;
      EList<FontFormat> labelFormat = style.getLabelFormat();
      for (FontFormat format : labelFormat) {
        if ("italic".equals(format.getLiteral())) {
          isItalic = true;
        }
      }
      assertEquals(mustBeItalic, isItalic);

    } else {
      fail();
    }
  }
}
