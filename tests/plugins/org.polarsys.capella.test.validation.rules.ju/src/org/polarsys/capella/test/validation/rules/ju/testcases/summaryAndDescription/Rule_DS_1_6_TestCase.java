/*******************************************************************************
 * Copyright (c) 2024 Thales LAS France SAS.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.summaryAndDescription;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Ensure that Properties have a description
 */
public class Rule_DS_1_6_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.information.validation.DS_1-6" });
  }

  public void Rule_DS_1_6_TestCase_OK() {
    final Property property_ok = InformationFactory.eINSTANCE.createProperty();
    property_ok.setDescription("My Description");
    
    final org.polarsys.capella.core.data.information.Class parentClass = InformationFactory.eINSTANCE.createClass();
    parentClass.getOwnedFeatures().add(property_ok);

    ok(property_ok);
  }

  public void Rule_DS_1_6_TestCase_KO() {
    final org.polarsys.capella.core.data.information.Class parentClass = InformationFactory.eINSTANCE.createClass();
    
    final Property property_ko_1 = InformationFactory.eINSTANCE.createProperty();
    property_ko_1.setDescription(null);
    parentClass.getOwnedFeatures().add(property_ko_1);
    ko(property_ko_1);

    final Property property_ko_2 = InformationFactory.eINSTANCE.createProperty();
    property_ko_2.setDescription("");
    parentClass.getOwnedFeatures().add(property_ko_2);
    ko(property_ko_2);
  }

  @Override
  public void test() throws Exception {
    Rule_DS_1_6_TestCase_OK();
    Rule_DS_1_6_TestCase_KO();
  }

}
