/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.commandline.core;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.common.tools.report.config.persistence.ConfigurationInstance;
import org.polarsys.capella.common.tools.report.config.persistence.LogLevel;
import org.polarsys.capella.common.tools.report.config.persistence.OutputConfiguration;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.application.appstart.AbstractApplication;

public class CommandLineApp extends AbstractApplication {
  private static final String POINT = "org.polarsys.capella.core.commandline.core.commandline"; //$NON-NLS-1$

  public static final String PLUGIN_ID = "org.polarsys.capella.core.commandline.core"; //$NON-NLS-1$
  private Logger __logger;

  /**
   * {@inheritDoc}
   */
  @Override
  public Object start(IApplicationContext context) throws Exception {
    super.start(context);

    // Parse command line arguments
    String[] args = CommandLineArgumentHelper.parseContext(context);
    CommandLineArgumentHelper.getInstance().parseArgs(args);
    
    // Configure logger
    configureReportManagerForCommandLine();
    __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DEFAULT);
    String appToLaunch = CommandLineArgumentHelper.getInstance().getAppid();

    if (null == appToLaunch) {
      String loggerMessage = Messages.no_app_found + CommandLineArgumentHelper.getInstance().getAppid();
      __logger.warn(loggerMessage, new CommandLineException(loggerMessage));
      return IApplication.EXIT_RELAUNCH;
    }

    // instantiate contributions from the context
    IConfigurationElement[] configElt = Platform.getExtensionRegistry().getConfigurationElementsFor(POINT);

    ICommandLine app = geInstanceFromId(appToLaunch, configElt);
    if (null == app) {
      String loggerMessage = NLS.bind(Messages.unable_load_extension, new String[] { appToLaunch, POINT });
      __logger.warn(loggerMessage, new CommandLineException(loggerMessage));
      return IApplication.EXIT_RELAUNCH;
    }

    return launchApp(app, context);

  }

  /**
   * Deactivate all logs that are not linked to "File" appender.<br>
   * For "File" + "Console" appender, activate all log levels except DEBUG.
   */
  private void configureReportManagerForCommandLine() {
    Collection<ConfigurationInstance> configurationInstances =  ReportManagerRegistry.getInstance().getConfigurations().values();
    for (ConfigurationInstance confInstance : configurationInstances) {
      List<OutputConfiguration> outputConfigurations = confInstance.getOutputConfiguration();
      for (OutputConfiguration outputConfiguration : outputConfigurations) {
        // Activate all log levels (except DEBUG) for FILE and CONSOLE appenders - Deactivate all others
        if (outputConfiguration.getOutputName() == ReportManagerConstants.LOG_OUTPUT_FILE || outputConfiguration.getOutputName() == ReportManagerConstants.LOG_OUTPUT_CONSOLE) {
          for (LogLevel ll : outputConfiguration.getLogLevel()) {
            if (ll.getName() != ReportManagerConstants.LOG_LEVEL_DEBUG) {
              ll.setValue(true);
            }
          }    
        } else {
          for (LogLevel ll : outputConfiguration.getLogLevel()) {
            ll.setValue(false);
          }
        }
      }
    }
  }
  
  /**
   * @throws CommandLineException
   */
  private void isWorkspaceInUse() throws CommandLineException {
    try {
      if (!Platform.getInstanceLocation().lock()) {
        throw new CommandLineException(Messages.workspace_in_use);
      }
    } catch (IOException exception) {
      String loggerMessage = exception.getMessage();
      __logger.error(loggerMessage, exception);
    }

  }
  
  /**
   * @param app
   */
  private Integer launchApp(ICommandLine app, IApplicationContext context) {
    Integer status = IApplication.EXIT_OK;
    try {
      isWorkspaceInUse();

      app.parseContext(context);

      if (helpNeeded()) {
        app.printHelp();
        return status;
      }
      
      // prepare execution (e.g. import project into a specified workspace)
      app.prepare(context);

      // precondition: check parameters validity
      app.checkArgs(context);

      // call execute
      app.execute(context);

      // post execute
      app.postExecute(context);
      
    } catch (CommandLineException exception) {
      __logger.error(exception.getMessage());
      status = IApplication.EXIT_RELAUNCH;

    }
    return status;
  }

  /**
   * @param context
   * @return
   */
  private boolean helpNeeded() {
    return CommandLineArgumentHelper.getInstance().isHelpNeeded();
  }

  /**
   * @param inputConfigElt
   * @return
   */
  private ICommandLine geInstanceFromId(String id, IConfigurationElement[] inputConfigElt) {
    for (IConfigurationElement configElt : inputConfigElt) {
      String eltId = configElt.getAttribute(CommandLineExtensionConstants.ATT_ID);
      if(null != eltId){
    	  if (eltId.equals(id)) {
    	        try {
    	          ICommandLine obj = (ICommandLine) configElt
    	              .createExecutableExtension(CommandLineExtensionConstants.ATT_CLASS);
    	          return obj;
    	        } catch (CoreException exception) {
    	          StringBuilder loggerMessage = new StringBuilder(Messages.could_not_create_exec);
    	          __logger.error(loggerMessage);
    	        }
    	  }  
      } 
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void stop() {
    super.stop();
  }

}
