/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.recrpl.ju.testcases;

import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class UpdateREC_WithSuffix extends Re {

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

    String suffix = "_SUFFIX";
    
    // Create a RPL with a suffix
    createReplica(getObjects(LF1, LF2, FUNCTIONALEXCHANGE_1), REC, suffix);
    
    String REC_elementNameBefore = REC_LF1.getName();
    
    // update the REC from selected RPL
    updateDef(getObjects(LF1, LF2, FUNCTIONALEXCHANGE_1));
    
    String REC_elementNameAfter = REC_LF1.getName();
    
    // the RPL element name must be the same (no suffix added)
    assertEquals(REC_elementNameAfter, REC_elementNameBefore);
  }
}
