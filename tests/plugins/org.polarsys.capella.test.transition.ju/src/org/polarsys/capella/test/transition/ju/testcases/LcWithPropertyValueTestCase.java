/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.transition.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

public class LcWithPropertyValueTestCase extends TopDownTransitionTestCase {
  
  private static String LA__LC1 = "5f7e5043-541e-4f2d-81f9-361309d15433";
  
  @Override
  public void performTest() throws Exception {
    // The LA__LC1 has an EnumarationPropertyValue referenced in appliedPropertyValues
    performLCtoPCTransition(getObjects(LA__LC1));

    Component logicalComponent = shouldExist(LA__LC1);
    
    PhysicalComponent physicalComponent = (PhysicalComponent) mustBeMonoTransitioned(logicalComponent);

    // The transitioned PhysicalComponent should also reference this EnumerationPropertyValue in appliedPropertyValues
    assertFalse(physicalComponent.getAppliedPropertyValues().isEmpty());
    assertTrue(physicalComponent.getAppliedPropertyValues().size() == 1);
  }
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("enumInPropertyPkgUnderSysEng");
  }
}
