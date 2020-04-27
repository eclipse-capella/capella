/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.handlers.scope;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeRetriever;
import org.polarsys.capella.core.transition.system.constants.ISystemConstants;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A scope retriever to retrieve all applied/owned property values for all scope's capella elements
 */
public class PropertyValuesScopeRetriever implements IScopeRetriever {

  public static final String PROPERTY_VALUES_SCOPE_RETRIEVER = "PROPERTY_VALUES_SCOPE_RETRIEVER"; //$NON-NLS-1$
  public static final String PROPERTY_VALUES_SCOPE_RETRIEVER__OWNED = "PROPERTY_VALUES_SCOPE_RETRIEVER__OWNED"; //$NON-NLS-1$
  public static final String PROPERTY_VALUES_SCOPE_RETRIEVER__APPLIED = "PROPERTY_VALUES_SCOPE_RETRIEVER__APPLIED"; //$NON-NLS-1$

  public static PropertyValuesScopeRetriever getInstance(IContext context) {
    PropertyValuesScopeRetriever handler = (PropertyValuesScopeRetriever) context.get(PropertyValuesScopeRetriever.PROPERTY_VALUES_SCOPE_RETRIEVER);
    if (handler == null) {
      handler = new PropertyValuesScopeRetriever();
      handler.init(context);
      context.put(PropertyValuesScopeRetriever.PROPERTY_VALUES_SCOPE_RETRIEVER, handler);
    }
    return handler;
  }

  /** Add elements to applied-list and all owning property-elements */
  protected void addAppliedElements(IContext context_p, Collection<? extends EObject> elements_p) {
    if ((elements_p != null) && !(elements_p.isEmpty())) {
      Collection<EObject> applied = getAppliedElements(context_p);
      for (EObject element : elements_p) {
        while ((element != null) && isProperty(element) && !(applied.contains(element))) {
          applied.add(element);
          element = element.eContainer();
        }
      }
    }
  }

  /** Add elements to applied-list and all owning property-elements */
  protected void addOwnedElements(IContext context_p, Collection<? extends EObject> elements_p) {
    if ((elements_p != null) && !(elements_p.isEmpty())) {
      Collection<EObject> applied = getOwnedElements(context_p);
      for (EObject element : elements_p) {
        while ((element != null) && isProperty(element) && !(applied.contains(element))) {
          applied.add(element);
          element = element.eContainer();
        }
      }
    }
  }

  protected void addOwnedElement(IContext context_p, EObject element_p) {
    if ((element_p != null)) {
      Collection<EObject> applied = getOwnedElements(context_p);
      EObject element = element_p;
      while ((element != null) && isProperty(element) && !(applied.contains(element))) {
        applied.add(element);
        element = element.eContainer();
      }
    }
  }

  /**
   * @param element_p
   * @return
   */
  public boolean isProperty(EObject source_p) {
    return (source_p != null)
           && (((source_p instanceof AbstractPropertyValue)) || (source_p instanceof PropertyValuePkg) || (source_p instanceof PropertyValueGroup)
               || (source_p instanceof EnumerationPropertyType) || (source_p instanceof EnumerationPropertyLiteral));
  }

  public Collection<EObject> getOwnedElements(IContext context_p) {
    if (!context_p.exists(PROPERTY_VALUES_SCOPE_RETRIEVER__OWNED)) {
      context_p.put(PROPERTY_VALUES_SCOPE_RETRIEVER__OWNED, new HashSet<EObject>());
    }
    return (Collection<EObject>) context_p.get(PROPERTY_VALUES_SCOPE_RETRIEVER__OWNED);
  }

  public Collection<EObject> getAppliedElements(IContext context_p) {
    if (!context_p.exists(PROPERTY_VALUES_SCOPE_RETRIEVER__APPLIED)) {
      context_p.put(PROPERTY_VALUES_SCOPE_RETRIEVER__APPLIED, new HashSet<EObject>());
    }
    return (Collection<EObject>) context_p.get(PROPERTY_VALUES_SCOPE_RETRIEVER__APPLIED);
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context_p) {
    getOwnedElements(context_p).clear();
    getAppliedElements(context_p).clear();
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public Collection<? extends EObject> retrieveRelatedElements(EObject source_p, IContext context_p) {
    Collection<EObject> result = new LinkedList<EObject>();

    if (source_p instanceof CapellaElement) {
      CapellaElement element = (CapellaElement) source_p;

      //for the property values transition, we add the selected properties in the list of property that will be propagated to the next level by the TransformationSelectionContext
      if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.INITIAL_SOURCE_SCOPE, element, context_p)) {
        if (isProperty(source_p)) {
          String transitionKind = (String) context_p.get(ITopDownConstants.TRANSITION_KIND);
          if (ITopDownConstants.TRANSITION_TOPDOWN_PROPERTYVALUE.equals(transitionKind)) {
            addOwnedElement(context_p, source_p);
          }
        }
      }

      result.addAll(element.getAppliedPropertyValueGroups());
      result.addAll(element.getAppliedPropertyValues());

      result.addAll(element.getOwnedPropertyValueGroups());
      result.addAll(element.getOwnedPropertyValues());
      
      if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {

        //if the applied property values preference is checked, we add applied properties
        if (OptionsHandlerHelper.getInstance(context_p).getBooleanValue(context_p, ISystemConstants.TRANSITION_PREFERENCES,
            ISystemConstants.SCOPE__APPLIED_PROPERTY_VALUES, ISystemConstants.SCOPE__APPLIED_PROPERTY_VALUES__DEFAULT_VALUE.booleanValue())) {
          addAppliedElements(context_p, element.getAppliedPropertyValueGroups());
          addAppliedElements(context_p, element.getAppliedPropertyValues());

          ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getAppliedPropertyValueGroups(), context_p);
          ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getAppliedPropertyValues(), context_p);
        }

        //For a referenced property included in the scope or if the owned property values preference  is checked, we add internal properties
        if (isProperty(source_p)
            || OptionsHandlerHelper.getInstance(context_p).getBooleanValue(context_p, ISystemConstants.TRANSITION_PREFERENCES,
                ISystemConstants.SCOPE__OWNED_PROPERTY_VALUES, ISystemConstants.SCOPE__OWNED_PROPERTY_VALUES__DEFAULT_VALUE.booleanValue())) {
          addOwnedElements(context_p, element.getOwnedPropertyValueGroups());
          addOwnedElements(context_p, element.getOwnedPropertyValues());


          ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedPropertyValueGroups(), context_p);
          ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedPropertyValues(), context_p);
        }

      }
    }

    return result;
  }

  /**
   * {@inheritDoc}
   */
  public Collection<? extends EObject> retrieveSharedElements(IContext context_p) {
    return Collections.emptyList();
  }

}
