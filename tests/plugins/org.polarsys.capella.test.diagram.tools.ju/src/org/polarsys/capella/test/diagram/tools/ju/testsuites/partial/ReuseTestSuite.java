/*******************************************************************************
 * Copyright (c) 2019, THALES GLOBAL SERVICES.
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

import org.polarsys.capella.test.diagram.tools.ju.reuse.ReuseActorTestCase;
import org.polarsys.capella.test.diagram.tools.ju.reuse.ReuseConfigurationItemTestCase;
import org.polarsys.capella.test.diagram.tools.ju.reuse.ReuseLogicalActorTestCase;
import org.polarsys.capella.test.diagram.tools.ju.reuse.ReuseLogicalComponentTestCase;
import org.polarsys.capella.test.diagram.tools.ju.reuse.ReusePCTestCase;
import org.polarsys.capella.test.diagram.tools.ju.reuse.ReusePhysicalActorTestCase;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class ReuseTestSuite extends BasicTestSuite {

  public static Test suite() {
    return new ReuseTestSuite();
  }
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("ReuseOfComponentsModel");
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new ReuseLogicalActorTestCase());
    tests.add(new ReuseLogicalComponentTestCase());
    tests.add(new ReusePCTestCase());
    tests.add(new ReusePhysicalActorTestCase());
    tests.add(new ReuseActorTestCase());
    tests.add(new ReuseConfigurationItemTestCase());

    return tests;
  }

}
