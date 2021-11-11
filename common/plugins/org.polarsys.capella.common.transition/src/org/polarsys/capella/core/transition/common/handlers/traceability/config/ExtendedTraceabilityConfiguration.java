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

package org.polarsys.capella.core.transition.common.handlers.traceability.config;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.ExtensionHelper;
import org.polarsys.capella.core.transition.common.constants.ISchemaConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A traceabilityConfiguration which load also extensions
 */
public abstract class ExtendedTraceabilityConfiguration extends TraceabilityConfiguration {

  protected List<ITraceabilityConfiguration> _configurations;

  protected abstract String getExtensionIdentifier(IContext context);

  /**
   * @param context
   */
  protected void loadConfigurations(IContext context, String identifier) {
    for (Object object : ExtensionHelper.collectFromExtensions(context, ISchemaConstants.EXTENSION_ID, identifier,
        (String) context.get(ITransitionConstants.TRANSPOSER_PURPOSE),
        (String) context.get(ITransitionConstants.TRANSPOSER_MAPPING))) {
      if (object instanceof ITraceabilityConfiguration) {
        addConfiguration(context, (ITraceabilityConfiguration) object);
      }
    }
  }

  protected void addConfiguration(IContext fContext, ITraceabilityConfiguration iTraceabilityConfiguration1) {
    getDefinedConfigurations(fContext).add(iTraceabilityConfiguration1);
    iTraceabilityConfiguration1.init(fContext);

    for (ITraceabilityHandler handler : iTraceabilityConfiguration1.getDefinedHandlers(fContext)) {
      addHandler(fContext, handler);
    }
  }

  /**
   * {@inheritDoc}
   */
  public List<ITraceabilityConfiguration> getDefinedConfigurations(IContext context) {
    if (_configurations == null) {
      _configurations = new LinkedList<ITraceabilityConfiguration>();
    }
    return _configurations;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initHandlers(IContext fContext) {
    super.initHandlers(fContext);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context) {
    loadConfigurations(context, getExtensionIdentifier(context));
    return super.init(context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context) {
    return super.dispose(context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<ITraceabilityHandler> getDefinedHandlers(IContext context) {
    return super.getDefinedHandlers(context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForAttachment(EObject source, EObject target, ITraceabilityHandler handler,
      IContext context) {
    boolean result = super.useHandlerForAttachment(source, target, handler, context);

    if (result) {
      for (ITraceabilityConfiguration configuration : getDefinedConfigurations(context)) {
        result = configuration.useHandlerForAttachment(source, target, handler, context);
        if (!result) {
          return false;
        }
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForTracedElements(EObject source, ITraceabilityHandler handler, IContext context) {
    boolean result = super.useHandlerForTracedElements(source, handler, context);

    if (result) {
      for (ITraceabilityConfiguration configuration : getDefinedConfigurations(context)) {
        result = configuration.useHandlerForTracedElements(source, handler, context);
        if (!result) {
          return false;
        }
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForSourceElements(EObject source, ITraceabilityHandler handler, IContext context) {
    boolean result = super.useHandlerForSourceElements(source, handler, context);

    if (result) {
      for (ITraceabilityConfiguration configuration : getDefinedConfigurations(context)) {
        result = configuration.useHandlerForSourceElements(source, handler, context);
        if (!result) {
          return false;
        }
      }
    }
    return result;
  }

}
