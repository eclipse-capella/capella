/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.re.handlers.location.CapellaModelSkeleton;

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

        PhysicalActorPkg aPkg = project.getPhysicalArchitecture().getOwnedPhysicalActorPkg();
        aPkg.getOwnedPhysicalActorPkgs().add(PaFactory.eINSTANCE.createPhysicalActorPkg());
        aPkg.getOwnedPhysicalActors().add(PaFactory.eINSTANCE.createPhysicalActor());
        result.addAll(aPkg.eContents());

        PhysicalComponent pc = project.getPhysicalArchitecture().getOwnedPhysicalComponent();

        // FIXME
     //   pc.getOwnedPhysicalComponentPkgs().add(PaFactory.eINSTANCE.createPhysicalComponentPkg());
        pc.getOwnedPhysicalComponents().add(PaFactory.eINSTANCE.createPhysicalComponent());

        result.addAll(pc.getOwnedPhysicalComponentPkgs());
        result.addAll(pc.getOwnedPhysicalComponents());

      }

    });

    return result;
  }

}
