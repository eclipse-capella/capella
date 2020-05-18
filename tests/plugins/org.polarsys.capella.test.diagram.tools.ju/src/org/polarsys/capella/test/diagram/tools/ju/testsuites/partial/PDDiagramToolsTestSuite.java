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
package org.polarsys.capella.test.diagram.tools.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.pd.LAPDCreateConstrainedElement;
import org.polarsys.capella.test.diagram.tools.ju.pd.LAPDCreateConstraint;
import org.polarsys.capella.test.diagram.tools.ju.pd.LAPDDNDInterfacePackages;
import org.polarsys.capella.test.diagram.tools.ju.pd.LAPDDependencyScenario;
import org.polarsys.capella.test.diagram.tools.ju.pd.LAPDInsertRemoveConstraints;
import org.polarsys.capella.test.diagram.tools.ju.pd.LAPDInsertRemoveDataPackages;
import org.polarsys.capella.test.diagram.tools.ju.pd.LAPDInsertRemoveInterfacePackages;
import org.polarsys.capella.test.diagram.tools.ju.pd.LAPDInsertRemovePackageDependencies;
import org.polarsys.capella.test.diagram.tools.ju.pd.OAPDCreateConstrainedElement;
import org.polarsys.capella.test.diagram.tools.ju.pd.OAPDCreateConstraint;
import org.polarsys.capella.test.diagram.tools.ju.pd.OAPDDNDInterfacePackages;
import org.polarsys.capella.test.diagram.tools.ju.pd.OAPDDependencyScenario;
import org.polarsys.capella.test.diagram.tools.ju.pd.OAPDInsertRemoveConstraints;
import org.polarsys.capella.test.diagram.tools.ju.pd.OAPDInsertRemoveDataPackages;
import org.polarsys.capella.test.diagram.tools.ju.pd.OAPDInsertRemoveDependencies;
import org.polarsys.capella.test.diagram.tools.ju.pd.OAPDInsertRemoveInterfacePackages;
import org.polarsys.capella.test.diagram.tools.ju.pd.PAPDCreateConstrainedElement;
import org.polarsys.capella.test.diagram.tools.ju.pd.PAPDCreateConstraint;
import org.polarsys.capella.test.diagram.tools.ju.pd.PAPDDNDInterfacePackages;
import org.polarsys.capella.test.diagram.tools.ju.pd.PAPDDependencyScenario;
import org.polarsys.capella.test.diagram.tools.ju.pd.PAPDInsertRemoveConstraints;
import org.polarsys.capella.test.diagram.tools.ju.pd.PAPDInsertRemoveDataPackages;
import org.polarsys.capella.test.diagram.tools.ju.pd.PAPDInsertRemoveInterfacePackages;
import org.polarsys.capella.test.diagram.tools.ju.pd.PAPDInsertRemovePackageDependencies;
import org.polarsys.capella.test.diagram.tools.ju.pd.SAPDCreateConstrainedElement;
import org.polarsys.capella.test.diagram.tools.ju.pd.SAPDCreateConstraint;
import org.polarsys.capella.test.diagram.tools.ju.pd.SAPDDNDInterfacePackages;
import org.polarsys.capella.test.diagram.tools.ju.pd.SAPDDependencyScenario;
import org.polarsys.capella.test.diagram.tools.ju.pd.SAPDInsertRemoveConstraints;
import org.polarsys.capella.test.diagram.tools.ju.pd.SAPDInsertRemoveDataPackages;
import org.polarsys.capella.test.diagram.tools.ju.pd.SAPDInsertRemoveInterfacePackages;
import org.polarsys.capella.test.diagram.tools.ju.pd.SAPDInsertRemovePackageDependencies;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class PDDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new PDDiagramToolsTestSuite();
  }
  
  public List<String> getRequiredTestModels() {
    return Arrays.asList("DiagramToolsModel");
  }
  
  @Override
  protected List<BasicTestArtefact> getTests() {

    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();

    tests.add(new OAPDInsertRemoveDataPackages());
    tests.add(new OAPDInsertRemoveInterfacePackages());
    tests.add(new OAPDInsertRemoveDependencies());
    tests.add(new OAPDDependencyScenario());
    tests.add(new OAPDCreateConstraint());
    tests.add(new OAPDCreateConstrainedElement());
    tests.add(new OAPDInsertRemoveConstraints());
    tests.add(new SAPDInsertRemoveDataPackages());
    tests.add(new SAPDInsertRemoveInterfacePackages());
    tests.add(new SAPDInsertRemovePackageDependencies());
    tests.add(new SAPDDependencyScenario());
    tests.add(new SAPDCreateConstraint());
    tests.add(new SAPDCreateConstrainedElement());
    tests.add(new SAPDInsertRemoveConstraints());
    tests.add(new LAPDInsertRemoveDataPackages());
    tests.add(new LAPDInsertRemoveInterfacePackages());
    tests.add(new LAPDInsertRemovePackageDependencies());
    tests.add(new LAPDDependencyScenario());
    tests.add(new LAPDCreateConstraint());
    tests.add(new LAPDCreateConstrainedElement());
    tests.add(new LAPDInsertRemoveConstraints());
    tests.add(new PAPDInsertRemoveDataPackages());
    tests.add(new PAPDInsertRemoveInterfacePackages());
    tests.add(new PAPDInsertRemovePackageDependencies());
    tests.add(new PAPDDependencyScenario());
    tests.add(new PAPDCreateConstraint());
    tests.add(new PAPDCreateConstrainedElement());
    tests.add(new PAPDInsertRemoveConstraints());
    tests.add(new OAPDDNDInterfacePackages());
    tests.add(new LAPDDNDInterfacePackages());
    tests.add(new SAPDDNDInterfacePackages());
    tests.add(new PAPDDNDInterfacePackages());

    return tests;
  }

}
