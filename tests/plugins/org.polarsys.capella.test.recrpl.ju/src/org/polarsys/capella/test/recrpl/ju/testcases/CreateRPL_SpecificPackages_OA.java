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
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.oa.RolePkg;
import org.polarsys.capella.core.model.skeleton.CapellaModelSkeleton;

public class CreateRPL_SpecificPackages_OA extends CreateRPL_SpecificPackages {

  @Override
  protected Collection<EObject> getRecElements(CapellaModelSkeleton project){

    final Collection<EObject> result = new ArrayList<EObject>();

    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(project.getSystemEngineering());
    domain.getCommandStack().execute(new RecordingCommand(domain) {

      @Override
      protected void doExecute() {
        EntityPkg entityPkg = project.getOperationalAnalysis().getOwnedEntityPkg();

        Entity entity = OaFactory.eINSTANCE.createEntity();
        Part part = CsFactory.eINSTANCE.createPart();
        part.setAbstractType(entity);
        entityPkg.getOwnedEntities().add(entity);
        entityPkg.getOwnedParts().add(part);
        entityPkg.getOwnedEntityPkgs().add(OaFactory.eINSTANCE.createEntityPkg());
        result.addAll(entityPkg.eContents());

        OperationalActivityPkg activityPkg = (OperationalActivityPkg) project.getOperationalAnalysis().getOwnedFunctionPkg();
        OperationalActivity rootActivity = activityPkg.getOwnedOperationalActivities().get(0);

        rootActivity.getOwnedFunctions().add(OaFactory.eINSTANCE.createOperationalActivity());
        rootActivity.getOwnedOperationalActivityPkgs().add(OaFactory.eINSTANCE.createOperationalActivityPkg());

        result.addAll(rootActivity.eContents());

        RolePkg rolePkg = project.getOperationalAnalysis().getOwnedRolePkg();
        rolePkg.getOwnedRoles().add(OaFactory.eINSTANCE.createRole());
        rolePkg.getOwnedRolePkgs().add(OaFactory.eINSTANCE.createRolePkg());

        result.addAll(rolePkg.eContents());

        OperationalCapabilityPkg cPkg = (OperationalCapabilityPkg) project.getOperationalAnalysis().getOwnedAbstractCapabilityPkg();
        cPkg.getOwnedOperationalCapabilities().add(OaFactory.eINSTANCE.createOperationalCapability());
        cPkg.getOwnedOperationalCapabilityPkgs().add(OaFactory.eINSTANCE.createOperationalCapabilityPkg());

        result.addAll(cPkg.eContents());

      }

    });

    return result;
  }

}
