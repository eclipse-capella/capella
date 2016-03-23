/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.information.ExchangeItem;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Functional Chain Involvement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementImpl#getInvolver <em>Involver</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementImpl#getInvolved <em>Involved</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementImpl#getNextFunctionalChainInvolvements <em>Next Functional Chain Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementImpl#getPreviousFunctionalChainInvolvements <em>Previous Functional Chain Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementImpl#getExchangedItems <em>Exchanged Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementImpl#getInvolvedElement <em>Involved Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FunctionalChainInvolvementImpl extends RelationshipImpl implements FunctionalChainInvolvement {

	/**
	 * The cached value of the '{@link #getInvolver() <em>Involver</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvolver()
	 * @generated
	 * @ordered
	 */
	protected InvolverElement involver;





	/**
	 * The cached value of the '{@link #getInvolved() <em>Involved</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvolved()
	 * @generated
	 * @ordered
	 */
	protected InvolvedElement involved;





	/**
	 * The cached value of the '{@link #getNextFunctionalChainInvolvements() <em>Next Functional Chain Involvements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNextFunctionalChainInvolvements()
	 * @generated
	 * @ordered
	 */
	protected EList<FunctionalChainInvolvement> nextFunctionalChainInvolvements;









	/**
	 * The cached value of the '{@link #getExchangedItems() <em>Exchanged Items</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExchangedItems()
	 * @generated
	 * @ordered
	 */
	protected EList<ExchangeItem> exchangedItems;








	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FunctionalChainInvolvementImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public InvolverElement getInvolver() {

		if (involver != null && involver.eIsProxy()) {
			InternalEObject oldInvolver = (InternalEObject)involver;
			involver = (InvolverElement)eResolveProxy(oldInvolver);
			if (involver != oldInvolver) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVER, oldInvolver, involver));
			}
		}
		return involver;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public InvolverElement basicGetInvolver() {

		return involver;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setInvolver(InvolverElement newInvolver) {

		InvolverElement oldInvolver = involver;
		involver = newInvolver;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVER, oldInvolver, involver));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public InvolvedElement getInvolved() {

		if (involved != null && involved.eIsProxy()) {
			InternalEObject oldInvolved = (InternalEObject)involved;
			involved = (InvolvedElement)eResolveProxy(oldInvolved);
			if (involved != oldInvolved) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED, oldInvolved, involved));
			}
		}
		return involved;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public InvolvedElement basicGetInvolved() {

		return involved;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setInvolved(InvolvedElement newInvolved) {

		InvolvedElement oldInvolved = involved;
		involved = newInvolved;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED, oldInvolved, involved));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<FunctionalChainInvolvement> getNextFunctionalChainInvolvements() {

		if (nextFunctionalChainInvolvements == null) {
			nextFunctionalChainInvolvements = new EObjectResolvingEList<FunctionalChainInvolvement>(FunctionalChainInvolvement.class, this, FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS);
		}
		return nextFunctionalChainInvolvements;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<FunctionalChainInvolvement> getPreviousFunctionalChainInvolvements() {


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
    EAnnotation annotation = FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<FunctionalChainInvolvement> resultAsList = (Collection<FunctionalChainInvolvement>) result;
		return new EcoreEList.UnmodifiableEList<FunctionalChainInvolvement>(this, FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<ExchangeItem> getExchangedItems() {

		if (exchangedItems == null) {
			exchangedItems = new EObjectResolvingEList<ExchangeItem>(ExchangeItem.class, this, FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__EXCHANGED_ITEMS);
		}
		return exchangedItems;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public InvolvedElement getInvolvedElement() {

		InvolvedElement involvedElement = basicGetInvolvedElement();
		return involvedElement != null && involvedElement.eIsProxy() ? (InvolvedElement)eResolveProxy((InternalEObject)involvedElement) : involvedElement;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public InvolvedElement basicGetInvolvedElement() {


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
    EAnnotation annotation = FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED_ELEMENT.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED_ELEMENT, annotation);
		
		try {
			return (InvolvedElement) result;
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
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVER:
				if (resolve) return getInvolver();
				return basicGetInvolver();
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED:
				if (resolve) return getInvolved();
				return basicGetInvolved();
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS:
				return getNextFunctionalChainInvolvements();
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS:
				return getPreviousFunctionalChainInvolvements();
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__EXCHANGED_ITEMS:
				return getExchangedItems();
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED_ELEMENT:
				if (resolve) return getInvolvedElement();
				return basicGetInvolvedElement();
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
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVER:
				// begin-extension-code
				if (newValue == null || newValue instanceof InvolverElement) {
				// end-extension-code
					setInvolver((InvolverElement)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED:
				// begin-extension-code
				if (newValue == null || newValue instanceof InvolvedElement) {
				// end-extension-code
					setInvolved((InvolvedElement)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS:
				getNextFunctionalChainInvolvements().clear();
				getNextFunctionalChainInvolvements().addAll((Collection<? extends FunctionalChainInvolvement>)newValue);
				return;
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__EXCHANGED_ITEMS:
				getExchangedItems().clear();
				getExchangedItems().addAll((Collection<? extends ExchangeItem>)newValue);
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
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVER:
				setInvolver((InvolverElement)null);
				return;
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED:
				setInvolved((InvolvedElement)null);
				return;
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS:
				getNextFunctionalChainInvolvements().clear();
				return;
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__EXCHANGED_ITEMS:
				getExchangedItems().clear();
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
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVER:
				return involver != null;
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED:
				return involved != null;
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS:
				return nextFunctionalChainInvolvements != null && !nextFunctionalChainInvolvements.isEmpty();
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS:
				return !getPreviousFunctionalChainInvolvements().isEmpty();
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__EXCHANGED_ITEMS:
				return exchangedItems != null && !exchangedItems.isEmpty();
			case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED_ELEMENT:
				return basicGetInvolvedElement() != null;
		}
		return super.eIsSet(featureID);
	}



} //FunctionalChainInvolvementImpl