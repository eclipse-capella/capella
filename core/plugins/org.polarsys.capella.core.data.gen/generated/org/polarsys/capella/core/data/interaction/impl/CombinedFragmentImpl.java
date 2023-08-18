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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.Gate;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionOperatorKind;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Combined Fragment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.CombinedFragmentImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.CombinedFragmentImpl#getReferencedOperands <em>Referenced Operands</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.CombinedFragmentImpl#getExpressionGates <em>Expression Gates</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CombinedFragmentImpl extends AbstractFragmentImpl implements CombinedFragment {

	/**
   * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOperator()
   * @generated
   * @ordered
   */
	protected static final InteractionOperatorKind OPERATOR_EDEFAULT = InteractionOperatorKind.UNSET;

	/**
   * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOperator()
   * @generated
   * @ordered
   */
	protected InteractionOperatorKind operator = OPERATOR_EDEFAULT;





	/**
   * The cached value of the '{@link #getReferencedOperands() <em>Referenced Operands</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getReferencedOperands()
   * @generated
   * @ordered
   */
	protected EList<InteractionOperand> referencedOperands;








	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CombinedFragmentImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InteractionPackage.Literals.COMBINED_FRAGMENT;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InteractionOperatorKind getOperator() {

    return operator;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOperator(InteractionOperatorKind newOperator) {

    InteractionOperatorKind oldOperator = operator;
    operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.COMBINED_FRAGMENT__OPERATOR, oldOperator, operator));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<InteractionOperand> getReferencedOperands() {

    if (referencedOperands == null) {
      referencedOperands = new EObjectResolvingEList<InteractionOperand>(InteractionOperand.class, this, InteractionPackage.COMBINED_FRAGMENT__REFERENCED_OPERANDS);
    }
    return referencedOperands;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Gate> getExpressionGates() {


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
    EAnnotation annotation = InteractionPackage.Literals.COMBINED_FRAGMENT__EXPRESSION_GATES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.COMBINED_FRAGMENT__EXPRESSION_GATES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Gate> resultAsList = (Collection<Gate>) result;
    return new EcoreEList.UnmodifiableEList<Gate>(this, InteractionPackage.Literals.COMBINED_FRAGMENT__EXPRESSION_GATES, resultAsList.size(), resultAsList.toArray());
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case InteractionPackage.COMBINED_FRAGMENT__OPERATOR:
        return getOperator();
      case InteractionPackage.COMBINED_FRAGMENT__REFERENCED_OPERANDS:
        return getReferencedOperands();
      case InteractionPackage.COMBINED_FRAGMENT__EXPRESSION_GATES:
        return getExpressionGates();
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
      case InteractionPackage.COMBINED_FRAGMENT__OPERATOR:
          setOperator((InteractionOperatorKind)newValue);
        return;
      case InteractionPackage.COMBINED_FRAGMENT__REFERENCED_OPERANDS:
        getReferencedOperands().clear();
        getReferencedOperands().addAll((Collection<? extends InteractionOperand>)newValue);
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
      case InteractionPackage.COMBINED_FRAGMENT__OPERATOR:
        setOperator(OPERATOR_EDEFAULT);
        return;
      case InteractionPackage.COMBINED_FRAGMENT__REFERENCED_OPERANDS:
        getReferencedOperands().clear();
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
      case InteractionPackage.COMBINED_FRAGMENT__OPERATOR:
        return operator != OPERATOR_EDEFAULT;
      case InteractionPackage.COMBINED_FRAGMENT__REFERENCED_OPERANDS:
        return referencedOperands != null && !referencedOperands.isEmpty();
      case InteractionPackage.COMBINED_FRAGMENT__EXPRESSION_GATES:
        return !getExpressionGates().isEmpty();
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
    result.append(" (operator: "); //$NON-NLS-1$
    result.append(operator);
    result.append(')');
    return result.toString();
  }


} //CombinedFragmentImpl