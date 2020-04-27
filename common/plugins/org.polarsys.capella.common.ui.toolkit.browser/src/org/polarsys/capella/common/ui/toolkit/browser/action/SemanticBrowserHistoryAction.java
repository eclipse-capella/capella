/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.toolkit.browser.action;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.polarsys.capella.common.ui.toolkit.browser.action.BrowserHistory.BrowserNavigationHistoryEntry;
import org.polarsys.capella.common.ui.toolkit.browser.label.provider.factory.AbstractLabelProviderFactory;
import org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart;

/**
 */
public class SemanticBrowserHistoryAction extends Action implements IMenuCreator, IWorkbenchAction {
  public static final String FORWARD_ACTION_ID = "forward"; //$NON-NLS-1$
  public static final String BACKWARD_ACTION_ID = "backward"; //$NON-NLS-1$
  private boolean _forward;
  private ISemanticBrowserViewPart _semanticBrowserViewPart;
  private BrowserHistory _browserHistory;

  /**
   * Action for History Item.
   */
  protected class HistoryItemAction extends Action {
    protected BrowserNavigationHistoryEntry _navigationEntry;

    /**
     * Constructor.
     * @param navigationEntry
     * @param text
     * @param imageDescriptor
     */
    public HistoryItemAction(BrowserNavigationHistoryEntry navigationEntry, String text, ImageDescriptor imageDescriptor) {
      super(text, imageDescriptor);
      // object related to the clicked item.
      _navigationEntry = navigationEntry;
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @SuppressWarnings("synthetic-access")
    @Override
    public void run() {
      _browserHistory.setDoUpdate(false);
      try {
        // update history model.
        _browserHistory.goTo(_navigationEntry);
        // update semantic browser.
        _semanticBrowserViewPart.setInput(_navigationEntry.getRealObject());
        _browserHistory.notifyActionListeners();
      } finally {
        // Make sure to re-enable the browser history.
        _browserHistory.setDoUpdate(true);
      }
    }
  }

  /**
   * Constructor.
   * @param window
   * @param semanticBrowserView
   * @param forward
   */
  public SemanticBrowserHistoryAction(IWorkbenchWindow window, ISemanticBrowserViewPart semanticBrowserView, boolean forward) {
    _semanticBrowserViewPart = semanticBrowserView;
    ISharedImages sharedImages = window.getWorkbench().getSharedImages();
    if (forward) {
      setText("&Forward"); //$NON-NLS-1$
      setToolTipText("Forward"); //$NON-NLS-1$
      setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_FORWARD));
      setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_FORWARD_DISABLED));
      setId(FORWARD_ACTION_ID);
    } else {
      setText("&Back"); //$NON-NLS-1$
      setToolTipText("Back"); //$NON-NLS-1$
      setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_BACK));
      setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_BACK_DISABLED));
      setId(BACKWARD_ACTION_ID);
    }

    _browserHistory = semanticBrowserView.getHistory();
    _browserHistory.addActionAsListener(this);
    _forward = forward;
    setMenuCreator(this);
    setEnabled(false);
  }

  /**
   * Create History action for given object.
   * @param navigationEntry
   * @return
   */
  private HistoryItemAction createHistoryAction(BrowserNavigationHistoryEntry navigationEntry) {
    // Precondition :
    if (!navigationEntry.isValid()) {
      return null;
    }
    Object realObject = navigationEntry.getRealObject();
    ILabelProvider labelProvider = AbstractLabelProviderFactory.getInstance().getCurrentLabelProvider();
    Image image = labelProvider.getImage(realObject);
    ImageDescriptor imgDescriptor = null;
    if (image != null) {
      imgDescriptor = ImageDescriptor.createFromImage(image);
    }
    HistoryItemAction goToAction = new HistoryItemAction(navigationEntry, labelProvider.getText(realObject), imgDescriptor);
    return goToAction;
  }

  /**
   * Get available history entries according forward or backward navigation.
   * @return
   */
  private List<BrowserNavigationHistoryEntry> getAvailableNavigationEntries() {
    List<BrowserNavigationHistoryEntry> historyEntries = null;
    if (_forward) {
      historyEntries = _browserHistory.getForwardNavigationEntries();
    } else {
      historyEntries = _browserHistory.getBackwardNavigationEntries();
      Collections.reverse(historyEntries);
    }
    return historyEntries;
  }

  /**
   * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Control)
   */
  public Menu getMenu(Control parent) {
    MenuManager menuManager = new MenuManager();
    final Menu menu = menuManager.createContextMenu(parent);
    menuManager.addMenuListener(new IMenuListener() {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      public void menuAboutToShow(IMenuManager manager) {
        // Retrieve entries for the menu.
        List<BrowserNavigationHistoryEntry> navigationEntries = getAvailableNavigationEntries();
        // Populate the menu with entries.
        for (BrowserNavigationHistoryEntry entry : navigationEntries) {
          HistoryItemAction historyEntryAction = createHistoryAction(entry);
          if (null != historyEntryAction) {
            manager.add(historyEntryAction);
          }
        }
      }
    });
    // Add a listener to automatically dispose the menu when it becomes hidden.
    final Display display = menu.getDisplay();
    menu.addListener(SWT.Hide, new Listener() {
      public void handleEvent(Event event) {
        if (!display.isDisposed()) {
          display.asyncExec(new Runnable() {
            public void run() {
              if (!menu.isDisposed()) {
                menu.dispose();
              }
            }
          });
        }
      }
    });
    return menu;
  }

  /**
   * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Menu)
   */
  public Menu getMenu(Menu parent) {
    // Not applicable.
    return null;
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    Iterator<BrowserNavigationHistoryEntry> availableHistoryEntries = getAvailableNavigationEntries().iterator();
    if (availableHistoryEntries.hasNext()) {
      // Create an action to go to the first available entry.
      HistoryItemAction historyAction = createHistoryAction(availableHistoryEntries.next());
      while (null == historyAction) {
        historyAction = createHistoryAction(availableHistoryEntries.next());
      }
      historyAction.run();
    }
  }

  /**
   * Update control state.
   */
  protected void updateControlState() {
    boolean enabled = false;
    if (_forward) {
      if (_browserHistory.getForwardNavigationEntries().size() > 0) {
        enabled = true;
      }
    } else {
      if (_browserHistory.getBackwardNavigationEntries().size() > 0) {
        enabled = true;
      }
    }
    setEnabled(enabled);
  }

  /**
   * {@inheritDoc}
   */
  public void dispose() {
    // Do nothing.
  }
}
