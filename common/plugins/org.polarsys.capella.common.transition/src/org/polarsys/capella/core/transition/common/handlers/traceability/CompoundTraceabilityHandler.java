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

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ITraceabilityConfiguration;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class CompoundTraceabilityHandler implements ITraceabilityHandler, ITraceabilityTraceHandler {

  private ITraceabilityConfiguration configuration;

  /**
   * @return the configuration
   */
  public ITraceabilityConfiguration getConfiguration() {
    return configuration;
  }

  public CompoundTraceabilityHandler(ITraceabilityConfiguration configuration) {
    this.configuration = configuration;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context) {
    configuration.init(context);
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context) {
    if (configuration != null) {
      configuration.dispose(context);
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public void attachTraceability(EObject sourceElement, EObject targetElement, IContext context) {
    if (configuration != null) {
      for (ITraceabilityHandler handler : configuration.getDefinedHandlers(context)) {
        if (configuration.useHandlerForAttachment(sourceElement, targetElement, handler, context)) {
          handler.attachTraceability(sourceElement, targetElement, context);
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public Collection<EObject> retrieveTracedElements(EObject source, IContext context) {
    context.put(ITransitionConstants.TRACEABILITY_HANDLER, this);

    ArrayList<EObject> result = new ArrayList<EObject>();
    if (configuration != null) {
      for (ITraceabilityHandler handler : configuration.getDefinedHandlers(context)) {
        if (configuration.useHandlerForTracedElements(source, handler, context)) {
          Collection<EObject> elements = handler.retrieveTracedElements(source, context);

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
  public Collection<EObject> retrieveSourceElements(EObject source, IContext context) {
    context.put(ITransitionConstants.TRACEABILITY_HANDLER, this);

    ArrayList<EObject> result = new ArrayList<EObject>();
    if (configuration != null) {
      for (ITraceabilityHandler handler : configuration.getDefinedHandlers(context)) {
        if (configuration.useHandlerForSourceElements(source, handler, context)) {
          Collection<EObject> elements = handler.retrieveSourceElements(source, context);

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
  public Collection<EObject> retrieveTracedElements(EObject source, IContext context, EClass clazz) {
    ArrayList<EObject> result = new ArrayList<EObject>();
    for (EObject obj : retrieveTracedElements(source, context)) {
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
  public boolean isTraced(EObject element, IContext context) {
    return !retrieveTracedElements(element, context).isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Deprecated
  public String getId(EObject element, IContext context) {
    if (configuration != null) {
      for (ITraceabilityHandler handler : configuration.getDefinedHandlers(context)) {
        String id = handler.getId(element, context);
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
  public boolean isTrace(EObject element, IContext context) {

    if (configuration != null) {
      for (ITraceabilityHandler handler : configuration.getDefinedHandlers(context)) {
        if (handler instanceof ITraceabilityTraceHandler) {
          ITraceabilityTraceHandler tHandler = (ITraceabilityTraceHandler) handler;
          if (tHandler.isTrace(element, context)) {
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
  public EObject getSourceElement(EObject trace, IContext context) {

    if (configuration != null) {
      for (ITraceabilityHandler handler : configuration.getDefinedHandlers(context)) {
        if (handler instanceof ITraceabilityTraceHandler) {
          ITraceabilityTraceHandler tHandler = (ITraceabilityTraceHandler) handler;
          EObject result = tHandler.getSourceElement(trace, context);
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
  public EObject getTargetElement(EObject trace, IContext context) {

    if (configuration != null) {
      for (ITraceabilityHandler handler : configuration.getDefinedHandlers(context)) {
        if (handler instanceof ITraceabilityTraceHandler) {
          ITraceabilityTraceHandler tHandler = (ITraceabilityTraceHandler) handler;
          EObject result = tHandler.getTargetElement(trace, context);
          if (result != null) {
            return result;
          }
        }
      }
    }
    return null;
  }

}
