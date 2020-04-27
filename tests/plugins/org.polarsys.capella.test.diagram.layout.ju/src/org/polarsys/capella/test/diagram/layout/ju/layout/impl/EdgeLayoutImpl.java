/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage;
import org.polarsys.capella.test.diagram.layout.ju.layout.Location;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Edge Layout</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.EdgeLayoutImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.EdgeLayoutImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.EdgeLayoutImpl#getActualMapping <em>Actual Mapping</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.EdgeLayoutImpl#getAppliedFilters <em>Applied Filters</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.EdgeLayoutImpl#getAppliedStyles <em>Applied Styles</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.EdgeLayoutImpl#getBendpoints <em>Bendpoints</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.EdgeLayoutImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.EdgeLayoutImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EdgeLayoutImpl extends MinimalEObjectImpl.Container implements EdgeLayout {
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
   * The cached value of the '{@link #getBendpoints() <em>Bendpoints</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBendpoints()
   * @generated
   * @ordered
   */
  protected EList<Location> bendpoints;

  /**
   * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
  protected ISemanticLayout source;

  /**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected ISemanticLayout target;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EdgeLayoutImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return LayoutPackage.Literals.EDGE_LAYOUT;
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
      eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.EDGE_LAYOUT__ID, oldId, id));
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
      eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.EDGE_LAYOUT__NAME, oldName, name));
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
      eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.EDGE_LAYOUT__ACTUAL_MAPPING, oldActualMapping, actualMapping));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getAppliedFilters() {
    if (appliedFilters == null) {
      appliedFilters = new EDataTypeUniqueEList<String>(String.class, this, LayoutPackage.EDGE_LAYOUT__APPLIED_FILTERS);
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
      appliedStyles = new EDataTypeUniqueEList<String>(String.class, this, LayoutPackage.EDGE_LAYOUT__APPLIED_STYLES);
    }
    return appliedStyles;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Location> getBendpoints() {
    if (bendpoints == null) {
      bendpoints = new EObjectContainmentEList<Location>(Location.class, this, LayoutPackage.EDGE_LAYOUT__BENDPOINTS);
    }
    return bendpoints;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ISemanticLayout getSource() {
    if (source != null && source.eIsProxy()) {
      InternalEObject oldSource = (InternalEObject)source;
      source = (ISemanticLayout)eResolveProxy(oldSource);
      if (source != oldSource) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, LayoutPackage.EDGE_LAYOUT__SOURCE, oldSource, source));
      }
    }
    return source;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ISemanticLayout basicGetSource() {
    return source;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSource(ISemanticLayout newSource) {
    ISemanticLayout oldSource = source;
    source = newSource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.EDGE_LAYOUT__SOURCE, oldSource, source));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ISemanticLayout getTarget() {
    if (target != null && target.eIsProxy()) {
      InternalEObject oldTarget = (InternalEObject)target;
      target = (ISemanticLayout)eResolveProxy(oldTarget);
      if (target != oldTarget) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, LayoutPackage.EDGE_LAYOUT__TARGET, oldTarget, target));
      }
    }
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ISemanticLayout basicGetTarget() {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTarget(ISemanticLayout newTarget) {
    ISemanticLayout oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.EDGE_LAYOUT__TARGET, oldTarget, target));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case LayoutPackage.EDGE_LAYOUT__BENDPOINTS:
        return ((InternalEList<?>)getBendpoints()).basicRemove(otherEnd, msgs);
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
      case LayoutPackage.EDGE_LAYOUT__ID:
        return getId();
      case LayoutPackage.EDGE_LAYOUT__NAME:
        return getName();
      case LayoutPackage.EDGE_LAYOUT__ACTUAL_MAPPING:
        return getActualMapping();
      case LayoutPackage.EDGE_LAYOUT__APPLIED_FILTERS:
        return getAppliedFilters();
      case LayoutPackage.EDGE_LAYOUT__APPLIED_STYLES:
        return getAppliedStyles();
      case LayoutPackage.EDGE_LAYOUT__BENDPOINTS:
        return getBendpoints();
      case LayoutPackage.EDGE_LAYOUT__SOURCE:
        if (resolve) return getSource();
        return basicGetSource();
      case LayoutPackage.EDGE_LAYOUT__TARGET:
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
      case LayoutPackage.EDGE_LAYOUT__ID:
        setId((String)newValue);
        return;
      case LayoutPackage.EDGE_LAYOUT__NAME:
        setName((String)newValue);
        return;
      case LayoutPackage.EDGE_LAYOUT__ACTUAL_MAPPING:
        setActualMapping((String)newValue);
        return;
      case LayoutPackage.EDGE_LAYOUT__APPLIED_FILTERS:
        getAppliedFilters().clear();
        getAppliedFilters().addAll((Collection<? extends String>)newValue);
        return;
      case LayoutPackage.EDGE_LAYOUT__APPLIED_STYLES:
        getAppliedStyles().clear();
        getAppliedStyles().addAll((Collection<? extends String>)newValue);
        return;
      case LayoutPackage.EDGE_LAYOUT__BENDPOINTS:
        getBendpoints().clear();
        getBendpoints().addAll((Collection<? extends Location>)newValue);
        return;
      case LayoutPackage.EDGE_LAYOUT__SOURCE:
        setSource((ISemanticLayout)newValue);
        return;
      case LayoutPackage.EDGE_LAYOUT__TARGET:
        setTarget((ISemanticLayout)newValue);
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
      case LayoutPackage.EDGE_LAYOUT__ID:
        setId(ID_EDEFAULT);
        return;
      case LayoutPackage.EDGE_LAYOUT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case LayoutPackage.EDGE_LAYOUT__ACTUAL_MAPPING:
        setActualMapping(ACTUAL_MAPPING_EDEFAULT);
        return;
      case LayoutPackage.EDGE_LAYOUT__APPLIED_FILTERS:
        getAppliedFilters().clear();
        return;
      case LayoutPackage.EDGE_LAYOUT__APPLIED_STYLES:
        getAppliedStyles().clear();
        return;
      case LayoutPackage.EDGE_LAYOUT__BENDPOINTS:
        getBendpoints().clear();
        return;
      case LayoutPackage.EDGE_LAYOUT__SOURCE:
        setSource((ISemanticLayout)null);
        return;
      case LayoutPackage.EDGE_LAYOUT__TARGET:
        setTarget((ISemanticLayout)null);
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
      case LayoutPackage.EDGE_LAYOUT__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case LayoutPackage.EDGE_LAYOUT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case LayoutPackage.EDGE_LAYOUT__ACTUAL_MAPPING:
        return ACTUAL_MAPPING_EDEFAULT == null ? actualMapping != null : !ACTUAL_MAPPING_EDEFAULT.equals(actualMapping);
      case LayoutPackage.EDGE_LAYOUT__APPLIED_FILTERS:
        return appliedFilters != null && !appliedFilters.isEmpty();
      case LayoutPackage.EDGE_LAYOUT__APPLIED_STYLES:
        return appliedStyles != null && !appliedStyles.isEmpty();
      case LayoutPackage.EDGE_LAYOUT__BENDPOINTS:
        return bendpoints != null && !bendpoints.isEmpty();
      case LayoutPackage.EDGE_LAYOUT__SOURCE:
        return source != null;
      case LayoutPackage.EDGE_LAYOUT__TARGET:
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

} //EdgeLayoutImpl
