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
import org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.ILayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram Layout</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.DiagramLayoutImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.DiagramLayoutImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.DiagramLayoutImpl#getActualMapping <em>Actual Mapping</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.DiagramLayoutImpl#getAppliedFilters <em>Applied Filters</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.DiagramLayoutImpl#getAppliedStyles <em>Applied Styles</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.DiagramLayoutImpl#getOwnedLayouts <em>Owned Layouts</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.DiagramLayoutImpl#getAppliedLayers <em>Applied Layers</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.DiagramLayoutImpl#isSynchronized <em>Synchronized</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.DiagramLayoutImpl#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DiagramLayoutImpl extends MinimalEObjectImpl.Container implements DiagramLayout {
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
   * The cached value of the '{@link #getOwnedLayouts() <em>Owned Layouts</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOwnedLayouts()
   * @generated
   * @ordered
   */
  protected EList<ILayout> ownedLayouts;

  /**
   * The cached value of the '{@link #getAppliedLayers() <em>Applied Layers</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAppliedLayers()
   * @generated
   * @ordered
   */
  protected EList<String> appliedLayers;

  /**
   * The default value of the '{@link #isSynchronized() <em>Synchronized</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSynchronized()
   * @generated
   * @ordered
   */
  protected static final boolean SYNCHRONIZED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSynchronized() <em>Synchronized</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSynchronized()
   * @generated
   * @ordered
   */
  protected boolean synchronized_ = SYNCHRONIZED_EDEFAULT;

  /**
   * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
  protected static final String DESCRIPTION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
  protected String description = DESCRIPTION_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DiagramLayoutImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return LayoutPackage.Literals.DIAGRAM_LAYOUT;
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
      eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.DIAGRAM_LAYOUT__ID, oldId, id));
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
      eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.DIAGRAM_LAYOUT__NAME, oldName, name));
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
      eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.DIAGRAM_LAYOUT__ACTUAL_MAPPING, oldActualMapping, actualMapping));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getAppliedFilters() {
    if (appliedFilters == null) {
      appliedFilters = new EDataTypeUniqueEList<String>(String.class, this, LayoutPackage.DIAGRAM_LAYOUT__APPLIED_FILTERS);
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
      appliedStyles = new EDataTypeUniqueEList<String>(String.class, this, LayoutPackage.DIAGRAM_LAYOUT__APPLIED_STYLES);
    }
    return appliedStyles;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ILayout> getOwnedLayouts() {
    if (ownedLayouts == null) {
      ownedLayouts = new EObjectContainmentEList<ILayout>(ILayout.class, this, LayoutPackage.DIAGRAM_LAYOUT__OWNED_LAYOUTS);
    }
    return ownedLayouts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getAppliedLayers() {
    if (appliedLayers == null) {
      appliedLayers = new EDataTypeUniqueEList<String>(String.class, this, LayoutPackage.DIAGRAM_LAYOUT__APPLIED_LAYERS);
    }
    return appliedLayers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSynchronized() {
    return synchronized_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSynchronized(boolean newSynchronized) {
    boolean oldSynchronized = synchronized_;
    synchronized_ = newSynchronized;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.DIAGRAM_LAYOUT__SYNCHRONIZED, oldSynchronized, synchronized_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDescription() {
    return description;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDescription(String newDescription) {
    String oldDescription = description;
    description = newDescription;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.DIAGRAM_LAYOUT__DESCRIPTION, oldDescription, description));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case LayoutPackage.DIAGRAM_LAYOUT__OWNED_LAYOUTS:
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
      case LayoutPackage.DIAGRAM_LAYOUT__ID:
        return getId();
      case LayoutPackage.DIAGRAM_LAYOUT__NAME:
        return getName();
      case LayoutPackage.DIAGRAM_LAYOUT__ACTUAL_MAPPING:
        return getActualMapping();
      case LayoutPackage.DIAGRAM_LAYOUT__APPLIED_FILTERS:
        return getAppliedFilters();
      case LayoutPackage.DIAGRAM_LAYOUT__APPLIED_STYLES:
        return getAppliedStyles();
      case LayoutPackage.DIAGRAM_LAYOUT__OWNED_LAYOUTS:
        return getOwnedLayouts();
      case LayoutPackage.DIAGRAM_LAYOUT__APPLIED_LAYERS:
        return getAppliedLayers();
      case LayoutPackage.DIAGRAM_LAYOUT__SYNCHRONIZED:
        return isSynchronized();
      case LayoutPackage.DIAGRAM_LAYOUT__DESCRIPTION:
        return getDescription();
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
      case LayoutPackage.DIAGRAM_LAYOUT__ID:
        setId((String)newValue);
        return;
      case LayoutPackage.DIAGRAM_LAYOUT__NAME:
        setName((String)newValue);
        return;
      case LayoutPackage.DIAGRAM_LAYOUT__ACTUAL_MAPPING:
        setActualMapping((String)newValue);
        return;
      case LayoutPackage.DIAGRAM_LAYOUT__APPLIED_FILTERS:
        getAppliedFilters().clear();
        getAppliedFilters().addAll((Collection<? extends String>)newValue);
        return;
      case LayoutPackage.DIAGRAM_LAYOUT__APPLIED_STYLES:
        getAppliedStyles().clear();
        getAppliedStyles().addAll((Collection<? extends String>)newValue);
        return;
      case LayoutPackage.DIAGRAM_LAYOUT__OWNED_LAYOUTS:
        getOwnedLayouts().clear();
        getOwnedLayouts().addAll((Collection<? extends ILayout>)newValue);
        return;
      case LayoutPackage.DIAGRAM_LAYOUT__APPLIED_LAYERS:
        getAppliedLayers().clear();
        getAppliedLayers().addAll((Collection<? extends String>)newValue);
        return;
      case LayoutPackage.DIAGRAM_LAYOUT__SYNCHRONIZED:
        setSynchronized((Boolean)newValue);
        return;
      case LayoutPackage.DIAGRAM_LAYOUT__DESCRIPTION:
        setDescription((String)newValue);
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
      case LayoutPackage.DIAGRAM_LAYOUT__ID:
        setId(ID_EDEFAULT);
        return;
      case LayoutPackage.DIAGRAM_LAYOUT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case LayoutPackage.DIAGRAM_LAYOUT__ACTUAL_MAPPING:
        setActualMapping(ACTUAL_MAPPING_EDEFAULT);
        return;
      case LayoutPackage.DIAGRAM_LAYOUT__APPLIED_FILTERS:
        getAppliedFilters().clear();
        return;
      case LayoutPackage.DIAGRAM_LAYOUT__APPLIED_STYLES:
        getAppliedStyles().clear();
        return;
      case LayoutPackage.DIAGRAM_LAYOUT__OWNED_LAYOUTS:
        getOwnedLayouts().clear();
        return;
      case LayoutPackage.DIAGRAM_LAYOUT__APPLIED_LAYERS:
        getAppliedLayers().clear();
        return;
      case LayoutPackage.DIAGRAM_LAYOUT__SYNCHRONIZED:
        setSynchronized(SYNCHRONIZED_EDEFAULT);
        return;
      case LayoutPackage.DIAGRAM_LAYOUT__DESCRIPTION:
        setDescription(DESCRIPTION_EDEFAULT);
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
      case LayoutPackage.DIAGRAM_LAYOUT__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case LayoutPackage.DIAGRAM_LAYOUT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case LayoutPackage.DIAGRAM_LAYOUT__ACTUAL_MAPPING:
        return ACTUAL_MAPPING_EDEFAULT == null ? actualMapping != null : !ACTUAL_MAPPING_EDEFAULT.equals(actualMapping);
      case LayoutPackage.DIAGRAM_LAYOUT__APPLIED_FILTERS:
        return appliedFilters != null && !appliedFilters.isEmpty();
      case LayoutPackage.DIAGRAM_LAYOUT__APPLIED_STYLES:
        return appliedStyles != null && !appliedStyles.isEmpty();
      case LayoutPackage.DIAGRAM_LAYOUT__OWNED_LAYOUTS:
        return ownedLayouts != null && !ownedLayouts.isEmpty();
      case LayoutPackage.DIAGRAM_LAYOUT__APPLIED_LAYERS:
        return appliedLayers != null && !appliedLayers.isEmpty();
      case LayoutPackage.DIAGRAM_LAYOUT__SYNCHRONIZED:
        return synchronized_ != SYNCHRONIZED_EDEFAULT;
      case LayoutPackage.DIAGRAM_LAYOUT__DESCRIPTION:
        return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
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
    result.append(", appliedLayers: ");
    result.append(appliedLayers);
    result.append(", synchronized: ");
    result.append(synchronized_);
    result.append(", description: ");
    result.append(description);
    result.append(')');
    return result.toString();
  }

} //DiagramLayoutImpl
