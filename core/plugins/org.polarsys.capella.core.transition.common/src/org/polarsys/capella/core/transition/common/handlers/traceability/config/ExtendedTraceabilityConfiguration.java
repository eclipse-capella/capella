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
package org.polarsys.capella.core.transition.common.handlers.traceability.config;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.ExtensionHelper;
import org.polarsys.capella.core.transition.common.constants.ISchemaConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A traceabilityConfiguration which load also extensions
 */
public abstract class ExtendedTraceabilityConfiguration extends TraceabilityConfiguration {

  protected List<ITraceabilityConfiguration> _configurations;

  protected abstract String getExtensionIdentifier(IContext context_p);

  /**
   * @param context_p
   */
  protected void loadConfigurations(IContext context_p, String identifier_p) {
    for (Object object : ExtensionHelper.collectFromExtensions(context_p, ISchemaConstants.EXTENSION_ID, identifier_p)) {
      if (object instanceof ITraceabilityConfiguration) {
        addConfiguration(context_p, (ITraceabilityConfiguration) object);
      }
    }
  }

  protected void addConfiguration(IContext fContext_p, ITraceabilityConfiguration handler_p) {
    getDefinedConfigurations(fContext_p).add(handler_p);
    handler_p.init(fContext_p);

    for (ITraceabilityHandler handler : handler_p.getDefinedHandlers(fContext_p)) {
      addHandler(fContext_p, handler);
    }
  }

  /**
   * {@inheritDoc}
   */
  public List<ITraceabilityConfiguration> getDefinedConfigurations(IContext context_p) {
    if (_configurations == null) {
      _configurations = new LinkedList<ITraceabilityConfiguration>();
    }
    return _configurations;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initHandlers(IContext fContext_p) {
    super.initHandlers(fContext_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context_p) {
    loadConfigurations(context_p, getExtensionIdentifier(context_p));
    return super.init(context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context_p) {
    return super.dispose(context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<ITraceabilityHandler> getDefinedHandlers(IContext context_p) {
    return super.getDefinedHandlers(context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForAttachment(EObject source_p, EObject target_p, ITraceabilityHandler handler_p, IContext context_p) {
    boolean result = super.useHandlerForAttachment(source_p, target_p, handler_p, context_p);

    if (result) {
      for (ITraceabilityConfiguration configuration : getDefinedConfigurations(context_p)) {
        result = configuration.useHandlerForAttachment(source_p, target_p, handler_p, context_p);
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForTracedElements(EObject source_p, ITraceabilityHandler handler_p, IContext context_p) {
    boolean result = super.useHandlerForTracedElements(source_p, handler_p, context_p);

    if (result) {
      for (ITraceabilityConfiguration configuration : getDefinedConfigurations(context_p)) {
        result = configuration.useHandlerForTracedElements(source_p, handler_p, context_p);
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForSourceElements(EObject source_p, ITraceabilityHandler handler_p, IContext context_p) {
    boolean result = super.useHandlerForSourceElements(source_p, handler_p, context_p);

    if (result) {
      for (ITraceabilityConfiguration configuration : getDefinedConfigurations(context_p)) {
        result = configuration.useHandlerForSourceElements(source_p, handler_p, context_p);
      }
    }
    return result;
  }

}
