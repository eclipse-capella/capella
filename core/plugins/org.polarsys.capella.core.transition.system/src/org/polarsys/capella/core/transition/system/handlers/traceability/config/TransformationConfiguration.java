/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.transition.system.handlers.traceability.config;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.transition.common.constants.ISchemaConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ExtendedTraceabilityConfiguration;
import org.polarsys.capella.core.transition.system.handlers.traceability.ReconciliationTraceabilityHandler;
import org.polarsys.capella.core.transition.system.handlers.traceability.SIDTraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TransformationConfiguration extends ExtendedTraceabilityConfiguration {

  @Override
  protected String getExtensionIdentifier(IContext context) {
    return ISchemaConstants.TRANSFORMATION_TRACEABILITY_CONFIGURATION;
  }

  @Override
  protected void initHandlers(IContext fContext) {
    addHandler(fContext, new ReconciliationTraceabilityHandler(getIdentifier(fContext)) {

      @Override
      protected void initializeComponent(Component source, Component target, IContext context, LevelMappingTraceability map) {
        //Nothing here, we don't want to play with matching though stateMachines size while transformation
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected void initializeRootMappings(IContext context) {
        super.initializeRootMappings(context);
        EObject source = (EObject) context.get(ITransitionConstants.TRANSFORMATION_SOURCE_ROOT);
        EObject target = (EObject) context.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
        addMappings(source, target, context);
      }

    });

    addHandler(fContext, new SIDTraceabilityHandler(getIdentifier(fContext)) {

      /**
       * {@inheritDoc}
       */
      @Override
      protected void initializeRootMappings(IContext context) {
        super.initializeRootMappings(context);
        EObject source = (EObject) context.get(ITransitionConstants.TRANSFORMATION_SOURCE_ROOT);
        EObject target = (EObject) context.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
        initializeMappings(source, target, context);
      }

    });

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForAttachment(EObject source, EObject target, ITraceabilityHandler handler, IContext context) {

    boolean result = super.useHandlerForAttachment(source, target, handler, context);
    if (result) {
      //We disable Reconciliation for attachment
      if (handler instanceof ReconciliationTraceabilityHandler) {
        result = false;
      }
    }

    return result;
  }

}
