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
package org.polarsys.capella.test.transition.ju.testcases;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

public class RootPropertyValueTestCase extends TopDownTransitionTestCase {
  
  private static String SYSTEMFUNCTION_1 = "90440c5f-7ccc-4881-8019-c3fc3ede326b";
  
  public List<String> getRequiredTestModels() {
    return Collections.singletonList("pv");
  }
  
  @Override
  public void performTest() throws Exception {
    setPreferenceValue(ITopDownConstants.OPTIONS__PROPERTY_VALUE__APPLIED_PROPERTY_VALUES, Boolean.TRUE);
    performFunctionalTransition(getObjects(SYSTEMFUNCTION_1));

    EObject object = mustBeMonoTransitioned(SYSTEMFUNCTION_1);
    assertTrue(object instanceof LogicalFunction);
    
    LogicalFunction function = (LogicalFunction)object;
    assertTrue(function.getAppliedPropertyValueGroups().size() == 4);
    for (PropertyValueGroup group : function.getAppliedPropertyValueGroups()) {
      assertTrue(group.eResource() == getObject(SYSTEMFUNCTION_1).eResource());
    }
    
    assertTrue(function.getAppliedPropertyValues().size() == 4);
    for (AbstractPropertyValue pv : function.getAppliedPropertyValues()) {
      assertTrue(pv.eResource() == getObject(SYSTEMFUNCTION_1).eResource());
    }
  }
  
}
