/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentContext;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.OperationalAnalysisExt;
import org.polarsys.capella.core.model.helpers.PhysicalArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

/**
 *
 */
public class AllocationManagementData {

  /**
   * data message provides proper information to end user.
   */
  private String _dataMessage;

  /**
   * allocation type helps to identify the type of allocation action and message to apply.
   */
  private AllocationSelectionType _allocationType;

  /**
   * check weather the filtered sourceData is empty or not.
   */
  private boolean _sourceDataVoid;

  /**
   * default constructor
   */
  public AllocationManagementData() {
    _dataMessage = Messages.AllocationManagementWizardAction_Title;
    _allocationType = AllocationSelectionType.NONE;
    _sourceDataVoid = false;
  }

  /**
   * create instance of the class
   */
  public static AllocationManagementData getInstance() {
    return new AllocationManagementData();
  }

  /**
   * Allocation types
   */
  public enum AllocationSelectionType {
    NONE, FUNCTION_ALLOCATION, EXCHANGE_ITEM_ALLOCATION, PHYSICAL_PART_DEPLOYMENT, FUNCTIONAL_EXCHANGE_ALLOCATION, COMPONENT_EXCHANGE_ALLOCATION
  }

  /**
   * Return list of elements used for allocation or deployment
   * @param element_p
   * @param titleMessage_p
   * @return
   */
  public List<CapellaElement> getCorrespondingData(List<EObject> element_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    if (element_p == null) {
      return result;
    }

    if (areAllElementFunctions(element_p)) {
      // collect all non allocated functions from element_p
      //
      List<EObject> nonAllocatedFunctions = new ArrayList<EObject>(0);
      for (EObject object : element_p) {
        if (AbstractFunctionExt.isAbstractFunctionAvailableForAllocation(object)) {
          nonAllocatedFunctions.add(object);
        }
      }
      // check if any data available to work on or to apply action
      //
      if (nonAllocatedFunctions.isEmpty()) {
        setSourceDataVoid(true);
      }
      // set data message
      //
      setDataMessage(createDataMessage(nonAllocatedFunctions, AllocationSelectionType.FUNCTION_ALLOCATION));
      // set allocation type
      //
      setAllocationType(AllocationSelectionType.FUNCTION_ALLOCATION);
      // collect all Component from current layer and return
      //
      return getAllComponentsFromCurrentLayers(nonAllocatedFunctions);

    } else if (areAllElementExchangeItems(element_p)) {
      // check if any data available to work on or to apply action
      if (element_p.isEmpty()) {
        setSourceDataVoid(true);
      }
      // set data message
      setDataMessage(createDataMessage(element_p, AllocationSelectionType.EXCHANGE_ITEM_ALLOCATION));
      // set allocation type
      setAllocationType(AllocationSelectionType.EXCHANGE_ITEM_ALLOCATION);
      // collect all interfaces from all layers and return
      return getAllInterfacesFromAllLayers(element_p);

    } else if (WizardActionHelper.areAllElementFunctionalExchange(element_p)) {
      // collect all non allocated functions from element_p
      List<EObject> nonAllocatedFunExcs = new ArrayList<EObject>(0);
      for (EObject object : element_p) {
        if (AbstractFunctionExt.isFunctionExchangeAvailableForAllocation(object)) {
          nonAllocatedFunExcs.add(object);
        }
      }
      // check if any data available to work on or to apply action
      if (nonAllocatedFunExcs.isEmpty()) {
        setSourceDataVoid(true);
      }
      // set data message
      setDataMessage(createDataMessage(nonAllocatedFunExcs, AllocationSelectionType.FUNCTIONAL_EXCHANGE_ALLOCATION));
      // set allocation type
      setAllocationType(AllocationSelectionType.FUNCTIONAL_EXCHANGE_ALLOCATION);
      // collect all connection from same level
      return getAvailableAllocatingComponentExchanges2(nonAllocatedFunExcs);

    } else if (WizardActionHelper.areAllElementsComponentExchanges(element_p)) {
      // collect all non allocated functions from element_p
      List<EObject> nonAllocatedCompExcs = new ArrayList<EObject>(0);
      for (EObject object : element_p) {
        if (AbstractFunctionExt.isComponentExchangeAvailableForAllocation(object)) {
          nonAllocatedCompExcs.add(object);
        }
      }
      // check if any data available to work on or to apply action
      if (nonAllocatedCompExcs.isEmpty()) {
        setSourceDataVoid(true);
      }
      // set data message
      setDataMessage(createDataMessage(nonAllocatedCompExcs, AllocationSelectionType.COMPONENT_EXCHANGE_ALLOCATION));
      // set allocation type
      setAllocationType(AllocationSelectionType.COMPONENT_EXCHANGE_ALLOCATION);
      // collect all connection from same level
      return getAvailableAllocatingPhysicalLinks(nonAllocatedCompExcs);

    } else if (areAllElementPCParts(element_p)) {
      // collect all non deployed physical components from element_p
      List<EObject> nonDeployedPCParts = new ArrayList<EObject>(0);
      for (EObject object : element_p) {
        if (AbstractFunctionExt.isPCPartAvailableForDeployment(object)) {
          nonDeployedPCParts.add(object);
        }
      }
      // check if any data available to work on or to apply action
      if (nonDeployedPCParts.isEmpty()) {
        setSourceDataVoid(true);
      }
      // set data message
      setDataMessage(createDataMessage(nonDeployedPCParts, AllocationSelectionType.PHYSICAL_PART_DEPLOYMENT));
      // set allocation type
      setAllocationType(AllocationSelectionType.PHYSICAL_PART_DEPLOYMENT);
      // collect all Component from current layer and return
      return getAllPartsFromPCLayers(nonDeployedPCParts);
    }

    return result;
  }

  /**
   * @param function_p
   */
  private List<CapellaElement> getAllComponentsFromCurrentLayers(List<EObject> element_p) {
    List<CapellaElement> temp = new ArrayList<CapellaElement>(0);
    List<CapellaElement> result = new ArrayList<CapellaElement>(0);
    if (!element_p.isEmpty() && (element_p.get(0) != null)) {
      // get root architecture
      BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(element_p.get(0));
      if (null == arch) {
        return result;
      }
      // add all components from current architecture to temp list
      BlockArchitectureExt.getAllComponentsFromBlockArchitecture(arch, temp);
      // remove first component from temp list
      Component firstComponent = BlockArchitectureExt.getFirstComponent(arch);
      if (!(firstComponent instanceof org.polarsys.capella.core.data.ctx.System)) {
        temp.remove(firstComponent);
      }
      // filter ComponentContext from temp list... and add to result list
      for (CapellaElement capellaElement : temp) {
        if (!(capellaElement instanceof ComponentContext) && isNotNodePhysicalComponent(capellaElement)) {
          result.add(capellaElement);
        }
      }
      // add all roles to result list
      if (arch instanceof OperationalAnalysis) {
        result.addAll(OperationalAnalysisExt.getAllRoles((OperationalAnalysis) arch));
      }

    }
    return result;
  }

  /**
   * @param capellaElement_p
   * @return
   */
  private boolean isNotNodePhysicalComponent(CapellaElement capellaElement_p) {
    if (capellaElement_p instanceof PhysicalComponent) {
      PhysicalComponent comp = (PhysicalComponent) capellaElement_p;
      if (comp.getNature() == PhysicalComponentNature.NODE) {
        return false;
      }
    }
    return true;
  }

  /**
   * Return all the interfaces from all the layers
   * @param element_p
   * @return
   */
  private List<CapellaElement> getAllInterfacesFromAllLayers(List<EObject> element_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>(0);
    if (!element_p.isEmpty()) {
      SystemEngineering sysEng = SystemEngineeringExt.getSystemEngineering((CapellaElement) element_p.get(0));
      EList<ModellingArchitecture> architectures = sysEng.getOwnedArchitectures();
      for (ModellingArchitecture modellingArchitecture : architectures) {
        result.addAll(SystemEngineeringExt.getAllInterfaces(modellingArchitecture));
      }
    }
    return result;
  }

  /**
   * Return all parts from PC layer (except the root and )
   * @param nonDeployedPCParts_p
   * @return
   */
  private List<CapellaElement> getAllPartsFromPCLayers(List<EObject> nonDeployedPCParts_p) {
    List<CapellaElement> components = new ArrayList<CapellaElement>();
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    if (!nonDeployedPCParts_p.isEmpty()) {
      BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(nonDeployedPCParts_p.get(0));
      if ((null != arch) && (arch instanceof PhysicalArchitecture)) {
        BlockArchitectureExt.getAllComponentsFromPA(arch, components);
        if (components.isEmpty()) {
          return components;
        }
        // remove first component
        Component firstComponent = BlockArchitectureExt.getFirstComponent(arch);
        components.remove(firstComponent);
        for (CapellaElement capellaElement : components) {
          if ((capellaElement instanceof PhysicalComponent) && !(capellaElement instanceof ComponentContext)) {
            PhysicalComponent comp = (PhysicalComponent) capellaElement;
            EList<AbstractTypedElement> parts = comp.getAbstractTypedElements();
            for (AbstractTypedElement abstractTypedElement : parts) {
              if (abstractTypedElement instanceof Part) {
                result.add((Part) abstractTypedElement);
              }
            }
          }
        }
      }
    }

    // remove currents parts
    result.removeAll(nonDeployedPCParts_p);

    return result;
  }

  /**
   * @param nonAllocatedFunExcs_p
   * @return
   */
  private List<CapellaElement> getAvailableAllocatingComponentExchanges2(List<EObject> nonAllocatedFunExcs_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>(0);
    if (nonAllocatedFunExcs_p.isEmpty()) {
      return result;
    }

    EObject object = nonAllocatedFunExcs_p.get(0);
    if (null != object) {
      BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(object);
      if (null != arch) {
        for (ComponentExchange link : BlockArchitectureExt.getAllComponentExchanges(arch)) {
          // get availableComponentExhanges for current PhysicalLink
          IBusinessQuery query =
              BusinessQueriesProvider.getInstance().getContribution(FaPackage.Literals.COMPONENT_EXCHANGE,
                  FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS);
          List<CapellaElement> compExchanges = query.getAvailableElements(link);
          for (CapellaElement functionalExchange : compExchanges) {
            // if availableComponentExhanges for current PhysicalLink is one of the selected component exchange
            // add physical link
            if (nonAllocatedFunExcs_p.contains(functionalExchange)) {
              result.add(link);
            }
          }
        }
      }
    }

    return result;
  }

  /**
   * Returns all the physical link available for allocating <to be improved>
   * @param nonAllocatedCompExcs_p
   * @return
   */
  private List<CapellaElement> getAvailableAllocatingPhysicalLinks(List<EObject> nonAllocatedCompExcs_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>(0);
    if (nonAllocatedCompExcs_p.isEmpty()) {
      return result;
    }

    EObject object = nonAllocatedCompExcs_p.get(0);
    if (null != object) {
      BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(object);
      if (arch instanceof PhysicalArchitecture) {
        for (PhysicalLink link : PhysicalArchitectureExt.getAllPhysicalLinks((PhysicalArchitecture) arch)) {
          // get availableComponentExhanges for current PhysicalLink
          IBusinessQuery query =
              BusinessQueriesProvider.getInstance().getContribution(CsPackage.Literals.PHYSICAL_LINK,
                  FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATOR__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS);
          List<CapellaElement> compExchanges = query.getAvailableElements(link);
          for (CapellaElement compExchange : compExchanges) {
            // if availableComponentExhanges for current PhysicalLink is one of the selected component exchange
            // add physical link
            if (nonAllocatedCompExcs_p.contains(compExchange)) {
              result.add(link);
            }
          }
        }
      }
    }

    return result;
  }

  /**
   * return true if all he element in the list are of type AbstractFunction, false otherwise
   * @return
   */
  private boolean areAllElementFunctions(List<EObject> elements_p) {
    boolean flag = false;
    for (EObject object : elements_p) {
      if (!(object instanceof AbstractFunction)) {
        return false;
      } else if (object instanceof AbstractFunction) {
        flag = true;
      }
    }

    return flag;
  }

  /**
   * return true if all he element in the list are of type PhysicalComponent(!Node), false otherwise
   * @return
   */
  private boolean areAllElementPCParts(List<EObject> elements_p) {
    boolean flag = false;
    for (EObject object : elements_p) {
      if (!(object instanceof Part)) {
        return false;
      } else if (object instanceof Part) {
        Part part = (Part) object;
        AbstractType abstractType = part.getAbstractType();
        if ((abstractType != null) && (abstractType instanceof PhysicalComponent)) {
          flag = true;
        }
      }
    }

    return flag;
  }

  /**
   * return true if all he element in the list are of type ExchangeItem, false otherwise
   * @return
   */
  private boolean areAllElementExchangeItems(List<EObject> elements_p) {
    boolean flag = false;
    for (EObject object : elements_p) {
      if (!(object instanceof ExchangeItem)) {
        return false;
      } else if (object instanceof ExchangeItem) {
        flag = true;
      }
    }

    return flag;
  }

  /**
   * allow multiple selection or not
   * @param elements_p list of element selected
   * @return
   */
  public boolean isMultiSelection(List<EObject> elements_p) {
    if (isValidSelection(elements_p)) {
      EObject object = elements_p.get(0);
      if (object instanceof ExchangeItem) {
        return true;
      }
    }
    return false;
  }

  /**
   * decides weather list of elements are valid or not for allocation action
   * @param elements_p
   * @return
   */
  public boolean isValidSelection(List<EObject> elements_p) {
    // functions
    if (areAllElementFunctions(elements_p)) {
      return true;
    }
    // exchangeItem
    else if (areAllElementExchangeItems(elements_p)) {
      return true;
    }
    // behaviourPcs
    else if (areAllElementPCParts(elements_p)) {
      return true;
    }
    // functionalExchanges
    else if (WizardActionHelper.areAllElementFunctionalExchange(elements_p)) {
      return true;
    }
    // componentExchanges
    else if (WizardActionHelper.areAllElementsComponentExchanges(elements_p)) {
      return true;
    }

    return false;
  }

  /**
   * @param element_p
   * @param allocationSelectionType_p
   * @return
   */
  private String createDataMessage(List<EObject> element_p, AllocationSelectionType allocationSelectionType_p) {

    String result = ICommonConstants.EMPTY_STRING;
    // plural string s or not
    String plural = getPluralString(element_p, false);
    // list of elements as String
    String elementsNames = getElementsNames(element_p);
    if (allocationSelectionType_p == AllocationSelectionType.FUNCTION_ALLOCATION) {
      result = NLS.bind(Messages.Allocation_Functions_Selection_Message, new String[] { plural, elementsNames });
    } else if (allocationSelectionType_p == AllocationSelectionType.EXCHANGE_ITEM_ALLOCATION) {
      result = NLS.bind(Messages.Allocation_ExchangeItems_Selection_Message, new String[] { plural, elementsNames });
    } else if (allocationSelectionType_p == AllocationSelectionType.PHYSICAL_PART_DEPLOYMENT) {
      result = NLS.bind(Messages.Allocation_PhysicalComponents_Selection_Message, new String[] { plural, elementsNames });
    } else if (allocationSelectionType_p == AllocationSelectionType.FUNCTIONAL_EXCHANGE_ALLOCATION) {
      result = NLS.bind(Messages.Allocation_FunctionalExchagnes_Selection_Message, new String[] { plural, elementsNames });
    } else if (allocationSelectionType_p == AllocationSelectionType.COMPONENT_EXCHANGE_ALLOCATION) {
      result = NLS.bind(Messages.Allocation_ComponentExchagnes_Selection_Message, new String[] { plural, elementsNames });
    }

    return result;
  }

  /**
   * @param dataMessage_p the dataMessage to set
   */
  private void setDataMessage(String dataMessage_p) {
    _dataMessage = dataMessage_p;
  }

  /**
   * @return the dataMessage
   */
  public String getDataMessage() {
    return _dataMessage;
  }

  /**
   * @return the dataType
   */
  public AllocationSelectionType getAllocationType() {
    return _allocationType;
  }

  /**
   * @param dataType_p the dataType to set
   */
  private void setAllocationType(AllocationSelectionType dataType_p) {
    _allocationType = dataType_p;
  }

  public String getPluralString(List<EObject> element_p, boolean withVerb_p) {
    String plural = "s"; //$NON-NLS-1$
    return element_p.size() > 1 ? plural : ICommonConstants.EMPTY_STRING;
  }

  private String getElementsNames(List<EObject> element_p) {
    String elementsNames = ICommonConstants.EMPTY_STRING;
    Iterator<EObject> iterator = element_p.iterator();
    while (iterator.hasNext()) {
      EObject eObject = iterator.next();
      if (eObject instanceof NamedElement) {
        elementsNames = elementsNames + ((NamedElement) eObject).getName();
      }
      if (iterator.hasNext()) {
        elementsNames = elementsNames + ICommonConstants.COMMA_CHARACTER + ICommonConstants.WHITE_SPACE_CHARACTER;
      }
    }
    return elementsNames;
  }

  /**
   * @return the sourceDataVoid
   */
  public boolean isSourceDataVoid() {
    return _sourceDataVoid;
  }

  /**
   * @param sourceDataVoid_p the sourceDataVoid to set
   */
  private void setSourceDataVoid(boolean sourceDataVoid_p) {
    _sourceDataVoid = sourceDataVoid_p;
  }
}
