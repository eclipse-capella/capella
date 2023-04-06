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
package org.polarsys.capella.core.transition.system.topdown.handlers.attachment;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.HashMap;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class AbstractFunctionAttachmentHelper implements IHandler {

  protected static final String ATTACHMENT_MAP = "AFFFAttachmentMap"; //$NON-NLS-1$

  public static AbstractFunctionAttachmentHelper getInstance(IContext context_p) {
    AbstractFunctionAttachmentHelper handler = (AbstractFunctionAttachmentHelper) context_p.get(ITopDownConstants.ABSTRACT_FUNCTION_ATTACHMENT_HANDLER);
    if (handler == null) {
      handler = new AbstractFunctionAttachmentHelper();
      handler.init(context_p);
      context_p.put(ITopDownConstants.ABSTRACT_FUNCTION_ATTACHMENT_HANDLER, handler);
    }
    return handler;
  }

  @SuppressWarnings("unchecked")
  protected HashMap<AbstractFunction, Integer> getMap(IContext context_p) {
    if (context_p.get(ATTACHMENT_MAP) == null) {
      context_p.put(ATTACHMENT_MAP, new HashMap<AbstractFunction, Integer>());
    }
    return (HashMap<AbstractFunction, Integer>) context_p.get(ATTACHMENT_MAP);
  }

  /**
   * Compute number of no-transitioned sub functions which should be transitioned
   */
  public int getNbUntransitionedSubFunctions(AbstractFunction function_p, IContext context_p) {
    HashMap<AbstractFunction, Integer> map = getMap(context_p);

    if (!map.containsKey(function_p)) {
      int nb = 0;
      for (AbstractFunction sub : getCache(FunctionExt::getFirstLevelAbstractFunctions, function_p)) {

        if (FunctionExt.isLeaf(sub)) {
          boolean isTransform = TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(sub, context_p);
          if (!isTransform) {
            nb++;
          }
        }

        nb += getNbUntransitionedSubFunctions(sub, context_p);
      }
      map.put(function_p, Integer.valueOf(nb));
      return nb;
    }

    return map.get(function_p).intValue();
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }
}
