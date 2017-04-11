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

package org.polarsys.capella.vp.ms.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.vp.ms.AndOperation;
import org.polarsys.capella.vp.ms.BooleanExpression;
import org.polarsys.capella.vp.ms.BooleanOperation;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.Comparison;
import org.polarsys.capella.vp.ms.FSMType;
import org.polarsys.capella.vp.ms.InSituationExpression;
import org.polarsys.capella.vp.ms.InStateExpression;
import org.polarsys.capella.vp.ms.MsPackage;
import org.polarsys.capella.vp.ms.NotOperation;
import org.polarsys.capella.vp.ms.OrOperation;
import org.polarsys.capella.vp.ms.Result;
import org.polarsys.capella.vp.ms.Situation;
import org.polarsys.kitalpha.emde.model.Element;
import org.polarsys.kitalpha.emde.model.ElementExtension;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 * 
 * @see org.polarsys.capella.vp.ms.MsPackage
 * @generated
 */
public class MsAdapterFactory extends AdapterFactoryImpl {
  /**
   * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected static MsPackage modelPackage;

  /**
   * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public MsAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = MsPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This implementation
   * returns <code>true</code> if the object is either the model's package or is an instance object of the model. <!--
   * end-user-doc -->
   * 
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject) object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected MsSwitch<Adapter> modelSwitch = new MsSwitch<Adapter>() {
    @Override
    public Adapter caseCSConfiguration(CSConfiguration object) {
      return createCSConfigurationAdapter();
    }

    @Override
    public Adapter caseFSMType(FSMType object) {
      return createFSMTypeAdapter();
    }

    @Override
    public Adapter caseSituation(Situation object) {
      return createSituationAdapter();
    }

    @Override
    public Adapter caseBooleanExpression(BooleanExpression object) {
      return createBooleanExpressionAdapter();
    }

    @Override
    public Adapter caseBooleanOperation(BooleanOperation object) {
      return createBooleanOperationAdapter();
    }

    @Override
    public Adapter caseInStateExpression(InStateExpression object) {
      return createInStateExpressionAdapter();
    }

    @Override
    public Adapter caseInSituationExpression(InSituationExpression object) {
      return createInSituationExpressionAdapter();
    }

    @Override
    public Adapter caseAndOperation(AndOperation object) {
      return createAndOperationAdapter();
    }

    @Override
    public Adapter caseOrOperation(OrOperation object) {
      return createOrOperationAdapter();
    }

    @Override
    public Adapter caseNotOperation(NotOperation object) {
      return createNotOperationAdapter();
    }

    @Override
    public Adapter caseComparison(Comparison object) {
      return createComparisonAdapter();
    }

    @Override
    public Adapter caseResult(Result object) {
      return createResultAdapter();
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
    public Adapter caseTraceableElement(TraceableElement object) {
      return createTraceableElementAdapter();
    }

    @Override
    public Adapter casePublishableElement(PublishableElement object) {
      return createPublishableElementAdapter();
    }

    @Override
    public Adapter caseCapellaElement(CapellaElement object) {
      return createCapellaElementAdapter();
    }

    @Override
    public Adapter caseNamedElement(NamedElement object) {
      return createNamedElementAdapter();
    }

    @Override
    public Adapter caseElementExtension(ElementExtension object) {
      return createElementExtensionAdapter();
    }

    @Override
    public Adapter defaultCase(EObject object) {
      return createEObjectAdapter();
    }
  };

  /**
   * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param target
   *          the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target) {
    return modelSwitch.doSwitch((EObject) target);
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.vp.ms.CSConfiguration <em>CS
   * Configuration</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
   * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.vp.ms.CSConfiguration
   * @generated
   */
  public Adapter createCSConfigurationAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.vp.ms.FSMType <em>FSM Type</em>}'. <!--
   * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.vp.ms.FSMType
   * @generated
   */
  public Adapter createFSMTypeAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.vp.ms.Situation <em>Situation</em>}'.
   * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.vp.ms.Situation
   * @generated
   */
  public Adapter createSituationAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.vp.ms.BooleanExpression <em>Boolean
   * Expression</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
   * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.vp.ms.BooleanExpression
   * @generated
   */
  public Adapter createBooleanExpressionAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.vp.ms.BooleanOperation <em>Boolean
   * Operation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
   * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.vp.ms.BooleanOperation
   * @generated
   */
  public Adapter createBooleanOperationAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.vp.ms.InStateExpression <em>In State
   * Expression</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
   * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.vp.ms.InStateExpression
   * @generated
   */
  public Adapter createInStateExpressionAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.vp.ms.InSituationExpression <em>In
   * Situation Expression</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
   * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.vp.ms.InSituationExpression
   * @generated
   */
  public Adapter createInSituationExpressionAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.vp.ms.AndOperation <em>And
   * Operation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
   * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.vp.ms.AndOperation
   * @generated
   */
  public Adapter createAndOperationAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.vp.ms.OrOperation <em>Or
   * Operation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
   * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.vp.ms.OrOperation
   * @generated
   */
  public Adapter createOrOperationAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.vp.ms.NotOperation <em>Not
   * Operation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
   * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.vp.ms.NotOperation
   * @generated
   */
  public Adapter createNotOperationAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.vp.ms.Comparison <em>Comparison</em>}'.
   * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.vp.ms.Comparison
   * @generated
   */
  public Adapter createComparisonAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.vp.ms.Result <em>Result</em>}'. <!--
   * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.vp.ms.Result
   * @generated
   */
  public Adapter createResultAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.kitalpha.emde.model.Element <em>Element</em>}'.
   * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.kitalpha.emde.model.Element
   * @generated
   */
  public Adapter createElementAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.kitalpha.emde.model.ExtensibleElement
   * <em>Extensible Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
   * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
   * end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.kitalpha.emde.model.ExtensibleElement
   * @generated
   */
  public Adapter createExtensibleElementAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.ModelElement
   * <em>Model Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
   * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.ModelElement
   * @generated
   */
  public Adapter createModelElementAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '
   * {@link org.polarsys.capella.common.data.modellingcore.AbstractNamedElement <em>Abstract Named Element</em>}'. <!--
   * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractNamedElement
   * @generated
   */
  public Adapter createAbstractNamedElementAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '
   * {@link org.polarsys.capella.common.data.modellingcore.TraceableElement <em>Traceable Element</em>}'. <!--
   * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.TraceableElement
   * @generated
   */
  public Adapter createTraceableElementAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '
   * {@link org.polarsys.capella.common.data.modellingcore.PublishableElement <em>Publishable Element</em>}'. <!--
   * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.PublishableElement
   * @generated
   */
  public Adapter createPublishableElementAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.CapellaElement
   * <em>Capella Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
   * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.CapellaElement
   * @generated
   */
  public Adapter createCapellaElementAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.NamedElement
   * <em>Named Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
   * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.NamedElement
   * @generated
   */
  public Adapter createNamedElementAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.kitalpha.emde.model.ElementExtension <em>Element
   * Extension</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
   * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.polarsys.kitalpha.emde.model.ElementExtension
   * @generated
   */
  public Adapter createElementExtensionAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null. <!--
   * end-user-doc -->
   * 
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter() {
    return null;
  }

} // MsAdapterFactory
