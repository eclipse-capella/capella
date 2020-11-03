/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.model.skeleton.CapellaModelSkeleton;

public class CreateRPL_SpecificPackages_Data extends CreateRPL_SpecificPackages {

  @Override
  protected Collection<EObject> getRecElements(CapellaModelSkeleton project){

    DataPkg oaPkg = project.getOperationalAnalysis().getOwnedDataPkg();
    DataPkg saPkg = project.getSystemAnalysis().getOwnedDataPkg();
    DataPkg laPkg = project.getLogicalArchitecture().getOwnedDataPkg();
    DataPkg paPkg = project.getPhysicalArchitecture().getOwnedDataPkg();

    Collection<EObject> dataPkgChildren = new ArrayList<EObject>();

    // don't create any property values and no constraint
    ChildCreationHelper.Filter f = (EObject parent, EClass clazz, EReference ref) -> {
      return clazz.getEPackage() == CapellacorePackage.eINSTANCE;
    };

    dataPkgChildren.addAll(ChildCreationHelper.createAllChildren(oaPkg, f));
    dataPkgChildren.addAll(ChildCreationHelper.createAllChildren(saPkg, f));
    dataPkgChildren.addAll(ChildCreationHelper.createAllChildren(laPkg, f));
    dataPkgChildren.addAll(ChildCreationHelper.createAllChildren(paPkg, f));

    return dataPkgChildren;
  }

}
