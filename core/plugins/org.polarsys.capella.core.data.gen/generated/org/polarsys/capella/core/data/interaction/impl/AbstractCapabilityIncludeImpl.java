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
package org.polarsys.capella.core.data.interaction.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Capability Include</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityIncludeImpl#getIncluded <em>Included</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityIncludeImpl#getInclusion <em>Inclusion</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AbstractCapabilityIncludeImpl extends RelationshipImpl implements AbstractCapabilityInclude {

	/**
	 * The cached value of the '{@link #getIncluded() <em>Included</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncluded()
	 * @generated
	 * @ordered
	 */
	protected AbstractCapability included;








	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractCapabilityIncludeImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InteractionPackage.Literals.ABSTRACT_CAPABILITY_INCLUDE;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractCapability getIncluded() {

		if (included != null && included.eIsProxy()) {
			InternalEObject oldIncluded = (InternalEObject)included;
			included = (AbstractCapability)eResolveProxy(oldIncluded);
			if (included != oldIncluded) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUDED, oldIncluded, included));
			}
		}
		return included;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractCapability basicGetIncluded() {

		return included;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetIncluded(AbstractCapability newIncluded, NotificationChain msgs) {

		AbstractCapability oldIncluded = included;
		included = newIncluded;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUDED, oldIncluded, newIncluded);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setIncluded(AbstractCapability newIncluded) {

		if (newIncluded != included) {
			NotificationChain msgs = null;
			if (included != null)
				msgs = ((InternalEObject)included).eInverseRemove(this, InteractionPackage.ABSTRACT_CAPABILITY__INCLUDING, AbstractCapability.class, msgs);
			if (newIncluded != null)
				msgs = ((InternalEObject)newIncluded).eInverseAdd(this, InteractionPackage.ABSTRACT_CAPABILITY__INCLUDING, AbstractCapability.class, msgs);
			msgs = basicSetIncluded(newIncluded, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUDED, newIncluded, newIncluded));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractCapability getInclusion() {

		if (eContainerFeatureID() != InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUSION) return null;
		return (AbstractCapability)eContainer();
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractCapability basicGetInclusion() {

		if (eContainerFeatureID() != InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUSION) return null;
		return (AbstractCapability)eInternalContainer();
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetInclusion(AbstractCapability newInclusion, NotificationChain msgs) {

		msgs = eBasicSetContainer((InternalEObject)newInclusion, InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUSION, msgs);

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setInclusion(AbstractCapability newInclusion) {

		if (newInclusion != eInternalContainer() || (eContainerFeatureID() != InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUSION && newInclusion != null)) {
			if (EcoreUtil.isAncestor(this, newInclusion))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInclusion != null)
				msgs = ((InternalEObject)newInclusion).eInverseAdd(this, InteractionPackage.ABSTRACT_CAPABILITY__INCLUDES, AbstractCapability.class, msgs);
			msgs = basicSetInclusion(newInclusion, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUSION, newInclusion, newInclusion));

	}




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUDED:
				if (included != null)
					msgs = ((InternalEObject)included).eInverseRemove(this, InteractionPackage.ABSTRACT_CAPABILITY__INCLUDING, AbstractCapability.class, msgs);
				return basicSetIncluded((AbstractCapability)otherEnd, msgs);
			case InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUSION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetInclusion((AbstractCapability)otherEnd, msgs);
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
			case InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUDED:
				return basicSetIncluded(null, msgs);
			case InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUSION:
				return basicSetInclusion(null, msgs);
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
			case InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUSION:
				return eInternalContainer().eInverseRemove(this, InteractionPackage.ABSTRACT_CAPABILITY__INCLUDES, AbstractCapability.class, msgs);
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
			case InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUDED:
				if (resolve) return getIncluded();
				return basicGetIncluded();
			case InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUSION:
				if (resolve) return getInclusion();
				return basicGetInclusion();
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
			case InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUDED:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractCapability) {
				// end-extension-code
					setIncluded((AbstractCapability)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUSION:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractCapability) {
				// end-extension-code
					setInclusion((AbstractCapability)newValue);
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
			case InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUDED:
				setIncluded((AbstractCapability)null);
				return;
			case InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUSION:
				setInclusion((AbstractCapability)null);
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
			case InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUDED:
				return included != null;
			case InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE__INCLUSION:
				return basicGetInclusion() != null;
		}
		return super.eIsSet(featureID);
	}



} //AbstractCapabilityIncludeImpl