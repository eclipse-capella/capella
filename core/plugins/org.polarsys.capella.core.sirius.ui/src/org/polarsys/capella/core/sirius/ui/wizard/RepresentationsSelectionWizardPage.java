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
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

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
  private CheckboxTreeViewer treeViewer;

  /** The filter. */

  private Session root;

  private Collection<DRepresentationDescriptor> preselection;

  /**
   * Create a new <code>DescDiagramSelectionWizardPage</code>.
   * @param root the root object
   * @param representations the preselection.
   */
  public RepresentationsSelectionWizardPage(Session root, Collection<DRepresentationDescriptor> representations) {
    super(PAGE_TITLE);
    setTitle(PAGE_TITLE);
    this.root = root;
    preselection = representations;
    if (preselection.size() > 0)
      setPageComplete(true);
    setMessage(SELECT_DIAGRAMS_TO_EXPORT, IMessageProvider.INFORMATION);
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent) {
    initializeDialogUnits(parent);

    pageComposite = new Composite(parent, SWT.NONE);
    pageComposite.setLayout(new GridLayout());
    pageComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

    treeViewer = createTreeViewer(pageComposite);
    treeViewer.setInput(root);
    // Force the viewer to pre-load all items.
    treeViewer.expandAll();
    treeViewer.collapseAll();
    // Check default preselected elements.
    setControl(pageComposite);
    
    for (final DRepresentationDescriptor preselected : preselection) {
        this.treeViewer.setChecked(preselected, true);
    }
  }

  /**
   * Create the table viewer.
   * @param parent the parent composite.
   * @return the table viewer.
   */
  @SuppressWarnings("synthetic-access")
  private CheckboxTreeViewer createTreeViewer(Composite parent) {
    ContainerCheckedTreeViewer viewer = new DescDiagramSelectionTreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

    GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
    viewer.getControl().setLayoutData(gridData);
    viewer.getTree().setHeaderVisible(false);
    viewer.getTree().setLinesVisible(false);
    viewer.addCheckStateListener(new SiriusDiagramSelectionCheckStateListener());

    viewer.setContentProvider(new SessionContentProvider(root));
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
    public void checkStateChanged(final CheckStateChangedEvent event) {
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

  private int checkSelection(Collection<?> selectedItems) {
    int result = CODE_OK;
    if (selectedItems.isEmpty()) {
      result = CODE_NO_SEL;
    } else {
      final Iterator<?> iterItems = selectedItems.iterator();
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
     * @param parent the parent composite.
     */
    public DescDiagramSelectionTreeViewer(Composite parent) {
      super(parent);
    }

    /**
     * Create a new <code>DescDiagramSelectionTreeViewer</code>.
     * @param parent the parent composite.
     * @param style the style.
     */
    public DescDiagramSelectionTreeViewer(Composite parent, int style) {
      super(parent, style);
    }

    /**
     * Create a new <code>DescDiagramSelectionTreeViewer</code>.
     * @param tree the tree.
     */
    public DescDiagramSelectionTreeViewer(Tree tree) {
      super(tree);
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.ui.dialogs.ContainerCheckedTreeViewer#doCheckStateChanged(java.lang.Object)
     */
    @Override
    protected void doCheckStateChanged(Object element) {
      //
      // Check all diagrams that are under this element.
      final Widget item = findItem(element);
      if (item instanceof TreeItem) {
        final TreeItem treeItem = (TreeItem) item;
        if (!(element instanceof DRepresentationDescriptor)) {
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
    private void updateParentItems(TreeItem item) {
      if (item != null && !(item.getData() instanceof DRepresentationDescriptor)) {
        final Item[] children = getChildren(item);
        boolean containsChecked = false;
        for (int i = 0; i < children.length; i++) {
          final TreeItem curr = (TreeItem) children[i];
          containsChecked = containsChecked || curr.getChecked();
        }
        item.setChecked(containsChecked);
        item.setGrayed(containsChecked);
      }
      if (item != null)
        updateParentItems(item.getParentItem());
    }

    /**
     * Updates the check state of all created children
     * @return <code>true</code> if an element as been checked.
     */
    private boolean updateChildrenItems(TreeItem parent, boolean state) {
      boolean result = false;
      Item[] children = getChildren(parent);
      for (int i = 0; i < children.length; i++) {
        TreeItem curr = (TreeItem) children[i];
        if (curr.getData() instanceof DRepresentationDescriptor && ((curr.getChecked() != state) || curr.getGrayed())) {
          curr.setChecked(state);
          curr.setGrayed(false);
          result = result || state;
          result = result || updateChildrenItems(curr, state);
        } else if (curr.getData() != null && ((curr.getChecked() != state) || curr.getGrayed())) {
          boolean childrenResult = updateChildrenItems(curr, state);
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
  public Collection<DRepresentationDescriptor> getSelectedElements() {
    Collection<DRepresentationDescriptor> result = new HashSet<DRepresentationDescriptor>();
    Collection<? extends DRepresentationDescriptor> asList = (Collection<? extends DRepresentationDescriptor>) Arrays.asList(treeViewer.getCheckedElements());
    result.addAll(asList);
    result.removeAll(Arrays.asList(treeViewer.getGrayedElements()));
    return result;
  }

  private static final class SessionContentProvider implements ITreeContentProvider {

    private static final Object[] empty = new Object[0];

    /** The semantic EMF content provider. */
    private AdapterFactoryContentProvider _semanticProvider;

    private Session _session;

    /**
     * Create a new <code>SemanticDViewContentProvider</code> with the specified analysis.
     * @param session
     */
    public SessionContentProvider(Session session) {
      _session = session;
      AdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
      _semanticProvider = new AdapterFactoryContentProvider(adapterFactory);
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object parentElement) {
      Object[] children = empty;
      if (parentElement instanceof Session) {
    	Session session = (Session) parentElement;
    	children = SessionHelper.getSemanticResources(session).toArray();
      } else if (parentElement instanceof Resource) {
        children = ((Resource) parentElement).getContents().toArray();
      } else if (parentElement instanceof EObject && !(parentElement instanceof DRepresentationDescriptor)) {
        EObject parent = (EObject) parentElement;
        Collection<DRepresentationDescriptor> representations = this.findRepDescriptors(parent);
        Object[] semantic = this._semanticProvider.getChildren(parentElement);
        semantic = filtersSemanticFromAnotherResource(parent.eResource(), semantic);
        Object[] result = new Object[representations.size() + semantic.length];
        int i = 0;
        Iterator<DRepresentationDescriptor> iterRepresentation = representations.iterator();
        while (iterRepresentation.hasNext()) {
          result[i++] = iterRepresentation.next();
        }
        System.arraycopy(semantic, 0, result, representations.size(), semantic.length);
        children = result;
      }
      return children;
    }
    
    private Object[] filtersSemanticFromAnotherResource(Resource resource, Object[] semantic) {
      Collection<Object> filtered = new ArrayList<Object>();
      for (Object object : semantic) {
        filtered.add(object);
      }

      for (Object object : semantic) {
        if (object instanceof EObject && ((EObject) object).eResource() != null) {
          if (resource != ((EObject) object).eResource() && _session.getSemanticResources().contains(((EObject) object).eResource())) {
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
    public Object getParent(Object element) {
      if (element instanceof EObject) {
        EObject current = (EObject) element;
        EObject parent = current instanceof DRepresentationDescriptor ? ((DRepresentationDescriptor) current).getTarget() : current.eContainer();
        return parent;
      }
      return null;
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element) {
      return getChildren(element).length > 0;
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement) {
      return getChildren(inputElement);
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
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
      // empty
    }

    /**
     * Return all the DRepresentationDescriptor for the specified viewpoint.
     *
     * @param semanticElement
     *            the parent semantic element.
     * @return all the diagrams for the specified viewpoint.
     */
    private Collection<DRepresentationDescriptor> findRepDescriptors(final EObject semanticElement) {
        if (semanticElement == null) {
            return Collections.emptySet();
        }
        return DialectManager.INSTANCE.getRepresentationDescriptors(semanticElement, _session);
    }
  }

}
