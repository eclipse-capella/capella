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
package org.polarsys.capella.core.commandline.core;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

public class CommandLineApp implements IApplication {
  private static final String POINT = "org.polarsys.capella.core.commandline.core.commandline"; //$NON-NLS-1$

  public static final String PLUGIN_ID = "org.polarsys.capella.core.commandline.core"; //$NON-NLS-1$
  private Logger __logger;

  /**
   * {@inheritDoc}
   */
  @Override
  public Object start(IApplicationContext context_p) throws Exception {

    String[] args = CommandLineArgumentHelper.parseContext(context_p);
    CommandLineArgumentHelper.getInstance().parseArgs(args);
    __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DEFAULT);
    String appToLaunch = CommandLineArgumentHelper.getInstance().getAppid();

    if (null == appToLaunch) {
      StringBuilder loggerMessage = new StringBuilder(Messages.no_app_found + appToLaunch);
      __logger.warn(loggerMessage.toString(), new CommandLineException(loggerMessage.toString()));
      return IApplication.EXIT_RELAUNCH;
    }

    // instantiate contributions from the context
    IConfigurationElement[] configElt = Platform.getExtensionRegistry().getConfigurationElementsFor(POINT);

    ICommandLine app = geInstanceFromId(appToLaunch, configElt);
    if (null == app) {
      String loggerMessage = NLS.bind(Messages.unable_load_extension, new String[] { appToLaunch, POINT });
      __logger.warn(loggerMessage.toString(), new CommandLineException(loggerMessage.toString()));
      return IApplication.EXIT_RELAUNCH;
    }

    return launchApp(app, context_p);

  }

  /**
   * @param app_p
   */
  private Integer launchApp(ICommandLine app_p, IApplicationContext context_p) {
    Integer status = IApplication.EXIT_OK;
    try {

      app_p.parseContext(context_p);

      if (helpNeeded(context_p)) {
        app_p.printHelp();
        return status;
      }

      // precondition: check parameters validity
      app_p.checkArgs(context_p);

      // prepare execution (e.g. import project into a specified workspace)
      app_p.prepare(context_p);

      // call execute
      app_p.execute(context_p);

    } catch (CommandLineException exception_p) {
      __logger.error(exception_p.getMessage());
      status = IApplication.EXIT_RELAUNCH;

    } finally {

    }

    return status;

  }

  /**
   * @param context_p
   * @return
   */
  private boolean helpNeeded(IApplicationContext context_p) {
    return CommandLineArgumentHelper.getInstance().isHelpNeeded();
  }

  /**
   * @param configElt_p
   * @return
   */
  private ICommandLine geInstanceFromId(String id, IConfigurationElement[] configElt_p) {
    for (IConfigurationElement configElt : configElt_p) {
      String eltId = configElt.getAttribute(CommandLineExtensionConstants.ATT_ID);
      if (eltId.equals(id)) {
        try {
          ICommandLine obj = (ICommandLine) configElt.createExecutableExtension(CommandLineExtensionConstants.ATT_CLASS);
          return obj;
        } catch (CoreException exception_p) {
          StringBuilder loggerMessage = new StringBuilder(Messages.could_not_create_exec);
          __logger.error(loggerMessage);
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
  }

}
