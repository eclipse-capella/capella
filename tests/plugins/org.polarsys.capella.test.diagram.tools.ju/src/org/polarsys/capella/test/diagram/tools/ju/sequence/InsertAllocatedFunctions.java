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
package org.polarsys.capella.test.diagram.tools.ju.sequence;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.impl.InstanceRoleImpl;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.framework.model.GenericModel;

public class InsertAllocatedFunctions extends SequenceTest {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] types = { BlockArchitectureExt.Type.OA, BlockArchitectureExt.Type.SA,
        BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA };
    BlockArchitectureExt.Type[] typesIS = { BlockArchitectureExt.Type.SA,
        BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA };

    testOnAllLevels(types, SequenceType.ES);
    testOnAllLevels(typesIS, SequenceType.IS);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    setUpDiagram(diagram);

    diagram.insertAllocatedFunction(actor1, GenericModel.FUNCTION_1);
    diagram.insertAllocatedFunction(actor1, GenericModel.FUNCTION_2);
    diagram.insertAllocatedFunction(actor2, GenericModel.FUNCTION_3);
  }

  @Override
  public void setUpDiagram(SequenceDiagram diagram) {
    actor1 = diagram.createActor();
    actor2 = diagram.createActor();
    XDFBDiagram xdfb = XDFBDiagram.createDiagram(context, getRootFunctionId(diagram.getDiagramBlockArchitecture()));
    xdfb.createFunction(GenericModel.FUNCTION_1);
    xdfb.createFunction(GenericModel.FUNCTION_1_1, GenericModel.FUNCTION_1);
    xdfb.createFunction(GenericModel.FUNCTION_2);
    xdfb.createFunction(GenericModel.FUNCTION_3);

    addAllocatedFunctions(actor1, GenericModel.FUNCTION_1);
    addAllocatedFunctions(actor1, GenericModel.FUNCTION_2);
    addAllocatedFunctions(actor2, GenericModel.FUNCTION_3);
  }
  
  private void addAllocatedFunctions(String elementId, String functionId) {
    EObject obj = context.getSemanticElement(elementId);
    if(obj instanceof InstanceRoleImpl) {
      InstanceRoleImpl instRole = (InstanceRoleImpl) obj;
      AbstractInstance  inst = instRole.getRepresentedInstance();
      if(inst instanceof Part) {
        Part part = (Part) inst;
        AbstractType type = part.getAbstractType();
        if(type instanceof AbstractFunctionalBlock) {
          AbstractFunctionalBlock block = (AbstractFunctionalBlock) type;
          EObject objF = context.getSemanticElement(functionId);
          if(objF instanceof AbstractFunction) {
            AbstractFunction f = (AbstractFunction) objF;
            final AbstractCommand cmd = new AbstractReadWriteCommand() {
              public void run() {
                ComponentFunctionalAllocation cfa = FaFactory.eINSTANCE.createComponentFunctionalAllocation();
                block.getOwnedFunctionalAllocation().add(cfa);
                cfa.setTargetElement(f);
                cfa.setSourceElement(block);
                CapellaElementExt.creationService(cfa);
              }
            };
            context.getExecutionManager().execute(cmd);
          }
        }
      }
    }
  }
}
