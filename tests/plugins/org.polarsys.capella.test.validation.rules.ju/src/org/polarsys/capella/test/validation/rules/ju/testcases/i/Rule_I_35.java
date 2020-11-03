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
package org.polarsys.capella.test.validation.rules.ju.testcases.i;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase;

/**
 * Test the validation rule for functional exchange name consistency
 */
public class Rule_I_35 extends ValidationRulePartialTestCase {

  private final String PREFIX_OK = "ok_"; //$NON-NLS-1$
  private final String PREFIX_FAIL = "fail_"; //$NON-NLS-1$

  List<FunctionalExchange> okElements = new ArrayList<FunctionalExchange>();
  List<FunctionalExchange> failElements = new ArrayList<FunctionalExchange>();

  /**
   * Test objects are calculated dynamically by looking for functional exchanges with summary
   * ok_... and fail_...
   */
  protected void setUp() throws Exception{
    super.setUp();

    CapellaModel model = getTestModel(getRequiredTestModel());
    TransactionalEditingDomain domain = model.getEditingDomain();
    Project project = model.getProject(domain);
    for (Iterator<EObject> it = project.eAllContents(); it.hasNext();){
      EObject next = it.next();
      if (next instanceof FunctionalExchange){
        if (matchOk(next)){
          okElements.add((FunctionalExchange)next);
        } else if (matchFail(next)){
          failElements.add((FunctionalExchange)next);
        }
      }
    }
  }

  protected boolean matchFail(EObject element){
    return matchPrefix(element, CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY, PREFIX_FAIL);
  }

  protected boolean matchOk(EObject element){
    return matchPrefix(element, CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY, PREFIX_OK);
  }

  protected boolean matchPrefix(EObject element, EStructuralFeature attribute, String prefix){
    boolean result = false;
    String value = (String) element.eGet(attribute);
    if (value!= null && value.startsWith(prefix)){
      result = true;
    }
    return result;
  }
  
  @Override
  public void test() throws Exception {

    // check that we test at least one ok and one fail port
    assertFalse(okElements.isEmpty());
    assertFalse(failElements.isEmpty());

    super.test();
  }

  @Override
  protected List<String> getScopeDefinition() {
    List<String> result = new ArrayList<String>();
    for (EObject e : okElements){
      result.add(EcoreUtil.getID(e));
    }
    for (EObject e : failElements){
      result.add(EcoreUtil.getID(e));
    }
    return result;
  }

  @Override
  protected String getRequiredTestModel() {
    return "testFunctionalExchangeNameConsistency"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return FaPackage.Literals.FUNCTIONAL_EXCHANGE;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.fa.validation.I_35"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    List<OracleDefinition> result = new ArrayList<OracleDefinition>();
    for (EObject ok : okElements){
      result.add(new OracleDefinition(EcoreUtil.getID(ok), 0));
    }
    for (EObject fail : failElements){
      // FIXME because of https://bugs.polarsys.org/show_bug.cgi?id=1342 setting problem count to 1 works atm
      // FIXME find a way to encode problem count once the above bug is fixed
      result.add(new OracleDefinition(EcoreUtil.getID(fail), 1));
    }
    return result;
  }

}
