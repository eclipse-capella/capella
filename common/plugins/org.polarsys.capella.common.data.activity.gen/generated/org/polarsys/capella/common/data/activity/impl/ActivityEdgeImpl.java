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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.ActivityPartition;
import org.polarsys.capella.common.data.activity.InterruptibleActivityRegion;
import org.polarsys.capella.common.data.activity.StructuredActivityNode;
import org.polarsys.capella.common.data.modellingcore.RateKind;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;
import org.polarsys.capella.common.data.modellingcore.impl.AbstractRelationshipImpl;
import org.polarsys.capella.common.model.helpers.IHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ActivityEdgeImpl#getKindOfRate <em>Kind Of Rate</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ActivityEdgeImpl#getInActivityPartition <em>In Activity Partition</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ActivityEdgeImpl#getInInterruptibleRegion <em>In Interruptible Region</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ActivityEdgeImpl#getInStructuredNode <em>In Structured Node</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ActivityEdgeImpl#getRate <em>Rate</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ActivityEdgeImpl#getProbability <em>Probability</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ActivityEdgeImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ActivityEdgeImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ActivityEdgeImpl#getGuard <em>Guard</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ActivityEdgeImpl#getWeight <em>Weight</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ActivityEdgeImpl#getInterrupts <em>Interrupts</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ActivityEdgeImpl extends AbstractRelationshipImpl implements ActivityEdge {

	/**
   * The default value of the '{@link #getKindOfRate() <em>Kind Of Rate</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKindOfRate()
   * @generated
   * @ordered
   */
	protected static final RateKind KIND_OF_RATE_EDEFAULT = RateKind.UNSPECIFIED;

	/**
   * The cached value of the '{@link #getKindOfRate() <em>Kind Of Rate</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKindOfRate()
   * @generated
   * @ordered
   */
	protected RateKind kindOfRate = KIND_OF_RATE_EDEFAULT;

















	/**
   * The cached value of the '{@link #getRate() <em>Rate</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getRate()
   * @generated
   * @ordered
   */
	protected ValueSpecification rate;





	/**
   * The cached value of the '{@link #getProbability() <em>Probability</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getProbability()
   * @generated
   * @ordered
   */
	protected ValueSpecification probability;





	/**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
	protected ActivityNode target;





	/**
   * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
	protected ActivityNode source;





	/**
   * The cached value of the '{@link #getGuard() <em>Guard</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getGuard()
   * @generated
   * @ordered
   */
	protected ValueSpecification guard;





	/**
   * The cached value of the '{@link #getWeight() <em>Weight</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getWeight()
   * @generated
   * @ordered
   */
	protected ValueSpecification weight;





	/**
   * The cached value of the '{@link #getInterrupts() <em>Interrupts</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getInterrupts()
   * @generated
   * @ordered
   */
	protected InterruptibleActivityRegion interrupts;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ActivityEdgeImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ActivityPackage.Literals.ACTIVITY_EDGE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public RateKind getKindOfRate() {

    return kindOfRate;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setKindOfRate(RateKind newKindOfRate) {

    RateKind oldKindOfRate = kindOfRate;
    kindOfRate = newKindOfRate == null ? KIND_OF_RATE_EDEFAULT : newKindOfRate;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.ACTIVITY_EDGE__KIND_OF_RATE, oldKindOfRate, kindOfRate));

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
    EAnnotation annotation = ActivityPackage.Literals.ACTIVITY_EDGE__IN_ACTIVITY_PARTITION.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ActivityPackage.Literals.ACTIVITY_EDGE__IN_ACTIVITY_PARTITION, annotation);
    
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
    EAnnotation annotation = ActivityPackage.Literals.ACTIVITY_EDGE__IN_INTERRUPTIBLE_REGION.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ActivityPackage.Literals.ACTIVITY_EDGE__IN_INTERRUPTIBLE_REGION, annotation);
    
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

	public StructuredActivityNode getInStructuredNode() {

    StructuredActivityNode inStructuredNode = basicGetInStructuredNode();
    return inStructuredNode != null && inStructuredNode.eIsProxy() ? (StructuredActivityNode)eResolveProxy((InternalEObject)inStructuredNode) : inStructuredNode;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public StructuredActivityNode basicGetInStructuredNode() {


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
    EAnnotation annotation = ActivityPackage.Literals.ACTIVITY_EDGE__IN_STRUCTURED_NODE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ActivityPackage.Literals.ACTIVITY_EDGE__IN_STRUCTURED_NODE, annotation);
    
    try {
      return (StructuredActivityNode) result;
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

	public ValueSpecification getRate() {

    if (rate != null && rate.eIsProxy()) {
      InternalEObject oldRate = (InternalEObject)rate;
      rate = (ValueSpecification)eResolveProxy(oldRate);
      if (rate != oldRate) {
        InternalEObject newRate = (InternalEObject)rate;
        NotificationChain msgs = oldRate.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ACTIVITY_EDGE__RATE, null, null);
        if (newRate.eInternalContainer() == null) {
          msgs = newRate.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ACTIVITY_EDGE__RATE, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.ACTIVITY_EDGE__RATE, oldRate, rate));
      }
    }
    return rate;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ValueSpecification basicGetRate() {

    return rate;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetRate(ValueSpecification newRate, NotificationChain msgs) {

    ValueSpecification oldRate = rate;
    rate = newRate;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActivityPackage.ACTIVITY_EDGE__RATE, oldRate, newRate);
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
	public void setRate(ValueSpecification newRate) {

    if (newRate != rate) {
      NotificationChain msgs = null;
      if (rate != null)
        msgs = ((InternalEObject)rate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ACTIVITY_EDGE__RATE, null, msgs);
      if (newRate != null)
        msgs = ((InternalEObject)newRate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ACTIVITY_EDGE__RATE, null, msgs);
      msgs = basicSetRate(newRate, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.ACTIVITY_EDGE__RATE, newRate, newRate));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ValueSpecification getProbability() {

    if (probability != null && probability.eIsProxy()) {
      InternalEObject oldProbability = (InternalEObject)probability;
      probability = (ValueSpecification)eResolveProxy(oldProbability);
      if (probability != oldProbability) {
        InternalEObject newProbability = (InternalEObject)probability;
        NotificationChain msgs = oldProbability.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ACTIVITY_EDGE__PROBABILITY, null, null);
        if (newProbability.eInternalContainer() == null) {
          msgs = newProbability.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ACTIVITY_EDGE__PROBABILITY, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.ACTIVITY_EDGE__PROBABILITY, oldProbability, probability));
      }
    }
    return probability;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ValueSpecification basicGetProbability() {

    return probability;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetProbability(ValueSpecification newProbability, NotificationChain msgs) {

    ValueSpecification oldProbability = probability;
    probability = newProbability;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActivityPackage.ACTIVITY_EDGE__PROBABILITY, oldProbability, newProbability);
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
	public void setProbability(ValueSpecification newProbability) {

    if (newProbability != probability) {
      NotificationChain msgs = null;
      if (probability != null)
        msgs = ((InternalEObject)probability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ACTIVITY_EDGE__PROBABILITY, null, msgs);
      if (newProbability != null)
        msgs = ((InternalEObject)newProbability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ACTIVITY_EDGE__PROBABILITY, null, msgs);
      msgs = basicSetProbability(newProbability, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.ACTIVITY_EDGE__PROBABILITY, newProbability, newProbability));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ActivityNode getTarget() {

    if (target != null && target.eIsProxy()) {
      InternalEObject oldTarget = (InternalEObject)target;
      target = (ActivityNode)eResolveProxy(oldTarget);
      if (target != oldTarget) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.ACTIVITY_EDGE__TARGET, oldTarget, target));
      }
    }
    return target;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ActivityNode basicGetTarget() {

    return target;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setTarget(ActivityNode newTarget) {

    ActivityNode oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.ACTIVITY_EDGE__TARGET, oldTarget, target));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ActivityNode getSource() {

    if (source != null && source.eIsProxy()) {
      InternalEObject oldSource = (InternalEObject)source;
      source = (ActivityNode)eResolveProxy(oldSource);
      if (source != oldSource) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.ACTIVITY_EDGE__SOURCE, oldSource, source));
      }
    }
    return source;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ActivityNode basicGetSource() {

    return source;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setSource(ActivityNode newSource) {

    ActivityNode oldSource = source;
    source = newSource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.ACTIVITY_EDGE__SOURCE, oldSource, source));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ValueSpecification getGuard() {

    if (guard != null && guard.eIsProxy()) {
      InternalEObject oldGuard = (InternalEObject)guard;
      guard = (ValueSpecification)eResolveProxy(oldGuard);
      if (guard != oldGuard) {
        InternalEObject newGuard = (InternalEObject)guard;
        NotificationChain msgs = oldGuard.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ACTIVITY_EDGE__GUARD, null, null);
        if (newGuard.eInternalContainer() == null) {
          msgs = newGuard.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ACTIVITY_EDGE__GUARD, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.ACTIVITY_EDGE__GUARD, oldGuard, guard));
      }
    }
    return guard;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ValueSpecification basicGetGuard() {

    return guard;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetGuard(ValueSpecification newGuard, NotificationChain msgs) {

    ValueSpecification oldGuard = guard;
    guard = newGuard;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActivityPackage.ACTIVITY_EDGE__GUARD, oldGuard, newGuard);
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
	public void setGuard(ValueSpecification newGuard) {

    if (newGuard != guard) {
      NotificationChain msgs = null;
      if (guard != null)
        msgs = ((InternalEObject)guard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ACTIVITY_EDGE__GUARD, null, msgs);
      if (newGuard != null)
        msgs = ((InternalEObject)newGuard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ACTIVITY_EDGE__GUARD, null, msgs);
      msgs = basicSetGuard(newGuard, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.ACTIVITY_EDGE__GUARD, newGuard, newGuard));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ValueSpecification getWeight() {

    if (weight != null && weight.eIsProxy()) {
      InternalEObject oldWeight = (InternalEObject)weight;
      weight = (ValueSpecification)eResolveProxy(oldWeight);
      if (weight != oldWeight) {
        InternalEObject newWeight = (InternalEObject)weight;
        NotificationChain msgs = oldWeight.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ACTIVITY_EDGE__WEIGHT, null, null);
        if (newWeight.eInternalContainer() == null) {
          msgs = newWeight.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ACTIVITY_EDGE__WEIGHT, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.ACTIVITY_EDGE__WEIGHT, oldWeight, weight));
      }
    }
    return weight;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ValueSpecification basicGetWeight() {

    return weight;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetWeight(ValueSpecification newWeight, NotificationChain msgs) {

    ValueSpecification oldWeight = weight;
    weight = newWeight;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActivityPackage.ACTIVITY_EDGE__WEIGHT, oldWeight, newWeight);
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
	public void setWeight(ValueSpecification newWeight) {

    if (newWeight != weight) {
      NotificationChain msgs = null;
      if (weight != null)
        msgs = ((InternalEObject)weight).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ACTIVITY_EDGE__WEIGHT, null, msgs);
      if (newWeight != null)
        msgs = ((InternalEObject)newWeight).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ACTIVITY_EDGE__WEIGHT, null, msgs);
      msgs = basicSetWeight(newWeight, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.ACTIVITY_EDGE__WEIGHT, newWeight, newWeight));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InterruptibleActivityRegion getInterrupts() {

    if (interrupts != null && interrupts.eIsProxy()) {
      InternalEObject oldInterrupts = (InternalEObject)interrupts;
      interrupts = (InterruptibleActivityRegion)eResolveProxy(oldInterrupts);
      if (interrupts != oldInterrupts) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.ACTIVITY_EDGE__INTERRUPTS, oldInterrupts, interrupts));
      }
    }
    return interrupts;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InterruptibleActivityRegion basicGetInterrupts() {

    return interrupts;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetInterrupts(InterruptibleActivityRegion newInterrupts, NotificationChain msgs) {

    InterruptibleActivityRegion oldInterrupts = interrupts;
    interrupts = newInterrupts;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActivityPackage.ACTIVITY_EDGE__INTERRUPTS, oldInterrupts, newInterrupts);
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
	public void setInterrupts(InterruptibleActivityRegion newInterrupts) {

    if (newInterrupts != interrupts) {
      NotificationChain msgs = null;
      if (interrupts != null)
        msgs = ((InternalEObject)interrupts).eInverseRemove(this, ActivityPackage.INTERRUPTIBLE_ACTIVITY_REGION__INTERRUPTING_EDGES, InterruptibleActivityRegion.class, msgs);
      if (newInterrupts != null)
        msgs = ((InternalEObject)newInterrupts).eInverseAdd(this, ActivityPackage.INTERRUPTIBLE_ACTIVITY_REGION__INTERRUPTING_EDGES, InterruptibleActivityRegion.class, msgs);
      msgs = basicSetInterrupts(newInterrupts, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.ACTIVITY_EDGE__INTERRUPTS, newInterrupts, newInterrupts));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case ActivityPackage.ACTIVITY_EDGE__INTERRUPTS:
        if (interrupts != null)
          msgs = ((InternalEObject)interrupts).eInverseRemove(this, ActivityPackage.INTERRUPTIBLE_ACTIVITY_REGION__INTERRUPTING_EDGES, InterruptibleActivityRegion.class, msgs);
        return basicSetInterrupts((InterruptibleActivityRegion)otherEnd, msgs);
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
      case ActivityPackage.ACTIVITY_EDGE__RATE:
        return basicSetRate(null, msgs);
      case ActivityPackage.ACTIVITY_EDGE__PROBABILITY:
        return basicSetProbability(null, msgs);
      case ActivityPackage.ACTIVITY_EDGE__GUARD:
        return basicSetGuard(null, msgs);
      case ActivityPackage.ACTIVITY_EDGE__WEIGHT:
        return basicSetWeight(null, msgs);
      case ActivityPackage.ACTIVITY_EDGE__INTERRUPTS:
        return basicSetInterrupts(null, msgs);
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
      case ActivityPackage.ACTIVITY_EDGE__KIND_OF_RATE:
        return getKindOfRate();
      case ActivityPackage.ACTIVITY_EDGE__IN_ACTIVITY_PARTITION:
        if (resolve) return getInActivityPartition();
        return basicGetInActivityPartition();
      case ActivityPackage.ACTIVITY_EDGE__IN_INTERRUPTIBLE_REGION:
        if (resolve) return getInInterruptibleRegion();
        return basicGetInInterruptibleRegion();
      case ActivityPackage.ACTIVITY_EDGE__IN_STRUCTURED_NODE:
        if (resolve) return getInStructuredNode();
        return basicGetInStructuredNode();
      case ActivityPackage.ACTIVITY_EDGE__RATE:
        if (resolve) return getRate();
        return basicGetRate();
      case ActivityPackage.ACTIVITY_EDGE__PROBABILITY:
        if (resolve) return getProbability();
        return basicGetProbability();
      case ActivityPackage.ACTIVITY_EDGE__TARGET:
        if (resolve) return getTarget();
        return basicGetTarget();
      case ActivityPackage.ACTIVITY_EDGE__SOURCE:
        if (resolve) return getSource();
        return basicGetSource();
      case ActivityPackage.ACTIVITY_EDGE__GUARD:
        if (resolve) return getGuard();
        return basicGetGuard();
      case ActivityPackage.ACTIVITY_EDGE__WEIGHT:
        if (resolve) return getWeight();
        return basicGetWeight();
      case ActivityPackage.ACTIVITY_EDGE__INTERRUPTS:
        if (resolve) return getInterrupts();
        return basicGetInterrupts();
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
      case ActivityPackage.ACTIVITY_EDGE__KIND_OF_RATE:
          setKindOfRate((RateKind)newValue);
        return;
      case ActivityPackage.ACTIVITY_EDGE__RATE:
          setRate((ValueSpecification)newValue);
        return;
      case ActivityPackage.ACTIVITY_EDGE__PROBABILITY:
          setProbability((ValueSpecification)newValue);
        return;
      case ActivityPackage.ACTIVITY_EDGE__TARGET:
          setTarget((ActivityNode)newValue);
        return;
      case ActivityPackage.ACTIVITY_EDGE__SOURCE:
          setSource((ActivityNode)newValue);
        return;
      case ActivityPackage.ACTIVITY_EDGE__GUARD:
          setGuard((ValueSpecification)newValue);
        return;
      case ActivityPackage.ACTIVITY_EDGE__WEIGHT:
          setWeight((ValueSpecification)newValue);
        return;
      case ActivityPackage.ACTIVITY_EDGE__INTERRUPTS:
          setInterrupts((InterruptibleActivityRegion)newValue);
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
      case ActivityPackage.ACTIVITY_EDGE__KIND_OF_RATE:
        setKindOfRate(KIND_OF_RATE_EDEFAULT);
        return;
      case ActivityPackage.ACTIVITY_EDGE__RATE:
        setRate((ValueSpecification)null);
        return;
      case ActivityPackage.ACTIVITY_EDGE__PROBABILITY:
        setProbability((ValueSpecification)null);
        return;
      case ActivityPackage.ACTIVITY_EDGE__TARGET:
        setTarget((ActivityNode)null);
        return;
      case ActivityPackage.ACTIVITY_EDGE__SOURCE:
        setSource((ActivityNode)null);
        return;
      case ActivityPackage.ACTIVITY_EDGE__GUARD:
        setGuard((ValueSpecification)null);
        return;
      case ActivityPackage.ACTIVITY_EDGE__WEIGHT:
        setWeight((ValueSpecification)null);
        return;
      case ActivityPackage.ACTIVITY_EDGE__INTERRUPTS:
        setInterrupts((InterruptibleActivityRegion)null);
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
      case ActivityPackage.ACTIVITY_EDGE__KIND_OF_RATE:
        return kindOfRate != KIND_OF_RATE_EDEFAULT;
      case ActivityPackage.ACTIVITY_EDGE__IN_ACTIVITY_PARTITION:
        return basicGetInActivityPartition() != null;
      case ActivityPackage.ACTIVITY_EDGE__IN_INTERRUPTIBLE_REGION:
        return basicGetInInterruptibleRegion() != null;
      case ActivityPackage.ACTIVITY_EDGE__IN_STRUCTURED_NODE:
        return basicGetInStructuredNode() != null;
      case ActivityPackage.ACTIVITY_EDGE__RATE:
        return rate != null;
      case ActivityPackage.ACTIVITY_EDGE__PROBABILITY:
        return probability != null;
      case ActivityPackage.ACTIVITY_EDGE__TARGET:
        return target != null;
      case ActivityPackage.ACTIVITY_EDGE__SOURCE:
        return source != null;
      case ActivityPackage.ACTIVITY_EDGE__GUARD:
        return guard != null;
      case ActivityPackage.ACTIVITY_EDGE__WEIGHT:
        return weight != null;
      case ActivityPackage.ACTIVITY_EDGE__INTERRUPTS:
        return interrupts != null;
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

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (kindOfRate: "); //$NON-NLS-1$
    result.append(kindOfRate);
    result.append(')');
    return result.toString();
  }


} //ActivityEdgeImpl