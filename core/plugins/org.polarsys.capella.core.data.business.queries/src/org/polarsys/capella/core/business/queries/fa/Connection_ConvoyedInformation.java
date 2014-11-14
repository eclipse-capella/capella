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
package org.polarsys.capella.core.business.queries.fa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.abstractqueries.ExtendedBusinessQueryForLib;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.AbstractExchangeItemPkgExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 * This query return the ExchageItem from current and all the above levels
 *
 */
public class Connection_ConvoyedInformation implements ExtendedBusinessQueryForLib, RefactoredBusinessQuery, IBusinessQuery {

  
  
  /**
   * return the ExchageItem from current and all the above levels
   * @param compExchange_p 
   * @param arch_p
   * @return
   */
  @SuppressWarnings("unchecked")
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p, CapellaElement compExchange_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    
    if (arch_p != null) {
      // retrieve exchangeItem from all the DataPkg of current level and all above level
      List<DataPkg> allDataPkgs = DataPkgExt.getAllDataPkgs(compExchange_p);
      for (DataPkg dataPkg : allDataPkgs) {
        availableElements.addAll((Collection<? extends CapellaElement>) AbstractExchangeItemPkgExt.getAllAbstractExchangeItems(dataPkg));        
      }
      
      // retrieve exchangeItem from all the InterfacePkg of current level and all above level
      List<InterfacePkg> allInterfacePkgs = InterfacePkgExt.getAllInterfacePkgs(compExchange_p);
      for (InterfacePkg interfacePkg : allInterfacePkgs) {
        availableElements.addAll((Collection<? extends CapellaElement>) AbstractExchangeItemPkgExt.getAllAbstractExchangeItems(interfacePkg));        
      }
    }
    
    if (compExchange_p instanceof ComponentExchange) {
      //remove AbstractExchangeItem related to current
      EList<AbstractExchangeItem> convoyedInformations = ((ComponentExchange)compExchange_p).getConvoyedInformations();
      for (AbstractExchangeItem abstractExchangeItem : convoyedInformations) {
        if (null != abstractExchangeItem) {
          availableElements.remove(abstractExchangeItem);
        }
      }
    }
   
    return availableElements;
  }
  
  /**
   * get the root architecture of the current
   * @param systemEng_p : SystemEngineering
   * @param connection_p : Connection
   * @return
   */
  private List<CapellaElement> getRule_MQRY_Connection_ExchangeItems_11(SystemEngineering systemEng_p, CapellaElement connection_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    BlockArchitecture arch = null;
    arch = SystemEngineeringExt.getRootBlockArchitecture(connection_p);
    
    availableElements.addAll(getElementsFromBlockArchitecture(arch,connection_p));

    return availableElements;
  }
  
  
  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

    if (null == systemEngineering) {
      return availableElements;
    }
    
    availableElements.addAll(getRule_MQRY_Connection_ExchangeItems_11(systemEngineering, element_p));
     
    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement, boolean)
   */
  public List<CapellaElement> getOldCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    if (element_p instanceof ComponentExchange) {
      ComponentExchange ele = (ComponentExchange) element_p;
      EList<AbstractExchangeItem> convoyedInformations = ele.getConvoyedInformations();
      for (AbstractExchangeItem abstractExchangeItem : convoyedInformations) {
        currentElements.add((CapellaElement) abstractExchangeItem);
      }
    }
    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return FaPackage.Literals.COMPONENT_EXCHANGE;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeature()
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS);
  }

@Override
public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
  return RefactorDebugger.callAndTestQuery("GetAvailable_Connection_ConvoyedInformation__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
}

@Override
public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
  return RefactorDebugger.callAndTestQuery("GetCurrent_Connection_ConvoyedInformation", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
}

}
