/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.vp.ms;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.kitalpha.emde.model.ElementExtension;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>CS Configuration</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.polarsys.capella.vp.ms.CSConfiguration#getSupportedModes <em>Supported Modes</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.CSConfiguration#getElements <em>Elements</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.CSConfiguration#getDeploymentLinks <em>Deployment Links</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.CSConfiguration#getFunctions <em>Functions</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.CSConfiguration#getFunctionalChains <em>Functional Chains</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.CSConfiguration#getComponents <em>Components</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.CSConfiguration#getPorts <em>Ports</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.CSConfiguration#getChildConfigurations <em>Child Configurations</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.CSConfiguration#getKind <em>Kind</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.CSConfiguration#getAccess <em>Access</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.CSConfiguration#getSelector <em>Selector</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.CSConfiguration#getContext <em>Context</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.vp.ms.MsPackage#getCSConfiguration()
 * @model annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/constraint ExtendedElement='
 *        http://www.polarsys.org/capella/core/cs/1.1.0#//Component'"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/constraintMapping
 *        Mapping='platform:/plugin/org.polarsys.capella.core.data.gen/model/CompositeStructure.ecore#//Component'"
 * @generated
 */

public interface CSConfiguration extends NamedElement, ElementExtension {

  /**
   * Returns the value of the '<em><b>Supported Modes</b></em>' reference list. The list contents are of type
   * {@link org.polarsys.capella.core.data.capellacommon.AbstractState}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Supported Modes</em>' reference list isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Supported Modes</em>' reference list.
   * @see org.polarsys.capella.vp.ms.MsPackage#getCSConfiguration_SupportedModes()
   * @model
   * @generated
   */

  EList<AbstractState> getSupportedModes();

  /**
   * Returns the value of the '<em><b>Elements</b></em>' reference list. The list contents are of type
   * {@link org.polarsys.capella.common.data.modellingcore.ModelElement}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elements</em>' reference list isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Elements</em>' reference list.
   * @see org.polarsys.capella.vp.ms.MsPackage#getCSConfiguration_Elements()
   * @model
   * @generated
   */

  EList<ModelElement> getElements();

  /**
   * Returns the value of the '<em><b>Deployment Links</b></em>' reference list. The list contents are of type
   * {@link org.polarsys.capella.core.data.cs.AbstractDeploymentLink}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Deployment Links</em>' reference list isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Deployment Links</em>' reference list.
   * @see org.polarsys.capella.vp.ms.MsPackage#getCSConfiguration_DeploymentLinks()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */

  EList<AbstractDeploymentLink> getDeploymentLinks();

  /**
   * Returns the value of the '<em><b>Functions</b></em>' reference list. The list contents are of type
   * {@link org.polarsys.capella.core.data.fa.AbstractFunction}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Functions</em>' reference list isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Functions</em>' reference list.
   * @see org.polarsys.capella.vp.ms.MsPackage#getCSConfiguration_Functions()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */

  EList<AbstractFunction> getFunctions();

  /**
   * Returns the value of the '<em><b>Functional Chains</b></em>' reference list. The list contents are of type
   * {@link org.polarsys.capella.core.data.fa.FunctionalChain}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Functional Chains</em>' reference list isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Functional Chains</em>' reference list.
   * @see org.polarsys.capella.vp.ms.MsPackage#getCSConfiguration_FunctionalChains()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */

  EList<FunctionalChain> getFunctionalChains();

  /**
   * Returns the value of the '<em><b>Components</b></em>' reference list. The list contents are of type
   * {@link org.polarsys.capella.core.data.cs.Component}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Components</em>' reference list isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Components</em>' reference list.
   * @see org.polarsys.capella.vp.ms.MsPackage#getCSConfiguration_Components()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */

  EList<Component> getComponents();

  /**
   * Returns the value of the '<em><b>Ports</b></em>' reference list. The list contents are of type
   * {@link org.polarsys.capella.core.data.information.Port}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ports</em>' reference list isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Ports</em>' reference list.
   * @see org.polarsys.capella.vp.ms.MsPackage#getCSConfiguration_Ports()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */

  EList<Port> getPorts();

  /**
   * Returns the value of the '<em><b>Child Configurations</b></em>' reference list. The list contents are of type
   * {@link org.polarsys.capella.vp.ms.CSConfiguration}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Child Configurations</em>' reference list isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Child Configurations</em>' reference list.
   * @see org.polarsys.capella.vp.ms.MsPackage#getCSConfiguration_ChildConfigurations()
   * @model
   * @generated
   */

  EList<CSConfiguration> getChildConfigurations();

  /**
   * Returns the value of the '<em><b>Kind</b></em>' attribute. The literals are from the enumeration
   * {@link org.polarsys.capella.vp.ms.kind_Type}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Kind</em>' attribute isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.vp.ms.kind_Type
   * @see #setKind(kind_Type)
   * @see org.polarsys.capella.vp.ms.MsPackage#getCSConfiguration_Kind()
   * @model
   * @generated
   */

  kind_Type getKind();

  /**
   * Sets the value of the '{@link org.polarsys.capella.vp.ms.CSConfiguration#getKind <em>Kind</em>}' attribute.
   * 
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.vp.ms.kind_Type
   * @see #getKind()
   * @generated
   */

  void setKind(kind_Type value);

  /**
   * Returns the value of the '<em><b>Access</b></em>' attribute. The literals are from the enumeration
   * {@link org.polarsys.capella.vp.ms.access_Type}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Access</em>' attribute isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Access</em>' attribute.
   * @see org.polarsys.capella.vp.ms.access_Type
   * @see #setAccess(access_Type)
   * @see org.polarsys.capella.vp.ms.MsPackage#getCSConfiguration_Access()
   * @model
   * @generated
   */

  access_Type getAccess();

  /**
   * Sets the value of the '{@link org.polarsys.capella.vp.ms.CSConfiguration#getAccess <em>Access</em>}' attribute.
   * 
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>Access</em>' attribute.
   * @see org.polarsys.capella.vp.ms.access_Type
   * @see #getAccess()
   * @generated
   */

  void setAccess(access_Type value);

  /**
   * Returns the value of the '<em><b>Selector</b></em>' attribute. The literals are from the enumeration
   * {@link org.polarsys.capella.vp.ms.selector_Type}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Selector</em>' attribute isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Selector</em>' attribute.
   * @see org.polarsys.capella.vp.ms.selector_Type
   * @see #setSelector(selector_Type)
   * @see org.polarsys.capella.vp.ms.MsPackage#getCSConfiguration_Selector()
   * @model
   * @generated
   */

  selector_Type getSelector();

  /**
   * Sets the value of the '{@link org.polarsys.capella.vp.ms.CSConfiguration#getSelector <em>Selector</em>}' attribute.
   * 
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>Selector</em>' attribute.
   * @see org.polarsys.capella.vp.ms.selector_Type
   * @see #getSelector()
   * @generated
   */

  void setSelector(selector_Type value);

  /**
   * Returns the value of the '<em><b>Context</b></em>' reference list. The list contents are of type
   * {@link org.polarsys.capella.vp.ms.Situation}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Context</em>' reference list isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Context</em>' reference list.
   * @see org.polarsys.capella.vp.ms.MsPackage#getCSConfiguration_Context()
   * @model
   * @generated
   */

  EList<Situation> getContext();

  /**
   * 
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @model required="true" elementRequired="true"
   * @generated
   */

  boolean includes(ModelElement element);

  /**
   * 
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @model kind="operation"
   * @generated
   */

  EList<ModelElement> getScope();

  /**
   * 
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @model
   * @generated
   */

  <T extends ModelElement> EList<T> getScope(Class<T> type);

} // CSConfiguration
