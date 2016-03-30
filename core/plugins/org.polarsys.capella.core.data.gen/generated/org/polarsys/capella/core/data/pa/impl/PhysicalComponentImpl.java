/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.pa.impl;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.LogicalComponentRealization;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Physical Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getAllocatorConfigurationItems <em>Allocator Configuration Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedPhysicalComponents <em>Owned Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedPhysicalComponentPkgs <em>Owned Physical Component Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedLogicalComponentRealizations <em>Owned Logical Component Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getLogicalComponentRealizations <em>Logical Component Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getLogicalInterfaceRealizations <em>Logical Interface Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getSubPhysicalComponents <em>Sub Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getRealizedLogicalComponents <em>Realized Logical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getAllocatedPhysicalFunctions <em>Allocated Physical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getDeployedPhysicalComponents <em>Deployed Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getDeployingPhysicalComponents <em>Deploying Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getDeployingPhysicalActors <em>Deploying Physical Actors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PhysicalComponentImpl extends AbstractPhysicalComponentImpl implements PhysicalComponent {





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
	 * The cached value of the '{@link #getOwnedLogicalComponentRealizations() <em>Owned Logical Component Realizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLogicalComponentRealizations()
	 * @generated
	 * @ordered
	 */
	protected EList<LogicalComponentRealization> ownedLogicalComponentRealizations;




































	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PhysicalComponentImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PaPackage.Literals.PHYSICAL_COMPONENT;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<ConfigurationItem> getAllocatorConfigurationItems() {


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
    EAnnotation annotation = CsPackage.Literals.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<ConfigurationItem> resultAsList = (Collection<ConfigurationItem>) result;
		return new EcoreEList.UnmodifiableEList<ConfigurationItem>(this, CsPackage.Literals.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalComponent> getOwnedPhysicalComponents() {

		if (ownedPhysicalComponents == null) {
			ownedPhysicalComponents = new EObjectContainmentEList.Resolving<PhysicalComponent>(PhysicalComponent.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS);
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
			ownedPhysicalComponentPkgs = new EObjectContainmentEList.Resolving<PhysicalComponentPkg>(PhysicalComponentPkg.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS);
		}
		return ownedPhysicalComponentPkgs;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<LogicalComponentRealization> getOwnedLogicalComponentRealizations() {

		if (ownedLogicalComponentRealizations == null) {
			ownedLogicalComponentRealizations = new EObjectContainmentEList.Resolving<LogicalComponentRealization>(LogicalComponentRealization.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_REALIZATIONS);
		}
		return ownedLogicalComponentRealizations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<LogicalComponentRealization> getLogicalComponentRealizations() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_COMPONENT__LOGICAL_COMPONENT_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_COMPONENT__LOGICAL_COMPONENT_REALIZATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<LogicalComponentRealization> resultAsList = (Collection<LogicalComponentRealization>) result;
		return new EcoreEList.UnmodifiableEList<LogicalComponentRealization>(this, PaPackage.Literals.PHYSICAL_COMPONENT__LOGICAL_COMPONENT_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<LogicalInterfaceRealization> getLogicalInterfaceRealizations() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_COMPONENT__LOGICAL_INTERFACE_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_COMPONENT__LOGICAL_INTERFACE_REALIZATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<LogicalInterfaceRealization> resultAsList = (Collection<LogicalInterfaceRealization>) result;
		return new EcoreEList.UnmodifiableEList<LogicalInterfaceRealization>(this, PaPackage.Literals.PHYSICAL_COMPONENT__LOGICAL_INTERFACE_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalComponent> getSubPhysicalComponents() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_COMPONENT__SUB_PHYSICAL_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_COMPONENT__SUB_PHYSICAL_COMPONENTS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PhysicalComponent> resultAsList = (Collection<PhysicalComponent>) result;
		return new EcoreEList.UnmodifiableEList<PhysicalComponent>(this, PaPackage.Literals.PHYSICAL_COMPONENT__SUB_PHYSICAL_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<LogicalComponent> getRealizedLogicalComponents() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<LogicalComponent> resultAsList = (Collection<LogicalComponent>) result;
		return new EcoreEList.UnmodifiableEList<LogicalComponent>(this, PaPackage.Literals.PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalFunction> getAllocatedPhysicalFunctions() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PhysicalFunction> resultAsList = (Collection<PhysicalFunction>) result;
		return new EcoreEList.UnmodifiableEList<PhysicalFunction>(this, PaPackage.Literals.PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalComponent> getDeployedPhysicalComponents() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PhysicalComponent> resultAsList = (Collection<PhysicalComponent>) result;
		return new EcoreEList.UnmodifiableEList<PhysicalComponent>(this, PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalComponent> getDeployingPhysicalComponents() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PhysicalComponent> resultAsList = (Collection<PhysicalComponent>) result;
		return new EcoreEList.UnmodifiableEList<PhysicalComponent>(this, PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalActor> getDeployingPhysicalActors() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_ACTORS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_ACTORS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PhysicalActor> resultAsList = (Collection<PhysicalActor>) result;
		return new EcoreEList.UnmodifiableEList<PhysicalActor>(this, PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_ACTORS, resultAsList.size(), resultAsList.toArray());
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS:
				return ((InternalEList<?>)getOwnedPhysicalComponents()).basicRemove(otherEnd, msgs);
			case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS:
				return ((InternalEList<?>)getOwnedPhysicalComponentPkgs()).basicRemove(otherEnd, msgs);
			case PaPackage.PHYSICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_REALIZATIONS:
				return ((InternalEList<?>)getOwnedLogicalComponentRealizations()).basicRemove(otherEnd, msgs);
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
			case PaPackage.PHYSICAL_COMPONENT__ALLOCATOR_CONFIGURATION_ITEMS:
				return getAllocatorConfigurationItems();
			case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS:
				return getOwnedPhysicalComponents();
			case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS:
				return getOwnedPhysicalComponentPkgs();
			case PaPackage.PHYSICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_REALIZATIONS:
				return getOwnedLogicalComponentRealizations();
			case PaPackage.PHYSICAL_COMPONENT__LOGICAL_COMPONENT_REALIZATIONS:
				return getLogicalComponentRealizations();
			case PaPackage.PHYSICAL_COMPONENT__LOGICAL_INTERFACE_REALIZATIONS:
				return getLogicalInterfaceRealizations();
			case PaPackage.PHYSICAL_COMPONENT__SUB_PHYSICAL_COMPONENTS:
				return getSubPhysicalComponents();
			case PaPackage.PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS:
				return getRealizedLogicalComponents();
			case PaPackage.PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS:
				return getAllocatedPhysicalFunctions();
			case PaPackage.PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS:
				return getDeployedPhysicalComponents();
			case PaPackage.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS:
				return getDeployingPhysicalComponents();
			case PaPackage.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_ACTORS:
				return getDeployingPhysicalActors();
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
			case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS:
				getOwnedPhysicalComponents().clear();
				getOwnedPhysicalComponents().addAll((Collection<? extends PhysicalComponent>)newValue);
				return;
			case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS:
				getOwnedPhysicalComponentPkgs().clear();
				getOwnedPhysicalComponentPkgs().addAll((Collection<? extends PhysicalComponentPkg>)newValue);
				return;
			case PaPackage.PHYSICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_REALIZATIONS:
				getOwnedLogicalComponentRealizations().clear();
				getOwnedLogicalComponentRealizations().addAll((Collection<? extends LogicalComponentRealization>)newValue);
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
			case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS:
				getOwnedPhysicalComponents().clear();
				return;
			case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS:
				getOwnedPhysicalComponentPkgs().clear();
				return;
			case PaPackage.PHYSICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_REALIZATIONS:
				getOwnedLogicalComponentRealizations().clear();
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
			case PaPackage.PHYSICAL_COMPONENT__ALLOCATOR_CONFIGURATION_ITEMS:
				return !getAllocatorConfigurationItems().isEmpty();
			case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS:
				return ownedPhysicalComponents != null && !ownedPhysicalComponents.isEmpty();
			case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS:
				return ownedPhysicalComponentPkgs != null && !ownedPhysicalComponentPkgs.isEmpty();
			case PaPackage.PHYSICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_REALIZATIONS:
				return ownedLogicalComponentRealizations != null && !ownedLogicalComponentRealizations.isEmpty();
			case PaPackage.PHYSICAL_COMPONENT__LOGICAL_COMPONENT_REALIZATIONS:
				return !getLogicalComponentRealizations().isEmpty();
			case PaPackage.PHYSICAL_COMPONENT__LOGICAL_INTERFACE_REALIZATIONS:
				return !getLogicalInterfaceRealizations().isEmpty();
			case PaPackage.PHYSICAL_COMPONENT__SUB_PHYSICAL_COMPONENTS:
				return !getSubPhysicalComponents().isEmpty();
			case PaPackage.PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS:
				return !getRealizedLogicalComponents().isEmpty();
			case PaPackage.PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS:
				return !getAllocatedPhysicalFunctions().isEmpty();
			case PaPackage.PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS:
				return !getDeployedPhysicalComponents().isEmpty();
			case PaPackage.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS:
				return !getDeployingPhysicalComponents().isEmpty();
			case PaPackage.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_ACTORS:
				return !getDeployingPhysicalActors().isEmpty();
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
		if (baseClass == AbstractPhysicalArtifact.class) {
			switch (derivedFeatureID) {
				case PaPackage.PHYSICAL_COMPONENT__ALLOCATOR_CONFIGURATION_ITEMS: return CsPackage.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS;
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
		if (baseClass == AbstractPhysicalArtifact.class) {
			switch (baseFeatureID) {
				case CsPackage.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS: return PaPackage.PHYSICAL_COMPONENT__ALLOCATOR_CONFIGURATION_ITEMS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}


} //PhysicalComponentImpl