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

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.Location;
import org.polarsys.capella.core.data.oa.OaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityPkgImpl#getOwnedEntities <em>Owned Entities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityPkgImpl#getOwnedEntityPkgs <em>Owned Entity Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityPkgImpl#getOwnedLocations <em>Owned Locations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityPkgImpl#getOwnedCommunicationMeans <em>Owned Communication Means</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityPkgImpl extends ComponentPkgImpl implements EntityPkg {

	/**
   * The cached value of the '{@link #getOwnedEntities() <em>Owned Entities</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedEntities()
   * @generated
   * @ordered
   */
	protected EList<Entity> ownedEntities;





	/**
   * The cached value of the '{@link #getOwnedEntityPkgs() <em>Owned Entity Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedEntityPkgs()
   * @generated
   * @ordered
   */
	protected EList<EntityPkg> ownedEntityPkgs;





	/**
   * The cached value of the '{@link #getOwnedLocations() <em>Owned Locations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedLocations()
   * @generated
   * @ordered
   */
	protected EList<Location> ownedLocations;





	/**
   * The cached value of the '{@link #getOwnedCommunicationMeans() <em>Owned Communication Means</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedCommunicationMeans()
   * @generated
   * @ordered
   */
	protected EList<CommunicationMean> ownedCommunicationMeans;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected EntityPkgImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.ENTITY_PKG;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<EntityPkg> getOwnedEntityPkgs() {

    if (ownedEntityPkgs == null) {
      ownedEntityPkgs = new EObjectContainmentEList.Resolving<EntityPkg>(EntityPkg.class, this, OaPackage.ENTITY_PKG__OWNED_ENTITY_PKGS);
    }
    return ownedEntityPkgs;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Entity> getOwnedEntities() {

    if (ownedEntities == null) {
      ownedEntities = new EObjectContainmentEList.Resolving<Entity>(Entity.class, this, OaPackage.ENTITY_PKG__OWNED_ENTITIES);
    }
    return ownedEntities;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Location> getOwnedLocations() {

    if (ownedLocations == null) {
      ownedLocations = new EObjectContainmentEList.Resolving<Location>(Location.class, this, OaPackage.ENTITY_PKG__OWNED_LOCATIONS);
    }
    return ownedLocations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CommunicationMean> getOwnedCommunicationMeans() {

    if (ownedCommunicationMeans == null) {
      ownedCommunicationMeans = new EObjectContainmentEList.Resolving<CommunicationMean>(CommunicationMean.class, this, OaPackage.ENTITY_PKG__OWNED_COMMUNICATION_MEANS);
    }
    return ownedCommunicationMeans;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case OaPackage.ENTITY_PKG__OWNED_ENTITIES:
        return ((InternalEList<?>)getOwnedEntities()).basicRemove(otherEnd, msgs);
      case OaPackage.ENTITY_PKG__OWNED_ENTITY_PKGS:
        return ((InternalEList<?>)getOwnedEntityPkgs()).basicRemove(otherEnd, msgs);
      case OaPackage.ENTITY_PKG__OWNED_LOCATIONS:
        return ((InternalEList<?>)getOwnedLocations()).basicRemove(otherEnd, msgs);
      case OaPackage.ENTITY_PKG__OWNED_COMMUNICATION_MEANS:
        return ((InternalEList<?>)getOwnedCommunicationMeans()).basicRemove(otherEnd, msgs);
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
      case OaPackage.ENTITY_PKG__OWNED_ENTITIES:
        return getOwnedEntities();
      case OaPackage.ENTITY_PKG__OWNED_ENTITY_PKGS:
        return getOwnedEntityPkgs();
      case OaPackage.ENTITY_PKG__OWNED_LOCATIONS:
        return getOwnedLocations();
      case OaPackage.ENTITY_PKG__OWNED_COMMUNICATION_MEANS:
        return getOwnedCommunicationMeans();
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
      case OaPackage.ENTITY_PKG__OWNED_ENTITIES:
        getOwnedEntities().clear();
        getOwnedEntities().addAll((Collection<? extends Entity>)newValue);
        return;
      case OaPackage.ENTITY_PKG__OWNED_ENTITY_PKGS:
        getOwnedEntityPkgs().clear();
        getOwnedEntityPkgs().addAll((Collection<? extends EntityPkg>)newValue);
        return;
      case OaPackage.ENTITY_PKG__OWNED_LOCATIONS:
        getOwnedLocations().clear();
        getOwnedLocations().addAll((Collection<? extends Location>)newValue);
        return;
      case OaPackage.ENTITY_PKG__OWNED_COMMUNICATION_MEANS:
        getOwnedCommunicationMeans().clear();
        getOwnedCommunicationMeans().addAll((Collection<? extends CommunicationMean>)newValue);
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
      case OaPackage.ENTITY_PKG__OWNED_ENTITIES:
        getOwnedEntities().clear();
        return;
      case OaPackage.ENTITY_PKG__OWNED_ENTITY_PKGS:
        getOwnedEntityPkgs().clear();
        return;
      case OaPackage.ENTITY_PKG__OWNED_LOCATIONS:
        getOwnedLocations().clear();
        return;
      case OaPackage.ENTITY_PKG__OWNED_COMMUNICATION_MEANS:
        getOwnedCommunicationMeans().clear();
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
      case OaPackage.ENTITY_PKG__OWNED_ENTITIES:
        return ownedEntities != null && !ownedEntities.isEmpty();
      case OaPackage.ENTITY_PKG__OWNED_ENTITY_PKGS:
        return ownedEntityPkgs != null && !ownedEntityPkgs.isEmpty();
      case OaPackage.ENTITY_PKG__OWNED_LOCATIONS:
        return ownedLocations != null && !ownedLocations.isEmpty();
      case OaPackage.ENTITY_PKG__OWNED_COMMUNICATION_MEANS:
        return ownedCommunicationMeans != null && !ownedCommunicationMeans.isEmpty();
    }
    return super.eIsSet(featureID);
  }


} //EntityPkgImpl