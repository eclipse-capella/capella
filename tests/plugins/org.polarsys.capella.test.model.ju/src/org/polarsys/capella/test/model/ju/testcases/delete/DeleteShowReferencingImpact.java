/*******************************************************************************
* Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.testcases.delete;

import java.util.List;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.shared.id.handler.IdManager;

public class DeleteShowReferencingImpact extends DeleteTest {

  @Override
  public void test() {
    List<Object> references = getReferencingElements(SA__ROOT_SF__SF1, SA__ROOT_SF__SF2);
    assertFalse("Referencing elements view shall not be empty!", references.isEmpty());
    
    CapellaElement fc1tosf2 = (CapellaElement)IdManager.getInstance().getEObject(SA__ROOT_SF__FC1__FCI_TO_SF2, scope);
    CapellaElement fc1tosf1 = (CapellaElement)IdManager.getInstance().getEObject(SA__ROOT_SF__FC1__FCI_TO_SF1, scope);
   
    assertTrue(fc1tosf2.getId() + " not found in referencing elements view", references.contains(fc1tosf2));
    assertTrue(fc1tosf1.getId() + " not found in referencing elements view", references.contains(fc1tosf1));
  }
}
