/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.run;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jdt.internal.junit.model.ITestRunListener2;
import org.eclipse.jdt.internal.junit.model.RemoteTestRunnerClient;

public class ConsoleTestRunnerApplication implements IApplication {

  private static TestRunListener testListener;

  private void run(int port, String title) throws InterruptedException {
    testListener = new TestRunListener(this, title);
    RemoteTestRunnerClient client = new RemoteTestRunnerClient();
    client.startListening(new ITestRunListener2[] { testListener }, port);
    System.err.println("Listening on port " + port + " for test suite " + title + " results ...");
    System.out.println("Listening on port " + port + " for test suite " + title + " results ...");
    synchronized (this) {
      wait();
    }
  }
  
  @Override
  public Object start(IApplicationContext context) throws Exception {
    
    String[] args = Platform.getCommandLineArgs();

    System.out.println("");
    System.out.println("getCommandLineArgs");
    System.out.println(Arrays.asList(args).stream().collect(Collectors.joining("\n")));

    System.out.println("");
    System.out.println("getArguments");
    System.out.println(Arrays.asList(context.getArguments()).stream().map(x -> x.toString()).collect(Collectors.joining("\n")));

    String port = null;
    String title = "TestSuite";
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-port")) { //$NON-NLS-1$
        if (i < (args.length - 1)) {
          port = args[i + 1];
        }
      }
      if (args[i].equals("-title")) { //$NON-NLS-1$
        if (i < (args.length - 1)) {
          title = args[i + 1];
        }
      }
    }
    
    if (port == null) {
      System.out.println("usage: -port port -title title");
      System.exit(0);
    }

    try {
      run(Integer.parseInt(port), title);
    } catch (Throwable th) {
      th.printStackTrace();
    }

    return IApplication.EXIT_OK;
  }

  @Override
  public void stop() {
    
  }

}
