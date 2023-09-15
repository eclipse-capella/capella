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
package org.polarsys.capella.core.data.sharedmodel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.sharedmodel.*;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelFactory;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SharedmodelFactoryImpl extends EFactoryImpl implements SharedmodelFactory {
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static SharedmodelFactory init() {
    try {
      SharedmodelFactory theSharedmodelFactory = (SharedmodelFactory)EPackage.Registry.INSTANCE.getEFactory(SharedmodelPackage.eNS_URI);
      if (theSharedmodelFactory != null) {
        return theSharedmodelFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new SharedmodelFactoryImpl();
  }

	/**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public SharedmodelFactoryImpl() {
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
      case SharedmodelPackage.SHARED_PKG: return createSharedPkg();
      case SharedmodelPackage.GENERIC_PKG: return createGenericPkg();
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
	public SharedPkg createSharedPkg() {
    SharedPkgImpl sharedPkg = new SharedPkgImpl();
    //begin-capella-code
    sharedPkg.setId(IdGenerator.createId());
    //end-capella-code
    return sharedPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public GenericPkg createGenericPkg() {
    GenericPkgImpl genericPkg = new GenericPkgImpl();
    //begin-capella-code
    genericPkg.setId(IdGenerator.createId());
    //end-capella-code
    return genericPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public SharedmodelPackage getSharedmodelPackage() {
    return (SharedmodelPackage)getEPackage();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	@Deprecated
	public static SharedmodelPackage getPackage() {
    return SharedmodelPackage.eINSTANCE;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public SharedPkg createSharedPkg(String name_p) {
    SharedPkg sharedPkg = createSharedPkg();
    sharedPkg.setName(name_p);	  
    return sharedPkg;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public GenericPkg createGenericPkg(String name_p) {
    GenericPkg genericPkg = createGenericPkg();
    genericPkg.setName(name_p);	  
    return genericPkg;
  }

	//begin-capella-code

	//end-capella-code
} //SharedmodelFactoryImpl
