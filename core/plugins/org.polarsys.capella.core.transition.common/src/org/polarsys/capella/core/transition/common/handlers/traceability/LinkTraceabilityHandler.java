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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class LinkTraceabilityHandler extends TwoSideTraceabilityHandler {

  private static final String LINK_TRACEABILITY_MAPPING = "LTM"; //$NON-NLS-1$

  private String _linkTraceabilityKey = null;

  public LinkTraceabilityHandler(String identifier_p) {
    super(identifier_p);
    _linkTraceabilityKey = getIdentifier() + ICommonConstants.UNDERSCORE_CHARACTER + LINK_TRACEABILITY_MAPPING;
  }

  protected String getLinkTraceabilityKey() {
    return _linkTraceabilityKey;
  }

  @Override
  protected List<EObject> retrieveRelatedElements(EObject source_p, IContext context_p, MappingTraceability maps_p) {
    if (!context_p.exists(getLinkTraceabilityKey())) {
      initializeRootMappings(context_p);
      context_p.put(getLinkTraceabilityKey(), getLinkTraceabilityKey());
    }
    ArrayList<EObject> result = new ArrayList<EObject>();
    Collection<EObject> mapped = maps_p.get(source_p);
    if (mapped != null) {
      result.addAll(mapped);
    }
    return result;
  }

  protected void initializeRootMappings(IContext context_p) {
    //Initialize root mapping
  }

  protected void initializeMappings(EObject source_p, EObject target_p, IContext context_p) {
    Iterator<EObject> iterator = target_p.eAllContents();

    //For all elements from target, retrieve a source and addMapping
    addMappings(source_p, target_p, context_p);
    while (iterator.hasNext()) {
      EObject object = iterator.next();
      for (EObject sourceAttachment : getSourceAttachments(object, context_p)) {

        if (initTraceabilityMapping(sourceAttachment, object, context_p)) {
          addMappings(sourceAttachment, object, context_p);
        }
      }
    }
  }

  /**
   * @param sourceAttachment_p
   * @param object_p
   * @param context_p
   * @return
   */
  protected boolean initTraceabilityMapping(EObject sourceAttachment_p, EObject object_p, IContext context_p) {
    return sourceAttachment_p != null;
  }

  protected List<EObject> getSourceAttachments(EObject targetElement_p, IContext context_p) {
    return Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

}
