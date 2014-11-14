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

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ITraceabilityConfiguration;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class CompoundTraceabilityHandler implements ITraceabilityHandler, ITraceabilityTraceHandler {

  private ITraceabilityConfiguration _configuration;

  /**
   * @return the configuration
   */
  public ITraceabilityConfiguration getConfiguration() {
    return _configuration;
  }

  public CompoundTraceabilityHandler(ITraceabilityConfiguration configuration_p) {
    _configuration = configuration_p;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context_p) {
    _configuration.init(context_p);
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context_p) {
    if (_configuration != null) {
      _configuration.dispose(context_p);
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public void attachTraceability(EObject sourceElement_p, EObject targetElement_p, IContext context_p) {
    if (_configuration != null) {
      for (ITraceabilityHandler handler : _configuration.getDefinedHandlers(context_p)) {
        if (_configuration.useHandlerForAttachment(sourceElement_p, targetElement_p, handler, context_p)) {
          handler.attachTraceability(sourceElement_p, targetElement_p, context_p);
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public Collection<EObject> retrieveTracedElements(EObject source_p, IContext context_p) {
    context_p.put(ITransitionConstants.TRACEABILITY_HANDLER, this);

    ArrayList<EObject> result = new ArrayList<EObject>();
    if (_configuration != null) {
      for (ITraceabilityHandler handler : _configuration.getDefinedHandlers(context_p)) {
        if (_configuration.useHandlerForTracedElements(source_p, handler, context_p)) {
          Collection<EObject> elements = handler.retrieveTracedElements(source_p, context_p);

          if ((elements != null) && !elements.isEmpty()) {
            for (EObject object : elements) {
              if (!result.contains(object)) {
                result.add(object);
              }
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public Collection<EObject> retrieveSourceElements(EObject source_p, IContext context_p) {
    context_p.put(ITransitionConstants.TRACEABILITY_HANDLER, this);

    ArrayList<EObject> result = new ArrayList<EObject>();
    if (_configuration != null) {
      for (ITraceabilityHandler handler : _configuration.getDefinedHandlers(context_p)) {
        if (_configuration.useHandlerForSourceElements(source_p, handler, context_p)) {
          Collection<EObject> elements = handler.retrieveSourceElements(source_p, context_p);

          if ((elements != null) && !elements.isEmpty()) {
            for (EObject object : elements) {
              if (!result.contains(object)) {
                result.add(object);
              }
            }
          }

        }
      }
    }
    return result;
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

  /**
   * {@inheritDoc}
   * @deprecated
   */
  @Deprecated
  public boolean isTraced(EObject element_p, IContext context_p) {
    return !retrieveTracedElements(element_p, context_p).isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Deprecated
  public String getId(EObject element_p, IContext context_p) {
    if (_configuration != null) {
      for (ITraceabilityHandler handler : _configuration.getDefinedHandlers(context_p)) {
        String id = handler.getId(element_p, context_p);
        if (id != null) {
          return id;
        }
      }
    }
    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isTrace(EObject element_p, IContext context_p) {

    if (_configuration != null) {
      for (ITraceabilityHandler handler : _configuration.getDefinedHandlers(context_p)) {
        if (handler instanceof ITraceabilityTraceHandler) {
          ITraceabilityTraceHandler tHandler = (ITraceabilityTraceHandler) handler;
          if (tHandler.isTrace(element_p, context_p)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  public EObject getSourceElement(EObject trace_p, IContext context_p) {

    if (_configuration != null) {
      for (ITraceabilityHandler handler : _configuration.getDefinedHandlers(context_p)) {
        if (handler instanceof ITraceabilityTraceHandler) {
          ITraceabilityTraceHandler tHandler = (ITraceabilityTraceHandler) handler;
          EObject result = tHandler.getSourceElement(trace_p, context_p);
          if (result != null) {
            return result;
          }
        }
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public EObject getTargetElement(EObject trace_p, IContext context_p) {

    if (_configuration != null) {
      for (ITraceabilityHandler handler : _configuration.getDefinedHandlers(context_p)) {
        if (handler instanceof ITraceabilityTraceHandler) {
          ITraceabilityTraceHandler tHandler = (ITraceabilityTraceHandler) handler;
          EObject result = tHandler.getTargetElement(trace_p, context_p);
          if (result != null) {
            return result;
          }
        }
      }
    }
    return null;
  }

}
