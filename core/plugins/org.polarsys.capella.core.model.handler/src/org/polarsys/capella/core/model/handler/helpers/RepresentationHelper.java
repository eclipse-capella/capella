/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.handler.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.query.SiriusReferenceFinder;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.utils.RunnableWithBooleanResult;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

import com.google.common.collect.Iterables;

/**
 * Sirius representations helper.
 */
public class RepresentationHelper {

  /**
   * Same as {@link #getRepresentations(Collection, boolean)} with {@code mustBeDecorator=false}
   *
   * @param selection
   * @return
   */
  public static List<DRepresentation> getRepresentations(Collection<?> selection) {
    return getRepresentations(selection, false);
  }

  /**
   * Filters representations.
   *
   * @param selection
   * @param mustBeDecorator
   *          representation must be an instance of {@link DSemanticDecorator}
   *
   * @return
   */
  public static List<DRepresentation> getRepresentations(Collection<?> selection, boolean mustBeDecorator) {

    List<DRepresentation> representations = new ArrayList<DRepresentation>();
    Iterator<?> iterator = selection.iterator();
    while (iterator.hasNext()) {
      Object selectedObject = iterator.next();
      // We don't manage ItemWrapper here to avoid ui dependency...
      // if (selectedObject instanceof ItemWrapper) {
      // selectedObject = ((ItemWrapper)
      // selectedObject).getWrappedObject();
      // }

      if (selectedObject instanceof DRepresentation) {
        addRepresentation(mustBeDecorator, representations, (DRepresentation) selectedObject);
      }
      if (selectedObject instanceof DRepresentationDescriptor) {
        addRepresentation(mustBeDecorator, representations,
            ((DRepresentationDescriptor) selectedObject).getRepresentation());
      }
    }
    return representations;
  }

  private static void addRepresentation(boolean mustBeDecorator, List<DRepresentation> representations,
      DRepresentation selectedObject) {
    if (mustBeDecorator) {
      if (selectedObject instanceof DSemanticDecorator) {
        representations.add(selectedObject);
      }
    } else {
      representations.add(selectedObject);
    }
  }

  /**
   * Get all representation targeted by specified semantic elements.<br>
   * Default implementation loops over specified elements and search for all representations in a specified element
   * containment subtree.
   *
   * @return a not <code>null</code> collection.
   */
  public static Collection<DRepresentation> getAllRepresentationsTargetedBy(Collection<?> semanticElements) {
    Set<DRepresentation> representations = new HashSet<DRepresentation>();
    // Go through EObjects only.
    Iterable<EObject> semanticEObjects = Iterables.filter(semanticElements, EObject.class);
    for (EObject semanticEObject : semanticEObjects) {
      Session session = SessionManager.INSTANCE.getSession(semanticEObject);
      if (session != null) { // can happen during tests
        representations.addAll(DialectManager.INSTANCE.getRepresentations(semanticEObject, session));
        // Go trough element's subtree (sub elements have the same session as their parent).
        TreeIterator<EObject> allChildrenOfCurrentElement = semanticEObject.eAllContents();

        while (allChildrenOfCurrentElement.hasNext()) {
          EObject child = allChildrenOfCurrentElement.next();

          representations.addAll(DialectManager.INSTANCE.getRepresentations(child, session));
        }
      }
    }

    return representations;
  }

  /**
   * Get all representation descriptors targeted by specified semantic elements.<br>
   * Default implementation loops over specified elements and search for all representation descriptorss in a specified
   * element containment subtree.
   *
   * @return a not <code>null</code> collection.
   */
  public static Collection<DRepresentationDescriptor> getAllRepresentationDescriptorsTargetedBy(
      Collection<?> semanticElements) {
    Set<DRepresentationDescriptor> representations = new HashSet<DRepresentationDescriptor>();
    // Go through EObjects only.
    Iterable<EObject> semanticEObjects = Iterables.filter(semanticElements, EObject.class);
    for (EObject semanticEObject : semanticEObjects) {
      Session session = SessionManager.INSTANCE.getSession(semanticEObject);
      if (session != null) { // can happen during tests
        representations.addAll(DialectManager.INSTANCE.getRepresentationDescriptors(semanticEObject, session));
        // Go trough element's subtree (sub elements have the same session as their parent).
        TreeIterator<EObject> allChildrenOfCurrentElement = semanticEObject.eAllContents();

        while (allChildrenOfCurrentElement != null && allChildrenOfCurrentElement.hasNext()) {
          EObject child = allChildrenOfCurrentElement.next();

          representations.addAll(DialectManager.INSTANCE.getRepresentationDescriptors(child, session));
        }
      }
    }

    return representations;
  }

  /**
   * Get all representations where the specified semantic element appears.
   * 
   * @param semanticElement
   * @param representationDescriptors
   */
  private static void getRelatedRepresentations(EObject semanticElement,
      Set<DRepresentationDescriptor> representationDescriptors) {
    // Use Set instead of list to avoid dulicate
    Set<EObject> semanticElementsToCheck = new HashSet<>();

    // The semantic element itself needs to be checked
    semanticElementsToCheck.add(semanticElement);

    // For an AbstractActor, we want to see representations related to its allocated functions.
    if (semanticElement instanceof AbstractActor) {
      semanticElementsToCheck.addAll(((AbstractActor) semanticElement).getAllocatedFunctions());
    }

    Session session = SessionManager.INSTANCE.getSession(semanticElement);
    Map<EObject, Collection<DRepresentationDescriptor>> impactedRepresentationDescriptors = ((DAnalysisSessionImpl) session)
        .getSiriusReferenceFinder().getImpactedRepresentationDescriptors(semanticElementsToCheck,
            SiriusReferenceFinder.SearchScope.ALL_REPRESENTATIONS_SCOPE);

    for (Entry<EObject, Collection<DRepresentationDescriptor>> entry : impactedRepresentationDescriptors.entrySet()) {
      Collection<DRepresentationDescriptor> impactedRepDescs = entry.getValue();
      if (!impactedRepDescs.isEmpty()) {
        representationDescriptors.addAll(impactedRepDescs);
      }
    }
  }

  /**
   * Get all representations where specified semantic element is displayed.
   * 
   * @deprecated use getAllRepresentationDescriptorsWhereSemanticElementIsDisplayed instead because using
   *             {@link DRepresentationDescriptor} avoid loading the associated {@link DRepresentation} if not
   *             necessary.
   * @param semanticElement
   * @param filteringRepresentationDescriptionClass
   * @return a not <code>null</code> collection.
   */
  @Deprecated
  public static Collection<DRepresentation> getAllRepresentationsWhereSemanticElementIsDisplayed(
      EObject semanticElement, RunnableWithBooleanResult filteringCondition) {
    Collection<DRepresentation> reps = new ArrayList<>();

    Collection<DRepresentationDescriptor> repDescs = getAllRepresentationDescriptorsWhereSemanticElementIsDisplayed(
        semanticElement, filteringCondition);

    for (DRepresentationDescriptor dRepresentationDescriptor : repDescs) {
      reps.add(dRepresentationDescriptor.getRepresentation());
    }

    return reps;
  }

  /**
   * Get all representationDescriptors where specified semantic element is displayed.
   *
   * @param semanticElement
   * @param filteringRepresentationDescriptionClass
   * @return a not <code>null</code> collection.
   */
  public static Collection<DRepresentationDescriptor> getAllRepresentationDescriptorsWhereSemanticElementIsDisplayed(
      EObject semanticElement, RunnableWithBooleanResult filteringCondition) {
    // Precondition: we must have a Session.
    Session session = SessionManager.INSTANCE.getSession(semanticElement);
    if (null == session) {
      return Collections.emptySet();
    }
    // Collect all representations related to the given semantic elements and some other related ones (following
    // specific kind of references).
    Set<DRepresentationDescriptor> unfilteredRepresentationDescs = new HashSet<DRepresentationDescriptor>();
    getRelatedRepresentations(semanticElement, unfilteredRepresentationDescs);

    // Do some filtering.
    Set<DRepresentationDescriptor> filteredRepresentationDescs = new HashSet<DRepresentationDescriptor>();
    Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);
    // Go through representations.
    for (DRepresentationDescriptor representationDescriptor : unfilteredRepresentationDescs) {
      RepresentationDescription representationDescription = representationDescriptor.getDescription();
      // Keep only representations having their RepresentationDescription in selectedViewpoints.
      Viewpoint parentViewpoint = new RepresentationDescriptionQuery(representationDescription).getParentViewpoint();
      if (selectedViewpoints.contains(parentViewpoint)) {
        if (null == filteringCondition) {
          filteredRepresentationDescs.add(representationDescriptor);
        } else {
          // If a condition is given, use it to filter out or not current representation regarding its
          // description.
          filteringCondition.setObject(representationDescription);
          filteringCondition.run();
          if (filteringCondition.getResult().booleanValue()) {
            filteredRepresentationDescs.add(representationDescriptor);
          }
        }
      }
    }

    return filteredRepresentationDescs;
  }

  /**
   * Collect all resources where specified element is involved.
   *
   * @param semanticRoot
   * @return
   */
  public static Collection<Resource> collectDependentResources(EObject semanticRoot) {
    Collection<Resource> resources = new HashSet<Resource>();
    // Find all elements that reference semantic root.
    for (EObject referencingElement : CrossReferencerHelper.getReferencingElements(semanticRoot)) {
      resources.add(referencingElement.eResource());
    }
    // Get all representation descriptors that reference semantic root.
    for (DRepresentationDescriptor representationDescriptor : getAllRepresentationDescriptorsWhereSemanticElementIsDisplayed(
        semanticRoot, null)) {
      resources.add(representationDescriptor.eResource());
    }
    // Loop over semantic root sub tree to collect all dependent resources.
    Resource semanticRootResource = semanticRoot.eResource();
    TreeIterator<EObject> semanticRootEAllContents = semanticRoot.eAllContents();
    while (semanticRootEAllContents.hasNext()) {
      EObject child = semanticRootEAllContents.next();
      // Exclude child contained in its own resource (e.g sub fragments).
      Resource childResource = EcoreUtil2.getResourceContainer(child).eResource();
      if (semanticRootResource.equals(childResource)) {
        // Find all elements that reference current child.
        for (EObject referencingElement : CrossReferencerHelper.getReferencingElements(child)) {
          resources.add(referencingElement.eResource());
        }
        // Get all representation descriptors that reference current child.
        for (DRepresentationDescriptor representationDescriptor : getAllRepresentationDescriptorsWhereSemanticElementIsDisplayed(
            child, null)) {
          resources.add(representationDescriptor.eResource());
        }
      }
    }
    return resources;
  }

  /**
   * @param source
   * @param representation
   */
  public static DAnnotation getAnnotation(String source, DRepresentation representation) {
    for (DAnnotation annotation : representation.getEAnnotations()) {
      if (annotation.getSource().equals(source)) {
        return annotation;
      }
    }
    return null;
  }

  /**
   * @param source
   * @param representation
   */
  public static DAnnotation createAnnotation(final String source, DRepresentation representation) {
    DAnnotation annotation = DescriptionFactory.eINSTANCE.createDAnnotation();
    annotation.setSource(source);
    representation.getEAnnotations().add(annotation);
    return annotation;
  }

  /**
   * @param source
   * @param representation
   */
  public static void removeAnnotation(String source, DRepresentation representation) {
    DAnnotation annotation = getAnnotation(source, representation);
    if (null != annotation) {
      representation.getEAnnotations().remove(annotation);
    }
  }

  /**
   * Returns all related Capella resources
   */
  @SuppressWarnings("unchecked")
  public static Collection<Resource> getSemanticResources(DRepresentation representation) {

    if ((representation != null) && (representation instanceof DSemanticDecorator)) {
      EObject root = ((DSemanticDecorator) representation).getTarget();
      // if session is opened, return all defined resources from session
      if (root != null) {
        Collection<Resource> resources = new HashSet<Resource>();
        Session session = SessionManager.INSTANCE.getSession(root);

        if (session != null) {
          Collection<Resource> sessionResources = new HashSet<Resource>();
          // Add SemanticResources.
          Collection<Resource> sessionSemanticResources = session.getSemanticResources();
          if (null != sessionSemanticResources) {
            sessionResources.addAll(sessionSemanticResources);
          }
          // Add ReferencedSessionResources.
          Collection<Resource> referencedSessionResources = session.getReferencedSessionResources();
          if (null != referencedSessionResources) {
            sessionResources.addAll(referencedSessionResources);
          }
          // Go through found resources.
          for (Resource resource : sessionResources) {
            if (resource instanceof AirdResource) {
              AirdResource airdResource = (AirdResource) resource;
              if (airdResource.getContents() != null) {
                for (EObject rootAird : airdResource.getContents()) {
                  if (rootAird instanceof DAnalysis) {
                    DAnalysis analysis = (DAnalysis) rootAird;
                    if (analysis.getModels() != null) {
                      for (EObject model : analysis.getModels()) {
                        if ((model != null) && !(model.eIsProxy())) {
                          Resource modelResource = model.eResource();
                          if ((modelResource != null)) {
                            resources.add(modelResource);
                          }
                        }
                      }
                    }
                  }
                }
              }
            } else if (CapellaResourceHelper.isCapellaResource(resource)) {
              resources.add(resource);
            }
          }
          return resources;
        }
        return Collections.singletonList(root.eResource());
      }
    }
    // otherwise
    return Collections.EMPTY_LIST;
  }

  /**
   * Retrieves the descriptor related to the given representations
   *
   * @param session
   *          the Sirius session
   * @param representation
   *          the representation whose descriptor has to be retrieved
   * @return a possibly null descriptor related to the given representation
   *
   * @since Sirius 4.1: introduction of the representation descriptors
   */
  public static DRepresentationDescriptor getRepresentationDescriptor(Session session, DRepresentation representation) {
    if (representation != null && session != null) {
      RepresentationDescription description = DialectManager.INSTANCE.getDescription(representation);
      for (DRepresentationDescriptor descriptor : DialectManager.INSTANCE.getRepresentationDescriptors(description,
          session)) {
        if (representation.equals(descriptor.getRepresentation())) {
          return descriptor;
        }
      }
    }
    return null;
  }

  /**
   * Retrieves the descriptor related to the given representations
   *
   * @param representation
   *          the representation whose descriptor has to be retrieved
   * @return a possibly null descriptor related to the given representation
   *
   * @since Sirius 4.1: introduction of the representation descriptors
   */
  public static DRepresentationDescriptor getRepresentationDescriptor(DRepresentation representation) {
    if (representation instanceof DSemanticDecorator) {
      EObject target = ((DSemanticDecorator) representation).getTarget();
      Session session = SessionManager.INSTANCE.getSession(target);

      return getRepresentationDescriptor(session, representation);
    }
    return null;
  }

  /**
   * Retrieves all the descriptors related to the given representations
   *
   * @param representations
   *          the representations whose descriptors have to be retrieved
   * @return a possibly empty collection of descriptors related to the given representations
   *
   * @since Sirius 4.1: introduction of the representation descriptors
   */
  public static Collection<DRepresentationDescriptor> getRepresentationDescriptors(
      Collection<DRepresentation> representations) {
    Collection<DRepresentationDescriptor> result = new HashSet<DRepresentationDescriptor>();
    for (DRepresentation representation : representations) {
      DRepresentationDescriptor descriptor = getRepresentationDescriptor(representation);
      if (descriptor != null) {
        result.add(descriptor);
      }
    }
    return result;
  }
}
