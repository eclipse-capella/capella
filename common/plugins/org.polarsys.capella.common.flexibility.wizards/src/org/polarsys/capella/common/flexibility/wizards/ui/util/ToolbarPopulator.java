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

import org.eclipse.jface.action.ContributionManager;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.ISourceProvider;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.services.ServiceLocator;
import org.eclipse.ui.menus.IMenuService;
import org.eclipse.ui.services.IDisposable;
import org.eclipse.ui.services.IEvaluationService;
import org.eclipse.ui.services.IServiceLocator;
import org.polarsys.capella.common.flexibility.properties.PropertyChangeListener;
import org.polarsys.capella.common.flexibility.properties.PropertyChangedEvent;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 * This class is intended to be used to populate a ContributionManager for the given ISelectionProvider instead of global selection.
 */
public class ToolbarPopulator implements ISelectionListener, PropertyChangeListener, IDisposable {

  ContributionManager _contributionManager;
  String _location;
  ISelectionProvider _provider;
  IServiceLocator _parent;
  IServiceLocator _locator;

  IRendererContext _rendererContext;
  IRenderer _renderer;

  /**
   * @param contributionManager_p
   * @param selectListRenderer_p
   * @param rendererContext_p
   * @param viewer_p
   * @param activeWorkbenchWindow_p
   */
  public ToolbarPopulator(ContributionManager contributionManager_p, String location_p, IRendererContext rendererContext_p, IRenderer renderer_p,
      ISelectionProvider provider_p, IServiceLocator parent_p) {
    _contributionManager = contributionManager_p;
    _location = location_p;
    _provider = provider_p;
    _parent = parent_p;

    _renderer = renderer_p;
    _rendererContext = rendererContext_p;
  }

  public void populate() {
    if ((_location == null) || (_location.isEmpty())) {
      return;
    }

    // We can avoid internal uses by providing ours implementations, but its convenient for now.

    IServiceLocator parentLocator = _parent;
    ServiceLocator locator = new ServiceLocator(parentLocator, null, this);
    _locator = locator;

    SourceSelectionService newService = new RendererSelectionService(_rendererContext, _renderer, _provider);
    newService.addSelectionListener(this);
    locator.registerService(ISelectionService.class, newService);

    IEvaluationService parentService = (IEvaluationService) parentLocator.getService(IEvaluationService.class);
    locator.registerService(IEvaluationService.class, parentService);
    parentService.addSourceProvider(newService);
    locator.registerService(ISourceProvider.class, newService);

    IHandlerService handlerService = new SlavePopulatorHandlerService((IHandlerService) parentLocator.getService(IHandlerService.class), locator);
    locator.registerService(IHandlerService.class, handlerService);

    IMenuService menuService = (IMenuService) parentLocator.getService(IMenuService.class);
    menuService = new SlavePopulatorMenuService((IMenuService) menuService, locator, null);
    locator.registerService(IMenuService.class, menuService);
    menuService.populateContributionManager(_contributionManager, _location);
    selectionChanged(null, StructuredSelection.EMPTY);

    _rendererContext.getPropertyContext().registerListener(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void selectionChanged(IWorkbenchPart part_p, ISelection selection_p) {
    _contributionManager.update(true);
    for (IContributionItem item : _contributionManager.getItems()) {
      item.isEnabled();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(PropertyChangedEvent event_p) {
    ISelectionService service = (ISelectionService) _locator.getService(ISelectionService.class);
    ISelectionChangedListener sChanged = (ISelectionChangedListener) service;
    sChanged.selectionChanged(new SelectionChangedEvent(_provider, _provider.getSelection()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    if ((_parent != null) && (_locator != null)) {
      ISourceProvider provider = (ISourceProvider) _locator.getService(ISourceProvider.class);
      IEvaluationService parentService = (IEvaluationService) _parent.getService(IEvaluationService.class);
      if (provider != null) {
        parentService.removeSourceProvider(provider);
      }
    }
  }

}
