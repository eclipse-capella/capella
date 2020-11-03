/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Name conflict tests for DataValues. 
 * 
 * A DataValue never has a name conflict.
 */
public class DataValueTests extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] {
        "org.polarsys.capella.core.data.core.validation.I_08",
        "org.polarsys.capella.core.data.core.validation.I_19"});
  }
  /**
   * Two literal numeric values in the same package can have the same name.
   */
  public void literalNumericValue1(){
    doTest(
        DatavalueFactory.eINSTANCE.createLiteralNumericValue(),
        DatavalueFactory.eINSTANCE.createLiteralNumericValue()
    );
  }
  
  /**
   * Two literal string values in the same package can have the same name.
   */
  public void literalStringValue1(){
    doTest(
        DatavalueFactory.eINSTANCE.createLiteralStringValue(),
        DatavalueFactory.eINSTANCE.createLiteralStringValue()
    );
  }
  
  /**
   * Two literal boolean values in the same package can have the same name.
   */
  public void literalBooleanValue1(){
    doTest(
       DatavalueFactory.eINSTANCE.createLiteralBooleanValue(), 
       DatavalueFactory.eINSTANCE.createLiteralBooleanValue()
    );
  }
  
  private void doTest(DataValue d1, DataValue d2){
    DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg("package"); //$NON-NLS-1$
    d1.setName("name"); //$NON-NLS-1$
    d2.setName("name"); //$NON-NLS-1$
    pkg.getOwnedDataValues().add(d1);
    pkg.getOwnedDataValues().add(d2);
    ok(pkg);
  }

  @Override
  public void test() throws Exception {
    literalNumericValue1();
    literalStringValue1();
    literalBooleanValue1();
  }

}
