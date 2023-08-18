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
package org.polarsys.capella.common.re.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.re.*;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.CompliancyDefinition;
import org.polarsys.capella.common.re.CompliancyDefinitionPkg;
import org.polarsys.capella.common.re.GroupingElementPkg;
import org.polarsys.capella.common.re.ReAbstractElement;
import org.polarsys.capella.common.re.ReDescriptionElement;
import org.polarsys.capella.common.re.ReElementContainer;
import org.polarsys.capella.common.re.ReNamedElement;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.RecCatalog;
import org.polarsys.kitalpha.emde.model.Element;
import org.polarsys.kitalpha.emde.model.ElementExtension;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.common.re.RePackage
 * @generated
 */
public class ReSwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static RePackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ReSwitch() {
    if (modelPackage == null) {
      modelPackage = RePackage.eINSTANCE;
    }
  }

	/**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
    return ePackage == modelPackage;
  }

	/**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
      case RePackage.RE_ABSTRACT_ELEMENT: {
        ReAbstractElement reAbstractElement = (ReAbstractElement)theEObject;
        T result = caseReAbstractElement(reAbstractElement);
        if (result == null) result = caseExtensibleElement(reAbstractElement);
        if (result == null) result = caseElement(reAbstractElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case RePackage.RE_NAMED_ELEMENT: {
        ReNamedElement reNamedElement = (ReNamedElement)theEObject;
        T result = caseReNamedElement(reNamedElement);
        if (result == null) result = caseReAbstractElement(reNamedElement);
        if (result == null) result = caseExtensibleElement(reNamedElement);
        if (result == null) result = caseElement(reNamedElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case RePackage.RE_DESCRIPTION_ELEMENT: {
        ReDescriptionElement reDescriptionElement = (ReDescriptionElement)theEObject;
        T result = caseReDescriptionElement(reDescriptionElement);
        if (result == null) result = caseReNamedElement(reDescriptionElement);
        if (result == null) result = caseReAbstractElement(reDescriptionElement);
        if (result == null) result = caseExtensibleElement(reDescriptionElement);
        if (result == null) result = caseElement(reDescriptionElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case RePackage.RE_ELEMENT_CONTAINER: {
        ReElementContainer reElementContainer = (ReElementContainer)theEObject;
        T result = caseReElementContainer(reElementContainer);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case RePackage.CATALOG_ELEMENT_PKG: {
        CatalogElementPkg catalogElementPkg = (CatalogElementPkg)theEObject;
        T result = caseCatalogElementPkg(catalogElementPkg);
        if (result == null) result = caseReNamedElement(catalogElementPkg);
        if (result == null) result = caseReElementContainer(catalogElementPkg);
        if (result == null) result = caseReAbstractElement(catalogElementPkg);
        if (result == null) result = caseExtensibleElement(catalogElementPkg);
        if (result == null) result = caseElement(catalogElementPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case RePackage.REC_CATALOG: {
        RecCatalog recCatalog = (RecCatalog)theEObject;
        T result = caseRecCatalog(recCatalog);
        if (result == null) result = caseCatalogElementPkg(recCatalog);
        if (result == null) result = caseElementExtension(recCatalog);
        if (result == null) result = caseReNamedElement(recCatalog);
        if (result == null) result = caseReElementContainer(recCatalog);
        if (result == null) result = caseReAbstractElement(recCatalog);
        if (result == null) result = caseExtensibleElement(recCatalog);
        if (result == null) result = caseElement(recCatalog);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case RePackage.GROUPING_ELEMENT_PKG: {
        GroupingElementPkg groupingElementPkg = (GroupingElementPkg)theEObject;
        T result = caseGroupingElementPkg(groupingElementPkg);
        if (result == null) result = caseCatalogElementPkg(groupingElementPkg);
        if (result == null) result = caseElementExtension(groupingElementPkg);
        if (result == null) result = caseReNamedElement(groupingElementPkg);
        if (result == null) result = caseReElementContainer(groupingElementPkg);
        if (result == null) result = caseReAbstractElement(groupingElementPkg);
        if (result == null) result = caseExtensibleElement(groupingElementPkg);
        if (result == null) result = caseElement(groupingElementPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case RePackage.CATALOG_ELEMENT_LINK: {
        CatalogElementLink catalogElementLink = (CatalogElementLink)theEObject;
        T result = caseCatalogElementLink(catalogElementLink);
        if (result == null) result = caseReAbstractElement(catalogElementLink);
        if (result == null) result = caseExtensibleElement(catalogElementLink);
        if (result == null) result = caseElement(catalogElementLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case RePackage.CATALOG_ELEMENT: {
        CatalogElement catalogElement = (CatalogElement)theEObject;
        T result = caseCatalogElement(catalogElement);
        if (result == null) result = caseReDescriptionElement(catalogElement);
        if (result == null) result = caseReElementContainer(catalogElement);
        if (result == null) result = caseReNamedElement(catalogElement);
        if (result == null) result = caseReAbstractElement(catalogElement);
        if (result == null) result = caseExtensibleElement(catalogElement);
        if (result == null) result = caseElement(catalogElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case RePackage.COMPLIANCY_DEFINITION_PKG: {
        CompliancyDefinitionPkg compliancyDefinitionPkg = (CompliancyDefinitionPkg)theEObject;
        T result = caseCompliancyDefinitionPkg(compliancyDefinitionPkg);
        if (result == null) result = caseReNamedElement(compliancyDefinitionPkg);
        if (result == null) result = caseReAbstractElement(compliancyDefinitionPkg);
        if (result == null) result = caseExtensibleElement(compliancyDefinitionPkg);
        if (result == null) result = caseElement(compliancyDefinitionPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case RePackage.COMPLIANCY_DEFINITION: {
        CompliancyDefinition compliancyDefinition = (CompliancyDefinition)theEObject;
        T result = caseCompliancyDefinition(compliancyDefinition);
        if (result == null) result = caseReDescriptionElement(compliancyDefinition);
        if (result == null) result = caseReNamedElement(compliancyDefinition);
        if (result == null) result = caseReAbstractElement(compliancyDefinition);
        if (result == null) result = caseExtensibleElement(compliancyDefinition);
        if (result == null) result = caseElement(compliancyDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseReAbstractElement(ReAbstractElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseReNamedElement(ReNamedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Description Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Description Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseReDescriptionElement(ReDescriptionElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Element Container</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseReElementContainer(ReElementContainer object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Rec Catalog</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Rec Catalog</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseRecCatalog(RecCatalog object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Grouping Element Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Grouping Element Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseGroupingElementPkg(GroupingElementPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Catalog Element Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Catalog Element Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCatalogElementPkg(CatalogElementPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Catalog Element Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Catalog Element Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCatalogElementLink(CatalogElementLink object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Catalog Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Catalog Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCatalogElement(CatalogElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Compliancy Definition Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Compliancy Definition Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCompliancyDefinitionPkg(CompliancyDefinitionPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Compliancy Definition</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Compliancy Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCompliancyDefinition(CompliancyDefinition object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseElement(Element object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExtensibleElement(ExtensibleElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Element Extension</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element Extension</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseElementExtension(ElementExtension object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
	@Override
	public T defaultCase(EObject object) {
    return null;
  }

} //ReSwitch
