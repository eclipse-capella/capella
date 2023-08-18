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
package org.polarsys.capella.common.data.modellingcore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Publishable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.PublishableElement#isVisibleInDoc <em>Visible In Doc</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.PublishableElement#isVisibleInLM <em>Visible In LM</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getPublishableElement()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' type='n/a' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 * @generated
 */
public interface PublishableElement extends ModelElement {





	/**
   * Returns the value of the '<em><b>Visible In Doc</b></em>' attribute.
   * The default value is <code>"true"</code>.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visible In Doc</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Visible In Doc</em>' attribute.
   * @see #setVisibleInDoc(boolean)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getPublishableElement_VisibleInDoc()
   * @model default="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' type='n/a' comment/notes='none'"
   * @generated
   */

	boolean isVisibleInDoc();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.PublishableElement#isVisibleInDoc <em>Visible In Doc</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Visible In Doc</em>' attribute.
   * @see #isVisibleInDoc()
   * @generated
   */

	void setVisibleInDoc(boolean value);







	/**
   * Returns the value of the '<em><b>Visible In LM</b></em>' attribute.
   * The default value is <code>"true"</code>.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visible In LM</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Visible In LM</em>' attribute.
   * @see #setVisibleInLM(boolean)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getPublishableElement_VisibleInLM()
   * @model default="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' type='n/a' comment/notes='none'"
   * @generated
   */

	boolean isVisibleInLM();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.PublishableElement#isVisibleInLM <em>Visible In LM</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Visible In LM</em>' attribute.
   * @see #isVisibleInLM()
   * @generated
   */

	void setVisibleInLM(boolean value);





} // PublishableElement
