/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.util.LocalSelectionTransfer;
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
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Composite;
import org.polarsys.capella.common.flexibility.properties.PropertyChangeListener;
import org.polarsys.capella.common.flexibility.properties.PropertyChangedEvent;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.renderer.EditListRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.location.LocationHandlerHelper;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.common.ui.toolkit.viewers.data.ListData;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ReplicaContentLocationRenderer extends EditListRenderer implements PropertyChangeListener {

  @Override
  protected int getExpandLevel() {
    return 2;
  }

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
  protected void doubleClicked(ISelection doubleClickedElement, IRendererContext rendererContext) {
    super.doubleClicked(doubleClickedElement, rendererContext);
    rendererContext.getPropertyContext().setCurrentValue(
        rendererContext.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__CLICK_SELECTION),
        ((IStructuredSelection) doubleClickedElement).toList());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IContentProvider createContentProvider(final IRendererContext context) {
    return new AdapterFactoryContentProvider(getAdapterFactory(context)) {
      /**
       * {@inheritDoc}
       */
      @Override
      public Object[] getElements(Object object) {
        return getChildren(object);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public Object[] getChildren(Object object) {
        IContext ctx = (IContext) context.getPropertyContext().getSource();

        ArrayList<Object> childs = new ArrayList<Object>();

        if (object instanceof CatalogElement) {
          // For a CatalogElement, we remove CatalogElementLinks
          IProperty property = context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
          Object replica = context.getPropertyContext().getCurrentValue(property);

          Collection<CatalogElement> usedElements = ReplicableElementHandlerHelper.getInstance(ctx).getUsedReplicableElements((CatalogElement) replica);
          for (CatalogElement element : ((CatalogElement) object).getOwnedElements()) {
            if (!usedElements.contains(element)) {
              childs.add(element);
            }
          }

        } else {
          childs.addAll(Arrays.asList(super.getChildren(object)));
        }

        // We add also catalog element links which will be under the element (automatically or by a drag/drop)
        Object value = context.getPropertyContext().getCurrentValue(context.getProperty(ReplicaContentLocationRenderer.this));
        if ((value != null) && (value instanceof Collection)) {
          Collection<EObject> scopeElements = (Collection) value;
          for (EObject element : scopeElements) {
            if (!ContextScopeHandlerHelper.getInstance(ctx).contains(IReConstants.CREATED_LINKS, element, ctx)) {
              continue;
            }
            if (object.equals(getLocation(element))) {
              if (!childs.contains(element)) {
                childs.add(element);
              }
            }
          }
        }

        // We also add the 'to be created' replica if not yet added
        IProperty property = context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__LOCATION_TARGET);
        Object replica = context.getPropertyContext().getCurrentValue(property);
        if (object.equals(replica)) {
          property = context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
          replica = context.getPropertyContext().getCurrentValue(property);
          if (!childs.contains(replica)) {
            childs.add(replica);
          }
        }

        return childs.toArray();
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public boolean hasChildren(Object object) {
        return getChildren(object).length > 0;
      }

      protected Object getLocation(EObject element) {
        IContext ctx = (IContext) context.getPropertyContext().getSource();
        if (element instanceof CatalogElementLink) {
          CatalogElementLink link = (CatalogElementLink) element;
          EObject location = LocationHandlerHelper.getInstance(ctx).getCurrentLocation(link, ctx);
          if (location != null) {
            return location;
          }
          location = LocationHandlerHelper.getInstance(ctx).getLocation(link, link.getOrigin(), ctx);
          if (location == null) {
            Object parentLocator =
                context.getPropertyContext().getCurrentValue(
                    context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__PARENT_LOCATOR));

            EObject defaultLocation = null;
            if (!IReConstants.LOCATOR_OPTION_MANUAL.equals(parentLocator)) {
              defaultLocation = LocationHandlerHelper.getInstance(ctx).getDefaultLocation(link, link.getOrigin(), ctx);
              if (defaultLocation instanceof CatalogElementLink) {
                if (!ContextScopeHandlerHelper.getInstance(ctx).contains(IReConstants.VIRTUAL_LINKS, defaultLocation, ctx)) {
                  return ((CatalogElementLink) defaultLocation).getTarget();
                }
              }
            }
            return defaultLocation;
          }

          // For all links referencing a catalogElement, we put it in the link instead of the element
          if (location instanceof CatalogElement) {
            IProperty property = context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
            Object replica = context.getPropertyContext().getCurrentValue(property);

            for (CatalogElementLink link2 : ReplicableElementHandlerHelper.getInstance(ctx).getAllElementsLinks((CatalogElement) replica)) {
              if (link2.getTarget().equals(location)) {
                location = link2;
                break;
              }
            }
          }

          if (location instanceof CatalogElementLink) {
            if (!ContextScopeHandlerHelper.getInstance(ctx).contains(IReConstants.CREATED_LINKS, location, ctx)) {
              return ((CatalogElementLink) location).getTarget();
            }
          }
          return location;
        }
        return null;
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public Object getParent(Object object) {

        if (object instanceof CatalogElement) {
          // display all catalog elements as a root item
          IProperty property = context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
          Object replica = context.getPropertyContext().getCurrentValue(property);
          if (object.equals(replica)) {
            property = context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__LOCATION_TARGET);
            replica = context.getPropertyContext().getCurrentValue(property);
            return replica;
          }

          return null;
        }

        if (object instanceof CatalogElementLink) {
          Object parent = getLocation((EObject) object);

          return parent;
        }

        return super.getParent(object);
      }

    };
  }

  /**
   * @return
   */
  protected AdapterFactory getAdapterFactory(IRendererContext context) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getToolbarLocation() {
    return "toolbar:org.polarsys.capella.common.re.createReplica.location";
  }

  @Override
  protected String getPopupLocation() {
    return "popup:org.polarsys.capella.common.re.createReplica.location";
  }

  protected IRendererContext contextOnDrop = null;

  @Override
  public void performRender(Composite parent, final IRendererContext rendererContext) {
    super.performRender(parent, rendererContext);

    int ops = DND.DROP_COPY | DND.DROP_MOVE;

    Transfer[] transfers = new Transfer[] { LocalSelectionTransfer.getTransfer() };

    getViewer().getClientViewer().addDropSupport(ops, transfers, new ViewerDropAdapter(getViewer().getClientViewer()) {

      @Override
      protected int determineLocation(DropTargetEvent event) {
        int value = super.determineLocation(event);
        if ((value == LOCATION_AFTER) || (value == LOCATION_BEFORE)) {
          value = LOCATION_NONE;
        }
        return value;
      }

      @Override
      public void drop(DropTargetEvent event) {
        Object target = determineTarget(event);
        contextOnDrop = rendererContext;
        ReplicaContentLocationRenderer.this.handleDrop(target, event.operations, event).isOK();
      }

      @Override
      public boolean validateDrop(Object target, int operation, TransferData transferType) {
        return ReplicaContentLocationRenderer.this.validateDrop(target, operation, transferType).isOK();
      }

      @Override
      public boolean performDrop(Object data) {
        return true;
      }

    });

  }

  public IStatus validateDrop(Object target, int operation, TransferData transferType) {
    ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();

    if (selection.isEmpty()) {
      return Status.CANCEL_STATUS;
    }
    if (!(selection instanceof IStructuredSelection)) {
      return Status.CANCEL_STATUS;
    }

    for (Object item : ((IStructuredSelection) selection).toList()) {
      // We should also allow to move the Target Replica, but user can move it with the LocationTargetProperty.
      if (!((item instanceof CatalogElementLink))) {
        return Status.CANCEL_STATUS;
      }
    }

    return Status.OK_STATUS;

  }

  /**
   * {@inheritDoc}
   */
  public IStatus handleDrop(Object target, int operation, DropTargetEvent dropTargetEvent) {
    ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();

    IContext context = (IContext) contextOnDrop.getPropertyContext().getSource();

    for (Object item : ((IStructuredSelection) selection).toList()) {
      if (((item instanceof CatalogElementLink) && ((target instanceof CatalogElementLink)))) {
        LocationHandlerHelper.getInstance(context).setCurrentLocation((CatalogElementLink) item, (EObject) target, context);
      } else if ((item instanceof CatalogElementLink) && (target instanceof EObject)) {
        LocationHandlerHelper.getInstance(context).setCurrentLocation((CatalogElementLink) item, (EObject) target, context);
      }

    }
    IPropertyContext pContext = contextOnDrop.getPropertyContext();
    IProperty targetContent = pContext.getProperties().getProperty("targetContent");

    // Since we change location of selection from the backside, we need to change the property.
    // (little workaround since we can't change the property's value for the selection)
    pContext.setCurrentValue(targetContent, pContext.getCurrentValue(targetContent));

    getViewer().getClientViewer().refresh();
    getViewer().getClientViewer().setSelection(selection, true);
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Object createInput(IProperty property, IRendererContext context) {
    Object value = context.getPropertyContext().getCurrentValue(property);
    if ((value != null) && (value instanceof Collection)) {
      Collection<EObject> scopeElements = (Collection) value;
      if (scopeElements.isEmpty()) {
        return Collections.emptyList();
      }

      IProperty rplProperty = context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
      EObject replica = (EObject) context.getPropertyContext().getCurrentValue(rplProperty);
      return EcoreUtil.getRootContainer(replica).eResource();
    }
    return new ListData(Collections.emptyList(), context.getPropertyContext());
  }

  @Override
  protected boolean isValidElement(Object element, IRendererContext rendererContext) {
    IProperty property = rendererContext.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
    Object replica = rendererContext.getPropertyContext().getCurrentValue(property);
    IContext context = (IContext) rendererContext.getPropertyContext().getSource();

    // For an EObject, if it is used in the current target replica, it is valid!
    for (CatalogElementLink link : ReplicableElementExt.getReferencingLinks((EObject) element)) {
      if (ReplicableElementHandlerHelper.getInstance(context).getElementsLinks((CatalogElement) replica).contains(link)) {
        if (!ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.CREATED_LINKS, link, context)) {
          return true;
        }
      }
    }

    Collection a = (Collection) rendererContext.getPropertyContext().getCurrentValue(rendererContext.getProperty(this));
    return a.contains(element) || replica.equals(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ILabelProvider createLabelProvider(final IRendererContext rendererContext) {
    return new ReplicaLabelProvider(rendererContext.getPropertyContext(), super.createLabelProvider(rendererContext));
  }

  @Override
  protected IStatus getStatus(Object element, IRendererContext rendererContext) {
    IProperty property = rendererContext.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
    Object replica = rendererContext.getPropertyContext().getCurrentValue(property);

    if (element instanceof CatalogElementLink) {
      CatalogElementLink link = (CatalogElementLink) element;
      IContext context = (IContext) rendererContext.getPropertyContext().getSource();

      EObject location = LocationHandlerHelper.getInstance(context).getCurrentLocation(link, context);
      if (location != null) {
        return new Status(IStatus.INFO, "  ", "custom location");
      }
      location = LocationHandlerHelper.getInstance(context).getLocation(link, link.getOrigin(), context);
      if (location == null) {
        EObject defaultLocation = LocationHandlerHelper.getInstance(context).getDefaultLocation(link, link.getOrigin(), context);
        if (defaultLocation == null) {
          return new Status(IStatus.WARNING, "  ", "no location");
        }
        return new Status(IStatus.INFO, "  ", "default location");
      }
    }
    return Status.OK_STATUS;
  }

  @Override
  public void initialize(IProperty property, IRendererContext rendererContext) {
    super.initialize(property, rendererContext);
    rendererContext.getPropertyContext().registerListener(this,
        rendererContext.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__CLICK_SELECTION));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void reloadInput(IProperty property, IRendererContext propertyContext) {
    super.reloadInput(property, propertyContext);
  }

  @Override
  public boolean reloadInputRequired(Object input, Object input2) {
    return (input2 == null) || !(input.equals(input2));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property, IRendererContext context, Object newValue) {
    if (isDisposed()) {
      return;
    }
    super.updatedValue(property, context, newValue);
    getViewer().getClientViewer().refresh();
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
      List list = new ArrayList();
      IContext context = (IContext) event.getPropertyContext().getSource();
      IProperty property = event.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
      Object replica = event.getPropertyContext().getCurrentValue(property);

      for (Object item : (List) event.getPropertyContext().getCurrentValue(
          event.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__CLICK_SELECTION))) {
        if (!(item instanceof CatalogElementLink)) {
          list.add(item);
        } else {
          boolean isAdded = false;
          for (CatalogElementLink link : ReplicableElementExt.getReferencingLinks(((CatalogElementLink) item).getTarget())) {
            if (ReplicableElementHandlerHelper.getInstance(context).getElementsLinks((CatalogElement) replica).contains(link)) {
              if (!ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.CREATED_LINKS, link, context)) {
                list.add(((CatalogElementLink) item).getTarget());
                isAdded = true;
                break;
              }
            }
          }
          if (!isAdded) {
            list.add(item);
          }
        }
      }

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
