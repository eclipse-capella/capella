/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.progressmonitoring.ju.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.ui.metric.actions.MetricAction;
import org.polarsys.capella.core.ui.metric.core.MetricTree;
import org.polarsys.capella.core.ui.metric.dialog.MetricLabelProvider;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class MetricTest extends BasicTestCase {

  @Override
  public void test() throws Exception {
    Map<String, Integer> oaMetrics = new HashMap<>();
    oaMetrics.put("Change Event", 1);
    oaMetrics.put("Combined Fragment", 2);
    oaMetrics.put("Communication Mean", 2);
    oaMetrics.put("Constraint", 4);
    oaMetrics.put("Data Pkg", 2);
    oaMetrics.put("Entity", 8);
    oaMetrics.put("Entity Pkg", 1);
    oaMetrics.put("Execution", 15);
    oaMetrics.put("Fragment End", 4);
    oaMetrics.put("Interaction", 23);
    oaMetrics.put("Initial Pseudo State", 1);
    oaMetrics.put("Instance Role", 3);
    oaMetrics.put("Operand", 4);
    oaMetrics.put("Interface Pkg", 1);
    oaMetrics.put("Opaque Expression", 3);
    oaMetrics.put("Operational Activity", 24);
    oaMetrics.put("Operational Activity Pkg", 1);
    oaMetrics.put("Operational Capability", 3);
    oaMetrics.put("Operational Capability Pkg", 1);
    oaMetrics.put("Operational Context", 1);
    oaMetrics.put("Operational Process", 2);
    oaMetrics.put("Part", 8);
    oaMetrics.put("Region", 7);
    oaMetrics.put("Requirements Pkg", 1);
    oaMetrics.put("Role Pkg", 1);
    oaMetrics.put("Scenario", 1);
    oaMetrics.put("Sequence Message", 15);
    oaMetrics.put("State Fragment", 6);
    oaMetrics.put("State Machine", 1);
    oaMetrics.put("State Transition", 7);

    Map<String, Integer> saMetrics = new HashMap<>();
    saMetrics.put("Abstract Capability Include", 1);
    saMetrics.put("Actor Pkg", 1);
    saMetrics.put("Association", 8);
    saMetrics.put("Boolean Type", 1);
    saMetrics.put("Capability", 13);
    saMetrics.put("Capability Exploitation", 12);
    saMetrics.put("Capability Pkg", 1);
    saMetrics.put("Change Event", 1);
    saMetrics.put("Choice Pseudo State", 2);
    saMetrics.put("Class", 7);
    saMetrics.put("Constraint", 7);
    saMetrics.put("Data Pkg", 3);
    saMetrics.put("Deep History Pseudo State", 1);
    saMetrics.put("Enumeration", 1);
    saMetrics.put("Enumeration Literal", 3);
    saMetrics.put("Exchange Category", 7);
    saMetrics.put("Execution", 25);
    saMetrics.put("Final State", 2);
    saMetrics.put("Function Input Port", 41);
    saMetrics.put("Function Output Port", 40);
    saMetrics.put("Functional Chain", 10);
    saMetrics.put("Functional Exchange", 41);
    saMetrics.put("Initial Pseudo State", 4);
    saMetrics.put("Instance Role", 19);
    saMetrics.put("State", 36);
    saMetrics.put("Interface Pkg", 1);
    saMetrics.put("Key Value", 3);
    saMetrics.put("= <undefined>", 46);
    saMetrics.put("Mission", 5);
    saMetrics.put("Mission Pkg", 1);
    saMetrics.put("Mode", 24);
    saMetrics.put("Opaque Expression", 7);
    saMetrics.put("Part", 5);
    saMetrics.put("undefined", 23);
    saMetrics.put("Region", 27);
    saMetrics.put("Requirements Pkg", 1);
    saMetrics.put("Scenario", 6);
    saMetrics.put("Sequence Message", 25);
    saMetrics.put("State Fragment", 18);
    saMetrics.put("State Machine", 2);
    saMetrics.put("State Transition", 39);
    saMetrics.put("String Type", 1);
    saMetrics.put("System Component", 5);
    saMetrics.put("System Context", 1);
    saMetrics.put("System Function", 44);
    saMetrics.put("System Function Pkg", 1);

    Map<String, Integer> laMetrics = new HashMap<>();
    laMetrics.put("Association", 6);
    laMetrics.put("Capability Realization", 13);
    laMetrics.put("Capability Realization Pkg", 1);
    laMetrics.put("Change Event", 1);
    laMetrics.put("Choice Pseudo State", 2);
    laMetrics.put("Class", 12);
    laMetrics.put("Combined Fragment", 2);
    laMetrics.put("Component Exchange", 12);
    laMetrics.put("Component Port", 24);
    laMetrics.put("Constraint", 11);
    laMetrics.put("Data Pkg", 5);
    laMetrics.put("Deep History Pseudo State", 1);
    laMetrics.put("Enumeration", 2);
    laMetrics.put("Enumeration Literal", 6);
    laMetrics.put("Exchange Category", 13);
    laMetrics.put("Exchange Item", 7);
    laMetrics.put("Exchange Item Element", 9);
    laMetrics.put("Execution", 40);
    laMetrics.put("Final State", 2);
    laMetrics.put("Fragment End", 6);
    laMetrics.put("Function Input Port", 88);
    laMetrics.put("Function Output Port", 84);
    laMetrics.put("Functional Chain", 10);
    laMetrics.put("Functional Exchange", 88);
    laMetrics.put("Generalization", 4);
    laMetrics.put("Initial Pseudo State", 4);
    laMetrics.put("Instance Role", 37);
    laMetrics.put("Operand", 4);
    laMetrics.put("State", 46);
    laMetrics.put("Use", 1);
    laMetrics.put("Interface Pkg", 1);
    laMetrics.put("Key Value", 215);
    laMetrics.put("= <undefined>", 70);
    laMetrics.put("Logical Component Pkg", 1);
    laMetrics.put("Logical Component", 23);
    laMetrics.put("Logical Context", 1);
    laMetrics.put("Logical Function", 64);
    laMetrics.put("Logical Function Pkg", 1);
    laMetrics.put("Mode", 24);
    laMetrics.put("Numeric Type", 2);
    laMetrics.put("Opaque Expression", 11);
    laMetrics.put("Part", 23);
    laMetrics.put("Physical Quantity", 1);
    laMetrics.put("undefined", 26);
    laMetrics.put("Region", 27);
    laMetrics.put("Requirements Pkg", 1);
    laMetrics.put("Scenario", 6);
    laMetrics.put("Sequence Message", 40);
    laMetrics.put("State Fragment", 23);
    laMetrics.put("State Machine", 2);
    laMetrics.put("State Transition", 39);
    laMetrics.put("Unit", 1);

    Map<String, Integer> paMetrics = new HashMap<>();
    paMetrics.put("Boolean Type", 1);
    paMetrics.put("Capability Realization", 10);
    paMetrics.put("Capability Realization Pkg", 1);
    paMetrics.put("Class", 15);
    paMetrics.put("Component Exchange", 11);
    paMetrics.put("Component Port", 28);
    paMetrics.put("Data Pkg", 6);
    paMetrics.put("Enumeration", 6);
    paMetrics.put("Enumeration Literal", 19);
    paMetrics.put("Exchange Category", 12);
    paMetrics.put("Exchange Item", 10);
    paMetrics.put("Exchange Item Allocation", 5);
    paMetrics.put("Exchange Item Element", 13);
    paMetrics.put("Function Input Port", 109);
    paMetrics.put("Function Output Port", 100);
    paMetrics.put("Functional Chain", 11);
    paMetrics.put("Functional Exchange", 110);
    paMetrics.put("Generalization", 3);
    paMetrics.put("Interface", 4);
    paMetrics.put("Interface Pkg", 2);
    paMetrics.put("Key Value", 6);
    paMetrics.put("= <undefined>", 108);
    paMetrics.put("Numeric Type", 3);
    paMetrics.put("Part", 63);
    paMetrics.put("Physical Component Pkg", 1);
    paMetrics.put("Physical Component", 63);
    paMetrics.put("Physical Context", 1);
    paMetrics.put("Physical Function", 92);
    paMetrics.put("Physical Function Pkg", 2);
    paMetrics.put("Physical Link", 21);
    paMetrics.put("Physical Path", 2);
    paMetrics.put("Physical Port", 37);
    paMetrics.put("Physical Quantity", 3);
    paMetrics.put("undefined", 39);
    paMetrics.put("Requirements Pkg", 1);
    paMetrics.put("String Type", 1);
    paMetrics.put("System Non Functional Requirement", 2);
    paMetrics.put("Unit", 3);

    Map<String, Integer> epbsMetrics = new HashMap<>();
    epbsMetrics.put("Capability Realization Pkg", 1);
    epbsMetrics.put("Unset", 11);
    epbsMetrics.put("Configuration Item Pkg", 1);
    epbsMetrics.put("Part", 11);

    String requiredModel = getRequiredTestModels().get(0);
    CapellaModel model = getTestModel(requiredModel);
    Session session = getSession(requiredModel);
    Project project = model.getProject(session.getTransactionalEditingDomain());
    SystemEngineering eng = ((SystemEngineering) (project.getOwnedModelRoots().get(0)));

    MetricTree<EObject> computeMetricTree = new MetricAction().computeMetricTree(eng);

    assertNotNull("Metric tree must be successfully computed", computeMetricTree);
    assertEquals("5 children correspond to 5 levels", 5, computeMetricTree.getChildren().size());

    MetricLabelProvider labelProvider = new MetricLabelProvider();
    for (MetricTree<EObject> metricForArchitecture : computeMetricTree.getChildren()) {
      if (labelProvider.getText(metricForArchitecture).equals("Operational Analysis")) {
        testOnArchitecture(metricForArchitecture, oaMetrics);
      }
      if (labelProvider.getText(metricForArchitecture).equals("System Analysis")) {
        testOnArchitecture(metricForArchitecture, saMetrics);
      }
      if (labelProvider.getText(metricForArchitecture).equals("Logical Architecture")) {
        testOnArchitecture(metricForArchitecture, laMetrics);
      }
      if (labelProvider.getText(metricForArchitecture).equals("Physical Architecture")) {
        testOnArchitecture(metricForArchitecture, paMetrics);
      }
      if (labelProvider.getText(metricForArchitecture).equals("EPBS Architecture")) {
        testOnArchitecture(metricForArchitecture, epbsMetrics);
      }
    }
  }

  private void testOnArchitecture(MetricTree<EObject> metricForArchitecture, Map<String, Integer> expectedMetrics) {
    MetricLabelProvider labelProvider = new MetricLabelProvider();
    String archLabel = labelProvider.getText(metricForArchitecture);
    for (MetricTree<EObject> metric : metricForArchitecture.getChildren()) {
      String elementLabel = labelProvider.getText(metric);
      int count = metric.getCount();
      if (expectedMetrics.containsKey(elementLabel)) {
        assertEquals("On level " + archLabel + "; count of " + elementLabel,
            expectedMetrics.get(elementLabel).intValue(), count);
      }
    }
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(new String[] { "In-Flight Entertainment System" });
  }

}
