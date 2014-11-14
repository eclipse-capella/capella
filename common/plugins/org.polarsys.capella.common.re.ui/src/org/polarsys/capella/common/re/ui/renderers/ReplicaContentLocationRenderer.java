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
package org.polarsys.capella.common.re.ui.renderers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
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
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.jface.viewers.TreeViewerFocusCellManager;
import org.eclipse.jface.viewers.Viewer;
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
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
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
  protected void createTreeViewer(Composite parent_p, IRendererContext context_p) {
    super.createTreeViewer(parent_p, context_p);

    //We allow renaming of elements in this dialog
    final TreeViewer viewer = getViewer().getClientViewer();

    viewer.setColumnProperties(new String[] { "name" });
    viewer.setCellModifier(new NameCellModifier(viewer, context_p));
    viewer.setCellEditors(new CellEditor[] { new TextCellEditor(viewer.getTree()) });

    //Weird code used for TreeViewer
    TreeViewerFocusCellManager manager = new TreeViewerFocusCellManager(viewer, new FocusCellHighlighter(viewer) {});
    TreeViewerEditor.create(viewer, manager, new ColumnViewerEditorActivationStrategy(viewer) {
      @Override
      protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
        return ((event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED) && (event.keyCode == SWT.F2));
      }
    }, ColumnViewerEditor.KEYBOARD_ACTIVATION);

  }

  @Override
  protected void doubleClicked(ISelection doubleClickedElement_p, IRendererContext rendererContext_p) {
    super.doubleClicked(doubleClickedElement_p, rendererContext_p);
    rendererContext_p.getPropertyContext().setCurrentValue(
        rendererContext_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__CLICK_SELECTION),
        ((IStructuredSelection) doubleClickedElement_p).toList());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IContentProvider createContentProvider(final IRendererContext context_p) {
    return new ITreeContentProvider() {

      /**
       * {@inheritDoc}
       */
      @Override
      public Object[] getElements(Object object_p) {
        return getChildren(object_p);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public Object[] getChildren(Object object_p) {
        IContext context = (IContext) context_p.getPropertyContext().getSource();

        ArrayList<Object> childs = new ArrayList<Object>();

        if (object_p instanceof CatalogElement) {
          //For a CatalogElement, we remove CatalogElementLinks
          IProperty property = context_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
          Object replica = context_p.getPropertyContext().getCurrentValue(property);

          Collection<CatalogElement> usedElements = ReplicableElementHandlerHelper.getInstance(context).getUsedReplicableElements((CatalogElement) replica);
          for (CatalogElement element : ((CatalogElement) object_p).getOwnedElements()) {
            if (!usedElements.contains(element)) {
              childs.add(element);
            }
          }

        } else if (object_p instanceof EObject) {
          childs.addAll(((EObject) object_p).eContents());

        } else if (object_p instanceof Resource) {
          childs.addAll(((Resource) object_p).getContents());
        }

        //We add also catalog element links which will be under the element (automatically or by a drag/drop)
        Object value = context_p.getPropertyContext().getCurrentValue(context_p.getProperty(ReplicaContentLocationRenderer.this));
        if ((value != null) && (value instanceof Collection)) {
          Collection<EObject> scopeElements = (Collection) value;
          for (EObject element : scopeElements) {
            if (!ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.CREATED_LINKS, element, context)) {
              continue;
            }
            if (object_p.equals(getLocation(element))) {
              if (!childs.contains(element)) {
                childs.add(element);
              }
            }
          }
        }

        //We also add the 'to be created' replica if not yet added
        IProperty property = context_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__LOCATION_TARGET);
        Object replica = context_p.getPropertyContext().getCurrentValue(property);
        if (object_p.equals(replica)) {
          property = context_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
          replica = context_p.getPropertyContext().getCurrentValue(property);
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
      public boolean hasChildren(Object object_p) {
        return getChildren(object_p).length > 0;
      }

      protected Object getLocation(EObject element_p) {
        IContext context = (IContext) context_p.getPropertyContext().getSource();
        if (element_p instanceof CatalogElementLink) {
          CatalogElementLink link = (CatalogElementLink) element_p;
          EObject location = LocationHandlerHelper.getInstance(context).getCurrentLocation(link, context);
          if (location != null) {
            return location;
          }
          location = LocationHandlerHelper.getInstance(context).getLocation(link, link.getOrigin(), context);
          if (location == null) {
            EObject defaultLocation = LocationHandlerHelper.getInstance(context).getDefaultLocation(link, link.getOrigin(), context);
            if (defaultLocation != null) {
              Object value =
                  context_p.getPropertyContext().getCurrentValue(
                      context_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__USE_DEFAULT_LOCATION));
              if (Boolean.FALSE.equals(value)) {
                return null;
              }

              if (defaultLocation instanceof CatalogElementLink) {
                if (!ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.VIRTUAL_LINKS, defaultLocation, context)) {
                  return ((CatalogElementLink) defaultLocation).getTarget();
                }
              }
              return defaultLocation;
            }
            return null;
          }

          //For all links referencing a catalogElement, we put it in the link instead of the element
          if (location instanceof CatalogElement) {
            IProperty property = context_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
            Object replica = context_p.getPropertyContext().getCurrentValue(property);

            for (CatalogElementLink link2 : ReplicableElementHandlerHelper.getInstance(context).getAllElementsLinks((CatalogElement) replica)) {
              if (link2.getTarget().equals(location)) {
                location = link2;
                break;
              }
            }
          }

          if (location instanceof CatalogElementLink) {
            if (!ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.CREATED_LINKS, location, context)) {
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
      public Object getParent(Object object_p) {

        if (object_p instanceof CatalogElement) {
          //display all catalog elements as a root item
          IProperty property = context_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
          Object replica = context_p.getPropertyContext().getCurrentValue(property);
          if (object_p.equals(replica)) {
            property = context_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__LOCATION_TARGET);
            replica = context_p.getPropertyContext().getCurrentValue(property);
            return replica;
          }

          return null;
        }

        if (object_p instanceof CatalogElementLink) {
          Object parent = getLocation((EObject) object_p);

          return parent;
        }
        if (object_p instanceof EObject) {
          return ((EObject) object_p).eContainer();
        }

        return null;
      }

      @Override
      public void dispose() {

      }

      @Override
      public void inputChanged(Viewer viewer_p, Object oldInput_p, Object newInput_p) {

      }

    };
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
  public void performRender(Composite parent_p, final IRendererContext rendererContext_p) {
    super.performRender(parent_p, rendererContext_p);

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
      public void drop(DropTargetEvent event_p) {
        Object target = determineTarget(event_p);
        contextOnDrop = rendererContext_p;
        ReplicaContentLocationRenderer.this.handleDrop(target, event_p.operations, event_p).isOK();
      }

      @Override
      public boolean validateDrop(Object target_p, int operation_p, TransferData transferType_p) {
        return ReplicaContentLocationRenderer.this.validateDrop(target_p, operation_p, transferType_p).isOK();
      }

      @Override
      public boolean performDrop(Object data_p) {
        return true;
      }

    });

  }

  public IStatus validateDrop(Object target_p, int operation_p, TransferData transferType_p) {
    ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();

    if (selection.isEmpty()) {
      return Status.CANCEL_STATUS;
    }
    if (!(selection instanceof IStructuredSelection)) {
      return Status.CANCEL_STATUS;
    }

    for (Object item : ((IStructuredSelection) selection).toList()) {
      //We should also allow to move the Target Replica, but user can move it with the LocationTargetProperty.
      if (!((item instanceof CatalogElementLink))) {
        return Status.CANCEL_STATUS;
      }
    }

    return Status.OK_STATUS;

  }

  /**
   * {@inheritDoc}
   */
  public IStatus handleDrop(Object target_p, int operation_p, DropTargetEvent dropTargetEvent_p) {
    ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();

    IContext context = (IContext) contextOnDrop.getPropertyContext().getSource();

    for (Object item : ((IStructuredSelection) selection).toList()) {
      if (((item instanceof CatalogElementLink) && ((target_p instanceof CatalogElementLink)))) {
        LocationHandlerHelper.getInstance(context).setCurrentLocation((CatalogElementLink) item, (EObject) target_p, context);
      } else if ((item instanceof CatalogElementLink) && (target_p instanceof EObject)) {
        LocationHandlerHelper.getInstance(context).setCurrentLocation((CatalogElementLink) item, (EObject) target_p, context);
      }

    }
    IPropertyContext pContext = contextOnDrop.getPropertyContext();
    IProperty targetContent = pContext.getProperties().getProperty("targetContent");

    //Since we change location of selection from the backside, we need to change the property. 
    //(little workaround since we can't change the property's value for the selection)
    pContext.setCurrentValue(targetContent, pContext.getCurrentValue(targetContent));

    getViewer().getClientViewer().refresh();
    getViewer().getClientViewer().setSelection(selection, true);
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Object createInput(IProperty property_p, IRendererContext context_p) {
    Object value = context_p.getPropertyContext().getCurrentValue(property_p);
    if ((value != null) && (value instanceof Collection)) {
      Collection<EObject> scopeElements = (Collection) value;
      if (scopeElements.isEmpty()) {
        return Collections.emptyList();
      }

      Collection<Object> selection =
          (Collection<Object>) ((IContext) (context_p.getPropertyContext().getSource())).get(ITransitionConstants.TRANSITION_SOURCES);
      if ((selection != null) && (selection.size() > 0)) {
        for (Object item : selection) {
          if (item instanceof EObject) {
            return ((EObject) item).eResource();
          }
        }
      }

      return ((CatalogElementLink) scopeElements.iterator().next()).getTarget().eResource();

    }
    return new ListData(Collections.emptyList(), context_p.getPropertyContext());
  }

  @Override
  protected boolean isValidElement(Object element_p, IRendererContext rendererContext_p) {
    IProperty property = rendererContext_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
    Object replica = rendererContext_p.getPropertyContext().getCurrentValue(property);
    IContext context = (IContext) rendererContext_p.getPropertyContext().getSource();

    //For an EObject, if it is used in the current target replica, it is valid!
    for (CatalogElementLink link : ReplicableElementExt.getReferencingLinks((EObject) element_p)) {
      if (ReplicableElementHandlerHelper.getInstance(context).getElementsLinks((CatalogElement) replica).contains(link)) {
        if (!ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.CREATED_LINKS, link, context)) {
          return true;
        }
      }
    }

    Collection a = (Collection) rendererContext_p.getPropertyContext().getCurrentValue(rendererContext_p.getProperty(this));
    return a.contains(element_p) || replica.equals(element_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ILabelProvider createLabelProvider(final IRendererContext rendererContext_p) {
    return new ReplicaLabelProvider(rendererContext_p.getPropertyContext(), super.createLabelProvider(rendererContext_p));
  }

  @Override
  protected IStatus getStatus(Object element_p, IRendererContext rendererContext_p) {
    IProperty property = rendererContext_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
    Object replica = rendererContext_p.getPropertyContext().getCurrentValue(property);

    if (element_p instanceof CatalogElementLink) {
      CatalogElementLink link = (CatalogElementLink) element_p;
      IContext context = (IContext) rendererContext_p.getPropertyContext().getSource();

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
  public void initialize(IProperty property_p, IRendererContext rendererContext_p) {
    super.initialize(property_p, rendererContext_p);
    rendererContext_p.getPropertyContext().registerListener(this,
        rendererContext_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__CLICK_SELECTION));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void reloadInput(IProperty property_p, IRendererContext propertyContext_p) {
    super.reloadInput(property_p, propertyContext_p);
  }

  @Override
  public boolean reloadInputRequired(Object input_p, Object input2_p) {
    return (input2_p == null) || !(input_p.equals(input2_p));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property_p, IRendererContext context_p, Object newValue_p) {
    if (isDisposed()) {
      return;
    }
    super.updatedValue(property_p, context_p, newValue_p);
    getViewer().getClientViewer().refresh();
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
      List list = new ArrayList();
      IContext context = (IContext) event_p.getPropertyContext().getSource();
      IProperty property = event_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
      Object replica = event_p.getPropertyContext().getCurrentValue(property);

      for (Object item : (List) event_p.getPropertyContext().getCurrentValue(
          event_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__CLICK_SELECTION))) {
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

    } else if (IReConstants.PROPERTY__TARGET_NAME.equals(event_p.getProperty().getId())) {
      getViewer().getClientViewer().refresh();

    } else if (IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX.equals(event_p.getProperty().getId())) {
      getViewer().getClientViewer().refresh();
    }

  }
}
