/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.test.diagram.tools.ju.cdb.CDBScenarios;
import org.polarsys.capella.test.diagram.tools.ju.cdb.ConstraintsSection;
import org.polarsys.capella.test.diagram.tools.ju.cdb.CreateClassGroup;
import org.polarsys.capella.test.diagram.tools.ju.cdb.CreateDataPackage;
import org.polarsys.capella.test.diagram.tools.ju.cdb.CreateDataTypeGroup;
import org.polarsys.capella.test.diagram.tools.ju.cdb.CreateExchangeItemElement;
import org.polarsys.capella.test.diagram.tools.ju.cdb.CreateExchangeItemGroup;
import org.polarsys.capella.test.diagram.tools.ju.cdb.CreateInterface;
import org.polarsys.capella.test.diagram.tools.ju.cdb.CreateInterfacePackage;
import org.polarsys.capella.test.diagram.tools.ju.cdb.CreateOperation;
import org.polarsys.capella.test.diagram.tools.ju.cdb.CreateParameter;
import org.polarsys.capella.test.diagram.tools.ju.cdb.CreateProperty;
import org.polarsys.capella.test.diagram.tools.ju.cdb.CreateReferenceGroup;
import org.polarsys.capella.test.diagram.tools.ju.cdb.CreateRelationshipGroup;
import org.polarsys.capella.test.diagram.tools.ju.cdb.DragAndDropTest;
import org.polarsys.capella.test.diagram.tools.ju.cdb.InsertRemoveDataPackages;
import org.polarsys.capella.test.diagram.tools.ju.cdb.InsertRemoveDataTypes;
import org.polarsys.capella.test.diagram.tools.ju.cdb.InsertRemoveDataValues;
import org.polarsys.capella.test.diagram.tools.ju.cdb.InsertRemoveExchangeItemElements;
import org.polarsys.capella.test.diagram.tools.ju.cdb.InsertRemoveExchangeItems;
import org.polarsys.capella.test.diagram.tools.ju.cdb.InsertRemoveInterfacePackages;
import org.polarsys.capella.test.diagram.tools.ju.cdb.InsertRemoveInterfaces;
import org.polarsys.capella.test.diagram.tools.ju.cdb.InsertRemoveOperations;
import org.polarsys.capella.test.diagram.tools.ju.cdb.InsertRemoveProperties;
import org.polarsys.capella.test.diagram.tools.ju.cdb.InsertRemoveRelationships;
import org.polarsys.capella.test.diagram.tools.ju.cdb.ManageExchangeItemAllocations;
import org.polarsys.capella.test.diagram.tools.ju.cdb.ReconnectRelationshipGroup;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.LA_CDBProjectSettings;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.OA_CDBProjectSettings;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.PA_CDBProjectSettings;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.SA_CDBProjectSettings;
import org.polarsys.capella.test.diagram.tools.ju.pd.CreateDependencies;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class CDBDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new CDBDiagramToolsTestSuite();
  }

  public List<String> getRequiredTestModels() {
    return Arrays.asList("CDBCommunication");
  }
  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();

    tests.add(new CreateDataPackage(new OA_CDBProjectSettings()));
    tests.add(new CreateDataPackage(new SA_CDBProjectSettings()));
    tests.add(new CreateDataPackage(new LA_CDBProjectSettings()));
    tests.add(new CreateDataPackage(new PA_CDBProjectSettings()));

    tests.add(new CreateClassGroup(new OA_CDBProjectSettings()));
    tests.add(new CreateClassGroup(new SA_CDBProjectSettings()));
    tests.add(new CreateClassGroup(new LA_CDBProjectSettings()));
    tests.add(new CreateClassGroup(new PA_CDBProjectSettings()));

    tests.add(new CreateDataTypeGroup(new OA_CDBProjectSettings()));
    tests.add(new CreateDataTypeGroup(new SA_CDBProjectSettings()));
    tests.add(new CreateDataTypeGroup(new LA_CDBProjectSettings()));
    tests.add(new CreateDataTypeGroup(new PA_CDBProjectSettings()));

    tests.add(new CreateReferenceGroup(new OA_CDBProjectSettings()));
    tests.add(new CreateReferenceGroup(new SA_CDBProjectSettings()));
    tests.add(new CreateReferenceGroup(new LA_CDBProjectSettings()));
    tests.add(new CreateReferenceGroup(new PA_CDBProjectSettings()));

    tests.add(new CreateProperty(new OA_CDBProjectSettings()));
    tests.add(new CreateProperty(new SA_CDBProjectSettings()));
    tests.add(new CreateProperty(new LA_CDBProjectSettings()));
    tests.add(new CreateProperty(new PA_CDBProjectSettings()));

    tests.add(new CreateOperation(new OA_CDBProjectSettings()));
    tests.add(new CreateOperation(new SA_CDBProjectSettings()));
    tests.add(new CreateOperation(new LA_CDBProjectSettings()));
    tests.add(new CreateOperation(new PA_CDBProjectSettings()));

    tests.add(new CreateParameter(new OA_CDBProjectSettings()));
    tests.add(new CreateParameter(new SA_CDBProjectSettings()));
    tests.add(new CreateParameter(new LA_CDBProjectSettings()));
    tests.add(new CreateParameter(new PA_CDBProjectSettings()));

    tests.add(new CreateRelationshipGroup(new OA_CDBProjectSettings()));
    tests.add(new CreateRelationshipGroup(new SA_CDBProjectSettings()));
    tests.add(new CreateRelationshipGroup(new LA_CDBProjectSettings()));
    tests.add(new CreateRelationshipGroup(new PA_CDBProjectSettings()));

    tests.add(new ReconnectRelationshipGroup(new OA_CDBProjectSettings()));
    tests.add(new ReconnectRelationshipGroup(new SA_CDBProjectSettings()));
    tests.add(new ReconnectRelationshipGroup(new LA_CDBProjectSettings()));
    tests.add(new ReconnectRelationshipGroup(new PA_CDBProjectSettings()));

    tests.add(new CreateInterfacePackage(new OA_CDBProjectSettings()));
    tests.add(new CreateInterfacePackage(new SA_CDBProjectSettings()));
    tests.add(new CreateInterfacePackage(new LA_CDBProjectSettings()));
    tests.add(new CreateInterfacePackage(new PA_CDBProjectSettings()));

    tests.add(new CreateInterface(new OA_CDBProjectSettings()));
    tests.add(new CreateInterface(new SA_CDBProjectSettings()));
    tests.add(new CreateInterface(new LA_CDBProjectSettings()));
    tests.add(new CreateInterface(new PA_CDBProjectSettings()));

    tests.add(new CreateExchangeItemGroup(new OA_CDBProjectSettings()));
    tests.add(new CreateExchangeItemGroup(new SA_CDBProjectSettings()));
    tests.add(new CreateExchangeItemGroup(new LA_CDBProjectSettings()));
    tests.add(new CreateExchangeItemGroup(new PA_CDBProjectSettings()));

    tests.add(new CreateExchangeItemElement(new OA_CDBProjectSettings()));
    tests.add(new CreateExchangeItemElement(new SA_CDBProjectSettings()));
    tests.add(new CreateExchangeItemElement(new LA_CDBProjectSettings()));
    tests.add(new CreateExchangeItemElement(new PA_CDBProjectSettings()));

    tests.add(new InsertRemoveDataPackages(new OA_CDBProjectSettings()));
    tests.add(new InsertRemoveDataPackages(new SA_CDBProjectSettings()));
    tests.add(new InsertRemoveDataPackages(new LA_CDBProjectSettings()));
    tests.add(new InsertRemoveDataPackages(new PA_CDBProjectSettings()));

    tests.add(new InsertRemoveDataTypes(new OA_CDBProjectSettings()));
    tests.add(new InsertRemoveDataTypes(new SA_CDBProjectSettings()));
    tests.add(new InsertRemoveDataTypes(new LA_CDBProjectSettings()));
    tests.add(new InsertRemoveDataTypes(new PA_CDBProjectSettings()));

    tests.add(new InsertRemoveDataValues(new OA_CDBProjectSettings()));
    tests.add(new InsertRemoveDataValues(new SA_CDBProjectSettings()));
    tests.add(new InsertRemoveDataValues(new LA_CDBProjectSettings()));
    tests.add(new InsertRemoveDataValues(new PA_CDBProjectSettings()));

    tests.add(new InsertRemoveRelationships(new OA_CDBProjectSettings()));
    tests.add(new InsertRemoveRelationships(new SA_CDBProjectSettings()));
    tests.add(new InsertRemoveRelationships(new LA_CDBProjectSettings()));
    tests.add(new InsertRemoveRelationships(new PA_CDBProjectSettings()));

    tests.add(new InsertRemoveProperties(new OA_CDBProjectSettings()));
    tests.add(new InsertRemoveProperties(new SA_CDBProjectSettings()));
    tests.add(new InsertRemoveProperties(new LA_CDBProjectSettings()));
    tests.add(new InsertRemoveProperties(new PA_CDBProjectSettings()));

    tests.add(new InsertRemoveOperations(new OA_CDBProjectSettings()));
    tests.add(new InsertRemoveProperties(new SA_CDBProjectSettings()));
    tests.add(new InsertRemoveProperties(new LA_CDBProjectSettings()));
    tests.add(new InsertRemoveProperties(new PA_CDBProjectSettings()));

    tests.add(new InsertRemoveInterfacePackages(new OA_CDBProjectSettings()));
    tests.add(new InsertRemoveInterfacePackages(new SA_CDBProjectSettings()));
    tests.add(new InsertRemoveInterfacePackages(new LA_CDBProjectSettings()));
    tests.add(new InsertRemoveInterfacePackages(new PA_CDBProjectSettings()));

    tests.add(new InsertRemoveInterfaces(new OA_CDBProjectSettings()));
    tests.add(new InsertRemoveInterfaces(new SA_CDBProjectSettings()));
    tests.add(new InsertRemoveInterfaces(new LA_CDBProjectSettings()));
    tests.add(new InsertRemoveInterfaces(new PA_CDBProjectSettings()));

    tests.add(new InsertRemoveExchangeItems(new OA_CDBProjectSettings()));
    tests.add(new InsertRemoveExchangeItems(new SA_CDBProjectSettings()));
    tests.add(new InsertRemoveExchangeItems(new LA_CDBProjectSettings()));
    tests.add(new InsertRemoveExchangeItems(new PA_CDBProjectSettings()));

    tests.add(new InsertRemoveExchangeItems(new OA_CDBProjectSettings()));
    tests.add(new InsertRemoveExchangeItems(new SA_CDBProjectSettings()));
    tests.add(new InsertRemoveExchangeItems(new LA_CDBProjectSettings()));
    tests.add(new InsertRemoveExchangeItems(new PA_CDBProjectSettings()));

    tests.add(new InsertRemoveExchangeItemElements(new OA_CDBProjectSettings()));
    tests.add(new InsertRemoveExchangeItemElements(new SA_CDBProjectSettings()));
    tests.add(new InsertRemoveExchangeItemElements(new LA_CDBProjectSettings()));
    tests.add(new InsertRemoveExchangeItemElements(new PA_CDBProjectSettings()));

    tests.add(new ManageExchangeItemAllocations(new OA_CDBProjectSettings()));
    tests.add(new ManageExchangeItemAllocations(new SA_CDBProjectSettings()));
    tests.add(new ManageExchangeItemAllocations(new LA_CDBProjectSettings()));
    tests.add(new ManageExchangeItemAllocations(new PA_CDBProjectSettings()));

    tests.add(new ConstraintsSection(new OA_CDBProjectSettings()));
    tests.add(new ConstraintsSection(new SA_CDBProjectSettings()));
    tests.add(new ConstraintsSection(new LA_CDBProjectSettings()));
    tests.add(new ConstraintsSection(new PA_CDBProjectSettings()));

    tests.add(new CreateDependencies(new OA_CDBProjectSettings()));
    tests.add(new CreateDependencies(new SA_CDBProjectSettings()));
    tests.add(new CreateDependencies(new LA_CDBProjectSettings()));
    tests.add(new CreateDependencies(new PA_CDBProjectSettings()));

    tests.add(new CDBScenarios(new OA_CDBProjectSettings()));
    tests.add(new CDBScenarios(new SA_CDBProjectSettings()));
    tests.add(new CDBScenarios(new LA_CDBProjectSettings()));
    tests.add(new CDBScenarios(new PA_CDBProjectSettings()));

    tests.add(new DragAndDropTest(new OA_CDBProjectSettings()));
    tests.add(new DragAndDropTest(new SA_CDBProjectSettings()));
    tests.add(new DragAndDropTest(new LA_CDBProjectSettings()));
    tests.add(new DragAndDropTest(new PA_CDBProjectSettings()));
    return tests;
  }
}
