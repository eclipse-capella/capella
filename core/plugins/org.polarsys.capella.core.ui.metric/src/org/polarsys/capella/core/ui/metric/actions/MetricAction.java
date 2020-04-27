/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.metric.actions;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.core.ui.metric.IImageKeys;
import org.polarsys.capella.core.ui.metric.MetricActivator;
import org.polarsys.capella.core.ui.metric.MetricMessages;
import org.polarsys.capella.core.ui.metric.core.DefaultFilter;
import org.polarsys.capella.core.ui.metric.core.Metric;
import org.polarsys.capella.core.ui.metric.core.MetricTree;
import org.polarsys.capella.core.ui.metric.dialog.MetricDialog;

public class MetricAction extends BaseSelectionListenerAction {

  private ExecutionManager exeManager = ExecutionManagerRegistry.getInstance().addNewManager();
  private TransactionalEditingDomain editingDomain = exeManager.getEditingDomain();
  private Resource res = HoldingResourceHelper.getHoldingResource(editingDomain);

  public MetricAction() {
    super(MetricMessages.metricAction_lbl);
    setImageDescriptor(MetricActivator.getDefault().getImageDescriptor(IImageKeys.IMG_METRICS));
  }

  @Override
  public void run() {
    EObject rootSemanticObject = ProgressMonitoringActionsHelper.getSelectedEObject(getStructuredSelection());
    MetricTree<EObject> metricTree = computeMetricTree(rootSemanticObject);
    openMetricsDialog(rootSemanticObject, metricTree);
  }

  public MetricTree<EObject> computeMetricTree(EObject rootSemanticObject) {
    MetricTree<EObject> metricTree = null;

    if (rootSemanticObject instanceof Project) {
      Project project = (Project) rootSemanticObject;
      metricTree = createMetricTreeForProject(project, null);
    } else if (rootSemanticObject instanceof SystemEngineering) {
      SystemEngineering se = (SystemEngineering) rootSemanticObject;
      metricTree = createMetricTreeForSystemEngineering(se, null);
    } else if (rootSemanticObject instanceof ModellingArchitecture) {
      ModellingArchitecture arch = (ModellingArchitecture) rootSemanticObject;
      metricTree = createMetricTreeForArchitecture(arch, null);
    }

    if (metricTree != null) {
      for (MetricTree<EObject> leaf : metricTree.getLeafs()) {

        EObject container = leaf.getElement();
        Map<EClass, Integer> classToCount = countObjectByClass(container);

        for (Entry<EClass, Integer> entry : classToCount.entrySet()) {
          // Create temporary objects just to get their name and image.
          EClass eClass = entry.getKey();
          int countOfClass = entry.getValue().intValue();
          EObject tempEObject = createTemproraryEObject(container, eClass);
          MetricTree<EObject> newTreeNode = new MetricTree<>(tempEObject, leaf);
          newTreeNode.increaseCount(countOfClass);
        }
      }
    }
    return metricTree;
  }

  private EObject createTemproraryEObject(EObject container, EClass eclass) {
    EFactory factory = eclass.getEPackage().getEFactoryInstance();
    EObject tempEObject = factory.create(eclass);

    // Interaction shall be displayed as it, instead of basic FunctionalExchange.
    // @see org.polarsys.capella.core.data.fa.provider.FunctionalExchangeItemProvider#getText(Object)
    if ((tempEObject instanceof FunctionalExchange) && (container instanceof OperationalAnalysis)) {
      OaFactory oaFactory = OaFactory.eINSTANCE;
      OperationalActivity operationalActivity = oaFactory.createOperationalActivity();
      FunctionalExchange interaction = (FunctionalExchange) tempEObject;
      interaction.setSource(operationalActivity);
      interaction.setTarget(operationalActivity);
      // Should also be in FunctionalExchangeItemProvider
      interaction.setName("Interaction"); //$NON-NLS-1$
    }

    // Add the temporary object to holding resource so that the adapter factory can find a ItemProviderDecorator
    editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
      @Override
      protected void doExecute() {
        res.getContents().add(tempEObject);
      }
    });

    return tempEObject;
  }

  private Map<EClass, Integer> countObjectByClass(EObject container) {
    Metric<EClass> metric = new Metric<>();
    metric.addFilter(new DefaultFilter());
    for (TreeIterator<EObject> iterator = container.eAllContents(); iterator.hasNext();) {
      EObject eObject = iterator.next();
      if (metric.accept(eObject)) {
        metric.update(eObject.eClass());
      }
    }
    return metric.getResult();
  }

  private MetricTree<EObject> createMetricTreeForProject(Project project, MetricTree<EObject> parent) {
    MetricTree<EObject> tree = new MetricTree<>(project, parent);
    for (ModelRoot mr : project.getOwnedModelRoots()) {
      if (mr instanceof SystemEngineering) {
        SystemEngineering se = (SystemEngineering) mr;
        createMetricTreeForSystemEngineering(se, tree);
      }
    }
    return tree;
  }

  private MetricTree<EObject> createMetricTreeForSystemEngineering(SystemEngineering se, MetricTree<EObject> parent) {
    MetricTree<EObject> tree = new MetricTree<>(se, parent);
    for (ModellingArchitecture arch : se.getOwnedArchitectures()) {
      createMetricTreeForArchitecture(arch, tree);
    }
    return tree;
  }

  private MetricTree<EObject> createMetricTreeForArchitecture(ModellingArchitecture arch, MetricTree<EObject> parent) {
    return new MetricTree<>(arch, parent);
  }

  private void openMetricsDialog(EObject rootSemanticObject, MetricTree<EObject> metricTree) {
    if (metricTree != null) {
      String title = MetricMessages.metricDialogDefaultTitle;
      String resourceName = ICommonConstants.EMPTY_STRING;

      Object selectedObject = getStructuredSelection().getFirstElement();

      if (selectedObject instanceof IFile) { // IFile case
        title = NLS.bind(MetricMessages.metricDialogTitleFromFile, ((IFile) selectedObject).getName());
        resourceName = ((IFile) selectedObject).getName();
      } else if (selectedObject instanceof EObject) { // EObject case
        String objectName;
        if (selectedObject instanceof NamedElement) {
          objectName = ((NamedElement) selectedObject).getName();
        } else {
          objectName = rootSemanticObject.eClass().getName();
        }

        resourceName = rootSemanticObject.eResource().getURI().lastSegment();

        title = NLS.bind(MetricMessages.metricDialogTitleFromEObj, new Object[] { objectName, resourceName
            .replace(CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION, CapellaResourceHelper.AIRD_FILE_EXTENSION) });
      }

      Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
      MetricDialog dialog = new MetricDialog(shell, title, ICommonConstants.EMPTY_STRING,
          MetricMessages.metricDialogShellTitle);
      dialog.setData(metricTree);
      dialog.setResourceName(resourceName);
      dialog.open();
      // clean everything to avoid any memory leaks.
      metricTree.clear();
    }
  }

  @Override
  protected boolean updateSelection(IStructuredSelection selection) {
    if (selection != null && selection.size() == 1) {
      Object selectedObj = selection.getFirstElement();
      if (selectedObj instanceof IFile) {
        Session session = SessionHelper.getSession((IFile) selectedObj);
        return (null != session && session.isOpen());
      } else if (selectedObj instanceof EObject) {
        return selectedObj instanceof SystemEngineering || selectedObj instanceof BlockArchitecture;
      }
    }
    return false;
  }
}
