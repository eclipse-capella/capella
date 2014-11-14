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
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Capability Extend</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtendImpl#getExtended <em>Extended</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtendImpl#getExtension <em>Extension</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtendImpl#getExtensionLocation <em>Extension Location</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AbstractCapabilityExtendImpl extends RelationshipImpl implements AbstractCapabilityExtend {

	/**
	 * The cached value of the '{@link #getExtended() <em>Extended</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtended()
	 * @generated
	 * @ordered
	 */
	protected AbstractCapability extended;









	/**
	 * The cached value of the '{@link #getExtensionLocation() <em>Extension Location</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtensionLocation()
	 * @generated
	 * @ordered
	 */
	protected AbstractCapabilityExtensionPoint extensionLocation;




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractCapabilityExtendImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractCapability getExtended() {

		if (extended != null && extended.eIsProxy()) {
			InternalEObject oldExtended = (InternalEObject)extended;
			extended = (AbstractCapability)eResolveProxy(oldExtended);
			if (extended != oldExtended) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENDED, oldExtended, extended));
			}
		}
		return extended;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractCapability basicGetExtended() {

		return extended;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetExtended(AbstractCapability newExtended, NotificationChain msgs) {

		AbstractCapability oldExtended = extended;
		extended = newExtended;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENDED, oldExtended, newExtended);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setExtended(AbstractCapability newExtended) {

		if (newExtended != extended) {
			NotificationChain msgs = null;
			if (extended != null)
				msgs = ((InternalEObject)extended).eInverseRemove(this, InteractionPackage.ABSTRACT_CAPABILITY__EXTENDING, AbstractCapability.class, msgs);
			if (newExtended != null)
				msgs = ((InternalEObject)newExtended).eInverseAdd(this, InteractionPackage.ABSTRACT_CAPABILITY__EXTENDING, AbstractCapability.class, msgs);
			msgs = basicSetExtended(newExtended, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENDED, newExtended, newExtended));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractCapability getExtension() {

		if (eContainerFeatureID() != InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION) return null;
		return (AbstractCapability)eContainer();
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractCapability basicGetExtension() {

		if (eContainerFeatureID() != InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION) return null;
		return (AbstractCapability)eInternalContainer();
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetExtension(AbstractCapability newExtension, NotificationChain msgs) {

		msgs = eBasicSetContainer((InternalEObject)newExtension, InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION, msgs);

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setExtension(AbstractCapability newExtension) {

		if (newExtension != eInternalContainer() || (eContainerFeatureID() != InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION && newExtension != null)) {
			if (EcoreUtil.isAncestor(this, newExtension))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newExtension != null)
				msgs = ((InternalEObject)newExtension).eInverseAdd(this, InteractionPackage.ABSTRACT_CAPABILITY__EXTENDS, AbstractCapability.class, msgs);
			msgs = basicSetExtension(newExtension, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION, newExtension, newExtension));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractCapabilityExtensionPoint getExtensionLocation() {

		if (extensionLocation != null && extensionLocation.eIsProxy()) {
			InternalEObject oldExtensionLocation = (InternalEObject)extensionLocation;
			extensionLocation = (AbstractCapabilityExtensionPoint)eResolveProxy(oldExtensionLocation);
			if (extensionLocation != oldExtensionLocation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION, oldExtensionLocation, extensionLocation));
			}
		}
		return extensionLocation;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractCapabilityExtensionPoint basicGetExtensionLocation() {

		return extensionLocation;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetExtensionLocation(AbstractCapabilityExtensionPoint newExtensionLocation, NotificationChain msgs) {

		AbstractCapabilityExtensionPoint oldExtensionLocation = extensionLocation;
		extensionLocation = newExtensionLocation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION, oldExtensionLocation, newExtensionLocation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setExtensionLocation(AbstractCapabilityExtensionPoint newExtensionLocation) {

		if (newExtensionLocation != extensionLocation) {
			NotificationChain msgs = null;
			if (extensionLocation != null)
				msgs = ((InternalEObject)extensionLocation).eInverseRemove(this, InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__EXTEND_LINKS, AbstractCapabilityExtensionPoint.class, msgs);
			if (newExtensionLocation != null)
				msgs = ((InternalEObject)newExtensionLocation).eInverseAdd(this, InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__EXTEND_LINKS, AbstractCapabilityExtensionPoint.class, msgs);
			msgs = basicSetExtensionLocation(newExtensionLocation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION, newExtensionLocation, newExtensionLocation));

	}




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENDED:
				if (extended != null)
					msgs = ((InternalEObject)extended).eInverseRemove(this, InteractionPackage.ABSTRACT_CAPABILITY__EXTENDING, AbstractCapability.class, msgs);
				return basicSetExtended((AbstractCapability)otherEnd, msgs);
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetExtension((AbstractCapability)otherEnd, msgs);
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION:
				if (extensionLocation != null)
					msgs = ((InternalEObject)extensionLocation).eInverseRemove(this, InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__EXTEND_LINKS, AbstractCapabilityExtensionPoint.class, msgs);
				return basicSetExtensionLocation((AbstractCapabilityExtensionPoint)otherEnd, msgs);
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
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENDED:
				return basicSetExtended(null, msgs);
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION:
				return basicSetExtension(null, msgs);
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION:
				return basicSetExtensionLocation(null, msgs);
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
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION:
				return eInternalContainer().eInverseRemove(this, InteractionPackage.ABSTRACT_CAPABILITY__EXTENDS, AbstractCapability.class, msgs);
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
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENDED:
				if (resolve) return getExtended();
				return basicGetExtended();
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION:
				if (resolve) return getExtension();
				return basicGetExtension();
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION:
				if (resolve) return getExtensionLocation();
				return basicGetExtensionLocation();
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
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENDED:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractCapability) {
				// end-extension-code
					setExtended((AbstractCapability)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractCapability) {
				// end-extension-code
					setExtension((AbstractCapability)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractCapabilityExtensionPoint) {
				// end-extension-code
					setExtensionLocation((AbstractCapabilityExtensionPoint)newValue);
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
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENDED:
				setExtended((AbstractCapability)null);
				return;
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION:
				setExtension((AbstractCapability)null);
				return;
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION:
				setExtensionLocation((AbstractCapabilityExtensionPoint)null);
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
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENDED:
				return extended != null;
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION:
				return basicGetExtension() != null;
			case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION:
				return extensionLocation != null;
		}
		return super.eIsSet(featureID);
	}



} //AbstractCapabilityExtendImpl