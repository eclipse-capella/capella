/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.merge.ju.testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Assert;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.refinement.merge.merger.DefaultScenarioMerger;
import org.polarsys.capella.core.refinement.merge.merger.IScenarioMerger;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.EObjectHelper;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.merge.ju.utils.MergeTestUtils;

/**
 * Basic Test class to test the Merger.
 */
public abstract class AbstractMergerTest extends BasicTestCase {

  /** readable name for this test */
  String _readableTestName;

  /** test description */
  protected String _testCaseDesc;

  /** Map with needed semantic Object, useful */
  protected Map<String, EObject> _semanticObjectMap;

  /** The Id of the Scenario that will be merged */
  protected String _scenarioToMergeId;
  /** The control merged Scenario Id */
  protected String _controlScenarioId;

  protected Scenario _result = null;

  /**
   * Constructor
   * 
   * @param String
   *          scenarioToMergeId
   * @param String
   *          controlScenarioId
   * @param readableTestName
   * @param testCaseDesc
   */
  public AbstractMergerTest(String scenarioToMergeId, String controlScenarioId, String readableTestName,
      String testCaseDesc) {

    _scenarioToMergeId = scenarioToMergeId;
    _controlScenarioId = controlScenarioId;

    _readableTestName = readableTestName;
    _testCaseDesc = testCaseDesc;
  }

  @Override
  public void test() throws Exception {
    Scenario scenario = (Scenario) _semanticObjectMap.get(_scenarioToMergeId);
    IScenarioMerger ism = new DefaultScenarioMerger();

    _result = MergeTestUtils.performMerge(scenario, ism);

    // any result?
    Assert.assertNotNull(MergeTestingMessages.noMergedScenarioCreated, _result);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    setObjects();
  }

  /**
   * Set the map with interesting object for this test suite. Not really nice in this case because we close and re-open
   * session but really useful in "one session" approach
   */
  protected void setObjects() {

    Resource semanticResource = TestHelper.getSemanticResource(getSession(getRequiredTestModels().get(0)));

    _semanticObjectMap = new HashMap<String, EObject>();

    ArrayList<String> list = new ArrayList<String>();

    list.add(_scenarioToMergeId);
    list.add(_controlScenarioId);

    Map<String, EObject> map = EObjectHelper.getMatchingEObject(semanticResource.getContents().get(0),
        ModellingcorePackage.Literals.MODEL_ELEMENT, ModellingcorePackage.Literals.MODEL_ELEMENT__ID, list);

    _semanticObjectMap.putAll(map);
  }

}
