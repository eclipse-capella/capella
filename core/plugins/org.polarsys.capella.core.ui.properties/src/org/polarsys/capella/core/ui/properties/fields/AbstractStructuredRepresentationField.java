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

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.IImageKeys;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
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
   * @param parent_p
   * @param widgetFactory_p
   * @param referencedFeature_p a feature that refers to another element from elements contained by the semantic feature.
   * @param label_p the label displayed at the right top of the table.
   * @param viewerType_p
   */
  public AbstractStructuredRepresentationField(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p,
      EReference referencedFeature_p, String label_p, IDelegatedViewer delegatedViewer_p)
  {
    super(widgetFactory_p);

    _referencedFeature = referencedFeature_p;
    _label = label_p;
    _delegatedViewer = delegatedViewer_p;
    _delegatedViewer.addSelectionChangedListener(this);

    createActions(parent_p);
    _delegatedViewer.createContainer(parent_p);
  }

  /**
   * Create the actions.
   */
  private void createActions(Composite parent_p) {
    CLabel label = _widgetFactory.createCLabel(_delegatedViewer.getViewerGroup(parent_p), _label);
    label.setLayoutData(new GridData(SWT.FILL, GridData.VERTICAL_ALIGN_FILL, true, false));

    createCustomActions(parent_p);

    _deleteBtn = createTableButton(parent_p, CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.IMG_DELETE_BUTTON), new Runnable() {
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
  protected abstract void createCustomActions(Composite parent_p);

  /**
   * Create a button located at the right top corner of the table.
   * @param parent_p
   * @param image_p
   * @param runnable_p
   * @return a not <code>null</code> instance.
   */
  protected Button createTableButton(Composite parent_p, Image image_p, final Runnable runnable_p) {
    Button tableButton = _widgetFactory.createButton(_delegatedViewer.getViewerGroup(parent_p), null, SWT.PUSH);
    tableButton.setImage(image_p);
    tableButton.setLayoutData(new GridData(GridData.END, GridData.VERTICAL_ALIGN_FILL, false, false));
    tableButton.addSelectionListener(new SelectionAdapter() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        runnable_p.run();
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
  public void loadData(CapellaElement semanticElement_p) {
    loadData(semanticElement_p, _semanticFeature);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p) {
    super.loadData(semanticElement_p, semanticFeature_p);

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
    List<EObject> containedElements = (List<EObject>) _semanticElement.eGet(_semanticFeature);
    ArrayList<EObject> referencedElements = new ArrayList<EObject>(0);
    // Collect referenced elements according specified referenced feature.
    for (EObject containedElement : containedElements) {
      if (_referencedFeature != null) {
        EObject target = (EObject) containedElement.eGet(_referencedFeature);
        if (target != null)
          referencedElements.add(target);
      } else {
        referencedElements.add(containedElement);
      }
    }
    return referencedElements;
  }

  @SuppressWarnings("unchecked")
  protected List<EObject> getContainedElementsfor(List<EObject> referencedElements_p) {
    List<EObject> result = new ArrayList<EObject>(0);
    Iterator<EObject> containedElements = ((List<EObject>) _semanticElement.eGet(_semanticFeature)).iterator();
    // Iterate over contained elements to delete the ones that refer selected referenced elements.
    while (containedElements.hasNext()) {
      EObject containedElement = containedElements.next();
      // Get the referenced element of current element.
      EObject referencedObject = (_referencedFeature != null) ? (EObject) containedElement.eGet(_referencedFeature) : containedElement;
      if (referencedElements_p.contains(referencedObject)) {
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
    loadData(_semanticElement);
  }

  /**
   * {@inheritDoc}
   */
  public void selectionChanged(SelectionChangedEvent event_p) {
    ISelectionProvider provider = event_p.getSelectionProvider();
    if (provider instanceof IDelegatedViewer) {
      ISelection selection = event_p.getSelection();
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
   * @param selection_p
   * @return
   */
  protected boolean isSelectionValid(Object selection_p) {
    return true;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    if (null != _delegatedViewer) {
      _delegatedViewer.setEnabled(enabled_p);
    }
    if (null != _deleteBtn && !_deleteBtn.isDisposed()) {
      _deleteBtn.setEnabled(enabled_p);
    }
  }
}
