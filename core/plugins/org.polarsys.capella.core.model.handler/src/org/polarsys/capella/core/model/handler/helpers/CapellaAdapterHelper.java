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

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
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
   * Returns the semantic element for the given object.
   * 
   * @see it is similar than resolveSemanticObject(object, false) It shall have be called resolveEObject.
   * 
   * @param object
   *          must not be a list
   */
  @Deprecated
  public static EObject resolveSemanticObject(Object object) {
    return resolveEObject(object);
  }

  /**
   * Returns the semantic element for the given object.
   * 
   * @see it is similar than resolveSemanticObject(object, false)
   * 
   * @param object
   *          must not be a list
   */
  public static EObject resolveEObject(Object object) {
    return resolveSemanticObject(object, false);
  }

  /**
   * Returns the semantic element for the given object. According to onlySemantic, if the element is a Sirius
   * representation, it returns the semantic element associated.
   */
  public static EObject resolveSemanticObject(Object object, boolean onlySemantic) {
    if (object instanceof EObject) {
      return resolveEObject(object, onlySemantic);

    } else if (object instanceof IAdaptable) {
      EObject adapter = ((IAdaptable) object).getAdapter(EObject.class);
      if (adapter == null) {
        return resolveEObject(object, onlySemantic);
      }
      return resolveEObject(adapter, onlySemantic);
    }

    return null;
  }

  /**
   * This method returns the list of EObject from the given objects.
   */
  public static Collection<EObject> resolveEObjects(Collection<?> objects) {
    return resolveSemanticObjects(objects, false);
  }

  /**
   * This method returns the list of semantic objects from the given objects.
   */
  public static Collection<EObject> resolveSemanticsObjects(Collection<?> objects) {
    return resolveSemanticObjects(objects, true);
  }

  /**
   * This method returns the list of EObject from the given objects.
   */
  public static Collection<EObject> resolveSemanticObjects(Collection<?> objects, boolean onlySemantic) {
    return objects.stream() //
        .map(x -> resolveSemanticObject(x, onlySemantic)) //
        .filter(Objects::nonNull) //
        .collect(Collectors.toList());
  }

  /**
   * This method
   */
  private static EObject resolveEObject(Object object, boolean onlySemantic) {
    if ((object instanceof DRepresentationDescriptor) || (object instanceof DRepresentation)) {
      if (onlySemantic) {
        if (object instanceof DSemanticDecorator) {
          return ((DSemanticDecorator) object).getTarget();
        } else if (object instanceof DRepresentationDescriptor) {
          return ((DRepresentationDescriptor) object).getTarget();
        }
      } else {
        if (object instanceof DRepresentation) {
          return RepresentationHelper.getRepresentationDescriptor((DRepresentation) object);
        }
        return (EObject) object;
      }
    }
    return getBusinessObject(object);
  }

  /**
   * Business level adaptation
   * 
   * @param object
   */
  private static EObject getBusinessObject(Object object) {
    if (object != null) {
      if (CapellaResourceHelper.isSemanticElement(object)) {
        return getRelatedSemanticObject((EObject) object);
      }
      EObject obj = Adapters.adapt(object, Element.class, true);
      if (obj != null) {
        // null can happen when we try to adapt a non semantic element (notes, text, ...)
        if ((obj instanceof Project || null != obj.eContainer()) && (null != obj.eResource())) {
          // null can happen when a diagram shows a deleted element
          return getRelatedSemanticObject(obj);
        }
      }
    }
    return null;
  }

  /**
   * @param object
   *          object to adapt
   * @return adapted object
   */
  private static EObject getRelatedSemanticObject(EObject object) {
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
   * This method returns the list of EObject from the given objects.
   */
  public static Collection<EObject> resolveEObjects(Collection<?> objects, boolean onlySemantic, boolean onlyBusiness) {
    return objects.stream() //
        .map(x -> resolveEObject(x, onlySemantic, onlyBusiness)) //
        .filter(Objects::nonNull) //
        .collect(Collectors.toList());
  }
  
  /**
   * Resolve EObject
   * 
   * @param onlySemantic:
   *          if false, the element is a graphical element, return the related Sirius object, otherwise, return the
   *          semantic element behind it
   * @param onlyBusiness:
   *          if true, if the returned element is a semantic element, retrieve the user most convenient one behind it.
   */
  public static EObject resolveEObject(Object object, boolean onlySemantic, boolean onlyBusiness) {
    EObject result = null;
    EObject adapt = null;
    if (object instanceof DRepresentation) {
      object = RepresentationHelper.getRepresentationDescriptor((DRepresentation) object);
    }

    adapt = Adapters.adapt(object, EObject.class, true);
    if (adapt instanceof EObject) {
      result = (EObject) adapt;
    }
    if (onlySemantic) {
      if (result instanceof DSemanticDecorator) {
        result = ((DSemanticDecorator) result).getTarget();

      } else if (result instanceof DRepresentationDescriptor) {
        result = ((DRepresentationDescriptor) result).getTarget();
      }

      adapt = Adapters.adapt(result, Element.class, true);
      if (adapt instanceof Element) {
        result = (Element) adapt;
      }
      if (result instanceof Element) {
        // null can happen when we try to adapt a non semantic element (notes, text, ...)
        if (!((object instanceof Project || null != ((EObject) result).eContainer())
            && (null != ((EObject) result).eResource()))) {
          // null can happen when a diagram shows a deleted element
          return null;
        }
      }
    }
    if (onlyBusiness) {
      if (result instanceof Element) {
        result = getBusinessObject((Element) result);
      }
    }
    return result;
  }
}