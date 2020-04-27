/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;
import org.polarsys.capella.common.ui.toolkit.dialogs.MdeElementListSelectionDialog;
import org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.ui.toolkit.ToolkitPlugin;
import org.polarsys.capella.core.ui.toolkit.viewers.NamedElementFullLabelProvider;
import org.polarsys.capella.core.ui.toolkit.viewers.NamedElementLabelProvider;

public class DecompositionTargetViewer extends FieldsViewer implements IDecompositionDataConstants {

  // Listener registered on each button. It performs the action due to the end-user click.
  private SelectionListener buttonClickedListener;
  private Text decompNameText;
  private DecompositionModel decompositionModel;
  private ToolBar decompToolBar;
  private DecompositionGeneralViewer generalViewer;

  private ToolItem newDecompItem;
  @SuppressWarnings("unused")
  private ToolItem newTargetItem;
  private ToolItem removeDecompItem;
  private ToolItem removeTargetItem;

  private Group rightComposite;
  private List<CapellaElement> shortcutItems;
  private CTabFolder tabFolder;

  private Menu targetMenu;

  private ToolBar targetToolBar;
  private TreeViewer targetTreeViewer;

  public DecompositionTargetViewer(Composite parent, DecompositionGeneralViewer viewer) {
    super(parent);
    this.generalViewer = viewer;
    this.decompositionModel = generalViewer.getDecompositionModel();
    initializeListeners();
    createViewer(parent);
  }

  public void addShortcutInput(CapellaElement element) {
    if (shortcutItems != null) {
      shortcutItems.add(element);
    }
  }

  private void addTargetComponentValidationListener(final Text text, final Decomposition decomposition) {
    text.addKeyListener(new KeyListener() {

      public void keyPressed(KeyEvent event) {
        // do nothing
      }

      @SuppressWarnings("synthetic-access")
      public void keyReleased(KeyEvent event) {
        String newName = text.getText();
        if (newName != null) {
          if (newName.trim().length() == 0) {
            generalViewer.showErrorMessage(Messages.getString("LCDecompGeneralViewer.rename.empty.errormsg")); //$NON-NLS-1$
            return;
          }
          if (newName.trim().length() > 0) {
            if (!DecompositionUtil.isValidName(newName.trim(), decomposition)) {
              generalViewer.showErrorMessage(Messages.getString("LCDecompGeneralViewer.targetcomp.rename.errormsg")); //$NON-NLS-1$
            } else {
              generalViewer.showErrorMessage(null);
            }
          }
        }
      }
    });
  }

  protected Button createButton(Composite parent, String text, String tooltip, Object data) {
    Button button = new Button(parent, SWT.PUSH);
    GridData gdData = new GridData();
    gdData.horizontalAlignment = SWT.NONE;// FILL;
    gdData.grabExcessHorizontalSpace = false;

    button.setLayoutData(gdData);
    button.setText(text);
    button.setToolTipText(tooltip);
    button.setData(data);
    button.addSelectionListener(buttonClickedListener);
    return button;
  }

  public void createDecompositionTab(Decomposition decomposition) {
    if ((null == tabFolder) || tabFolder.isDisposed()) {
      tabFolder = new CTabFolder(rightComposite, SWT.CENTER);
      tabFolder.addSelectionListener(buttonClickedListener);
      tabFolder.setMinimumCharacters(16);
      tabFolder.setBorderVisible(true);
      tabFolder.setSimple(false);
      setTabEditor();
    }

    Group comp = new Group(tabFolder, SWT.CENTER | SWT.SHADOW_NONE);
    comp.setText(Messages.getString("LCDecompGeneralViewer.current.decomp")); //$NON-NLS-1$

    GridLayout layout = new GridLayout(1, true);
    comp.setLayout(layout);

    Composite buttonComposite = new Composite(comp, SWT.CENTER);
    layout = new GridLayout();
    layout.numColumns = 1;
    layout.verticalSpacing = 9;
    layout.marginLeft = -5;
    layout.marginRight = -5;

    buttonComposite.setLayout(layout);
    createTargetToolItems(buttonComposite);
    GridData gdData = new GridData(GridData.FILL_HORIZONTAL);
    gdData.horizontalAlignment = SWT.FILL;
    gdData.grabExcessHorizontalSpace = true;
    gdData.horizontalSpan = 1;
    buttonComposite.setLayoutData(gdData);

    gdData = new GridData(GridData.FILL_BOTH);

    gdData.horizontalSpan = 6;
    gdData.grabExcessHorizontalSpace = true;
    gdData.grabExcessVerticalSpace = true;

    Composite second = new Composite(comp, SWT.NONE);
    second.setLayoutData(gdData);

    // Sets the second area layout.
    second.setLayout(new FillLayout());
    tabFolder.setLayoutData(gdData);

    // Creates the client content.
    createTargetContent(second);

    CTabItem item = new CTabItem(tabFolder, SWT.CENTER);
    item.setControl(comp);
    item.setText(decomposition.getName());
    item.setData(decomposition);
    targetTreeViewer.setInput(decomposition);

    item.setData(TARGET_TREEVIEWER_DATA, targetTreeViewer);
    item.setData(REMOVE_TARGETCOMP_DATA, removeTargetItem);

    // setting validation for Target Component
    CellEditor editor = targetTreeViewer.getCellEditors()[0];
    Text text = (Text) editor.getControl();
    addTargetComponentValidationListener(text, decomposition);

    setTreeCellEditor(item);
    rightComposite.layout();
    tabFolder.setSelection(item);
    generalViewer.getDecompositionModel().refreshStatus(decomposition);
    generalViewer.refreshItems(targetTreeViewer);
  }

  void createDND() {
    generalViewer.createDragDropSourceTargets(targetTreeViewer);
  }

  @Override
  public Composite createInternalComposite(Composite parent) {
    return parent;
  }

  protected void createTargetContent(Composite composite) {
    targetTreeViewer = new TreeViewer(composite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

    targetTreeViewer.setColumnProperties(new String[] { "Component" }); //$NON-NLS-1$

    final Tree tree = targetTreeViewer.getTree();
    final CellEditor[] cellEditors = new CellEditor[1];
    cellEditors[0] = new TextCellEditor(tree, SWT.BORDER);

    targetTreeViewer.setCellEditors(cellEditors);
    targetTreeViewer.setCellModifier(new DecompositionTreeCellModifier(targetTreeViewer));
    targetTreeViewer.setLabelProvider(new DecompositionLabelProvider(false));
    targetTreeViewer.setContentProvider(new DecompositionTreeNodeContentProvider(decompositionModel));
    generalViewer.addSelectionListenerToViewer(targetTreeViewer);
  }

  private void createTargetToolItems(Composite customPart_p) {
    targetToolBar = new ToolBar(customPart_p, SWT.HORIZONTAL);
    ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();
    newTargetItem = createToolItem(targetToolBar, Messages.getString("LCDecompGeneralViewer.addtarget.tooltip"), NEW_TARGETCOMP_DATA, imgRegistry //$NON-NLS-1$
        .get(ToolkitPlugin.ADD_ITEM_IMAGE_ID), SWT.DROP_DOWN);
    removeTargetItem = createToolItem(targetToolBar, Messages.getString("LCDecompGeneralViewer.removetarget.tooltip"), REMOVE_TARGETCOMP_DATA, imgRegistry //$NON-NLS-1$
        .get(ToolkitPlugin.REMOVE_IMAGE_ITEM_ID), SWT.PUSH);

    removeTargetItem.setEnabled(false);
  }

  private ToolItem createToolItem(ToolBar toolbar_p, String tooltip_p, Object data_p, Image image_p, int style) {
    ToolItem item = new ToolItem(toolbar_p, style);
    item.setData(data_p);
    item.setToolTipText(tooltip_p);
    item.setImage(image_p);
    item.addSelectionListener(buttonClickedListener);
    return item;
  }

  private void createViewer(Composite parent_p) {
    parent_p.setLayout(new FillLayout());
    rightComposite = new Group(parent_p, SWT.CENTER | SWT.SHADOW_NONE);
    rightComposite.setText(Messages.getString("LCDecompGeneralViewer.alterantive.decomp")); //$NON-NLS-1$
    GridLayout rightLayout = new GridLayout();
    rightLayout.numColumns = 1;
    rightLayout.verticalSpacing = 5;
    GridData data = new GridData();
    data.horizontalAlignment = SWT.FILL;
    data.grabExcessHorizontalSpace = true;
    rightComposite.setLayout(rightLayout);

    Composite decompActionsComposite = new Composite(rightComposite, SWT.CENTER);
    GridLayout layout = new GridLayout();
    decompActionsComposite.setLayout(layout);
    layout.numColumns = 5;
    layout.marginLeft = -5;

    GridData gdData = new GridData();
    gdData.horizontalAlignment = SWT.FILL;
    gdData.grabExcessHorizontalSpace = true;
    decompActionsComposite.setLayoutData(gdData);

    // create the toolbar
    decompToolBar = new ToolBar(decompActionsComposite, SWT.HORIZONTAL);

    ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();

    newDecompItem = createToolItem(decompToolBar, Messages.getString("LCDecompGeneralViewer.adddecomp.tooltip"), NEW_DECOMP_DATA, imgRegistry //$NON-NLS-1$
        .get(ToolkitPlugin.ADD_ITEM_IMAGE_ID), SWT.PUSH);
    removeDecompItem = createToolItem(decompToolBar, Messages.getString("LCDecompGeneralViewer.removedecomp.tooltip"), REMOVE_DECOMP_DATA, imgRegistry //$NON-NLS-1$
        .get(ToolkitPlugin.REMOVE_IMAGE_ITEM_ID), SWT.PUSH);

    // TODO the button 'add' shall be enabled when alternative decomposition will be handled by Capella
    newDecompItem.setEnabled(false);
    removeDecompItem.setEnabled(false);

    initMenus();
  }

  public Text getDecompNameText() {
    return decompNameText;
  }

  public DecompositionModel getDecompositionModel() {
    return decompositionModel;
  }

  @Override
  public Object getInput() {
    return null;
  }

  public CTabFolder getTabFolder() {
    return tabFolder;
  }

  public TreeViewer getTargetTreeViewer() {
    return targetTreeViewer;
  }

  protected void handleButtonClicked(Widget button) {
    if (button.getData().equals(NEW_DECOMP_DATA)) {
      DecompositionVisitor.addNewDecomposition(this);
    } else if (button.getData().equals(REMOVE_DECOMP_DATA)) {
      DecompositionVisitor.removeSelectedDecomposition(this);
    } else if (button.getData().equals(REMOVEALL_DECOMP_DATA)) {
      DecompositionVisitor.removeAllDecomposition(this);
    } else if (button.getData().equals(RENAME_DECOMP_DATA)) {
      DecompositionVisitor.renameSelectedDecomposition(this);
    } else if (button.getData().equals(NEW_TARGETCOMP_DATA)) {
      DecompositionVisitor.addNewTargetComponent(this);
    } else if (button.getData().equals(RENAME_TARGETCOMP_DATA)) {
      DecompositionVisitor.renameTargetComponent(this);
    } else if (button.getData().equals(REMOVE_TARGETCOMP_DATA)) {
      DecompositionVisitor.removeSelectedTargetComponent(this);
    } else if (button.getData().equals(REMOVEALL_TARGETCOMP_DATA)) {
      DecompositionVisitor.removeAllTargetComponent(this);
    } else if (button.getData().equals(DETACH_INTERFACE_DATA)) {
      DecompositionVisitor.detachInterface(this);
    } else if (button.getData().equals(REUSE_COMP_DATA)) {
      DecompositionVisitor.reuseComponent(this);
    }
  }

  protected void handleMenuSelected(Widget widget) {
    MenuItem item = (MenuItem) widget;
    if (item.getData().equals(NEW_TARGETCOMP_DATA)) {
      DecompositionVisitor.addNewTargetComponent(this);
    } else if (item.getData().equals(REUSE_COMP_DATA)) {
      ILabelProvider _labelProvider = new NamedElementLabelProvider();
      ILabelProvider _statusLabelProvider = new NamedElementFullLabelProvider();

      MdeElementListSelectionDialog selectionDialog =
          new MdeElementListSelectionDialog(targetTreeViewer.getControl().getShell(), _labelProvider, _statusLabelProvider);
      selectionDialog.setTitle(Messages.getString("LCDecomp.title.label")); //$NON-NLS-1$
      selectionDialog.setMultipleSelection(false);
      selectionDialog.setMatchEmptyString(true);
      selectionDialog.setIgnoreCase(true);
      selectionDialog.setHelpAvailable(true);

      DecompositionComponent sourceComponent = decompositionModel.getSourceComponent();
      Object value = sourceComponent.getValue();
      if (value instanceof LogicalComponent) {
        LogicalComponent lc = (LogicalComponent) value;

        Collection<Component> allAncestors = ComponentExt.getComponentAncestors(lc);
        Collection<Component> allDescendants = ComponentExt.getAllSubUsedComponents(lc);
        Collection<Component> allLCS = BlockArchitectureExt.getAllComponents(BlockArchitectureExt.getRootBlockArchitecture(lc));

        // allLCS - allAncestors - allDescendants - lc
        allLCS.removeAll(allAncestors);
        allLCS.removeAll(allDescendants);
        allLCS.remove(lc);
        ArrayList<LogicalComponent> lclist = new ArrayList<LogicalComponent>();

        // add valid lc to list
        for (Component component : allLCS) {
          if (component instanceof LogicalComponent) {
            lclist.add((LogicalComponent) component);
          }
        }

        selectionDialog.setElements(lclist.toArray());
      }

      if (Window.OK == selectionDialog.open()) {
        tabFolder.getSelection().setData(REUSE_COMP_DATA, selectionDialog.getFirstResult());
        DecompositionVisitor.reuseComponent(this);
      }
    }
  }

  protected void handleTabItemChanged(Widget widget) {
    generalViewer.createDNDForTargetViewer((CTabFolder) widget);
  }

  private void initializeListeners() {
    // Called when the end-user clicks on a button.
    buttonClickedListener = new SelectionAdapter() {
      /**
       * @see SelectionAdapter#widgetSelected(SelectionEvent)
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void widgetSelected(SelectionEvent event) {
        Widget widget = event.widget;
        if ((widget instanceof Button) || (widget instanceof ToolItem)) {
          if ((event.detail == SWT.ARROW) && widget.getData().equals(NEW_TARGETCOMP_DATA)) {
            Point point = new Point(event.x, event.y);
            point = Display.getCurrent().map(targetToolBar, null, point);
            targetMenu.setLocation(point);
            targetMenu.setVisible(true);
          } else {
            handleButtonClicked(widget);
          }
        } else if (widget instanceof CTabFolder) {
          handleTabItemChanged(widget);
        } else if (widget instanceof MenuItem) {
          handleMenuSelected(widget);
        }
      }
    };
  }

  private void initMenus() {
    targetMenu = new Menu(rightComposite.getShell());
    MenuItem lcMenuItem = new MenuItem(targetMenu, SWT.PUSH);
    lcMenuItem.setText(Messages.getString("LCDecomp.lc.menu.label")); //$NON-NLS-1$
    lcMenuItem.addSelectionListener(buttonClickedListener);
    lcMenuItem.setData(NEW_TARGETCOMP_DATA);

    MenuItem lcShortcutMenuItem = new MenuItem(targetMenu, SWT.PUSH);
    lcShortcutMenuItem.setText(Messages.getString("LCDecomp.lcreuse.menu.label")); //$NON-NLS-1$
    lcShortcutMenuItem.addSelectionListener(buttonClickedListener);
    lcShortcutMenuItem.setData(REUSE_COMP_DATA);

  }

  void refreshItems(TreeViewer viewer) {
    if ((tabFolder == null) || tabFolder.isDisposed()) {
      generalViewer.refreshItems(null);
      return;
    }
    generalViewer.refreshItems(viewer);
  }

  public void removeShortcutInput(Object element) {
    if (shortcutItems != null) {
      shortcutItems.remove(element);
    }
  }

  public void setDecompNameText(Text decompNameText_p) {
    decompNameText = decompNameText_p;
  }

  public void setDecompositionFieldsEnabled(int size) {
    removeDecompItem.setEnabled((size > 1));
  }

  public void setDecompositionModel(DecompositionModel decompositionModel_p) {
    decompositionModel = decompositionModel_p;
  }

  void setDecompositionName(boolean flag) {
    List<Decomposition> list = decompositionModel.getDecompositions();
    int size = list.size();
    Decomposition firstDecomp = (Decomposition) tabFolder.getItem(0).getData();// list.get(0);
    String name = Messages.getString("LCDecompGeneralViewer.decomposition.name"); //$NON-NLS-1$
    firstDecomp.setName((size > 1) ? flag ? name + "0" : name + "1" : Util.ZERO_LENGTH_STRING); //$NON-NLS-1$ //$NON-NLS-2$
    tabFolder.getItem(0).setText(firstDecomp.getName());
  }

  void setDefaultSelectedTabIndex() {
    tabFolder.setSelection(0);
    generalViewer.createDNDForTargetViewer(tabFolder);
  }

  public void setDetachInterfaceButtonEnabled(boolean isEnabled) {
  }

  @Override
  public void setInput(Object input) {
    // do nothing
  }

  void setRemoveTCButtonEnabled(boolean isEnabled) {
    ToolItem removeItem = (ToolItem) tabFolder.getSelection().getData(REMOVE_TARGETCOMP_DATA);
    if (removeItem != null) {
      removeItem.setEnabled(isEnabled);
    }
  }

  private void setTabEditor() {
    if (tabFolder != null) {

      tabFolder.addMouseListener(new MouseAdapter() {

        @Override
        @SuppressWarnings("synthetic-access")
        public void mouseDoubleClick(MouseEvent me_p) {
          if (tabFolder.getItemCount() > 1) {
            final CTabItem item = tabFolder.getSelection();
            Point pt = new Point(me_p.x, me_p.y);
            PopupDialog popup =
                new RenameDecompositionDialog(tabFolder.getShell(), PopupDialog.HOVER_SHELLSTYLE, true, false, false, false, null, Messages
                    .getString("LCDecompGeneralViewer.renamedecomp.popupinfo"), item, //$NON-NLS-1$
                    pt, DecompositionTargetViewer.this);
            popup.open();

            item.addListener(SWT.MouseDoubleClick, DecompositionUtil.getRenameDecompListener(item));
          }
        }
      });
    }
  }

  public void setTabFolder(CTabFolder tabFolder_p) {
    tabFolder = tabFolder_p;
  }

  public void setTargetTreeViewer(TreeViewer targetTreeViewer_p) {
    targetTreeViewer = targetTreeViewer_p;
  }

  private void setTreeCellEditor(final CTabItem item_p) {
    final TreeViewer viewer = (TreeViewer) item_p.getData(TARGET_TREEVIEWER_DATA);
    Tree tree = viewer.getTree();
    tree.addListener(SWT.MouseDown, new Listener() {

      @SuppressWarnings("synthetic-access")
      public void handleEvent(Event event) {
        ((DecompositionTreeCellModifier) viewer.getCellModifier()).setEnabled(false);
        generalViewer.showErrorMessage(null);
        if (null != event) {
          Object data = event.data;
          if ((null != data) && data.equals("rename")) { //$NON-NLS-1$
            DecompositionComponent comp = (DecompositionComponent) event.item.getData();
            decompositionModel.setPathForNewTargetComponent(comp);
            generalViewer.updateStatusBar(comp);
          }
        }
      }
    });
    viewer.addDoubleClickListener(e -> DecompositionVisitor.renameTargetComponent(DecompositionTargetViewer.this));
  }

  void showErrorMessage(String msg) {
    generalViewer.showErrorMessage(msg);
  }
}
