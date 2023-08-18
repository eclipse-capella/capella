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

package org.polarsys.capella.core.data.information.impl;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.information.SynchronismKind;
import org.polarsys.capella.core.data.information.communication.Message;
import org.polarsys.capella.core.data.information.communication.MessageReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ServiceImpl#getSynchronismKind <em>Synchronism Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ServiceImpl#getThrownExceptions <em>Thrown Exceptions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ServiceImpl#getMessages <em>Messages</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ServiceImpl#getMessageReferences <em>Message References</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ServiceImpl extends OperationImpl implements Service {

	/**
   * The default value of the '{@link #getSynchronismKind() <em>Synchronism Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSynchronismKind()
   * @generated
   * @ordered
   */
	protected static final SynchronismKind SYNCHRONISM_KIND_EDEFAULT = SynchronismKind.UNSET;

	/**
   * The cached value of the '{@link #getSynchronismKind() <em>Synchronism Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSynchronismKind()
   * @generated
   * @ordered
   */
	protected SynchronismKind synchronismKind = SYNCHRONISM_KIND_EDEFAULT;





	/**
   * The cached value of the '{@link #getThrownExceptions() <em>Thrown Exceptions</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getThrownExceptions()
   * @generated
   * @ordered
   */
	protected EList<org.polarsys.capella.core.data.information.communication.Exception> thrownExceptions;









	/**
   * The cached value of the '{@link #getMessageReferences() <em>Message References</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getMessageReferences()
   * @generated
   * @ordered
   */
	protected EList<MessageReference> messageReferences;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ServiceImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InformationPackage.Literals.SERVICE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public SynchronismKind getSynchronismKind() {

    return synchronismKind;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setSynchronismKind(SynchronismKind newSynchronismKind) {

    SynchronismKind oldSynchronismKind = synchronismKind;
    synchronismKind = newSynchronismKind == null ? SYNCHRONISM_KIND_EDEFAULT : newSynchronismKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.SERVICE__SYNCHRONISM_KIND, oldSynchronismKind, synchronismKind));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<org.polarsys.capella.core.data.information.communication.Exception> getThrownExceptions() {

    if (thrownExceptions == null) {
      thrownExceptions = new EObjectResolvingEList<org.polarsys.capella.core.data.information.communication.Exception>(org.polarsys.capella.core.data.information.communication.Exception.class, this, InformationPackage.SERVICE__THROWN_EXCEPTIONS);
    }
    return thrownExceptions;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Message> getMessages() {


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
    EAnnotation annotation = InformationPackage.Literals.SERVICE__MESSAGES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.SERVICE__MESSAGES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Message> resultAsList = (Collection<Message>) result;
    return new EcoreEList.UnmodifiableEList<Message>(this, InformationPackage.Literals.SERVICE__MESSAGES, resultAsList.size(), resultAsList.toArray());
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

	public EList<MessageReference> getMessageReferences() {

    if (messageReferences == null) {
      messageReferences = new EObjectResolvingEList<MessageReference>(MessageReference.class, this, InformationPackage.SERVICE__MESSAGE_REFERENCES);
    }
    return messageReferences;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case InformationPackage.SERVICE__SYNCHRONISM_KIND:
        return getSynchronismKind();
      case InformationPackage.SERVICE__THROWN_EXCEPTIONS:
        return getThrownExceptions();
      case InformationPackage.SERVICE__MESSAGES:
        return getMessages();
      case InformationPackage.SERVICE__MESSAGE_REFERENCES:
        return getMessageReferences();
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
      case InformationPackage.SERVICE__SYNCHRONISM_KIND:
          setSynchronismKind((SynchronismKind)newValue);
        return;
      case InformationPackage.SERVICE__THROWN_EXCEPTIONS:
        getThrownExceptions().clear();
        getThrownExceptions().addAll((Collection<? extends org.polarsys.capella.core.data.information.communication.Exception>)newValue);
        return;
      case InformationPackage.SERVICE__MESSAGE_REFERENCES:
        getMessageReferences().clear();
        getMessageReferences().addAll((Collection<? extends MessageReference>)newValue);
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
      case InformationPackage.SERVICE__SYNCHRONISM_KIND:
        setSynchronismKind(SYNCHRONISM_KIND_EDEFAULT);
        return;
      case InformationPackage.SERVICE__THROWN_EXCEPTIONS:
        getThrownExceptions().clear();
        return;
      case InformationPackage.SERVICE__MESSAGE_REFERENCES:
        getMessageReferences().clear();
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
      case InformationPackage.SERVICE__SYNCHRONISM_KIND:
        return synchronismKind != SYNCHRONISM_KIND_EDEFAULT;
      case InformationPackage.SERVICE__THROWN_EXCEPTIONS:
        return thrownExceptions != null && !thrownExceptions.isEmpty();
      case InformationPackage.SERVICE__MESSAGES:
        return !getMessages().isEmpty();
      case InformationPackage.SERVICE__MESSAGE_REFERENCES:
        return messageReferences != null && !messageReferences.isEmpty();
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
    result.append(" (synchronismKind: "); //$NON-NLS-1$
    result.append(synchronismKind);
    result.append(')');
    return result.toString();
  }


} //ServiceImpl