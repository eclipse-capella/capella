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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
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
import org.polarsys.capella.core.ui.toolkit.ToolkitPlugin;
import org.polarsys.capella.core.ui.toolkit.viewers.NamedElementFullLabelProvider;
import org.polarsys.capella.core.ui.toolkit.viewers.NamedElementLabelProvider;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;

/**
 */
public class DecompositionTargetViewer extends FieldsViewer implements IDecompositionDataConstants {

  // Listener registered on each button. It performs the action due to the end-user click.
  private SelectionListener _buttonClickedListener;
  private Text _decompNameText;
  private DecompositionModel _decompositionModel;
  private ToolBar _decompToolBar;
  private DecompositionGeneralViewer _generalViewer;

  private ToolItem _newDecompItem;
  @SuppressWarnings("unused")
  private ToolItem _newTargetItem;
  private ToolItem _removeDecompItem;
  private ToolItem _removeTargetItem;

  private Group _rightComposite;
  private List<CapellaElement> _shortcutItems;
  private CTabFolder _tabFolder;

  private Menu _targetMenu;

  private ToolBar _targetToolBar;
  private TreeViewer _targetTreeViewer;


  /**
   * @param parent_p
   */
  public DecompositionTargetViewer(Composite parent_p, DecompositionGeneralViewer viewer_p) {
    super(parent_p);
    this._generalViewer = viewer_p;
    this._decompositionModel = _generalViewer.getDecompositionModel();
    initializeListeners();
    createViewer(parent_p);
  }

  /**
   * 
   */
  public void addShortcutInput(CapellaElement element_p) {
    if (_shortcutItems != null) {
      _shortcutItems.add(element_p);
    }
  }

  /**
   * 
   */
  private void addTargetComponentValidationListener(final Text text_p, final Decomposition decomposition_p) {
    text_p.addKeyListener(new KeyListener() {

      public void keyPressed(KeyEvent event_p) {
        // do nothing
      }

      @SuppressWarnings("synthetic-access")
      public void keyReleased(KeyEvent event_p) {
        String newName = text_p.getText();
        if (newName != null) {
          if (newName.trim().length() == 0) {
            _generalViewer.showErrorMessage(Messages.getString("LCDecompGeneralViewer.rename.empty.errormsg")); //$NON-NLS-1$
            return;
          }
          if (newName.trim().length() > 0) {
            if (!DecompositionUtil.isValidName(newName.trim(), decomposition_p)) {
              _generalViewer.showErrorMessage(Messages.getString("LCDecompGeneralViewer.targetcomp.rename.errormsg")); //$NON-NLS-1$
            } else {
              _generalViewer.showErrorMessage(null);
            }
          }
        }
      }
    });
  }

  /**
   * Creates the button
   * @param parent_p the parent composite
   * @param text_p text for the button
   * @param tooltip_p tooltip for the button
   * @param data_p data for the button (this is used to check in the handleButtonClicked())
   * @see #handleButtonClicked(Widget)
   * @return the created button
   */
  protected Button createButton(Composite parent_p, String text_p, String tooltip_p, Object data_p) {
    Button button = new Button(parent_p, SWT.PUSH);
    GridData gdData = new GridData();
    gdData.horizontalAlignment = SWT.NONE;// FILL;
    gdData.grabExcessHorizontalSpace = false;

    button.setLayoutData(gdData);
    button.setText(text_p);
    button.setToolTipText(tooltip_p);
    button.setData(data_p);
    button.addSelectionListener(_buttonClickedListener);
    return button;
  }

  /**
   * Creates the decomposition tab
   * @param decomposition_p the decomposition
   */
  public void createDecompositionTab(Decomposition decomposition_p) {
    if ((null == _tabFolder) || _tabFolder.isDisposed()) {
      _tabFolder = new CTabFolder(_rightComposite, SWT.CENTER);
      _tabFolder.addSelectionListener(_buttonClickedListener);
      _tabFolder.setMinimumCharacters(16);
      _tabFolder.setBorderVisible(true);
      _tabFolder.setSimple(false);
      setTabEditor();
    }

    Group comp = new Group(_tabFolder, SWT.CENTER | SWT.SHADOW_NONE);
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
    _tabFolder.setLayoutData(gdData);

    // Creates the client content.
    createTargetContent(second);

    CTabItem item = new CTabItem(_tabFolder, SWT.CENTER);
    item.setControl(comp);
    item.setText(decomposition_p.getName());
    item.setData(decomposition_p);
    _targetTreeViewer.setInput(decomposition_p);

    item.setData(TARGET_TREEVIEWER_DATA, _targetTreeViewer);
    item.setData(REMOVE_TARGETCOMP_DATA, _removeTargetItem);

    // setting validation for Target Component
    CellEditor editor = _targetTreeViewer.getCellEditors()[0];
    Text text = (Text) editor.getControl();
    addTargetComponentValidationListener(text, decomposition_p);

    setTreeCellEditor(item);
    _rightComposite.layout();
    _tabFolder.setSelection(item);
    _generalViewer.getDecompositionModel().refreshStatus(decomposition_p);
    _generalViewer.refreshItems(_targetTreeViewer);
  }

  void createDND() {
    _generalViewer.createDragDropSourceTargets(_targetTreeViewer);
  }

  /**
   * 
   */
  @Override
  public Composite createInternalComposite(Composite parent_p) {
    return parent_p;
  }

  /**
   * Creates the target content
   * @param composite_p the composite
   */
  protected void createTargetContent(Composite composite_p) {
    _targetTreeViewer = new TreeViewer(composite_p, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

    _targetTreeViewer.setColumnProperties(new String[] { "Component" }); //$NON-NLS-1$

    final Tree tree = _targetTreeViewer.getTree();
    final CellEditor[] cellEditors = new CellEditor[1];
    cellEditors[0] = new TextCellEditor(tree, SWT.BORDER);

    _targetTreeViewer.setCellEditors(cellEditors);
    _targetTreeViewer.setCellModifier(new DecompositionTreeCellModifier(_targetTreeViewer));
    _targetTreeViewer.setLabelProvider(new DecompositionLabelProvider(false, _generalViewer.getDecompositionModel().getImgRegistry()));
    _targetTreeViewer.setContentProvider(new DecompositionTreeNodeContentProvider());
    _generalViewer.addSelectionListenerToViewer(_targetTreeViewer);
  }

  /**
   * 
   */
  private void createTargetToolItems(Composite customPart_p) {
    _targetToolBar = new ToolBar(customPart_p, SWT.HORIZONTAL);
    ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();
    _newTargetItem = createToolItem(_targetToolBar, Messages.getString("LCDecompGeneralViewer.addtarget.tooltip"), NEW_TARGETCOMP_DATA, imgRegistry //$NON-NLS-1$
        .get(ToolkitPlugin.ADD_ITEM_IMAGE_ID), SWT.DROP_DOWN);
    _removeTargetItem = createToolItem(_targetToolBar, Messages.getString("LCDecompGeneralViewer.removetarget.tooltip"), REMOVE_TARGETCOMP_DATA, imgRegistry //$NON-NLS-1$
        .get(ToolkitPlugin.REMOVE_IMAGE_ITEM_ID), SWT.PUSH);

    _removeTargetItem.setEnabled(false);
  }

  /**
   * 
   */
  private ToolItem createToolItem(ToolBar toolbar_p, String tooltip_p, Object data_p, Image image_p, int style) {
    ToolItem item = new ToolItem(toolbar_p, style);
    item.setData(data_p);
    item.setToolTipText(tooltip_p);
    item.setImage(image_p);
    item.addSelectionListener(_buttonClickedListener);
    return item;
  }

  /**
   * 
   */
  private void createViewer(Composite parent_p) {
    parent_p.setLayout(new FillLayout());
    _rightComposite = new Group(parent_p, SWT.CENTER | SWT.SHADOW_NONE);
    _rightComposite.setText(Messages.getString("LCDecompGeneralViewer.alterantive.decomp")); //$NON-NLS-1$
    GridLayout rightLayout = new GridLayout();
    rightLayout.numColumns = 1;
    rightLayout.verticalSpacing = 5;
    GridData data = new GridData();
    data.horizontalAlignment = SWT.FILL;
    data.grabExcessHorizontalSpace = true;
    _rightComposite.setLayout(rightLayout);

    Composite decompActionsComposite = new Composite(_rightComposite, SWT.CENTER);
    GridLayout layout = new GridLayout();
    decompActionsComposite.setLayout(layout);
    layout.numColumns = 5;
    layout.marginLeft = -5;

    GridData gdData = new GridData();
    gdData.horizontalAlignment = SWT.FILL;
    gdData.grabExcessHorizontalSpace = true;
    decompActionsComposite.setLayoutData(gdData);

    // create the toolbar
    _decompToolBar = new ToolBar(decompActionsComposite, SWT.HORIZONTAL);

    ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();

    _newDecompItem = createToolItem(_decompToolBar, Messages.getString("LCDecompGeneralViewer.adddecomp.tooltip"), NEW_DECOMP_DATA, imgRegistry //$NON-NLS-1$
        .get(ToolkitPlugin.ADD_ITEM_IMAGE_ID), SWT.PUSH);
    _removeDecompItem = createToolItem(_decompToolBar, Messages.getString("LCDecompGeneralViewer.removedecomp.tooltip"), REMOVE_DECOMP_DATA, imgRegistry //$NON-NLS-1$
        .get(ToolkitPlugin.REMOVE_IMAGE_ITEM_ID), SWT.PUSH);

    // TODO the button 'add' shall be enabled when alternative decomposition will be handled by Capella
    _newDecompItem.setEnabled(false);
    _removeDecompItem.setEnabled(false);

    initMenus();
  }

  /**
   * @return the decompNameText
   */
  public Text getDecompNameText() {
    return _decompNameText;
  }

  /**
   * @return the decompositionModel
   */
  public DecompositionModel getDecompositionModel() {
    return _decompositionModel;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#getInput()
   */
  @Override
  public Object getInput() {
    return null;
  }

  /**
   * @return the tabFolder
   */
  public CTabFolder getTabFolder() {
    return _tabFolder;
  }

  /**
   * @return the targetTreeViewer
   */
  public TreeViewer getTargetTreeViewer() {
    return _targetTreeViewer;
  }

  /**
   * Handles the button clicked event
   * @param button_p the button
   */
  protected void handleButtonClicked(Widget button_p) {
    if (button_p.getData().equals(NEW_DECOMP_DATA)) {
      DecompositionVisitor.addNewDecomposition(this);
    } else if (button_p.getData().equals(REMOVE_DECOMP_DATA)) {
      DecompositionVisitor.removeSelectedDecomposition(this);
    } else if (button_p.getData().equals(REMOVEALL_DECOMP_DATA)) {
      DecompositionVisitor.removeAllDecomposition(this);
    } else if (button_p.getData().equals(RENAME_DECOMP_DATA)) {
      DecompositionVisitor.renameSelectedDecomposition(this);
    } else if (button_p.getData().equals(NEW_TARGETCOMP_DATA)) {
      DecompositionVisitor.addNewTargetComponent(this);
    } else if (button_p.getData().equals(RENAME_TARGETCOMP_DATA)) {
      DecompositionVisitor.renameTargetComponent(this);
    } else if (button_p.getData().equals(REMOVE_TARGETCOMP_DATA)) {
      DecompositionVisitor.removeSelectedTargetComponent(this);
    } else if (button_p.getData().equals(REMOVEALL_TARGETCOMP_DATA)) {
      DecompositionVisitor.removeAllTargetComponent(this);
    } else if (button_p.getData().equals(DETACH_INTERFACE_DATA)) {
      DecompositionVisitor.detachInterface(this);
    } else if (button_p.getData().equals(REUSE_COMP_DATA)) {
      DecompositionVisitor.reuseComponent(this);
    }
  }

  /**
   * @param widget_p
   */
  protected void handleMenuSelected(Widget widget_p) {
    MenuItem item = (MenuItem) widget_p;
    if (item.getData().equals(NEW_TARGETCOMP_DATA)) {
      DecompositionVisitor.addNewTargetComponent(this);
    } else if (item.getData().equals(REUSE_COMP_DATA)) {
      ILabelProvider _labelProvider = new NamedElementLabelProvider();
      ILabelProvider _statusLabelProvider = new NamedElementFullLabelProvider();

      MdeElementListSelectionDialog selectionDialog =
          new MdeElementListSelectionDialog(_targetTreeViewer.getControl().getShell(), _labelProvider, _statusLabelProvider);
      selectionDialog.setTitle(Messages.getString("LCDecomp.title.label")); //$NON-NLS-1$
      selectionDialog.setMultipleSelection(false);
      selectionDialog.setMatchEmptyString(true);
      selectionDialog.setIgnoreCase(true);
      selectionDialog.setHelpAvailable(true);

      DecompositionComponent sourceComponent = _decompositionModel.getSourceComponent();
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
        _tabFolder.getSelection().setData(REUSE_COMP_DATA, selectionDialog.getFirstResult());
        DecompositionVisitor.reuseComponent(this);
      }
    }
  }

  /**
   * @param widget_p
   */
  protected void handleTabItemChanged(Widget widget_p) {
    _generalViewer.createDNDForTargetViewer((CTabFolder) widget_p);
  }

  /**
   * Initialize the listener
   */
  private void initializeListeners() {
    // Called when the end-user clicks on a button.
    _buttonClickedListener = new SelectionAdapter() {
      /**
       * @see SelectionAdapter#widgetSelected(SelectionEvent)
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        Widget widget = event_p.widget;
        if ((widget instanceof Button) || (widget instanceof ToolItem)) {
          if ((event_p.detail == SWT.ARROW) && widget.getData().equals(NEW_TARGETCOMP_DATA)) {
            Point point = new Point(event_p.x, event_p.y);
            point = Display.getCurrent().map(_targetToolBar, null, point);
            _targetMenu.setLocation(point);
            _targetMenu.setVisible(true);
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

  /**
   * 
   */
  private void initMenus() {
    _targetMenu = new Menu(_rightComposite.getShell());
    MenuItem lcMenuItem = new MenuItem(_targetMenu, SWT.PUSH);
    lcMenuItem.setText(Messages.getString("LCDecomp.lc.menu.label")); //$NON-NLS-1$
    lcMenuItem.addSelectionListener(_buttonClickedListener);
    lcMenuItem.setData(NEW_TARGETCOMP_DATA);

    MenuItem lcShortcutMenuItem = new MenuItem(_targetMenu, SWT.PUSH);
    lcShortcutMenuItem.setText(Messages.getString("LCDecomp.lcreuse.menu.label")); //$NON-NLS-1$
    lcShortcutMenuItem.addSelectionListener(_buttonClickedListener);
    lcShortcutMenuItem.setData(REUSE_COMP_DATA);

  }

  /**
   * 
   */
  void refreshItems(TreeViewer viewer) {
    if ((_tabFolder == null) || _tabFolder.isDisposed()) {
      _generalViewer.refreshItems(null);
      return;
    }
    _generalViewer.refreshItems(viewer);
  }

  /**
   * 
   */
  public void removeShortcutInput(Object element_p) {
    if (_shortcutItems != null) {
      _shortcutItems.remove(element_p);
    }
  }

  /**
   * @param decompNameText_p the decompNameText to set
   */
  public void setDecompNameText(Text decompNameText_p) {
    _decompNameText = decompNameText_p;
  }

  /**
   * Sets the enabled status of the decomposition fields
   */
  public void setDecompositionFieldsEnabled(int size_p) {
    _removeDecompItem.setEnabled((size_p > 1));
  }

  /**
   * @param decompositionModel_p the decompositionModel to set
   */
  public void setDecompositionModel(DecompositionModel decompositionModel_p) {
    _decompositionModel = decompositionModel_p;
  }

  /**
   * Sets the name for the first decomposition
   */
  void setDecompositionName(boolean flag) {
    List<Decomposition> list = _decompositionModel.getDecompositions();
    int size = list.size();
    Decomposition firstDecomp = (Decomposition) _tabFolder.getItem(0).getData();// list.get(0);
    String name = Messages.getString("LCDecompGeneralViewer.decomposition.name"); //$NON-NLS-1$
    firstDecomp.setName((size > 1) ? flag ? name + "0" : name + "1" : Util.ZERO_LENGTH_STRING); //$NON-NLS-1$ //$NON-NLS-2$
    _tabFolder.getItem(0).setText(firstDecomp.getName());
  }

  /**
   * 
   */
  void setDefaultSelectedTabIndex() {
    _tabFolder.setSelection(0);
    _generalViewer.createDNDForTargetViewer(_tabFolder);
  }

  public void setDetachInterfaceButtonEnabled(boolean isEnabled_p) {
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#setInput(java.lang.Object)
   */
  @Override
  public void setInput(Object input_p) {
    // do nothing
  }

  void setRemoveTCButtonEnabled(boolean isEnabled_p) {
    ToolItem removeItem = (ToolItem) _tabFolder.getSelection().getData(REMOVE_TARGETCOMP_DATA);
    if (removeItem != null) {
      removeItem.setEnabled(isEnabled_p);
    }
  }

  /**
   * 
   */
  private void setTabEditor() {
    if (_tabFolder != null) {

      _tabFolder.addMouseListener(new MouseAdapter() {

        @Override
        @SuppressWarnings("synthetic-access")
        public void mouseDoubleClick(MouseEvent me_p) {
          if (_tabFolder.getItemCount() > 1) {
            final CTabItem item = _tabFolder.getSelection();
            Point pt = new Point(me_p.x, me_p.y);
            PopupDialog popup =
                new RenameDecompositionDialog(_tabFolder.getShell(), PopupDialog.HOVER_SHELLSTYLE, true, false, false, false, null, Messages
                    .getString("LCDecompGeneralViewer.renamedecomp.popupinfo"), item, //$NON-NLS-1$
                    pt, DecompositionTargetViewer.this);
            popup.open();

            item.addListener(SWT.MouseDoubleClick, DecompositionUtil.getRenameDecompListener(item));
          }
        }
      });
    }
  }

  /**
   * @param tabFolder_p the tabFolder to set
   */
  public void setTabFolder(CTabFolder tabFolder_p) {
    _tabFolder = tabFolder_p;
  }

  /**
   * @param targetTreeViewer_p the targetTreeViewer to set
   */
  public void setTargetTreeViewer(TreeViewer targetTreeViewer_p) {
    _targetTreeViewer = targetTreeViewer_p;
  }

  /**
   * Sets the tree cell editor
   * @param item_p the tab item in which the tree item has to be edited
   */
  private void setTreeCellEditor(final CTabItem item_p) {
    final TreeViewer viewer = (TreeViewer) item_p.getData(TARGET_TREEVIEWER_DATA);
    Tree tree = viewer.getTree();
    tree.addListener(SWT.MouseDown, new Listener() {

      @SuppressWarnings("synthetic-access")
      public void handleEvent(Event event) {
        ((DecompositionTreeCellModifier) viewer.getCellModifier()).setEnabled(false);
        _generalViewer.showErrorMessage(null);
        if (null != event) {
          Object data = event.data;
          if ((null != data) && data.equals("rename")) { //$NON-NLS-1$
            DecompositionComponent comp = (DecompositionComponent) event.item.getData();
            _decompositionModel.setPathForNewTargetComponent(comp);
            _generalViewer.updateStatusBar(comp);
          }
        }
      }
    });
    viewer.addDoubleClickListener(new IDoubleClickListener() {
      public void doubleClick(DoubleClickEvent event_p) {
        DecompositionVisitor.renameTargetComponent(DecompositionTargetViewer.this);
      }
    });
  }

  /**
   * 
   */
  void showErrorMessage(String msg) {
    _generalViewer.showErrorMessage(msg);
  }
}
