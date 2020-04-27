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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.progress.IProgressService;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.CapellaPatternFilter;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.ui.search.CapellaSearchConstants;
import org.polarsys.capella.core.ui.search.CapellaSearchPage;
import org.polarsys.capella.core.ui.search.CapellaSearchSettings;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForClassItem;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForItem;

public class CapellaLeftSearchForContainerArea extends AbstractCapellaSearchForContainerArea {
  protected AbstractSearchForContentProvider searchForContentProvider;
  Button checkboxFilterAbstract;
  Button checkboxFilterNonSemantic;

  public CapellaLeftSearchForContainerArea(Group parent, CapellaSearchPage searchPage) {
    super(parent, null, searchPage);
  }

  @Override
  protected ClassContentProvider getSearchForContentProvider() {
    if (searchForContentProvider == null) {
      searchForContentProvider = new ClassContentProvider();
    }
    return (ClassContentProvider) searchForContentProvider;
  }

  @Override
  protected PatternFilter createPatternFilter() {
    return new CapellaPatternFilter();
  }

  @Override
  public void applySearchSettings(CapellaSearchSettings settings) {
    super.applySearchSettings(settings.getSearchClassItems());

    boolean updateRequired = false;

    boolean settingsAbstractChecked = settings.isAbstractChecked();

    // TODO This is not enough, Should also check the status for the ClassContentProvider
    // to avoid useless refresh when the history settings are the default ones
    if (settingsAbstractChecked != isAbstractChecked()) {
      checkboxFilterAbstract.setSelection(settingsAbstractChecked);
      updateRequired = true;
    }

    boolean settingsNonSemanticChecked = settings.isNonSemanticChecked();
    if (settingsAbstractChecked != isNonSemanticChecked()) {
      checkboxFilterNonSemantic.setSelection(settingsNonSemanticChecked);
      updateRequired = true;
    }

    if (updateRequired) {
      applyFilter();
    }

  }

  public boolean isAbstractChecked() {
    return checkboxFilterAbstract.getSelection();
  }

  public boolean isNonSemanticChecked() {
    return checkboxFilterNonSemantic.getSelection();
  }

  @Override
  public void updateSearchSettings() {
    // setSearchMetaClasses, beside the metaclass it contains also the category (Diagram Elements or Model Elements)
    searchPage.getCapellaSearchSettings().setSearchClassItems(getCheckedElements());
  }

  public void createFiltercontainer(Group parentGroup) {
    Group searchForSelectionGroup = new Group(parentGroup, SWT.NONE);
    GridLayoutFactory.swtDefaults().numColumns(2).applyTo(searchForSelectionGroup);

    GridData gdGrp = new GridData(GridData.FILL_BOTH);
    gdGrp.widthHint = 50;
    searchForSelectionGroup.setLayoutData(gdGrp);

    searchForSelectionGroup.setText(CapellaSearchConstants.Filters_Label);
    checkboxFilterAbstract = createCheckboxFilters(searchForSelectionGroup, CapellaSearchConstants.Abstract_Label,
        true);
    checkboxFilterNonSemantic = createCheckboxFilters(searchForSelectionGroup, CapellaSearchConstants.Semantic_Label,
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
      public void widgetSelected(SelectionEvent event) {
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
        ClassContentProvider provider = getSearchForContentProvider();
        provider.setFilterAbstract(isAbstractChecked());
        provider.setFilterNonSemantic(isNonSemanticChecked());
        checkboxTreeViewer.refresh();
        checkboxTreeViewer.expandAll();
      }
    };
    IProgressService service = PlatformUI.getWorkbench().getProgressService();
    try {
      service.run(false, false, runnable);
    } catch (InterruptedException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void refreshOtherSideArea() {
    if (otherSideArea != null) {
      otherSideArea.filteredTree.getViewer().refresh();
    }
  }

  @Override
  protected void createContentArea() {
    super.createContentArea();
    // Map of fix categories and their index
    Map<String, Integer> fixedCategories = new HashMap<>();
    fixedCategories.put(CapellaSearchConstants.ModelElements_Key, 0);
    fixedCategories.put(CapellaSearchConstants.DiagramElements_Key, 1);

    filteredTree.getViewer().setComparator(new ViewerComparator() {
      @Override
      public int compare(Viewer viewer, Object e1, Object e2) {
        if (e1 instanceof SearchForItem && e2 instanceof SearchForItem) {
          return ((SearchForItem) e1).getText().compareTo(((SearchForItem) e2).getText());
        } else if (e1 instanceof String && e2 instanceof String) {
          String category1 = (String) e1;
          String category2 = (String) e2;
          Integer indexOfCategory1 = fixedCategories.get(category1);
          Integer indexOfCategory2 = fixedCategories.get(category2);
          if (indexOfCategory1 != null && indexOfCategory2 != null) {
            return indexOfCategory1 < indexOfCategory2 ? -1 : 1;
          } else if (indexOfCategory1 != null) {
            return -1;
          } else if (indexOfCategory2 != null) {
            return 1;
          }
          return category1.compareTo(category2);
        }
        return 0;
      }
    });
  }

  @Override
  protected SelectionListener getRestoreDefaultsSelectionListener() {
    return new SelectionListener() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        applyDefaultSearchSettings();
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        widgetSelected(e);
      }
    };
  }

  @Override
  public void applyDefaultSearchSettings() {
    Set<Object> namedElementClasses = SearchForItemCache.getInstance().getClassItems().stream()
        .filter(SearchForClassItem.class::isInstance).map(SearchForClassItem.class::cast)
        .filter(item -> CapellacorePackage.Literals.NAMED_ELEMENT.isSuperTypeOf((EClass) item.getObject()))
        .collect(Collectors.toSet());
    filteredTree.getCheckboxTreeViewer().setCheckedElements(namedElementClasses.toArray());
    cleanCheckedElements();
    for (Object obj : filteredTree.getCheckboxTreeViewer().getCheckedElements()) {
      updateCheckedElements(obj, true);
    }
    updateSearchSettings();
    refreshOtherSideArea();
  }

}
