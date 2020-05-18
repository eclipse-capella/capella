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
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.RecCatalog;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class CompositeREC extends Re {

  public void moveFunction(String element, String newContainer) throws Exception {
    ExecutionManager manager = ExecutionManagerRegistry.getInstance().getExecutionManager(TransactionUtil.getEditingDomain(getProject()));
    manager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        ((LogicalFunction)getObject(newContainer)).getOwnedFunctions().add((LogicalFunction)getObject(element));
      }
    });
  }
  
  public LogicalFunction createFunction(String element, String newContainer) throws Exception {
    LogicalFunction[] result = new LogicalFunction[1];
    ExecutionManager manager = ExecutionManagerRegistry.getInstance().getExecutionManager(TransactionUtil.getEditingDomain(getProject()));
    manager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        LogicalFunction lf = LaFactory.eINSTANCE.createLogicalFunction(element);
        ((LogicalFunction)getObject(newContainer)).getOwnedFunctions().add(lf);
        result[0] = lf;
      }
    });
    return result[0];
  }
  
  @Override
  public void performTest() throws Exception {

    // Create a REC with a function
    CatalogElement REC1 = createREC(getObjects(LF1));
    // Create a 2nd REC with a function
    CatalogElement REC2 = createREC(getObjects(LF2));
    // Move the REC2 into REC1
    moveFunction(LF2, LF1);
    
    //We add REC2 into REC1
    updateCur(Collections.singletonList(REC2), REC1);
    
    RecCatalog catalog = (RecCatalog)REC1.eContainer();
    
    //We create a RPL of REC1, a new RPL of REC2 shall be created inside REC Catalog
    CatalogElement RPL1 = createReplica(Arrays.asList(catalog, getObject(LF3)), REC1);
    
    //We update the RPL of REC2. RPL of LF2 shall exist and be located into LF1.
    CatalogElement RPL_REC2 = (CatalogElement)ReplicableElementExt.getReferencingElement(RPL1, REC2);
    updateReplica(Arrays.asList(RPL_REC2), RPL_REC2);

    EObject RPL_LF1 = ReplicableElementExt.getReferencingElement(RPL1, getObject(LF1));
    EObject RPL_LF2 = ReplicableElementExt.getReferencingElement(RPL_REC2, getObject(LF2));
    assertTrue("RPL of LF2 shall be contained into RPL of LF1", RPL_LF2.eContainer() == RPL_LF1);
    
    
    
    //Create a function LF21 aside LF1, another inside LF1 (aside LF2)
    LogicalFunction lf21 = createFunction("LF21", ROOT_LF);
    LogicalFunction lf22 = createFunction("LF22", LF1);
    updateCur(Arrays.asList(lf21, lf22), REC2);
    
    updateReplica(Arrays.asList(RPL_REC2), RPL_REC2);
    EObject RPL_LF21 = ReplicableElementExt.getReferencingElement(RPL_REC2, lf21);
    assertTrue("RPL of LF21 shall be contained into root LF", RPL_LF21.eContainer() == getObject(ROOT_LF));

    EObject RPL_LF22 = ReplicableElementExt.getReferencingElement(RPL_REC2, lf22);
    assertTrue("RPL of LF22 shall be contained into RPL of LF1", RPL_LF22.eContainer() == RPL_LF1);
    
    
    System.out.println(0);
  }

}
