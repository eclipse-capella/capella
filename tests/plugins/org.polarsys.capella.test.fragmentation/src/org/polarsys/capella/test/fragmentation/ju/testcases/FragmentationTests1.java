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
package org.polarsys.capella.test.fragmentation.ju.testcases;

import java.util.ArrayList;

import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.test.fragmentation.ju.model.FragmentModelTestFramework;
import org.polarsys.capella.test.framework.helpers.EObjectHelper;
import org.polarsys.capella.test.framework.helpers.TestHelper;

public class FragmentationTests1 extends FragmentModelTestFramework {
  @Override
  public void test() throws Exception {
    // open session a first time
    init();
    checkModelIntegrity();
    checkLoadedFragmentsNumber(4);

    // check the number of tables and diagrams for each fragment
    checkFragmentTablesDiagramsNumber(RLF_OA2_SysOA2_1airdfragment, 2);
    checkFragmentTablesDiagramsNumber(LC2airdfragment, 6);
    checkFragmentTablesDiagramsNumber(OC1airdfragment, 2);
    checkFragmentTablesDiagramsNumber(Dataairdfragment, 3);

    // // check the number of model elements in semantic model elements for each fragment
    checkFragmentModelElementsNumber(RLF_OA2_SysOA2_1airdfragment, 26);
    checkFragmentModelElementsNumber(LC2airdfragment, 149);
    checkFragmentModelElementsNumber(OC1airdfragment, 177);
    checkFragmentModelElementsNumber(Dataairdfragment, 152);

    // // retrieve number of diagram elements for each diagram
    retrieveDiagramElementsNumber();

    fragment(context, UC1FromLogicalSystem_ID);
    fragment(context, LC3_ID);
    fragment(context, LC3_3_ID);
    fragment(context, OA2_ID);
    fragment(context, LF2_3_ID);

    saveAndCloseSession();
    // open the session a second time
    openFragmentSession(_airdFile);
    checkModelIntegrity();
    // check that the number of loaded fragments has increased
    checkLoadedFragmentsNumber(9); // 5 fragments added

    // // check the number of tables and diagrams for each fragment
    checkFragmentTablesDiagramsNumber(RLF_OA2_SysOA2_1airdfragment, 1);
    checkFragmentTablesDiagramsNumber(LC2airdfragment, 3);
    checkFragmentTablesDiagramsNumber(OC1airdfragment, 2);
    checkFragmentTablesDiagramsNumber(Dataairdfragment, 3);
    checkFragmentTablesDiagramsNumber(UC1FromLogicalSystemairdfragment, 3);
    checkFragmentTablesDiagramsNumber(LC3airdfragment, 2);
    checkFragmentTablesDiagramsNumber(LC3_3airdfragment, 0);
    checkFragmentTablesDiagramsNumber(LF2_3airdfragment, 1);
    //
    // // check the number of model elements in semantic model elements for each fragment
    checkFragmentModelElementsNumber(RLF_OA2_SysOA2_1airdfragment, 23); // less model elements
                                                                                              // because of LF2_3
                                                                                              // fragmentation
    checkFragmentModelElementsNumber(LC2airdfragment, 22); // less model elements because of UC1
                                                                                 // [From 'Logical System']
    // // fragmentation
    checkFragmentModelElementsNumber(OC1airdfragment, 177);
    checkFragmentModelElementsNumber(Dataairdfragment, 152);
    checkFragmentModelElementsNumber(UC1FromLogicalSystemairdfragment, 127);
    checkFragmentModelElementsNumber(LC3airdfragment, 15);
    checkFragmentModelElementsNumber(LC3_3airdfragment, 3);
    checkFragmentModelElementsNumber(LF2_3airdfragment, 3);
    checkFragmentModelElementsNumber(RLF_OA2airdfragment, 14);

    // open all diagrams/tables and check if they are not modified
    openAndCheckAllSessionRepresentations();
  }

  @Override
  protected void init() {
    super.init();
    Resource semanticResource = TestHelper.getSemanticResource(session);
    ArrayList<String> listOfObjectIds = new ArrayList<String>();
    listOfObjectIds.add(UC1FromLogicalSystem_ID);
    listOfObjectIds.add(LC3_3_ID);
    listOfObjectIds.add(LF2_3_ID);
    listOfObjectIds.add(LA_LogicalArchitecture_ID);
    listOfObjectIds.add(PA_PhysicalArchitecture_ID);
    mapSemanticObjects = EObjectHelper.getMatchingEObject(semanticResource.getContents().get(0),
        ModellingcorePackage.Literals.MODEL_ELEMENT, ModellingcorePackage.Literals.MODEL_ELEMENT__ID, listOfObjectIds);
  }
}
