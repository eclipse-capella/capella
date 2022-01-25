/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
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
  private static final String HTML_IMAGE_ABSOLUTE_PATH_PATTERN = "<img.*?src=\"(file:/.*?)\".*?/>"; //$NON-NLS-1$

  private static final String IMAGE_NAME_FORMAT = "yyyyMMdd_HHmmssSSS"; //$NON-NLS-1$

  @Override
  public void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet,
      MigrationContext context) {
    Resource resourceToMigrate = resourceSet.getResource(EcoreUtil2.getURI(context.getResource()), false);

    if (resourceToMigrate != null) {
      IProject project = EcoreUtil2.getFile(resourceToMigrate).getProject();
      if (project != null) {
        Set<EAttribute> richTextAttributes = RichTextAttributeRegistry.INSTANCE.getEAttributes();
        TreeIterator<EObject> allContents = resourceToMigrate.getAllContents();
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
              new Base64ImageHelper().createFileAndUpdateAttribute(eObject, (EAttribute) attr);

              // replace the absolute path
              createFileAndUpdateAttributeFromAbsoluteToRelativePath(eObject, (EAttribute) attr);

              // update the project relative path according to the new path serialization
              updateProjectRelativePath(resourceToMigrate, eObject, (EAttribute) attr);
            }
          }
        }
      } else {
        Platform.getLog(Activator.class).error(MessageFormat.format(
            Messages.MigrationAction_Image_ImpossibleToFindProject, resourceToMigrate.getURI().toPlatformString(true)));
      }
    }
  }

  /**
   * This migration adds the project at the beginning of the image path.<br/>
   * It converts the path from a project relative path to a workspace relative path.
   */
  private void updateProjectRelativePath(Resource resource, EObject eObject, EAttribute attr) {
    String oldValue = (String) eObject.eGet(attr);
    String newValue = (String) eObject.eGet(attr);
    if (newValue != null && !newValue.isEmpty()) {
      Pattern pattern = Pattern.compile(ImageManager.HTML_IMAGE_PATH_PATTERN);
      Matcher matcher = pattern.matcher(newValue);

      IProject project = EcoreUtil2.getFile(resource).getProject();
      while (matcher.find()) {
        if (matcher.groupCount() == 1) {
          String path = matcher.group(1);
          if (!path.startsWith(project.getName()) && !path.startsWith("file:/")) {
            newValue = newValue.replace(path, project.getName() + "/" + path);
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
  public void createFileAndUpdateAttributeFromAbsoluteToRelativePath(EObject eObject, EAttribute attr) {
    String strValue = (String) eObject.eGet(attr);
    if (strValue != null) {
      Map<File, IFile> createdFiles = createFiles(eObject, attr, strValue);

      updateField(eObject, attr, strValue, createdFiles);
    }
  }

  private void updateField(EObject eObject, EAttribute attr, String strValue, Map<File, IFile> createdFiles) {
    String newStringValue = strValue;
    // change the attribute value to replace with a path to the created file.
    for (File absolutePathFile : createdFiles.keySet()) {
      try {
        // The following code produces the string the same way it was done in
        // org.polarsys.kitalpha.richtext.widget.tools.ext.types.AbsoluteImageHandler
        String pathToReplace = absolutePathFile.toURI().toURL().toExternalForm();
        String replacementString = createdFiles.get(absolutePathFile).getFullPath().toString().replaceFirst("^/", "");
        String quote = "\"";
        newStringValue = newStringValue.replace(quote + pathToReplace + quote, quote + replacementString + quote);
      } catch (MalformedURLException e) {
        // should not be possible according to how this file is created
      }
    }
    if (!Objects.equals(newStringValue, strValue)) {
      eObject.eSet(attr, newStringValue);
    }
  }

  private Map<File, IFile> createFiles(EObject notifier, EAttribute attribute, String strValue) {
    Pattern pattern = Pattern.compile(HTML_IMAGE_ABSOLUTE_PATH_PATTERN);
    Matcher matcher = pattern.matcher(strValue);
    Map<File, IFile> filesToCreate = new LinkedHashMap<>();
    List<File> nonCreatedFiles = new ArrayList<>();

    IFolder imageFolder = EcoreUtil2.getProject(notifier).getFolder(ImageManager.IMAGE_FOLDER_NAME);
    while (matcher.find()) {
      if (matcher.groupCount() == 1) {
        try {
          URI uri = new java.net.URL(matcher.group(1)).toURI();
          File fileWithAbsolutePath = new File(uri);
          if (fileWithAbsolutePath.exists()) {
            IFile fileToCreate = getFileToCreate(imageFolder, fileWithAbsolutePath, notifier);
            filesToCreate.put(fileWithAbsolutePath, fileToCreate);
          } else {
            nonCreatedFiles.add(fileWithAbsolutePath);
          }
        } catch (MalformedURLException | URISyntaxException e) {
          nonCreatedFiles.add(new File(matcher.group(1)));
        }
      }
    }

    Map<File, IFile> createdFiles = createFiles(imageFolder, filesToCreate, notifier, attribute);

    // Log what have been done
    if (!createdFiles.isEmpty()) {
      String createdFilesPath = createdFiles.keySet().stream().map(File::getAbsolutePath)
          .collect(Collectors.joining(", "));
      Platform.getLog(Activator.class).info(MessageFormat.format(Messages.MigrationAction_Image_AsolutePathImageMigrated,
          new EditingDomainServices().getLabelProviderText(notifier), attribute.getName(), createdFilesPath));
    }
    if (!nonCreatedFiles.isEmpty()) {
      String nonCreatedFilesPath = nonCreatedFiles.stream().map(File::getAbsolutePath)
          .collect(Collectors.joining(", "));
      Platform.getLog(Activator.class).warn(MessageFormat.format(Messages.MigrationAction_Image_AsolutePathImageNotMigrated,
              new EditingDomainServices().getLabelProviderText(notifier), attribute.getName(), nonCreatedFilesPath));
    }
    return createdFiles;
  }

  /**
   * Get the image file to create in the images folder of the project.
   */
  public IFile getFileToCreate(IFolder imageFolder, File fileToCopy, EObject contextObject) {
    String wsImageName = ImageManager.IMAGE_FOLDER_NAME + File.separator + fileToCopy.getName();
    IFile targetImageFile = imageFolder.getProject().getFile(wsImageName);

    return targetImageFile;
  }

  /**
   * Create an image file in the images folder of the project.
   * 
   * @throws CoreException
   *           if the image folder can not be created
   */
  private Map<File, IFile> createFiles(IFolder imageFolder, Map<File, IFile> filesToCopy, EObject contextObject,
      EAttribute attribute) {
    Map<File, IFile> createdFiles = new LinkedHashMap<>();
    if (filesToCopy.isEmpty()) {
        return createdFiles;
    }

    List<File> nonCreatedFiles = new ArrayList<>();

    // create the images folder if needed
    if (!imageFolder.exists()) {
      try {
        imageFolder.create(true, true, null);
      } catch (CoreException e) {
        String nonCreatedFilesPath = filesToCopy.values().stream().map(iFile -> iFile.getFullPath().toString())
            .collect(Collectors.joining(", "));
        Platform.getLog(Activator.class).error(MessageFormat.format(Messages.MigrationAction_Image_ImpossibleToCreateImageFolder,
                imageFolder.getFullPath(), new EditingDomainServices().getLabelProviderText(contextObject),
                attribute.getName(), nonCreatedFilesPath), e);
      }
    }

    for (File fileToCopy : filesToCopy.keySet()) {
      IFile targetFileToCreate = filesToCopy.get(fileToCopy);
      if (targetFileToCreate.exists()) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(IMAGE_NAME_FORMAT);
        String strDate = formatter.format(date);
        String newName = targetFileToCreate.getFullPath().lastSegment().replace(
            "." + targetFileToCreate.getFileExtension(), strDate + "." + targetFileToCreate.getFileExtension());
        targetFileToCreate = targetFileToCreate.getParent().getFile(new org.eclipse.core.runtime.Path(newName));
        filesToCopy.put(fileToCopy, targetFileToCreate);
      }

      try (FileInputStream fileInputStream = new FileInputStream(fileToCopy)) {

        targetFileToCreate.create(fileInputStream, false, null);

        createdFiles.put(fileToCopy, targetFileToCreate);
      } catch (CoreException | IOException e) {
        Platform.getLog(Activator.class).error(
            MessageFormat.format(Messages.MigrationAction_Image_ImpossibleToCreateImage, fileToCopy.getAbsolutePath()),
            e);
        nonCreatedFiles.add(fileToCopy);
      }
    }

    if (nonCreatedFiles.size() > 0) {
      String nonCreatedFilesPath = nonCreatedFiles.stream().map(File::getAbsolutePath)
          .collect(Collectors.joining(", "));
      Platform.getLog(Activator.class).error(MessageFormat.format(Messages.MigrationAction_Image_ImpossibleToCreateImages,
              new EditingDomainServices().getLabelProviderText(contextObject), attribute.getName(),
              nonCreatedFilesPath));
    }
    return createdFiles;
  }
}
