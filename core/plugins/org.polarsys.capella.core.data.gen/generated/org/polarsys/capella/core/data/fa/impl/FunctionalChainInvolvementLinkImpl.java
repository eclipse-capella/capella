/**
 *
 *  Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.fa.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.ReferenceHierarchyContext;
import org.polarsys.capella.core.data.information.ExchangeItem;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Functional Chain Involvement Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementLinkImpl#getSourceReferenceHierarchy <em>Source Reference Hierarchy</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementLinkImpl#getTargetReferenceHierarchy <em>Target Reference Hierarchy</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementLinkImpl#getExchangeContext <em>Exchange Context</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementLinkImpl#getExchangedItems <em>Exchanged Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementLinkImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementLinkImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FunctionalChainInvolvementLinkImpl extends FunctionalChainInvolvementImpl implements FunctionalChainInvolvementLink {

	/**
   * The cached value of the '{@link #getSourceReferenceHierarchy() <em>Source Reference Hierarchy</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSourceReferenceHierarchy()
   * @generated
   * @ordered
   */
	protected EList<FunctionalChainReference> sourceReferenceHierarchy;





	/**
   * The cached value of the '{@link #getTargetReferenceHierarchy() <em>Target Reference Hierarchy</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTargetReferenceHierarchy()
   * @generated
   * @ordered
   */
	protected EList<FunctionalChainReference> targetReferenceHierarchy;





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
   * The cached value of the '{@link #getExchangedItems() <em>Exchanged Items</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getExchangedItems()
   * @generated
   * @ordered
   */
	protected EList<ExchangeItem> exchangedItems;




	/**
   * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
	protected FunctionalChainInvolvementFunction source;





	/**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
	protected FunctionalChainInvolvementFunction target;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected FunctionalChainInvolvementLinkImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<FunctionalChainReference> getSourceReferenceHierarchy() {

    if (sourceReferenceHierarchy == null) {
      sourceReferenceHierarchy = new EObjectResolvingEList<FunctionalChainReference>(FunctionalChainReference.class, this, FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE_REFERENCE_HIERARCHY);
    }
    return sourceReferenceHierarchy;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<FunctionalChainReference> getTargetReferenceHierarchy() {

    if (targetReferenceHierarchy == null) {
      targetReferenceHierarchy = new EObjectResolvingEList<FunctionalChainReference>(FunctionalChainReference.class, this, FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET_REFERENCE_HIERARCHY);
    }
    return targetReferenceHierarchy;
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGE_CONTEXT, oldExchangeContext, exchangeContext));
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
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGE_CONTEXT, oldExchangeContext, exchangeContext));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ExchangeItem> getExchangedItems() {

    if (exchangedItems == null) {
      exchangedItems = new EObjectResolvingEList<ExchangeItem>(ExchangeItem.class, this, FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGED_ITEMS);
    }
    return exchangedItems;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public FunctionalChainInvolvementFunction getSource() {

    if (source != null && source.eIsProxy()) {
      InternalEObject oldSource = (InternalEObject)source;
      source = (FunctionalChainInvolvementFunction)eResolveProxy(oldSource);
      if (source != oldSource) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE, oldSource, source));
      }
    }
    return source;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public FunctionalChainInvolvementFunction basicGetSource() {

    return source;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setSource(FunctionalChainInvolvementFunction newSource) {

    FunctionalChainInvolvementFunction oldSource = source;
    source = newSource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE, oldSource, source));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public FunctionalChainInvolvementFunction getTarget() {

    if (target != null && target.eIsProxy()) {
      InternalEObject oldTarget = (InternalEObject)target;
      target = (FunctionalChainInvolvementFunction)eResolveProxy(oldTarget);
      if (target != oldTarget) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET, oldTarget, target));
      }
    }
    return target;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public FunctionalChainInvolvementFunction basicGetTarget() {

    return target;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setTarget(FunctionalChainInvolvementFunction newTarget) {

    FunctionalChainInvolvementFunction oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET, oldTarget, target));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE_REFERENCE_HIERARCHY:
        return getSourceReferenceHierarchy();
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET_REFERENCE_HIERARCHY:
        return getTargetReferenceHierarchy();
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGE_CONTEXT:
        if (resolve) return getExchangeContext();
        return basicGetExchangeContext();
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGED_ITEMS:
        return getExchangedItems();
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE:
        if (resolve) return getSource();
        return basicGetSource();
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET:
        if (resolve) return getTarget();
        return basicGetTarget();
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
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE_REFERENCE_HIERARCHY:
        getSourceReferenceHierarchy().clear();
        getSourceReferenceHierarchy().addAll((Collection<? extends FunctionalChainReference>)newValue);
        return;
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET_REFERENCE_HIERARCHY:
        getTargetReferenceHierarchy().clear();
        getTargetReferenceHierarchy().addAll((Collection<? extends FunctionalChainReference>)newValue);
        return;
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGE_CONTEXT:
          setExchangeContext((Constraint)newValue);
        return;
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGED_ITEMS:
        getExchangedItems().clear();
        getExchangedItems().addAll((Collection<? extends ExchangeItem>)newValue);
        return;
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE:
          setSource((FunctionalChainInvolvementFunction)newValue);
        return;
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET:
          setTarget((FunctionalChainInvolvementFunction)newValue);
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
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE_REFERENCE_HIERARCHY:
        getSourceReferenceHierarchy().clear();
        return;
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET_REFERENCE_HIERARCHY:
        getTargetReferenceHierarchy().clear();
        return;
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGE_CONTEXT:
        setExchangeContext((Constraint)null);
        return;
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGED_ITEMS:
        getExchangedItems().clear();
        return;
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE:
        setSource((FunctionalChainInvolvementFunction)null);
        return;
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET:
        setTarget((FunctionalChainInvolvementFunction)null);
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
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE_REFERENCE_HIERARCHY:
        return sourceReferenceHierarchy != null && !sourceReferenceHierarchy.isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET_REFERENCE_HIERARCHY:
        return targetReferenceHierarchy != null && !targetReferenceHierarchy.isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGE_CONTEXT:
        return exchangeContext != null;
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGED_ITEMS:
        return exchangedItems != null && !exchangedItems.isEmpty();
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE:
        return source != null;
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET:
        return target != null;
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
    if (baseClass == ReferenceHierarchyContext.class) {
      switch (derivedFeatureID) {
        case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE_REFERENCE_HIERARCHY: return FaPackage.REFERENCE_HIERARCHY_CONTEXT__SOURCE_REFERENCE_HIERARCHY;
        case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET_REFERENCE_HIERARCHY: return FaPackage.REFERENCE_HIERARCHY_CONTEXT__TARGET_REFERENCE_HIERARCHY;
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
    if (baseClass == ReferenceHierarchyContext.class) {
      switch (baseFeatureID) {
        case FaPackage.REFERENCE_HIERARCHY_CONTEXT__SOURCE_REFERENCE_HIERARCHY: return FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE_REFERENCE_HIERARCHY;
        case FaPackage.REFERENCE_HIERARCHY_CONTEXT__TARGET_REFERENCE_HIERARCHY: return FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET_REFERENCE_HIERARCHY;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }



} //FunctionalChainInvolvementLinkImpl