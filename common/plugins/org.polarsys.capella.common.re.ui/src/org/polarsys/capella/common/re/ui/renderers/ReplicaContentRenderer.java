/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.ui.renderers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
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
  protected void createTreeViewer(Composite parent, IRendererContext context) {
    super.createTreeViewer(parent, context);

    // We allow renaming of elements in this dialog
    final TreeViewer viewer = getViewer().getClientViewer();

    viewer.setColumnProperties(new String[] { "name" });
    viewer.setCellModifier(new NameCellModifier(viewer, context));
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
  protected IContentProvider createContentProvider(IRendererContext context) {
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
  protected void doubleClicked(ISelection doubleClickedElement, IRendererContext rendererContext) {
    super.doubleClicked(doubleClickedElement, rendererContext);
    IProperty property = rendererContext.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__CLICK_SELECTION);
    rendererContext.getPropertyContext().setCurrentValue(property, ((IStructuredSelection) doubleClickedElement).toList());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void performRender(Composite parent, IRendererContext rendererContext) {
    super.performRender(parent, rendererContext);

    int ops = DND.DROP_COPY | DND.DROP_MOVE;

    Transfer[] transfers = new Transfer[] { LocalSelectionTransfer.getTransfer() };

    getViewer().getClientViewer().addDragSupport(ops, transfers, new DragSourceListener() {

      @Override
      public void dragStart(DragSourceEvent event) {
        LocalSelectionTransfer.getTransfer().setSelection(getViewer().getClientViewer().getSelection());
        event.doit = true;
      }

      @Override
      public void dragSetData(DragSourceEvent event) {
        // Nothing here
      }

      @Override
      public void dragFinished(DragSourceEvent event) {
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
  protected Object createInput(IProperty property, final IRendererContext context) {
    Object value = context.getPropertyContext().getCurrentValue(property);
    if (value instanceof Collection<?>) {
      Collection<Object> scopeElements = new LinkedHashSet<Object>((Collection<?>) value);

      if (!scopeElements.isEmpty()) {
        IProperty prop = context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
        Object replica = context.getPropertyContext().getCurrentValue(prop);
        scopeElements.add(replica);
      }

      TreeData data = new TreeData(scopeElements, null) {

        @Override
        protected Collection<Object> createChildrenCollection() {
          return new LinkedHashSet<Object>();
        }

        @Override
        protected Collection<Object> initializeRootElementCollection() {
          return new LinkedHashSet<Object>();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Object doGetParent(Object element) {
          if (element instanceof CatalogElement) {
            // display all catalog elements as a root item
            return null;
          }

          if (element instanceof CatalogElementLink) {
            CatalogElementLink link = (CatalogElementLink) element;
            IContext ctx = (IContext) context.getPropertyContext().getSource();
            EObject location = LocationHandlerHelper.getInstance(ctx).getCurrentLocation(link, ctx);
            if ((location != null) && (location instanceof CatalogElementLink)) {
              return location;
            }
            location = LocationHandlerHelper.getInstance(ctx).getLocation(link, link.getOrigin(), ctx);
            if ((location != null) && (location instanceof CatalogElementLink)) {
              return location;
            }

            IProperty property = context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
            Object replica = context.getPropertyContext().getCurrentValue(property);
            if (!(location instanceof CatalogElementLink) && !(location instanceof CatalogElementPkg)) {
              for (CatalogElementLink link2 : ReplicableElementHandlerHelper.getInstance(ctx).getAllElementsLinks((CatalogElement) replica)) {
                if (link2.getTarget().equals(location)) {
                  return link2;
                }
              }
            }

            return replica;
          }
          return super.doGetParent(element);
        }

      };
      return data;
    }
    return new ListData(Collections.emptyList(), context.getPropertyContext());
  }

  @Override
  protected IStatus getStatus(Object element, IRendererContext context) {

    if (element instanceof CatalogElementLink) {
      CatalogElementLink link = (CatalogElementLink) element;
      IContext ctx = (IContext) context.getPropertyContext().getSource();

      IProperty property = context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
      Object replica = context.getPropertyContext().getCurrentValue(property);

      if (!replica.equals(link.getSource())) {
        return new Status(IStatus.OK, "  ", "custom location");
      }

      EObject location = LocationHandlerHelper.getInstance(ctx).getCurrentLocation((CatalogElementLink) element, ctx);
      if (location != null) {
        return new Status(IStatus.INFO, "  ", "custom location");
      }
      location = LocationHandlerHelper.getInstance(ctx).getLocation(link, link.getOrigin(), ctx);
      if (location == null) {

        Object parentLocator =
            context.getPropertyContext().getCurrentValue(
                context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__PARENT_LOCATOR));

        EObject defaultLocation = null;
        if (!IReConstants.LOCATOR_OPTION_MANUAL.equals(parentLocator)) {
          defaultLocation = LocationHandlerHelper.getInstance(ctx).getDefaultLocation(link, link.getOrigin(), ctx);
        }

        if (defaultLocation == null) {
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
  protected ILabelProvider createLabelProvider(final IRendererContext rendererContext) {
    return new ReplicaLabelProvider(rendererContext.getPropertyContext(), super.createLabelProvider(rendererContext));
  }

  @Override
  public boolean reloadInputRequired(Object input, Object input2) {
    TreeData data1 = (TreeData) input;
    TreeData data2 = (TreeData) input2;
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
  public void updatedValue(IProperty property, IRendererContext context, Object newValue) {
    if (!isDisposed()) {
      super.updatedValue(property, context, newValue);
      getViewer().getClientViewer().refresh();
    }
  }

  @Override
  public void initialize(IProperty property, IRendererContext rendererContext) {
    super.initialize(property, rendererContext);
    rendererContext.getPropertyContext().registerListener(this,
        rendererContext.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__CLICK_SELECTION));
    rendererContext.getPropertyContext().registerListener(this,
        rendererContext.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__TARGET_NAME));
    rendererContext.getPropertyContext().registerListener(this,
        rendererContext.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void selectionChange(IStructuredSelection selection, IRendererContext context) {
    super.selectionChange(selection, context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(PropertyChangedEvent event) {

    if (isDisposed()) {
      return;
    }
    if (IReConstants.PROPERTY__CLICK_SELECTION.equals(event.getProperty().getId())) {
      List initialSelection = ((IStructuredSelection) getViewer().getClientViewer().getSelection()).toList();
      List selection = new ArrayList(initialSelection);
      List list =
          (List) event.getPropertyContext().getCurrentValue(event.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__CLICK_SELECTION));
      selection.removeAll(list);
      if (initialSelection.isEmpty() || !selection.isEmpty()) {
        getViewer().getClientViewer().setSelection(new StructuredSelection(list), true);
      }

    } else if (IReConstants.PROPERTY__TARGET_NAME.equals(event.getProperty().getId())) {
      getViewer().getClientViewer().refresh();

    } else if (IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX.equals(event.getProperty().getId())) {
      getViewer().getClientViewer().refresh();
    }

  }
}
