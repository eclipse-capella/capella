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
package org.polarsys.capella.common.re.ui.renderers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.FocusCellHighlighter;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.jface.viewers.TreeViewerFocusCellManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.polarsys.capella.common.flexibility.properties.PropertyChangeListener;
import org.polarsys.capella.common.flexibility.properties.PropertyChangedEvent;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.renderer.EditListRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.location.LocationHandlerHelper;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.ListData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ReplicaContentRenderer extends EditListRenderer implements PropertyChangeListener {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createTreeViewer(Composite parent_p, IRendererContext context_p) {
    super.createTreeViewer(parent_p, context_p);

    // We allow renaming of elements in this dialog
    final TreeViewer viewer = getViewer().getClientViewer();

    viewer.setColumnProperties(new String[] { "name" });
    viewer.setCellModifier(new NameCellModifier(viewer, context_p));
    viewer.setCellEditors(new CellEditor[] { new TextCellEditor(viewer.getTree()) });

    // Weird code used for TreeViewer
    TreeViewerFocusCellManager manager = new TreeViewerFocusCellManager(viewer, new FocusCellHighlighter(viewer) {});
    TreeViewerEditor.create(viewer, manager, new ColumnViewerEditorActivationStrategy(viewer) {
      @Override
      protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
        return ((event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED) && (event.keyCode == SWT.F2));
      }
    }, ColumnViewerEditor.KEYBOARD_ACTIVATION);

  }

  @Override
  protected int getExpandLevel() {
    return AbstractTreeViewer.ALL_LEVELS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IContentProvider createContentProvider(IRendererContext context_p) {
    return new DataContentProvider() {

    };

  }

  @Override
  protected Object createMainLayoutData() {

    GridData layoutData = new GridData(GridData.FILL_VERTICAL);
    layoutData.minimumWidth = 250;
    layoutData.minimumHeight = 400;
    return layoutData;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doubleClicked(ISelection doubleClickedElement_p, IRendererContext rendererContext_p) {
    super.doubleClicked(doubleClickedElement_p, rendererContext_p);
    IProperty property = rendererContext_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__CLICK_SELECTION);
    rendererContext_p.getPropertyContext().setCurrentValue(property, ((IStructuredSelection) doubleClickedElement_p).toList());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void performRender(Composite parent_p, IRendererContext rendererContext_p) {
    super.performRender(parent_p, rendererContext_p);

    int ops = DND.DROP_COPY | DND.DROP_MOVE;

    Transfer[] transfers = new Transfer[] { LocalSelectionTransfer.getTransfer() };

    getViewer().getClientViewer().addDragSupport(ops, transfers, new DragSourceListener() {

      @Override
      public void dragStart(DragSourceEvent event_p) {
        LocalSelectionTransfer.getTransfer().setSelection(getViewer().getClientViewer().getSelection());
        event_p.doit = true;
      }

      @Override
      public void dragSetData(DragSourceEvent event_p) {
        // Nothing here
      }

      @Override
      public void dragFinished(DragSourceEvent event_p) {
        // Nothing here
      }
    });

  }

  /**
   * {@inheritDoc}
   */

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getToolbarLocation() {
    return "toolbar:org.polarsys.capella.common.re.createReplica.content";
  }

  @Override
  protected String getPopupLocation() {
    return "popup:org.polarsys.capella.common.re.createReplica.content";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Object createInput(IProperty property_p, final IRendererContext context_p) {
    Object value = context_p.getPropertyContext().getCurrentValue(property_p);
    if ((value != null) && (value instanceof Collection)) {
      Collection<EObject> scopeElements = new HashSet<EObject>((Collection) value);

      if (!scopeElements.isEmpty()) {
        IProperty property = context_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
        Object replica = context_p.getPropertyContext().getCurrentValue(property);
        scopeElements.add((EObject) replica);
      }

      TreeData data = new TreeData(scopeElements, null) {

        /**
         * {@inheritDoc}
         */
        @Override
        protected Object doGetParent(Object element_p) {
          if (element_p instanceof CatalogElement) {
            // display all catalog elements as a root item
            return null;
          }

          if (element_p instanceof CatalogElementLink) {
            CatalogElementLink link = (CatalogElementLink) element_p;
            IContext context = (IContext) context_p.getPropertyContext().getSource();
            EObject location = LocationHandlerHelper.getInstance(context).getCurrentLocation(link, context);
            if ((location != null) && (location instanceof CatalogElementLink)) {
              return location;
            }
            location = LocationHandlerHelper.getInstance(context).getLocation(link, link.getOrigin(), context);
            if ((location != null) && (location instanceof CatalogElementLink)) {
              return location;
            }

            IProperty property = context_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
            Object replica = context_p.getPropertyContext().getCurrentValue(property);
            if (!(location instanceof CatalogElementLink) && !(location instanceof CatalogElementPkg)) {
              for (CatalogElementLink link2 : ReplicableElementHandlerHelper.getInstance(context).getAllElementsLinks((CatalogElement) replica)) {
                if (link2.getTarget().equals(location)) {
                  return link2;
                }
              }
            }

            return replica;
          }
          return super.doGetParent(element_p);
        }

      };
      return data;
    }
    return new ListData(Collections.emptyList(), context_p.getPropertyContext());
  }

  @Override
  protected IStatus getStatus(Object element_p, IRendererContext context_p) {

    if (element_p instanceof CatalogElementLink) {
      CatalogElementLink link = (CatalogElementLink) element_p;
      IContext context = (IContext) context_p.getPropertyContext().getSource();

      IProperty property = context_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
      Object replica = context_p.getPropertyContext().getCurrentValue(property);

      if (!replica.equals(link.getSource())) {
        return new Status(IStatus.OK, "  ", "custom location");
      }

      EObject location = LocationHandlerHelper.getInstance(context).getCurrentLocation((CatalogElementLink) element_p, context);
      if (location != null) {
        return new Status(IStatus.INFO, "  ", "custom location");
      }
      location = LocationHandlerHelper.getInstance(context).getLocation(link, link.getOrigin(), context);
      if (location == null) {
        EObject defaultLocation = LocationHandlerHelper.getInstance(context).getDefaultLocation(link, link.getOrigin(), context);

        Object value =
            context_p.getPropertyContext().getCurrentValue(
                context_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__USE_DEFAULT_LOCATION));
        if (Boolean.FALSE.equals(value) || (defaultLocation == null)) {
          return new Status(IStatus.WARNING, "  ", "no location");
        }
        return new Status(IStatus.INFO, "  ", "default location");
      }
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ILabelProvider createLabelProvider(final IRendererContext rendererContext_p) {
    return new ReplicaLabelProvider(rendererContext_p.getPropertyContext(), super.createLabelProvider(rendererContext_p));
  }

  @Override
  public boolean reloadInputRequired(Object input_p, Object input2_p) {
    TreeData data1 = (TreeData) input_p;
    TreeData data2 = (TreeData) input2_p;
    if ((data1 == null) || (data2 == null)) {
      return true;
    }
    ArrayList<Object> valid = new ArrayList<Object>(data1.getValidElements());
    ArrayList<Object> valid2 = new ArrayList<Object>(data2.getValidElements());
    valid.removeAll(valid2);
    return !valid.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property_p, IRendererContext context_p, Object newValue_p) {
    if (!isDisposed()) {
      super.updatedValue(property_p, context_p, newValue_p);
      getViewer().getClientViewer().refresh();
    }
  }

  @Override
  public void initialize(IProperty property_p, IRendererContext rendererContext_p) {
    super.initialize(property_p, rendererContext_p);
    rendererContext_p.getPropertyContext().registerListener(this,
        rendererContext_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__CLICK_SELECTION));
    rendererContext_p.getPropertyContext().registerListener(this,
        rendererContext_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__TARGET_NAME));
    rendererContext_p.getPropertyContext().registerListener(this,
        rendererContext_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void selectionChange(IStructuredSelection selection_p, IRendererContext context_p) {
    super.selectionChange(selection_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(PropertyChangedEvent event_p) {

    if (isDisposed()) {
      return;
    }
    if (IReConstants.PROPERTY__CLICK_SELECTION.equals(event_p.getProperty().getId())) {
      List initialSelection = ((IStructuredSelection) getViewer().getClientViewer().getSelection()).toList();
      List selection = new ArrayList(initialSelection);
      List list =
          (List) event_p.getPropertyContext().getCurrentValue(event_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__CLICK_SELECTION));
      selection.removeAll(list);
      if (initialSelection.isEmpty() || !selection.isEmpty()) {
        getViewer().getClientViewer().setSelection(new StructuredSelection(list), true);
      }

    } else if (IReConstants.PROPERTY__TARGET_NAME.equals(event_p.getProperty().getId())) {
      getViewer().getClientViewer().refresh();

    } else if (IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX.equals(event_p.getProperty().getId())) {
      getViewer().getClientViewer().refresh();
    }

  }
}
