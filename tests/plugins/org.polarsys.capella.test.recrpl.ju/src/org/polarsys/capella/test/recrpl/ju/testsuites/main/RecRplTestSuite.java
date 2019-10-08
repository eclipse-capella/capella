/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.recrpl.ju.testsuites.main;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.recrpl.ju.testcases.CatalogPkgName;
import org.polarsys.capella.test.recrpl.ju.testcases.CompositeREC;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateREC_ComponentFunctionalAllocation;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateREC_ContainedInCatalog;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateREC_DeploymentLink;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateREC_FunctionalExchange;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateREC_Part;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateREC_WholeContent;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_ContainedInCatalog;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_Containment;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_ContainmentPart;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_InvalidCatalogElementLink;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_SharedElements_ExchangeItems;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_SharedElements_ExternalResource;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_SharedElements_Interface;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_SimpleCase;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_SpecificPackages_Combined;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_SpecificPackages_Data;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_SpecificPackages_Interfaces;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_SpecificPackages_LA;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_SpecificPackages_OA;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_SpecificPackages_PA;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_SpecificPackages_SA;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_WithSuffix;
import org.polarsys.capella.test.recrpl.ju.testcases.CreateRPL_onREC;
import org.polarsys.capella.test.recrpl.ju.testcases.DeleteDetachRPL_RelatedElements;
import org.polarsys.capella.test.recrpl.ju.testcases.LocationOnUpdate;
import org.polarsys.capella.test.recrpl.ju.testcases.RemoveElement_CatalogElementLink;
import org.polarsys.capella.test.recrpl.ju.testcases.UpdateDefREC_AddElementOnRPL;
import org.polarsys.capella.test.recrpl.ju.testcases.UpdateDefREC_RemoveElement;
import org.polarsys.capella.test.recrpl.ju.testcases.UpdateREC_WithSuffix;
import org.polarsys.capella.test.recrpl.ju.testcases.UpdateRPL_AddElementWithSuffix;
import org.polarsys.capella.test.recrpl.ju.testcases.UpdateRPL_Containment;
import org.polarsys.capella.test.recrpl.ju.testcases.UpdateRPL_RemoveElement;
import org.polarsys.capella.test.recrpl.ju.testcases.UpdateRPL_RenameElementWithSuffix;
import org.polarsys.capella.test.recrpl.ju.testcases.compliance.BatchBlackboxComplianceTest1;
import org.polarsys.capella.test.recrpl.ju.testcases.compliance.BlackboxComplianceTest1;
import org.polarsys.capella.test.recrpl.ju.testcases.compliance.BlackboxComplianceTest2;
import org.polarsys.capella.test.recrpl.ju.testcases.compliance.BlackboxComplianceTest3;
import org.polarsys.capella.test.recrpl.ju.testcases.fragmented.CreateRPLOnFragmentedModel;

import junit.framework.Test;

public class RecRplTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new RecRplTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CatalogPkgName());
    tests.add(new CreateREC_ComponentFunctionalAllocation());
    tests.add(new CreateREC_ContainedInCatalog());
    tests.add(new CreateREC_DeploymentLink());
    tests.add(new CreateREC_FunctionalExchange());
    tests.add(new CreateREC_Part());
    tests.add(new CreateREC_WholeContent());
    tests.add(new CreateRPL_ContainedInCatalog());
    tests.add(new CreateRPL_Containment());
    tests.add(new CreateRPL_ContainmentPart());
    tests.add(new CreateRPL_InvalidCatalogElementLink());
    tests.add(new CreateRPL_onREC());
    tests.add(new CreateRPL_SharedElements_ExchangeItems());
    tests.add(new CreateRPL_SharedElements_ExternalResource());
    tests.add(new CreateRPL_SharedElements_Interface());
    tests.add(new CreateRPL_SimpleCase());
    tests.add(new CreateRPL_WithSuffix());
    tests.add(new DeleteDetachRPL_RelatedElements());
    tests.add(new RemoveElement_CatalogElementLink());
    tests.add(new UpdateDefREC_AddElementOnRPL());
    tests.add(new UpdateDefREC_RemoveElement());
    tests.add(new UpdateREC_WithSuffix());
    tests.add(new UpdateRPL_AddElementWithSuffix());
    tests.add(new UpdateRPL_Containment());
    tests.add(new UpdateRPL_RemoveElement());
    tests.add(new UpdateRPL_RenameElementWithSuffix());
    tests.add(new BlackboxComplianceTest1());
    tests.add(new BlackboxComplianceTest2());
    tests.add(new BlackboxComplianceTest3());
    tests.add(new BatchBlackboxComplianceTest1());
    tests.add(new CreateRPL_SpecificPackages_Data());
    tests.add(new CreateRPL_SpecificPackages_OA());
    tests.add(new CreateRPL_SpecificPackages_SA());
    tests.add(new CreateRPL_SpecificPackages_LA());
    tests.add(new CreateRPL_SpecificPackages_PA());
    tests.add(new CreateRPL_SpecificPackages_Interfaces());
    tests.add(new CreateRPL_SpecificPackages_Combined());
    tests.add(new CompositeREC());
    tests.add(new LocationOnUpdate());
    tests.add(new CreateRPLOnFragmentedModel());
    return tests;
  }

}
