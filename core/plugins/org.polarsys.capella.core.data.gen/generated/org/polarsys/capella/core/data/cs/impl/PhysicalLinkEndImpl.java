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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalPort;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Physical Link End</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkEndImpl#getPort <em>Port</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkEndImpl#getPart <em>Part</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PhysicalLinkEndImpl extends AbstractPhysicalLinkEndImpl implements PhysicalLinkEnd {

	/**
   * The cached value of the '{@link #getPort() <em>Port</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPort()
   * @generated
   * @ordered
   */
	protected PhysicalPort port;





	/**
   * The cached value of the '{@link #getPart() <em>Part</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPart()
   * @generated
   * @ordered
   */
	protected Part part;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PhysicalLinkEndImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CsPackage.Literals.PHYSICAL_LINK_END;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public PhysicalPort getPort() {

    if (port != null && port.eIsProxy()) {
      InternalEObject oldPort = (InternalEObject)port;
      port = (PhysicalPort)eResolveProxy(oldPort);
      if (port != oldPort) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.PHYSICAL_LINK_END__PORT, oldPort, port));
      }
    }
    return port;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public PhysicalPort basicGetPort() {

    return port;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setPort(PhysicalPort newPort) {

    PhysicalPort oldPort = port;
    port = newPort;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_LINK_END__PORT, oldPort, port));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Part getPart() {

    if (part != null && part.eIsProxy()) {
      InternalEObject oldPart = (InternalEObject)part;
      part = (Part)eResolveProxy(oldPart);
      if (part != oldPart) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.PHYSICAL_LINK_END__PART, oldPart, part));
      }
    }
    return part;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Part basicGetPart() {

    return part;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setPart(Part newPart) {

    Part oldPart = part;
    part = newPart;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_LINK_END__PART, oldPart, part));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case CsPackage.PHYSICAL_LINK_END__PORT:
        if (resolve) return getPort();
        return basicGetPort();
      case CsPackage.PHYSICAL_LINK_END__PART:
        if (resolve) return getPart();
        return basicGetPart();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case CsPackage.PHYSICAL_LINK_END__PORT:
          setPort((PhysicalPort)newValue);
        return;
      case CsPackage.PHYSICAL_LINK_END__PART:
          setPart((Part)newValue);
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
      case CsPackage.PHYSICAL_LINK_END__PORT:
        setPort((PhysicalPort)null);
        return;
      case CsPackage.PHYSICAL_LINK_END__PART:
        setPart((Part)null);
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
      case CsPackage.PHYSICAL_LINK_END__PORT:
        return port != null;
      case CsPackage.PHYSICAL_LINK_END__PART:
        return part != null;
    }
    return super.eIsSet(featureID);
  }



} //PhysicalLinkEndImpl