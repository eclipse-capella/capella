/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class LinkTraceabilityHandler extends TwoSideTraceabilityHandler {

  private static final String LINK_TRACEABILITY_MAPPING = "LTM"; //$NON-NLS-1$

  private String _linkTraceabilityKey = null;

  public LinkTraceabilityHandler(String identifier) {
    super(identifier);
    _linkTraceabilityKey = getIdentifier() + ICommonConstants.UNDERSCORE_CHARACTER + LINK_TRACEABILITY_MAPPING;
  }

  protected String getLinkTraceabilityKey() {
    return _linkTraceabilityKey;
  }

  @Override
  protected List<EObject> retrieveRelatedElements(EObject source, IContext context, MappingTraceability maps) {
    if (!context.exists(getLinkTraceabilityKey())) {
      initializeRootMappings(context);
      context.put(getLinkTraceabilityKey(), getLinkTraceabilityKey());
    }
    ArrayList<EObject> result = new ArrayList<EObject>();
    Collection<EObject> mapped = maps.get(source);
    if (mapped != null) {
      result.addAll(mapped);
    }
    return result;
  }

  protected void initializeRootMappings(IContext context) {
    // Initialize root mapping
  }

  protected void initializeMappings(EObject source, EObject target, IContext context) {
    // For all elements from target, retrieve a source and addMapping
    addMappings(source, target, context);
    
    Iterator<EObject> iterator = target.eAllContents();
    while (iterator.hasNext()) {
      EObject object = iterator.next();
      for (EObject sourceAttachment : getSourceAttachments(object, context)) {
        if (initTraceabilityMapping(sourceAttachment, object, context)) {
          addMappings(sourceAttachment, object, context);
        }
      }
    }
  }

  /**
   * @param sourceAttachment
   * @param object
   * @param context
   * @return
   */
  protected boolean initTraceabilityMapping(EObject sourceAttachment, EObject object, IContext context) {
    return sourceAttachment != null;
  }

  protected List<EObject> getSourceAttachments(EObject targetElement, IContext context) {
    return Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

}
