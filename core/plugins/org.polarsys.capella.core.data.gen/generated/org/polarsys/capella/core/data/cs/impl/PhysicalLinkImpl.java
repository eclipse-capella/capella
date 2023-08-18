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

package org.polarsys.capella.core.data.cs.impl;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalLinkRealization;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Physical Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkImpl#getAllocatorConfigurationItems <em>Allocator Configuration Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkImpl#getInvolvingInvolvements <em>Involving Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkImpl#getLinkEnds <em>Link Ends</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkImpl#getOwnedComponentExchangeFunctionalExchangeAllocations <em>Owned Component Exchange Functional Exchange Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkImpl#getOwnedPhysicalLinkEnds <em>Owned Physical Link Ends</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkImpl#getOwnedPhysicalLinkRealizations <em>Owned Physical Link Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkImpl#getCategories <em>Categories</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkImpl#getSourcePhysicalPort <em>Source Physical Port</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkImpl#getTargetPhysicalPort <em>Target Physical Port</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkImpl#getRealizedPhysicalLinks <em>Realized Physical Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkImpl#getRealizingPhysicalLinks <em>Realizing Physical Links</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PhysicalLinkImpl extends AbstractPhysicalPathLinkImpl implements PhysicalLink {









	/**
   * The cached value of the '{@link #getLinkEnds() <em>Link Ends</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getLinkEnds()
   * @generated
   * @ordered
   */
	protected EList<AbstractPhysicalLinkEnd> linkEnds;





	/**
   * The cached value of the '{@link #getOwnedComponentExchangeFunctionalExchangeAllocations() <em>Owned Component Exchange Functional Exchange Allocations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedComponentExchangeFunctionalExchangeAllocations()
   * @generated
   * @ordered
   */
	protected EList<ComponentExchangeFunctionalExchangeAllocation> ownedComponentExchangeFunctionalExchangeAllocations;





	/**
   * The cached value of the '{@link #getOwnedPhysicalLinkEnds() <em>Owned Physical Link Ends</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPhysicalLinkEnds()
   * @generated
   * @ordered
   */
	protected EList<PhysicalLinkEnd> ownedPhysicalLinkEnds;





	/**
   * The cached value of the '{@link #getOwnedPhysicalLinkRealizations() <em>Owned Physical Link Realizations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPhysicalLinkRealizations()
   * @generated
   * @ordered
   */
	protected EList<PhysicalLinkRealization> ownedPhysicalLinkRealizations;





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PhysicalLinkImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CsPackage.Literals.PHYSICAL_LINK;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ConfigurationItem> getAllocatorConfigurationItems() {


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
    EAnnotation annotation = CsPackage.Literals.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ConfigurationItem> resultAsList = (Collection<ConfigurationItem>) result;
    return new EcoreEList.UnmodifiableEList<ConfigurationItem>(this, CsPackage.Literals.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractPhysicalLinkEnd> getLinkEnds() {

    if (linkEnds == null) {
      linkEnds = new EObjectResolvingEList<AbstractPhysicalLinkEnd>(AbstractPhysicalLinkEnd.class, this, CsPackage.PHYSICAL_LINK__LINK_ENDS);
    }
    return linkEnds;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentExchangeFunctionalExchangeAllocation> getOwnedComponentExchangeFunctionalExchangeAllocations() {

    if (ownedComponentExchangeFunctionalExchangeAllocations == null) {
      ownedComponentExchangeFunctionalExchangeAllocations = new EObjectContainmentEList.Resolving<ComponentExchangeFunctionalExchangeAllocation>(ComponentExchangeFunctionalExchangeAllocation.class, this, CsPackage.PHYSICAL_LINK__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS);
    }
    return ownedComponentExchangeFunctionalExchangeAllocations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalLinkEnd> getOwnedPhysicalLinkEnds() {

    if (ownedPhysicalLinkEnds == null) {
      ownedPhysicalLinkEnds = new EObjectContainmentEList.Resolving<PhysicalLinkEnd>(PhysicalLinkEnd.class, this, CsPackage.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_ENDS);
    }
    return ownedPhysicalLinkEnds;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalLinkRealization> getOwnedPhysicalLinkRealizations() {

    if (ownedPhysicalLinkRealizations == null) {
      ownedPhysicalLinkRealizations = new EObjectContainmentEList.Resolving<PhysicalLinkRealization>(PhysicalLinkRealization.class, this, CsPackage.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_REALIZATIONS);
    }
    return ownedPhysicalLinkRealizations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalLinkCategory> getCategories() {


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
    EAnnotation annotation = CsPackage.Literals.PHYSICAL_LINK__CATEGORIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PHYSICAL_LINK__CATEGORIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalLinkCategory> resultAsList = (Collection<PhysicalLinkCategory>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalLinkCategory>(this, CsPackage.Literals.PHYSICAL_LINK__CATEGORIES, resultAsList.size(), resultAsList.toArray());
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

	public PhysicalPort getSourcePhysicalPort() {

    PhysicalPort sourcePhysicalPort = basicGetSourcePhysicalPort();
    return sourcePhysicalPort != null && sourcePhysicalPort.eIsProxy() ? (PhysicalPort)eResolveProxy((InternalEObject)sourcePhysicalPort) : sourcePhysicalPort;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public PhysicalPort basicGetSourcePhysicalPort() {


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
    EAnnotation annotation = CsPackage.Literals.PHYSICAL_LINK__SOURCE_PHYSICAL_PORT.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PHYSICAL_LINK__SOURCE_PHYSICAL_PORT, annotation);
    
    try {
      return (PhysicalPort) result;
    } catch (ClassCastException exception) {
       exception.printStackTrace();
      return null;
    }
    
  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public PhysicalPort getTargetPhysicalPort() {

    PhysicalPort targetPhysicalPort = basicGetTargetPhysicalPort();
    return targetPhysicalPort != null && targetPhysicalPort.eIsProxy() ? (PhysicalPort)eResolveProxy((InternalEObject)targetPhysicalPort) : targetPhysicalPort;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public PhysicalPort basicGetTargetPhysicalPort() {


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
    EAnnotation annotation = CsPackage.Literals.PHYSICAL_LINK__TARGET_PHYSICAL_PORT.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PHYSICAL_LINK__TARGET_PHYSICAL_PORT, annotation);
    
    try {
      return (PhysicalPort) result;
    } catch (ClassCastException exception) {
       exception.printStackTrace();
      return null;
    }
    
  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalLink> getRealizedPhysicalLinks() {


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
    EAnnotation annotation = CsPackage.Literals.PHYSICAL_LINK__REALIZED_PHYSICAL_LINKS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PHYSICAL_LINK__REALIZED_PHYSICAL_LINKS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalLink> resultAsList = (Collection<PhysicalLink>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalLink>(this, CsPackage.Literals.PHYSICAL_LINK__REALIZED_PHYSICAL_LINKS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalLink> getRealizingPhysicalLinks() {


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
    EAnnotation annotation = CsPackage.Literals.PHYSICAL_LINK__REALIZING_PHYSICAL_LINKS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PHYSICAL_LINK__REALIZING_PHYSICAL_LINKS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalLink> resultAsList = (Collection<PhysicalLink>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalLink>(this, CsPackage.Literals.PHYSICAL_LINK__REALIZING_PHYSICAL_LINKS, resultAsList.size(), resultAsList.toArray());
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
      case CsPackage.PHYSICAL_LINK__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS:
        return ((InternalEList<?>)getOwnedComponentExchangeFunctionalExchangeAllocations()).basicRemove(otherEnd, msgs);
      case CsPackage.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_ENDS:
        return ((InternalEList<?>)getOwnedPhysicalLinkEnds()).basicRemove(otherEnd, msgs);
      case CsPackage.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_REALIZATIONS:
        return ((InternalEList<?>)getOwnedPhysicalLinkRealizations()).basicRemove(otherEnd, msgs);
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
      case CsPackage.PHYSICAL_LINK__ALLOCATOR_CONFIGURATION_ITEMS:
        return getAllocatorConfigurationItems();
      case CsPackage.PHYSICAL_LINK__INVOLVING_INVOLVEMENTS:
        return getInvolvingInvolvements();
      case CsPackage.PHYSICAL_LINK__LINK_ENDS:
        return getLinkEnds();
      case CsPackage.PHYSICAL_LINK__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS:
        return getOwnedComponentExchangeFunctionalExchangeAllocations();
      case CsPackage.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_ENDS:
        return getOwnedPhysicalLinkEnds();
      case CsPackage.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_REALIZATIONS:
        return getOwnedPhysicalLinkRealizations();
      case CsPackage.PHYSICAL_LINK__CATEGORIES:
        return getCategories();
      case CsPackage.PHYSICAL_LINK__SOURCE_PHYSICAL_PORT:
        if (resolve) return getSourcePhysicalPort();
        return basicGetSourcePhysicalPort();
      case CsPackage.PHYSICAL_LINK__TARGET_PHYSICAL_PORT:
        if (resolve) return getTargetPhysicalPort();
        return basicGetTargetPhysicalPort();
      case CsPackage.PHYSICAL_LINK__REALIZED_PHYSICAL_LINKS:
        return getRealizedPhysicalLinks();
      case CsPackage.PHYSICAL_LINK__REALIZING_PHYSICAL_LINKS:
        return getRealizingPhysicalLinks();
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
      case CsPackage.PHYSICAL_LINK__LINK_ENDS:
        getLinkEnds().clear();
        getLinkEnds().addAll((Collection<? extends AbstractPhysicalLinkEnd>)newValue);
        return;
      case CsPackage.PHYSICAL_LINK__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS:
        getOwnedComponentExchangeFunctionalExchangeAllocations().clear();
        getOwnedComponentExchangeFunctionalExchangeAllocations().addAll((Collection<? extends ComponentExchangeFunctionalExchangeAllocation>)newValue);
        return;
      case CsPackage.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_ENDS:
        getOwnedPhysicalLinkEnds().clear();
        getOwnedPhysicalLinkEnds().addAll((Collection<? extends PhysicalLinkEnd>)newValue);
        return;
      case CsPackage.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_REALIZATIONS:
        getOwnedPhysicalLinkRealizations().clear();
        getOwnedPhysicalLinkRealizations().addAll((Collection<? extends PhysicalLinkRealization>)newValue);
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
      case CsPackage.PHYSICAL_LINK__LINK_ENDS:
        getLinkEnds().clear();
        return;
      case CsPackage.PHYSICAL_LINK__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS:
        getOwnedComponentExchangeFunctionalExchangeAllocations().clear();
        return;
      case CsPackage.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_ENDS:
        getOwnedPhysicalLinkEnds().clear();
        return;
      case CsPackage.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_REALIZATIONS:
        getOwnedPhysicalLinkRealizations().clear();
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
      case CsPackage.PHYSICAL_LINK__ALLOCATOR_CONFIGURATION_ITEMS:
        return !getAllocatorConfigurationItems().isEmpty();
      case CsPackage.PHYSICAL_LINK__INVOLVING_INVOLVEMENTS:
        return !getInvolvingInvolvements().isEmpty();
      case CsPackage.PHYSICAL_LINK__LINK_ENDS:
        return linkEnds != null && !linkEnds.isEmpty();
      case CsPackage.PHYSICAL_LINK__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS:
        return ownedComponentExchangeFunctionalExchangeAllocations != null && !ownedComponentExchangeFunctionalExchangeAllocations.isEmpty();
      case CsPackage.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_ENDS:
        return ownedPhysicalLinkEnds != null && !ownedPhysicalLinkEnds.isEmpty();
      case CsPackage.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_REALIZATIONS:
        return ownedPhysicalLinkRealizations != null && !ownedPhysicalLinkRealizations.isEmpty();
      case CsPackage.PHYSICAL_LINK__CATEGORIES:
        return !getCategories().isEmpty();
      case CsPackage.PHYSICAL_LINK__SOURCE_PHYSICAL_PORT:
        return basicGetSourcePhysicalPort() != null;
      case CsPackage.PHYSICAL_LINK__TARGET_PHYSICAL_PORT:
        return basicGetTargetPhysicalPort() != null;
      case CsPackage.PHYSICAL_LINK__REALIZED_PHYSICAL_LINKS:
        return !getRealizedPhysicalLinks().isEmpty();
      case CsPackage.PHYSICAL_LINK__REALIZING_PHYSICAL_LINKS:
        return !getRealizingPhysicalLinks().isEmpty();
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
    if (baseClass == AbstractPhysicalArtifact.class) {
      switch (derivedFeatureID) {
        case CsPackage.PHYSICAL_LINK__ALLOCATOR_CONFIGURATION_ITEMS: return CsPackage.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS;
        default: return -1;
      }
    }
    if (baseClass == InvolvedElement.class) {
      switch (derivedFeatureID) {
        case CsPackage.PHYSICAL_LINK__INVOLVING_INVOLVEMENTS: return CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS;
        default: return -1;
      }
    }
    if (baseClass == AbstractPathInvolvedElement.class) {
      switch (derivedFeatureID) {
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
    if (baseClass == AbstractPhysicalArtifact.class) {
      switch (baseFeatureID) {
        case CsPackage.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS: return CsPackage.PHYSICAL_LINK__ALLOCATOR_CONFIGURATION_ITEMS;
        default: return -1;
      }
    }
    if (baseClass == InvolvedElement.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS: return CsPackage.PHYSICAL_LINK__INVOLVING_INVOLVEMENTS;
        default: return -1;
      }
    }
    if (baseClass == AbstractPathInvolvedElement.class) {
      switch (baseFeatureID) {
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }


} //PhysicalLinkImpl