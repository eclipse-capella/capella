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
package org.polarsys.capella.test.validation.rules.ju.testcases.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.common.utils.graph.IDirectedGraph;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.StringPropertyValue;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.core.ui.quickfix.resolver.DWF_D25_Resolver;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase;

/**
 * Test package dependency cycle detection. A cycle detection test is self-contained in a capella model, i.e. the expected test result is contained within the
 * model under test. To create a new test: 1) Create a new capella model that contains (or doesn't) any package dependency cycles. 2) For each cycle in the
 * model, create an owned StringPropertyValue under "SystemEngineering", named "cycle1", "cycle2", etc. 3) For each created StringPropertyValue, set its value
 * to a whitespace-separated list of element names that form the cycle. The order in which you put the element names in the value does not matter. The test will
 * scan its input model for presence of those PropertyValues, launch cycle detection and compares the actually detected cycles with the expected cycles.
 */
public abstract class AbstractPackageCycleTest extends ValidationRuleTestCase {
  
  public void test() throws Exception {
    ICapellaModel model = getTestModel(getRequiredTestModel());
    Project project = model.getProject(getSessionForTestModel(getRequiredTestModel()).getTransactionalEditingDomain());
    if (project != null) {
      SystemEngineering se = (SystemEngineering) project.getOwnedModelRoots().get(0);
      Couple<IDirectedGraph<EObject>, Collection<List<EObject>>> scc = new DWF_D25_Resolver().computeCyclesGraph(se);
      Collection<List<EObject>> foundCycles = null;
      if (scc == null) {
        // no cycles found...
        foundCycles = Collections.emptyList();
      } else {
        foundCycles = scc.getValue();
      }
      List<String> expectedCycles = getExpectedCycles(se);
      assertEquals(expectedCycles.size(), foundCycles.size());
      for (String expected : getExpectedCycles(se)) {
        findCycle(foundCycles, expected);
      }
    }
  }

  protected Set<String> cycleFromString(String cycle) {
    Set<String> s = new HashSet<String>();
    String[] elems = cycle.split("\\s+"); //$NON-NLS-1$
    for (String e : elems) {
      s.add(e);
    }
    return s;
  }

  protected Set<String> cycleFromEObjectList(List<EObject> list) {
    Set<String> s = new HashSet<String>();
    for (EObject e : list) {
      if (e instanceof AbstractNamedElement) {
        String name = ((AbstractNamedElement) e).getName();
        if (!s.add(name)) {
          fail("Found duplicate ocurrence of element '" + name + "' in cycle"); //$NON-NLS-1$ //$NON-NLS-2$
        }
      } else {
        fail("Expected an AbstractNamedElement in cycle!"); //$NON-NLS-1$
      }
    }
    return s;
  }

  private void findCycle(Collection<List<EObject>> foundCycles, String descriptor) {
    Set<String> expected = cycleFromString(descriptor);
    boolean found = false;
    for (List<EObject> cycle : foundCycles) {
      Set<String> current = cycleFromEObjectList(cycle);
      if (current.equals(expected)) {
        found = true;
        break;
      }
    }
    assertTrue("Expected cycle " + expected + " was not detected!", found); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public List<String> getExpectedCycles(SystemEngineering se) {
    List<String> result = new ArrayList<String>();
    List<AbstractPropertyValue> ppv = se.getOwnedPropertyValues();
    for (AbstractPropertyValue val : ppv) {
      if (val.getName().startsWith("cycle")) { //$NON-NLS-1$
        StringPropertyValue sval = ((StringPropertyValue) val);
        result.add(sval.getValue());
      }
    }
    return result;
  }

  @Override
  protected EClass getTargetedEClass() {
    return null;
  }

  @Override
  protected String getRuleID() {
    return null;
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return null;
  }
}
