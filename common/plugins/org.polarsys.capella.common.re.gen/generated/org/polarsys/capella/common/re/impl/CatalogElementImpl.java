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

package org.polarsys.capella.common.re.impl;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.CompliancyDefinition;
import org.polarsys.capella.common.re.ReElementContainer;
import org.polarsys.capella.common.re.RePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Catalog Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementImpl#getOwnedElements <em>Owned Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementImpl#getEnvironment <em>Environment</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementImpl#getSuffix <em>Suffix</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementImpl#getPurpose <em>Purpose</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementImpl#isReadOnly <em>Read Only</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementImpl#getOrigin <em>Origin</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementImpl#getCurrentCompliancy <em>Current Compliancy</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementImpl#getDefaultReplicaCompliancy <em>Default Replica Compliancy</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementImpl#getOwnedLinks <em>Owned Links</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementImpl#getReferencedElements <em>Referenced Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementImpl#getReplicatedElements <em>Replicated Elements</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CatalogElementImpl extends ReDescriptionElementImpl implements CatalogElement {

	/**
   * The cached value of the '{@link #getOwnedElements() <em>Owned Elements</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedElements()
   * @generated
   * @ordered
   */
	protected EList<CatalogElement> ownedElements;





	/**
   * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
	protected static final CatalogElementKind KIND_EDEFAULT = CatalogElementKind.REC;

	/**
   * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
	protected CatalogElementKind kind = KIND_EDEFAULT;





	/**
   * The default value of the '{@link #getAuthor() <em>Author</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAuthor()
   * @generated
   * @ordered
   */
	protected static final String AUTHOR_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getAuthor() <em>Author</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAuthor()
   * @generated
   * @ordered
   */
	protected String author = AUTHOR_EDEFAULT;





	/**
   * The default value of the '{@link #getEnvironment() <em>Environment</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getEnvironment()
   * @generated
   * @ordered
   */
	protected static final String ENVIRONMENT_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getEnvironment() <em>Environment</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getEnvironment()
   * @generated
   * @ordered
   */
	protected String environment = ENVIRONMENT_EDEFAULT;





	/**
   * The default value of the '{@link #getSuffix() <em>Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSuffix()
   * @generated
   * @ordered
   */
  protected static final String SUFFIX_EDEFAULT = null;





  /**
   * The cached value of the '{@link #getSuffix() <em>Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSuffix()
   * @generated
   * @ordered
   */
  protected String suffix = SUFFIX_EDEFAULT;





  /**
   * The default value of the '{@link #getPurpose() <em>Purpose</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPurpose()
   * @generated
   * @ordered
   */
	protected static final String PURPOSE_EDEFAULT = null;





	/**
   * The cached value of the '{@link #getPurpose() <em>Purpose</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPurpose()
   * @generated
   * @ordered
   */
	protected String purpose = PURPOSE_EDEFAULT;





	/**
   * The default value of the '{@link #isReadOnly() <em>Read Only</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isReadOnly()
   * @generated
   * @ordered
   */
	protected static final boolean READ_ONLY_EDEFAULT = false;





	/**
   * The cached value of the '{@link #isReadOnly() <em>Read Only</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isReadOnly()
   * @generated
   * @ordered
   */
	protected boolean readOnly = READ_ONLY_EDEFAULT;





	/**
   * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getVersion()
   * @generated
   * @ordered
   */
	protected static final String VERSION_EDEFAULT = null;





	/**
   * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getVersion()
   * @generated
   * @ordered
   */
	protected String version = VERSION_EDEFAULT;





	/**
   * The cached value of the '{@link #getTags() <em>Tags</em>}' attribute list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTags()
   * @generated
   * @ordered
   */
	protected EList<String> tags;





	/**
   * The cached value of the '{@link #getOrigin() <em>Origin</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOrigin()
   * @generated
   * @ordered
   */
	protected CatalogElement origin;





	/**
   * The cached value of the '{@link #getCurrentCompliancy() <em>Current Compliancy</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getCurrentCompliancy()
   * @generated
   * @ordered
   */
	protected CompliancyDefinition currentCompliancy;





	/**
   * The cached value of the '{@link #getDefaultReplicaCompliancy() <em>Default Replica Compliancy</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getDefaultReplicaCompliancy()
   * @generated
   * @ordered
   */
	protected CompliancyDefinition defaultReplicaCompliancy;





	/**
   * The cached value of the '{@link #getOwnedLinks() <em>Owned Links</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedLinks()
   * @generated
   * @ordered
   */
	protected EList<CatalogElementLink> ownedLinks;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CatalogElementImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return RePackage.Literals.CATALOG_ELEMENT;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CatalogElement> getOwnedElements() {

    if (ownedElements == null) {
      ownedElements = new EObjectContainmentEList.Resolving<CatalogElement>(CatalogElement.class, this, RePackage.CATALOG_ELEMENT__OWNED_ELEMENTS);
    }
    return ownedElements;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CatalogElementKind getKind() {

    return kind;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setKind(CatalogElementKind newKind) {

    CatalogElementKind oldKind = kind;
    kind = newKind == null ? KIND_EDEFAULT : newKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RePackage.CATALOG_ELEMENT__KIND, oldKind, kind));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public String getAuthor() {

    return author;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setAuthor(String newAuthor) {

    String oldAuthor = author;
    author = newAuthor;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RePackage.CATALOG_ELEMENT__AUTHOR, oldAuthor, author));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public String getEnvironment() {

    return environment;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setEnvironment(String newEnvironment) {

    String oldEnvironment = environment;
    environment = newEnvironment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RePackage.CATALOG_ELEMENT__ENVIRONMENT, oldEnvironment, environment));

  }






	/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */

  public String getSuffix() {

    return suffix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */

  @Override
		public void setSuffix(String newSuffix) {

    String oldSuffix = suffix;
    suffix = newSuffix;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RePackage.CATALOG_ELEMENT__SUFFIX, oldSuffix, suffix));

  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public String getPurpose() {

    return purpose;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setPurpose(String newPurpose) {

    String oldPurpose = purpose;
    purpose = newPurpose;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RePackage.CATALOG_ELEMENT__PURPOSE, oldPurpose, purpose));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isReadOnly() {

    return readOnly;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setReadOnly(boolean newReadOnly) {

    boolean oldReadOnly = readOnly;
    readOnly = newReadOnly;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RePackage.CATALOG_ELEMENT__READ_ONLY, oldReadOnly, readOnly));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public String getVersion() {

    return version;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setVersion(String newVersion) {

    String oldVersion = version;
    version = newVersion;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RePackage.CATALOG_ELEMENT__VERSION, oldVersion, version));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<String> getTags() {

    if (tags == null) {
      tags = new EDataTypeUniqueEList<String>(String.class, this, RePackage.CATALOG_ELEMENT__TAGS);
    }
    return tags;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CatalogElement getOrigin() {

    if (origin != null && origin.eIsProxy()) {
      InternalEObject oldOrigin = (InternalEObject)origin;
      origin = (CatalogElement)eResolveProxy(oldOrigin);
      if (origin != oldOrigin) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, RePackage.CATALOG_ELEMENT__ORIGIN, oldOrigin, origin));
      }
    }
    return origin;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CatalogElement basicGetOrigin() {

    return origin;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOrigin(CatalogElement newOrigin) {

    CatalogElement oldOrigin = origin;
    origin = newOrigin;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RePackage.CATALOG_ELEMENT__ORIGIN, oldOrigin, origin));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CompliancyDefinition getCurrentCompliancy() {

    if (currentCompliancy != null && currentCompliancy.eIsProxy()) {
      InternalEObject oldCurrentCompliancy = (InternalEObject)currentCompliancy;
      currentCompliancy = (CompliancyDefinition)eResolveProxy(oldCurrentCompliancy);
      if (currentCompliancy != oldCurrentCompliancy) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, RePackage.CATALOG_ELEMENT__CURRENT_COMPLIANCY, oldCurrentCompliancy, currentCompliancy));
      }
    }
    return currentCompliancy;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CompliancyDefinition basicGetCurrentCompliancy() {

    return currentCompliancy;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setCurrentCompliancy(CompliancyDefinition newCurrentCompliancy) {

    CompliancyDefinition oldCurrentCompliancy = currentCompliancy;
    currentCompliancy = newCurrentCompliancy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RePackage.CATALOG_ELEMENT__CURRENT_COMPLIANCY, oldCurrentCompliancy, currentCompliancy));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CompliancyDefinition getDefaultReplicaCompliancy() {

    if (defaultReplicaCompliancy != null && defaultReplicaCompliancy.eIsProxy()) {
      InternalEObject oldDefaultReplicaCompliancy = (InternalEObject)defaultReplicaCompliancy;
      defaultReplicaCompliancy = (CompliancyDefinition)eResolveProxy(oldDefaultReplicaCompliancy);
      if (defaultReplicaCompliancy != oldDefaultReplicaCompliancy) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, RePackage.CATALOG_ELEMENT__DEFAULT_REPLICA_COMPLIANCY, oldDefaultReplicaCompliancy, defaultReplicaCompliancy));
      }
    }
    return defaultReplicaCompliancy;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CompliancyDefinition basicGetDefaultReplicaCompliancy() {

    return defaultReplicaCompliancy;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setDefaultReplicaCompliancy(CompliancyDefinition newDefaultReplicaCompliancy) {

    CompliancyDefinition oldDefaultReplicaCompliancy = defaultReplicaCompliancy;
    defaultReplicaCompliancy = newDefaultReplicaCompliancy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RePackage.CATALOG_ELEMENT__DEFAULT_REPLICA_COMPLIANCY, oldDefaultReplicaCompliancy, defaultReplicaCompliancy));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CatalogElementLink> getOwnedLinks() {

    if (ownedLinks == null) {
      ownedLinks = new EObjectContainmentEList.Resolving<CatalogElementLink>(CatalogElementLink.class, this, RePackage.CATALOG_ELEMENT__OWNED_LINKS);
    }
    return ownedLinks;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<EObject> getReferencedElements() {


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
    EAnnotation annotation = RePackage.Literals.CATALOG_ELEMENT__REFERENCED_ELEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, RePackage.Literals.CATALOG_ELEMENT__REFERENCED_ELEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<EObject> resultAsList = (Collection<EObject>) result;
    return new EcoreEList.UnmodifiableEList<EObject>(this, RePackage.Literals.CATALOG_ELEMENT__REFERENCED_ELEMENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<CatalogElement> getReplicatedElements() {


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
    EAnnotation annotation = RePackage.Literals.CATALOG_ELEMENT__REPLICATED_ELEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, RePackage.Literals.CATALOG_ELEMENT__REPLICATED_ELEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CatalogElement> resultAsList = (Collection<CatalogElement>) result;
    return new EcoreEList.UnmodifiableEList<CatalogElement>(this, RePackage.Literals.CATALOG_ELEMENT__REPLICATED_ELEMENTS, resultAsList.size(), resultAsList.toArray());
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
      case RePackage.CATALOG_ELEMENT__OWNED_ELEMENTS:
        return ((InternalEList<?>)getOwnedElements()).basicRemove(otherEnd, msgs);
      case RePackage.CATALOG_ELEMENT__OWNED_LINKS:
        return ((InternalEList<?>)getOwnedLinks()).basicRemove(otherEnd, msgs);
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
      case RePackage.CATALOG_ELEMENT__OWNED_ELEMENTS:
        return getOwnedElements();
      case RePackage.CATALOG_ELEMENT__KIND:
        return getKind();
      case RePackage.CATALOG_ELEMENT__AUTHOR:
        return getAuthor();
      case RePackage.CATALOG_ELEMENT__ENVIRONMENT:
        return getEnvironment();
      case RePackage.CATALOG_ELEMENT__SUFFIX:
        return getSuffix();
      case RePackage.CATALOG_ELEMENT__PURPOSE:
        return getPurpose();
      case RePackage.CATALOG_ELEMENT__READ_ONLY:
        return isReadOnly();
      case RePackage.CATALOG_ELEMENT__VERSION:
        return getVersion();
      case RePackage.CATALOG_ELEMENT__TAGS:
        return getTags();
      case RePackage.CATALOG_ELEMENT__ORIGIN:
        if (resolve) return getOrigin();
        return basicGetOrigin();
      case RePackage.CATALOG_ELEMENT__CURRENT_COMPLIANCY:
        if (resolve) return getCurrentCompliancy();
        return basicGetCurrentCompliancy();
      case RePackage.CATALOG_ELEMENT__DEFAULT_REPLICA_COMPLIANCY:
        if (resolve) return getDefaultReplicaCompliancy();
        return basicGetDefaultReplicaCompliancy();
      case RePackage.CATALOG_ELEMENT__OWNED_LINKS:
        return getOwnedLinks();
      case RePackage.CATALOG_ELEMENT__REFERENCED_ELEMENTS:
        return getReferencedElements();
      case RePackage.CATALOG_ELEMENT__REPLICATED_ELEMENTS:
        return getReplicatedElements();
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
      case RePackage.CATALOG_ELEMENT__OWNED_ELEMENTS:
        getOwnedElements().clear();
        getOwnedElements().addAll((Collection<? extends CatalogElement>)newValue);
        return;
      case RePackage.CATALOG_ELEMENT__KIND:
          setKind((CatalogElementKind)newValue);
        return;
      case RePackage.CATALOG_ELEMENT__AUTHOR:
          setAuthor((String)newValue);
        return;
      case RePackage.CATALOG_ELEMENT__ENVIRONMENT:
          setEnvironment((String)newValue);
        return;
      case RePackage.CATALOG_ELEMENT__SUFFIX:
          setSuffix((String)newValue);
        return;
      case RePackage.CATALOG_ELEMENT__PURPOSE:
          setPurpose((String)newValue);
        return;
      case RePackage.CATALOG_ELEMENT__READ_ONLY:
          setReadOnly((Boolean)newValue);
        return;
      case RePackage.CATALOG_ELEMENT__VERSION:
          setVersion((String)newValue);
        return;
      case RePackage.CATALOG_ELEMENT__TAGS:
        getTags().clear();
        getTags().addAll((Collection<? extends String>)newValue);
        return;
      case RePackage.CATALOG_ELEMENT__ORIGIN:
          setOrigin((CatalogElement)newValue);
        return;
      case RePackage.CATALOG_ELEMENT__CURRENT_COMPLIANCY:
          setCurrentCompliancy((CompliancyDefinition)newValue);
        return;
      case RePackage.CATALOG_ELEMENT__DEFAULT_REPLICA_COMPLIANCY:
          setDefaultReplicaCompliancy((CompliancyDefinition)newValue);
        return;
      case RePackage.CATALOG_ELEMENT__OWNED_LINKS:
        getOwnedLinks().clear();
        getOwnedLinks().addAll((Collection<? extends CatalogElementLink>)newValue);
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
      case RePackage.CATALOG_ELEMENT__OWNED_ELEMENTS:
        getOwnedElements().clear();
        return;
      case RePackage.CATALOG_ELEMENT__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case RePackage.CATALOG_ELEMENT__AUTHOR:
        setAuthor(AUTHOR_EDEFAULT);
        return;
      case RePackage.CATALOG_ELEMENT__ENVIRONMENT:
        setEnvironment(ENVIRONMENT_EDEFAULT);
        return;
      case RePackage.CATALOG_ELEMENT__SUFFIX:
        setSuffix(SUFFIX_EDEFAULT);
        return;
      case RePackage.CATALOG_ELEMENT__PURPOSE:
        setPurpose(PURPOSE_EDEFAULT);
        return;
      case RePackage.CATALOG_ELEMENT__READ_ONLY:
        setReadOnly(READ_ONLY_EDEFAULT);
        return;
      case RePackage.CATALOG_ELEMENT__VERSION:
        setVersion(VERSION_EDEFAULT);
        return;
      case RePackage.CATALOG_ELEMENT__TAGS:
        getTags().clear();
        return;
      case RePackage.CATALOG_ELEMENT__ORIGIN:
        setOrigin((CatalogElement)null);
        return;
      case RePackage.CATALOG_ELEMENT__CURRENT_COMPLIANCY:
        setCurrentCompliancy((CompliancyDefinition)null);
        return;
      case RePackage.CATALOG_ELEMENT__DEFAULT_REPLICA_COMPLIANCY:
        setDefaultReplicaCompliancy((CompliancyDefinition)null);
        return;
      case RePackage.CATALOG_ELEMENT__OWNED_LINKS:
        getOwnedLinks().clear();
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
      case RePackage.CATALOG_ELEMENT__OWNED_ELEMENTS:
        return ownedElements != null && !ownedElements.isEmpty();
      case RePackage.CATALOG_ELEMENT__KIND:
        return kind != KIND_EDEFAULT;
      case RePackage.CATALOG_ELEMENT__AUTHOR:
        return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
      case RePackage.CATALOG_ELEMENT__ENVIRONMENT:
        return ENVIRONMENT_EDEFAULT == null ? environment != null : !ENVIRONMENT_EDEFAULT.equals(environment);
      case RePackage.CATALOG_ELEMENT__SUFFIX:
        return SUFFIX_EDEFAULT == null ? suffix != null : !SUFFIX_EDEFAULT.equals(suffix);
      case RePackage.CATALOG_ELEMENT__PURPOSE:
        return PURPOSE_EDEFAULT == null ? purpose != null : !PURPOSE_EDEFAULT.equals(purpose);
      case RePackage.CATALOG_ELEMENT__READ_ONLY:
        return readOnly != READ_ONLY_EDEFAULT;
      case RePackage.CATALOG_ELEMENT__VERSION:
        return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
      case RePackage.CATALOG_ELEMENT__TAGS:
        return tags != null && !tags.isEmpty();
      case RePackage.CATALOG_ELEMENT__ORIGIN:
        return origin != null;
      case RePackage.CATALOG_ELEMENT__CURRENT_COMPLIANCY:
        return currentCompliancy != null;
      case RePackage.CATALOG_ELEMENT__DEFAULT_REPLICA_COMPLIANCY:
        return defaultReplicaCompliancy != null;
      case RePackage.CATALOG_ELEMENT__OWNED_LINKS:
        return ownedLinks != null && !ownedLinks.isEmpty();
      case RePackage.CATALOG_ELEMENT__REFERENCED_ELEMENTS:
        return !getReferencedElements().isEmpty();
      case RePackage.CATALOG_ELEMENT__REPLICATED_ELEMENTS:
        return !getReplicatedElements().isEmpty();
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
    if (baseClass == ReElementContainer.class) {
      switch (derivedFeatureID) {
        case RePackage.CATALOG_ELEMENT__OWNED_ELEMENTS: return RePackage.RE_ELEMENT_CONTAINER__OWNED_ELEMENTS;
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
    if (baseClass == ReElementContainer.class) {
      switch (baseFeatureID) {
        case RePackage.RE_ELEMENT_CONTAINER__OWNED_ELEMENTS: return RePackage.CATALOG_ELEMENT__OWNED_ELEMENTS;
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
    result.append(" (kind: "); //$NON-NLS-1$
    result.append(kind);
    result.append(", author: "); //$NON-NLS-1$
    result.append(author);
    result.append(", environment: "); //$NON-NLS-1$
    result.append(environment);
    result.append(", suffix: "); //$NON-NLS-1$
    result.append(suffix);
    result.append(", purpose: "); //$NON-NLS-1$
    result.append(purpose);
    result.append(", readOnly: "); //$NON-NLS-1$
    result.append(readOnly);
    result.append(", version: "); //$NON-NLS-1$
    result.append(version);
    result.append(", tags: "); //$NON-NLS-1$
    result.append(tags);
    result.append(')');
    return result.toString();
  }


} //CatalogElementImpl