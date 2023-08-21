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
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.NamingRule;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ExchangeLink;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl#getOwnedTraces <em>Owned Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl#getContainedGenericTraces <em>Contained Generic Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl#getNamingRules <em>Naming Rules</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl#getOwnedPropertyValuePkgs <em>Owned Property Value Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl#getOwnedParts <em>Owned Parts</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl#getOwnedComponentExchanges <em>Owned Component Exchanges</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl#getOwnedComponentExchangeCategories <em>Owned Component Exchange Categories</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl#getOwnedFunctionalLinks <em>Owned Functional Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl#getOwnedFunctionalAllocations <em>Owned Functional Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl#getOwnedComponentExchangeRealizations <em>Owned Component Exchange Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl#getOwnedPhysicalLinks <em>Owned Physical Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl#getOwnedPhysicalLinkCategories <em>Owned Physical Link Categories</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl#getOwnedStateMachines <em>Owned State Machines</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ComponentPkgImpl extends NamedElementImpl implements ComponentPkg {

	/**
   * The cached value of the '{@link #getOwnedTraces() <em>Owned Traces</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedTraces()
   * @generated
   * @ordered
   */
	protected EList<Trace> ownedTraces;
	/**
   * The cached value of the '{@link #getNamingRules() <em>Naming Rules</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getNamingRules()
   * @generated
   * @ordered
   */
	protected EList<NamingRule> namingRules;
	/**
   * The cached value of the '{@link #getOwnedPropertyValuePkgs() <em>Owned Property Value Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPropertyValuePkgs()
   * @generated
   * @ordered
   */
	protected EList<PropertyValuePkg> ownedPropertyValuePkgs;
	/**
   * The cached value of the '{@link #getOwnedParts() <em>Owned Parts</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedParts()
   * @generated
   * @ordered
   */
	protected EList<Part> ownedParts;

	/**
   * The cached value of the '{@link #getOwnedComponentExchanges() <em>Owned Component Exchanges</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedComponentExchanges()
   * @generated
   * @ordered
   */
	protected EList<ComponentExchange> ownedComponentExchanges;
	/**
   * The cached value of the '{@link #getOwnedComponentExchangeCategories() <em>Owned Component Exchange Categories</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedComponentExchangeCategories()
   * @generated
   * @ordered
   */
	protected EList<ComponentExchangeCategory> ownedComponentExchangeCategories;
	/**
   * The cached value of the '{@link #getOwnedFunctionalLinks() <em>Owned Functional Links</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedFunctionalLinks()
   * @generated
   * @ordered
   */
	protected EList<ExchangeLink> ownedFunctionalLinks;
	/**
   * The cached value of the '{@link #getOwnedFunctionalAllocations() <em>Owned Functional Allocations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedFunctionalAllocations()
   * @generated
   * @ordered
   */
	protected EList<ComponentFunctionalAllocation> ownedFunctionalAllocations;
	/**
   * The cached value of the '{@link #getOwnedComponentExchangeRealizations() <em>Owned Component Exchange Realizations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedComponentExchangeRealizations()
   * @generated
   * @ordered
   */
	protected EList<ComponentExchangeRealization> ownedComponentExchangeRealizations;
	/**
   * The cached value of the '{@link #getOwnedPhysicalLinks() <em>Owned Physical Links</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPhysicalLinks()
   * @generated
   * @ordered
   */
	protected EList<PhysicalLink> ownedPhysicalLinks;
	/**
   * The cached value of the '{@link #getOwnedPhysicalLinkCategories() <em>Owned Physical Link Categories</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPhysicalLinkCategories()
   * @generated
   * @ordered
   */
	protected EList<PhysicalLinkCategory> ownedPhysicalLinkCategories;
	/**
   * The cached value of the '{@link #getOwnedStateMachines() <em>Owned State Machines</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedStateMachines()
   * @generated
   * @ordered
   */
	protected EList<StateMachine> ownedStateMachines;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ComponentPkgImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CsPackage.Literals.COMPONENT_PKG;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Trace> getOwnedTraces() {

    if (ownedTraces == null) {
      ownedTraces = new EObjectContainmentEList<Trace>(Trace.class, this, CsPackage.COMPONENT_PKG__OWNED_TRACES);
    }
    return ownedTraces;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<GenericTrace> getContainedGenericTraces() {


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
    EAnnotation annotation = CapellacorePackage.Literals.NAMESPACE__CONTAINED_GENERIC_TRACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.NAMESPACE__CONTAINED_GENERIC_TRACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<GenericTrace> resultAsList = (Collection<GenericTrace>) result;
    return new EcoreEList.UnmodifiableEList<GenericTrace>(this, CapellacorePackage.Literals.NAMESPACE__CONTAINED_GENERIC_TRACES, resultAsList.size(), resultAsList.toArray());
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

	public EList<NamingRule> getNamingRules() {

    if (namingRules == null) {
      namingRules = new EObjectContainmentEList<NamingRule>(NamingRule.class, this, CsPackage.COMPONENT_PKG__NAMING_RULES);
    }
    return namingRules;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PropertyValuePkg> getOwnedPropertyValuePkgs() {

    if (ownedPropertyValuePkgs == null) {
      ownedPropertyValuePkgs = new EObjectContainmentEList.Resolving<PropertyValuePkg>(PropertyValuePkg.class, this, CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUE_PKGS);
    }
    return ownedPropertyValuePkgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Part> getOwnedParts() {

    if (ownedParts == null) {
      ownedParts = new EObjectContainmentEList.Resolving<Part>(Part.class, this, CsPackage.COMPONENT_PKG__OWNED_PARTS);
    }
    return ownedParts;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentExchange> getOwnedComponentExchanges() {

    if (ownedComponentExchanges == null) {
      ownedComponentExchanges = new EObjectContainmentEList<ComponentExchange>(ComponentExchange.class, this, CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES);
    }
    return ownedComponentExchanges;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentExchangeCategory> getOwnedComponentExchangeCategories() {

    if (ownedComponentExchangeCategories == null) {
      ownedComponentExchangeCategories = new EObjectContainmentEList<ComponentExchangeCategory>(ComponentExchangeCategory.class, this, CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES);
    }
    return ownedComponentExchangeCategories;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ExchangeLink> getOwnedFunctionalLinks() {

    if (ownedFunctionalLinks == null) {
      ownedFunctionalLinks = new EObjectContainmentEList<ExchangeLink>(ExchangeLink.class, this, CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_LINKS);
    }
    return ownedFunctionalLinks;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentFunctionalAllocation> getOwnedFunctionalAllocations() {

    if (ownedFunctionalAllocations == null) {
      ownedFunctionalAllocations = new EObjectContainmentEList.Resolving<ComponentFunctionalAllocation>(ComponentFunctionalAllocation.class, this, CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_ALLOCATIONS);
    }
    return ownedFunctionalAllocations;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentExchangeRealization> getOwnedComponentExchangeRealizations() {

    if (ownedComponentExchangeRealizations == null) {
      ownedComponentExchangeRealizations = new EObjectContainmentEList<ComponentExchangeRealization>(ComponentExchangeRealization.class, this, CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS);
    }
    return ownedComponentExchangeRealizations;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalLink> getOwnedPhysicalLinks() {

    if (ownedPhysicalLinks == null) {
      ownedPhysicalLinks = new EObjectContainmentEList<PhysicalLink>(PhysicalLink.class, this, CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINKS);
    }
    return ownedPhysicalLinks;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalLinkCategory> getOwnedPhysicalLinkCategories() {

    if (ownedPhysicalLinkCategories == null) {
      ownedPhysicalLinkCategories = new EObjectContainmentEList<PhysicalLinkCategory>(PhysicalLinkCategory.class, this, CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINK_CATEGORIES);
    }
    return ownedPhysicalLinkCategories;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<StateMachine> getOwnedStateMachines() {

    if (ownedStateMachines == null) {
      ownedStateMachines = new EObjectContainmentEList<StateMachine>(StateMachine.class, this, CsPackage.COMPONENT_PKG__OWNED_STATE_MACHINES);
    }
    return ownedStateMachines;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CsPackage.COMPONENT_PKG__OWNED_TRACES:
        return ((InternalEList<?>)getOwnedTraces()).basicRemove(otherEnd, msgs);
      case CsPackage.COMPONENT_PKG__NAMING_RULES:
        return ((InternalEList<?>)getNamingRules()).basicRemove(otherEnd, msgs);
      case CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUE_PKGS:
        return ((InternalEList<?>)getOwnedPropertyValuePkgs()).basicRemove(otherEnd, msgs);
      case CsPackage.COMPONENT_PKG__OWNED_PARTS:
        return ((InternalEList<?>)getOwnedParts()).basicRemove(otherEnd, msgs);
      case CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES:
        return ((InternalEList<?>)getOwnedComponentExchanges()).basicRemove(otherEnd, msgs);
      case CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        return ((InternalEList<?>)getOwnedComponentExchangeCategories()).basicRemove(otherEnd, msgs);
      case CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_LINKS:
        return ((InternalEList<?>)getOwnedFunctionalLinks()).basicRemove(otherEnd, msgs);
      case CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_ALLOCATIONS:
        return ((InternalEList<?>)getOwnedFunctionalAllocations()).basicRemove(otherEnd, msgs);
      case CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS:
        return ((InternalEList<?>)getOwnedComponentExchangeRealizations()).basicRemove(otherEnd, msgs);
      case CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINKS:
        return ((InternalEList<?>)getOwnedPhysicalLinks()).basicRemove(otherEnd, msgs);
      case CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINK_CATEGORIES:
        return ((InternalEList<?>)getOwnedPhysicalLinkCategories()).basicRemove(otherEnd, msgs);
      case CsPackage.COMPONENT_PKG__OWNED_STATE_MACHINES:
        return ((InternalEList<?>)getOwnedStateMachines()).basicRemove(otherEnd, msgs);
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
      case CsPackage.COMPONENT_PKG__OWNED_TRACES:
        return getOwnedTraces();
      case CsPackage.COMPONENT_PKG__CONTAINED_GENERIC_TRACES:
        return getContainedGenericTraces();
      case CsPackage.COMPONENT_PKG__NAMING_RULES:
        return getNamingRules();
      case CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUE_PKGS:
        return getOwnedPropertyValuePkgs();
      case CsPackage.COMPONENT_PKG__OWNED_PARTS:
        return getOwnedParts();
      case CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES:
        return getOwnedComponentExchanges();
      case CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        return getOwnedComponentExchangeCategories();
      case CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_LINKS:
        return getOwnedFunctionalLinks();
      case CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_ALLOCATIONS:
        return getOwnedFunctionalAllocations();
      case CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS:
        return getOwnedComponentExchangeRealizations();
      case CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINKS:
        return getOwnedPhysicalLinks();
      case CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINK_CATEGORIES:
        return getOwnedPhysicalLinkCategories();
      case CsPackage.COMPONENT_PKG__OWNED_STATE_MACHINES:
        return getOwnedStateMachines();
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
      case CsPackage.COMPONENT_PKG__OWNED_TRACES:
        getOwnedTraces().clear();
        getOwnedTraces().addAll((Collection<? extends Trace>)newValue);
        return;
      case CsPackage.COMPONENT_PKG__NAMING_RULES:
        getNamingRules().clear();
        getNamingRules().addAll((Collection<? extends NamingRule>)newValue);
        return;
      case CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUE_PKGS:
        getOwnedPropertyValuePkgs().clear();
        getOwnedPropertyValuePkgs().addAll((Collection<? extends PropertyValuePkg>)newValue);
        return;
      case CsPackage.COMPONENT_PKG__OWNED_PARTS:
        getOwnedParts().clear();
        getOwnedParts().addAll((Collection<? extends Part>)newValue);
        return;
      case CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES:
        getOwnedComponentExchanges().clear();
        getOwnedComponentExchanges().addAll((Collection<? extends ComponentExchange>)newValue);
        return;
      case CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        getOwnedComponentExchangeCategories().clear();
        getOwnedComponentExchangeCategories().addAll((Collection<? extends ComponentExchangeCategory>)newValue);
        return;
      case CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_LINKS:
        getOwnedFunctionalLinks().clear();
        getOwnedFunctionalLinks().addAll((Collection<? extends ExchangeLink>)newValue);
        return;
      case CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_ALLOCATIONS:
        getOwnedFunctionalAllocations().clear();
        getOwnedFunctionalAllocations().addAll((Collection<? extends ComponentFunctionalAllocation>)newValue);
        return;
      case CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS:
        getOwnedComponentExchangeRealizations().clear();
        getOwnedComponentExchangeRealizations().addAll((Collection<? extends ComponentExchangeRealization>)newValue);
        return;
      case CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINKS:
        getOwnedPhysicalLinks().clear();
        getOwnedPhysicalLinks().addAll((Collection<? extends PhysicalLink>)newValue);
        return;
      case CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINK_CATEGORIES:
        getOwnedPhysicalLinkCategories().clear();
        getOwnedPhysicalLinkCategories().addAll((Collection<? extends PhysicalLinkCategory>)newValue);
        return;
      case CsPackage.COMPONENT_PKG__OWNED_STATE_MACHINES:
        getOwnedStateMachines().clear();
        getOwnedStateMachines().addAll((Collection<? extends StateMachine>)newValue);
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
      case CsPackage.COMPONENT_PKG__OWNED_TRACES:
        getOwnedTraces().clear();
        return;
      case CsPackage.COMPONENT_PKG__NAMING_RULES:
        getNamingRules().clear();
        return;
      case CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUE_PKGS:
        getOwnedPropertyValuePkgs().clear();
        return;
      case CsPackage.COMPONENT_PKG__OWNED_PARTS:
        getOwnedParts().clear();
        return;
      case CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES:
        getOwnedComponentExchanges().clear();
        return;
      case CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        getOwnedComponentExchangeCategories().clear();
        return;
      case CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_LINKS:
        getOwnedFunctionalLinks().clear();
        return;
      case CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_ALLOCATIONS:
        getOwnedFunctionalAllocations().clear();
        return;
      case CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS:
        getOwnedComponentExchangeRealizations().clear();
        return;
      case CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINKS:
        getOwnedPhysicalLinks().clear();
        return;
      case CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINK_CATEGORIES:
        getOwnedPhysicalLinkCategories().clear();
        return;
      case CsPackage.COMPONENT_PKG__OWNED_STATE_MACHINES:
        getOwnedStateMachines().clear();
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
      case CsPackage.COMPONENT_PKG__OWNED_TRACES:
        return ownedTraces != null && !ownedTraces.isEmpty();
      case CsPackage.COMPONENT_PKG__CONTAINED_GENERIC_TRACES:
        return !getContainedGenericTraces().isEmpty();
      case CsPackage.COMPONENT_PKG__NAMING_RULES:
        return namingRules != null && !namingRules.isEmpty();
      case CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUE_PKGS:
        return ownedPropertyValuePkgs != null && !ownedPropertyValuePkgs.isEmpty();
      case CsPackage.COMPONENT_PKG__OWNED_PARTS:
        return ownedParts != null && !ownedParts.isEmpty();
      case CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES:
        return ownedComponentExchanges != null && !ownedComponentExchanges.isEmpty();
      case CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        return ownedComponentExchangeCategories != null && !ownedComponentExchangeCategories.isEmpty();
      case CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_LINKS:
        return ownedFunctionalLinks != null && !ownedFunctionalLinks.isEmpty();
      case CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_ALLOCATIONS:
        return ownedFunctionalAllocations != null && !ownedFunctionalAllocations.isEmpty();
      case CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS:
        return ownedComponentExchangeRealizations != null && !ownedComponentExchangeRealizations.isEmpty();
      case CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINKS:
        return ownedPhysicalLinks != null && !ownedPhysicalLinks.isEmpty();
      case CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINK_CATEGORIES:
        return ownedPhysicalLinkCategories != null && !ownedPhysicalLinkCategories.isEmpty();
      case CsPackage.COMPONENT_PKG__OWNED_STATE_MACHINES:
        return ownedStateMachines != null && !ownedStateMachines.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //ComponentPkgImpl