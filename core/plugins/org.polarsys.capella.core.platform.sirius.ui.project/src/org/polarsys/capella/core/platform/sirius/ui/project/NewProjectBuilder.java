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
package org.polarsys.capella.core.platform.sirius.ui.project;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateCapellaProjectCmd;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.command.AbstractNonDirtyingCommand;

/**
 * FIXME this is accidentally committed work in progress. don't use.
 */
class NewProjectBuilder extends NewProjectWizard {

  private String eclipseProjectName;
  
  private ProjectApproach approach;
  
  private Logger logger;
  
  private Set<? extends Viewpoint> viewpoints;
  
  private Result result;
  
  public void setLogger(Logger logger_p){
    logger = logger_p;
  }
  
  public void setEclipseProjectName(String name_p){
    eclipseProjectName = name_p;
  }
  
  public void setProjectApproach(ProjectApproach approach_p){
    approach = approach_p;
  }
 
  public void setViewpoints(Set<? extends Viewpoint> viewpoints_p){
    viewpoints = viewpoints_p;
  }
  
  public void finish(IRunnableContext context){
    IRunnableWithProgress runnable = new IRunnableWithProgress(){
      @SuppressWarnings("synthetic-access")
      public void run(IProgressMonitor monitor_p){
        try {
          int stepCount = 7;
          SubMonitor progress =
              SubMonitor.convert(monitor_p, Messages.getString("NewProjectWizard.CreateProject_Title") + getEclipseProjectName(), STEP_TICK_COUNT * stepCount); //$NON-NLS-1$
          // 1 - Creates the Eclipse Project with the Capella Project nature.
          IProject eclipseProject = null;
          try {
            eclipseProject = createNewEclipseProject(getEclipseProjectName(), progress.newChild(STEP_TICK_COUNT));
          } catch (Exception ex) {
            getLoggerImpl().debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.MODEL));
          }

          // 2 - Creates the Capella project.
          Project capellaProject = createCapellaProject(eclipseProject, progress.newChild(STEP_TICK_COUNT));
          if (null != capellaProject) {
            // 3 - Creates the default skeleton structure of the Capella model.
            createCapellaModelSkeleton(capellaProject, progress.newChild(STEP_TICK_COUNT));

            // 4- Creates the aird resource.
            _session = createAirdResource(eclipseProject, getEclipseProjectName(), progress.newChild(STEP_TICK_COUNT));
            if (null == _session) {
              throw new InterruptedException("Cannot create the session"); //$NON-NLS-1$
            }

            final Iterator<? extends Viewpoint> viewpointIterator = getViewpointsImpl().iterator();
            // Select the viewpoints in TED way as of Sirius 4.15
            MDEAdapterFactory.getExecutionManager().execute(new AbstractNonDirtyingCommand() {
              /**
               * @see java.lang.Runnable#run()
               */
              public void run() {
                ViewpointSelectionCallback viewpointSelectionCallback = new ViewpointSelectionCallback();
                // Activate all viewpoints
                while (viewpointIterator.hasNext()) {
                  Viewpoint viewpoint = viewpointIterator.next();
                  viewpointSelectionCallback.selectViewpoint(viewpoint, _session);
                }
              }
            });
            progress.worked(STEP_TICK_COUNT);

            result = new Result(eclipseProject);
          }
        } catch (InvocationTargetException exception_p) {
          StringBuilder loggerMessage = new StringBuilder(".run(..) _ "); //$NON-NLS-1$
          getLoggerImpl().warn(loggerMessage.toString(), exception_p);
        } catch (InterruptedException exception_p) {
          StringBuilder loggerMessage = new StringBuilder(".run(..) _ "); //$NON-NLS-1$
          getLoggerImpl().warn(loggerMessage.toString(), exception_p);
        } finally {
          monitor_p.done();
        }
      }
    };
    
    try {
      context.run(false, false, runnable);
    } catch (InvocationTargetException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("NewProjectBuilder.finish(..) _ "); //$NON-NLS-1$
      getLoggerImpl().warn(loggerMessage.toString(), exception_p);
    } catch (InterruptedException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("NewProjectBuilder.finish(..) _ "); //$NON-NLS-1$
      getLoggerImpl().warn(loggerMessage.toString(), exception_p);
    }
  }
  
  protected Logger getDefaultLogger(){
    return Logger.getRootLogger();
  }
  
  @Override
  public String getEclipseProjectName(){
    return eclipseProjectName;
  }
  
  private Logger getLoggerImpl(){
    if (logger != null){
      return logger;
    }
    return getDefaultLogger();
  }
  
  public Logger getLogger(){
    return logger;
  }
  
  public Set<? extends Viewpoint> getViewpoints(){
    return viewpoints;
  }
  
  private Set<? extends Viewpoint> getViewpointsImpl(){
    if (viewpoints != null){
      return viewpoints;
    }
    return getDefaultViewpoints();
  }
  
  protected Set<? extends Viewpoint> getDefaultViewpoints(){
    return ViewpointSelection.getViewpoints(CreateCapellaProjectCmd.XMI_EXTENSION);
  }
  
  @Override
  public ProjectApproach getProjectApproach(){
    return approach;
  }
  
  class Result {
    final IProject eclipseProject;
    
    private Result(IProject eclipseProject_p){
      eclipseProject = eclipseProject_p;
    }

    public IProject getProject(){
      return eclipseProject;
    }
  }
}
