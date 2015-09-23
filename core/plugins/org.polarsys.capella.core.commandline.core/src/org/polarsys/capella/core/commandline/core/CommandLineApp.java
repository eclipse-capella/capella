/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

    String[] args = CommandLineArgumentHelper.parseContext(context);
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

    return launchApp(app, context);

  }

  /**
   * @param app
   */
  private Integer launchApp(ICommandLine app, IApplicationContext context) {
    Integer status = IApplication.EXIT_OK;
    try {

      app.parseContext(context);

      if (helpNeeded(context)) {
        app.printHelp();
        return status;
      }

      // precondition: check parameters validity
      app.checkArgs(context);

      // prepare execution (e.g. import project into a specified workspace)
      app.prepare(context);

      // call execute
      app.execute(context);

    } catch (CommandLineException exception) {
      __logger.error(exception.getMessage());
      status = IApplication.EXIT_RELAUNCH;

    } finally {

    }

    return status;

  }

  /**
   * @param context
   * @return
   */
  private boolean helpNeeded(IApplicationContext context) {
    return CommandLineArgumentHelper.getInstance().isHelpNeeded();
  }

  /**
   * @param inputConfigElt
   * @return
   */
  private ICommandLine geInstanceFromId(String id, IConfigurationElement[] inputConfigElt) {
    for (IConfigurationElement configElt : inputConfigElt) {
      String eltId = configElt.getAttribute(CommandLineExtensionConstants.ATT_ID);
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
