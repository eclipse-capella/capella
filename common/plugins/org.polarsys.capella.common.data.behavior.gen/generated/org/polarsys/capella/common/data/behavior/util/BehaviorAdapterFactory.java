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
package org.polarsys.capella.common.data.behavior.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.behavior.*;
import org.polarsys.capella.common.data.behavior.AbstractBehavior;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.behavior.AbstractMessageEvent;
import org.polarsys.capella.common.data.behavior.AbstractSignal;
import org.polarsys.capella.common.data.behavior.AbstractSignalEvent;
import org.polarsys.capella.common.data.behavior.AbstractTimeEvent;
import org.polarsys.capella.common.data.behavior.BehaviorPackage;
import org.polarsys.capella.common.data.behavior.TimeExpression;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;
import org.polarsys.kitalpha.emde.model.Element;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.common.data.behavior.BehaviorPackage
 * @generated
 */
public class BehaviorAdapterFactory extends AdapterFactoryImpl {
	/**
   * The cached model package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static BehaviorPackage modelPackage;

	/**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public BehaviorAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = BehaviorPackage.eINSTANCE;
    }
  }

	/**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
	@Override
	public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

	/**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected BehaviorSwitch<Adapter> modelSwitch =
		new BehaviorSwitch<Adapter>() {
      @Override
      public Adapter caseAbstractBehavior(AbstractBehavior object) {
        return createAbstractBehaviorAdapter();
      }
      @Override
      public Adapter caseAbstractSignal(AbstractSignal object) {
        return createAbstractSignalAdapter();
      }
      @Override
      public Adapter caseAbstractEvent(AbstractEvent object) {
        return createAbstractEventAdapter();
      }
      @Override
      public Adapter caseAbstractTimeEvent(AbstractTimeEvent object) {
        return createAbstractTimeEventAdapter();
      }
      @Override
      public Adapter caseAbstractMessageEvent(AbstractMessageEvent object) {
        return createAbstractMessageEventAdapter();
      }
      @Override
      public Adapter caseAbstractSignalEvent(AbstractSignalEvent object) {
        return createAbstractSignalEventAdapter();
      }
      @Override
      public Adapter caseTimeExpression(TimeExpression object) {
        return createTimeExpressionAdapter();
      }
      @Override
      public Adapter caseElement(Element object) {
        return createElementAdapter();
      }
      @Override
      public Adapter caseExtensibleElement(ExtensibleElement object) {
        return createExtensibleElementAdapter();
      }
      @Override
      public Adapter caseModelElement(ModelElement object) {
        return createModelElementAdapter();
      }
      @Override
      public Adapter caseAbstractNamedElement(AbstractNamedElement object) {
        return createAbstractNamedElementAdapter();
      }
      @Override
      public Adapter caseAbstractType(AbstractType object) {
        return createAbstractTypeAdapter();
      }
      @Override
      public Adapter caseAbstractTypedElement(AbstractTypedElement object) {
        return createAbstractTypedElementAdapter();
      }
      @Override
      public Adapter caseValueSpecification(ValueSpecification object) {
        return createValueSpecificationAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object) {
        return createEObjectAdapter();
      }
    };

	/**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
	@Override
	public Adapter createAdapter(Notifier target) {
    return modelSwitch.doSwitch((EObject)target);
  }


	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.behavior.AbstractBehavior <em>Abstract Behavior</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.behavior.AbstractBehavior
   * @generated
   */
	public Adapter createAbstractBehaviorAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.behavior.AbstractSignal <em>Abstract Signal</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.behavior.AbstractSignal
   * @generated
   */
	public Adapter createAbstractSignalAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.behavior.AbstractEvent <em>Abstract Event</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.behavior.AbstractEvent
   * @generated
   */
	public Adapter createAbstractEventAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.behavior.AbstractTimeEvent <em>Abstract Time Event</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.behavior.AbstractTimeEvent
   * @generated
   */
	public Adapter createAbstractTimeEventAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.behavior.AbstractMessageEvent <em>Abstract Message Event</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.behavior.AbstractMessageEvent
   * @generated
   */
	public Adapter createAbstractMessageEventAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.behavior.AbstractSignalEvent <em>Abstract Signal Event</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.behavior.AbstractSignalEvent
   * @generated
   */
	public Adapter createAbstractSignalEventAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.behavior.TimeExpression <em>Time Expression</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.behavior.TimeExpression
   * @generated
   */
	public Adapter createTimeExpressionAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.kitalpha.emde.model.Element <em>Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.kitalpha.emde.model.Element
   * @generated
   */
	public Adapter createElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.kitalpha.emde.model.ExtensibleElement <em>Extensible Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.kitalpha.emde.model.ExtensibleElement
   * @generated
   */
	public Adapter createExtensibleElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.ModelElement <em>Model Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.ModelElement
   * @generated
   */
	public Adapter createModelElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.AbstractNamedElement <em>Abstract Named Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractNamedElement
   * @generated
   */
	public Adapter createAbstractNamedElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.AbstractType <em>Abstract Type</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractType
   * @generated
   */
	public Adapter createAbstractTypeAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.AbstractTypedElement <em>Abstract Typed Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractTypedElement
   * @generated
   */
	public Adapter createAbstractTypedElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.ValueSpecification <em>Value Specification</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.ValueSpecification
   * @generated
   */
	public Adapter createValueSpecificationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
	public Adapter createEObjectAdapter() {
    return null;
  }

} //BehaviorAdapterFactory
