/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.updateconnections.ui;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeDialog;
import org.eclipse.emf.diffmerge.ui.viewers.ComparisonSideViewer;
import org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.EnhancedComparisonSideViewer;
import org.eclipse.emf.diffmerge.ui.viewers.EnhancedComparisonTreeViewer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.flexibility.properties.loader.PropertiesLoader;
import org.polarsys.capella.common.flexibility.properties.property.PropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.loader.RenderersLoader;
import org.polarsys.capella.common.flexibility.wizards.renderer.RendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.ui.PropertyDialog;
import org.polarsys.capella.common.flexibility.wizards.ui.PropertyWizard;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.core.re.handlers.replicable.ReplicableElementHandler;
import org.polarsys.capella.core.re.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.re.updateconnections.ui.properties.CatalogElementSelection;
import org.polarsys.capella.core.re.updateconnections.ui.properties.ConnectionMatcherStrategy;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

public class UpdateConnectionsHandler extends AbstractHandler {

  public static final String RPL_1_PROPERTY = "RPL_1"; //$NON-NLS-1$
  public static final String RPL_2_PROPERTY = "RPL_2"; //$NON-NLS-1$
  public static final String CONNECTION_MATCHER_STRATEGY_PROPERTY = "connectionMatcherStrategy"; //$NON-NLS-1$

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {

    List<?> selection = adaptSelection(((IStructuredSelection) HandlerUtil.getCurrentSelection(event)).toList());

    IProperties properties = new PropertiesLoader().getProperties("updateconnections.properties"); //$NON-NLS-1$
    final IPropertyContext pcontext = new PropertyContext(properties, event);

    CatalogElementSelection rpl1 = ((CatalogElementSelection) properties.getProperty(RPL_1_PROPERTY));
    rpl1.setAvailableCatalogElements(
        getOrderedAvailableCatalogElements((EObject) selection.get(0), CatalogElementKind.RPL));

    CatalogElementSelection rpl2 = ((CatalogElementSelection) properties.getProperty(RPL_2_PROPERTY));
    rpl2.setAvailableCatalogElements(
        getOrderedAvailableCatalogElements((EObject) selection.get(1), CatalogElementKind.RPL));

    ConnectionMatcherStrategy cms = ((ConnectionMatcherStrategy) properties
        .getProperty(CONNECTION_MATCHER_STRATEGY_PROPERTY));
    Collection<ConnectionMatcher> availableConnectionMatchers = new ArrayList<ConnectionMatcher>();
    availableConnectionMatchers.add(new SingleUsePortsMatcher());
    availableConnectionMatchers.add(new ByNameMatcher());
    
    cms.setAvailableConnectionMatchers(availableConnectionMatchers);

    IRendererContext rcontext = new RendererContext(new RenderersLoader().getRenderers(properties), pcontext);
    PropertyWizard wizard = new PropertyWizard(pcontext, rcontext);
    wizard.setWindowTitle(Messages.UpdateConnectionsHandler_0);
    wizard.setHelpAvailable(false);

    PropertyDialog propertyDialog = new PropertyDialog(HandlerUtil.getActiveShell(event), wizard);
    propertyDialog.setHelpAvailable(false);
    propertyDialog.create();
    propertyDialog.setTitle(Messages.PropertyDialog_Title);

    if (propertyDialog.open() == Window.OK) {

      CatalogElement selectedRPL1 = (CatalogElement) pcontext.getCurrentValue(rpl1);
      CatalogElement selectedRPL2 = (CatalogElement) pcontext.getCurrentValue(rpl2);

      ConnectionMatcher connectionMatcher = (ConnectionMatcher) pcontext.getCurrentValue(cms);

      final EComparison comp = new DiffmergeHandler(selectedRPL1, selectedRPL2, connectionMatcher)
          .computeDifferences(new NullProgressMonitor());

      final EMFDiffNode diffNode = new EMFDiffNode(comp, TransactionHelper.getEditingDomain(selectedRPL1));

      // Show the comparison in a dialog
      UpdateConnectionsDiffmergeDialog dialog = new UpdateConnectionsDiffmergeDialog(HandlerUtil.getActiveShell(event),
          Messages.UpdateConnectionsHandler_0, diffNode);
      dialog.open();
    }
    return null;
  }

  private Collection<CatalogElement> getAllAvailableCatalogElements(EObject selection, final CatalogElementKind kind) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_CATALOG_ELEMENTS_FOR_LIB, selection,
        new QueryContext(), new IQueryFilter() {
          @Override
          public boolean keepElement(Object element, IQueryContext context) {
            return (element != null) && (element instanceof CatalogElement)
                && (((CatalogElement) element).getKind() == kind);
          }
        });
  }

  private Collection<CatalogElement> getOrderedAvailableCatalogElements(EObject selection, CatalogElementKind kind) {
    Collection<CatalogElement> allRPLs = getAllAvailableCatalogElements(selection, kind);
    EList<CatalogElement> result = new BasicEList<CatalogElement>(allRPLs);
    Collection<CatalogElement> selectedRPLs = new ReplicableElementHandler().getIndirectlyReplicableElements(null,
        Collections.singleton(selection));
    int index = 0;
    for (CatalogElement selected : selectedRPLs) {
      result.move(index, selected);
      index++;
    }

    return result;
  }

  protected List<?> adaptSelection(List<?> originalSelection) {
    return originalSelection;
  }

  static class UpdateConnectionsDiffmergeDialog extends DiffMergeDialog {

    public UpdateConnectionsDiffmergeDialog(Shell shell_p, String title_p, EMFDiffNode input_p) {
      super(shell_p, title_p, input_p);
    }

    @Override
    protected ComparisonViewer createComparisonViewer(Composite parent_p) {

      ComparisonViewer comparisonViewer = new ComparisonViewer(parent_p);

      EnhancedComparisonSideViewer leftViewer = comparisonViewer.getModelScopeViewer(true);
      leftViewer.getInnerViewer().setContentProvider(new SparseScopeTreeContentProvider());
      EnhancedComparisonSideViewer rightViewer = comparisonViewer.getModelScopeViewer(false);
      rightViewer.getInnerViewer().setContentProvider(new SparseScopeTreeContentProvider());

      // the synthesis viewer (Leftmost viewer in the dialog)
      // should not show differences between elements that
      // are only in the scope as reference targets.
      EnhancedComparisonTreeViewer mainViewer = comparisonViewer.getSynthesisViewer();
      mainViewer.getInnerViewer().addFilter(new ViewerFilter() {
        @Override
        public boolean select(Viewer viewer, Object parentElement, Object element) {
          IMatch match = (IMatch) element;
          EMFDiffNode node = (EMFDiffNode) viewer.getInput();
          SparseModelScope referenceScope = (SparseModelScope) node.getActualComparison().getReferenceScope();
          SparseModelScope targetScope = (SparseModelScope) node.getActualComparison().getTargetScope();
          Object refElement = match.get(Role.REFERENCE);
          if (refElement != null) {
            if (referenceScope.getExternalReferenceElements().contains(refElement)) {
              return false;
            }
          }
          Object tarElement = match.get(Role.TARGET);
          if (tarElement != null) {
            if (targetScope.getExternalReferenceElements().contains(tarElement)) {
              return false;
            }
          }
          return true;
        }
      });
      return comparisonViewer;
    }
  }

  static class SparseScopeTreeContentProvider implements ITreeContentProvider, SparseModelScope.Listener {

    private final Multimap<EObject, EObject> tree = LinkedHashMultimap.create();
    private final EObject root = EcoreFactory.eINSTANCE.createEObject();

    private SparseModelScope currentScope;

    @Override
    public void dispose() {
      if (currentScope != null) {
        currentScope.removeListener(this);
      }
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
      if (currentScope != null) {
        currentScope.removeListener(this);
      }

      if (newInput == null) {
        currentScope = null;
      } else {
        Role role = ((EMFDiffNode) newInput).getRoleForSide(((ComparisonSideViewer) viewer).isLeftSide());
        currentScope = (SparseModelScope) ((EMFDiffNode) newInput).getActualComparison().getScope(role);
        currentScope.addListener(this);
      }
      rebuildTree();
    }

    private void rebuildTree() {
      tree.clear();
      if (currentScope != null) {

        Deque<EObject> toVisit = new ArrayDeque<EObject>(Lists.newArrayList(currentScope.getAllContents()));
        while (!toVisit.isEmpty()) {
          EObject e = toVisit.pop();
          if (!currentScope.getExternalReferenceElements().contains(e)) {
            if (e.eContainer() != null) {
              tree.put(e.eContainer(), e);
              toVisit.push(e.eContainer());
            } else {
              tree.put(root, e);
            }
          }
        }
      }
    }

    @Override
    public Object[] getElements(Object inputElement) {

      return tree.get(root).toArray();
    }

    @Override
    public Object[] getChildren(Object parentElement) {
      return tree.get((EObject) parentElement).toArray();
    }

    @Override
    public Object getParent(Object element) {
      return (((EObject) element).eContainer());
    }

    @Override
    public boolean hasChildren(Object element) {
      return getChildren(element) != null;
    }

    @Override
    public void scopeChanged() {
      rebuildTree();
    }
  }
}
