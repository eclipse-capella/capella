/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

public class LcWithPropertyValueTestCase extends TopDownTransitionTestCase {
  
  private static String LA__LC1 = "5f7e5043-541e-4f2d-81f9-361309d15433";
  
  @Override
  public void performTest() throws Exception {
	setPreferenceValue(ITopDownConstants.OPTIONS__PROPERTY_VALUE__APPLIED_PROPERTY_VALUES, Boolean.TRUE);
	
    // The LA__LC1 has an EnumarationPropertyValue referenced in appliedPropertyValues
    performLCtoPCTransition(getObjects(LA__LC1));

    EObject physicalComponent = mustBeMonoTransitioned(LA__LC1);
    assertTrue(physicalComponent instanceof PhysicalComponent);
    
    // The transitioned PhysicalComponent should also reference this EnumerationPropertyValue in appliedPropertyValues
    assertEquals(1, ((PhysicalComponent) physicalComponent).getAppliedPropertyValues().size());
  }
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("enumInPropertyPkgUnderSysEng");
  }
}
