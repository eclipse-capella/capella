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

package org.polarsys.capella.core.data.cs.impl;

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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.cs.PhysicalPortRealization;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.data.information.impl.AbstractInstanceImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Physical Port</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getIncomingPortRealizations <em>Incoming Port Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOutgoingPortRealizations <em>Outgoing Port Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOwnedProtocols <em>Owned Protocols</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getIncomingPortAllocations <em>Incoming Port Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOutgoingPortAllocations <em>Outgoing Port Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getProvidedInterfaces <em>Provided Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getRequiredInterfaces <em>Required Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOwnedPortRealizations <em>Owned Port Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOwnedPortAllocations <em>Owned Port Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getAllocatorConfigurationItems <em>Allocator Configuration Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getIncomingInformationFlows <em>Incoming Information Flows</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOutgoingInformationFlows <em>Outgoing Information Flows</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getInformationFlows <em>Information Flows</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getInvolvedLinks <em>Involved Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOwnedComponentPortAllocations <em>Owned Component Port Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOwnedPhysicalPortRealizations <em>Owned Physical Port Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getAllocatedComponentPorts <em>Allocated Component Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getRealizedPhysicalPorts <em>Realized Physical Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getRealizingPhysicalPorts <em>Realizing Physical Ports</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PhysicalPortImpl extends AbstractInstanceImpl implements PhysicalPort {









	/**
	 * The cached value of the '{@link #getOwnedProtocols() <em>Owned Protocols</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedProtocols()
	 * @generated
	 * @ordered
	 */
	protected EList<StateMachine> ownedProtocols;













	/**
	 * The cached value of the '{@link #getProvidedInterfaces() <em>Provided Interfaces</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvidedInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList<Interface> providedInterfaces;





	/**
	 * The cached value of the '{@link #getRequiredInterfaces() <em>Required Interfaces</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList<Interface> requiredInterfaces;





	/**
	 * The cached value of the '{@link #getOwnedPortRealizations() <em>Owned Port Realizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPortRealizations()
	 * @generated
	 * @ordered
	 */
	protected EList<PortRealization> ownedPortRealizations;





	/**
	 * The cached value of the '{@link #getOwnedPortAllocations() <em>Owned Port Allocations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPortAllocations()
	 * @generated
	 * @ordered
	 */
	protected EList<PortAllocation> ownedPortAllocations;

























	/**
	 * The cached value of the '{@link #getOwnedComponentPortAllocations() <em>Owned Component Port Allocations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedComponentPortAllocations()
	 * @generated
	 * @ordered
	 */
	protected EList<ComponentPortAllocation> ownedComponentPortAllocations;





	/**
	 * The cached value of the '{@link #getOwnedPhysicalPortRealizations() <em>Owned Physical Port Realizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPhysicalPortRealizations()
	 * @generated
	 * @ordered
	 */
	protected EList<PhysicalPortRealization> ownedPhysicalPortRealizations;
















	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PhysicalPortImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CsPackage.Literals.PHYSICAL_PORT;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<PortRealization> getIncomingPortRealizations() {


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
    EAnnotation annotation = InformationPackage.Literals.PORT__INCOMING_PORT_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.PORT__INCOMING_PORT_REALIZATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PortRealization> resultAsList = (Collection<PortRealization>) result;
		return new EcoreEList.UnmodifiableEList<PortRealization>(this, InformationPackage.Literals.PORT__INCOMING_PORT_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PortRealization> getOutgoingPortRealizations() {


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
    EAnnotation annotation = InformationPackage.Literals.PORT__OUTGOING_PORT_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.PORT__OUTGOING_PORT_REALIZATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PortRealization> resultAsList = (Collection<PortRealization>) result;
		return new EcoreEList.UnmodifiableEList<PortRealization>(this, InformationPackage.Literals.PORT__OUTGOING_PORT_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<StateMachine> getOwnedProtocols() {

		if (ownedProtocols == null) {
			ownedProtocols = new EObjectContainmentEList.Resolving<StateMachine>(StateMachine.class, this, CsPackage.PHYSICAL_PORT__OWNED_PROTOCOLS);
		}
		return ownedProtocols;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<PortAllocation> getIncomingPortAllocations() {


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
    EAnnotation annotation = InformationPackage.Literals.PORT__INCOMING_PORT_ALLOCATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.PORT__INCOMING_PORT_ALLOCATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PortAllocation> resultAsList = (Collection<PortAllocation>) result;
		return new EcoreEList.UnmodifiableEList<PortAllocation>(this, InformationPackage.Literals.PORT__INCOMING_PORT_ALLOCATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PortAllocation> getOutgoingPortAllocations() {


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
    EAnnotation annotation = InformationPackage.Literals.PORT__OUTGOING_PORT_ALLOCATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.PORT__OUTGOING_PORT_ALLOCATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PortAllocation> resultAsList = (Collection<PortAllocation>) result;
		return new EcoreEList.UnmodifiableEList<PortAllocation>(this, InformationPackage.Literals.PORT__OUTGOING_PORT_ALLOCATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Interface> getProvidedInterfaces() {

		if (providedInterfaces == null) {
			providedInterfaces = new EObjectResolvingEList<Interface>(Interface.class, this, CsPackage.PHYSICAL_PORT__PROVIDED_INTERFACES);
		}
		return providedInterfaces;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<Interface> getRequiredInterfaces() {

		if (requiredInterfaces == null) {
			requiredInterfaces = new EObjectResolvingEList<Interface>(Interface.class, this, CsPackage.PHYSICAL_PORT__REQUIRED_INTERFACES);
		}
		return requiredInterfaces;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<PortRealization> getOwnedPortRealizations() {

		if (ownedPortRealizations == null) {
			ownedPortRealizations = new EObjectContainmentEList.Resolving<PortRealization>(PortRealization.class, this, CsPackage.PHYSICAL_PORT__OWNED_PORT_REALIZATIONS);
		}
		return ownedPortRealizations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<PortAllocation> getOwnedPortAllocations() {

		if (ownedPortAllocations == null) {
			ownedPortAllocations = new EObjectContainmentEList.Resolving<PortAllocation>(PortAllocation.class, this, CsPackage.PHYSICAL_PORT__OWNED_PORT_ALLOCATIONS);
		}
		return ownedPortAllocations;
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

	public EList<AbstractInformationFlow> getIncomingInformationFlows() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<AbstractInformationFlow> resultAsList = (Collection<AbstractInformationFlow>) result;
		return new EcoreEList.UnmodifiableEList<AbstractInformationFlow>(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractInformationFlow> getOutgoingInformationFlows() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<AbstractInformationFlow> resultAsList = (Collection<AbstractInformationFlow>) result;
		return new EcoreEList.UnmodifiableEList<AbstractInformationFlow>(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractInformationFlow> getInformationFlows() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<AbstractInformationFlow> resultAsList = (Collection<AbstractInformationFlow>) result;
		return new EcoreEList.UnmodifiableEList<AbstractInformationFlow>(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalLink> getInvolvedLinks() {


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
    EAnnotation annotation = CsPackage.Literals.ABSTRACT_PHYSICAL_LINK_END__INVOLVED_LINKS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.ABSTRACT_PHYSICAL_LINK_END__INVOLVED_LINKS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PhysicalLink> resultAsList = (Collection<PhysicalLink>) result;
		return new EcoreEList.UnmodifiableEList<PhysicalLink>(this, CsPackage.Literals.ABSTRACT_PHYSICAL_LINK_END__INVOLVED_LINKS, resultAsList.size(), resultAsList.toArray());
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

	public EList<ComponentPortAllocation> getOwnedComponentPortAllocations() {

		if (ownedComponentPortAllocations == null) {
			ownedComponentPortAllocations = new EObjectContainmentEList.Resolving<ComponentPortAllocation>(ComponentPortAllocation.class, this, CsPackage.PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS);
		}
		return ownedComponentPortAllocations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<PhysicalPortRealization> getOwnedPhysicalPortRealizations() {

		if (ownedPhysicalPortRealizations == null) {
			ownedPhysicalPortRealizations = new EObjectContainmentEList.Resolving<PhysicalPortRealization>(PhysicalPortRealization.class, this, CsPackage.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS);
		}
		return ownedPhysicalPortRealizations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<ComponentPort> getAllocatedComponentPorts() {


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
    EAnnotation annotation = CsPackage.Literals.PHYSICAL_PORT__ALLOCATED_COMPONENT_PORTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PHYSICAL_PORT__ALLOCATED_COMPONENT_PORTS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<ComponentPort> resultAsList = (Collection<ComponentPort>) result;
		return new EcoreEList.UnmodifiableEList<ComponentPort>(this, CsPackage.Literals.PHYSICAL_PORT__ALLOCATED_COMPONENT_PORTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalPort> getRealizedPhysicalPorts() {


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
    EAnnotation annotation = CsPackage.Literals.PHYSICAL_PORT__REALIZED_PHYSICAL_PORTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PHYSICAL_PORT__REALIZED_PHYSICAL_PORTS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PhysicalPort> resultAsList = (Collection<PhysicalPort>) result;
		return new EcoreEList.UnmodifiableEList<PhysicalPort>(this, CsPackage.Literals.PHYSICAL_PORT__REALIZED_PHYSICAL_PORTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalPort> getRealizingPhysicalPorts() {


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
    EAnnotation annotation = CsPackage.Literals.PHYSICAL_PORT__REALIZING_PHYSICAL_PORTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PHYSICAL_PORT__REALIZING_PHYSICAL_PORTS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PhysicalPort> resultAsList = (Collection<PhysicalPort>) result;
		return new EcoreEList.UnmodifiableEList<PhysicalPort>(this, CsPackage.Literals.PHYSICAL_PORT__REALIZING_PHYSICAL_PORTS, resultAsList.size(), resultAsList.toArray());
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
			case CsPackage.PHYSICAL_PORT__OWNED_PROTOCOLS:
				return ((InternalEList<?>)getOwnedProtocols()).basicRemove(otherEnd, msgs);
			case CsPackage.PHYSICAL_PORT__OWNED_PORT_REALIZATIONS:
				return ((InternalEList<?>)getOwnedPortRealizations()).basicRemove(otherEnd, msgs);
			case CsPackage.PHYSICAL_PORT__OWNED_PORT_ALLOCATIONS:
				return ((InternalEList<?>)getOwnedPortAllocations()).basicRemove(otherEnd, msgs);
			case CsPackage.PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS:
				return ((InternalEList<?>)getOwnedComponentPortAllocations()).basicRemove(otherEnd, msgs);
			case CsPackage.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS:
				return ((InternalEList<?>)getOwnedPhysicalPortRealizations()).basicRemove(otherEnd, msgs);
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
			case CsPackage.PHYSICAL_PORT__INCOMING_PORT_REALIZATIONS:
				return getIncomingPortRealizations();
			case CsPackage.PHYSICAL_PORT__OUTGOING_PORT_REALIZATIONS:
				return getOutgoingPortRealizations();
			case CsPackage.PHYSICAL_PORT__OWNED_PROTOCOLS:
				return getOwnedProtocols();
			case CsPackage.PHYSICAL_PORT__INCOMING_PORT_ALLOCATIONS:
				return getIncomingPortAllocations();
			case CsPackage.PHYSICAL_PORT__OUTGOING_PORT_ALLOCATIONS:
				return getOutgoingPortAllocations();
			case CsPackage.PHYSICAL_PORT__PROVIDED_INTERFACES:
				return getProvidedInterfaces();
			case CsPackage.PHYSICAL_PORT__REQUIRED_INTERFACES:
				return getRequiredInterfaces();
			case CsPackage.PHYSICAL_PORT__OWNED_PORT_REALIZATIONS:
				return getOwnedPortRealizations();
			case CsPackage.PHYSICAL_PORT__OWNED_PORT_ALLOCATIONS:
				return getOwnedPortAllocations();
			case CsPackage.PHYSICAL_PORT__ALLOCATOR_CONFIGURATION_ITEMS:
				return getAllocatorConfigurationItems();
			case CsPackage.PHYSICAL_PORT__INCOMING_INFORMATION_FLOWS:
				return getIncomingInformationFlows();
			case CsPackage.PHYSICAL_PORT__OUTGOING_INFORMATION_FLOWS:
				return getOutgoingInformationFlows();
			case CsPackage.PHYSICAL_PORT__INFORMATION_FLOWS:
				return getInformationFlows();
			case CsPackage.PHYSICAL_PORT__INVOLVED_LINKS:
				return getInvolvedLinks();
			case CsPackage.PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS:
				return getOwnedComponentPortAllocations();
			case CsPackage.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS:
				return getOwnedPhysicalPortRealizations();
			case CsPackage.PHYSICAL_PORT__ALLOCATED_COMPONENT_PORTS:
				return getAllocatedComponentPorts();
			case CsPackage.PHYSICAL_PORT__REALIZED_PHYSICAL_PORTS:
				return getRealizedPhysicalPorts();
			case CsPackage.PHYSICAL_PORT__REALIZING_PHYSICAL_PORTS:
				return getRealizingPhysicalPorts();
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
			case CsPackage.PHYSICAL_PORT__OWNED_PROTOCOLS:
				getOwnedProtocols().clear();
				getOwnedProtocols().addAll((Collection<? extends StateMachine>)newValue);
				return;
			case CsPackage.PHYSICAL_PORT__PROVIDED_INTERFACES:
				getProvidedInterfaces().clear();
				getProvidedInterfaces().addAll((Collection<? extends Interface>)newValue);
				return;
			case CsPackage.PHYSICAL_PORT__REQUIRED_INTERFACES:
				getRequiredInterfaces().clear();
				getRequiredInterfaces().addAll((Collection<? extends Interface>)newValue);
				return;
			case CsPackage.PHYSICAL_PORT__OWNED_PORT_REALIZATIONS:
				getOwnedPortRealizations().clear();
				getOwnedPortRealizations().addAll((Collection<? extends PortRealization>)newValue);
				return;
			case CsPackage.PHYSICAL_PORT__OWNED_PORT_ALLOCATIONS:
				getOwnedPortAllocations().clear();
				getOwnedPortAllocations().addAll((Collection<? extends PortAllocation>)newValue);
				return;
			case CsPackage.PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS:
				getOwnedComponentPortAllocations().clear();
				getOwnedComponentPortAllocations().addAll((Collection<? extends ComponentPortAllocation>)newValue);
				return;
			case CsPackage.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS:
				getOwnedPhysicalPortRealizations().clear();
				getOwnedPhysicalPortRealizations().addAll((Collection<? extends PhysicalPortRealization>)newValue);
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
			case CsPackage.PHYSICAL_PORT__OWNED_PROTOCOLS:
				getOwnedProtocols().clear();
				return;
			case CsPackage.PHYSICAL_PORT__PROVIDED_INTERFACES:
				getProvidedInterfaces().clear();
				return;
			case CsPackage.PHYSICAL_PORT__REQUIRED_INTERFACES:
				getRequiredInterfaces().clear();
				return;
			case CsPackage.PHYSICAL_PORT__OWNED_PORT_REALIZATIONS:
				getOwnedPortRealizations().clear();
				return;
			case CsPackage.PHYSICAL_PORT__OWNED_PORT_ALLOCATIONS:
				getOwnedPortAllocations().clear();
				return;
			case CsPackage.PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS:
				getOwnedComponentPortAllocations().clear();
				return;
			case CsPackage.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS:
				getOwnedPhysicalPortRealizations().clear();
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
			case CsPackage.PHYSICAL_PORT__INCOMING_PORT_REALIZATIONS:
				return !getIncomingPortRealizations().isEmpty();
			case CsPackage.PHYSICAL_PORT__OUTGOING_PORT_REALIZATIONS:
				return !getOutgoingPortRealizations().isEmpty();
			case CsPackage.PHYSICAL_PORT__OWNED_PROTOCOLS:
				return ownedProtocols != null && !ownedProtocols.isEmpty();
			case CsPackage.PHYSICAL_PORT__INCOMING_PORT_ALLOCATIONS:
				return !getIncomingPortAllocations().isEmpty();
			case CsPackage.PHYSICAL_PORT__OUTGOING_PORT_ALLOCATIONS:
				return !getOutgoingPortAllocations().isEmpty();
			case CsPackage.PHYSICAL_PORT__PROVIDED_INTERFACES:
				return providedInterfaces != null && !providedInterfaces.isEmpty();
			case CsPackage.PHYSICAL_PORT__REQUIRED_INTERFACES:
				return requiredInterfaces != null && !requiredInterfaces.isEmpty();
			case CsPackage.PHYSICAL_PORT__OWNED_PORT_REALIZATIONS:
				return ownedPortRealizations != null && !ownedPortRealizations.isEmpty();
			case CsPackage.PHYSICAL_PORT__OWNED_PORT_ALLOCATIONS:
				return ownedPortAllocations != null && !ownedPortAllocations.isEmpty();
			case CsPackage.PHYSICAL_PORT__ALLOCATOR_CONFIGURATION_ITEMS:
				return !getAllocatorConfigurationItems().isEmpty();
			case CsPackage.PHYSICAL_PORT__INCOMING_INFORMATION_FLOWS:
				return !getIncomingInformationFlows().isEmpty();
			case CsPackage.PHYSICAL_PORT__OUTGOING_INFORMATION_FLOWS:
				return !getOutgoingInformationFlows().isEmpty();
			case CsPackage.PHYSICAL_PORT__INFORMATION_FLOWS:
				return !getInformationFlows().isEmpty();
			case CsPackage.PHYSICAL_PORT__INVOLVED_LINKS:
				return !getInvolvedLinks().isEmpty();
			case CsPackage.PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS:
				return ownedComponentPortAllocations != null && !ownedComponentPortAllocations.isEmpty();
			case CsPackage.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS:
				return ownedPhysicalPortRealizations != null && !ownedPhysicalPortRealizations.isEmpty();
			case CsPackage.PHYSICAL_PORT__ALLOCATED_COMPONENT_PORTS:
				return !getAllocatedComponentPorts().isEmpty();
			case CsPackage.PHYSICAL_PORT__REALIZED_PHYSICAL_PORTS:
				return !getRealizedPhysicalPorts().isEmpty();
			case CsPackage.PHYSICAL_PORT__REALIZING_PHYSICAL_PORTS:
				return !getRealizingPhysicalPorts().isEmpty();
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
		if (baseClass == Port.class) {
			switch (derivedFeatureID) {
				case CsPackage.PHYSICAL_PORT__INCOMING_PORT_REALIZATIONS: return InformationPackage.PORT__INCOMING_PORT_REALIZATIONS;
				case CsPackage.PHYSICAL_PORT__OUTGOING_PORT_REALIZATIONS: return InformationPackage.PORT__OUTGOING_PORT_REALIZATIONS;
				case CsPackage.PHYSICAL_PORT__OWNED_PROTOCOLS: return InformationPackage.PORT__OWNED_PROTOCOLS;
				case CsPackage.PHYSICAL_PORT__INCOMING_PORT_ALLOCATIONS: return InformationPackage.PORT__INCOMING_PORT_ALLOCATIONS;
				case CsPackage.PHYSICAL_PORT__OUTGOING_PORT_ALLOCATIONS: return InformationPackage.PORT__OUTGOING_PORT_ALLOCATIONS;
				case CsPackage.PHYSICAL_PORT__PROVIDED_INTERFACES: return InformationPackage.PORT__PROVIDED_INTERFACES;
				case CsPackage.PHYSICAL_PORT__REQUIRED_INTERFACES: return InformationPackage.PORT__REQUIRED_INTERFACES;
				case CsPackage.PHYSICAL_PORT__OWNED_PORT_REALIZATIONS: return InformationPackage.PORT__OWNED_PORT_REALIZATIONS;
				case CsPackage.PHYSICAL_PORT__OWNED_PORT_ALLOCATIONS: return InformationPackage.PORT__OWNED_PORT_ALLOCATIONS;
				default: return -1;
			}
		}
		if (baseClass == AbstractPhysicalArtifact.class) {
			switch (derivedFeatureID) {
				case CsPackage.PHYSICAL_PORT__ALLOCATOR_CONFIGURATION_ITEMS: return CsPackage.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS;
				default: return -1;
			}
		}
		if (baseClass == InformationsExchanger.class) {
			switch (derivedFeatureID) {
				case CsPackage.PHYSICAL_PORT__INCOMING_INFORMATION_FLOWS: return ModellingcorePackage.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS;
				case CsPackage.PHYSICAL_PORT__OUTGOING_INFORMATION_FLOWS: return ModellingcorePackage.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS;
				case CsPackage.PHYSICAL_PORT__INFORMATION_FLOWS: return ModellingcorePackage.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS;
				default: return -1;
			}
		}
		if (baseClass == AbstractPhysicalLinkEnd.class) {
			switch (derivedFeatureID) {
				case CsPackage.PHYSICAL_PORT__INVOLVED_LINKS: return CsPackage.ABSTRACT_PHYSICAL_LINK_END__INVOLVED_LINKS;
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
		if (baseClass == Port.class) {
			switch (baseFeatureID) {
				case InformationPackage.PORT__INCOMING_PORT_REALIZATIONS: return CsPackage.PHYSICAL_PORT__INCOMING_PORT_REALIZATIONS;
				case InformationPackage.PORT__OUTGOING_PORT_REALIZATIONS: return CsPackage.PHYSICAL_PORT__OUTGOING_PORT_REALIZATIONS;
				case InformationPackage.PORT__OWNED_PROTOCOLS: return CsPackage.PHYSICAL_PORT__OWNED_PROTOCOLS;
				case InformationPackage.PORT__INCOMING_PORT_ALLOCATIONS: return CsPackage.PHYSICAL_PORT__INCOMING_PORT_ALLOCATIONS;
				case InformationPackage.PORT__OUTGOING_PORT_ALLOCATIONS: return CsPackage.PHYSICAL_PORT__OUTGOING_PORT_ALLOCATIONS;
				case InformationPackage.PORT__PROVIDED_INTERFACES: return CsPackage.PHYSICAL_PORT__PROVIDED_INTERFACES;
				case InformationPackage.PORT__REQUIRED_INTERFACES: return CsPackage.PHYSICAL_PORT__REQUIRED_INTERFACES;
				case InformationPackage.PORT__OWNED_PORT_REALIZATIONS: return CsPackage.PHYSICAL_PORT__OWNED_PORT_REALIZATIONS;
				case InformationPackage.PORT__OWNED_PORT_ALLOCATIONS: return CsPackage.PHYSICAL_PORT__OWNED_PORT_ALLOCATIONS;
				default: return -1;
			}
		}
		if (baseClass == AbstractPhysicalArtifact.class) {
			switch (baseFeatureID) {
				case CsPackage.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS: return CsPackage.PHYSICAL_PORT__ALLOCATOR_CONFIGURATION_ITEMS;
				default: return -1;
			}
		}
		if (baseClass == InformationsExchanger.class) {
			switch (baseFeatureID) {
				case ModellingcorePackage.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS: return CsPackage.PHYSICAL_PORT__INCOMING_INFORMATION_FLOWS;
				case ModellingcorePackage.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS: return CsPackage.PHYSICAL_PORT__OUTGOING_INFORMATION_FLOWS;
				case ModellingcorePackage.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS: return CsPackage.PHYSICAL_PORT__INFORMATION_FLOWS;
				default: return -1;
			}
		}
		if (baseClass == AbstractPhysicalLinkEnd.class) {
			switch (baseFeatureID) {
				case CsPackage.ABSTRACT_PHYSICAL_LINK_END__INVOLVED_LINKS: return CsPackage.PHYSICAL_PORT__INVOLVED_LINKS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}


} //PhysicalPortImpl