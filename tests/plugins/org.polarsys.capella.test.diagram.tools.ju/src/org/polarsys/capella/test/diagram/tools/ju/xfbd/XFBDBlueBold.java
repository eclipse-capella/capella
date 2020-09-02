/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xfbd;

import java.util.Arrays;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.FunctionAllocator;
import org.polarsys.capella.core.sirius.analysis.constants.ColorNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.XBreakdownDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;
import org.polarsys.capella.test.framework.model.GenericModel;

public class XFBDBlueBold extends EmptyProject {

  @Override
  public void test() throws Exception {
    SessionContext context = new SessionContext(getSession(getRequiredTestModel()));

    for (BlockArchitectureExt.Type type : Arrays.asList(BlockArchitectureExt.Type.OA, BlockArchitectureExt.Type.SA,
        BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA)) {
      testNormalFunction(context, type);
      testActorFunction(context, type);
      testParentActorFunction(context, type);
    }

  }

  public void testNormalFunction(SessionContext context, BlockArchitectureExt.Type type) {

    // Bold if the diagram.target's function is a normal function
    context.getExecutionManager().execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        SkeletonHelper.createFunction(getRootFunctionId(type), GenericModel.FUNCTION_1, context);
      }
    });

    XBreakdownDiagram diagram = XBreakdownDiagram.createFBDiagram(context, GenericModel.FUNCTION_1);
    isBold(diagram, GenericModel.FUNCTION_1);
    diagram.close();

  }

  public void testActorFunction(SessionContext context, BlockArchitectureExt.Type type) {

    // Bold if the diagram.target's function is an actor function
    context.getExecutionManager().execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        SkeletonHelper.createFunction(getRootFunctionId(type), GenericModel.FUNCTION_2, context);
        SkeletonHelper.createActor(getContextId(type), GenericModel.ACTOR_1, GenericModel.PART_1, context);
        FunctionAllocator.allocate(context.getSemanticElements(GenericModel.FUNCTION_2))
            .on(context.getSemanticElement(GenericModel.ACTOR_1));
      }
    });

    XBreakdownDiagram diagram = XBreakdownDiagram.createFBDiagram(context, GenericModel.FUNCTION_2);
    isBold(diagram, GenericModel.FUNCTION_2);
    if (type != BlockArchitectureExt.Type.OA) {
      isActorColor(diagram, GenericModel.FUNCTION_2);
    }
    diagram.close();
  }

  public void testParentActorFunction(SessionContext context, BlockArchitectureExt.Type type) {

    // Bold if the diagram.target's function is a function parent of an actor function
    context.getExecutionManager().execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        SkeletonHelper.createFunction(getRootFunctionId(type), GenericModel.FUNCTION_3, context);
        SkeletonHelper.createFunction(GenericModel.FUNCTION_3, GenericModel.FUNCTION_1_3, context);
        SkeletonHelper.createActor(getContextId(type), GenericModel.ACTOR_2, GenericModel.PART_2, context);
        FunctionAllocator.allocate(context.getSemanticElements(GenericModel.FUNCTION_1_3))
            .on(context.getSemanticElement(GenericModel.ACTOR_2));
      }
    });

    XBreakdownDiagram diagram = XBreakdownDiagram.createFBDiagram(context, GenericModel.FUNCTION_3);
    isBold(diagram, GenericModel.FUNCTION_3);
    if (type != BlockArchitectureExt.Type.OA) {
      isActorColor(diagram, GenericModel.FUNCTION_3);
    }
    diagram.close();
  }

  private void isBold(XBreakdownDiagram diagram, String semanticId) {
    assertTrue(NLS.bind("{0} shall be bold in breakdown diagram {0}", semanticId, diagram.getDiagram().getName()),
        ((DNode) diagram.getView(semanticId)).getOwnedStyle().getLabelFormat().contains(FontFormat.BOLD_LITERAL));
  }

  private void isActorColor(XBreakdownDiagram diagram, String semanticId) {
    boolean result = ColorNameConstants.equals(
        ((Square) (((DNode) diagram.getView(semanticId)).getOwnedStyle())).getColor(), ColorNameConstants.ACTOR);
    assertTrue(NLS.bind("{0} shall be blue in breakdown diagram {0}", semanticId, diagram.getDiagram().getName()),
        result);
  }
}