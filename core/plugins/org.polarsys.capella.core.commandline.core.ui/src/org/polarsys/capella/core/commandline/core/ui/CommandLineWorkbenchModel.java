/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.commandline.core.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

/**
 * Register to the APP_STARTUP_COMPLETE event. When a command line using the workbench is launched, then we run it after
 * the startup is fully finished
 */
public class CommandLineWorkbenchModel {

  @Execute
  void process(IEventBroker broker) {
    broker.subscribe(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE, new EventHandler() {

      @Override
      public void handleEvent(Event event) {
        broker.unsubscribe(this);
        Object data = event.getProperty(IEventBroker.DATA);
        if (data instanceof MApplication) {
          MApplication app = (MApplication) event.getProperty(IEventBroker.DATA);
          IEclipseContext context = app.getContext();
          if (context.containsKey(AbstractWorkbenchCommandLine.class)) {
            AbstractWorkbenchCommandLine wcl = (AbstractWorkbenchCommandLine) context
                .get(AbstractWorkbenchCommandLine.class);
            if (wcl != null) {
              IStatus status = wcl.executeWithinWorkbench();
              wcl.setStatus(status);
            }
          }
        }
      }
    });
  }
}
