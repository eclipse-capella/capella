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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class LevelBasedTraceabilityHandler extends TwoSideTraceabilityHandler {

  public LevelBasedTraceabilityHandler(String identifier_p) {
    super(identifier_p);
  }

  protected class LevelMappingTraceability extends MappingTraceability {

    private static final long serialVersionUID = 1605638569527611797L;

    public Collection<EObject> initialized;

    public IContext context;

    public String key;

    public LevelMappingTraceability(IContext context_p, String key_p) {
      initialized = new HashSet<EObject>();
      context = context_p;
      key = key_p;
    }

  }

  protected class BackwardTraceability extends LevelMappingTraceability {

    public BackwardTraceability(IContext context_p, String key_p) {
      super(context_p, key_p);
    }

  }

  protected class ForwardTraceability extends LevelMappingTraceability {

    public ForwardTraceability(IContext context_p, String key_p) {
      super(context_p, key_p);
    }
  }

  @Override
  protected Collection<EObject> retrieveRelatedElements(EObject source_p, IContext context_p, MappingTraceability maps_p) {
    Collection<EObject> mapped = null;
    if (maps_p.contains(source_p)) {
      mapped = maps_p.get(source_p);

    } else if (maps_p instanceof LevelMappingTraceability) {
      initializeMappings(source_p, context_p, (LevelMappingTraceability) maps_p);
      mapped = maps_p.get(source_p);
    }

    if (mapped != null) {
      return mapped;
    }
    return Collections.emptyList();
  }

  protected boolean isLevelElement(EObject object_p, IContext context_p) {
    return true;
  }

  protected EObject getLevelElement(EObject source_p, IContext context_p) {
    EObject parent = null;
    if (source_p != null) {
      parent = source_p.eContainer();
      while (parent != null) {
        if (isLevelElement(parent, context_p)) {
          break;
        }
        parent = parent.eContainer();
      }
    }
    return parent;
  }

  protected void initializeMappings(EObject source_p, IContext context_p, LevelMappingTraceability map_p) {
    EObject sourceRoot = getLevelElement(source_p, context_p);

    if (!map_p.initialized.contains(sourceRoot)) {
      map_p.initialized.add(sourceRoot);

      if (sourceRoot == null) {
        initializeRootMappings(context_p);

      } else {
        ITraceabilityHandler handler = TraceabilityHandlerHelper.getInstance(context_p);

        if (!isBackward(map_p.key)) {
          for (EObject targetRoot : handler.retrieveTracedElements(sourceRoot, context_p)) {
            if (targetRoot != null) {
              initializeMapping(sourceRoot, targetRoot, context_p, map_p);
            }
          }

        } else { //backward, target is source, source is target !
          for (EObject targetRoot : handler.retrieveSourceElements(sourceRoot, context_p)) {
            if (targetRoot != null) {
              initializeMapping(targetRoot, sourceRoot, context_p, map_p);
            }
          }
        }
      }
    }
  }

  protected boolean isBackward(String key_p) {
    return getBackwardMappingKey().equals(key_p);
  }

  @Override
  protected MappingTraceability createMappingTraceability(EObject source_p, IContext context_p, String key_p) {
    if (isBackward(key_p)) {
      return new BackwardTraceability(context_p, key_p);
    }
    return new ForwardTraceability(context_p, key_p);
  }

  protected void initializeRootMappings(IContext context_p) {
    //Nothing yet
  }

  protected void initializeMapping(EObject sourceRoot_p, EObject targetRoot_p, IContext context_p, LevelMappingTraceability map_p) {
    //Nothing yet
  }

}
