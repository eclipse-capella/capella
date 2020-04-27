/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.common.handlers.traceability;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.session.SessionHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class TwoSideTraceabilityHandler implements ITraceabilityHandler2 {

  private String identifier;

  private String identifierFm;
  private String identifierBm;

  public String getIdentifier() {
    return identifier;
  }

  private static final String FORWARD_MAPPINGS = "Fm"; //$NON-NLS-1$

  private static final String BACKWARD_MAPPINGS = "Bm"; //$NON-NLS-1$

  public TwoSideTraceabilityHandler(String identifier) {
    this.identifier = getClass().getName() + identifier;
    this.identifierFm = FORWARD_MAPPINGS + getIdentifier();
    this.identifierBm = BACKWARD_MAPPINGS + getIdentifier();
  }

  protected String getForwardMappingKey() {
    return identifierFm;
  }

  protected String getBackwardMappingKey() {
    return identifierBm;
  }

  protected MappingTraceability getForwardMappings(EObject source, IContext context) {
    return getMappings(source, context, getForwardMappingKey());
  }

  protected MappingTraceability getBackwardMappings(EObject source, IContext context) {
    return getMappings(source, context, getBackwardMappingKey());
  }

  protected MappingTraceability createMappingTraceability(EObject source, IContext context, String key) {
    return new MappingTraceability();
  }

  protected MappingTraceability getMappings(EObject source, IContext context, String key) {
    MappingTraceability map = (MappingTraceability) context.get(key);
    if (map == null) {
      map = createMappingTraceability(source, context, key);
      context.put(key, map);
    }
    return map;
  }

  protected void addMappings(EObject sourceElement, EObject targetElement, IContext context) {
    addMapping(getForwardMappings(sourceElement, context), sourceElement, targetElement, context);
    addMapping(getBackwardMappings(sourceElement, context), sourceElement, targetElement, context);
  }

  protected void removeMapping(MappingTraceability map, EObject obj, IContext context) {
    map.remove(obj);
  }

  protected void addMapping(MappingTraceability map, EObject sourceElement, EObject targetElement, IContext context) {

    if (map.equals(context.get(getForwardMappingKey()))) {
      if ((sourceElement != null) && (targetElement != null)) {
        map.put(sourceElement, targetElement);
      }

    } else {
      if ((sourceElement != null) && (targetElement != null)) {
        map.put(targetElement, sourceElement);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public void attachTraceability(EObject sourceElement, EObject targetElement, IContext context) {
    //Nothing yet
  }

  protected Collection<EObject> retrieveRelatedElements(EObject source, IContext context, MappingTraceability maps) {
    ArrayList<EObject> result = new ArrayList<EObject>();
    if (maps.contains(source)) {
      Collection<EObject> mapped = maps.get(source);
      result.addAll(mapped);
    }
    return result;
  }

  public Collection<EObject> retrieveTracedElements(EObject source, IContext context) {
    return retrieveRelatedElements(source, context, getForwardMappings(source, context));
  }

  public Collection<EObject> retrieveSourceElements(EObject source, IContext context) {
    return retrieveRelatedElements(source, context, getBackwardMappings(source, context));
  }

  @Deprecated
  public boolean isTraced(EObject element, IContext context) {
    return retrieveTracedElements(element, context).size() > 0;
  }

  @Deprecated
  public Collection<EObject> retrieveTracedElements(EObject source, IContext context, EClass clazz) {
    ArrayList<EObject> result = new ArrayList<EObject>();
    for (EObject obj : retrieveTracedElements(source, context)) {
      if (clazz.isInstance(obj)) {
        result.add(obj);
      }
    }
    return result;
  }

  @Deprecated
  public String getId(EObject element, IContext context) {
    return SessionHandlerHelper.getInstance(context).getId(element, context);
  }

  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

}
