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
package org.polarsys.capella.core.transition.common.transposer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;

import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.kitalpha.cadence.core.api.parameter.WorkflowActivityParameter;
import org.polarsys.capella.core.transition.common.exception.TransitionException;
import org.polarsys.kitalpha.transposer.TransposerCorePlugin;
import org.polarsys.kitalpha.transposer.analyzer.graph.Graph;
import org.polarsys.kitalpha.transposer.analyzer.graph.Vertex;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.api.TransposerConfiguration;
import org.polarsys.kitalpha.transposer.generic.GenericContext;
import org.polarsys.kitalpha.transposer.generic.GenericTransposer;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.mappings.purposes.NonExistingPurposeException;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.rules.RuleExecutionException;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IDomainHelper;
import org.polarsys.kitalpha.transposer.scheduler.api.ITransposerTask;

/**
 *
 */
public class ExtendedTransposer extends GenericTransposer {

  protected ExtendedCadenceLauncher cadenceLauncher;

  protected HashMap<IProgressMonitor, IStatus> statuses;

  public IContext getContext() {
    return _context;
  }

  @Override
  public void dispose() {
    if (statuses != null) {
      statuses.clear();
      statuses = null;
    }

    super.dispose();
  }

  /**
   * @param purpose_p
   * @param mappingId_p
   */
  public ExtendedTransposer(String purpose_p, String mappingId_p) {
    super(purpose_p, mappingId_p);
  }

  @Override
  protected void setUp(String purpose_p, String mappingId_p) {
    super.setUp(purpose_p, mappingId_p);
    initCadence();
    statuses = new HashMap<IProgressMonitor, IStatus>();
  }

  public void initCadence() {
    cadenceLauncher = new ExtendedCadenceLauncher();
  }

  /**
   * @see org.polarsys.kitalpha.transposer.api.ITransposer#transpose(java.util.Collection)
   */
  @Override
  public void transpose(Collection<Object> selection_p, TransposerConfiguration configuration_p, Comparator<Vertex<?>> comparator_p, IProgressMonitor monitor_p) {
    try {
      Collection<Object> analysisSources = (Collection) getContext().get(ITransposerWorkflow.TRANSPOSER_ANALYSIS_SOURCES);
      if (analysisSources == null) {
        analysisSources = new ArrayList<Object>();
      }

      checkCancel(monitor_p);

      callPreAnalysisActivities(selection_p, analysisSources, configuration_p, monitor_p);

      checkCancel(monitor_p);

      // Default sources : selection, cleaned by the domain helper.
      if ((analysisSources == null) || analysisSources.isEmpty()) {
        Collection<Object> sources = _rulesHandler.getDomainHelper().getAnalysisSources(selection_p);
        if ((sources != null) && !sources.isEmpty()) {
          analysisSources.addAll(sources);
        }
      }

      // Create graph
      Graph graph = getAnalyzer().analyze(analysisSources, selection_p, monitor_p);

      checkCancel(monitor_p);

      callPreSchedulingActivities(graph, configuration_p, monitor_p);

      checkCancel(monitor_p);

      // Create a sorted list of task from the graph
      getScheduler().setModel(graph);
      getScheduler().schedule(comparator_p, monitor_p);

      List<ITransposerTask<Vertex<?>>> scheduledTasks = getScheduler().getScheduleResult();

      checkCancel(monitor_p);

      callPreExecutionActivities(scheduledTasks, configuration_p, monitor_p);

      checkCancel(monitor_p);

      // Execute computed tasks
      // getRulesHandler().setDefaultContext(defaultContext_p)

      if (monitor_p != null) {
        monitor_p.beginTask("Transposer Tasks Execution", scheduledTasks.size()); //$NON-NLS-1$
      }

      for (ITransposerTask<Vertex<?>> scheduledTask : scheduledTasks) {
        Vertex<?> v = scheduledTask.getTaskContent();
        try {
          getRulesHandler().apply(v.getContent(), scheduledTask.isCompletelyTransposable(), monitor_p);
          checkCancel(monitor_p);
        } catch (RuleExecutionException e) {
          TransposerCorePlugin.getDefault().logError(TransposerCorePlugin.PLUGIN_ID,
              RuleExecutionException.class.getSimpleName() + " on " + e.getMessage(), e.getCause()); //$NON-NLS-1$
        }

        if (monitor_p != null) {
          monitor_p.worked(1);
        }
      }

      checkCancel(monitor_p);

      callPostExecutionActivities(configuration_p, monitor_p);

      checkCancel(monitor_p);

      if (monitor_p != null) {
        monitor_p.done();
      }

    } catch (OperationCanceledException e) {
      throw e;
    } catch (Exception e) {
      throw new TransitionException(e);
    }
  }

  /**
   * @param monitor_p
   */
  protected void checkCancel(IProgressMonitor monitor_p) throws Exception {
    if (monitor_p.isCanceled()) {
      IStatus status = statuses.get(monitor_p);

      if ((status != null) && (!status.matches(IStatus.CANCEL))) {
        throw new TransitionException(status);
      }
      throw new OperationCanceledException();
    }

  }

  @Override
  public void initAnalyzer() {
    _analyzer = new ExtendedAnalyzer(_rulesHandler);
  }

  @Override
  public void initScheduler() {
    _scheduler = new ExtendedScheduler();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initRulesHandler(String purpose_p, String mappingId_p) {
    try {
      _rulesHandler = new ExtendedRulesHandler(purpose_p, mappingId_p);

      if (_rulesHandler.getContext() == null) {
        _rulesHandler.setContext(new GenericContext());
      }

      _context = _rulesHandler.getContext();

    } catch (NonExistingPurposeException e) {
      TransposerCorePlugin.getDefault().logError(TransposerCorePlugin.PLUGIN_ID, e.getMessage(), e);
    }
  }

  /**
   * @param selection_p
   * @param analysisSources_p
   * @param graph_p
   * @param configuration_p
   */
  protected void callPreSchedulingActivities(Graph graph_p, TransposerConfiguration configuration_p, IProgressMonitor monitor_p) {
    if (configuration_p == null) {
      return;
    }
    _cadenceParameters.addParameter(new GenericParameter<Graph>(TRANSPOSER_ANALYSIS_GRAPH, graph_p, "Computed analysis graph")); //$NON-NLS-1$

    complementActivitiesParameters(configuration_p.getPreSchedulingActivities());

    try {
      cadence(TRANSPOSER_WORKFLOW, PRE_SCHEDULING, configuration_p.getPreSchedulingActivities(), monitor_p);
    } catch (Exception e) {
      throw new TransitionException(e);
    }
  }

  protected Set<?> getElements(Collection<ITransposerTask<Vertex<?>>> sortedTasks_p) {
    Set<Object> set = new LinkedHashSet<Object>();

    for (ITransposerTask<Vertex<?>> task : sortedTasks_p) {
      set.add(task.getTaskContent().getContent());
    }

    return set;

  }

  /**
   * 
   * 
   * @param sortedTasks_p
   * @param configuration_p
   */
  protected void callPreExecutionActivities(Collection<ITransposerTask<Vertex<?>>> sortedTasks_p, TransposerConfiguration configuration_p,
      IProgressMonitor monitor_p) {
    if (configuration_p == null) {
      return;
    }

    _cadenceParameters.addParameter(new GenericParameter<Collection<ITransposerTask<Vertex<?>>>>(TRANSPOSER_SORTED_TASKS, sortedTasks_p,
        "Computed and sorted tasks list")); //$NON-NLS-1$
    _cadenceParameters.addParameter(new GenericParameter<Set<?>>(TRANSPOSER_TRANSPOSABLE_OBJECTS, getElements(sortedTasks_p), "Transposable elements")); //$NON-NLS-1$

    complementActivitiesParameters(configuration_p.getPreExecutionActivities());

    try {
      cadence(TRANSPOSER_WORKFLOW, PRE_EXECUTION, configuration_p.getPreExecutionActivities(), monitor_p);
    } catch (Exception e) {
      throw new TransitionException(e);
    }
  }

  public IStatus cadence(final String workflow_id, final String workflowElement_id, final WorkflowActivityParameter workflowActivityParameters,
      final IProgressMonitor monitor_p) throws Exception {
    IStatus result = cadenceLauncher.cadence(workflow_id, workflowElement_id, workflowActivityParameters, monitor_p);
    if (result.matches(IStatus.CANCEL) || result.matches(IStatus.ERROR)) {
      monitor_p.setCanceled(true);
      statuses.put(monitor_p, result);
    }
    return result;
  }

  /**
   * @param preAnalysisActivities_p
   */
  protected void complementActivitiesParameters(WorkflowActivityParameter activities_p) {
    if (activities_p == null) {
      return;
    }

    // Add business parameters to the map
    for (String key : activities_p.getActivitiesID()) { // key == activities_id
      if ((_cadenceParameters != null) && !_cadenceParameters.getParameters().isEmpty()) {
        for (GenericParameter<?> p : _cadenceParameters.getParameters()) {
          activities_p.addParameter(key, p);
        }
      }
    }

  }

  /**
   * @param selection_p
   * @param analysisSources_p
   * @param graph_p
   * @param configuration_p
   */
  protected void callPostExecutionActivities(TransposerConfiguration configuration_p, IProgressMonitor monitor_p) {
    if (configuration_p == null) {
      return;
    }

    complementActivitiesParameters(configuration_p.getPostExecutionActivities());

    try {
      cadence(TRANSPOSER_WORKFLOW, POST_EXECUTION, configuration_p.getPostExecutionActivities(), monitor_p);
    } catch (Exception e) {
      throw new TransitionException(e);
    }
  }

  /**
   * @param selection_p_p
   * @param analysisSources_p
   */
  protected void callPreAnalysisActivities(Collection<Object> selection_p, Collection<Object> analysisSources_p, TransposerConfiguration configuration_p,
      IProgressMonitor monitor_p) {
    if (configuration_p == null) {
      return;
    }

    _cadenceParameters.addParameter(new GenericParameter<Collection<Object>>(TRANSPOSER_INITIAL_SELECTION, selection_p,
        "List of selected elements before Transposer's launch")); //$NON-NLS-1$
    _cadenceParameters.addParameter(new GenericParameter<Collection<Object>>(TRANSPOSER_SELECTION, selection_p,
        "List of selected elements before Transposer's launch")); //$NON-NLS-1$
    _cadenceParameters.addParameter(new GenericParameter<Collection<Object>>(TRANSPOSER_ANALYSIS_SOURCES, analysisSources_p,
        "List to store object required to start the analysis")); //$NON-NLS-1$
    _cadenceParameters.addParameter(new GenericParameter<IContext>(TRANSPOSER_CONTEXT, _context, "Context used during rules execution")); //$NON-NLS-1$

    _cadenceParameters.addParameter(new GenericParameter<IDomainHelper>(TRANSPOSER_DOMAIN_HELPER, _rulesHandler.getDomainHelper(),
        "Domain Helper used by Transposer")); //$NON-NLS-1$

    complementActivitiesParameters(configuration_p.getPreAnalysisActivities());

    try {
      cadence(TRANSPOSER_WORKFLOW, PRE_ANALYSIS, configuration_p.getPreAnalysisActivities(), monitor_p);
    } catch (Exception e) {
      throw new TransitionException(e);
    }
  }
}
