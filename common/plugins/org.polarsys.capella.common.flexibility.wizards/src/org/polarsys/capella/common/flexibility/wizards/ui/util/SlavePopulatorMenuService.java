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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.jface.action.ContributionManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.ISourceProvider;
import org.eclipse.ui.ISources;
import org.eclipse.ui.menus.AbstractContributionFactory;
import org.eclipse.ui.menus.IMenuService;
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
public class SlavePopulatorMenuService implements IMenuService {

  private Collection providers = new ArrayList();
  private Collection factories = new ArrayList();

  /**
   * The parent menu service for this window. This parent must track menu
   * definitions and the registry. Must not be <code>null</code>
   */
  private final IMenuService parent;
  private IServiceLocator serviceLocator;
  private Set restrictionExpression;

  /**
   * Constructs a new instance of <code>MenuService</code> using a menu
   * manager.
   * 
   * @param parent
   *    The parent menu service for this window. This parent must track menu
   *    definitions and the regsitry. Must not be <code>null</code>
   */
  public SlavePopulatorMenuService(IMenuService parent, final IServiceLocator serviceLocator, Set restrictions) {
    restrictionExpression = restrictions;

    this.parent = parent;
    this.serviceLocator = serviceLocator;
  }

  @Override
  public void populateContributionManager(ContributionManager mgr, String uri) {
	parent.populateContributionManager(mgr, uri);
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

}
