/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.dialog;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public class CapellaTreeDataTestCase extends MiscModel {
  ICapellaModel model;
  IScope scope;
  
  @Override
  public void test() throws Exception {
    init();
    
    EObject obj1 = IdManager.getInstance().getEObject(OA_OPERATIONAL_ENTITIES, scope);
    EObject obj2 = IdManager.getInstance().getEObject(SA__ROOT_SF__FC3_2, scope);
    EObject obj3 = IdManager.getInstance().getEObject(SA__ACTORS, scope);
    EObject obj4 = IdManager.getInstance().getEObject(LA_2, scope);
    EObject obj5 = IdManager.getInstance().getEObject(PC_1, scope);
    
    List<EObject> displayedElements = Arrays.asList(obj1, obj2, obj3, obj4, obj5);
    TreeData inputData = new TreeData(displayedElements, null);
    Object[] elems = inputData.getElements();
    assertTrue(elems.length == 1 && elems[0] instanceof Project);
    
    elems = inputData.getChildren(elems[0]);
    assertTrue(elems.length == 1 && elems[0] instanceof SystemEngineering);
    
    elems = inputData.getChildren(elems[0]);
    assertTrue(elems.length == 4);
    assertTrue(elems[0] instanceof OperationalAnalysis);
    assertTrue(elems[1] instanceof SystemAnalysis);
    assertTrue(elems[2] instanceof LogicalArchitecture);
    assertTrue(elems[3] instanceof PhysicalArchitecture);
    
    // check the elements on OperationalAnalysis
    Object[] subelems = inputData.getChildren(elems[0]);
    assertTrue(subelems.length == 1 && subelems[0].equals(obj1));
    
    // check the elements on SystemAnalysis
    subelems = inputData.getChildren(elems[1]);
    EObject saFunctionPkg = IdManager.getInstance().getEObject(SA__SYSTEM_FUNCTIONS, scope);
    EObject saSystemComp = IdManager.getInstance().getEObject(SA__SYSTEM_COMPONENT_PKG, scope);
    assertTrue(subelems.length == 2 && subelems[0].equals(saFunctionPkg));
    assertTrue(subelems[1].equals(saSystemComp));
  }
  
  protected void init() {
    model = getTestModel();
    scope = new ScopeModelWrapper(model);
  }
}
