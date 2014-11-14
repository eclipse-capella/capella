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
package org.polarsys.capella.core.model.handler.helpers;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.utils.RunnableWithBooleanResult;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 * Sirius representations helper.
 */
public class RepresentationHelper {

  /**
   * Get all representation targeted by specified semantic elements.<br>
   * Default implementation loops over specified elements and search for all representations in a specified element containment subtree.
   * @return a not <code>null</code> collection.
   */
  public static Collection<DRepresentation> getAllRepresentationsTargetedBy(Collection<?> elements_p) {
    HashSet<DRepresentation> representations = new HashSet<DRepresentation>(0);
    for (Object o : elements_p) {
      if (o instanceof EObject) {
        EObject currentElement = (EObject) o;
        Session session = SessionManager.INSTANCE.getSession(currentElement);
        if (session != null) { // can happen during tests
          representations.addAll(DialectManager.INSTANCE.getRepresentations(currentElement, session));
        }
        // Add the element subtree.
        TreeIterator<EObject> allChildrenOfCurrentElement = currentElement.eAllContents();
        while (allChildrenOfCurrentElement.hasNext()) {
          EObject child = allChildrenOfCurrentElement.next();
          session = SessionManager.INSTANCE.getSession(child);
          if (session != null) { // can happen during tests
            representations.addAll(DialectManager.INSTANCE.getRepresentations(child, session));
          }
        }
      }
    }
    return representations;
  }

  /**
   * Get all representations where specified semantic element is displayed.
   * @param semanticElement_p
   * @param filteringRepresentationDescriptionClass_p
   * @return a not <code>null</code> collection.
   */
  public static Collection<DRepresentation> getAllRepresentationsWhereSemanticElementIsDisplayed(EObject semanticElement_p,
      RunnableWithBooleanResult filteringCondition_p) {
    Set<DRepresentation> result = new HashSet<DRepresentation>(0);
    Session session = SessionManager.INSTANCE.getSession(semanticElement_p);
    if (null == session) {
      return result;
    }
    for (Viewpoint currentSelectedViewpoint : session.getSelectedViewpoints(false)) {
      for (RepresentationDescription representationDescription : currentSelectedViewpoint.getOwnedRepresentations()) {
        boolean search = true;
        // If a condition is given, used to filter out or not current representation description.
        if (null != filteringCondition_p) {
          filteringCondition_p.setObject(representationDescription);
          filteringCondition_p.run();
          search = filteringCondition_p.getResult().booleanValue();
        }
        if (search) {
          // Get all representations for current representation description.
          Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(representationDescription, session);
          // Loop over representations, to search the ones that contain our model element.
          for (DRepresentation representation : representations) {
            boolean flag = false;
            // add to result if the target of a representation is equal to semantic element
            //
            if (representation instanceof DSemanticDiagram) {
              // get Representation target
              EObject diagramTarget = ((DSemanticDiagram) representation).getTarget();
              if (null != diagramTarget) {
                if (diagramTarget.equals(semanticElement_p)) {
                  if (!result.contains(representation)) {
                    flag = true;
                    result.add(representation);
                  }
                }
              }
            } else if (representation instanceof DTable) {
              // get Representation target
              EObject tableTarget = ((DTable) representation).getTarget();
              if (null != tableTarget) {
                if (tableTarget.equals(semanticElement_p)) {
                  if (!result.contains(representation)) {
                    flag = true;
                    result.add(representation);
                  }
                }
              }
            }
            if (!flag) {
              for (DRepresentationElement representationElement : representation.getRepresentationElements()) {
                EObject target = representationElement.getTarget();
                if (semanticElement_p.equals(target)) {
                  // Current representation contains our semantic element.
                  // Add it in resulting set, break current loop to search for next representation.
                  result.add(representation);
                  break;
                }
                // PART (looking for abstractType)
                else if (target instanceof Part) {
                  EReference eReference = ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE;
                  if (isSemanticElementEqualToTargetReferencedElement(target, semanticElement_p, eReference, false)) {
                    result.add(representation);
                    break;
                  }
                }
                // SEQUENCE MESSAGE (Looking for invokedOperation)
                else if (target instanceof SequenceMessage) {
                  EReference eReference = InteractionPackage.Literals.SEQUENCE_MESSAGE__INVOKED_OPERATION;
                  if (isSemanticElementEqualToTargetReferencedElement(target, semanticElement_p, eReference, false)) {
                    result.add(representation);
                    break;
                  }
                }
                // INSTANCE ROLE (Looking for represented Instance)
                else if (target instanceof InstanceRole) {
                  EReference eReference = InteractionPackage.Literals.INSTANCE_ROLE__REPRESENTED_INSTANCE;
                  if (isSemanticElementEqualToTargetReferencedElement(target, semanticElement_p, eReference, true)) {
                    result.add(representation);
                    break;
                  }
                }
                // STATE FRAGMENT (looking for related abstract function and related state)
                else if (target instanceof StateFragment) {
                  EReference eReference = InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_STATE;
                  EReference eReference2 = InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION;
                  if (isSemanticElementEqualToTargetReferencedElement(target, semanticElement_p, eReference, true)
                      || isSemanticElementEqualToTargetReferencedElement(target, semanticElement_p, eReference2, true)) {
                    result.add(representation);
                    break;
                  }
                }
                // Functional Chain Involvement (looking for related involved element)
                else if (target instanceof FunctionalChainInvolvement) {
                  EReference eReference = CapellacorePackage.Literals.INVOLVEMENT__INVOLVED;
                  if (isSemanticElementEqualToTargetReferencedElement(target, semanticElement_p, eReference, false)) {
                    result.add(representation);
                    break;
                  }
                }
                // Physical Path Involvement (looking for related involved element) (involved element could be part)
                else if (target instanceof PhysicalPathInvolvement) {
                  EReference eReference = CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__INVOLVED_ELEMENT;
                  // consider part abstract Type
                  if (isSemanticElementEqualToTargetReferencedElement(target, semanticElement_p, eReference, true)) {
                    result.add(representation);
                    break;
                  }
                }
                // ABSTRACT ACTOR
                // For an AbstractActor consider all the allocatedFunctions as SemanticElements
                // ? do consider other component
                if (semanticElement_p instanceof AbstractActor) {
                  //
                  AbstractActor actor = (AbstractActor) semanticElement_p;
                  EList<AbstractFunction> allocatedFunctions = actor.getAllocatedFunctions();
                  boolean found = false;
                  for (AbstractFunction abstractFunction : allocatedFunctions) {
                    if (abstractFunction.equals(target)) {
                      // Current representation contains our semantic element.
                      // Add it in resulting set, break current loop to search for next representation.
                      result.add(representation);
                      found = true;
                      break;
                    }
                  }
                  if (found) {
                    // jump back to new diagram iteration
                    break;
                  }
                }

              }
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * Return true if Referenced Element of target is equal to the given semantiqueElement
   * @param target_p
   * @param semanticElement_p
   * @param abstractTypeRef_p
   */
  private static boolean isSemanticElementEqualToTargetReferencedElement(EObject target_p, EObject semanticElement_p, EReference abstractTypeRef_p,
      boolean considerPartAbstractType_p) {
    Object eGet = target_p.eGet(abstractTypeRef_p);
    // assuming the reference return single object
    if ((null != eGet) && !abstractTypeRef_p.isMany()) {
      if (semanticElement_p.equals(eGet)) {
        return true;
      } else if (considerPartAbstractType_p) {
        if (eGet instanceof Part) {
          AbstractType abstractType = ((Part) eGet).getAbstractType();
          if ((null != abstractType) && abstractType.equals(semanticElement_p)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Collect all resources where specified element is involved.
   * @param semanticRoot_p
   * @return
   */
  public static Collection<Resource> collectDependentResources(EObject semanticRoot_p) {
    Collection<Resource> resources = new HashSet<Resource>(0);
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
   * Returns all related capella resources
   */
  @SuppressWarnings("unchecked")
  public static Collection<Resource> getSemanticResources(DRepresentation representation_p) {

    if ((representation_p != null) && (representation_p instanceof DSemanticDecorator)) {
      EObject root = ((DSemanticDecorator) representation_p).getTarget();
      // if session is opened, return all defined resources from session
      if (root != null) {
        Collection<Resource> resources = new HashSet<Resource>();
        Session session = SessionManager.INSTANCE.getSession(root);

        if (session != null) {
          Collection<Resource> sessionResources = new HashSet<Resource>();
          if (session.getSemanticResources() != null) {
            sessionResources.addAll(session.getSemanticResources());
          }
          if (session.getReferencedSessionResources() != null) {
            sessionResources.addAll(session.getReferencedSessionResources());
          }

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
                          if ((model.eResource() != null)) {
                            resources.add(model.eResource());
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
