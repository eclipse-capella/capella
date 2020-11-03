/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.dcom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase;

/**
 * Test the validation rule for unused exchange items on function ports
 */
public class Rule_DCOM_21 extends ValidationRulePartialTestCase {

  private final String PREFIX_OK = "ok_"; //$NON-NLS-1$
  private final String PREFIX_FAIL = "fail_"; //$NON-NLS-1$

  List<FunctionPort> okPorts = new ArrayList<FunctionPort>();
  List<FunctionPort> failPorts = new ArrayList<FunctionPort>();

  /**
   * Test objects are calculated dynamically by looking for ports named ok_... and fail_...
   */
  protected void setUp() throws Exception{
    super.setUp();

    CapellaModel model = getTestModel(getRequiredTestModel());
    TransactionalEditingDomain domain = model.getEditingDomain();
    Project project = model.getProject(domain);
    for (Iterator<EObject> it = project.eAllContents(); it.hasNext();){
      EObject next = it.next();
      if (next instanceof FunctionPort){
        if (((FunctionPort) next).getName().startsWith(PREFIX_OK)){
          okPorts.add((FunctionPort)next);
        } else if (((FunctionPort) next).getName().startsWith(PREFIX_FAIL)){
          failPorts.add((FunctionPort)next);
        }
      }
    }
  }


  @Override
  public void test() throws Exception {

    // check that we test at least one ok and one fail port
    assertFalse(okPorts.isEmpty());
    assertFalse(failPorts.isEmpty());

    super.test();
  }

  @Override
  protected List<String> getScopeDefinition() {
    List<String> result = new ArrayList<String>();
    for (FunctionPort p : okPorts){
      result.add(p.getId());
    }
    for (FunctionPort p : failPorts){
      result.add(p.getId());
    }
    return result;
  }

  @Override
  protected String getRequiredTestModel() {
    return "testUnusedExchangeItems"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return FaPackage.Literals.FUNCTION_PORT;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.fa.validation.DCOM_21"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    List<OracleDefinition> result = new ArrayList<OracleDefinition>();
    for (FunctionPort p : okPorts){
      result.add(new OracleDefinition(p.getId(), 0));
    }
    for (FunctionPort p : failPorts){
      // FIXME because of https://bugs.polarsys.org/show_bug.cgi?id=1342 setting problem count to 1 works atm
      // FIXME find a way to encode problem count once the above bug is fixed
      result.add(new OracleDefinition(p.getId(), 1));
    }
    return result;
  }

}
