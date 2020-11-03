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
package org.polarsys.capella.core.transition.common.ui.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeColumn;

public class CSVExporter
{

   private static final Logger logger = Logger.getLogger(CSVExporter.class.getName());
   
   private static final String[] FILTER_NAMES = { "Comma Separated Values Files (*.csv)", "All Files (*.*)" };

   private static final String[] FILTER_EXTS = { "*.csv", "*.*" };

   private PrintWriter resultFile;

   private TreeViewer diffviewer;

   public CSVExporter(Shell shell, TreeViewer diffviewer)
   {
      FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
      fileDialog.setFilterNames(FILTER_NAMES);
      fileDialog.setFilterExtensions(FILTER_EXTS);
      fileDialog.setText("Choose file to save the differences");

      fileDialog.setFileName("DifferencesLog");

      String myResultFile = fileDialog.open();

      this.diffviewer = diffviewer;

      // If operation not canceled
      if (myResultFile != null)
      {
         createFile(myResultFile);

         exportFromViewer();
      }
   }

   /**
    * Export result from viewer
    * 
    * @param resultCVSFile
    */
   private void exportFromViewer()
   {

      int nbColumn = diffviewer.getTree().getColumnCount();

      // Header
      for (TreeColumn treeColumn : diffviewer.getTree().getColumns())
      {
         if (/* treeColumn.getResizable() && */treeColumn.getWidth() != 0)
         {
            writeField(treeColumn.getText());
         }
      }

      writeNewLine();

      // For each entry line
      for (int i = 0; i < diffviewer.getTree().getItemCount(); i++)
      {
         for (int j = 0; j < nbColumn; j++)
         {
            TreeColumn treeColumn = diffviewer.getTree().getColumns()[j];
            if (treeColumn.getWidth() != 0)
            {
               String string = diffviewer.getTree().getItem(i).getText(j);

               writeField(string);
            }
         }

         writeNewLine();

      }

      resultFile.close();
   }

  private void createFile(String resultCVSFile) {
    try (FileWriter csvFileWriter = new FileWriter(resultCVSFile)) {
      File errorFile = new File(resultCVSFile);
      if (errorFile.exists()) {
        errorFile.delete();
      }

      resultFile = new PrintWriter(new BufferedWriter(csvFileWriter));
    } catch (FileNotFoundException e) {
      MessageBox lMessageBox = new MessageBox(new Shell(), SWT.OK);
      lMessageBox.setText("Write EXCEL file");
      lMessageBox.setMessage("Write operation failed \n" + e.getMessage());
      lMessageBox.open();
    } catch (IOException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }

   /**
    * writeField Write a String followed by a ";"
    * 
    * @param pField
    */
   public void writeField(String pField)
   {
      String dotComma = ";";

      if (pField == null)
      {
         resultFile.print("");
      }
      else
      {
         resultFile.print(pField);
      }
      resultFile.print(dotComma);
   }

   /**
    * writeNewLine Write a new line followed by a ";"
    */
   public void writeNewLine()
   {
      resultFile.println();
   }

}
