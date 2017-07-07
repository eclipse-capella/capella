/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *    Altran - Compare Configurations
 *******************************************************************************/

package org.polarsys.capella.vp.ms.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.MsPackage;
import org.polarsys.capella.vp.ms.Situation;
import org.polarsys.capella.vp.ms.access_Type;
import org.polarsys.capella.vp.ms.kind_Type;
import org.polarsys.capella.vp.ms.selector_Type;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>CS Configuration</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.polarsys.capella.vp.ms.impl.CSConfigurationImpl#getSupportedModes <em>Supported Modes</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.impl.CSConfigurationImpl#getElements <em>Elements</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.impl.CSConfigurationImpl#getDeploymentLinks <em>Deployment Links</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.impl.CSConfigurationImpl#getFunctions <em>Functions</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.impl.CSConfigurationImpl#getFunctionalChains <em>Functional Chains</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.impl.CSConfigurationImpl#getComponents <em>Components</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.impl.CSConfigurationImpl#getPorts <em>Ports</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.impl.CSConfigurationImpl#getSubsetOf <em>Subset Of</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.impl.CSConfigurationImpl#getKind <em>Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CSConfigurationImpl extends NamedElementImpl implements CSConfiguration {

  /**
   * The cached value of the '{@link #getSupportedModes() <em>Supported Modes</em>}' reference list. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @see #getSupportedModes()
   * @generated
   * @ordered
   */
  protected EList<AbstractState> supportedModes;

  /**
   * The cached value of the '{@link #getElements() <em>Elements</em>}' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see #getElements()
   * @generated
   * @ordered
   */
  protected EList<ModelElement> elements;

  /**
   * The cached value of the '{@link #getChildConfigurations() <em>Child Configurations</em>}' reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getChildConfigurations()
   * @generated
   * @ordered
   */
  protected EList<CSConfiguration> childConfigurations;

  /**
   * The default value of the '{@link #getKind() <em>Kind</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @see #getKind()
   * @generated
   * @ordered
   */
  protected static final kind_Type KIND_EDEFAULT = kind_Type.ATOMIC;

  /**
   * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getKind()
   * @generated
   * @ordered
   */
  protected kind_Type kind = KIND_EDEFAULT;

  /**
   * The default value of the '{@link #getAccess() <em>Access</em>}' attribute. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see #getAccess()
   * @generated
   * @ordered
   */
  protected static final access_Type ACCESS_EDEFAULT = access_Type.FLAT;

  /**
   * The cached value of the '{@link #getAccess() <em>Access</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @see #getAccess()
   * @generated
   * @ordered
   */
  protected access_Type access = ACCESS_EDEFAULT;

  /**
   * The default value of the '{@link #getSelector() <em>Selector</em>}' attribute. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see #getSelector()
   * @generated
   * @ordered
   */
  protected static final selector_Type SELECTOR_EDEFAULT = selector_Type.INCLUSION;

  /**
   * The cached value of the '{@link #getSelector() <em>Selector</em>}' attribute. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see #getSelector()
   * @generated
   * @ordered
   */
  protected selector_Type selector = SELECTOR_EDEFAULT;

  /**
   * The cached value of the '{@link #getContext() <em>Context</em>}' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see #getContext()
   * @generated
   * @ordered
   */
  protected EList<Situation> context;

  /**
   * The cached value of the '{@link #getCompareTo() <em>Compare To</em>}' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see #getCompareTo()
   * @generated
   * @ordered
   */
  protected EList<CSConfiguration> compareTo;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected CSConfigurationImpl() {

    super();

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return MsPackage.Literals.CS_CONFIGURATION;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public EList<AbstractState> getSupportedModes() {

    if (supportedModes == null) {
      supportedModes = new EObjectResolvingEList<AbstractState>(AbstractState.class, this,
          MsPackage.CS_CONFIGURATION__SUPPORTED_MODES);
    }
    return supportedModes;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public EList<ModelElement> getElements() {

    if (elements == null) {
      elements = new EObjectResolvingEList<ModelElement>(ModelElement.class, this,
          MsPackage.CS_CONFIGURATION__ELEMENTS);
    }
    return elements;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public EList<AbstractDeploymentLink> getDeploymentLinks() {
    Object[] data = getElementsFiltered(AbstractDeploymentLink.class);
    return new EcoreEList.UnmodifiableEList<AbstractDeploymentLink>(this,
        MsPackage.Literals.CS_CONFIGURATION__DEPLOYMENT_LINKS, data.length, data);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public EList<AbstractFunction> getFunctions() {
    Object[] data = getElementsFiltered(AbstractFunction.class);
    return new EcoreEList.UnmodifiableEList<AbstractFunction>(this, MsPackage.Literals.CS_CONFIGURATION__FUNCTIONS,
        data.length, data);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public EList<FunctionalChain> getFunctionalChains() {
    Object[] data = getElementsFiltered(FunctionalChain.class);
    return new EcoreEList.UnmodifiableEList<FunctionalChain>(this,
        MsPackage.Literals.CS_CONFIGURATION__FUNCTIONAL_CHAINS, data.length, data);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public EList<Component> getComponents() {
    Object[] data = getElementsFiltered(Component.class);
    return new EcoreEList.UnmodifiableEList<Component>(this, MsPackage.Literals.CS_CONFIGURATION__COMPONENTS,
        data.length, data);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public EList<Port> getPorts() {
    Object[] data = getElementsFiltered(Port.class);
    return new EcoreEList.UnmodifiableEList<Port>(this, MsPackage.Literals.CS_CONFIGURATION__PORTS, data.length, data);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public EList<CSConfiguration> getChildConfigurations() {

    if (childConfigurations == null) {
      childConfigurations = new EObjectResolvingEList<CSConfiguration>(CSConfiguration.class, this,
          MsPackage.CS_CONFIGURATION__CHILD_CONFIGURATIONS);
    }
    return childConfigurations;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public kind_Type getKind() {

    return kind;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public void setKind(kind_Type newKind) {

    kind_Type oldKind = kind;
    kind = newKind == null ? KIND_EDEFAULT : newKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MsPackage.CS_CONFIGURATION__KIND, oldKind, kind));

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public access_Type getAccess() {

    return access;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public void setAccess(access_Type newAccess) {

    access_Type oldAccess = access;
    access = newAccess == null ? ACCESS_EDEFAULT : newAccess;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MsPackage.CS_CONFIGURATION__ACCESS, oldAccess, access));

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public selector_Type getSelector() {

    return selector;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public void setSelector(selector_Type newSelector) {

    selector_Type oldSelector = selector;
    selector = newSelector == null ? SELECTOR_EDEFAULT : newSelector;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(this, Notification.SET, MsPackage.CS_CONFIGURATION__SELECTOR, oldSelector, selector));

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public EList<Situation> getContext() {

    if (context == null) {
      context = new EObjectResolvingEList<Situation>(Situation.class, this, MsPackage.CS_CONFIGURATION__CONTEXT);
    }
    return context;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public EList<CSConfiguration> getCompareTo() {

    if (compareTo == null) {
      compareTo = new EObjectResolvingEList<CSConfiguration>(CSConfiguration.class, this,
          MsPackage.CS_CONFIGURATION__COMPARE_TO);
    }
    return compareTo;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */

  public boolean includes(ModelElement element) {

    if (getScope().contains(element)) {

      if (getSelector() == selector_Type.INCLUSION) {

        if (getElements().contains(element)) {

          return true;

        } else {

          return false;

        }

      } else {

        ModelElement current = element;

        do {

          if (getElements().contains(current)) {

            return false;

          } else {

            // the current element is not explitly included in the inactive
            // elements list, but maybe one of its logical ancestors is.
            // e.g. a function that is allocated in an inactive component
            // is implicitly excluded

            if (current instanceof AbstractFunction) {

              for (ComponentFunctionalAllocation alloc : ((AbstractFunction) current)
                  .getComponentFunctionalAllocations()) {
                current = alloc.getBlock();
              }

            } else if (current instanceof PhysicalComponent) {

              if (((PhysicalComponent) current).getDeployingPhysicalActors().size() > 0) {

                current = ((PhysicalComponent) current).getDeployingPhysicalActors().get(0);

              } else if (((PhysicalComponent) current).getDeployingPhysicalComponents().size() > 0) {

                current = ((PhysicalComponent) current).getDeployingPhysicalComponents().get(0);

              } else {

                current = (ModelElement) current.eContainer();
              }

            } else {

              current = (ModelElement) current.eContainer();

            }
          }

        } while (getScope().contains(current));

        return true;

      }

    }

    return false;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */

  public boolean excludes(ModelElement element) {

    // TODO: implement this method
    // Ensure that you remove @generated or mark it @generated NOT
    throw new UnsupportedOperationException();

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public EList<ModelElement> getScope() {
    Component parent = (Component) eContainer();
    EList<ModelElement> result = new UniqueEList<ModelElement>();
    if (parent instanceof System) {
      recurseComponentsForComboBox(result, (System) parent);
    } else if (parent instanceof LogicalComponent) {
      recurseComponentsForComboBox(result, (LogicalComponent) parent);
    } else if (parent instanceof LogicalActor) {
      recurseComponentsForComboBox(result, (LogicalActor) parent);
    } else if (parent instanceof PhysicalComponent) {
      recurseComponentsForComboBox(result, (PhysicalComponent) parent);
    } else if (parent instanceof PhysicalActor) {
      recurseComponentsForComboBox(result, (PhysicalActor) parent);
    }
    return result;

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  @SuppressWarnings("unchecked")
  public <T extends ModelElement> EList<T> getScope(Class<T> type) {
    EList<T> result = new BasicEList<T>();
    for (ModelElement e : getScope()) {
      if (type.isInstance(e)) {
        result.add((T) e);
      }
    }
    return result;
  }

  private void recurseComponentsForComboBox(Collection<ModelElement> result, System parent) {
    addComboBoxElements(result, parent);
  }

  private void recurseComponentsForComboBox(Collection<ModelElement> result, LogicalComponent parent) {
    addComboBoxElements(result, parent);
    if (getAccess() == access_Type.SUBCOMPONENTS || getAccess() == access_Type.FULL) {
      for (LogicalComponent child : parent.getOwnedLogicalComponents()) {
        recurseComponentsForComboBox(result, child);
      }
    }

  }

  private void recurseComponentsForComboBox(Collection<ModelElement> result, LogicalActor parent) {
    addComboBoxElements(result, parent);
  }

  private void recurseComponentsForComboBox(Collection<ModelElement> result, PhysicalComponent parent) {
    addComboBoxElements(result, parent);

    if (getAccess() == access_Type.SUBCOMPONENTS || getAccess() == access_Type.FULL) {
      for (PhysicalComponent owned : parent.getOwnedPhysicalComponents()) {
        recurseComponentsForComboBox(result, owned);
      }
    }

    if (getAccess() == access_Type.FULL) {
      for (PhysicalComponent deployed : parent.getDeployedPhysicalComponents()) {
        recurseComponentsForComboBox(result, deployed);
      }
    }
  }

  private void recurseComponentsForComboBox(Collection<ModelElement> result, PhysicalActor parent) {
    addComboBoxElements(result, (PhysicalActor) parent);

    if (getAccess() == access_Type.FULL) {
      for (PhysicalComponent child : parent.getDeployedPhysicalComponents()) {
        recurseComponentsForComboBox(result, child);
      }
    }
  }

  private void addComboBoxElements(Collection<ModelElement> result, Component parent) {

    for (AbstractFunction allocated : parent.getAllocatedFunctions()) {
      result.add(allocated);
      for (Port port : FunctionExt.getOwnedFunctionPorts(allocated)) {
        result.add(port);
      }
      for (FunctionalChain chain : allocated.getInvolvingFunctionalChains()) {
        result.add(chain);
      }
    }

    result.addAll(parent.getContainedComponentPorts());
    result.addAll(parent.getContainedPhysicalPorts());
  }

  private void addComboBoxElements(Collection<ModelElement> result, System parent) {
    addComboBoxElements(result, (Component) parent);
  }

  private void addComboBoxElements(Collection<ModelElement> result, LogicalComponent parent) {
    addComboBoxElements(result, (Component) parent);
    for (LogicalComponent child : parent.getOwnedLogicalComponents()) {
      result.add(child);
    }
  }

  private void addComboBoxElements(Collection<ModelElement> result, PhysicalComponent parent) {
    addComboBoxElements(result, (Component) parent);

    for (PhysicalComponent child : parent.getOwnedPhysicalComponents()) {
      result.add(child);
    }
    for (PhysicalComponent deployed : parent.getDeployedPhysicalComponents()) {
      result.add(deployed);
    }

  }

  private void addComboBoxElements(Collection<ModelElement> result, PhysicalActor parent) {
    addComboBoxElements(result, (Component) parent);
    for (PhysicalComponent deployed : parent.getDeployedPhysicalComponents()) {
      result.add(deployed);
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case MsPackage.CS_CONFIGURATION__SUPPORTED_MODES:
      return getSupportedModes();
    case MsPackage.CS_CONFIGURATION__ELEMENTS:
      return getElements();
    case MsPackage.CS_CONFIGURATION__DEPLOYMENT_LINKS:
      return getDeploymentLinks();
    case MsPackage.CS_CONFIGURATION__FUNCTIONS:
      return getFunctions();
    case MsPackage.CS_CONFIGURATION__FUNCTIONAL_CHAINS:
      return getFunctionalChains();
    case MsPackage.CS_CONFIGURATION__COMPONENTS:
      return getComponents();
    case MsPackage.CS_CONFIGURATION__PORTS:
      return getPorts();
    case MsPackage.CS_CONFIGURATION__CHILD_CONFIGURATIONS:
      return getChildConfigurations();
    case MsPackage.CS_CONFIGURATION__KIND:
      return getKind();
    case MsPackage.CS_CONFIGURATION__ACCESS:
      return getAccess();
    case MsPackage.CS_CONFIGURATION__SELECTOR:
      return getSelector();
    case MsPackage.CS_CONFIGURATION__CONTEXT:
      return getContext();
    case MsPackage.CS_CONFIGURATION__COMPARE_TO:
      return getCompareTo();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case MsPackage.CS_CONFIGURATION__SUPPORTED_MODES:
      getSupportedModes().clear();
      getSupportedModes().addAll((Collection<? extends AbstractState>) newValue);
      return;
    case MsPackage.CS_CONFIGURATION__ELEMENTS:
      getElements().clear();
      getElements().addAll((Collection<? extends ModelElement>) newValue);
      return;
    case MsPackage.CS_CONFIGURATION__CHILD_CONFIGURATIONS:
      getChildConfigurations().clear();
      getChildConfigurations().addAll((Collection<? extends CSConfiguration>) newValue);
      return;
    case MsPackage.CS_CONFIGURATION__KIND:
      setKind((kind_Type) newValue);
      return;
    case MsPackage.CS_CONFIGURATION__ACCESS:
      setAccess((access_Type) newValue);
      return;
    case MsPackage.CS_CONFIGURATION__SELECTOR:
      setSelector((selector_Type) newValue);
      return;
    case MsPackage.CS_CONFIGURATION__CONTEXT:
      getContext().clear();
      getContext().addAll((Collection<? extends Situation>) newValue);
      return;
    case MsPackage.CS_CONFIGURATION__COMPARE_TO:
      getCompareTo().clear();
      getCompareTo().addAll((Collection<? extends CSConfiguration>) newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
    case MsPackage.CS_CONFIGURATION__SUPPORTED_MODES:
      getSupportedModes().clear();
      return;
    case MsPackage.CS_CONFIGURATION__ELEMENTS:
      getElements().clear();
      return;
    case MsPackage.CS_CONFIGURATION__CHILD_CONFIGURATIONS:
      getChildConfigurations().clear();
      return;
    case MsPackage.CS_CONFIGURATION__KIND:
      setKind(KIND_EDEFAULT);
      return;
    case MsPackage.CS_CONFIGURATION__ACCESS:
      setAccess(ACCESS_EDEFAULT);
      return;
    case MsPackage.CS_CONFIGURATION__SELECTOR:
      setSelector(SELECTOR_EDEFAULT);
      return;
    case MsPackage.CS_CONFIGURATION__CONTEXT:
      getContext().clear();
      return;
    case MsPackage.CS_CONFIGURATION__COMPARE_TO:
      getCompareTo().clear();
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case MsPackage.CS_CONFIGURATION__SUPPORTED_MODES:
      return supportedModes != null && !supportedModes.isEmpty();
    case MsPackage.CS_CONFIGURATION__ELEMENTS:
      return elements != null && !elements.isEmpty();
    case MsPackage.CS_CONFIGURATION__DEPLOYMENT_LINKS:
      return !getDeploymentLinks().isEmpty();
    case MsPackage.CS_CONFIGURATION__FUNCTIONS:
      return !getFunctions().isEmpty();
    case MsPackage.CS_CONFIGURATION__FUNCTIONAL_CHAINS:
      return !getFunctionalChains().isEmpty();
    case MsPackage.CS_CONFIGURATION__COMPONENTS:
      return !getComponents().isEmpty();
    case MsPackage.CS_CONFIGURATION__PORTS:
      return !getPorts().isEmpty();
    case MsPackage.CS_CONFIGURATION__CHILD_CONFIGURATIONS:
      return childConfigurations != null && !childConfigurations.isEmpty();
    case MsPackage.CS_CONFIGURATION__KIND:
      return kind != KIND_EDEFAULT;
    case MsPackage.CS_CONFIGURATION__ACCESS:
      return access != ACCESS_EDEFAULT;
    case MsPackage.CS_CONFIGURATION__SELECTOR:
      return selector != SELECTOR_EDEFAULT;
    case MsPackage.CS_CONFIGURATION__CONTEXT:
      return context != null && !context.isEmpty();
    case MsPackage.CS_CONFIGURATION__COMPARE_TO:
      return compareTo != null && !compareTo.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy())
      return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (kind: "); //$NON-NLS-1$
    result.append(kind);
    result.append(", access: "); //$NON-NLS-1$
    result.append(access);
    result.append(", selector: "); //$NON-NLS-1$
    result.append(selector);
    result.append(')');
    return result.toString();
  }

  private Object[] getElementsFiltered(Class<?> clazz) {
    ArrayList<Object> result = new ArrayList<Object>();
    for (ModelElement elem : getElements()) {
      if (clazz.isInstance(elem)) {
        result.add(elem);
      }
    }
    return result.toArray();
  }

} // CSConfigurationImpl