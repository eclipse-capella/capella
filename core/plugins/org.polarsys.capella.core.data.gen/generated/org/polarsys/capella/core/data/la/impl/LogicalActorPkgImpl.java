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
package org.polarsys.capella.core.data.la.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.fa.impl.AbstractFunctionalStructureImpl;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Logical Actor Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalActorPkgImpl#getOwnedLogicalActorPkgs <em>Owned Logical Actor Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalActorPkgImpl#getOwnedLogicalActors <em>Owned Logical Actors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LogicalActorPkgImpl extends AbstractFunctionalStructureImpl implements LogicalActorPkg {

	/**
	 * The cached value of the '{@link #getOwnedLogicalActorPkgs() <em>Owned Logical Actor Pkgs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLogicalActorPkgs()
	 * @generated
	 * @ordered
	 */
	protected EList<LogicalActorPkg> ownedLogicalActorPkgs;





	/**
	 * The cached value of the '{@link #getOwnedLogicalActors() <em>Owned Logical Actors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLogicalActors()
	 * @generated
	 * @ordered
	 */
	protected EList<LogicalActor> ownedLogicalActors;




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LogicalActorPkgImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LaPackage.Literals.LOGICAL_ACTOR_PKG;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<LogicalActorPkg> getOwnedLogicalActorPkgs() {

		if (ownedLogicalActorPkgs == null) {
			ownedLogicalActorPkgs = new EObjectContainmentEList.Resolving<LogicalActorPkg>(LogicalActorPkg.class, this, LaPackage.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTOR_PKGS);
		}
		return ownedLogicalActorPkgs;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<LogicalActor> getOwnedLogicalActors() {

		if (ownedLogicalActors == null) {
			ownedLogicalActors = new EObjectContainmentEList.Resolving<LogicalActor>(LogicalActor.class, this, LaPackage.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTORS);
		}
		return ownedLogicalActors;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LaPackage.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTOR_PKGS:
				return ((InternalEList<?>)getOwnedLogicalActorPkgs()).basicRemove(otherEnd, msgs);
			case LaPackage.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTORS:
				return ((InternalEList<?>)getOwnedLogicalActors()).basicRemove(otherEnd, msgs);
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
			case LaPackage.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTOR_PKGS:
				return getOwnedLogicalActorPkgs();
			case LaPackage.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTORS:
				return getOwnedLogicalActors();
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
			case LaPackage.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTOR_PKGS:
				getOwnedLogicalActorPkgs().clear();
				getOwnedLogicalActorPkgs().addAll((Collection<? extends LogicalActorPkg>)newValue);
				return;
			case LaPackage.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTORS:
				getOwnedLogicalActors().clear();
				getOwnedLogicalActors().addAll((Collection<? extends LogicalActor>)newValue);
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
			case LaPackage.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTOR_PKGS:
				getOwnedLogicalActorPkgs().clear();
				return;
			case LaPackage.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTORS:
				getOwnedLogicalActors().clear();
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
			case LaPackage.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTOR_PKGS:
				return ownedLogicalActorPkgs != null && !ownedLogicalActorPkgs.isEmpty();
			case LaPackage.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTORS:
				return ownedLogicalActors != null && !ownedLogicalActors.isEmpty();
		}
		return super.eIsSet(featureID);
	}



} //LogicalActorPkgImpl