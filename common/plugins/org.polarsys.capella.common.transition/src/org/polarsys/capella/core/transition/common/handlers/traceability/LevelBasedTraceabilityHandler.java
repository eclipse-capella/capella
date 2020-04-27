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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class LevelBasedTraceabilityHandler extends TwoSideTraceabilityHandler {

  public LevelBasedTraceabilityHandler(String identifier) {
    super(identifier);
  }

  protected class LevelMappingTraceability extends MappingTraceability {

    private static final long serialVersionUID = 1605638569527611797L;

    public Collection<EObject> initialized;

    public IContext context;

    public String key;

    public LevelMappingTraceability(IContext context, String key) {
      this.initialized = new HashSet<EObject>();
      this.context = context;
      this.key = key;
    }

  }

  protected class BackwardTraceability extends LevelMappingTraceability {

    public BackwardTraceability(IContext context, String key) {
      super(context, key);
    }

  }

  protected class ForwardTraceability extends LevelMappingTraceability {

    public ForwardTraceability(IContext context, String key) {
      super(context, key);
    }
  }

  @Override
  protected Collection<EObject> retrieveRelatedElements(EObject source, IContext context, MappingTraceability maps) {
    Collection<EObject> mapped = null;
    if (maps.contains(source)) {
      mapped = maps.get(source);

    } else if (maps instanceof LevelMappingTraceability) {
      initializeMappings(source, context, (LevelMappingTraceability) maps);
      mapped = maps.get(source);
    }

    if (mapped != null) {
      return mapped;
    }
    return Collections.emptyList();
  }

  protected boolean isLevelElement(EObject object, IContext context) {
    return true;
  }

  protected EObject getLevelElement(EObject source, IContext context) {
    EObject parent = null;
    if (source != null) {
      parent = source.eContainer();
      while (parent != null) {
        if (isLevelElement(parent, context)) {
          break;
        }
        parent = parent.eContainer();
      }
    }
    return parent;
  }

  protected void initializeMappings(EObject source, IContext context, LevelMappingTraceability map) {
    EObject sourceRoot = getLevelElement(source, context);

    if (!map.initialized.contains(sourceRoot)) {
      map.initialized.add(sourceRoot);

      if (sourceRoot == null) {
        initializeRootMappings(context);

      } else {
        ITraceabilityHandler handler = TraceabilityHandlerHelper.getInstance(context);

        if (!isBackward(map.key)) {
          for (EObject targetRoot : handler.retrieveTracedElements(sourceRoot, context)) {
            if (targetRoot != null) {
              initializeMapping(sourceRoot, targetRoot, context, map);
            }
          }

        } else { //backward, target is source, source is target !
          for (EObject targetRoot : handler.retrieveSourceElements(sourceRoot, context)) {
            if (targetRoot != null) {
              initializeMapping(targetRoot, sourceRoot, context, map);
            }
          }
        }
      }
    }
  }

  protected boolean isBackward(String key) {
    return getBackwardMappingKey().equals(key);
  }

  @Override
  protected MappingTraceability createMappingTraceability(EObject source, IContext context, String key) {
    if (isBackward(key)) {
      return new BackwardTraceability(context, key);
    }
    return new ForwardTraceability(context, key);
  }

  protected void initializeRootMappings(IContext context) {
    //Nothing yet
  }

  protected void initializeMapping(EObject sourceRoot, EObject targetRoot, IContext context, LevelMappingTraceability map) {
    //Nothing yet
  }

}
