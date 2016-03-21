/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.command.recorder.core.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FilenameFilter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.polarsys.capella.common.command.recorder.core.preferences.RecorderCorePreferenceServices;
import org.polarsys.capella.common.command.recorder.core.recorder.IRecorder;

/**
 * Utility class for file manager
 */
public class OutputHelper {

  /** records file extension */
  public final static String LOG_EXT = ".log"; //$NON-NLS-1$

  /** system dependent file separator char */
  public final static String FILE_SEPARATOR = System.getProperty("file.separator"); //$NON-NLS-1$

  /**
   * Returns a Writer for the given OutputStream
   * @param output an OutputStream to use for the Writer
   * @return a Writer for the given OutputStream
   */
  static public Writer logForStream(OutputStream output) {
    try {
      return new BufferedWriter(new OutputStreamWriter(output, "UTF-8")); //$NON-NLS-1$
    } catch (UnsupportedEncodingException e) {
      return new BufferedWriter(new OutputStreamWriter(output));
    }
  }

  /**
   * Return all records files for a given recorder
   * @param recorder the target {@link Recorder}.
   * @return a sorted (from the newer to the older one) list of record files, an empty ones whether no records was found.
   */
  static public List<File> getRecordFiles(IRecorder recorder) {
    return getRecordFiles(getDir(recorder));
  }

  /**
   * Return all records files in a given directory
   * @param dir the target directory
   * @return a sorted (from the newer to the older one) list of record files, an empty ones whether no records was found.
   */
  public static List<File> getRecordFiles(final File dir) {

    List<File> result = new ArrayList<File>();

    if (isDirectoryOk(dir)) {
      FilenameFilter filter = new FilenameFilter() {
        @SuppressWarnings("synthetic-access")
        public boolean accept(File directory, String name) {
          return (
              isDirectoryOk(directory) && 
              null != name && 
              name.endsWith(LOG_EXT)
          );
        }
      };

      File[] files = dir.listFiles(filter);
      if ( null != files && 0 < files.length ) {
        result.add(files[0]);
        File file = null;

        for (int i = 1; i < files.length; i++) {
          file = files[i];
          int index = -1;
          for (int j = 0; j < result.size(); j++) {
            if (!isOlder(file, result.get(j))) {
              index = j;
              break;
            }
          }

          if (-1 != index) {
            result.add(index, file);
          } else {
            result.add(file);
          }
        }

      }
    }

    return result;

  }

  /**
   * Return the list of deprecated record files in a given directory.
   * @param dir the target directory
   * @return
   */
  public static List<File> getDeprecatedRecordFiles(File dir) {

    List<File> result = new ArrayList<File>();

    Date date = new Date();

    List<File> files = getRecordFiles(dir);
    int sz = files.size();
    Iterator<File> it = files.iterator();

    int idx = 0;
    File current = null;
    while (it.hasNext()) {
      current = it.next();
      if (isOutOfTime(current, date)) {
        break;
      }
      idx++;
    }

    if (idx < sz) {
      result.addAll(files.subList(idx, sz));
    }

    return result;
  }

  /**
   * Check whether a given {@link File} is too old
   * @param file the given file
   * @param refDate the reference date. Whether <code>null</code>, the execution time is take
   * @return <code>true</code> if the file is not a valid one or <code>null</code>.
   */
  public static boolean isOutOfTime(File file, Date refDate) {

    boolean result = true;

    if (null == file || !file.exists()) {
      result = true;
    } else {
      Date date = (null == refDate ? new Date() : refDate);
      long ref = date.getTime() - (RecorderCorePreferenceServices.getHistoryDelay() * 1000 * 24 * 3600);
      result = file.lastModified() < ref;
    }

    return result;
  }

  /**
   * Return the root directory used for history storage purpose
   */
  public static File getRootDirectoryForStorage() {
    return new File(RecorderCorePreferenceServices.getRootRecordPath());
  }

  /**
   * Return the directory for a given recorder or, whether no recorder is given as input, the root directory
   * @param recorder the target recorder or <code>null</code>
   * @return
   */
  public static File getDir(IRecorder recorder) {

    String path = RecorderCorePreferenceServices.getRootRecordPath();
    
    if (
        null != recorder && 
        null != recorder.getRelativePath() &&
        0 != recorder.getRelativePath().length()
    ) {
      path += FILE_SEPARATOR + recorder.getRelativePath();
    }

    return new File(path);
  }

  /** check whether a file exceed the upper allowed size for records */
  public static boolean isOverSized(File file) {

    // in bytes
    long max = RecorderCorePreferenceServices.getMaxFileSize() * 1024 * 1024;

    long sz = file.length(); // in bytes

    return (sz >= max);
  }

  /**
   * Create a new log file for a given recorder
   * @param recorder
   * @return
   */
  public static File createNewLogFile(IRecorder recorder) {
    File result = null;

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm"); //$NON-NLS-1$

    String pathname = getDir(recorder).getPath() + FILE_SEPARATOR + dateFormat.format(new Date()) + LOG_EXT;

    result = new File(pathname);

    return result;
  }

  /**
   * Remove all deprecated records in a given directory
   * @param dir the target dir
   */
  public static void cleanUpStorageArea(File dir) {
    deleteFiles(getDeprecatedRecordFiles(dir));
    return;
  }

  /**
   * remove all deprecated records for theses recorders
   * @param recorders the target recorders
   */
  public static void cleanUpStorageAreaForRecords(Set<? extends IRecorder> recorders) {

    List<File> allDirectories = getDirectSubDirectory(getRootDirectoryForStorage());

    // clean up recorders
    File dir = null;
    for (IRecorder recorder : recorders) {
      dir = getDir(recorder);
      if (allDirectories.contains(dir)) {
        allDirectories.remove(dir);
      }
      cleanUpStorageArea(dir);
    }

    return;
  }

  /**
   * Remove all the given log file in a given directory
   * @param dir the target directory
   * @param deleteFolder
   * @return <code>true</code> whether all is ok, <code>false</code> otherwise
   */
  public static boolean cleanAllRecordsFile(File dir, boolean deleteFolder) {

    boolean result = false;

    if (isDirectoryOk(dir)) {
      if (true == deleteFolder) {
        result = dir.delete();
      } else {
        deleteFiles(getRecordFiles(dir)); // records
      }
    }

    return result;

  }

  /**
   * Return all direct sub-directory for a given directory
   * @param dir the target directory
   * @return an empty {@link List} whether the target directory is not valid or whether it has no sub directory
   */
  public static List<File> getDirectSubDirectory(final File dir) {

    List<File> result = new ArrayList<File>();

    if (isDirectoryOk(dir)) {
      File[] files = dir.listFiles();
      File current = null;
      for (int i = 0; i < files.length; i++) {
        current = files[i];
        if (isDirectoryOk(current)) {
          result.add(current);
        }
      }
    }

    return result;
  }

  /**
   * Delete files.
   * @param files
   * @return
   */
  public static boolean deleteFiles(List<File> files) {
    return (files == null || files.isEmpty()) ? true : deleteFiles(files.toArray(new File[] {}));
  }

  /**
   * Delte files
   * @param files
   * @return
   */
  public static boolean deleteFiles(File[] files) {
    boolean result = true;

    if (null == files) {
      result = false;
    } else {
      for (int i = 0; i < files.length; i++) {
        result &= deleteFile(files[i]);
      }
    }

    return result;
  }

  /**
   * Delete file.
   * @param file
   * @return
   */
  public static boolean deleteFile(File file) {
    
    if (null != file && file.exists()) {
      if (file.isDirectory()) {
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
          if (files[i].isDirectory()) {
            deleteFile(files[i]);
          } else {
            files[i].delete();
          }
        }
      }
      file.delete();
    }
    
    return true;
  }

  /** For internal use */
  private static boolean isDirectoryOk(File dir) {
    return (null != dir && dir.exists() && dir.isDirectory());
  }

  /** For internal use */
  private static boolean isOlder(File file1, File file2) {
    return file1.lastModified() < file2.lastModified();
  }

}
