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

import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.progress.IProgressService;
import org.polarsys.capella.core.ui.search.CapellaSearchConstants;
import org.polarsys.capella.core.ui.search.CapellaSearchPage;

public class CapellaLeftSearchForContainerArea extends AbstractCapellaSearchForContainerArea {
  protected AbstractMetaModelParticipantsItemProvider partictipantsItemProvider;
  Button checkboxFilterAbstract;
  Button checkboxFilterSemantic;

  public CapellaLeftSearchForContainerArea(Group parent, CapellaSearchPage searchPage) {
    super(parent, null, searchPage);
  }

  @Override
  protected AbstractMetaModelParticipantsItemProvider getPartictipantsItemProvider() {
    if (partictipantsItemProvider == null) {
      partictipantsItemProvider = new MetaClassesParticipantsItemProvider(this);
    }
    return partictipantsItemProvider;
  }

  protected PatternFilter createPatternFilter() {
    return new PatternFilter() {
      @Override
      public Object[] filter(Viewer viewer, Object parent, Object[] elements) {
        Object[] result = super.filter(viewer, parent, elements);
        if (parent != null) {
          if (parent.equals("")) {
            displayedElements.clear();
            for (Object element : result) {
              updateDisplayedElements(filter(viewer, element, partictipantsItemProvider.getChildren(element)));
            }
          }
        }
        return result;
      }

      private void updateDisplayedElements(Object[] elements) {
        for (Object displayedElement : elements) {
          if (displayedElement instanceof EClass) {
            displayedElements.add(displayedElement);
          }
        }
      }
    };
  }

  @Override
  protected void setCheckSubtree() {
    ((CheckboxTreeViewer) filteredTree.getViewer()).addCheckStateListener(getCheckStateListener());
  }

  private ICheckStateListener getCheckStateListener() {
    return new ICheckStateListener() {
      public void checkStateChanged(final CheckStateChangedEvent event) {
        CheckboxTreeViewer viewer = (CheckboxTreeViewer) filteredTree.getViewer();
        boolean state = event.getChecked();
        Object parent = event.getElement();
        if (state == true)
          checkedElements.add(parent);
        else
          checkedElements.remove(parent);

        // handle the inheritance check propagation
        Object[] changedObjects = partictipantsItemProvider.getChildren(parent);

        for (Object obj : changedObjects) {
          viewer.setChecked(obj, state);
          if (state == true) {
            checkedElements.add(obj);
          } else {
            checkedElements.remove(obj);
          }
        }

        searchPage.updateValidationStatus(searchPage.getCapellaSearchSettings().validate());
        // setSearchMetaClasses, beside the metaclass it contains also the category (Diagram Elements or Model Elements)
        searchPage.getCapellaSearchSettings().setSearchMetaClasses(checkedElements);
        // refresh the attributes in right panel
        if (otherSideArea != null) {
          otherSideArea.filteredTree.getViewer().refresh();
        }
      }
    };
  }

  public void createFiltercontainer(Group parentGroup) {
    Group searchForSelectionGroup = new Group(parentGroup, SWT.NONE);
    GridLayoutFactory.swtDefaults().numColumns(2).applyTo(searchForSelectionGroup);

    GridData gdGrp = new GridData(GridData.FILL_BOTH);
    gdGrp.widthHint = 50;
    searchForSelectionGroup.setLayoutData(gdGrp);

    searchForSelectionGroup.setText(CapellaSearchConstants.Filters_Label);
    checkboxFilterAbstract = createCheckboxFilters(searchForSelectionGroup, CapellaSearchConstants.Abstract_Label,
        false);
    checkboxFilterSemantic = createCheckboxFilters(searchForSelectionGroup, CapellaSearchConstants.Semantic_Label,
        true);
  }

  private Button createCheckboxFilters(Composite group, String label, boolean selected) {
    Button checkboxFilters = new Button(group, SWT.CHECK);
    checkboxFilters.setText(label);
    GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(checkboxFilters);
    checkboxFilters.setFont(group.getFont());
    checkboxFilters.setSelection(selected);

    checkboxFilters.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        applyFilter();
      }
    });
    return checkboxFilters;
  }

  protected void applyFilter() {
    IRunnableWithProgress runnable = new IRunnableWithProgress() {
      @Override
      public void run(IProgressMonitor monitor) throws InvocationTargetException {
        CheckboxTreeViewer checkboxTreeViewer = (CheckboxTreeViewer) filteredTree.getViewer();
        MetaClassesParticipantsItemProvider provider = (MetaClassesParticipantsItemProvider) getPartictipantsItemProvider();
        provider.setShowAbstract(checkboxFilterAbstract.getSelection());
        provider.setShowSemantics(checkboxFilterSemantic.getSelection());
        checkboxTreeViewer.refresh();
        checkboxTreeViewer.expandAll();
      }
    };
    IProgressService service = PlatformUI.getWorkbench().getProgressService();
    try {
      service.run(false, false, runnable);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  public void addFilters() {
    Composite treeViewerParent = parentGroup;

    // treeViewerParent.setData(gridData);
    Group filterGrp = new Group(treeViewerParent, SWT.FILL);
    GridLayout filterGrpLayout = new GridLayout(SWT.NONE, true);
    GridData gdData = new GridData(GridData.FILL_BOTH);
    filterGrpLayout.numColumns = 2;
    gdData.grabExcessHorizontalSpace = true;
    gdData.grabExcessVerticalSpace = true;
    gdData.heightHint = 200;
    filterGrp.setLayoutData(gdData);
    filterGrpLayout.marginHeight = 0;
    filterGrpLayout.marginWidth = 0;
    filterGrp.setLayout(filterGrpLayout);

    Group toBefilteredGrp = new Group(filterGrp, SWT.NONE);
    toBefilteredGrp.setText("Filters :");
    GridData toBefilteredGrpGdData = new GridData(GridData.FILL_BOTH);
    toBefilteredGrpGdData.grabExcessHorizontalSpace = true;
    toBefilteredGrpGdData.grabExcessVerticalSpace = true;
    toBefilteredGrpGdData.widthHint = 100;
    toBefilteredGrp.setLayoutData(toBefilteredGrpGdData);

    GridLayout toBeFilteredGrpGridLayout = new GridLayout(SWT.FILL, false);
    toBeFilteredGrpGridLayout.numColumns = 2;
    toBefilteredGrp.setLayout(toBeFilteredGrpGridLayout);

    // GridData abstractFilterGrpGridData = new GridData(SWT.NONE);
    // abstractFilterGrpGridData.grabExcessHorizontalSpace = true;
    // abstractFilterGrp.setData(abstractFilterGrpGridData);

    GridData abstractFilterButtonGdData = new GridData();
    Button abstractFilterButton = new Button(toBefilteredGrp, SWT.CHECK);
    abstractFilterButton.setLayoutData(abstractFilterButtonGdData);
    ;

    GridData abstractFilterTxtGdData = new GridData();
    Text abstractFilterTxt = new Text(toBefilteredGrp, SWT.NONE);
    abstractFilterTxt.setLayoutData(abstractFilterTxtGdData);
    abstractFilterTxt.setText("Abstract");

    abstractFilterButton.addSelectionListener(new SelectionListener() {

      @Override
      public void widgetSelected(SelectionEvent e) {
        // filtersAbstract();
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        // TODO Auto-generated method stub

      }
    });

    Group toBeFocusedGrp = new Group(filterGrp, SWT.FILL);
    toBeFocusedGrp.setText("Show Only :");

    GridData toBeFocusedGrpGdData = new GridData(GridData.FILL_BOTH);
    toBeFocusedGrpGdData.grabExcessHorizontalSpace = true;
    toBeFocusedGrpGdData.grabExcessVerticalSpace = true;
    toBeFocusedGrpGdData.widthHint = 100;
    toBeFocusedGrp.setLayoutData(toBeFocusedGrpGdData);

    GridLayout toBeFocusedGrpLayout = new GridLayout(SWT.FILL, false);
    toBeFocusedGrpLayout.numColumns = 2;
    toBeFocusedGrp.setLayout(toBeFocusedGrpLayout);

    GridData semanticFilterButtonGdData = new GridData();
    Button semanticFilterButton = new Button(toBeFocusedGrp, SWT.CHECK);
    semanticFilterButton.setLayoutData(semanticFilterButtonGdData);

    GridData semanticFilterTxtGdData = new GridData();
    Text semanticFilterTxt = new Text(toBeFocusedGrp, SWT.NONE);
    semanticFilterTxt.setText("Semantics");
    semanticFilterTxt.setLayoutData(semanticFilterTxtGdData);

    semanticFilterButton.addSelectionListener(new SelectionListener() {

      @Override
      public void widgetSelected(SelectionEvent e) {
        // showOnlySemantics();

      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        // TODO Auto-generated method stub

      }
    });

  }

}
