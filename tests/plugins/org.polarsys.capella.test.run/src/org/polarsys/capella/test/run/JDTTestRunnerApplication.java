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
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.Launch;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jdt.internal.junit.model.ITestSessionListener;
import org.eclipse.jdt.internal.junit.model.TestCaseElement;
import org.eclipse.jdt.internal.junit.model.TestElement;
import org.eclipse.jdt.internal.junit.model.TestElement.Status;
import org.eclipse.jdt.internal.junit.model.TestRunListenerAdapter;
import org.eclipse.jdt.internal.junit.model.TestRunSession;

/**
 * A draft based on JDT junit model elements. Duplicated id for tests doesn't seems to work.
 */
public class JDTTestRunnerApplication implements IApplication {

  private static TestRunListener testListener;

  private void run(int port, String title) throws InterruptedException {

    ILaunch launch = new Launch(null, ILaunchManager.RUN_MODE, null);
    final TestRunSession session = new TestRunSession(launch, new JavaProject() {
      @Override
      public String getElementName() {
        return title;
      }
    }, port);
    session.addTestSessionListener(new TestRunListenerAdapter(session));
    session.addTestSessionListener(new ITestSessionListener() {

      @Override
      public void testStarted(TestCaseElement testCaseElement) {
        // Nothing
      }

      @Override
      public void testReran(TestCaseElement testCaseElement, Status status, String trace, String expectedResult,
          String actualResult) {

      }

      @Override
      public void testFailed(TestElement testElement, Status status, String trace, String expected, String actual) {
        // Nothing

      }

      @Override
      public void testEnded(TestCaseElement testCaseElement) {
        // Nothing

      }

      @Override
      public void testAdded(TestElement testElement) {
        // Nothing

      }

      @Override
      public void sessionTerminated() {
        System.out.println(session);
      }

      @Override
      public void sessionStopped(long elapsedTime) {
        JDTTestRunnerApplication.this.notifyAll();
      }

      @Override
      public void sessionStarted() {
        System.out.println(session);

      }

      @Override
      public void sessionEnded(long elapsedTime) {
        System.out.println(session);
        JDTTestRunnerApplication.this.notifyAll();
      }

      @Override
      public void runningBegins() {

      }

      @Override
      public boolean acceptsSwapToDisk() {
        return false;
      }
    });

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
    System.out.println(
        Arrays.asList(context.getArguments()).stream().map(x -> x.toString()).collect(Collectors.joining("\n")));

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
