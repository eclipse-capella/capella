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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.NamingRule;
import org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Capability Extension Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtensionPointImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtensionPointImpl#getNamingRules <em>Naming Rules</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtensionPointImpl#getAbstractCapability <em>Abstract Capability</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtensionPointImpl#getExtendLinks <em>Extend Links</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AbstractCapabilityExtensionPointImpl extends RelationshipImpl implements AbstractCapabilityExtensionPoint {

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
   * The cached value of the '{@link #getNamingRules() <em>Naming Rules</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getNamingRules()
   * @generated
   * @ordered
   */
	protected EList<NamingRule> namingRules;









	/**
   * The cached value of the '{@link #getExtendLinks() <em>Extend Links</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getExtendLinks()
   * @generated
   * @ordered
   */
	protected EList<AbstractCapabilityExtend> extendLinks;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected AbstractCapabilityExtensionPointImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTENSION_POINT;
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
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__NAME, oldName, name));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<NamingRule> getNamingRules() {

    if (namingRules == null) {
      namingRules = new EObjectContainmentEList<NamingRule>(NamingRule.class, this, InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__NAMING_RULES);
    }
    return namingRules;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractCapability getAbstractCapability() {

    AbstractCapability abstractCapability = basicGetAbstractCapability();
    return abstractCapability != null && abstractCapability.eIsProxy() ? (AbstractCapability)eResolveProxy((InternalEObject)abstractCapability) : abstractCapability;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractCapability basicGetAbstractCapability() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTENSION_POINT__ABSTRACT_CAPABILITY.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTENSION_POINT__ABSTRACT_CAPABILITY, annotation);
    
    try {
      return (AbstractCapability) result;
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

	public EList<AbstractCapabilityExtend> getExtendLinks() {

    if (extendLinks == null) {
      extendLinks = new EObjectWithInverseResolvingEList<AbstractCapabilityExtend>(AbstractCapabilityExtend.class, this, InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__EXTEND_LINKS, InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION);
    }
    return extendLinks;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__EXTEND_LINKS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtendLinks()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__NAMING_RULES:
        return ((InternalEList<?>)getNamingRules()).basicRemove(otherEnd, msgs);
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__EXTEND_LINKS:
        return ((InternalEList<?>)getExtendLinks()).basicRemove(otherEnd, msgs);
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
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__NAME:
        return getName();
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__NAMING_RULES:
        return getNamingRules();
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__ABSTRACT_CAPABILITY:
        if (resolve) return getAbstractCapability();
        return basicGetAbstractCapability();
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__EXTEND_LINKS:
        return getExtendLinks();
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
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__NAME:
          setName((String)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__NAMING_RULES:
        getNamingRules().clear();
        getNamingRules().addAll((Collection<? extends NamingRule>)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__EXTEND_LINKS:
        getExtendLinks().clear();
        getExtendLinks().addAll((Collection<? extends AbstractCapabilityExtend>)newValue);
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
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__NAMING_RULES:
        getNamingRules().clear();
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__EXTEND_LINKS:
        getExtendLinks().clear();
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
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__NAMING_RULES:
        return namingRules != null && !namingRules.isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__ABSTRACT_CAPABILITY:
        return basicGetAbstractCapability() != null;
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__EXTEND_LINKS:
        return extendLinks != null && !extendLinks.isEmpty();
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
        case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__NAME: return ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__NAME;
        default: return -1;
      }
    }
    if (baseClass == NamedElement.class) {
      switch (derivedFeatureID) {
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
        case ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__NAME: return InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__NAME;
        default: return -1;
      }
    }
    if (baseClass == NamedElement.class) {
      switch (baseFeatureID) {
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
    result.append(')');
    return result.toString();
  }


} //AbstractCapabilityExtensionPointImpl
