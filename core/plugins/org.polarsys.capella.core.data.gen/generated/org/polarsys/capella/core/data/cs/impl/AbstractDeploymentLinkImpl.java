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
package org.polarsys.capella.core.data.cs.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Deployment Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractDeploymentLinkImpl#getDeployedElement <em>Deployed Element</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractDeploymentLinkImpl#getLocation <em>Location</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractDeploymentLinkImpl extends RelationshipImpl implements AbstractDeploymentLink {

	/**
	 * The cached value of the '{@link #getDeployedElement() <em>Deployed Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeployedElement()
	 * @generated
	 * @ordered
	 */
	protected DeployableElement deployedElement;





	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected DeploymentTarget location;




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractDeploymentLinkImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public DeployableElement getDeployedElement() {

		if (deployedElement != null && deployedElement.eIsProxy()) {
			InternalEObject oldDeployedElement = (InternalEObject)deployedElement;
			deployedElement = (DeployableElement)eResolveProxy(oldDeployedElement);
			if (deployedElement != oldDeployedElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT, oldDeployedElement, deployedElement));
			}
		}
		return deployedElement;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public DeployableElement basicGetDeployedElement() {

		return deployedElement;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setDeployedElement(DeployableElement newDeployedElement) {

		DeployableElement oldDeployedElement = deployedElement;
		deployedElement = newDeployedElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT, oldDeployedElement, deployedElement));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public DeploymentTarget getLocation() {

		if (location != null && location.eIsProxy()) {
			InternalEObject oldLocation = (InternalEObject)location;
			location = (DeploymentTarget)eResolveProxy(oldLocation);
			if (location != oldLocation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.ABSTRACT_DEPLOYMENT_LINK__LOCATION, oldLocation, location));
			}
		}
		return location;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public DeploymentTarget basicGetLocation() {

		return location;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setLocation(DeploymentTarget newLocation) {

		DeploymentTarget oldLocation = location;
		location = newLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.ABSTRACT_DEPLOYMENT_LINK__LOCATION, oldLocation, location));

	}




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CsPackage.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT:
				if (resolve) return getDeployedElement();
				return basicGetDeployedElement();
			case CsPackage.ABSTRACT_DEPLOYMENT_LINK__LOCATION:
				if (resolve) return getLocation();
				return basicGetLocation();
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
			case CsPackage.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT:
				// begin-extension-code
				if (newValue == null || newValue instanceof DeployableElement) {
				// end-extension-code
					setDeployedElement((DeployableElement)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CsPackage.ABSTRACT_DEPLOYMENT_LINK__LOCATION:
				// begin-extension-code
				if (newValue == null || newValue instanceof DeploymentTarget) {
				// end-extension-code
					setLocation((DeploymentTarget)newValue);
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
			case CsPackage.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT:
				setDeployedElement((DeployableElement)null);
				return;
			case CsPackage.ABSTRACT_DEPLOYMENT_LINK__LOCATION:
				setLocation((DeploymentTarget)null);
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
			case CsPackage.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT:
				return deployedElement != null;
			case CsPackage.ABSTRACT_DEPLOYMENT_LINK__LOCATION:
				return location != null;
		}
		return super.eIsSet(featureID);
	}



} //AbstractDeploymentLinkImpl