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
package org.polarsys.capella.test.validation.rules.ju.testcases.namingConventions;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Property names are different from their Target Type name
 * 
 * Pattern is,
 *   <CLASS> Class_P (is Primitive)
 *   <CLASS> Class_T
 *     <PROPERTY> Class_P    <-- Is OK for NC_2_2
 *     <PROPERTY> Property_T <-- Is KO for NC_2_2
 * 
 */
public class Rule_NC_2_2_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.information.validation.NC_2-2" });
  }
  
  public void Rule_NC_2_2_TestCase_OK() {
    final DataPkg dataPkg = InformationFactory.eINSTANCE.createDataPkg();
    
    final Class class_P = InformationFactory.eINSTANCE.createClass("Class_P");
    dataPkg.getOwnedClasses().add(class_P);
    class_P.setIsPrimitive(true);
    
    final Class class_T = InformationFactory.eINSTANCE.createClass("Class_T");
    dataPkg.getOwnedClasses().add(class_T);
    
    final Property property_T = InformationFactory.eINSTANCE.createProperty();
    property_T.setAbstractType(class_P);
    property_T.setName("Property_T"); // OK
    class_T.getOwnedFeatures().add(property_T);
    
    ok(dataPkg);
  }
  
  /**
   * 
   */
  public void Rule_NC_2_2_TestCase_KO() {
    final DataPkg dataPkg = InformationFactory.eINSTANCE.createDataPkg();
    
    final Class class_P = InformationFactory.eINSTANCE.createClass("Class_P");
    dataPkg.getOwnedClasses().add(class_P);
    class_P.setIsPrimitive(true);
    
    final Class class_T = InformationFactory.eINSTANCE.createClass("Class_T");
    dataPkg.getOwnedClasses().add(class_T);
    
    final Property property_T = InformationFactory.eINSTANCE.createProperty();
    property_T.setAbstractType(class_P);
    property_T.setName(property_T.getAbstractType().getName()); // KO !
    class_T.getOwnedFeatures().add(property_T);
    
    ko(dataPkg);
  }

  @Override
  public void test() throws Exception {
    Rule_NC_2_2_TestCase_OK();
    Rule_NC_2_2_TestCase_KO();
  }

}
