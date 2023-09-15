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
package org.polarsys.capella.core.data.capellacore.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacore.*;
import org.polarsys.capella.core.data.capellacore.AbstractAnnotation;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg;
import org.polarsys.capella.core.data.capellacore.AbstractModellingStructure;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.data.capellacore.BooleanPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.FloatPropertyValue;
import org.polarsys.capella.core.data.capellacore.GeneralClass;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.IntegerPropertyValue;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecturePkg;
import org.polarsys.capella.core.data.capellacore.ModellingBlock;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.NamedRelationship;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.NamingRule;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellacore.ReuseableStructure;
import org.polarsys.capella.core.data.capellacore.ReuserStructure;
import org.polarsys.capella.core.data.capellacore.StringPropertyValue;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.kitalpha.emde.model.Element;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage
 * @generated
 */
public class CapellacoreSwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static CapellacorePackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CapellacoreSwitch() {
    if (modelPackage == null) {
      modelPackage = CapellacorePackage.eINSTANCE;
    }
  }

	/**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
    return ePackage == modelPackage;
  }

	/**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
      case CapellacorePackage.CAPELLA_ELEMENT: {
        CapellaElement capellaElement = (CapellaElement)theEObject;
        T result = caseCapellaElement(capellaElement);
        if (result == null) result = caseTraceableElement(capellaElement);
        if (result == null) result = casePublishableElement(capellaElement);
        if (result == null) result = caseModelElement(capellaElement);
        if (result == null) result = caseExtensibleElement(capellaElement);
        if (result == null) result = caseElement(capellaElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.NAMED_ELEMENT: {
        NamedElement namedElement = (NamedElement)theEObject;
        T result = caseNamedElement(namedElement);
        if (result == null) result = caseAbstractNamedElement(namedElement);
        if (result == null) result = caseCapellaElement(namedElement);
        if (result == null) result = caseTraceableElement(namedElement);
        if (result == null) result = casePublishableElement(namedElement);
        if (result == null) result = caseModelElement(namedElement);
        if (result == null) result = caseExtensibleElement(namedElement);
        if (result == null) result = caseElement(namedElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.RELATIONSHIP: {
        Relationship relationship = (Relationship)theEObject;
        T result = caseRelationship(relationship);
        if (result == null) result = caseAbstractRelationship(relationship);
        if (result == null) result = caseCapellaElement(relationship);
        if (result == null) result = caseTraceableElement(relationship);
        if (result == null) result = casePublishableElement(relationship);
        if (result == null) result = caseModelElement(relationship);
        if (result == null) result = caseExtensibleElement(relationship);
        if (result == null) result = caseElement(relationship);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.NAMESPACE: {
        Namespace namespace = (Namespace)theEObject;
        T result = caseNamespace(namespace);
        if (result == null) result = caseNamedElement(namespace);
        if (result == null) result = caseAbstractNamedElement(namespace);
        if (result == null) result = caseCapellaElement(namespace);
        if (result == null) result = caseTraceableElement(namespace);
        if (result == null) result = casePublishableElement(namespace);
        if (result == null) result = caseModelElement(namespace);
        if (result == null) result = caseExtensibleElement(namespace);
        if (result == null) result = caseElement(namespace);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.NAMED_RELATIONSHIP: {
        NamedRelationship namedRelationship = (NamedRelationship)theEObject;
        T result = caseNamedRelationship(namedRelationship);
        if (result == null) result = caseRelationship(namedRelationship);
        if (result == null) result = caseNamedElement(namedRelationship);
        if (result == null) result = caseAbstractRelationship(namedRelationship);
        if (result == null) result = caseCapellaElement(namedRelationship);
        if (result == null) result = caseAbstractNamedElement(namedRelationship);
        if (result == null) result = caseTraceableElement(namedRelationship);
        if (result == null) result = casePublishableElement(namedRelationship);
        if (result == null) result = caseModelElement(namedRelationship);
        if (result == null) result = caseExtensibleElement(namedRelationship);
        if (result == null) result = caseElement(namedRelationship);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.STRUCTURE: {
        Structure structure = (Structure)theEObject;
        T result = caseStructure(structure);
        if (result == null) result = caseNamespace(structure);
        if (result == null) result = caseNamedElement(structure);
        if (result == null) result = caseAbstractNamedElement(structure);
        if (result == null) result = caseCapellaElement(structure);
        if (result == null) result = caseTraceableElement(structure);
        if (result == null) result = casePublishableElement(structure);
        if (result == null) result = caseModelElement(structure);
        if (result == null) result = caseExtensibleElement(structure);
        if (result == null) result = caseElement(structure);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE: {
        AbstractModellingStructure abstractModellingStructure = (AbstractModellingStructure)theEObject;
        T result = caseAbstractModellingStructure(abstractModellingStructure);
        if (result == null) result = caseReuserStructure(abstractModellingStructure);
        if (result == null) result = caseStructure(abstractModellingStructure);
        if (result == null) result = caseNamespace(abstractModellingStructure);
        if (result == null) result = caseNamedElement(abstractModellingStructure);
        if (result == null) result = caseAbstractNamedElement(abstractModellingStructure);
        if (result == null) result = caseCapellaElement(abstractModellingStructure);
        if (result == null) result = caseTraceableElement(abstractModellingStructure);
        if (result == null) result = casePublishableElement(abstractModellingStructure);
        if (result == null) result = caseModelElement(abstractModellingStructure);
        if (result == null) result = caseExtensibleElement(abstractModellingStructure);
        if (result == null) result = caseElement(abstractModellingStructure);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.MODELLING_BLOCK: {
        ModellingBlock modellingBlock = (ModellingBlock)theEObject;
        T result = caseModellingBlock(modellingBlock);
        if (result == null) result = caseType(modellingBlock);
        if (result == null) result = caseAbstractType(modellingBlock);
        if (result == null) result = caseNamespace(modellingBlock);
        if (result == null) result = caseNamedElement(modellingBlock);
        if (result == null) result = caseAbstractNamedElement(modellingBlock);
        if (result == null) result = caseCapellaElement(modellingBlock);
        if (result == null) result = caseExtensibleElement(modellingBlock);
        if (result == null) result = caseTraceableElement(modellingBlock);
        if (result == null) result = casePublishableElement(modellingBlock);
        if (result == null) result = caseModelElement(modellingBlock);
        if (result == null) result = caseElement(modellingBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.MODELLING_ARCHITECTURE: {
        ModellingArchitecture modellingArchitecture = (ModellingArchitecture)theEObject;
        T result = caseModellingArchitecture(modellingArchitecture);
        if (result == null) result = caseStructure(modellingArchitecture);
        if (result == null) result = caseNamespace(modellingArchitecture);
        if (result == null) result = caseNamedElement(modellingArchitecture);
        if (result == null) result = caseAbstractNamedElement(modellingArchitecture);
        if (result == null) result = caseCapellaElement(modellingArchitecture);
        if (result == null) result = caseTraceableElement(modellingArchitecture);
        if (result == null) result = casePublishableElement(modellingArchitecture);
        if (result == null) result = caseModelElement(modellingArchitecture);
        if (result == null) result = caseExtensibleElement(modellingArchitecture);
        if (result == null) result = caseElement(modellingArchitecture);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.MODELLING_ARCHITECTURE_PKG: {
        ModellingArchitecturePkg modellingArchitecturePkg = (ModellingArchitecturePkg)theEObject;
        T result = caseModellingArchitecturePkg(modellingArchitecturePkg);
        if (result == null) result = caseStructure(modellingArchitecturePkg);
        if (result == null) result = caseNamespace(modellingArchitecturePkg);
        if (result == null) result = caseNamedElement(modellingArchitecturePkg);
        if (result == null) result = caseAbstractNamedElement(modellingArchitecturePkg);
        if (result == null) result = caseCapellaElement(modellingArchitecturePkg);
        if (result == null) result = caseTraceableElement(modellingArchitecturePkg);
        if (result == null) result = casePublishableElement(modellingArchitecturePkg);
        if (result == null) result = caseModelElement(modellingArchitecturePkg);
        if (result == null) result = caseExtensibleElement(modellingArchitecturePkg);
        if (result == null) result = caseElement(modellingArchitecturePkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.TYPE: {
        Type type = (Type)theEObject;
        T result = caseType(type);
        if (result == null) result = caseAbstractType(type);
        if (result == null) result = caseNamespace(type);
        if (result == null) result = caseNamedElement(type);
        if (result == null) result = caseAbstractNamedElement(type);
        if (result == null) result = caseCapellaElement(type);
        if (result == null) result = caseExtensibleElement(type);
        if (result == null) result = caseTraceableElement(type);
        if (result == null) result = casePublishableElement(type);
        if (result == null) result = caseModelElement(type);
        if (result == null) result = caseElement(type);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.TYPED_ELEMENT: {
        TypedElement typedElement = (TypedElement)theEObject;
        T result = caseTypedElement(typedElement);
        if (result == null) result = caseAbstractTypedElement(typedElement);
        if (result == null) result = caseNamedElement(typedElement);
        if (result == null) result = caseAbstractNamedElement(typedElement);
        if (result == null) result = caseCapellaElement(typedElement);
        if (result == null) result = caseTraceableElement(typedElement);
        if (result == null) result = casePublishableElement(typedElement);
        if (result == null) result = caseModelElement(typedElement);
        if (result == null) result = caseExtensibleElement(typedElement);
        if (result == null) result = caseElement(typedElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.TRACE: {
        Trace trace = (Trace)theEObject;
        T result = caseTrace(trace);
        if (result == null) result = caseRelationship(trace);
        if (result == null) result = caseAbstractTrace(trace);
        if (result == null) result = caseAbstractRelationship(trace);
        if (result == null) result = caseCapellaElement(trace);
        if (result == null) result = caseTraceableElement(trace);
        if (result == null) result = casePublishableElement(trace);
        if (result == null) result = caseModelElement(trace);
        if (result == null) result = caseExtensibleElement(trace);
        if (result == null) result = caseElement(trace);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.ABSTRACT_ANNOTATION: {
        AbstractAnnotation abstractAnnotation = (AbstractAnnotation)theEObject;
        T result = caseAbstractAnnotation(abstractAnnotation);
        if (result == null) result = caseCapellaElement(abstractAnnotation);
        if (result == null) result = caseTraceableElement(abstractAnnotation);
        if (result == null) result = casePublishableElement(abstractAnnotation);
        if (result == null) result = caseModelElement(abstractAnnotation);
        if (result == null) result = caseExtensibleElement(abstractAnnotation);
        if (result == null) result = caseElement(abstractAnnotation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.NAMING_RULE: {
        NamingRule namingRule = (NamingRule)theEObject;
        T result = caseNamingRule(namingRule);
        if (result == null) result = caseAbstractAnnotation(namingRule);
        if (result == null) result = caseCapellaElement(namingRule);
        if (result == null) result = caseTraceableElement(namingRule);
        if (result == null) result = casePublishableElement(namingRule);
        if (result == null) result = caseModelElement(namingRule);
        if (result == null) result = caseExtensibleElement(namingRule);
        if (result == null) result = caseElement(namingRule);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.CONSTRAINT: {
        Constraint constraint = (Constraint)theEObject;
        T result = caseConstraint(constraint);
        if (result == null) result = caseNamedElement(constraint);
        if (result == null) result = caseAbstractConstraint(constraint);
        if (result == null) result = caseAbstractNamedElement(constraint);
        if (result == null) result = caseCapellaElement(constraint);
        if (result == null) result = caseTraceableElement(constraint);
        if (result == null) result = casePublishableElement(constraint);
        if (result == null) result = caseModelElement(constraint);
        if (result == null) result = caseExtensibleElement(constraint);
        if (result == null) result = caseElement(constraint);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.KEY_VALUE: {
        KeyValue keyValue = (KeyValue)theEObject;
        T result = caseKeyValue(keyValue);
        if (result == null) result = caseCapellaElement(keyValue);
        if (result == null) result = caseTraceableElement(keyValue);
        if (result == null) result = casePublishableElement(keyValue);
        if (result == null) result = caseModelElement(keyValue);
        if (result == null) result = caseExtensibleElement(keyValue);
        if (result == null) result = caseElement(keyValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.REUSE_LINK: {
        ReuseLink reuseLink = (ReuseLink)theEObject;
        T result = caseReuseLink(reuseLink);
        if (result == null) result = caseRelationship(reuseLink);
        if (result == null) result = caseAbstractRelationship(reuseLink);
        if (result == null) result = caseCapellaElement(reuseLink);
        if (result == null) result = caseTraceableElement(reuseLink);
        if (result == null) result = casePublishableElement(reuseLink);
        if (result == null) result = caseModelElement(reuseLink);
        if (result == null) result = caseExtensibleElement(reuseLink);
        if (result == null) result = caseElement(reuseLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.REUSEABLE_STRUCTURE: {
        ReuseableStructure reuseableStructure = (ReuseableStructure)theEObject;
        T result = caseReuseableStructure(reuseableStructure);
        if (result == null) result = caseStructure(reuseableStructure);
        if (result == null) result = caseNamespace(reuseableStructure);
        if (result == null) result = caseNamedElement(reuseableStructure);
        if (result == null) result = caseAbstractNamedElement(reuseableStructure);
        if (result == null) result = caseCapellaElement(reuseableStructure);
        if (result == null) result = caseTraceableElement(reuseableStructure);
        if (result == null) result = casePublishableElement(reuseableStructure);
        if (result == null) result = caseModelElement(reuseableStructure);
        if (result == null) result = caseExtensibleElement(reuseableStructure);
        if (result == null) result = caseElement(reuseableStructure);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.REUSER_STRUCTURE: {
        ReuserStructure reuserStructure = (ReuserStructure)theEObject;
        T result = caseReuserStructure(reuserStructure);
        if (result == null) result = caseStructure(reuserStructure);
        if (result == null) result = caseNamespace(reuserStructure);
        if (result == null) result = caseNamedElement(reuserStructure);
        if (result == null) result = caseAbstractNamedElement(reuserStructure);
        if (result == null) result = caseCapellaElement(reuserStructure);
        if (result == null) result = caseTraceableElement(reuserStructure);
        if (result == null) result = casePublishableElement(reuserStructure);
        if (result == null) result = caseModelElement(reuserStructure);
        if (result == null) result = caseExtensibleElement(reuserStructure);
        if (result == null) result = caseElement(reuserStructure);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.GENERALIZABLE_ELEMENT: {
        GeneralizableElement generalizableElement = (GeneralizableElement)theEObject;
        T result = caseGeneralizableElement(generalizableElement);
        if (result == null) result = caseType(generalizableElement);
        if (result == null) result = caseAbstractType(generalizableElement);
        if (result == null) result = caseNamespace(generalizableElement);
        if (result == null) result = caseNamedElement(generalizableElement);
        if (result == null) result = caseAbstractNamedElement(generalizableElement);
        if (result == null) result = caseCapellaElement(generalizableElement);
        if (result == null) result = caseExtensibleElement(generalizableElement);
        if (result == null) result = caseTraceableElement(generalizableElement);
        if (result == null) result = casePublishableElement(generalizableElement);
        if (result == null) result = caseModelElement(generalizableElement);
        if (result == null) result = caseElement(generalizableElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.CLASSIFIER: {
        Classifier classifier = (Classifier)theEObject;
        T result = caseClassifier(classifier);
        if (result == null) result = caseGeneralizableElement(classifier);
        if (result == null) result = caseType(classifier);
        if (result == null) result = caseAbstractType(classifier);
        if (result == null) result = caseNamespace(classifier);
        if (result == null) result = caseNamedElement(classifier);
        if (result == null) result = caseAbstractNamedElement(classifier);
        if (result == null) result = caseCapellaElement(classifier);
        if (result == null) result = caseExtensibleElement(classifier);
        if (result == null) result = caseTraceableElement(classifier);
        if (result == null) result = casePublishableElement(classifier);
        if (result == null) result = caseModelElement(classifier);
        if (result == null) result = caseElement(classifier);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.GENERAL_CLASS: {
        GeneralClass generalClass = (GeneralClass)theEObject;
        T result = caseGeneralClass(generalClass);
        if (result == null) result = caseClassifier(generalClass);
        if (result == null) result = caseFinalizableElement(generalClass);
        if (result == null) result = caseGeneralizableElement(generalClass);
        if (result == null) result = caseType(generalClass);
        if (result == null) result = caseAbstractType(generalClass);
        if (result == null) result = caseNamespace(generalClass);
        if (result == null) result = caseNamedElement(generalClass);
        if (result == null) result = caseAbstractNamedElement(generalClass);
        if (result == null) result = caseCapellaElement(generalClass);
        if (result == null) result = caseExtensibleElement(generalClass);
        if (result == null) result = caseTraceableElement(generalClass);
        if (result == null) result = casePublishableElement(generalClass);
        if (result == null) result = caseModelElement(generalClass);
        if (result == null) result = caseElement(generalClass);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.GENERALIZATION: {
        Generalization generalization = (Generalization)theEObject;
        T result = caseGeneralization(generalization);
        if (result == null) result = caseRelationship(generalization);
        if (result == null) result = caseAbstractRelationship(generalization);
        if (result == null) result = caseCapellaElement(generalization);
        if (result == null) result = caseTraceableElement(generalization);
        if (result == null) result = casePublishableElement(generalization);
        if (result == null) result = caseModelElement(generalization);
        if (result == null) result = caseExtensibleElement(generalization);
        if (result == null) result = caseElement(generalization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.FEATURE: {
        Feature feature = (Feature)theEObject;
        T result = caseFeature(feature);
        if (result == null) result = caseNamedElement(feature);
        if (result == null) result = caseAbstractNamedElement(feature);
        if (result == null) result = caseCapellaElement(feature);
        if (result == null) result = caseTraceableElement(feature);
        if (result == null) result = casePublishableElement(feature);
        if (result == null) result = caseModelElement(feature);
        if (result == null) result = caseExtensibleElement(feature);
        if (result == null) result = caseElement(feature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.ABSTRACT_EXCHANGE_ITEM_PKG: {
        AbstractExchangeItemPkg abstractExchangeItemPkg = (AbstractExchangeItemPkg)theEObject;
        T result = caseAbstractExchangeItemPkg(abstractExchangeItemPkg);
        if (result == null) result = caseStructure(abstractExchangeItemPkg);
        if (result == null) result = caseNamespace(abstractExchangeItemPkg);
        if (result == null) result = caseNamedElement(abstractExchangeItemPkg);
        if (result == null) result = caseAbstractNamedElement(abstractExchangeItemPkg);
        if (result == null) result = caseCapellaElement(abstractExchangeItemPkg);
        if (result == null) result = caseTraceableElement(abstractExchangeItemPkg);
        if (result == null) result = casePublishableElement(abstractExchangeItemPkg);
        if (result == null) result = caseModelElement(abstractExchangeItemPkg);
        if (result == null) result = caseExtensibleElement(abstractExchangeItemPkg);
        if (result == null) result = caseElement(abstractExchangeItemPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.ALLOCATION: {
        Allocation allocation = (Allocation)theEObject;
        T result = caseAllocation(allocation);
        if (result == null) result = caseRelationship(allocation);
        if (result == null) result = caseAbstractTrace(allocation);
        if (result == null) result = caseAbstractRelationship(allocation);
        if (result == null) result = caseCapellaElement(allocation);
        if (result == null) result = caseTraceableElement(allocation);
        if (result == null) result = casePublishableElement(allocation);
        if (result == null) result = caseModelElement(allocation);
        if (result == null) result = caseExtensibleElement(allocation);
        if (result == null) result = caseElement(allocation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.INVOLVEMENT: {
        Involvement involvement = (Involvement)theEObject;
        T result = caseInvolvement(involvement);
        if (result == null) result = caseRelationship(involvement);
        if (result == null) result = caseAbstractRelationship(involvement);
        if (result == null) result = caseCapellaElement(involvement);
        if (result == null) result = caseTraceableElement(involvement);
        if (result == null) result = casePublishableElement(involvement);
        if (result == null) result = caseModelElement(involvement);
        if (result == null) result = caseExtensibleElement(involvement);
        if (result == null) result = caseElement(involvement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.INVOLVER_ELEMENT: {
        InvolverElement involverElement = (InvolverElement)theEObject;
        T result = caseInvolverElement(involverElement);
        if (result == null) result = caseCapellaElement(involverElement);
        if (result == null) result = caseTraceableElement(involverElement);
        if (result == null) result = casePublishableElement(involverElement);
        if (result == null) result = caseModelElement(involverElement);
        if (result == null) result = caseExtensibleElement(involverElement);
        if (result == null) result = caseElement(involverElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.INVOLVED_ELEMENT: {
        InvolvedElement involvedElement = (InvolvedElement)theEObject;
        T result = caseInvolvedElement(involvedElement);
        if (result == null) result = caseCapellaElement(involvedElement);
        if (result == null) result = caseTraceableElement(involvedElement);
        if (result == null) result = casePublishableElement(involvedElement);
        if (result == null) result = caseModelElement(involvedElement);
        if (result == null) result = caseExtensibleElement(involvedElement);
        if (result == null) result = caseElement(involvedElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.ABSTRACT_PROPERTY_VALUE: {
        AbstractPropertyValue abstractPropertyValue = (AbstractPropertyValue)theEObject;
        T result = caseAbstractPropertyValue(abstractPropertyValue);
        if (result == null) result = caseNamedElement(abstractPropertyValue);
        if (result == null) result = caseAbstractNamedElement(abstractPropertyValue);
        if (result == null) result = caseCapellaElement(abstractPropertyValue);
        if (result == null) result = caseTraceableElement(abstractPropertyValue);
        if (result == null) result = casePublishableElement(abstractPropertyValue);
        if (result == null) result = caseModelElement(abstractPropertyValue);
        if (result == null) result = caseExtensibleElement(abstractPropertyValue);
        if (result == null) result = caseElement(abstractPropertyValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.STRING_PROPERTY_VALUE: {
        StringPropertyValue stringPropertyValue = (StringPropertyValue)theEObject;
        T result = caseStringPropertyValue(stringPropertyValue);
        if (result == null) result = caseAbstractPropertyValue(stringPropertyValue);
        if (result == null) result = caseNamedElement(stringPropertyValue);
        if (result == null) result = caseAbstractNamedElement(stringPropertyValue);
        if (result == null) result = caseCapellaElement(stringPropertyValue);
        if (result == null) result = caseTraceableElement(stringPropertyValue);
        if (result == null) result = casePublishableElement(stringPropertyValue);
        if (result == null) result = caseModelElement(stringPropertyValue);
        if (result == null) result = caseExtensibleElement(stringPropertyValue);
        if (result == null) result = caseElement(stringPropertyValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.INTEGER_PROPERTY_VALUE: {
        IntegerPropertyValue integerPropertyValue = (IntegerPropertyValue)theEObject;
        T result = caseIntegerPropertyValue(integerPropertyValue);
        if (result == null) result = caseAbstractPropertyValue(integerPropertyValue);
        if (result == null) result = caseNamedElement(integerPropertyValue);
        if (result == null) result = caseAbstractNamedElement(integerPropertyValue);
        if (result == null) result = caseCapellaElement(integerPropertyValue);
        if (result == null) result = caseTraceableElement(integerPropertyValue);
        if (result == null) result = casePublishableElement(integerPropertyValue);
        if (result == null) result = caseModelElement(integerPropertyValue);
        if (result == null) result = caseExtensibleElement(integerPropertyValue);
        if (result == null) result = caseElement(integerPropertyValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.BOOLEAN_PROPERTY_VALUE: {
        BooleanPropertyValue booleanPropertyValue = (BooleanPropertyValue)theEObject;
        T result = caseBooleanPropertyValue(booleanPropertyValue);
        if (result == null) result = caseAbstractPropertyValue(booleanPropertyValue);
        if (result == null) result = caseNamedElement(booleanPropertyValue);
        if (result == null) result = caseAbstractNamedElement(booleanPropertyValue);
        if (result == null) result = caseCapellaElement(booleanPropertyValue);
        if (result == null) result = caseTraceableElement(booleanPropertyValue);
        if (result == null) result = casePublishableElement(booleanPropertyValue);
        if (result == null) result = caseModelElement(booleanPropertyValue);
        if (result == null) result = caseExtensibleElement(booleanPropertyValue);
        if (result == null) result = caseElement(booleanPropertyValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.FLOAT_PROPERTY_VALUE: {
        FloatPropertyValue floatPropertyValue = (FloatPropertyValue)theEObject;
        T result = caseFloatPropertyValue(floatPropertyValue);
        if (result == null) result = caseAbstractPropertyValue(floatPropertyValue);
        if (result == null) result = caseNamedElement(floatPropertyValue);
        if (result == null) result = caseAbstractNamedElement(floatPropertyValue);
        if (result == null) result = caseCapellaElement(floatPropertyValue);
        if (result == null) result = caseTraceableElement(floatPropertyValue);
        if (result == null) result = casePublishableElement(floatPropertyValue);
        if (result == null) result = caseModelElement(floatPropertyValue);
        if (result == null) result = caseExtensibleElement(floatPropertyValue);
        if (result == null) result = caseElement(floatPropertyValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.ENUMERATION_PROPERTY_VALUE: {
        EnumerationPropertyValue enumerationPropertyValue = (EnumerationPropertyValue)theEObject;
        T result = caseEnumerationPropertyValue(enumerationPropertyValue);
        if (result == null) result = caseAbstractPropertyValue(enumerationPropertyValue);
        if (result == null) result = caseNamedElement(enumerationPropertyValue);
        if (result == null) result = caseAbstractNamedElement(enumerationPropertyValue);
        if (result == null) result = caseCapellaElement(enumerationPropertyValue);
        if (result == null) result = caseTraceableElement(enumerationPropertyValue);
        if (result == null) result = casePublishableElement(enumerationPropertyValue);
        if (result == null) result = caseModelElement(enumerationPropertyValue);
        if (result == null) result = caseExtensibleElement(enumerationPropertyValue);
        if (result == null) result = caseElement(enumerationPropertyValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.ENUMERATION_PROPERTY_TYPE: {
        EnumerationPropertyType enumerationPropertyType = (EnumerationPropertyType)theEObject;
        T result = caseEnumerationPropertyType(enumerationPropertyType);
        if (result == null) result = caseNamedElement(enumerationPropertyType);
        if (result == null) result = caseAbstractNamedElement(enumerationPropertyType);
        if (result == null) result = caseCapellaElement(enumerationPropertyType);
        if (result == null) result = caseTraceableElement(enumerationPropertyType);
        if (result == null) result = casePublishableElement(enumerationPropertyType);
        if (result == null) result = caseModelElement(enumerationPropertyType);
        if (result == null) result = caseExtensibleElement(enumerationPropertyType);
        if (result == null) result = caseElement(enumerationPropertyType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.ENUMERATION_PROPERTY_LITERAL: {
        EnumerationPropertyLiteral enumerationPropertyLiteral = (EnumerationPropertyLiteral)theEObject;
        T result = caseEnumerationPropertyLiteral(enumerationPropertyLiteral);
        if (result == null) result = caseNamedElement(enumerationPropertyLiteral);
        if (result == null) result = caseAbstractNamedElement(enumerationPropertyLiteral);
        if (result == null) result = caseCapellaElement(enumerationPropertyLiteral);
        if (result == null) result = caseTraceableElement(enumerationPropertyLiteral);
        if (result == null) result = casePublishableElement(enumerationPropertyLiteral);
        if (result == null) result = caseModelElement(enumerationPropertyLiteral);
        if (result == null) result = caseExtensibleElement(enumerationPropertyLiteral);
        if (result == null) result = caseElement(enumerationPropertyLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.PROPERTY_VALUE_GROUP: {
        PropertyValueGroup propertyValueGroup = (PropertyValueGroup)theEObject;
        T result = casePropertyValueGroup(propertyValueGroup);
        if (result == null) result = caseNamespace(propertyValueGroup);
        if (result == null) result = caseNamedElement(propertyValueGroup);
        if (result == null) result = caseAbstractNamedElement(propertyValueGroup);
        if (result == null) result = caseCapellaElement(propertyValueGroup);
        if (result == null) result = caseTraceableElement(propertyValueGroup);
        if (result == null) result = casePublishableElement(propertyValueGroup);
        if (result == null) result = caseModelElement(propertyValueGroup);
        if (result == null) result = caseExtensibleElement(propertyValueGroup);
        if (result == null) result = caseElement(propertyValueGroup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.PROPERTY_VALUE_PKG: {
        PropertyValuePkg propertyValuePkg = (PropertyValuePkg)theEObject;
        T result = casePropertyValuePkg(propertyValuePkg);
        if (result == null) result = caseStructure(propertyValuePkg);
        if (result == null) result = caseNamespace(propertyValuePkg);
        if (result == null) result = caseNamedElement(propertyValuePkg);
        if (result == null) result = caseAbstractNamedElement(propertyValuePkg);
        if (result == null) result = caseCapellaElement(propertyValuePkg);
        if (result == null) result = caseTraceableElement(propertyValuePkg);
        if (result == null) result = casePublishableElement(propertyValuePkg);
        if (result == null) result = caseModelElement(propertyValuePkg);
        if (result == null) result = caseExtensibleElement(propertyValuePkg);
        if (result == null) result = caseElement(propertyValuePkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG: {
        AbstractDependenciesPkg abstractDependenciesPkg = (AbstractDependenciesPkg)theEObject;
        T result = caseAbstractDependenciesPkg(abstractDependenciesPkg);
        if (result == null) result = caseStructure(abstractDependenciesPkg);
        if (result == null) result = caseNamespace(abstractDependenciesPkg);
        if (result == null) result = caseNamedElement(abstractDependenciesPkg);
        if (result == null) result = caseAbstractNamedElement(abstractDependenciesPkg);
        if (result == null) result = caseCapellaElement(abstractDependenciesPkg);
        if (result == null) result = caseTraceableElement(abstractDependenciesPkg);
        if (result == null) result = casePublishableElement(abstractDependenciesPkg);
        if (result == null) result = caseModelElement(abstractDependenciesPkg);
        if (result == null) result = caseExtensibleElement(abstractDependenciesPkg);
        if (result == null) result = caseElement(abstractDependenciesPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Capella Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Capella Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCapellaElement(CapellaElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNamedElement(NamedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Relationship</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Relationship</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseRelationship(Relationship object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Namespace</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Namespace</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNamespace(Namespace object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Named Relationship</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Relationship</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNamedRelationship(NamedRelationship object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Structure</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Structure</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStructure(Structure object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Modelling Structure</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Modelling Structure</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractModellingStructure(AbstractModellingStructure object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Modelling Block</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Modelling Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseModellingBlock(ModellingBlock object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Modelling Architecture</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Modelling Architecture</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseModellingArchitecture(ModellingArchitecture object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Modelling Architecture Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Modelling Architecture Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseModellingArchitecturePkg(ModellingArchitecturePkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Type</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseType(Type object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Typed Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Typed Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTypedElement(TypedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Trace</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Trace</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTrace(Trace object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Annotation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Annotation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractAnnotation(AbstractAnnotation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Naming Rule</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Naming Rule</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNamingRule(NamingRule object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Constraint</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Constraint</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseConstraint(Constraint object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Key Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Key Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseKeyValue(KeyValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Reuse Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Reuse Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseReuseLink(ReuseLink object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Reuseable Structure</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Reuseable Structure</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseReuseableStructure(ReuseableStructure object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Reuser Structure</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Reuser Structure</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseReuserStructure(ReuserStructure object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Generalizable Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Generalizable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseGeneralizableElement(GeneralizableElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Classifier</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Classifier</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseClassifier(Classifier object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>General Class</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>General Class</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseGeneralClass(GeneralClass object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Generalization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Generalization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseGeneralization(Generalization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Feature</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFeature(Feature object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Exchange Item Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Exchange Item Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractExchangeItemPkg(AbstractExchangeItemPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Allocation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Allocation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAllocation(Allocation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Involvement</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Involvement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInvolvement(Involvement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Involver Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Involver Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInvolverElement(InvolverElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Involved Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Involved Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInvolvedElement(InvolvedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Property Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Property Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractPropertyValue(AbstractPropertyValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>String Property Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Property Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStringPropertyValue(StringPropertyValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Integer Property Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Integer Property Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseIntegerPropertyValue(IntegerPropertyValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Boolean Property Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Boolean Property Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseBooleanPropertyValue(BooleanPropertyValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Float Property Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Float Property Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFloatPropertyValue(FloatPropertyValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Enumeration Property Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Enumeration Property Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseEnumerationPropertyValue(EnumerationPropertyValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Enumeration Property Type</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Enumeration Property Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseEnumerationPropertyType(EnumerationPropertyType object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Enumeration Property Literal</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Enumeration Property Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseEnumerationPropertyLiteral(EnumerationPropertyLiteral object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Property Value Group</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property Value Group</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePropertyValueGroup(PropertyValueGroup object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Property Value Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property Value Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePropertyValuePkg(PropertyValuePkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Dependencies Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Dependencies Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractDependenciesPkg(AbstractDependenciesPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseElement(Element object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExtensibleElement(ExtensibleElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseModelElement(ModelElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Traceable Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Traceable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTraceableElement(TraceableElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Publishable Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Publishable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePublishableElement(PublishableElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Named Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Named Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractNamedElement(AbstractNamedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Relationship</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Relationship</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractRelationship(AbstractRelationship object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Type</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractType(AbstractType object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Typed Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Typed Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractTypedElement(AbstractTypedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Trace</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Trace</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractTrace(AbstractTrace object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Constraint</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Constraint</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractConstraint(AbstractConstraint object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Finalizable Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Finalizable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFinalizableElement(FinalizableElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
	@Override
	public T defaultCase(EObject object) {
    return null;
  }

} //CapellacoreSwitch
