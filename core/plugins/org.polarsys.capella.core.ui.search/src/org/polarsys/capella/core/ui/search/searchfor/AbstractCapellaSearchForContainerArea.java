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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
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
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForItem;

public abstract class AbstractCapellaSearchForContainerArea {
  protected Button selectAllButton;
  protected Button deselectAllButton;
  protected Button defaultButton;

  protected PatternFilter patternFilter;
  protected CheckboxFilteredTree filteredTree;
  protected Group parentGroup;
  protected int participantsCheckStrategy = SWT.MULTI;
  protected AbstractCapellaSearchForContainerArea otherSideArea;
  protected CapellaSearchPage searchPage;

  public AbstractCapellaSearchForContainerArea(Group parent, AbstractCapellaSearchForContainerArea area,
      CapellaSearchPage page) {
    parentGroup = parent;
    otherSideArea = area;
    searchPage = page;
    createContent();  }

  protected void createContent() {
    createContentArea();
    createButtonsArea();
  }

  protected void createContentArea() {
    AbstractSearchForContentProvider partictipantsItemProvider = getSearchForContentProvider();
    patternFilter = createPatternFilter();

    filteredTree = new CheckboxFilteredTree(parentGroup, SWT.BORDER, patternFilter);
    filteredTree.getViewer().setContentProvider(partictipantsItemProvider);

    filteredTree.getViewer()
        .setLabelProvider(new SearchForLabelProvider());

    filteredTree.getViewer().setInput("");

    ((CheckboxTreeViewer) filteredTree.getViewer()).expandAll();
    filteredTree.getViewer().getTree().setLayout(new GridLayout());

    GridData chechboxTreeViewerGridData = new GridData(GridData.FILL_BOTH);
    chechboxTreeViewerGridData.heightHint = 140;

    filteredTree.getViewer().getTree().setLayoutData(chechboxTreeViewerGridData);
    filteredTree.getViewer().setComparator(new ViewerComparator() {
      @Override
      public int compare(Viewer testViewer, Object e1, Object e2) {
        if (e1 instanceof SearchForItem && e2 instanceof SearchForItem) {
          return ((SearchForItem) e1).getText().compareTo(((SearchForItem) e2).getText());
        } else if (e1 instanceof String && e2 instanceof String) {
          String cat1 = (String) e1;
          if (cat1.equals(CapellaSearchConstants.ModelElements_Key))
            return -1;
          return 1;
        }
        return 0;
      }
    });
    ((CheckboxTreeViewer) filteredTree.getViewer()).addCheckStateListener(getCheckStateListener());
  }

  protected ICheckStateListener getCheckStateListener() {
    return new ICheckStateListener() {
      public void checkStateChanged(final CheckStateChangedEvent event) {
        boolean state = event.getChecked();
        Object parent = event.getElement();
        updateCheckedElements(parent, state);
      }
    };
  }

  protected void updateCheckedElements(Object parent, boolean state) {
    // handle the inheritance check propagation
    boolean hasChildren = getSearchForContentProvider().hasChildren(parent);
    CheckboxTreeViewer viewer = (CheckboxTreeViewer) filteredTree.getViewer();
    if(hasChildren) {
      viewer.setSubtreeChecked(parent, state);
    }
    else {
      viewer.setChecked(parent, state);
    }
    updateSearchSettings();
    refreshOtherSideArea();
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

  public void checkAll(CheckboxTreeViewer viewer, boolean state) {
    Object[] viewerElements = getSearchForContentProvider().getElements("");
    for (Object obj : viewerElements) {
      viewer.setSubtreeChecked(obj, state);
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
    }
    
    refreshOtherSideArea();
  }
  
  public abstract void updateSearchSettings();

  public void refresh() {
    filteredTree.getViewer().refresh();
  }
  
  public Set<Object> getCheckedElements() {
    return new HashSet<>(Arrays.asList(((CheckboxTreeViewer) filteredTree.getViewer()).getCheckedElements()));
  }
}
