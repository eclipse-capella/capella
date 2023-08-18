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
package org.polarsys.capella.core.data.oa.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl;
import org.polarsys.capella.core.data.oa.Concept;
import org.polarsys.capella.core.data.oa.ConceptCompliance;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalCapability;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Concept Compliance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.ConceptComplianceImpl#getComplyWithConcept <em>Comply With Concept</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.ConceptComplianceImpl#getCompliantCapability <em>Compliant Capability</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConceptComplianceImpl extends RelationshipImpl implements ConceptCompliance {

	/**
   * The cached value of the '{@link #getComplyWithConcept() <em>Comply With Concept</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getComplyWithConcept()
   * @generated
   * @ordered
   */
	protected Concept complyWithConcept;





	/**
   * The cached value of the '{@link #getCompliantCapability() <em>Compliant Capability</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getCompliantCapability()
   * @generated
   * @ordered
   */
	protected OperationalCapability compliantCapability;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ConceptComplianceImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.CONCEPT_COMPLIANCE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Concept getComplyWithConcept() {

    if (complyWithConcept != null && complyWithConcept.eIsProxy()) {
      InternalEObject oldComplyWithConcept = (InternalEObject)complyWithConcept;
      complyWithConcept = (Concept)eResolveProxy(oldComplyWithConcept);
      if (complyWithConcept != oldComplyWithConcept) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, OaPackage.CONCEPT_COMPLIANCE__COMPLY_WITH_CONCEPT, oldComplyWithConcept, complyWithConcept));
      }
    }
    return complyWithConcept;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Concept basicGetComplyWithConcept() {

    return complyWithConcept;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setComplyWithConcept(Concept newComplyWithConcept) {

    Concept oldComplyWithConcept = complyWithConcept;
    complyWithConcept = newComplyWithConcept;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.CONCEPT_COMPLIANCE__COMPLY_WITH_CONCEPT, oldComplyWithConcept, complyWithConcept));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public OperationalCapability getCompliantCapability() {

    if (compliantCapability != null && compliantCapability.eIsProxy()) {
      InternalEObject oldCompliantCapability = (InternalEObject)compliantCapability;
      compliantCapability = (OperationalCapability)eResolveProxy(oldCompliantCapability);
      if (compliantCapability != oldCompliantCapability) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, OaPackage.CONCEPT_COMPLIANCE__COMPLIANT_CAPABILITY, oldCompliantCapability, compliantCapability));
      }
    }
    return compliantCapability;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public OperationalCapability basicGetCompliantCapability() {

    return compliantCapability;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setCompliantCapability(OperationalCapability newCompliantCapability) {

    OperationalCapability oldCompliantCapability = compliantCapability;
    compliantCapability = newCompliantCapability;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.CONCEPT_COMPLIANCE__COMPLIANT_CAPABILITY, oldCompliantCapability, compliantCapability));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case OaPackage.CONCEPT_COMPLIANCE__COMPLY_WITH_CONCEPT:
        if (resolve) return getComplyWithConcept();
        return basicGetComplyWithConcept();
      case OaPackage.CONCEPT_COMPLIANCE__COMPLIANT_CAPABILITY:
        if (resolve) return getCompliantCapability();
        return basicGetCompliantCapability();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case OaPackage.CONCEPT_COMPLIANCE__COMPLY_WITH_CONCEPT:
          setComplyWithConcept((Concept)newValue);
        return;
      case OaPackage.CONCEPT_COMPLIANCE__COMPLIANT_CAPABILITY:
          setCompliantCapability((OperationalCapability)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eUnset(int featureID) {
    switch (featureID) {
      case OaPackage.CONCEPT_COMPLIANCE__COMPLY_WITH_CONCEPT:
        setComplyWithConcept((Concept)null);
        return;
      case OaPackage.CONCEPT_COMPLIANCE__COMPLIANT_CAPABILITY:
        setCompliantCapability((OperationalCapability)null);
        return;
    }
    super.eUnset(featureID);
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean eIsSet(int featureID) {
    switch (featureID) {
      case OaPackage.CONCEPT_COMPLIANCE__COMPLY_WITH_CONCEPT:
        return complyWithConcept != null;
      case OaPackage.CONCEPT_COMPLIANCE__COMPLIANT_CAPABILITY:
        return compliantCapability != null;
    }
    return super.eIsSet(featureID);
  }



} //ConceptComplianceImpl