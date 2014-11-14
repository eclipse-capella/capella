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
package org.polarsys.capella.common.flexibility.wizards.ui.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.ISources;

import org.polarsys.capella.common.flexibility.wizards.schema.IRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 * Abstract selection service.
 */
public class RendererSelectionService extends SourceSelectionService {

  private IRendererContext _context;

  private IRenderer _renderer;

  /**
   * Creates a new SelectionService.
   */
  public RendererSelectionService(IRendererContext context_p, IRenderer renderer_p, ISelectionProvider activeProvider_p) {
    super(activeProvider_p);
    _context = context_p;
    _renderer = renderer_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void selectionChanged(SelectionChangedEvent event_p) {
    fireSourceChanged(11, ISources.ACTIVE_CURRENT_SELECTION_NAME, event_p.getSelection());
    fireSelection(event_p.getSelection());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map getCurrentState() {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put(ISources.ACTIVE_CURRENT_SELECTION_NAME, getSelection());
    map.put(IRenderer.EXECUTION_EVENT__RENDERER, getRenderer());
    map.put(IRenderer.EXECUTION_EVENT__RENDERER_CONTEXT, getRendererContext());
    return map;
  }

  /**
   * @return
   */
  private IRendererContext getRendererContext() {
    return _context;
  }

  /**
   * @return
   */
  private IRenderer getRenderer() {
    return _renderer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getProvidedSourceNames() {
    return new String[] { "selection", IRenderer.EXECUTION_EVENT__RENDERER, IRenderer.EXECUTION_EVENT__RENDERER_CONTEXT };
  }

}
