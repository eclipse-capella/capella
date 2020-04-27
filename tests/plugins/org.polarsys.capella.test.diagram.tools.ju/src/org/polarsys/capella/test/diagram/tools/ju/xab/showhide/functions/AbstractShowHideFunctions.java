/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public abstract class AbstractShowHideFunctions extends EmptyProject {

  /**
   * Common create component tool, that can be overrided with custom behavior by each level.
   * 
   * @param xab
   *          the xab diagram
   * @param componentId
   *          component id
   * @param containerId
   *          container id
   */
  protected void createComponent(XABDiagram xab, String componentId, String containerId) {
    xab.createComponent(componentId, containerId);
  }

  /**
   * Common create insert tool, that can be overrided with custom behavior by each level.
   * 
   * @param xab
   *          the xab diagram
   * @param componentId
   *          component id
   * @param containerId
   *          container id
   */
  protected void insertComponent(XABDiagram xab, String id, String containerId) {
    xab.insertComponent(id, containerId);
  }

  /**
   * Common create remove tool, that can be overrided with custom behavior by each level.
   * 
   * @param xab
   *          the xab diagram
   * @param componentId
   *          component id
   * @param containerId
   *          container id
   */
  protected void removeComponent(XABDiagram xab, String id, String containerId) {
    xab.removeComponent(id, containerId);
  }

  /**
   * Common scenarios that can be safely tested on all levels.
   * 
   * @param context
   *          the context
   * @param rootSF
   *          root system function
   * @param rootCPS
   *          root component system
   */
  protected void testCommonScenarios(SessionContext context, String rootSF, String rootCPS) {
    testLeafFunctionInComponent(context, rootSF, rootCPS);
    testLeafFunctionInParentComponent(context, rootSF, rootCPS);
    testParentFunctionInComponent(context, rootSF, rootCPS);
    testParentFunctionInParentComponent(context, rootSF, rootCPS);
    testFunctionsMustHideIfShowSubComponent(context, rootSF, rootCPS);
  }

  /**
   * Common model for all scenarios.
   * 
   * @param context
   *          the context
   * @param rootSF
   *          root system function
   * @param rootCPS
   *          root component system
   */
  protected void initialize(SessionContext context, String rootSF, String rootCPS) {
    initializeXDFB(context, rootSF, rootCPS);
    initializeXAB(context, rootSF, rootCPS);
  }

  protected XDFBDiagram initializeXDFB(SessionContext context, String rootSF, String rootCPS) {
    XDFBDiagram xdfb = XDFBDiagram.createDiagram(context, rootSF);
    xdfb.createFunction(GenericModel.FUNCTION_1);
    xdfb.createFunction(GenericModel.FUNCTION_1_1, GenericModel.FUNCTION_1);
    xdfb.createFunction(GenericModel.FUNCTION_1_1_1, GenericModel.FUNCTION_1_1);
    xdfb.createFunction(GenericModel.FUNCTION_1_1_2, GenericModel.FUNCTION_1_1);
    xdfb.createFunction(GenericModel.FUNCTION_1_2, GenericModel.FUNCTION_1);

    return xdfb;
  }

  protected XABDiagram initializeXAB(SessionContext context, String rootSF, String rootCPS) {
    XABDiagram xab = XABDiagram.createDiagram(context, rootCPS);
    xab.clearDiagram();
    createComponent(xab, GenericModel.COMPONENT_1, xab.getDiagramId());
    createComponent(xab, GenericModel.COMPONENT_1_1, GenericModel.COMPONENT_1);
    createComponent(xab, GenericModel.COMPONENT_1_1_1, GenericModel.COMPONENT_1_1);
    createComponent(xab, GenericModel.COMPONENT_1_2, GenericModel.COMPONENT_1);

    xab.manageAllocatedFunction(GenericModel.FUNCTION_1_1_1, GenericModel.COMPONENT_1_1_1);
    xab.manageAllocatedFunction(GenericModel.FUNCTION_1_1_2, GenericModel.COMPONENT_1_1_1);
    xab.manageAllocatedFunction(GenericModel.FUNCTION_1_2, GenericModel.COMPONENT_1_2);

    return xab;
  }

  /**
   * Create a diagram with components inside
   */
  protected XABDiagram setUpDiagram(SessionContext context, String rootSF, String rootCPS) {

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
  protected void testLeafFunctionInComponent(SessionContext context, String rootSF, String rootCPS) {
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
  protected void testLeafFunctionInParentComponent(SessionContext context, String rootSF, String rootCPS) {
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
  protected void testParentFunctionInComponent(SessionContext context, String rootSF, String rootCPS) {
    XABDiagram xab = setUpDiagram(context, rootSF, rootCPS);
    xab.hasntView(GenericModel.FUNCTION_1_1);
    xab.insertAllocatedFunction(GenericModel.FUNCTION_1_1, GenericModel.COMPONENT_1_1_1);
    checkFunction(xab, GenericModel.FUNCTION_1_1, true, false);
  }

  /**
   * test show parent function in parent component (italic, dash)
   * 
   * @param context
   */
  protected void testParentFunctionInParentComponent(SessionContext context, String rootSF, String rootCPS) {
    XABDiagram xab = setUpDiagram(context, rootSF, rootCPS);
    removeComponent(xab, GenericModel.COMPONENT_1_1_1, GenericModel.COMPONENT_1_1);
    xab.insertAllocatedFunction(GenericModel.FUNCTION_1_1, GenericModel.COMPONENT_1_1);
    checkFunction(xab, GenericModel.FUNCTION_1_1, true, true);

    removeComponent(xab, GenericModel.COMPONENT_1_1, GenericModel.COMPONENT_1);
    xab.hasntView(GenericModel.FUNCTION_1_1);
    xab.insertAllocatedFunction(GenericModel.FUNCTION_1_1, GenericModel.COMPONENT_1);
    checkFunction(xab, GenericModel.FUNCTION_1_1, true, true);
  }

  /**
   * test that if sub component is shown, allocated functions in parent component must hide
   * 
   * @param context
   */
  protected void testFunctionsMustHideIfShowSubComponent(SessionContext context, String rootSF, String rootCPS) {
    XABDiagram xab = setUpDiagram(context, rootSF, rootCPS);
    removeComponent(xab, GenericModel.COMPONENT_1_2, GenericModel.COMPONENT_1);
    xab.insertAllocatedFunction(GenericModel.FUNCTION_1_2, GenericModel.COMPONENT_1);
    insertComponent(xab, GenericModel.COMPONENT_1_2, GenericModel.COMPONENT_1);
    xab.hasntView(GenericModel.FUNCTION_1_2);
  }

  protected void checkFunction(XABDiagram xab, String id, boolean mustBeItalic, boolean mustBeDash) {

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
