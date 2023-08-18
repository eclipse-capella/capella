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

package org.polarsys.capella.common.data.activity.impl;

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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.data.activity.AbstractAction;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.ActivityPartition;
import org.polarsys.capella.common.data.activity.ExceptionHandler;
import org.polarsys.capella.common.data.activity.ExecutableNode;
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.common.data.activity.InterruptibleActivityRegion;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.common.data.activity.StructuredActivityNode;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Structured Activity Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.StructuredActivityNodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.StructuredActivityNodeImpl#getInActivityPartition <em>In Activity Partition</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.StructuredActivityNodeImpl#getInInterruptibleRegion <em>In Interruptible Region</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.StructuredActivityNodeImpl#getInStructuredNode <em>In Structured Node</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.StructuredActivityNodeImpl#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.StructuredActivityNodeImpl#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.StructuredActivityNodeImpl#getOwnedHandlers <em>Owned Handlers</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.StructuredActivityNodeImpl#getLocalPrecondition <em>Local Precondition</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.StructuredActivityNodeImpl#getLocalPostcondition <em>Local Postcondition</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.StructuredActivityNodeImpl#getContext <em>Context</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.StructuredActivityNodeImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.StructuredActivityNodeImpl#getOutputs <em>Outputs</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class StructuredActivityNodeImpl extends ActivityGroupImpl implements StructuredActivityNode {

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
   * The cached value of the '{@link #getOwnedHandlers() <em>Owned Handlers</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedHandlers()
   * @generated
   * @ordered
   */
	protected EList<ExceptionHandler> ownedHandlers;





	/**
   * The cached value of the '{@link #getLocalPrecondition() <em>Local Precondition</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getLocalPrecondition()
   * @generated
   * @ordered
   */
	protected AbstractConstraint localPrecondition;





	/**
   * The cached value of the '{@link #getLocalPostcondition() <em>Local Postcondition</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getLocalPostcondition()
   * @generated
   * @ordered
   */
	protected AbstractConstraint localPostcondition;





	/**
   * The cached value of the '{@link #getContext() <em>Context</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getContext()
   * @generated
   * @ordered
   */
	protected AbstractType context;





	/**
   * The cached value of the '{@link #getInputs() <em>Inputs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getInputs()
   * @generated
   * @ordered
   */
	protected EList<InputPin> inputs;





	/**
   * The cached value of the '{@link #getOutputs() <em>Outputs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOutputs()
   * @generated
   * @ordered
   */
	protected EList<OutputPin> outputs;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected StructuredActivityNodeImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ActivityPackage.Literals.STRUCTURED_ACTIVITY_NODE;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.STRUCTURED_ACTIVITY_NODE__NAME, oldName, name));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ActivityPartition getInActivityPartition() {

    ActivityPartition inActivityPartition = basicGetInActivityPartition();
    return inActivityPartition != null && inActivityPartition.eIsProxy() ? (ActivityPartition)eResolveProxy((InternalEObject)inActivityPartition) : inActivityPartition;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ActivityPartition basicGetInActivityPartition() {


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
    EAnnotation annotation = ActivityPackage.Literals.ACTIVITY_NODE__IN_ACTIVITY_PARTITION.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ActivityPackage.Literals.ACTIVITY_NODE__IN_ACTIVITY_PARTITION, annotation);
    
    try {
      return (ActivityPartition) result;
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

	public InterruptibleActivityRegion getInInterruptibleRegion() {

    InterruptibleActivityRegion inInterruptibleRegion = basicGetInInterruptibleRegion();
    return inInterruptibleRegion != null && inInterruptibleRegion.eIsProxy() ? (InterruptibleActivityRegion)eResolveProxy((InternalEObject)inInterruptibleRegion) : inInterruptibleRegion;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InterruptibleActivityRegion basicGetInInterruptibleRegion() {


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
    EAnnotation annotation = ActivityPackage.Literals.ACTIVITY_NODE__IN_INTERRUPTIBLE_REGION.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ActivityPackage.Literals.ACTIVITY_NODE__IN_INTERRUPTIBLE_REGION, annotation);
    
    try {
      return (InterruptibleActivityRegion) result;
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

	public InterruptibleActivityRegion getInStructuredNode() {

    InterruptibleActivityRegion inStructuredNode = basicGetInStructuredNode();
    return inStructuredNode != null && inStructuredNode.eIsProxy() ? (InterruptibleActivityRegion)eResolveProxy((InternalEObject)inStructuredNode) : inStructuredNode;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InterruptibleActivityRegion basicGetInStructuredNode() {


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
    EAnnotation annotation = ActivityPackage.Literals.ACTIVITY_NODE__IN_STRUCTURED_NODE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ActivityPackage.Literals.ACTIVITY_NODE__IN_STRUCTURED_NODE, annotation);
    
    try {
      return (InterruptibleActivityRegion) result;
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

	public EList<ActivityEdge> getOutgoing() {


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
    EAnnotation annotation = ActivityPackage.Literals.ACTIVITY_NODE__OUTGOING.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ActivityPackage.Literals.ACTIVITY_NODE__OUTGOING, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ActivityEdge> resultAsList = (Collection<ActivityEdge>) result;
    return new EcoreEList.UnmodifiableEList<ActivityEdge>(this, ActivityPackage.Literals.ACTIVITY_NODE__OUTGOING, resultAsList.size(), resultAsList.toArray());
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

	public EList<ActivityEdge> getIncoming() {


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
    EAnnotation annotation = ActivityPackage.Literals.ACTIVITY_NODE__INCOMING.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ActivityPackage.Literals.ACTIVITY_NODE__INCOMING, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ActivityEdge> resultAsList = (Collection<ActivityEdge>) result;
    return new EcoreEList.UnmodifiableEList<ActivityEdge>(this, ActivityPackage.Literals.ACTIVITY_NODE__INCOMING, resultAsList.size(), resultAsList.toArray());
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

	public EList<ExceptionHandler> getOwnedHandlers() {

    if (ownedHandlers == null) {
      ownedHandlers = new EObjectContainmentWithInverseEList.Resolving<ExceptionHandler>(ExceptionHandler.class, this, ActivityPackage.STRUCTURED_ACTIVITY_NODE__OWNED_HANDLERS, ActivityPackage.EXCEPTION_HANDLER__PROTECTED_NODE);
    }
    return ownedHandlers;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractConstraint getLocalPrecondition() {

    if (localPrecondition != null && localPrecondition.eIsProxy()) {
      InternalEObject oldLocalPrecondition = (InternalEObject)localPrecondition;
      localPrecondition = (AbstractConstraint)eResolveProxy(oldLocalPrecondition);
      if (localPrecondition != oldLocalPrecondition) {
        InternalEObject newLocalPrecondition = (InternalEObject)localPrecondition;
        NotificationChain msgs = oldLocalPrecondition.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_PRECONDITION, null, null);
        if (newLocalPrecondition.eInternalContainer() == null) {
          msgs = newLocalPrecondition.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_PRECONDITION, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_PRECONDITION, oldLocalPrecondition, localPrecondition));
      }
    }
    return localPrecondition;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractConstraint basicGetLocalPrecondition() {

    return localPrecondition;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetLocalPrecondition(AbstractConstraint newLocalPrecondition, NotificationChain msgs) {

    AbstractConstraint oldLocalPrecondition = localPrecondition;
    localPrecondition = newLocalPrecondition;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_PRECONDITION, oldLocalPrecondition, newLocalPrecondition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setLocalPrecondition(AbstractConstraint newLocalPrecondition) {

    if (newLocalPrecondition != localPrecondition) {
      NotificationChain msgs = null;
      if (localPrecondition != null)
        msgs = ((InternalEObject)localPrecondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_PRECONDITION, null, msgs);
      if (newLocalPrecondition != null)
        msgs = ((InternalEObject)newLocalPrecondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_PRECONDITION, null, msgs);
      msgs = basicSetLocalPrecondition(newLocalPrecondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_PRECONDITION, newLocalPrecondition, newLocalPrecondition));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractConstraint getLocalPostcondition() {

    if (localPostcondition != null && localPostcondition.eIsProxy()) {
      InternalEObject oldLocalPostcondition = (InternalEObject)localPostcondition;
      localPostcondition = (AbstractConstraint)eResolveProxy(oldLocalPostcondition);
      if (localPostcondition != oldLocalPostcondition) {
        InternalEObject newLocalPostcondition = (InternalEObject)localPostcondition;
        NotificationChain msgs = oldLocalPostcondition.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_POSTCONDITION, null, null);
        if (newLocalPostcondition.eInternalContainer() == null) {
          msgs = newLocalPostcondition.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_POSTCONDITION, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_POSTCONDITION, oldLocalPostcondition, localPostcondition));
      }
    }
    return localPostcondition;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractConstraint basicGetLocalPostcondition() {

    return localPostcondition;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetLocalPostcondition(AbstractConstraint newLocalPostcondition, NotificationChain msgs) {

    AbstractConstraint oldLocalPostcondition = localPostcondition;
    localPostcondition = newLocalPostcondition;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_POSTCONDITION, oldLocalPostcondition, newLocalPostcondition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setLocalPostcondition(AbstractConstraint newLocalPostcondition) {

    if (newLocalPostcondition != localPostcondition) {
      NotificationChain msgs = null;
      if (localPostcondition != null)
        msgs = ((InternalEObject)localPostcondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_POSTCONDITION, null, msgs);
      if (newLocalPostcondition != null)
        msgs = ((InternalEObject)newLocalPostcondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_POSTCONDITION, null, msgs);
      msgs = basicSetLocalPostcondition(newLocalPostcondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_POSTCONDITION, newLocalPostcondition, newLocalPostcondition));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractType getContext() {

    if (context != null && context.eIsProxy()) {
      InternalEObject oldContext = (InternalEObject)context;
      context = (AbstractType)eResolveProxy(oldContext);
      if (context != oldContext) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.STRUCTURED_ACTIVITY_NODE__CONTEXT, oldContext, context));
      }
    }
    return context;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractType basicGetContext() {

    return context;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setContext(AbstractType newContext) {

    AbstractType oldContext = context;
    context = newContext;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.STRUCTURED_ACTIVITY_NODE__CONTEXT, oldContext, context));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<InputPin> getInputs() {

    if (inputs == null) {
      inputs = new EObjectContainmentEList.Resolving<InputPin>(InputPin.class, this, ActivityPackage.STRUCTURED_ACTIVITY_NODE__INPUTS);
    }
    return inputs;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<OutputPin> getOutputs() {

    if (outputs == null) {
      outputs = new EObjectContainmentEList.Resolving<OutputPin>(OutputPin.class, this, ActivityPackage.STRUCTURED_ACTIVITY_NODE__OUTPUTS);
    }
    return outputs;
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
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OWNED_HANDLERS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedHandlers()).basicAdd(otherEnd, msgs);
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
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OWNED_HANDLERS:
        return ((InternalEList<?>)getOwnedHandlers()).basicRemove(otherEnd, msgs);
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_PRECONDITION:
        return basicSetLocalPrecondition(null, msgs);
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_POSTCONDITION:
        return basicSetLocalPostcondition(null, msgs);
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__INPUTS:
        return ((InternalEList<?>)getInputs()).basicRemove(otherEnd, msgs);
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OUTPUTS:
        return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
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
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__NAME:
        return getName();
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__IN_ACTIVITY_PARTITION:
        if (resolve) return getInActivityPartition();
        return basicGetInActivityPartition();
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__IN_INTERRUPTIBLE_REGION:
        if (resolve) return getInInterruptibleRegion();
        return basicGetInInterruptibleRegion();
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__IN_STRUCTURED_NODE:
        if (resolve) return getInStructuredNode();
        return basicGetInStructuredNode();
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OUTGOING:
        return getOutgoing();
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__INCOMING:
        return getIncoming();
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OWNED_HANDLERS:
        return getOwnedHandlers();
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_PRECONDITION:
        if (resolve) return getLocalPrecondition();
        return basicGetLocalPrecondition();
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_POSTCONDITION:
        if (resolve) return getLocalPostcondition();
        return basicGetLocalPostcondition();
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__CONTEXT:
        if (resolve) return getContext();
        return basicGetContext();
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__INPUTS:
        return getInputs();
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OUTPUTS:
        return getOutputs();
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
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__NAME:
          setName((String)newValue);
        return;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OWNED_HANDLERS:
        getOwnedHandlers().clear();
        getOwnedHandlers().addAll((Collection<? extends ExceptionHandler>)newValue);
        return;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_PRECONDITION:
          setLocalPrecondition((AbstractConstraint)newValue);
        return;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_POSTCONDITION:
          setLocalPostcondition((AbstractConstraint)newValue);
        return;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__CONTEXT:
          setContext((AbstractType)newValue);
        return;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__INPUTS:
        getInputs().clear();
        getInputs().addAll((Collection<? extends InputPin>)newValue);
        return;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OUTPUTS:
        getOutputs().clear();
        getOutputs().addAll((Collection<? extends OutputPin>)newValue);
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
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OWNED_HANDLERS:
        getOwnedHandlers().clear();
        return;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_PRECONDITION:
        setLocalPrecondition((AbstractConstraint)null);
        return;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_POSTCONDITION:
        setLocalPostcondition((AbstractConstraint)null);
        return;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__CONTEXT:
        setContext((AbstractType)null);
        return;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__INPUTS:
        getInputs().clear();
        return;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OUTPUTS:
        getOutputs().clear();
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
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__IN_ACTIVITY_PARTITION:
        return basicGetInActivityPartition() != null;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__IN_INTERRUPTIBLE_REGION:
        return basicGetInInterruptibleRegion() != null;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__IN_STRUCTURED_NODE:
        return basicGetInStructuredNode() != null;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OUTGOING:
        return !getOutgoing().isEmpty();
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__INCOMING:
        return !getIncoming().isEmpty();
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OWNED_HANDLERS:
        return ownedHandlers != null && !ownedHandlers.isEmpty();
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_PRECONDITION:
        return localPrecondition != null;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_POSTCONDITION:
        return localPostcondition != null;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__CONTEXT:
        return context != null;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__INPUTS:
        return inputs != null && !inputs.isEmpty();
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OUTPUTS:
        return outputs != null && !outputs.isEmpty();
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
        case ActivityPackage.STRUCTURED_ACTIVITY_NODE__NAME: return ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__NAME;
        default: return -1;
      }
    }
    if (baseClass == ActivityNode.class) {
      switch (derivedFeatureID) {
        case ActivityPackage.STRUCTURED_ACTIVITY_NODE__IN_ACTIVITY_PARTITION: return ActivityPackage.ACTIVITY_NODE__IN_ACTIVITY_PARTITION;
        case ActivityPackage.STRUCTURED_ACTIVITY_NODE__IN_INTERRUPTIBLE_REGION: return ActivityPackage.ACTIVITY_NODE__IN_INTERRUPTIBLE_REGION;
        case ActivityPackage.STRUCTURED_ACTIVITY_NODE__IN_STRUCTURED_NODE: return ActivityPackage.ACTIVITY_NODE__IN_STRUCTURED_NODE;
        case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OUTGOING: return ActivityPackage.ACTIVITY_NODE__OUTGOING;
        case ActivityPackage.STRUCTURED_ACTIVITY_NODE__INCOMING: return ActivityPackage.ACTIVITY_NODE__INCOMING;
        default: return -1;
      }
    }
    if (baseClass == ExecutableNode.class) {
      switch (derivedFeatureID) {
        case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OWNED_HANDLERS: return ActivityPackage.EXECUTABLE_NODE__OWNED_HANDLERS;
        default: return -1;
      }
    }
    if (baseClass == AbstractAction.class) {
      switch (derivedFeatureID) {
        case ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_PRECONDITION: return ActivityPackage.ABSTRACT_ACTION__LOCAL_PRECONDITION;
        case ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_POSTCONDITION: return ActivityPackage.ABSTRACT_ACTION__LOCAL_POSTCONDITION;
        case ActivityPackage.STRUCTURED_ACTIVITY_NODE__CONTEXT: return ActivityPackage.ABSTRACT_ACTION__CONTEXT;
        case ActivityPackage.STRUCTURED_ACTIVITY_NODE__INPUTS: return ActivityPackage.ABSTRACT_ACTION__INPUTS;
        case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OUTPUTS: return ActivityPackage.ABSTRACT_ACTION__OUTPUTS;
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
        case ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__NAME: return ActivityPackage.STRUCTURED_ACTIVITY_NODE__NAME;
        default: return -1;
      }
    }
    if (baseClass == ActivityNode.class) {
      switch (baseFeatureID) {
        case ActivityPackage.ACTIVITY_NODE__IN_ACTIVITY_PARTITION: return ActivityPackage.STRUCTURED_ACTIVITY_NODE__IN_ACTIVITY_PARTITION;
        case ActivityPackage.ACTIVITY_NODE__IN_INTERRUPTIBLE_REGION: return ActivityPackage.STRUCTURED_ACTIVITY_NODE__IN_INTERRUPTIBLE_REGION;
        case ActivityPackage.ACTIVITY_NODE__IN_STRUCTURED_NODE: return ActivityPackage.STRUCTURED_ACTIVITY_NODE__IN_STRUCTURED_NODE;
        case ActivityPackage.ACTIVITY_NODE__OUTGOING: return ActivityPackage.STRUCTURED_ACTIVITY_NODE__OUTGOING;
        case ActivityPackage.ACTIVITY_NODE__INCOMING: return ActivityPackage.STRUCTURED_ACTIVITY_NODE__INCOMING;
        default: return -1;
      }
    }
    if (baseClass == ExecutableNode.class) {
      switch (baseFeatureID) {
        case ActivityPackage.EXECUTABLE_NODE__OWNED_HANDLERS: return ActivityPackage.STRUCTURED_ACTIVITY_NODE__OWNED_HANDLERS;
        default: return -1;
      }
    }
    if (baseClass == AbstractAction.class) {
      switch (baseFeatureID) {
        case ActivityPackage.ABSTRACT_ACTION__LOCAL_PRECONDITION: return ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_PRECONDITION;
        case ActivityPackage.ABSTRACT_ACTION__LOCAL_POSTCONDITION: return ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_POSTCONDITION;
        case ActivityPackage.ABSTRACT_ACTION__CONTEXT: return ActivityPackage.STRUCTURED_ACTIVITY_NODE__CONTEXT;
        case ActivityPackage.ABSTRACT_ACTION__INPUTS: return ActivityPackage.STRUCTURED_ACTIVITY_NODE__INPUTS;
        case ActivityPackage.ABSTRACT_ACTION__OUTPUTS: return ActivityPackage.STRUCTURED_ACTIVITY_NODE__OUTPUTS;
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


} //StructuredActivityNodeImpl
