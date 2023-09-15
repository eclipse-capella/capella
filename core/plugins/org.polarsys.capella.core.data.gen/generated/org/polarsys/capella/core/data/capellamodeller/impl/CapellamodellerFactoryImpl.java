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
package org.polarsys.capella.core.data.capellamodeller.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.capellamodeller.*;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerFactory;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.Folder;
import org.polarsys.capella.core.data.capellamodeller.Library;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineeringPkg;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CapellamodellerFactoryImpl extends EFactoryImpl implements CapellamodellerFactory {
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static CapellamodellerFactory init() {
    try {
      CapellamodellerFactory theCapellamodellerFactory = (CapellamodellerFactory)EPackage.Registry.INSTANCE.getEFactory(CapellamodellerPackage.eNS_URI);
      if (theCapellamodellerFactory != null) {
        return theCapellamodellerFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new CapellamodellerFactoryImpl();
  }

	/**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CapellamodellerFactoryImpl() {
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
      case CapellamodellerPackage.PROJECT: return createProject();
      case CapellamodellerPackage.FOLDER: return createFolder();
      case CapellamodellerPackage.SYSTEM_ENGINEERING: return createSystemEngineering();
      case CapellamodellerPackage.SYSTEM_ENGINEERING_PKG: return createSystemEngineeringPkg();
      case CapellamodellerPackage.LIBRARY: return createLibrary();
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
	public Project createProject() {
    ProjectImpl project = new ProjectImpl();
    //begin-capella-code
    project.setId(IdGenerator.createId());
    //end-capella-code
    return project;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Folder createFolder() {
    FolderImpl folder = new FolderImpl();
    //begin-capella-code
    folder.setId(IdGenerator.createId());
    //end-capella-code
    return folder;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public SystemEngineering createSystemEngineering() {
    SystemEngineeringImpl systemEngineering = new SystemEngineeringImpl();
    //begin-capella-code
    systemEngineering.setId(IdGenerator.createId());
    //end-capella-code
    return systemEngineering;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public SystemEngineeringPkg createSystemEngineeringPkg() {
    SystemEngineeringPkgImpl systemEngineeringPkg = new SystemEngineeringPkgImpl();
    //begin-capella-code
    systemEngineeringPkg.setId(IdGenerator.createId());
    //end-capella-code
    return systemEngineeringPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Library createLibrary() {
    LibraryImpl library = new LibraryImpl();
    //begin-capella-code
    library.setId(IdGenerator.createId());
    //end-capella-code
    return library;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CapellamodellerPackage getCapellamodellerPackage() {
    return (CapellamodellerPackage)getEPackage();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	@Deprecated
	public static CapellamodellerPackage getPackage() {
    return CapellamodellerPackage.eINSTANCE;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Project createProject(String name_p) {
    Project project = createProject();
    project.setName(name_p);	  
    return project;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Folder createFolder(String name_p) {
    Folder folder = createFolder();
    folder.setName(name_p);	  
    return folder;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public SystemEngineering createSystemEngineering(String name_p) {
    SystemEngineering systemEngineering = createSystemEngineering();
    systemEngineering.setName(name_p);	  
    return systemEngineering;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public SystemEngineeringPkg createSystemEngineeringPkg(String name_p) {
    SystemEngineeringPkg systemEngineeringPkg = createSystemEngineeringPkg();
    systemEngineeringPkg.setName(name_p);	  
    return systemEngineeringPkg;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Library createLibrary(String name_p) {
    Library library = createLibrary();
    library.setName(name_p);	  
    return library;
  }

	//begin-capella-code

	//end-capella-code
} //CapellamodellerFactoryImpl
