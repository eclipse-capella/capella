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
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.re.handlers.location.CapellaModelSkeleton;

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

        LogicalActorPkg aPkg = project.getLogicalArchitecture().getOwnedLogicalActorPkg();
        aPkg.getOwnedLogicalActorPkgs().add(LaFactory.eINSTANCE.createLogicalActorPkg());
        aPkg.getOwnedLogicalActors().add(LaFactory.eINSTANCE.createLogicalActor());
        result.addAll(aPkg.eContents());

        LogicalComponent lc = project.getLogicalArchitecture().getOwnedLogicalComponent();

      // FIXME
      //  lc.getOwnedLogicalComponentPkgs().add(LaFactory.eINSTANCE.createLogicalComponentPkg());
        lc.getOwnedLogicalComponents().add(LaFactory.eINSTANCE.createLogicalComponent());

        result.addAll(lc.getOwnedLogicalComponentPkgs());
        result.addAll(lc.getOwnedLogicalComponents());

      }

    });

    return result;
  }

}
