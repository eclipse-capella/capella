/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class UpdateRPL_RenameElementWithSuffix extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC
    CatalogElement REC = createREC(getObjects(LF1, LF2, FUNCTIONALEXCHANGE_1));
    
    // a element should be suffixed
    LogicalFunction REC_LF1 = (LogicalFunction) getObject(LF1);
    for (CatalogElementLink link : REC.getOwnedLinks()) {
      if (link.getTarget() == REC_LF1) {
        link.setSuffixed(true);
        break;
      }
    }
    
    String newName = "LF1_modif";
    String suffix = "_SUFFIX";
    
    // Create a RPL with a suffix
    CatalogElement RPL = createReplica(getObjects(LF1, LF2, FUNCTIONALEXCHANGE_1), REC, suffix);
    
    // rename the REC element
    REC_LF1.setName(newName);
    
    // update the RPL: must update the suffixed element
    updateReplica(Collections.singletonList((EObject) RPL), RPL);
    
    EObject RPL_LF1 = ReplicableElementExt.getReferencingElement(RPL, getObject(LF1));
    String RPL_elementNameAfter = ((LogicalFunction) RPL_LF1).getName();
    
    // the RPL element name must be the new REC element name + the suffix
    assertEquals(RPL_elementNameAfter, newName + suffix);
  }
}
