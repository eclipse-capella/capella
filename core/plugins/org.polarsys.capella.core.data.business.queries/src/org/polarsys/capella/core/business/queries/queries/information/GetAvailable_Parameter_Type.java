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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.OperationExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Parameter_Type extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<EObject> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  /**
   * <p>
   * Gets All the Classes ,Collections, Signals and DataTypes contained by the Data Package (and all of its
   * sub-packages) of the current Element's parent (can be a Component, a Component Architecture Decomposition package,
   * or a Component Architecture root package). All the Classes, Signals and DataTypes contained by the Data Packages
   * (and all of its sub-packages) of the current Element's parents hierarchy according to layer visibility and multiple
   * decomposition rules. All the Interfaces contained by the Interface Package (and all of its subpackages) of the
   * current Element's parent (can be a Component, a Component Architecture Decomposition package, or a Component
   * Architecture root package). All the Interfaces contained by the Interface Packages (and all of its subpackages) of
   * the current Element's parents hierarchy according to layer visibility and multiple decomposition rules. All the
   * Classes, Signals and DataTypes contained by the Data Package (and all of its sub-packages) of the Shared Assets
   * Package. All the Interfaces contained by the Interface Package (and all of its subpackages) of the Shared Assets
   * Package.
   * </p>
   * <p>
   * Refer MQRY_Parameter_Type_1
   * </p>
   * 
   * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
   */
  public List<EObject> getAvailableElements(CapellaElement element) {
    Classifier classifierToRemove = null;
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
    List<EObject> availableElements = new ArrayList<EObject>();
    boolean isParameterFromSharedPkg = false;
    if (null == systemEngineering) {
      SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
      if (sharedPkg != null) {
        for (ReuseLink link : sharedPkg.getReuseLinks()) {
          if (SystemEngineeringExt.getSystemEngineering(link) != null) {
            systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
            isParameterFromSharedPkg = true;
            break;
          }
        }
      }
      if (systemEngineering == null)
        return availableElements;
    }
    if (element instanceof Parameter) {
      Parameter parameter = (Parameter) element;
      EObject eContainer = parameter.eContainer();
      if (eContainer != null && eContainer instanceof Service) {
        EObject eContainer2 = eContainer.eContainer();
        if (eContainer2 != null && eContainer2 instanceof Classifier) {
          classifierToRemove = (Classifier) eContainer2;
        }
      }
      boolean isFromService = (parameter.eContainer() != null && parameter.eContainer() instanceof Service);
      if (!isParameterFromSharedPkg) {
        availableElements.addAll(getRule_MQRY_Parameter_Type_11(parameter));
        availableElements.addAll(getRule_MQRY_Parameter_Type_12(parameter));
        if (!isFromService) {
          availableElements.addAll(getRule_MQRY_Parameter_Type_13(parameter));
          availableElements.addAll(getRule_MQRY_Parameter_Type_14(parameter));
        }
      }
    }
    availableElements = ListExt.removeDuplicates(availableElements);
    availableElements.remove(classifierToRemove);
    return availableElements;
  }

  private List<EObject> getRule_MQRY_Parameter_Type_11(Parameter currenParameter) {
    List<EObject> availableElements = new ArrayList<EObject>(1);
    EObject container = currenParameter.eContainer();
    if (container instanceof Operation) {
      Operation operation = (Operation) container;
      AbstractType type = currenParameter.getType();
      BlockArchitecture compArch = OperationExt.getRootBlockArchitecture(operation);
      availableElements.addAll(getElementsFromBlockArchitecture(compArch, type));
      Component comp = OperationExt.getRootComponent(operation);
      if (comp != null) {
        DataPkg dataPkg = comp.getOwnedDataPkg();
        if (dataPkg != null) {
          for (EObject element : DataPkgExt.getAllTypesFromDataPkgForPropsNParams(dataPkg)) {
            availableElements.add(element);
          }
        }
      }
    }
    return availableElements;
  }

  private List<EObject> getRule_MQRY_Parameter_Type_12(Parameter currentParameter) {
    List<EObject> availableElements = new ArrayList<EObject>(1);
    EObject container = currentParameter.eContainer();
    if (container instanceof Operation) {
      Operation operation = (Operation) container;
      List<DataPkg> dataPkgList = OperationExt.getDataPkgsFromParentHierarchy(operation);
      for (DataPkg dataPkg : dataPkgList) {
        for (EObject element : DataPkgExt.getAllTypesFromDataPkgForPropsNParams(dataPkg)) {
          availableElements.add(element);
        }
      }
      availableElements.addAll(getRule_MQRY_Parameter_Type_12_1(currentParameter));
    }
    return availableElements;
  }

  private List<CapellaElement> getRule_MQRY_Parameter_Type_13(Parameter currentParameter) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    EObject container = currentParameter.eContainer();
    if (container instanceof Operation) {
      Operation operation = (Operation) container;
      BlockArchitecture compArch = OperationExt.getRootBlockArchitecture(operation);
      if (null != compArch) {
        for (CapellaElement element : InterfacePkgExt.getAllInterfaces(compArch.getOwnedInterfacePkg())) {
          availableElements.add(element);
        }
      } else {
        Component comp = OperationExt.getRootComponent(operation);
        if (comp != null) {
          for (CapellaElement element : InterfacePkgExt.getAllInterfaces(comp.getOwnedInterfacePkg())) {
            availableElements.add(element);
          }
        }
      }
    }
    return availableElements;
  }

  private List<CapellaElement> getRule_MQRY_Parameter_Type_14(Parameter currentParameter) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    EObject container = currentParameter.eContainer();
    if (container instanceof Operation) {
      Operation operation = (Operation) container;
      for (CapellaElement element : OperationExt.getOwnedInterfacesFromParentHierarchy(operation)) {
        availableElements.add(element);
      }
      availableElements.addAll(getRule_MQRY_Parameter_Type_14_1(currentParameter));
    }
    return availableElements;
  }

  /**
   * Get DataTypes from the parent Block Architecture
   * 
   * @param arch
   * @param type
   * @return
   */
  private List<EObject> getElementsFromBlockArchitecture(BlockArchitecture arch, AbstractType type) {
    List<EObject> availableElements = new ArrayList<EObject>(1);
    if (null != arch) {
      DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(arch);
      if (dataPkg != null) {
        for (EObject element : DataPkgExt.getAllTypesFromDataPkgForPropsNParams(dataPkg)) {
          availableElements.add(element);
        }
      }
    }
    return availableElements;
  }

  private List<EObject> getRule_MQRY_Parameter_Type_12_1(Parameter currentParameter) {
    List<EObject> availableElements = new ArrayList<EObject>(1);
    EObject container = currentParameter.eContainer();
    if (container instanceof Operation) {
      Operation operation = (Operation) container;
      AbstractType type = currentParameter.getType();
      BlockArchitecture arch = OperationExt.getRootBlockArchitecture(operation);
      SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries()
          .getSystemEngineering(currentParameter);
      OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(systemEngineering);
      if (null != oa) {
        availableElements.addAll(getElementsFromBlockArchitecture(oa, type));
      } else {
        SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
        availableElements.addAll(getElementsFromBlockArchitecture(ca, type));
      }
      if (null != arch) {
        if (null != oa && (arch instanceof LogicalArchitecture) || (arch instanceof PhysicalArchitecture)
            || (arch instanceof EPBSArchitecture)) {
          SystemAnalysis ctx = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
          availableElements.addAll(getElementsFromBlockArchitecture(ctx, type));
        }
        if ((arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
          LogicalArchitecture logArch = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering);
          availableElements.addAll(getElementsFromBlockArchitecture(logArch, type));
        }
        if ((arch instanceof EPBSArchitecture)) {
          PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering);
          availableElements.addAll(getElementsFromBlockArchitecture(physArch, type));
        }
      }
    }
    return availableElements;
  }

  private List<CapellaElement> getRule_MQRY_Parameter_Type_14_1(Parameter currentParameter) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    EObject container = currentParameter.eContainer();
    if (container instanceof Operation) {
      Operation operation = (Operation) container;
      BlockArchitecture arch = OperationExt.getRootBlockArchitecture(operation);
      SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries()
          .getSystemEngineering(currentParameter);
      OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(systemEngineering);
      if (null != oa) {
        for (CapellaElement element : InterfacePkgExt.getAllInterfaces(oa.getOwnedInterfacePkg())) {
          availableElements.add(element);
        }
      } else {
        SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
        if (null != ca) {
          for (CapellaElement element : InterfacePkgExt.getAllInterfaces(ca.getOwnedInterfacePkg())) {
            availableElements.add(element);
          }
        }
      }
      if (null != arch) {
        if (null != oa && (arch instanceof LogicalArchitecture) || (arch instanceof PhysicalArchitecture)
            || (arch instanceof EPBSArchitecture)) {
          SystemAnalysis ctx = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
          if (null != ctx) {
            for (CapellaElement element : InterfacePkgExt.getAllInterfaces(ctx.getOwnedInterfacePkg())) {
              availableElements.add(element);
            }
          }
        }
        if ((arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
          LogicalArchitecture logArch = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering);
          if (logArch != null) {
            for (CapellaElement element : InterfacePkgExt.getAllInterfaces(logArch.getOwnedInterfacePkg())) {
              availableElements.add(element);
            }
          }
        }
        if ((arch instanceof EPBSArchitecture)) {
          PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering);
          if (physArch != null) {
            for (CapellaElement element : InterfacePkgExt.getAllInterfaces(physArch.getOwnedInterfacePkg())) {
              availableElements.add(element);
            }
          }
        }
      }
    }
    return availableElements;
  }

}