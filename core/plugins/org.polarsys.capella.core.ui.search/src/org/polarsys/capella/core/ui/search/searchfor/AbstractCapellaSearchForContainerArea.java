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
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.progress.IProgressService;
import org.polarsys.capella.core.ui.search.Activator;
import org.polarsys.capella.core.ui.search.CapellaSearchConstants;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;

public abstract class AbstractCapellaSearchForContainerArea {
  protected Button selectAllButton;
  protected Button deselectAllButton;
  protected Button defaultButton;
  protected AbstractMetaModelParticipantsItemProvider partictipantsItemProvider;

  protected Set<Object> checkedElements = new HashSet<Object>();
  protected Set<Object> displayedElements = new HashSet<Object>();
  protected PatternFilter patternFilter;
  CheckboxFilteredTree filteredTree;
  protected Group parentGroup;
  protected int participantsCheckStrategy = SWT.MULTI;
  protected AbstractCapellaSearchForContainerArea otherSideArea;

  public AbstractCapellaSearchForContainerArea(Group parent, AbstractCapellaSearchForContainerArea area) {
    parentGroup = parent;
    otherSideArea = area;
    createContent();
  }

  protected void createContent() {
    createContentArea();
    createButtonsArea();
  }

  protected void createContentArea() {
    partictipantsItemProvider = getPartictipantsItemProvider();
    patternFilter = createPatternFilter();

    filteredTree = new CheckboxFilteredTree(parentGroup, SWT.BORDER, patternFilter);
    filteredTree.getViewer().setContentProvider(partictipantsItemProvider);
    filteredTree.getViewer()
        .setLabelProvider(new AdapterFactoryLabelProvider(getMetaElementComposeableAdapterFactory()) {
          @Override
          public Image getImage(Object object) {
            Image img = getTargetModelElementImage(object);
            return img != null ? img : super.getImage(object);
          }

          @Override
          public String getText(Object object) {
            String txt = getTargetModelElementText(object);
            return txt != null ? txt : super.getText(object);
          }

          @Override
          public String getColumnText(Object object, int columnIndex) {
            return super.getText(object);
          }
        });

    filteredTree.getViewer().setInput("");

    IStructuredContentProvider contentProvider = (IStructuredContentProvider) filteredTree.getViewer()
        .getContentProvider();
    ((CheckboxTreeViewer) filteredTree.getViewer()).expandAll();
    filteredTree.getViewer().getTree().setLayout(new GridLayout());

    GridData chechboxTreeViewerGridData = new GridData(GridData.FILL_BOTH);
    chechboxTreeViewerGridData.heightHint = 140;

    filteredTree.getViewer().getTree().setLayoutData(chechboxTreeViewerGridData);
    filteredTree.getViewer().setSorter(new ParticipantsViewerSorter());
    setCheckSubtree();
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
    viewer.setAllChecked(state);
  }

  protected Image getTargetModelElementImage(Object object) {
    try {
      if (object instanceof ENamedElement) {
        String imagePath = "/icons/full/obj16/" + computeElementImageName(((ENamedElement) object).getName()) + ".gif";
        URL url = FileLocator.find(CapellaModellerEditPlugin.getPlugin().getBundle(), new Path(imagePath), null);
        if (url != null) {
          return ModelSearchImagesUtil.getImage(url);
        }
      }
    } catch (Throwable t) {
      Activator.getDefault().getLog()
          .log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Error while attempmting to retrieve image from edit"
              + CapellaModellerEditPlugin.getPlugin().getBundle() + " bundle"));
    }
    return null;
  }

  private String computeElementImageName(String name) {
    return name;
  }

  public final ComposeableAdapterFactory getMetaElementComposeableAdapterFactory() {
    List<AdapterFactory> adapterFactoryList = new ArrayList<AdapterFactory>();
    return new ComposedAdapterFactory(adapterFactoryList);
  }

  protected String getTargetModelElementText(Object object) {
    if (object instanceof ENamedElement)
      return ((ENamedElement) object).getName();
    if (object instanceof DRepresentationDescriptor)
      return ((DRepresentationDescriptor) object).getName();
    if (object instanceof DRepresentationElement)
      return ((DRepresentationElement) object).getName();

    return null;
  }

  protected abstract AbstractMetaModelParticipantsItemProvider getPartictipantsItemProvider();

  /*
   * set the listeners in order to check all the children when a parent is selected
   */
  protected void setCheckSubtree() {};
  
  public void setOtherSideArea(AbstractCapellaSearchForContainerArea area) {
    this.otherSideArea = area;
  }
}
