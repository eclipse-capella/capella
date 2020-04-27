/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers.refmap;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;

public class CapellaRefMap {
  /** */
  private static CapellaRefMap instance;

  /** */
  private Map<KPair, VPair> mapping = null;

  /**
   * Private constructor
   */
  private CapellaRefMap() {
    //
  }

  /**
   * Singleton
   */
  public static CapellaRefMap getInstance() {
    if (null == instance) {
      instance = new CapellaRefMap();
    }
    return instance;
  }

  /**
   * Returns the map containing the references map
   * @return
   */
  public Map<KPair, VPair> getMappings() {
    if (mapping == null) {
      mapping = new HashMap<KPair, VPair>();

      // miscellaneous realizations
      mapping.put(new KPair(CsPackage.Literals.INTERFACE, CsPackage.Literals.INTERFACE), new VPair(new EClass[] { CsPackage.Literals.INTERFACE_ALLOCATION },
          new EReference[] { CsPackage.Literals.INTERFACE_ALLOCATOR__OWNED_INTERFACE_ALLOCATIONS }));
      mapping.put(new KPair(FaPackage.Literals.COMPONENT_EXCHANGE, FaPackage.Literals.COMPONENT_EXCHANGE), new VPair(
          new EClass[] { FaPackage.Literals.COMPONENT_EXCHANGE_REALIZATION },
          new EReference[] { FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS }));
      mapping.put(new KPair(FaPackage.Literals.COMPONENT_EXCHANGE, OaPackage.Literals.COMMUNICATION_MEAN), new VPair(
          new EClass[] { FaPackage.Literals.COMPONENT_EXCHANGE_REALIZATION },
          new EReference[] { FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS }));
      mapping.put(new KPair(FaPackage.Literals.FUNCTIONAL_EXCHANGE, FaPackage.Literals.FUNCTIONAL_EXCHANGE), new VPair(
          new EClass[] { FaPackage.Literals.FUNCTIONAL_EXCHANGE_REALIZATION },
          new EReference[] { FaPackage.Literals.FUNCTIONAL_EXCHANGE__OWNED_FUNCTIONAL_EXCHANGE_REALIZATIONS }));
      mapping.put(new KPair(InformationPackage.Literals.PORT, InformationPackage.Literals.PORT), new VPair(
          new EClass[] { InformationPackage.Literals.PORT_ALLOCATION, InformationPackage.Literals.PORT_REALIZATION },
          new EReference[] { InformationPackage.Literals.PORT__OWNED_PORT_ALLOCATIONS, InformationPackage.Literals.PORT__OWNED_PORT_REALIZATIONS }));
      mapping.put(new KPair(FaPackage.Literals.COMPONENT_PORT, FaPackage.Literals.COMPONENT_PORT), new VPair(
          new EClass[] { InformationPackage.Literals.PORT_REALIZATION }, new EReference[] { InformationPackage.Literals.PORT__OWNED_PORT_REALIZATIONS }));

      // scenario realizations
      mapping.put(new KPair(InteractionPackage.Literals.SCENARIO, InteractionPackage.Literals.SCENARIO), new VPair(
          new EClass[] { InteractionPackage.Literals.SCENARIO_REALIZATION },
          new EReference[] { InteractionPackage.Literals.SCENARIO__OWNED_SCENARIO_REALIZATION }));

      // capability realizations
      mapping.put(new KPair(CtxPackage.Literals.CAPABILITY, OaPackage.Literals.OPERATIONAL_CAPABILITY), new VPair(
          new EClass[] { InteractionPackage.Literals.ABSTRACT_CAPABILITY_REALIZATION },
          new EReference[] { InteractionPackage.Literals.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS }));
      mapping.put(new KPair(LaPackage.Literals.CAPABILITY_REALIZATION, CtxPackage.Literals.CAPABILITY), new VPair(
          new EClass[] { InteractionPackage.Literals.ABSTRACT_CAPABILITY_REALIZATION },
          new EReference[] { InteractionPackage.Literals.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS }));
      mapping.put(new KPair(LaPackage.Literals.CAPABILITY_REALIZATION, LaPackage.Literals.CAPABILITY_REALIZATION), new VPair(
          new EClass[] { InteractionPackage.Literals.ABSTRACT_CAPABILITY_REALIZATION },
          new EReference[] { InteractionPackage.Literals.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS }));

      // state machine realizations
      mapping.put(new KPair(CapellacommonPackage.Literals.CHOICE_PSEUDO_STATE, CapellacommonPackage.Literals.CHOICE_PSEUDO_STATE), new VPair(
          new EClass[] { CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION },
          new EReference[] { CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS }));
      mapping.put(new KPair(CapellacommonPackage.Literals.FORK_PSEUDO_STATE, CapellacommonPackage.Literals.FORK_PSEUDO_STATE), new VPair(
          new EClass[] { CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION },
          new EReference[] { CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS }));
      mapping.put(new KPair(CapellacommonPackage.Literals.INITIAL_PSEUDO_STATE, CapellacommonPackage.Literals.INITIAL_PSEUDO_STATE), new VPair(
          new EClass[] { CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION },
          new EReference[] { CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS }));
      mapping.put(new KPair(CapellacommonPackage.Literals.JOIN_PSEUDO_STATE, CapellacommonPackage.Literals.JOIN_PSEUDO_STATE), new VPair(
          new EClass[] { CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION },
          new EReference[] { CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS }));
      mapping.put(new KPair(CapellacommonPackage.Literals.TERMINATE_PSEUDO_STATE, CapellacommonPackage.Literals.TERMINATE_PSEUDO_STATE), new VPair(
          new EClass[] { CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION },
          new EReference[] { CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS }));
      mapping.put(new KPair(CapellacommonPackage.Literals.FINAL_STATE, CapellacommonPackage.Literals.FINAL_STATE), new VPair(
          new EClass[] { CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION },
          new EReference[] { CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS }));
      mapping.put(new KPair(CapellacommonPackage.Literals.MODE, CapellacommonPackage.Literals.MODE), new VPair(
          new EClass[] { CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION },
          new EReference[] { CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS }));
      mapping.put(new KPair(CapellacommonPackage.Literals.STATE, CapellacommonPackage.Literals.STATE), new VPair(
          new EClass[] { CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION },
          new EReference[] { CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS }));
      mapping.put(new KPair(CapellacommonPackage.Literals.STATE_TRANSITION, CapellacommonPackage.Literals.STATE_TRANSITION), new VPair(
          new EClass[] { CapellacommonPackage.Literals.STATE_TRANSITION_REALIZATION },
          new EReference[] { CapellacommonPackage.Literals.STATE_TRANSITION__OWNED_STATE_TRANSITION_REALIZATIONS }));

      // functional chain realizations
      mapping.put(new KPair(FaPackage.Literals.FUNCTIONAL_CHAIN, OaPackage.Literals.OPERATIONAL_PROCESS), new VPair(
          new EClass[] { FaPackage.Literals.FUNCTIONAL_CHAIN_REALIZATION },
          new EReference[] { FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS }));
      mapping.put(new KPair(FaPackage.Literals.FUNCTIONAL_CHAIN, FaPackage.Literals.FUNCTIONAL_CHAIN), new VPair(
          new EClass[] { FaPackage.Literals.FUNCTIONAL_CHAIN_REALIZATION },
          new EReference[] { FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS }));

      // information realizations
      mapping.put(new KPair(DatatypePackage.Literals.BOOLEAN_TYPE, DatatypePackage.Literals.BOOLEAN_TYPE), new VPair(
          new EClass[] { InformationPackage.Literals.INFORMATION_REALIZATION },
          new EReference[] { DatatypePackage.Literals.DATA_TYPE__OWNED_INFORMATION_REALIZATIONS }));
      mapping.put(new KPair(DatatypePackage.Literals.ENUMERATION, DatatypePackage.Literals.ENUMERATION), new VPair(
          new EClass[] { InformationPackage.Literals.INFORMATION_REALIZATION },
          new EReference[] { DatatypePackage.Literals.DATA_TYPE__OWNED_INFORMATION_REALIZATIONS }));
      mapping.put(new KPair(DatatypePackage.Literals.NUMERIC_TYPE, DatatypePackage.Literals.NUMERIC_TYPE), new VPair(
          new EClass[] { InformationPackage.Literals.INFORMATION_REALIZATION },
          new EReference[] { DatatypePackage.Literals.DATA_TYPE__OWNED_INFORMATION_REALIZATIONS }));
      mapping.put(new KPair(DatatypePackage.Literals.PHYSICAL_QUANTITY, DatatypePackage.Literals.PHYSICAL_QUANTITY), new VPair(
          new EClass[] { InformationPackage.Literals.INFORMATION_REALIZATION },
          new EReference[] { DatatypePackage.Literals.DATA_TYPE__OWNED_INFORMATION_REALIZATIONS }));
      mapping.put(new KPair(DatatypePackage.Literals.STRING_TYPE, DatatypePackage.Literals.STRING_TYPE), new VPair(
          new EClass[] { InformationPackage.Literals.INFORMATION_REALIZATION },
          new EReference[] { DatatypePackage.Literals.DATA_TYPE__OWNED_INFORMATION_REALIZATIONS }));
      mapping.put(new KPair(InformationPackage.Literals.CLASS, InformationPackage.Literals.CLASS), new VPair(
          new EClass[] { InformationPackage.Literals.INFORMATION_REALIZATION },
          new EReference[] { InformationPackage.Literals.CLASS__OWNED_INFORMATION_REALIZATIONS }));
      mapping.put(new KPair(InformationPackage.Literals.EXCHANGE_ITEM, InformationPackage.Literals.EXCHANGE_ITEM), new VPair(
          new EClass[] { InformationPackage.Literals.INFORMATION_REALIZATION },
          new EReference[] { InformationPackage.Literals.EXCHANGE_ITEM__OWNED_INFORMATION_REALIZATIONS }));

      // function realizations
      mapping.put(new KPair(FaPackage.Literals.ABSTRACT_FUNCTION, FaPackage.Literals.ABSTRACT_FUNCTION), new VPair(
          new EClass[] { FaPackage.Literals.FUNCTION_REALIZATION }, new EReference[] { FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS }));
      mapping.put(new KPair(CtxPackage.Literals.SYSTEM_FUNCTION, OaPackage.Literals.OPERATIONAL_ACTIVITY), new VPair(
          new EClass[] { FaPackage.Literals.FUNCTION_REALIZATION }, new EReference[] { FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS }));
      mapping.put(new KPair(LaPackage.Literals.LOGICAL_FUNCTION, CtxPackage.Literals.SYSTEM_FUNCTION), new VPair(
          new EClass[] { FaPackage.Literals.FUNCTION_REALIZATION }, new EReference[] { FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS }));
      mapping.put(new KPair(PaPackage.Literals.PHYSICAL_FUNCTION, LaPackage.Literals.LOGICAL_FUNCTION), new VPair(
          new EClass[] { FaPackage.Literals.FUNCTION_REALIZATION }, new EReference[] { FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS }));
      mapping.put(new KPair(FaPackage.Literals.FUNCTION_INPUT_PORT, FaPackage.Literals.FUNCTION_INPUT_PORT), new VPair(
          new EClass[] { InformationPackage.Literals.PORT_REALIZATION }, new EReference[] { InformationPackage.Literals.PORT__OWNED_PORT_REALIZATIONS }));
      mapping.put(new KPair(FaPackage.Literals.FUNCTION_OUTPUT_PORT, FaPackage.Literals.FUNCTION_OUTPUT_PORT), new VPair(
          new EClass[] { InformationPackage.Literals.PORT_REALIZATION }, new EReference[] { InformationPackage.Literals.PORT__OWNED_PORT_REALIZATIONS }));

      mapping.put(new KPair(CtxPackage.Literals.SYSTEM_COMPONENT, OaPackage.Literals.ENTITY),
          new VPair(new EClass[] { CsPackage.Literals.COMPONENT_REALIZATION },
              new EReference[] { CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS }));
      mapping.put(new KPair(LaPackage.Literals.LOGICAL_COMPONENT, CtxPackage.Literals.SYSTEM_COMPONENT),
          new VPair(new EClass[] { CsPackage.Literals.COMPONENT_REALIZATION },
              new EReference[] { CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS }));
      mapping.put(new KPair(PaPackage.Literals.PHYSICAL_COMPONENT, LaPackage.Literals.LOGICAL_COMPONENT),
          new VPair(new EClass[] { CsPackage.Literals.COMPONENT_REALIZATION },
              new EReference[] { CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS }));
    }

    return mapping;
  }
}
