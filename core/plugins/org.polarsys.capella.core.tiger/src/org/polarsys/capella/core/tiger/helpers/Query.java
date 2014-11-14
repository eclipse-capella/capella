/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.tiger.helpers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.tiger.IResolver;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.impl.Transfo;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Utility class to provide queries for transformation based on
 * <ul>
 * <li>EMF</li>
 * <li>Capella</li>
 * </ul>
 */
public class Query {

  /**
   * Default constructor
   */
  private Query() {
    // Nothing to be done here...
  }

  /**
   * Get incoming traces for specified element (whatever the source may be).
   * @param element_p
   * @param transfo_p
   * @return
   */
  private static List<AbstractTrace> getIncomingTraces(CapellaElement element_p, ITransfo transfo_p) {
    return element_p.getIncomingTraces();
  }

  /**
   * Get incoming traces for specified element (whatever the source may be).
   * @param element_p
   * @param transfo_p
   * @return
   */
  private static List<AbstractTrace> getOutgoingTraces(CapellaElement element_p, ITransfo transfo_p) {
    return element_p.getOutgoingTraces();
  }

  /**
   * Retrieve a resolver
   * @param transfo
   */
  public static IResolver getResolver(ITransfo transfo) {
    List<IResolver> resolvers = transfo.getResolvers();
    if (resolvers.size() > 0) {
      return resolvers.get(0);
    }
    return null;
  }

  /**
   * Returns whether the object is transformed by the transformation
   * @param object_p The object to be tested
   * @param transfo_p The transformation
   * @return <code>true</code> if the object is transformed
   */
  public static boolean isElementTransformed(EObject object_p, ITransfo transfo_p) {
    return retrieveTransformedElement(object_p, transfo_p) != null;
  }

  /**
   * Returns whether the object is transformed by the transformation
   * @param object_p The object to be tested
   * @param transfo_p The transformation
   * @return <code>true</code> if the object is transformed
   */
  public static boolean isElementTransformed(EObject object_p, ITransfo transfo_p, EClass eTargetType) {
    return retrieveTransformedElement(object_p, transfo_p, eTargetType) != null;
  }

  /**
   * Specifies whether the transformation link has been created by the transformation
   * @param link_p The link to be tested
   * @param transfo_p The transformation
   * @return <code>true</code> if it is the case
   */
  public static boolean isLinkOfTransfo(AbstractTrace link_p, ITransfo transfo_p) {
    if (transfo_p == null) {
      return false;
    }

    if (isValidUID(link_p, transfo_p)) {
      if ((transfo_p instanceof Transfo) && ((Transfo) transfo_p).isValidLinkKind(link_p)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns whether the object is one to one transformed
   * @param object_p The object to be tested
   * @param transfo_p The transformation
   * @return <code>true</code> if the object is transformed
   */
  public static boolean isOneToManyTransformed(EObject object_p, ITransfo transfo_p) {
    return retrieveTransformedElements(object_p, transfo_p).size() > 1;
  }

  /**
   * Returns whether the object is one to one transformed
   * @param object_p The object to be tested
   * @param transfo_p The transformation
   * @return <code>true</code> if the object is transformed
   */
  public static boolean isOneToOneTransformed(EObject object_p, ITransfo transfo_p) {
    return isOneToOneTransformed(object_p, transfo_p, null);
  }

  /**
   * Returns whether the object is one to one transformed
   * @param object_p The object to be tested
   * @param transfo_p The transformation
   * @return <code>true</code> if the object is transformed
   */
  public static boolean isOneToOneTransformed(EObject object_p, ITransfo transfo_p, EClass target) {
    Object result = retrieveTransformedElement(object_p, transfo_p, target);
    return (result != null) && !((result instanceof List<?>) && (((List<?>) result).size() > 1));
  }

  public static boolean isValidUID(AbstractTrace link_p, ITransfo transfo_p) {
    boolean isDetected = false;

    // Workaround for Semantic trace : 'KeyValue' not supported by this link
    // kind
    if (!(link_p instanceof GenericTrace)) {
      isDetected = true;
    } else {
      GenericTrace genericTrace = (GenericTrace) link_p;
      // Case 'GenericTrace' kind
      for (KeyValue keyValue : genericTrace.getKeyValuePairs()) {
        String key = keyValue.getKey();
        String value = keyValue.getValue();

        if (key.equals(TigerRelationshipHelper.PROPERTY_TRANSFO_UID)) {
          isDetected = value.equals(transfo_p.getUid());

          // DELETE 1.6
          // In 1.5, TransfoLink id have been unified. Simple algorithm to check same targetId of transfoLinks
          if (!isDetected) {
            if (!value.contains("Bridge")) { //$NON-NLS-1$
              String[] values = value.split("TargetId");//$NON-NLS-1$
              String[] valuesTransfo = transfo_p.getUid().split("TargetId");//$NON-NLS-1$
              if ((values != null) && (valuesTransfo != null) && (values.length == 2) && (valuesTransfo.length == 2)) {
                if ((values[1] != null) && values[1].equals(valuesTransfo[1])) {
                  isDetected = true;
                }
              }
            }
          }
        }
      }
    }
    return isDetected;
  }

  /**
   * Retrieves the list of relationship names linking this object to others. Retains only the containment relationships
   * @return The list of relationship name
   */
  public static List<String> retrieveContainmentRelationships(EObject element) {
    EClass clazz = element.eClass();
    EList<?> features = clazz.getEAllContainments();
    List<String> relationshipNameList = new ArrayList<String>();

    for (Object featureObject : features) {
      EReference feature = (EReference) featureObject;
      if (feature.isContainer()) {
        relationshipNameList.add(feature.getName());
      }
    }

    return relationshipNameList;
  }

  /**
   * Retrieves the transformed elements by a specified transformation.
   * @param object_p The element to be queried
   * @param transfo_p The transformation
   * @param expectedTarget_p
   * @return The transformed element
   */
  public static EObject retrieveFirstTransformedElement(EObject object_p, ITransfo transfo_p) {
    return retrieveFirstTransformedElement(object_p, transfo_p, null);
  }

  /**
   * Retrieves the transformed elements by a specified transformation.
   * @param object_p The element to be queried
   * @param transfo_p The transformation
   * @param expectedTarget_p
   * @return The transformed element
   */
  public static EObject retrieveFirstTransformedElement(EObject object_p, ITransfo transfo_p, EClass expectedTarget_p) {
    Object res = retrieveTransformedElement(object_p, transfo_p, expectedTarget_p);
    if (res instanceof List<?>) {
      List<?> p = (List<?>) res;
      return (EObject) p.get(0);
    }
    return (EObject) res;
  }

  /**
   * Retrieves the model root of an element
   * @param object_p The element to be queried
   * @return The model root
   */
  public static ModelRoot retrieveModelRoot(EObject object_p) {
    EObject currentElement = object_p;
    while (currentElement != null) {
      if (currentElement instanceof ModelRoot) {
        return (ModelRoot) currentElement;
      }
      currentElement = currentElement.eContainer();
    }

    return null;
  }

  /**
   * Retrieve elements linked by a relationship by passing its name
   * @param relationshipString_p The relationship name
   * @return
   */
  @SuppressWarnings("unchecked")
  public static List<EObject> retrieveRelatedElements(EObject element, EReference relationshipString_p) {

    List<EObject> relatedElements = new ArrayList<EObject>();

    // Skip derived features (computed) <=> UML slashed relationships
    if (!relationshipString_p.isDerived()) {
      try {
        Object obj = element.eGet(relationshipString_p);
        if (obj instanceof EObject) {
          EObject eObject = (EObject) obj;
          relatedElements.add(eObject);
        } else {
          if (obj instanceof EList) {
            EList eObject = (EList) obj;
            relatedElements.addAll(eObject);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return relatedElements;
  }

  /**
   * Retrieves the list of related elements linked with the specified relationships in parameter
   * @param element_p The element
   * @param relationships_p The list of relationship (roles)
   * @return The element to be queried
   */
  public static List<EObject> retrieveRelatedElements(EObject element_p, EReference[] relationships_p) {
    List<EObject> relatedElements = new ArrayList<EObject>();

    for (EReference relationship : relationships_p) {
      relatedElements.addAll(retrieveRelatedElements(element_p, relationship));
    }

    return relatedElements;
  }

  /**
   * Retrieves the list of relationship names linking this object to others
   * @return The list of relationship name
   */
  public static List<String> retrieveRelationships(EObject element) {
    EClass clazz = element.eClass();
    EList<?> features = clazz.getEAllStructuralFeatures();
    List<String> relationshipNameList = new ArrayList<String>();

    for (Object featureObject : features) {
      EStructuralFeature feature = (EStructuralFeature) featureObject;
      relationshipNameList.add(feature.getName());
    }

    return relationshipNameList;
  }

  /**
   * Retrieves the shared package of an element (a Capella Project) at the upper containment level.
   * @param object_p The element to be tested
   * @return The list of shared packages
   */
  public static List<SharedPkg> retrieveSharedPkgs(EObject object_p) {
    List<EObject> objects = object_p.eContents();
    List<SharedPkg> sharedPkgs = new ArrayList<SharedPkg>();

    for (EObject object : objects) {
      if (object instanceof SharedPkg) {
        SharedPkg sharedPkg = (SharedPkg) object;
        sharedPkgs.add(sharedPkg);
      }
    }
    return sharedPkgs;
  }

  public static List<SharedPkg> retrieveSharedPkgsOfElement(EObject object_p) {
    SystemEngineering systemEngineering = retrieveSystemEngineering(object_p);
    return retrieveSharedPkgs(systemEngineering.eContainer());
  }

  /**
   * Retrieves the transformed elements by a specified transformation.
   * @param object_p The element to be queried
   * @param transfo_p The transformation
   * @param expectedTarget_p
   * @return The transformed element
   */
  public static List<? extends EObject> retrieveSourceElements(EObject targetElement_p, ITransfo transfo_p, EClass expectedTarget_p) {
    List<EObject> results = new ArrayList<EObject>();

    if (targetElement_p instanceof CapellaElement) {
      CapellaElement element = (CapellaElement) targetElement_p;

      try {
        // load rules for the given element if wasn't loaded to load all related transfo link (FunctionalRealization, PortRealization etc)
        transfo_p.findCachedMatchingRule(targetElement_p);
      } catch (TransfoException exception_p) {
        // Nothing to do
      }

      List<AbstractTrace> traceList = getOutgoingTraces(element, transfo_p);
      for (AbstractTrace trace : traceList) {
        if (isLinkOfTransfo(trace, transfo_p)) {
          TraceableElement srcElement = trace.getTargetElement();
          if ((expectedTarget_p == null) || EcoreUtil2.isEqualOrSuperClass(expectedTarget_p, srcElement.eClass())) {
            results.add(srcElement);
          }
        }
      }
    }
    return results;
  }

  /**
   * Retrieves the system engineering from a model element
   * @param object_p The model element
   * @return The system engineering
   */
  protected static SystemEngineering retrieveSystemEngineering(EObject object_p) {
    EObject currentElement = object_p.eContainer();
    while (currentElement != null) {
      if (currentElement instanceof SystemEngineering) {
        return (SystemEngineering) currentElement;
      }
      currentElement = currentElement.eContainer();
    }

    return null;
  }

  /**
   * Retrieves the transformed elements by a specified transformation. USE IT CAREFULLY! (we can be sure that the given object_p is transitioned one2one only
   * when we have created it into the transition, otherwise, the user can have made some bad changes with transfoLinks)
   * @param object_p The element to be queried
   * @param transfo_p The transformation
   * @return The transformed element
   */
  public static Object retrieveTransformedElement(EObject object_p, ITransfo transfo_p) {
    return retrieveTransformedElement(object_p, transfo_p, null);
  }

  /**
   * Retrieves the transformed elements by a specified transformation. USE IT CAREFULLY! (we can be sure that the given object_p is transitioned one2one only
   * when we have created it into the transition, otherwise, the user can have made some bad changes with transfoLinks)
   * @param object_p The element to be queried
   * @param transfo_p The transformation
   * @param expectedTarget_p
   * @return The transformed element
   */
  public static Object retrieveTransformedElement(EObject object_p, ITransfo transfo_p, EClass expectedTarget_p) {
    List<EObject> result = new ArrayList<EObject>();
    if (object_p instanceof CapellaElement) {
      CapellaElement element = (CapellaElement) object_p;

      try {
        // load rules for the given element if wasn't loaded to load all related transfo link (FunctionalRealization, PortRealization etc)
        transfo_p.findCachedMatchingRule(object_p);
      } catch (TransfoException exception_p) {
        // Nothing to do
      }

      List<AbstractTrace> traceList = getIncomingTraces(element, transfo_p);
      for (AbstractTrace trace : traceList) {
        if (isLinkOfTransfo(trace, transfo_p)) {
          TraceableElement srcElement = trace.getSourceElement();
          if ((expectedTarget_p == null) || EcoreUtil2.isEqualOrSuperClass(expectedTarget_p, srcElement.eClass())) {
            result.add(srcElement);
          }
        }
      }
    }
    if (result.size() == 0) {
      return null;
    }
    if (result.size() == 1) {
      return result.get(0);
    }
    return result;
  }

  /**
   * Retrieves the transformed elements by a specified transformation.
   * @param object_p The element to be queried
   * @param transfo_p The transformation
   * @param expectedTarget_p
   * @return The transformed element
   */
  public static List<? extends EObject> retrieveTransformedElements(EObject object_p, ITransfo transfo_p) {
    return retrieveTransformedElements(object_p, transfo_p, null);
  }

  /**
   * Retrieves the transformed elements by a specified transformation.
   * @param object_p The element to be queried
   * @param transfo_p The transformation
   * @param expectedTarget_p
   * @return The transformed element
   */
  public static List<? extends EObject> retrieveTransformedElements(EObject object_p, ITransfo transfo_p, EClass expectedTarget_p) {
    List<EObject> results = new ArrayList<EObject>();

    if ((object_p != null) && (object_p instanceof CapellaElement)) {
      CapellaElement element = (CapellaElement) object_p;

      try {
        // load rules for the given element if wasn't loaded to load all related transfo link (FunctionalRealization, PortRealization etc)
        transfo_p.findCachedMatchingRule(object_p);
      } catch (TransfoException exception_p) {
        // Nothing to do
      }

      List<AbstractTrace> traceList = getIncomingTraces(element, transfo_p);
      for (AbstractTrace trace : traceList) {
        if (isLinkOfTransfo(trace, transfo_p)) {
          TraceableElement srcElement = trace.getSourceElement();
          if ((expectedTarget_p == null) || EcoreUtil2.isEqualOrSuperClass(expectedTarget_p, srcElement.eClass())) {
            results.add(srcElement);
          }
        }
      }
    }
    return results;
  }

  /**
   * Retrieves the transformed elements by a specified transformation.
   * @param object_p The element to be queried
   * @param transfo_p The transformation
   * @param expectedTarget_p
   * @return The transformed element
   */
  public static List<? extends EObject> retrieveUnattachedTransformedElements(EObject object_p, ITransfo transfo_p, EClass expectedTarget_p) {
    List<EObject> result = new ArrayList<EObject>();
    if (object_p instanceof CapellaElement) {
      CapellaElement element = (CapellaElement) object_p;

      try {
        // load rules for the given element if wasn't loaded to load all related transfo link (FunctionalRealization, PortRealization etc)
        transfo_p.findCachedMatchingRule(object_p);
      } catch (TransfoException exception_p) {
        // Nothing to do
      }

      List<AbstractTrace> traceList = getIncomingTraces(element, transfo_p);
      for (AbstractTrace trace : traceList) {
        if (isLinkOfTransfo(trace, transfo_p)) {
          TraceableElement srcElement = trace.getSourceElement();
          if ((expectedTarget_p == null) || EcoreUtil2.isEqualOrSuperClass(expectedTarget_p, srcElement.eClass())) {
            if (srcElement.eContainer() == null) {
              result.add(srcElement);
            }
          }
        }
      }
    }
    return result;
  }

}
