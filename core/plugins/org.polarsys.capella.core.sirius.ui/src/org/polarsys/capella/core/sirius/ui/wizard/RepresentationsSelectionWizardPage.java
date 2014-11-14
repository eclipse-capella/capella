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
package org.polarsys.capella.core.sirius.ui.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;

/**
 * Wizard to select diagrams from an Aird.
 */
public class RepresentationsSelectionWizardPage extends WizardPage {

  private static final String SELECT_DIAGRAMS_TO_EXPORT = "Select Representations to export"; //$NON-NLS-1$

  /** The title of the page. */
  private static final String PAGE_TITLE = "Select representations to export"; //$NON-NLS-1$

  /** The page is completed. */
  private static final int CODE_OK = 0;

  /** The user has not selected one or more diagrams. */
  private static final int CODE_NO_SEL = 1;

  /** The user has selected an object that is not a diagram. */
  private static final int CODE_ERROR = 2;

  /** The composite control of the page. */
  private Composite pageComposite;

  /** The table viewer. */
  private CheckboxTreeViewer _treeViewer;

  /** The filter. */

  private Session _root;

  private Collection<DRepresentation> _preselection;

  /**
   * Create a new <code>DescDiagramSelectionWizardPage</code>.
   * @param root_p the root object
   * @param representations_p the preselection.
   */
  public RepresentationsSelectionWizardPage(Session root_p, Collection<DRepresentation> representations_p) {
    super(PAGE_TITLE);
    setTitle(PAGE_TITLE);
    _root = root_p;
    _preselection = representations_p;
    if (_preselection.size() > 0)
      setPageComplete(true);
    setMessage(SELECT_DIAGRAMS_TO_EXPORT, IMessageProvider.INFORMATION);
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent_p) {
    initializeDialogUnits(parent_p);

    pageComposite = new Composite(parent_p, SWT.NONE);
    pageComposite.setLayout(new GridLayout());
    pageComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

    _treeViewer = createTreeViewer(pageComposite);
    _treeViewer.setInput(_root);
    // Force the viewer to pre-load all items.
    _treeViewer.expandAll();
    _treeViewer.collapseAll();
    // Check default preselected elements.
    _treeViewer.setCheckedElements(_preselection.toArray());
    setControl(pageComposite);
  }

  /**
   * Create the table viewer.
   * @param parent_p the parent composite.
   * @return the table viewer.
   */
  @SuppressWarnings("synthetic-access")
  private CheckboxTreeViewer createTreeViewer(Composite parent_p) {
    ContainerCheckedTreeViewer viewer = new DescDiagramSelectionTreeViewer(parent_p, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

    GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
    viewer.getControl().setLayoutData(gridData);
    viewer.getTree().setHeaderVisible(false);
    viewer.getTree().setLinesVisible(false);
    SiriusDiagramSelectionCheckStateListener listener = new SiriusDiagramSelectionCheckStateListener();
    viewer.addCheckStateListener(listener);

    viewer.setContentProvider(new SessionContentProvider(_root));
    ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
    viewer.setLabelProvider(new AdapterFactoryLabelProvider(composedAdapterFactory));

    return viewer;
  }

  /**
   * Change the page according to the new state of the selection.
   */
  private class SiriusDiagramSelectionCheckStateListener implements ICheckStateListener {

    /**
     * {@inheritDoc}
     * @see org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(org.eclipse.jface.viewers.CheckStateChangedEvent)
     */
    @SuppressWarnings("synthetic-access")
    public void checkStateChanged(final CheckStateChangedEvent event_p) {
      int result = checkSelection(getSelectedElements());
      switch (result) {
        case CODE_OK:
          setMessage(SELECT_DIAGRAMS_TO_EXPORT, IMessageProvider.INFORMATION);
          setPageComplete(true);
        break;
        case CODE_NO_SEL:
          setMessage(SELECT_DIAGRAMS_TO_EXPORT, IMessageProvider.INFORMATION);
          setPageComplete(true);
        break;
        case CODE_ERROR:
        break;
        default:
          setMessage("Unknown code result", IMessageProvider.ERROR); //$NON-NLS-1$
          setPageComplete(false);
        break;
      }
    }

  }

  private int checkSelection(Collection<?> selectedItems_p) {
    int result = CODE_OK;
    if (selectedItems_p.isEmpty()) {
      result = CODE_NO_SEL;
    } else {
      final Iterator<?> iterItems = selectedItems_p.iterator();
      while (iterItems.hasNext()) {
        Object next = iterItems.next();
        if (!(next instanceof DDiagram)) {
          result = CODE_ERROR;
        }
      }
    }
    return result;
  }

  /**
   * Selects only diagrams.
   */
  public class DescDiagramSelectionTreeViewer extends ContainerCheckedTreeViewer {

    /**
     * Create a new <code>DescDiagramSelectionTreeViewer</code>.
     * @param parent_p the parent composite.
     */
    public DescDiagramSelectionTreeViewer(Composite parent_p) {
      super(parent_p);
    }

    /**
     * Create a new <code>DescDiagramSelectionTreeViewer</code>.
     * @param parent_p the parent composite.
     * @param style_p the style.
     */
    public DescDiagramSelectionTreeViewer(Composite parent_p, int style_p) {
      super(parent_p, style_p);
    }

    /**
     * Create a new <code>DescDiagramSelectionTreeViewer</code>.
     * @param tree_p the tree.
     */
    public DescDiagramSelectionTreeViewer(Tree tree_p) {
      super(tree_p);
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.ui.dialogs.ContainerCheckedTreeViewer#doCheckStateChanged(java.lang.Object)
     */
    @Override
    protected void doCheckStateChanged(Object element_p) {
      //
      // Check all diagrams that are under this element.
      final Widget item = findItem(element_p);
      if (item instanceof TreeItem) {
        final TreeItem treeItem = (TreeItem) item;
        if (!(element_p instanceof DRepresentation)) {
          final boolean result = updateChildrenItems(treeItem, treeItem.getChecked());
          if (result) {
            treeItem.setGrayed(true);
          } else {
            treeItem.setGrayed(false);
          }
          treeItem.setChecked(result);
        }
        updateParentItems(treeItem);
      }

    }

    /**
     * Updates the check / gray state of all parent items
     */
    private void updateParentItems(TreeItem item_p) {
      if (item_p != null && !(item_p.getData() instanceof DDiagram)) {
        final Item[] children = getChildren(item_p);
        boolean containsChecked = false;
        for (int i = 0; i < children.length; i++) {
          final TreeItem curr = (TreeItem) children[i];
          containsChecked = containsChecked || curr.getChecked();
        }
        item_p.setChecked(containsChecked);
        item_p.setGrayed(containsChecked);
      }
      if (item_p != null)
        updateParentItems(item_p.getParentItem());
    }

    /**
     * Updates the check state of all created children
     * @return <code>true</code> if an element as been checked.
     */
    private boolean updateChildrenItems(TreeItem parent_p, boolean state_p) {
      boolean result = false;
      Item[] children = getChildren(parent_p);
      for (int i = 0; i < children.length; i++) {
        TreeItem curr = (TreeItem) children[i];
        if (curr.getData() instanceof DDiagram && ((curr.getChecked() != state_p) || curr.getGrayed())) {
          curr.setChecked(state_p);
          curr.setGrayed(false);
          result = result || state_p;
          result = result || updateChildrenItems(curr, state_p);
        } else if (curr.getData() != null && ((curr.getChecked() != state_p) || curr.getGrayed())) {
          boolean childrenResult = updateChildrenItems(curr, state_p);
          if (childrenResult) {
            curr.setChecked(true);
            curr.setGrayed(true);
          } else {
            curr.setChecked(false);
            curr.setGrayed(false);
          }
          result = result || childrenResult;
        }
      }
      return result;
    }
  }

  /**
   * Return all selected elements.
   * @return all selected elements.
   */
  @SuppressWarnings("unchecked")
  public Collection<DRepresentation> getSelectedElements() {
    Collection<DRepresentation> result = new HashSet<DRepresentation>();
    Collection<? extends DRepresentation> asList = (Collection<? extends DRepresentation>) Arrays.asList(_treeViewer.getCheckedElements());
    result.addAll(asList);
    result.removeAll(Arrays.asList(_treeViewer.getGrayedElements()));
    return result;
  }

  private static final class SessionContentProvider implements ITreeContentProvider {

    private static final Object[] empty = new Object[0];

    /** The semantic EMF content provider. */
    private AdapterFactoryContentProvider _semanticProvider;

    private Session _session;

    /**
     * Create a new <code>SemanticDViewContentProvider</code> with the specified analysis.
     * @param session_p
     */
    public SessionContentProvider(Session session_p) {
      _session = session_p;
      AdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
      _semanticProvider = new AdapterFactoryContentProvider(adapterFactory);
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object parentElement_p) {
      Object[] children = empty;
      if (parentElement_p instanceof EObject && !(parentElement_p instanceof DDiagram)) {
        EObject parent = (EObject) parentElement_p;
        Collection<DRepresentation> representations = this.findRepresentations(parent);
        Object[] semantic = this._semanticProvider.getChildren(parentElement_p);
        semantic = filtersSemanticFromAnotherResource(parent.eResource(), semantic);
        Object[] result = new Object[representations.size() + semantic.length];
        int i = 0;
        Iterator<DRepresentation> iterRepresentation = representations.iterator();
        while (iterRepresentation.hasNext()) {
          result[i++] = iterRepresentation.next();
        }
        System.arraycopy(semantic, 0, result, representations.size(), semantic.length);
        children = result;
      } else if (parentElement_p instanceof Session) {
        children = ((Session) parentElement_p).getSemanticResources().toArray();
      } else if (parentElement_p instanceof Resource) {
        children = ((Resource) parentElement_p).getContents().toArray();
      }
      return children;
    }

    private Object[] filtersSemanticFromAnotherResource(Resource resource_p, Object[] semantic_p) {
      Collection<Object> filtered = new ArrayList<Object>();
      for (Object object : semantic_p) {
        filtered.add(object);
      }

      for (Object object : semantic_p) {
        if (object instanceof EObject && ((EObject) object).eResource() != null) {
          if (resource_p != ((EObject) object).eResource() && _session.getSemanticResources().contains(((EObject) object).eResource())) {
            filtered.remove(object);
          }
        }
      }

      return filtered.toArray();
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element_p) {
      if (element_p instanceof EObject) {
        EObject current = (EObject) element_p;
        EObject parent = current instanceof DSemanticDiagram ? ((DSemanticDiagram) current).getTarget() : current.eContainer();
        return parent;
      }
      return null;
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element_p) {
      return getChildren(element_p).length > 0;
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement_p) {
      return getChildren(inputElement_p);
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
      _semanticProvider.dispose();
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer_p, Object oldInput_p, Object newInput_p) {
      // empty
    }

    /**
     * Return all the diagrams for the specified viewpoint.
     * @param semanticElement_p the parent semantic element.
     * @return all the diagrams for the specified viewpoint.
     */
    private Collection<DRepresentation> findRepresentations(EObject semanticElement_p) {
      if (semanticElement_p == null)
        return Collections.emptySet();
      return DialectManager.INSTANCE.getRepresentations(semanticElement_p, _session);
    }
  }

}
