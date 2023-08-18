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
package org.polarsys.capella.core.data.pa.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.capellacore.VisibilityKind;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.AssociationPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.KeyPart;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Physical Component Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentPkgImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentPkgImpl#getOwnedAssociations <em>Owned Associations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentPkgImpl#getOwnedPhysicalComponents <em>Owned Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentPkgImpl#getOwnedPhysicalComponentPkgs <em>Owned Physical Component Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentPkgImpl#getOwnedKeyParts <em>Owned Key Parts</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentPkgImpl#getOwnedDeployments <em>Owned Deployments</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PhysicalComponentPkgImpl extends ComponentPkgImpl implements PhysicalComponentPkg {

	/**
   * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getVisibility()
   * @generated
   * @ordered
   */
	protected static final VisibilityKind VISIBILITY_EDEFAULT = VisibilityKind.UNSET;

	/**
   * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getVisibility()
   * @generated
   * @ordered
   */
	protected VisibilityKind visibility = VISIBILITY_EDEFAULT;

	/**
   * The cached value of the '{@link #getOwnedAssociations() <em>Owned Associations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedAssociations()
   * @generated
   * @ordered
   */
	protected EList<Association> ownedAssociations;

	/**
   * The cached value of the '{@link #getOwnedPhysicalComponents() <em>Owned Physical Components</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPhysicalComponents()
   * @generated
   * @ordered
   */
	protected EList<PhysicalComponent> ownedPhysicalComponents;

	/**
   * The cached value of the '{@link #getOwnedPhysicalComponentPkgs() <em>Owned Physical Component Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPhysicalComponentPkgs()
   * @generated
   * @ordered
   */
	protected EList<PhysicalComponentPkg> ownedPhysicalComponentPkgs;





	/**
   * The cached value of the '{@link #getOwnedKeyParts() <em>Owned Key Parts</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedKeyParts()
   * @generated
   * @ordered
   */
	protected EList<KeyPart> ownedKeyParts;





	/**
   * The cached value of the '{@link #getOwnedDeployments() <em>Owned Deployments</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedDeployments()
   * @generated
   * @ordered
   */
	protected EList<AbstractDeploymentLink> ownedDeployments;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PhysicalComponentPkgImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return PaPackage.Literals.PHYSICAL_COMPONENT_PKG;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public VisibilityKind getVisibility() {

    return visibility;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setVisibility(VisibilityKind newVisibility) {

    VisibilityKind oldVisibility = visibility;
    visibility = newVisibility == null ? VISIBILITY_EDEFAULT : newVisibility;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_COMPONENT_PKG__VISIBILITY, oldVisibility, visibility));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Association> getOwnedAssociations() {

    if (ownedAssociations == null) {
      ownedAssociations = new EObjectContainmentEList<Association>(Association.class, this, PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_ASSOCIATIONS);
    }
    return ownedAssociations;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalComponent> getOwnedPhysicalComponents() {

    if (ownedPhysicalComponents == null) {
      ownedPhysicalComponents = new EObjectContainmentEList.Resolving<PhysicalComponent>(PhysicalComponent.class, this, PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS);
    }
    return ownedPhysicalComponents;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalComponentPkg> getOwnedPhysicalComponentPkgs() {

    if (ownedPhysicalComponentPkgs == null) {
      ownedPhysicalComponentPkgs = new EObjectContainmentEList.Resolving<PhysicalComponentPkg>(PhysicalComponentPkg.class, this, PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS);
    }
    return ownedPhysicalComponentPkgs;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<KeyPart> getOwnedKeyParts() {

    if (ownedKeyParts == null) {
      ownedKeyParts = new EObjectContainmentEList<KeyPart>(KeyPart.class, this, PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_KEY_PARTS);
    }
    return ownedKeyParts;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractDeploymentLink> getOwnedDeployments() {

    if (ownedDeployments == null) {
      ownedDeployments = new EObjectContainmentEList.Resolving<AbstractDeploymentLink>(AbstractDeploymentLink.class, this, PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_DEPLOYMENTS);
    }
    return ownedDeployments;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_ASSOCIATIONS:
        return ((InternalEList<?>)getOwnedAssociations()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS:
        return ((InternalEList<?>)getOwnedPhysicalComponents()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS:
        return ((InternalEList<?>)getOwnedPhysicalComponentPkgs()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_KEY_PARTS:
        return ((InternalEList<?>)getOwnedKeyParts()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_DEPLOYMENTS:
        return ((InternalEList<?>)getOwnedDeployments()).basicRemove(otherEnd, msgs);
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
      case PaPackage.PHYSICAL_COMPONENT_PKG__VISIBILITY:
        return getVisibility();
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_ASSOCIATIONS:
        return getOwnedAssociations();
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS:
        return getOwnedPhysicalComponents();
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS:
        return getOwnedPhysicalComponentPkgs();
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_KEY_PARTS:
        return getOwnedKeyParts();
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_DEPLOYMENTS:
        return getOwnedDeployments();
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
      case PaPackage.PHYSICAL_COMPONENT_PKG__VISIBILITY:
          setVisibility((VisibilityKind)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_ASSOCIATIONS:
        getOwnedAssociations().clear();
        getOwnedAssociations().addAll((Collection<? extends Association>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS:
        getOwnedPhysicalComponents().clear();
        getOwnedPhysicalComponents().addAll((Collection<? extends PhysicalComponent>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS:
        getOwnedPhysicalComponentPkgs().clear();
        getOwnedPhysicalComponentPkgs().addAll((Collection<? extends PhysicalComponentPkg>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_KEY_PARTS:
        getOwnedKeyParts().clear();
        getOwnedKeyParts().addAll((Collection<? extends KeyPart>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_DEPLOYMENTS:
        getOwnedDeployments().clear();
        getOwnedDeployments().addAll((Collection<? extends AbstractDeploymentLink>)newValue);
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
      case PaPackage.PHYSICAL_COMPONENT_PKG__VISIBILITY:
        setVisibility(VISIBILITY_EDEFAULT);
        return;
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_ASSOCIATIONS:
        getOwnedAssociations().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS:
        getOwnedPhysicalComponents().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS:
        getOwnedPhysicalComponentPkgs().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_KEY_PARTS:
        getOwnedKeyParts().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_DEPLOYMENTS:
        getOwnedDeployments().clear();
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
      case PaPackage.PHYSICAL_COMPONENT_PKG__VISIBILITY:
        return visibility != VISIBILITY_EDEFAULT;
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_ASSOCIATIONS:
        return ownedAssociations != null && !ownedAssociations.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS:
        return ownedPhysicalComponents != null && !ownedPhysicalComponents.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS:
        return ownedPhysicalComponentPkgs != null && !ownedPhysicalComponentPkgs.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_KEY_PARTS:
        return ownedKeyParts != null && !ownedKeyParts.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_DEPLOYMENTS:
        return ownedDeployments != null && !ownedDeployments.isEmpty();
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
    if (baseClass == AssociationPkg.class) {
      switch (derivedFeatureID) {
        case PaPackage.PHYSICAL_COMPONENT_PKG__VISIBILITY: return InformationPackage.ASSOCIATION_PKG__VISIBILITY;
        case PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_ASSOCIATIONS: return InformationPackage.ASSOCIATION_PKG__OWNED_ASSOCIATIONS;
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
    if (baseClass == AssociationPkg.class) {
      switch (baseFeatureID) {
        case InformationPackage.ASSOCIATION_PKG__VISIBILITY: return PaPackage.PHYSICAL_COMPONENT_PKG__VISIBILITY;
        case InformationPackage.ASSOCIATION_PKG__OWNED_ASSOCIATIONS: return PaPackage.PHYSICAL_COMPONENT_PKG__OWNED_ASSOCIATIONS;
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
    result.append(" (visibility: "); //$NON-NLS-1$
    result.append(visibility);
    result.append(')');
    return result.toString();
  }


} //PhysicalComponentPkgImpl