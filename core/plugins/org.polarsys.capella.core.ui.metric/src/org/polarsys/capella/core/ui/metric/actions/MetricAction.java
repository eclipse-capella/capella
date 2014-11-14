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
package org.polarsys.capella.core.ui.metric.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.ui.metric.IImageKeys;
import org.polarsys.capella.core.ui.metric.MetricActivator;
import org.polarsys.capella.core.ui.metric.MetricMessages;
import org.polarsys.capella.core.ui.metric.core.Metric;
import org.polarsys.capella.core.ui.metric.core.MetricTree;
import org.polarsys.capella.core.ui.metric.core.filter.DefaultFilter;
import org.polarsys.capella.core.ui.metric.dialog.MetricDialog;

public class MetricAction extends BaseSelectionListenerAction {

  /** the root semantic object selected */
  private EObject _rootSemanticObject;

  /** result of the metrics (unrefined) */
  MetricTree<EObject> _results;

  /**
   * Constructor for Action1.
   */
  public MetricAction() {
    super(MetricMessages.metricAction_lbl);
    setImageDescriptor(MetricActivator.getDefault().getImageDescriptor(IImageKeys.IMG_METRICS));
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {

    _rootSemanticObject = getRootSemanticObject();

    IRunnableWithProgress runnable = new IRunnableWithProgress() {
      /**
       * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
       */
      @SuppressWarnings("synthetic-access")
      public void run(IProgressMonitor progressMonitor_p) throws InvocationTargetException, InterruptedException {
        progressMonitor_p.beginTask(MetricMessages.progressMonitorMsg, -1);

        //
        // Build the tree data (to refactor)
        //

        _results = new MetricTree<EObject>(_rootSemanticObject, null, null);

        ArrayList<MetricTree<EObject>> nodeWithSes = new ArrayList<MetricTree<EObject>>();

        EClass eRoot = _rootSemanticObject.eClass();
        if ( // Project and SystemEngineering cases
        eRoot == CapellamodellerPackage.Literals.PROJECT) {
          Project project = (Project) _rootSemanticObject;
          MetricTree<EObject> tree = null;
          MetricTree<EObject> tree1 = null;
          for (ModelRoot mr : project.getOwnedModelRoots()) {
            tree = new MetricTree<EObject>(mr, null, _results);
            _results.addChild(tree);
            for (ModellingArchitecture ma : ((SystemEngineering) mr).getOwnedArchitectures()) {
              tree1 = new MetricTree<EObject>(ma, null, tree);
              tree.addChild(tree1);
              nodeWithSes.add(tree1);
            }
          }
        } else if (eRoot == CapellamodellerPackage.Literals.SYSTEM_ENGINEERING) {
          SystemEngineering se = (SystemEngineering) _rootSemanticObject;
          MetricTree<EObject> tree = null;
          for (ModellingArchitecture ma : se.getOwnedArchitectures()) {
            tree = new MetricTree<EObject>(ma, null, _results);
            _results.addChild(tree);
            nodeWithSes.add(tree);
          }

        } else if (CapellacorePackage.Literals.MODELLING_ARCHITECTURE.isSuperTypeOf(eRoot)) {
          nodeWithSes.add(_results);
        }

        //
        // Main job (before displaying)
        //

        try {

          EObject root = null;
          MetricTree<EObject> leaf = null;
          EObject eobject = null;
          for (MetricTree<EObject> tree : nodeWithSes) {
            root = tree.getId();

            TreeIterator<EObject> it = root.eAllContents();
            Metric<EClass> metric = new Metric<EClass>();
            metric.addFilter(new DefaultFilter());
            // the main count job
            EObject current = null;
            while (it.hasNext()) {
              current = it.next();
              if (metric.accept(current)) {
                metric.update(current.eClass());
              }
              if (progressMonitor_p.isCanceled()) {
                _results.clear();
                return;
              }
            }
            // we finally store data

            for (EClass eclass : sort(metric.getResult().keySet())) {
              EFactory factory = eclass.getEPackage().getEFactoryInstance();
              eobject = factory.create(eclass);

              customizeMetricTree(root, eobject);

              leaf = new MetricTree<EObject>(eobject, null, tree);
              leaf.setData(metric.getResult().get(eclass));
              tree.addChild(leaf);
            }

          }

        } finally {
          progressMonitor_p.done();
        }
        return;
      }

      /**
       * @param root
       * @param eobject
       */
      private void customizeMetricTree(EObject root, EObject eobject) {

        // Interaction shall be displayed as it, instead of basic FunctionalExchange.
        // @see org.polarsys.capella.core.data.fa.provider.FunctionalExchangeItemProvider#getText(Object)
        if ((eobject instanceof FunctionalExchange) && (root instanceof OperationalAnalysis)) {
          OaFactory oaFactory = OaFactory.eINSTANCE;
          OperationalActivity operationalActivity = oaFactory.createOperationalActivity();
          FunctionalExchange interaction = (FunctionalExchange) eobject;
          interaction.setSource(operationalActivity);
          interaction.setTarget(operationalActivity);
          // Should also be in FunctionalExchangeItemProvider
          interaction.setName("Interaction"); //$NON-NLS-1$
        }
      }
    };

    try {

      PlatformUI.getWorkbench().getProgressService().busyCursorWhile(runnable);

    } catch (Exception exception_p) {
      throw new RuntimeException(exception_p);
    }

    //
    // Let's display result and allow user.
    //

    if ((null != _results) && _results.hasChildren()) {
      openMetricsDialog();
    } else { // action was canceled
      // Do nothing
    }

    // clean everything to avoid any memory leaks.
    _results.clear();
    _rootSemanticObject = null;

    return;
  }

  private List<EClass> sort(Set<EClass> keySet_p) {

    List<EClass> list = new ArrayList<EClass>(keySet_p);

    Collections.sort(list, new java.util.Comparator<EClass>() {

      public int compare(EClass o1_p, EClass o2_p) {
        return o1_p.getName().compareTo(o2_p.getName());
      }

    });

    return list;
  }

  /**
   * Open the metrics window.
   */
  protected void openMetricsDialog() {

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
        objectName = _rootSemanticObject.eClass().getName();
      }

      resourceName = _rootSemanticObject.eResource().getURI().lastSegment();

      title =
          NLS.bind(MetricMessages.metricDialogTitleFromEObj,
              new Object[] { objectName, resourceName.replace(CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION, CapellaResourceHelper.AIRD_FILE_EXTENSION) });
    }

    MetricDialog dialog =
        new MetricDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), title, ICommonConstants.EMPTY_STRING, MetricMessages.metricDialogShellTitle);
    dialog.setData(_results);
    dialog.setResourceName(resourceName);
    dialog.open();

    return;
  }

  /**
   * get the Root semantic {@link EObject} corresponding to the current selection
   * @return <code>null</code> whether selection does not fit any supported case .
   */
  protected EObject getRootSemanticObject() {

    EObject result = null;
    Object selectedObject = getStructuredSelection().getFirstElement();

    try {
      if (selectedObject instanceof IFile) {

        IFile file = (IFile) selectedObject;
        URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
        Session session = SessionManager.INSTANCE.getSession(uri);
        if ((null != session) && session.isOpen()) { // Session is open
          DAnalysis da = null;
          Resource resource = session.getSessionResource();
          da = (DAnalysis) resource.getContents().get(0);
          result = da.getModels().get(0);
        }
      } else if (selectedObject instanceof EObject) {
        result = (EObject) selectedObject;
      } else {
        result = null;
      }
    } catch (Exception exception_p) { // Old models raise up exception
      result = null;
    }

    return result;
  }

}
