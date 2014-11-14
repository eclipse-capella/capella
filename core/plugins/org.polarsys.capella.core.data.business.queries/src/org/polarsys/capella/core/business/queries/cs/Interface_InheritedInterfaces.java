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
package org.polarsys.capella.core.business.queries.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.GenericPkgExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class Interface_InheritedInterfaces implements IBusinessQuery, RefactoredBusinessQuery {

  @Override
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    return RefactorDebugger.callAndTestQuery("GetAvailable_Interface_InheritedInterfaces__Lib", element_p, getOldAvailableElements(element_p), getEClass(),//$NON-NLS-1$
        getClass());
  }

  /**
   * <p>
   * Gets all the Interfaces contained by the Interface Package (and all of its subPackages) of the current Element's parents hierarchy.
   * </p>
   * <p>
   * Gets all the Interfaces contained by the Interface Package (and all of its subPackages) of the current Element's parent (can be a Component, a Block
   * Architecture root package or the SystemEngineering package).
   * </p>
   * <p>
   * All the Interfaces contained by the Interface Package (and all of its sub-packages) of the current Element's parents hierarchy.
   * </p>
   * <p>
   * All the Interfaces contained by the Shared Package (and all of its sub-packages).
   * </p>
   * <p>
   * Except the current Interface itself and interfaces in the inheritance hierarchy of the current Interface
   * </p>
   * <p>
   * Refer MQRY_Interface_Inherited_1
   * </p>
   * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
   */
  @Override
  public List<CapellaElement> getOldAvailableElements(CapellaElement element) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);

    boolean isInterfaceFromSharedPkg = false;

    if (null == systemEngineering) {
      SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
      for (ReuseLink link : sharedPkg.getReuseLinks()) {
        if (SystemEngineeringExt.getSystemEngineering(link) != null) {
          systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
          isInterfaceFromSharedPkg = true;
          break;
        }
      }
      if (systemEngineering == null) {
        return availableElements;
      }
    }

    if (element instanceof Interface) {
      if (!isInterfaceFromSharedPkg) {
        availableElements.addAll(getRule_MQRY_Interface_Inherited_11((Interface) element, systemEngineering));
        availableElements.addAll(getRule_MQRY_Interface_Inherited_12((Interface) element, systemEngineering));
        availableElements.addAll(getRule_MQRY_Interface_Inherited_13((Interface) element, systemEngineering));
      }
      availableElements.addAll(getRule_MQRY_Interface_Inherited_14((Interface) element, systemEngineering));
    }

    availableElements = ListExt.removeDuplicates(availableElements);
    availableElements.remove(element);

    return availableElements;
  }

  /**
   * All the Interfaces contained by the Interface Package (and all of its sub-packages) of the current Element.
   * @param currentInterface
   * @param systemEngineering
   * @return : List of Interfaces
   */
  private List<CapellaElement> getRule_MQRY_Interface_Inherited_11(Interface currentInterface, SystemEngineering systemEngineering) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    InterfacePkg interfacePkg = InterfaceExt.getRootOwnerInterfacePkg(currentInterface);
    if (interfacePkg != null) {
      for (Interface inter : InterfacePkgExt.getAllInterfaces(interfacePkg)) {
        if ((inter == null) || (inter.equals(currentInterface))) {
          continue;
        }
        if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentInterface).contains(inter)
            && !GeneralizableElementExt.getAllSuperGeneralizableElements(inter).contains(currentInterface)) {
          availableElements.add(inter);
        }
      }
    }

    return availableElements;
  }

  /**
   * All the Interfaces contained by the Interface Package (and all of its sub-packages) of the current Element's parent (can be a Component, a Block
   * Architecture Decomposition package, or a Block Architecture root package).
   * @param currentInterface
   * @param systemEngineering
   * @return : List of Interfaces
   */
  private List<CapellaElement> getRule_MQRY_Interface_Inherited_12(Interface currentInterface, SystemEngineering systemEngineering) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    InterfacePkg interfacePkg = InterfaceExt.getRootOwnerInterfacePkg(currentInterface);
    if (interfacePkg != null) {
      BlockArchitecture parentBlockArchitecture = InterfacePkgExt.getRootComponentArchitecture(interfacePkg);
      if (parentBlockArchitecture != null) {
        for (Interface inter : InterfacePkgExt.getAllInterfaces(parentBlockArchitecture.getOwnedInterfacePkg())) {
          if ((inter == null) || (inter.equals(currentInterface))) {
            continue;
          }
          if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentInterface).contains(inter)
              && !GeneralizableElementExt.getAllSuperGeneralizableElements(inter).contains(currentInterface)) {
            availableElements.add(inter);
          }
        }
      }
      Component parentComponent = InterfacePkgExt.getRootComponent(interfacePkg);
      if (parentComponent != null) {
        for (Interface inter : InterfacePkgExt.getAllInterfaces((parentComponent).getOwnedInterfacePkg())) {
          if ((inter == null) || (inter.equals(currentInterface))) {
            continue;
          }
          if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentInterface).contains(inter)
              && !GeneralizableElementExt.getAllSuperGeneralizableElements(inter).contains(currentInterface)) {
            availableElements.add(inter);
          }
        }
      }
    }

    return availableElements;
  }

  /**
   * All the Interfaces contained by the Interface Package (and all of its sub-packages) of the current Element's parents hierarchy.
   * @param currentInterface
   * @param systemEngineering
   * @return : List of Interfaces
   */
  private List<CapellaElement> getRule_MQRY_Interface_Inherited_13(Interface currentInterface, SystemEngineering systemEngineering) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    InterfacePkg interfacePkg = InterfaceExt.getRootOwnerInterfacePkg(currentInterface);
    for (Interface inter : InterfacePkgExt.getOwnedInterfacesFromParentHierarchy(interfacePkg)) {
      if ((inter == null) || (inter.equals(currentInterface))) {
        continue;
      }
      if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentInterface).contains(inter)
          && !GeneralizableElementExt.getAllSuperGeneralizableElements(inter).contains(currentInterface)) {
        availableElements.add(inter);
      }
    }

    return availableElements;
  }

  /**
   * All the Interfaces contained by the Shared Package (and all of its sub-packages).
   * @param currentInterface
   * @param systemEngineering
   * @return : List of Interfaces
   */
  private List<CapellaElement> getRule_MQRY_Interface_Inherited_14(Interface currentInterface, SystemEngineering systemEngineering) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(systemEngineering)) {
      GenericPkg pkg = sharedPkg.getOwnedGenericPkg();
      if (pkg != null) {
        for (Interface inter : GenericPkgExt.getAllInterfaces(pkg)) {
          if ((inter == null) || (inter.equals(currentInterface))) {
            continue;
          }
          if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentInterface).contains(inter)
              && !GeneralizableElementExt.getAllSuperGeneralizableElements(inter).contains(currentInterface)) {
            availableElements.add(inter);
          }
        }
      }
    }

    return availableElements;
  }

  /**
   * <p>
   * Gets all interfaces in the inheritance hierarchy of the current Interface; except the current Interface itself
   * </p>
   * <p>
   * Refer MQRY_Interface_Inherited_1
   * </p>
   * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
   *      boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

    if (null == systemEngineering) {
      return currentElements;
    }

    if (element_p instanceof Interface) {
      Interface currentInterface = (Interface) element_p;
      currentElements.addAll(currentInterface.getSuper());
      currentElements = ListExt.removeDuplicates(currentElements);
      currentElements.remove(currentInterface);
    }
    return currentElements;
  }

  public EClass getEClass() {
    return CsPackage.Literals.INTERFACE;
  }

  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS);
  }
}
