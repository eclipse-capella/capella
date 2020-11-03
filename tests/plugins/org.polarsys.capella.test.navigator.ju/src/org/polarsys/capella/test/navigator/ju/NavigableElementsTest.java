/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.navigator.ju;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.platform.sirius.ui.navigator.internal.navigate.NavigationAdvisor;
import org.polarsys.capella.test.navigator.ju.model.NavigableElements;

public class NavigableElementsTest extends NavigableElements {

  @Override
  public void test() throws Exception {
    
    Collection<EObject> result = NavigationAdvisor.getInstance().getNavigableElements(LOGICAL_SYSTEM);
    //For a Component, retrieve related elements among Part
    assertTrue(result.contains(INTERFACE_1));
    assertTrue(result.contains(LOGICALFUNCTION_1));
    assertTrue(result.contains(LOGICAL_SYSTEM_PART));
    
    result = NavigationAdvisor.getInstance().getNavigableElements(LOGICAL_SYSTEM_PART);
    //For a Part, retrieve related elements (its type), and related elements of the type
    assertTrue(result.contains(LOGICAL_SYSTEM));
    assertTrue(result.contains(INTERFACE_1));
    assertTrue(result.contains(LOGICALFUNCTION_1));
    //The Part shall not be retrieved. Its a related element of the type, but it's the part itself
    assertTrue(!result.contains(LOGICAL_SYSTEM_PART));
  }

}
