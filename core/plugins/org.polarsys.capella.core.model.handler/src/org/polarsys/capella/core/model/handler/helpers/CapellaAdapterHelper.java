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

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Adapters;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.query.IllegalStateExceptionQuery;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.kitalpha.emde.model.Element;

/**
 *
 */
public class CapellaAdapterHelper {

  /**
   * Retrieve the related EObject (it is equivalent to resolveEObject(object, false, false))
   * 
   * - If the object is an EObject or can be adapted to EObject, return it. - For a graphical element (EditPart),
   * retrieve the related Sirius EObject (DDiagramElement for instance)
   * 
   * @param object
   *          must not be a list
   */
  public static EObject resolveEObject(Object object) {
    return resolveEObject(object, false, false);
  }

  /**
   * Retrieve the related Semantic element (ie EObject from a model, not a diagram) (it is equivalent to
   * resolveEObject(object, true, false))
   * 
   * - If the object is an Element or can be adapted to Element, return it. - If the object is a graphical element
   * (EditPart) or a Sirius element, returns DSemanticDecorator.getTarget, or DRepresentationDescriptor.getTarget
   */
  public static EObject resolveSemanticObject(Object object) {
    return resolveEObject(object, true, false);
  }

  /**
   * Retrieve the user semantic element related to the given object (it is equivalent to resolveEObject(object, true,
   * true))
   * 
   * - Its the most convenient semantic element for an user (For instance, for a given graphical element related to a
   * Part, it retrieve the Component, which is the main element used by the user)
   */
  public static EObject resolveBusinessObject(Object object) {
    return resolveEObject(object, true, true);
  }

  /**
   * This method returns the list of EObject from the given objects.
   * 
   * @see resolveEObject
   */
  public static Collection<EObject> resolveEObjects(Collection<?> objects) {
    return resolveEObjects(objects, false, false);
  }

  /**
   * This method returns the list of semantic objects from the given objects.
   * 
   * @see resolveSemanticObject
   */
  public static Collection<EObject> resolveSemanticObjects(Collection<?> objects) {
    return resolveEObjects(objects, true, false);
  }

  /**
   * This method returns the list of business objects from the given objects.
   * 
   * @see resolveBusinessObject
   */
  public static Collection<EObject> resolveBusinessObjects(Collection<?> objects) {
    return resolveEObjects(objects, true, true);
  }

  /**
   * This method returns the list of descriptor or business objects from the given objects.
   * 
   * @param objects
   * @return
   */
  public static Collection<EObject> resolveDescriptorsOrBusinessObjects(Collection<?> objects) {
    return objects.stream() //
        .map(CapellaAdapterHelper::resolveDescriptorOrBusinessObject) //
        .filter(Objects::nonNull) //
        .collect(Collectors.toList());
  }

  /**
   * This method returns the list of EObject from the given objects.
   */
  public static Collection<EObject> resolveEObjects(Collection<?> objects, boolean onlySemantic, boolean onlyBusiness) {
    return objects.stream() //
        .map(x -> resolveEObject(x, onlySemantic, onlyBusiness)) //
        .filter(Objects::nonNull) //
        .collect(Collectors.toList());
  }

  /**
   * You may rather use one of resolveEObject, resolveSemanticObject, resolveBusinessObject with no argument
   * 
   * @param onlySemantic:
   *          if false, the element is a graphical element, return the related Sirius object, otherwise, return the
   *          semantic element behind it
   * @param onlyBusiness:
   *          if true, if the returned element is a semantic element, retrieve the user most convenient one behind it.
   */
  public static EObject resolveEObject(Object object, boolean onlySemantic, boolean onlyBusiness) {
    EObject result = null;
    try {
      EObject adapt = null;
      if (object instanceof DRepresentation) {
        object = RepresentationHelper.getRepresentationDescriptor((DRepresentation) object);
      }

      adapt = Adapters.adapt(object, EObject.class, true);
      if (adapt instanceof EObject) {
        result = adapt;
      }
      if (onlySemantic) {
        if (result instanceof DSemanticDecorator) {
          result = ((DSemanticDecorator) result).getTarget();

        } else if (result instanceof DRepresentationDescriptor) {
          result = ((DRepresentationDescriptor) result).getTarget();
        }

        if (result != null) {
          adapt = Adapters.adapt(result, Element.class, true);
        } else {
          adapt = Adapters.adapt(object, Element.class, true);
        }

        if (adapt instanceof Element) {
          result = adapt;
        }
        if (result instanceof Element) {
          // null can happen when we try to adapt a non semantic element (notes, text, ...)
          if (!((object instanceof Project || null != result.eContainer()) && (null != result.eResource()))) {
            // null can happen when a diagram shows a deleted element
            return null;
          }
        } else if (!CapellaResourceHelper.isSemanticElement(result)) {
          return null;
        }
      }
      if (onlyBusiness) {
        if (result instanceof Element) {
          result = getBusinessObject((Element) result);
        }
      }
    } catch (IllegalStateException e) {
      if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
        // Nothing to log here, this can happen if the resource is not accessible anymore (distant resource).
      } else {
        throw e;
      }
    }
    return result;
  }

  /**
   * @param object
   *          object to adapt
   * @return adapted object
   */
  private static EObject getBusinessObject(Element object) {
    if (object.eClass().equals(CsPackage.eINSTANCE.getPart())) {
      boolean allowMultiplePart = TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(object));
      if (!allowMultiplePart) {
        AbstractType type = ((Part) object).getAbstractType();
        if ((type != null) && !(type instanceof ConfigurationItem)) {
          return type;
        }
      }
    }
    return object;
  }

  /**
   * Use resolveSemanticObjects instead
   */
  @Deprecated
  public static Collection<EObject> resolveSemanticsObjects(Collection<?> objects) {
    return resolveSemanticObjects(objects);
  }

  @Deprecated
  public static Collection<EObject> resolveSemanticObjects(Collection<?> objects, boolean onlySemantic) {
    return objects.stream() //
        .map(x -> resolveSemanticObject(x, onlySemantic)) //
        .filter(Objects::nonNull) //
        .collect(Collectors.toList());
  }

  /**
   * Returns a DRepresentationDescriptor or the business element associated to the given object
   * 
   * resolveSemanticObject(object, true) is equivalent to resolveBusinessObject(object)
   * 
   * resolveSemanticObject(object, false) is equivalent to resolveDescriptorOrBusinessObject(object)
   */
  @Deprecated
  public static EObject resolveSemanticObject(Object object, boolean onlySemantic) {
    EObject result = resolveEObject(object, false, false);
    if (result instanceof DRepresentation || result instanceof DRepresentationDescriptor) {
      return resolveEObject(result, onlySemantic, false);
    }
    if (result != null) {
      return resolveEObject(result, true, true);
    }
    return resolveEObject(object, true, true);
  }

  /**
   * Returns a DRepresentationDescriptor or the business element associated to the given object
   */
  public static EObject resolveDescriptorOrBusinessObject(Object object) {
    return resolveSemanticObject(object, false);
  }
}