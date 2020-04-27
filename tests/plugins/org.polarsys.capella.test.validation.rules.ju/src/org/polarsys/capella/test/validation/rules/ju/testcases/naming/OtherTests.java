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

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Name conflict constraint tests that do not fit in any other test class.
 */
public class OtherTests extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] {
        "org.polarsys.capella.core.data.core.validation.I_08",
        "org.polarsys.capella.core.data.core.validation.I_19"});
  }

  /**
   * A LogicalComponent and a Part may have the same name.
   */
  public void lcAndPartSameName() {
    LogicalComponent container = LaFactory.eINSTANCE.createLogicalComponent();
    container.setName("container"); //$NON-NLS-1$

    LogicalComponent lc1 = LaFactory.eINSTANCE.createLogicalComponent();
    lc1.setName("lc1"); //$NON-NLS-1$

    Part part1 = CsFactory.eINSTANCE.createPart();
    part1.setName("lc1"); //$NON-NLS-1$

    container.getOwnedFeatures().add(part1);
    container.getOwnedLogicalComponents().add(lc1);

    ok(container);
  }
  
  /**
   * Elements with no names do not violate the name conflict constraint
   */
  public void unnamedElements1(){
    
    DataPkg pkg =InformationFactory.eINSTANCE.createDataPkg("package"); //$NON-NLS-1$
    org.polarsys.capella.core.data.information.Class c1 = InformationFactory.eINSTANCE.createClass(null);
    org.polarsys.capella.core.data.information.Class c2 = InformationFactory.eINSTANCE.createClass(null);
    
    pkg.getOwnedClasses().add(c1);
    pkg.getOwnedClasses().add(c2);
    ok(pkg);
  }
  
  /**
   * Elements with the empty name do not violate the name conflict constraint
   */
  public void unnamedElements2(){
    
    DataPkg pkg =InformationFactory.eINSTANCE.createDataPkg("package"); //$NON-NLS-1$
    org.polarsys.capella.core.data.information.Class c1 = InformationFactory.eINSTANCE.createClass(ICommonConstants.EMPTY_STRING);
    org.polarsys.capella.core.data.information.Class c2 = InformationFactory.eINSTANCE.createClass(ICommonConstants.EMPTY_STRING);
    
    pkg.getOwnedClasses().add(c1);
    pkg.getOwnedClasses().add(c2);
    ok(pkg);
  }
  
  /**
   * Elements with the name 'null' (case insensitive) do not violate the name conflict constraint.
   */
  public void unnamedElements3(){
    
    DataPkg pkg =InformationFactory.eINSTANCE.createDataPkg("package"); //$NON-NLS-1$
    org.polarsys.capella.core.data.information.Class c1 = InformationFactory.eINSTANCE.createClass("null"); //$NON-NLS-1$
    org.polarsys.capella.core.data.information.Class c2 = InformationFactory.eINSTANCE.createClass("null"); //$NON-NLS-1$
    
    pkg.getOwnedClasses().add(c1);
    pkg.getOwnedClasses().add(c2);
    ok(pkg);
  }

  @Override
  public void test() throws Exception {
    lcAndPartSameName();
    unnamedElements1();
    unnamedElements2();
    unnamedElements3();
  }

}
