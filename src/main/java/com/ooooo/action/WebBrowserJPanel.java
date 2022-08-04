package com.ooooo.action;

import com.intellij.ui.components.JBTextField;
import com.intellij.ui.jcef.JBCefBrowser;

import javax.swing.*;
import java.awt.*;

/**
 * @author <a href="https://github.com/ooooo-youwillsee">ooooo</a>
 * @since 1.0.0
 */
public class WebBrowserJPanel extends JPanel {

  private JBCefBrowser browser;

  private String url = "http://www.google.com";

  public WebBrowserJPanel() {
    // settings
    setVisible(true);
    setLayout(new BorderLayout());

    // nav
    add(getNavPanel(), BorderLayout.NORTH);
    // browser
    add(getBrowserPanel(), BorderLayout.CENTER);
  }

  private Component getBrowserPanel() {
    browser = new JBCefBrowser();
    browser.loadURL(url);
    JComponent component = browser.getComponent();
    component.setSize(new Dimension(getWidth(), (int) (getSize().getHeight() - 30)));
    return component;
  }

  private Component getNavPanel() {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    // backButton
    JButton backButton = new JButton("back");
    panel.add(backButton);
    backButton.addActionListener(e -> {
      System.out.println(e.toString());
    });

    // forwardButton
    JButton forwardButton = new JButton("forward");
    panel.add(forwardButton);
    backButton.addActionListener(e -> {
      System.out.println(e.toString());
    });

    JBTextField textField = new JBTextField();
    textField.setText(url);
    textField.setSize(200, 30);
    panel.add(textField);

    panel.setSize(new Dimension(getWidth(), 30));
    return panel;
  }


}
