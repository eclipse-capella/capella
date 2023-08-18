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
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.impl.AbstractInstanceImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Part</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PartImpl#getIncomingInformationFlows <em>Incoming Information Flows</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PartImpl#getOutgoingInformationFlows <em>Outgoing Information Flows</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PartImpl#getInformationFlows <em>Information Flows</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PartImpl#getDeployingLinks <em>Deploying Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PartImpl#getDeploymentLinks <em>Deployment Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PartImpl#getInvolvingInvolvements <em>Involving Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PartImpl#getProvidedInterfaces <em>Provided Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PartImpl#getRequiredInterfaces <em>Required Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PartImpl#getOwnedDeploymentLinks <em>Owned Deployment Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PartImpl#getDeployedParts <em>Deployed Parts</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PartImpl#getDeployingParts <em>Deploying Parts</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PartImpl#getOwnedAbstractType <em>Owned Abstract Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PartImpl extends AbstractInstanceImpl implements Part {

















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
   * The cached value of the '{@link #getOwnedAbstractType() <em>Owned Abstract Type</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedAbstractType()
   * @generated
   * @ordered
   */
	protected AbstractType ownedAbstractType;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PartImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CsPackage.Literals.PART;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractInformationFlow> getIncomingInformationFlows() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractInformationFlow> resultAsList = (Collection<AbstractInformationFlow>) result;
    return new EcoreEList.UnmodifiableEList<AbstractInformationFlow>(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractInformationFlow> getOutgoingInformationFlows() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractInformationFlow> resultAsList = (Collection<AbstractInformationFlow>) result;
    return new EcoreEList.UnmodifiableEList<AbstractInformationFlow>(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractInformationFlow> getInformationFlows() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractInformationFlow> resultAsList = (Collection<AbstractInformationFlow>) result;
    return new EcoreEList.UnmodifiableEList<AbstractInformationFlow>(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Interface> getProvidedInterfaces() {


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
    EAnnotation annotation = CsPackage.Literals.PART__PROVIDED_INTERFACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PART__PROVIDED_INTERFACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Interface> resultAsList = (Collection<Interface>) result;
    return new EcoreEList.UnmodifiableEList<Interface>(this, CsPackage.Literals.PART__PROVIDED_INTERFACES, resultAsList.size(), resultAsList.toArray());
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

	public EList<Interface> getRequiredInterfaces() {


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
    EAnnotation annotation = CsPackage.Literals.PART__REQUIRED_INTERFACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PART__REQUIRED_INTERFACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Interface> resultAsList = (Collection<Interface>) result;
    return new EcoreEList.UnmodifiableEList<Interface>(this, CsPackage.Literals.PART__REQUIRED_INTERFACES, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractDeploymentLink> getOwnedDeploymentLinks() {

    if (ownedDeploymentLinks == null) {
      ownedDeploymentLinks = new EObjectContainmentEList.Resolving<AbstractDeploymentLink>(AbstractDeploymentLink.class, this, CsPackage.PART__OWNED_DEPLOYMENT_LINKS);
    }
    return ownedDeploymentLinks;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Part> getDeployedParts() {


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
    EAnnotation annotation = CsPackage.Literals.PART__DEPLOYED_PARTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PART__DEPLOYED_PARTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Part> resultAsList = (Collection<Part>) result;
    return new EcoreEList.UnmodifiableEList<Part>(this, CsPackage.Literals.PART__DEPLOYED_PARTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Part> getDeployingParts() {


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
    EAnnotation annotation = CsPackage.Literals.PART__DEPLOYING_PARTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PART__DEPLOYING_PARTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Part> resultAsList = (Collection<Part>) result;
    return new EcoreEList.UnmodifiableEList<Part>(this, CsPackage.Literals.PART__DEPLOYING_PARTS, resultAsList.size(), resultAsList.toArray());
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

	public AbstractType getOwnedAbstractType() {

    if (ownedAbstractType != null && ownedAbstractType.eIsProxy()) {
      InternalEObject oldOwnedAbstractType = (InternalEObject)ownedAbstractType;
      ownedAbstractType = (AbstractType)eResolveProxy(oldOwnedAbstractType);
      if (ownedAbstractType != oldOwnedAbstractType) {
        InternalEObject newOwnedAbstractType = (InternalEObject)ownedAbstractType;
        NotificationChain msgs = oldOwnedAbstractType.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.PART__OWNED_ABSTRACT_TYPE, null, null);
        if (newOwnedAbstractType.eInternalContainer() == null) {
          msgs = newOwnedAbstractType.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.PART__OWNED_ABSTRACT_TYPE, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.PART__OWNED_ABSTRACT_TYPE, oldOwnedAbstractType, ownedAbstractType));
      }
    }
    return ownedAbstractType;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractType basicGetOwnedAbstractType() {

    return ownedAbstractType;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedAbstractType(AbstractType newOwnedAbstractType, NotificationChain msgs) {

    AbstractType oldOwnedAbstractType = ownedAbstractType;
    ownedAbstractType = newOwnedAbstractType;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsPackage.PART__OWNED_ABSTRACT_TYPE, oldOwnedAbstractType, newOwnedAbstractType);
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
	public void setOwnedAbstractType(AbstractType newOwnedAbstractType) {

    if (newOwnedAbstractType != ownedAbstractType) {
      NotificationChain msgs = null;
      if (ownedAbstractType != null)
        msgs = ((InternalEObject)ownedAbstractType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.PART__OWNED_ABSTRACT_TYPE, null, msgs);
      if (newOwnedAbstractType != null)
        msgs = ((InternalEObject)newOwnedAbstractType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.PART__OWNED_ABSTRACT_TYPE, null, msgs);
      msgs = basicSetOwnedAbstractType(newOwnedAbstractType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PART__OWNED_ABSTRACT_TYPE, newOwnedAbstractType, newOwnedAbstractType));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CsPackage.PART__OWNED_DEPLOYMENT_LINKS:
        return ((InternalEList<?>)getOwnedDeploymentLinks()).basicRemove(otherEnd, msgs);
      case CsPackage.PART__OWNED_ABSTRACT_TYPE:
        return basicSetOwnedAbstractType(null, msgs);
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
      case CsPackage.PART__INCOMING_INFORMATION_FLOWS:
        return getIncomingInformationFlows();
      case CsPackage.PART__OUTGOING_INFORMATION_FLOWS:
        return getOutgoingInformationFlows();
      case CsPackage.PART__INFORMATION_FLOWS:
        return getInformationFlows();
      case CsPackage.PART__DEPLOYING_LINKS:
        return getDeployingLinks();
      case CsPackage.PART__DEPLOYMENT_LINKS:
        return getDeploymentLinks();
      case CsPackage.PART__INVOLVING_INVOLVEMENTS:
        return getInvolvingInvolvements();
      case CsPackage.PART__PROVIDED_INTERFACES:
        return getProvidedInterfaces();
      case CsPackage.PART__REQUIRED_INTERFACES:
        return getRequiredInterfaces();
      case CsPackage.PART__OWNED_DEPLOYMENT_LINKS:
        return getOwnedDeploymentLinks();
      case CsPackage.PART__DEPLOYED_PARTS:
        return getDeployedParts();
      case CsPackage.PART__DEPLOYING_PARTS:
        return getDeployingParts();
      case CsPackage.PART__OWNED_ABSTRACT_TYPE:
        if (resolve) return getOwnedAbstractType();
        return basicGetOwnedAbstractType();
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
      case CsPackage.PART__OWNED_DEPLOYMENT_LINKS:
        getOwnedDeploymentLinks().clear();
        getOwnedDeploymentLinks().addAll((Collection<? extends AbstractDeploymentLink>)newValue);
        return;
      case CsPackage.PART__OWNED_ABSTRACT_TYPE:
          setOwnedAbstractType((AbstractType)newValue);
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
      case CsPackage.PART__OWNED_DEPLOYMENT_LINKS:
        getOwnedDeploymentLinks().clear();
        return;
      case CsPackage.PART__OWNED_ABSTRACT_TYPE:
        setOwnedAbstractType((AbstractType)null);
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
      case CsPackage.PART__INCOMING_INFORMATION_FLOWS:
        return !getIncomingInformationFlows().isEmpty();
      case CsPackage.PART__OUTGOING_INFORMATION_FLOWS:
        return !getOutgoingInformationFlows().isEmpty();
      case CsPackage.PART__INFORMATION_FLOWS:
        return !getInformationFlows().isEmpty();
      case CsPackage.PART__DEPLOYING_LINKS:
        return !getDeployingLinks().isEmpty();
      case CsPackage.PART__DEPLOYMENT_LINKS:
        return !getDeploymentLinks().isEmpty();
      case CsPackage.PART__INVOLVING_INVOLVEMENTS:
        return !getInvolvingInvolvements().isEmpty();
      case CsPackage.PART__PROVIDED_INTERFACES:
        return !getProvidedInterfaces().isEmpty();
      case CsPackage.PART__REQUIRED_INTERFACES:
        return !getRequiredInterfaces().isEmpty();
      case CsPackage.PART__OWNED_DEPLOYMENT_LINKS:
        return ownedDeploymentLinks != null && !ownedDeploymentLinks.isEmpty();
      case CsPackage.PART__DEPLOYED_PARTS:
        return !getDeployedParts().isEmpty();
      case CsPackage.PART__DEPLOYING_PARTS:
        return !getDeployingParts().isEmpty();
      case CsPackage.PART__OWNED_ABSTRACT_TYPE:
        return ownedAbstractType != null;
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
    if (baseClass == InformationsExchanger.class) {
      switch (derivedFeatureID) {
        case CsPackage.PART__INCOMING_INFORMATION_FLOWS: return ModellingcorePackage.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS;
        case CsPackage.PART__OUTGOING_INFORMATION_FLOWS: return ModellingcorePackage.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS;
        case CsPackage.PART__INFORMATION_FLOWS: return ModellingcorePackage.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS;
        default: return -1;
      }
    }
    if (baseClass == DeployableElement.class) {
      switch (derivedFeatureID) {
        case CsPackage.PART__DEPLOYING_LINKS: return CsPackage.DEPLOYABLE_ELEMENT__DEPLOYING_LINKS;
        default: return -1;
      }
    }
    if (baseClass == DeploymentTarget.class) {
      switch (derivedFeatureID) {
        case CsPackage.PART__DEPLOYMENT_LINKS: return CsPackage.DEPLOYMENT_TARGET__DEPLOYMENT_LINKS;
        default: return -1;
      }
    }
    if (baseClass == InvolvedElement.class) {
      switch (derivedFeatureID) {
        case CsPackage.PART__INVOLVING_INVOLVEMENTS: return CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS;
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
    if (baseClass == InformationsExchanger.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS: return CsPackage.PART__INCOMING_INFORMATION_FLOWS;
        case ModellingcorePackage.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS: return CsPackage.PART__OUTGOING_INFORMATION_FLOWS;
        case ModellingcorePackage.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS: return CsPackage.PART__INFORMATION_FLOWS;
        default: return -1;
      }
    }
    if (baseClass == DeployableElement.class) {
      switch (baseFeatureID) {
        case CsPackage.DEPLOYABLE_ELEMENT__DEPLOYING_LINKS: return CsPackage.PART__DEPLOYING_LINKS;
        default: return -1;
      }
    }
    if (baseClass == DeploymentTarget.class) {
      switch (baseFeatureID) {
        case CsPackage.DEPLOYMENT_TARGET__DEPLOYMENT_LINKS: return CsPackage.PART__DEPLOYMENT_LINKS;
        default: return -1;
      }
    }
    if (baseClass == InvolvedElement.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS: return CsPackage.PART__INVOLVING_INVOLVEMENTS;
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


} //PartImpl