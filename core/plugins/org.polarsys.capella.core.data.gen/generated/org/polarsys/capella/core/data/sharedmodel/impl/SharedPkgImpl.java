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

package org.polarsys.capella.core.data.sharedmodel.impl;

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
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.NamingRule;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.information.DataPkg;

import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Shared Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.sharedmodel.impl.SharedPkgImpl#getOwnedTraces <em>Owned Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.sharedmodel.impl.SharedPkgImpl#getContainedGenericTraces <em>Contained Generic Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.sharedmodel.impl.SharedPkgImpl#getNamingRules <em>Naming Rules</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.sharedmodel.impl.SharedPkgImpl#getOwnedPropertyValuePkgs <em>Owned Property Value Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.sharedmodel.impl.SharedPkgImpl#getReuseLinks <em>Reuse Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.sharedmodel.impl.SharedPkgImpl#getOwnedDataPkg <em>Owned Data Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.sharedmodel.impl.SharedPkgImpl#getOwnedGenericPkg <em>Owned Generic Pkg</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SharedPkgImpl extends NamedElementImpl implements SharedPkg {

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
   * The cached value of the '{@link #getReuseLinks() <em>Reuse Links</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getReuseLinks()
   * @generated
   * @ordered
   */
	protected EList<ReuseLink> reuseLinks;





	/**
   * The cached value of the '{@link #getOwnedDataPkg() <em>Owned Data Pkg</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedDataPkg()
   * @generated
   * @ordered
   */
	protected DataPkg ownedDataPkg;





	/**
   * The cached value of the '{@link #getOwnedGenericPkg() <em>Owned Generic Pkg</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedGenericPkg()
   * @generated
   * @ordered
   */
	protected GenericPkg ownedGenericPkg;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected SharedPkgImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return SharedmodelPackage.Literals.SHARED_PKG;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Trace> getOwnedTraces() {

    if (ownedTraces == null) {
      ownedTraces = new EObjectContainmentEList<Trace>(Trace.class, this, SharedmodelPackage.SHARED_PKG__OWNED_TRACES);
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
      namingRules = new EObjectContainmentEList<NamingRule>(NamingRule.class, this, SharedmodelPackage.SHARED_PKG__NAMING_RULES);
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
      ownedPropertyValuePkgs = new EObjectContainmentEList.Resolving<PropertyValuePkg>(PropertyValuePkg.class, this, SharedmodelPackage.SHARED_PKG__OWNED_PROPERTY_VALUE_PKGS);
    }
    return ownedPropertyValuePkgs;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ReuseLink> getReuseLinks() {

    if (reuseLinks == null) {
      reuseLinks = new EObjectResolvingEList<ReuseLink>(ReuseLink.class, this, SharedmodelPackage.SHARED_PKG__REUSE_LINKS);
    }
    return reuseLinks;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataPkg getOwnedDataPkg() {

    return ownedDataPkg;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedDataPkg(DataPkg newOwnedDataPkg, NotificationChain msgs) {

    DataPkg oldOwnedDataPkg = ownedDataPkg;
    ownedDataPkg = newOwnedDataPkg;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SharedmodelPackage.SHARED_PKG__OWNED_DATA_PKG, oldOwnedDataPkg, newOwnedDataPkg);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOwnedDataPkg(DataPkg newOwnedDataPkg) {

    if (newOwnedDataPkg != ownedDataPkg) {
      NotificationChain msgs = null;
      if (ownedDataPkg != null)
        msgs = ((InternalEObject)ownedDataPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SharedmodelPackage.SHARED_PKG__OWNED_DATA_PKG, null, msgs);
      if (newOwnedDataPkg != null)
        msgs = ((InternalEObject)newOwnedDataPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SharedmodelPackage.SHARED_PKG__OWNED_DATA_PKG, null, msgs);
      msgs = basicSetOwnedDataPkg(newOwnedDataPkg, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SharedmodelPackage.SHARED_PKG__OWNED_DATA_PKG, newOwnedDataPkg, newOwnedDataPkg));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public GenericPkg getOwnedGenericPkg() {

    return ownedGenericPkg;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedGenericPkg(GenericPkg newOwnedGenericPkg, NotificationChain msgs) {

    GenericPkg oldOwnedGenericPkg = ownedGenericPkg;
    ownedGenericPkg = newOwnedGenericPkg;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SharedmodelPackage.SHARED_PKG__OWNED_GENERIC_PKG, oldOwnedGenericPkg, newOwnedGenericPkg);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOwnedGenericPkg(GenericPkg newOwnedGenericPkg) {

    if (newOwnedGenericPkg != ownedGenericPkg) {
      NotificationChain msgs = null;
      if (ownedGenericPkg != null)
        msgs = ((InternalEObject)ownedGenericPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SharedmodelPackage.SHARED_PKG__OWNED_GENERIC_PKG, null, msgs);
      if (newOwnedGenericPkg != null)
        msgs = ((InternalEObject)newOwnedGenericPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SharedmodelPackage.SHARED_PKG__OWNED_GENERIC_PKG, null, msgs);
      msgs = basicSetOwnedGenericPkg(newOwnedGenericPkg, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SharedmodelPackage.SHARED_PKG__OWNED_GENERIC_PKG, newOwnedGenericPkg, newOwnedGenericPkg));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case SharedmodelPackage.SHARED_PKG__OWNED_TRACES:
        return ((InternalEList<?>)getOwnedTraces()).basicRemove(otherEnd, msgs);
      case SharedmodelPackage.SHARED_PKG__NAMING_RULES:
        return ((InternalEList<?>)getNamingRules()).basicRemove(otherEnd, msgs);
      case SharedmodelPackage.SHARED_PKG__OWNED_PROPERTY_VALUE_PKGS:
        return ((InternalEList<?>)getOwnedPropertyValuePkgs()).basicRemove(otherEnd, msgs);
      case SharedmodelPackage.SHARED_PKG__OWNED_DATA_PKG:
        return basicSetOwnedDataPkg(null, msgs);
      case SharedmodelPackage.SHARED_PKG__OWNED_GENERIC_PKG:
        return basicSetOwnedGenericPkg(null, msgs);
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
      case SharedmodelPackage.SHARED_PKG__OWNED_TRACES:
        return getOwnedTraces();
      case SharedmodelPackage.SHARED_PKG__CONTAINED_GENERIC_TRACES:
        return getContainedGenericTraces();
      case SharedmodelPackage.SHARED_PKG__NAMING_RULES:
        return getNamingRules();
      case SharedmodelPackage.SHARED_PKG__OWNED_PROPERTY_VALUE_PKGS:
        return getOwnedPropertyValuePkgs();
      case SharedmodelPackage.SHARED_PKG__REUSE_LINKS:
        return getReuseLinks();
      case SharedmodelPackage.SHARED_PKG__OWNED_DATA_PKG:
        return getOwnedDataPkg();
      case SharedmodelPackage.SHARED_PKG__OWNED_GENERIC_PKG:
        return getOwnedGenericPkg();
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
      case SharedmodelPackage.SHARED_PKG__OWNED_TRACES:
        getOwnedTraces().clear();
        getOwnedTraces().addAll((Collection<? extends Trace>)newValue);
        return;
      case SharedmodelPackage.SHARED_PKG__NAMING_RULES:
        getNamingRules().clear();
        getNamingRules().addAll((Collection<? extends NamingRule>)newValue);
        return;
      case SharedmodelPackage.SHARED_PKG__OWNED_PROPERTY_VALUE_PKGS:
        getOwnedPropertyValuePkgs().clear();
        getOwnedPropertyValuePkgs().addAll((Collection<? extends PropertyValuePkg>)newValue);
        return;
      case SharedmodelPackage.SHARED_PKG__REUSE_LINKS:
        getReuseLinks().clear();
        getReuseLinks().addAll((Collection<? extends ReuseLink>)newValue);
        return;
      case SharedmodelPackage.SHARED_PKG__OWNED_DATA_PKG:
          setOwnedDataPkg((DataPkg)newValue);
        return;
      case SharedmodelPackage.SHARED_PKG__OWNED_GENERIC_PKG:
          setOwnedGenericPkg((GenericPkg)newValue);
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
      case SharedmodelPackage.SHARED_PKG__OWNED_TRACES:
        getOwnedTraces().clear();
        return;
      case SharedmodelPackage.SHARED_PKG__NAMING_RULES:
        getNamingRules().clear();
        return;
      case SharedmodelPackage.SHARED_PKG__OWNED_PROPERTY_VALUE_PKGS:
        getOwnedPropertyValuePkgs().clear();
        return;
      case SharedmodelPackage.SHARED_PKG__REUSE_LINKS:
        getReuseLinks().clear();
        return;
      case SharedmodelPackage.SHARED_PKG__OWNED_DATA_PKG:
        setOwnedDataPkg((DataPkg)null);
        return;
      case SharedmodelPackage.SHARED_PKG__OWNED_GENERIC_PKG:
        setOwnedGenericPkg((GenericPkg)null);
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
      case SharedmodelPackage.SHARED_PKG__OWNED_TRACES:
        return ownedTraces != null && !ownedTraces.isEmpty();
      case SharedmodelPackage.SHARED_PKG__CONTAINED_GENERIC_TRACES:
        return !getContainedGenericTraces().isEmpty();
      case SharedmodelPackage.SHARED_PKG__NAMING_RULES:
        return namingRules != null && !namingRules.isEmpty();
      case SharedmodelPackage.SHARED_PKG__OWNED_PROPERTY_VALUE_PKGS:
        return ownedPropertyValuePkgs != null && !ownedPropertyValuePkgs.isEmpty();
      case SharedmodelPackage.SHARED_PKG__REUSE_LINKS:
        return reuseLinks != null && !reuseLinks.isEmpty();
      case SharedmodelPackage.SHARED_PKG__OWNED_DATA_PKG:
        return ownedDataPkg != null;
      case SharedmodelPackage.SHARED_PKG__OWNED_GENERIC_PKG:
        return ownedGenericPkg != null;
    }
    return super.eIsSet(featureID);
  }



} //SharedPkgImpl