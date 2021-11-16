/*******************************************************************************
 * Copyright (c) 2019, 2020, THALES GLOBAL SERVICES.
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
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.image.ImageManager;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.migration.contribution.ImagePathInRichTextAttributeContribution;
import org.polarsys.capella.core.data.oa.OperationalActivity;
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
  private static final String DINOSAUR_PNG = "capella.png";

  private static final String SOURCE_MODEL = "ImagePath InRichTextAttribute";

  String HTML_IMAGE_PATH_PATTERN_BASE64 = "<img.*?src=\"" + SOURCE_MODEL + "/images/(.*?)\".*?/>"; //$NON-NLS-1$

  String HTML_IMAGE_PATH_PATTERN_COPIED = "<img.*?src=\"(" + SOURCE_MODEL + "/images/capella[0-9]*?\\.png)\".*?/>"; //$NON-NLS-1$

  String HTML_IMAGE_PATH_PATTERN_RELATIVE = "<img.*?src=(\"" + SOURCE_MODEL + "/capella\\.png\").*?/>"; //$NON-NLS-1$

  String HTML_IMAGE_INVALID_ABSOLUTE_PATH = "file:/C:/INVALID/PATH/IMAGE.png"; //$NON-NLS-1$

  private static final String OA_ELEMENT_ID = "b302cb2c-9ebb-4a79-a1cb-2cf8e46fe51b";

  private IProject sourceModelProject;

  @Override
  public void test() throws Exception {
    if (sourceModelProject.exists()) {
      assertTrue("Bad test data: there should be no images folder",
          Arrays.asList(sourceModelProject.members()).stream().filter(IFolder.class::isInstance).count() == 0);

      // migrate the project
      MigrationHelper.migrateProject(sourceModelProject);

      Session session = getSessionForTestModel(SOURCE_MODEL);
      SessionContext context = new SessionContext(session);

      OperationalActivity oa = context.getSemanticElement(OA_ELEMENT_ID);

      // check the base64 string migration
      checkBase64StringMigration(session, oa);

      // check the absolute path migration
      checkAbsolutePathMigration(session, oa);

      // check the project path migration
      checkProjectRelativePathMigration(oa);
    }
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
    IResource[] members = folders.get(0).members();
    assertTrue("There should be only one image in the images folder",
        members.length == 1 && members[0] instanceof IFile);

    String imageName = members[0].getName();

    // check that the description has been properly migrated
    Pattern pattern = Pattern.compile(HTML_IMAGE_PATH_PATTERN_BASE64);
    Matcher matcher = pattern.matcher(oa.getDescription());
    assertTrue("The path to the image has not been found with pattern " + HTML_IMAGE_PATH_PATTERN_BASE64,
        matcher.find());
    assertEquals(imageName, matcher.group(1));
    assertFalse("There should not be any other match for the pattern " + HTML_IMAGE_PATH_PATTERN_BASE64,
        matcher.find());
  }

  private void checkAbsolutePathMigration(Session session, final OperationalActivity oa) throws CoreException {
    assertTrue(
        HTML_IMAGE_INVALID_ABSOLUTE_PATH
            + " should have been found because this path does not lead to an existing file",
        oa.getDescription().contains(HTML_IMAGE_INVALID_ABSOLUTE_PATH));

    // change the absolute path so that the absolute path references the existing image dinosaur in the project.
    session.getTransactionalEditingDomain().getCommandStack()
        .execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

          @Override
          protected void doExecute() {
            String newDescription = "";
            try {
              newDescription = oa.getDescription().replace(HTML_IMAGE_INVALID_ABSOLUTE_PATH,
                  sourceModelProject.getFile(DINOSAUR_PNG).getLocationURI().toURL().toExternalForm());
            } catch (MalformedURLException e) {
            }
            oa.setDescription(newDescription);
          }
        });

    session.save(new NullProgressMonitor());
    session.close(new NullProgressMonitor());
    // migrate the project
    MigrationHelper.migrateProject(sourceModelProject);

    session = getSessionForTestModel(SOURCE_MODEL);
    SessionContext context = new SessionContext(session);

    // Valid test
    OperationalActivity oa2 = context.getSemanticElement(OA_ELEMENT_ID);
    String expectedPath = SOURCE_MODEL + "/" + ImageManager.IMAGE_FOLDER_NAME + "/" + DINOSAUR_PNG;
    Pattern pattern = Pattern.compile(HTML_IMAGE_PATH_PATTERN_COPIED);
    Matcher matcher = pattern.matcher(oa2.getDescription());
    assertTrue(HTML_IMAGE_INVALID_ABSOLUTE_PATH + " should have been migrated into " + expectedPath, matcher.find());

    // Check the copied file
    long nbImageFiles = Arrays.asList(sourceModelProject.getFolder(ImageManager.IMAGE_FOLDER_NAME).members()).stream()
        .filter(IFile.class::isInstance).map(IFile.class::cast).filter(file -> {
          return "png".equals(file.getFileExtension());
        }).count();
    assertEquals("Bad number of images in the images foldes", 2, nbImageFiles);
  }

  private void checkProjectRelativePathMigration(OperationalActivity oa) {
    Pattern pattern = Pattern.compile(HTML_IMAGE_PATH_PATTERN_RELATIVE);
    Matcher matcher = pattern.matcher(oa.getDescription());
    assertTrue("The project relative path migration failed. The regex" + HTML_IMAGE_PATH_PATTERN_RELATIVE
        + " should have been found. The image path should be migrated from a project relative path to a workspace relative path.",
        matcher.find());
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(SOURCE_MODEL);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    sourceModelProject = IResourceHelpers.getEclipseProjectInWorkspace(SOURCE_MODEL);
  }

}
