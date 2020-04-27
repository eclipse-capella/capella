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
package org.polarsys.capella.test.recrpl.ju.testcases;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.model.skeleton.CapellaModelSkeleton;

public class CreateRPL_SpecificPackages_LA extends CreateRPL_SpecificPackages {

  @Override
  protected Collection<EObject> getRecElements(CapellaModelSkeleton project){

    final Collection<EObject> result = new ArrayList<EObject>();
    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(project.getSystemEngineering());
    domain.getCommandStack().execute(new RecordingCommand(domain) {

      @Override
      protected void doExecute() {

        LogicalFunctionPkg sfPkg = (LogicalFunctionPkg) project.getLogicalArchitecture().getOwnedFunctionPkg();
        LogicalFunction rootSf = sfPkg.getOwnedLogicalFunctions().get(0);

        rootSf.getOwnedLogicalFunctionPkgs().add(LaFactory.eINSTANCE.createLogicalFunctionPkg());
        rootSf.getOwnedFunctions().add(LaFactory.eINSTANCE.createLogicalFunction());

        // eContents has the sf oa realisation, we don't want that.
        result.addAll(rootSf.getOwnedFunctions());
        result.addAll(rootSf.getOwnedLogicalFunctionPkgs());

        CapabilityRealizationPkg cPkg = (CapabilityRealizationPkg) project.getLogicalArchitecture().getOwnedAbstractCapabilityPkg();
        cPkg.getOwnedCapabilityRealizations().add(LaFactory.eINSTANCE.createCapabilityRealization());
        cPkg.getOwnedCapabilityRealizationPkgs().add(LaFactory.eINSTANCE.createCapabilityRealizationPkg());
        result.addAll(cPkg.eContents());

        LogicalComponentPkg aPkg = project.getLogicalArchitecture().getOwnedLogicalComponentPkg();
        aPkg.getOwnedLogicalComponentPkgs().add(LaFactory.eINSTANCE.createLogicalComponentPkg());
        LogicalComponent actor = LaFactory.eINSTANCE.createLogicalComponent();
        actor.setActor(true);
        aPkg.getOwnedLogicalComponents().add(actor);
        result.addAll(aPkg.getOwnedLogicalComponentPkgs());
        result.add(actor);

        LogicalComponent lc = (LogicalComponent)project.getLogicalArchitecture().getSystem();

        lc.getOwnedLogicalComponentPkgs().add(LaFactory.eINSTANCE.createLogicalComponentPkg());
        LogicalComponent subLC = LaFactory.eINSTANCE.createLogicalComponent();
        lc.getOwnedLogicalComponents().add(subLC);
        Part part = CsFactory.eINSTANCE.createPart();
        part.setAbstractType(subLC);
        lc.getOwnedFeatures().add(part);

        result.addAll(lc.getOwnedLogicalComponentPkgs());
        result.addAll(lc.getOwnedLogicalComponents());
        result.addAll(lc.getContainedParts());

      }

    });

    return result;
  }

}
