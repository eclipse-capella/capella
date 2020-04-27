/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.naming;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DatatypeFactory;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Test name conflict constraints focusing on data types.
 */
public class DataTypeTests extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] {
        "org.polarsys.capella.core.data.information.validation.I_02",
        "org.polarsys.capella.core.data.core.validation.I_19"});
  }

  private final String name1 = "wololo"; //$NON-NLS-1$
  private final String NAME1 = "WOLOLO"; //$NON-NLS-1$

  /**
   * A string and a boolean type with the same name are not allowed.
   */
  public void simpleDatatypes1() {
    DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg();
    BooleanType type1 = DatatypeFactory.eINSTANCE.createBooleanType(NAME1);
    StringType type2 = DatatypeFactory.eINSTANCE.createStringType(NAME1);
    pkg.getOwnedDataTypes().add(type1);
    pkg.getOwnedDataTypes().add(type2);
    ko(pkg);
  }

  /**
   * A string and a boolean type with the same name in different capitalization are not allowed.
   */
  public void simpleDatatypes2() {
    DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg();
    BooleanType type1 = DatatypeFactory.eINSTANCE.createBooleanType(NAME1);
    StringType type2 = DatatypeFactory.eINSTANCE.createStringType(name1);
    pkg.getOwnedDataTypes().add(type1);
    pkg.getOwnedDataTypes().add(type2);
    ko(pkg);
  }
  
 
  /**
   * A numeric and a boolean type with the same name are not allowed.
   */
  public void simpleDatatypes3() {
    DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg();
    BooleanType type1 = DatatypeFactory.eINSTANCE.createBooleanType(NAME1);
    NumericType type2 = DatatypeFactory.eINSTANCE.createNumericType(NAME1);
    pkg.getOwnedDataTypes().add(type1);
    pkg.getOwnedDataTypes().add(type2);
    ko(pkg);
  }
  
  /**
   * A numeric and a boolean type with the same name in different capitalization are not allowed.
   */
  public void simpleDatatypes4() {
    DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg();
    BooleanType type1 = DatatypeFactory.eINSTANCE.createBooleanType(NAME1);
    NumericType type2 = DatatypeFactory.eINSTANCE.createNumericType(name1);
    pkg.getOwnedDataTypes().add(type1);
    pkg.getOwnedDataTypes().add(type2);
    ko(pkg);
  }
  
  
  /**
   * A numeric and a string type with the same name are not allowed.
   */
  public void simpleDatatypes5() {
    DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg();
    StringType type1 = DatatypeFactory.eINSTANCE.createStringType(NAME1);
    NumericType type2 = DatatypeFactory.eINSTANCE.createNumericType(NAME1);
    pkg.getOwnedDataTypes().add(type1);
    pkg.getOwnedDataTypes().add(type2);
    ko(pkg);
  }
  
  /**
   * A numeric and a string type with the same name in different capitalization are not allowed.
   */
  public void simpleDatatypes6() {
    DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg();
    StringType type1 = DatatypeFactory.eINSTANCE.createStringType(NAME1);
    NumericType type2 = DatatypeFactory.eINSTANCE.createNumericType(name1);
    pkg.getOwnedDataTypes().add(type1);
    pkg.getOwnedDataTypes().add(type2);
    ko(pkg);
  }
  
 
  /**
   * A (numeric) type and a DataPackage with the same name are not allowed.
   */
  public void simpleDatatypes7() {
    DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg();
    StringType type1 = DatatypeFactory.eINSTANCE.createStringType(NAME1);
    DataPkg sub = InformationFactory.eINSTANCE.createDataPkg(NAME1);
    pkg.getOwnedDataTypes().add(type1);
    pkg.getOwnedDataPkgs().add(sub);
    ko(pkg);
  }
  
  /**
   * A (numeric) type and a DataPackage with the same name in different capitalization are not allowed.
   */
  public void simpleDatatypes8() {
    DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg();
    StringType type1 = DatatypeFactory.eINSTANCE.createStringType(NAME1);
    DataPkg sub = InformationFactory.eINSTANCE.createDataPkg(name1);
    pkg.getOwnedDataTypes().add(type1);
    pkg.getOwnedDataPkgs().add(sub);
    ko(pkg);
  }

  @Override
  public void test() throws Exception {
    simpleDatatypes1();
    simpleDatatypes2();
    simpleDatatypes3();
    simpleDatatypes4();
    simpleDatatypes5();
    simpleDatatypes6();
    simpleDatatypes7();
    simpleDatatypes8();
  }
  
}
