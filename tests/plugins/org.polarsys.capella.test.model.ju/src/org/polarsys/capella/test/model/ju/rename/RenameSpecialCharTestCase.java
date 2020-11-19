/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.rename;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.helper.ResourceHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

public class RenameSpecialCharTestCase extends BasicTestCase {

  private static class RenameData {
    protected final String initialName;
    protected final String renamedName;
    protected final String folder;

    public RenameData(String initialName, String renamedName, String folder) {
      this.folder = folder;
      this.initialName = initialName;
      this.renamedName = renamedName;
    }

    public RenameData(String initialName, String renamedName) {
      this(initialName, renamedName, null);
    }
  }

  public static final String TEST_MODEL_NAME = "[rename model]";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(TEST_MODEL_NAME);
  }

  @Override
  public void test() throws Exception {

    List<RenameData> firstRenameData = Arrays.asList( //
        new RenameData("[rename model].capella", "superman.capella"), //
        new RenameData("[rename model].aird", "hulk.aird"), //
        new RenameData("[OA].airdfragment", "batman.airdfragment", "fragments"), //
        new RenameData("[OA].capellafragment", "hawkeye.capellafragment", "fragments"), //

        new RenameData("[rename model].capella", "-!@^()[].capella"), //
        new RenameData("[rename model].aird", "-!@^()[].aird"), //
        new RenameData("[OA].airdfragment", "-!@^()[].airdfragment", "fragments"), //
        new RenameData("[OA].capellafragment", "-!@^()[].capellafragment", "fragments") //
    );

    for (RenameData renameData : firstRenameData) {
      IProject project = IResourceHelpers.getEclipseProjectInWorkspace(TEST_MODEL_NAME);

      IFile initialFile = getInitialFile(project, renameData);
      GuiActions.renameModelFile(initialFile, renameData.renamedName);
      IFile renamedFile = getRenamedFile(project, renameData);

      assertRenamedFileExistence(renameData, renamedFile);

      assertValidSession(renamedFile);

      Collection<IFile> allProjectFiles = ResourceHelper.collectFiles(project);

      for (IFile childFile : allProjectFiles) {
        assertAllExpectedFilesRenamed(renameData, childFile);

        String childFileContent = FileHelper.readFile(childFile.getFullPath().toString());

        assertOldRawNameReferenceAbsence(renameData, initialFile, childFileContent);

        assertOldEncodedNameReferenceAbsence(renameData, initialFile, childFileContent);
      }

      GuiActions.renameModelFile(renamedFile, renameData.initialName);
    }
  }

  private void assertOldEncodedNameReferenceAbsence(RenameData renameData, IFile initialFile, String fileContent) {
    String encodedName = URI.encodeSegment(renameData.initialName, true);
    String fileContainsOldEncodedNameReferences = MessageFormat.format(
        "File {0} should should not contain old encoded references towards {1}", initialFile.getName(), encodedName);

    assertFalse(fileContainsOldEncodedNameReferences, fileContent.contains(encodedName));
  }

  private void assertOldRawNameReferenceAbsence(RenameData renameData, IFile initialFile, String fileContent) {
    String fileContainsOldRawNameReferences = MessageFormat.format(
        "File {0} should should not contain old raw references towards {1}", initialFile.getName(),
        renameData.initialName);

    assertFalse(fileContainsOldRawNameReferences, fileContent.contains(renameData.initialName));
  }

  private void assertAllExpectedFilesRenamed(RenameData renameData, IFile projectFile) {
    String fileNotRenamedMsg = MessageFormat.format("File {0} should have been renamed to {1}", projectFile.getName(),
        renameData.renamedName);

    assertFalse(fileNotRenamedMsg, projectFile.getName().equals(renameData.initialName));
  }

  private void assertValidSession(IFile renamedFile) {
    IFile airdFile = getAirdFile(renamedFile);
    URI airdFileUri = EcoreUtil2.getURI(airdFile);
    Session session = SessionManager.INSTANCE.getSession(airdFileUri, new NullProgressMonitor());

    assertNotNull(MessageFormat.format("Invalid Session for renamed file {0}", renamedFile.getName()), session);
    session.close(new NullProgressMonitor());
  }

  private void assertRenamedFileExistence(RenameData renameData, IFile renamedFile) {
    assertTrue(MessageFormat.format("File {0} should exist", renameData.renamedName), renamedFile.exists());
  }

  private IFile getAirdFile(IFile renamedFile) {
    return CapellaResourceHelper.AIRD_FILE_EXTENSION.equals(renamedFile.getFileExtension()) ? //
        renamedFile : //
        getAirdFileForLoadedModel(TEST_MODEL_NAME);
  }

  private IFile getInitialFile(IProject project, RenameData data) {
    return data.folder == null ? //
        project.getFile(data.initialName) : //
        project.getFolder(data.folder).getFile(data.initialName);
  }

  private IFile getRenamedFile(IProject project, RenameData data) {
    return data.folder == null ? //
        project.getFile(data.renamedName) : //
        project.getFolder(data.folder).getFile(data.renamedName);
  }

}
