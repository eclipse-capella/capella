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
package org.polarsys.capella.common.libraries;

import org.eclipse.emf.common.util.EList;

import org.polarsys.kitalpha.emde.model.ElementExtension;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Information</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.polarsys.capella.common.libraries.ModelInformation#getOwnedReferences <em>Owned References</em>}</li>
 *   <li>{@link org.polarsys.capella.common.libraries.ModelInformation#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.polarsys.capella.common.libraries.LibrariesPackage#getModelInformation()
 * @model annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/constraint ExtendedElement='http://www.polarsys.org/capella/core/modeller/0.8.0#//Project'"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/constraintMapping Mapping='platform:/plugin/org.polarsys.capella.core.data.gen/model/CapellaModeller.ecore#//Project'"
 * @generated
 */

public interface ModelInformation extends LibraryAbstractElement, ElementExtension {





	/**
	 * Returns the value of the '<em><b>Owned References</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.common.libraries.LibraryReference}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned References</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned References</em>' containment reference list.
	 * @see org.polarsys.capella.common.libraries.LibrariesPackage#getModelInformation_OwnedReferences()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */

	EList<LibraryReference> getOwnedReferences();







	/**
	 * Returns the value of the '<em><b>Version</b></em>' reference.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' reference.
	 * @see #setVersion(ModelVersion)
	 * @see org.polarsys.capella.common.libraries.LibrariesPackage#getModelInformation_Version()
	 * @model required="true"
	 * @generated
	 */

	ModelVersion getVersion();




	/**
	 * Sets the value of the '{@link org.polarsys.capella.common.libraries.ModelInformation#getVersion <em>Version</em>}' reference.

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' reference.
	 * @see #getVersion()
	 * @generated
	 */

	void setVersion(ModelVersion value);





} // ModelInformation
