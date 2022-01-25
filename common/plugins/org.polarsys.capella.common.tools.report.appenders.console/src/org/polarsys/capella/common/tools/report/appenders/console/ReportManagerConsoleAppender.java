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
package org.polarsys.capella.common.tools.report.appenders.console;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.console.MessageConsoleStream;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;

/**
 * A log4j appender that writes to an eclipse console. Logged events are queued for max 500ms an then bulk-written to
 * the console. Of course, if the console is blocked because the ui is blocked (e.g by a dialog), events may queue up
 * and cause memory usage peaks.
 */
public class ReportManagerConsoleAppender extends AppenderSkeleton {

  private static final String CONSOLE_APPENDER_JOB_FAMILY = "ConsoleAppenderJob";

  protected IReportConsole console;
  private volatile boolean running;
  private Map<Level, Writer> logWriters;

  public ReportManagerConsoleAppender() {
    this(new PatternLayout("%d{MM-dd HH:mm:ss} %-5.5p %m%n")); //$NON-NLS-1$
  }

  public ReportManagerConsoleAppender(Layout layout) {
    setLayout(layout);
    setName(ReportManagerConstants.LOG_OUTPUT_CONSOLE);
    console = ConsoleAppenderActivator.getDefault().getReportConsole();
    logWriters = new HashMap<>();
    if (console != null) {
      Map<Level, MessageConsoleStream> messageStreams = console.getOutputStreams();
      for (Map.Entry<Level, MessageConsoleStream> entry : messageStreams.entrySet()) {
        logWriters.put(entry.getKey(), new BufferedWriter(new OutputStreamWriter(entry.getValue())));        
      }
    }
    running = true;
  }

  @Override
  // Handle the event is an existing or new job.
  protected void append(LoggingEvent event) {
    ConsoleAppenderJob job = getAppenderJob();
    job.addLoggingEvent(event);
    job.schedule();
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

  private ConsoleAppenderJob getAppenderJob() {
    Job[] jobs = Job.getJobManager().find(CONSOLE_APPENDER_JOB_FAMILY);
    if (jobs.length != 0) {
      return (ConsoleAppenderJob) jobs[0];
    }
    return new ConsoleAppenderJob();
  }

  /*
   * Write to eclipse console from a separate Job.
   */
  class ConsoleAppenderJob extends Job {

    LinkedList<LoggingEvent> fifo = new LinkedList<>();

    public ConsoleAppenderJob() {
      super("Capella Console Appender");
    }

    @Override
    public boolean belongsTo(Object family) {
      return CONSOLE_APPENDER_JOB_FAMILY.equals(family);
    }

    public void addLoggingEvent(LoggingEvent event) {
      fifo.addLast(event);
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
      while (running && !fifo.isEmpty()) {
        writeToConsole(fifo.removeFirst());
      }
      return Status.OK_STATUS;
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
        Platform.getLog(ConsoleAppenderActivator.class).log(new Status(IStatus.ERROR, FrameworkUtil.getBundle(ConsoleAppenderActivator.class).getSymbolicName(), e.getMessage(), e));
      }
    }
  }
}
