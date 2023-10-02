/*******************************************************************************
 * Copyright (c) 2021, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.contribution;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.image.Base64ImageHelper;
import org.eclipse.sirius.business.api.image.ImageManager;
import org.eclipse.sirius.business.api.image.RichTextAttributeRegistry;
import org.eclipse.sirius.ext.emf.edit.EditingDomainServices;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.tools.report.util.LogExt;
import org.polarsys.capella.core.data.migration.Activator;
import org.polarsys.capella.core.data.migration.context.MigrationContext;

/**
 * This contribution applies on the attribute containing rich text(html). It will <br/>
 * - replace the image base64 encoded string by a path to a local file that is also created.<br/>
 * - If the absolute path leads to an image, update the absolute path to a relative path and copy the image and the
 * "project"/images folder <br/>
 * - update project relative path to workspace relative path
 * 
 * @author lfasani
 */
public class ImagePathInRichTextAttributeContribution extends AbstractMigrationContribution {
  private static final String HTML_IMAGE_ABSOLUTE_PATH_PATTERN = "<img.*?src=\"((file:/|//|\\\\).*?)\".*?/>"; //$NON-NLS-1$

  private static final String IMAGE_NAME_FORMAT = "yyyyMMdd_HHmmssSSSSSS"; //$NON-NLS-1$

  @Override
  public void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet,
      MigrationContext context) {
    Resource resourceToMigrate = resourceSet.getResource(EcoreUtil2.getURI(context.getResource()), false);

    if (resourceToMigrate != null) {
      IProject project = EcoreUtil2.getFile(resourceToMigrate).getProject();
      if (project != null) {
        Set<EAttribute> richTextAttributes = RichTextAttributeRegistry.INSTANCE.getEAttributes();
        TreeIterator<EObject> allContents = resourceToMigrate.getAllContents();
        Set<String> notMigratedAndNotFoundRelativeFiles = new LinkedHashSet<>();
        Set<String> migratedButNonFoundRelativeFiles = new LinkedHashSet<>();
        while (allContents.hasNext()) {
          EObject eObject = allContents.next();
          // keep only EAttributes that are declared as containing html content
          List<EAttribute> featuresToMigrate = eObject.eClass().getEAllAttributes().stream().filter(feature -> {
            return richTextAttributes.contains(feature);
          }).collect(Collectors.toList());

          for (EStructuralFeature attr : featuresToMigrate) {
            Object value = eObject.eGet(attr);
            if (value instanceof String) {
              // replace the image base64 encoded string
              updateBase64Images(eObject, attr);

              // replace the absolute path
              createFileAndUpdateAttributeFromAbsoluteToRelativePath(eObject, (EAttribute) attr);

              // update the project relative path according to the new path serialization
              updateProjectRelativePath(resourceToMigrate, eObject, (EAttribute) attr,
                  notMigratedAndNotFoundRelativeFiles, migratedButNonFoundRelativeFiles);
            }
          }
        }

        if (!notMigratedAndNotFoundRelativeFiles.isEmpty()) {
          String notFoundFilesPath = notMigratedAndNotFoundRelativeFiles.stream().collect(Collectors.joining(", "));
          Activator.getDefault().getLog()
              .warn(MessageFormat.format(Messages.MigrationAction_Image_RelativePathImageNotFound, notFoundFilesPath));
        }
        if (!migratedButNonFoundRelativeFiles.isEmpty()) {
          String notFoundFilesPath = migratedButNonFoundRelativeFiles.stream().collect(Collectors.joining(", "));
          Activator.getDefault().getLog().warn(MessageFormat
              .format(Messages.MigrationAction_Image_RelativePathImageMigratedButNotFound, notFoundFilesPath));
        }
      } else {
        Activator.getDefault().getLog().error(MessageFormat.format(
            Messages.MigrationAction_Image_ImpossibleToFindProject, resourceToMigrate.getURI().toPlatformString(true)));
      }
    }
  }

  private void updateBase64Images(EObject eObject, EStructuralFeature attr) {
    Map<String, String> createdFiles = new Base64ImageHelper().createFileAndUpdateAttribute(eObject, (EAttribute) attr);
    // Log what have been done
    if (!createdFiles.isEmpty()) {
      String createdFilesPath = createdFiles.keySet().stream().collect(Collectors.joining(", "));
      Activator.getDefault().getLog().info(MessageFormat.format(Messages.MigrationAction_Image_Base64ImageMigrated,
          new EditingDomainServices().getLabelProviderText(eObject), attr.getName(), createdFilesPath));
    }
  }

  /**
   * This migration adds the project at the beginning of the image path.<br/>
   * It converts the path from a project relative path to a workspace relative path.
   */
  private void updateProjectRelativePath(Resource resource, EObject eObject, EAttribute attr,
      Set<String> notMigratedAndNotFoundRelativeFiles, Set<String> migratedButNonFoundRelativeFiles) {
    String oldValue = (String) eObject.eGet(attr);
    String newValue = (String) eObject.eGet(attr);
    if (newValue != null && !newValue.isEmpty()) {
      Pattern pattern = Pattern.compile(ImageManager.HTML_IMAGE_PATH_PATTERN);
      Matcher matcher = pattern.matcher(newValue);

      IProject project = EcoreUtil2.getFile(resource).getProject();
      while (matcher.find()) {
        if (matcher.groupCount() == 1) {
          String path = matcher.group(1);
          String newPath = path;
          // Excluded cases
          if (path.startsWith("file:/") || path.startsWith("http://") || path.startsWith("https://")
              || path.startsWith("//") || path.startsWith("\\\\")) {
            continue;
          }

          String[] splitPath = newPath.split("/");
          // check if the path is just an image name
          // In this case it should be expected in the current project
          if (splitPath.length == 1) {
            newPath = project.getName() + "/" + path;
            newValue = newValue.replace("\"" + path + "\"", "\"" + newPath + "\"");

            if (!project.getWorkspace().getRoot().getFile(new Path(newPath)).exists()) {
              migratedButNonFoundRelativeFiles.add(newPath);
            }
          } else {
            // The path may either a path to a folder inside the current project "folder/image.png" or a path to another
            // project "otherProject/image.png".
            // We check the potential existence of the former. If it succeeds we add the current project name to the
            // path, otherwise we consider that it is the latter case and we do nothing.
            IFile iFileInCurrentProject = ResourcesPlugin.getWorkspace().getRoot()
                .getFile(new Path(project.getName() + "/" + path));
            if (iFileInCurrentProject.exists()) {
              newPath = project.getName() + "/" + path;
              newValue = newValue.replace(path, newPath);
            } else {
              IFile iFileInOtherProject = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path));
              if (!iFileInOtherProject.exists()) {
                notMigratedAndNotFoundRelativeFiles.add(newPath);
              }
            }
            // Note that there is still an issue with the migration because we are not able to distinguish a path to
            // migrate "folder/image.png" from a path to another project "otherProject/image.png" that should not be
            // migrated. So if the image was supposed to be in the current project but not here during the migration,
            // then the path "folder/image.png" will not be migrated to "currentProject/folder/image.png" as it should.
          }
        }
      }
      if (!Objects.equals(newValue, oldValue)) {
        eObject.eSet(attr, newValue);
      }
    }
  }

  /**
   * This method gets the absolute path string from the String attribute of the given eObject, try to get the file and
   * if found copy it into the images folder in the project.<br/>
   * The attribute is updated so that the copied file is referenced.
   * 
   * @param eObject
   *          the eObject to change
   * @param attr
   *          the attribute that must be of type String
   */
  public List<String> createFileAndUpdateAttributeFromAbsoluteToRelativePath(EObject eObject, EAttribute attr) {
    String strValue = (String) eObject.eGet(attr);
    List<String> nonCreatedFiles = new ArrayList<>();
    if (strValue != null) {
      Map<String, IFile> createdFiles = createFiles(eObject, attr, strValue, nonCreatedFiles);

      updateField(eObject, attr, strValue, createdFiles);
    }
    return nonCreatedFiles;
  }

  private void updateField(EObject eObject, EAttribute attr, String strValue, Map<String, IFile> createdFiles) {
    String newStringValue = strValue;
    // change the attribute value to replace with a path to the created file.
    for (String absolutePathFile : createdFiles.keySet()) {
      String replacementString = createdFiles.get(absolutePathFile).getFullPath().toString().replaceFirst("^/", "");
      String quote = "\"";
      newStringValue = newStringValue.replace(quote + absolutePathFile + quote, quote + replacementString + quote);
    }
    if (!Objects.equals(newStringValue, strValue)) {
      eObject.eSet(attr, newStringValue);
    }
  }

  private Map<String, IFile> createFiles(EObject notifier, EAttribute attribute, String strValue,
      List<String> nonCreatedFiles) {
    Map<String, IFile> filesToCreate = new LinkedHashMap<>();

    Pattern pattern = Pattern.compile(HTML_IMAGE_ABSOLUTE_PATH_PATTERN);
    Matcher matcher = pattern.matcher(strValue);
    IFolder imageFolder = getExistingImagesFolder(notifier);
    while (matcher.find()) {
      if (matcher.groupCount() >= 1) {

        Optional<File> fileWithAbsolutePath = getFileFromString(matcher.group(1));
        if (fileWithAbsolutePath.isPresent()) {
          IFile fileToCreate = imageFolder.getFile(fileWithAbsolutePath.get().getName());
          filesToCreate.put(matcher.group(1), fileToCreate);
        } else {
          nonCreatedFiles.add(matcher.group(1));
        }
      }
    }

    Map<String, IFile> createdFiles = createFiles(imageFolder, filesToCreate, notifier, attribute);

    // Log what have been done
    if (!createdFiles.isEmpty()) {
      String createdFilesPath = createdFiles.keySet().stream().collect(Collectors.joining(", "));
      Activator.getDefault().getLog().info(MessageFormat.format(Messages.MigrationAction_Image_AsolutePathImageMigrated,
          new EditingDomainServices().getLabelProviderText(notifier), attribute.getName(), createdFilesPath));
    }
    if (!nonCreatedFiles.isEmpty()) {
      String nonCreatedFilesPath = nonCreatedFiles.stream().collect(Collectors.joining(", "));
      String message = MessageFormat.format(Messages.MigrationAction_Image_AsolutePathImageNotMigrated,
          new EditingDomainServices().getLabelProviderText(notifier), attribute.getName(), nonCreatedFilesPath);
      Activator.getDefault().getLog().warn(message);

      Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DEFAULT);
      IStatus status = new Status(IStatus.WARNING, Activator.PLUGIN_ID, message);
      LogExt.log(logger, status);
    }
    return createdFiles;
  }

  private IFolder getExistingImagesFolder(EObject notifier) {
    IFolder imageFolder;
    IProject project = EcoreUtil2.getProject(notifier);
    Optional<IFolder> optImageFolder = Optional.empty();
    try {
    optImageFolder = Arrays.stream(project.members())
              .filter(IFolder.class::isInstance)
              .map(IFolder.class::cast)
              .filter(folder -> ImageManager.IMAGE_FOLDER_NAME.equals(folder.getName().toLowerCase()))
              .findFirst();
    } catch (CoreException e) {
      Activator.getDefault().getLog()
      .error(MessageFormat.format(Messages.MigrationAction_Image_BrowseProjectToFindExistingImagesProject, project.getName()), e);
    }
    if (optImageFolder.isEmpty()) {
      imageFolder = EcoreUtil2.getProject(notifier).getFolder(ImageManager.IMAGE_FOLDER_NAME);
    } else {
      imageFolder = optImageFolder.get();
    }
    return imageFolder;
}

  private Optional<File> getFileFromString(String fileString) {
    Optional<File> fileOpt = Optional.empty();
    File fileWithAbsolutePath = new File(fileString);
    try {
      if (!fileWithAbsolutePath.exists()) {
        URI uri = new java.net.URL(fileString).toURI();
        fileWithAbsolutePath = new File(uri);
        if (!fileWithAbsolutePath.exists()) {
          String decodedURI = URIUtil.toUnencodedString(uri);
          fileWithAbsolutePath = new File(decodedURI);
        }
      }
      if (fileWithAbsolutePath.exists()) {
        fileOpt = Optional.of(fileWithAbsolutePath);
      }
    } catch (MalformedURLException | URISyntaxException | IllegalArgumentException | SecurityException e) {
    }

    return fileOpt;
  }

  /**
   * Create an image file in the images folder of the project.
   * 
   * @throws CoreException
   *           if the image folder can not be created
   */
  private Map<String, IFile> createFiles(IFolder imageFolder, Map<String, IFile> filesToCopy, EObject contextObject,
      EAttribute attribute) {
    Map<String, IFile> createdFiles = new LinkedHashMap<>();
    if (filesToCopy.isEmpty()) {
      return createdFiles;
    }

    List<String> nonCreatedFiles = new ArrayList<>();

    // create the images folder if needed
    if (!imageFolder.exists()) {
      try {
        imageFolder.create(true, true, null);
      } catch (CoreException e) {
        String nonCreatedFilesPath = filesToCopy.values().stream().map(iFile -> iFile.getFullPath().toString())
            .collect(Collectors.joining(", "));
        Activator.getDefault().getLog()
            .error(MessageFormat.format(Messages.MigrationAction_Image_ImpossibleToCreateImageFolder,
                imageFolder.getFullPath(), new EditingDomainServices().getLabelProviderText(contextObject),
                attribute.getName(), nonCreatedFilesPath), e);
      }
    }

    for (String fileToCopy : filesToCopy.keySet()) {
      IFile targetFileToCreate = filesToCopy.get(fileToCopy);
      if (targetFileToCreate.exists()) {
        Instant now = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(IMAGE_NAME_FORMAT).withZone(ZoneId.systemDefault());
        String strDate = formatter.format(now);
        String newName = targetFileToCreate.getFullPath().lastSegment().replace(
            "." + targetFileToCreate.getFileExtension(), strDate + "." + targetFileToCreate.getFileExtension());
        targetFileToCreate = targetFileToCreate.getParent().getFile(new org.eclipse.core.runtime.Path(newName));
        filesToCopy.put(fileToCopy, targetFileToCreate);
      }

      Optional<File> fileFromString = getFileFromString(fileToCopy);
      if (fileFromString.isPresent()) {
        try (FileInputStream fileInputStream = new FileInputStream(fileFromString.get())) {

          targetFileToCreate.create(fileInputStream, false, null);

          createdFiles.put(fileToCopy, targetFileToCreate);
        } catch (CoreException | IOException e) {
          Activator.getDefault().getLog()
              .error(MessageFormat.format(Messages.MigrationAction_Image_ImpossibleToCreateImage, fileToCopy), e);
          nonCreatedFiles.add(fileToCopy);
        }
      }
    }

    if (nonCreatedFiles.size() > 0) {
      String nonCreatedFilesPath = nonCreatedFiles.stream().collect(Collectors.joining(", "));
      String message = MessageFormat.format(Messages.MigrationAction_Image_ImpossibleToCreateImages,
          new EditingDomainServices().getLabelProviderText(contextObject), attribute.getName(), nonCreatedFilesPath);
      Activator.getDefault().getLog().error(message);

      Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DEFAULT);
      IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, message);
      LogExt.log(logger, status);
    }
    return createdFiles;
  }
}
