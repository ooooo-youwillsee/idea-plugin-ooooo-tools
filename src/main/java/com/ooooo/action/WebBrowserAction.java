package com.ooooo.action;

import com.intellij.ide.plugins.UIComponentVirtualFile;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author <a href="https://github.com/ooooo-youwillsee">ooooo</a>
 * @since 1.0.0
 */
public class WebBrowserAction extends AnAction {

  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    Project project = e.getProject();
    if (project == null) {
      return;
    }

    FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);

    String fileName = RandomStringUtils.random(6);

    WebBrowserJPanel webBrowserJPanel = new WebBrowserJPanel();
    UIComponentVirtualFile uiComponentVirtualFile = new UIComponentVirtualFile(fileName, new UIComponentVirtualFile.Content() {
      @Override
      public @NotNull JComponent createComponent() {
        return webBrowserJPanel;
      }

      @Override
      public @Nullable JComponent getPreferredFocusedComponent() {
        return null;
      }
    });

    fileEditorManager.openFile(uiComponentVirtualFile, true);
  }


}
