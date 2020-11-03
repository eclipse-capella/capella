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
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
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
import org.polarsys.capella.core.model.helpers.ComponentExt;
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
   * 
   * @param element
   * @param titleMessage
   * @return
   */
  public List<CapellaElement> getCorrespondingData(List<EObject> element) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    if (element == null) {
      return result;
    }

    if (areAllElementFunctions(element)) {
      // collect all non allocated functions from element
      //
      List<EObject> nonAllocatedFunctions = new ArrayList<EObject>(0);
      for (EObject object : element) {
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

    } else if (areAllElementExchangeItems(element)) {
      // check if any data available to work on or to apply action
      if (element.isEmpty()) {
        setSourceDataVoid(true);
      }
      // set data message
      setDataMessage(createDataMessage(element, AllocationSelectionType.EXCHANGE_ITEM_ALLOCATION));
      // set allocation type
      setAllocationType(AllocationSelectionType.EXCHANGE_ITEM_ALLOCATION);
      // collect all interfaces from all layers and return
      return getAllInterfacesFromAllLayers(element);

    } else if (WizardActionHelper.areAllElementFunctionalExchange(element)) {
      // collect all non allocated functions from element
      List<EObject> nonAllocatedFunExcs = new ArrayList<EObject>(0);
      for (EObject object : element) {
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

    } else if (WizardActionHelper.areAllElementsComponentExchanges(element)) {
      // collect all non allocated functions from element
      List<EObject> nonAllocatedCompExcs = new ArrayList<EObject>(0);
      for (EObject object : element) {
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

    } else if (areAllElementPCParts(element)) {
      // collect all non deployed physical components from element
      List<EObject> nonDeployedPCParts = new ArrayList<EObject>(0);
      for (EObject object : element) {
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
   * @param function
   */
  private List<CapellaElement> getAllComponentsFromCurrentLayers(List<EObject> element) {
    List<CapellaElement> temp = new ArrayList<CapellaElement>(0);
    List<CapellaElement> result = new ArrayList<CapellaElement>(0);
    if (!element.isEmpty() && (element.get(0) != null)) {
      // get root architecture
      BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(element.get(0));
      if (null == arch) {
        return result;
      }
      // add all components from current architecture to temp list
      temp.addAll(BlockArchitectureExt.getAllComponents(arch));
      // remove first component from temp list
      Component firstComponent = arch.getSystem();
      if (!(arch instanceof SystemAnalysis) && firstComponent != null) {
        temp.remove(firstComponent);
      }
      // filter ComponentContext from temp list... and add to result list
      for (CapellaElement capellaElement : temp) {
        if (isNotNodePhysicalComponent(capellaElement) || ComponentExt.isActor(capellaElement)) {
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
   * @param capellaElement
   * @return
   */
  private boolean isNotNodePhysicalComponent(CapellaElement capellaElement) {
    if (capellaElement instanceof PhysicalComponent) {
      PhysicalComponent comp = (PhysicalComponent) capellaElement;
      if (comp.getNature() == PhysicalComponentNature.NODE) {
        return false;
      }
    }
    return true;
  }

  /**
   * Return all the interfaces from all the layers
   * 
   * @param element
   * @return
   */
  private List<CapellaElement> getAllInterfacesFromAllLayers(List<EObject> element) {
    List<CapellaElement> result = new ArrayList<CapellaElement>(0);
    if (!element.isEmpty()) {
      SystemEngineering sysEng = SystemEngineeringExt.getSystemEngineering((CapellaElement) element.get(0));
      EList<ModellingArchitecture> architectures = sysEng.getOwnedArchitectures();
      for (ModellingArchitecture modellingArchitecture : architectures) {
        result.addAll(SystemEngineeringExt.getAllInterfaces(modellingArchitecture));
      }
    }
    return result;
  }

  /**
   * Return all parts from PC layer (except the root and )
   * 
   * @param nonDeployedPCParts
   * @return
   */
  private List<CapellaElement> getAllPartsFromPCLayers(List<EObject> nonDeployedPCParts) {
    List<CapellaElement> components = new ArrayList<CapellaElement>();
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    if (!nonDeployedPCParts.isEmpty()) {
      BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(nonDeployedPCParts.get(0));
      if ((null != arch) && (arch instanceof PhysicalArchitecture)) {
        components.addAll(BlockArchitectureExt.getAllComponents(arch));
        if (components.isEmpty()) {
          return components;
        }
        // remove first component
        Component firstComponent = arch.getSystem();
        if (firstComponent != null) {
          components.remove(firstComponent);
        }

        for (CapellaElement capellaElement : components) {
          if (capellaElement instanceof PhysicalComponent) {
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
    result.removeAll(nonDeployedPCParts);

    return result;
  }

  /**
   * @param nonAllocatedFunExcs
   * @return
   */
  private List<CapellaElement> getAvailableAllocatingComponentExchanges2(List<EObject> nonAllocatedFunExcs) {
    List<CapellaElement> result = new ArrayList<CapellaElement>(0);
    if (nonAllocatedFunExcs.isEmpty()) {
      return result;
    }

    EObject object = nonAllocatedFunExcs.get(0);
    if (null != object) {
      BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(object);
      if (null != arch) {
        for (ComponentExchange link : BlockArchitectureExt.getAllComponentExchanges(arch)) {
          // get availableComponentExhanges for current PhysicalLink
          IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(
              FaPackage.Literals.COMPONENT_EXCHANGE,
              FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS);
          List<EObject> compExchanges = query.getAvailableElements(link);
          for (EObject functionalExchange : compExchanges) {
            // if availableComponentExhanges for current PhysicalLink is one of the selected component exchange
            // add physical link
            if (nonAllocatedFunExcs.contains(functionalExchange)) {
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
   * 
   * @param nonAllocatedCompExcs
   * @return
   */
  private List<CapellaElement> getAvailableAllocatingPhysicalLinks(List<EObject> nonAllocatedCompExcs) {
    List<CapellaElement> result = new ArrayList<CapellaElement>(0);
    if (nonAllocatedCompExcs.isEmpty()) {
      return result;
    }

    EObject object = nonAllocatedCompExcs.get(0);
    if (null != object) {
      BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(object);
      if (arch instanceof PhysicalArchitecture) {
        for (PhysicalLink link : PhysicalArchitectureExt.getAllPhysicalLinks((PhysicalArchitecture) arch)) {
          // get availableComponentExhanges for current PhysicalLink
          IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(CsPackage.Literals.PHYSICAL_LINK,
              FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATOR__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS);
          List<EObject> compExchanges = query.getAvailableElements(link);
          for (EObject compExchange : compExchanges) {
            // if availableComponentExhanges for current PhysicalLink is one of the selected component exchange
            // add physical link
            if (nonAllocatedCompExcs.contains(compExchange)) {
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
   * 
   * @return
   */
  private boolean areAllElementFunctions(List<EObject> elements) {
    boolean flag = false;
    for (EObject object : elements) {
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
   * 
   * @return
   */
  private boolean areAllElementPCParts(List<EObject> elements) {
    boolean flag = false;
    for (EObject object : elements) {
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
   * 
   * @return
   */
  private boolean areAllElementExchangeItems(List<EObject> elements) {
    boolean flag = false;
    for (EObject object : elements) {
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
   * 
   * @param elements
   *          list of element selected
   * @return
   */
  public boolean isMultiSelection(List<EObject> elements) {
    if (isValidSelection(elements)) {
      EObject object = elements.get(0);
      if (object instanceof ExchangeItem) {
        return true;
      }
    }
    return false;
  }

  /**
   * decides weather list of elements are valid or not for allocation action
   * 
   * @param elements
   * @return
   */
  public boolean isValidSelection(List<EObject> elements) {
    // functions
    if (areAllElementFunctions(elements)) {
      return true;
    }
    // exchangeItem
    else if (areAllElementExchangeItems(elements)) {
      return true;
    }
    // behaviourPcs
    else if (areAllElementPCParts(elements)) {
      return true;
    }
    // functionalExchanges
    else if (WizardActionHelper.areAllElementFunctionalExchange(elements)) {
      return true;
    }
    // componentExchanges
    else if (WizardActionHelper.areAllElementsComponentExchanges(elements)) {
      return true;
    }

    return false;
  }

  /**
   * @param element
   * @param allocationSelectionType
   * @return
   */
  private String createDataMessage(List<EObject> element, AllocationSelectionType allocationSelectionType) {

    String result = ICommonConstants.EMPTY_STRING;
    // plural string s or not
    String plural = getPluralString(element, false);
    // list of elements as String
    String elementsNames = getElementsNames(element);
    if (allocationSelectionType == AllocationSelectionType.FUNCTION_ALLOCATION) {
      result = NLS.bind(Messages.Allocation_Functions_Selection_Message, new String[] { plural, elementsNames });
    } else if (allocationSelectionType == AllocationSelectionType.EXCHANGE_ITEM_ALLOCATION) {
      result = NLS.bind(Messages.Allocation_ExchangeItems_Selection_Message, new String[] { plural, elementsNames });
    } else if (allocationSelectionType == AllocationSelectionType.PHYSICAL_PART_DEPLOYMENT) {
      result = NLS.bind(Messages.Allocation_PhysicalComponents_Selection_Message,
          new String[] { plural, elementsNames });
    } else if (allocationSelectionType == AllocationSelectionType.FUNCTIONAL_EXCHANGE_ALLOCATION) {
      result = NLS.bind(Messages.Allocation_FunctionalExchagnes_Selection_Message,
          new String[] { plural, elementsNames });
    } else if (allocationSelectionType == AllocationSelectionType.COMPONENT_EXCHANGE_ALLOCATION) {
      result = NLS.bind(Messages.Allocation_ComponentExchagnes_Selection_Message,
          new String[] { plural, elementsNames });
    }

    return result;
  }

  /**
   * @param dataMessage
   *          the dataMessage to set
   */
  private void setDataMessage(String dataMessage) {
    _dataMessage = dataMessage;
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
   * @param dataType
   *          the dataType to set
   */
  private void setAllocationType(AllocationSelectionType dataType) {
    _allocationType = dataType;
  }

  public String getPluralString(List<EObject> element, boolean withVerb) {
    String plural = "s"; //$NON-NLS-1$
    return element.size() > 1 ? plural : ICommonConstants.EMPTY_STRING;
  }

  private String getElementsNames(List<EObject> element) {
    String elementsNames = ICommonConstants.EMPTY_STRING;
    Iterator<EObject> iterator = element.iterator();
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
   * @param sourceDataVoid
   *          the sourceDataVoid to set
   */
  private void setSourceDataVoid(boolean sourceDataVoid) {
    _sourceDataVoid = sourceDataVoid;
  }
}
