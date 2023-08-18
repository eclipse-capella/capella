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
package org.polarsys.capella.common.data.activity.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityGroup;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.modellingcore.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ActivityGroupImpl#getSuperGroup <em>Super Group</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ActivityGroupImpl#getSubGroups <em>Sub Groups</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ActivityGroupImpl#getOwnedNodes <em>Owned Nodes</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ActivityGroupImpl#getOwnedEdges <em>Owned Edges</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ActivityGroupImpl extends ModelElementImpl implements ActivityGroup {





	/**
   * The cached value of the '{@link #getSubGroups() <em>Sub Groups</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSubGroups()
   * @generated
   * @ordered
   */
	protected EList<ActivityGroup> subGroups;





	/**
   * The cached value of the '{@link #getOwnedNodes() <em>Owned Nodes</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedNodes()
   * @generated
   * @ordered
   */
	protected EList<ActivityNode> ownedNodes;





	/**
   * The cached value of the '{@link #getOwnedEdges() <em>Owned Edges</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedEdges()
   * @generated
   * @ordered
   */
	protected EList<ActivityEdge> ownedEdges;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ActivityGroupImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ActivityPackage.Literals.ACTIVITY_GROUP;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ActivityGroup getSuperGroup() {

    if (eContainerFeatureID() != ActivityPackage.ACTIVITY_GROUP__SUPER_GROUP) return null;
    return (ActivityGroup)eContainer();
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ActivityGroup basicGetSuperGroup() {

    if (eContainerFeatureID() != ActivityPackage.ACTIVITY_GROUP__SUPER_GROUP) return null;
    return (ActivityGroup)eInternalContainer();
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetSuperGroup(ActivityGroup newSuperGroup, NotificationChain msgs) {

    msgs = eBasicSetContainer((InternalEObject)newSuperGroup, ActivityPackage.ACTIVITY_GROUP__SUPER_GROUP, msgs);

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setSuperGroup(ActivityGroup newSuperGroup) {

    if (newSuperGroup != eInternalContainer() || (eContainerFeatureID() != ActivityPackage.ACTIVITY_GROUP__SUPER_GROUP && newSuperGroup != null)) {
      if (EcoreUtil.isAncestor(this, newSuperGroup))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newSuperGroup != null)
        msgs = ((InternalEObject)newSuperGroup).eInverseAdd(this, ActivityPackage.ACTIVITY_GROUP__SUB_GROUPS, ActivityGroup.class, msgs);
      msgs = basicSetSuperGroup(newSuperGroup, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.ACTIVITY_GROUP__SUPER_GROUP, newSuperGroup, newSuperGroup));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ActivityGroup> getSubGroups() {

    if (subGroups == null) {
      subGroups = new EObjectContainmentWithInverseEList.Resolving<ActivityGroup>(ActivityGroup.class, this, ActivityPackage.ACTIVITY_GROUP__SUB_GROUPS, ActivityPackage.ACTIVITY_GROUP__SUPER_GROUP);
    }
    return subGroups;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ActivityNode> getOwnedNodes() {

    if (ownedNodes == null) {
      ownedNodes = new EObjectContainmentEList.Resolving<ActivityNode>(ActivityNode.class, this, ActivityPackage.ACTIVITY_GROUP__OWNED_NODES);
    }
    return ownedNodes;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ActivityEdge> getOwnedEdges() {

    if (ownedEdges == null) {
      ownedEdges = new EObjectContainmentEList.Resolving<ActivityEdge>(ActivityEdge.class, this, ActivityPackage.ACTIVITY_GROUP__OWNED_EDGES);
    }
    return ownedEdges;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case ActivityPackage.ACTIVITY_GROUP__SUPER_GROUP:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetSuperGroup((ActivityGroup)otherEnd, msgs);
      case ActivityPackage.ACTIVITY_GROUP__SUB_GROUPS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubGroups()).basicAdd(otherEnd, msgs);
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
      case ActivityPackage.ACTIVITY_GROUP__SUPER_GROUP:
        return basicSetSuperGroup(null, msgs);
      case ActivityPackage.ACTIVITY_GROUP__SUB_GROUPS:
        return ((InternalEList<?>)getSubGroups()).basicRemove(otherEnd, msgs);
      case ActivityPackage.ACTIVITY_GROUP__OWNED_NODES:
        return ((InternalEList<?>)getOwnedNodes()).basicRemove(otherEnd, msgs);
      case ActivityPackage.ACTIVITY_GROUP__OWNED_EDGES:
        return ((InternalEList<?>)getOwnedEdges()).basicRemove(otherEnd, msgs);
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
      case ActivityPackage.ACTIVITY_GROUP__SUPER_GROUP:
        return eInternalContainer().eInverseRemove(this, ActivityPackage.ACTIVITY_GROUP__SUB_GROUPS, ActivityGroup.class, msgs);
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
      case ActivityPackage.ACTIVITY_GROUP__SUPER_GROUP:
        if (resolve) return getSuperGroup();
        return basicGetSuperGroup();
      case ActivityPackage.ACTIVITY_GROUP__SUB_GROUPS:
        return getSubGroups();
      case ActivityPackage.ACTIVITY_GROUP__OWNED_NODES:
        return getOwnedNodes();
      case ActivityPackage.ACTIVITY_GROUP__OWNED_EDGES:
        return getOwnedEdges();
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
      case ActivityPackage.ACTIVITY_GROUP__SUPER_GROUP:
          setSuperGroup((ActivityGroup)newValue);
        return;
      case ActivityPackage.ACTIVITY_GROUP__SUB_GROUPS:
        getSubGroups().clear();
        getSubGroups().addAll((Collection<? extends ActivityGroup>)newValue);
        return;
      case ActivityPackage.ACTIVITY_GROUP__OWNED_NODES:
        getOwnedNodes().clear();
        getOwnedNodes().addAll((Collection<? extends ActivityNode>)newValue);
        return;
      case ActivityPackage.ACTIVITY_GROUP__OWNED_EDGES:
        getOwnedEdges().clear();
        getOwnedEdges().addAll((Collection<? extends ActivityEdge>)newValue);
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
      case ActivityPackage.ACTIVITY_GROUP__SUPER_GROUP:
        setSuperGroup((ActivityGroup)null);
        return;
      case ActivityPackage.ACTIVITY_GROUP__SUB_GROUPS:
        getSubGroups().clear();
        return;
      case ActivityPackage.ACTIVITY_GROUP__OWNED_NODES:
        getOwnedNodes().clear();
        return;
      case ActivityPackage.ACTIVITY_GROUP__OWNED_EDGES:
        getOwnedEdges().clear();
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
      case ActivityPackage.ACTIVITY_GROUP__SUPER_GROUP:
        return basicGetSuperGroup() != null;
      case ActivityPackage.ACTIVITY_GROUP__SUB_GROUPS:
        return subGroups != null && !subGroups.isEmpty();
      case ActivityPackage.ACTIVITY_GROUP__OWNED_NODES:
        return ownedNodes != null && !ownedNodes.isEmpty();
      case ActivityPackage.ACTIVITY_GROUP__OWNED_EDGES:
        return ownedEdges != null && !ownedEdges.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //ActivityGroupImpl