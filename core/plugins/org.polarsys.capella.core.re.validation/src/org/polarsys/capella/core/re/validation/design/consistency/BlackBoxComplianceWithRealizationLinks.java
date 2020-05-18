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
package org.polarsys.capella.core.re.validation.design.consistency;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;

import com.google.common.collect.Sets;

/**
 * This is a black box compliance, but allows adding realisation links to RPL elements.
 */
public class BlackBoxComplianceWithRealizationLinks extends BlackBoxComplianceWithExceptions {

  /**
   * The validation rule id for which this constraint is registered.
   */
  public static final String CONSTRAINT_ID = "org.polarsys.capella.core.re.validation.compliance.blackbox.withRealizationLinks"; //$NON-NLS-1$

  public BlackBoxComplianceWithRealizationLinks() {
    super(Sets.<EStructuralFeature>newHashSet(
        InteractionPackage.Literals.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS,
        FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS,
        CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS,
        InformationPackage.Literals.OPERATION__OWNED_EXCHANGE_ITEM_REALIZATIONS,
        FaPackage.Literals.FUNCTION_PKG__OWNED_EXCHANGE_SPECIFICATION_REALIZATIONS,
        FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS,
        FaPackage.Literals.FUNCTIONAL_EXCHANGE__OWNED_FUNCTIONAL_EXCHANGE_REALIZATIONS,
        InformationPackage.Literals.CLASS__OWNED_INFORMATION_REALIZATIONS,
        InformationPackage.Literals.EXCHANGE_ITEM__OWNED_INFORMATION_REALIZATIONS,
        DatatypePackage.Literals.DATA_TYPE__OWNED_INFORMATION_REALIZATIONS,
        CsPackage.Literals.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_REALIZATIONS,
        CsPackage.Literals.PHYSICAL_PATH__OWNED_PHYSICAL_PATH_REALIZATIONS,
        CsPackage.Literals.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS,
        InformationPackage.Literals.PORT__OWNED_PORT_REALIZATIONS,
        InteractionPackage.Literals.SCENARIO__OWNED_SCENARIO_REALIZATION,
        CapellacommonPackage.Literals.STATE_EVENT__OWNED_STATE_EVENT_REALIZATIONS,
        CapellacommonPackage.Literals.STATE_TRANSITION__OWNED_STATE_TRANSITION_REALIZATIONS,
        CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS,
        EpbsPackage.Literals.CONFIGURATION_ITEM__OWNED_PHYSICAL_ARTIFACT_REALIZATIONS
    ));
  }



}
