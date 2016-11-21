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
package org.polarsys.capella.core.model.handler.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.utils.RunnableWithBooleanResult;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

import com.google.common.collect.Iterables;

/**
 * Sirius representations helper.
 */
public class RepresentationHelper {

  /**
   * Get all representation targeted by specified semantic elements.<br>
   * Default implementation loops over specified elements and search for all representations in a specified element containment subtree.
   * @return a not <code>null</code> collection.
   */
  public static Collection<DRepresentation> getAllRepresentationsTargetedBy(Collection<?> semanticElements_p) {
    Set<DRepresentation> representations = new HashSet<DRepresentation>();
    // Go through EObjects only.
    Iterable<EObject> semanticEObjects = Iterables.filter(semanticElements_p, EObject.class);
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
   * Get all representations where the specified semantic element appears. This method is recursive and get also representation of some related elements.
   * @param semanticElement_p
   * @param representations
   */
  private static void getRelatedRepresentations(EObject semanticElement_p, Set<DRepresentation> representations) {
    Session session = SessionManager.INSTANCE.getSession(semanticElement_p);

    List<EObject> semanticElementsToCheck = new ArrayList<EObject>();
    semanticElementsToCheck.add(semanticElement_p);
    // For an AbstractActor, we want to see representations related to its allocated functions.
    if (semanticElement_p instanceof AbstractActor) {
      semanticElementsToCheck.addAll(((AbstractActor) semanticElement_p).getAllocatedFunctions());
    }

    for (EObject semanticElementToCheck : semanticElementsToCheck) {
      for (Setting setting : session.getSemanticCrossReferencer().getInverseReferences(semanticElementToCheck)) {
        // Get DRepresentation related to semanticElement.
        if (ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET.equals(setting.getEStructuralFeature())) {
          DSemanticDecorator decorator = (DSemanticDecorator) setting.getEObject();
          // TODO Use DiagramHelper.getService().getRepresentation(decorator) (not possible currently because of a cycle in dependencies).
          DRepresentation diagram = null;
          if (decorator instanceof DRepresentation) {
            // DRepresentation is referencing directly to the semantic element.
            diagram = (DRepresentation) decorator;
          } else {
            // An internal element of a representation is referencing the semantic element -> get containing representation.
            diagram = (DRepresentation) EcoreUtil2.getFirstContainer(decorator, ViewpointPackage.Literals.DREPRESENTATION);
          }
          if (null != diagram) {
            // Will be added only if not already present (it's a Set).
            representations.add(diagram);
          }
        } else if (ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE.equals(setting.getEStructuralFeature())
                   || InteractionPackage.Literals.EVENT_RECEIPT_OPERATION__OPERATION.equals(setting.getEStructuralFeature())
                   || InteractionPackage.Literals.ABSTRACT_END__EVENT.equals(setting.getEStructuralFeature())
                   || InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END.equals(setting.getEStructuralFeature())
                   || InteractionPackage.Literals.EVENT_SENT_OPERATION__OPERATION.equals(setting.getEStructuralFeature())
                   || InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_END.equals(setting.getEStructuralFeature())
                   || InteractionPackage.Literals.INSTANCE_ROLE__REPRESENTED_INSTANCE.equals(setting.getEStructuralFeature())
                   || InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_STATE.equals(setting.getEStructuralFeature())
                   || InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION.equals(setting.getEStructuralFeature())
                   || CapellacorePackage.Literals.INVOLVEMENT__INVOLVED.equals(setting.getEStructuralFeature())) {
          // Get representations associated to another semantic element referencing the given semantic element.
          getRelatedRepresentations(setting.getEObject(), representations);
        }
      }
    }
  }

  /**
   * Get all representations where specified semantic element is displayed.
   * @param semanticElement_p
   * @param filteringRepresentationDescriptionClass_p
   * @return a not <code>null</code> collection.
   */
  public static Collection<DRepresentation> getAllRepresentationsWhereSemanticElementIsDisplayed(EObject semanticElement_p,
      RunnableWithBooleanResult filteringCondition_p) {
    // Precondition: we must have a Session.
    Session session = SessionManager.INSTANCE.getSession(semanticElement_p);
    if (null == session) {
      return Collections.emptySet();
    }
    // Collect all representations related to the given semantic elements and some other related ones (following specific kind of references).
    Set<DRepresentation> unfilteredRepresentations = new HashSet<DRepresentation>();
    getRelatedRepresentations(semanticElement_p, unfilteredRepresentations);

    // Do some filtering.
    Set<DRepresentation> filteredRepresentations = new HashSet<DRepresentation>();
    Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);
    // Go through representations.
    for (DRepresentation representation : unfilteredRepresentations) {
      RepresentationDescription representationDescription = DialectManager.INSTANCE.getDescription(representation);
      // Keep only representations having their RepresentationDescription in selectedViewpoints.
      Viewpoint parentViewpoint = new RepresentationDescriptionQuery(representationDescription).getParentViewpoint();
      if (selectedViewpoints.contains(parentViewpoint)) {
        if (null == filteringCondition_p) {
          filteredRepresentations.add(representation);
        } else {
          // If a condition is given, use it to filter out or not current representation regarding its description.
          filteringCondition_p.setObject(representationDescription);
          filteringCondition_p.run();
          if (filteringCondition_p.getResult().booleanValue()) {
            filteredRepresentations.add(representation);
          }
        }
      }
    }

    return filteredRepresentations;
  }

  /**
   * Collect all resources where specified element is involved.
   * @param semanticRoot_p
   * @return
   */
  public static Collection<Resource> collectDependentResources(EObject semanticRoot_p) {
    Collection<Resource> resources = new HashSet<Resource>();
    // Find all elements that reference semantic root.
    for (EObject referencingElement : CrossReferencerHelper.getReferencingElements(semanticRoot_p)) {
      resources.add(referencingElement.eResource());
    }
    // Get all representations that reference semantic root.
    for (DRepresentation representation : getAllRepresentationsWhereSemanticElementIsDisplayed(semanticRoot_p, null)) {
      resources.add(representation.eResource());
    }
    // Loop over semantic root sub tree to collect all dependent resources.
    Resource semanticRootResource = semanticRoot_p.eResource();
    TreeIterator<EObject> semanticRootEAllContents = semanticRoot_p.eAllContents();
    while (semanticRootEAllContents.hasNext()) {
      EObject child = semanticRootEAllContents.next();
      // Exclude child contained in its own resource (e.g sub fragments).
      Resource childResource = EcoreUtil2.getResourceContainer(child).eResource();
      if (semanticRootResource.equals(childResource)) {
        // Find all elements that reference current child.
        for (EObject referencingElement : CrossReferencerHelper.getReferencingElements(child)) {
          resources.add(referencingElement.eResource());
        }
        // Get all representations that reference current child.
        for (DRepresentation representation : getAllRepresentationsWhereSemanticElementIsDisplayed(child, null)) {
          resources.add(representation.eResource());
        }
      }
    }
    return resources;
  }

  /**
   * @param source_p
   * @param representation_p
   */
  public static DAnnotation getAnnotation(String source_p, DRepresentation representation_p) {
    for (DAnnotation annotation : representation_p.getEAnnotations()) {
      if (annotation.getSource().equals(source_p)) {
        return annotation;
      }
    }
    return null;
  }

  /**
   * @param source_p
   * @param representation_p
   */
  public static DAnnotation createAnnotation(final String source_p, DRepresentation representation_p) {
    DAnnotation annotation = DescriptionFactory.eINSTANCE.createDAnnotation();
    annotation.setSource(source_p);
    representation_p.getEAnnotations().add(annotation);
    return annotation;
  }

  /**
   * @param source_p
   * @param representation_p
   */
  public static void removeAnnotation(String source_p, DRepresentation representation_p) {
    DAnnotation annotation = getAnnotation(source_p, representation_p);
    if (null != annotation) {
      representation_p.getEAnnotations().remove(annotation);
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
}
