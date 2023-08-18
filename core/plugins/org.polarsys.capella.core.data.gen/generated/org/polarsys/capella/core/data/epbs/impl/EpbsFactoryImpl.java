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
package org.polarsys.capella.core.data.epbs.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.epbs.*;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemKind;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSArchitecturePkg;
import org.polarsys.capella.core.data.epbs.EpbsFactory;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.epbs.PhysicalArchitectureRealization;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EpbsFactoryImpl extends EFactoryImpl implements EpbsFactory {
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static EpbsFactory init() {
    try {
      EpbsFactory theEpbsFactory = (EpbsFactory)EPackage.Registry.INSTANCE.getEFactory(EpbsPackage.eNS_URI);
      if (theEpbsFactory != null) {
        return theEpbsFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new EpbsFactoryImpl();
  }

	/**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EpbsFactoryImpl() {
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
      case EpbsPackage.EPBS_ARCHITECTURE_PKG: return createEPBSArchitecturePkg();
      case EpbsPackage.EPBS_ARCHITECTURE: return createEPBSArchitecture();
      case EpbsPackage.CONFIGURATION_ITEM_PKG: return createConfigurationItemPkg();
      case EpbsPackage.CONFIGURATION_ITEM: return createConfigurationItem();
      case EpbsPackage.PHYSICAL_ARCHITECTURE_REALIZATION: return createPhysicalArchitectureRealization();
      case EpbsPackage.PHYSICAL_ARTIFACT_REALIZATION: return createPhysicalArtifactRealization();
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
	public Object createFromString(EDataType eDataType, String initialValue) {
    switch (eDataType.getClassifierID()) {
      case EpbsPackage.CONFIGURATION_ITEM_KIND:
        return createConfigurationItemKindFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
    switch (eDataType.getClassifierID()) {
      case EpbsPackage.CONFIGURATION_ITEM_KIND:
        return convertConfigurationItemKindToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EPBSArchitecturePkg createEPBSArchitecturePkg() {
    EPBSArchitecturePkgImpl epbsArchitecturePkg = new EPBSArchitecturePkgImpl();
    //begin-capella-code
    epbsArchitecturePkg.setId(IdGenerator.createId());
    //end-capella-code
    return epbsArchitecturePkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EPBSArchitecture createEPBSArchitecture() {
    EPBSArchitectureImpl epbsArchitecture = new EPBSArchitectureImpl();
    //begin-capella-code
    epbsArchitecture.setId(IdGenerator.createId());
    //end-capella-code
    return epbsArchitecture;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ConfigurationItemPkg createConfigurationItemPkg() {
    ConfigurationItemPkgImpl configurationItemPkg = new ConfigurationItemPkgImpl();
    //begin-capella-code
    configurationItemPkg.setId(IdGenerator.createId());
    //end-capella-code
    return configurationItemPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ConfigurationItem createConfigurationItem() {
    ConfigurationItemImpl configurationItem = new ConfigurationItemImpl();
    //begin-capella-code
    configurationItem.setId(IdGenerator.createId());
    //end-capella-code
    return configurationItem;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PhysicalArchitectureRealization createPhysicalArchitectureRealization() {
    PhysicalArchitectureRealizationImpl physicalArchitectureRealization = new PhysicalArchitectureRealizationImpl();
    //begin-capella-code
    physicalArchitectureRealization.setId(IdGenerator.createId());
    //end-capella-code
    return physicalArchitectureRealization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PhysicalArtifactRealization createPhysicalArtifactRealization() {
    PhysicalArtifactRealizationImpl physicalArtifactRealization = new PhysicalArtifactRealizationImpl();
    //begin-capella-code
    physicalArtifactRealization.setId(IdGenerator.createId());
    //end-capella-code
    return physicalArtifactRealization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ConfigurationItemKind createConfigurationItemKindFromString(EDataType eDataType, String initialValue) {
    ConfigurationItemKind result = ConfigurationItemKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertConfigurationItemKindToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EpbsPackage getEpbsPackage() {
    return (EpbsPackage)getEPackage();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	@Deprecated
	public static EpbsPackage getPackage() {
    return EpbsPackage.eINSTANCE;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public EPBSArchitecturePkg createEPBSArchitecturePkg(String name_p) {
    EPBSArchitecturePkg epbsArchitecturePkg = createEPBSArchitecturePkg();
    epbsArchitecturePkg.setName(name_p);	  
    return epbsArchitecturePkg;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public EPBSArchitecture createEPBSArchitecture(String name_p) {
    EPBSArchitecture epbsArchitecture = createEPBSArchitecture();
    epbsArchitecture.setName(name_p);	  
    return epbsArchitecture;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ConfigurationItemPkg createConfigurationItemPkg(String name_p) {
    ConfigurationItemPkg configurationItemPkg = createConfigurationItemPkg();
    configurationItemPkg.setName(name_p);	  
    return configurationItemPkg;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ConfigurationItem createConfigurationItem(String name_p) {
    ConfigurationItem configurationItem = createConfigurationItem();
    configurationItem.setName(name_p);	  
    return configurationItem;
  }

	//begin-capella-code

	//end-capella-code
} //EpbsFactoryImpl
