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
package org.polarsys.capella.core.data.cs.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.cs.*;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalLinkRealization;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.cs.PhysicalPathRealization;
import org.polarsys.capella.core.data.cs.PhysicalPathReference;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.cs.PhysicalPortRealization;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CsFactoryImpl extends EFactoryImpl implements CsFactory {
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static CsFactory init() {
    try {
      CsFactory theCsFactory = (CsFactory)EPackage.Registry.INSTANCE.getEFactory(CsPackage.eNS_URI);
      if (theCsFactory != null) {
        return theCsFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new CsFactoryImpl();
  }

	/**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CsFactoryImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
      case CsPackage.PART: return createPart();
      case CsPackage.COMPONENT_REALIZATION: return createComponentRealization();
      case CsPackage.INTERFACE_PKG: return createInterfacePkg();
      case CsPackage.INTERFACE: return createInterface();
      case CsPackage.INTERFACE_IMPLEMENTATION: return createInterfaceImplementation();
      case CsPackage.INTERFACE_USE: return createInterfaceUse();
      case CsPackage.EXCHANGE_ITEM_ALLOCATION: return createExchangeItemAllocation();
      case CsPackage.PHYSICAL_LINK: return createPhysicalLink();
      case CsPackage.PHYSICAL_LINK_CATEGORY: return createPhysicalLinkCategory();
      case CsPackage.PHYSICAL_LINK_END: return createPhysicalLinkEnd();
      case CsPackage.PHYSICAL_LINK_REALIZATION: return createPhysicalLinkRealization();
      case CsPackage.PHYSICAL_PATH: return createPhysicalPath();
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT: return createPhysicalPathInvolvement();
      case CsPackage.PHYSICAL_PATH_REFERENCE: return createPhysicalPathReference();
      case CsPackage.PHYSICAL_PATH_REALIZATION: return createPhysicalPathRealization();
      case CsPackage.PHYSICAL_PORT: return createPhysicalPort();
      case CsPackage.PHYSICAL_PORT_REALIZATION: return createPhysicalPortRealization();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Part createPart() {
    PartImpl part = new PartImpl();
    //begin-capella-code
    part.setId(IdGenerator.createId());
    //end-capella-code
    return part;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ComponentRealization createComponentRealization() {
    ComponentRealizationImpl componentRealization = new ComponentRealizationImpl();
    //begin-capella-code
    componentRealization.setId(IdGenerator.createId());
    //end-capella-code
    return componentRealization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public InterfacePkg createInterfacePkg() {
    InterfacePkgImpl interfacePkg = new InterfacePkgImpl();
    //begin-capella-code
    interfacePkg.setId(IdGenerator.createId());
    //end-capella-code
    return interfacePkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Interface createInterface() {
    InterfaceImpl interface_ = new InterfaceImpl();
    //begin-capella-code
    interface_.setId(IdGenerator.createId());
    //end-capella-code
    return interface_;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public InterfaceImplementation createInterfaceImplementation() {
    InterfaceImplementationImpl interfaceImplementation = new InterfaceImplementationImpl();
    //begin-capella-code
    interfaceImplementation.setId(IdGenerator.createId());
    //end-capella-code
    return interfaceImplementation;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public InterfaceUse createInterfaceUse() {
    InterfaceUseImpl interfaceUse = new InterfaceUseImpl();
    //begin-capella-code
    interfaceUse.setId(IdGenerator.createId());
    //end-capella-code
    return interfaceUse;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ExchangeItemAllocation createExchangeItemAllocation() {
    ExchangeItemAllocationImpl exchangeItemAllocation = new ExchangeItemAllocationImpl();
    //begin-capella-code
    exchangeItemAllocation.setId(IdGenerator.createId());
    //end-capella-code
    return exchangeItemAllocation;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PhysicalLink createPhysicalLink() {
    PhysicalLinkImpl physicalLink = new PhysicalLinkImpl();
    //begin-capella-code
    physicalLink.setId(IdGenerator.createId());
    //end-capella-code
    return physicalLink;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PhysicalLinkCategory createPhysicalLinkCategory() {
    PhysicalLinkCategoryImpl physicalLinkCategory = new PhysicalLinkCategoryImpl();
    //begin-capella-code
    physicalLinkCategory.setId(IdGenerator.createId());
    //end-capella-code
    return physicalLinkCategory;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PhysicalLinkEnd createPhysicalLinkEnd() {
    PhysicalLinkEndImpl physicalLinkEnd = new PhysicalLinkEndImpl();
    //begin-capella-code
    physicalLinkEnd.setId(IdGenerator.createId());
    //end-capella-code
    return physicalLinkEnd;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PhysicalLinkRealization createPhysicalLinkRealization() {
    PhysicalLinkRealizationImpl physicalLinkRealization = new PhysicalLinkRealizationImpl();
    //begin-capella-code
    physicalLinkRealization.setId(IdGenerator.createId());
    //end-capella-code
    return physicalLinkRealization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PhysicalPath createPhysicalPath() {
    PhysicalPathImpl physicalPath = new PhysicalPathImpl();
    //begin-capella-code
    physicalPath.setId(IdGenerator.createId());
    //end-capella-code
    return physicalPath;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PhysicalPathInvolvement createPhysicalPathInvolvement() {
    PhysicalPathInvolvementImpl physicalPathInvolvement = new PhysicalPathInvolvementImpl();
    //begin-capella-code
    physicalPathInvolvement.setId(IdGenerator.createId());
    //end-capella-code
    return physicalPathInvolvement;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PhysicalPathReference createPhysicalPathReference() {
    PhysicalPathReferenceImpl physicalPathReference = new PhysicalPathReferenceImpl();
    //begin-capella-code
    physicalPathReference.setId(IdGenerator.createId());
    //end-capella-code
    return physicalPathReference;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PhysicalPathRealization createPhysicalPathRealization() {
    PhysicalPathRealizationImpl physicalPathRealization = new PhysicalPathRealizationImpl();
    //begin-capella-code
    physicalPathRealization.setId(IdGenerator.createId());
    //end-capella-code
    return physicalPathRealization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PhysicalPort createPhysicalPort() {
    PhysicalPortImpl physicalPort = new PhysicalPortImpl();
    //begin-capella-code
    physicalPort.setId(IdGenerator.createId());
    //end-capella-code
    return physicalPort;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PhysicalPortRealization createPhysicalPortRealization() {
    PhysicalPortRealizationImpl physicalPortRealization = new PhysicalPortRealizationImpl();
    //begin-capella-code
    physicalPortRealization.setId(IdGenerator.createId());
    //end-capella-code
    return physicalPortRealization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CsPackage getCsPackage() {
    return (CsPackage)getEPackage();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	@Deprecated
	public static CsPackage getPackage() {
    return CsPackage.eINSTANCE;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Part createPart(String name_p) {
    Part part = createPart();
    part.setName(name_p);	  
    return part;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public InterfacePkg createInterfacePkg(String name_p) {
    InterfacePkg interfacePkg = createInterfacePkg();
    interfacePkg.setName(name_p);	  
    return interfacePkg;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Interface createInterface(String name_p) {
    Interface interface_ = createInterface();
    interface_.setName(name_p);	  
    return interface_;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ExchangeItemAllocation createExchangeItemAllocation(String name_p) {
    ExchangeItemAllocation exchangeItemAllocation = createExchangeItemAllocation();
    exchangeItemAllocation.setName(name_p);	  
    return exchangeItemAllocation;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PhysicalLink createPhysicalLink(String name_p) {
    PhysicalLink physicalLink = createPhysicalLink();
    physicalLink.setName(name_p);	  
    return physicalLink;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PhysicalLinkCategory createPhysicalLinkCategory(String name_p) {
    PhysicalLinkCategory physicalLinkCategory = createPhysicalLinkCategory();
    physicalLinkCategory.setName(name_p);	  
    return physicalLinkCategory;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PhysicalPath createPhysicalPath(String name_p) {
    PhysicalPath physicalPath = createPhysicalPath();
    physicalPath.setName(name_p);	  
    return physicalPath;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PhysicalPort createPhysicalPort(String name_p) {
    PhysicalPort physicalPort = createPhysicalPort();
    physicalPort.setName(name_p);	  
    return physicalPort;
  }

	//begin-capella-code

	//end-capella-code
} //CsFactoryImpl
