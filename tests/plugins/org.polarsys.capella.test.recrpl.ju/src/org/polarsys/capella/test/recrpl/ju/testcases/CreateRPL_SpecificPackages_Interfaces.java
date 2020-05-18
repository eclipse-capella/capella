/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.recrpl.ju.testcases;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.model.skeleton.CapellaModelSkeleton;

public class CreateRPL_SpecificPackages_Interfaces extends CreateRPL_SpecificPackages {

  @Override
  protected Collection<EObject> getRecElements(CapellaModelSkeleton project){

    Collection<EObject> result = new ArrayList<EObject>();

    InterfacePkg oaPkg = project.getOperationalAnalysis().getOwnedInterfacePkg();
    InterfacePkg saPkg = project.getSystemAnalysis().getOwnedInterfacePkg();
    InterfacePkg laPkg = project.getLogicalArchitecture().getOwnedInterfacePkg();
    InterfacePkg paPkg = project.getPhysicalArchitecture().getOwnedInterfacePkg();

    // don't create any property values and no constraint
    ChildCreationHelper.Filter f = (EObject parent, EClass clazz, EReference ref) -> {
      return clazz.getEPackage() == CapellacorePackage.eINSTANCE;
    };

    result.addAll(ChildCreationHelper.createAllChildren(oaPkg, f));
    result.addAll(ChildCreationHelper.createAllChildren(saPkg, f));
    result.addAll(ChildCreationHelper.createAllChildren(laPkg, f));
    result.addAll(ChildCreationHelper.createAllChildren(paPkg, f));

    return result;
  }

}
