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

package org.polarsys.capella.core.model.links.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.helpers.refmap.KPair;
import org.polarsys.capella.core.model.helpers.refmap.CapellaRefMap;
import org.polarsys.capella.core.model.helpers.refmap.VPair;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class CapellaLinksMap {
  /**
   * Singleton instance.
   */
  private static CapellaLinksMap __instance;

  private Map<KPair, VPair> mapping = null;

  /**
   * Singleton private constructor.
   */
  private CapellaLinksMap() {
    // Nothing to do.
  }

  public static CapellaLinksMap getInstance() {
    if (null == __instance) {
      __instance = new CapellaLinksMap();
    }
    return __instance;
  }

  /**
   * Get the link mapping map (the map is initialized if that's not already done).
   * @return
   */
  public Map<KPair, VPair> getMappings() {
    if (mapping == null) {
      // Mapping map doesn't exist -> create it and fill it.
      mapping = new HashMap<KPair, VPair>();
      // Initialize the map with the content of CapellaRefMap.
      mapping.putAll(CapellaRefMap.getInstance().getMappings());

      // EPBS to Physical Realization links.
      addMapping(EpbsPackage.Literals.CONFIGURATION_ITEM, PaPackage.Literals.PHYSICAL_COMPONENT, EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION,
          EpbsPackage.Literals.CONFIGURATION_ITEM__OWNED_PHYSICAL_ARTIFACT_REALIZATIONS);
      addMapping(EpbsPackage.Literals.CONFIGURATION_ITEM, CsPackage.Literals.PHYSICAL_LINK, EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION,
          EpbsPackage.Literals.CONFIGURATION_ITEM__OWNED_PHYSICAL_ARTIFACT_REALIZATIONS);
      addMapping(EpbsPackage.Literals.CONFIGURATION_ITEM, CsPackage.Literals.PHYSICAL_PORT, EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION,
          EpbsPackage.Literals.CONFIGURATION_ITEM__OWNED_PHYSICAL_ARTIFACT_REALIZATIONS);

      // Entity/Component/Actor -> Functions/Activities allocations.
      // OA
      addMapping(OaPackage.Literals.ENTITY, OaPackage.Literals.OPERATIONAL_ACTIVITY, FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION,
          FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION);
      // SA
      addMapping(CtxPackage.Literals.SYSTEM_COMPONENT, CtxPackage.Literals.SYSTEM_FUNCTION, FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION,
          FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION);
      // LA
      addMapping(LaPackage.Literals.LOGICAL_COMPONENT, LaPackage.Literals.LOGICAL_FUNCTION, FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION,
          FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION);
      // PA
      addMapping(PaPackage.Literals.PHYSICAL_COMPONENT, PaPackage.Literals.PHYSICAL_FUNCTION, FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION,
          FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION);

      // Exchange item allocation.
      addMapping(CsPackage.Literals.INTERFACE, InformationPackage.Literals.EXCHANGE_ITEM, CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION,
              CsPackage.Literals.INTERFACE__OWNED_EXCHANGE_ITEM_ALLOCATIONS);


      addMapping(FaPackage.Literals.COMPONENT_EXCHANGE_CATEGORY, FaPackage.Literals.COMPONENT_EXCHANGE, null,
          FaPackage.Literals.COMPONENT_EXCHANGE_CATEGORY__EXCHANGES);
      
      
      

      // Generalizations.
      addMapping(CsPackage.Literals.INTERFACE, CsPackage.Literals.INTERFACE, CapellacorePackage.Literals.GENERALIZATION,
          CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS);
      addMapping(InformationPackage.Literals.CLASS, InformationPackage.Literals.CLASS, CapellacorePackage.Literals.GENERALIZATION,
          CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS);

      // Associations.
      addMapping(InformationPackage.Literals.CLASS, InformationPackage.Literals.CLASS, InformationPackage.Literals.ASSOCIATION, null);
      addMapping(InformationPackage.Literals.CLASS, InformationPackage.Literals.UNION, InformationPackage.Literals.ASSOCIATION, null);
      addMapping(InformationPackage.Literals.UNION, InformationPackage.Literals.CLASS, InformationPackage.Literals.ASSOCIATION, null);
      addMapping(InformationPackage.Literals.UNION, InformationPackage.Literals.UNION, InformationPackage.Literals.ASSOCIATION, null);
      addMapping(InformationPackage.Literals.CLASS, InformationPackage.Literals.COLLECTION, InformationPackage.Literals.ASSOCIATION, null);
      addMapping(InformationPackage.Literals.UNION, InformationPackage.Literals.COLLECTION, InformationPackage.Literals.ASSOCIATION, null);

      // Component exchange allocations.
      addMapping(CsPackage.Literals.PHYSICAL_LINK, FaPackage.Literals.COMPONENT_EXCHANGE, FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATION,
          FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATOR__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS);
      addMapping(FaPackage.Literals.COMPONENT_EXCHANGE, FaPackage.Literals.FUNCTIONAL_EXCHANGE,
          FaPackage.Literals.COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION,
          FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS);

      // Function Input/Output ports to ExchangeItem link.
      addMapping(FaPackage.Literals.FUNCTION_INPUT_PORT, InformationPackage.Literals.EXCHANGE_ITEM, null,
          FaPackage.Literals.FUNCTION_INPUT_PORT__INCOMING_EXCHANGE_ITEMS);
      addMapping(FaPackage.Literals.FUNCTION_OUTPUT_PORT, InformationPackage.Literals.EXCHANGE_ITEM, null,
          FaPackage.Literals.FUNCTION_OUTPUT_PORT__OUTGOING_EXCHANGE_ITEMS);
      
      //add Exchange Item to Functional Exchange
      addMapping(FaPackage.Literals.FUNCTIONAL_EXCHANGE, InformationPackage.Literals.EXCHANGE_ITEM, null,   FaPackage.Literals.FUNCTIONAL_EXCHANGE__EXCHANGED_ITEMS);

      //Functional Exchange to Component Exchange
      addMapping(FaPackage.Literals.FUNCTIONAL_EXCHANGE, FaPackage.Literals.COMPONENT_EXCHANGE, FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATION,  FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS);
      
      // component Exchange to Exchange Item
      addMapping(FaPackage.Literals.COMPONENT_EXCHANGE, InformationPackage.Literals.EXCHANGE_ITEM, null, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS);

      // component Exchange to Physical Link
      addMapping(CsPackage.Literals.PHYSICAL_LINK, FaPackage.Literals.COMPONENT_EXCHANGE, null, CsPackage.Literals.PHYSICAL_LINK__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS);
      
      //Functional Exchange category to Functional Exchange 
      addMapping(FaPackage.Literals.EXCHANGE_CATEGORY, FaPackage.Literals.FUNCTIONAL_EXCHANGE, null, FaPackage.Literals.EXCHANGE_CATEGORY__EXCHANGES);

      //add Physical Category to Physical Link
      addMapping(CsPackage.Literals.PHYSICAL_LINK_CATEGORY, CsPackage.Literals.PHYSICAL_LINK, null, CsPackage.Literals.PHYSICAL_LINK_CATEGORY__LINKS);

      //add Component Exchange Category to Component Exchange
      addMapping(FaPackage.Literals.COMPONENT_EXCHANGE_CATEGORY, FaPackage.Literals.COMPONENT_EXCHANGE, null, FaPackage.Literals.COMPONENT_EXCHANGE_CATEGORY__EXCHANGES);

      //add Mode to an Abstract Function like OperationalActivity, LogicalFunction , PhysicalFunction,SystemFunction
      addMapping(OaPackage.Literals.OPERATIONAL_ACTIVITY,CapellacommonPackage.Literals.MODE , null , FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES);
      addMapping(LaPackage.Literals.LOGICAL_FUNCTION,CapellacommonPackage.Literals.MODE , null , FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES);
      addMapping(PaPackage.Literals.PHYSICAL_FUNCTION,CapellacommonPackage.Literals.MODE , null , FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES);
      addMapping(CtxPackage.Literals.SYSTEM_FUNCTION,CapellacommonPackage.Literals.MODE , null , FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES);
      
      //add State to an Abstract Function like OperationalActivity, LogicalFunction , PhysicalFunction,SystemFunction
      addMapping(OaPackage.Literals.OPERATIONAL_ACTIVITY,CapellacommonPackage.Literals.STATE , null , FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES);
      addMapping(LaPackage.Literals.LOGICAL_FUNCTION,CapellacommonPackage.Literals.STATE , null , FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES);
      addMapping(PaPackage.Literals.PHYSICAL_FUNCTION,CapellacommonPackage.Literals.STATE , null , FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES);
      addMapping(CtxPackage.Literals.SYSTEM_FUNCTION,CapellacommonPackage.Literals.STATE , null , FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES);


      //add Mode to an Abstract Function like OperationalActivity, LogicalFunction , PhysicalFunction,SystemFunction
      addMapping(CtxPackage.Literals.CAPABILITY,CapellacommonPackage.Literals.MODE , null , InteractionPackage.Literals.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES);
      addMapping(LaPackage.Literals.CAPABILITY_REALIZATION,CapellacommonPackage.Literals.MODE , null , InteractionPackage.Literals.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES);
      addMapping(OaPackage.Literals.OPERATIONAL_CAPABILITY,CapellacommonPackage.Literals.MODE , null , InteractionPackage.Literals.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES);


      //add State to an Abstract Capability like OperationalCapability, CapabilityRealization ...
      addMapping(CtxPackage.Literals.CAPABILITY,CapellacommonPackage.Literals.STATE , null , InteractionPackage.Literals.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES);
      addMapping(LaPackage.Literals.CAPABILITY_REALIZATION,CapellacommonPackage.Literals.STATE , null , InteractionPackage.Literals.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES);
      addMapping(OaPackage.Literals.OPERATIONAL_CAPABILITY,CapellacommonPackage.Literals.STATE , null , InteractionPackage.Literals.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES);

      
    }
    return mapping;
  }

  /**
   * Find available mappings for given source type.
   * @param sourceType
   * @return
   */
  public List<VPair> findMappingsForSourceType(EClass sourceType) {
    List<VPair> availableMappings = new ArrayList<VPair>();
    for (Map.Entry<KPair, VPair> entry : getMappings().entrySet()) {
      if (entry.getKey().getFirstValue() == sourceType) {
        availableMappings.add(entry.getValue());
      }
    }
    return availableMappings;
  }

  /**
   * Find available mappings for given target type.
   * @param targetType
   * @return
   */
  public List<VPair> findMappingsForTargetType(EClass targetType) {
    List<VPair> availableMappings = new ArrayList<VPair>();
    for (Map.Entry<KPair, VPair> entry : getMappings().entrySet()) {
      if (entry.getKey().getSecondValue() == targetType) {
        availableMappings.add(entry.getValue());
      }
    }
    return availableMappings;
  }

  /**
   * Get mapping for given couple (sourceType, targetType).
   * @param sourceType
   * @param targetType
   * @return a <code>VPair</code> or <code>null</code> if no mapping for this couple.
   */
  public VPair getMappingFor(EClass sourceType, EClass targetType) {
    KPair key = new KPair(sourceType, targetType);
    return getMappings().get(key);
  }

  /**
   * Add a new mapping to the map. If a mapping already exists with the same (link source type, link target type) couple, the new mapping is added to existing
   * ones.
   * @param linkSrcType
   * @param linkTgtType
   * @param linkType
   * @param linkRefInSource
   */
  public void addMapping(EClass linkSrcType, EClass linkTgtType, EClass linkType, EReference linkRefInSource) {
    // Create a key.
    KPair key = new KPair(linkSrcType, linkTgtType);
    // Try and get an existing mapping.
    VPair value = getMappings().get(key);
    if (null == value) {
      // No mapping for given key -> add one.
      getMappings().put(key, new VPair(new EClass[] { linkType }, new EReference[] { linkRefInSource }));
    } else {
      // A mapping exists for given key -> create a new one with new link type and reference.
      EClass[] newLinkTypesArray = createNewArray(value.getFirstValue(), linkType);
      EReference[] newLinkRefInSourceArray = createNewArray(value.getSecondValue(), linkRefInSource);
      getMappings().put(key, new VPair(newLinkTypesArray, newLinkRefInSourceArray));
    }
  }

  /**
   * Utility method : create a new array with elements from an existing array + a new element.
   * @param <E>
   * @param baseArray
   * @param elementToAdd
   * @return
   */
  public static <E> E[] createNewArray(E[] baseArray, E elementToAdd) {
    E[] newArray = Arrays.copyOf(baseArray, baseArray.length + 1);
    newArray[baseArray.length] = elementToAdd;
    return newArray;
  }
}
