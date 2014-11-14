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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.jface.action.ContributionManager;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.ISourceProvider;
import org.eclipse.ui.ISources;
import org.eclipse.ui.internal.menus.InternalMenuService;
import org.eclipse.ui.internal.menus.MenuLocationURI;
import org.eclipse.ui.menus.AbstractContributionFactory;
import org.eclipse.ui.services.IServiceLocator;

/**
 * <p>
 * Provides services related to contributing menu elements to a workbench
 * window. Visibility and showing are tracked at the workbench window level.
 * </p>
 * <p>
 * This class is only intended for internal use within the
 * <code>org.eclipse.ui.workbench</code> plug-in.
 * </p>
 * 
 * @since 3.2
 */
public class SlavePopulatorMenuService extends InternalMenuService {

  private Collection providers = new ArrayList();
  private Collection factories = new ArrayList();

  /**
   * The parent menu service for this window. This parent must track menu
   * definitions and the registry. Must not be <code>null</code>
   */
  private final InternalMenuService parent;
  private IServiceLocator serviceLocator;
  private Set restrictionExpression;

  /**
   * Constructs a new instance of <code>MenuService</code> using a menu
   * manager.
   * 
   * @param parent
   * 		The parent menu service for this window. This parent must track menu
   * 		definitions and the regsitry. Must not be <code>null</code>
   */
  public SlavePopulatorMenuService(InternalMenuService parent, final IServiceLocator serviceLocator, Set restrictions) {
    restrictionExpression = restrictions;

    this.parent = parent;
    this.serviceLocator = serviceLocator;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ui.internal.menus.IMenuService#populateMenu(org.eclipse.jface
   * .action.MenuManager, org.eclipse.ui.internal.menus.MenuLocationURI)
   */
  public void populateContributionManager(ContributionManager mgr, String uri) {
    parent.populateContributionManager(serviceLocator, restrictionExpression, mgr, uri, true);
  }

  @Override
  public void populateContributionManager(ContributionManager mgr, String uri, boolean recurse) {
    parent.populateContributionManager(serviceLocator, restrictionExpression, mgr, uri, recurse);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.internal.menus.IMenuService#getCurrentState()
   */
  public IEvaluationContext getCurrentState() {
    IEvaluationContext parentA = parent.getCurrentState();
    Object value = ((ISelectionService) serviceLocator.getService(ISelectionService.class)).getSelection();
    if (value == null) {
      value = new StructuredSelection();
    }
    IEvaluationContext context = new EvaluationContext(parentA, ((IStructuredSelection) value).toList());
    context.addVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME, value);
    return context;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ui.internal.menus.IMenuService#addCacheForURI(org.eclipse
   * .ui.internal.menus.MenuLocationURI,
   * org.eclipse.ui.internal.menus.MenuCacheEntry)
   */
  public void addContributionFactory(AbstractContributionFactory cache) {
    if (!factories.contains(cache)) {
      factories.add(cache);
    }
    parent.addContributionFactory(cache);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ui.internal.menus.IMenuService#releaseMenu(org.eclipse.jface
   * .action.ContributionManager)
   */
  public void releaseContributions(ContributionManager mgr) {
    parent.releaseContributions(mgr);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ui.menus.IMenuService#removeContributionFactory(org.eclipse
   * .ui.menus.AbstractContributionFactory)
   */
  public void removeContributionFactory(AbstractContributionFactory factory) {
    factories.remove(factory);
    parent.removeContributionFactory(factory);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.services.IDisposable#dispose()
   */
  public void dispose() {
    if (!providers.isEmpty()) {
      Object[] array = providers.toArray();
      for (Object element : array) {
        parent.removeSourceProvider((ISourceProvider) element);
      }
      providers.clear();
    }
    if (!factories.isEmpty()) {
      Object[] array = factories.toArray();
      for (Object element : array) {
        parent.removeContributionFactory((AbstractContributionFactory) element);
      }
      factories.clear();
    }
    restrictionExpression = null;
    serviceLocator = null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ui.services.IServiceWithSources#addSourceProvider(org.eclipse
   * .ui.ISourceProvider)
   */
  public void addSourceProvider(ISourceProvider provider) {
    if (!providers.contains(provider)) {
      providers.add(provider);
    }
    parent.addSourceProvider(provider);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ui.services.IServiceWithSources#removeSourceProvider(org.
   * eclipse.ui.ISourceProvider)
   */
  public void removeSourceProvider(ISourceProvider provider) {
    providers.remove(provider);
    parent.removeSourceProvider(provider);
  }

  @Override
  public List getAdditionsForURI(MenuLocationURI uri) {
    return parent.getAdditionsForURI(uri);
  }

  @Override
  public void registerVisibleWhen(final IContributionItem item, final Expression visibleWhen, final Set restriction, String identifierID) {
    parent.registerVisibleWhen(item, visibleWhen, restriction, identifierID);
  }

  @Override
  public void unregisterVisibleWhen(IContributionItem item, final Set restriction) {
    parent.unregisterVisibleWhen(item, restriction);
  }

  @Override
  public void populateContributionManager(IServiceLocator serviceLocatorToUse, Set restriction, ContributionManager mgr, String uri, boolean recurse) {
    parent.populateContributionManager(serviceLocatorToUse, restriction, mgr, uri, recurse);
  }
}
