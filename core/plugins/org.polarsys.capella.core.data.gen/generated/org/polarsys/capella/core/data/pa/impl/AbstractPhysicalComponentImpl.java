/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.pa.impl;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.impl.SystemComponentImpl;
import org.polarsys.capella.core.data.pa.AbstractPhysicalComponent;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponentKind;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.deployment.DeploymentAspect;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Physical Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.AbstractPhysicalComponentImpl#getDeployingLinks <em>Deploying Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.AbstractPhysicalComponentImpl#getDeploymentLinks <em>Deployment Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.AbstractPhysicalComponentImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.AbstractPhysicalComponentImpl#getNature <em>Nature</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.AbstractPhysicalComponentImpl#getOwnedDeploymentLinks <em>Owned Deployment Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.AbstractPhysicalComponentImpl#getOwnedDeploymentAspect <em>Owned Deployment Aspect</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractPhysicalComponentImpl extends SystemComponentImpl implements AbstractPhysicalComponent {





	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final PhysicalComponentKind KIND_EDEFAULT = PhysicalComponentKind.UNSET;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected PhysicalComponentKind kind = KIND_EDEFAULT;





	/**
	 * The default value of the '{@link #getNature() <em>Nature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNature()
	 * @generated
	 * @ordered
	 */
	protected static final PhysicalComponentNature NATURE_EDEFAULT = PhysicalComponentNature.UNSET;

	/**
	 * The cached value of the '{@link #getNature() <em>Nature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNature()
	 * @generated
	 * @ordered
	 */
	protected PhysicalComponentNature nature = NATURE_EDEFAULT;





	/**
	 * The cached value of the '{@link #getOwnedDeploymentLinks() <em>Owned Deployment Links</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedDeploymentLinks()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractDeploymentLink> ownedDeploymentLinks;





	/**
	 * The cached value of the '{@link #getOwnedDeploymentAspect() <em>Owned Deployment Aspect</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedDeploymentAspect()
	 * @generated
	 * @ordered
	 */
	protected DeploymentAspect ownedDeploymentAspect;




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractPhysicalComponentImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PaPackage.Literals.ABSTRACT_PHYSICAL_COMPONENT;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<AbstractDeploymentLink> getDeployingLinks() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.DEPLOYABLE_ELEMENT__DEPLOYING_LINKS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.DEPLOYABLE_ELEMENT__DEPLOYING_LINKS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<AbstractDeploymentLink> resultAsList = (Collection<AbstractDeploymentLink>) result;
		return new EcoreEList.UnmodifiableEList<AbstractDeploymentLink>(this, CsPackage.Literals.DEPLOYABLE_ELEMENT__DEPLOYING_LINKS, resultAsList.size(), resultAsList.toArray());
		} catch (ClassCastException exception) {
	  	exception.printStackTrace();
	  	return org.eclipse.emf.common.util.ECollections.emptyEList();
	  }
		
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<AbstractDeploymentLink> getDeploymentLinks() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.DEPLOYMENT_TARGET__DEPLOYMENT_LINKS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.DEPLOYMENT_TARGET__DEPLOYMENT_LINKS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<AbstractDeploymentLink> resultAsList = (Collection<AbstractDeploymentLink>) result;
		return new EcoreEList.UnmodifiableEList<AbstractDeploymentLink>(this, CsPackage.Literals.DEPLOYMENT_TARGET__DEPLOYMENT_LINKS, resultAsList.size(), resultAsList.toArray());
		} catch (ClassCastException exception) {
	  	exception.printStackTrace();
	  	return org.eclipse.emf.common.util.ECollections.emptyEList();
	  }
		
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public PhysicalComponentKind getKind() {

		return kind;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setKind(PhysicalComponentKind newKind) {

		PhysicalComponentKind oldKind = kind;
		kind = newKind == null ? KIND_EDEFAULT : newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.ABSTRACT_PHYSICAL_COMPONENT__KIND, oldKind, kind));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public PhysicalComponentNature getNature() {

		return nature;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setNature(PhysicalComponentNature newNature) {

		PhysicalComponentNature oldNature = nature;
		nature = newNature == null ? NATURE_EDEFAULT : newNature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.ABSTRACT_PHYSICAL_COMPONENT__NATURE, oldNature, nature));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<AbstractDeploymentLink> getOwnedDeploymentLinks() {

		if (ownedDeploymentLinks == null) {
			ownedDeploymentLinks = new EObjectContainmentEList.Resolving<AbstractDeploymentLink>(AbstractDeploymentLink.class, this, PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS);
		}
		return ownedDeploymentLinks;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public DeploymentAspect getOwnedDeploymentAspect() {

		if (ownedDeploymentAspect != null && ownedDeploymentAspect.eIsProxy()) {
			InternalEObject oldOwnedDeploymentAspect = (InternalEObject)ownedDeploymentAspect;
			ownedDeploymentAspect = (DeploymentAspect)eResolveProxy(oldOwnedDeploymentAspect);
			if (ownedDeploymentAspect != oldOwnedDeploymentAspect) {
				InternalEObject newOwnedDeploymentAspect = (InternalEObject)ownedDeploymentAspect;
				NotificationChain msgs = oldOwnedDeploymentAspect.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT, null, null);
				if (newOwnedDeploymentAspect.eInternalContainer() == null) {
					msgs = newOwnedDeploymentAspect.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT, oldOwnedDeploymentAspect, ownedDeploymentAspect));
			}
		}
		return ownedDeploymentAspect;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public DeploymentAspect basicGetOwnedDeploymentAspect() {

		return ownedDeploymentAspect;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedDeploymentAspect(DeploymentAspect newOwnedDeploymentAspect, NotificationChain msgs) {

		DeploymentAspect oldOwnedDeploymentAspect = ownedDeploymentAspect;
		ownedDeploymentAspect = newOwnedDeploymentAspect;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT, oldOwnedDeploymentAspect, newOwnedDeploymentAspect);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedDeploymentAspect(DeploymentAspect newOwnedDeploymentAspect) {

		if (newOwnedDeploymentAspect != ownedDeploymentAspect) {
			NotificationChain msgs = null;
			if (ownedDeploymentAspect != null)
				msgs = ((InternalEObject)ownedDeploymentAspect).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT, null, msgs);
			if (newOwnedDeploymentAspect != null)
				msgs = ((InternalEObject)newOwnedDeploymentAspect).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT, null, msgs);
			msgs = basicSetOwnedDeploymentAspect(newOwnedDeploymentAspect, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT, newOwnedDeploymentAspect, newOwnedDeploymentAspect));

	}




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS:
				return ((InternalEList<?>)getOwnedDeploymentLinks()).basicRemove(otherEnd, msgs);
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT:
				return basicSetOwnedDeploymentAspect(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__DEPLOYING_LINKS:
				return getDeployingLinks();
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__DEPLOYMENT_LINKS:
				return getDeploymentLinks();
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__KIND:
				return getKind();
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__NATURE:
				return getNature();
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS:
				return getOwnedDeploymentLinks();
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT:
				if (resolve) return getOwnedDeploymentAspect();
				return basicGetOwnedDeploymentAspect();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__KIND:
				// begin-extension-code
				if (newValue == null || newValue instanceof PhysicalComponentKind) {
				// end-extension-code
					setKind((PhysicalComponentKind)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__NATURE:
				// begin-extension-code
				if (newValue == null || newValue instanceof PhysicalComponentNature) {
				// end-extension-code
					setNature((PhysicalComponentNature)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS:
				getOwnedDeploymentLinks().clear();
				getOwnedDeploymentLinks().addAll((Collection<? extends AbstractDeploymentLink>)newValue);
				return;
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT:
				// begin-extension-code
				if (newValue == null || newValue instanceof DeploymentAspect) {
				// end-extension-code
					setOwnedDeploymentAspect((DeploymentAspect)newValue);
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
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__NATURE:
				setNature(NATURE_EDEFAULT);
				return;
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS:
				getOwnedDeploymentLinks().clear();
				return;
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT:
				setOwnedDeploymentAspect((DeploymentAspect)null);
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
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__DEPLOYING_LINKS:
				return !getDeployingLinks().isEmpty();
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__DEPLOYMENT_LINKS:
				return !getDeploymentLinks().isEmpty();
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__KIND:
				return kind != KIND_EDEFAULT;
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__NATURE:
				return nature != NATURE_EDEFAULT;
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS:
				return ownedDeploymentLinks != null && !ownedDeploymentLinks.isEmpty();
			case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT:
				return ownedDeploymentAspect != null;
		}
		return super.eIsSet(featureID);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == DeployableElement.class) {
			switch (derivedFeatureID) {
				case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__DEPLOYING_LINKS: return CsPackage.DEPLOYABLE_ELEMENT__DEPLOYING_LINKS;
				default: return -1;
			}
		}
		if (baseClass == DeploymentTarget.class) {
			switch (derivedFeatureID) {
				case PaPackage.ABSTRACT_PHYSICAL_COMPONENT__DEPLOYMENT_LINKS: return CsPackage.DEPLOYMENT_TARGET__DEPLOYMENT_LINKS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == DeployableElement.class) {
			switch (baseFeatureID) {
				case CsPackage.DEPLOYABLE_ELEMENT__DEPLOYING_LINKS: return PaPackage.ABSTRACT_PHYSICAL_COMPONENT__DEPLOYING_LINKS;
				default: return -1;
			}
		}
		if (baseClass == DeploymentTarget.class) {
			switch (baseFeatureID) {
				case CsPackage.DEPLOYMENT_TARGET__DEPLOYMENT_LINKS: return PaPackage.ABSTRACT_PHYSICAL_COMPONENT__DEPLOYMENT_LINKS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (kind: "); //$NON-NLS-1$
		result.append(kind);
		result.append(", nature: "); //$NON-NLS-1$
		result.append(nature);
		result.append(')');
		return result.toString();
	}


} //AbstractPhysicalComponentImpl