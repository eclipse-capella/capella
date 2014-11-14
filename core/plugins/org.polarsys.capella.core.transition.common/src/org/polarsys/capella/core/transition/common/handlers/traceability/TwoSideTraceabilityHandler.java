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

  private String _identifier;

  private String _identifierFm;
  private String _identifierBm;

  public String getIdentifier() {
    return _identifier;
  }

  private static final String FORWARD_MAPPINGS = "Fm"; //$NON-NLS-1$

  private static final String BACKWARD_MAPPINGS = "Bm"; //$NON-NLS-1$

  public TwoSideTraceabilityHandler(String identifier_p) {
    _identifier = getClass().getName() + identifier_p;
    _identifierFm = FORWARD_MAPPINGS + getIdentifier();
    _identifierBm = BACKWARD_MAPPINGS + getIdentifier();
  }

  protected String getForwardMappingKey() {
    return _identifierFm;
  }

  protected String getBackwardMappingKey() {
    return _identifierBm;
  }

  protected MappingTraceability getForwardMappings(EObject source_p, IContext context_p) {
    return getMappings(source_p, context_p, getForwardMappingKey());
  }

  protected MappingTraceability getBackwardMappings(EObject source_p, IContext context_p) {
    return getMappings(source_p, context_p, getBackwardMappingKey());
  }

  protected MappingTraceability createMappingTraceability(EObject source_p, IContext context_p, String key) {
    return new MappingTraceability();
  }

  protected MappingTraceability getMappings(EObject source_p, IContext context_p, String key_p) {
    MappingTraceability map = (MappingTraceability) context_p.get(key_p);
    if (map == null) {
      map = createMappingTraceability(source_p, context_p, key_p);
      context_p.put(key_p, map);
    }
    return map;
  }

  protected void addMappings(EObject sourceElement_p, EObject targetElement_p, IContext context_p) {
    addMapping(getForwardMappings(sourceElement_p, context_p), sourceElement_p, targetElement_p, context_p);
    addMapping(getBackwardMappings(sourceElement_p, context_p), sourceElement_p, targetElement_p, context_p);
  }

  protected void removeMapping(MappingTraceability map_p, EObject obj_p, IContext context_p) {
    map_p.remove(obj_p);
  }

  protected void addMapping(MappingTraceability map, EObject sourceElement_p, EObject targetElement_p, IContext context_p) {

    if (map.equals(context_p.get(getForwardMappingKey()))) {
      if ((sourceElement_p != null) && (targetElement_p != null)) {
        map.put(sourceElement_p, targetElement_p);
      }

    } else {
      if ((sourceElement_p != null) && (targetElement_p != null)) {
        map.put(targetElement_p, sourceElement_p);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public void attachTraceability(EObject sourceElement_p, EObject targetElement_p, IContext context_p) {
    //Nothing yet
  }

  protected Collection<EObject> retrieveRelatedElements(EObject source_p, IContext context_p, MappingTraceability maps_p) {
    ArrayList<EObject> result = new ArrayList<EObject>();
    if (maps_p.contains(source_p)) {
      Collection<EObject> mapped = maps_p.get(source_p);
      result.addAll(mapped);
    }
    return result;
  }

  public Collection<EObject> retrieveTracedElements(EObject source_p, IContext context_p) {
    return retrieveRelatedElements(source_p, context_p, getForwardMappings(source_p, context_p));
  }

  public Collection<EObject> retrieveSourceElements(EObject source_p, IContext context_p) {
    return retrieveRelatedElements(source_p, context_p, getBackwardMappings(source_p, context_p));
  }

  @Deprecated
  public boolean isTraced(EObject element_p, IContext context_p) {
    return retrieveTracedElements(element_p, context_p).size() > 0;
  }

  @Deprecated
  public Collection<EObject> retrieveTracedElements(EObject source_p, IContext context_p, EClass clazz) {
    ArrayList<EObject> result = new ArrayList<EObject>();
    for (EObject obj : retrieveTracedElements(source_p, context_p)) {
      if (clazz.isInstance(obj)) {
        result.add(obj);
      }
    }
    return result;
  }

  @Deprecated
  public String getId(EObject element_p, IContext context_p) {
    return SessionHandlerHelper.getInstance(context_p).getId(element_p, context_p);
  }

  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

}
