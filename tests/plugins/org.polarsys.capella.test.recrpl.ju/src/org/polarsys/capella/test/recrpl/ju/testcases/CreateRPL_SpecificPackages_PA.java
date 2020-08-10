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
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.model.skeleton.CapellaModelSkeleton;

public class CreateRPL_SpecificPackages_PA extends CreateRPL_SpecificPackages {

  @Override
  protected Collection<EObject> getRecElements(CapellaModelSkeleton project){

    final Collection<EObject> result = new ArrayList<EObject>();

    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(project.getSystemEngineering());
    domain.getCommandStack().execute(new RecordingCommand(domain) {

      @Override
      protected void doExecute() {

        PhysicalFunctionPkg pfPkg = (PhysicalFunctionPkg) project.getPhysicalArchitecture().getOwnedFunctionPkg();
        PhysicalFunction rootPf = pfPkg.getOwnedPhysicalFunctions().get(0);

        rootPf.getOwnedPhysicalFunctionPkgs().add(PaFactory.eINSTANCE.createPhysicalFunctionPkg());
        rootPf.getOwnedFunctions().add(PaFactory.eINSTANCE.createPhysicalFunction());

        // eContents has the sf oa realisation, we don't want that.
        result.addAll(rootPf.getOwnedFunctions());
        result.addAll(rootPf.getOwnedPhysicalFunctionPkgs());

        CapabilityRealizationPkg cPkg = (CapabilityRealizationPkg) project.getPhysicalArchitecture().getOwnedAbstractCapabilityPkg();
        cPkg.getOwnedCapabilityRealizations().add(LaFactory.eINSTANCE.createCapabilityRealization());
        cPkg.getOwnedCapabilityRealizationPkgs().add(LaFactory.eINSTANCE.createCapabilityRealizationPkg());
        result.addAll(cPkg.eContents());

        PhysicalComponentPkg aPkg = project.getPhysicalArchitecture().getOwnedPhysicalComponentPkg();
        aPkg.getOwnedPhysicalComponentPkgs().add(PaFactory.eINSTANCE.createPhysicalComponentPkg());
        PhysicalComponent actor = PaFactory.eINSTANCE.createPhysicalComponent();
        actor.setActor(true);
        aPkg.getOwnedPhysicalComponents().add(actor);
        result.addAll(aPkg.getOwnedPhysicalComponentPkgs());
        result.add(actor);

        PhysicalComponent pc = (PhysicalComponent)project.getPhysicalArchitecture().getSystem();

        aPkg.getOwnedPhysicalLinks().add(CsFactory.eINSTANCE.createPhysicalLink());
        result.addAll(aPkg.getOwnedPhysicalLinks());
        pc.getOwnedPhysicalLinks().add(CsFactory.eINSTANCE.createPhysicalLink());
        result.addAll(pc.getOwnedPhysicalLinks());
        aPkg.getOwnedComponentExchanges().add(FaFactory.eINSTANCE.createComponentExchange());
        result.addAll(aPkg.getOwnedComponentExchanges());
        pc.getOwnedComponentExchanges().add(FaFactory.eINSTANCE.createComponentExchange());
        result.addAll(pc.getOwnedComponentExchanges());
        
        pc.getOwnedPhysicalComponentPkgs().add(PaFactory.eINSTANCE.createPhysicalComponentPkg());
        PhysicalComponent subPC = PaFactory.eINSTANCE.createPhysicalComponent();
        Part part = CsFactory.eINSTANCE.createPart();
        part.setAbstractType(subPC);
        pc.getOwnedPhysicalComponents().add(subPC);
        pc.getOwnedFeatures().add(part);

        result.addAll(pc.getOwnedPhysicalComponentPkgs());
        result.addAll(pc.getOwnedPhysicalComponents());

      }

    });

    return result;
  }

}
