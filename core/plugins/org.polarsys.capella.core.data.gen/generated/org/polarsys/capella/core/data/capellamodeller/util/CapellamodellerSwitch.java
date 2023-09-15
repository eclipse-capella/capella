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
package org.polarsys.capella.core.data.capellamodeller.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacore.AbstractModellingStructure;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.ReuserStructure;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellamodeller.*;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.Folder;
import org.polarsys.capella.core.data.capellamodeller.Library;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineeringPkg;
import org.polarsys.kitalpha.emde.model.Element;
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
 * @see org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage
 * @generated
 */
public class CapellamodellerSwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static CapellamodellerPackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CapellamodellerSwitch() {
    if (modelPackage == null) {
      modelPackage = CapellamodellerPackage.eINSTANCE;
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
      case CapellamodellerPackage.PROJECT: {
        Project project = (Project)theEObject;
        T result = caseProject(project);
        if (result == null) result = caseStructure(project);
        if (result == null) result = caseNamespace(project);
        if (result == null) result = caseNamedElement(project);
        if (result == null) result = caseAbstractNamedElement(project);
        if (result == null) result = caseCapellaElement(project);
        if (result == null) result = caseTraceableElement(project);
        if (result == null) result = casePublishableElement(project);
        if (result == null) result = caseModelElement(project);
        if (result == null) result = caseExtensibleElement(project);
        if (result == null) result = caseElement(project);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellamodellerPackage.FOLDER: {
        Folder folder = (Folder)theEObject;
        T result = caseFolder(folder);
        if (result == null) result = caseStructure(folder);
        if (result == null) result = caseNamespace(folder);
        if (result == null) result = caseNamedElement(folder);
        if (result == null) result = caseAbstractNamedElement(folder);
        if (result == null) result = caseCapellaElement(folder);
        if (result == null) result = caseTraceableElement(folder);
        if (result == null) result = casePublishableElement(folder);
        if (result == null) result = caseModelElement(folder);
        if (result == null) result = caseExtensibleElement(folder);
        if (result == null) result = caseElement(folder);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellamodellerPackage.MODEL_ROOT: {
        ModelRoot modelRoot = (ModelRoot)theEObject;
        T result = caseModelRoot(modelRoot);
        if (result == null) result = caseCapellaElement(modelRoot);
        if (result == null) result = caseTraceableElement(modelRoot);
        if (result == null) result = casePublishableElement(modelRoot);
        if (result == null) result = caseModelElement(modelRoot);
        if (result == null) result = caseExtensibleElement(modelRoot);
        if (result == null) result = caseElement(modelRoot);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellamodellerPackage.SYSTEM_ENGINEERING: {
        SystemEngineering systemEngineering = (SystemEngineering)theEObject;
        T result = caseSystemEngineering(systemEngineering);
        if (result == null) result = caseAbstractModellingStructure(systemEngineering);
        if (result == null) result = caseModelRoot(systemEngineering);
        if (result == null) result = caseReuserStructure(systemEngineering);
        if (result == null) result = caseStructure(systemEngineering);
        if (result == null) result = caseNamespace(systemEngineering);
        if (result == null) result = caseNamedElement(systemEngineering);
        if (result == null) result = caseAbstractNamedElement(systemEngineering);
        if (result == null) result = caseCapellaElement(systemEngineering);
        if (result == null) result = caseTraceableElement(systemEngineering);
        if (result == null) result = casePublishableElement(systemEngineering);
        if (result == null) result = caseModelElement(systemEngineering);
        if (result == null) result = caseExtensibleElement(systemEngineering);
        if (result == null) result = caseElement(systemEngineering);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellamodellerPackage.SYSTEM_ENGINEERING_PKG: {
        SystemEngineeringPkg systemEngineeringPkg = (SystemEngineeringPkg)theEObject;
        T result = caseSystemEngineeringPkg(systemEngineeringPkg);
        if (result == null) result = caseStructure(systemEngineeringPkg);
        if (result == null) result = caseModelRoot(systemEngineeringPkg);
        if (result == null) result = caseNamespace(systemEngineeringPkg);
        if (result == null) result = caseNamedElement(systemEngineeringPkg);
        if (result == null) result = caseAbstractNamedElement(systemEngineeringPkg);
        if (result == null) result = caseCapellaElement(systemEngineeringPkg);
        if (result == null) result = caseTraceableElement(systemEngineeringPkg);
        if (result == null) result = casePublishableElement(systemEngineeringPkg);
        if (result == null) result = caseModelElement(systemEngineeringPkg);
        if (result == null) result = caseExtensibleElement(systemEngineeringPkg);
        if (result == null) result = caseElement(systemEngineeringPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellamodellerPackage.LIBRARY: {
        Library library = (Library)theEObject;
        T result = caseLibrary(library);
        if (result == null) result = caseProject(library);
        if (result == null) result = caseStructure(library);
        if (result == null) result = caseNamespace(library);
        if (result == null) result = caseNamedElement(library);
        if (result == null) result = caseAbstractNamedElement(library);
        if (result == null) result = caseCapellaElement(library);
        if (result == null) result = caseTraceableElement(library);
        if (result == null) result = casePublishableElement(library);
        if (result == null) result = caseModelElement(library);
        if (result == null) result = caseExtensibleElement(library);
        if (result == null) result = caseElement(library);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Project</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Project</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseProject(Project object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Folder</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Folder</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFolder(Folder object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Model Root</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Root</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseModelRoot(ModelRoot object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>System Engineering</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>System Engineering</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseSystemEngineering(SystemEngineering object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>System Engineering Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>System Engineering Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseSystemEngineeringPkg(SystemEngineeringPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Library</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Library</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLibrary(Library object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseModelElement(ModelElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Named Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Named Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractNamedElement(AbstractNamedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Traceable Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Traceable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTraceableElement(TraceableElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Publishable Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Publishable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePublishableElement(PublishableElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Capella Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Capella Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCapellaElement(CapellaElement object) {
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
	public T caseNamedElement(NamedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Namespace</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Namespace</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNamespace(Namespace object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Structure</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Structure</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStructure(Structure object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Reuser Structure</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Reuser Structure</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseReuserStructure(ReuserStructure object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Modelling Structure</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Modelling Structure</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractModellingStructure(AbstractModellingStructure object) {
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

} //CapellamodellerSwitch
