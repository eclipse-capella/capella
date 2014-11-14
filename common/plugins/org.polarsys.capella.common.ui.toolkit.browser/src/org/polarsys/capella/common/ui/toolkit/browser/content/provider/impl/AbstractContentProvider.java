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
package org.polarsys.capella.common.ui.toolkit.browser.content.provider.impl;

import java.util.HashMap;
import java.util.HashSet;
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
import org.polarsys.capella.common.ui.toolkit.provider.GroupedAdapterFactoryContentProvider;
import org.polarsys.capella.common.ui.toolkit.browser.BrowserActivator;
import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryRegistry;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.IBrowserContentProvider;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.BrowserElementWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.CategoryWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.query.QueryAdapter;

/**
 * Controller.
 */
public abstract class AbstractContentProvider extends GroupedAdapterFactoryContentProvider implements IBrowserContentProvider {
  /**
   * Root element i.e the element given as initial input.
   */
  protected EObject _rootElement;

  /**
   * Flag that indicates input has changed.
   */
  protected boolean _inputHasChanged;

  /**
   * Weak hash map for seeking semantic container of an element (category or object)
   */
  protected HashMap<BrowserElementWrapper, BrowserElementWrapper> _semanticParentHashMap;

  protected boolean refreshRequired = false;

  /**
   * Constructor.
   */
  public AbstractContentProvider(AdapterFactory adapterFactory_p) {
    super(adapterFactory_p);
    _semanticParentHashMap = new HashMap<BrowserElementWrapper, BrowserElementWrapper>(0);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    _semanticParentHashMap.clear();
    super.dispose();
  }

  /**
   * Get category children.
   * @param wrapper
   * @param element
   * @param gatheredElements
   * @param gatheredCategories
   */
  protected void getCategoryChildren(ICategory category_p, BrowserElementWrapper wrapper, Set<Object> gatheredElements) {
    // lookup for the element that we need to query on.
    EObject elementToQuery = lookUpModelElement(wrapper);

    // Gather subCategories & compute queries attached to the category.
    gatheredElements.addAll(category_p.compute(elementToQuery));
    gatheredElements.addAll(CategoryRegistry.getInstance().gatherSubCategories(getBrowserId(), elementToQuery, category_p));
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
        Set<Object> gatheredElements = new HashSet<Object>(0);
        if (wrapper instanceof EObjectWrapper) {
          // Provide the root element to the CurrentElement Browser in purpose to display it.
          if ((element == _rootElement) && _inputHasChanged) {
            // Root element has no parent : store null value.
            _semanticParentHashMap.put(wrapper, null);
            _inputHasChanged = false;
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
        Set<Object> wrappers = new HashSet<Object>(0);
        for (Object gatherElement : gatheredElements) {
          // ignore queries result that returns a null object (reference with cardinality max 1)
          if (gatherElement != null) {
            BrowserElementWrapper elementWrapper = wrapElement(gatherElement);
            // Add wrapper and element wrapper in internal data and returned collection.
            wrappers.add(elementWrapper);
            _semanticParentHashMap.put(elementWrapper, wrapper);
            // Flag to filter out empty category.
            boolean shouldRemovedEmptyCategoryWrapper = false;
            if (gatherElement instanceof ICategory) {
              Set<Object> categoryChildren = new HashSet<Object>(0);
              // Compute category children, if no child, remove this category from displayed elements.
              getCategoryChildren((ICategory) gatherElement, elementWrapper, categoryChildren);
              if (categoryChildren.isEmpty()) {
                shouldRemovedEmptyCategoryWrapper = true;
              }
            }
            if (shouldRemovedEmptyCategoryWrapper) {
              wrappers.remove(elementWrapper);
              _semanticParentHashMap.remove(elementWrapper);
            }
          }
        }

        result = wrappers.toArray();
      } else {
        // Wrap given element. This input element can't be a Category because a category element is computed.
        result = getChildren(new EObjectWrapper(parentElement));
      }
    } catch (Exception exception_p) {
      BrowserActivator.getDefault().getLog()
          .log(new Status(IStatus.ERROR, BrowserActivator.PLUGIN_ID, "Error while getting children for " + parentElement, exception_p)); //$NON-NLS-1$
      result = new Object[0];
    }
    return result;
  }

  /**
   * Ge element children.
   * @param wrapper
   * @param gatheredElements
   * @param gatheredCategories
   * @param modelElement
   */
  protected void getElementChildren(EObject modelElement, BrowserElementWrapper wrapper, Set<Object> gatheredElements) {
    if (modelElement != _rootElement) {
      // If it's an item gather sub-queries from parent category.
      BrowserElementWrapper semanticParentWrapper = _semanticParentHashMap.get(wrapper);
      if (null != semanticParentWrapper) {
        Object semanticParent = semanticParentWrapper.getElement();
        if (semanticParent instanceof ICategory) {
          // temporary result (list typed)
          for (Object query : ((ICategory) semanticParent).getItemQueries()) {
            // compute item queries with model element as context.
            gatheredElements.addAll(QueryAdapter.getInstance().compute(modelElement, query));
          }
        }
      }
    }

    // Gather direct categories for any model element.
    BrowserElementWrapper parentWrapper = _semanticParentHashMap.get(wrapper);
    if (null == _semanticParentHashMap.get(parentWrapper)) // for blocking recursion
    {
      gatheredElements.addAll(CategoryRegistry.getInstance().gatherCategories(getBrowserId(), modelElement));
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object[] getElements(Object rootElement_p) {
    return getChildren(rootElement_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getParent(Object element_p) {
    return _semanticParentHashMap.get(element_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasChildren(Object element_p) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void inputChanged(Viewer viewer_p, Object oldInput, Object newInput) {
    if (null == viewer) {
      viewer = viewer_p;
    }
    if (newInput instanceof BrowserElementWrapper) {
      if (oldInput == null) {
        inputChanged(viewer_p, new EObjectWrapper(null), ((BrowserElementWrapper) newInput).getElement());
      } else if (oldInput instanceof BrowserElementWrapper) {
        inputChanged(viewer_p, ((BrowserElementWrapper) oldInput).getElement(), ((BrowserElementWrapper) newInput).getElement());
      } else if (oldInput instanceof EObject) {
        inputChanged(viewer_p, oldInput, ((BrowserElementWrapper) newInput).getElement());
      }
    } else if (newInput instanceof EObject) {
      // clear cache.
      _semanticParentHashMap.clear();
      _inputHasChanged = true;
      _rootElement = (EObject) newInput;
    } else if (null == newInput) {
      // View is closing or no input selection.
      _inputHasChanged = false;
      _rootElement = null;
      _semanticParentHashMap.clear();
    }
  }

  /**
   * Look up a model element for specified wrapper.
   * @param wrapper_p
   * @return
   */
  private EObject lookUpModelElement(BrowserElementWrapper wrapper_p) {
    BrowserElementWrapper parentWrapper = _semanticParentHashMap.get(wrapper_p);
    if (parentWrapper instanceof CategoryWrapper) {
      return lookUpModelElement(parentWrapper);
    }
    return (EObject) parentWrapper.getElement();
  }

  @Override
  protected boolean refreshRequired(ResourceSetChangeEvent event_p) {
    boolean result = false;
    synchronized (this) {
      if (refreshRequired) {
        result = true;
      }
    }
    return result || super.refreshRequired(event_p);
  }

  /**
   * Overridden to forward EObject notifications to wrapped equivalent objects.<br>
   * {@inheritDoc}
   */
  @Override
  public void notifyChanged(Notification notification_p) {

    if (notification_p instanceof IViewerNotification) {
      final IViewerNotification viewerNotification = (IViewerNotification) notification_p;
      if (!viewerNotification.isContentRefresh()) {
        Object element = viewerNotification.getElement();
        Object wrappedElement = wrapElement(element);
        if (null != wrappedElement) {
          super.notifyChanged(ViewerNotification.wrapNotification(viewerNotification, wrappedElement));
        }
      } else {
        // Impossible to know if modifications from other AbstractContentProvider instances in the semantic browser have impacts on current one.
        // Let's refresh completely the viewer.
        // refresh fails here due to the poor content provider implementation based on EObjectWrapper & co.
        synchronized (this) {
          if (!refreshRequired) {
            refreshRequired = true;
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
      refresh = refreshRequired;
      refreshRequired = false;

      if (refreshRequired) {
        notifications = null;
        toRefresh = null;
      }
    }

    if (!refresh) {
      super.processRefresh();

    } else {
      if ((viewer != null) && (viewer.getControl() != null) && !viewer.getControl().isDisposed()) {
        ViewerHelper.run((StructuredViewer) viewer, new Runnable() {
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
   * @param gatherElement_p
   * @return
   */
  private BrowserElementWrapper wrapElement(Object gatherElement_p) {
    BrowserElementWrapper wrapper = null;
    if ((gatherElement_p != null) && (gatherElement_p instanceof EObject)) {
      wrapper = new EObjectWrapper(gatherElement_p);
    } else if (gatherElement_p instanceof ICategory) {
      wrapper = new CategoryWrapper(gatherElement_p);
    }
    return wrapper;
  }

  /**
   * Get root element.
   * @return
   */
  public EObject getRootElement() {
    return _rootElement;
  }
}
