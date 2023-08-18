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
package org.polarsys.capella.core.data.information.communication.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.information.communication.CommunicationFactory;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.communication.Message;
import org.polarsys.capella.core.data.information.communication.MessageReference;
import org.polarsys.capella.core.data.information.communication.Signal;
import org.polarsys.capella.core.data.information.communication.SignalInstance;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CommunicationFactoryImpl extends EFactoryImpl implements CommunicationFactory {
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static CommunicationFactory init() {
    try {
      CommunicationFactory theCommunicationFactory = (CommunicationFactory)EPackage.Registry.INSTANCE.getEFactory(CommunicationPackage.eNS_URI);
      if (theCommunicationFactory != null) {
        return theCommunicationFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new CommunicationFactoryImpl();
  }

	/**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CommunicationFactoryImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
      case CommunicationPackage.EXCEPTION: return createException();
      case CommunicationPackage.MESSAGE: return createMessage();
      case CommunicationPackage.MESSAGE_REFERENCE: return createMessageReference();
      case CommunicationPackage.SIGNAL: return createSignal();
      case CommunicationPackage.SIGNAL_INSTANCE: return createSignalInstance();
      case CommunicationPackage.COMMUNICATION_LINK: return createCommunicationLink();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
    switch (eDataType.getClassifierID()) {
      case CommunicationPackage.COMMUNICATION_LINK_KIND:
        return createCommunicationLinkKindFromString(eDataType, initialValue);
      case CommunicationPackage.COMMUNICATION_LINK_PROTOCOL:
        return createCommunicationLinkProtocolFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
    switch (eDataType.getClassifierID()) {
      case CommunicationPackage.COMMUNICATION_LINK_KIND:
        return convertCommunicationLinkKindToString(eDataType, instanceValue);
      case CommunicationPackage.COMMUNICATION_LINK_PROTOCOL:
        return convertCommunicationLinkProtocolToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public org.polarsys.capella.core.data.information.communication.Exception createException() {
    ExceptionImpl exception = new ExceptionImpl();
    //begin-capella-code
    exception.setId(IdGenerator.createId());
    //end-capella-code
    return exception;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Message createMessage() {
    MessageImpl message = new MessageImpl();
    //begin-capella-code
    message.setId(IdGenerator.createId());
    //end-capella-code
    return message;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public MessageReference createMessageReference() {
    MessageReferenceImpl messageReference = new MessageReferenceImpl();
    //begin-capella-code
    messageReference.setId(IdGenerator.createId());
    //end-capella-code
    return messageReference;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Signal createSignal() {
    SignalImpl signal = new SignalImpl();
    //begin-capella-code
    signal.setId(IdGenerator.createId());
    //end-capella-code
    return signal;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public SignalInstance createSignalInstance() {
    SignalInstanceImpl signalInstance = new SignalInstanceImpl();
    //begin-capella-code
    signalInstance.setId(IdGenerator.createId());
    //end-capella-code
    return signalInstance;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CommunicationLink createCommunicationLink() {
    CommunicationLinkImpl communicationLink = new CommunicationLinkImpl();
    //begin-capella-code
    communicationLink.setId(IdGenerator.createId());
    //end-capella-code
    return communicationLink;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CommunicationLinkKind createCommunicationLinkKindFromString(EDataType eDataType, String initialValue) {
    CommunicationLinkKind result = CommunicationLinkKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertCommunicationLinkKindToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CommunicationLinkProtocol createCommunicationLinkProtocolFromString(EDataType eDataType, String initialValue) {
    CommunicationLinkProtocol result = CommunicationLinkProtocol.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertCommunicationLinkProtocolToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CommunicationPackage getCommunicationPackage() {
    return (CommunicationPackage)getEPackage();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	@Deprecated
	public static CommunicationPackage getPackage() {
    return CommunicationPackage.eINSTANCE;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public org.polarsys.capella.core.data.information.communication.Exception createException(String name_p) {
    org.polarsys.capella.core.data.information.communication.Exception exception = createException();
    exception.setName(name_p);	  
    return exception;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Message createMessage(String name_p) {
    Message message = createMessage();
    message.setName(name_p);	  
    return message;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Signal createSignal(String name_p) {
    Signal signal = createSignal();
    signal.setName(name_p);	  
    return signal;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public SignalInstance createSignalInstance(String name_p) {
    SignalInstance signalInstance = createSignalInstance();
    signalInstance.setName(name_p);	  
    return signalInstance;
  }

	//begin-capella-code

	//end-capella-code
} //CommunicationFactoryImpl
