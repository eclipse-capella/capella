/*******************************************************************************
 * Copyright (c) 2019, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.migration.ju.testcases.basic;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.image.ImageManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.data.migration.contribution.ImagePathInRichTextAttributeContribution;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.migration.ju.helpers.MigrationHelper;

/**
 * This test check the {@link ImagePathInRichTextAttributeContribution} class.
 * 
 * @author lfasani
 */
public class ImagePathInRichTextAttributeMigrationTest extends BasicTestCase {
  private static final String CAPELLA_PNG = "capella.png";

  private static final String SOURCE_MODEL = "ImagePath InRichTextAttribute";

  String HTML_IMAGE_PATH_PATTERN_HTTP = "<img.*?src=\"https?://(.*?)\".*?/>"; //$NON-NLS-1$

  String HTML_IMAGE_PATH_PATTERN_BASE64 = "<img.*?src=\"" + SOURCE_MODEL + "/images/([0-9]{8}_.*?)\".*?/>"; //$NON-NLS-1$

  String HTML_IMAGE_PATH_PATTERN_COPIED = "<img.*?src=\"(" + SOURCE_MODEL + "/images/capella[0-9]*?\\.png)\".*?/>"; //$NON-NLS-1$

  String HTML_MIGRATED_NOT_EXISTING_IMAGE_RELATIVE_PATH = SOURCE_MODEL + "/RELATIVE_IMAGE_NOT_FOUND.png"; //$NON-NLS-1$
  String HTML_NOT_MIGRATED_NOT_EXISTING_IMAGE_RELATIVE_PATH = "FOLDEROROTHERPROJECT/RELATIVE_IMAGE_NOT_FOUND.png"; //$NON-NLS-1$
  String HTML_MIGRATED_EXISTING_IMAGE_RELATIVE_PATH_PATTERN = "<img.*?src=(\"" + SOURCE_MODEL //$NON-NLS-1$
      + "/capella\\.png\").*?/>";
  String HTML_NOT_MIGRATED_NOT_EXISTING_IMAGE_RELATIVE_PATH_PATTERN = "<img.*?src=(\""
      + HTML_NOT_MIGRATED_NOT_EXISTING_IMAGE_RELATIVE_PATH + "\").*?/>";

  String HTML_IMAGE_INVALID_ABSOLUTE_PATH = "C:\\INVALID\\PATH\\IMAGE.png"; //$NON-NLS-1$

  private static final String OA_ELEMENT_ID = "b302cb2c-9ebb-4a79-a1cb-2cf8e46fe51b";

  private IProject sourceModelProject;

  private ILogListener logListener;

  private List<IStatus> statuses = new ArrayList<>();

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(SOURCE_MODEL);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    sourceModelProject = IResourceHelpers.getEclipseProjectInWorkspace(SOURCE_MODEL);

    logListener = new ILogListener() {
      @Override
      public void logging(IStatus status, String plugin) {
        statuses.add(status);
      }
    };
    Platform.addLogListener(logListener);
  }

  @Override
  public void test() throws Exception {
    if (sourceModelProject.exists()) {
      assertTrue("Bad test data: there should be no images folder",
          Arrays.asList(sourceModelProject.members()).stream().filter(IFolder.class::isInstance).count() == 0);

      // migrate the project
      migrateAndCheckFirstTime();

      // change the absolute path so that the absolute path references the existing image dinosaur in the project.
      Session session = getSessionForTestModel(SOURCE_MODEL);
      session.getTransactionalEditingDomain().getCommandStack()
          .execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
              String newDescription = "";
              SessionContext context = new SessionContext(session);
              OperationalActivity oa = context.getSemanticElement(OA_ELEMENT_ID);
              try {
                newDescription = oa.getDescription().replace("file:/" + HTML_IMAGE_INVALID_ABSOLUTE_PATH,
                    sourceModelProject.getFile(CAPELLA_PNG).getLocationURI().toURL().toExternalForm());
              } catch (MalformedURLException e) {
              }
              oa.setDescription(newDescription);
            }
          });

      session.save(new NullProgressMonitor());
      session.close(new NullProgressMonitor());

      // migrate a second time to check the absolute path differently and that the expected did not change
      migrateAndCheckSecondTime();

    }
  }

  private void migrateAndCheckFirstTime() throws Exception {
    statuses.clear();
    MigrationHelper.migrateProject(sourceModelProject);

    Session session = getSessionForTestModel(SOURCE_MODEL);
    SessionContext context = new SessionContext(session);

    OperationalActivity oa = context.getSemanticElement(OA_ELEMENT_ID);

    // check the base64 string migration
    checkBase64StringMigration(session, oa);

    // check the absolute path migration
    checkInvalidAbsolutePathMigration(session, oa);

    // check the path beginning with https
    checkPathWithHttp(session, oa);

    // check the project path migration
    checkProjectRelativePathMigration(oa);

    checkLogsAfterFirstMigration();
  }

  private void migrateAndCheckSecondTime() throws Exception {
    statuses.clear();
    MigrationHelper.migrateProject(sourceModelProject);

    Session session = getSessionForTestModel(SOURCE_MODEL);
    SessionContext context = new SessionContext(session);

    OperationalActivity oa = context.getSemanticElement(OA_ELEMENT_ID);

    // check the base64 string migration
    checkBase64StringMigration(session, oa);

    // check the absolute path migration
    checkValidAbsolutePathMigration(session, oa);

    // check the path beginning with https
    checkPathWithHttp(session, oa);

    // check the project path migration
    checkProjectRelativePathMigration(oa);

    // check that the migration log only not found images
    checkLogsAfterSecondMigration();
  }

  private void checkPathWithHttp(Session session, OperationalActivity oa) {
    // check that the OA description has been properly migrated
    Pattern pattern = Pattern.compile(HTML_IMAGE_PATH_PATTERN_HTTP);
    Matcher matcher = pattern.matcher(oa.getDescription());
    assertTrue("The path to the image has not been found with pattern " + HTML_IMAGE_PATH_PATTERN_HTTP,
        matcher.find() && matcher.find());
  }

  /**
   * When the image with the absolute path is found, the base64 should be converted into a image in the images folder in
   * the project.
   */
  private void checkBase64StringMigration(Session session, OperationalActivity oa) throws CoreException {
    // check that an image has been created in the images folder
    List<IFolder> folders = Arrays.asList(sourceModelProject.members()).stream().filter(IFolder.class::isInstance)
        .map(IFolder.class::cast).collect(Collectors.toList());
    assertEquals(1, folders.size());
    assertEquals("Bad imagefolder name", ImageManager.IMAGE_FOLDER_NAME, folders.get(0).getName());
    List<String> imageNames = List.of(folders.get(0).members()).stream()//
        .map(IResource::getName)//
        .filter(name -> {
          Pattern pattern = Pattern.compile("[0-9]{8}_.*?"); //$NON-NLS-1$ ;
          Matcher matcher = pattern.matcher(name);
          return matcher.find();
        })//
        .collect(Collectors.toList());

    assertEquals(
        "There should be only two images in the images folder: one from the OA description and one from the diagram description",
        2, imageNames.size());

    // check that the OA description has been properly migrated
    Pattern pattern = Pattern.compile(HTML_IMAGE_PATH_PATTERN_BASE64);
    Matcher matcher = pattern.matcher(oa.getDescription());
    assertTrue("The path to the image has not been found with pattern " + HTML_IMAGE_PATH_PATTERN_BASE64,
        matcher.find());
    assertEquals(imageNames.get(0), matcher.group(1));
    assertFalse("There should not be any other match for the pattern " + HTML_IMAGE_PATH_PATTERN_BASE64,
        matcher.find());
  }

  private void checkInvalidAbsolutePathMigration(Session session, final OperationalActivity oa) throws CoreException {
    assertTrue(
        HTML_IMAGE_INVALID_ABSOLUTE_PATH
            + " should have been found because this path does not lead to an existing file",
        oa.getDescription().contains(HTML_IMAGE_INVALID_ABSOLUTE_PATH));
  }

  private void checkValidAbsolutePathMigration(Session session, final OperationalActivity oa) throws CoreException {
    SessionContext context = new SessionContext(session);

    // Valid test
    OperationalActivity oa2 = context.getSemanticElement(OA_ELEMENT_ID);
    String expectedPath = SOURCE_MODEL + "/" + ImageManager.IMAGE_FOLDER_NAME + "/" + CAPELLA_PNG;
    Pattern pattern = Pattern.compile(HTML_IMAGE_PATH_PATTERN_COPIED);
    Matcher matcher = pattern.matcher(oa2.getDescription());
    assertTrue("file:/xxxx/junit-workspace/ImagePath%20InRichTextAttribute/capella.png  should have been migrated into "
        + expectedPath, matcher.find());

    // Check the copied file
    long nbImageFiles = Arrays.asList(sourceModelProject.getFolder(ImageManager.IMAGE_FOLDER_NAME).members()).stream()
        .filter(IFile.class::isInstance).map(IFile.class::cast).filter(file -> {
          return "png".equals(file.getFileExtension());
        }).count();
    assertEquals("Bad number of images in the images folder", 3, nbImageFiles);
  }

  private void checkProjectRelativePathMigration(OperationalActivity oa) {
    Pattern pattern = Pattern.compile(HTML_MIGRATED_EXISTING_IMAGE_RELATIVE_PATH_PATTERN);
    Matcher matcher = pattern.matcher(oa.getDescription());
    assertTrue("The project relative path migration failed. The regex"
        + HTML_MIGRATED_EXISTING_IMAGE_RELATIVE_PATH_PATTERN
        + " should have been found. The image path should be migrated from a project relative path to a workspace relative path.",
        matcher.find());

    pattern = Pattern.compile(HTML_NOT_MIGRATED_NOT_EXISTING_IMAGE_RELATIVE_PATH_PATTERN);
    matcher = pattern.matcher(((OperationalActivityPkg) oa.eContainer()).getDescription());
    assertTrue(
        "The project relative path migration failed. The regex" + HTML_NOT_MIGRATED_NOT_EXISTING_IMAGE_RELATIVE_PATH
            + " should have been found. The image path should not be migrated.",
        matcher.find());
  }

  private void checkLogsAfterFirstMigration() {
    synchronizationWithUIThread();
    List<IStatus> warnings = statuses.stream().filter(s -> s.getSeverity() == IStatus.WARNING)
        .collect(Collectors.toList());
    assertEquals("Bad number of warning logs", 3, warnings.size());
    assertTrue("There should be a log that warns that an absolute path could not be migrated",
        warnings.get(0).getMessage().contains(HTML_IMAGE_INVALID_ABSOLUTE_PATH));
    assertTrue("There should be a log that warns that a not migrated relative path has not found",
        warnings.get(1).getMessage().contains(HTML_NOT_MIGRATED_NOT_EXISTING_IMAGE_RELATIVE_PATH));
    assertTrue("There should be a log that warns that an relative path has been migrated but the image is not found",
        warnings.get(2).getMessage().contains(HTML_MIGRATED_NOT_EXISTING_IMAGE_RELATIVE_PATH));
  }

  private void checkLogsAfterSecondMigration() {
    synchronizationWithUIThread();
    List<IStatus> warnings = statuses.stream().filter(s -> s.getSeverity() == IStatus.WARNING)
        .collect(Collectors.toList());
    assertEquals("Bad number of warning logs", 1, warnings.size());
    assertTrue("There should be a log that warns that a not migrated relative path has not found",
        warnings.get(0).getMessage().contains(HTML_NOT_MIGRATED_NOT_EXISTING_IMAGE_RELATIVE_PATH));
  }

  /**
   * Wait the end of the asynchronous calls waiting in UI thread.
   */
  private void synchronizationWithUIThread() {
    while (PlatformUI.getWorkbench().getDisplay().readAndDispatch()) {
      // Do nothing, just wait
    }
  }

  @Override
  protected void tearDown() throws Exception {
    if (logListener != null) {
      Platform.removeLogListener(logListener);
    }
    super.tearDown();
  }
}
