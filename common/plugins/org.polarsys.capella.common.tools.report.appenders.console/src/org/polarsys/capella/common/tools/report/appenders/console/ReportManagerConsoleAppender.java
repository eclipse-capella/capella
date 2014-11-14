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
 * A log4j appender that writes to an eclipse console. Logged 
 * events are queued for max 500ms an then bulk-written to the
 * console. Of course, if the console is blocked because 
 * the ui is blocked (e.g by a dialog), events may queue up 
 * and cause memory usage peaks.
 * 
 */
public class ReportManagerConsoleAppender extends AppenderSkeleton {

  public Map<Level, Writer> _logWriters;
  protected IReportConsole _console;
  private Thread _worker;
  private volatile boolean _running;
  private final List<LoggingEvent> _pending;
  
  public ReportManagerConsoleAppender(Layout layout_p) {
	  setLayout(layout_p);
	  setName(ReportManagerConstants.LOG_OUTPUT_CONSOLE);
	  _console = ConsoleAppenderActivator.getDefault().getReportConsole();
	  _logWriters = new HashMap<Level, Writer>();
	  if (_console != null) {
	    Map<Level, MessageConsoleStream> messageStreams = _console.getOutputStreams();
		  for (Level lev : messageStreams.keySet()){
		    _logWriters.put(lev, new BufferedWriter(new OutputStreamWriter(messageStreams.get(lev))));
		  }
	  }
	  _running = true;
	  _pending = Collections.synchronizedList(new ArrayList<LoggingEvent>());
	  _worker = new Thread(null, new ConsoleAppenderJob(), "ReportManagerConsoleAppender"); //$NON-NLS-1$
    _worker.start();
  }

  public ReportManagerConsoleAppender() {
    this(new PatternLayout("%d{MM-dd HH:mm:ss} %-5.5p %m%n")); //$NON-NLS-1$
  }

  @Override
  // handle event in the future
  protected void append(LoggingEvent event) {
    _pending.add(event);
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
      while (_running) {
        if (!_pending.isEmpty()){
          List<LoggingEvent> pendingCopy = null;
          synchronized (_pending){
            pendingCopy = new ArrayList<LoggingEvent>(_pending);
            _pending.clear();
          }
          for (LoggingEvent e : pendingCopy) {
            writeToConsole(e);
          }
        }
        try {
          Thread.sleep(500);
        } catch (InterruptedException e){
          
        }
      }
    }
  }
  
  private void writeToConsole(LoggingEvent event){
    try {
      Writer writer = _logWriters.get(event.getLevel());
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
    } catch (IOException e) {
      ConsoleAppenderActivator.getDefault().getLog().log(
          new Status(IStatus.ERROR, ConsoleAppenderActivator.PLUGIN_ID, e.getMessage(), e));
    }
  }

  /**
   * {@inheritDoc}
   */
  public void close() {
    _running = false;
  }

  /**
   * {@inheritDoc}
   */
  public boolean requiresLayout() {
    return true;
  }
  
}
