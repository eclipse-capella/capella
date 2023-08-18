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

package org.polarsys.capella.core.data.fa.impl;

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
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ControlNode;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainKind;
import org.polarsys.capella.core.data.fa.FunctionalChainRealization;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.data.la.CapabilityRealization;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Functional Chain</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getInvolvedInvolvements <em>Involved Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getInvolvingInvolvements <em>Involving Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getOwnedFunctionalChainInvolvements <em>Owned Functional Chain Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getOwnedFunctionalChainRealizations <em>Owned Functional Chain Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getInvolvedFunctionalChainInvolvements <em>Involved Functional Chain Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getInvolvedFunctions <em>Involved Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getInvolvedFunctionalExchanges <em>Involved Functional Exchanges</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getInvolvedElements <em>Involved Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getEnactedFunctions <em>Enacted Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getEnactedFunctionalBlocks <em>Enacted Functional Blocks</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getAvailableInStates <em>Available In States</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getFirstFunctionalChainInvolvements <em>First Functional Chain Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getInvolvingCapabilities <em>Involving Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getInvolvingCapabilityRealizations <em>Involving Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getRealizedFunctionalChains <em>Realized Functional Chains</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getRealizingFunctionalChains <em>Realizing Functional Chains</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getPreCondition <em>Pre Condition</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getPostCondition <em>Post Condition</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getOwnedSequenceNodes <em>Owned Sequence Nodes</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl#getOwnedSequenceLinks <em>Owned Sequence Links</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FunctionalChainImpl extends NamedElementImpl implements FunctionalChain {









	/**
   * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
	protected static final FunctionalChainKind KIND_EDEFAULT = FunctionalChainKind.SIMPLE;

	/**
   * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
	protected FunctionalChainKind kind = KIND_EDEFAULT;





	/**
   * The cached value of the '{@link #getOwnedFunctionalChainInvolvements() <em>Owned Functional Chain Involvements</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedFunctionalChainInvolvements()
   * @generated
   * @ordered
   */
	protected EList<FunctionalChainInvolvement> ownedFunctionalChainInvolvements;





	/**
   * The cached value of the '{@link #getOwnedFunctionalChainRealizations() <em>Owned Functional Chain Realizations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedFunctionalChainRealizations()
   * @generated
   * @ordered
   */
	protected EList<FunctionalChainRealization> ownedFunctionalChainRealizations;





























	/**
   * The cached value of the '{@link #getAvailableInStates() <em>Available In States</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAvailableInStates()
   * @generated
   * @ordered
   */
	protected EList<State> availableInStates;
























	/**
   * The cached value of the '{@link #getPreCondition() <em>Pre Condition</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPreCondition()
   * @generated
   * @ordered
   */
	protected Constraint preCondition;

	/**
   * The cached value of the '{@link #getPostCondition() <em>Post Condition</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPostCondition()
   * @generated
   * @ordered
   */
	protected Constraint postCondition;

	/**
   * The cached value of the '{@link #getOwnedSequenceNodes() <em>Owned Sequence Nodes</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedSequenceNodes()
   * @generated
   * @ordered
   */
	protected EList<ControlNode> ownedSequenceNodes;

	/**
   * The cached value of the '{@link #getOwnedSequenceLinks() <em>Owned Sequence Links</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedSequenceLinks()
   * @generated
   * @ordered
   */
	protected EList<SequenceLink> ownedSequenceLinks;
























	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected FunctionalChainImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return FaPackage.Literals.FUNCTIONAL_CHAIN;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Involvement> getInvolvedInvolvements() {


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
    EAnnotation annotation = CapellacorePackage.Literals.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Involvement> resultAsList = (Collection<Involvement>) result;
    return new EcoreEList.UnmodifiableEList<Involvement>(this, CapellacorePackage.Literals.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
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

	public FunctionalChainKind getKind() {

    return kind;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setKind(FunctionalChainKind newKind) {

    FunctionalChainKind oldKind = kind;
    kind = newKind == null ? KIND_EDEFAULT : newKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.FUNCTIONAL_CHAIN__KIND, oldKind, kind));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<FunctionalChainInvolvement> getOwnedFunctionalChainInvolvements() {

    if (ownedFunctionalChainInvolvements == null) {
      ownedFunctionalChainInvolvements = new EObjectContainmentEList.Resolving<FunctionalChainInvolvement>(FunctionalChainInvolvement.class, this, FaPackage.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS);
    }
    return ownedFunctionalChainInvolvements;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<FunctionalChainRealization> getOwnedFunctionalChainRealizations() {

    if (ownedFunctionalChainRealizations == null) {
      ownedFunctionalChainRealizations = new EObjectContainmentEList.Resolving<FunctionalChainRealization>(FunctionalChainRealization.class, this, FaPackage.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS);
    }
    return ownedFunctionalChainRealizations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<FunctionalChainInvolvement> getInvolvedFunctionalChainInvolvements() {


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
    EAnnotation annotation = FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_CHAIN_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_CHAIN_INVOLVEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<FunctionalChainInvolvement> resultAsList = (Collection<FunctionalChainInvolvement>) result;
    return new EcoreEList.UnmodifiableEList<FunctionalChainInvolvement>(this, FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_CHAIN_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractFunction> getInvolvedFunctions() {


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
    EAnnotation annotation = FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractFunction> resultAsList = (Collection<AbstractFunction>) result;
    return new EcoreEList.UnmodifiableEList<AbstractFunction>(this, FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<FunctionalExchange> getInvolvedFunctionalExchanges() {


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
    EAnnotation annotation = FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_EXCHANGES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_EXCHANGES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<FunctionalExchange> resultAsList = (Collection<FunctionalExchange>) result;
    return new EcoreEList.UnmodifiableEList<FunctionalExchange>(this, FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_EXCHANGES, resultAsList.size(), resultAsList.toArray());
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

	public EList<InvolvedElement> getInvolvedElements() {


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
    EAnnotation annotation = FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_ELEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_ELEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<InvolvedElement> resultAsList = (Collection<InvolvedElement>) result;
    return new EcoreEList.UnmodifiableEList<InvolvedElement>(this, FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_ELEMENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractFunction> getEnactedFunctions() {


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
    EAnnotation annotation = FaPackage.Literals.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractFunction> resultAsList = (Collection<AbstractFunction>) result;
    return new EcoreEList.UnmodifiableEList<AbstractFunction>(this, FaPackage.Literals.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractFunctionalBlock> getEnactedFunctionalBlocks() {


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
    EAnnotation annotation = FaPackage.Literals.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONAL_BLOCKS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONAL_BLOCKS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractFunctionalBlock> resultAsList = (Collection<AbstractFunctionalBlock>) result;
    return new EcoreEList.UnmodifiableEList<AbstractFunctionalBlock>(this, FaPackage.Literals.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONAL_BLOCKS, resultAsList.size(), resultAsList.toArray());
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

	public EList<State> getAvailableInStates() {

    if (availableInStates == null) {
      availableInStates = new EObjectResolvingEList<State>(State.class, this, FaPackage.FUNCTIONAL_CHAIN__AVAILABLE_IN_STATES);
    }
    return availableInStates;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<FunctionalChainInvolvement> getFirstFunctionalChainInvolvements() {


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
    EAnnotation annotation = FaPackage.Literals.FUNCTIONAL_CHAIN__FIRST_FUNCTIONAL_CHAIN_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTIONAL_CHAIN__FIRST_FUNCTIONAL_CHAIN_INVOLVEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<FunctionalChainInvolvement> resultAsList = (Collection<FunctionalChainInvolvement>) result;
    return new EcoreEList.UnmodifiableEList<FunctionalChainInvolvement>(this, FaPackage.Literals.FUNCTIONAL_CHAIN__FIRST_FUNCTIONAL_CHAIN_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
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
    EAnnotation annotation = FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Capability> resultAsList = (Collection<Capability>) result;
    return new EcoreEList.UnmodifiableEList<Capability>(this, FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
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

	public EList<CapabilityRealization> getInvolvingCapabilityRealizations() {


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
    EAnnotation annotation = FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITY_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITY_REALIZATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CapabilityRealization> resultAsList = (Collection<CapabilityRealization>) result;
    return new EcoreEList.UnmodifiableEList<CapabilityRealization>(this, FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITY_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<FunctionalChain> getRealizedFunctionalChains() {


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
    EAnnotation annotation = FaPackage.Literals.FUNCTIONAL_CHAIN__REALIZED_FUNCTIONAL_CHAINS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTIONAL_CHAIN__REALIZED_FUNCTIONAL_CHAINS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<FunctionalChain> resultAsList = (Collection<FunctionalChain>) result;
    return new EcoreEList.UnmodifiableEList<FunctionalChain>(this, FaPackage.Literals.FUNCTIONAL_CHAIN__REALIZED_FUNCTIONAL_CHAINS, resultAsList.size(), resultAsList.toArray());
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

	public EList<FunctionalChain> getRealizingFunctionalChains() {


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
    EAnnotation annotation = FaPackage.Literals.FUNCTIONAL_CHAIN__REALIZING_FUNCTIONAL_CHAINS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTIONAL_CHAIN__REALIZING_FUNCTIONAL_CHAINS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<FunctionalChain> resultAsList = (Collection<FunctionalChain>) result;
    return new EcoreEList.UnmodifiableEList<FunctionalChain>(this, FaPackage.Literals.FUNCTIONAL_CHAIN__REALIZING_FUNCTIONAL_CHAINS, resultAsList.size(), resultAsList.toArray());
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

	public Constraint getPreCondition() {

    if (preCondition != null && preCondition.eIsProxy()) {
      InternalEObject oldPreCondition = (InternalEObject)preCondition;
      preCondition = (Constraint)eResolveProxy(oldPreCondition);
      if (preCondition != oldPreCondition) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FaPackage.FUNCTIONAL_CHAIN__PRE_CONDITION, oldPreCondition, preCondition));
      }
    }
    return preCondition;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Constraint basicGetPreCondition() {

    return preCondition;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setPreCondition(Constraint newPreCondition) {

    Constraint oldPreCondition = preCondition;
    preCondition = newPreCondition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.FUNCTIONAL_CHAIN__PRE_CONDITION, oldPreCondition, preCondition));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Constraint getPostCondition() {

    if (postCondition != null && postCondition.eIsProxy()) {
      InternalEObject oldPostCondition = (InternalEObject)postCondition;
      postCondition = (Constraint)eResolveProxy(oldPostCondition);
      if (postCondition != oldPostCondition) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FaPackage.FUNCTIONAL_CHAIN__POST_CONDITION, oldPostCondition, postCondition));
      }
    }
    return postCondition;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Constraint basicGetPostCondition() {

    return postCondition;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setPostCondition(Constraint newPostCondition) {

    Constraint oldPostCondition = postCondition;
    postCondition = newPostCondition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.FUNCTIONAL_CHAIN__POST_CONDITION, oldPostCondition, postCondition));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ControlNode> getOwnedSequenceNodes() {

    if (ownedSequenceNodes == null) {
      ownedSequenceNodes = new EObjectContainmentEList.Resolving<ControlNode>(ControlNode.class, this, FaPackage.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_NODES);
    }
    return ownedSequenceNodes;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<SequenceLink> getOwnedSequenceLinks() {

    if (ownedSequenceLinks == null) {
      ownedSequenceLinks = new EObjectContainmentEList.Resolving<SequenceLink>(SequenceLink.class, this, FaPackage.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_LINKS);
    }
    return ownedSequenceLinks;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS:
        return ((InternalEList<?>)getOwnedFunctionalChainInvolvements()).basicRemove(otherEnd, msgs);
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS:
        return ((InternalEList<?>)getOwnedFunctionalChainRealizations()).basicRemove(otherEnd, msgs);
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_NODES:
        return ((InternalEList<?>)getOwnedSequenceNodes()).basicRemove(otherEnd, msgs);
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_LINKS:
        return ((InternalEList<?>)getOwnedSequenceLinks()).basicRemove(otherEnd, msgs);
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
      case FaPackage.FUNCTIONAL_CHAIN__INVOLVED_INVOLVEMENTS:
        return getInvolvedInvolvements();
      case FaPackage.FUNCTIONAL_CHAIN__INVOLVING_INVOLVEMENTS:
        return getInvolvingInvolvements();
      case FaPackage.FUNCTIONAL_CHAIN__KIND:
        return getKind();
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS:
        return getOwnedFunctionalChainInvolvements();
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS:
        return getOwnedFunctionalChainRealizations();
      case FaPackage.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_CHAIN_INVOLVEMENTS:
        return getInvolvedFunctionalChainInvolvements();
      case FaPackage.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONS:
        return getInvolvedFunctions();
      case FaPackage.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_EXCHANGES:
        return getInvolvedFunctionalExchanges();
      case FaPackage.FUNCTIONAL_CHAIN__INVOLVED_ELEMENTS:
        return getInvolvedElements();
      case FaPackage.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONS:
        return getEnactedFunctions();
      case FaPackage.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONAL_BLOCKS:
        return getEnactedFunctionalBlocks();
      case FaPackage.FUNCTIONAL_CHAIN__AVAILABLE_IN_STATES:
        return getAvailableInStates();
      case FaPackage.FUNCTIONAL_CHAIN__FIRST_FUNCTIONAL_CHAIN_INVOLVEMENTS:
        return getFirstFunctionalChainInvolvements();
      case FaPackage.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITIES:
        return getInvolvingCapabilities();
      case FaPackage.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITY_REALIZATIONS:
        return getInvolvingCapabilityRealizations();
      case FaPackage.FUNCTIONAL_CHAIN__REALIZED_FUNCTIONAL_CHAINS:
        return getRealizedFunctionalChains();
      case FaPackage.FUNCTIONAL_CHAIN__REALIZING_FUNCTIONAL_CHAINS:
        return getRealizingFunctionalChains();
      case FaPackage.FUNCTIONAL_CHAIN__PRE_CONDITION:
        if (resolve) return getPreCondition();
        return basicGetPreCondition();
      case FaPackage.FUNCTIONAL_CHAIN__POST_CONDITION:
        if (resolve) return getPostCondition();
        return basicGetPostCondition();
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_NODES:
        return getOwnedSequenceNodes();
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_LINKS:
        return getOwnedSequenceLinks();
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
      case FaPackage.FUNCTIONAL_CHAIN__KIND:
          setKind((FunctionalChainKind)newValue);
        return;
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS:
        getOwnedFunctionalChainInvolvements().clear();
        getOwnedFunctionalChainInvolvements().addAll((Collection<? extends FunctionalChainInvolvement>)newValue);
        return;
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS:
        getOwnedFunctionalChainRealizations().clear();
        getOwnedFunctionalChainRealizations().addAll((Collection<? extends FunctionalChainRealization>)newValue);
        return;
      case FaPackage.FUNCTIONAL_CHAIN__AVAILABLE_IN_STATES:
        getAvailableInStates().clear();
        getAvailableInStates().addAll((Collection<? extends State>)newValue);
        return;
      case FaPackage.FUNCTIONAL_CHAIN__PRE_CONDITION:
          setPreCondition((Constraint)newValue);
        return;
      case FaPackage.FUNCTIONAL_CHAIN__POST_CONDITION:
          setPostCondition((Constraint)newValue);
        return;
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_NODES:
        getOwnedSequenceNodes().clear();
        getOwnedSequenceNodes().addAll((Collection<? extends ControlNode>)newValue);
        return;
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_LINKS:
        getOwnedSequenceLinks().clear();
        getOwnedSequenceLinks().addAll((Collection<? extends SequenceLink>)newValue);
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
      case FaPackage.FUNCTIONAL_CHAIN__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS:
        getOwnedFunctionalChainInvolvements().clear();
        return;
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS:
        getOwnedFunctionalChainRealizations().clear();
        return;
      case FaPackage.FUNCTIONAL_CHAIN__AVAILABLE_IN_STATES:
        getAvailableInStates().clear();
        return;
      case FaPackage.FUNCTIONAL_CHAIN__PRE_CONDITION:
        setPreCondition((Constraint)null);
        return;
      case FaPackage.FUNCTIONAL_CHAIN__POST_CONDITION:
        setPostCondition((Constraint)null);
        return;
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_NODES:
        getOwnedSequenceNodes().clear();
        return;
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_LINKS:
        getOwnedSequenceLinks().clear();
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
      case FaPackage.FUNCTIONAL_CHAIN__INVOLVED_INVOLVEMENTS:
        return !getInvolvedInvolvements().isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__INVOLVING_INVOLVEMENTS:
        return !getInvolvingInvolvements().isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__KIND:
        return kind != KIND_EDEFAULT;
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS:
        return ownedFunctionalChainInvolvements != null && !ownedFunctionalChainInvolvements.isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS:
        return ownedFunctionalChainRealizations != null && !ownedFunctionalChainRealizations.isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_CHAIN_INVOLVEMENTS:
        return !getInvolvedFunctionalChainInvolvements().isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONS:
        return !getInvolvedFunctions().isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_EXCHANGES:
        return !getInvolvedFunctionalExchanges().isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__INVOLVED_ELEMENTS:
        return !getInvolvedElements().isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONS:
        return !getEnactedFunctions().isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONAL_BLOCKS:
        return !getEnactedFunctionalBlocks().isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__AVAILABLE_IN_STATES:
        return availableInStates != null && !availableInStates.isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__FIRST_FUNCTIONAL_CHAIN_INVOLVEMENTS:
        return !getFirstFunctionalChainInvolvements().isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITIES:
        return !getInvolvingCapabilities().isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITY_REALIZATIONS:
        return !getInvolvingCapabilityRealizations().isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__REALIZED_FUNCTIONAL_CHAINS:
        return !getRealizedFunctionalChains().isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__REALIZING_FUNCTIONAL_CHAINS:
        return !getRealizingFunctionalChains().isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__PRE_CONDITION:
        return preCondition != null;
      case FaPackage.FUNCTIONAL_CHAIN__POST_CONDITION:
        return postCondition != null;
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_NODES:
        return ownedSequenceNodes != null && !ownedSequenceNodes.isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_LINKS:
        return ownedSequenceLinks != null && !ownedSequenceLinks.isEmpty();
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
    if (baseClass == InvolverElement.class) {
      switch (derivedFeatureID) {
        case FaPackage.FUNCTIONAL_CHAIN__INVOLVED_INVOLVEMENTS: return CapellacorePackage.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS;
        default: return -1;
      }
    }
    if (baseClass == InvolvedElement.class) {
      switch (derivedFeatureID) {
        case FaPackage.FUNCTIONAL_CHAIN__INVOLVING_INVOLVEMENTS: return CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS;
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
    if (baseClass == InvolverElement.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS: return FaPackage.FUNCTIONAL_CHAIN__INVOLVED_INVOLVEMENTS;
        default: return -1;
      }
    }
    if (baseClass == InvolvedElement.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS: return FaPackage.FUNCTIONAL_CHAIN__INVOLVING_INVOLVEMENTS;
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
    result.append(')');
    return result.toString();
  }


} //FunctionalChainImpl