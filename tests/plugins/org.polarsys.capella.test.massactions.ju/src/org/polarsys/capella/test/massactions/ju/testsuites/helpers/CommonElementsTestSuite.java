/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.massactions.ju.testsuites.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.common.ui.massactions.core.shared.helper.CommonElementsHelper;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.massactions.ju.model.AbstractCapellaMATestCase;
import org.polarsys.capella.test.massactions.ju.testcases.helpers.commonelements.CatAppliedPropertyValuesBQTest;
import org.polarsys.capella.test.massactions.ju.testcases.helpers.commonelements.CatExchangesBQTest;

import junit.framework.Test;

/**
 * A test suite for the {@link CommonElementsHelper} tests.
 * 
 * @author Sandu Postaru
 *
 */
public class CommonElementsTestSuite extends BasicTestSuite {

  public static Test suite() {
    return new CommonElementsTestSuite();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(AbstractCapellaMATestCase.MODEL_NAME);
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> testSuite = new ArrayList<>();

    testSuite.add(new CatAppliedPropertyValuesBQTest());
    testSuite.add(new CatExchangesBQTest());

    // An associated bug has been opened [Bug: 2127 https://bugs.polarsys.org/show_bug.cgi?id=2127] meanwhile these
    // tests are disabled.
    // testSuite.add(new PropNameBQTest());
    // testSuite.add(new PropSeatBQTest());

    return testSuite;
  }
}
