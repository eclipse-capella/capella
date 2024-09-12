/*******************************************************************************
 * Copyright (c) 2024, 2024 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.namingConventions;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Ensure that Property names are different from the containing Class name.
 */
public class Rule_NC_1_3_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.information.validation.NC_1-3" });
  }
  
  public void Rule_NC_1_3_TestCase_OK() {
    final Property property_ok = InformationFactory.eINSTANCE.createProperty();
    property_ok.setName("My Property Name");
    
    final Class parentClass = InformationFactory.eINSTANCE.createClass();
    parentClass.setName("Another Name for parent Class");
    parentClass.getOwnedFeatures().add(property_ok);
    
    ok(property_ok);
  }
  
  /**
   * 
   */
  public void Rule_NC_1_3_TestCase_KO() {
    final Property property_ko = InformationFactory.eINSTANCE.createProperty();
    property_ko.setName("My Property Name");
    
    final Class parentClass = InformationFactory.eINSTANCE.createClass();
    parentClass.setName(property_ko.getName());
    parentClass.getOwnedFeatures().add(property_ko);
    
    ko(property_ko);
  }

  @Override
  public void test() throws Exception {
    Rule_NC_1_3_TestCase_OK();
    Rule_NC_1_3_TestCase_KO();
  }

}
