/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

import javax.inject.Inject;

import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.action.ContributionManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.ISourceProvider;
import org.eclipse.ui.ISources;
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
import org.polarsys.capella.common.ui.toolkit.viewers.menu.ContextMenuSelectionService;

/**
 * This class is intended to be used to populate a ContributionManager for the
 * given ISelectionProvider instead of global selection.
 */
public class ToolbarPopulator implements ISelectionListener, PropertyChangeListener, IDisposable {

  @Inject
  IEclipseContext context;
  
  String location;
  IRenderer renderer;
  IServiceLocator parent;
  IServiceLocator locator;
  ISelectionProvider provider;
  IRendererContext rendererContext;
  ContributionManager contributionManager;

  /**
   * @param contributionManager
   * @param location
   * @param rendererContext
   * @param renderer
   * @param provider
   * @param parent
   */
  public ToolbarPopulator(ContributionManager contributionManager, String location, IRendererContext rendererContext,
      IRenderer renderer, ISelectionProvider provider, IServiceLocator parent) {
    this.contributionManager = contributionManager;
    this.location = location;
    this.provider = provider;
    this.parent = parent;
    this.renderer = renderer;
    this.rendererContext = rendererContext;
  }
  
  @SuppressWarnings("restriction")
  public void populate() {
    if ((location == null) || (location.isEmpty())) {
      return;
    }

    // We can avoid internal uses by providing ours implementations, but its
    // convenient for now.

    IServiceLocator parentLocator = this.parent;
    ServiceLocator locator = new ServiceLocator(parentLocator, null, this);
    locator.setContext(context);
    this.locator = locator;

    ContextMenuSelectionService newService = new RendererSelectionService(rendererContext, renderer, provider);
    newService.addSelectionListener(this);
    locator.registerService(ISelectionService.class, newService);

    IEvaluationService parentService = (IEvaluationService) parentLocator.getService(IEvaluationService.class);
    locator.registerService(IEvaluationService.class, parentService);
    parentService.addSourceProvider(newService);
    locator.registerService(ISourceProvider.class, newService);

    IHandlerService handlerService = new SlavePopulatorHandlerService(
        (IHandlerService) parentLocator.getService(IHandlerService.class), locator);
    locator.registerService(IHandlerService.class, handlerService);

    IMenuService  menuService = parentLocator.getService(IMenuService.class);
    IMenuService slaveMenuService = new SlavePopulatorMenuService((IMenuService) menuService, locator, null);
    slaveMenuService.populateContributionManager(contributionManager, location);
    selectionChanged(null, StructuredSelection.EMPTY);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    IEvaluationContext currentState = ((IHandlerService) context.get(IHandlerService.class)).getCurrentState();
    currentState.addVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME, selection);

    // This is to update the enablement state of the tool items in the toolbar
    {
      ISourceProvider provider = (ISourceProvider) locator.getService(ISourceProvider.class);
      IEvaluationService parentService = (IEvaluationService) this.parent.getService(IEvaluationService.class);
      parentService.removeSourceProvider(provider);
      parentService.addSourceProvider(provider);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(PropertyChangedEvent event) {
    ISelectionService service = (ISelectionService) locator.getService(ISelectionService.class);
    ISelectionChangedListener sChanged = (ISelectionChangedListener) service;
    sChanged.selectionChanged(new SelectionChangedEvent(provider, provider.getSelection()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    if ((parent != null) && (locator != null)) {
      ISourceProvider provider = (ISourceProvider) locator.getService(ISourceProvider.class);
      IEvaluationService parentService = (IEvaluationService) parent.getService(IEvaluationService.class);
      if (provider != null) {
        parentService.removeSourceProvider(provider);
      }
    }
  }
}