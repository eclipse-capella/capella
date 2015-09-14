/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.oa.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.oa.AbstractConceptItem;
import org.polarsys.capella.core.data.oa.Concept;
import org.polarsys.capella.core.data.oa.ItemInConcept;
import org.polarsys.capella.core.data.oa.OaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Item In Concept</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.ItemInConceptImpl#getConcept <em>Concept</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.ItemInConceptImpl#getItem <em>Item</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ItemInConceptImpl extends NamedElementImpl implements ItemInConcept {

	/**
	 * The cached value of the '{@link #getConcept() <em>Concept</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConcept()
	 * @generated
	 * @ordered
	 */
	protected Concept concept;





	/**
	 * The cached value of the '{@link #getItem() <em>Item</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getItem()
	 * @generated
	 * @ordered
	 */
	protected AbstractConceptItem item;




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ItemInConceptImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OaPackage.Literals.ITEM_IN_CONCEPT;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public Concept getConcept() {

		if (concept != null && concept.eIsProxy()) {
			InternalEObject oldConcept = (InternalEObject)concept;
			concept = (Concept)eResolveProxy(oldConcept);
			if (concept != oldConcept) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OaPackage.ITEM_IN_CONCEPT__CONCEPT, oldConcept, concept));
			}
		}
		return concept;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public Concept basicGetConcept() {

		return concept;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setConcept(Concept newConcept) {

		Concept oldConcept = concept;
		concept = newConcept;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.ITEM_IN_CONCEPT__CONCEPT, oldConcept, concept));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractConceptItem getItem() {

		if (item != null && item.eIsProxy()) {
			InternalEObject oldItem = (InternalEObject)item;
			item = (AbstractConceptItem)eResolveProxy(oldItem);
			if (item != oldItem) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OaPackage.ITEM_IN_CONCEPT__ITEM, oldItem, item));
			}
		}
		return item;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractConceptItem basicGetItem() {

		return item;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setItem(AbstractConceptItem newItem) {

		AbstractConceptItem oldItem = item;
		item = newItem;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.ITEM_IN_CONCEPT__ITEM, oldItem, item));

	}




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OaPackage.ITEM_IN_CONCEPT__CONCEPT:
				if (resolve) return getConcept();
				return basicGetConcept();
			case OaPackage.ITEM_IN_CONCEPT__ITEM:
				if (resolve) return getItem();
				return basicGetItem();
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
			case OaPackage.ITEM_IN_CONCEPT__CONCEPT:
				// begin-extension-code
				if (newValue == null || newValue instanceof Concept) {
				// end-extension-code
					setConcept((Concept)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case OaPackage.ITEM_IN_CONCEPT__ITEM:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractConceptItem) {
				// end-extension-code
					setItem((AbstractConceptItem)newValue);
				// begin-extension-code
				}
				// end-extension-code
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
			case OaPackage.ITEM_IN_CONCEPT__CONCEPT:
				setConcept((Concept)null);
				return;
			case OaPackage.ITEM_IN_CONCEPT__ITEM:
				setItem((AbstractConceptItem)null);
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
			case OaPackage.ITEM_IN_CONCEPT__CONCEPT:
				return concept != null;
			case OaPackage.ITEM_IN_CONCEPT__ITEM:
				return item != null;
		}
		return super.eIsSet(featureID);
	}



} //ItemInConceptImpl