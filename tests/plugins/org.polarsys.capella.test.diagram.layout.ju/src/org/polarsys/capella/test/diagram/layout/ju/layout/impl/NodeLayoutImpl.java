/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.layout.ju.layout.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.test.diagram.layout.ju.layout.Bounds;
import org.polarsys.capella.test.diagram.layout.ju.layout.ILayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage;
import org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Layout</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.NodeLayoutImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.NodeLayoutImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.NodeLayoutImpl#getActualMapping <em>Actual Mapping</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.NodeLayoutImpl#getAppliedFilters <em>Applied Filters</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.NodeLayoutImpl#getAppliedStyles <em>Applied Styles</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.NodeLayoutImpl#getBounds <em>Bounds</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.NodeLayoutImpl#getOwnedLayouts <em>Owned Layouts</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NodeLayoutImpl extends MinimalEObjectImpl.Container implements NodeLayout {
  /**
   * The default value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected static final String ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected String id = ID_EDEFAULT;

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
   * The default value of the '{@link #getActualMapping() <em>Actual Mapping</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getActualMapping()
   * @generated
   * @ordered
   */
  protected static final String ACTUAL_MAPPING_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getActualMapping() <em>Actual Mapping</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getActualMapping()
   * @generated
   * @ordered
   */
  protected String actualMapping = ACTUAL_MAPPING_EDEFAULT;

  /**
   * The cached value of the '{@link #getAppliedFilters() <em>Applied Filters</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAppliedFilters()
   * @generated
   * @ordered
   */
  protected EList<String> appliedFilters;

  /**
   * The cached value of the '{@link #getAppliedStyles() <em>Applied Styles</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAppliedStyles()
   * @generated
   * @ordered
   */
  protected EList<String> appliedStyles;

  /**
   * The cached value of the '{@link #getBounds() <em>Bounds</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBounds()
   * @generated
   * @ordered
   */
  protected Bounds bounds;

  /**
   * The cached value of the '{@link #getOwnedLayouts() <em>Owned Layouts</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOwnedLayouts()
   * @generated
   * @ordered
   */
  protected EList<ILayout> ownedLayouts;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NodeLayoutImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return LayoutPackage.Literals.NODE_LAYOUT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getId() {
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setId(String newId) {
    String oldId = id;
    id = newId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.NODE_LAYOUT__ID, oldId, id));
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
  public void setName(String newName) {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.NODE_LAYOUT__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getActualMapping() {
    return actualMapping;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setActualMapping(String newActualMapping) {
    String oldActualMapping = actualMapping;
    actualMapping = newActualMapping;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.NODE_LAYOUT__ACTUAL_MAPPING, oldActualMapping, actualMapping));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getAppliedFilters() {
    if (appliedFilters == null) {
      appliedFilters = new EDataTypeUniqueEList<String>(String.class, this, LayoutPackage.NODE_LAYOUT__APPLIED_FILTERS);
    }
    return appliedFilters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getAppliedStyles() {
    if (appliedStyles == null) {
      appliedStyles = new EDataTypeUniqueEList<String>(String.class, this, LayoutPackage.NODE_LAYOUT__APPLIED_STYLES);
    }
    return appliedStyles;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Bounds getBounds() {
    return bounds;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBounds(Bounds newBounds, NotificationChain msgs) {
    Bounds oldBounds = bounds;
    bounds = newBounds;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LayoutPackage.NODE_LAYOUT__BOUNDS, oldBounds, newBounds);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBounds(Bounds newBounds) {
    if (newBounds != bounds) {
      NotificationChain msgs = null;
      if (bounds != null)
        msgs = ((InternalEObject)bounds).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LayoutPackage.NODE_LAYOUT__BOUNDS, null, msgs);
      if (newBounds != null)
        msgs = ((InternalEObject)newBounds).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LayoutPackage.NODE_LAYOUT__BOUNDS, null, msgs);
      msgs = basicSetBounds(newBounds, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.NODE_LAYOUT__BOUNDS, newBounds, newBounds));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ILayout> getOwnedLayouts() {
    if (ownedLayouts == null) {
      ownedLayouts = new EObjectContainmentEList<ILayout>(ILayout.class, this, LayoutPackage.NODE_LAYOUT__OWNED_LAYOUTS);
    }
    return ownedLayouts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case LayoutPackage.NODE_LAYOUT__BOUNDS:
        return basicSetBounds(null, msgs);
      case LayoutPackage.NODE_LAYOUT__OWNED_LAYOUTS:
        return ((InternalEList<?>)getOwnedLayouts()).basicRemove(otherEnd, msgs);
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
      case LayoutPackage.NODE_LAYOUT__ID:
        return getId();
      case LayoutPackage.NODE_LAYOUT__NAME:
        return getName();
      case LayoutPackage.NODE_LAYOUT__ACTUAL_MAPPING:
        return getActualMapping();
      case LayoutPackage.NODE_LAYOUT__APPLIED_FILTERS:
        return getAppliedFilters();
      case LayoutPackage.NODE_LAYOUT__APPLIED_STYLES:
        return getAppliedStyles();
      case LayoutPackage.NODE_LAYOUT__BOUNDS:
        return getBounds();
      case LayoutPackage.NODE_LAYOUT__OWNED_LAYOUTS:
        return getOwnedLayouts();
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
      case LayoutPackage.NODE_LAYOUT__ID:
        setId((String)newValue);
        return;
      case LayoutPackage.NODE_LAYOUT__NAME:
        setName((String)newValue);
        return;
      case LayoutPackage.NODE_LAYOUT__ACTUAL_MAPPING:
        setActualMapping((String)newValue);
        return;
      case LayoutPackage.NODE_LAYOUT__APPLIED_FILTERS:
        getAppliedFilters().clear();
        getAppliedFilters().addAll((Collection<? extends String>)newValue);
        return;
      case LayoutPackage.NODE_LAYOUT__APPLIED_STYLES:
        getAppliedStyles().clear();
        getAppliedStyles().addAll((Collection<? extends String>)newValue);
        return;
      case LayoutPackage.NODE_LAYOUT__BOUNDS:
        setBounds((Bounds)newValue);
        return;
      case LayoutPackage.NODE_LAYOUT__OWNED_LAYOUTS:
        getOwnedLayouts().clear();
        getOwnedLayouts().addAll((Collection<? extends ILayout>)newValue);
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
      case LayoutPackage.NODE_LAYOUT__ID:
        setId(ID_EDEFAULT);
        return;
      case LayoutPackage.NODE_LAYOUT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case LayoutPackage.NODE_LAYOUT__ACTUAL_MAPPING:
        setActualMapping(ACTUAL_MAPPING_EDEFAULT);
        return;
      case LayoutPackage.NODE_LAYOUT__APPLIED_FILTERS:
        getAppliedFilters().clear();
        return;
      case LayoutPackage.NODE_LAYOUT__APPLIED_STYLES:
        getAppliedStyles().clear();
        return;
      case LayoutPackage.NODE_LAYOUT__BOUNDS:
        setBounds((Bounds)null);
        return;
      case LayoutPackage.NODE_LAYOUT__OWNED_LAYOUTS:
        getOwnedLayouts().clear();
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
      case LayoutPackage.NODE_LAYOUT__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case LayoutPackage.NODE_LAYOUT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case LayoutPackage.NODE_LAYOUT__ACTUAL_MAPPING:
        return ACTUAL_MAPPING_EDEFAULT == null ? actualMapping != null : !ACTUAL_MAPPING_EDEFAULT.equals(actualMapping);
      case LayoutPackage.NODE_LAYOUT__APPLIED_FILTERS:
        return appliedFilters != null && !appliedFilters.isEmpty();
      case LayoutPackage.NODE_LAYOUT__APPLIED_STYLES:
        return appliedStyles != null && !appliedStyles.isEmpty();
      case LayoutPackage.NODE_LAYOUT__BOUNDS:
        return bounds != null;
      case LayoutPackage.NODE_LAYOUT__OWNED_LAYOUTS:
        return ownedLayouts != null && !ownedLayouts.isEmpty();
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

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (id: ");
    result.append(id);
    result.append(", name: ");
    result.append(name);
    result.append(", actualMapping: ");
    result.append(actualMapping);
    result.append(", appliedFilters: ");
    result.append(appliedFilters);
    result.append(", appliedStyles: ");
    result.append(appliedStyles);
    result.append(')');
    return result.toString();
  }

} //NodeLayoutImpl
