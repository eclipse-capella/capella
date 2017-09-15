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
package org.polarsys.capella.common.tools.report.appenders.console;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.console.MessageConsoleStream;

import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;

/**
 * A log4j appender that writes to an eclipse console. Logged events are queued for max 500ms an then bulk-written to
 * the console. Of course, if the console is blocked because the ui is blocked (e.g by a dialog), events may queue up
 * and cause memory usage peaks.
 */
public class ReportManagerConsoleAppender extends AppenderSkeleton {

  public Map<Level, Writer> logWriters;
  protected IReportConsole console;
  private Thread worker;
  private volatile boolean running;
  private final List<LoggingEvent> pending;

  public ReportManagerConsoleAppender(Layout layout) {
    setLayout(layout);
    setName(ReportManagerConstants.LOG_OUTPUT_CONSOLE);
    console = ConsoleAppenderActivator.getDefault().getReportConsole();
    logWriters = new HashMap<Level, Writer>();
    if (console != null) {
      Map<Level, MessageConsoleStream> messageStreams = console.getOutputStreams();
      for (Level lev : messageStreams.keySet()) {
        logWriters.put(lev, new BufferedWriter(new OutputStreamWriter(messageStreams.get(lev))));
      }
    }
    running = true;
    pending = Collections.synchronizedList(new ArrayList<LoggingEvent>());
    worker = new Thread(null, new ConsoleAppenderJob(), "ReportManagerConsoleAppender"); //$NON-NLS-1$
    worker.start();
  }

  public ReportManagerConsoleAppender() {
    this(new PatternLayout("%d{MM-dd HH:mm:ss} %-5.5p %m%n")); //$NON-NLS-1$
  }

  @Override
  // handle event in the future
  protected void append(LoggingEvent event) {
    pending.add(event);
  }

  /*
   * Write to eclipse console from a separate thread.
   */
  class ConsoleAppenderJob implements Runnable {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("synthetic-access")
    public void run() {
      while (running) {
        if (!pending.isEmpty()) {
          List<LoggingEvent> pendingCopy = null;
          synchronized (pending) {
            pendingCopy = new ArrayList<LoggingEvent>(pending);
            pending.clear();
          }
          for (LoggingEvent e : pendingCopy) {
            writeToConsole(e);
          }
        }
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {

        }
      }
    }
  }

  private void writeToConsole(LoggingEvent event) {
    try {
      Writer writer = logWriters.get(event.getLevel());
      if (writer != null) {
        writer.write(" [From " + event.getLoggerName() + "] "); //$NON-NLS-1$ //$NON-NLS-2$
        writer.write(layout.format(event));
        if (layout.ignoresThrowable()) {
          String[] s = event.getThrowableStrRep();
          if (s != null) {
            int len = s.length;
            for (int i = 0; i < len; i++) {
              writer.write(s[i]);
              writer.write(Layout.LINE_SEP);
            }
          }
        }
      }
    } catch (IOException e) {
      ConsoleAppenderActivator.getDefault().getLog()
          .log(new Status(IStatus.ERROR, ConsoleAppenderActivator.PLUGIN_ID, e.getMessage(), e));
    }
  }

  /**
   * {@inheritDoc}
   */
  public void close() {
    running = false;
  }

  /**
   * {@inheritDoc}
   */
  public boolean requiresLayout() {
    return true;
  }

}
