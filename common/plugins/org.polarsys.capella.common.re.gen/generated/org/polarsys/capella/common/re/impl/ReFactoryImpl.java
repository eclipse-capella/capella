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
package org.polarsys.capella.common.re.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.common.re.*;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.CompliancyDefinition;
import org.polarsys.capella.common.re.CompliancyDefinitionPkg;
import org.polarsys.capella.common.re.GroupingElementPkg;
import org.polarsys.capella.common.re.ReFactory;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.RecCatalog;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ReFactoryImpl extends EFactoryImpl implements ReFactory {
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static ReFactory init() {
    try {
      ReFactory theReFactory = (ReFactory)EPackage.Registry.INSTANCE.getEFactory(RePackage.eNS_URI);
      if (theReFactory != null) {
        return theReFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ReFactoryImpl();
  }

	/**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ReFactoryImpl() {
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
      case RePackage.CATALOG_ELEMENT_PKG: return createCatalogElementPkg();
      case RePackage.REC_CATALOG: return createRecCatalog();
      case RePackage.GROUPING_ELEMENT_PKG: return createGroupingElementPkg();
      case RePackage.CATALOG_ELEMENT_LINK: return createCatalogElementLink();
      case RePackage.CATALOG_ELEMENT: return createCatalogElement();
      case RePackage.COMPLIANCY_DEFINITION_PKG: return createCompliancyDefinitionPkg();
      case RePackage.COMPLIANCY_DEFINITION: return createCompliancyDefinition();
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
      case RePackage.CATALOG_ELEMENT_KIND:
        return createCatalogElementKindFromString(eDataType, initialValue);
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
      case RePackage.CATALOG_ELEMENT_KIND:
        return convertCatalogElementKindToString(eDataType, instanceValue);
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
	public RecCatalog createRecCatalog() {
    RecCatalogImpl recCatalog = new RecCatalogImpl();
    //begin-capella-code
    //end-capella-code
    return recCatalog;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public GroupingElementPkg createGroupingElementPkg() {
    GroupingElementPkgImpl groupingElementPkg = new GroupingElementPkgImpl();
    //begin-capella-code
    //end-capella-code
    return groupingElementPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CatalogElementPkg createCatalogElementPkg() {
    CatalogElementPkgImpl catalogElementPkg = new CatalogElementPkgImpl();
    //begin-capella-code
    //end-capella-code
    return catalogElementPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CatalogElementLink createCatalogElementLink() {
    CatalogElementLinkImpl catalogElementLink = new CatalogElementLinkImpl();
    //begin-capella-code
    //end-capella-code
    return catalogElementLink;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CatalogElement createCatalogElement() {
    CatalogElementImpl catalogElement = new CatalogElementImpl();
    //begin-capella-code
    //end-capella-code
    return catalogElement;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CompliancyDefinitionPkg createCompliancyDefinitionPkg() {
    CompliancyDefinitionPkgImpl compliancyDefinitionPkg = new CompliancyDefinitionPkgImpl();
    //begin-capella-code
    //end-capella-code
    return compliancyDefinitionPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CompliancyDefinition createCompliancyDefinition() {
    CompliancyDefinitionImpl compliancyDefinition = new CompliancyDefinitionImpl();
    //begin-capella-code
    //end-capella-code
    return compliancyDefinition;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CatalogElementKind createCatalogElementKindFromString(EDataType eDataType, String initialValue) {
    CatalogElementKind result = CatalogElementKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertCatalogElementKindToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public RePackage getRePackage() {
    return (RePackage)getEPackage();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	@Deprecated
	public static RePackage getPackage() {
    return RePackage.eINSTANCE;
  }

	//begin-capella-code
	
	//end-capella-code
} //ReFactoryImpl