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
package org.polarsys.capella.core.ui.fastlinker.view;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
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
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.links.helpers.commands.AbstractCreateLinksCommand;
import org.polarsys.capella.core.ui.fastlinker.FastLinkerActivator;
import org.polarsys.capella.core.ui.fastlinker.FastLinkerState;
import org.polarsys.capella.core.ui.fastlinker.view.providers.FastLinkerLabelProvider;
import org.polarsys.capella.core.ui.properties.CapellaTabbedPropertySheetPage;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;

public class FastLinkerView extends ViewPart implements ITabbedPropertySheetPageContributor, IEditingDomainProvider {

  protected class PinDropDownMenuCreator implements IMenuCreator {
    private Menu menu;

    @SuppressWarnings("rawtypes")
	protected MenuItem createPinMenuItem(Menu parentMenu, final Collection modelElement, Collection<EObject> pinnedElement) {
      MenuItem menuItem = new MenuItem(parentMenu, SWT.CHECK);
      menuItem.setImage(fastLinkerLabelProvider.getImage(modelElement));
      menuItem.setText(fastLinkerLabelProvider.getText(modelElement));
      menuItem.setSelection(modelElement == pinnedElement);
      menuItem.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent e) {
          FastLinkerActivator.getDefault().getFastLinkerManager().pinModelElement(modelElement);
        }
      });
      return menuItem;
    }

    @Override
    public void dispose() {
      if (null != menu) {
        menu.dispose();
      }
    }

    @SuppressWarnings("unchecked")
	@Override
    public Menu getMenu(Control parent) {
      // Dispose the previous menu (if any).
      if (null != menu) {
        menu.dispose();
      }
      // Create a new menu.
      menu = new Menu(parent);
      final FastLinkerState state = FastLinkerActivator.getDefault().getFastLinkerManager().getCurrentState();
      if (null != state.getSecondElement()) {
        createPinMenuItem(menu, state.getSecondElement(), state.getPinnedElement());
      }
      if (null != state.getFirstElement()) {
        createPinMenuItem(menu, state.getFirstElement(), state.getPinnedElement());
      }
      return menu;
    }

    @Override
    public Menu getMenu(Menu parent) {
      return null;
    }
  }

  public static final String VIEW_ID = "org.polarsys.capella.core.ui.fastlinker.view"; //$NON-NLS-1$

  protected Action clearFastLinkerAction;

  protected FastLinkerFigureCanvas fastLinkerFigureCanvas;

  protected FastLinkerLabelProvider fastLinkerLabelProvider;

  protected Action pinElementAction;

  protected CapellaTabbedPropertySheetPage propertySheetPage;

  /**
   * Displays a menu to ask the user which command he will to execute.
   * @param firstToSecondCommands
   * @param secondToFirstCommands
   * @return
   */
  public AbstractCreateLinksCommand chooseCommandToExecute(List<AbstractCreateLinksCommand> firstToSecondCommands,
      List<AbstractCreateLinksCommand> secondToFirstCommands) {
    final AbstractCreateLinksCommand[] selectedCommand = new AbstractCreateLinksCommand[1];
    SelectionListener menuItemSelectionAdapter = new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        if (!(e.getSource() instanceof MenuItem)) {
          return;
        }
        Object data = ((MenuItem) e.getSource()).getData();
        if (data instanceof AbstractCreateLinksCommand) {
          selectedCommand[0] = (AbstractCreateLinksCommand) data;
        }
      }
    };

    String firstElementName =
        fastLinkerLabelProvider.getText(FastLinkerActivator.getDefault().getFastLinkerManager().getCurrentState().getFirstElement());
    String secondElementName =
        fastLinkerLabelProvider.getText(FastLinkerActivator.getDefault().getFastLinkerManager().getCurrentState().getSecondElement());
    Menu myMenu = new Menu(fastLinkerFigureCanvas);
    if (!firstToSecondCommands.isEmpty() && !secondToFirstCommands.isEmpty()) {
      // Create first to second sub menu.
      Menu firstToSecondSubMenu = new Menu(myMenu);
      MenuItem firstToSecondSubMenuItem = new MenuItem(myMenu, SWT.CASCADE);
      firstToSecondSubMenuItem.setText(firstElementName + " -> " + secondElementName); //$NON-NLS-1$
      firstToSecondSubMenuItem.setMenu(firstToSecondSubMenu);
      fillMenuWithCommands(firstToSecondSubMenu, firstToSecondCommands, menuItemSelectionAdapter);
      // Create second to first sub menu.
      Menu secondToFirstSubMenu = new Menu(myMenu);
      MenuItem secondToFirstSubMenuItem = new MenuItem(myMenu, SWT.CASCADE);
      secondToFirstSubMenuItem.setText(secondElementName + " -> " + firstElementName); //$NON-NLS-1$
      secondToFirstSubMenuItem.setMenu(secondToFirstSubMenu);
      fillMenuWithCommands(secondToFirstSubMenu, secondToFirstCommands, menuItemSelectionAdapter);
    } else if (!firstToSecondCommands.isEmpty()) {
      fillMenuWithCommands(myMenu, firstToSecondCommands, menuItemSelectionAdapter);
    } else if (!secondToFirstCommands.isEmpty()) {
      fillMenuWithCommands(myMenu, secondToFirstCommands, menuItemSelectionAdapter);
    }
    new MenuItem(myMenu, SWT.SEPARATOR);
    MenuItem mi1 = new MenuItem(myMenu, SWT.NONE);
    mi1.setText(Messages.FastLinkerView_MenuItem_Cancel_Text);

    Point fastLinkerSize = fastLinkerFigureCanvas.getSize();
    myMenu.setLocation(fastLinkerFigureCanvas.toDisplay(fastLinkerSize.x / 2, fastLinkerSize.y / 2));
    myMenu.setVisible(true);
    // Wait until user does its selection or the menu is hidden.
    while (!myMenu.isDisposed() && myMenu.isVisible()) {
      if (!myMenu.getDisplay().readAndDispatch()) {
        myMenu.getDisplay().sleep();
      }
    }
    return selectedCommand[0];
  }

  protected void createActions() {
    IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
    // Clear action.
    clearFastLinkerAction = createClearAction();
    toolBarManager.add(clearFastLinkerAction);
    // Pin/Unpin action.
    pinElementAction = createPinElementAction();
    toolBarManager.add(pinElementAction);
  }

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

  @Override
  public void createPartControl(Composite parent) {
    parent.setLayout(new FillLayout());
    // Label provider for status bar.
    fastLinkerLabelProvider = new FastLinkerLabelProvider();

    fastLinkerFigureCanvas = new FastLinkerFigureCanvas(parent, SWT.NONE);
    fastLinkerFigureCanvas.addSelectionChangedListener(event -> {
      refreshPropertyPage(event.getSelectionProvider());
      updateStatusBar(getFirstModelElementFromSelection(event.getSelection()));
    });
    fastLinkerFigureCanvas.addDoubleClickListener(event -> {
      Collection<?> doubleClickedModelElement = getFirstModelElementFromSelection(event.getSelection());
      if (null != doubleClickedModelElement && !doubleClickedModelElement.isEmpty()) {
        // TODO only forMA3
        CapellaUIPropertiesPlugin.getDefault().openWizard((EObject) doubleClickedModelElement.iterator().next());
      }
    });

    createActions();
    initializeContextMenu();
    getViewSite().setSelectionProvider(fastLinkerFigureCanvas);

    // Configure drop.
    // Allow data to be copied or moved to the drop target
    int operations = DND.DROP_MOVE;
    DropTarget target = new DropTarget(fastLinkerFigureCanvas, operations);
    target.setTransfer(LocalSelectionTransfer.getTransfer());
    target.addDropListener(new DropTargetAdapter() {
      @SuppressWarnings("rawtypes")
	  @Override
      public void dragEnter(DropTargetEvent event) {
        Collection draggedElement = getFirstModelElementFromSelection(LocalSelectionTransfer.getTransfer().getSelection());
        if (!FastLinkerActivator.getDefault().getFastLinkerManager().acceptElementInFastLinker(draggedElement)) {
          event.detail = DND.DROP_NONE;
        }
      }

      @SuppressWarnings("rawtypes")
	  @Override
      public void drop(DropTargetEvent event) {
        Collection draggedElement = getFirstModelElementFromSelection(LocalSelectionTransfer.getTransfer().getSelection());
        FastLinkerActivator.getDefault().getFastLinkerManager().putElementInFastLinker(draggedElement);
      }
    });
    update();
  }

  protected Action createPinElementAction() {
    Action action = new Action(Messages.FastLinkerView_Action_PinUnpinSelectedElement_Text, IAction.AS_DROP_DOWN_MENU) {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("rawtypes")
	  @Override
      public void run() {
        Collection selectedModelElement = getFirstModelElementFromSelection(fastLinkerFigureCanvas.getSelection());
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
   * @param menu
   * @param commands
   * @param menuItemSelectionListener
   */
  protected void fillMenuWithCommands(Menu menu, List<AbstractCreateLinksCommand> commands, SelectionListener menuItemSelectionListener) {
    for (Command executableCommand : commands) {
      MenuItem mi = new MenuItem(menu, SWT.NONE);
      mi.setText(executableCommand.getLabel());
      mi.setData(executableCommand);
      mi.addSelectionListener(menuItemSelectionListener);
    }
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Object getAdapter(Class adapter) {
    if (IPropertySheetPage.class.equals(adapter)) {
      return getPropertySheetPage();
    }
    return super.getAdapter(adapter);
  }

  @Override
  public String getContributorId() {
    return CapellaUIPropertiesPlugin.PROPERTIES_CONTRIBUTOR;
  }

  @Override
  public EditingDomain getEditingDomain() {
	ISelection selection = fastLinkerFigureCanvas.getSelection();
	if (selection instanceof IStructuredSelection) {
	  Object elt = ((IStructuredSelection) selection).getFirstElement();
	  if (elt instanceof EObject) {
		return TransactionHelper.getEditingDomain((EObject) elt);
	  }
	}
    return null;
  }

  /**
   * Get the first element of the given <code>ISelection</code>. Return <code>null</code> if no selection available.
   * @return
   */
  @SuppressWarnings("rawtypes")
  protected Collection getFirstModelElementFromSelection(
			ISelection selection) {
		if (!(selection instanceof IStructuredSelection)) {
			return null;
		}
		List firstElements = ((IStructuredSelection) selection).toList();
		for (Object firstElement : firstElements)
			if (!(firstElement instanceof ModelElement)) {
				return null;
			}
		return firstElements;
	}

  protected TabbedPropertySheetPage getPropertySheetPage() {
    if (null == propertySheetPage) {
      propertySheetPage = new CapellaTabbedPropertySheetPage(this) {
        @Override
        public void dispose() {
          super.dispose();
          propertySheetPage = null;
        }

        @Override
        public void init(IPageSite pageSite) {
          super.init(pageSite);
          pageSite.setSelectionProvider(FastLinkerView.this.getViewSite().getSelectionProvider());
        }
      };
    }
    return propertySheetPage;
  }

  private void initializeContextMenu() {
    MenuManager menuManager = new MenuManager("FastLinkerPopupMenu", VIEW_ID); //$NON-NLS-1$
    menuManager.setRemoveAllWhenShown(true);
    Menu currentMenu = menuManager.createContextMenu(fastLinkerFigureCanvas);
    fastLinkerFigureCanvas.setMenu(currentMenu);
    getSite().registerContextMenu(VIEW_ID, menuManager, fastLinkerFigureCanvas);
  }

  protected void refreshPropertyPage(ISelectionProvider selectionProvider) {
    // Notify the property page to refresh with the new selection.
    // Be careful, the properties view can be closed, don't send it
    // selection changes.
    IStructuredSelection selection = (IStructuredSelection) selectionProvider.getSelection();
    if ((null != propertySheetPage) && (!propertySheetPage.getControl().isDisposed())) {
      ISelectionProvider pageSelectionProvider = propertySheetPage.getSite().getSelectionProvider();
      if ((null == pageSelectionProvider) || (pageSelectionProvider != selectionProvider)) {
        propertySheetPage.getSite().setSelectionProvider(selectionProvider);
      }
      propertySheetPage.selectionChanged(FastLinkerView.this, selection);
    }
  }

  @Override
  public void setFocus() {
    // Give focus to the figure canvas (if omitted, RuntimeExceptions can be raised because of recursive view activations).
    fastLinkerFigureCanvas.setFocus();
  }

  /**
   * Update the FastLinkerView regarding the FastLinker state.
   */
  public void update() {
   FastLinkerState currentState = FastLinkerActivator.getDefault()
				.getFastLinkerManager().getCurrentState();
		fastLinkerFigureCanvas.fillFigure(currentState.getFirstElement(),
				currentState.getSecondElement(),
				currentState.getPinnedElement(), currentState.getLinkCreated());
		pinElementAction.setEnabled((null != currentState.getFirstElement())
				|| (null != currentState.getSecondElement()));
		clearFastLinkerAction.setEnabled((null != currentState
				.getFirstElement())
				|| (null != currentState.getSecondElement()));
  }

  /**
   * Display the path of the given element in the status bar.
   * @param elementToDisplay
   */
  public void updateStatusBar(Object elementToDisplay) {
    Image elementImage = fastLinkerLabelProvider.getImage(elementToDisplay);
    String elementDescription = fastLinkerLabelProvider.getDescription(elementToDisplay);
    getViewSite().getActionBars().getStatusLineManager().setMessage(elementImage, elementDescription);
  }
  
  @Override
  public void dispose() {
    super.dispose();
    fastLinkerLabelProvider.dispose();
  }
}
