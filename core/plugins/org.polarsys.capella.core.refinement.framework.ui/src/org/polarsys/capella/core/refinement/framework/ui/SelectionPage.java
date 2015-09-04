/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.framework.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.polarsys.capella.common.ui.toolkit.viewers.AbstractContextMenuFiller;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.ShowInDiagramAction;
import org.polarsys.capella.core.refinement.framework.ui.model.ComponentSelectionItem;
import org.polarsys.capella.core.refinement.framework.ui.model.SelectionItemNode;
import org.polarsys.capella.core.refinement.framework.ui.model.TargetSelectionItem;

/**
 */
public class SelectionPage extends WizardPage implements ICheckStateListener {

  private String _nameLabel;
  private boolean _isMultipleSelection = false;
  private boolean _autoSelectChild = false;
  private boolean _selectAllByDefault = false;
  private boolean _showNameTextField = false;
  private boolean _isAmbiguityResolutionPage = false;
  private SelectionItemNode _root = null;
  private Button _selectAll = null;
  private Button _deselectAll = null;
  private Label _nameLbl = null;
  private Text _nameTxt = null;
  private Text _statusBar = null;

  protected CheckboxTreeViewer _tree = null;
  private AbstractContextMenuFiller _contextMenuManagerFiller;

  /**
   * 
   * @param root
   * @param title
   * @param description
   * @param isMultipleSelection
   * @param autoSelectChild
   * @param selectAllByDefault
   * @param showNameTextField
   * @param nameLabel
   */
  public SelectionPage(SelectionItemNode root, String title, String description, boolean isMultipleSelection,
      boolean autoSelectChild, boolean selectAllByDefault, boolean showNameTextField, String nameLabel,
      boolean isAmbiguityResolutionPage) {
    super("wizardPage"); //$NON-NLS-1$

    _root = root;
    _nameLabel = nameLabel;
    _isMultipleSelection = isMultipleSelection;
    _autoSelectChild = autoSelectChild;
    _selectAllByDefault = selectAllByDefault;
    _showNameTextField = showNameTextField;
    _isAmbiguityResolutionPage = isAmbiguityResolutionPage;
    setTitle(title);
    setDescription(description);
  }

  /**
   * 
   * @param root
   * @param title
   * @param description
   * @param isMultipleSelection
   * @param autoSelectChild
   */
  public SelectionPage(SelectionItemNode root, String title, String description, boolean isMultipleSelection,
      boolean autoSelectChild) {
    this(root, title, description, isMultipleSelection, autoSelectChild, false, false, "", false); //$NON-NLS-1$
  }

  /**
   * @param parent
   * 
   */
  public void createControl(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);

    GridData layoutData = null;
    GridLayout layout = new GridLayout();
    layout.numColumns = 5;
    layout.verticalSpacing = 4;
    container.setLayout(layout);

    if (_showNameTextField) {
      /** Creates the name TextField */
      _nameLbl = new Label(container, SWT.NONE);
      _nameLbl.setText(_nameLabel);
      layoutData = new GridData();
      layoutData.horizontalSpan = 1;
      _nameLbl.setLayoutData(layoutData);

      _nameTxt = new Text(container, SWT.BORDER);
      layoutData = new GridData();
      layoutData.horizontalAlignment = GridData.FILL;
      layoutData.horizontalSpan = 3;
      _nameTxt.setLayoutData(layoutData);
      _nameTxt.addModifyListener(new ModifyListener() {
        public void modifyText(ModifyEvent e) {
          validatePage();
        }
      });
    }

    /** Creates the Tree */
    _tree = new CheckboxTreeViewer(container);
    _tree.setContentProvider(new SelectionContentProvider());
    _tree.setLabelProvider(new SelectionLabelProvider());
    _tree.setUseHashlookup(true);

    _contextMenuManagerFiller = new AbstractContextMenuFiller() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void fillMenuManager(IMenuManager contextMenuManager, final ISelection selection) {
        final LocateInCapellaExplorerAction selectInExplorerAction = new LocateInCapellaExplorerAction() {
          /**
           * {@inheritDoc}
           */
          @Override
          protected ISelection getSelection() {
            return selection;
          }

          @Override
          protected Object getFirstSelectedElement(ISelection selection) {
            Object selectedObj = super.getFirstSelectedElement(selection);
            if (selectedObj instanceof SelectionItemNode)
              return ((SelectionItemNode) selectedObj).getData();
            return selectedObj;
          }
        };
        IAction showInBrowserAction = new Action() {
          /**
           * {@inheritDoc}
           */
          @Override
          public void run() {
            selectInExplorerAction.run(this);
          }
        };
        // Ignore workbench part site, since in a dialog, site has no meaning.
        selectInExplorerAction.shouldIgnoreWorkbenchPartSite(true);
        showInBrowserAction.setText("Show in Capella Explorer");
        showInBrowserAction.setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor(
            IImageKeys.IMG_SHOW_IN_CAPELLA_EXPLORER));
        selectInExplorerAction.selectionChanged(showInBrowserAction, selection);
        if (showInBrowserAction.isEnabled()) {
          contextMenuManager.add(showInBrowserAction);
        }

        ShowInDiagramAction showInDiagramAction = new ShowInDiagramAction() {

          private Object getFirstSelectedElement(IStructuredSelection selection) {
            Object selectedObj = selection.getFirstElement();
            if (selectedObj instanceof SelectionItemNode)
              return ((SelectionItemNode) selectedObj).getData();
            return selectedObj;
          }

          @Override
          public IStructuredSelection getStructuredSelection() {
            Object selectedObj = getFirstSelectedElement((IStructuredSelection) selection);
            return new StructuredSelection(selectedObj);
          }

          @Override
          protected boolean updateSelection(IStructuredSelection selection) {
            Object selectedObj = getFirstSelectedElement((IStructuredSelection) selection);
            return super.updateSelection(new StructuredSelection(selectedObj));
          }
        };
        showInDiagramAction.setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor(IImageKeys.IMG_SHOW_IN_DIAGRAM));
        showInDiagramAction.selectionChanged(showInDiagramAction.getStructuredSelection());
        if (showInDiagramAction.isEnabled()) {
          contextMenuManager.add(showInDiagramAction);
        }
      }
    };

    final MenuManager _contextMenuManager;
    Control control = _tree.getControl();
    _contextMenuManager = new MenuManager("#Popup"); //$NON-NLS-1$
    _contextMenuManager.setRemoveAllWhenShown(true);
    _contextMenuManager.addMenuListener(new IMenuListener() {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      public void menuAboutToShow(IMenuManager manager) {
        if (null != _contextMenuManagerFiller) {
          _contextMenuManagerFiller.fillMenuManager(_contextMenuManager, _tree.getSelection());
        }
      }
    });
    Menu _contextMenu = _contextMenuManager.createContextMenu(control);
    control.setMenu(_contextMenu);

    /** layout the tree viewer below the text field */
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.grabExcessVerticalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    layoutData.horizontalSpan = 4;
    layoutData.verticalAlignment = GridData.FILL;
    layoutData.verticalSpan = 2;
    _tree.getControl().setLayoutData(layoutData);

    _tree.setInput(_root);
    _tree.expandAll();
    _tree.setAllChecked(_selectAllByDefault);
    _tree.addCheckStateListener(this);
    _tree.addSelectionChangedListener(new ISelectionChangedListener() {
      /**
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      @SuppressWarnings("synthetic-access")
      public void selectionChanged(SelectionChangedEvent event) {
        if (_statusBar != null) {
          ContentViewer source = (ContentViewer) event.getSource();
          ILabelProvider labelProvider = (ILabelProvider) source.getLabelProvider();
          if (labelProvider instanceof SelectionLabelProvider) {
            SelectionLabelProvider prov = (SelectionLabelProvider) labelProvider;
            _statusBar.setText(prov.getExtendedText(event.getSelection()));
          }
        }
      }
    });

    if (_isMultipleSelection) {
      _selectAll = new Button(container, SWT.PUSH);
      _selectAll.setText(Messages.getString("SelectionPage.1")); //$NON-NLS-1$
      _selectAll.setSize(100, 20);
      layoutData = new GridData();
      layoutData.grabExcessVerticalSpace = true;
      layoutData.horizontalAlignment = GridData.FILL;
      layoutData.verticalAlignment = GridData.END;
      _selectAll.setLayoutData(layoutData);
      _selectAll.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent event) {
          for (Object obj : _tree.getCheckedElements())
            _tree.setChecked(obj, false);
          for (Object obj : _tree.getGrayedElements()) {
            _tree.setGrayChecked(obj, false);
          }
          _tree.setAllChecked(true);
          validatePage();
        }
      });
      _deselectAll = new Button(container, SWT.PUSH);
      _deselectAll.setText(Messages.getString("SelectionPage.2")); //$NON-NLS-1$
      layoutData = new GridData();
      layoutData.grabExcessVerticalSpace = true;
      layoutData.horizontalAlignment = GridData.FILL;
      layoutData.verticalAlignment = GridData.BEGINNING;
      _deselectAll.setLayoutData(layoutData);
      _deselectAll.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent event) {
          _tree.setAllChecked(false);
          validatePage();
        }
      });
    }

    _statusBar = new Text(container, SWT.READ_ONLY | SWT.BORDER);
    _statusBar.setEditable(false);
    layoutData = new GridData();
    layoutData.horizontalAlignment = SWT.FILL;
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalSpan = 4;
    _statusBar.setLayoutData(layoutData);

    setControl(container);
  }

  /**
   * 
   * @return
   */
  public List<SelectionItemNode> getSelection() {
    List<SelectionItemNode> list = new ArrayList<SelectionItemNode>();

    for (Object obj : _tree.getCheckedElements()) {
      list.add((SelectionItemNode) obj);
    }

    return list;
  }

  /**
   * @return
   */
  public String getNameValue() {
    if (_showNameTextField) {
      return _nameTxt.getText();
    }
    return null;
  }

  /**
   * @see ICheckStateListener#checkStateChanged(Event)
   */
  public void checkStateChanged(CheckStateChangedEvent event) {
    if (_isMultipleSelection && _autoSelectChild) {
      boolean isChecked = event.getChecked();
      TargetSelectionItem item = (TargetSelectionItem) event.getElement();
      TargetSelectionItem parent = (TargetSelectionItem) item.getParent();

      item.setIsChecked(isChecked);

      _tree.setSubtreeChecked(item, isChecked);
      _tree.setGrayChecked(item, false);
      _tree.setChecked(item, isChecked);

      if (parent.hasAllChildrenChecked()) {
        _tree.setGrayChecked(parent, false);
        _tree.setChecked(parent, true);
      } else if (parent.hasChildrenChecked()) {
        _tree.setGrayChecked(parent, true);
      } else {
        _tree.setChecked(parent, false);
      }
    }
    validatePage();
  }

  /**
   * Returns whether this page's controls currently all contain valid values.
   * 
   * @return <code>true</code> if all controls are valid, and <code>false</code> if at least one is invalid.
   */
  protected boolean validatePage() {
    boolean bRes = true;

    SelectionWizard wizard = (SelectionWizard) getWizard();
    if (wizard != null) {
      for (IValidator validator : wizard.getValidators()) {
        if (!validator.isValid(this)) {
          setMessage(validator.getMessage(), IMessageProvider.ERROR);
          bRes = false;
          break;
        }
      }
    }

    if (getSelection().isEmpty()) {
      setMessage(Messages.getString("SelectionPage.3"), IMessageProvider.ERROR); //$NON-NLS-1$
      bRes = false;
    } else if (!_isMultipleSelection && (getSelection().size() != 1)) {
      setMessage(Messages.getString("SelectionPage.4"), IMessageProvider.ERROR); //$NON-NLS-1$
      bRes = false;
    } else if (_showNameTextField && getNameValue().equals("")) { //$NON-NLS-1$
      setMessage(Messages.getString("SelectionPage.5"), IMessageProvider.ERROR); //$NON-NLS-1$
      bRes = false;
    }

    if (_isAmbiguityResolutionPage) {
      if (getSelection().size() > 0 && getSelection().get(0) instanceof ComponentSelectionItem) {
        if (((ComponentSelectionItem) getSelection().get(0)).getData() instanceof Scenario) {
          setMessage(Messages.getString("SelectionPage.6"), IMessageProvider.ERROR); //$NON-NLS-1$
          bRes = false;
        } else if (((ComponentSelectionItem) getSelection().get(0)).getData() instanceof SequenceMessage) {
          setMessage(Messages.getString("SelectionPage.7"), IMessageProvider.ERROR); //$NON-NLS-1$
          bRes = false;
        }
      }
    }

    if (bRes) {
      setMessage(null);
    }

    return bRes;
  }
}
