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
package org.polarsys.capella.core.ui.fastlinker.view;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.model.links.helpers.commands.AbstractCreateLinksCommand;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.CapellaNavigatorLabelProvider;
import org.polarsys.capella.core.ui.fastlinker.FastLinkerActivator;
import org.polarsys.capella.core.ui.fastlinker.FastLinkerState;
import org.polarsys.capella.core.ui.properties.CapellaTabbedPropertySheetPage;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 * The FastLinker graphical view.
 */
public class FastLinkerView extends ViewPart implements ITabbedPropertySheetPageContributor, IEditingDomainProvider {

  /**
   * The Capella project explorer view identifier.
   */
  public static final String ID = "org.polarsys.capella.core.ui.fastlinker.view"; //$NON-NLS-1$ 

  /**
   * The FastLinker pin menu creator.
   */
  protected class PinDropDownMenuCreator implements IMenuCreator {
    private Menu _menu;

    protected MenuItem createPinMenuItem(Menu parentMenu_p, final ModelElement modelElement_p, ModelElement pinnedElement_p) {
      MenuItem menuItem = new MenuItem(parentMenu_p, SWT.CHECK);
      menuItem.setImage(_capellaNavigatorLabelProvider.getImage(modelElement_p));
      menuItem.setText(_capellaNavigatorLabelProvider.getText(modelElement_p));
      menuItem.setSelection(modelElement_p == pinnedElement_p);
      menuItem.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent e_p) {
          FastLinkerActivator.getDefault().getFastLinkerManager().pinModelElement(modelElement_p);
        }
      });
      return menuItem;
    }

    @Override
    public void dispose() {
      if (null != _menu) {
        _menu.dispose();
      }
    }

    @Override
    public Menu getMenu(Control parent_p) {
      // Dispose the previous menu (if any).
      if (null != _menu) {
        _menu.dispose();
      }
      // Create a new menu.
      _menu = new Menu(parent_p);
      final FastLinkerState state = FastLinkerActivator.getDefault().getFastLinkerManager().getCurrentState();
      if (null != state.getSecondElement()) {
        createPinMenuItem(_menu, state.getSecondElement(), state.getPinnedElement());
      }
      if (null != state.getFirstElement()) {
        createPinMenuItem(_menu, state.getFirstElement(), state.getPinnedElement());
      }
      return _menu;
    }

    @Override
    public Menu getMenu(Menu parent_p) {
      return null;
    }
  }

  public static final String VIEW_ID = "org.polarsys.capella.core.ui.fastlinker.view"; //$NON-NLS-1$

  protected Action _clearFastLinkerAction;

  protected FastLinkerFigureCanvas _fastLinkerFigureCanvas;

  protected CapellaNavigatorLabelProvider _capellaNavigatorLabelProvider;

  protected Action _pinElementAction;

  protected CapellaTabbedPropertySheetPage _propertySheetPage;

  /**
   * Displays a menu to ask the user which command he will to execute.
   * @param firstToSecondCommands_p
   * @param secondToFirstCommands_p
   * @return
   */
  public AbstractCreateLinksCommand chooseCommandToExecute(List<AbstractCreateLinksCommand> firstToSecondCommands_p,
      List<AbstractCreateLinksCommand> secondToFirstCommands_p) {
    final AbstractCreateLinksCommand[] selectedCommand = new AbstractCreateLinksCommand[1];
    SelectionListener menuItemSelectionAdapter = new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        if (!(e_p.getSource() instanceof MenuItem)) {
          return;
        }
        Object data = ((MenuItem) e_p.getSource()).getData();
        if (data instanceof AbstractCreateLinksCommand) {
          selectedCommand[0] = (AbstractCreateLinksCommand) data;
        }
      }
    };

    String firstElementName =
        _capellaNavigatorLabelProvider.getText(FastLinkerActivator.getDefault().getFastLinkerManager().getCurrentState().getFirstElement());
    String secondElementName =
        _capellaNavigatorLabelProvider.getText(FastLinkerActivator.getDefault().getFastLinkerManager().getCurrentState().getSecondElement());
    Menu myMenu = new Menu(_fastLinkerFigureCanvas);
    if (!firstToSecondCommands_p.isEmpty() && !secondToFirstCommands_p.isEmpty()) {
      // Create first to second sub menu.
      Menu firstToSecondSubMenu = new Menu(myMenu);
      MenuItem firstToSecondSubMenuItem = new MenuItem(myMenu, SWT.CASCADE);
      firstToSecondSubMenuItem.setText(firstElementName + " -> " + secondElementName); //$NON-NLS-1$
      firstToSecondSubMenuItem.setMenu(firstToSecondSubMenu);
      fillMenuWithCommands(firstToSecondSubMenu, firstToSecondCommands_p, menuItemSelectionAdapter);
      // Create second to first sub menu.
      Menu secondToFirstSubMenu = new Menu(myMenu);
      MenuItem secondToFirstSubMenuItem = new MenuItem(myMenu, SWT.CASCADE);
      secondToFirstSubMenuItem.setText(secondElementName + " -> " + firstElementName); //$NON-NLS-1$
      secondToFirstSubMenuItem.setMenu(secondToFirstSubMenu);
      fillMenuWithCommands(secondToFirstSubMenu, secondToFirstCommands_p, menuItemSelectionAdapter);
    } else if (!firstToSecondCommands_p.isEmpty()) {
      fillMenuWithCommands(myMenu, firstToSecondCommands_p, menuItemSelectionAdapter);
    } else if (!secondToFirstCommands_p.isEmpty()) {
      fillMenuWithCommands(myMenu, secondToFirstCommands_p, menuItemSelectionAdapter);
    }
    new MenuItem(myMenu, SWT.SEPARATOR);
    MenuItem mi1 = new MenuItem(myMenu, SWT.NONE);
    mi1.setText(Messages.FastLinkerView_MenuItem_Cancel_Text);

    Point fastLinkerSize = _fastLinkerFigureCanvas.getSize();
    myMenu.setLocation(_fastLinkerFigureCanvas.toDisplay(fastLinkerSize.x / 2, fastLinkerSize.y / 2));
    myMenu.setVisible(true);
    // Wait until user does its selection or the menu is hidden.
    while (!myMenu.isDisposed() && myMenu.isVisible()) {
      if (!myMenu.getDisplay().readAndDispatch()) {
        myMenu.getDisplay().sleep();
      }
    }
    return selectedCommand[0];
  }

  /**
   * Create view actions.
   */
  protected void createActions() {
    IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
    // Clear action.
    _clearFastLinkerAction = createClearAction();
    toolBarManager.add(_clearFastLinkerAction);
    // Pin/Unpin action.
    _pinElementAction = createPinElementAction();
    toolBarManager.add(_pinElementAction);
  }

  /**
   * Clear action creation.
   * @return
   */
  protected Action createClearAction() {
    Action action = new Action(Messages.FastLinkerView_Action_ClearView_Text) {
      @Override
      public void run() {
        FastLinkerActivator.getDefault().getFastLinkerManager().clearFastLinker();
      }
    };
    action.setToolTipText(Messages.FastLinkerView_Action_ClearView_Text);
    action.setImageDescriptor(FastLinkerActivator.getDefault().getImageRegistry().getDescriptor(FastLinkerActivator.IMG_CLEAR));
    return action;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createPartControl(Composite parent_p) {
    parent_p.setLayout(new FillLayout());
    // Label provider for status bar.
    _capellaNavigatorLabelProvider = new CapellaNavigatorLabelProvider();

    _fastLinkerFigureCanvas = new FastLinkerFigureCanvas(parent_p, SWT.NONE);
    _fastLinkerFigureCanvas.addSelectionChangedListener(new ISelectionChangedListener() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void selectionChanged(SelectionChangedEvent event_p) {
        refreshPropertyPage(event_p.getSelectionProvider());
        updateStatusBar(getFirstModelElementFromSelection(event_p.getSelection()));
      }
    });
    _fastLinkerFigureCanvas.addDoubleClickListener(new IDoubleClickListener() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void doubleClick(SelectionChangedEvent event_p) {
        ModelElement doubleClickedModelElement = getFirstModelElementFromSelection(event_p.getSelection());
        if (null != doubleClickedModelElement) {
          CapellaUIPropertiesPlugin.getDefault().openWizard(doubleClickedModelElement);
        }
      }
    });

    createActions();
    initializeContextMenu();
    getViewSite().setSelectionProvider(_fastLinkerFigureCanvas);

    // Configure drop.
    // Allow data to be copied or moved to the drop target
    int operations = DND.DROP_MOVE;
    DropTarget target = new DropTarget(_fastLinkerFigureCanvas, operations);
    target.setTransfer(new Transfer[] { LocalSelectionTransfer.getTransfer() });
    target.addDropListener(new DropTargetAdapter() {
      @Override
      public void dragEnter(DropTargetEvent event) {
        ModelElement draggedElement = getFirstModelElementFromSelection(LocalSelectionTransfer.getTransfer().getSelection());
        if (!FastLinkerActivator.getDefault().getFastLinkerManager().acceptElementInFastLinker(draggedElement)) {
          event.detail = DND.DROP_NONE;
        }
      }

      @Override
      public void drop(DropTargetEvent event) {
        ModelElement draggedElement = getFirstModelElementFromSelection(LocalSelectionTransfer.getTransfer().getSelection());
        FastLinkerActivator.getDefault().getFastLinkerManager().putElementInFastLinker(draggedElement);
      }
    });
    update();
  }

  /**
   * Pin/Unpin action creation.
   * @return
   */
  protected Action createPinElementAction() {
    Action action = new Action(Messages.FastLinkerView_Action_PinUnpinSelectedElement_Text, IAction.AS_DROP_DOWN_MENU) {
      /**
       * {@inheritDoc}
       */
      @Override
      public void run() {
        ModelElement selectedModelElement = getFirstModelElementFromSelection(_fastLinkerFigureCanvas.getSelection());
        FastLinkerActivator.getDefault().getFastLinkerManager().pinModelElement(selectedModelElement);
      }
    };
    action.setMenuCreator(new PinDropDownMenuCreator());
    action.setToolTipText(Messages.FastLinkerView_Action_PinUnpinSelectedElement_Text);
    action.setImageDescriptor(FastLinkerActivator.getDefault().getImageRegistry().getDescriptor(FastLinkerActivator.IMG_PIN));
    return action;
  }

  /**
   * Fill given menu, creating a MenuItem for each given command.
   * @param menu_p
   * @param commands_p
   * @param menuItemSelectionListener_p
   */
  protected void fillMenuWithCommands(Menu menu_p, List<AbstractCreateLinksCommand> commands_p, SelectionListener menuItemSelectionListener_p) {
    for (Command executableCommand : commands_p) {
      MenuItem mi = new MenuItem(menu_p, SWT.NONE);
      mi.setText(executableCommand.getLabel());
      mi.setData(executableCommand);
      mi.addSelectionListener(menuItemSelectionListener_p);
    }
  }

  /**
   * @see org.eclipse.ui.navigator.CommonNavigator#getAdapter(java.lang.Class)
   */
  @SuppressWarnings("rawtypes")
  @Override
  public Object getAdapter(Class adapter_p) {
    if (IPropertySheetPage.class.equals(adapter_p)) {
      return getPropertySheetPage();
    }
    return super.getAdapter(adapter_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getContributorId() {
    return CapellaUIPropertiesPlugin.PROPERTIES_CONTRIBUTOR;
  }

  /**
   * @see org.eclipse.emf.edit.domain.IEditingDomainProvider#getEditingDomain()
   */
  @Override
  public EditingDomain getEditingDomain() {
    return MDEAdapterFactory.getEditingDomain();
  }

  /**
   * Get the first element of the given <code>ISelection</code>. Return <code>null</code> if no selection available.
   * @return
   */
  protected ModelElement getFirstModelElementFromSelection(ISelection selection_p) {
    if (!(selection_p instanceof IStructuredSelection)) {
      return null;
    }
    Object firstElement = ((IStructuredSelection) selection_p).getFirstElement();
    if (!(firstElement instanceof ModelElement)) {
      return null;
    }
    return (ModelElement) firstElement;
  }

  /**
   * Gets the property sheet page.
   */
  protected TabbedPropertySheetPage getPropertySheetPage() {
    if (null == _propertySheetPage) {
      _propertySheetPage = new CapellaTabbedPropertySheetPage(this) {
        /**
         * {@inheritDoc}
         */
        @Override
        public void dispose() {
          super.dispose();
          _propertySheetPage = null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void init(IPageSite pageSite_p) {
          super.init(pageSite_p);
          pageSite_p.setSelectionProvider(FastLinkerView.this.getViewSite().getSelectionProvider());
        }
      };
    }
    return _propertySheetPage;
  }

  /**
   * Initialize a context menu for given viewer.
   * @param menuManagerText_p
   * @param menuManagerId_p
   * @param viewer_p
   */
  private void initializeContextMenu() {
    MenuManager menuManager = new MenuManager("FastLinkerPopupMenu", VIEW_ID); //$NON-NLS-1$
    menuManager.setRemoveAllWhenShown(true);
    Menu currentMenu = menuManager.createContextMenu(_fastLinkerFigureCanvas);
    _fastLinkerFigureCanvas.setMenu(currentMenu);
    getSite().registerContextMenu(VIEW_ID, menuManager, _fastLinkerFigureCanvas);
  }

  protected void refreshPropertyPage(ISelectionProvider selectionProvider_p) {
    // Notify the property page to refresh with the new selection.
    // Be careful, the properties view can be closed, don't send it
    // selection changes.
    IStructuredSelection selection = (IStructuredSelection) selectionProvider_p.getSelection();
    if ((null != _propertySheetPage) && (!_propertySheetPage.getControl().isDisposed())) {
      ISelectionProvider pageSelectionProvider = _propertySheetPage.getSite().getSelectionProvider();
      if ((null == pageSelectionProvider) || (pageSelectionProvider != selectionProvider_p)) {
        _propertySheetPage.getSite().setSelectionProvider(selectionProvider_p);
      }
      _propertySheetPage.selectionChanged(FastLinkerView.this, selection);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setFocus() {
    // Give focus to the figure canvas (if omitted, RuntimeExceptions can be raised because of recursive view activations).
    _fastLinkerFigureCanvas.setFocus();
  }

  /**
   * Update the FastLinkerView regarding the FastLinker state.
   */
  public void update() {
    FastLinkerState currentState = FastLinkerActivator.getDefault().getFastLinkerManager().getCurrentState();
    _fastLinkerFigureCanvas.fillFigure(currentState.getFirstElement(), currentState.getSecondElement(), currentState.getPinnedElement(),
        currentState.getLinkCreated());
    _pinElementAction.setEnabled((null != currentState.getFirstElement()) || (null != currentState.getSecondElement()));
    _clearFastLinkerAction.setEnabled((null != currentState.getFirstElement()) || (null != currentState.getSecondElement()));
  }

  /**
   * Display the path of the given element in the status bar.
   * @param elementToDisplay_p
   */
  public void updateStatusBar(Object elementToDisplay_p) {
    Image elementImage = _capellaNavigatorLabelProvider.getImage(elementToDisplay_p);
    String elementDescription = _capellaNavigatorLabelProvider.getDescription(elementToDisplay_p);
    getViewSite().getActionBars().getStatusLineManager().setMessage(elementImage, elementDescription);
  }
}
