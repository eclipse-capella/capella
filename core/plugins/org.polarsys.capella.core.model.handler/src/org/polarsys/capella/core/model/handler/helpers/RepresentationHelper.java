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
package org.polarsys.capella.core.model.handler.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationDescriptorQuery;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.query.SiriusReferenceFinder;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.utils.RunnableWithBooleanResult;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;

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
  public static Collection<DRepresentation> getRepresentations(Collection<?> selection) {
    return getSelectedDescriptors(selection).stream().map(d -> d.getRepresentation()).collect(Collectors.toList());
  }

  /**
   * Get selected representations.
   * 
   * @param selectedElements
   *          A list of selected elements.
   * @return A not <code>null</code> (possibly empty) collection of representations.
   */
  public static Collection<DRepresentationDescriptor> getSelectedDescriptors(Collection<?> selectedElements) {
    return CapellaAdapterHelper.resolveEObjects(selectedElements).stream()
        .filter(x -> x instanceof DRepresentationDescriptor || x instanceof DRepresentation)
        .map(new Function<Object, DRepresentationDescriptor>() {
          @Override
          public DRepresentationDescriptor apply(Object t) {
            if (t instanceof DRepresentation) {
              return new DRepresentationQuery((DRepresentation) t).getRepresentationDescriptor();
            }
            return (DRepresentationDescriptor) t;
          }
        }).collect(Collectors.toList());
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

    // For a Component, we want to see representations related to its allocated functions.
    if (semanticElement instanceof Component) {
      semanticElementsToCheck.addAll(((Component) semanticElement).getAllocatedFunctions());
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
   * This method shall not be used to store annotation on diagram. Annotations shall be stored on
   * DRepresentationDescriptor instead
   * 
   * @use DAnnotationHelper.getAnnotation instead
   */
  @Deprecated
  public static DAnnotation getAnnotation(String source, DRepresentation representation) {
    for (DAnnotation annotation : representation.getEAnnotations()) {
      if (annotation.getSource() != null && annotation.getSource().equals(source)) {
        return annotation;
      }
    }
    return null;
  }

  /**
   * This method shall not be used to store annotation on diagram. Annotations shall be stored on
   * DRepresentationDescriptor instead
   * 
   * @use DAnnotationHelper.createAnnotation instead
   */
  @Deprecated
  public static DAnnotation createAnnotation(final String source, DRepresentation representation) {
    DAnnotation annotation = DescriptionFactory.eINSTANCE.createDAnnotation();
    annotation.setSource(source);
    representation.getEAnnotations().add(annotation);
    return annotation;
  }

  /**
   * This method shall not be used to store annotation on diagram. Annotations shall be stored on
   * DRepresentationDescriptor instead
   * 
   * @use DAnnotationHelper.removeAnnotation instead
   */
  @Deprecated
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
  public static Collection<Resource> getSemanticResources(DRepresentationDescriptor representation) {

    if (representation != null) {
      EObject root = representation.getTarget();
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
    return new DRepresentationQuery(representation).getRepresentationDescriptor();
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
    return new DRepresentationQuery(representation).getRepresentationDescriptor();
  }

  /**
   * Get the representation descriptor whose UID or repPath equals to the parameter id 
   * or the semantic object with the given id
   */
  public static EObject getRepresentationDescriptorOrSemanticObject(ResourceSet rSet, String id) {
    IScope scope = new SemanticResourcesScope(rSet);
    EObject eObject = IdManager.getInstance().getEObject(id, scope);
    if (eObject == null) {
      return RepresentationHelper.getRepresentationDescriptor(rSet, id);
    }
    return eObject;
  }

  /**
   * Get the representation descriptor whose UID or repPath equals to the parameter id.
   */
  public static DRepresentationDescriptor getRepresentationDescriptor(ResourceSet resourceSet, String id) {
    IScope capellaSemanticResourceScope = new SemanticResourcesScope(resourceSet);
    List<Resource> capellaSemanticResources = capellaSemanticResourceScope.getResources();
    Resource resource = capellaSemanticResources.stream().findFirst().orElse(null);
    Session session = SessionManager.INSTANCE.getSession(resource);
    Collection<DRepresentationDescriptor> representationDescriptors = DialectManager.INSTANCE
        .getAllRepresentationDescriptors(session);
    for (DRepresentationDescriptor representationDescriptor : representationDescriptors) {

      String descriptorFragment;
      try {
        descriptorFragment = representationDescriptor.getRepPath().getResourceURI().fragment();
      } catch (NullPointerException e) {
        descriptorFragment = "";
      }

      String descriptorUid = representationDescriptor.getUid();

      if (id.equals(descriptorFragment) || id.equals(descriptorUid)) {
        return representationDescriptor;
      }
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

  /**
   * Returns all representation descriptors containing an annotation referencing at least one element of the given list
   */
  public static Collection<DRepresentationDescriptor> getAllRepresentationDescriptorsAnnotatedBy(
      List<EObject> objects) {
    Collection<DRepresentationDescriptor> result = new ArrayList<DRepresentationDescriptor>();
    if (!objects.isEmpty()) {
      Session session = SessionManager.INSTANCE.getSession(objects.iterator().next());
      if (session != null) {
        for (DRepresentationDescriptor descriptor : DialectManager.INSTANCE.getAllRepresentationDescriptors(session)) {
          for (DAnnotation annotation : descriptor.getEAnnotations()) {
            if (!Collections.disjoint(annotation.getReferences(), objects)) {
              result.add(descriptor);
              break;
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * Returns whether the given descriptor is valid
   * 
   * @see org.eclipse.sirius.business.api.query.DRepresentationDescriptorQuery.isRepresentationValid
   */
  public static boolean isValid(DRepresentationDescriptor descriptor) {
    return new DRepresentationDescriptorQuery(descriptor).isRepresentationValid();
  }

  public static String getRepresentationStatusText(DRepresentationDescriptor element) {
    String result = ICommonConstants.EMPTY_STRING;

    if (!new DRepresentationDescriptorQuery(element).isRepresentationValid()) {
      return "(Invalid)";
    } else if (!element.isLoadedRepresentation()) {
      result = "(Not Loaded)";
    } else {
      DRepresentation representation = element.getRepresentation();
      if (representation instanceof DDiagram) {
        if (((DDiagram) representation).isSynchronized()) {
          result = "(Synchronized)";
        } else {
          result = "(Unsynchronized)";
        }
      }
    }
    return result;
  }

  public static String getRepresentationFullPathText(DRepresentationDescriptor descriptor) {
    String fullPathText = "";
    EObject semanticElement = descriptor.getTarget();

    if (semanticElement != null) {
      fullPathText += EObjectLabelProviderHelper.getFullPathText(semanticElement);
    }

    fullPathText += EObjectLabelProviderHelper.FULL_PATH_SEPARATOR + descriptor.getName() + " "
        + RepresentationHelper.getRepresentationStatusText(descriptor);
    return fullPathText;
  }
       
  /**
   * Sets the target for the representation and its descriptor.
   * @param representationDescriptor the representation descriptor.
   * @param representation the representation.
   * @param target the new target.
   */
  public static void setTarget(DRepresentationDescriptor representationDescriptor, DRepresentation representation, EObject target) {            
    if (representation instanceof DSemanticDecorator) {      
      ((DSemanticDecorator) representation).setTarget(target);
      representationDescriptor.setTarget(target);
    }    
  }

  /**
   * Sets the target for the representation and synchronizes it with its descriptor.
   * @param representation the representation.
   * @param target the new target.
   */
  public static void setTarget(DRepresentation representation, EObject target) {
    setTarget(getRepresentationDescriptor(representation), representation, target);
  }

  /**
   * Sets the target for the representation descriptor and synchronizes it with its representation.
   * @param representationDescriptor the representation.
   * @param target the new target.
   */
  public static void setTarget(DRepresentationDescriptor representationDescriptor, EObject target) {
    setTarget(representationDescriptor, representationDescriptor.getRepresentation(), target);
  }
  
}
