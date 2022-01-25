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
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.core.transition.common.exception.TransitionException;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.kitalpha.cadence.core.api.parameter.WorkflowActivityParameter;
import org.polarsys.kitalpha.transposer.TransposerCorePlugin;
import org.polarsys.kitalpha.transposer.analyzer.graph.Graph;
import org.polarsys.kitalpha.transposer.analyzer.graph.Vertex;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.api.TransposerConfiguration;
import org.polarsys.kitalpha.transposer.generic.GenericTransposer;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.mappings.purposes.NonExistingPurposeException;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.rules.RuleExecutionException;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IDomainHelper;
import org.polarsys.kitalpha.transposer.scheduler.api.ITransposerTask;

/**
 *
 */
public class ExtendedTransposer extends GenericTransposer {

  protected ExtendedCadenceLauncher extendedCadenceLauncher;

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
   * @param purpose
   * @param mappingId
   */
  public ExtendedTransposer(String purpose, String mappingId) {
    super(purpose, mappingId);
  }

  @Override
  protected void setUp(String purpose, String mappingId) {
    super.setUp(purpose, mappingId);
    initCadence();
    statuses = new HashMap<IProgressMonitor, IStatus>();
  }

  public void initCadence() {
    extendedCadenceLauncher = new ExtendedCadenceLauncher();
  }

  /**
   * @see org.polarsys.kitalpha.transposer.api.ITransposer#transpose(java.util.Collection)
   */
  @Override
  public void transpose(Collection<Object> selection, TransposerConfiguration configuration, Comparator<Vertex<?>> comparator, IProgressMonitor monitor) {
    try {
      Collection<Object> analysisSources = (Collection) getContext().get(ITransposerWorkflow.TRANSPOSER_ANALYSIS_SOURCES);
      if (analysisSources == null) {
        analysisSources = new ArrayList<Object>();
      }

      checkCancel(monitor);

      callPreAnalysisActivities(selection, analysisSources, configuration, monitor);

      checkCancel(monitor);

      // Default sources : selection, cleaned by the domain helper.
      if ((analysisSources == null) || analysisSources.isEmpty()) {
        Collection<Object> sources = _rulesHandler.getDomainHelper().getAnalysisSources(selection);
        if ((sources != null) && !sources.isEmpty()) {
          analysisSources.addAll(sources);
        }
      }

      // Create graph
      Graph graph = getAnalyzer().analyze(analysisSources, selection, monitor);

      checkCancel(monitor);

      callPreSchedulingActivities(graph, configuration, monitor);

      checkCancel(monitor);

      // Create a sorted list of task from the graph
      getScheduler().setModel(graph);
      getScheduler().schedule(comparator, monitor);

      List<ITransposerTask<Vertex<?>>> scheduledTasks = getScheduler().getScheduleResult();

      checkCancel(monitor);

      callPreExecutionActivities(scheduledTasks, configuration, monitor);

      checkCancel(monitor);

      // Execute computed tasks
      // getRulesHandler().setDefaultContext(defaultContext_p)

      if (monitor != null) {
        monitor.beginTask("Transposer Tasks Execution", scheduledTasks.size()); //$NON-NLS-1$
      }

      for (ITransposerTask<Vertex<?>> scheduledTask : scheduledTasks) {
        Vertex<?> v = scheduledTask.getTaskContent();
        try {
          getRulesHandler().apply(v.getContent(), scheduledTask.isCompletelyTransposable(), monitor);
          checkCancel(monitor);
        } catch (RuleExecutionException e) {
          TransposerCorePlugin.getDefault().logError(FrameworkUtil.getBundle(TransposerCorePlugin.class).getSymbolicName(),
              RuleExecutionException.class.getSimpleName() + " on " + e.getMessage(), e.getCause()); //$NON-NLS-1$
        }

        if (monitor != null) {
          monitor.worked(1);
        }
      }

      checkCancel(monitor);

      callPostExecutionActivities(configuration, monitor);

      checkCancel(monitor);

      if (monitor != null) {
        monitor.done();
      }

    } catch (OperationCanceledException e) {
      throw e;
    } catch (Exception e) {
      throw new TransitionException(e);
    }
  }

  /**
   * @param monitor
   */
  protected void checkCancel(IProgressMonitor monitor) throws Exception {
    if (monitor.isCanceled()) {
      IStatus status = statuses.get(monitor);

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

  @Override
  protected IRulesHandler getRulesHandler(String purpose, String mappingId) throws NonExistingPurposeException {
    return new ExtendedRulesHandler(purpose, mappingId);
  }

  /**
   * @param selection_p
   * @param analysisSources_p
   * @param graph
   * @param configuration
   */
  protected void callPreSchedulingActivities(Graph graph, TransposerConfiguration configuration, IProgressMonitor monitor) {
    if (configuration == null) {
      return;
    }
    _cadenceParameters.addParameter(new GenericParameter<Graph>(TRANSPOSER_ANALYSIS_GRAPH, graph, "Computed analysis graph")); //$NON-NLS-1$

    complementActivitiesParameters(configuration.getPreSchedulingActivities());

    try {
      cadence(TRANSPOSER_WORKFLOW, PRE_SCHEDULING, configuration.getPreSchedulingActivities(), monitor);
    } catch (Exception e) {
      throw new TransitionException(e);
    }
  }

  protected Set<?> getElements(Collection<ITransposerTask<Vertex<?>>> sortedTasks) {
    Set<Object> set = new LinkedHashSet<Object>();

    for (ITransposerTask<Vertex<?>> task : sortedTasks) {
      set.add(task.getTaskContent().getContent());
    }

    return set;

  }

  /**
   * 
   * 
   * @param sortedTasks
   * @param configuration
   */
  protected void callPreExecutionActivities(Collection<ITransposerTask<Vertex<?>>> sortedTasks, TransposerConfiguration configuration,
      IProgressMonitor monitor) {
    if (configuration == null) {
      return;
    }

    _cadenceParameters.addParameter(new GenericParameter<Collection<ITransposerTask<Vertex<?>>>>(TRANSPOSER_SORTED_TASKS, sortedTasks,
        "Computed and sorted tasks list")); //$NON-NLS-1$
    _cadenceParameters.addParameter(new GenericParameter<Set<?>>(TRANSPOSER_TRANSPOSABLE_OBJECTS, getElements(sortedTasks), "Transposable elements")); //$NON-NLS-1$

    complementActivitiesParameters(configuration.getPreExecutionActivities());

    try {
      cadence(TRANSPOSER_WORKFLOW, PRE_EXECUTION, configuration.getPreExecutionActivities(), monitor);
    } catch (Exception e) {
      throw new TransitionException(e);
    }
  }

  public IStatus cadence(final String workflow_id, final String workflowElement_id, final WorkflowActivityParameter workflowActivityParameters,
      final IProgressMonitor monitor) throws Exception {
    IStatus result = extendedCadenceLauncher.cadence(workflow_id, workflowElement_id, workflowActivityParameters, monitor);
    if (result.matches(IStatus.CANCEL) || result.matches(IStatus.ERROR)) {
      monitor.setCanceled(true);
      statuses.put(monitor, result);
    }
    return result;
  }

  /**
   * @param preAnalysisActivities_p
   */
  protected void complementActivitiesParameters(WorkflowActivityParameter activities) {
    if (activities == null) {
      return;
    }

    // Add business parameters to the map
    for (String key : activities.getActivitiesID()) { // key == activities_id
      if ((_cadenceParameters != null) && !_cadenceParameters.getParameters().isEmpty()) {
        for (GenericParameter<?> p : _cadenceParameters.getParameters()) {
          activities.addParameter(key, p);
        }
      }
    }

  }

  /**
   * @param selection_p
   * @param analysisSources_p
   * @param graph_p
   * @param configuration
   */
  protected void callPostExecutionActivities(TransposerConfiguration configuration, IProgressMonitor monitor) {
    if (configuration == null) {
      return;
    }

    complementActivitiesParameters(configuration.getPostExecutionActivities());

    try {
      cadence(TRANSPOSER_WORKFLOW, POST_EXECUTION, configuration.getPostExecutionActivities(), monitor);
    } catch (Exception e) {
      throw new TransitionException(e);
    }
  }

  /**
   * @param selection_p
   * @param analysisSources
   */
  protected void callPreAnalysisActivities(Collection<Object> selection, Collection<Object> analysisSources, TransposerConfiguration configuration,
      IProgressMonitor monitor) {
    if (configuration == null) {
      return;
    }

    _cadenceParameters.addParameter(new GenericParameter<Collection<Object>>(TRANSPOSER_INITIAL_SELECTION, selection,
        "List of selected elements before Transposer's launch")); //$NON-NLS-1$
    _cadenceParameters.addParameter(new GenericParameter<Collection<Object>>(TRANSPOSER_SELECTION, selection,
        "List of selected elements before Transposer's launch")); //$NON-NLS-1$
    _cadenceParameters.addParameter(new GenericParameter<Collection<Object>>(TRANSPOSER_ANALYSIS_SOURCES, analysisSources,
        "List to store object required to start the analysis")); //$NON-NLS-1$
    _cadenceParameters.addParameter(new GenericParameter<IContext>(TRANSPOSER_CONTEXT, _context, "Context used during rules execution")); //$NON-NLS-1$

    _cadenceParameters.addParameter(new GenericParameter<IDomainHelper>(TRANSPOSER_DOMAIN_HELPER, _rulesHandler.getDomainHelper(),
        "Domain Helper used by Transposer")); //$NON-NLS-1$

    complementActivitiesParameters(configuration.getPreAnalysisActivities());

    try {
      cadence(TRANSPOSER_WORKFLOW, PRE_ANALYSIS, configuration.getPreAnalysisActivities(), monitor);
    } catch (Exception e) {
      throw new TransitionException(e);
    }
  }
}
