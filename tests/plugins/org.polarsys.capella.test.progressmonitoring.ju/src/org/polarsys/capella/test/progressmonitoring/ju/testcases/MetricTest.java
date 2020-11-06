/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.ui.metric.actions.MetricAction;
import org.polarsys.capella.core.ui.metric.core.MetricTree;
import org.polarsys.capella.core.ui.metric.dialog.MetricLabelProvider;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class MetricTest extends BasicTestCase {

  @Override
  public void test() throws Exception {
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
        testOnArchitecture(metricForArchitecture, Type.OA);
      }
      if (labelProvider.getText(metricForArchitecture).equals("System Analysis")) {
        testOnArchitecture(metricForArchitecture, Type.SA);
      }
      if (labelProvider.getText(metricForArchitecture).equals("Logical Architecture")) {
        testOnArchitecture(metricForArchitecture, Type.LA);
      }
      if (labelProvider.getText(metricForArchitecture).equals("Physical Architecture")) {
        testOnArchitecture(metricForArchitecture, Type.PA);
      }
      if (labelProvider.getText(metricForArchitecture).equals("EPBS Architecture")) {
        testOnArchitecture(metricForArchitecture, Type.EPBS);
      }
    }
  }

  private void testOnArchitecture(MetricTree<EObject> metricForArchitecture, Type type) {
    List<String> expectedMetricNames = new ArrayList<>();
    List<Integer> expectedMetricValues = new ArrayList<>();
    switch (type) {
    case OA:
      readTestResult("oaMetric.txt", expectedMetricNames, expectedMetricValues);
      break;
    case SA:
      readTestResult("saMetric.txt", expectedMetricNames, expectedMetricValues);
      break;
    case LA:
      readTestResult("laMetric.txt", expectedMetricNames, expectedMetricValues);
      break;
    case PA:
      readTestResult("paMetric.txt", expectedMetricNames, expectedMetricValues);
      break;
    case EPBS:
      readTestResult("epbsMetric.txt", expectedMetricNames, expectedMetricValues);
      break;
    default:
      break;
    }

    MetricLabelProvider labelProvider = new MetricLabelProvider();
    List<String> metricNames = metricForArchitecture.getChildren().stream().map(m -> labelProvider.getText(m))
        .collect(Collectors.toList());
    assertTrue("Metric list is different from what is expected", metricNames.equals(expectedMetricNames));
    List<Integer> metricValues = metricForArchitecture.getChildren().stream().map(m -> m.getCount())
        .collect(Collectors.toList());
    assertTrue("Metric values are different from what is expected", metricValues.equals(expectedMetricValues));
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("In-Flight Entertainment System");
  }

  private void readTestResult(String fileName, List<String> metricName, List<Integer> metricValue) {
    try {
      File inputFile = getTestResult(fileName);
      List<String> lines = Files.readAllLines(inputFile.toPath());
      for (String line : lines) {
        metricName.add(line.split(",")[0]);
        metricValue.add(Integer.parseInt(line.split(",")[1]));
      }
    } catch (IOException | URISyntaxException ex) {
      fail("I/O error while reading test result files");
    }
  }

  private File getTestResult(String fileName) throws URISyntaxException, IOException {
    Bundle bundle = FrameworkUtil.getBundle(this.getClass());
    URL fileURL = bundle.getEntry("testResult/" + fileName);
    URL resolvedFileURL  = FileLocator.toFileURL(fileURL);
    java.net.URI resolvedURI = new java.net.URI(resolvedFileURL.getProtocol(), resolvedFileURL.getPath(), null);
    return new File(resolvedURI);
  }

}
