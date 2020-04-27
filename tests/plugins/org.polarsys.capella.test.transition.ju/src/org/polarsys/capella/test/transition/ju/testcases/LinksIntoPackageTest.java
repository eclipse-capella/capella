/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.test.transition.ju.model.PLCE;

public class LinksIntoPackageTest extends PLCE {

  @Override
  public void performTest() throws Exception {
    performActorTransition(getObjects(SA_2, SA_4, SA_9, SA_5, SA_7, SA_8, SA_10, SA_11));

    // PL_1 shall be created into LA_5 as LA_5 will be created (resp. CE)
    mustBeTransitionedAndContainedByTransitioned(PL_1, SA_5, CsPackage.Literals.COMPONENT__OWNED_PHYSICAL_LINKS);
    mustBeTransitionedAndContainedByTransitioned(C_1, SA_2,
        FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES);

    // PL_5 shall be created into LA_5 as LA_5 will be created (resp. CE)
    mustBeTransitionedAndContainedByTransitioned(PL_5, SA_5, CsPackage.Literals.COMPONENT__OWNED_PHYSICAL_LINKS);
    mustBeTransitionedAndContainedByTransitioned(C_5, SA_2,
        FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES);

    // PL_4 into LA_6 as owning component is already existing in LA (resp. CE)
    mustBeTransitionedAndContainedByTransitioned(PL_4, SA_6, CsPackage.Literals.COMPONENT__OWNED_PHYSICAL_LINKS);
    mustBeTransitionedAndContainedByTransitioned(C_4, SA_3,
        FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES);

    // PL_2 into Structure as owning by System which is not into scope (resp. CE)
    // This is a specific case (perhaps unwanted as System is realized by Logical System but as System is not into the
    // initial source of transition, it doesn't work directly)
    mustBeTransitionedAndContainedBy(PL_2, LA_STRUCTURE, CsPackage.Literals.COMPONENT_PKG__OWNED_PHYSICAL_LINKS);
    mustBeTransitionedAndContainedBy(C_2, LA_STRUCTURE, CsPackage.Literals.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES);

    // PL_3 into Structure as owning Pkg is not propagated (resp. CE)
    mustBeTransitionedAndContainedBy(PL_3, LA_STRUCTURE, CsPackage.Literals.COMPONENT_PKG__OWNED_PHYSICAL_LINKS);
    mustBeTransitionedAndContainedBy(C_3, LA_STRUCTURE, CsPackage.Literals.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES);

    // PL_6 into Structure. (resp. CE) As owned by a component not propagated, it is now located into getDefaultContainer.
    // we could have put it into SA_3 though, but getBestContainer isn't recursive here.
    mustBeTransitionedAndContainedBy(PL_6, LA_STRUCTURE, CsPackage.Literals.COMPONENT_PKG__OWNED_PHYSICAL_LINKS);
    mustBeTransitionedAndContainedBy(C_6, LA_STRUCTURE, CsPackage.Literals.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES);

  }
}
