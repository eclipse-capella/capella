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
package org.polarsys.capella.core.data.pa.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.fa.impl.AbstractFunctionalStructureImpl;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Physical Actor Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalActorPkgImpl#getOwnedPhysicalActorPkgs <em>Owned Physical Actor Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalActorPkgImpl#getOwnedPhysicalActors <em>Owned Physical Actors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PhysicalActorPkgImpl extends AbstractFunctionalStructureImpl implements PhysicalActorPkg {

	/**
	 * The cached value of the '{@link #getOwnedPhysicalActorPkgs() <em>Owned Physical Actor Pkgs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPhysicalActorPkgs()
	 * @generated
	 * @ordered
	 */
	protected EList<PhysicalActorPkg> ownedPhysicalActorPkgs;





	/**
	 * The cached value of the '{@link #getOwnedPhysicalActors() <em>Owned Physical Actors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPhysicalActors()
	 * @generated
	 * @ordered
	 */
	protected EList<PhysicalActor> ownedPhysicalActors;




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PhysicalActorPkgImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PaPackage.Literals.PHYSICAL_ACTOR_PKG;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<PhysicalActorPkg> getOwnedPhysicalActorPkgs() {

		if (ownedPhysicalActorPkgs == null) {
			ownedPhysicalActorPkgs = new EObjectContainmentEList.Resolving<PhysicalActorPkg>(PhysicalActorPkg.class, this, PaPackage.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTOR_PKGS);
		}
		return ownedPhysicalActorPkgs;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<PhysicalActor> getOwnedPhysicalActors() {

		if (ownedPhysicalActors == null) {
			ownedPhysicalActors = new EObjectContainmentEList.Resolving<PhysicalActor>(PhysicalActor.class, this, PaPackage.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTORS);
		}
		return ownedPhysicalActors;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PaPackage.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTOR_PKGS:
				return ((InternalEList<?>)getOwnedPhysicalActorPkgs()).basicRemove(otherEnd, msgs);
			case PaPackage.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTORS:
				return ((InternalEList<?>)getOwnedPhysicalActors()).basicRemove(otherEnd, msgs);
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
			case PaPackage.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTOR_PKGS:
				return getOwnedPhysicalActorPkgs();
			case PaPackage.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTORS:
				return getOwnedPhysicalActors();
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
			case PaPackage.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTOR_PKGS:
				getOwnedPhysicalActorPkgs().clear();
				getOwnedPhysicalActorPkgs().addAll((Collection<? extends PhysicalActorPkg>)newValue);
				return;
			case PaPackage.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTORS:
				getOwnedPhysicalActors().clear();
				getOwnedPhysicalActors().addAll((Collection<? extends PhysicalActor>)newValue);
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
			case PaPackage.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTOR_PKGS:
				getOwnedPhysicalActorPkgs().clear();
				return;
			case PaPackage.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTORS:
				getOwnedPhysicalActors().clear();
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
			case PaPackage.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTOR_PKGS:
				return ownedPhysicalActorPkgs != null && !ownedPhysicalActorPkgs.isEmpty();
			case PaPackage.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTORS:
				return ownedPhysicalActors != null && !ownedPhysicalActors.isEmpty();
		}
		return super.eIsSet(featureID);
	}



} //PhysicalActorPkgImpl