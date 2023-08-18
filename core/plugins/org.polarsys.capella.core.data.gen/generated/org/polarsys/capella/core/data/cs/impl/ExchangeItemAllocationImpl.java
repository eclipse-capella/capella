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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exchange Item Allocation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ExchangeItemAllocationImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ExchangeItemAllocationImpl#getInvokingSequenceMessages <em>Invoking Sequence Messages</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ExchangeItemAllocationImpl#isFinal <em>Final</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ExchangeItemAllocationImpl#getSendProtocol <em>Send Protocol</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ExchangeItemAllocationImpl#getReceiveProtocol <em>Receive Protocol</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ExchangeItemAllocationImpl#getAllocatedItem <em>Allocated Item</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ExchangeItemAllocationImpl#getAllocatingInterface <em>Allocating Interface</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExchangeItemAllocationImpl extends RelationshipImpl implements ExchangeItemAllocation {

	/**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
	protected static final String NAME_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
	protected String name = NAME_EDEFAULT;





	/**
   * The default value of the '{@link #isFinal() <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isFinal()
   * @generated
   * @ordered
   */
	protected static final boolean FINAL_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isFinal() <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isFinal()
   * @generated
   * @ordered
   */
	protected boolean final_ = FINAL_EDEFAULT;





	/**
   * The default value of the '{@link #getSendProtocol() <em>Send Protocol</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSendProtocol()
   * @generated
   * @ordered
   */
	protected static final CommunicationLinkProtocol SEND_PROTOCOL_EDEFAULT = CommunicationLinkProtocol.UNSET;

	/**
   * The cached value of the '{@link #getSendProtocol() <em>Send Protocol</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSendProtocol()
   * @generated
   * @ordered
   */
	protected CommunicationLinkProtocol sendProtocol = SEND_PROTOCOL_EDEFAULT;





	/**
   * The default value of the '{@link #getReceiveProtocol() <em>Receive Protocol</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getReceiveProtocol()
   * @generated
   * @ordered
   */
	protected static final CommunicationLinkProtocol RECEIVE_PROTOCOL_EDEFAULT = CommunicationLinkProtocol.UNSET;

	/**
   * The cached value of the '{@link #getReceiveProtocol() <em>Receive Protocol</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getReceiveProtocol()
   * @generated
   * @ordered
   */
	protected CommunicationLinkProtocol receiveProtocol = RECEIVE_PROTOCOL_EDEFAULT;





	/**
   * The cached value of the '{@link #getAllocatedItem() <em>Allocated Item</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAllocatedItem()
   * @generated
   * @ordered
   */
	protected ExchangeItem allocatedItem;








	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ExchangeItemAllocationImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public String getName() {

    return name;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setName(String newName) {

    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.EXCHANGE_ITEM_ALLOCATION__NAME, oldName, name));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<SequenceMessage> getInvokingSequenceMessages() {


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
    EAnnotation annotation = InformationPackage.Literals.ABSTRACT_EVENT_OPERATION__INVOKING_SEQUENCE_MESSAGES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.ABSTRACT_EVENT_OPERATION__INVOKING_SEQUENCE_MESSAGES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<SequenceMessage> resultAsList = (Collection<SequenceMessage>) result;
    return new EcoreEList.UnmodifiableEList<SequenceMessage>(this, InformationPackage.Literals.ABSTRACT_EVENT_OPERATION__INVOKING_SEQUENCE_MESSAGES, resultAsList.size(), resultAsList.toArray());
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

	public boolean isFinal() {

    return final_;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setFinal(boolean newFinal) {

    boolean oldFinal = final_;
    final_ = newFinal;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.EXCHANGE_ITEM_ALLOCATION__FINAL, oldFinal, final_));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CommunicationLinkProtocol getSendProtocol() {

    return sendProtocol;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setSendProtocol(CommunicationLinkProtocol newSendProtocol) {

    CommunicationLinkProtocol oldSendProtocol = sendProtocol;
    sendProtocol = newSendProtocol == null ? SEND_PROTOCOL_EDEFAULT : newSendProtocol;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.EXCHANGE_ITEM_ALLOCATION__SEND_PROTOCOL, oldSendProtocol, sendProtocol));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CommunicationLinkProtocol getReceiveProtocol() {

    return receiveProtocol;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setReceiveProtocol(CommunicationLinkProtocol newReceiveProtocol) {

    CommunicationLinkProtocol oldReceiveProtocol = receiveProtocol;
    receiveProtocol = newReceiveProtocol == null ? RECEIVE_PROTOCOL_EDEFAULT : newReceiveProtocol;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.EXCHANGE_ITEM_ALLOCATION__RECEIVE_PROTOCOL, oldReceiveProtocol, receiveProtocol));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ExchangeItem getAllocatedItem() {

    if (allocatedItem != null && allocatedItem.eIsProxy()) {
      InternalEObject oldAllocatedItem = (InternalEObject)allocatedItem;
      allocatedItem = (ExchangeItem)eResolveProxy(oldAllocatedItem);
      if (allocatedItem != oldAllocatedItem) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM, oldAllocatedItem, allocatedItem));
      }
    }
    return allocatedItem;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ExchangeItem basicGetAllocatedItem() {

    return allocatedItem;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setAllocatedItem(ExchangeItem newAllocatedItem) {

    ExchangeItem oldAllocatedItem = allocatedItem;
    allocatedItem = newAllocatedItem;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM, oldAllocatedItem, allocatedItem));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Interface getAllocatingInterface() {

    Interface allocatingInterface = basicGetAllocatingInterface();
    return allocatingInterface != null && allocatingInterface.eIsProxy() ? (Interface)eResolveProxy((InternalEObject)allocatingInterface) : allocatingInterface;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Interface basicGetAllocatingInterface() {


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
    EAnnotation annotation = CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATING_INTERFACE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATING_INTERFACE, annotation);
    
    try {
      return (Interface) result;
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
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__NAME:
        return getName();
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__INVOKING_SEQUENCE_MESSAGES:
        return getInvokingSequenceMessages();
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__FINAL:
        return isFinal();
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__SEND_PROTOCOL:
        return getSendProtocol();
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__RECEIVE_PROTOCOL:
        return getReceiveProtocol();
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM:
        if (resolve) return getAllocatedItem();
        return basicGetAllocatedItem();
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__ALLOCATING_INTERFACE:
        if (resolve) return getAllocatingInterface();
        return basicGetAllocatingInterface();
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
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__NAME:
          setName((String)newValue);
        return;
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__FINAL:
          setFinal((Boolean)newValue);
        return;
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__SEND_PROTOCOL:
          setSendProtocol((CommunicationLinkProtocol)newValue);
        return;
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__RECEIVE_PROTOCOL:
          setReceiveProtocol((CommunicationLinkProtocol)newValue);
        return;
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM:
          setAllocatedItem((ExchangeItem)newValue);
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
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__FINAL:
        setFinal(FINAL_EDEFAULT);
        return;
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__SEND_PROTOCOL:
        setSendProtocol(SEND_PROTOCOL_EDEFAULT);
        return;
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__RECEIVE_PROTOCOL:
        setReceiveProtocol(RECEIVE_PROTOCOL_EDEFAULT);
        return;
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM:
        setAllocatedItem((ExchangeItem)null);
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
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__INVOKING_SEQUENCE_MESSAGES:
        return !getInvokingSequenceMessages().isEmpty();
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__FINAL:
        return final_ != FINAL_EDEFAULT;
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__SEND_PROTOCOL:
        return sendProtocol != SEND_PROTOCOL_EDEFAULT;
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__RECEIVE_PROTOCOL:
        return receiveProtocol != RECEIVE_PROTOCOL_EDEFAULT;
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM:
        return allocatedItem != null;
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__ALLOCATING_INTERFACE:
        return basicGetAllocatingInterface() != null;
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
    if (baseClass == AbstractNamedElement.class) {
      switch (derivedFeatureID) {
        case CsPackage.EXCHANGE_ITEM_ALLOCATION__NAME: return ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__NAME;
        default: return -1;
      }
    }
    if (baseClass == NamedElement.class) {
      switch (derivedFeatureID) {
        default: return -1;
      }
    }
    if (baseClass == AbstractEventOperation.class) {
      switch (derivedFeatureID) {
        case CsPackage.EXCHANGE_ITEM_ALLOCATION__INVOKING_SEQUENCE_MESSAGES: return InformationPackage.ABSTRACT_EVENT_OPERATION__INVOKING_SEQUENCE_MESSAGES;
        default: return -1;
      }
    }
    if (baseClass == FinalizableElement.class) {
      switch (derivedFeatureID) {
        case CsPackage.EXCHANGE_ITEM_ALLOCATION__FINAL: return ModellingcorePackage.FINALIZABLE_ELEMENT__FINAL;
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
    if (baseClass == AbstractNamedElement.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__NAME: return CsPackage.EXCHANGE_ITEM_ALLOCATION__NAME;
        default: return -1;
      }
    }
    if (baseClass == NamedElement.class) {
      switch (baseFeatureID) {
        default: return -1;
      }
    }
    if (baseClass == AbstractEventOperation.class) {
      switch (baseFeatureID) {
        case InformationPackage.ABSTRACT_EVENT_OPERATION__INVOKING_SEQUENCE_MESSAGES: return CsPackage.EXCHANGE_ITEM_ALLOCATION__INVOKING_SEQUENCE_MESSAGES;
        default: return -1;
      }
    }
    if (baseClass == FinalizableElement.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.FINALIZABLE_ELEMENT__FINAL: return CsPackage.EXCHANGE_ITEM_ALLOCATION__FINAL;
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
    result.append(" (name: "); //$NON-NLS-1$
    result.append(name);
    result.append(", final: "); //$NON-NLS-1$
    result.append(final_);
    result.append(", sendProtocol: "); //$NON-NLS-1$
    result.append(sendProtocol);
    result.append(", receiveProtocol: "); //$NON-NLS-1$
    result.append(receiveProtocol);
    result.append(')');
    return result.toString();
  }


} //ExchangeItemAllocationImpl
