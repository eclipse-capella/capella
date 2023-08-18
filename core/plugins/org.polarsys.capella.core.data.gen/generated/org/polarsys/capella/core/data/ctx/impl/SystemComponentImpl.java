/**
 *
 *  Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.ctx.impl;

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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.cs.impl.ComponentImpl;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionInvolvement;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.Entity;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentImpl#getInvolvingInvolvements <em>Involving Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentImpl#getOwnedSystemComponents <em>Owned System Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentImpl#getOwnedSystemComponentPkgs <em>Owned System Component Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentImpl#isDataComponent <em>Data Component</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentImpl#getInvolvingCapabilities <em>Involving Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentImpl#getCapabilityInvolvements <em>Capability Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentImpl#getInvolvingMissions <em>Involving Missions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentImpl#getMissionInvolvements <em>Mission Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentImpl#getRealizedEntities <em>Realized Entities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentImpl#getRealizingLogicalComponents <em>Realizing Logical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentImpl#getAllocatedSystemFunctions <em>Allocated System Functions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SystemComponentImpl extends ComponentImpl implements SystemComponent {

	/**
   * The cached value of the '{@link #getOwnedSystemComponents() <em>Owned System Components</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedSystemComponents()
   * @generated
   * @ordered
   */
	protected EList<SystemComponent> ownedSystemComponents;

	/**
   * The cached value of the '{@link #getOwnedSystemComponentPkgs() <em>Owned System Component Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedSystemComponentPkgs()
   * @generated
   * @ordered
   */
	protected EList<SystemComponentPkg> ownedSystemComponentPkgs;

	/**
   * The default value of the '{@link #isDataComponent() <em>Data Component</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isDataComponent()
   * @generated
   * @ordered
   */
	protected static final boolean DATA_COMPONENT_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isDataComponent() <em>Data Component</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isDataComponent()
   * @generated
   * @ordered
   */
	protected boolean dataComponent = DATA_COMPONENT_EDEFAULT;





	/**
   * The cached value of the '{@link #getDataType() <em>Data Type</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getDataType()
   * @generated
   * @ordered
   */
	protected EList<Classifier> dataType;




















	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected SystemComponentImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CtxPackage.Literals.SYSTEM_COMPONENT;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Involvement> getInvolvingInvolvements() {


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
    EAnnotation annotation = CapellacorePackage.Literals.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Involvement> resultAsList = (Collection<Involvement>) result;
    return new EcoreEList.UnmodifiableEList<Involvement>(this, CapellacorePackage.Literals.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<SystemComponent> getOwnedSystemComponents() {

    if (ownedSystemComponents == null) {
      ownedSystemComponents = new EObjectContainmentEList.Resolving<SystemComponent>(SystemComponent.class, this, CtxPackage.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENTS);
    }
    return ownedSystemComponents;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<SystemComponentPkg> getOwnedSystemComponentPkgs() {

    if (ownedSystemComponentPkgs == null) {
      ownedSystemComponentPkgs = new EObjectContainmentEList.Resolving<SystemComponentPkg>(SystemComponentPkg.class, this, CtxPackage.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENT_PKGS);
    }
    return ownedSystemComponentPkgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isDataComponent() {

    return dataComponent;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setDataComponent(boolean newDataComponent) {

    boolean oldDataComponent = dataComponent;
    dataComponent = newDataComponent;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CtxPackage.SYSTEM_COMPONENT__DATA_COMPONENT, oldDataComponent, dataComponent));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Classifier> getDataType() {

    if (dataType == null) {
      dataType = new EObjectResolvingEList<Classifier>(Classifier.class, this, CtxPackage.SYSTEM_COMPONENT__DATA_TYPE);
    }
    return dataType;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Capability> getInvolvingCapabilities() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_COMPONENT__INVOLVING_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_COMPONENT__INVOLVING_CAPABILITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Capability> resultAsList = (Collection<Capability>) result;
    return new EcoreEList.UnmodifiableEList<Capability>(this, CtxPackage.Literals.SYSTEM_COMPONENT__INVOLVING_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
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

	public EList<CapabilityInvolvement> getCapabilityInvolvements() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_COMPONENT__CAPABILITY_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_COMPONENT__CAPABILITY_INVOLVEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CapabilityInvolvement> resultAsList = (Collection<CapabilityInvolvement>) result;
    return new EcoreEList.UnmodifiableEList<CapabilityInvolvement>(this, CtxPackage.Literals.SYSTEM_COMPONENT__CAPABILITY_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Mission> getInvolvingMissions() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_COMPONENT__INVOLVING_MISSIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_COMPONENT__INVOLVING_MISSIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Mission> resultAsList = (Collection<Mission>) result;
    return new EcoreEList.UnmodifiableEList<Mission>(this, CtxPackage.Literals.SYSTEM_COMPONENT__INVOLVING_MISSIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<MissionInvolvement> getMissionInvolvements() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_COMPONENT__MISSION_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_COMPONENT__MISSION_INVOLVEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<MissionInvolvement> resultAsList = (Collection<MissionInvolvement>) result;
    return new EcoreEList.UnmodifiableEList<MissionInvolvement>(this, CtxPackage.Literals.SYSTEM_COMPONENT__MISSION_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Entity> getRealizedEntities() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_COMPONENT__REALIZED_ENTITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_COMPONENT__REALIZED_ENTITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Entity> resultAsList = (Collection<Entity>) result;
    return new EcoreEList.UnmodifiableEList<Entity>(this, CtxPackage.Literals.SYSTEM_COMPONENT__REALIZED_ENTITIES, resultAsList.size(), resultAsList.toArray());
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

	public EList<LogicalComponent> getRealizingLogicalComponents() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_COMPONENT__REALIZING_LOGICAL_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_COMPONENT__REALIZING_LOGICAL_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<LogicalComponent> resultAsList = (Collection<LogicalComponent>) result;
    return new EcoreEList.UnmodifiableEList<LogicalComponent>(this, CtxPackage.Literals.SYSTEM_COMPONENT__REALIZING_LOGICAL_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<SystemFunction> getAllocatedSystemFunctions() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_COMPONENT__ALLOCATED_SYSTEM_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_COMPONENT__ALLOCATED_SYSTEM_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<SystemFunction> resultAsList = (Collection<SystemFunction>) result;
    return new EcoreEList.UnmodifiableEList<SystemFunction>(this, CtxPackage.Literals.SYSTEM_COMPONENT__ALLOCATED_SYSTEM_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CtxPackage.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENTS:
        return ((InternalEList<?>)getOwnedSystemComponents()).basicRemove(otherEnd, msgs);
      case CtxPackage.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENT_PKGS:
        return ((InternalEList<?>)getOwnedSystemComponentPkgs()).basicRemove(otherEnd, msgs);
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
      case CtxPackage.SYSTEM_COMPONENT__INVOLVING_INVOLVEMENTS:
        return getInvolvingInvolvements();
      case CtxPackage.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENTS:
        return getOwnedSystemComponents();
      case CtxPackage.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENT_PKGS:
        return getOwnedSystemComponentPkgs();
      case CtxPackage.SYSTEM_COMPONENT__DATA_COMPONENT:
        return isDataComponent();
      case CtxPackage.SYSTEM_COMPONENT__DATA_TYPE:
        return getDataType();
      case CtxPackage.SYSTEM_COMPONENT__INVOLVING_CAPABILITIES:
        return getInvolvingCapabilities();
      case CtxPackage.SYSTEM_COMPONENT__CAPABILITY_INVOLVEMENTS:
        return getCapabilityInvolvements();
      case CtxPackage.SYSTEM_COMPONENT__INVOLVING_MISSIONS:
        return getInvolvingMissions();
      case CtxPackage.SYSTEM_COMPONENT__MISSION_INVOLVEMENTS:
        return getMissionInvolvements();
      case CtxPackage.SYSTEM_COMPONENT__REALIZED_ENTITIES:
        return getRealizedEntities();
      case CtxPackage.SYSTEM_COMPONENT__REALIZING_LOGICAL_COMPONENTS:
        return getRealizingLogicalComponents();
      case CtxPackage.SYSTEM_COMPONENT__ALLOCATED_SYSTEM_FUNCTIONS:
        return getAllocatedSystemFunctions();
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
      case CtxPackage.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENTS:
        getOwnedSystemComponents().clear();
        getOwnedSystemComponents().addAll((Collection<? extends SystemComponent>)newValue);
        return;
      case CtxPackage.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENT_PKGS:
        getOwnedSystemComponentPkgs().clear();
        getOwnedSystemComponentPkgs().addAll((Collection<? extends SystemComponentPkg>)newValue);
        return;
      case CtxPackage.SYSTEM_COMPONENT__DATA_COMPONENT:
          setDataComponent((Boolean)newValue);
        return;
      case CtxPackage.SYSTEM_COMPONENT__DATA_TYPE:
        getDataType().clear();
        getDataType().addAll((Collection<? extends Classifier>)newValue);
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
      case CtxPackage.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENTS:
        getOwnedSystemComponents().clear();
        return;
      case CtxPackage.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENT_PKGS:
        getOwnedSystemComponentPkgs().clear();
        return;
      case CtxPackage.SYSTEM_COMPONENT__DATA_COMPONENT:
        setDataComponent(DATA_COMPONENT_EDEFAULT);
        return;
      case CtxPackage.SYSTEM_COMPONENT__DATA_TYPE:
        getDataType().clear();
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
      case CtxPackage.SYSTEM_COMPONENT__INVOLVING_INVOLVEMENTS:
        return !getInvolvingInvolvements().isEmpty();
      case CtxPackage.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENTS:
        return ownedSystemComponents != null && !ownedSystemComponents.isEmpty();
      case CtxPackage.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENT_PKGS:
        return ownedSystemComponentPkgs != null && !ownedSystemComponentPkgs.isEmpty();
      case CtxPackage.SYSTEM_COMPONENT__DATA_COMPONENT:
        return dataComponent != DATA_COMPONENT_EDEFAULT;
      case CtxPackage.SYSTEM_COMPONENT__DATA_TYPE:
        return dataType != null && !dataType.isEmpty();
      case CtxPackage.SYSTEM_COMPONENT__INVOLVING_CAPABILITIES:
        return !getInvolvingCapabilities().isEmpty();
      case CtxPackage.SYSTEM_COMPONENT__CAPABILITY_INVOLVEMENTS:
        return !getCapabilityInvolvements().isEmpty();
      case CtxPackage.SYSTEM_COMPONENT__INVOLVING_MISSIONS:
        return !getInvolvingMissions().isEmpty();
      case CtxPackage.SYSTEM_COMPONENT__MISSION_INVOLVEMENTS:
        return !getMissionInvolvements().isEmpty();
      case CtxPackage.SYSTEM_COMPONENT__REALIZED_ENTITIES:
        return !getRealizedEntities().isEmpty();
      case CtxPackage.SYSTEM_COMPONENT__REALIZING_LOGICAL_COMPONENTS:
        return !getRealizingLogicalComponents().isEmpty();
      case CtxPackage.SYSTEM_COMPONENT__ALLOCATED_SYSTEM_FUNCTIONS:
        return !getAllocatedSystemFunctions().isEmpty();
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
    if (baseClass == InvolvedElement.class) {
      switch (derivedFeatureID) {
        case CtxPackage.SYSTEM_COMPONENT__INVOLVING_INVOLVEMENTS: return CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS;
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
    if (baseClass == InvolvedElement.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS: return CtxPackage.SYSTEM_COMPONENT__INVOLVING_INVOLVEMENTS;
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

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (dataComponent: "); //$NON-NLS-1$
    result.append(dataComponent);
    result.append(')');
    return result.toString();
  }


} //SystemComponentImpl