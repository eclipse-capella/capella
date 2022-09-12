/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.toolkit.browser.content.provider.impl;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IViewerNotification;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.polarsys.capella.common.ui.services.helper.ViewerHelper;
import org.polarsys.capella.common.ui.toolkit.browser.BrowserActivator;
import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryRegistry;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.IBrowserContentProvider;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.BrowserElementWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.CategoryWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.PrimitiveWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.TechnicalCategoryWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.model.ISemanticBrowserModel;
import org.polarsys.capella.common.ui.toolkit.browser.query.QueryAdapter;
import org.polarsys.capella.common.ui.toolkit.provider.GroupedAdapterFactoryContentProvider;

/**
 * Controller.
 */
public abstract class AbstractContentProvider extends GroupedAdapterFactoryContentProvider
    implements IBrowserContentProvider {
  /**
   * Root element i.e the element given as initial input.
   */
  protected EObject rootElement;

  /**
   * Flag that indicates input has changed.
   */
  protected boolean inputHasChanged;

  /**
   * Weak hash map for seeking semantic container of an element (category or object)
   */
  protected HashMap<BrowserElementWrapper, BrowserElementWrapper> semanticParentHashMap;

  /**
   * Hashmap to cache category children for a given element, so that compute isn't called more than once
   */
  protected HashMap<EObject, HashMap<ICategory, Set<Object>>> categoryChildrenCache;

  /**
   * Hashmap to cache direct categories for a given element, so that compute isn't called more than once
   */
  protected HashMap<EObject, Set<ICategory>> directCategoriesCache;

  /**
   * Hashmap to cache item queries for a given element, so that compute isn't called more than once
   */
  protected HashMap<EObject, HashMap<ICategory, Set<Object>>> itemQueriesCache;

  protected boolean isRefreshRequired = false;

  protected ISemanticBrowserModel model;

  /**
   * Constructor.
   */
  public AbstractContentProvider(AdapterFactory adapterFactory, ISemanticBrowserModel model) {
    super(adapterFactory);
    this.model = model;
    semanticParentHashMap = new HashMap<>(0);
    categoryChildrenCache = new HashMap<>(0);
    directCategoriesCache = new HashMap<>(0);
    itemQueriesCache = new HashMap<>(0);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    semanticParentHashMap.clear();
    clearCaches();
    super.dispose();
  }

  /**
   * Get category children.
   * 
   * @param wrapper
   * @param element
   * @param gatheredElements
   * @param gatheredCategories
   */
  protected void getCategoryChildren(ICategory category, BrowserElementWrapper wrapper,
      LinkedHashSet<Object> gatheredElements) {
    // lookup for the element that we need to query on.
    EObject elementToQuery = lookUpModelElement(wrapper);

    HashMap<ICategory, Set<Object>> modelElementCategoryChildren = categoryChildrenCache.get(elementToQuery);
    if (modelElementCategoryChildren == null) {
      modelElementCategoryChildren = new HashMap<ICategory, Set<Object>>();
      categoryChildrenCache.put(elementToQuery, modelElementCategoryChildren);
    }

    // Try to get already computed values from cache
    Set<Object> categoryChildren = modelElementCategoryChildren.get(category);
    if (categoryChildren == null) {
      categoryChildren = new LinkedHashSet<Object>();

      // compute query attached to the category.
      categoryChildren.addAll(category.compute(elementToQuery));

      // Gather subCategories & compute queries attached to the category.
      categoryChildren
          .addAll(CategoryRegistry.getInstance().gatherSubCategories(getBrowserId(), elementToQuery, category));
      modelElementCategoryChildren.put(category, categoryChildren);
    }
    gatheredElements.addAll(categoryChildren);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object[] getChildren(Object parentElement) {
    Object[] result = new Object[0];
    try {
      if (parentElement instanceof BrowserElementWrapper) {
        BrowserElementWrapper wrapper = (BrowserElementWrapper) parentElement;
        // retrieve referenced element by the wrapper.
        Object element = ((BrowserElementWrapper) parentElement).getElement();
        LinkedHashSet<Object> gatheredElements = new LinkedHashSet<>(0);
        if (wrapper instanceof EObjectWrapper) {
          // Provide the root element to the CurrentElement Browser in purpose to display it.
          if ((element == rootElement) && inputHasChanged) {
            // Root element has no parent : store null value.
            semanticParentHashMap.put(wrapper, null);
            inputHasChanged = false;
            if (getBrowserId().equalsIgnoreCase(IBrowserContentProvider.ID_CURRENT_CP)) {
              return new Object[] { wrapper };
            }
          }
          getElementChildren((EObject) element, wrapper, gatheredElements);
        } else if (wrapper instanceof CategoryWrapper) {
          getCategoryChildren((ICategory) element, wrapper, gatheredElements);
        }

        /**
         * Wrap gathered elements & register each wrapper in cache.
         */
        LinkedHashSet<Object> wrappers = new LinkedHashSet<>(0);
        for (Object gatherElement : gatheredElements) {
          // ignore queries result that returns a null object (reference with cardinality max 1)
          if (gatherElement != null) {
            BrowserElementWrapper elementWrapper = wrapElement(gatherElement);
            // Add wrapper and element wrapper in internal data and returned collection.
            wrappers.add(elementWrapper);
            semanticParentHashMap.put(elementWrapper, wrapper);
            // Flag to filter out empty category.
            boolean shouldRemovedEmptyCategoryWrapper = false;
            if (gatherElement instanceof ICategory) {
              if (!model.doesShowCategory((ICategory) gatherElement)) {
                shouldRemovedEmptyCategoryWrapper = true;
              } else {
                LinkedHashSet<Object> categoryChildren = new LinkedHashSet<>(0);
                // Compute category children, if no child, remove this category from displayed elements.
                getCategoryChildren((ICategory) gatherElement, elementWrapper, categoryChildren);
                if (categoryChildren.isEmpty()) {
                  shouldRemovedEmptyCategoryWrapper = true;
                }
              }
            }
            if (shouldRemovedEmptyCategoryWrapper) {
              wrappers.remove(elementWrapper);
              semanticParentHashMap.remove(elementWrapper);
            }
          }
        }

        result = wrappers.toArray();
      } else {
        // Wrap given element. This input element can't be a Category because a category element is computed.
        result = getChildren(new EObjectWrapper((EObject) parentElement));
      }
    } catch (Exception exception) {
      BrowserActivator.getDefault().getLog().log(new Status(IStatus.ERROR, BrowserActivator.PLUGIN_ID,
          "Error while getting children for " + parentElement, exception)); //$NON-NLS-1$
      result = new Object[0];
    }
    return result;
  }

  /**
   * Ge element children.
   * 
   * @param wrapper
   * @param gatheredElements
   * @param gatheredCategories
   * @param modelElement
   */
  protected void getElementChildren(EObject modelElement, BrowserElementWrapper wrapper, Set<Object> gatheredElements) {
    if (modelElement != rootElement) {
      // If it's an item gather sub-queries from parent category.
      BrowserElementWrapper semanticParentWrapper = semanticParentHashMap.get(wrapper);
      if (null != semanticParentWrapper) {
        Object semanticParent = semanticParentWrapper.getElement();
        if (semanticParent instanceof ICategory) {
          // temporary result (list typed)
          ICategory category = (ICategory) semanticParent;
          HashMap<ICategory, Set<Object>> modelElementItemQueries = itemQueriesCache.get(modelElement);
          if (modelElementItemQueries == null) {
            modelElementItemQueries = new HashMap<ICategory, Set<Object>>();
            itemQueriesCache.put(modelElement, modelElementItemQueries);
          }

          Set<Object> itemQueries = modelElementItemQueries.get(category);
          if (itemQueries == null) {
            itemQueries = new LinkedHashSet<Object>();
            for (Object query : category.getItemQueries()) {
              // compute item queries with model element as context.
              itemQueries.addAll(QueryAdapter.getInstance().compute(modelElement, query));
            }
            modelElementItemQueries.put(category, itemQueries);
          }
          gatheredElements.addAll(itemQueries);
        }
      }
    }

    // Gather direct categories for any model element.
    BrowserElementWrapper parentWrapper = semanticParentHashMap.get(wrapper);
    if (null == semanticParentHashMap.get(parentWrapper)) // for blocking recursion
    {
      // Try to get already computed values from cache
      Set<ICategory> categories = directCategoriesCache.get(modelElement);
      if (categories == null) {
        categories = new LinkedHashSet<ICategory>();
        categories.addAll(CategoryRegistry.getInstance().gatherCategories(getBrowserId(), modelElement));
        directCategoriesCache.put(modelElement, categories);
      }
      gatheredElements.addAll(categories);
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object[] getElements(Object rootElement) {
    return getChildren(rootElement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getParent(Object element) {
    return semanticParentHashMap.get(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasChildren(Object element) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    if (null == this.viewer) {
      this.viewer = viewer;
    }
    if (newInput instanceof BrowserElementWrapper) {
      if (oldInput == null) {
        inputChanged(viewer, new EObjectWrapper(null), ((BrowserElementWrapper) newInput).getElement());
      } else if (oldInput instanceof BrowserElementWrapper) {
        inputChanged(viewer, ((BrowserElementWrapper) oldInput).getElement(),
            ((BrowserElementWrapper) newInput).getElement());
      } else if (oldInput instanceof EObject) {
        inputChanged(viewer, oldInput, ((BrowserElementWrapper) newInput).getElement());
      }
    } else if (newInput instanceof EObject) {
      // clear cache.
      semanticParentHashMap.clear();
      clearCaches();
      inputHasChanged = true;
      rootElement = (EObject) newInput;
    } else if (null == newInput) {
      // View is closing or no input selection.
      inputHasChanged = false;
      rootElement = null;
      semanticParentHashMap.clear();
      clearCaches();
    }
  }

  /**
   * Look up a model element for specified wrapper.
   * 
   * @param wrapper
   * @return
   */
  private EObject lookUpModelElement(BrowserElementWrapper wrapper) {
    BrowserElementWrapper parentWrapper = semanticParentHashMap.get(wrapper);
    if (parentWrapper instanceof CategoryWrapper) {
      return lookUpModelElement(parentWrapper);
    }
    return (EObject) parentWrapper.getElement();
  }

  @Override
  protected boolean refreshRequired(ResourceSetChangeEvent event) {
    boolean result = false;
    synchronized (this) {
      if (isRefreshRequired) {
        result = true;
      }
    }
    return result || super.refreshRequired(event);
  }

  /**
   * Overridden to forward EObject notifications to wrapped equivalent objects.<br>
   * {@inheritDoc}
   */
  @Override
  public void notifyChanged(Notification notification) {

    if (notification instanceof IViewerNotification) {
      final IViewerNotification viewerNotification = (IViewerNotification) notification;
      if (!viewerNotification.isContentRefresh()) {
        Object element = viewerNotification.getElement();
        Object wrappedElement = wrapElement(element);
        if (null != wrappedElement) {
          super.notifyChanged(ViewerNotification.wrapNotification(viewerNotification, wrappedElement));
        }
      } else {
        // Impossible to know if modifications from other AbstractContentProvider instances in the semantic browser have
        // impacts on current one.
        // Let's refresh completely the viewer.
        // refresh fails here due to the poor content provider implementation based on EObjectWrapper & co.
        synchronized (this) {
          if (!isRefreshRequired) {
            isRefreshRequired = true;
          }
        }
      }
    }
  }

  @Override
  protected void processRefresh() {
    boolean refresh = false;

    // If we need to refresh complete view, we refresh it and don't perform any refresh for all others notifications
    synchronized (this) {
      refresh = isRefreshRequired;
      isRefreshRequired = false;

      if (refresh) {
        notifications = null;
        toRefresh = null;
      }
    }

    if (!refresh) {
      super.processRefresh();

    } else {
      if ((viewer != null) && (viewer.getControl() != null) && !viewer.getControl().isDisposed()) {
        ViewerHelper.run((StructuredViewer) viewer, new Runnable() {
          @Override
          @SuppressWarnings("synthetic-access")
          public void run() {
            Object input = viewer.getInput();
            viewer.setInput(input);
          }
        });
      }
    }

  }

  /**
   * Wrap element in the proper wrapper.
   * 
   * @param gatherElement
   * @return
   */
  private BrowserElementWrapper wrapElement(Object gatherElement) {
    BrowserElementWrapper wrapper = null;
    if (gatherElement instanceof EObject) {
      wrapper = new EObjectWrapper((EObject) gatherElement);
    } else if (gatherElement instanceof ICategory) {
      if (((ICategory) gatherElement).isTechnical()) {
        wrapper = new TechnicalCategoryWrapper((ICategory) gatherElement);
      } else {
        wrapper = new CategoryWrapper((ICategory) gatherElement);
      }
    } else if (gatherElement instanceof Object) {
      wrapper = new PrimitiveWrapper(gatherElement);
    }
    return wrapper;
  }

  /**
   * Get root element.
   * 
   * @return
   */
  public EObject getRootElement() {
    return rootElement;
  }

  protected void clearCaches() {
    categoryChildrenCache.clear();
    directCategoriesCache.clear();
    itemQueriesCache.clear();
  }
}
