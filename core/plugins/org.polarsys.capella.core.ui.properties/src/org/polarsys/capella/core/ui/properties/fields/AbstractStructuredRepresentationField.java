/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.fields;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.IImageKeys;
import org.polarsys.capella.core.ui.properties.viewers.IDelegatedViewer;

/**
 * Display a table that contains elements pointing to other ones.
 */
public abstract class AbstractStructuredRepresentationField extends AbstractSemanticField implements ISelectionChangedListener {
  protected EReference _referencedFeature;
  private String _label;
  protected IDelegatedViewer _delegatedViewer;
  private Button _deleteBtn;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param referencedFeature a feature that refers to another element from elements contained by the semantic feature.
   * @param label the label displayed at the right top of the table.
   * @param viewerType
   */
  public AbstractStructuredRepresentationField(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory,
      EReference referencedFeature, String label, IDelegatedViewer delegatedViewer)
  {
    super(widgetFactory);

    _referencedFeature = referencedFeature;
    _label = label;
    _delegatedViewer = delegatedViewer;
    _delegatedViewer.addSelectionChangedListener(this);

    createActions(parent);
    _delegatedViewer.createContainer(parent);
  }

  /**
   * Create the actions.
   */
  private void createActions(Composite parent) {
    CLabel label = widgetFactory.createCLabel(_delegatedViewer.getViewerGroup(parent), _label);
    label.setLayoutData(new GridData(SWT.FILL, GridData.VERTICAL_ALIGN_FILL, true, false));

    createCustomActions(parent);

    _deleteBtn = createTableButton(parent, CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.IMG_DELETE_BUTTON), new Runnable() {
      public void run() {
        handleDelete();
      }
    });
    _deleteBtn.setToolTipText("Remove selected elements");
    _deleteBtn.setEnabled(false);
  }

  /**
   * Create the custom actions.
   */
  protected abstract void createCustomActions(Composite parent);

  /**
   * Create a button located at the right top corner of the table.
   * @param parent
   * @param image
   * @param runnable
   * @return a not <code>null</code> instance.
   */
  protected Button createTableButton(Composite parent, Image image, final Runnable runnable) {
    Button tableButton = widgetFactory.createButton(_delegatedViewer.getViewerGroup(parent), null, SWT.PUSH);
    tableButton.setImage(image);
    tableButton.setLayoutData(new GridData(GridData.END, GridData.VERTICAL_ALIGN_FILL, false, false));
    tableButton.addSelectionListener(new SelectionAdapter() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void widgetSelected(SelectionEvent e) {
        runnable.run();
      }
    });
    return tableButton;
  }

  /**
   * Handle Delete button.
   */
  protected abstract void handleDelete();

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement) {
    loadData(semanticElement, semanticFeature);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement, EStructuralFeature semanticFeature) {
    super.loadData(semanticElement, semanticFeature);

    // Get elements for the semantic feature.
    if (null != _delegatedViewer) {
      _delegatedViewer.setInput(getReferencedElementsByContainedOnes());
    }
  }

  /**
   * Get referenced elements of the semantic element according to the semantic feature and the referenced one.
   * @return a not <code>null</code> list.
   */
  @SuppressWarnings("unchecked")
  protected List<EObject> getReferencedElementsByContainedOnes() {
    List<EObject> containedElements = (List<EObject>) semanticElement.eGet(semanticFeature);
    ArrayList<EObject> referencedElements = new ArrayList<>(0);
    // Collect referenced elements according specified referenced feature.
    for (EObject containedElement : containedElements) {
      if (_referencedFeature != null) {
        if (containedElement.eClass().getEAllReferences().contains(_referencedFeature)){
          EObject target = (EObject) containedElement.eGet(_referencedFeature);
          if (target != null){
            referencedElements.add(target);
          }
        }
      } else {
        referencedElements.add(containedElement);
      }
    }
    return referencedElements;
  }

  @SuppressWarnings("unchecked")
  protected List<EObject> getContainedElementsfor(List<EObject> referencedElements) {
    List<EObject> result = new ArrayList<>(0);
    Iterator<EObject> containedElements = ((List<EObject>) semanticElement.eGet(semanticFeature)).iterator();
    // Iterate over contained elements to delete the ones that refer selected referenced elements.
    while (containedElements.hasNext()) {
      EObject containedElement = containedElements.next();
      // Get the referenced element of current element.
      EObject referencedObject = (_referencedFeature != null) ? (EObject) containedElement.eGet(_referencedFeature) : containedElement;
      if (referencedElements.contains(referencedObject)) {
        result.add(containedElement);
      }
    }
    return result;
  }

  /**
   * Refresh the current field.
   */
  protected void refreshViewer() {
    // Refresh the viewer.
    loadData(semanticElement);
  }

  /**
   * {@inheritDoc}
   */
  public void selectionChanged(SelectionChangedEvent event) {
    ISelectionProvider provider = event.getSelectionProvider();
    if (provider instanceof IDelegatedViewer) {
      ISelection selection = event.getSelection();
      if (selection instanceof IStructuredSelection) {
        Object obj = ((IStructuredSelection) selection).getFirstElement();
        if (isSelectionValid(obj)) {
          _deleteBtn.setEnabled(true);
        } else {
          _deleteBtn.setEnabled(false);
        }
      }
    }
  }

  /**
   * @param selection
   * @return
   */
  protected boolean isSelectionValid(Object selection) {
    return true;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    if (null != _delegatedViewer) {
      _delegatedViewer.setEnabled(enabled);
    }
    if (null != _deleteBtn && !_deleteBtn.isDisposed()) {
      _deleteBtn.setEnabled(enabled);
    }
  }
}
