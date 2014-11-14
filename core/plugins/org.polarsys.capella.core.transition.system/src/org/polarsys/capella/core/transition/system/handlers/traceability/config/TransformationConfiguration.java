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
  protected String getExtensionIdentifier(IContext context_p) {
    return ISchemaConstants.TRANSFORMATION_TRACEABILITY_CONFIGURATION;
  }

  @Override
  protected void initHandlers(IContext fContext_p) {
    addHandler(fContext_p, new ReconciliationTraceabilityHandler(getIdentifier(fContext_p)) {

      @Override
      protected void initializeComponent(Component source_p, Component target_p, IContext context_p, LevelMappingTraceability map_p) {
        //Nothing here, we don't want to play with matching though stateMachines size while transformation
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected void initializeRootMappings(IContext context_p) {
        super.initializeRootMappings(context_p);
        EObject source = (EObject) context_p.get(ITransitionConstants.TRANSFORMATION_SOURCE_ROOT);
        EObject target = (EObject) context_p.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
        addMappings(source, target, context_p);
      }

    });

    addHandler(fContext_p, new SIDTraceabilityHandler(getIdentifier(fContext_p)) {

      /**
       * {@inheritDoc}
       */
      @Override
      protected void initializeRootMappings(IContext context_p) {
        super.initializeRootMappings(context_p);
        EObject source = (EObject) context_p.get(ITransitionConstants.TRANSFORMATION_SOURCE_ROOT);
        EObject target = (EObject) context_p.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
        initializeMappings(source, target, context_p);
      }

    });

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForAttachment(EObject source_p, EObject target_p, ITraceabilityHandler handler_p, IContext context_p) {

    boolean result = super.useHandlerForAttachment(source_p, target_p, handler_p, context_p);
    if (result) {
      //We disable Reconciliation for attachment
      if (handler_p instanceof ReconciliationTraceabilityHandler) {
        result = false;
      }
    }

    return result;
  }

}
