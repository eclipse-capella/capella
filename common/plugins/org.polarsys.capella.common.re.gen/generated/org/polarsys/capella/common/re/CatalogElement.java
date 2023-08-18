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
package org.polarsys.capella.common.re;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Catalog Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElement#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElement#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElement#getEnvironment <em>Environment</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElement#getSuffix <em>Suffix</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElement#getPurpose <em>Purpose</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElement#isReadOnly <em>Read Only</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElement#getVersion <em>Version</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElement#getTags <em>Tags</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElement#getOrigin <em>Origin</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElement#getCurrentCompliancy <em>Current Compliancy</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElement#getDefaultReplicaCompliancy <em>Default Replica Compliancy</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElement#getOwnedLinks <em>Owned Links</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElement#getReferencedElements <em>Referenced Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElement#getReplicatedElements <em>Replicated Elements</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.re.RePackage#getCatalogElement()
 * @model annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */

public interface CatalogElement extends ReDescriptionElement, ReElementContainer {





	/**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The default value is <code>"REC"</code>.
   * The literals are from the enumeration {@link org.polarsys.capella.common.re.CatalogElementKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.common.re.CatalogElementKind
   * @see #setKind(CatalogElementKind)
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElement_Kind()
   * @model default="REC"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	CatalogElementKind getKind();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.re.CatalogElement#getKind <em>Kind</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.common.re.CatalogElementKind
   * @see #getKind()
   * @generated
   */

	void setKind(CatalogElementKind value);







	/**
   * Returns the value of the '<em><b>Author</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Author</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Author</em>' attribute.
   * @see #setAuthor(String)
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElement_Author()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getAuthor();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.re.CatalogElement#getAuthor <em>Author</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Author</em>' attribute.
   * @see #getAuthor()
   * @generated
   */

	void setAuthor(String value);







	/**
   * Returns the value of the '<em><b>Environment</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Environment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Environment</em>' attribute.
   * @see #setEnvironment(String)
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElement_Environment()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getEnvironment();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.re.CatalogElement#getEnvironment <em>Environment</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Environment</em>' attribute.
   * @see #getEnvironment()
   * @generated
   */

	void setEnvironment(String value);







	/**
   * Returns the value of the '<em><b>Suffix</b></em>' attribute.

   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Suffix</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Suffix</em>' attribute.
   * @see #setSuffix(String)
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElement_Suffix()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

  String getSuffix();




  /**
   * Sets the value of the '{@link org.polarsys.capella.common.re.CatalogElement#getSuffix <em>Suffix</em>}' attribute.

   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Suffix</em>' attribute.
   * @see #getSuffix()
   * @generated
   */

  void setSuffix(String value);




  /**
   * Returns the value of the '<em><b>Purpose</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Purpose</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Purpose</em>' attribute.
   * @see #setPurpose(String)
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElement_Purpose()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getPurpose();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.re.CatalogElement#getPurpose <em>Purpose</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Purpose</em>' attribute.
   * @see #getPurpose()
   * @generated
   */

	void setPurpose(String value);




	/**
   * Returns the value of the '<em><b>Read Only</b></em>' attribute.
   * The default value is <code>"false"</code>.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Read Only</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Read Only</em>' attribute.
   * @see #setReadOnly(boolean)
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElement_ReadOnly()
   * @model default="false"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isReadOnly();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.re.CatalogElement#isReadOnly <em>Read Only</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Read Only</em>' attribute.
   * @see #isReadOnly()
   * @generated
   */

	void setReadOnly(boolean value);




	/**
   * Returns the value of the '<em><b>Version</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Version</em>' attribute.
   * @see #setVersion(String)
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElement_Version()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getVersion();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.re.CatalogElement#getVersion <em>Version</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Version</em>' attribute.
   * @see #getVersion()
   * @generated
   */

	void setVersion(String value);




	/**
   * Returns the value of the '<em><b>Tags</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tags</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Tags</em>' attribute list.
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElement_Tags()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<String> getTags();







	/**
   * Returns the value of the '<em><b>Origin</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Origin</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Origin</em>' reference.
   * @see #setOrigin(CatalogElement)
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElement_Origin()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	CatalogElement getOrigin();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.re.CatalogElement#getOrigin <em>Origin</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Origin</em>' reference.
   * @see #getOrigin()
   * @generated
   */

	void setOrigin(CatalogElement value);







	/**
   * Returns the value of the '<em><b>Current Compliancy</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Current Compliancy</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Current Compliancy</em>' reference.
   * @see #setCurrentCompliancy(CompliancyDefinition)
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElement_CurrentCompliancy()
   * @model
   * @generated
   */

	CompliancyDefinition getCurrentCompliancy();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.re.CatalogElement#getCurrentCompliancy <em>Current Compliancy</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Current Compliancy</em>' reference.
   * @see #getCurrentCompliancy()
   * @generated
   */

	void setCurrentCompliancy(CompliancyDefinition value);







	/**
   * Returns the value of the '<em><b>Default Replica Compliancy</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Replica Compliancy</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Default Replica Compliancy</em>' reference.
   * @see #setDefaultReplicaCompliancy(CompliancyDefinition)
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElement_DefaultReplicaCompliancy()
   * @model
   * @generated
   */

	CompliancyDefinition getDefaultReplicaCompliancy();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.re.CatalogElement#getDefaultReplicaCompliancy <em>Default Replica Compliancy</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Default Replica Compliancy</em>' reference.
   * @see #getDefaultReplicaCompliancy()
   * @generated
   */

	void setDefaultReplicaCompliancy(CompliancyDefinition value);







	/**
   * Returns the value of the '<em><b>Owned Links</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.common.re.CatalogElementLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Links</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Links</em>' containment reference list.
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElement_OwnedLinks()
   * @model containment="true" resolveProxies="true"
   * @generated
   */

	EList<CatalogElementLink> getOwnedLinks();




	/**
   * Returns the value of the '<em><b>Referenced Elements</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Referenced Elements</em>' reference list.
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElement_ReferencedElements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedLinks.target'"
   * @generated
   */

	EList<EObject> getReferencedElements();




	/**
   * Returns the value of the '<em><b>Replicated Elements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.re.CatalogElement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replicated Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Replicated Elements</em>' reference list.
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElement_ReplicatedElements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='origin'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='retrieve all referencing elements which have the current element as origin'"
   * @generated
   */

	EList<CatalogElement> getReplicatedElements();





} // CatalogElement
