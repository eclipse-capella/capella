/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.testcases.delete;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class DeleteElementTestSuite extends BasicTestSuite {

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("miscmodel");
  }

  public static Test suite() {
    return new DeleteElementTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new DeleteChainInvolvmentFunction());
    tests.add(new DeleteChainInvolvmentLink());
    tests.add(new DeleteChainReferenceSourceHierarchy());
    tests.add(new DeleteChainReferenceTargetHierarchy());
    tests.add(new DeleteExchangeItem());
    tests.add(new DeleteGuard());
    tests.add(new DeleteProtectedElements());
    tests.add(new DeleteDoNotDeletePropertyValue());

    return tests;
  }

}
