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
package org.polarsys.capella.core.data.oa.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalScenario;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operational Scenario</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalScenarioImpl#getContext <em>Context</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalScenarioImpl#getObjective <em>Objective</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class OperationalScenarioImpl extends NamedElementImpl implements OperationalScenario {

	/**
   * The default value of the '{@link #getContext() <em>Context</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getContext()
   * @generated
   * @ordered
   */
	protected static final String CONTEXT_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getContext() <em>Context</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getContext()
   * @generated
   * @ordered
   */
	protected String context = CONTEXT_EDEFAULT;





	/**
   * The default value of the '{@link #getObjective() <em>Objective</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getObjective()
   * @generated
   * @ordered
   */
	protected static final String OBJECTIVE_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getObjective() <em>Objective</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getObjective()
   * @generated
   * @ordered
   */
	protected String objective = OBJECTIVE_EDEFAULT;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected OperationalScenarioImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.OPERATIONAL_SCENARIO;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public String getContext() {

    return context;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setContext(String newContext) {

    String oldContext = context;
    context = newContext;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.OPERATIONAL_SCENARIO__CONTEXT, oldContext, context));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public String getObjective() {

    return objective;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setObjective(String newObjective) {

    String oldObjective = objective;
    objective = newObjective;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.OPERATIONAL_SCENARIO__OBJECTIVE, oldObjective, objective));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case OaPackage.OPERATIONAL_SCENARIO__CONTEXT:
        return getContext();
      case OaPackage.OPERATIONAL_SCENARIO__OBJECTIVE:
        return getObjective();
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
      case OaPackage.OPERATIONAL_SCENARIO__CONTEXT:
          setContext((String)newValue);
        return;
      case OaPackage.OPERATIONAL_SCENARIO__OBJECTIVE:
          setObjective((String)newValue);
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
      case OaPackage.OPERATIONAL_SCENARIO__CONTEXT:
        setContext(CONTEXT_EDEFAULT);
        return;
      case OaPackage.OPERATIONAL_SCENARIO__OBJECTIVE:
        setObjective(OBJECTIVE_EDEFAULT);
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
      case OaPackage.OPERATIONAL_SCENARIO__CONTEXT:
        return CONTEXT_EDEFAULT == null ? context != null : !CONTEXT_EDEFAULT.equals(context);
      case OaPackage.OPERATIONAL_SCENARIO__OBJECTIVE:
        return OBJECTIVE_EDEFAULT == null ? objective != null : !OBJECTIVE_EDEFAULT.equals(objective);
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
    result.append(" (context: "); //$NON-NLS-1$
    result.append(context);
    result.append(", objective: "); //$NON-NLS-1$
    result.append(objective);
    result.append(')');
    return result.toString();
  }


} //OperationalScenarioImpl