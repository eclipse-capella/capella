/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.junit.Assert;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * A Test, which checks the coverage of validation rules tested
 */
public class RulesCoverageTest extends BasicTestCase {

  private List<String> _testedRulesIdList;

  public RulesCoverageTest() {
    _testedRulesIdList = new ArrayList<String>();
  }

  /**
   * get the list of all validation rules id
   * @return
   */
  private List<String> getAllCapellaConstraintId() {
    ConstraintRegistry registry = ConstraintRegistry.getInstance();
    List<String> allRulesId = new ArrayList<String>();
    Collection<IConstraintDescriptor> allDescriptors = registry.getAllDescriptors();
    for (IConstraintDescriptor desc : allDescriptors) {
      if (desc.getId().contains("org.polarsys.capella.core")) { //$NON-NLS-1$
        allRulesId.add(desc.getId());
      }
    }
    return allRulesId;
  }

  @Override
  public void test() throws Exception {
    List<String> allRulesId = getAllCapellaConstraintId();

    // the list of not tested rules
    List<String> missingRules = new ArrayList<String>();
    for (String current : allRulesId) {
      // if an id of allRulesId is not contains in _testedRulesIdList, it means it is not tested
      if (!_testedRulesIdList.contains(current)) {
        missingRules.add(current);
      }
    }

    Assert.assertTrue(missingRules.size() + " validation rules are not tested: " + missingRules, missingRules.isEmpty()); //$NON-NLS-1$
  }

  public void testRulesCoverageTest() throws Exception {
    test();
  }
}
