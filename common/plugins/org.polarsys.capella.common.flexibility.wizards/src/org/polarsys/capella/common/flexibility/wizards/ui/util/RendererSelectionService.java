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

package org.polarsys.capella.common.flexibility.wizards.ui.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.ISources;

import org.polarsys.capella.common.flexibility.wizards.schema.IRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.ui.toolkit.viewers.menu.ContextMenuSelectionService;

/**
 * Abstract selection service.
 */
public class RendererSelectionService extends ContextMenuSelectionService {

  private IRendererContext _context;

  private IRenderer _renderer;

  /**
   * Creates a new SelectionService.
   */
  public RendererSelectionService(IRendererContext context, IRenderer renderer, ISelectionProvider activeProvider) {
    super(activeProvider);
    _context = context;
    _renderer = renderer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void selectionChanged(SelectionChangedEvent event) {
    fireSourceChanged(11, ISources.ACTIVE_CURRENT_SELECTION_NAME, event.getSelection());
    fireSelection(event.getSelection());
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
