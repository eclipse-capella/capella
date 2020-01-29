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
import org.polarsys.capella.core.data.capellamodeller.Library;
import org.polarsys.capella.test.recrpl.ju.model.WholeContent;

public class CreateREC_WholeContent extends WholeContent {

  @Override
  public void performTest() throws Exception {

    Library library = (Library) getObject(LIBRARY);
    CatalogElement REC = createRECWholeContent(library);

    // REC shall contains Actor and its part
    mustReference(REC, getObject(SA_2));
    mustReference(REC, getObject(SA_2_PART));

    // REC shall not contains any other elements (skeleton)
    assertTrue("REC shall not reference any other elements than the actor", REC.getOwnedLinks().size() == 2);

  }

}
