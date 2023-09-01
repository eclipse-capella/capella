/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.system.domain;

import java.util.Set;

import org.eclipse.emf.ecore.EPackage;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.behavior.BehaviorPackage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;
import org.polarsys.capella.core.transition.common.domain.DomainHelper;
import org.polarsys.kitalpha.emde.model.Element;

/**
 * 
 */
public class CapellaTransitionDomainHelper extends DomainHelper {

  /**
   * @see org.polarsys.kitalpha.transposer.transformation.emf.util.EmfDomainHelper#getEPackages()
   */
  @Override
  protected Set<EPackage> getEPackages() {
    Set<EPackage> ePackages = super.getEPackages();

    ePackages.add(ModellingcorePackage.eINSTANCE);
    ePackages.add(CapellacorePackage.eINSTANCE);
    ePackages.add(BehaviorPackage.eINSTANCE);
    ePackages.add(ActivityPackage.eINSTANCE);
    ePackages.add(CapellacommonPackage.eINSTANCE);
    ePackages.add(InteractionPackage.eINSTANCE);
    ePackages.add(DatavaluePackage.eINSTANCE);
    ePackages.add(DatatypePackage.eINSTANCE);
    ePackages.add(InformationPackage.eINSTANCE);
    ePackages.add(FaPackage.eINSTANCE);
    ePackages.add(CsPackage.eINSTANCE);
    ePackages.add(OaPackage.eINSTANCE);
    ePackages.add(CtxPackage.eINSTANCE);
    ePackages.add(LaPackage.eINSTANCE);
    ePackages.add(PaPackage.eINSTANCE);
    ePackages.add(DeploymentPackage.eINSTANCE);
    ePackages.add(EpbsPackage.eINSTANCE);
    ePackages.add(CapellamodellerPackage.eINSTANCE);
    ePackages.add(SharedmodelPackage.eINSTANCE);
    ePackages.add(CommunicationPackage.eINSTANCE);

    return ePackages;
  }

  /**
   * @see org.polarsys.kitalpha.transposer.rules.handler.rules.api.IDomainHelper#isDomainFor(java.lang.Object)
   */
  @Override
  public boolean isDomainFor(Object object) {
    return object instanceof Element;
  }

}
