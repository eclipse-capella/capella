/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.recrpl.ju.testcases;

import java.util.Arrays;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class LocationOnUpdate extends Re {

  public LogicalFunction createFunction(String element, EObject newContainer) throws Exception {
    LogicalFunction[] result = new LogicalFunction[1];
    ExecutionManager manager = ExecutionManagerRegistry.getInstance().getExecutionManager(TransactionUtil.getEditingDomain(getProject()));
    manager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        LogicalFunction lf = LaFactory.eINSTANCE.createLogicalFunction(element);
        ((LogicalFunction)newContainer).getOwnedFunctions().add(lf);
        result[0] = lf;
      }
    });
    return result[0];
  }
  
  @Override
  public void performTest() throws Exception {

    // Create a REC with a function
    CatalogElement REC1 = createREC(getObjects(LF1));
    
    //We create a RPL of REC1
    CatalogElement RPL1 = createReplica(Arrays.asList(getObject(LF3)), REC1);
    LogicalFunction RPL_LF1 = (LogicalFunction)ReplicableElementExt.getReferencingElement(RPL1, getObject(LF1));

    //Add two elements into the REC. One outside, one inside an exiREC elements
    LogicalFunction lf21 = createFunction("LF21", getObject(ROOT_LF));
    LogicalFunction lf22 = createFunction("LF21", getObject(LF1));
    updateCur(Arrays.asList(lf21, lf22), REC1);
    
    //We update the RPL. LF21 shall be contained into Default Container, LF22, into RPL of LF1
    updateReplica(Arrays.asList(RPL_LF1), RPL1);
    EObject RPL_LF21 = ReplicableElementExt.getReferencingElement(RPL1, lf21);
    EObject RPL_LF22 = ReplicableElementExt.getReferencingElement(RPL1, lf22);
    
    assertTrue("RPL of LF21 shall be contained into root LF", RPL_LF21.eContainer() == getObject(ROOT_LF));
    assertTrue("RPL of LF22 shall be contained into RPL of LF1", RPL_LF22.eContainer() == RPL_LF1);
    

    //We create two functions into the RPL, and promote it to the REC
    LogicalFunction lf31 = createFunction("LF31", getObject(ROOT_LF));
    LogicalFunction lf32 = createFunction("LF32", RPL_LF1);
    updateDef(Arrays.asList(RPL_LF1, lf31, lf32));
    
    //REC of LF31 shall exist and be located into Root LF
    CatalogElementLink RPL_LF31 = ReplicableElementExt.getReferencingLinks(lf31).iterator().next();
    assertTrue(RPL_LF31.getSource() == RPL1);
    assertTrue(RPL_LF31.getOrigin() != null);
    assertTrue(RPL_LF31.getOrigin().getTarget() != RPL_LF31.getTarget());
    assertTrue(RPL_LF31.getOrigin().getTarget().eContainer() == getObject(ROOT_LF));
    
    //REC of LF32 shall exist and be located into LF1
    CatalogElementLink RPL_LF32 = ReplicableElementExt.getReferencingLinks(lf32).iterator().next();
    assertTrue(RPL_LF32.getSource() == RPL1);
    assertTrue(RPL_LF32.getOrigin() != null);
    assertTrue(RPL_LF32.getOrigin().getTarget() != RPL_LF32.getTarget());
    assertTrue(RPL_LF32.getOrigin().getTarget().eContainer() == getObject(LF1));
    
  }

}
