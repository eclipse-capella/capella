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
package org.polarsys.capella.core.data.interaction.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Capability Generalization</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityGeneralizationImpl#getSuper <em>Super</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityGeneralizationImpl#getSub <em>Sub</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AbstractCapabilityGeneralizationImpl extends RelationshipImpl implements AbstractCapabilityGeneralization {

	/**
	 * The cached value of the '{@link #getSuper() <em>Super</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuper()
	 * @generated
	 * @ordered
	 */
	protected AbstractCapability super_;








	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractCapabilityGeneralizationImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractCapability getSuper() {

		if (super_ != null && super_.eIsProxy()) {
			InternalEObject oldSuper = (InternalEObject)super_;
			super_ = (AbstractCapability)eResolveProxy(oldSuper);
			if (super_ != oldSuper) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUPER, oldSuper, super_));
			}
		}
		return super_;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractCapability basicGetSuper() {

		return super_;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetSuper(AbstractCapability newSuper, NotificationChain msgs) {

		AbstractCapability oldSuper = super_;
		super_ = newSuper;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUPER, oldSuper, newSuper);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setSuper(AbstractCapability newSuper) {

		if (newSuper != super_) {
			NotificationChain msgs = null;
			if (super_ != null)
				msgs = ((InternalEObject)super_).eInverseRemove(this, InteractionPackage.ABSTRACT_CAPABILITY__SUB_GENERALIZATIONS, AbstractCapability.class, msgs);
			if (newSuper != null)
				msgs = ((InternalEObject)newSuper).eInverseAdd(this, InteractionPackage.ABSTRACT_CAPABILITY__SUB_GENERALIZATIONS, AbstractCapability.class, msgs);
			msgs = basicSetSuper(newSuper, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUPER, newSuper, newSuper));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractCapability getSub() {

		if (eContainerFeatureID() != InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUB) return null;
		return (AbstractCapability)eContainer();
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractCapability basicGetSub() {

		if (eContainerFeatureID() != InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUB) return null;
		return (AbstractCapability)eInternalContainer();
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetSub(AbstractCapability newSub, NotificationChain msgs) {

		msgs = eBasicSetContainer((InternalEObject)newSub, InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUB, msgs);

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setSub(AbstractCapability newSub) {

		if (newSub != eInternalContainer() || (eContainerFeatureID() != InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUB && newSub != null)) {
			if (EcoreUtil.isAncestor(this, newSub))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSub != null)
				msgs = ((InternalEObject)newSub).eInverseAdd(this, InteractionPackage.ABSTRACT_CAPABILITY__SUPER_GENERALIZATIONS, AbstractCapability.class, msgs);
			msgs = basicSetSub(newSub, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUB, newSub, newSub));

	}




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUPER:
				if (super_ != null)
					msgs = ((InternalEObject)super_).eInverseRemove(this, InteractionPackage.ABSTRACT_CAPABILITY__SUB_GENERALIZATIONS, AbstractCapability.class, msgs);
				return basicSetSuper((AbstractCapability)otherEnd, msgs);
			case InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUB:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSub((AbstractCapability)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUPER:
				return basicSetSuper(null, msgs);
			case InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUB:
				return basicSetSub(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUB:
				return eInternalContainer().eInverseRemove(this, InteractionPackage.ABSTRACT_CAPABILITY__SUPER_GENERALIZATIONS, AbstractCapability.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUPER:
				if (resolve) return getSuper();
				return basicGetSuper();
			case InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUB:
				if (resolve) return getSub();
				return basicGetSub();
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
			case InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUPER:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractCapability) {
				// end-extension-code
					setSuper((AbstractCapability)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUB:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractCapability) {
				// end-extension-code
					setSub((AbstractCapability)newValue);
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
			case InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUPER:
				setSuper((AbstractCapability)null);
				return;
			case InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUB:
				setSub((AbstractCapability)null);
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
			case InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUPER:
				return super_ != null;
			case InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION__SUB:
				return basicGetSub() != null;
		}
		return super.eIsSet(featureID);
	}



} //AbstractCapabilityGeneralizationImpl