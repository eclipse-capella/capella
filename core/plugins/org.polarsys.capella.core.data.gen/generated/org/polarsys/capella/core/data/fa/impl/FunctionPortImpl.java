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
import org.eclipse.emf.ecore.util.EcoreEList;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.information.impl.PortImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function Port</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionPortImpl#getAbstractType <em>Abstract Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionPortImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionPortImpl#getAbstractTypedElements <em>Abstract Typed Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionPortImpl#getRepresentedComponentPort <em>Represented Component Port</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionPortImpl#getAllocatorComponentPorts <em>Allocator Component Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionPortImpl#getRealizedFunctionPorts <em>Realized Function Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionPortImpl#getRealizingFunctionPorts <em>Realizing Function Ports</em>}</li>
 * </ul>
 *
 * @generated
 */
@SuppressWarnings("deprecation")
public abstract class FunctionPortImpl extends PortImpl implements FunctionPort {

	/**
   * The cached value of the '{@link #getAbstractType() <em>Abstract Type</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAbstractType()
   * @generated
   * @ordered
   */
	protected AbstractType abstractType;









	/**
   * The cached value of the '{@link #getRepresentedComponentPort() <em>Represented Component Port</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getRepresentedComponentPort()
   * @deprecated See {@link org.polarsys.capella.core.data.fa.FunctionPort#getRepresentedComponentPort() model documentation} for details.
   * @generated
   * @ordered
   */
	@Deprecated
	protected ComponentPort representedComponentPort;
















	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected FunctionPortImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return FaPackage.Literals.FUNCTION_PORT;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractType getAbstractType() {

    if (abstractType != null && abstractType.eIsProxy()) {
      InternalEObject oldAbstractType = (InternalEObject)abstractType;
      abstractType = (AbstractType)eResolveProxy(oldAbstractType);
      if (abstractType != oldAbstractType) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FaPackage.FUNCTION_PORT__ABSTRACT_TYPE, oldAbstractType, abstractType));
      }
    }
    return abstractType;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractType basicGetAbstractType() {

    return abstractType;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setAbstractType(AbstractType newAbstractType) {

    AbstractType oldAbstractType = abstractType;
    abstractType = newAbstractType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.FUNCTION_PORT__ABSTRACT_TYPE, oldAbstractType, abstractType));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Type getType() {

    Type type = basicGetType();
    return type != null && type.eIsProxy() ? (Type)eResolveProxy((InternalEObject)type) : type;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Type basicGetType() {


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
    EAnnotation annotation = CapellacorePackage.Literals.TYPED_ELEMENT__TYPE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.TYPED_ELEMENT__TYPE, annotation);
    
    try {
      return (Type) result;
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

	public EList<AbstractTypedElement> getAbstractTypedElements() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractTypedElement> resultAsList = (Collection<AbstractTypedElement>) result;
    return new EcoreEList.UnmodifiableEList<AbstractTypedElement>(this, ModellingcorePackage.Literals.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated See {@link org.polarsys.capella.core.data.fa.FunctionPort#getRepresentedComponentPort() model documentation} for details.
   * @generated
   */

	@Deprecated
	public ComponentPort getRepresentedComponentPort() {

    if (representedComponentPort != null && representedComponentPort.eIsProxy()) {
      InternalEObject oldRepresentedComponentPort = (InternalEObject)representedComponentPort;
      representedComponentPort = (ComponentPort)eResolveProxy(oldRepresentedComponentPort);
      if (representedComponentPort != oldRepresentedComponentPort) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FaPackage.FUNCTION_PORT__REPRESENTED_COMPONENT_PORT, oldRepresentedComponentPort, representedComponentPort));
      }
    }
    return representedComponentPort;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated See {@link org.polarsys.capella.core.data.fa.FunctionPort#getRepresentedComponentPort() model documentation} for details.
   * @generated
   */

	@Deprecated
	public ComponentPort basicGetRepresentedComponentPort() {

    return representedComponentPort;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated See {@link org.polarsys.capella.core.data.fa.FunctionPort#getRepresentedComponentPort() model documentation} for details.
   * @generated
   */

	@Deprecated
		@Override
	public void setRepresentedComponentPort(ComponentPort newRepresentedComponentPort) {

    ComponentPort oldRepresentedComponentPort = representedComponentPort;
    representedComponentPort = newRepresentedComponentPort;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.FUNCTION_PORT__REPRESENTED_COMPONENT_PORT, oldRepresentedComponentPort, representedComponentPort));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentPort> getAllocatorComponentPorts() {


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
    EAnnotation annotation = FaPackage.Literals.FUNCTION_PORT__ALLOCATOR_COMPONENT_PORTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTION_PORT__ALLOCATOR_COMPONENT_PORTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ComponentPort> resultAsList = (Collection<ComponentPort>) result;
    return new EcoreEList.UnmodifiableEList<ComponentPort>(this, FaPackage.Literals.FUNCTION_PORT__ALLOCATOR_COMPONENT_PORTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<FunctionPort> getRealizedFunctionPorts() {


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
    EAnnotation annotation = FaPackage.Literals.FUNCTION_PORT__REALIZED_FUNCTION_PORTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTION_PORT__REALIZED_FUNCTION_PORTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<FunctionPort> resultAsList = (Collection<FunctionPort>) result;
    return new EcoreEList.UnmodifiableEList<FunctionPort>(this, FaPackage.Literals.FUNCTION_PORT__REALIZED_FUNCTION_PORTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<FunctionPort> getRealizingFunctionPorts() {


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
    EAnnotation annotation = FaPackage.Literals.FUNCTION_PORT__REALIZING_FUNCTION_PORTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTION_PORT__REALIZING_FUNCTION_PORTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<FunctionPort> resultAsList = (Collection<FunctionPort>) result;
    return new EcoreEList.UnmodifiableEList<FunctionPort>(this, FaPackage.Literals.FUNCTION_PORT__REALIZING_FUNCTION_PORTS, resultAsList.size(), resultAsList.toArray());
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
      case FaPackage.FUNCTION_PORT__ABSTRACT_TYPE:
        if (resolve) return getAbstractType();
        return basicGetAbstractType();
      case FaPackage.FUNCTION_PORT__TYPE:
        if (resolve) return getType();
        return basicGetType();
      case FaPackage.FUNCTION_PORT__ABSTRACT_TYPED_ELEMENTS:
        return getAbstractTypedElements();
      case FaPackage.FUNCTION_PORT__REPRESENTED_COMPONENT_PORT:
        if (resolve) return getRepresentedComponentPort();
        return basicGetRepresentedComponentPort();
      case FaPackage.FUNCTION_PORT__ALLOCATOR_COMPONENT_PORTS:
        return getAllocatorComponentPorts();
      case FaPackage.FUNCTION_PORT__REALIZED_FUNCTION_PORTS:
        return getRealizedFunctionPorts();
      case FaPackage.FUNCTION_PORT__REALIZING_FUNCTION_PORTS:
        return getRealizingFunctionPorts();
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
      case FaPackage.FUNCTION_PORT__ABSTRACT_TYPE:
          setAbstractType((AbstractType)newValue);
        return;
      case FaPackage.FUNCTION_PORT__REPRESENTED_COMPONENT_PORT:
          setRepresentedComponentPort((ComponentPort)newValue);
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
      case FaPackage.FUNCTION_PORT__ABSTRACT_TYPE:
        setAbstractType((AbstractType)null);
        return;
      case FaPackage.FUNCTION_PORT__REPRESENTED_COMPONENT_PORT:
        setRepresentedComponentPort((ComponentPort)null);
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
      case FaPackage.FUNCTION_PORT__ABSTRACT_TYPE:
        return abstractType != null;
      case FaPackage.FUNCTION_PORT__TYPE:
        return basicGetType() != null;
      case FaPackage.FUNCTION_PORT__ABSTRACT_TYPED_ELEMENTS:
        return !getAbstractTypedElements().isEmpty();
      case FaPackage.FUNCTION_PORT__REPRESENTED_COMPONENT_PORT:
        return representedComponentPort != null;
      case FaPackage.FUNCTION_PORT__ALLOCATOR_COMPONENT_PORTS:
        return !getAllocatorComponentPorts().isEmpty();
      case FaPackage.FUNCTION_PORT__REALIZED_FUNCTION_PORTS:
        return !getRealizedFunctionPorts().isEmpty();
      case FaPackage.FUNCTION_PORT__REALIZING_FUNCTION_PORTS:
        return !getRealizingFunctionPorts().isEmpty();
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
    if (baseClass == AbstractTypedElement.class) {
      switch (derivedFeatureID) {
        case FaPackage.FUNCTION_PORT__ABSTRACT_TYPE: return ModellingcorePackage.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE;
        default: return -1;
      }
    }
    if (baseClass == TypedElement.class) {
      switch (derivedFeatureID) {
        case FaPackage.FUNCTION_PORT__TYPE: return CapellacorePackage.TYPED_ELEMENT__TYPE;
        default: return -1;
      }
    }
    if (baseClass == AbstractType.class) {
      switch (derivedFeatureID) {
        case FaPackage.FUNCTION_PORT__ABSTRACT_TYPED_ELEMENTS: return ModellingcorePackage.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS;
        default: return -1;
      }
    }
    if (baseClass == AbstractEvent.class) {
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
    if (baseClass == AbstractTypedElement.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE: return FaPackage.FUNCTION_PORT__ABSTRACT_TYPE;
        default: return -1;
      }
    }
    if (baseClass == TypedElement.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.TYPED_ELEMENT__TYPE: return FaPackage.FUNCTION_PORT__TYPE;
        default: return -1;
      }
    }
    if (baseClass == AbstractType.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS: return FaPackage.FUNCTION_PORT__ABSTRACT_TYPED_ELEMENTS;
        default: return -1;
      }
    }
    if (baseClass == AbstractEvent.class) {
      switch (baseFeatureID) {
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }


} //FunctionPortImpl