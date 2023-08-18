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

package org.polarsys.capella.core.data.interaction.impl;

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
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.SequenceMessageValuation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sequence Message</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.SequenceMessageImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.SequenceMessageImpl#getExchangeContext <em>Exchange Context</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.SequenceMessageImpl#getSendingEnd <em>Sending End</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.SequenceMessageImpl#getReceivingEnd <em>Receiving End</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.SequenceMessageImpl#getInvokedOperation <em>Invoked Operation</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.SequenceMessageImpl#getExchangedItems <em>Exchanged Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.SequenceMessageImpl#getSendingPart <em>Sending Part</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.SequenceMessageImpl#getReceivingPart <em>Receiving Part</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.SequenceMessageImpl#getSendingFunction <em>Sending Function</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.SequenceMessageImpl#getReceivingFunction <em>Receiving Function</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.SequenceMessageImpl#getOwnedSequenceMessageValuations <em>Owned Sequence Message Valuations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SequenceMessageImpl extends NamedElementImpl implements SequenceMessage {

	/**
   * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
	protected static final MessageKind KIND_EDEFAULT = MessageKind.UNSET;

	/**
   * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
	protected MessageKind kind = KIND_EDEFAULT;





	/**
   * The cached value of the '{@link #getExchangeContext() <em>Exchange Context</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getExchangeContext()
   * @generated
   * @ordered
   */
	protected Constraint exchangeContext;





	/**
   * The cached value of the '{@link #getSendingEnd() <em>Sending End</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSendingEnd()
   * @generated
   * @ordered
   */
	protected MessageEnd sendingEnd;





	/**
   * The cached value of the '{@link #getReceivingEnd() <em>Receiving End</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getReceivingEnd()
   * @generated
   * @ordered
   */
	protected MessageEnd receivingEnd;









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
   * The cached value of the '{@link #getOwnedSequenceMessageValuations() <em>Owned Sequence Message Valuations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedSequenceMessageValuations()
   * @generated
   * @ordered
   */
	protected EList<SequenceMessageValuation> ownedSequenceMessageValuations;




















	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected SequenceMessageImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InteractionPackage.Literals.SEQUENCE_MESSAGE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public MessageKind getKind() {

    return kind;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setKind(MessageKind newKind) {

    MessageKind oldKind = kind;
    kind = newKind == null ? KIND_EDEFAULT : newKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.SEQUENCE_MESSAGE__KIND, oldKind, kind));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Constraint getExchangeContext() {

    if (exchangeContext != null && exchangeContext.eIsProxy()) {
      InternalEObject oldExchangeContext = (InternalEObject)exchangeContext;
      exchangeContext = (Constraint)eResolveProxy(oldExchangeContext);
      if (exchangeContext != oldExchangeContext) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.SEQUENCE_MESSAGE__EXCHANGE_CONTEXT, oldExchangeContext, exchangeContext));
      }
    }
    return exchangeContext;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Constraint basicGetExchangeContext() {

    return exchangeContext;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setExchangeContext(Constraint newExchangeContext) {

    Constraint oldExchangeContext = exchangeContext;
    exchangeContext = newExchangeContext;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.SEQUENCE_MESSAGE__EXCHANGE_CONTEXT, oldExchangeContext, exchangeContext));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public MessageEnd getSendingEnd() {

    if (sendingEnd != null && sendingEnd.eIsProxy()) {
      InternalEObject oldSendingEnd = (InternalEObject)sendingEnd;
      sendingEnd = (MessageEnd)eResolveProxy(oldSendingEnd);
      if (sendingEnd != oldSendingEnd) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.SEQUENCE_MESSAGE__SENDING_END, oldSendingEnd, sendingEnd));
      }
    }
    return sendingEnd;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public MessageEnd basicGetSendingEnd() {

    return sendingEnd;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setSendingEnd(MessageEnd newSendingEnd) {

    MessageEnd oldSendingEnd = sendingEnd;
    sendingEnd = newSendingEnd;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.SEQUENCE_MESSAGE__SENDING_END, oldSendingEnd, sendingEnd));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public MessageEnd getReceivingEnd() {

    if (receivingEnd != null && receivingEnd.eIsProxy()) {
      InternalEObject oldReceivingEnd = (InternalEObject)receivingEnd;
      receivingEnd = (MessageEnd)eResolveProxy(oldReceivingEnd);
      if (receivingEnd != oldReceivingEnd) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.SEQUENCE_MESSAGE__RECEIVING_END, oldReceivingEnd, receivingEnd));
      }
    }
    return receivingEnd;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public MessageEnd basicGetReceivingEnd() {

    return receivingEnd;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setReceivingEnd(MessageEnd newReceivingEnd) {

    MessageEnd oldReceivingEnd = receivingEnd;
    receivingEnd = newReceivingEnd;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.SEQUENCE_MESSAGE__RECEIVING_END, oldReceivingEnd, receivingEnd));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractEventOperation getInvokedOperation() {

    AbstractEventOperation invokedOperation = basicGetInvokedOperation();
    return invokedOperation != null && invokedOperation.eIsProxy() ? (AbstractEventOperation)eResolveProxy((InternalEObject)invokedOperation) : invokedOperation;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractEventOperation basicGetInvokedOperation() {


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
    EAnnotation annotation = InteractionPackage.Literals.SEQUENCE_MESSAGE__INVOKED_OPERATION.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.SEQUENCE_MESSAGE__INVOKED_OPERATION, annotation);
    
    try {
      return (AbstractEventOperation) result;
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

	public EList<ExchangeItem> getExchangedItems() {

    if (exchangedItems == null) {
      exchangedItems = new EObjectResolvingEList<ExchangeItem>(ExchangeItem.class, this, InteractionPackage.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    }
    return exchangedItems;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Part getSendingPart() {

    Part sendingPart = basicGetSendingPart();
    return sendingPart != null && sendingPart.eIsProxy() ? (Part)eResolveProxy((InternalEObject)sendingPart) : sendingPart;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Part basicGetSendingPart() {


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
    EAnnotation annotation = InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_PART.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_PART, annotation);
    
    try {
      return (Part) result;
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

	public Part getReceivingPart() {

    Part receivingPart = basicGetReceivingPart();
    return receivingPart != null && receivingPart.eIsProxy() ? (Part)eResolveProxy((InternalEObject)receivingPart) : receivingPart;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Part basicGetReceivingPart() {


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
    EAnnotation annotation = InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_PART.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_PART, annotation);
    
    try {
      return (Part) result;
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

	public AbstractFunction getSendingFunction() {

    AbstractFunction sendingFunction = basicGetSendingFunction();
    return sendingFunction != null && sendingFunction.eIsProxy() ? (AbstractFunction)eResolveProxy((InternalEObject)sendingFunction) : sendingFunction;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractFunction basicGetSendingFunction() {


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
    EAnnotation annotation = InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_FUNCTION.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_FUNCTION, annotation);
    
    try {
      return (AbstractFunction) result;
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

	public AbstractFunction getReceivingFunction() {

    AbstractFunction receivingFunction = basicGetReceivingFunction();
    return receivingFunction != null && receivingFunction.eIsProxy() ? (AbstractFunction)eResolveProxy((InternalEObject)receivingFunction) : receivingFunction;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractFunction basicGetReceivingFunction() {


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
    EAnnotation annotation = InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_FUNCTION.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_FUNCTION, annotation);
    
    try {
      return (AbstractFunction) result;
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

	public EList<SequenceMessageValuation> getOwnedSequenceMessageValuations() {

    if (ownedSequenceMessageValuations == null) {
      ownedSequenceMessageValuations = new EObjectContainmentEList.Resolving<SequenceMessageValuation>(SequenceMessageValuation.class, this, InteractionPackage.SEQUENCE_MESSAGE__OWNED_SEQUENCE_MESSAGE_VALUATIONS);
    }
    return ownedSequenceMessageValuations;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case InteractionPackage.SEQUENCE_MESSAGE__OWNED_SEQUENCE_MESSAGE_VALUATIONS:
        return ((InternalEList<?>)getOwnedSequenceMessageValuations()).basicRemove(otherEnd, msgs);
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
      case InteractionPackage.SEQUENCE_MESSAGE__KIND:
        return getKind();
      case InteractionPackage.SEQUENCE_MESSAGE__EXCHANGE_CONTEXT:
        if (resolve) return getExchangeContext();
        return basicGetExchangeContext();
      case InteractionPackage.SEQUENCE_MESSAGE__SENDING_END:
        if (resolve) return getSendingEnd();
        return basicGetSendingEnd();
      case InteractionPackage.SEQUENCE_MESSAGE__RECEIVING_END:
        if (resolve) return getReceivingEnd();
        return basicGetReceivingEnd();
      case InteractionPackage.SEQUENCE_MESSAGE__INVOKED_OPERATION:
        if (resolve) return getInvokedOperation();
        return basicGetInvokedOperation();
      case InteractionPackage.SEQUENCE_MESSAGE__EXCHANGED_ITEMS:
        return getExchangedItems();
      case InteractionPackage.SEQUENCE_MESSAGE__SENDING_PART:
        if (resolve) return getSendingPart();
        return basicGetSendingPart();
      case InteractionPackage.SEQUENCE_MESSAGE__RECEIVING_PART:
        if (resolve) return getReceivingPart();
        return basicGetReceivingPart();
      case InteractionPackage.SEQUENCE_MESSAGE__SENDING_FUNCTION:
        if (resolve) return getSendingFunction();
        return basicGetSendingFunction();
      case InteractionPackage.SEQUENCE_MESSAGE__RECEIVING_FUNCTION:
        if (resolve) return getReceivingFunction();
        return basicGetReceivingFunction();
      case InteractionPackage.SEQUENCE_MESSAGE__OWNED_SEQUENCE_MESSAGE_VALUATIONS:
        return getOwnedSequenceMessageValuations();
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
      case InteractionPackage.SEQUENCE_MESSAGE__KIND:
          setKind((MessageKind)newValue);
        return;
      case InteractionPackage.SEQUENCE_MESSAGE__EXCHANGE_CONTEXT:
          setExchangeContext((Constraint)newValue);
        return;
      case InteractionPackage.SEQUENCE_MESSAGE__SENDING_END:
          setSendingEnd((MessageEnd)newValue);
        return;
      case InteractionPackage.SEQUENCE_MESSAGE__RECEIVING_END:
          setReceivingEnd((MessageEnd)newValue);
        return;
      case InteractionPackage.SEQUENCE_MESSAGE__EXCHANGED_ITEMS:
        getExchangedItems().clear();
        getExchangedItems().addAll((Collection<? extends ExchangeItem>)newValue);
        return;
      case InteractionPackage.SEQUENCE_MESSAGE__OWNED_SEQUENCE_MESSAGE_VALUATIONS:
        getOwnedSequenceMessageValuations().clear();
        getOwnedSequenceMessageValuations().addAll((Collection<? extends SequenceMessageValuation>)newValue);
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
      case InteractionPackage.SEQUENCE_MESSAGE__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case InteractionPackage.SEQUENCE_MESSAGE__EXCHANGE_CONTEXT:
        setExchangeContext((Constraint)null);
        return;
      case InteractionPackage.SEQUENCE_MESSAGE__SENDING_END:
        setSendingEnd((MessageEnd)null);
        return;
      case InteractionPackage.SEQUENCE_MESSAGE__RECEIVING_END:
        setReceivingEnd((MessageEnd)null);
        return;
      case InteractionPackage.SEQUENCE_MESSAGE__EXCHANGED_ITEMS:
        getExchangedItems().clear();
        return;
      case InteractionPackage.SEQUENCE_MESSAGE__OWNED_SEQUENCE_MESSAGE_VALUATIONS:
        getOwnedSequenceMessageValuations().clear();
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
      case InteractionPackage.SEQUENCE_MESSAGE__KIND:
        return kind != KIND_EDEFAULT;
      case InteractionPackage.SEQUENCE_MESSAGE__EXCHANGE_CONTEXT:
        return exchangeContext != null;
      case InteractionPackage.SEQUENCE_MESSAGE__SENDING_END:
        return sendingEnd != null;
      case InteractionPackage.SEQUENCE_MESSAGE__RECEIVING_END:
        return receivingEnd != null;
      case InteractionPackage.SEQUENCE_MESSAGE__INVOKED_OPERATION:
        return basicGetInvokedOperation() != null;
      case InteractionPackage.SEQUENCE_MESSAGE__EXCHANGED_ITEMS:
        return exchangedItems != null && !exchangedItems.isEmpty();
      case InteractionPackage.SEQUENCE_MESSAGE__SENDING_PART:
        return basicGetSendingPart() != null;
      case InteractionPackage.SEQUENCE_MESSAGE__RECEIVING_PART:
        return basicGetReceivingPart() != null;
      case InteractionPackage.SEQUENCE_MESSAGE__SENDING_FUNCTION:
        return basicGetSendingFunction() != null;
      case InteractionPackage.SEQUENCE_MESSAGE__RECEIVING_FUNCTION:
        return basicGetReceivingFunction() != null;
      case InteractionPackage.SEQUENCE_MESSAGE__OWNED_SEQUENCE_MESSAGE_VALUATIONS:
        return ownedSequenceMessageValuations != null && !ownedSequenceMessageValuations.isEmpty();
    }
    return super.eIsSet(featureID);
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


} //SequenceMessageImpl
