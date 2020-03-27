/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search.searchfor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.progress.IProgressService;
import org.polarsys.capella.core.ui.search.CapellaSearchConstants;
import org.polarsys.capella.core.ui.search.CapellaSearchPage;

public abstract class AbstractCapellaSearchForContainerArea {
  protected Button selectAllButton;
  protected Button deselectAllButton;
  protected Button defaultButton;
  protected Button restoreDefaultsButton;

  protected PatternFilter patternFilter;
  protected CheckboxFilteredTree filteredTree;
  protected Group parentGroup;
  protected int participantsCheckStrategy = SWT.MULTI;
  protected AbstractCapellaSearchForContainerArea otherSideArea;
  protected CapellaSearchPage searchPage;

  // Collection of elements currently checked by user.
  protected Set<Object> checkedElements;

  public AbstractCapellaSearchForContainerArea(Group parent, AbstractCapellaSearchForContainerArea area,
      CapellaSearchPage page) {
    checkedElements = new HashSet<>();
    parentGroup = parent;
    otherSideArea = area;
    searchPage = page;
    createContent();
  }

  protected void createContent() {
    createContentArea();
    createButtonsArea();
  }

  protected void createContentArea() {
    AbstractSearchForContentProvider partictipantsItemProvider = getSearchForContentProvider();
    patternFilter = createPatternFilter();

    filteredTree = new CheckboxFilteredTree(parentGroup, SWT.BORDER, patternFilter, checkedElements);
    filteredTree.getViewer().setContentProvider(partictipantsItemProvider);

    filteredTree.getViewer().setLabelProvider(new SearchForLabelProvider());

    filteredTree.getViewer().setInput("");

    ((CheckboxTreeViewer) filteredTree.getViewer()).expandAll();
    filteredTree.getViewer().getTree().setLayout(new GridLayout());

    GridData chechboxTreeViewerGridData = new GridData(GridData.FILL_BOTH);
    chechboxTreeViewerGridData.heightHint = 140;
    chechboxTreeViewerGridData.widthHint = 140;

    filteredTree.getViewer().getTree().setLayoutData(chechboxTreeViewerGridData);

    ((CheckboxTreeViewer) filteredTree.getViewer()).addCheckStateListener(getCheckStateListener());
  }

  protected ICheckStateListener getCheckStateListener() {
    return new ICheckStateListener() {
      public void checkStateChanged(final CheckStateChangedEvent event) {
        boolean state = event.getChecked();
        Object parent = event.getElement();
        setCheckedState(parent, state);
      }
    };
  }

  protected void setCheckedState(Object parent, boolean state) {
    // handle the inheritance check propagation
    boolean hasChildren = getSearchForContentProvider().hasChildren(parent);
    CheckboxTreeViewer viewer = (CheckboxTreeViewer) filteredTree.getViewer();
    viewer.setChecked(parent, state);
    updateCheckedElements(parent, state);
    if (hasChildren) {
      for (Object child : getSearchForContentProvider().getChildren(parent)) {
        viewer.setChecked(child, state);
        updateCheckedElements(child, state);
      }
    }
    updateSearchSettings();
    refreshOtherSideArea();
  }

  protected void updateCheckedElements(Object obj, boolean state) {
    if (state) {
      checkedElements.add(obj);
    } else {
      checkedElements.remove(obj);
    }
  }
  
  protected void cleanCheckedElements() {
    checkedElements.clear();
  }

  protected void createButtonsArea() {
    // buttons area
    Composite buttonsContainer = new Composite(parentGroup, SWT.NONE);
    buttonsContainer.setLayout(new GridLayout());

    GridData rightPaneContainerGridData = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_CENTER);
    rightPaneContainerGridData.heightHint = 140;
    buttonsContainer.setLayoutData(rightPaneContainerGridData);

    createSelectAllButton(buttonsContainer);
    createDeselectAllButton(buttonsContainer);
    createRestoreDefaultsButton(buttonsContainer);
  }

  protected void createSelectAllButton(Composite rightPaneContainer) {
    // Select All butons
    selectAllButton = new Button(rightPaneContainer, SWT.PUSH);
    selectAllButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    selectAllButton.setText(CapellaSearchConstants.SelectAllButton_Name);
    // add listener
    selectAllButton.addSelectionListener(getSelectionListener(true));
    selectAllButton.setEnabled(SWT.MULTI == participantsCheckStrategy);
  }

  protected void createDeselectAllButton(Composite rightPaneContainer) {
    deselectAllButton = new Button(rightPaneContainer, SWT.PUSH);
    deselectAllButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    deselectAllButton.setText(CapellaSearchConstants.DeselectAllButton_Name);
    deselectAllButton.addSelectionListener(getSelectionListener(false));

    deselectAllButton.setEnabled(SWT.MULTI == participantsCheckStrategy);
  }

  protected void createRestoreDefaultsButton(Composite rightPaneContainer) {
    restoreDefaultsButton = new Button(rightPaneContainer, SWT.PUSH);
    restoreDefaultsButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    restoreDefaultsButton.setText(CapellaSearchConstants.RestoreDefaultsButton_Name);
    restoreDefaultsButton.addSelectionListener(getRestoreDefaultsSelectionListener());
  }

  protected SelectionListener getSelectionListener(boolean selected) {
    return new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        IWorkbench wb = PlatformUI.getWorkbench();
        IProgressService ps = wb.getProgressService();
        try {
          ps.runInUI(ps, new IRunnableWithProgress() {

            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
              CheckboxTreeViewer checkboxTreeViewer = (CheckboxTreeViewer) filteredTree.getViewer();
              checkAll(checkboxTreeViewer, selected);
              updateSearchSettings();
              refreshOtherSideArea();
            }
          }, null);
        } catch (InvocationTargetException e1) {
          e1.printStackTrace();
        } catch (InterruptedException e1) {
          e1.printStackTrace();
        }
      }

      public void widgetDefaultSelected(SelectionEvent e) {
        widgetSelected(e);
      }
    };
  }

  protected abstract PatternFilter createPatternFilter();

  protected abstract SelectionListener getRestoreDefaultsSelectionListener();

  public void checkAll(CheckboxTreeViewer viewer, boolean state) {
    Object[] viewerElements = getSearchForContentProvider().getElements("");
    for (Object obj : viewerElements) {
      setCheckedState(obj, state);
    }
  }

  protected abstract AbstractSearchForContentProvider getSearchForContentProvider();

  public void setOtherSideArea(AbstractCapellaSearchForContainerArea area) {
    this.otherSideArea = area;
  }

  public void refreshOtherSideArea() {
    // do nothing, want to refresh only the right side (attributes) based on the left (metaclasses) selection
  }

  public void applySearchSettings(Set<Object> objects) {
    CheckboxTreeViewer checkboxTreeViewer = (CheckboxTreeViewer) filteredTree.getViewer();
    checkAll(checkboxTreeViewer, false);

    for (Object obj : objects) {
      checkboxTreeViewer.setChecked(obj, true);
      updateCheckedElements(obj, true);
    }

    refreshOtherSideArea();
  }

  public abstract void updateSearchSettings();

  public abstract void applyDefaultSearchSettings();

  public void refresh() {
    filteredTree.getViewer().refresh();
  }

  public Set<Object> getCheckedElements() {
    return checkedElements;
  }
}
