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
package org.polarsys.capella.core.data.sharedmodel.util;

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
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.ReuseableStructure;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.sharedmodel.*;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;
import org.polarsys.kitalpha.emde.model.Element;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage
 * @generated
 */
public class SharedmodelAdapterFactory extends AdapterFactoryImpl {
	/**
   * The cached model package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static SharedmodelPackage modelPackage;

	/**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public SharedmodelAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = SharedmodelPackage.eINSTANCE;
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
	protected SharedmodelSwitch<Adapter> modelSwitch =
		new SharedmodelSwitch<Adapter>() {
      @Override
      public Adapter caseSharedPkg(SharedPkg object) {
        return createSharedPkgAdapter();
      }
      @Override
      public Adapter caseGenericPkg(GenericPkg object) {
        return createGenericPkgAdapter();
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
      public Adapter caseNamespace(Namespace object) {
        return createNamespaceAdapter();
      }
      @Override
      public Adapter caseStructure(Structure object) {
        return createStructureAdapter();
      }
      @Override
      public Adapter caseReuseableStructure(ReuseableStructure object) {
        return createReuseableStructureAdapter();
      }
      @Override
      public Adapter caseModelRoot(ModelRoot object) {
        return createModelRootAdapter();
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
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.sharedmodel.SharedPkg <em>Shared Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.sharedmodel.SharedPkg
   * @generated
   */
	public Adapter createSharedPkgAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.sharedmodel.GenericPkg <em>Generic Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.sharedmodel.GenericPkg
   * @generated
   */
	public Adapter createGenericPkgAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.TraceableElement <em>Traceable Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.TraceableElement
   * @generated
   */
	public Adapter createTraceableElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.PublishableElement <em>Publishable Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.PublishableElement
   * @generated
   */
	public Adapter createPublishableElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.CapellaElement <em>Capella Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.CapellaElement
   * @generated
   */
	public Adapter createCapellaElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.NamedElement <em>Named Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.NamedElement
   * @generated
   */
	public Adapter createNamedElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.Namespace <em>Namespace</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.Namespace
   * @generated
   */
	public Adapter createNamespaceAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.Structure <em>Structure</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.Structure
   * @generated
   */
	public Adapter createStructureAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.ReuseableStructure <em>Reuseable Structure</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.ReuseableStructure
   * @generated
   */
	public Adapter createReuseableStructureAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellamodeller.ModelRoot <em>Model Root</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellamodeller.ModelRoot
   * @generated
   */
	public Adapter createModelRootAdapter() {
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

} //SharedmodelAdapterFactory
